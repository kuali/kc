/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.core;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kns.document.authorization.DocumentAuthorizer;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.kns.lookup.HtmlData.AnchorHtmlData;
import org.kuali.rice.kns.service.DocumentHelperService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.lookup.CollectionIncomplete;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.util.UrlFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component("proposalDevelopmentLookupableHelperService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DevelopmentProposalLookupableHelperServiceImpl extends KraLookupableHelperServiceImpl {

    private static final long serialVersionUID = 8611232870631352662L;

    @Autowired
    @Qualifier("kcAuthorizationService")
    private KcAuthorizationService kraAuthorizationService;


    @Autowired
    @Qualifier("documentHelperService")
    private DocumentHelperService documentHelperService;

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
            AnchorHtmlData editHtmlData = getViewLink(document.getDocumentNumber());
            String href = editHtmlData.getHref();
            href = href.replace("viewDocument=true", "viewDocument=false");
            editHtmlData.setHref(href);
            editHtmlData.setDisplayText("edit");
            htmlDataList.add(editHtmlData);
        }
        if(canViewProposal) {
            AnchorHtmlData viewLink = getViewLink(document.getDocumentNumber());
            htmlDataList.add(viewLink);
            
            htmlDataList.add(getCustomLink(document.getDocumentNumber(), "actions", "copy", !canModifyProposal));
        }
        
        if (canModifyProposal) {
            htmlDataList.add(getMedusaLink(document.getDocumentNumber(), false));
        } else if (canViewProposal) {
            htmlDataList.add(getMedusaLink(document.getDocumentNumber(), true));
        }
        
        return htmlDataList;
    }

    /**
     *
     * @param methodToCall method to call on action
     * @param readOnly whether the document should be readOnly or not
     */
    protected AnchorHtmlData getCustomLink(String documentNumber, String methodToCall, String linkName, Boolean readOnly) {
        AnchorHtmlData htmlData = new AnchorHtmlData();
        htmlData.setDisplayText(linkName);
        Properties parameters = new Properties();
        parameters.put(KRADConstants.DISPATCH_REQUEST_PARAMETER, methodToCall);
        parameters.put(KRADConstants.PARAMETER_COMMAND, KewApiConstants.DOCSEARCH_COMMAND);
        parameters.put(KRADConstants.DOCUMENT_TYPE_NAME, getDocumentTypeName());
        parameters.put("viewDocument", readOnly.toString());
        parameters.put("docId", documentNumber);
        String href  = UrlFactory.parameterizeUrl("../" + getHtmlAction(), parameters);

        htmlData.setHref(href);
        return htmlData;
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

    public KcAuthorizationService getKraAuthorizationService() {
        return kraAuthorizationService;
    }

    public DocumentHelperService getDocumentHelperService() {
        return documentHelperService;
    }

    public void setDocumentHelperService(DocumentHelperService documentHelperService) {
        this.documentHelperService = documentHelperService;
    }
}
