package edu.iis.powp.gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.iis.powp.app.gui.WindowComponent;

public class GUI extends JFrame implements WindowComponent {

  JList list;

  DefaultListModel model;

  int counter = 15;

  public GUI() {
    setLayout(new BorderLayout());
    model = new DefaultListModel();
    list = new JList(model);
    JScrollPane pane = new JScrollPane(list);
    JButton addButton = new JButton("Change order");
    JButton removeButton = new JButton("Remove command");
    JButton addCommand=new JButton("Add new command");
    String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

  //Create the combo box, select item at index 4.
  //Indices start at 0, so 4 specifies the pig.
  JComboBox petList = new JComboBox(petStrings);
  petList.setSelectedIndex(4);
  //
  
    for (int i = 0; i < 15; i++)
      model.addElement("Element " + i);

    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        model.addElement("Element " + counter);
        counter++;
      }
    });
    removeButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (model.getSize() > 0)
          model.removeElementAt(0);
      }
    });

    add(pane, BorderLayout.CENTER);
    add(addButton, BorderLayout.WEST);
    add(removeButton, BorderLayout.EAST);
    add(petList, BorderLayout.NORTH);
    add(addCommand,BorderLayout.AFTER_LAST_LINE);
    
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Command builder");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setContentPane(new GUI());
    frame.setSize(600, 370);
    frame.setVisible(true);
  }

@Override
public void HideIfVisibleAndShowIfHidden() {
	// TODO Auto-generated method stub
	
}
}

