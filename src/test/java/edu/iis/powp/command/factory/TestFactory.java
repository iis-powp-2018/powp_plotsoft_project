package edu.iis.powp.command.factory;

import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.SetPositionCommand;

final class TestFactory {

    public static void main(String[] args) throws Throwable {
        CommandRegistry cf = new CommandRegistry();
        cf.registerCommand(DrawToCommand.class, int.class, int.class);
        cf.registerCommand(SetPositionCommand.class, int.class, int.class);
        System.out.println(cf.getRegisteredCommands().toString());
    }
}
