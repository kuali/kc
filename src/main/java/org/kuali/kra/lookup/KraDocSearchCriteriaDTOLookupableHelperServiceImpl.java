/*
 * Copyright 2006-2008 The Kuali Foundation
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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.kew.bo.lookup.DocSearchCriteriaDTOLookupableHelperServiceImpl;
import org.kuali.rice.kew.docsearch.DocumentSearchResult;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Column;
import org.kuali.rice.kns.web.ui.ResultRow;

public class KraDocSearchCriteriaDTOLookupableHelperServiceImpl extends DocSearchCriteriaDTOLookupableHelperServiceImpl {

    @Override
    public Collection performLookup(LookupForm lookupForm,
            Collection resultTable, boolean bounded) {
        List<DocumentSearchResult> result = (List<DocumentSearchResult> ) super.performLookup(lookupForm, resultTable, bounded);
        //DocumentSearchResultComponents components = KEWServiceLocator.getDocumentSearchService().getList(GlobalVariables.getUserSession().getPrincipalId(), criteria);

    
        for (ResultRow resultRow : (List<ResultRow>)resultTable) {
            for (Column column : resultRow.getColumns()) {
                if (column.getPropertyName().equals("copyDocument") && column.getColumnAnchor()!= null) {
                    AnchorHtmlData anchor = (AnchorHtmlData)column.getColumnAnchor();
                     String docId = StringUtils.substringBetween(column.getPropertyValue(),"docId=", "&");
                     anchor.setHref(StringUtils.substringBetween(column.getPropertyValue(), "<a href=\"", "docId=")+"docId="+docId);
                     column.setColumnAnchor(anchor);
                }
            }
        }

        return result;
    }
}
