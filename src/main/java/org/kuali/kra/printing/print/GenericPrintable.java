package org.kuali.kra.printing.print;

import java.util.List;
import java.util.Map;

import javax.xml.transform.Source;

import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.util.watermark.Watermarkable;

public class GenericPrintable implements Printable {

	Map<String, byte[]> streamMap;
	Map<String, byte[]> attachments;
	Map<String, Source> xSLTemplateWithBookmarks;
	List<Source> xSLTemplates;
	KraPersistableBusinessObjectBase printableBusinessObject;

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

	public void setPrintableBusinessObject(KraPersistableBusinessObjectBase printableBusinessObject) {
		this.printableBusinessObject = printableBusinessObject;
	}

	public Map<String, byte[]> getAttachments() {
		return attachments;
	}

	public KraPersistableBusinessObjectBase getPrintableBusinessObject() {
		return printableBusinessObject;
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
      * @see org.kuali.kra.printing.Printable#getWatermarkable()
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
