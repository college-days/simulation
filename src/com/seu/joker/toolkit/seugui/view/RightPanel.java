package com.seu.joker.toolkit.seugui.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.seu.joker.toolkit.seugui.controller.GUIMainController;

public class RightPanel extends JPanel {
	private static final long serialVersionUID = -5580514403124309611L;
	private JTabbedPane tabbedPane;
	private FixedAngleTracePanel fat;
	private JointAngleControllerPanel jac;
	private JointRateControllerPanel jrc;
	
	public FixedAngleTracePanel getFixedAngleTracePanel(){
		return fat;
	}
	
	public JointAngleControllerPanel getJointAngleControllerPanel(){
		return jac;
	}
	
	public JointRateControllerPanel getJointRateControllerPanel(){
		return jrc;
	}

	public RightPanel(GUIMainController controller) {
		tabbedPane = new JTabbedPane();
		//tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBackground(Color.WHITE);
		fat = new FixedAngleTracePanel(controller);
		JScrollPane scrollPane = new JScrollPane(fat);
		scrollPane.setPreferredSize(new Dimension(300, 550));
		jac = new JointAngleControllerPanel();
		jrc = new JointRateControllerPanel();
		tabbedPane.add("Fixed Angle Trace", scrollPane);
		//		tabbedPane.add("Joint Angle Controller", jac);
		//		tabbedPane.add("Joint Rate Controller", jrc);

		this.add(tabbedPane);
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(300, 600));
		this.setAutoscrolls(true);
	}
}
