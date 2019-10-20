package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.plaf.basic.BasicArrowButton;

import global.GConstants.EToolBar;

public class GToolBar extends JToolBar {
	// attributes
	private static final long serialVersionUID = 1L;
	// components
	// associations
	private GDrawingPanel drawingPanel;
	private JPanel shapeButtonPanel;
	private JPanel transformButtonPanel;

    public GToolBar() {
    	ActionHandler actionHandler = new ActionHandler();
    	this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    	
		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		this.shapeButtonPanel = new JPanel();
		this.shapeButtonPanel.setBorder(BorderFactory.createTitledBorder("Shape"));
		this.add(shapeButtonPanel);
		for(EToolBar eToolBar : EToolBar.values()) {
			JRadioButton button = new JRadioButton();
			button.setIcon(new ImageIcon(eToolBar.getIconFileName()));
			button.setSelectedIcon(new ImageIcon(eToolBar.getIconSLTFileName()));
			button.setFocusable(false);
			group.add(button);
			shapeButtonPanel.add(button);
			button.addActionListener(actionHandler);
			button.setActionCommand(eToolBar.name());
		}
		
		this.transformButtonPanel = new JPanel();
		this.transformButtonPanel.setLayout(new BoxLayout(this.transformButtonPanel, BoxLayout.X_AXIS) );
		this.transformButtonPanel.setBorder(BorderFactory.createTitledBorder("Transform"));
		this.add(this.transformButtonPanel);
			BasicArrowButton arrowButton = new BasicArrowButton(BasicArrowButton.NORTH);
			arrowButton.setActionCommand("LineThickUp");
			arrowButton.addActionListener(actionHandler);
			this.transformButtonPanel.add(arrowButton);
			arrowButton = new BasicArrowButton(BasicArrowButton.SOUTH);
			arrowButton.setActionCommand("LineThickDown");
			arrowButton.addActionListener(actionHandler);
			this.transformButtonPanel.add(arrowButton);
			JLabel label = new JLabel("LineThick");
			this.transformButtonPanel.add(label);

		this.add(Box.createHorizontalStrut(200));
   }

	public void initialize(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
		((JRadioButton) shapeButtonPanel.getComponent(EToolBar.eRectangle.ordinal())).doClick();
	}

	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getActionCommand().equals("LineThickUp")) {
				System.out.println("up");
				drawingPanel.makeLineThcikUp();
			}else if(event.getActionCommand().equals("LineThickDown")) {
				drawingPanel.makeLineThcikDown();
			}else {
				drawingPanel.setActionCommand(EToolBar.valueOf(event.getActionCommand()).getSelectedTool());
			}
		}		
	}

}
