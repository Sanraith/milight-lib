package hu.sanraith.milightlib.commands_v2;

public abstract class AbstractCommand {
    public boolean isResponseValid(byte[] response) {
        return true;
    }

    protected static byte[] AddChecksum(byte[] bytes) {
        byte[] result = new byte[bytes.length + 1];
        System.arraycopy(bytes, 0, result, 0, bytes.length);

        int checksumLength = 11;
        int start = bytes.length - checksumLength;
        int sum = 0;
        for (int i = start; i < bytes.length; i++) {
            sum = (sum + bytes[i]) % 256;
        }
        result[bytes.length] = (byte) sum;

        return result;
    }
}
