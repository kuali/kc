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
package org.kuali.kra.institutionalproposal.proposallog;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.UrlFactory;
import org.kuali.rice.kns.web.struts.form.LookupForm;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;

/**
 * Lookupable helper service used for proposal log lookup
 */     
public class ProposalLogLookupableHelperServiceImpl extends KualiLookupableHelperServiceImpl {

    private static final long serialVersionUID = -7638045643796948730L;
    
    private static final String USERNAME_FIELD = "person.userName";
    private static final String STATUS_PENDING = "1";
    
    private boolean isLookupForProposalCreation;
    
    /*
     * We want to allow users to query on principal name instead of person id, 
     * so we need to translate before performing the lookup.
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection performLookup(LookupForm lookupForm, Collection resultTable, boolean bounded) {
        String userName = (String) lookupForm.getFieldsForLookup().get(USERNAME_FIELD);
        if (!StringUtils.isBlank(userName)) {
            KcPerson person = getKcPersonService().getKcPersonByUserName(userName);
            if (person != null) {
                lookupForm.getFieldsForLookup().put("piId", person.getPersonId());
            }
            lookupForm.getFieldsForLookup().remove(USERNAME_FIELD);
        }
        
        return super.performLookup(lookupForm, resultTable, bounded);
    }
    
    /* 
     * Overriding this to only return the currently Active version of a proposal 
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        
        checkIsLookupForProposalCreation(fieldValues);
        
//        if (isLookupForProposalCreation) {
//            fieldValues.remove(ProposalLog.LOG_STATUS);
//            fieldValues.put(ProposalLog.LOG_STATUS, ProposalLogUtils.getProposalLogPendingStatusCode());
//        }
        
        return super.getSearchResults(fieldValues);
    }
    
    /**
     * create 'merge' link
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        if (isLookupForProposalCreation) {
            if (STATUS_PENDING.equals(((ProposalLog) businessObject).getLogStatus())) {
                htmlDataList.add(getSelectLinkForProposalCreation((ProposalLog) businessObject));
            }
        }
        else {
            htmlDataList = super.getCustomActionUrls(businessObject, pkNames);
            if (((ProposalLog) businessObject).isMergeCandidate()) {
                htmlDataList.add(getMergeLink(((ProposalLog) businessObject).getProposalNumber()));
            }
            if (((ProposalLog) businessObject).isSubmitted()) {
                removeEditLink(htmlDataList);
            }
            htmlDataList.add(getPrintLink(((ProposalLog)businessObject).getProposalNumber()));
        }
        return htmlDataList;
    }
    
    /**
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getRows()
     */
    @Override
    public List<Row> getRows() {
        if (this.getParameters().containsKey("returnLocation")
                && ((String[]) this.getParameters().get("returnLocation"))[0].indexOf("institutionalProposalCreate") > 0) {
            isLookupForProposalCreation = true;
        }
//        if (this.getParameters().containsKey("isPendingSelected")) {
//            GlobalVariables.getMessageMap().putError("proposalLogStatus", KeyConstants.ERROR_PENDING_PROPOSAL_LOG_ONLY);
//        }

        List<Row> rows = super.getRows();
        for (Row row : rows) {
            for (Field field : row.getFields()) {
                if (field.getPropertyName().equals(USERNAME_FIELD)) {
                    field.setFieldConversions("principalName:person.userName,principalId:personId");
                }
                if (field.getPropertyName().equals("logStatus") && isLookupForProposalCreation) {
                    field.setPropertyValue(STATUS_PENDING);
                }
            }
        }
        return rows;
    }
    
    protected AnchorHtmlData getSelectLinkForProposalCreation(ProposalLog proposalLog) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("select");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "docHandler");
        parameters.put(KNSConstants.PARAMETER_COMMAND, "initiate");
        parameters.put(KNSConstants.DOCUMENT_TYPE_NAME, "InstitutionalProposalDocument");
        parameters.put("proposalNumber", proposalLog.getProposalNumber());
        String href  = UrlFactory.parameterizeUrl("../institutionalProposalHome.do", parameters);

        htmlData.setHref(href);
        return htmlData;
    }
    

    protected AnchorHtmlData getMergeLink(String proposalNumber) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("merge");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "pageEntry");
        parameters.put("proposalLogNumber", proposalNumber);
        String href  = UrlFactory.parameterizeUrl("../mergeProposalLog.do", parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }
    
    protected AnchorHtmlData getPrintLink(String proposalNumber) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText("print");
        Properties parameters = new Properties();
        parameters.put(KNSConstants.DISPATCH_REQUEST_PARAMETER, "printProposalLog");
        parameters.put("proposalNumber", proposalNumber);
        String href  = UrlFactory.parameterizeUrl("../printProposalLog.do", parameters);
        
        htmlData.setHref(href);
        return htmlData;
    }    
    
    protected void checkIsLookupForProposalCreation(Map<String, String> fieldValues) {
        String returnLocation = fieldValues.get("backLocation");
        if (returnLocation.contains("institutionalProposalCreate.do")) {
            isLookupForProposalCreation = true;
        }
    }
    
    protected void removeEditLink(List<HtmlData> htmlDataList) {
        int editLinkIndex = -1;
        int currentIndex = 0;
        for (HtmlData htmlData : htmlDataList) {
            if (KNSConstants.MAINTENANCE_EDIT_METHOD_TO_CALL.equals(htmlData.getMethodToCall())) {
                editLinkIndex = currentIndex;
                break;
            }
            currentIndex++;
        }
        if (editLinkIndex != -1) {
            htmlDataList.remove(editLinkIndex);
        }
    }
    
    protected KcPersonService getKcPersonService() {
        return KraServiceLocator.getService(KcPersonService.class);
    }
    
        }
    
