package com.seu.joker.toolkit.seugui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.seu.joker.toolkit.seugui.controller.GUIMainController;

public class GUIMainFrame extends JFrame {
	private static final long serialVersionUID = 2167200850304953947L;
	private LeftPanel leftPanel = null;
	private RightPanel rightPanel = null;
	private GUIMainController controller = null;
	
	public LeftPanel getLeftPanel(){
		return leftPanel;
	}
	
	public RightPanel getRightPanel(){
		return rightPanel;
	}

	public GUIMainController getMainController() {
		return controller;
	}
	public GUIMainFrame(){
		controller = new GUIMainController();
		this.init();
	}
	
	public void init() {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager
			//		.setLookAndFeel("com.sun.java.swing.plaf.MotifLookAndFeel");
			//	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager
			//		.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			//UIManager
			//		.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
		} catch (Exception e) {

		}
		JPanel p = new JPanel(new GridBagLayout());

		leftPanel = new LeftPanel(this);
		rightPanel = new RightPanel(controller);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(editMenu);
		
		JMenuItem onconnect = new JMenuItem("Connect");
		//		JMenuItem onbeam = new JMenuItem("Beam");
		
		editMenu.add(onconnect);
		//		editMenu.add(onbeam);
		
		p.setBorder(BorderFactory.createTitledBorder("GUI"));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		p.add(leftPanel, c);
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 20;
		p.add(rightPanel, c);
		p.setBackground(Color.WHITE);
		this.add(p);
		
		this.setTitle("SEU AGENT GUI");
		this.setBounds(5, 5, 550, 730);
		this.setFont(new Font("Arial", Font.ITALIC, 30));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	
	}

	public static void main(String[] a){
		new GUIMainFrame();
	}
}
