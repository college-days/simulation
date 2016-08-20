package com.seu.joker.toolkit.seugui.model;

public class Task {
	private String desiredPose = null; // desired pose
	private float time = 0.0f; // move in given time
	private boolean changeable = false; // can be changed by other tasks
	private int adjustFoot = 0; // can adjust foot?which?
	private String nextTaskName = null; // next task name
	private boolean changeFoot = false; // will the foot changed in next task

	public String getDesiredPose() {
		return desiredPose;
	}

	public void setDesiredPose(String desiredPose) {
		this.desiredPose = desiredPose;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public boolean isChangeable() {
		return changeable;
	}

	public void setChangeable(boolean changeable) {
		this.changeable = changeable;
	}

	public int getAdjustFoot() {
		return adjustFoot;
	}

	public void setAdjustFoot(int adjustFoot) {
		this.adjustFoot = adjustFoot;
	}

	public String getNextTaskName() {
		return nextTaskName;
	}

	public void setNextTaskName(String nextTaskName) {
		this.nextTaskName = nextTaskName;
	}

	public boolean isChangeFoot() {
		return changeFoot;
	}

	public void setChangeFoot(boolean changeFoot) {
		this.changeFoot = changeFoot;
	}
}
