package com.seu.joker.toolkit.seugui.naomodel;

import javax.media.j3d.Appearance;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Box;

public class Limb extends Box{
	
	private Transform3D mTransform3d;
	private TransformGroup mTransformGroup;

	public TransformGroup getmTransformGroup() {
		return mTransformGroup;
	}

	public Limb(float x, float y, float z, Appearance ap){
		super(x, y, z, ap);
		mTransform3d = new Transform3D();
		mTransformGroup = new TransformGroup(mTransform3d);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mTransformGroup.addChild(this);
	}
	
	public Limb(float x, float y, float z, Appearance ap, Vector3f vector3f, Quat4f quat4f){
		super(x, y, z, ap);
		mTransform3d = new Transform3D();
		mTransform3d.setRotation(quat4f);
		mTransform3d.setTranslation(vector3f);
		mTransformGroup = new TransformGroup(mTransform3d);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mTransformGroup.addChild(this);
	}
	
	public void setTrans(Vector3f vector3f){
		mTransform3d.setTranslation(vector3f);
		mTransformGroup.setTransform(mTransform3d);
	}
	
	public void setRot(Quat4f quat4f){
		mTransform3d.setRotation(quat4f);
		mTransformGroup.setTransform(mTransform3d);
	}
	
	public void setScale(double a){
		mTransform3d.setScale(a);
		mTransformGroup.setTransform(mTransform3d);
	}
	

}
