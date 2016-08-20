package com.seu.joker.toolkit.seugui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.vecmath.Quat4f;

import com.seu.joker.toolkit.seugui.controller.GUIMainController;
import com.seu.joker.toolkit.seugui.model.NaoPose;
import com.seu.joker.toolkit.seugui.model.Task;
import com.seu.joker.toolkit.seugui.naomodel.Roller;
import com.seu.joker.toolkit.seugui.util.JointNameTransform;
import com.seu.joker.toolkit.seugui.util.NaoFileReader;

public class BodyControllerPanelInFAT extends JPanel {
	private static final long serialVersionUID = 2399393357765205183L;
	private static int pos = 0;
	private GUIMainController controller = null;
	private HashMap<String, NaoPose> poseMap = new HashMap<String, NaoPose>();
	private ArrayList<InnerBody> listeners = new ArrayList<InnerBody>();
	private Task currentTask = null;
	private ArrayList<String> listeningJointsArrayList = null;

	public ArrayList<String> getListeningJointsArrayList() {
		return listeningJointsArrayList;
	}

	private String[] allBodyNames = null;

	public HashMap<String, NaoPose> getPoseMap() {
		return poseMap;
	}

	public void addAListener(InnerBody panel) {
		listeners.add(panel);
	}

	public void removeAListener(InnerBody panel) {
		listeners.remove(panel);
	}

	public HashMap<String, Float> getListeningJoints() {
		HashMap<String, Float> listeningJoints = new HashMap<String, Float>();
		for (InnerBody panel : listeners) {
			listeningJoints.put(JointNameTransform.transform(panel.bodyName),
					(float) panel.slider.getValue());
			this.listeningJointsArrayList.add(JointNameTransform
					.transform(panel.bodyName));
		}
		return listeningJoints;
	}

	public BodyControllerPanelInFAT(GUIMainController controller, Task task) {
		//	Toolkit.getDefaultToolkit().setDynamicLayout(true);
		this.listeningJointsArrayList = new ArrayList<String>();

		this.controller = controller;
		this.currentTask = task;
		this.allBodyNames = JointNameTransform.nameInTree;
		this.setBackground(Color.WHITE);
		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createTitledBorder("Body"));
		NaoFileReader.readNaoPoseFile(poseMap);
	}

	private class InnerBody extends JPanel {
		private static final long serialVersionUID = -3793670910665273347L;
		private String bodyName = null;
		private JLabel current = null;
		private JLabel currentAngleValue = null;
		private JButton closeButton = null;
		private JSlider slider = null;
		private JLabel desired = null;
		private JTextField desiredAngleValue = null;

		public InnerBody(String bodyName) {
			this.bodyName = bodyName;
		}

		public boolean init() {
			String jointName = JointNameTransform.transform(bodyName);
			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createTitledBorder(bodyName + " ("
					+ jointName + ") "));
			this.setPreferredSize(new Dimension(250, 110));
			//	JLabel mass = new JLabel("Mass(Kg): ");
			//	JLabel massValue = new JLabel();
			//	mass.setPreferredSize(new Dimension(50, 15));
			//	massValue.setPreferredSize(new Dimension(30, 15));
			//  panel.add(mass);
			//  panel.add(massValue);
			float currentAngle = 0.0f;
			try {
				currentAngle = poseMap.get(currentTask.getDesiredPose())
						.findAngleByName(jointName);
			} catch (Exception e) {
				System.err.println("cannot get the current angle");
				//return false;
			}
			Roller currentRoller = controller.getMyUniverse().getNao()
					.getRollerByName(bodyName);
			int maxAngle = currentRoller.getMaxAngle();
			int minAngle = currentRoller.getMinAngle();

			current = new JLabel("Current:");
			currentAngleValue = new JLabel(String.valueOf(currentAngle));
			current.setPreferredSize(new Dimension(65, 20));
			currentAngleValue.setPreferredSize(new Dimension(30, 30));
			this.add(current);
			this.add(currentAngleValue);

			closeButton = new JButton("close");
			closeButton.setPreferredSize(new Dimension(70, 20));
			closeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					BodyControllerPanelInFAT.this.remove(InnerBody.this);
					BodyControllerPanelInFAT.this.revalidate();
					removeAListener(InnerBody.this);
				}
			});
			this.add(closeButton);

			slider = new JSlider(minAngle, maxAngle, (int) currentAngle);
			naoModelChange(bodyName, currentAngle);
			slider.setBackground(Color.WHITE);
			slider.setPreferredSize(new Dimension(100, 25));
			slider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					//TODO：还有添加NaoModel的响应
					naoModelChange(bodyName, slider.getValue());
					desiredAngleValue.setText(String.valueOf(slider.getValue()));

				}
			});
			this.add(slider);

			desired = new JLabel("Desired:");
			desired.setPreferredSize(new Dimension(50, 25));
			this.add(desired);

			desiredAngleValue = new JTextField();
			desiredAngleValue.setPreferredSize(new Dimension(40, 24));
			desiredAngleValue.setText(String.valueOf(slider.getValue()));
			desiredAngleValue.setDocument(new IntTextDocument());
			desiredAngleValue.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					slider.setValue(Integer.parseInt(desiredAngleValue
							.getText()));
				}
			});
			this.add(desiredAngleValue);
			return true;
		}

		public boolean update() {
			String jointName = JointNameTransform.transform(this.bodyName);
			float currentAngle = 0.0f;
			try {
				currentAngle = poseMap.get(currentTask.getDesiredPose())
						.findAngleByName(jointName);
			} catch (Exception e) {
				System.err.println("cannot get the current angle");
				return false;
			}
			this.currentAngleValue.setText(String.valueOf(currentAngle));
			this.slider.setValue((int) currentAngle);
			return true;
		}
	}
	
	private class IntTextDocument extends PlainDocument {
		private static final long serialVersionUID = -725813810683135033L;

		@Override
		public void insertString(int offs, String str, AttributeSet a)
				throws BadLocationException {
			if (str == null)
				return;
			String oldString = getText(0, getLength());
			String newString = oldString.substring(0, offs) + str
					+ oldString.substring(offs);
			try {
				Integer.parseInt(newString + "0");

				//向Document中插入文本前判断  
				super.insertString(offs, str, a);
			} catch (NumberFormatException e) {
				System.out.println(newString);
			}

		}
	}

	public void addBoneToBodyControllerPanel(String bodyName) {
		InnerBody panel = new InnerBody(bodyName);
		if (panel.init()) {
			System.out.println(bodyName);
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = pos++;
			BodyControllerPanelInFAT.this.add(panel, c);
			addAListener(panel);
			BodyControllerPanelInFAT.this.revalidate();
		}
	}
/*	public void addBoneToBodyControllerPanel(String bodyName) {
		final JPanel panel = new JPanel(new FlowLayout());
		String jointName = JointNameTransform.transform(bodyName);
		panel.setBorder(BorderFactory.createTitledBorder(bodyName + " ("
				+ jointName + ") "));
		panel.setPreferredSize(new Dimension(250, 70));
		//	JLabel mass = new JLabel("Mass(Kg): ");
		//	JLabel massValue = new JLabel();
		//	mass.setPreferredSize(new Dimension(50, 15));
		//	massValue.setPreferredSize(new Dimension(30, 15));
		//  panel.add(mass);
		//  panel.add(massValue);
		float currentAngle = 0.0f;
		try {
			currentAngle = poseMap.get(currentTask.getDesiredPose())
					.findAngleByName(jointName);
		} catch (Exception e) {
			return;
		}
		Roller currentRoller = controller.getMyUniverse().getNao()
				.getRollerByName(bodyName);
		int maxAngle = currentRoller.getMaxAngle();
		int minAngle = currentRoller.getMinAngle();

		JLabel current = new JLabel("Current: ");
		JLabel currentAngleValue = new JLabel(String.valueOf(currentAngle));
		current.setPreferredSize(new Dimension(50, 15));
		currentAngleValue.setPreferredSize(new Dimension(30, 15));
		panel.add(current);
		panel.add(currentAngleValue);

		JButton closeButton = new JButton("close");
		closeButton.setPreferredSize(new Dimension(20, 15));
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BodyControllerPanelInFAT.this.remove(panel);
				removeAListener(panel);
				validate();
			}
		});
		panel.add(closeButton);

		//		addLabel("Desired: ", panel, c, 0, 2);
		//        desire.setPreferredSize(new Dimension(50, 25));
		//        panel.add(desire,c);

		JSlider slider = new JSlider(minAngle, maxAngle, (int) currentAngle);
		slider.setPreferredSize(new Dimension(200, 20));
		panel.add(slider);

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = pos++;
		this.add(panel, c);
		addAListener(panel);
		validate();
		this.repaint();
	}
*/
	public boolean update() {
		NaoFileReader.readNaoPoseFile(poseMap);
		for (String bodyName : allBodyNames) {
			String jointName = JointNameTransform.transform(bodyName);
			float currentAngle = 0.0f;
			try {
				currentAngle = poseMap.get(currentTask.getDesiredPose())
						.findAngleByName(jointName);
			} catch (Exception e) {
				System.err.println("cannot get the " + jointName + " angle");
				//		return false;
			}
			naoModelChange(bodyName, currentAngle);
		}

		for (InnerBody panel : listeners) {
			//			String jointName = JointNameTransform.transform(panel.bodyName);
			//			float currentAngle = 0.0f;
			//			try {
			//				currentAngle = poseMap.get(currentTask.getDesiredPose())
			//						.findAngleByName(jointName);
			//			} catch (Exception e) {
			//				System.err.println("cannot get the current angle");
			//				return false;
			//			}
			//			panel.currentAngleValue.setText(String.valueOf(currentAngle));
			//			panel.slider.setValue((int) currentAngle);
			panel.update();
		}
		return true;
	}

	protected void naoModelChange(String bodyName, float value) {
		float rot = (float) (value * Math.PI / 180);
		if (bodyName.endsWith("shoulder") || bodyName.equals("lelbow")) {
			rot += Math.PI / 2;
		} else if (bodyName.equals("relbow")) {
			rot -= Math.PI / 2;
		}
		float cos = (float) Math.cos(rot / 2);
		float sin = (float) Math.sin(rot / 2);
		Quat4f rot4f = null;
		if (bodyName.equals("neck")) {
			rot4f = new Quat4f(0.0f, sin, 0.0f, cos);
		} else if (bodyName.equals("head")) {
			rot4f = new Quat4f(-sin, 0.0f, 0.0f, cos);
		} else if (bodyName.endsWith("shoulder") || bodyName.endsWith("thigh")
				|| bodyName.endsWith("shank") || bodyName.endsWith("ankle")) {
			rot4f = new Quat4f(-sin, 0.0f, 0.0f, cos);
		} else if (bodyName.endsWith("upperarm") || bodyName.endsWith("foot")) {
			rot4f = new Quat4f(0.0f, 0.0f, sin, cos);
		} else if (bodyName.endsWith("elbow")) {
			rot4f = new Quat4f(0.0f, -sin, 0.0f, cos);
		} else if (bodyName.endsWith("rlowerarm")) {
			rot4f = new Quat4f(-sin, 0.0f, 0.0f, cos);
		} else if (bodyName.endsWith("llowerarm")) {
			rot4f = new Quat4f(sin, 0.0f, 0.0f, cos);
		} else if (bodyName.endsWith("rhip1")) {
			rot4f = new Quat4f(0.7072f * sin, 0.7072f * sin, 0.0f, cos);
		} else if (bodyName.endsWith("lhip1")) {
			rot4f = new Quat4f(0.7072f * sin, -0.7072f * sin, 0.0f, cos);
		} else if (bodyName.endsWith("hip2")) {
			rot4f = new Quat4f(0.0f, 0.0f, sin, cos);
		}
		if (bodyName.equals("rhip1")) {
			controller.getMyUniverse().getNao().getRlegTransform3d()
					.setRotation(rot4f);
			controller
					.getMyUniverse()
					.getNao()
					.getRlegTransformGroup()
					.setTransform(
							controller.getMyUniverse().getNao()
									.getRlegTransform3d());
		} else if (bodyName.equals("lhip1")) {
			controller.getMyUniverse().getNao().getLlegTransform3d()
					.setRotation(rot4f);
			controller
					.getMyUniverse()
					.getNao()
					.getLlegTransformGroup()
					.setTransform(
							controller.getMyUniverse().getNao()
									.getLlegTransform3d());
		} else {
			controller.getMyUniverse().getNao().getRollerByName(bodyName)
					.setRot(rot4f);

		}
	}
}
