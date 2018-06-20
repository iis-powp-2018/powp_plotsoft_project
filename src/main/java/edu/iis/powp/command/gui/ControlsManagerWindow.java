package edu.iis.powp.command.gui;

import edu.iis.powp.app.Application;
import edu.iis.powp.app.gui.WindowComponent;
import edu.iis.powp.command.manager.PlotterControlsManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlsManagerWindow extends JFrame implements WindowComponent {


	private static final long serialVersionUID = 9204679248304669948L;

	private PlotterControlsManager controlsManager;

	public ControlsManagerWindow(Application application) {
		controlsManager = new PlotterControlsManager(application);

		this.setTitle("Controls Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());


		GridBagConstraints c = new GridBagConstraints();

		JButton btnZoomIn = new JButton("Zoom In");
		btnZoomIn.addActionListener((ActionEvent e) -> controlsManager.zoomInWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnZoomIn, c);

		JButton btnZoomOut = new JButton("Zoom Out");
		btnZoomOut.addActionListener((ActionEvent e) -> controlsManager.zoomOutWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnZoomOut, c);

		JButton btnRotateLeft = new JButton("Rotate Left");
		btnRotateLeft.addActionListener((ActionEvent e) -> controlsManager.rotateLeftWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnRotateLeft, c);

		JButton btnRotateRight = new JButton("Rotate Right");
		btnRotateRight.addActionListener((ActionEvent e) -> controlsManager.rotateRightWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnRotateRight, c);

		JButton btnFlipVertical = new JButton("Flip Vertical");
		btnFlipVertical.addActionListener((ActionEvent e) -> controlsManager.flipVerticalWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnFlipVertical, c);

		JButton btnFlipHorizontal = new JButton("Flip Horizontal");
		btnFlipHorizontal.addActionListener((ActionEvent e) -> controlsManager.flipHorizontalWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnFlipHorizontal, c);

		JButton btnMoveRight = new JButton("Move Right");
		btnMoveRight.addActionListener((ActionEvent e) -> controlsManager.moveRightWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnMoveRight, c);

		JButton btnMoveLeft = new JButton("Move Left");
		btnMoveLeft.addActionListener((ActionEvent e) -> controlsManager.moveLeftWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnMoveLeft, c);

		JButton btnMoveUp = new JButton("Move Up");
		btnMoveUp.addActionListener((ActionEvent e) -> controlsManager.moveUpWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnMoveUp, c);

		JButton btnMoveDown = new JButton("Move Down");
		btnMoveDown.addActionListener((ActionEvent e) -> controlsManager.moveDownWindow());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnMoveDown, c);


		JButton btnHistory = new JButton("Enable / Disable history");
		btnHistory.addActionListener((ActionEvent e) -> controlsManager.enableHistory());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		content.add(btnHistory, c);
	}


	/**
	 *
	 */
	@Override
	public void HideIfVisibleAndShowIfHidden() {
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}
}
