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
package org.kuali.coeus.common.impl.custom.attr;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kns.inquiry.KualiInquirableImpl;

public class CustomAtttributeDocumentInquirableImpl extends KualiInquirableImpl {
	
	private static final String DOCUMENT_TYPE_NAME = "documentTypeName";
	
	public transient CustomAttributeService customAttributeService;
	
	@Override
	public Object retrieveDataObject(Map fieldValues) {
		Object docType = fieldValues.get(DOCUMENT_TYPE_NAME);
		if (docType != null && docType instanceof String) {
			String formatedDocType = StringUtils.replace(docType.toString(), " ", "+");
			String newDocType = getCustomAttributeService().getReverseDocumentTypeMap().get(formatedDocType);
			if (StringUtils.isNotBlank(newDocType)){
				fieldValues.put(DOCUMENT_TYPE_NAME, newDocType);
			}
		}
 		return super.retrieveDataObject(fieldValues);
	}

	public CustomAttributeService getCustomAttributeService() {
		if (customAttributeService == null) {
			customAttributeService = KcServiceLocator.getService("customAttributeService");
		}
		return customAttributeService;
	}

	public void setCustomAttributeService(
			CustomAttributeService customAttributeService) {
		this.customAttributeService = customAttributeService;
	}

	

}
