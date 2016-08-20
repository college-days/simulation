package com.seu.joker.toolkit.seugui.naomodel;

import javax.media.j3d.Appearance;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;


public class CoordinateAxis extends Box {

	public static final float ARROW_X_LENGTH = 0.17f;
	public static final float ARROW_Y_LENGTH = 0.017f;
	public static final float ARROW_Z_LENGTH = 0.017f;
	
	public static final float ARROW_UP_TRANSLATION = 0.13f;
	public static final float ARROW_DOWN_TRANSLATION = -0.13f;
	
	public static final float ARROW_X_COORDINATE = 2.3f;
	public static final float ARROW_Y_COORDINATE = 0.0f;
	public static final float ARROW_Z_COORDINATE = 0.0f;
	
	//	private Box arrowHead1;
	//	private Box arrowHead2;
	private Box arrowHead3;
	private Box arrowHead4;
	
	
	private Transform3D mTransform3d;
	private TransformGroup mTransformGroup;
	
	public Transform3D getmTransform3d(){
		return mTransform3d;
	}
	
	public TransformGroup getmTransformGroup() {
		return mTransformGroup;
	}
	
	public CoordinateAxis(float x, float y, float z, int flag, int flag2, Appearance ap){
		super(x, y, z, flag|flag2, ap);
		
		/**
		 * initial the arrows
		 */
		Transform3D arrowsTransform3d = new Transform3D();
		arrowsTransform3d.setTranslation(new Vector3f(1.2f, 0.0f, 0.0f));
		TransformGroup arrowsTransformGroup = new TransformGroup(
				arrowsTransform3d);
		/*
		 * arrowHead1 = new Box(ARROW_X_LENGTH, ARROW_Y_LENGTH, ARROW_Z_LENGTH,
		 * ap); arrowHead1Transform3d = new Transform3D();
		 * arrowHead1Transform3d.rotZ(-Math.PI/4.0f);
		 * arrowHead1Transform3d.setTranslation(new Vector3f(0.0f,
		 * ARROW_UP_TRANSLATION, 0.0f)); arrowHead1TransformGroup = new
		 * TransformGroup(arrowHead1Transform3d);
		 * arrowHead1TransformGroup.addChild(arrowHead1);
		 * arrowHead1TransformGroup
		 * .setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		 * 
		 * arrowHead2 = new Box(ARROW_X_LENGTH, ARROW_Y_LENGTH, ARROW_Z_LENGTH,
		 * ap); arrowHead2Transform3d = new Transform3D();
		 * arrowHead2Transform3d.rotZ(Math.PI/4.0f);
		 * arrowHead2Transform3d.setTranslation(new Vector3f(0.0f,
		 * ARROW_DOWN_TRANSLATION, 0.0f)); arrowHead2TransformGroup = new
		 * TransformGroup(arrowHead2Transform3d);
		 * arrowHead2TransformGroup.addChild(arrowHead2);
		 * arrowHead2TransformGroup
		 * .setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		 */
		arrowHead3 = new Box(ARROW_X_LENGTH, ARROW_Y_LENGTH, ARROW_Z_LENGTH, ap);
		Transform3D arrowHead3Transform3d = new Transform3D();
		arrowHead3Transform3d.rotY(-Math.PI/4.0f);
		arrowHead3Transform3d.setTranslation(new Vector3f(0.0f, 0.0f, ARROW_DOWN_TRANSLATION));
		TransformGroup arrowHead3TransformGroup = new TransformGroup(
				arrowHead3Transform3d);
		arrowHead3TransformGroup.addChild(arrowHead3);
		arrowHead3TransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		arrowHead4 = new Box(ARROW_X_LENGTH, ARROW_Y_LENGTH, ARROW_Z_LENGTH, ap);
		Transform3D arrowHead4Transform3d = new Transform3D();
		arrowHead4Transform3d.rotY(Math.PI/4.0f);
		arrowHead4Transform3d.setTranslation(new Vector3f(0.0f, 0.0f, ARROW_UP_TRANSLATION));
		TransformGroup arrowHead4TransformGroup = new TransformGroup(
				arrowHead4Transform3d);
		arrowHead4TransformGroup.addChild(arrowHead4);
		arrowHead4TransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		arrowsTransform3d = new Transform3D();
		arrowsTransform3d.setTranslation(new Vector3f(ARROW_X_COORDINATE, ARROW_Y_COORDINATE, ARROW_Z_COORDINATE));
		arrowsTransformGroup = new TransformGroup(arrowsTransform3d);
		arrowsTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		// arrowsTransformGroup.addChild(arrowHead1TransformGroup);
		// arrowsTransformGroup.addChild(arrowHead2TransformGroup);
		arrowsTransformGroup.addChild(arrowHead3TransformGroup);
		arrowsTransformGroup.addChild(arrowHead4TransformGroup);
		
		
		
		mTransform3d = new Transform3D();
		mTransformGroup = new TransformGroup(mTransform3d);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mTransformGroup.setCapability(TransformGroup.ALLOW_CHILDREN_WRITE);
		mTransformGroup.addChild(this);
		mTransformGroup.addChild(arrowsTransformGroup);
	}

	public void setTrans(Vector3f vector3f){
		mTransform3d.setTranslation(vector3f);
		mTransformGroup.setTransform(mTransform3d);
	}
	
	public void setRot(Quat4f quat4f){
		mTransform3d.setRotation(quat4f);
		mTransformGroup.setTransform(mTransform3d);
	}
	
	public void rotZ(double angle){
		mTransform3d.rotZ(angle);
		mTransformGroup.setTransform(mTransform3d);
	}
	
	public void rotY(double angle){
		mTransform3d.rotY(angle);
		mTransformGroup.setTransform(mTransform3d);
	}
	
	public void rotX(double angle){
		mTransform3d.rotX(angle);
		mTransformGroup.setTransform(mTransform3d);
	}
	
	public void setScale(double a){
		mTransform3d.setScale(a);
		mTransformGroup.setTransform(mTransform3d);
	}
	
}
