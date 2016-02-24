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
package org.kuali.kra.iacuc.protocol.funding;

import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonnelService;
import org.kuali.kra.iacuc.protocol.IacucProtocolNumberService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonnelService;
import org.kuali.kra.protocol.protocol.funding.impl.ProposalDevelopmentProtocolDocumentServiceImplBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;

/**
 * 
 * This service creates Proposal Development Document from ProtocolBase for users authorized to create proposal. This created
 * proposal is then added to ProtocolBase Funding sources. 
 */
public class IacucProtocolProposalDevelopmentProtocolDocumentServiceImpl 
    extends ProposalDevelopmentProtocolDocumentServiceImplBase<IacucProtocolDocument>
    implements IacucProtocolProposalDevelopmentProtocolDocumentService {
    
    public static final String IACUC_PROTOCOL_CREATED = "IACUC ProtocolBase created";
    public final static String IACUC_PROTOCOL_TYPE_CODE_DEFAULT = "iacuc.protocol.type.code.default";
    public final static String IACUC_PROTOCOL_LAY_STATEMENT1_DEFAULT = "iacuc.protocol.lay.statement1.default";
    private ParameterService parameterService;    
    private IacucProtocolPersonnelService iacucProtocolPersonnelService;
    private IacucProtocolFundingSourceService iacucProtocolFundingSourceService;
    private IacucProtocolNumberService iacucProtocolNumberService;
    
    @Override
    protected IacucProtocolDocument getProtocolDocumentNewInstanceHook(DocumentService documentService) throws WorkflowException
    {
        return (IacucProtocolDocument) documentService.getNewDocument(IacucProtocolDocument.class);
    }
    
    @Override
    protected IacucProtocolNumberService getProtocolNumberServiceHook() {
        return getIacucProtocolNumberService();
    }

    @Override
    protected String getProtocolActionProtocolCreatedCodeHook() {
        return IacucProtocolActionType.IACUC_PROTOCOL_CREATED;
    }

    @Override
    protected IacucProtocolAction getProtocolActionNewInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission,
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
    protected String getCreateProposalPermissionNamespaceHook() {
        return Constants.MODULE_NAMESPACE_IACUC;
    }

    @Override
    protected String getCreateProposalPermissionNameHook() {
        return PermissionConstants.CREATE_IACUC_PROTOCOL;
    }

    @Override
    protected ProtocolPersonBase getProtocolPersonNewInstanceHook() {
        return new IacucProtocolPerson();
    }

    @Override
    protected ProtocolPersonnelService getProtocolPersonnelServiceHook() {
        return getIacucProtocolPersonnelService();
    }

    @Override
    protected IacucProtocolFundingSourceService getProtocolFundingSourceServiceHook() {
        return getIacucProtocolFundingSourceService();
    }

    @Override
    protected String getProtocolTypeCodeHook() {
        String parameter= parameterService.getParameterValueAsString(IacucProtocolDocument.class, IACUC_PROTOCOL_TYPE_CODE_DEFAULT);
        return parameter;
    }

    @Override
    protected void populateProtocolSpecificFieldsHook(ProtocolBase protocol) {
        String parameter= parameterService.getParameterValueAsString(IacucProtocolDocument.class, IACUC_PROTOCOL_LAY_STATEMENT1_DEFAULT);
        IacucProtocol iacucProtocol = (IacucProtocol)protocol;
        iacucProtocol.setLayStatement1(parameter);
    }

    protected ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected IacucProtocolPersonnelService getIacucProtocolPersonnelService() {
        return iacucProtocolPersonnelService;
    }

    public void setIacucProtocolPersonnelService(IacucProtocolPersonnelService iacucProtocolPersonnelService) {
        this.iacucProtocolPersonnelService = iacucProtocolPersonnelService;
    }

    protected IacucProtocolFundingSourceService getIacucProtocolFundingSourceService() {
        if (iacucProtocolFundingSourceService == null) {
            iacucProtocolFundingSourceService = KcServiceLocator.getService(IacucProtocolFundingSourceService.class);
        }
        return iacucProtocolFundingSourceService;
    }

    public void setIacucProtocolFundingSourceService(IacucProtocolFundingSourceService iacucProtocolFundingSourceService) {
        this.iacucProtocolFundingSourceService = iacucProtocolFundingSourceService;
    }

    protected IacucProtocolNumberService getIacucProtocolNumberService() {
        return iacucProtocolNumberService;
    }

    public void setIacucProtocolNumberService(IacucProtocolNumberService iacucProtocolNumberService) {
        this.iacucProtocolNumberService = iacucProtocolNumberService;
    }

    
}
