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
package org.kuali.kra.iacuc.actions.copy;

import java.util.List;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.IacucProtocolAction;
import org.kuali.kra.iacuc.actions.IacucProtocolActionType;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmission;
import org.kuali.kra.iacuc.customdata.IacucProtocolCustomData;
import org.kuali.kra.iacuc.protocol.IacucProtocolNumberService;
import org.kuali.kra.iacuc.species.IacucProtocolSpecies;
import org.kuali.kra.iacuc.species.exception.IacucProtocolException;
import org.kuali.kra.iacuc.threers.IacucAlternateSearch;
import org.kuali.kra.iacuc.threers.IacucPrinciples;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.copy.ProtocolCopyServiceImpl;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;

public class IacucProtocolCopyServiceImpl extends ProtocolCopyServiceImpl<IacucProtocolDocument> implements IacucProtocolCopyService{

    @Override
    protected Class<? extends ProtocolAction> getProtocolActionBOClassHook() {
        return IacucProtocolAction.class;
    }

    @Override
    protected String getProtocolActionProtocolCreatedCodeHook() {
        return IacucProtocolActionType.IACUC_PROTOCOL_CREATED;
    }

    @Override
    protected String getSequenceNumberNameHook() {
        return "SEQ_IACUC_PROTOCOL_ID";
    }

    @Override
    protected void copyRequiredProperties(IacucProtocolDocument srcDoc, IacucProtocolDocument destDoc) {
        super.copyRequiredProperties(srcDoc, destDoc);
        destDoc.getIacucProtocol().setLayStatement1(srcDoc.getIacucProtocol().getLayStatement1());
        destDoc.getIacucProtocol().setLayStatement2(srcDoc.getIacucProtocol().getLayStatement2());
        destDoc.getIacucProtocol().setProtocolProjectTypeCode(srcDoc.getIacucProtocol().getProtocolProjectTypeCode());
        destDoc.getIacucProtocol().setOverviewTimeline(srcDoc.getIacucProtocol().getOverviewTimeline());
        destDoc.getIacucProtocol().setSpeciesStudyGroupIndicator(srcDoc.getIacucProtocol().getSpeciesStudyGroupIndicator());
        destDoc.getIacucProtocol().setAlternativeSearchIndicator(srcDoc.getIacucProtocol().getAlternativeSearchIndicator());
        destDoc.getIacucProtocol().setScientificJustifIndicator(srcDoc.getIacucProtocol().getScientificJustifIndicator());
    }
    
    @SuppressWarnings("unchecked")
    @Override
    protected void copyProtocolLists(IacucProtocolDocument srcDoc, IacucProtocolDocument destDoc) {
        super.copyProtocolLists(srcDoc, destDoc);
        destDoc.getIacucProtocol().setIacucProtocolSpeciesList((List<IacucProtocolSpecies>) 
                deepCopy(srcDoc.getIacucProtocol().getIacucProtocolSpeciesList()));
        destDoc.getIacucProtocol().setIacucProtocolExceptions((List<IacucProtocolException>) 
                deepCopy(srcDoc.getIacucProtocol().getIacucProtocolExceptions()));
        destDoc.getIacucProtocol().setIacucProtocolCustomDataList((List<IacucProtocolCustomData>) 
                deepCopy(srcDoc.getIacucProtocol().getIacucProtocolCustomDataList()));
        destDoc.getIacucProtocol().setIacucPrinciples((List<IacucPrinciples>) 
                deepCopy(srcDoc.getIacucProtocol().getIacucPrinciples()));
        destDoc.getIacucProtocol().setIacucAlternateSearches((List<IacucAlternateSearch>) 
                deepCopy(srcDoc.getIacucProtocol().getIacucAlternateSearches()));
    }
    
    @Override
    protected IacucProtocolNumberService getProtocolNumberServiceHook() {
        return (IacucProtocolNumberService)KraServiceLocator.getService("iacucProtocolNumberService");
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

}
