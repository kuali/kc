package org.kuali.kra.s2s.printing;

import org.kuali.coeus.common.framework.print.Printable;
import org.kuali.coeus.common.framework.print.watermark.Watermarkable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.xml.transform.Source;
import java.util.List;
import java.util.Map;

public class GenericPrintable implements Printable {

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

    public KcPersistableBusinessObjectBase getPrintableBusinessObject() {
        throw new RuntimeException("PrintableBusinessObject not implemented");
    }

	/**
     * 
     * This method for checking watermark is enable or disable
     * for this document.
     */
     public boolean isWatermarkEnabled(){
         return false;
     }
     /**
      * This method for getting the watermark.
      * @see org.kuali.coeus.common.framework.print.Printable#getWatermarkable()
      * return watermarkable
      */
     public Watermarkable getWatermarkable(){
         if(isWatermarkEnabled()){
             throw new RuntimeException("Watermarkable not implemented");
         }else{
             return null;
         }
     }
}
