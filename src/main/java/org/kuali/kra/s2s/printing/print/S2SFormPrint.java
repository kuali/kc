package org.kuali.kra.s2s.printing.print;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.printing.xmlstream.XmlStream;

public class S2SFormPrint extends AbstractPrint {
	private static final long serialVersionUID = 1L;
	private ArrayList<Source> templates;
	private Map<String, byte[]> xmlDataMap;

	public S2SFormPrint() {
		// do nothing
	}

	public S2SFormPrint(XmlStream xmlStream, Source template,
			ResearchDocumentBase document) {
		setXmlStream(xmlStream);
		setDocument(document);
		templates = new ArrayList<Source>();
		templates.add(template);
	}

	/**
	 * Fetches the {@link ResearchDocumentBase}
	 * 
	 * @return {@link ResearchDocumentBase} document
	 */
	public ResearchDocumentBase getDocument() {
		return document;
	}

	public void setXSLT(ArrayList<Source> templates) {
		this.templates = templates;
	}

	public List<Source> getXSLTemplates() {
		return templates;
	}

	@Override
	public Map<String, byte[]> renderXML() throws PrintingException {
		return xmlDataMap;
	}

	public Map<String, byte[]> getXmlDataMap() {
		return xmlDataMap;
	}

	public void setXmlDataMap(Map<String, byte[]> xmlDataMap) {
		this.xmlDataMap = xmlDataMap;
	}
}
