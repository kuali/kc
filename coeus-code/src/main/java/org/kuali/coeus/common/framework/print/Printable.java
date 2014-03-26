/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.framework.print;

import org.kuali.coeus.common.framework.print.watermark.Watermarkable;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import javax.xml.transform.Source;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * This interface marks reports, notifications, BOs and Documents as printable
 * in Kuali-Coeus. KC Docs & BOs that will be printed via KC printing services
 * should implement this interface.
 */
public interface Printable extends Serializable {

	/**
	 * 
	 * This method provides a way to get the XSL Transform(s) for the KC
	 * generated XML. This XSLT will create a transformed XML-FO stream that
	 * will be converted to PDF. Note that multiple transforms are possible on
	 * this data.
	 */
    public List<Source> getXSLTemplates();

    public Map<String,Source> getXSLTemplateWithBookmarks();
	/**
	 * 
	 * This method will provide the either reflected or XML-Bean based XML for
	 * input to the Transform into XML-FO.
	 */
	public Map<String, byte[]> renderXML() throws PrintingException;

	/**
	 * 
	 * This method will provide the document object associated with the
	 * printable
	 * 
	 * @return ResearchDocument
	 */
	public KcPersistableBusinessObjectBase getPrintableBusinessObject();
	
	/**
	 * This method will return the PDF attachments specific to the printable.
	 * During printing the attachments will be added as bookmarks to the output.
	 * The Key in the map is used as the name of the bookmark.
	 * @return Map of Attachment pdf bytes with bookmark names.
	 */
	public Map<String, byte[]> getAttachments();
	
	/**
	 * 
	 * This method returns whether to enable or disable watermark
	 *in the report.
	 * @return boolean Value
	 */
	public boolean isWatermarkEnabled();
	/**
	 *  
	 * This method for setting the appropriate watermark
	 * with respect to the document.
	 * @return Watermarkable
	 */
	public Watermarkable getWatermarkable();
}
