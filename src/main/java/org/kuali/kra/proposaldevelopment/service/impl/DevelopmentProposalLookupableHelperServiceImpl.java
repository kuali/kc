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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.service.KcPersonService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.rice.kns.bo.BusinessObject;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * This class...
 */
public class DevelopmentProposalLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    static final String INVESTIGATOR = "principalInvestigatorName";
    static final String PERSON_ID = "personId";
    static final String ROLODEX_ID = "rolodexId";

    private static final long serialVersionUID = 1371970456980693936L;

    private KraAuthorizationService kraAuthorizationService;
    private KcPersonService kcPersonService;

    /**
     * @see org.kuali.rice.kns.lookup.KualiLookupableHelperServiceImpl#getSearchResults(java.util.Map)
     */
    @Override
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<? extends BusinessObject> results = super.getSearchResults(fieldValues);
        String investigatorSearch = fieldValues.get(INVESTIGATOR);
        if (investigatorSearch != null && investigatorSearch.length() > 0) {
            List<BusinessObject> filteredResults = new ArrayList<BusinessObject>();
            investigatorSearch = investigatorSearch.replace("?", ".").replace("*", ".*").replace("%", ".*");
            for (BusinessObject bo : results) {
                if (((DevelopmentProposal)bo).getPrincipalInvestigatorName().matches(investigatorSearch)) {
                    filteredResults.add(bo);
                }
            }
            results = filteredResults;
        }
        return results;
    }
    
    /**
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getCustomActionUrls(org.kuali.rice.kns.bo.BusinessObject, java.util.List)
     */
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        ProposalDevelopmentDocument document = ((DevelopmentProposal)businessObject).getProposalDocument();
        String currentUser = GlobalVariables.getUserSession().getPrincipalId();
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        boolean canModifyProposal = kraAuthorizationService.hasPermission(currentUser, document, PermissionConstants.MODIFY_PROPOSAL);
        boolean canViewProposal = kraAuthorizationService.hasPermission(currentUser, document, PermissionConstants.VIEW_PROPOSAL);
        if(canModifyProposal) {
            AnchorHtmlData editHtmlData = getViewLink(document);
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
        }
        if(canViewProposal) {
            AnchorHtmlData viewLink = getViewLink(document);
            htmlDataList.add(viewLink);
            
        }
        if (canModifyProposal) {
            htmlDataList.add(getMedusaLink(document, false));
        } else if (canViewProposal) {
            htmlDataList.add(getMedusaLink(document, true));
        }
        
        return htmlDataList;
    }

    /**
     * @see org.kuali.rice.kns.lookup.AbstractLookupableHelperServiceImpl#getInquiryUrl(org.kuali.rice.kns.bo.BusinessObject, java.lang.String)
     */
    @Override
    public HtmlData getInquiryUrl(BusinessObject bo, String propertyName) {
        BusinessObject inqBo = bo;
        String inqPropertyName = propertyName;
        if (propertyName.equals(INVESTIGATOR)) {
            DevelopmentProposal proposal = (DevelopmentProposal)bo;
            ProposalPerson principalInvestigator = proposal.getPrincipalInvestigator();
            if (principalInvestigator != null) {
                if (StringUtils.isNotBlank(principalInvestigator.getPersonId())) {
                    inqBo = this.kcPersonService.getKcPersonByPersonId(principalInvestigator.getPersonId());
                    inqPropertyName = PERSON_ID;
                } else {
                    if (principalInvestigator.getRolodexId() != null) {
                        inqBo = new Rolodex();
                        ((Rolodex) inqBo).setRolodexId(principalInvestigator.getRolodexId());
                        inqPropertyName = ROLODEX_ID;
                    }
                }
            }
        }
        return super.getInquiryUrl(inqBo, inqPropertyName);    
    }

    /**
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getDocumentTypeName()
     */
    @Override
    protected String getDocumentTypeName() {
        return "ProposalDevelopmentDocument";
    }

    /**
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getHtmlAction()
     */
    @Override
    protected String getHtmlAction() {
        return "proposalDevelopmentProposal.do";
    }

    /**
     * @see org.kuali.kra.lookup.KraLookupableHelperServiceImpl#getKeyFieldName()
     */
    @Override
    protected String getKeyFieldName() {
        return "proposalNumber";
    }

    /**
     * Sets the kraAuthorizationService attribute value.
     * @param kraAuthorizationService The kraAuthorizationService to set.
     */
    public void setKraAuthorizationService(KraAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }

    /**
     * Sets the kcPersonService attribute value.
     * @param kcPersonService The kcPersonService to set.
     */
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

}
