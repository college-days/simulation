package com.seu.joker.toolkit.seugui.naomodel;

import javax.media.j3d.Appearance;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Sphere;


public class Head extends Sphere{

	private Transform3D mTransform3d;
	private TransformGroup mTransformGroup;

	public TransformGroup getmTransformGroup() {
		return mTransformGroup;
	}
	
	public Head(float radius, int flag, int k, Appearance ap){
		super(radius, flag, k, ap);
		mTransform3d = new Transform3D();
		mTransformGroup = new TransformGroup(mTransform3d);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mTransformGroup.addChild(this);
	}
	
	public Head(float radius, int flag, int k, Appearance ap, Vector3f vector3f, Quat4f quat4f){
		this(radius, flag, k, ap);
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
	
	public void setScale(double a){
		mTransform3d.setScale(a);
		mTransformGroup.setTransform(mTransform3d);
	}
}
