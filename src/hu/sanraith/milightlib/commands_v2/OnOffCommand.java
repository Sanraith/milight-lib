package hu.sanraith.milightlib.commands_v2;

import hu.sanraith.milightlib.Utils;

public class OnOffCommand extends AbstractCommand implements IDuplexLedCommand {
    private final Zone zone;
    private final boolean isOn;
    private final BridgeSession bridgeSession;

    private static final String separator = "00";
    private static final String commandPrefix = "80 00 00 00 11";
    private static final String onCommandPart = "31 00 00 08 04 01 00 00 00"; // Light ON
    private static final String offCommandPart = "31 00 00 08 04 02 00 00 00"; // Light OFF

    public OnOffCommand(Zone zone, boolean isOn, BridgeSession bridgeSession) {
        this.zone = zone;
        this.isOn = isOn;
        this.bridgeSession = bridgeSession;
    }

    @Override
    public byte[] getCommandBytes() {
        String command = isOn ? onCommandPart : offCommandPart;
        byte[] commandBytes = AddChecksum(Utils.hexStringsToByteArray(
                commandPrefix,
                bridgeSession.getSessionIds(),
                separator,
                bridgeSession.getSequenceNumber(),
                separator,
                command,
                zone.getHexValue(),
                separator));

        return commandBytes;
    }

    @Override
    public boolean isResponseValid(byte[] response) {
        String responseHex = Utils.bytesToHexString(response);
        boolean isValid = responseHex.matches(String.join("",
                "^880000000300",
                bridgeSession.getSequenceNumber(),
                "00$"));

        return isValid;
    }
}
