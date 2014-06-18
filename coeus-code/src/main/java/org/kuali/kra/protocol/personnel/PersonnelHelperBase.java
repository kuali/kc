/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.sys.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.ProtocolFormBase;
import org.kuali.coeus.common.protocol.framework.attachment.ProtocolAttachmentPersonnelBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class PersonnelHelperBase implements Serializable {
    

    private static final long serialVersionUID = -411537473714173061L;

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the actual document.
     */
    protected ProtocolFormBase form;
    
    protected boolean modifyPersonnel;
    protected ProtocolPersonBase newProtocolPerson;
    protected List<ProtocolUnitBase> newProtocolPersonUnits;
    protected List<ProtocolAttachmentPersonnelBase> newProtocolAttachmentPersonnels;
    protected boolean personTrainingSectionRequired;
    protected transient ParameterService parameterService;

    public PersonnelHelperBase(ProtocolFormBase form) {
        setForm(form); 
        setNewProtocolPersonUnits(new ArrayList<ProtocolUnitBase>());
        setNewProtocolAttachmentPersonnels(new ArrayList<ProtocolAttachmentPersonnelBase>());
    }    
    
    public void prepareView() {
        initializePermissions(getProtocol());    
        initializeTrainingSection();
        getForm().populatePersonEditableFields();

    }
    
    protected ProtocolBase getProtocol() {
        ProtocolDocumentBase document = form.getProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocumentBase in ProtocolFormBase");
        }
        return document.getProtocol();
    }
    
    public boolean isProtocolFinal() {
        return form.getDocument().getDocumentHeader().getWorkflowDocument().isFinal();
    }
   
    protected void initializePermissions(ProtocolBase protocol) {
        initializeModifyProtocolPermission(protocol);
    }

    protected abstract void initializeModifyProtocolPermission(ProtocolBase protocol);
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }
    
    public boolean getModifyPersonnel() {
        return modifyPersonnel;
    }

    public void setNewProtocolPerson(ProtocolPersonBase newProtocolPerson) {
        this.newProtocolPerson = newProtocolPerson;
    }

    public ProtocolPersonBase getNewProtocolPerson() {
        return newProtocolPerson;
    }

    public List<ProtocolUnitBase> getNewProtocolPersonUnits() {
        if (getForm().getProtocolDocument().getProtocol().getProtocolPersons().size() > this.newProtocolPersonUnits.size()) {
            this.newProtocolPersonUnits.add(this.newProtocolPersonUnits.size(), createNewProtocolUnitInstanceHook());
        }
        return newProtocolPersonUnits;
    }

    public void setNewProtocolPersonUnits(List<ProtocolUnitBase> newProtocolPersonUnits) {
        this.newProtocolPersonUnits = newProtocolPersonUnits;
    }
    
    public List<ProtocolAttachmentPersonnelBase> getNewProtocolAttachmentPersonnels() {
        if (getForm().getProtocolDocument().getProtocol().getProtocolPersons().size() > this.newProtocolAttachmentPersonnels.size()) {
            this.newProtocolAttachmentPersonnels.add(this.newProtocolAttachmentPersonnels.size(), createNewProtocolAttachmentPersonnelInstanceHook());
        }
        return newProtocolAttachmentPersonnels;
    }
    
    public void setNewProtocolAttachmentPersonnels(List<ProtocolAttachmentPersonnelBase> newProtocolAttachmentPersonnels) {
        this.newProtocolAttachmentPersonnels = newProtocolAttachmentPersonnels;
    }

    public ProtocolFormBase getForm() {
        return form;
    }

    public void setForm(ProtocolFormBase form) {
        this.form = form;
    }

    protected abstract void initializeTrainingSection();

    /**
     * This method is to get parameter value
     * @return parameter value
     */
    protected String getParameterValue(String parameterName) {
        return this.getParameterService().getParameterValueAsString(getProtocolDocumentBOClassHook(), parameterName);        
    }

    protected abstract Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook();
    

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
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
    
    /**
     * 
     * This method returns the appropriate implementation of protocol unit
     * @return
     */
    public abstract ProtocolUnitBase createNewProtocolUnitInstanceHook();
    public abstract ProtocolAttachmentPersonnelBase createNewProtocolAttachmentPersonnelInstanceHook();
}
