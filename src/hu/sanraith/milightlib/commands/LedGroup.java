package hu.sanraith.milightlib.commands;

public enum LedGroup {
    One(0),
    Two(1),
    Three(2),
    Four(3),
    All(4),
    None(-1);

    private final int value;

    LedGroup(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
