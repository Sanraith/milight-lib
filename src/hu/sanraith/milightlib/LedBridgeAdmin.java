package hu.sanraith.milightlib;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LedBridgeAdmin {
    private static Logger LOGGER = Logger.getLogger(LedBridgeAdmin.class.getName());

    public static final String DEFAULT_BROADCAST_ADDRESS = "192.168.1.255";

    private static final int ADMIN_PORT = 48899;
    private static final int COMMAND_PORT = 8899;
    private static final String DISCOVERY_COMMAND = "Link_Wi-Fi";

    public static List<String> findBridgeAddresses(String broadcastAddress) {
        List<String> addressList = new ArrayList<>();
        LedBridgeConnection conn = null;

        try {
            conn = new LedBridgeConnection(broadcastAddress, ADMIN_PORT);
            conn.send(DISCOVERY_COMMAND);

            List<String> dataList = conn.receiveAll();

            for (String data : dataList) {
                String address = getIpFromUdpMessage(data);
                if (address != null && !addressList.contains(address)) {
                    addressList.add(address);
                    LOGGER.log(Level.INFO, String.format("Found bridge at %s.", address));
                }
            }
        } catch (SocketException e) {
            LOGGER.log(Level.WARNING, "Could not create broadcast connection!", e);
        } finally {
            if (conn != null) {
                conn.close();
            }
        }

        return addressList;
    }

    public static LedBridge findFirstBridge(String broadcastAddress) {
        LedBridge bridge = null;

        try {
            List<String> addresses = findBridgeAddresses(broadcastAddress);
            if (!addresses.isEmpty()) {
                String firstAddress = addresses.get(0);
                bridge = new LedBridge(new LedBridgeConnection(firstAddress, COMMAND_PORT));
            } else {
                LOGGER.log(Level.WARNING, "No Bridges found!");
            }

        } catch (SocketException e) {
            LOGGER.log(Level.WARNING, "Cannot create connection!", e);
        }

        return bridge;
    }

    private static String getIpFromUdpMessage(String data) {
        if (data == null) {
            return null;
        }

        String address = data.split(",")[0];
        if (!Utils.isValidIp(address)) {
            return null;
        }

        return address;
    }
}
