package com.seu.joker.toolkit.seugui.naomodel;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;

import com.sun.j3d.utils.geometry.Primitive;

public class CoordinateSystem {
	private CoordinateAxis xAxis = null;
	private CoordinateAxis yAxis = null;
	private CoordinateAxis zAxis = null;

	private Transform3D transform3d = null;
	private TransformGroup transformGroup = null;

	public Transform3D getTransform3d() {
		return transform3d;
	}

	public TransformGroup getTransformGroup() {
		return transformGroup;
	}

	public CoordinateSystem() {
		Appearance apAxis = new Appearance();
		ColoringAttributes caAxis = new ColoringAttributes();
		caAxis.setColor(0.0f, 0.0f, 0.0f);
		PolygonAttributes paAxis = new PolygonAttributes();
		paAxis.setCullFace(PolygonAttributes.CULL_NONE);
		paAxis.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		apAxis.setColoringAttributes(caAxis);
		apAxis.setPolygonAttributes(paAxis);
		/**
		 * the coordinates
		 */
		xAxis = new CoordinateAxis(2.4f, 0.017f, 0.017f,
				Primitive.ENABLE_APPEARANCE_MODIFY, Primitive.GENERATE_NORMALS,
				apAxis);
		yAxis = new CoordinateAxis(2.4f, 0.017f, 0.017f,
				Primitive.ENABLE_APPEARANCE_MODIFY, Primitive.GENERATE_NORMALS,
				apAxis);
		zAxis = new CoordinateAxis(2.4f, 0.017f, 0.017f,
				Primitive.ENABLE_APPEARANCE_MODIFY, Primitive.GENERATE_NORMALS,
				apAxis);
		yAxis.rotZ(Math.PI / 2.0);
		zAxis.rotY(-Math.PI / 2.0);

		transform3d = new Transform3D();
		transformGroup = new TransformGroup(transform3d);
		transformGroup
				.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		transformGroup.addChild(xAxis.getmTransformGroup());
		transformGroup.addChild(yAxis.getmTransformGroup());
		transformGroup.addChild(zAxis.getmTransformGroup());
	}

}
