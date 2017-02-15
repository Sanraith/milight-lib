package hu.sanraith.milightlib;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LedBridgeConnection {
    private final static Logger LOGGER = Logger.getLogger(LedBridgeConnection.class.getName());

    private static final int SOCKET_TIMEOUT = 1000;
    private static final int SOCKET_BUFFER_SIZE = 2048;
    private static final String UTF_8_CHARSET = "UTF-8";

    private DatagramSocket socket;
    private InetSocketAddress address;

    public LedBridgeConnection(String address, int port) throws SocketException {
        this.address = new InetSocketAddress(address, port);
        socket = new DatagramSocket(port);
        socket.setSoTimeout(SOCKET_TIMEOUT);
    }

    public void send(String data) throws SocketException {
        send(data.getBytes(Charset.forName(UTF_8_CHARSET)));
    }

    public void send(byte[] data) throws SocketException {
        DatagramPacket packet = new DatagramPacket(data, data.length, address);

        try {
            socket.send(packet);
            LOGGER.log(Level.INFO, String.format("SENT: %s - \"%s\"", packet.getSocketAddress(), new String(data, Charset.forName(UTF_8_CHARSET))));
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could not send DatagramPacket!", e);
        }
    }

    public void silentSend(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, address);
        try {
            socket.send(packet);
        } catch (IOException e) {
        }
    }

    public String receive() throws IOException {
        byte[] buffer = new byte[SOCKET_BUFFER_SIZE];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);
        String data = new String(packet.getData(), packet.getOffset(), packet.getLength(), Charset.forName(UTF_8_CHARSET));
        LOGGER.log(Level.INFO, String.format("RECEIVED: %s - \"%s\"", packet.getSocketAddress(), data));

        return data;
    }

    public String receiveOrNull() {
        String data = null;

        try {
            data = receive();
        } catch (SocketTimeoutException e) {
            LOGGER.log(Level.INFO, "Socket timed out.");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could not receive packet!", e);
        }

        return data;
    }

    public List<String> receiveAll() {
        List<String> dataList = new ArrayList<>();

        String data;
        while ((data = receiveOrNull()) != null) {
            dataList.add(data);
        }

        return dataList;
    }

    public void close() {
        socket.close();
    }
}
