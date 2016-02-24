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
package org.kuali.kra.irb.protocol.funding.impl;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.personnel.ProtocolPerson;
import org.kuali.kra.irb.protocol.ProtocolNumberService;
import org.kuali.kra.irb.protocol.funding.ProposalDevelopmentProtocolDocumentService;
import org.kuali.kra.irb.protocol.funding.ProtocolFundingSourceService;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.protocol.funding.impl.ProposalDevelopmentProtocolDocumentServiceImplBase;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

/**
 * 
 * This service creates Proposal Development Document from Protocol for users authorized to create proposal. This created
 * proposal is then added to Protocol Funding sources. 
 */
public class ProposalDevelopmentProtocolDocumentServiceImpl 
        extends ProposalDevelopmentProtocolDocumentServiceImplBase<ProtocolDocument> 
        implements ProposalDevelopmentProtocolDocumentService {
    
    private ProtocolNumberService protocolNumberService;
    private ProtocolPersonnelService protocolPersonnelService;
    private ProtocolFundingSourceService protocolFundingSourceService;

    protected static final String PROTOCOL_CREATED = "Protocol created";
    protected static final String DEFAULT_PROTOCOL_TYPE = "1";
    
    @Override
    protected ProtocolDocument getProtocolDocumentNewInstanceHook(DocumentService documentService) throws WorkflowException
    {
        return (ProtocolDocument) documentService.getNewDocument(ProtocolDocument.class);
    }
    
    @Override
    protected ProtocolNumberService getProtocolNumberServiceHook() {
        return getProtocolNumberService();
    }

    @Override
    protected String getProtocolActionProtocolCreatedCodeHook() {
        return ProtocolActionType.PROTOCOL_CREATED;
    }

    @Override
    protected ProtocolAction getProtocolActionNewInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission,
            String protocolActionTypeCode) {
        return new ProtocolAction((Protocol) protocol, (ProtocolSubmission) protocolSubmission, protocolActionTypeCode);
    }

    @Override
    protected String getProtocolAggregatorHook() {
        return RoleConstants.PROTOCOL_AGGREGATOR;
    }

    @Override
    protected String getProtocolApproverHook() {
        return RoleConstants.PROTOCOL_APPROVER;
    }

    @Override
    protected String getProtocolRoleTypeHook() {
        return RoleConstants.PROTOCOL_ROLE_TYPE;
    }

    @Override
    protected String getProtocolNameSpaceHook() {
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }

    @Override
    protected String getProtocolCreatedHook() {
        return PROTOCOL_CREATED;
    }

    @Override
    protected String getSequenceNumberNameHook() {
        return "SEQ_IACUC_PROTOCOL_ID";
    }

    @Override
    protected String getCreateProposalPermissionNamespaceHook() {
        return Constants.MODULE_NAMESPACE_PROTOCOL;
    }

    @Override
    protected String getCreateProposalPermissionNameHook() {
        return PermissionConstants.CREATE_PROTOCOL;
    }

    @Override
    protected ProtocolPersonBase getProtocolPersonNewInstanceHook() {
        return new ProtocolPerson();
    }

    @Override
    protected ProtocolPersonnelService getProtocolPersonnelServiceHook() {
        return getProtocolPersonnelService();
    }

    @Override
    protected ProtocolFundingSourceService getProtocolFundingSourceServiceHook() {
        return getProtocolFundingSourceService(); 
    }

    @Override
    protected String getProtocolTypeCodeHook() {
        return DEFAULT_PROTOCOL_TYPE;
    }

    @Override
    protected void populateProtocolSpecificFieldsHook(ProtocolBase protocol) {

    }

    protected ProtocolNumberService getProtocolNumberService() {
        return protocolNumberService;
    }

    public void setProtocolNumberService(ProtocolNumberService protocolNumberService) {
        this.protocolNumberService = protocolNumberService;
    }

    protected ProtocolPersonnelService getProtocolPersonnelService() {
        return protocolPersonnelService;
    }

    public void setProtocolPersonnelService(ProtocolPersonnelService protocolPersonnelService) {
        this.protocolPersonnelService = protocolPersonnelService;
    }

    protected ProtocolFundingSourceService getProtocolFundingSourceService() {
        if (protocolFundingSourceService == null) {
            protocolFundingSourceService = KcServiceLocator.getService(ProtocolFundingSourceService.class);
        }
        return protocolFundingSourceService;
    }

    public void setProtocolFundingSourceService(ProtocolFundingSourceService protocolFundingSourceService) {
        this.protocolFundingSourceService = protocolFundingSourceService;
    }

}
