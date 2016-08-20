package com.seu.joker.toolkit.seugui.naomodel;

import java.util.HashMap;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Primitive;

public class Nao {
	private HashMap<String, Roller> rollers = new HashMap<String, Roller>();

	private String lastSelectedRollerName = null;
	/**
	 * the main body
	 */
	private Limb bodyLimb;
	private Roller neckRoller;
	private Roller headRoller;
	private Head headSphere;
	
	/**
	 * the right hand
	 */
	private Roller rshoulderRoller;
	private Roller rupperarmRoller;
	private Limb rshoulderLimb;
	private Roller relbowRoller;
	private Roller rlowerarmRoller;
	private Limb rlowerarmLimb;
	
	/**
	 * the left hand
	 */
	private Roller lshoulderRoller;
	private Roller lupperarmRoller;
	private Limb lshoulderLimb;
	private Roller lelbowRoller;
	private Roller llowerarmRoller;
	private Limb llowerarmLimb;
	
	/**
	 * the right leg
	 */
	private Transform3D rlegTransform3d;

	public Transform3D getRlegTransform3d() {
		return rlegTransform3d;
	}

	private TransformGroup rlegTransformGroup;

	public TransformGroup getRlegTransformGroup() {
		return rlegTransformGroup;
	}

	private Roller rhip1Roller;
	private Roller rhip2Roller;
	private Roller rthighRoller;
	private Limb rthighLimb;
	private Roller rshankRoller;
	private Limb rshankLimb;
	private Roller rankleRoller;
	private Roller rfootRoller;
	private Limb rfootLimb;
	
	/**
	 * the left leg
	 */
	private Transform3D llegTransform3d;

	public Transform3D getLlegTransform3d() {
		return llegTransform3d;
	}

	private TransformGroup llegTransformGroup;

	public TransformGroup getLlegTransformGroup() {
		return llegTransformGroup;
	}

	private Roller lhip1Roller;
	private Roller lhip2Roller;
	private Roller lthighRoller;
	private Limb lthighLimb;
	private Roller lshankRoller;
	private Limb lshankLimb;
	private Roller lankleRoller;
	private Roller lfootRoller;
	private Limb lfootLimb;
	

	public Limb getBodyLimb(){
		return bodyLimb;
	}
	
	public Roller getRollerByName(String name) {
		return rollers.get(name);
	}

	public Roller getLastSelectedRoller() {
		return rollers.get(lastSelectedRollerName);
	}

	public void setLastSelectedRoller(String name) {
		lastSelectedRollerName = name;
	}

	/**
	 * for debug
	 */
	Roller rlegRoller;
	Roller llegRoller;

	public Nao(){
		/**
		 * the appearances
		 */
		Appearance ap = new Appearance();
		ColoringAttributes ca = new ColoringAttributes();
		ca.setColor(1.0f, 1.0f, 1.0f);
		PolygonAttributes pa = new PolygonAttributes();
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		ap.setColoringAttributes(ca);
		ap.setPolygonAttributes(pa);
		
		Appearance ap1 = new Appearance();
		ColoringAttributes ca1 = new ColoringAttributes();
		ca1.setColor(0.0f, 0.0f, 0.0f);
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setTransparency(0.5f);
		ta.setTransparencyMode(TransparencyAttributes.BLENDED);
		ap1.setColoringAttributes(ca1);
		ap1.setTransparencyAttributes(ta);
		
		Appearance ap2 = new Appearance();
		ColoringAttributes ca2 = new ColoringAttributes();
		ca2.setColor(1.0f, 1.0f, 1.0f);
		TransparencyAttributes ta1 = new TransparencyAttributes();
		ta1.setTransparency(0.5f);
		ta1.setTransparencyMode(TransparencyAttributes.BLENDED);
		ap2.setColoringAttributes(ca2);
		ap2.setTransparencyAttributes(ta1);
		
		/**
		 * the main body
		 */
		bodyLimb = new Limb(0.5f, 0.7f, 0.5f, ap1);
//		bodyLimb.setTrans(new Vector3f(0.0f, 0.88f, -5.3f));
//		bodyLimb.setScale(0.8);
		headSphere = new Head(0.39f, Primitive.GENERATE_NORMALS, 50, ap);
		headRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "head", -45, 45);
		headRoller.setTrans(new Vector3f(0.0f, 0.45f, 0.0f));
		neckRoller = new Roller(0.02f, 0.2f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "neck", -120, 120);
		neckRoller.setTrans(new Vector3f(0.0f, 0.75f, 0.0f));
		rollers.put(neckRoller.getRollerName(), neckRoller);
		rollers.put(headRoller.getRollerName(), headRoller);
		
		rlegRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "head", -45, 45);
		rlegRoller.setTrans(new Vector3f(-0.379f, -1.1f, 0.0f));
		llegRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "head", -45, 45);
		llegRoller.setTrans(new Vector3f(0.379f, -1.1f, 0.0f));

		/**
		 * the right hand
		 */
		rshoulderRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rshoulder",
				-120, 120);
		rshoulderRoller.setTrans(new Vector3f(-0.59f, 0.5f, 0.0f));
		rupperarmRoller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rupperarm", -95, 1);
		rshoulderLimb = new Limb(0.3f, 0.3f, 0.3f, ap2);
		rshoulderLimb.setTrans(new Vector3f(-0.36f, 0.0f, 0.0f));
		relbowRoller = new Roller(0.02f, 0.2f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "relbow", -120,
				120);
		relbowRoller.setTrans(new Vector3f(-0.33f, -0.46f, 0.0f));
		rlowerarmRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rlowerarm", -1, 90);
		rlowerarmLimb = new Limb(0.25f, 0.45f, 0.25f, ap2);
		rlowerarmLimb.setTrans(new Vector3f(0.0f, -0.5f, 0.0f));
		rollers.put(rshoulderRoller.getRollerName(), rshoulderRoller);
		rollers.put(rupperarmRoller.getRollerName(), rupperarmRoller);
		rollers.put(relbowRoller.getRollerName(), relbowRoller);
		rollers.put(rlowerarmRoller.getRollerName(), rlowerarmRoller);

		/**
		 * the left hand
		 */
		lshoulderRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lshoulder",
				-120, 120);
		lshoulderRoller.setTrans(new Vector3f(0.59f, 0.5f, 0.0f));
		lupperarmRoller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lupperarm", -1, 95);
		lshoulderLimb = new Limb(0.3f, 0.3f, 0.3f, ap2);
		lshoulderLimb.setTrans(new Vector3f(0.36f, 0.0f, 0.0f));
		lelbowRoller = new Roller(0.02f, 0.2f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lelbow", -120,
				120);
		lelbowRoller.setTrans(new Vector3f(0.33f, -0.46f, 0.0f));
		llowerarmRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "llowerarm", -90, 1);
		llowerarmLimb = new Limb(0.25f, 0.45f, 0.25f, ap2);
		llowerarmLimb.setTrans(new Vector3f(0.0f, -0.5f, 0.0f));
		rollers.put(lshoulderRoller.getRollerName(), lshoulderRoller);
		rollers.put(lupperarmRoller.getRollerName(), lupperarmRoller);
		rollers.put(lelbowRoller.getRollerName(), lelbowRoller);
		rollers.put(llowerarmRoller.getRollerName(), llowerarmRoller);
		
		/**
		 * the right leg
		 */
		rlegTransform3d = new Transform3D();
		rlegTransform3d.setTranslation(new Vector3f(-0.379f, -1.1f, 0.0f));
		rlegTransformGroup = new TransformGroup(rlegTransform3d);
		rlegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		rlegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		rhip1Roller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rhip1", -90, 1);
		rhip1Roller.rotZ(Math.PI/4);
		
		rhip2Roller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rhip2", -45, 25);
		rthighRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rthigh", -25, 100);
		
		rthighLimb = new Limb(0.3f, 0.42f, 0.3f, ap2);
		rthighLimb.setTrans(new Vector3f(0.0f, -0.4f, 0.0f));
		
		rshankRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rshank", -130, 1);
		rshankRoller.setTrans(new Vector3f(0.0f, -0.95f, 0.0f));
		
		rshankLimb = new Limb(0.3f, 0.45f, 0.3f, ap2);
		rshankLimb.setTrans(new Vector3f(0.0f, -0.58f, 0.0f));
		
		rankleRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rankle", -45, 75);
		rankleRoller.setTrans(new Vector3f(0.0f, -1.15f, 0.0f));
	
		rfootRoller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rfoot", -25, 45);
		rfootLimb = new Limb(0.33f, 0.15f, 0.65f, ap2);
		rfootLimb.setTrans(new Vector3f(0.0f, -0.33f, 0.33f));
		rollers.put(rhip1Roller.getRollerName(), rhip1Roller);
		rollers.put(rhip2Roller.getRollerName(), rhip2Roller);
		rollers.put(rthighRoller.getRollerName(), rthighRoller);
		rollers.put(rshankRoller.getRollerName(), rshankRoller);
		rollers.put(rankleRoller.getRollerName(), rankleRoller);
		rollers.put(rfootRoller.getRollerName(), rfootRoller);

		/**
		 * the left leg
		 */
		llegTransform3d = new Transform3D();
		llegTransform3d.setTranslation(new Vector3f(0.379f, -1.1f, 0.0f));
		llegTransformGroup = new TransformGroup(llegTransform3d);
		llegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		llegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		lhip1Roller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lhip1", -90, 1);
		lhip1Roller.rotZ(-(Math.PI/4));
		
		lhip2Roller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lhip2", -25, 45);
		lthighRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lthigh", -25, 100);
		
		lthighLimb = new Limb(0.3f, 0.42f, 0.3f, ap2);
		lthighLimb.setTrans(new Vector3f(0.0f, -0.4f, 0.0f));
		
		lshankRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lshank", -130, 1);
		lshankRoller.setTrans(new Vector3f(0.0f, -0.95f, 0.0f));
		
		lshankLimb = new Limb(0.3f, 0.45f, 0.3f, ap2);
		lshankLimb.setTrans(new Vector3f(0.0f, -0.58f, 0.0f));
		
		lankleRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lankle", -45, 75);
		lankleRoller.setTrans(new Vector3f(0.0f, -1.15f, 0.0f));
	
		lfootRoller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lfoot", -45, 25);
		lfootLimb = new Limb(0.33f, 0.15f, 0.65f, ap2);
		lfootLimb.setTrans(new Vector3f(0.0f, -0.33f, 0.33f));
		
		rollers.put(lhip1Roller.getRollerName(), lhip1Roller);
		rollers.put(lhip2Roller.getRollerName(), lhip2Roller);
		rollers.put(lthighRoller.getRollerName(), lthighRoller);
		rollers.put(lshankRoller.getRollerName(), lshankRoller);
		rollers.put(lankleRoller.getRollerName(), lankleRoller);
		rollers.put(lfootRoller.getRollerName(), lfootRoller);

		/**
		 * the main body
		 */
		bodyLimb.getmTransformGroup().addChild(neckRoller.getmTransformGroup());
		neckRoller.getmTransformGroup().addChild(headRoller.getmTransformGroup());
		headRoller.getmTransformGroup().addChild(headSphere.getmTransformGroup());
		
		/**
		 * the right hand
		 */
		bodyLimb.getmTransformGroup().addChild(rshoulderRoller.getmTransformGroup());
		rshoulderRoller.getmTransformGroup().addChild(rupperarmRoller.getmTransformGroup());
		rupperarmRoller.getmTransformGroup().addChild(rshoulderLimb.getmTransformGroup());
		rupperarmRoller.getmTransformGroup().addChild(relbowRoller.getmTransformGroup());
		relbowRoller.getmTransformGroup().addChild(rlowerarmRoller.getmTransformGroup());
		rlowerarmRoller.getmTransformGroup().addChild(rlowerarmLimb.getmTransformGroup());
	
		/**
		 * the left hand
		 */
		bodyLimb.getmTransformGroup().addChild(lshoulderRoller.getmTransformGroup());
		lshoulderRoller.getmTransformGroup().addChild(lupperarmRoller.getmTransformGroup());
		lupperarmRoller.getmTransformGroup().addChild(lshoulderLimb.getmTransformGroup());
		lupperarmRoller.getmTransformGroup().addChild(lelbowRoller.getmTransformGroup());
		lelbowRoller.getmTransformGroup().addChild(llowerarmRoller.getmTransformGroup());
		llowerarmRoller.getmTransformGroup().addChild(llowerarmLimb.getmTransformGroup());
	
		/**
		 * the right leg
		 */
		bodyLimb.getmTransformGroup().addChild(rlegTransformGroup);
		rlegTransformGroup.addChild(rhip1Roller.getmTransformGroup());
		rlegTransformGroup.addChild(rhip2Roller.getmTransformGroup());
		//		bodyLimb.getmTransformGroup().addChild(rlegRoller.getmTransformGroup());
		//		rlegRoller.getmTransformGroup().addChild(rhip1Roller.getmTransformGroup());
		//		rlegRoller.getmTransformGroup().addChild(rhip2Roller.getmTransformGroup());
		rhip2Roller.getmTransformGroup().addChild(rthighRoller.getmTransformGroup());
		rthighRoller.getmTransformGroup().addChild(rthighLimb.getmTransformGroup());
		rthighRoller.getmTransformGroup().addChild(rshankRoller.getmTransformGroup());
		rshankRoller.getmTransformGroup().addChild(rshankLimb.getmTransformGroup());
		rshankRoller.getmTransformGroup().addChild(rankleRoller.getmTransformGroup());
		rankleRoller.getmTransformGroup().addChild(rfootRoller.getmTransformGroup());
		rfootRoller.getmTransformGroup().addChild(rfootLimb.getmTransformGroup());
		
		/**
		 * the left leg
		 */
		bodyLimb.getmTransformGroup().addChild(llegTransformGroup);
		llegTransformGroup.addChild(lhip1Roller.getmTransformGroup());
		llegTransformGroup.addChild(lhip2Roller.getmTransformGroup());
		//		bodyLimb.getmTransformGroup().addChild(llegRoller.getmTransformGroup());
		//		llegRoller.getmTransformGroup().addChild(lhip1Roller.getmTransformGroup());
		//		llegRoller.getmTransformGroup().addChild(lhip2Roller.getmTransformGroup());
		lhip2Roller.getmTransformGroup().addChild(lthighRoller.getmTransformGroup());
		lthighRoller.getmTransformGroup().addChild(lthighLimb.getmTransformGroup());
		lthighRoller.getmTransformGroup().addChild(lshankRoller.getmTransformGroup());
		lshankRoller.getmTransformGroup().addChild(lshankLimb.getmTransformGroup());
		lshankRoller.getmTransformGroup().addChild(lankleRoller.getmTransformGroup());
		lankleRoller.getmTransformGroup().addChild(lfootRoller.getmTransformGroup());
		lfootRoller.getmTransformGroup().addChild(lfootLimb.getmTransformGroup());
	}
}
