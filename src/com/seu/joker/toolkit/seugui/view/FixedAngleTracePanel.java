package com.seu.joker.toolkit.seugui.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.seu.joker.toolkit.seugui.animation.AnimationMyUniverse;
import com.seu.joker.toolkit.seugui.controller.GUIMainController;
import com.seu.joker.toolkit.seugui.model.NaoPose;
import com.seu.joker.toolkit.seugui.model.Task;
import com.seu.joker.toolkit.seugui.util.JointNameTransform;
import com.seu.joker.toolkit.seugui.util.NaoFileReader;
import com.seu.joker.toolkit.seugui.util.NaoFileWriter;

public class FixedAngleTracePanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = -533847841558814027L;

	private GUIMainController controller = null;

	private BodyControllerPanelInFAT bodyContollerPanel = null;
	private JPanel taskControllerPanel = null;
	private Task task = null;
	private Vector<String> taskList = new Vector<String>();
	private HashMap<String, Task> taskMap = new HashMap<String, Task>();
	private HashMap<String, NaoPose> poseMap = new HashMap<String, NaoPose>();

	private JComboBox chooseTask = null;
	private JTextField currentTask = new JTextField();
	private JTextField duration = new JTextField();
	private JTextField poseName = new JTextField();
	private JTextField nextTask = new JTextField();
	private JTextField changeable = new JTextField();
	private JTextField adjustFoot = new JTextField();
	
	
	private JButton singleButton = new JButton("Single");
	private JButton reloadButton = new JButton("Reload");
	private JButton newButton = new JButton("New");
	private JButton saveButton = new JButton("Save");
	
	private AnimationMyUniverse mAnimationMyUniverse;
	public static int counter = 0;

	public BodyControllerPanelInFAT getBodyControllerPanel() {
		return bodyContollerPanel;
	}
	
	public JPanel getTaskControllerPanel(){
		return taskControllerPanel;
	}

	public FixedAngleTracePanel(GUIMainController controller) {
		this.controller = controller;
		this.task = new Task();
		NaoFileReader.readNaoPoseFile(poseMap);
		init();
	}
	
	private void init(){
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.WHITE);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;
        c.gridy = 0;
		initBodyControls();
		add(bodyContollerPanel, c);

        c.gridx = 0;
        c.gridy = 1;
        initTaskControls();
		add(taskControllerPanel, c);
	}
	
	private void addLabel(String name, JComponent component,
			GridBagConstraints c,
			int x, int y) {
		c.gridx = x;
		c.gridy = y;
		JLabel l = new JLabel(name, SwingConstants.RIGHT);
		l.setFont(new Font("Arial", Font.PLAIN, 12));
		l.setPreferredSize(new Dimension(80, 25));
		component.add(l, c);
	}

	private void initBodyControls() {
		bodyContollerPanel = new BodyControllerPanelInFAT(controller, task);
	}

	private void initTaskControls() {
		taskControllerPanel = new JPanel(new GridBagLayout());
		taskControllerPanel.setBackground(Color.WHITE);
		taskControllerPanel.setBorder(BorderFactory.createTitledBorder("Task"));
		GridBagConstraints c = new GridBagConstraints();
		c.ipadx = 30;
		c.fill = GridBagConstraints.HORIZONTAL;
		 
		addLabel("Choose Task: ", taskControllerPanel, c, 0, 1);
		c.gridx = 2;
		c.gridy = 1;

		if (NaoFileReader.readNaoFile(taskList, taskMap)) {
			chooseTask = new JComboBox(taskList);
		}
		chooseTask.setSelectedItem(taskList.get(0));
		chooseTask.setPreferredSize(new Dimension(120, 28));
		chooseTask.setAutoscrolls(true);
		chooseTask.addActionListener(this);
		taskControllerPanel.add(chooseTask, c);
		
		addLabel("Current Task: ", taskControllerPanel, c, 0, 2);
		c.gridx = 2;
		c.gridy = 2;
		currentTask.setPreferredSize(new Dimension(120, 28));
		taskControllerPanel.add(currentTask, c);
		 
		addLabel("Duration: ", taskControllerPanel, c, 0, 3);

		c.gridx = 2;
		c.gridy = 3;
		duration.setPreferredSize(new Dimension(120, 28));
		taskControllerPanel.add(duration, c);

		addLabel("Pose Name: ", taskControllerPanel, c, 0, 4);

		c.gridx = 2;
		c.gridy = 4;
		poseName.setPreferredSize(new Dimension(120, 28));
		taskControllerPanel.add(poseName, c);
	     
		addLabel("Next Task: ", taskControllerPanel, c, 0, 5);

		c.gridx = 2;
		c.gridy = 5;
		nextTask.setPreferredSize(new Dimension(120, 28));
		taskControllerPanel.add(nextTask, c);

		addLabel("Changeable: ", taskControllerPanel, c, 0, 6);

		c.gridx = 2;
		c.gridy = 6;
		changeable.setPreferredSize(new Dimension(120, 28));
		taskControllerPanel.add(changeable, c);

		addLabel("Adjust Foot: ", taskControllerPanel, c, 0, 7);

		c.gridx = 2;
		c.gridy = 7;
		adjustFoot.setPreferredSize(new Dimension(120, 28));
		taskControllerPanel.add(adjustFoot, c);

		c.gridx = 0;
		c.gridy = 8;
		JPanel southPanel = new JPanel(new GridLayout(2, 2));
		southPanel.setBackground(Color.WHITE);
		singleButton.addActionListener(this);
		reloadButton.addActionListener(this);
		newButton.addActionListener(this);
		saveButton.addActionListener(this);

		southPanel.add(singleButton);
		southPanel.add(reloadButton);
		southPanel.add(newButton);
		southPanel.add(saveButton);
		add(southPanel, c);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == chooseTask){
			String taskName = chooseTask.getSelectedItem().toString();
			Task tempTask = taskMap.get(taskName);

			//here we don't do "task=taskMap.get(taskName);"
			//because the member variable task will be passed to BodyControllerPanel
			task.setDesiredPose(tempTask.getDesiredPose());
			task.setTime(tempTask.getTime());
			task.setChangeable(tempTask.isChangeable());
			task.setAdjustFoot(tempTask.getAdjustFoot());
			task.setNextTaskName(tempTask.getNextTaskName());
			task.setChangeFoot(tempTask.isChangeFoot());
			System.out.println(taskName);
			currentTask.setText(taskName);
			duration.setText(String.valueOf(task.getTime()));
			poseName.setText(task.getDesiredPose());
			nextTask.setText(task.getNextTaskName());
			changeable.setText(String.valueOf(task.isChangeable()));
			adjustFoot.setText(String.valueOf(task.getAdjustFoot()));

			//更新动态产生的那些每个关节的panel
			bodyContollerPanel.update();
		} else if (source == singleButton) {
			String taskName = chooseTask.getSelectedItem().toString();
			Task tempTask = taskMap.get(taskName);
			
			if (counter == 0) {
				if (taskName.equals(tempTask.getNextTaskName())) {
					JOptionPane.showMessageDialog(null,
									"currentTaskName equals to nextTaskName, please select other task",
									"alert",
							JOptionPane.ERROR_MESSAGE);
				} else {
				mAnimationMyUniverse = new AnimationMyUniverse(chooseTask
						.getSelectedItem().toString(), taskList, taskMap,
						poseMap);
				counter++;
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"please press reload button", "alert",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (source == reloadButton) {
			try {
			mAnimationMyUniverse.releaseAnimation();
			} catch (NullPointerException e1) {
				JOptionPane.showMessageDialog(null, "no NaoModel exist",
						"alert", JOptionPane.ERROR_MESSAGE);
			}
			counter = 0;
		} else if (source == newButton) {
			String taskName = currentTask.getText();
			if (!taskMap.containsKey(taskName)) {
				Task tempTask = new Task();
				tempTask.setDesiredPose(poseName.getText());
				tempTask.setTime(Float.parseFloat(duration.getText()));
				if (changeable.getText().equals("true")) {
					tempTask.setChangeable(true);
				}
				tempTask.setNextTaskName(nextTask.getText());
				tempTask.setAdjustFoot(Integer.parseInt(adjustFoot.getText()));
				//	if(change)
				//tempTask.setChangeFoot(changeFoot)
				taskMap.put(taskName, tempTask);
				//NaoFileWriter.write2NaoFile(taskList, taskMap);
				chooseTask.addItem(taskName);
			}
		} else if (source == saveButton) {
			save();
		}
	}

	public void save() {
		saveTask();
		savePose();
	}

	public void saveTask() {
		/**
		 * debug for can not edit fixAngleTrace Panel
		 */
		String taskName = chooseTask.getSelectedItem().toString();
		Task newTask = taskMap.get(taskName);

		newTask.setDesiredPose(poseName.getText());
		newTask.setNextTaskName(nextTask.getText());
		newTask.setTime(Float.parseFloat(duration.getText()));
		newTask.setAdjustFoot(Integer.parseInt(adjustFoot.getText()));
		newTask.setChangeable(Boolean.parseBoolean(changeable.getText()));
		
		taskMap.put(taskName, newTask);

		/**
		 * 
		 */
		NaoFileWriter.write2NaoFile(taskList, taskMap);
	}

	public void savePose() {
		if (!NaoFileReader.poseList.contains(poseName.getText())) {
			NaoFileReader.poseList.add(poseName.getText());
			NaoPose naoPose = new NaoPose();
			for (String joint : JointNameTransform.nameInPoseFile) {
				HashMap<String, Float> jointsMap = bodyContollerPanel
						.getListeningJoints();
				if (jointsMap.containsKey(joint)) {
					naoPose.pushAngle(joint, jointsMap.get(joint));
				}
			}
			poseMap.put(poseName.getText(), naoPose);
			NaoFileWriter.write2NaoPoseFile(poseMap);
		} else if (NaoFileReader.poseList.contains(poseName.getText())) {
			NaoPose naoPose = poseMap.get(poseName.getText());
			HashMap<String, Float> jointsMap = bodyContollerPanel
					.getListeningJoints();
			for (String joint : naoPose.getNames()) {
				//				if (jointsMap.containsKey(joint)) {
				//					naoPose.pushAngle(joint, jointsMap.get(joint));
				//				} else {
				//					naoPose.pushAngle(joint, 0.0f);
				//				}
				System.out.println("done1");
				naoPose.pushAngle(joint, naoPose.getJointsMap().get(joint));
			}
			for (int i = 0; i < bodyContollerPanel
					.getListeningJointsArrayList().size(); i++) {
				System.out.println("cleatha111"
						+ "  "
						+ bodyContollerPanel.getListeningJointsArrayList().get(
								i));
			}
			
			for (String joint : bodyContollerPanel
					.getListeningJointsArrayList()) {
				if (naoPose.getJointsMap().containsKey(joint)) {
					System.out.println("done2");
					if (jointsMap.get(joint) != naoPose.getJointsMap().get(
							joint)) {
						naoPose.pushAngle(joint, jointsMap.get(joint));
					}
				} else {
					naoPose.pushAngle(joint, jointsMap.get(joint));
				}

			}
			poseMap.put(poseName.getText(), naoPose);
			NaoFileWriter.write2NaoPoseFile(poseMap);
		}
	}
}
