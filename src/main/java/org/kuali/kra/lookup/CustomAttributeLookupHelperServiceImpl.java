/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.lookup;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.krad.bo.BusinessObject;

public class CustomAttributeLookupHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final String EQUAL_CHAR = "=";
    private Collection<String> documentTypeParam;
    
    public CustomAttributeLookupHelperServiceImpl() {
        documentTypeParam = getParameterService().getParameterValuesAsString(Constants.KC_GENERIC_PARAMETER_NAMESPACE,
                Constants.CUSTOM_ATTRIBUTE_DOCUMENT_DETAIL_TYPE_CODE, Constants.CUSTOM_ATTRIBUTE_DOCUMENT_PARAM_NAME);
    }
    
    /**
     * Rewrite this method to use descriptive name for document type code
     * @see org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<CustomAttributeDocument> searchResults = (List<CustomAttributeDocument>) super.getSearchResults(fieldValues);
        Map<String, String> documentTypes = getDocumentTypeMap();
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
        Map<String, String> documentTypes = getReverseDocumentTypeMap();
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

    protected Map<String, String> getDocumentTypeMap() {
        Map<String, String> documentTypes = new HashMap<String, String>();
        for (String documentType : documentTypeParam) {
            String[] params = documentType.split(EQUAL_CHAR);
            documentTypes.put(params[0], params[1]);
        }
        return documentTypes;

    }

    protected Map<String, String> getReverseDocumentTypeMap() {
        Map<String, String> documentTypes = new HashMap<String, String>();
        for (String documentType : documentTypeParam) {
            String[] params = documentType.split(EQUAL_CHAR);
            documentTypes.put(params[1].replace(" ", "+"), params[0]);
        }
        return documentTypes;

    }


}
