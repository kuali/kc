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
package org.kuali.kra.proposaldevelopment.service.impl;

import org.kuali.coeus.sys.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DevelopmentProposalLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = 8611232870631352662L;

    private KcAuthorizationService kraAuthorizationService;
    
    @Override
    @SuppressWarnings("unchecked")
    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<DevelopmentProposal> unboundedResults = (List<DevelopmentProposal>) super.getSearchResults(fieldValues);
        
        List<DevelopmentProposal> filteredResults = new ArrayList<DevelopmentProposal>();
        
        filteredResults = (List<DevelopmentProposal>) filterForPermissions(unboundedResults);
        
        if (unboundedResults instanceof CollectionIncomplete) {
            filteredResults = new CollectionIncomplete<DevelopmentProposal>(filteredResults, ((CollectionIncomplete)unboundedResults).getActualSizeIfTruncated());
        }

        return filteredResults;
    }
    
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
            
            htmlDataList.add(getCustomLink(document, "actions", "copy", !canModifyProposal));
        }
        
        if (canModifyProposal) {
            htmlDataList.add(getMedusaLink(document, false));
        } else if (canViewProposal) {
            htmlDataList.add(getMedusaLink(document, true));
        }
        
        return htmlDataList;
    }

    @Override
    protected String getDocumentTypeName() {
        return "ProposalDevelopmentDocument";
    }

    @Override
    protected String getHtmlAction() {
        return "proposalDevelopmentProposal.do";
    }

    @Override
    protected String getKeyFieldName() {
        return "proposalNumber";
    }

    private List<DevelopmentProposal> filterForPermissions(List<DevelopmentProposal> results) {
        Person user = GlobalVariables.getUserSession().getPerson();
        List<DevelopmentProposal> filteredResults = new ArrayList<DevelopmentProposal>();
        for (DevelopmentProposal developmentProposal : results) {      
            DocumentAuthorizer authorizer = getDocumentHelperService().getDocumentAuthorizer("ProposalDevelopmentDocument");
            if (authorizer.canOpen(developmentProposal.getProposalDocument(), user)) {
                filteredResults.add(developmentProposal);
            }
        }
        
        return filteredResults;
    }

    /**
     * Sets the kraAuthorizationService attribute value.
     * @param kraAuthorizationService The kraAuthorizationService to set.
     */
    public void setKraAuthorizationService(KcAuthorizationService kraAuthorizationService) {
        this.kraAuthorizationService = kraAuthorizationService;
    }
    
    private DocumentHelperService getDocumentHelperService() {
        return KcServiceLocator.getService(DocumentHelperService.class);
    }
}
