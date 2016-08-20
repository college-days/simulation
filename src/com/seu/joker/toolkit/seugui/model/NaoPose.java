package com.seu.joker.toolkit.seugui.model;

import java.util.ArrayList;
import java.util.HashMap;

public class NaoPose {
	private HashMap<String, Float> joints = new HashMap<String, Float>();
	private ArrayList<String> names = new ArrayList<String>();
	private String[] namesArray = null;

	public void pushAngle(String name, Float angle) {
		joints.put(name, angle);
		names.add(name);
	}

	public Float findAngleByName(String name) {
		//		System.out.println(joints.get(name) + "");
		//		return joints.get(name);
		//		if (joints.get(name) == null) {
		//			return 0.0f;
		//		} else {
			return joints.get(name);
		//		}
	}

	public HashMap<String, Float> getJointsMap() {
		return joints;
	}

	//	public ArrayList<String> getNamesArraylist() {
	//		return this.names;
	//	}

	public String[] getNames() {
		namesArray = new String[names.size()];
		for (int i = 0; i < names.size(); i++) {
			namesArray[i] = names.get(i);
		}
		return namesArray;
	}
}
