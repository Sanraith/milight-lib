package hu.sanraith.milightlib;

import hu.sanraith.milightlib.commands_v2.IDuplexLedCommand;

import java.net.SocketException;
import java.util.logging.Logger;

public class MilightBridgeV2 {
    private static final Logger LOGGER = Logger.getLogger(MilightBridgeV2.class.getName());
    public static final int COMMAND_PORT = 5987;

    private final String bridgeAddress;
    private final int commandPort;
    private UdpConnection connection;

    public MilightBridgeV2(String bridgeAddress) {
        this(bridgeAddress, COMMAND_PORT);
    }

    public MilightBridgeV2(String bridgeAddress, int commandPort) {
        this.bridgeAddress = bridgeAddress;
        this.commandPort = commandPort;
    }

    public void init() throws SocketException {
        UdpConnection udpConnection = new UdpConnection(bridgeAddress, commandPort);
        this.connection = udpConnection;
    }

    public void flush() {
        connection.receiveAll();
    }

    public void close() {
        connection.close();
    }

    public byte[] sendCommandForResponse(IDuplexLedCommand command) throws SocketException {
        connection.send(command.getCommandBytes());
        byte[] response = connection.receiveOrNull();
        response = response == null ? new byte[0] : response;

        return response;
    }

    public boolean sendCommand(IDuplexLedCommand command) throws SocketException {
        connection.send(command.getCommandBytes());
        byte[] response = connection.receiveOrNull();
        response = response == null ? new byte[0] : response;

        return command.isResponseValid(response);
    }
}
