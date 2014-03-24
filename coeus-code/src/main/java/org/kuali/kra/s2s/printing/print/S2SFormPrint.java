package org.kuali.kra.s2s.printing.print;

import org.kuali.coeus.common.framework.print.AbstractPrint;
import org.kuali.coeus.common.framework.print.PrintingException;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class S2SFormPrint extends AbstractPrint {
	private static final long serialVersionUID = 1L;
	private ArrayList<Source> templates;
	private Map<String, byte[]> xmlDataMap;

	public S2SFormPrint() {
		// do nothing
	}

	public S2SFormPrint(XmlStream xmlStream, Source template,KcPersistableBusinessObjectBase printableBusinessObject) {
		setXmlStream(xmlStream);
		setPrintableBusinessObject(printableBusinessObject);
		templates = new ArrayList<Source>();
		templates.add(template);
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
