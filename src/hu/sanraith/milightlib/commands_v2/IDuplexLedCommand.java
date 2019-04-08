package hu.sanraith.milightlib.commands_v2;

import hu.sanraith.milightlib.commands.ILedCommand;

public interface IDuplexLedCommand extends ILedCommand {
    boolean isResponseValid(byte[] response);
}
