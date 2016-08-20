package com.seu.joker.toolkit.seugui.view;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class JointAngleControllerPanel extends JPanel {
	private static final long serialVersionUID = 7292575025803554251L;
	JTextField mass = new JTextField();
	JTextField nextField = new JTextField();
	JTextField choiField = new JTextField();
	
	JButton nextButton = new JButton("Next");
	
	JList choice = new JList();
	
	public JointAngleControllerPanel() {
		init();
		this.setVisible(true);
	}
	
	private void init(){
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        
        c.gridx = 0;
        c.gridy = 0;
        add(initBodyControls(), c);
        
        c.gridx = 0;
        c.gridy = 1;
        add(initChoiceControls(), c);
		
	}
	
	 void addLabel(String name, JComponent component, GridBagConstraints c, int x, int y) {
	        c.gridx = x;
	        c.gridy = y;
	        JLabel l = new JLabel(name, SwingConstants.RIGHT);
	        l.setPreferredSize(new Dimension(60, 28));
	        component.add(l, c);
	    }
	
	private JPanel initBodyControls(){
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createTitledBorder("Body"));
		
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 30;
        c.fill = GridBagConstraints.HORIZONTAL;
        
        addLabel("Mass (/Kg): ", panel, c, 0, 0);
        
        c.gridx = 2;
        c.gridy = 0;
        mass.setPreferredSize(new Dimension(150, 28));
        panel.add(mass, c);
        
        return panel;
	}
	
	private JPanel initChoiceControls(){
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 30;
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        c.gridy = 0;
        panel.add(nextButton, c);
        
        c.gridx = 1;
        c.gridy = 0;
        nextField.setPreferredSize(new Dimension(150,28));
        panel.add(nextField,c);
        
        c.gridx = 0;
        c.gridy = 1;
        panel.add(choice,c);
        
        c.gridx = 1;
        c.gridy = 1;
        choiField.setPreferredSize(new Dimension(150,28));
        panel.add(choiField,c);
 
        return panel;
	}
}
