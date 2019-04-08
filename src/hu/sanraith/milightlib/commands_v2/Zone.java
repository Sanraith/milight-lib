package hu.sanraith.milightlib.commands_v2;

import hu.sanraith.milightlib.Utils;

public enum Zone {
    All(0),
    One(1),
    Two(2),
    Three(3),
    Four(4);

    private final byte value;

    Zone(int value) {
        this.value = (byte) value;
    }

    /**
     * @param Number of the zone 1..4
     * @return
     */
    public static Zone fromNumber(int number) {
        switch (number) {
            case 1:
                return One;
            case 2:
                return Two;
            case 3:
                return Three;
            case 4:
                return Four;
            default:
                return All;
        }
    }

    public String getHexValue() {
        return Utils.byteToHexString(value);
    }
}
