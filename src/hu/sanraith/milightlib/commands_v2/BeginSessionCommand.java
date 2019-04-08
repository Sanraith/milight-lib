package hu.sanraith.milightlib.commands_v2;

import hu.sanraith.milightlib.Utils;

import java.util.Random;

public class BeginSessionCommand extends AbstractCommand implements IDuplexLedCommand {
    private static final String commandHex = "20 00 00 00 16 02 62 3A D5 ED A3 01 AE 08 2D 46 61 41 A7 F6 DC AF D3 E6 00 00 1E";
    private final Random random = new Random();

    public BridgeSession parseSessionFromResponse(byte[] response) {
        if (response == null || response.length < 21) {
            return new BridgeSession("00", "00", 0);
        }
        String id1 = Utils.byteToHexString(response[19]);
        String id2 = Utils.byteToHexString(response[20]);
        return new BridgeSession(id1, id2, random.nextInt(256));
    }

    @Override
    public byte[] getCommandBytes() {
        byte[] commandBytes = Utils.hexStringToByteArray(commandHex);
        return commandBytes;
    }

    @Override
    public boolean isResponseValid(byte[] response) {
        String responseHex = Utils.bytesToHexString(response);
        boolean isValid = responseHex.matches("^28000000110002[0-9a-zA-Z]{28}00$");

        return isValid;
    }
}
