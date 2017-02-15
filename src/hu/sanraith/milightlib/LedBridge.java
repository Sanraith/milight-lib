package hu.sanraith.milightlib;

import hu.sanraith.milightlib.commands.ILedCommand;

import java.net.SocketException;
import java.util.List;
import java.util.logging.Logger;

public class LedBridge {
    private static Logger LOGGER = Logger.getLogger(LedBridge.class.getName());

    private LedBridgeConnection conn;

    public LedBridge(LedBridgeConnection conn) {
        this.conn = conn;
    }

    public void executeCommand(ILedCommand command) throws SocketException {
        byte[] commandBytes = command.getCommandBytes();
        conn.send(commandBytes);
    }

    public void executeCommands(List<ILedCommand> commands) throws SocketException {
        for (int i = 0; i < commands.size(); i++) {
            if (i > 0) {
                Utils.sleep(150);
            }
            executeCommand(commands.get(i));
        }
    }

    public void close() {
        conn.close();
    }
}
