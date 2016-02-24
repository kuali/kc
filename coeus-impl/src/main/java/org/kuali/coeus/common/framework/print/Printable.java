/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
 * in Kuali-Coeus. KC Docs &amp; BOs that will be printed via KC printing services
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
