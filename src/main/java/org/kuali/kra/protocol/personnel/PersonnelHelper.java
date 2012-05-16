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
package org.kuali.kra.protocol.personnel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.Protocol;
import org.kuali.kra.protocol.ProtocolDocument;
import org.kuali.kra.protocol.ProtocolForm;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

public abstract class PersonnelHelper implements Serializable {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -411537473714173061L;

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the actual document.
     */
    protected ProtocolForm form;
    
    protected boolean modifyPersonnel;
    protected ProtocolPerson newProtocolPerson;
    protected List<ProtocolUnit> newProtocolPersonUnits;
    protected List<ProtocolAttachmentPersonnel> newProtocolAttachmentPersonnels;
    protected boolean personTrainingSectionRequired;
    protected transient ParameterService parameterService;

    public PersonnelHelper(ProtocolForm form) {
        setForm(form); 
        setNewProtocolPersonUnits(new ArrayList<ProtocolUnit>());
        setNewProtocolAttachmentPersonnels(new ArrayList<ProtocolAttachmentPersonnel>());
    }    
    
    public void prepareView() {
        initializePermissions(getProtocol());    
        initializeTrainingSection();
        getForm().populatePersonEditableFields();

    }
    
    protected Protocol getProtocol() {
        ProtocolDocument document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }
    
    public boolean isProtocolFinal() {
        return form.getDocument().getDocumentHeader().getWorkflowDocument().isFinal();
    }
   
    protected void initializePermissions(Protocol protocol) {
        initializeModifyProtocolPermission(protocol);
    }

    protected abstract void initializeModifyProtocolPermission(Protocol protocol);
// TODO *********commented the code below during IACUC refactoring*********      
    /*
    private void initializeModifyProtocolPermission(Protocol protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_PERSONNEL, protocol);
        modifyPersonnel = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);     
    }
    */
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    public boolean getModifyPersonnel() {
        return modifyPersonnel;
    }

    public void setNewProtocolPerson(ProtocolPerson newProtocolPerson) {
        this.newProtocolPerson = newProtocolPerson;
    }

    public ProtocolPerson getNewProtocolPerson() {
        return newProtocolPerson;
    }

    public List<ProtocolUnit> getNewProtocolPersonUnits() {
        if (getForm().getProtocolDocument().getProtocol().getProtocolPersons().size() > this.newProtocolPersonUnits.size()) {
            this.newProtocolPersonUnits.add(this.newProtocolPersonUnits.size(), createNewProtocolUnitInstanceHook());
        }
        return newProtocolPersonUnits;
    }

    public void setNewProtocolPersonUnits(List<ProtocolUnit> newProtocolPersonUnits) {
        this.newProtocolPersonUnits = newProtocolPersonUnits;
    }
    
    public List<ProtocolAttachmentPersonnel> getNewProtocolAttachmentPersonnels() {
        if (getForm().getProtocolDocument().getProtocol().getProtocolPersons().size() > this.newProtocolAttachmentPersonnels.size()) {
            this.newProtocolAttachmentPersonnels.add(this.newProtocolAttachmentPersonnels.size(), createNewProtocolAttachmentPersonnelInstanceHook());
        }
        return newProtocolAttachmentPersonnels;
    }
    
    public void setNewProtocolAttachmentPersonnels(List<ProtocolAttachmentPersonnel> newProtocolAttachmentPersonnels) {
        this.newProtocolAttachmentPersonnels = newProtocolAttachmentPersonnels;
    }

    public ProtocolForm getForm() {
        return form;
    }

    public void setForm(ProtocolForm form) {
        this.form = form;
    }

    protected abstract void initializeTrainingSection();
// TODO *********commented the code below during IACUC refactoring*********      
    /*
    private void initializeTrainingSection() {
        setPersonTrainingSectionRequired(Boolean.parseBoolean(getParameterValue(Constants.PARAMETER_PROTOCOL_PERSON_TRAINING_SECTION)));
    }
    */

    /**
     * This method is to get parameter value
     * @return parameter value
     */
    protected String getParameterValue(String parameterName) {
        return this.getParameterService().getParameterValueAsString(ProtocolDocument.class, parameterName);        
    }

    public boolean isPersonTrainingSectionRequired() {
        return personTrainingSectionRequired;
    }

    public void setPersonTrainingSectionRequired(boolean personTrainingSectionRequired) {
        this.personTrainingSectionRequired = personTrainingSectionRequired;
    }   

    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    /**
     * 
     * This method returns the appropriate implementation of protocol unit
     * @return
     */
    public abstract ProtocolUnit createNewProtocolUnitInstanceHook();
    public abstract ProtocolAttachmentPersonnel createNewProtocolAttachmentPersonnelInstanceHook();
}
