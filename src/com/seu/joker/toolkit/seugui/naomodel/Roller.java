package com.seu.joker.toolkit.seugui.naomodel;

import javax.media.j3d.Appearance;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;


public class Roller extends Box {

	private Transform3D mTransform3d;
	private TransformGroup mTransformGroup;
	private String rollerName = null;
	private int minAngle = 0;
	private int maxAngle = 0;

	public String getRollerName() {
		return rollerName;
	}

	public int getMinAngle() {
		return minAngle;
	}

	public int getMaxAngle() {
		return maxAngle;
	}

	public TransformGroup getmTransformGroup() {
		return mTransformGroup;
	}

	public Roller(float x, float y, float z, int flag, Appearance ap,
			String name, int minAngle, int maxAngle) {
		super(x, y, z, flag, ap);
		this.rollerName = name;
		this.minAngle = minAngle;
		this.maxAngle = maxAngle;
		mTransform3d = new Transform3D();
		mTransformGroup = new TransformGroup(mTransform3d);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mTransformGroup.addChild(this);
	}
	
	public Roller(float x, float y, float z, int flag, Appearance ap, String name, int minAngle, int maxAngle, Vector3f vector3f, Quat4f quat4f){
		this(x, y, z, flag, ap, name, minAngle, maxAngle);
		mTransform3d.setRotation(quat4f);
		mTransform3d.setTranslation(vector3f);
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
	
	public void setScale(double a){
		mTransform3d.setScale(a);
		mTransformGroup.setTransform(mTransform3d);
	}
}
