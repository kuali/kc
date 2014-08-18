/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.coi.lookup;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;

import java.util.*;

@SuppressWarnings("deprecation")
public abstract class CoiDisclosureLookupableHelperBase extends KraLookupableHelperServiceImpl {


    private static final long serialVersionUID = -1746355811792663715L;
    
    private TaskAuthorizationService taskAuthorizationService;

    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<? extends BusinessObject> retVal = new ArrayList<CoiDisclosure>();
        if(isAuthorizedForCoiLookups()) {
            retVal = getLookupSpecificSearchResults(fieldValues);
        }
        return retVal;
    }

    protected boolean isAuthorizedForCoiLookups() {
        ApplicationTask task = new ApplicationTask(TaskName.LOOKUP_COI_DISCLOSURES);
        return getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
    }
    
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        if (taskAuthorizationService == null) {
            taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
        }
        return taskAuthorizationService;
    }

    protected AnchorHtmlData getOpenLink(Document document) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("open");
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, KRADConstants.DOC_HANDLER_METHOD);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("docId", document.getDocumentNumber());
        String href  = UrlFactory.parameterizeUrl("../" + getHtmlAction(), parameters);
        
        htmlData.setHref(href);
        return htmlData;

    }
    
    @Override
    public List<Row> getRows() {
        List<Row> rows =  super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals("person.userName")) {
                    field.setFieldConversions("principalName:person.userName,principalId:personId");
                }
            }
        }
        return rows;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        String userName = (String) lookupForm.getFieldsForLookup().get("person.userName");
            if (StringUtils.isNotEmpty(userName)) {
                KcPerson person = getKcPersonService().getKcPersonByUserName(userName);
            if (person != null) {
                lookupForm.getFieldsForLookup().put("personId", person.getPersonId());
            }
        }
        
        return super.performLookup(lookupForm, resultTable, bounded);
    }
    
    public KcPersonService getKcPersonService() {
        return (KcPersonService) KcServiceLocator.getService(KcPersonService.class);
    }
    
    /**
     * This method returns all the disclosures for the given field values
     * @param fieldValues
     * @return
     */
    public List<? extends BusinessObject> getResults(Map<String, String> fieldValues) {
        List<CoiDisclosure> allDisclosures = (List<CoiDisclosure>) getLookupService().findCollectionBySearch(CoiDisclosure.class, fieldValues);
        return allDisclosures;
    }
    
    @SuppressWarnings("rawtypes")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        htmlDataList.add(getOpenLink(((CoiDisclosure) businessObject).getCoiDisclosureDocument()));
        return htmlDataList;
    }
    
    @Override
    protected void addEditHtmlData(List<HtmlData> htmlDataList, BusinessObject businessObject) {     
        htmlDataList.add(getEditLink(businessObject));    
    }
    
    /*
     * Not sure if we need this at the moment.
     */
    protected AnchorHtmlData getEditLink(BusinessObject businessObject) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("edit");

        return htmlData;
    }
    
    @Override
    protected String getDocumentTypeName() {
        return "CoiDisclosureDocument";
    }

    @Override
    protected String getHtmlAction() {
        return "coiDisclosure.do";
    }

    @Override
    protected String getKeyFieldName() {
        return "coiDisclosureId";
    }

    public abstract List<? extends BusinessObject> getLookupSpecificSearchResults(Map<String, String> fieldValues);

}
