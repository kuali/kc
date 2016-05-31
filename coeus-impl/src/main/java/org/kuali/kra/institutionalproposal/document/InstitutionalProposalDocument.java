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
package org.kuali.kra.institutionalproposal.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.auth.perm.Permissionable;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.coeus.common.permissions.impl.PermissionableKeys;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.institutionalproposal.InstitutionalProposalConstants;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonCreditSplit;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnit;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalVersioningService;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReviewExemption;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.coeus.common.framework.krms.KrmsRulesContext;
import org.kuali.coeus.common.impl.krms.KcKrmsFactBuilderServiceHelper;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krms.api.engine.Facts.Builder;

/**
 * 
 * This class represents the InstitutionalProposalDocument Object.
 * InstitutionalProposalDocument has a 1:1 relationship with InstitutionalProposal Business Object.
 * We have declared a list of InstitutionalProposal BOs in the InstitutionalProposalDocument at the same time to
 * get around the OJB anonymous keys issue of primary keys of different data types.
 * Also we have provided convenient getter and setter methods so that to the outside world;
 * InstitutionalProposal and InstitutionalProposalDocument can have a 1:1 relationship.
 */
@NAMESPACE(namespace=InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE)
@COMPONENT(component=ParameterConstants.DOCUMENT_COMPONENT)
public class InstitutionalProposalDocument extends KcTransactionalDocumentBase implements KrmsRulesContext, Permissionable {
    private static final Log LOG = LogFactory.getLog(InstitutionalProposalDocument.class);

    public static final String DOCUMENT_TYPE_CODE = "INPR";
    

    private static final long serialVersionUID = 5101782927161970631L;
    
    
    private List<InstitutionalProposal> institutionalProposalList;
    

    public InstitutionalProposalDocument(){        
        super();        
        init();
    }
    
    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between InstitutionalProposalDocument 
     * and InstitutionalProposal to the outside world - aka a single InstitutionalProposal field associated with InstitutionalProposalDocument
     * @return
     */
    public InstitutionalProposal getInstitutionalProposal() {
        return institutionalProposalList.get(0);
    }

    /**
     * 
     * This method is a convenience method for facilitating a 1:1 relationship between InstitutionalProposalDocument 
     * and InstitutionalProposal to the outside world - aka a single InstitutionalProposal field associated with InstitutionalProposalDocument
     * @param institutionalProposal
     */
    public void setInstitutionalProposal(InstitutionalProposal institutionalProposal) {
        institutionalProposalList.set(0, institutionalProposal);
    }
   

    public List<InstitutionalProposal> getInstitutionalProposalList() {
        return institutionalProposalList;
    }

    /**
     *
     * @param institutionalProposalList
     */
    public void setInstitutionalProposalList(List<InstitutionalProposal> institutionalProposalList) {
        this.institutionalProposalList = institutionalProposalList;
    }

    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }
    
    protected void init() {
        institutionalProposalList = new ArrayList<InstitutionalProposal>();
        institutionalProposalList.add(new InstitutionalProposal());
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();       
        
        InstitutionalProposal institutionalProposal = getInstitutionalProposal();
        
        managedLists.add(institutionalProposal.getInstitutionalProposalUnitContacts());
        managedLists.add(institutionalProposal.getInstitutionalProposalScienceKeywords());
        managedLists.add(institutionalProposal.getInstitutionalProposalCostShares());
        managedLists.add(institutionalProposal.getInstitutionalProposalUnrecoveredFandAs());
        managedLists.add(institutionalProposal.getAwardFundingProposals());
        managedLists.add(institutionalProposal.getInstitutionalProposalNotepads());
        
        List<InstitutionalProposalSpecialReviewExemption> specialReviewExemptions = new ArrayList<InstitutionalProposalSpecialReviewExemption>();
        for (InstitutionalProposalSpecialReview specialReview : getInstitutionalProposal().getSpecialReviews()) {
            specialReviewExemptions.addAll(specialReview.getSpecialReviewExemptions());        
        }
        managedLists.add(specialReviewExemptions);
        managedLists.add(institutionalProposal.getSpecialReviews());
        
        // For project personnel 
        List<InstitutionalProposalPersonUnit> units = new ArrayList<InstitutionalProposalPersonUnit>();
        List<InstitutionalProposalPersonCreditSplit> creditSplits = new ArrayList<InstitutionalProposalPersonCreditSplit>();
        for (InstitutionalProposalPerson person : institutionalProposal.getProjectPersons()) {
            units.addAll(person.getUnits());
            creditSplits.addAll(person.getCreditSplits());
        }
        managedLists.add(units);
        managedLists.add(creditSplits);
        managedLists.add(institutionalProposal.getProjectPersons());

        return managedLists;
    }
    
    @Override
    public void doRouteStatusChange(DocumentRouteStatusChange statusChangeEvent) {
        executeAsLastActionUser(() -> {
            super.doRouteStatusChange(statusChangeEvent);

            String newStatus = statusChangeEvent.getNewRouteStatus();

            if (LOG.isDebugEnabled()) {
                LOG.debug(String.format("********************* Status Change: from %s to %s", statusChangeEvent.getOldRouteStatus(), newStatus));
            }

            if (KewApiConstants.ROUTE_HEADER_PROCESSED_CD.equalsIgnoreCase(newStatus)) {
                getInstitutionalProposalVersioningService().updateInstitutionalProposalVersionStatus(this.getInstitutionalProposal(), VersionStatus.ACTIVE);
            }
            if (newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_CANCEL_CD) || newStatus.equalsIgnoreCase(KewApiConstants.ROUTE_HEADER_DISAPPROVED_CD)) {
                getInstitutionalProposalVersioningService().updateInstitutionalProposalVersionStatus(this.getInstitutionalProposal(), VersionStatus.CANCELED);
            }
            return null;
        });
    }
    
    private InstitutionalProposalVersioningService getInstitutionalProposalVersioningService() {
        return KcServiceLocator.getService(InstitutionalProposalVersioningService.class);
    }
    
    @Override
    public void prepareForSave() {
        super.prepareForSave();
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(new Long(0));
        }
    }
    
    public String getDocumentBoNumber() {
        return getInstitutionalProposal().getInstProposalNumber();
    }
    
    /**
     * This method is to check whether rice async routing is ok now.   
     * Close to hack.  called by holdingpageaction
     * Different document type may have different routing set up, so each document type
     * can implement its own isProcessComplete
     * @return
     */
    public boolean isProcessComplete() {
        boolean isComplete = false;
        
        if (getDocumentHeader().hasWorkflowDocument()) {
            String docRouteStatus = getDocumentHeader().getWorkflowDocument().getStatus().getCode();
            if (KewApiConstants.ROUTE_HEADER_FINAL_CD.equals(docRouteStatus)) {
                isComplete = true;
            }
        }
           
        return isComplete;
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return getInstitutionalProposal().getInstitutionalProposalCustomDataList();
    }
    
    @Override
    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_INSITUTIONAL_PROPOSAL);
        qualifiers.put("name", KcKrmsConstants.InstitutionalProposal.INSTITUTIONAL_PROPOSAL_CONTEXT);
    }

    @Override
    public void addFacts(Builder factsBuilder) {
        KcKrmsFactBuilderServiceHelper fbService = KcServiceLocator.getService("institutionalProposalFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }

    @Override
    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getLeadUnitNumber());
    }

    @Override
    public String getDocumentNumberForPermission() {
       return getInstitutionalProposal().getInstProposalNumber();
    }

    @Override
    public String getDocumentKey() {
        return PermissionableKeys.INSTITUTIONAL_PROPOSAL_KEY;
    }

    @Override
    public List<String> getRoleNames() {
        List<String> roleNames = new ArrayList<String>();
        return roleNames;
    }

    @Override
    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_INSITUTIONAL_PROPOSAL;
    }

    public String getLeadUnitNumber() {
        return getInstitutionalProposal().getLeadUnitNumber();
    }

    @Override
    public String getDocumentRoleTypeCode() {
        return RoleConstants.INSTITUTIONAL_PROPOSAL_TYPE;
    }
}
