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
