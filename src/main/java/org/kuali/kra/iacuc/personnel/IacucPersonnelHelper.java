/*
 * Copyright 2005-2013 The Kuali Foundation
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
package org.kuali.kra.iacuc.personnel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.iacuc.noteattachment.IacucProtocolAttachmentPersonnel;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.protocol.personnel.PersonnelHelperBase;
import org.kuali.kra.protocol.personnel.ProtocolPersonBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

public class IacucPersonnelHelper extends PersonnelHelperBase implements Serializable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7184772967685221846L;


    public IacucPersonnelHelper(ProtocolFormBase form) {
        super(form);
        setNewProtocolPerson(new IacucProtocolPerson());
    }    
      

    protected void initializeModifyProtocolPermission(ProtocolBase protocol) {
        ProtocolTaskBase task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_PERSONNEL, (IacucProtocol) protocol);
        modifyPersonnel = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);     
    }


    protected void initializeTrainingSection() {
        setPersonTrainingSectionRequired(Boolean.parseBoolean(getParameterValue(Constants.PARAMETER_IACUC_PROTOCOL_PERSON_TRAINING_SECTION)));
    }


    @Override
    public ProtocolUnitBase createNewProtocolUnitInstanceHook() {
        return new IacucProtocolUnit();
    }


    @Override
    public ProtocolAttachmentPersonnelBase createNewProtocolAttachmentPersonnelInstanceHook() {
        return new IacucProtocolAttachmentPersonnel();
    }


    @Override
    protected Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook() {
        return IacucProtocolDocument.class;
    }


}