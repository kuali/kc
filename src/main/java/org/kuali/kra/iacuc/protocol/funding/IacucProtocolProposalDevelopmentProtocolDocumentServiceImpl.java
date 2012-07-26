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
package org.kuali.kra.iacuc.protocol.funding;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonnelService;
import org.kuali.kra.iacuc.protocol.IacucProtocolNumberService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.personnel.ProtocolPerson;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.protocol.funding.impl.ProposalDevelopmentProtocolDocumentServiceImpl;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

/**
 * 
 * This service creates Proposal Development Document from Protocol for users authorized to create proposal. This created
 * proposal is then added to Protocol Funding sources. 
 */
public class IacucProtocolProposalDevelopmentProtocolDocumentServiceImpl 
    extends ProposalDevelopmentProtocolDocumentServiceImpl 
    implements IacucProtocolProposalDevelopmentProtocolDocumentService {
    
    public static final String IACUC_PROTOCOL_CREATED = "IACUC Protocol created";
    public final static String IACUC_PROTOCOL_TYPE_CODE_DEFAULT = "iacuc.protocol.type.code.default";
    public final static String IACUC_PROTOCOL_LAY_STATEMENT1_DEFAULT = "iacuc.protocol.lay.statement1.default";
    ParameterService parameterService;    
    @Override
    protected IacucProtocolDocument getProtocolDocumentNewInstanceHook(DocumentService documentService) throws WorkflowException
    {
        return (IacucProtocolDocument) documentService.getNewDocument(IacucProtocolDocument.class);
    }
    
    @Override
    protected IacucProtocolNumberService getProtocolNumberServiceHook() {
        return (IacucProtocolNumberService)KraServiceLocator.getService("iacucProtocolNumberService");
    }

    @Override
    protected String getProtocolActionProtocolCreatedCodeHook() {
        return IacucProtocolActionType.IACUC_PROTOCOL_CREATED;
    }

    @Override
    protected IacucProtocolAction getProtocolActionNewInstanceHook(Protocol protocol, ProtocolSubmission protocolSubmission,
            String protocolActionTypeCode) {
        return new IacucProtocolAction((IacucProtocol) protocol, (IacucProtocolSubmission) protocolSubmission, protocolActionTypeCode);
    }

    @Override
    protected String getProtocolAggregatorHook() {
        return RoleConstants.IACUC_PROTOCOL_AGGREGATOR;
    }

    @Override
    protected String getProtocolApproverHook() {
        return RoleConstants.IACUC_PROTOCOL_APPROVER;
    }

    @Override
    protected String getProtocolRoleTypeHook() {
        return RoleConstants.IACUC_ROLE_TYPE;
    }

    @Override
    protected String getProtocolNameSpaceHook() {
        return Constants.MODULE_NAMESPACE_IACUC;
    }

    @Override
    protected String getProtocolCreatedHook() {
        return IACUC_PROTOCOL_CREATED;
    }

    @Override
    protected String getSequenceNumberNameHook() {
        return "SEQ_IACUC_PROTOCOL_ID";
    }

    @Override
    protected ProtocolPerson getProtocolPersonNewInstanceHook() {
        return new IacucProtocolPerson();
    }

    @Override
    protected ProtocolPersonnelService getProtocolPersonnelServiceHook() {
        return (ProtocolPersonnelService)KraServiceLocator.getService(IacucProtocolPersonnelService.class);
    }

    @Override
    protected IacucProtocolFundingSourceService getProtocolFundingSourceServiceHook() {
        return (IacucProtocolFundingSourceService)KraServiceLocator.getService(IacucProtocolFundingSourceService.class);
    }

    @Override
    protected String getProtocolTypeCodeHook() {
        if ( parameterService ==null)
        {
            parameterService = KraServiceLocator.getService(ParameterService.class);
        }
        String parameter= parameterService.getParameterValueAsString(IacucProtocolDocument.class, IACUC_PROTOCOL_TYPE_CODE_DEFAULT);
        return parameter;
    }

    @Override
    protected void populateProtocolSpecificFieldsHook(Protocol protocol) {
        if ( parameterService ==null)
        {
            parameterService = KraServiceLocator.getService(ParameterService.class);
        }
        String parameter= parameterService.getParameterValueAsString(IacucProtocolDocument.class, IACUC_PROTOCOL_LAY_STATEMENT1_DEFAULT);
        IacucProtocol iacucProtocol = (IacucProtocol)protocol;
        iacucProtocol.setLayStatement1(parameter);
    }

    
}