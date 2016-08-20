package com.seu.joker.toolkit.seugui.animation;
import java.util.Enumeration;

import javax.media.j3d.Alpha;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupOnBehaviorPost;


public class MyInterpolator extends RotationInterpolator {

	int actionID;


	public void setActionID(int actionID) {
		this.actionID = actionID;
		postID = new WakeupOnBehaviorPost(this, actionID);
	}

	public int getActionID() {
		return actionID;
	}

	WakeupOnBehaviorPost postID;
	
	public WakeupOnBehaviorPost getPostID() {
		return postID;
	}
	
	MyInterpolator(Alpha alpha, TransformGroup target, Transform3D axis, float start, float end, int actionID) {
		super(alpha, target, axis, start, end);
//		this.mAlpha = alpha;
		this.actionID = actionID;
		postID = new WakeupOnBehaviorPost(this, actionID);		
	}
	
	@Override
	public void processStimulus(Enumeration criteria){
		Alpha thisAlpha = this.getAlpha();
		if(thisAlpha.finished()){
			this.postId(this.actionID);
			System.out.println("sending finished message: " + this.actionID);
			this.setEnable(false);
		}
	super.processStimulus(criteria);
	}
	

}
