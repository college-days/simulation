package com.seu.joker.toolkit.seugui.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import com.seu.joker.toolkit.seugui.model.NaoPose;
import com.seu.joker.toolkit.seugui.model.Task;

public class NaoFileReader {
	private final static String NAOFILE = "nao.txt";
	private final static String NAOPOSEFILE = "nao_pose.txt";

	public static ArrayList<String> poseList = new ArrayList<String>();
	/**
	 * 
	 * @param filePath
	 * @param tasksName
	 *            only to maintain the order of the tasks in the file.
	 * @param taskMap
	 * @return
	 */
	public static boolean readNaoFile(Vector<String> tasksName,
			HashMap<String, Task> taskMap) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(NAOFILE)));
			String nameLine = null;
			String dataLine = null;
			while ((nameLine = reader.readLine()) != null) {
				if (nameLine.isEmpty())
					continue;
				if (nameLine.startsWith("#"))
					continue;

				dataLine = reader.readLine();
				String[] taskContent = dataLine.split("\\s");
				tasksName.add(nameLine);
				taskMap.put(nameLine, makeTask(taskContent));
			}
		} catch (Exception e) {
			System.err.println("failed to read file " + NAOFILE);
			return false;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	private static Task makeTask(String[] taskContent) {
		Task task = new Task();
		task.setDesiredPose(taskContent[0]);
		task.setTime(Float.parseFloat(taskContent[1]));
		if (taskContent[2].equals("0"))
			task.setChangeable(false);
		else
			task.setChangeable(true);
		task.setAdjustFoot(Integer.parseInt(taskContent[3]));
		task.setNextTaskName(taskContent[4]);
		if (taskContent[5].equals("0"))
			task.setChangeFoot(false);
		else
			task.setChangeFoot(true);

		return task;
	}

	public static boolean readNaoPoseFile(HashMap<String, NaoPose> poses) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(NAOPOSEFILE)));
			String poseName = null;
			String dataLine = null;
			while ((poseName = reader.readLine()) != null) {
				if (poseName.isEmpty())
					continue;
				if (poseName.startsWith("#"))
					continue;
				dataLine = reader.readLine();
				dataLine = dataLine.replaceAll("\\s{1,}", " ");
				String[] poseContent = dataLine.split("\\s");
				poses.put(poseName, makeNaoPose(poseContent));
				if (poseList.contains(poseName))
					continue;
				poseList.add(poseName);
			}
		} catch (IOException e) {
			System.err.println("failed to read file " + NAOPOSEFILE);
			return false;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	private static NaoPose makeNaoPose(String[] poseContent) {
		for (String pose : poseContent) {
			//			System.out.println(pose);
		}
		NaoPose naoPose = new NaoPose();
		for (int i = 0; i != poseContent.length; i = i + 2) {
			naoPose.pushAngle(poseContent[i],
					Float.parseFloat(poseContent[i + 1]));
		}
		return naoPose;
	}
}
