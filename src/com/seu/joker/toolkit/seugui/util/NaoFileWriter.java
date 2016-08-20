package com.seu.joker.toolkit.seugui.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

import com.seu.joker.toolkit.seugui.model.NaoPose;
import com.seu.joker.toolkit.seugui.model.Task;

public class NaoFileWriter {
	private final static String NAOFILE = "nao.txt";
	private final static String NAOPOSEFILE = "nao_pose.txt";

	public static boolean write2NaoFile(Vector<String> tasksName,
			HashMap<String, Task> taskMap) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(
					new BufferedWriter(new FileWriter(NAOFILE)));
			for (String taskName : tasksName) {
				Task task = taskMap.get(taskName);
				writer.println(taskName);
				writer.print(task.getDesiredPose() + " ");
				writer.print(task.getTime() + " ");
				writer.print((task.isChangeable() ? "1" : "0") + " ");
				writer.print(task.getAdjustFoot() + " ");
				writer.print(task.getNextTaskName() + " ");
				writer.print((task.isChangeFoot() ? "1" : "0") + "\n");
				writer.println();
			}
		} catch (IOException e) {
			System.err.println("failed to write to file " + NAOFILE);
			return false;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

		return true;
	}

	public static boolean write2NaoPoseFile(HashMap<String, NaoPose> poseMap) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(
					NAOPOSEFILE)));
			//			Iterator<Entry<String, NaoPose>> iter = poseMap.entrySet().iterator();

			String[] jointsName = { "hj1", "hj2", "raj1", "raj2", "raj3",
					"raj4", "laj1", "laj2", "laj3", "laj4", "rlj1", "rlj2",
					"rlj3", "rlj4", "rlj5", "rlj6", "llj1", "llj2", "llj3",
					"llj4", "llj5", "llj6" };
			int i = 0;
			for (String poseName : NaoFileReader.poseList) {
				System.out.println(i++ + " " + poseName);
				writer.println(poseName);
				NaoPose naoPose = poseMap.get(poseName);
				for (String name : jointsName) {
					if (naoPose.findAngleByName(name) != null) {
						writer.print(name + " " + naoPose.findAngleByName(name)
								+ "\t");
					}
				}
				writer.println("\n");
			}
			//			for (Map.Entry<String, NaoPose> item : poseMap.entrySet()) {
			//				writer.println(item.getKey());
			//				for(String name:jointsName){
			//					if (item.getValue().findAngleByName(name) != null) {
			//						writer.print(name + " "
			//								+ item.getValue().findAngleByName(name) + "\t");
			//					}
			//				}
			//				writer.println("\n");
			//			}
		} catch (IOException e) {
			System.err.println("failed to write to file " + NAOPOSEFILE);
			return false;
		} finally {
			if (writer != null) {
				writer.close();
			}
		}

		return true;
	}
}
