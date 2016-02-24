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

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.sys.framework.lookup.KcKualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("customAttributeDocumentLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomAttributeLookupHelperServiceImpl extends KcKualiLookupableHelperServiceImpl {
	
	@Autowired
    @Qualifier("customAttributeService")
	public transient CustomAttributeService customAttributeService;

    private static final String EQUAL_CHAR = "=";
    
    public CustomAttributeLookupHelperServiceImpl() {
    }
    
    /**
     * Rewrite this method to use descriptive name for document type code
     * @see org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<CustomAttributeDocument> searchResults = (List<CustomAttributeDocument>) super.getSearchResults(fieldValues);
        Map<String, String> documentTypes = getCustomAttributeService().getDocumentTypeMap();
        for (CustomAttributeDocument customAttributeDocument : searchResults) {
            customAttributeDocument.setDocumentTypeName(documentTypes.get(customAttributeDocument.getDocumentTypeName()));
        }
        return searchResults;
    }

    /**
     * Because the search results is using descriptive name, we need to reverse to use code in href. 
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.krad.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
        Map<String, String> documentTypes = getCustomAttributeService().getReverseDocumentTypeMap();
        for (HtmlData htmlData : htmlDataList) {
            if (StringUtils.isNotBlank(((AnchorHtmlData) htmlData).getHref())) {
                String docType = StringUtils.substringBetween(((AnchorHtmlData) htmlData).getHref(), "documentTypeName=", "&");
                if (StringUtils.isNotBlank(docType) && documentTypes.containsKey(docType)) {
                    ((AnchorHtmlData) htmlData).setHref(((AnchorHtmlData) htmlData).getHref().replace(docType,
                            documentTypes.get(docType)));
                }
            }

        }
        return htmlDataList;
    }

	public CustomAttributeService getCustomAttributeService() {
		return customAttributeService;
	}

	public void setCustomAttributeService(
			CustomAttributeService customAttributeService) {
		this.customAttributeService = customAttributeService;
	}
}
