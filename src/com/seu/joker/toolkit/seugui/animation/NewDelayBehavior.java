package com.seu.joker.toolkit.seugui.animation;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.WakeupCondition;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnBehaviorPost;
import javax.media.j3d.WakeupOr;


public class NewDelayBehavior extends Behavior {
	
	ArrayList<MyInterpolator> rotationsArrayList;
	
	public ArrayList<MyInterpolator> getRotationsArrayList() {
		return rotationsArrayList;
	}

	public MyInterpolator[] rotationsInterpolator;
	public Alpha[] alphas;
	public WakeupCriterion[] criterion;
	public WakeupCondition trigger;
	public static final int FINISHED = 0;
	public static final int FINISHED1 = 1;
	public static final int FINISHED2 = 2;
	int i = 0;
	
	public NewDelayBehavior(){
		super();
	}
	
	public NewDelayBehavior(ArrayList<MyInterpolator> rotationsArrayList){
		super();
		this.rotationsArrayList = rotationsArrayList;
	
		rotationsInterpolator = new MyInterpolator[rotationsArrayList.size()];
		alphas = new Alpha[rotationsArrayList.size()];
		
		for(int i=0; i<rotationsArrayList.size(); i++){
			rotationsInterpolator[i] = rotationsArrayList.get(i);
		}
		
		for(int i=0; i<rotationsArrayList.size(); i++){
			alphas[i] = rotationsArrayList.get(i).getAlpha();
		}
	}
	
	public void resetRotationsArrayList(ArrayList<MyInterpolator> newrotationsArrayList){
		this.rotationsArrayList = newrotationsArrayList;
		
		rotationsInterpolator = new MyInterpolator[rotationsArrayList.size()];
		alphas = new Alpha[rotationsArrayList.size()];
		
		for(int i=0; i<rotationsArrayList.size(); i++){
			rotationsInterpolator[i] = rotationsArrayList.get(i);
		}
		
		for(int i=0; i<rotationsArrayList.size(); i++){
			alphas[i] = rotationsArrayList.get(i).getAlpha();
		}
		
	}
	@Override
	public void initialize() {
		criterion = new WakeupCriterion[rotationsArrayList.size()];
		for(int i=0; i<this.rotationsArrayList.size(); i++){
			criterion[i] = rotationsArrayList.get(i).getPostID();
		}
		trigger = new WakeupOr(criterion);
		wakeupOn(trigger);
	}

/*		@Override
	public void processStimulus(Enumeration criteria) {
	WakeupCriterion wakeup;
	int i;
		for(wakeup = (WakeupCriterion)criteria.nextElement(), i=0; i<rotationsArrayList.size(); i++, wakeup = (WakeupCriterion)criteria.nextElement()){
			if(wakeup instanceof WakeupOnBehaviorPost){
				WakeupOnBehaviorPost post = (WakeupOnBehaviorPost) wakeup;
				int postID = post.getPostId();
				if(postID == i){
					rotationsArrayList.get(i+1).setEnable(true);
					long time = System.currentTimeMillis();
					rotationsArrayList.get(i+1).getAlpha().setStartTime(time);
					System.out.println("clea" + postID);
				}
			}
		}
			
	}*/
	
	@Override
	public void processStimulus(Enumeration criteria) {
		WakeupCriterion wakeup;
		System.out.println("start the actions");
			while(criteria.hasMoreElements()) {
			wakeup = (WakeupCriterion)criteria.nextElement();
			if(wakeup instanceof WakeupOnBehaviorPost){
				WakeupOnBehaviorPost post = (WakeupOnBehaviorPost) wakeup;
				if(post.getPostId() == i){
					int k = i+1;
					if(k >= rotationsArrayList.size()){
						i = 0;
						System.out.println(i);
						break;
					}
					rotationsArrayList.get(i+1).setEnable(true);
					long time = System.currentTimeMillis();
					rotationsArrayList.get(i+1).getAlpha().setStartTime(time);
					System.out.println("clea" + i);
					i++;
				}
				
			}
			wakeupOn(trigger);
		}
	}
}