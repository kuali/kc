/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.document.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.RiceConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.Core;

import edu.iu.uis.eden.EdenConstants;
import edu.iu.uis.eden.docsearch.DocSearchCriteriaVO;
import edu.iu.uis.eden.docsearch.DocSearchVO;
import edu.iu.uis.eden.docsearch.SearchableAttributeValue;
import edu.iu.uis.eden.docsearch.StandardDocumentSearchResultProcessor;
import edu.iu.uis.eden.lookupable.Column;
import edu.iu.uis.eden.util.Utilities;
import edu.iu.uis.eden.web.KeyValueSort;

public class ProposalDocumentSearchResultProcessor extends StandardDocumentSearchResultProcessor {
    private static final String PROPERTY_NAME_COPY_DOCUMENT = "copyDocument";
    private static final String DOC_TYPE_PROPOSAL_DEVELOPMENT = "ProposalDevelopmentDocument";
    private static final String DOC_COPY_HANDLER_ACTION = "DocCopyHandler.do";
    
    @Override
    public List<Column> constructColumnList(DocSearchCriteriaVO criteria) {
        List<Column> proposalSearchResultColumns = super.constructColumnList(criteria);
        this.addColumnUsingKey(proposalSearchResultColumns, new HashMap<String,String>(), PROPERTY_NAME_COPY_DOCUMENT, "Copy Document", null);
        
        return proposalSearchResultColumns;
    }  
    
    private String buildDocCopyHandlerUrl(String documentNumber) {
        String appContext = Core.getCurrentContextConfig().getProperty(Constants.APP_CONTEXT_KEY);
        StringBuffer urlBuffer = new StringBuffer();
        urlBuffer.append("/");
        urlBuffer.append(appContext);
        urlBuffer.append("/");
        urlBuffer.append(DOC_COPY_HANDLER_ACTION);
        urlBuffer.append("?");
        urlBuffer.append(RiceConstants.PARAMETER_DOC_ID);
        urlBuffer.append("=");
        urlBuffer.append(documentNumber);
        urlBuffer.append(RiceConstants.DOCHANDLER_URL_CHUNK);
        
        return urlBuffer.toString();
    }
    
    private boolean isDocumentHandlerPopup() {
        String applicationConstant = Utilities.getApplicationConstant(EdenConstants.DOCUMENT_SEARCH_DOCUMENT_POPUP_KEY).trim();
        return (EdenConstants.DOCUMENT_SEARCH_DOCUMENT_POPUP_VALUE.equals(applicationConstant));
    }

    private String getCustomFieldValue(DocSearchVO docSearchVO) {
        String fieldValue = null;
        String copyDocumentUrl = buildDocCopyHandlerUrl(docSearchVO.getRouteHeaderId().toString());
        String linkPopup = "";
        
        if(StringUtils.isNotEmpty(docSearchVO.getDocTypeName()) && docSearchVO.getDocTypeName().equalsIgnoreCase(DOC_TYPE_PROPOSAL_DEVELOPMENT)) {
            if (this.isDocumentHandlerPopup()) {
                linkPopup = " target=\"_blank\"";
            }
            //fieldValue = "<a href='" + copyDocumentUrl + "' target='_blank'>Copy</a>" ;
            fieldValue = "<a href='" + copyDocumentUrl + "' " + linkPopup + ">Copy</a>" ;
        } else {
            fieldValue = "";
        }
        
        return fieldValue;
    }
    
    @Override
    public KeyValueSort generateSearchResult(DocSearchVO docSearchVO, Column column, Map<String,Object> sortValuesByColumnKey) {
        KeyValueSort returnValue = null;
        String fieldValue = null;
        Object sortFieldValue = null;
        String columnKeyName = column.getKey();
        SearchableAttributeValue attributeValue = null;
        
        if (PROPERTY_NAME_COPY_DOCUMENT.equals(columnKeyName)) {
            fieldValue = getCustomFieldValue(docSearchVO);
            sortFieldValue = sortValuesByColumnKey.get(columnKeyName);
            returnValue = new KeyValueSort(columnKeyName, fieldValue, (sortFieldValue != null) ? sortFieldValue : fieldValue, attributeValue);
            
        } else { 
            returnValue = super.generateSearchResult(docSearchVO, column, sortValuesByColumnKey);
        }
        
        return returnValue;
    }
}
