package hu.sanraith.milightlib.commands_v2;

import hu.sanraith.milightlib.Utils;

public class KeepAliveCommand extends AbstractCommand implements IDuplexLedCommand {
    private final String bridgeSessionId;

    public KeepAliveCommand(String bridgeSessionId) {
        this.bridgeSessionId = bridgeSessionId;
    }

    @Override
    public byte[] getCommandBytes() {
        byte[] commandBytes = Utils.hexStringsToByteArray(
                "D0 00 00 00 02",
                bridgeSessionId,
                "00"
        );
        return commandBytes;
    }

    @Override
    public boolean isResponseValid(byte[] response) {
        String responseHex = Utils.bytesToHexString(response);
        boolean isValid = responseHex.matches("^D800000007[0-9a-zA-Z]{12}01$");

        return isValid;
    }
}
