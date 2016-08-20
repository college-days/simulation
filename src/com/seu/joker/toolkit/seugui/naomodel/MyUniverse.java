package com.seu.joker.toolkit.seugui.naomodel;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class MyUniverse extends JFrame implements  MouseBehaviorCallback{  
	private static final long serialVersionUID = -6856185686971016796L;
	private Canvas3D mCanvas3d = null;
	private Nao nao = null;

	public Nao getNao() {
		return nao;
	}
	
	public MyUniverse(){
		nao = new Nao();
		
		Transform3D transform3d = new Transform3D();
		transform3d.setTranslation(new Vector3f(0.0f, 0.88f, -5.3f));
		transform3d.setScale(0.8);
		TransformGroup transformGroup = new TransformGroup(transform3d);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		transformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
		transformGroup.addChild(nao.getBodyLimb().getmTransformGroup());
		
		BranchGroup branchGroup = new BranchGroup();
		BoundingSphere boundingSphere = new BoundingSphere(new Point3d(0.0f, 0.0f, 0.0f),100.0f);
		Color3f color = new Color3f(0.1f, 0.5f, 0.5f);
		Background background = new Background(color);
		background.setApplicationBounds(boundingSphere);
		
		CoordinateSystem coordSys = new CoordinateSystem();
		transformGroup.addChild(coordSys.getTransformGroup());

		/**
		 * the keyBoardBehavior
		 */
		KeyNavigatorBehavior keyNavigatorBehavior = new KeyNavigatorBehavior(transformGroup);
		keyNavigatorBehavior.setSchedulingBounds(boundingSphere);
		branchGroup.addChild(keyNavigatorBehavior);
	
		/**
		 *  the mouseBehavior
		 */
		MouseRotate mouseRotate = new MouseRotate();
		mouseRotate.setTransformGroup(transformGroup);
		mouseRotate.setupCallback(this);
		mouseRotate.setSchedulingBounds(boundingSphere);
		branchGroup.addChild(mouseRotate);
		
		MouseTranslate mouseTranslate = new MouseTranslate();
		mouseTranslate.setTransformGroup(transformGroup);
		mouseTranslate.setupCallback(this);
		mouseTranslate.setSchedulingBounds(boundingSphere);
		branchGroup.addChild(mouseTranslate);
		
//		MouseZoom mouseZoom = new MouseZoom(MouseBehavior.INVERT_INPUT);
		MouseZoom mouseZoom = new MouseZoom();
		mouseZoom.setTransformGroup(transformGroup);
		mouseZoom.setupCallback(this);
		mouseZoom.setSchedulingBounds(boundingSphere);
		branchGroup.addChild(mouseZoom);
		
		branchGroup.addChild(background);
		branchGroup.addChild(transformGroup);
		branchGroup.compile();
		
		GraphicsConfiguration configuration = SimpleUniverse.getPreferredConfiguration();	
		mCanvas3d = new Canvas3D(configuration);
		this.add(mCanvas3d, BorderLayout.CENTER);
		SimpleUniverse su = new SimpleUniverse(mCanvas3d);
		su.getViewingPlatform().setNominalViewingTransform();
		su.addBranchGraph(branchGroup);
		
		
		this.setBounds(0, 0, 800, 728);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}


	@Override
	public void transformChanged(int arg0, Transform3D arg1) {
		// TODO Auto-generated method stub
		
	}
}
