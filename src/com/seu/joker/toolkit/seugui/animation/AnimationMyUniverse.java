package com.seu.joker.toolkit.seugui.animation;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RestrictedAccessException;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3f;

import com.seu.joker.toolkit.seugui.model.NaoPose;
import com.seu.joker.toolkit.seugui.model.Task;
import com.seu.joker.toolkit.seugui.naomodel.Head;
import com.seu.joker.toolkit.seugui.naomodel.Limb;
import com.seu.joker.toolkit.seugui.naomodel.Roller;
import com.seu.joker.toolkit.seugui.util.JointNameTransform;
import com.seu.joker.toolkit.seugui.util.NaoFileReader;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseBehaviorCallback;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.universe.SimpleUniverse;


public class AnimationMyUniverse implements ActionListener,
		MouseBehaviorCallback {

	//hehe
    GraphicsConfiguration mConfiguration = SimpleUniverse.getPreferredConfiguration();
	BoundingSphere mBoundingSphere;
	BranchGroup mBranchGroup;
	Canvas3D mCanvas3d;
	Background mBackground;
	SimpleUniverse msimSimpleUniverse;
	Transform3D mTransform3d;
	TransformGroup mTransformGroup;
	Color3f mColor3f;
	
	JButton debugButton;
	JButton custoButton;

	/**
	 * the main body
	 */
	Limb bodyLimb;
	Roller neckRoller;
	Roller headRoller;
	Head headSphere;
	
	/**
	 * the right hand
	 */
	Roller rshoulderRoller;
	Roller rupperarmRoller;
	Limb rshoulderLimb;
	Roller relbowRoller;
	Roller rlowerarmRoller;
	Limb rlowerarmLimb;
	
	/**
	 * the left hand
	 */
	Roller lshoulderRoller;
	Roller lupperarmRoller;
	Limb lshoulderLimb;
	Roller lelbowRoller;
	Roller llowerarmRoller;
	Limb llowerarmLimb;
	
	/**
	 * the right leg
	 */
	Transform3D rlegTransform3d;
	TransformGroup rlegTransformGroup;
	Roller rhip1Roller;
	Roller rhip2Roller;
	Roller rthighRoller;
	Limb rthighLimb;
	Roller rshankRoller;
	Limb rshankLimb;
	Roller rankleRoller;
	Roller rfootRoller;
	Limb rfootLimb;
	
	/**
	 * the left leg
	 */
	Transform3D llegTransform3d;
	TransformGroup llegTransformGroup;
	Roller lhip1Roller;
	Roller lhip2Roller;
	Roller lthighRoller;
	Limb lthighLimb;
	Roller lshankRoller;
	Limb lshankLimb;
	Roller lankleRoller;
	Roller lfootRoller;
	Limb lfootLimb;
	
	Appearance ap;
	Appearance ap1;
	Appearance ap2;
	
	/**
	 * for animation
	 */
	
	JButton linkButton;
	JButton cutButton;
	
	JTextField rollerTextField;
	JTextField angelTextField;
	JTextField durationTimeTextField;
	JButton addButton;
	JButton showButton;
	JButton releaseButton;
	String rollerString;
	String angelString;
	String durationTimeString;
	
	float angelRot;
	int durationTime;
	
	int rotationCounter = NewDelayBehavior.FINISHED;
	
	ArrayList<MyInterpolator> rotationsArrayList;
	
	ShowTheAnimation mDelay;
	int delayCounter = 0;
	
	NewDelayBehavior myDelayBehavior;
	
	/**
	 * rshoulderStuff
	 */
	Alpha rshoulderAlpha;
	MyInterpolator rshoulderRotationInterpolator;
	//	float rshoulderInitAngel = 90.0f;
	float rshoulderInitAngel = 0.0f;
	float rshoulderDebugAngel;
	Transform3D rshoulderAxis;
	float rshouldercos;
	float rshouldersin;
	
	/**
	 * rupperarmStuff
	 */
	Alpha rupperarmAlpha;
	MyInterpolator rupperarmRotationInterpolator;
	float rupperarmInitAngle = 0.0f;
	float rupperarmDebugAngel;
	Transform3D rupperarmAxis;
	float rupperarmcos;
	float rupperarmsin;
	TransformGroup rupperarmForAnimationGroup;
	
	/**
	 * relbowStuff
	 */
	Alpha relbowAlpha;
	MyInterpolator relbowRotationInterpolator;
	//	float relbowInitAngel = 90.0f;
	float relbowInitAngel = 0.0f;
	float relbowDebugAngel;
	Transform3D relbowAxis;
	float relbowcos;
	float relbowsin;
	
	/**
	 * rlowerarmStuff
	 */
	Alpha rlowerarmAlpha;
	MyInterpolator rlowerarmRotationInterpolator;
	float rlowerarmInitAngel = 0.0f;
	float rlowerarmDebugAngel;
	Transform3D rlowerarmAxis;
	float rlowerarmcos;
	float rlowerarmsin;
	
	/**
	 * lshoulderStuff
	 */
	Alpha lshoulderAlpha;
	MyInterpolator lshoulderRotationInterpolator;
	//	float lshoulderInitAngel = 90.0f;
	float lshoulderInitAngel = 0.0f;
	float lshoulderDebugAngel;
	Transform3D lshoulderAxis;
	float lshouldercos;
	float lshouldersin;
	
	/**
	 * lupperarmStuff
	 */
	Alpha lupperarmAlpha;
	MyInterpolator lupperarmRotationInterpolator;
	float lupperarmInitAngle = 0.0f;
	float lupperarmDebugAngel;
	Transform3D lupperarmAxis;
	float lupperarmcos;
	float lupperarmsin;
	TransformGroup lupperarmForAnimationGroup;
	
	/**
	 * lelbowStuff
	 */
	Alpha lelbowAlpha;
	MyInterpolator lelbowRotationInterpolator;
	//	float lelbowInitAngel = -90.0f;
	float lelbowInitAngel = 0.0f;
	float lelbowDebugAngel;
	Transform3D lelbowAxis;
	float lelbowcos;
	float lelbowsin;
	
	/**
	 * llowerarmStuff
	 */
	Alpha llowerarmAlpha;
	MyInterpolator llowerarmRotationInterpolator;
	float llowerarmInitAngel = 0.0f;
	float llowerarmDebugAngel;
	Transform3D llowerarmAxis;
	float llowerarmcos;
	float llowerarmsin;
	
	/**
	 * rhip1Stuff
	 */
	Alpha rhip1Alpha;
	MyInterpolator rhip1RotationInterpolator;
	float rhip1InitAngel = 0.0f;
	float rhip1DebugAngel;
	Transform3D rhip1Axis;
	float rhip1cos;
	float rhip1sin;
	TransformGroup rhip1ForAnimationGroup;
	
	/**
	 * rhip2Stuff
	 */
	Alpha rhip2Alpha;
	MyInterpolator rhip2RotationInterpolator;
	float rhip2InitAngle = 0.0f;
	float rhip2DebugAngel;
	Transform3D rhip2Axis;
	float rhip2cos;
	float rhip2sin;
	
	/**
	 * rthighStuff
	 */
	Alpha rthighAlpha;
	MyInterpolator rthighRotationInterpolator;
	float rthighInitAngel = 0.0f;
	float rthighDebugAngel;
	Transform3D rthighAxis;
	float rthighcos;
	float rthighsin;
	
	/**
	 * rshankStuff
	 */
	Alpha rshankAlpha;
	MyInterpolator rshankRotationInterpolator;
	float rshankInitAngel = 0.0f;
	float rshankDebugAngel;
	Transform3D rshankAxis;
	float rshankcos;
	float rshanksin;
	
	/**
	 * rankleStuff
	 */
	Alpha rankleAlpha;
	MyInterpolator rankleRotationInterpolator;
	float rankleInitAngel = 0.0f;
	float rankleDebugAngel;
	Transform3D rankleAxis;
	float ranklecos;
	float ranklesin;
	TransformGroup rshankForAnimationGroup;
	
	/**
	 * rfootStuff
	 */
	Alpha rfootAlpha;
	MyInterpolator rfootRotationInterpolator;
	float rfootInitAngel = 0.0f;
	float rfootDebugAngel;
	Transform3D rfootAxis;
	float rfootcos;
	float rfootsin;
	
	/**
	 * lhip1Stuff
	 */
	Alpha lhip1Alpha;
	MyInterpolator lhip1RotationInterpolator;
	float lhip1InitAngel = 0.0f;
	float lhip1DebugAngel;
	Transform3D lhip1Axis;
	float lhip1cos;
	float lhip1sin;
	TransformGroup lhip1ForAnimationGroup;
	
	/**
	 * rhip2Stuff
	 */
	Alpha lhip2Alpha;
	MyInterpolator lhip2RotationInterpolator;
	float lhip2InitAngle = 0.0f;
	float lhip2DebugAngel;
	Transform3D lhip2Axis;
	float lhip2cos;
	float lhip2sin;
	
	/**
	 * rthighStuff
	 */
	Alpha lthighAlpha;
	MyInterpolator lthighRotationInterpolator;
	float lthighInitAngel = 0.0f;
	float lthighDebugAngel;
	Transform3D lthighAxis;
	float lthighcos;
	float lthighsin;
	
	/**
	 * rshankStuff
	 */
	Alpha lshankAlpha;
	MyInterpolator lshankRotationInterpolator;
	float lshankInitAngel = 0.0f;
	float lshankDebugAngel;
	Transform3D lshankAxis;
	float lshankcos;
	float lshanksin;
	
	/**
	 * rankleStuff
	 */
	Alpha lankleAlpha;
	MyInterpolator lankleRotationInterpolator;
	float lankleInitAngel = 0.0f;
	float lankleDebugAngel;
	Transform3D lankleAxis;
	float lanklecos;
	float lanklesin;
	TransformGroup lshankForAnimationGroup;
	
	/**
	 * rfootStuff
	 */
	Alpha lfootAlpha;
	MyInterpolator lfootRotationInterpolator;
	float lfootInitAngel = 0.0f;
	float lfootDebugAngel;
	Transform3D lfootAxis;
	float lfootcos;
	float lfootsin;
	
	/**
	 *  the animationUIs
	 */
	int rshoulderDuration;
	int rupperarmDuration;
	int relbowDuration;
	int rlowerarmDuration;
	int lshoulderDuration;
	int lupperarmDuration;
	int lelbowDuration;
	int llowerarmDuration;
	int rhip1Duration;
	int rhip2Duration;
	int rthighDuration;
	int rshankDuration;
	int rankleDuration;
	int rfootDuration;
	int lhip1Duration;
	int lhip2Duration;
	int lthighDuration;
	int lshankDuration;
	int lankleDuration;
	int lfootDuration;
	
	boolean rshoulder = false;
	boolean rupperarm = false;
	boolean relbow = false;
	boolean rlowerarm = false;
	boolean lshoulder = false;
	boolean lupperarm = false;
	boolean lelbow = false;
	boolean llowerarm = false;
	boolean rhip1 = false;
	boolean rhip2 = false;
	boolean rthigh = false;
	boolean rshank = false;
	boolean rankle = false;
	boolean rfoot = false;
	boolean lhip1 = false;
	boolean lhip2 = false;
	boolean lthigh = false;
	boolean lshank = false;
	boolean lankle = false;
	boolean lfoot = false;
	
	NewDelayBehavior rshoulderBehavior;
	NewDelayBehavior rupperarmBehavior;
	NewDelayBehavior relbowBehavior;
	NewDelayBehavior rlowerarmBehavior;
	NewDelayBehavior lshoulderBehavior;
	NewDelayBehavior lupperarmBehavior;
	NewDelayBehavior lelbowBehavior;
	NewDelayBehavior llowerarmBehavior;
	NewDelayBehavior rhip1Behavior;
	NewDelayBehavior rhip2Behavior;
	NewDelayBehavior rthighBehavior;
	NewDelayBehavior rshankBehavior;
	NewDelayBehavior rankleBehavior;
	NewDelayBehavior rfootBehavior;
	NewDelayBehavior lhip1Behavior;
	NewDelayBehavior lhip2Behavior;
	NewDelayBehavior lthighBehavior;
	NewDelayBehavior lshankBehavior;
	NewDelayBehavior lankleBehavior;
	NewDelayBehavior lfootBehavior;
	
	ArrayList<MyInterpolator> rshoulderArrayList;
	ArrayList<MyInterpolator> rupperarmArrayList;
	ArrayList<MyInterpolator> relbowArrayList;
	ArrayList<MyInterpolator> rlowerarmArrayList;
	ArrayList<MyInterpolator> lshoulderArrayList;
	ArrayList<MyInterpolator> lupperarmArrayList;
	ArrayList<MyInterpolator> lelbowArrayList;
	ArrayList<MyInterpolator> llowerarmArrayList;
	ArrayList<MyInterpolator> rhip1ArrayList;
	ArrayList<MyInterpolator> rhip2ArrayList;
	ArrayList<MyInterpolator> rthighArrayList;
	ArrayList<MyInterpolator> rshankArrayList;
	ArrayList<MyInterpolator> rankleArrayList;
	ArrayList<MyInterpolator> rfootArrayList;
	ArrayList<MyInterpolator> lhip1ArrayList;
	ArrayList<MyInterpolator> lhip2ArrayList;
	ArrayList<MyInterpolator> lthighArrayList;
	ArrayList<MyInterpolator> lshankArrayList;
	ArrayList<MyInterpolator> lankleArrayList;
	ArrayList<MyInterpolator> lfootArrayList;
	
	int rshoulderRotationCounter = 0;
	int rupperarmRotationCounter = 0;
	int relbowRotationCounter = 0;
	int rlowerarmRotationCounter = 0;
	int lshoulderRotationCounter = 0;
	int lupperarmRotationCounter = 0;
	int lelbowRotationCounter = 0;
	int llowerarmRotationCounter = 0;
	int rhip1RotationCounter = 0;
	int rhip2RotationCounter = 0;
	int rthighRotationCounter = 0;
	int rshankRotationCounter = 0;
	int rankleRotationCounter = 0;
	int rfootRotationCounter = 0;
	int lhip1RotationCounter = 0;
	int lhip2RotationCounter = 0;
	int lthighRotationCounter = 0;
	int lshankRotationCounter = 0;
	int lankleRotationCounter = 0;
	int lfootRotationCounter = 0;
	
	/**
	 * for fileAnimation
	 */
	private NaoFileReader mNaoFileReader = null;
	private Vector<String> mTasksName;
	private HashMap<String, Task> mTaskMap;
	private HashMap<String, NaoPose> mPoses;

	private String[] nameInTree = JointNameTransform.nameInTree;
	private String[] nameInFile = JointNameTransform.nameInFile;

	private HashMap<String, String> jointNameTransMap = null;
	//	private HashMap<String, String> testJointNameTransMap = null;

	/**
	 * for debug
	 */
	TransformGroup rankleForAnimationGroup;
	Transform3D rankleForAnimation3D;
	TransformGroup rfootForAnimationGroup;
	Transform3D rfootForAnimation3D;
	
	TransformGroup lankleForAnimationGroup;
	Transform3D lankleForAnimation3D;
	TransformGroup lfootForAnimationGroup;
	Transform3D lfootForAnimation3D;

	private NaoPose mNaoPose;
	private String taskName;
	private String mCurrentPoseName;
	private String mNextTaskName;
	
	public AnimationMyUniverse(String naoTaskName, Vector<String> tasksName,
			HashMap<String, Task> taskMap, HashMap<String, NaoPose> naoPose) {
		/**
		 * for fileAnimation
		 */
		mTasksName = new Vector<String>();
		mTaskMap = new HashMap<String, Task>();
		mPoses = new HashMap<String, NaoPose>();
		
		mTasksName = tasksName;
		mTaskMap = taskMap;
		mPoses = naoPose;

		taskName = naoTaskName;
		mNextTaskName = taskName;

		jointNameTransMap = new HashMap<String, String>();
		for (int i = 0; i < nameInTree.length; i++) {
			jointNameTransMap.put(nameInTree[i], nameInFile[i]);
		}
		
		//		testJointNameTransMap = JointNameTransform.transMap;

		mBranchGroup = new BranchGroup();
		
		rotationsArrayList = new ArrayList<MyInterpolator>();
		rshoulderArrayList = new ArrayList<MyInterpolator>();
		rupperarmArrayList = new ArrayList<MyInterpolator>();
		relbowArrayList = new ArrayList<MyInterpolator>();
		rlowerarmArrayList = new ArrayList<MyInterpolator>();
		lshoulderArrayList = new ArrayList<MyInterpolator>();
		lupperarmArrayList = new ArrayList<MyInterpolator>();
		lelbowArrayList = new ArrayList<MyInterpolator>();
		llowerarmArrayList = new ArrayList<MyInterpolator>();
		rhip1ArrayList = new ArrayList<MyInterpolator>();
		rhip2ArrayList = new ArrayList<MyInterpolator>();
		rthighArrayList = new ArrayList<MyInterpolator>();
		rshankArrayList = new ArrayList<MyInterpolator>();
		rankleArrayList = new ArrayList<MyInterpolator>();
		rfootArrayList = new ArrayList<MyInterpolator>();
		lhip1ArrayList = new ArrayList<MyInterpolator>();
		lhip2ArrayList = new ArrayList<MyInterpolator>();
		lthighArrayList = new ArrayList<MyInterpolator>();
		lshankArrayList = new ArrayList<MyInterpolator>();
		lankleArrayList = new ArrayList<MyInterpolator>();
		lfootArrayList = new ArrayList<MyInterpolator>();
		
		/**
		 * the appearances
		 */
		ap = new Appearance();
		ColoringAttributes ca = new ColoringAttributes();
		ca.setColor(1.0f, 1.0f, 1.0f);
		PolygonAttributes pa = new PolygonAttributes();
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		ap.setColoringAttributes(ca);
		ap.setPolygonAttributes(pa);
		
		ap1 = new Appearance();
		ColoringAttributes ca1 = new ColoringAttributes();
		ca1.setColor(0.0f, 0.0f, 0.0f);
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setTransparency(0.5f);
		ta.setTransparencyMode(TransparencyAttributes.BLENDED);
		ap1.setColoringAttributes(ca1);
		ap1.setTransparencyAttributes(ta);
		
		ap2 = new Appearance();
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
		headSphere = new Head(0.39f, Primitive.GENERATE_NORMALS, 50, ap);
		headRoller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "headRoller", -45, 45);
		headRoller.setTrans(new Vector3f(0.0f, 0.45f, 0.0f));
		neckRoller = new Roller(0.02f, 0.2f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "neckRoller", -120, 120);
		neckRoller.setTrans(new Vector3f(0.0f, 0.75f, 0.0f));
		
		/**
		 * the right hand
		 */
		rshoulderRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rshoulderRoller",
				-120, 120);
		rshoulderRoller.setTrans(new Vector3f(-0.59f, 0.5f, 0.0f));
		rupperarmRoller = new Roller(0.02f, 0.02f, 0.2f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rupperarmRoller", -95, 1);
		rshoulderLimb = new Limb(0.3f, 0.3f, 0.3f, ap2);
		rshoulderLimb.setTrans(new Vector3f(-0.36f, 0.0f, 0.0f));
		relbowRoller = new Roller(0.02f, 0.2f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "relbowRoller", -120,
				120);
		relbowRoller.setTrans(new Vector3f(-0.33f, -0.46f, 0.0f));
		rlowerarmRoller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rlowerarmrRoller", -1, 90);
		rlowerarmLimb = new Limb(0.25f, 0.45f, 0.25f, ap2);
		rlowerarmLimb.setTrans(new Vector3f(0.0f, -0.5f, 0.0f));
		rupperarmForAnimationGroup = new TransformGroup();
		rupperarmForAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			
		/*
		 * the left hand
		 */
		lshoulderRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lshoulderRoller",
				-120, 120);
		lshoulderRoller.setTrans(new Vector3f(0.59f, 0.5f, 0.0f));
		lupperarmRoller = new Roller(0.02f, 0.02f, 0.2f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lupperarmRoller", -1, 95);
		lshoulderLimb = new Limb(0.3f, 0.3f, 0.3f, ap2);
		lshoulderLimb.setTrans(new Vector3f(0.36f, 0.0f, 0.0f));
		lelbowRoller = new Roller(0.02f, 0.2f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lelbowRoller", -120,
				120);
		lelbowRoller.setTrans(new Vector3f(0.33f, -0.46f, 0.0f));
		llowerarmRoller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "llowerarmrRoller", -90, 1);
		llowerarmLimb = new Limb(0.25f, 0.45f, 0.25f, ap2);
		llowerarmLimb.setTrans(new Vector3f(0.0f, -0.5f, 0.0f));
		lupperarmForAnimationGroup = new TransformGroup();
		lupperarmForAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		/**
		 * the right leg
		 */
		rlegTransform3d = new Transform3D();
		rlegTransform3d.setTranslation(new Vector3f(-0.379f, -1.1f, 0.0f));
		rlegTransformGroup = new TransformGroup(rlegTransform3d);
		rlegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		rlegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		rhip1Roller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rhip1Roller", -90, 1);
		rhip1Roller.rotZ(Math.PI/4);
		
		rhip2Roller = new Roller(0.02f, 0.02f, 0.2f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rhip2Roller", -45, 25);
		rthighRoller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rthighRoller", -25, 100);
		
		rthighLimb = new Limb(0.3f, 0.42f, 0.3f, ap2);
		rthighLimb.setTrans(new Vector3f(0.0f, -0.4f, 0.0f));
		
		rshankRoller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rshankRoller", -130, 1);
		rshankRoller.setTrans(new Vector3f(0.0f, -0.95f, 0.0f));
		
		rshankLimb = new Limb(0.3f, 0.45f, 0.3f, ap2);
		rshankLimb.setTrans(new Vector3f(0.0f, -0.58f, 0.0f));
		
		rankleRoller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rankleRoller", -45, 75);
		//		rankleRoller.setTrans(new Vector3f(0.0f, -1.15f, 0.0f));
	
		rfootRoller = new Roller(0.02f, 0.02f, 0.2f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rfootRoller", -25, 45);
		rfootLimb = new Limb(0.33f, 0.15f, 0.65f, ap2);
		rfootLimb.setTrans(new Vector3f(0.0f, -0.33f, 0.33f));
		
		rhip1ForAnimationGroup = new TransformGroup();
		rhip1ForAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		rshankForAnimationGroup = new TransformGroup();
		rshankForAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		rankleForAnimation3D = new Transform3D();
		rankleForAnimationGroup = new TransformGroup(rankleForAnimation3D);
		rankleForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		rankleForAnimation3D.setTranslation(new Vector3f(0.0f, -0.6f, 0.0f));
		rankleForAnimationGroup.setTransform(rankleForAnimation3D);
		
		rfootForAnimation3D = new Transform3D();
		rfootForAnimationGroup = new TransformGroup(rfootForAnimation3D);
		rfootForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		/**
		 * the left leg
		 */
		llegTransform3d = new Transform3D();
		llegTransform3d.setTranslation(new Vector3f(0.379f, -1.1f, 0.0f));
		llegTransformGroup = new TransformGroup(llegTransform3d);
		llegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		llegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		lhip1Roller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lhip1Roller", -90, 1);
		lhip1Roller.rotZ(-(Math.PI/4));
		
		lhip2Roller = new Roller(0.02f, 0.02f, 0.2f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lhip2Roller", -25, 45);
		lthighRoller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lthighRoller", -25, 100);
		
		lthighLimb = new Limb(0.3f, 0.42f, 0.3f, ap2);
		lthighLimb.setTrans(new Vector3f(0.0f, -0.4f, 0.0f));
		
		lshankRoller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lshankRoller", -130, 1);
		lshankRoller.setTrans(new Vector3f(0.0f, -0.95f, 0.0f));
		
		lshankLimb = new Limb(0.3f, 0.45f, 0.3f, ap2);
		lshankLimb.setTrans(new Vector3f(0.0f, -0.58f, 0.0f));
		
		lankleRoller = new Roller(0.2f, 0.02f, 0.02f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lankleRoller", -45, 75);
		//		lankleRoller.setTrans(new Vector3f(0.0f, -1.15f, 0.0f));
	
		lfootRoller = new Roller(0.02f, 0.02f, 0.2f, Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lfootRoller", -45, 25);
		lfootLimb = new Limb(0.33f, 0.15f, 0.65f, ap2);
		lfootLimb.setTrans(new Vector3f(0.0f, -0.33f, 0.33f));
		
		lhip1ForAnimationGroup = new TransformGroup();
		lhip1ForAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		lshankForAnimationGroup = new TransformGroup();
		lshankForAnimationGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		lankleForAnimation3D = new Transform3D();
		lankleForAnimationGroup = new TransformGroup(lankleForAnimation3D);
		lankleForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		lankleForAnimation3D.setTranslation(new Vector3f(0.0f, -0.6f, 0.0f));
		lankleForAnimationGroup.setTransform(lankleForAnimation3D);

		lfootForAnimation3D = new Transform3D();
		lfootForAnimationGroup = new TransformGroup(lfootForAnimation3D);
		lfootForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		/**
		 * the main body
		 */
		bodyLimb.getmTransformGroup().addChild(neckRoller.getmTransformGroup());
		neckRoller.getmTransformGroup().addChild(headRoller.getmTransformGroup());
		headRoller.getmTransformGroup().addChild(headSphere.getmTransformGroup());
		
		/*
		 * the right hand
		 */
		bodyLimb.getmTransformGroup().addChild(rshoulderRoller.getmTransformGroup());
		rshoulderRoller.getmTransformGroup().addChild(rupperarmRoller.getmTransformGroup());
		rupperarmRoller.getmTransformGroup().addChild(rupperarmForAnimationGroup);
		rupperarmForAnimationGroup.addChild(rshoulderLimb.getmTransformGroup());
		rupperarmForAnimationGroup.addChild(relbowRoller.getmTransformGroup());
		relbowRoller.getmTransformGroup().addChild(rlowerarmRoller.getmTransformGroup());
		rlowerarmRoller.getmTransformGroup().addChild(rlowerarmLimb.getmTransformGroup());
	
		/**
		 * the left hand
		 */
		bodyLimb.getmTransformGroup().addChild(lshoulderRoller.getmTransformGroup());
		lshoulderRoller.getmTransformGroup().addChild(lupperarmRoller.getmTransformGroup());
		lupperarmRoller.getmTransformGroup().addChild(lupperarmForAnimationGroup);
		lupperarmForAnimationGroup.addChild(lshoulderLimb.getmTransformGroup());
		lupperarmForAnimationGroup.addChild(lelbowRoller.getmTransformGroup());
		lelbowRoller.getmTransformGroup().addChild(llowerarmRoller.getmTransformGroup());
		llowerarmRoller.getmTransformGroup().addChild(llowerarmLimb.getmTransformGroup());
	
		/**
		 * the right leg
		 */
		bodyLimb.getmTransformGroup().addChild(rlegTransformGroup);
		rlegTransformGroup.addChild(rhip1ForAnimationGroup);
		rhip1ForAnimationGroup.addChild(rhip1Roller.getmTransformGroup());
		rhip1ForAnimationGroup.addChild(rhip2Roller.getmTransformGroup());
		rhip2Roller.getmTransformGroup().addChild(rthighRoller.getmTransformGroup());
		rthighRoller.getmTransformGroup().addChild(rthighLimb.getmTransformGroup());
		rthighRoller.getmTransformGroup().addChild(rshankRoller.getmTransformGroup());
		rshankRoller.getmTransformGroup().addChild(rshankForAnimationGroup);
		rshankForAnimationGroup.addChild(rshankLimb.getmTransformGroup());
		//		rshankForAnimationGroup.addChild(rankleRoller.getmTransformGroup());
		rshankLimb.getmTransformGroup().addChild(rankleForAnimationGroup);
		//		rankleRoller.getmTransformGroup().addChild(rfootRoller.getmTransformGroup());
		rankleForAnimationGroup.addChild(rankleRoller.getmTransformGroup());
		rankleRoller.getmTransformGroup().addChild(rfootForAnimationGroup);
		rfootForAnimationGroup.addChild(rfootRoller.getmTransformGroup());
		rfootRoller.getmTransformGroup().addChild(rfootLimb.getmTransformGroup());
		
		/**
		 * the left leg
		 */
		bodyLimb.getmTransformGroup().addChild(llegTransformGroup);
		llegTransformGroup.addChild(lhip1ForAnimationGroup);
		lhip1ForAnimationGroup.addChild(lhip1Roller.getmTransformGroup());
		lhip1ForAnimationGroup.addChild(lhip2Roller.getmTransformGroup());
		lhip2Roller.getmTransformGroup().addChild(lthighRoller.getmTransformGroup());
		lthighRoller.getmTransformGroup().addChild(lthighLimb.getmTransformGroup());
		lthighRoller.getmTransformGroup().addChild(lshankRoller.getmTransformGroup());
		lshankRoller.getmTransformGroup().addChild(lshankForAnimationGroup);
		lshankForAnimationGroup.addChild(lshankLimb.getmTransformGroup());
		//		lshankForAnimationGroup.addChild(lankleRoller.getmTransformGroup());
		lshankLimb.getmTransformGroup().addChild(lankleForAnimationGroup);
		lankleForAnimationGroup.addChild(lankleRoller.getmTransformGroup());
		//		lankleRoller.getmTransformGroup().addChild(lfootRoller.getmTransformGroup());
		lankleRoller.getmTransformGroup().addChild(lfootForAnimationGroup);
		lfootForAnimationGroup.addChild(lfootRoller.getmTransformGroup());
		lfootRoller.getmTransformGroup().addChild(lfootLimb.getmTransformGroup());
		
		
		
		mTransform3d = new Transform3D();
		mTransform3d.setTranslation(new Vector3f(0.0f, 0.88f, -5.3f));
		mTransform3d.setScale(0.8);
		mTransformGroup = new TransformGroup(mTransform3d);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);	
		mTransformGroup.addChild(bodyLimb.getmTransformGroup());
		
		mBoundingSphere = new BoundingSphere(new Point3d(0.0f, 0.0f, 0.0f),100.0f);
		mColor3f = new Color3f(0.3f, 0.3f, 0.3f);
		mBackground = new Background(mColor3f);
		mBackground.setApplicationBounds(mBoundingSphere);
		
		/**
		 *  the keyBoardBehavior
		 */
		KeyNavigatorBehavior keyNavigatorBehavior = new KeyNavigatorBehavior(mTransformGroup);
		keyNavigatorBehavior.setSchedulingBounds(mBoundingSphere);
		mBranchGroup.addChild(keyNavigatorBehavior);
	
		/**
		 *  the mouseBehavior
		 */
		MouseRotate mouseRotate = new MouseRotate();
		mouseRotate.setTransformGroup(mTransformGroup);
		mouseRotate.setupCallback(this);
		mouseRotate.setSchedulingBounds(mBoundingSphere);
		mBranchGroup.addChild(mouseRotate);
		
		MouseTranslate mouseTranslate = new MouseTranslate();
		mouseTranslate.setTransformGroup(mTransformGroup);
		mouseTranslate.setupCallback(this);
		mouseTranslate.setSchedulingBounds(mBoundingSphere);
		mBranchGroup.addChild(mouseTranslate);
	
		MouseZoom mouseZoom = new MouseZoom();
		mouseZoom.setTransformGroup(mTransformGroup);
		mouseZoom.setupCallback(this);
		mouseZoom.setSchedulingBounds(mBoundingSphere);
		mBranchGroup.addChild(mouseZoom);
		
		serialTask();
		showAnimation();

	}
	private void showAnimation() {
		try {
			mBranchGroup.addChild(mBackground);
			mBranchGroup.addChild(mTransformGroup);

			rshoulderBehavior = new NewDelayBehavior(rshoulderArrayList);
			rshoulderBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(rshoulderBehavior);
			rupperarmBehavior = new NewDelayBehavior(rupperarmArrayList);
			rupperarmBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(rupperarmBehavior);
			relbowBehavior = new NewDelayBehavior(relbowArrayList);
			relbowBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(relbowBehavior);
			rlowerarmBehavior = new NewDelayBehavior(rlowerarmArrayList);
			rlowerarmBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(rlowerarmBehavior);
			lshoulderBehavior = new NewDelayBehavior(lshoulderArrayList);
			lshoulderBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(lshoulderBehavior);
			lupperarmBehavior = new NewDelayBehavior(lupperarmArrayList);
			lupperarmBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(lupperarmBehavior);
			lelbowBehavior = new NewDelayBehavior(lelbowArrayList);
			lelbowBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(lelbowBehavior);
			llowerarmBehavior = new NewDelayBehavior(llowerarmArrayList);
			llowerarmBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(llowerarmBehavior);
			rhip1Behavior = new NewDelayBehavior(rhip1ArrayList);
			rhip1Behavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(rhip1Behavior);
			rhip2Behavior = new NewDelayBehavior(rhip2ArrayList);
			rhip2Behavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(rhip2Behavior);
			rthighBehavior = new NewDelayBehavior(rthighArrayList);
			rthighBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(rthighBehavior);
			rshankBehavior = new NewDelayBehavior(rshankArrayList);
			rshankBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(rshankBehavior);
			rankleBehavior = new NewDelayBehavior(rankleArrayList);
			rankleBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(rankleBehavior);
			rfootBehavior = new NewDelayBehavior(rfootArrayList);
			rfootBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(rfootBehavior);
			lhip1Behavior = new NewDelayBehavior(lhip1ArrayList);
			lhip1Behavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(lhip1Behavior);
			lhip2Behavior = new NewDelayBehavior(lhip2ArrayList);
			lhip2Behavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(lhip2Behavior);
			lthighBehavior = new NewDelayBehavior(lthighArrayList);
			lthighBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(lthighBehavior);
			lshankBehavior = new NewDelayBehavior(lshankArrayList);
			lshankBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(lshankBehavior);
			lankleBehavior = new NewDelayBehavior(lankleArrayList);
			lankleBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(lankleBehavior);
			lfootBehavior = new NewDelayBehavior(lfootArrayList);
			lfootBehavior.setSchedulingBounds(mBoundingSphere);
			mBranchGroup.addChild(lfootBehavior);

			mBranchGroup.compile();

			mDelay = new ShowTheAnimation(this.mBranchGroup, this);
			if (rshoulder) {
				this.rshoulderArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.rshoulderArrayList.get(0).getAlpha().setStartTime(time);

				rshoulder = false;

			}

			if (rupperarm) {
				this.rupperarmArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.rupperarmArrayList.get(0).getAlpha().setStartTime(time);

				rupperarm = false;
			}

			if (relbow) {
				this.relbowArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.relbowArrayList.get(0).getAlpha().setStartTime(time);

				relbow = false;
			}

			if (rlowerarm) {
				this.rlowerarmArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.rlowerarmArrayList.get(0).getAlpha().setStartTime(time);

				rlowerarm = false;
			}

			if (lshoulder) {
				this.lshoulderArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.lshoulderArrayList.get(0).getAlpha().setStartTime(time);

				lshoulder = false;

			}

			if (lupperarm) {
				this.lupperarmArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.lupperarmArrayList.get(0).getAlpha().setStartTime(time);

				lupperarm = false;

			}

			if (lelbow) {
				this.lelbowArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.lelbowArrayList.get(0).getAlpha().setStartTime(time);

				lelbow = false;
			}

			if (llowerarm) {
				this.llowerarmArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.llowerarmArrayList.get(0).getAlpha().setStartTime(time);

				llowerarm = false;
			}

			if (rhip1) {
				this.rhip1ArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.rhip1ArrayList.get(0).getAlpha().setStartTime(time);

				rhip1 = false;
			}

			if (rhip2) {
				this.rhip2ArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.rhip2ArrayList.get(0).getAlpha().setStartTime(time);

				rhip2 = false;
			}

			if (rthigh) {
				this.rthighArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.rthighArrayList.get(0).getAlpha().setStartTime(time);

				rthigh = false;
			}

			if (rshank) {
				this.rshankArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.rshankArrayList.get(0).getAlpha().setStartTime(time);

				rshank = false;
			}

			if (rankle) {
				this.rankleArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.rankleArrayList.get(0).getAlpha().setStartTime(time);

				rankle = false;
				//				System.out.println("rankleTaskExcute!");
			}

			if (rfoot) {
				this.rfootArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.rfootArrayList.get(0).getAlpha().setStartTime(time);

				rfoot = false;
			}

			if (lhip1) {
				this.lhip1ArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.lhip1ArrayList.get(0).getAlpha().setStartTime(time);

				lhip1 = false;
			}

			if (lhip2) {
				this.lhip2ArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.lhip2ArrayList.get(0).getAlpha().setStartTime(time);

				lhip2 = false;
			}

			if (lthigh) {
				this.lthighArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.lthighArrayList.get(0).getAlpha().setStartTime(time);

				lthigh = false;
			}

			if (lshank) {
				this.lshankArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.lshankArrayList.get(0).getAlpha().setStartTime(time);

				lshank = false;
			}

			if (lankle) {
				this.lankleArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.lankleArrayList.get(0).getAlpha().setStartTime(time);

				lankle = false;
			}

			if (lfoot) {
				this.lfootArrayList.get(0).setEnable(true);
				long time = System.currentTimeMillis();
				this.lfootArrayList.get(0).getAlpha().setStartTime(time);

				lfoot = false;
			}
		} catch (RestrictedAccessException re) {
			//			System.out.println("please release the old nao");
		}
	}
	
	public void releaseAnimation() {
		//		System.out.println("releasing");
		mBranchGroup = new BranchGroup();

		rotationsArrayList = new ArrayList<MyInterpolator>();
		rshoulderArrayList = new ArrayList<MyInterpolator>();
		rupperarmArrayList = new ArrayList<MyInterpolator>();
		relbowArrayList = new ArrayList<MyInterpolator>();
		rlowerarmArrayList = new ArrayList<MyInterpolator>();
		lshoulderArrayList = new ArrayList<MyInterpolator>();
		lupperarmArrayList = new ArrayList<MyInterpolator>();
		lelbowArrayList = new ArrayList<MyInterpolator>();
		llowerarmArrayList = new ArrayList<MyInterpolator>();
		rhip1ArrayList = new ArrayList<MyInterpolator>();
		rhip2ArrayList = new ArrayList<MyInterpolator>();
		rthighArrayList = new ArrayList<MyInterpolator>();
		rshankArrayList = new ArrayList<MyInterpolator>();
		rankleArrayList = new ArrayList<MyInterpolator>();
		rfootArrayList = new ArrayList<MyInterpolator>();
		lhip1ArrayList = new ArrayList<MyInterpolator>();
		lhip2ArrayList = new ArrayList<MyInterpolator>();
		lthighArrayList = new ArrayList<MyInterpolator>();
		lshankArrayList = new ArrayList<MyInterpolator>();
		lankleArrayList = new ArrayList<MyInterpolator>();
		lfootArrayList = new ArrayList<MyInterpolator>();

		/**
		 * the appearances
		 */
		ap = new Appearance();
		ColoringAttributes ca = new ColoringAttributes();
		ca.setColor(1.0f, 1.0f, 1.0f);
		PolygonAttributes pa = new PolygonAttributes();
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		ap.setColoringAttributes(ca);
		ap.setPolygonAttributes(pa);

		ap1 = new Appearance();
		ColoringAttributes ca1 = new ColoringAttributes();
		ca1.setColor(0.0f, 0.0f, 0.0f);
		TransparencyAttributes ta = new TransparencyAttributes();
		ta.setTransparency(0.5f);
		ta.setTransparencyMode(TransparencyAttributes.BLENDED);
		ap1.setColoringAttributes(ca1);
		ap1.setTransparencyAttributes(ta);

		ap2 = new Appearance();
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
		headSphere = new Head(0.39f, Primitive.GENERATE_NORMALS, 50, ap);
		headRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "headRoller", -45, 45);
		headRoller.setTrans(new Vector3f(0.0f, 0.45f, 0.0f));
		neckRoller = new Roller(0.02f, 0.2f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "neckRoller", -120, 120);
		neckRoller.setTrans(new Vector3f(0.0f, 0.75f, 0.0f));

		/**
		 * the right hand
		 */
		rshoulderRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rshoulderRoller",
				-120, 120);
		rshoulderRoller.setTrans(new Vector3f(-0.59f, 0.5f, 0.0f));
		rupperarmRoller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rupperarmRoller", -95,
				1);
		rshoulderLimb = new Limb(0.3f, 0.3f, 0.3f, ap2);
		rshoulderLimb.setTrans(new Vector3f(-0.36f, 0.0f, 0.0f));
		relbowRoller = new Roller(0.02f, 0.2f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "relbowRoller", -120,
				120);
		relbowRoller.setTrans(new Vector3f(-0.33f, -0.46f, 0.0f));
		rlowerarmRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rlowerarmrRoller", -1,
				90);
		rlowerarmLimb = new Limb(0.25f, 0.45f, 0.25f, ap2);
		rlowerarmLimb.setTrans(new Vector3f(0.0f, -0.5f, 0.0f));
		rupperarmForAnimationGroup = new TransformGroup();
		rupperarmForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			
		/*
		 * the left hand
		 */
		lshoulderRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lshoulderRoller",
				-120, 120);
		lshoulderRoller.setTrans(new Vector3f(0.59f, 0.5f, 0.0f));
		lupperarmRoller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lupperarmRoller", -1,
				95);
		lshoulderLimb = new Limb(0.3f, 0.3f, 0.3f, ap2);
		lshoulderLimb.setTrans(new Vector3f(0.36f, 0.0f, 0.0f));
		lelbowRoller = new Roller(0.02f, 0.2f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lelbowRoller", -120,
				120);
		lelbowRoller.setTrans(new Vector3f(0.33f, -0.46f, 0.0f));
		llowerarmRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "llowerarmrRoller",
				-90, 1);
		llowerarmLimb = new Limb(0.25f, 0.45f, 0.25f, ap2);
		llowerarmLimb.setTrans(new Vector3f(0.0f, -0.5f, 0.0f));
		lupperarmForAnimationGroup = new TransformGroup();
		lupperarmForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		/**
		 * the right leg
		 */
		rlegTransform3d = new Transform3D();
		rlegTransform3d.setTranslation(new Vector3f(-0.379f, -1.1f, 0.0f));
		rlegTransformGroup = new TransformGroup(rlegTransform3d);
		rlegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		rlegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		rhip1Roller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rhip1Roller", -90, 1);
		rhip1Roller.rotZ(Math.PI / 4);

		rhip2Roller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rhip2Roller", -45, 25);
		rthighRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rthighRoller", -25,
				100);

		rthighLimb = new Limb(0.3f, 0.42f, 0.3f, ap2);
		rthighLimb.setTrans(new Vector3f(0.0f, -0.4f, 0.0f));

		rshankRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rshankRoller", -130, 1);
		rshankRoller.setTrans(new Vector3f(0.0f, -0.95f, 0.0f));

		rshankLimb = new Limb(0.3f, 0.45f, 0.3f, ap2);
		rshankLimb.setTrans(new Vector3f(0.0f, -0.58f, 0.0f));

		rankleRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rankleRoller", -45, 75);
		rankleRoller.setTrans(new Vector3f(0.0f, -1.15f, 0.0f));

		rfootRoller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "rfootRoller", -25, 45);
		rfootLimb = new Limb(0.33f, 0.15f, 0.65f, ap2);
		rfootLimb.setTrans(new Vector3f(0.0f, -0.33f, 0.33f));

		rhip1ForAnimationGroup = new TransformGroup();
		rhip1ForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		rshankForAnimationGroup = new TransformGroup();
		rshankForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		/**
		 * the left leg
		 */
		llegTransform3d = new Transform3D();
		llegTransform3d.setTranslation(new Vector3f(0.379f, -1.1f, 0.0f));
		llegTransformGroup = new TransformGroup(llegTransform3d);
		llegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		llegTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		lhip1Roller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lhip1Roller", -90, 1);
		lhip1Roller.rotZ(-(Math.PI / 4));

		lhip2Roller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lhip2Roller", -25, 45);
		lthighRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lthighRoller", -25,
				100);

		lthighLimb = new Limb(0.3f, 0.42f, 0.3f, ap2);
		lthighLimb.setTrans(new Vector3f(0.0f, -0.4f, 0.0f));

		lshankRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lshankRoller", -130, 1);
		lshankRoller.setTrans(new Vector3f(0.0f, -0.95f, 0.0f));

		lshankLimb = new Limb(0.3f, 0.45f, 0.3f, ap2);
		lshankLimb.setTrans(new Vector3f(0.0f, -0.58f, 0.0f));

		lankleRoller = new Roller(0.2f, 0.02f, 0.02f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lankleRoller", -45, 75);
		lankleRoller.setTrans(new Vector3f(0.0f, -1.15f, 0.0f));

		lfootRoller = new Roller(0.02f, 0.02f, 0.2f,
				Primitive.ENABLE_APPEARANCE_MODIFY, ap, "lfootRoller", -45, 25);
		lfootLimb = new Limb(0.33f, 0.15f, 0.65f, ap2);
		lfootLimb.setTrans(new Vector3f(0.0f, -0.33f, 0.33f));

		lhip1ForAnimationGroup = new TransformGroup();
		lhip1ForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		lshankForAnimationGroup = new TransformGroup();
		lshankForAnimationGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		/**
		 * the main body
		 */
		bodyLimb.getmTransformGroup().addChild(neckRoller.getmTransformGroup());
		neckRoller.getmTransformGroup().addChild(
				headRoller.getmTransformGroup());
		headRoller.getmTransformGroup().addChild(
				headSphere.getmTransformGroup());

		/*
		 * the right hand
		 */
		bodyLimb.getmTransformGroup().addChild(
				rshoulderRoller.getmTransformGroup());
		rshoulderRoller.getmTransformGroup().addChild(
				rupperarmRoller.getmTransformGroup());
		rupperarmRoller.getmTransformGroup().addChild(
				rupperarmForAnimationGroup);
		rupperarmForAnimationGroup.addChild(rshoulderLimb.getmTransformGroup());
		rupperarmForAnimationGroup.addChild(relbowRoller.getmTransformGroup());
		relbowRoller.getmTransformGroup().addChild(
				rlowerarmRoller.getmTransformGroup());
		rlowerarmRoller.getmTransformGroup().addChild(
				rlowerarmLimb.getmTransformGroup());

		/**
		 * the left hand
		 */
		bodyLimb.getmTransformGroup().addChild(
				lshoulderRoller.getmTransformGroup());
		lshoulderRoller.getmTransformGroup().addChild(
				lupperarmRoller.getmTransformGroup());
		lupperarmRoller.getmTransformGroup().addChild(
				lupperarmForAnimationGroup);
		lupperarmForAnimationGroup.addChild(lshoulderLimb.getmTransformGroup());
		lupperarmForAnimationGroup.addChild(lelbowRoller.getmTransformGroup());
		lelbowRoller.getmTransformGroup().addChild(
				llowerarmRoller.getmTransformGroup());
		llowerarmRoller.getmTransformGroup().addChild(
				llowerarmLimb.getmTransformGroup());

		/**
		 * the right leg
		 */
		bodyLimb.getmTransformGroup().addChild(rlegTransformGroup);
		rlegTransformGroup.addChild(rhip1ForAnimationGroup);
		rhip1ForAnimationGroup.addChild(rhip1Roller.getmTransformGroup());
		rhip1ForAnimationGroup.addChild(rhip2Roller.getmTransformGroup());
		rhip2Roller.getmTransformGroup().addChild(
				rthighRoller.getmTransformGroup());
		rthighRoller.getmTransformGroup().addChild(
				rthighLimb.getmTransformGroup());
		rthighRoller.getmTransformGroup().addChild(
				rshankRoller.getmTransformGroup());
		rshankRoller.getmTransformGroup().addChild(rshankForAnimationGroup);
		rshankForAnimationGroup.addChild(rshankLimb.getmTransformGroup());
		rshankForAnimationGroup.addChild(rankleRoller.getmTransformGroup());
		rankleRoller.getmTransformGroup().addChild(
				rfootRoller.getmTransformGroup());
		rfootRoller.getmTransformGroup().addChild(
				rfootLimb.getmTransformGroup());

		/**
		 * the light leg
		 */
		bodyLimb.getmTransformGroup().addChild(llegTransformGroup);
		llegTransformGroup.addChild(lhip1ForAnimationGroup);
		lhip1ForAnimationGroup.addChild(lhip1Roller.getmTransformGroup());
		lhip1ForAnimationGroup.addChild(lhip2Roller.getmTransformGroup());
		lhip2Roller.getmTransformGroup().addChild(
				lthighRoller.getmTransformGroup());
		lthighRoller.getmTransformGroup().addChild(
				lthighLimb.getmTransformGroup());
		lthighRoller.getmTransformGroup().addChild(
				lshankRoller.getmTransformGroup());
		lshankRoller.getmTransformGroup().addChild(lshankForAnimationGroup);
		lshankForAnimationGroup.addChild(lshankLimb.getmTransformGroup());
		lshankForAnimationGroup.addChild(lankleRoller.getmTransformGroup());
		lankleRoller.getmTransformGroup().addChild(
				lfootRoller.getmTransformGroup());
		lfootRoller.getmTransformGroup().addChild(
				lfootLimb.getmTransformGroup());

		mTransform3d = new Transform3D();
		mTransform3d.setTranslation(new Vector3f(0.0f, 0.88f, -5.3f));
		mTransform3d.setScale(0.8);
		mTransformGroup = new TransformGroup(mTransform3d);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		mTransformGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		mTransformGroup.addChild(bodyLimb.getmTransformGroup());
		
		mBoundingSphere = new BoundingSphere(new Point3d(0.0f, 0.0f, 0.0f),
				100.0f);
		mColor3f = new Color3f(0.1f, 0.5f, 0.5f);
		mBackground = new Background(mColor3f);
		mBackground.setApplicationBounds(mBoundingSphere);
		
		/**
		 * the keyBoardBehavior
		 */
		KeyNavigatorBehavior keyNavigatorBehavior = new KeyNavigatorBehavior(
				mTransformGroup);
		keyNavigatorBehavior.setSchedulingBounds(mBoundingSphere);
		mBranchGroup.addChild(keyNavigatorBehavior);

		/**
		 * the mouseBehavior
		 */
		MouseRotate mouseRotate = new MouseRotate();
		mouseRotate.setTransformGroup(mTransformGroup);
		mouseRotate.setupCallback(this);
		mouseRotate.setSchedulingBounds(mBoundingSphere);
		mBranchGroup.addChild(mouseRotate);
		
		MouseTranslate mouseTranslate = new MouseTranslate();
		mouseTranslate.setTransformGroup(mTransformGroup);
		mouseTranslate.setupCallback(this);
		mouseTranslate.setSchedulingBounds(mBoundingSphere);
		mBranchGroup.addChild(mouseTranslate);

		MouseZoom mouseZoom = new MouseZoom();
		mouseZoom.setTransformGroup(mTransformGroup);
		mouseZoom.setupCallback(this);
		mouseZoom.setSchedulingBounds(mBoundingSphere);
		mBranchGroup.addChild(mouseZoom);
		
		rshoulderRotationCounter = 0;
		rupperarmRotationCounter = 0;
		relbowRotationCounter = 0;
		rlowerarmRotationCounter = 0;
		lshoulderRotationCounter = 0;
		lupperarmRotationCounter = 0;
		lelbowRotationCounter = 0;
		llowerarmRotationCounter = 0;
		rhip1RotationCounter = 0;
		rhip2RotationCounter = 0;
		rthighRotationCounter = 0;
		rshankRotationCounter = 0;
		rankleRotationCounter = 0;
		rfootRotationCounter = 0;
		lhip1RotationCounter = 0;
		lhip2RotationCounter = 0;
		lthighRotationCounter = 0;
		lshankRotationCounter = 0;
		lankleRotationCounter = 0;
		lfootRotationCounter = 0;
		
		rshoulderInitAngel = 0.0f;
		rupperarmInitAngle = 0.0f;
		relbowInitAngel = 0.0f;
		rlowerarmInitAngel = 0.0f;
		lshoulderInitAngel = 0.0f;
		lupperarmInitAngle = 0.0f;
		lelbowInitAngel = 0.0f;
		llowerarmInitAngel = 0.0f;
		rhip1InitAngel = 0.0f;
		rhip2InitAngle = 0.0f;
		rthighInitAngel = 0.0f;
		rshankInitAngel = 0.0f;
		rankleInitAngel = 0.0f;
		rfootInitAngel = 0.0f;
		lhip1InitAngel = 0.0f;
		lhip2InitAngle = 0.0f;
		lthighInitAngel = 0.0f;
		lshankInitAngel = 0.0f;
		lankleInitAngel = 0.0f;
		lfootInitAngel = 0.0f;
		
		mDelay.setVisible(false);

		//		System.out.println("released");
	}

	private void singleTask() {
		try{
					createTasks();
		
					initAxis();
					rshoulder = true;
					rupperarm = true;
					relbow = true;
					rlowerarm = true;
					lshoulder = true;
					lupperarm = true;
					lelbow = true;
					llowerarm = true;
					rhip1 = true;
					rhip2 = true;
					rthigh = true;
					rshank = true;
					rankle = true;
					rfoot = true;
					lhip1 = true;
					lhip2 = true;
					lthigh = true;
					lshank = true;
					lankle = true;
					lfoot = true;
			//					loadTask();
		} catch (RestrictedAccessException re) {
			JOptionPane.showMessageDialog(null, "please press relese button",
					"alert", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void serialTask() {
		try {
			do {
				taskName = mNextTaskName;

				mCurrentPoseName = mTaskMap.get
				(taskName).getDesiredPose();

				mNextTaskName = mTaskMap.get(taskName).getNextTaskName();

				//				createTasks();
				//				rhip1 = true;
				//				lhip1 = true;
				setTaskValue();
				initAxis();
				loadTask();

			}while (!mTaskMap.get(taskName).getNextTaskName

			().equals("null"));

			} catch (RestrictedAccessException re) {
			JOptionPane.showMessageDialog(null, "please press relese button",
					"alert", JOptionPane.ERROR_MESSAGE);
			} catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null,
					"there is no such pose please select again", "alert",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) throws RestrictedAccessException {

	}

	private void createTasks() throws RestrictedAccessException {
		setTaskValue();
		createRshoulderTask();
		createRupperarmTask();
		createRelbowTask();
		createRlowerarmTask();
		createLshoulderTask();
		createLupperarmTask();
		createLelbowTask();
		createLowerarmTask();
		createRhip1Task();
		createRhip2Task();
		createRthighTask();
		createRshankTask();
		createRankleTask();
		createRfootTask();
		createLhip1Task();
		createLhip2Task();
		createLthighTask();
		createLshankTask();
		createLankleTask();
		createLfootTask();
	}

	private void setTaskValue() throws RestrictedAccessException {
		try {
			//			System.out.println("clea");
			rshoulder = true;
			rshoulderDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
					jointNameTransMap.get("rshoulder"));
		} catch (NullPointerException e) {
			rshoulderDebugAngel = rshoulderInitAngel;
			rshoulder = true;
		}
			rshoulderDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			rupperarm = true;
			rupperarmDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("rupperarm"));
		} catch (NullPointerException e) {
			rupperarmDebugAngel = rupperarmInitAngle;
		}
			rupperarmDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			relbow = true;
			relbowDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("relbow"));
		} catch (NullPointerException e) {
			relbowDebugAngel = relbowInitAngel;
		}
			relbowDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			rlowerarm = true;
			rlowerarmDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("rlowerarm"));
		} catch (NullPointerException e) {
			rlowerarmDebugAngel = rlowerarmInitAngel;
		}
			rlowerarmDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			lshoulder = true;
			lshoulderDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
					jointNameTransMap.get("lshoulder"));
		} catch (NullPointerException e) {
			lshoulderDebugAngel = lshoulderInitAngel;
		}
		lshoulderDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			lupperarm = true;
			lupperarmDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("lupperarm"));
		} catch (NullPointerException e) {
			lupperarmDebugAngel = lupperarmInitAngle;
		}
		lupperarmDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			lelbow = true;
			lelbowDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("lelbow"));
		} catch (NullPointerException e) {
			lelbowDebugAngel = lelbowInitAngel;
		}
			lelbowDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			llowerarm = true;
			llowerarmDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("llowerarm"));
		} catch (NullPointerException e) {
			llowerarmDebugAngel = llowerarmInitAngel;
		}
			llowerarmDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			rhip1 = true;
			rhip1DebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("rhip1"));
		} catch (NullPointerException e) {
			rhip1DebugAngel = rhip1InitAngel;
		}
			rhip1Duration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			rhip2 = true;
			rhip2DebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("rhip2"));
		} catch (NullPointerException e) {
			rhip2DebugAngel = rhip2InitAngle;
		}
			rhip2Duration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			rthigh = true;
			rthighDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("rthigh"));
		} catch (NullPointerException e) {
			rthighDebugAngel = rthighInitAngel;
		}
			rthighDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			rshank = true;
			rshankDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
					jointNameTransMap.get("rshank"));
		} catch (NullPointerException e) {
			rshankDebugAngel = rshankInitAngel;
		}
			rshankDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			rankle = true;
			rankleDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
					jointNameTransMap.get("rankle"));
			//			System.out.println("rankleRot" + rankleDebugAngel);
		} catch (NullPointerException e) {
			rankleDebugAngel = rankleInitAngel;
			//			System.out.println("rankleException");
		}
			rankleDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			rfoot = true;
			rfootDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("rfoot"));
		} catch (NullPointerException e) {
			rfootDebugAngel = rfootInitAngel;
		}
			rfootDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			lhip1 = true;
			lhip1DebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("lhip1"));
		} catch (NullPointerException e) {
			lhip1DebugAngel = lhip1InitAngel;
		}
			lhip1Duration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			lhip2 = true;
			lhip2DebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("lhip2"));
		} catch (NullPointerException e) {
			lhip2DebugAngel = lhip2InitAngle;
		}
			lhip2Duration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			lthigh = true;
			lthighDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("lthigh"));
		} catch (NullPointerException e) {
			lthighDebugAngel = lthighInitAngel;
		}
			lthighDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			lshank = true;
			lshankDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("lshank"));
		} catch (NullPointerException e) {
			lshankDebugAngel = lshankInitAngel;
		}
			lshankDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			lankle = true;
			lankleDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("lankle"));
		} catch (NullPointerException e) {
			lankleDebugAngel = lankleInitAngel;
		}
			lankleDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
		try {
			lfoot = true;
			lfootDebugAngel = mPoses.get(mCurrentPoseName).findAngleByName(
				jointNameTransMap.get("lfoot"));
		} catch (NullPointerException e) {
			lfootDebugAngel = lfootInitAngel;
		}
			lfootDuration = (int) (mTaskMap.get(taskName).getTime() * 1000);
	}

	private void createRshoulderTask() throws RestrictedAccessException {
		rshoulderAxis = new Transform3D();
		rshoulderAxis.rotZ(Math.PI / 2.0f);

		rshoulderAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				rshoulderDuration, 0, 0, 0, 0, 0);
		rshoulderRotationInterpolator = new MyInterpolator(rshoulderAlpha,
				rupperarmRoller.getmTransformGroup(), rshoulderAxis,
				(float) ((rshoulderInitAngel * Math.PI / 180) + Math.PI / 2),
				(float) ((rshoulderDebugAngel * Math.PI / 180) + Math.PI / 2),
				rshoulderRotationCounter);
		rshoulderRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		rshoulderRoller.getmTransformGroup().addChild(
				rshoulderRotationInterpolator);
		rshoulderRotationInterpolator.setEnable(false);

		rshoulderInitAngel = rshoulderDebugAngel;
		rshoulderArrayList.add(rshoulderRotationInterpolator);
		rshoulderRotationCounter++;
	}

	private void createRupperarmTask() throws RestrictedAccessException {
		rupperarmAxis = new Transform3D();
		rupperarmAxis.rotX(Math.PI / 2.0f);

		rupperarmAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				rupperarmDuration, 0, 0, 0, 0, 0);
		rupperarmRotationInterpolator = new MyInterpolator(rupperarmAlpha,
				rupperarmForAnimationGroup, rupperarmAxis,
				(float) (rupperarmInitAngle * Math.PI / 180),
				(float) (rupperarmDebugAngel * Math.PI / 180),
				rupperarmRotationCounter);
		rupperarmRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		rupperarmRoller.getmTransformGroup().addChild(
				rupperarmRotationInterpolator);
		rupperarmRotationInterpolator.setEnable(false);

		rupperarmInitAngle = rupperarmDebugAngel;
		rupperarmArrayList.add(rupperarmRotationInterpolator);
		rupperarmRotationCounter++;
	}
	
	private void createRelbowTask() throws RestrictedAccessException {
		relbowAxis = new Transform3D();
		relbowAxis.rotZ(Math.PI);
		
		relbowAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				relbowDuration, 0, 0, 0, 0, 0);
		relbowRotationInterpolator = new MyInterpolator(relbowAlpha,
				rlowerarmRoller.getmTransformGroup(), relbowAxis,
				(float) ((relbowInitAngel * Math.PI / 180) - Math.PI / 2),
				(float) ((relbowDebugAngel * Math.PI / 180) - Math.PI / 2),
				relbowRotationCounter);
		relbowRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		relbowRoller.getmTransformGroup().addChild(relbowRotationInterpolator);
		relbowRotationInterpolator.setEnable(false);
		
		relbowInitAngel = relbowDebugAngel;
		relbowArrayList.add(relbowRotationInterpolator);
		relbowRotationCounter++;
	}
	
	private void createRlowerarmTask() throws RestrictedAccessException {
		rlowerarmAxis = new Transform3D();
		rlowerarmAxis.rotZ(Math.PI / 2);

		rlowerarmAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				rlowerarmDuration, 0, 0, 0, 0, 0);
		rlowerarmRotationInterpolator = new MyInterpolator(rlowerarmAlpha,
				rlowerarmRoller.getmTransformGroup(), rlowerarmAxis,
				(float) (rlowerarmInitAngel * Math.PI / 180),
				(float) (rlowerarmDebugAngel * Math.PI / 180),
				rlowerarmRotationCounter);
		rlowerarmRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		rlowerarmRoller.getmTransformGroup().addChild(
				rlowerarmRotationInterpolator);
		rlowerarmRotationInterpolator.setEnable(false);

		rlowerarmInitAngel = rlowerarmDebugAngel;
		rlowerarmArrayList.add(rlowerarmRotationInterpolator);
		rlowerarmRotationCounter++;
	}

	private void createLshoulderTask() throws RestrictedAccessException {
		lshoulderAxis = new Transform3D();
		lshoulderAxis.rotZ(Math.PI / 2.0f);

		lshoulderAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				lshoulderDuration, 0, 0, 0, 0, 0);
		lshoulderRotationInterpolator = new MyInterpolator(lshoulderAlpha,
				lupperarmRoller.getmTransformGroup(), lshoulderAxis,
				(float) ((lshoulderInitAngel * Math.PI / 180) + Math.PI / 2),
				(float) ((lshoulderDebugAngel * Math.PI / 180) + Math.PI / 2),
				lshoulderRotationCounter);
		lshoulderRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		lshoulderRoller.getmTransformGroup().addChild(
				lshoulderRotationInterpolator);
		lshoulderRotationInterpolator.setEnable(false);
		
		lshoulderInitAngel = lshoulderDebugAngel;
		lshoulderArrayList.add(lshoulderRotationInterpolator);
		lshoulderRotationCounter++;
	}

	private void createLupperarmTask() throws RestrictedAccessException {
		lupperarmAxis = new Transform3D();
		lupperarmAxis.rotX(Math.PI / 2.0f);

		lupperarmAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				lupperarmDuration, 0, 0, 0, 0, 0);
		lupperarmRotationInterpolator = new MyInterpolator(lupperarmAlpha,
				lupperarmForAnimationGroup, lupperarmAxis,
				(float) (lupperarmInitAngle * Math.PI / 180),
				(float) (lupperarmDebugAngel * Math.PI / 180),
				lupperarmRotationCounter);
		lupperarmRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		lupperarmRoller.getmTransformGroup().addChild(
				lupperarmRotationInterpolator);
		lupperarmRotationInterpolator.setEnable(false);

		lupperarmInitAngle = lupperarmDebugAngel;
		lupperarmArrayList.add(lupperarmRotationInterpolator);
		lupperarmRotationCounter++;
	}

	private void createLelbowTask() throws RestrictedAccessException {
		lelbowAxis = new Transform3D();
		lelbowAxis.rotZ(Math.PI);

		lelbowAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				lelbowDuration, 0, 0, 0, 0, 0);
		lelbowRotationInterpolator = new MyInterpolator(lelbowAlpha,
				llowerarmRoller.getmTransformGroup(), lelbowAxis,
				(float) ((lelbowInitAngel * Math.PI / 180) + Math.PI / 2),
				(float) ((lelbowDebugAngel * Math.PI / 180) + Math.PI / 2),
				lelbowRotationCounter);
		lelbowRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		lelbowRoller.getmTransformGroup().addChild(lelbowRotationInterpolator);
		lelbowRotationInterpolator.setEnable(false);

		lelbowInitAngel = lelbowDebugAngel;
		lelbowArrayList.add(lelbowRotationInterpolator);
		lelbowRotationCounter++;
	}

	private void createLowerarmTask() throws RestrictedAccessException {
		llowerarmAxis = new Transform3D();
		llowerarmAxis.rotZ(-Math.PI / 2);

		llowerarmAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				llowerarmDuration, 0, 0, 0, 0, 0);
		llowerarmRotationInterpolator = new MyInterpolator(llowerarmAlpha,
				llowerarmRoller.getmTransformGroup(), llowerarmAxis,
				(float) (llowerarmInitAngel * Math.PI / 180),
				(float) (llowerarmDebugAngel * Math.PI / 180),
				llowerarmRotationCounter);
		llowerarmRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		llowerarmRoller.getmTransformGroup().addChild(
				llowerarmRotationInterpolator);
		llowerarmRotationInterpolator.setEnable(false);

		llowerarmInitAngel = llowerarmDebugAngel;
		llowerarmArrayList.add(llowerarmRotationInterpolator);
		llowerarmRotationCounter++;
	}

	private void createRhip1Task() throws RestrictedAccessException {
		rhip1Axis = new Transform3D();
		rhip1Axis.rotZ(-Math.PI / 4);

		rhip1Alpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rhip1Duration,
				0, 0, 0, 0, 0);
		rhip1RotationInterpolator = new MyInterpolator(rhip1Alpha,
				rhip1ForAnimationGroup, rhip1Axis, (float) (rhip1InitAngel
						* Math.PI / 180),
				(float) (rhip1DebugAngel * Math.PI / 180), rhip1RotationCounter);
		rhip1RotationInterpolator.setSchedulingBounds(mBoundingSphere);
		rhip1Roller.getmTransformGroup().addChild(rhip1RotationInterpolator);
		rhip1RotationInterpolator.setEnable(false);

		rhip1InitAngel = rhip1DebugAngel;
		rhip1ArrayList.add(rhip1RotationInterpolator);
		rhip1RotationCounter++;
	}

	private void createRhip2Task() throws RestrictedAccessException {
		rhip2Axis = new Transform3D();
		rhip2Axis.rotX(Math.PI / 2);

		rhip2Alpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rhip2Duration,
				0, 0, 0, 0, 0);
		rhip2RotationInterpolator = new MyInterpolator(rhip2Alpha,
				rhip2Roller.getmTransformGroup(), rhip2Axis,
				(float) (rhip2InitAngle * Math.PI / 180),
				(float) (rhip2DebugAngel * Math.PI / 180), rhip2RotationCounter);
		rhip2RotationInterpolator.setSchedulingBounds(mBoundingSphere);
		rhip2Roller.getmTransformGroup().addChild(rhip2RotationInterpolator);
		rhip2RotationInterpolator.setEnable(false);

		rhip2InitAngle = rhip2DebugAngel;
		rhip2ArrayList.add(rhip2RotationInterpolator);
		rhip2RotationCounter++;
	}

	private void createRthighTask() throws RestrictedAccessException {
		rthighAxis = new Transform3D();
		rthighAxis.rotZ(Math.PI / 2);

		rthighAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				rthighDuration, 0, 0, 0, 0, 0);
		rthighRotationInterpolator = new MyInterpolator(rthighAlpha,
				rthighRoller.getmTransformGroup(), rthighAxis,
				(float) (rthighInitAngel * Math.PI / 180),
				(float) (rthighDebugAngel * Math.PI / 180),
				rthighRotationCounter);
		rthighRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		rthighRoller.getmTransformGroup().addChild(rthighRotationInterpolator);
		rthighRotationInterpolator.setEnable(false);

		rthighInitAngel = rthighDebugAngel;
		rthighArrayList.add(rthighRotationInterpolator);
		rthighRotationCounter++;
	}

	private void createRshankTask() throws RestrictedAccessException {
		rshankAxis = new Transform3D();
		rshankAxis.rotZ(Math.PI / 2);

		rshankAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				rshankDuration, 0, 0, 0, 0, 0);
		rshankRotationInterpolator = new MyInterpolator(rshankAlpha,
				rshankForAnimationGroup, rshankAxis, (float) (rshankInitAngel
						* Math.PI / 180),
				(float) (rshankDebugAngel * Math.PI / 180),
				rshankRotationCounter);
		rshankRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		rshankRoller.getmTransformGroup().addChild(rshankRotationInterpolator);
		rshankRotationInterpolator.setEnable(false);

		rshankInitAngel = rshankDebugAngel;
		rshankArrayList.add(rshankRotationInterpolator);
		rshankRotationCounter++;
	}

	private void createRankleTask() throws RestrictedAccessException {
		rankleAxis = new Transform3D();
		rankleAxis.rotZ(Math.PI / 2);

		rankleAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				rankleDuration, 0, 0, 0, 0, 0);
		rankleRotationInterpolator = new MyInterpolator(rankleAlpha,
				rfootRoller.getmTransformGroup(), rankleAxis,
				(float) (rankleInitAngel * Math.PI / 180),
				(float) (rankleDebugAngel * Math.PI / 180),
				rankleRotationCounter);
		rankleRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		rankleRoller.getmTransformGroup().addChild(rankleRotationInterpolator);
		rankleRotationInterpolator.setEnable(false);

		rankleInitAngel = rankleDebugAngel;
		rankleArrayList.add(rankleRotationInterpolator);
		rankleRotationCounter++;

		//		System.out.println("createRankelTask!!");
	}

	private void createRfootTask() throws RestrictedAccessException {
		rfootAxis = new Transform3D();
		rfootAxis.rotX(Math.PI / 2);

		rfootAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rfootDuration,
				0, 0, 0, 0, 0);
		rfootRotationInterpolator = new MyInterpolator(rfootAlpha,
				rfootRoller.getmTransformGroup(), rfootAxis,
				(float) (rfootInitAngel * Math.PI / 180),
				(float) (rfootDebugAngel * Math.PI / 180), rfootRotationCounter);
		rfootRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		rfootRoller.getmTransformGroup().addChild(rfootRotationInterpolator);
		rfootRotationInterpolator.setEnable(false);

		rfootInitAngel = rfootDebugAngel;
		rfootArrayList.add(rfootRotationInterpolator);
		rfootRotationCounter++;
	}

	private void createLhip1Task() throws RestrictedAccessException {
		lhip1Axis = new Transform3D();
		lhip1Axis.rotZ(-(Math.PI * 3) / 4);

		lhip1Alpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lhip1Duration,
				0, 0, 0, 0, 0);
		lhip1RotationInterpolator = new MyInterpolator(lhip1Alpha,
				lhip1ForAnimationGroup, lhip1Axis, (float) (lhip1InitAngel
						* Math.PI / 180),
				(float) (lhip1DebugAngel * Math.PI / 180), lhip1RotationCounter);
		lhip1RotationInterpolator.setSchedulingBounds(mBoundingSphere);
		lhip1Roller.getmTransformGroup().addChild(lhip1RotationInterpolator);
		lhip1RotationInterpolator.setEnable(false);

		lhip1InitAngel = lhip1DebugAngel;
		lhip1ArrayList.add(lhip1RotationInterpolator);
		lhip1RotationCounter++;
	}

	private void createLhip2Task() throws RestrictedAccessException {
		lhip2Axis = new Transform3D();
		lhip2Axis.rotX(Math.PI / 2);

		lhip2Alpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lhip2Duration,
				0, 0, 0, 0, 0);
		lhip2RotationInterpolator = new MyInterpolator(lhip2Alpha,
				lhip2Roller.getmTransformGroup(), lhip2Axis,
				(float) (lhip2InitAngle * Math.PI / 180),
				(float) (lhip2DebugAngel * Math.PI / 180), lhip2RotationCounter);
		lhip2RotationInterpolator.setSchedulingBounds(mBoundingSphere);
		lhip2Roller.getmTransformGroup().addChild(lhip2RotationInterpolator);
		lhip2RotationInterpolator.setEnable(false);

		lhip2InitAngle = lhip2DebugAngel;
		lhip2ArrayList.add(lhip2RotationInterpolator);
		lhip2RotationCounter++;
	}

	private void createLthighTask() throws RestrictedAccessException {
		lthighAxis = new Transform3D();
		lthighAxis.rotZ(Math.PI / 2);

		lthighAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				lthighDuration, 0, 0, 0, 0, 0);
		lthighRotationInterpolator = new MyInterpolator(lthighAlpha,
				lthighRoller.getmTransformGroup(), lthighAxis,
				(float) (lthighInitAngel * Math.PI / 180),
				(float) (lthighDebugAngel * Math.PI / 180),
				lthighRotationCounter);
		lthighRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		lthighRoller.getmTransformGroup().addChild(lthighRotationInterpolator);
		lthighRotationInterpolator.setEnable(false);

		lthighInitAngel = lthighDebugAngel;
		lthighArrayList.add(lthighRotationInterpolator);
		lthighRotationCounter++;
	}

	private void createLshankTask() throws RestrictedAccessException {
		lshankAxis = new Transform3D();
		lshankAxis.rotZ(Math.PI / 2);

		lshankAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				lshankDuration, 0, 0, 0, 0, 0);
		lshankRotationInterpolator = new MyInterpolator(lshankAlpha,
				lshankForAnimationGroup, lshankAxis, (float) (lshankInitAngel
						* Math.PI / 180),
				(float) (lshankDebugAngel * Math.PI / 180),
				lshankRotationCounter);
		lshankRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		lshankRoller.getmTransformGroup().addChild(lshankRotationInterpolator);
		lshankRotationInterpolator.setEnable(false);

		lshankInitAngel = lshankDebugAngel;
		lshankArrayList.add(lshankRotationInterpolator);
		lshankRotationCounter++;
	}

	private void createLankleTask() throws RestrictedAccessException {
		lankleAxis = new Transform3D();
		lankleAxis.rotZ(Math.PI / 2);

		lankleAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,
				lankleDuration, 0, 0, 0, 0, 0);
		lankleRotationInterpolator = new MyInterpolator(lankleAlpha,
				lfootRoller.getmTransformGroup(), lankleAxis,
				(float) (lankleInitAngel * Math.PI / 180),
				(float) (lankleDebugAngel * Math.PI / 180),
				lankleRotationCounter);
		lankleRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		lankleRoller.getmTransformGroup().addChild(lankleRotationInterpolator);
		lankleRotationInterpolator.setEnable(false);

		lankleInitAngel = lankleDebugAngel;
		lankleArrayList.add(lankleRotationInterpolator);
		lankleRotationCounter++;
	}

	private void createLfootTask() throws RestrictedAccessException {
		lfootAxis = new Transform3D();
		lfootAxis.rotX(Math.PI / 2);

		lfootAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lfootDuration,
				0, 0, 0, 0, 0);
		lfootRotationInterpolator = new MyInterpolator(lfootAlpha,
				lfootRoller.getmTransformGroup(), lfootAxis,
				(float) (lfootInitAngel * Math.PI / 180),
				(float) (lfootDebugAngel * Math.PI / 180), lfootRotationCounter);
		lfootRotationInterpolator.setSchedulingBounds(mBoundingSphere);
		lfootRoller.getmTransformGroup().addChild(lfootRotationInterpolator);
		lfootRotationInterpolator.setEnable(false);

		lfootInitAngel = lfootDebugAngel;
		lfootArrayList.add(lfootRotationInterpolator);
		lfootRotationCounter++;
	}
	
	private void initAxis() {
		rshoulderAxis = new Transform3D();
		rshoulderAxis.rotZ(Math.PI / 2.0f);
		rupperarmAxis = new Transform3D();
		rupperarmAxis.rotX(Math.PI / 2.0f);
		relbowAxis = new Transform3D();
		relbowAxis.rotZ(Math.PI);
		rlowerarmAxis = new Transform3D();
		rlowerarmAxis.rotZ(Math.PI / 2);
		lshoulderAxis = new Transform3D();
		lshoulderAxis.rotZ(Math.PI / 2.0f);
		lupperarmAxis = new Transform3D();
		lupperarmAxis.rotX(Math.PI / 2.0f);
		lelbowAxis = new Transform3D();
		lelbowAxis.rotZ(Math.PI);
		llowerarmAxis = new Transform3D();
		llowerarmAxis.rotZ(-Math.PI / 2);
		rhip1Axis = new Transform3D();
		rhip1Axis.rotZ(-Math.PI / 4);
		rhip2Axis = new Transform3D();
		rhip2Axis.rotX(Math.PI / 2);
		rthighAxis = new Transform3D();
		rthighAxis.rotZ(Math.PI / 2);
		rshankAxis = new Transform3D();
		rshankAxis.rotZ(Math.PI / 2);
		rankleAxis = new Transform3D();
		rankleAxis.rotZ(Math.PI / 2);
		rfootAxis = new Transform3D();
		rfootAxis.rotX(Math.PI / 2);
		lhip1Axis = new Transform3D();
		lhip1Axis.rotZ(-(Math.PI * 3) / 4);
		lhip2Axis = new Transform3D();
		lhip2Axis.rotX(Math.PI / 2);
		lthighAxis = new Transform3D();
		lthighAxis.rotZ(Math.PI / 2);
		lshankAxis = new Transform3D();
		lshankAxis.rotZ(Math.PI / 2);
		lankleAxis = new Transform3D();
		lankleAxis.rotZ(Math.PI / 2);
		lfootAxis = new Transform3D();
		lfootAxis.rotX(Math.PI / 2);
	}

	private void loadTask() throws RestrictedAccessException {
		//		System.out.println("" + rshoulder);
		if(rshoulder){
//			rshoulderAxis = new Transform3D();
//			rshoulderAxis.rotZ(Math.PI/2.0f);
			
			rshoulderAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rshoulderDuration, 0, 0, 0, 0, 0);
			//			System.out.println("" + rshoulderInitAngel + "  "
			//					+ rshoulderDebugAngel);
			rshoulderRotationInterpolator = new MyInterpolator(rshoulderAlpha, rupperarmRoller.getmTransformGroup(), rshoulderAxis, (float)((rshoulderInitAngel*Math.PI/180)+Math.PI/2), (float)((rshoulderDebugAngel*Math.PI/180)+Math.PI/2), rshoulderRotationCounter); 
			rshoulderRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			rshoulderRoller.getmTransformGroup().addChild(rshoulderRotationInterpolator);
			rshoulderRotationInterpolator.setEnable(false);
			
			
			rshoulderInitAngel = rshoulderDebugAngel;
			rshoulderArrayList.add(rshoulderRotationInterpolator);
			rshoulderRotationCounter++;
			
		}
		
		if(rupperarm){
//			rupperarmAxis = new Transform3D();
//			rupperarmAxis.rotX(Math.PI/2.0f);
			
			rupperarmAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rupperarmDuration, 0, 0, 0, 0, 0);
			rupperarmRotationInterpolator = new MyInterpolator(rupperarmAlpha, rupperarmForAnimationGroup, rupperarmAxis, (float)(rupperarmInitAngle*Math.PI/180), (float)(rupperarmDebugAngel*Math.PI/180), rupperarmRotationCounter);
			rupperarmRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			rupperarmRoller.getmTransformGroup().addChild(rupperarmRotationInterpolator);
			rupperarmRotationInterpolator.setEnable(false);
			
			rupperarmInitAngle = rupperarmDebugAngel;
			rupperarmArrayList.add(rupperarmRotationInterpolator);
			rupperarmRotationCounter++;
			
		}
		
		if(relbow){
//			relbowAxis = new Transform3D();
//			relbowAxis.rotZ(Math.PI);
			
			relbowAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, relbowDuration, 0, 0, 0, 0, 0);
			relbowRotationInterpolator = new MyInterpolator(relbowAlpha, rlowerarmRoller.getmTransformGroup(), relbowAxis, (float)((relbowInitAngel*Math.PI/180)-Math.PI/2), (float)((relbowDebugAngel*Math.PI/180)-Math.PI/2), relbowRotationCounter);
			relbowRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			relbowRoller.getmTransformGroup().addChild(relbowRotationInterpolator);
			relbowRotationInterpolator.setEnable(false);
			
			relbowInitAngel = relbowDebugAngel;
			relbowArrayList.add(relbowRotationInterpolator);
			relbowRotationCounter++;
		}
		
		if(rlowerarm){
//			rlowerarmAxis = new Transform3D();
//			rlowerarmAxis.rotZ(Math.PI/2);
			
			rlowerarmAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rlowerarmDuration, 0, 0, 0, 0, 0);
			rlowerarmRotationInterpolator = new MyInterpolator(rlowerarmAlpha, rlowerarmRoller.getmTransformGroup(), rlowerarmAxis, (float)(rlowerarmInitAngel*Math.PI/180), (float)(rlowerarmDebugAngel*Math.PI/180), rlowerarmRotationCounter);
			rlowerarmRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			rlowerarmRoller.getmTransformGroup().addChild(rlowerarmRotationInterpolator);
			rlowerarmRotationInterpolator.setEnable(false);
			
			rlowerarmInitAngel = rlowerarmDebugAngel;
			rlowerarmArrayList.add(rlowerarmRotationInterpolator);
			rlowerarmRotationCounter++;
		}
		
		if(lshoulder){
//			lshoulderAxis = new Transform3D();
//			lshoulderAxis.rotZ(Math.PI/2.0f);
			
			lshoulderAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0,lshoulderDuration, 0, 0, 0, 0, 0);
			lshoulderRotationInterpolator = new MyInterpolator(lshoulderAlpha, lupperarmRoller.getmTransformGroup(), lshoulderAxis, (float)((lshoulderInitAngel*Math.PI/180)+Math.PI/2), (float)((lshoulderDebugAngel*Math.PI/180)+Math.PI/2), lshoulderRotationCounter); 
			lshoulderRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			lshoulderRoller.getmTransformGroup().addChild(lshoulderRotationInterpolator);
			lshoulderRotationInterpolator.setEnable(false);
			
			lshoulderInitAngel = lshoulderDebugAngel;
			lshoulderArrayList.add(lshoulderRotationInterpolator);
			lshoulderRotationCounter++;
			
		}
		
		if(lupperarm){
//			lupperarmAxis = new Transform3D();
//			lupperarmAxis.rotX(Math.PI/2.0f);
			
			lupperarmAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lupperarmDuration, 0, 0, 0, 0, 0);
			lupperarmRotationInterpolator = new MyInterpolator(lupperarmAlpha, lupperarmForAnimationGroup, lupperarmAxis, (float)(lupperarmInitAngle*Math.PI/180), (float)(lupperarmDebugAngel*Math.PI/180), lupperarmRotationCounter);
			lupperarmRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			lupperarmRoller.getmTransformGroup().addChild(lupperarmRotationInterpolator);
			lupperarmRotationInterpolator.setEnable(false);
			
			lupperarmInitAngle = lupperarmDebugAngel;
			lupperarmArrayList.add(lupperarmRotationInterpolator);
			lupperarmRotationCounter++;
		}
		
		if(lelbow){
//			lelbowAxis = new Transform3D();
//			lelbowAxis.rotZ(Math.PI);
			
			lelbowAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lelbowDuration, 0, 0, 0, 0, 0);
			lelbowRotationInterpolator = new MyInterpolator(lelbowAlpha, llowerarmRoller.getmTransformGroup(), lelbowAxis, (float)((lelbowInitAngel*Math.PI/180)+Math.PI/2), (float)((lelbowDebugAngel*Math.PI/180)+Math.PI/2), lelbowRotationCounter);
			lelbowRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			lelbowRoller.getmTransformGroup().addChild(lelbowRotationInterpolator);
			lelbowRotationInterpolator.setEnable(false);
			
			lelbowInitAngel = lelbowDebugAngel;
			lelbowArrayList.add(lelbowRotationInterpolator);
			lelbowRotationCounter++;
		}
		
		if(llowerarm){
//			llowerarmAxis = new Transform3D();
//			llowerarmAxis.rotZ(-Math.PI/2);
			
			llowerarmAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, llowerarmDuration, 0, 0, 0, 0, 0);
			llowerarmRotationInterpolator = new MyInterpolator(llowerarmAlpha, llowerarmRoller.getmTransformGroup(), llowerarmAxis, (float)(llowerarmInitAngel*Math.PI/180), (float)(llowerarmDebugAngel*Math.PI/180), llowerarmRotationCounter);
			llowerarmRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			llowerarmRoller.getmTransformGroup().addChild(llowerarmRotationInterpolator);
			llowerarmRotationInterpolator.setEnable(false);
			
			llowerarmInitAngel = llowerarmDebugAngel;
			llowerarmArrayList.add(llowerarmRotationInterpolator);
			llowerarmRotationCounter++;
		}
		
		if(rhip1){
//			rhip1Axis = new Transform3D();
//			rhip1Axis.rotZ(-Math.PI/4);
	
			rhip1Alpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rhip1Duration, 0, 0, 0, 0, 0);
			rhip1RotationInterpolator = new MyInterpolator(rhip1Alpha, rhip1ForAnimationGroup, rhip1Axis, (float)(rhip1InitAngel*Math.PI/180), (float)(rhip1DebugAngel*Math.PI/180), rhip1RotationCounter);
			rhip1RotationInterpolator.setSchedulingBounds(mBoundingSphere);
			rhip1Roller.getmTransformGroup().addChild(rhip1RotationInterpolator);
			rhip1RotationInterpolator.setEnable(false);
			
			rhip1InitAngel = rhip1DebugAngel;
			rhip1ArrayList.add(rhip1RotationInterpolator);
			rhip1RotationCounter++;
		}
		
		if(rhip2){
//			rhip2Axis = new Transform3D();
//			rhip2Axis.rotX(Math.PI/2);
		
			rhip2Alpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rhip2Duration, 0, 0, 0, 0, 0);
			rhip2RotationInterpolator = new MyInterpolator(rhip2Alpha, rhip2Roller.getmTransformGroup(), rhip2Axis, (float)(rhip2InitAngle*Math.PI/180), (float)(rhip2DebugAngel*Math.PI/180), rhip2RotationCounter);
			rhip2RotationInterpolator.setSchedulingBounds(mBoundingSphere);
			rhip2Roller.getmTransformGroup().addChild(rhip2RotationInterpolator);
			rhip2RotationInterpolator.setEnable(false);
			
			
			rhip2InitAngle = rhip2DebugAngel;
			rhip2ArrayList.add(rhip2RotationInterpolator);
			rhip2RotationCounter++;
		}
//		rthigh = true;
		
		if(rthigh){
//			rthighDebugAngel = 90;
//			rthighDuration = 1500;
			
//			rthighAxis = new Transform3D();
//			rthighAxis.rotZ(Math.PI/2);
	
			rthighAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rthighDuration, 0, 0, 0, 0, 0);
			rthighRotationInterpolator = new MyInterpolator(rthighAlpha, rthighRoller.getmTransformGroup(), rthighAxis, (float)(rthighInitAngel*Math.PI/180), (float)(rthighDebugAngel*Math.PI/180), rthighRotationCounter);
			rthighRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			rthighRoller.getmTransformGroup().addChild(rthighRotationInterpolator);
			rthighRotationInterpolator.setEnable(false);
			
			
			rthighInitAngel = rthighDebugAngel;
			rthighArrayList.add(rthighRotationInterpolator);
			rthighRotationCounter++;
		}
		
		if(rshank){
//			rshankAxis = new Transform3D();
//			rshankAxis.rotZ(Math.PI/2);

			rshankAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rshankDuration, 0, 0, 0, 0, 0);
			rshankRotationInterpolator = new MyInterpolator(rshankAlpha, rshankForAnimationGroup, rshankAxis, (float)(rshankInitAngel*Math.PI/180), (float)(rshankDebugAngel*Math.PI/180), rshankRotationCounter);
			rshankRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			rshankRoller.getmTransformGroup().addChild(rshankRotationInterpolator);
			rshankRotationInterpolator.setEnable(false);
			
			rshankInitAngel = rshankDebugAngel;
			rshankArrayList.add(rshankRotationInterpolator);
			rshankRotationCounter++;
		}
		
		if(rankle){
//			rankleAxis = new Transform3D();
//			rankleAxis.rotZ(Math.PI/2);
			
			rankleAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rankleDuration, 0, 0, 0, 0, 0);
			rankleRotationInterpolator = new MyInterpolator(rankleAlpha,
					rankleRoller.getmTransformGroup(), rankleAxis,
					(float) (rankleInitAngel * Math.PI / 180),
					(float) (rankleDebugAngel * Math.PI / 180),
					rankleRotationCounter);
			rankleRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			rankleRoller.getmTransformGroup().addChild(rankleRotationInterpolator);
			rankleRotationInterpolator.setEnable(false);
			
			rankleInitAngel = rankleDebugAngel;
			rankleArrayList.add(rankleRotationInterpolator);
			rankleRotationCounter++;
		}
		
		if(rfoot){
//			rfootAxis = new Transform3D();
//			rfootAxis.rotX(Math.PI/2);

			rfootAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, rfootDuration, 0, 0, 0, 0, 0);
			rfootRotationInterpolator = new MyInterpolator(rfootAlpha, rfootRoller.getmTransformGroup(), rfootAxis, (float)(rfootInitAngel*Math.PI/180), (float)(rfootDebugAngel*Math.PI/180), rfootRotationCounter);
			rfootRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			rfootRoller.getmTransformGroup().addChild(rfootRotationInterpolator);
			rfootRotationInterpolator.setEnable(false);
			
			
			rfootInitAngel = rfootDebugAngel;
			rfootArrayList.add(rfootRotationInterpolator);
			rfootRotationCounter++;
		}
		
		if(lhip1){
//			lhip1Axis = new Transform3D();
//			lhip1Axis.rotZ(-(Math.PI*3)/4);
		
			lhip1Alpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lhip1Duration, 0, 0, 0, 0, 0);
			lhip1RotationInterpolator = new MyInterpolator(lhip1Alpha, lhip1ForAnimationGroup, lhip1Axis, (float)(lhip1InitAngel*Math.PI/180), (float)(lhip1DebugAngel*Math.PI/180), lhip1RotationCounter);
			lhip1RotationInterpolator.setSchedulingBounds(mBoundingSphere);
			lhip1Roller.getmTransformGroup().addChild(lhip1RotationInterpolator);
			lhip1RotationInterpolator.setEnable(false);
			
			lhip1InitAngel = lhip1DebugAngel;
			lhip1ArrayList.add(lhip1RotationInterpolator);
			lhip1RotationCounter++;
		}
		
		if(lhip2){
//			lhip2Axis = new Transform3D();
//			lhip2Axis.rotX(Math.PI/2);
		
			lhip2Alpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lhip2Duration, 0, 0, 0, 0, 0);
			lhip2RotationInterpolator = new MyInterpolator(lhip2Alpha, lhip2Roller.getmTransformGroup(), lhip2Axis, (float)(lhip2InitAngle*Math.PI/180), (float)(lhip2DebugAngel*Math.PI/180), lhip2RotationCounter);
			lhip2RotationInterpolator.setSchedulingBounds(mBoundingSphere);
			lhip2Roller.getmTransformGroup().addChild(lhip2RotationInterpolator);
			lhip2RotationInterpolator.setEnable(false);
			
			
			lhip2InitAngle = angelRot;
			lhip2ArrayList.add(lhip2RotationInterpolator);
			lhip2RotationCounter++;
		}
		
		if(lthigh){
//			lthighAxis = new Transform3D();
//			lthighAxis.rotZ(Math.PI/2);
		
			lthighAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lthighDuration, 0, 0, 0, 0, 0);
			lthighRotationInterpolator = new MyInterpolator(lthighAlpha, lthighRoller.getmTransformGroup(), lthighAxis, (float)(lthighInitAngel*Math.PI/180), (float)(lthighDebugAngel*Math.PI/180), lthighRotationCounter);
			lthighRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			lthighRoller.getmTransformGroup().addChild(lthighRotationInterpolator);
			lthighRotationInterpolator.setEnable(false);
			
			
			lthighInitAngel = lthighDebugAngel;
			lthighArrayList.add(lthighRotationInterpolator);
			lthighRotationCounter++;
		}
		
		if(lshank){
//			lshankAxis = new Transform3D();
//			lshankAxis.rotZ(Math.PI/2);
		
			lshankAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lshankDuration, 0, 0, 0, 0, 0);
			lshankRotationInterpolator = new MyInterpolator(lshankAlpha, lshankForAnimationGroup, lshankAxis, (float)(lshankInitAngel*Math.PI/180), (float)(lshankDebugAngel*Math.PI/180), lshankRotationCounter);
			lshankRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			lshankRoller.getmTransformGroup().addChild(lshankRotationInterpolator);
			lshankRotationInterpolator.setEnable(false);
			
			lshankInitAngel = lshankDebugAngel;
			lshankArrayList.add(lshankRotationInterpolator);
			lshankRotationCounter++;
		}
		
		if(lankle){
//			lankleAxis = new Transform3D();
//			lankleAxis.rotZ(Math.PI/2);
			
			lankleAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lankleDuration, 0, 0, 0, 0, 0);
			lankleRotationInterpolator = new MyInterpolator(lankleAlpha,
					lankleRoller.getmTransformGroup(), lankleAxis,
					(float) (lankleInitAngel * Math.PI / 180),
					(float) (lankleDebugAngel * Math.PI / 180),
					lankleRotationCounter);
			lankleRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			lankleRoller.getmTransformGroup().addChild(lankleRotationInterpolator);
			lankleRotationInterpolator.setEnable(false);
			
			lankleInitAngel = lankleDebugAngel;
			lankleArrayList.add(lankleRotationInterpolator);
			lankleRotationCounter++;
		}
		
		if(lfoot){
//			lfootAxis = new Transform3D();
//			lfootAxis.rotX(Math.PI/2);
			
			lfootAlpha = new Alpha(1, Alpha.INCREASING_ENABLE, 0, 0, lfootDuration, 0, 0, 0, 0, 0);
			lfootRotationInterpolator = new MyInterpolator(lfootAlpha, lfootRoller.getmTransformGroup(), lfootAxis, (float)(lfootInitAngel*Math.PI/180), (float)(lfootDebugAngel*Math.PI/180), lfootRotationCounter);
			lfootRotationInterpolator.setSchedulingBounds(mBoundingSphere);
			lfootRoller.getmTransformGroup().addChild(lfootRotationInterpolator);
			lfootRotationInterpolator.setEnable(false);
			
			
			lfootInitAngel = lfootDebugAngel;
			lfootArrayList.add(lfootRotationInterpolator);
			lfootRotationCounter++;
		}
	}

	@Override
	public void transformChanged(int arg0, Transform3D arg1) {

	}

}
