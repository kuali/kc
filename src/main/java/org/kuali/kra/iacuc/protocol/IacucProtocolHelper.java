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
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.personnel.IacucProtocolPerson;
import org.kuali.kra.iacuc.personnel.IacucProtocolPersonnelService;
import org.kuali.kra.iacuc.personnel.IacucProtocolUnit;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.actions.ProtocolAction;
import org.kuali.kra.protocol.actions.submit.ProtocolSubmission;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.protocol.ProtocolHelper;

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
    protected String getProtocolCreatedProtocolActionTypeCodeHook() {
        return IacucProtocolActionType.IACUC_PROTOCOL_CREATED;
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

    
    
    protected ProtocolAction createProtocolActionHook(Protocol protocol, ProtocolSubmission protocolSubmission) {
        return new IacucProtocolAction(protocol, protocolSubmission, getProtocolCreatedProtocolActionTypeCodeHook());
    }


    

}