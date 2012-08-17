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
package org.kuali.kra.iacuc.protocol;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonnelService;
import org.kuali.kra.iacuc.personnel.IacucProtocolUnit;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSource;
import org.kuali.kra.iacuc.protocol.funding.IacucProtocolFundingSourceService;
import org.kuali.kra.iacuc.protocol.location.IacucProtocolLocation;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.protocol.ProtocolHelper;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSource;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceService;
import org.kuali.kra.protocol.protocol.location.ProtocolLocation;

public class IacucProtocolHelper extends ProtocolHelper {
      
    private static final long serialVersionUID = -71094343536405026L;
 
    
    public IacucProtocolHelper(IacucProtocolForm form) {
        super(form);
    }


    @Override
    // implementation of hook method
    protected IacucProtocolPersonnelService getProtocolPersonnelService() {
        return (IacucProtocolPersonnelService)KraServiceLocator.getService("iacucProtocolPersonnelService");
    }


    @Override
    // implementation of hook method
    protected IacucProtocolNumberService getProtocolNumberService() {
        // TODO Auto-generated method stub
        return (IacucProtocolNumberService)KraServiceLocator.getService("iacucProtocolNumberService");
    }


    @Override
    // implementation of hook method
    protected IacucProtocolUnit createNewProtocolUnitInstanceHook() {
        return new IacucProtocolUnit();
    }


    @Override
    // implementation of hook method
    protected IacucProtocolPerson createNewProtocolPersonInstanceHook() {
        return new IacucProtocolPerson();
    }


    @Override
    protected Class<? extends ProtocolDocument> getProtocolDocumentClassHook() {
        return IacucProtocolDocument.class;
    }


    @Override
    protected ProtocolTask getNewInstanceModifyProtocolGeneralInfoTaskHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_GENERAL_INFO, (IacucProtocol)protocol);
    }


    @Override
    protected ProtocolTask getNewInstanceModifyProtocolResearchAreasTaskHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_RESEARCH_AREAS, (IacucProtocol)protocol);
    }
    
    
    @Override
    protected ProtocolTask getNewInstanceModifyProtocolTaskHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL, (IacucProtocol) protocol);
    }


    @Override
    protected ProtocolTask getNewInstanceModifyProtocolReferencesTaskHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_REFERENCES, (IacucProtocol) protocol);
    }
    
    
    @Override
    protected ProtocolTask getNewInstanceModifyProtocolOrganizationsTaskHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_ORGANIZATIONS, (IacucProtocol) protocol);
    }

    @Override
    protected ProtocolTask getNewInstanceCreateProposalDevelopmentTaskHook(Protocol protocol)
    {
        return new IacucProtocolTask(IacucProtocolTask.CREATE_PROPOSAL_FOR_IACUC_PROTOCOL, (IacucProtocol) protocol);
    }

    @Override
    protected boolean getProtocolProposalDevelopmentLinkingHook()
    {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_IACUC, 
                Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER);
    }
    
    protected ProtocolAction createProtocolCreatedTypeProtocolActionInstanceHook(Protocol protocol) {
        return new IacucProtocolAction((IacucProtocol) protocol, null, IacucProtocolActionType.IACUC_PROTOCOL_CREATED);
    }


    @Override
    protected ProtocolLocation getNewProtocolLocationInstanceHook() {
        return new IacucProtocolLocation();
    }


    @Override
    protected String getReferenceID1ParameterNameHook() {
        return Constants.PARAMETER_MODULE_IACUC_PROTOCOL_REFERENCEID1;
    }


    @Override
    protected String getReferenceID2ParameterNameHook() {
        return Constants.PARAMETER_MODULE_IACUC_PROTOCOL_REFERENCEID2;
    }


    @Override
    protected ProtocolTask getNewInstanceModifyProtocolFundingSourceTaskHook(Protocol protocol) {
        return new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_FUNDING_SOURCE, (IacucProtocol) protocol);
    }


    @Override
    protected ProtocolFundingSource getNewProtocolFundingSourceInstanceHook() {
        return new IacucProtocolFundingSource();
    }


    @Override
    protected Class<? extends ProtocolFundingSourceService> getProtocolFundingSourceServiceClassHook() {
        return IacucProtocolFundingSourceService.class;
    }


    @Override
    public ProtocolTask getNewInstanceModifyProtocolBillableTaskNewHook(Protocol protocol) {
        return new IacucProtocolTask(getModifyProtocolBillableTask(), (IacucProtocol) protocol);
    }

    public String getModifyProtocolBillableTask() {
        return TaskName.MODIFY_IACUC_PROTOCOL_BILLABLE;
    }


    @Override
    protected String getBillableParameterHook() {
        return Constants.PARAMETER_MODULE_IACUC_PROTOCOL_BILLABLE;
    }

    /*
     * here as a placeholder for now, for when we must call specific IACUC prepareView().
     */
    @Override
    public void prepareView() {
        super.prepareView();    
    }

    /**
     * This method initializes permission related to form.
     * Note: Billable permission is only set if displayBillable is true.
     * Reason: For Institution who does not bill.  
     * @param protocol
     */
    @Override
    protected void initializePermissions(Protocol protocol) {
        IacucProtocol iacucProtocol = (IacucProtocol)protocol;
        super.initializePermissions(protocol); 
    }

}