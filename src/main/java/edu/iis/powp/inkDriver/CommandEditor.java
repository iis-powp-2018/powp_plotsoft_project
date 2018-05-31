package edu.iis.powp.inkDriver;

import edu.iis.client.plottermagic.IPlotter;
import edu.iis.powp.app.Application;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.DrawToCommand;
import edu.iis.powp.command.IPlotterCommand;
import edu.iis.powp.command.SetPositionCommand;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CommandEditor extends JFrame implements WindowComponent {

    private static CommandEditor ourInstance = new CommandEditor();
    private JTextField valX, valY, setPosCommandCountLabel, drawToCommandCountLabel;
    private ArrayList<IPlotterCommand> commandList = new ArrayList<>();
    private int setPosCnt = 0, drawToCnt = 0;
    private IPlotter currentPlotter;
    private Application application;


    private CommandEditor(){
        this.setTitle("Command Editor");
        this.setSize(600, 200);
        Container content = this.getContentPane();
        content.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JTextField coordX = new JTextField("X Coordinate:");
        coordX.setEditable(false);
        coordX.setHorizontalAlignment(JTextField.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(coordX, c);

        JTextField coordY = new JTextField("Y Coordinate:");
        coordY.setEditable(false);
        coordY.setHorizontalAlignment(JTextField.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(coordY, c);

        valX = new JTextField("0", 8);
        valX.setEditable(true);
        coordY.setHorizontalAlignment(JTextField.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 1;
        c.weighty = 1;
        content.add(valX, c);

        valY = new JTextField("0", 8);
        valX.setEditable(true);
        coordY.setHorizontalAlignment(JTextField.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 1;
        c.weighty = 1;
        content.add(valY, c);

        setPosCommandCountLabel = new JTextField("setPositon Commands: "+setPosCnt);
        setPosCommandCountLabel.setEditable(false);
        setPosCommandCountLabel.setHorizontalAlignment(JTextField.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 0;
        c.weighty = 1;
        content.add(setPosCommandCountLabel, c);

        drawToCommandCountLabel = new JTextField("drawTo Commands: "+drawToCnt);
        drawToCommandCountLabel.setEditable(false);
        drawToCommandCountLabel.setHorizontalAlignment(JTextField.CENTER);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 1;
        c.weighty = 1;
        content.add(drawToCommandCountLabel, c);

        JButton btnAddSetPosition = new JButton("Add setPosition Command");
        btnAddSetPosition.addActionListener((ActionEvent e) -> addSetPositionCmnd());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 2;
        c.weighty = 1;
        content.add(btnAddSetPosition, c);

        JButton btnAddDrawTo = new JButton("Add drawTo Command");
        btnAddDrawTo.addActionListener((ActionEvent e) -> addDrawToCmnd());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 2;
        c.weighty = 1;
        content.add(btnAddDrawTo, c);

        JButton btnExecuteCommands = new JButton("Execute Commands");
        btnExecuteCommands.addActionListener((ActionEvent e) -> executeCmnds());
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.gridx = 2;
        c.weighty = 1;
        content.add(btnExecuteCommands, c);

    }

    @Override
    public void HideIfVisibleAndShowIfHidden() {
        if (this.isVisible()) {
            this.setVisible(false);
        } else {
            this.setVisible(true);
        }
    }

    public void setApplication(Application application){
        this.application = application;
        currentPlotter = application.getDriverManager().getCurrentPlotter();
    }

    private void addSetPositionCmnd(){
        int x,y;
        try {
            x = Integer.parseInt(valX.getText());
            y = Integer.parseInt(valY.getText());
            commandList.add(new SetPositionCommand(x,y));
            setPosCnt++;
            updateCountLabels();
        }catch(Exception e){
            informationPopUp();
        }
    }

    private void addDrawToCmnd(){
        int x,y;
        try {
            x = Integer.parseInt(valX.getText());
            y = Integer.parseInt(valY.getText());
            commandList.add(new DrawToCommand(x,y));
            drawToCnt++;
            updateCountLabels();
        }catch(Exception e){
            informationPopUp();
        }
    }

    private void executeCmnds(){
        for(IPlotterCommand command : commandList){
            command.execute(currentPlotter);
        }
        commandList.clear();
        setPosCnt = 0;
        drawToCnt = 0;
        updateCountLabels();
    }

    private void updateCountLabels(){
        setPosCommandCountLabel.setText("setPositon Commands: "+setPosCnt);
        drawToCommandCountLabel.setText("drawTo Commands: "+drawToCnt);
    }

    private void informationPopUp(){
        JOptionPane.showMessageDialog(ourInstance, "You have entered the wrong coordinates!", "Wrong Coordinates!", JOptionPane.WARNING_MESSAGE);
    }

    public static CommandEditor getInstance() {
        return ourInstance;
    }
}
