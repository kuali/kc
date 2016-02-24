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
package org.kuali.kra.irb.actions.copy;

import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.actions.ProtocolAction;
import org.kuali.kra.irb.actions.ProtocolActionType;
import org.kuali.kra.irb.actions.submit.ProtocolSubmission;
import org.kuali.kra.irb.protocol.ProtocolNumberService;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.actions.ProtocolActionBase;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyServiceImplBase;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmissionBase;

/**
 * The Protocol Copy Service creates a new Protocol Document
 * based upon a current document.
 * 
 * The service uses the following steps in order to copy a protocol:
 * <ol>
 * <li>The Document Service is used to create a new Protocol
 *     Document.  By having a new document, its initiator and timestamp
 *     are set correctly and all workflow information is in its initial
 *     state, e.g.  there are no adhoc routes.
 * </li>
 * <li>The Document Overview, Required, and Additional properties 
 *     are copied from the original protocol to the new one.
 * </li>
 * <li>The new protocol document is saved to the database so that we
 *     can obtain its ProtocolId and ProtocolNumber.
 * </li>
 * <li>The list properties are moved from the original protocol to
 *     the new protocol and their primary keys are initialized along with
 *     their values for ProtocolId and ProtocolNumber.
 * </li>
 * <li>The new document is saved a second time to the database.
 * </li>
 * </ul>
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProtocolCopyServiceImpl extends ProtocolCopyServiceImplBase<ProtocolDocument> implements ProtocolCopyService {
    
    private ProtocolNumberService protocolNumberService;

    @Override
    protected Class<? extends ProtocolActionBase> getProtocolActionBOClassHook() {
        return ProtocolAction.class;
    }

    @Override
    protected String getProtocolActionProtocolCreatedCodeHook() {
        return ProtocolActionType.PROTOCOL_CREATED;
    }

    @Override
    protected String getSequenceNumberNameHook() {
        return "SEQ_PROTOCOL_ID";
    }

    @Override
    protected ProtocolNumberService getProtocolNumberServiceHook() {
        return protocolNumberService;
    }

    @Override
    protected ProtocolActionBase getProtocolActionNewInstanceHook(ProtocolBase protocol, ProtocolSubmissionBase protocolSubmission,
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
    
    /**
     * Set the Protocol Number Service
     * 
     * @param protocolNumberService the Protocol Number Service
     */
    public void setProtocolNumberService(ProtocolNumberService protocolNumberService) {
        this.protocolNumberService = protocolNumberService;
    }
}
