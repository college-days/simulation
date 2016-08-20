package com.seu.joker.toolkit.seugui.util;

import java.util.HashMap;

public class JointNameTransform {
	public static final String[] nameInTree = { "neck", "head", "lshoulder",
			"lupperarm", "lelbow", "llowerarm", "rshoulder", "rupperarm",
			"relbow", "rlowerarm", "lhip1", "lhip2", "lthigh", "lshank",
			"lankle", "lfoot", "rhip1", "rhip2", "rthigh", "rshank", "rankle",
			"rfoot" };
	public static final String[] nameInFile = { "hj1", "hj2", "laj1", "laj2",
			"laj3", "laj4", "raj1", "raj2", "raj3", "raj4", "llj1", "llj2",
			"llj3", "llj4", "llj5", "llj6", "rlj1", "rlj2", "rlj3", "rlj4",
			"rlj5", "rlj6" };
	public static final String[] nameInPoseFile = { "hj1", "hj2", "raj1",
			"raj2", "raj3", "raj4", "laj1", "laj2", "laj3", "laj4", "rlj1",
			"rlj2", "rlj3", "rlj4", "rlj5", "rlj6", "llj1", "llj2", "llj3",
			"llj4", "llj5", "llj6" };
	private static HashMap<String, String> transMap = null;

	static {
		transMap = new HashMap<String, String>();
		for (int i = 0; i != nameInTree.length; ++i) {
			transMap.put(nameInTree[i], nameInFile[i]);
		}
	}
	public static String transform(String jointName) {
		return transMap.get(jointName);
	}
}
