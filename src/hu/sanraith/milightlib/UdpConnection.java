package hu.sanraith.milightlib;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UdpConnection {
    private final static Logger LOGGER = Logger.getLogger(UdpConnection.class.getName());

    private static final int SOCKET_TIMEOUT = 1000;
    private static final int SOCKET_BUFFER_SIZE = 2048;
    private static final String UTF_8_CHARSET = "UTF-8";

    private DatagramSocket socket;
    private InetSocketAddress address;
    private Object lock = new Object();

    public UdpConnection(String address, int port) throws SocketException {
        this.address = new InetSocketAddress(address, port);
        socket = new DatagramSocket(port);
        socket.setSoTimeout(SOCKET_TIMEOUT);
    }

    public void send(byte[] data) throws SocketException {
        synchronized (lock) {
            DatagramPacket packet = new DatagramPacket(data, data.length, address);

            try {
                socket.send(packet);
                LOGGER.log(Level.INFO, String.format("SENT: %s - \"%s\"", packet.getSocketAddress(), Utils.bytesToHexString(data)));
            } catch (IOException e) {
                LOGGER.log(Level.WARNING, "Could not send DatagramPacket!", e);
            }
        }
    }

    public void silentSend(byte[] data) {
        synchronized (lock) {
            DatagramPacket packet = new DatagramPacket(data, data.length, address);
            try {
                socket.send(packet);
            } catch (IOException e) {
            }
        }
    }

    public byte[] receive() throws IOException {
        synchronized (lock) {
            byte[] buffer = new byte[SOCKET_BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            byte data[] = new byte[packet.getLength()];
            System.arraycopy(buffer, packet.getOffset(), data, 0, packet.getLength());
            LOGGER.log(Level.INFO, String.format("RECEIVED: %s - \"%s\"", packet.getSocketAddress(), Utils.bytesToHexString(data)));

            return data;
        }
    }

    public byte[] receiveOrNull() {
        byte[] data = null;

        try {
            data = receive();
        } catch (SocketTimeoutException e) {
            LOGGER.log(Level.INFO, "Socket timed out.");
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Could not receive packet!", e);
        }

        return data;
    }

    public List<byte[]> receiveAll() {
        List<byte[]> dataList = new ArrayList<>();

        byte[] data;
        while ((data = receiveOrNull()) != null) {
            dataList.add(data);
        }

        return dataList;
    }

    public void close() {
        socket.close();
    }
}
