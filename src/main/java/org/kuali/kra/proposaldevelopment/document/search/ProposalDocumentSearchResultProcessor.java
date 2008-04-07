/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.document.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.Core;

import edu.iu.uis.eden.docsearch.DocSearchCriteriaVO;
import edu.iu.uis.eden.docsearch.DocSearchVO;
import edu.iu.uis.eden.docsearch.SearchableAttributeValue;
import edu.iu.uis.eden.docsearch.StandardDocumentSearchResultProcessor;
import edu.iu.uis.eden.lookupable.Column;
import edu.iu.uis.eden.web.KeyValueSort;

public class ProposalDocumentSearchResultProcessor extends StandardDocumentSearchResultProcessor {
    private static final String PROPERTY_NAME_COPY_DOCUMENT = "copyDocument";
    
    @Override
    public List<Column> constructColumnList(DocSearchCriteriaVO criteria) {
        List<Column> proposalSearchResultColumns = super.constructColumnList(criteria);
        this.addColumnUsingKey(proposalSearchResultColumns, new HashMap<String,String>(), PROPERTY_NAME_COPY_DOCUMENT, "Copy Document", null);
        
        return proposalSearchResultColumns;
    }  
    
    @Override
    public KeyValueSort generateSearchResult(DocSearchVO docSearchVO, Column column, Map<String,Object> sortValuesByColumnKey) {
        KeyValueSort returnValue = null;
        String fieldValue = null;
        Object sortFieldValue = null;
        String columnKeyName = column.getKey();
        SearchableAttributeValue attributeValue = null;
        String appContext = Core.getCurrentContextConfig().getProperty("app.context.name");
        String copyDocumentUrl = "/" + appContext + "/DocCopyHandler.do?command=displayDocSearchView&docId=";
        if (PROPERTY_NAME_COPY_DOCUMENT.equals(columnKeyName)) {
            if(StringUtils.isNotEmpty(docSearchVO.getDocTypeName()) && docSearchVO.getDocTypeName().equalsIgnoreCase("ProposalDevelopmentDocument"))
                fieldValue = "<a href='" + copyDocumentUrl +  docSearchVO.getRouteHeaderId().toString() + "' target='_blank'>Copy</a>" ;
            else
                fieldValue = ""; 
            
            sortFieldValue = sortValuesByColumnKey.get(columnKeyName);
            returnValue = new KeyValueSort(columnKeyName, fieldValue, (sortFieldValue != null) ? sortFieldValue : fieldValue, attributeValue);
            
        } else { 
            returnValue = super.generateSearchResult(docSearchVO, column, sortValuesByColumnKey);
        }
        
        return returnValue;
    }
}
