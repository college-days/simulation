package com.seu.joker.toolkit.seugui.controller;

import com.seu.joker.toolkit.seugui.naomodel.MyUniverse;

public class GUIMainController {
	private MyUniverse myUniverse = null;

	public MyUniverse getMyUniverse() {
		return myUniverse;
	}

	public GUIMainController() {
		myUniverse = new MyUniverse();
	}
}