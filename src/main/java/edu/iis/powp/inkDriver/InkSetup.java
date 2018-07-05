package edu.iis.powp.inkDriver;

import edu.iis.powp.app.Application;

public class InkSetup {

    private final static float INITIAL_INK_LVL = 500f;

    public static void InkSetupDriver(Application application){
        InkGuiLogic inkGuiLogic = new InkGuiLogic();

        InkGui inkGui = new InkGui(inkGuiLogic);
        inkGuiLogic.updateMaxInkLevel(INITIAL_INK_LVL);
        application.addWindowComponent("Ink controller", inkGui);

        InkGuiObserver inkGuiObserver = new InkGuiObserver(inkGuiLogic);
        application.getDriverManager().getChangePublisher().addSubscriber(inkGuiObserver);
        inkGuiLogic.setApplication(application);

        CommandEditor commandEditor = CommandEditor.getInstance();
        commandEditor.setApplication(application);
        application.addWindowComponent("Command Editor", commandEditor);
    }
}
