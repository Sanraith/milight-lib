package hu.sanraith.milightlib.commands_v2;

import hu.sanraith.milightlib.Utils;

public class BridgeSession {
    private String sessionId1;
    private String sessionId2;
    private byte sequenceNumber;

    public String getSessionId1() {
        return sessionId1;
    }

    public String getSessionId2() {
        return sessionId2;
    }

    public String getSessionIds() {
        return sessionId1 + sessionId2;
    }

    public String getSequenceNumber() {
        return Utils.byteToHexString(sequenceNumber);
    }

    public BridgeSession(String sessionId1, String sessionId2, int sequenceNumber) {
        this.sessionId1 = sessionId1;
        this.sessionId2 = sessionId2;
        this.sequenceNumber = (byte) (sequenceNumber % 256);
    }

    public BridgeSession next() {
        return new BridgeSession(sessionId1, sessionId2, sequenceNumber + 1);
    }
}
