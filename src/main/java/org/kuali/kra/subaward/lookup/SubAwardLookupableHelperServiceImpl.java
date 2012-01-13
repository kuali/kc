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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.versioning.VersionHistory;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.service.VersionHistoryService;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DocumentService;
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
    private VersionHistoryService versionHistoryService; 
    
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
        List<SubAward> returnResults = new ArrayList<SubAward>();
        try {
            returnResults = filterForActiveSubAwards(unboundedResults);
        }
        catch (WorkflowException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     
        return returnResults;
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
      
      @SuppressWarnings("unchecked")
      protected List<SubAward> filterForActiveSubAwards(Collection<SubAward> collectionByQuery) throws WorkflowException {
          BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);
          DocumentService documentService = KraServiceLocator.getService(DocumentService.class);
          Set<String> subAwardCodes = new TreeSet<String>();
          List<Integer> subAwardCodeList = new ArrayList<Integer>();
          List<String> subAwardCodeSortedList = new ArrayList<String>();
          for(SubAward subAward: collectionByQuery) {
              subAwardCodes.add(subAward.getSubAwardCode());
          }
          for(String subAwardCode: subAwardCodes) {
              subAwardCodeList.add(Integer.parseInt(subAwardCode));
          }
          Collections.sort(subAwardCodeList);
          for(Integer subAward: subAwardCodeList){
              subAwardCodeSortedList.add(Integer.toString(subAward));
          }
          List<SubAward> activeSubAwards = new ArrayList<SubAward>();
          for(String versionName: subAwardCodeSortedList) {
              VersionHistory versionHistory = versionHistoryService.findActiveVersion(SubAward.class, versionName);
              if(versionHistory != null) {
                  SubAward activeSubAward = (SubAward) versionHistory.getSequenceOwner();
                  if(activeSubAward != null) {
                      activeSubAwards.add(activeSubAward);
                  }
              }
          } 
          return activeSubAwards;
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
    /**
     * @param versionHistoryService
     */
    public void setVersionHistoryService(VersionHistoryService versionHistoryService) {
       this.versionHistoryService = versionHistoryService; 
    }
}
