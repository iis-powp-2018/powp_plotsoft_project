package edu.iis.powp.command.factory;

import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.SetPositionCommand;

final class TestFactory {

    public static void main(String[] args) throws Throwable {
        CommandRegistry cf = new CommandRegistry();
        cf.registerBasicCommand(DrawToCommand.class, int.class, int.class);
        cf.registerBasicCommand(SetPositionCommand.class, int.class, int.class);
        System.out.println(cf.getRegisteredBasicCommands().toString());
    }
}
