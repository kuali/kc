package org.kuali.coeus.s2sgen.impl.print;


import javax.xml.transform.Source;
import java.util.List;
import java.util.Map;

public class GenericPrintable implements S2SPrintable {

	Map<String, byte[]> streamMap;
	Map<String, byte[]> attachments;
	Map<String, Source> xSLTemplateWithBookmarks;
	List<Source> xSLTemplates;

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

	public Map<String, byte[]> getAttachments() {
		return attachments;
	}

	public Map<String, Source> getXSLTemplateWithBookmarks() {
		return xSLTemplateWithBookmarks;
	}

	public List<Source> getXSLTemplates() {
		return xSLTemplates;
	}

	public Map<String, byte[]> renderXML() {
		return streamMap;
	}
}
