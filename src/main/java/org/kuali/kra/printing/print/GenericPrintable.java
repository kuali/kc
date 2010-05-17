package org.kuali.kra.printing.print;

import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;

public class GenericPrintable implements Printable {

	Map<String, byte[]> streamMap;
	Map<String, byte[]> attachments;
	Map<String, Source> xSLTemplateWithBookmarks;
	List<Source> xSLTemplates;
	ResearchDocumentBase documentBase;

	public void setStreamMap(Map<String, byte[]> streamMap) {
		this.streamMap = streamMap;
	}

	public void setAttachments(Map<String, byte[]> attachments) {
		this.attachments = attachments;
	}

	public void setXSLTemplateWithBookmarks(
			Map<String, Source> templateWithBookmarks) {
		xSLTemplateWithBookmarks = templateWithBookmarks;
	}

	public void setXSLTemplates(List<Source> templates) {
		xSLTemplates = templates;
	}

	public void setDocumentBase(ResearchDocumentBase documentBase) {
		this.documentBase = documentBase;
	}

	public Map<String, byte[]> getAttachments() {
		return attachments;
	}

	public ResearchDocumentBase getDocument() {
		return documentBase;
	}

	public Map<String, Source> getXSLTemplateWithBookmarks() {
		return xSLTemplateWithBookmarks;
	}

	public List<Source> getXSLTemplates() {
		return xSLTemplates;
	}

	public Map<String, byte[]> renderXML() throws PrintingException {
		return streamMap;
	}

}
