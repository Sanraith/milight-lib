package hu.sanraith.milightlib.commands;

public class OnOffCommand implements ILedCommand {
    private LedGroup group;
    private boolean setOn;

    public OnOffCommand(LedGroup group, boolean setOn) {
        this.group = group;
        this.setOn = setOn;
    }

    @Override
    public byte[] getCommandBytes() {
        if (group == LedGroup.All && setOn) {
            return LedCommands.RGBWOn.clone();
        } else if (group == LedGroup.All && !setOn) {
            return LedCommands.RGBWOff.clone();
        } else {
            byte[] commandBytes = LedCommands.RGBWGroup1AllOn.clone();
            commandBytes[0] += (byte) (group.getValue() * 2 + (setOn ? 1 : 0));
            return commandBytes;
        }
    }
}
