package edu.iis.powp.command.gui;

import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class ControlsManagerWindow extends JFrame implements WindowComponent {

	private static final long serialVersionUID = 9204679248304669948L;

	public ControlsManagerWindow(PlotterCommandManager commandManager) {
		this.setTitle("Controls Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());


		GridBagConstraints c = new GridBagConstraints();

		JButton btnZoomIn = new JButton("Zoom In");
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnZoomIn, c);

		JButton btnZoomOut = new JButton("Zoom Out");
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnZoomOut, c);

		JButton btnRotateLeft = new JButton("Rotate Left");
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnRotateLeft, c);

		JButton btnRotateRight = new JButton("Rotate Right");
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnRotateRight, c);

		JButton btnFlipVertical = new JButton("Flip Vertical");
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnFlipVertical, c);

		JButton btnFlipHorizontal = new JButton("Flip Horizontal");
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnFlipHorizontal, c);
	}



	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}
