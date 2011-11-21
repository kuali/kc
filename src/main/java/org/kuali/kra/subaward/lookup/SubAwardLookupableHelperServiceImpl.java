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
package org.kuali.kra.subaward.lookup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.kra.subaward.bo.SubAward;

public class SubAwardLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    
    private static final String COPY_HREF_PATTERN = "../DocCopyHandler.do?docId=%s&command=displayDocSearchView&documentTypeName=%s";
    static final String PERSON_ID = "personId";
    static final String ROLODEX_ID = "rolodexId";
    static final String UNIT_NUMBER = "unitNumber";
    static final String USER_ID = "userId";
    static final String PI_NAME = "principalInvestigatorName";
    static final String OSP_ADMIN_NAME = "ospAdministratorName";
    
    
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        super.setBackLocationDocFormKey(fieldValues);  
        if (this.getParameters().containsKey(USER_ID)) {
            fieldValues.put("projectPersons.personId", ((String[]) this.getParameters().get(USER_ID))[0]);
        }
        Map<String, String> formProps = new HashMap<String, String>();
        if (!StringUtils.isEmpty(fieldValues.get("lookupOspAdministratorName"))) {
            formProps.put("fullName", fieldValues.get("lookupOspAdministratorName"));
            formProps.put("unitAdministratorTypeCode", "2");
        }
        fieldValues.remove("lookupOspAdministratorName");
        List<SubAward> unboundedResults = (List<SubAward>) super.getSearchResultsUnbounded(fieldValues);
        
     
        return unboundedResults;
    }


    
    /**
     * add open links to actions list
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
        SubAwardDocument document = ((SubAward) businessObject).getSubAwardDocument();
        htmlDataList.add(getOpenLink((SubAward) businessObject, false));
        return htmlDataList;
    }
   /**
     * @param subaward
     * @return
     */
    protected AnchorHtmlData getOpenLink(SubAward subAward, Boolean viewOnly) {
        SubAwardDocument subAwardDocument = subAward.getSubAwardDocument();
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("open");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, KNSConstants.DOC_HANDLER_METHOD);
        parameters.put(KNSConstants.PARAMETER_COMMAND, KEWConstants.DOCSEARCH_COMMAND);
        parameters.put(KNSConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", viewOnly.toString());
        parameters.put("docOpenedFromAwardSearch", "true");
        parameters.put("docId", subAwardDocument.getDocumentNumber());
        parameters.put("placeHolderAwardId", subAward.getSubAwardId().toString());
        String href = UrlFactory.parameterizeUrl("../"+getHtmlAction(), parameters);
        htmlData.setHref(href);
        return htmlData;
    }
     
   
    
      /**
       * This override is reset field definitions
       * @see org.kuali.core.lookup.AbstractLookupableHelperServiceImpl#getRows()
       */
      @Override
      public List<Row> getRows() {
          List<Row> rows =  super.getRows();
          for (Row row : rows) {
              for (Field field : row.getFields()) {
                 
                  if (field.getPropertyName().equals("startDate")||field.getPropertyName().equals("endDate")
                          ||field.getPropertyName().equals("closeoutDate")) {
                      field.setDatePicker(true);
                  }
              }
          }
          return rows;
      }
      @Override
      protected void addEditHtmlData(List<HtmlData> htmlDataList, BusinessObject businessObject) {
          //no-op
      }
    @Override
    protected String getHtmlAction() {
        return "subAwardHome.do";
    }

    @Override
    protected String getDocumentTypeName() {
        return "SubAwardDocument";
    }

    @Override
    protected String getKeyFieldName() {
        return "SubAwardId";
    }

}
