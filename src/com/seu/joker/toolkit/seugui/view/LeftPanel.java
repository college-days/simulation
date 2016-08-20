package com.seu.joker.toolkit.seugui.view;

//import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.media.j3d.Appearance;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PolygonAttributes;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.DocumentException;

import com.seu.joker.toolkit.seugui.naomodel.Nao;
import com.seu.joker.toolkit.seugui.naomodel.Roller;
import com.seu.joker.toolkit.seugui.util.JTreeFromXML;

public class LeftPanel extends JPanel implements TreeSelectionListener{
	private static final long serialVersionUID = 4708120301218717165L;
	private GUIMainFrame parentFrame = null;
	private JLabel globalPosLabel = null;

	private JTextField globalxField = null;
	private JTextField globalyField = null;
	private JTextField globalzField = null;

	
	private JTree boneTree = null;
	private JTree deviceTree = null;

	public LeftPanel(GUIMainFrame parentFrame) {
		this.parentFrame = parentFrame;
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(200, 640));
		add(initUpperControls(), BorderLayout.NORTH);
		add(initTreesControls(), BorderLayout.SOUTH);
	}

	private JPanel initUpperControls() {
		JPanel panel = new JPanel(new GridBagLayout());
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(180, 30));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		globalPosLabel = new JLabel("Pos:");
		//		addComponent(panel, globalPosLabel, c, 0, 0);

		globalxField = new JTextField("-2");
		globalyField = new JTextField("0");
		globalzField = new JTextField("0");
		//		addComponent(panel, globalxField, c, 1, 0);
		//		addComponent(panel, globalyField, c, 2, 0);
		//		addComponent(panel, globalzField, c, 3, 0);
		//		globalPosLabel.setFont(new Font("Arial", Font.ITALIC, 13));
		return panel;
	}

	private void addComponent(JComponent parent, JComponent added,
			GridBagConstraints c, int x, int y) {
		c.gridx = x;
		c.gridy = y;
		added.setPreferredSize(new Dimension(30, 20));
		parent.add(added, c);
	}

	private JTabbedPane initTreesControls() {
		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("Arial", Font.ITALIC, 13));
		try {
			boneTree = JTreeFromXML.readXML("bone.xml");
			boneTree.setFont(new Font("Arial", Font.ITALIC, 12));
			JScrollPane jsp1 = new JScrollPane(boneTree);
			boneTree.addTreeSelectionListener(this);
			boneTree.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) boneTree
							.getLastSelectedPathComponent();
					if (treeNode != null) {
						selectJoint(treeNode);
						if (e.getClickCount() == 2) {
							addNewJointPanelInRightPanel(treeNode);
						}
					}
				}
			});
			deviceTree = JTreeFromXML.readXML("device.xml");
			deviceTree.setFont(new Font("Arial", Font.ITALIC, 12));
			JScrollPane jsp2 = new JScrollPane(deviceTree);
			tabbedPane.addTab("Bone", jsp1);
			//			tabbedPane.addTab("Device", jsp2);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		tabbedPane.setPreferredSize(new Dimension(180, 600));
		return tabbedPane;
	}

	private void selectJoint(DefaultMutableTreeNode treeNode) {
		Appearance ap = new Appearance();
		ColoringAttributes ca = new ColoringAttributes();
		ca.setColor(1.0f, 0.0f, 0.0f);
		PolygonAttributes pa = new PolygonAttributes();
		pa.setCullFace(PolygonAttributes.CULL_NONE);
		pa.setPolygonMode(PolygonAttributes.POLYGON_LINE);
		ap.setColoringAttributes(ca);
		ap.setPolygonAttributes(pa);
		if (treeNode.isLeaf()) {
			Nao nao = parentFrame.getMainController().getMyUniverse().getNao();
			Roller lastSelectedRoller = nao.getLastSelectedRoller();
			if (lastSelectedRoller != null) {
				//System.out.println(treeNode);
				Appearance ap2 = new Appearance();
				ColoringAttributes ca2 = new ColoringAttributes();
				ca2.setColor(1.0f, 1.0f, 1.0f);
				PolygonAttributes pa2 = new PolygonAttributes();
				pa2.setCullFace(PolygonAttributes.CULL_NONE);
				pa2.setPolygonMode(PolygonAttributes.POLYGON_LINE);
				ap2.setColoringAttributes(ca2);
				ap2.setPolygonAttributes(pa2);
				nao.getLastSelectedRoller().setAppearance(ap2);
			}
			nao.getRollerByName(treeNode.toString()).setAppearance(ap);
			nao.setLastSelectedRoller(treeNode.toString());
		}
	}

	private void addNewJointPanelInRightPanel(DefaultMutableTreeNode treeNode) {
		if (treeNode.isLeaf()) {
			//System.out.println(treeNode);
			parentFrame.getRightPanel().getFixedAngleTracePanel()
					.getBodyControllerPanel()
					.addBoneToBodyControllerPanel(treeNode.toString());
		}
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
	//	TreePath leadPath = e.getNewLeadSelectionPath();

	}
}
