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
package org.kuali.kra.proposaldevelopment.document;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.common.permissions.Permissionable;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.proposaldevelopment.service.ProposalStateService;
import org.kuali.kra.proposaldevelopment.service.ProposalStatusService;
import org.kuali.kra.workflow.KraDocumentXMLMaterializer;
import org.kuali.rice.kns.document.Copyable;
import org.kuali.rice.kns.document.SessionDocument;
import org.kuali.rice.kns.workflow.DocumentInitiator;
import org.kuali.rice.kns.workflow.KualiDocumentXmlMaterializer;
import org.kuali.rice.kns.workflow.KualiTransactionalDocumentInformation;

public class ProposalDevelopmentDocument extends ResearchDocumentBase implements Copyable, SessionDocument, Permissionable {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ProposalDevelopmentDocument.class);

    private static final String DOCUMENT_TYPE_CODE = "PRDV";

    private static final long serialVersionUID = 2958631745964610527L;
    private List<DevelopmentProposal> developmentProposalList;


    public ProposalDevelopmentDocument() {
        super();
        developmentProposalList = new ArrayList<DevelopmentProposal>();
        DevelopmentProposal newProposal = new DevelopmentProposal();
        newProposal.setProposalDocument(this);
        developmentProposalList.add(newProposal);
    }

    public List<DevelopmentProposal> getDevelopmentProposalList() {
        return developmentProposalList;
    }

    public void setDevelopmentProposalList(List<DevelopmentProposal> proposalList) {
        this.developmentProposalList = proposalList;
    }

    public DevelopmentProposal getDevelopmentProposal() {
        return developmentProposalList.get(0);
    }

    public void setDevelopmentProposal(DevelopmentProposal proposal) {
        developmentProposalList.set(0, proposal);
    }

    @Override
    public void initialize() {
        super.initialize();
        getDevelopmentProposal().initializeOwnedByUnitNumber();
    }

    @Override
    public void handleRouteStatusChange() {
        super.handleRouteStatusChange();

        ProposalStateService proposalStateService = KraServiceLocator.getService(ProposalStateService.class);
        getDevelopmentProposal().setProposalStateTypeCode(proposalStateService.getProposalStateTypeCode(this, true));
    }


    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }


    /**
     * Wraps a document in an instance of KualiDocumentXmlMaterializer, that provides additional metadata for serialization
     * 
     * @see org.kuali.core.document.Document#wrapDocumentWithMetadataForXmlSerialization()
     */
    @Override
    // This method should go away in favor of using DD workflowProperties bean to serialize properties
    public KualiDocumentXmlMaterializer wrapDocumentWithMetadataForXmlSerialization() {
        ProposalAuthorizationService proposalauthservice = KraServiceLocator.getService(ProposalAuthorizationService.class);
        KualiTransactionalDocumentInformation transInfo = new KualiTransactionalDocumentInformation();
        DocumentInitiator initiatior = new DocumentInitiator();
        // String initiatorNetworkId = getDocumentHeader().getWorkflowDocument().getInitiatorNetworkId();
        // try {
        // UniversalUser initiatorUser = KNSServiceLocator.getUniversalUserService().getUniversalUser(new
        // AuthenticationUserId(initiatorNetworkId));
        // initiatorUser.getModuleUsers(); // init the module users map for serialization
        // initiatior.setUniversalUser(initiatorUser);
        // }
        // catch (UserNotFoundException e) {
        // throw new RuntimeException(e);
        // }
        transInfo.setDocumentInitiator(initiatior);
        KraDocumentXMLMaterializer xmlWrapper = new KraDocumentXMLMaterializer();
        // KualiDocumentXmlMaterializer xmlWrapper = new KualiDocumentXmlMaterializer();
        // xmlWrapper.setDocument(getDocumentRepresentationForSerialization());
        xmlWrapper.setKualiTransactionalDocumentInformation(transInfo);
        xmlWrapper.setRolepersons(proposalauthservice.getAllRolePersons(this));
        return xmlWrapper;

    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        getDevelopmentProposal().updateS2sOpportunity();

        KraServiceLocator.getService(ProposalStatusService.class).saveBudgetFinalVersionStatus(this);

        if (getDevelopmentProposal().getBudgetVersionOverviews() != null) {
            updateDocumentDescriptions(getDevelopmentProposal().getBudgetVersionOverviews());
        }
    }

    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        KraServiceLocator.getService(ProposalStatusService.class).loadBudgetStatus(this);

        getDevelopmentProposal().updateProposalChangeHistory();
    }

    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getRoleNames()
     */
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();

        roleNames.add(RoleConstants.AGGREGATOR);
        roleNames.add(RoleConstants.BUDGET_CREATOR);
        roleNames.add(RoleConstants.NARRATIVE_WRITER);
        roleNames.add(RoleConstants.VIEWER);
        roleNames.add("approver");

        return roleNames;
    }

    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentNumberForPermission()
     */
    public String getDocumentNumberForPermission() {
        return getDevelopmentProposal().getProposalNumber();
    }

    /**
     * 
     * @see org.kuali.kra.common.permissions.Permissionable#getDocumentKey()
     */
    public String getDocumentKey() {
        return Permissionable.PROPOSAL_KEY;
    }

    /**
     * @see org.kuali.core.bo.PersistableBusinessObjectBase#buildListOfDeletionAwareLists()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.addAll(getDevelopmentProposal().buildListOfDeletionAwareLists());
        managedLists.add(developmentProposalList);
        return managedLists;
    }

}
