package com.seu.joker.toolkit.seugui.animation;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.swing.JFrame;

import com.seu.joker.toolkit.seugui.view.FixedAngleTracePanel;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;


public class ShowTheAnimation extends JFrame {
	
	BoundingSphere bounds;
	BranchGroup mBranchGroup;
	Canvas3D canvas3d;
	SimpleUniverse su;
	OrbitBehavior orbit;
	AnimationMyUniverse myAnimationMyUniverse;
	
	public ShowTheAnimation(BranchGroup _mBranchGroup,
			AnimationMyUniverse animationMyUniverse) {
	
		this.myAnimationMyUniverse = animationMyUniverse;
		mBranchGroup = _mBranchGroup;
		bounds = (BoundingSphere) _mBranchGroup.getBounds();
		
		this.setBounds(10, 10, 800, 600);
		this.setLayout(new BorderLayout());
		GraphicsConfiguration configuration = SimpleUniverse.getPreferredConfiguration();
		canvas3d = new Canvas3D(configuration);
		this.add(canvas3d, BorderLayout.CENTER);
		
		su = new SimpleUniverse(canvas3d);
		su.getViewingPlatform().setNominalViewingTransform();
		su.addBranchGraph(mBranchGroup);
		
//		orbit = new OrbitBehavior(canvas3d, OrbitBehavior.REVERSE_ALL);
//		orbit.setSchedulingBounds(bounds);
//		ViewingPlatform vp = su.getViewingPlatform();
//		vp.setViewPlatformBehavior(orbit);
	
		//this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				myAnimationMyUniverse.releaseAnimation();
				FixedAngleTracePanel.counter = 0;
			}
		});
		this.setVisible(true);
	}

}
