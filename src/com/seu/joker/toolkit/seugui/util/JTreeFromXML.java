package com.seu.joker.toolkit.seugui.util;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class JTreeFromXML {
	public static JTree readXML(String xmlFilePath) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document doc = saxReader.read(xmlFilePath);
		return new JTree(build(doc.getRootElement()));
	}

	private static DefaultMutableTreeNode build(Element rootElement) {
		DefaultMutableTreeNode result;
		if (rootElement.getName() == "name") {
			result = new DefaultMutableTreeNode(rootElement.getText());
		} else {
			result = new DefaultMutableTreeNode(rootElement.getName());
		}

		for (Object o : rootElement.elements()) {
			Element child = (Element) o;
			result.add(build(child));
		}
		return result;
	}
}
