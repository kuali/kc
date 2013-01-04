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
package org.kuali.kra.irb.personnel;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.irb.noteattachment.ProtocolAttachmentPersonnel;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.noteattachment.ProtocolAttachmentPersonnelBase;
import org.kuali.kra.protocol.personnel.PersonnelHelperBase;
import org.kuali.kra.protocol.personnel.ProtocolUnitBase;

public class PersonnelHelper extends PersonnelHelperBase {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8864871204975643125L;

    public PersonnelHelper(ProtocolForm form) {
        super(form);
        setNewProtocolPerson(new ProtocolPerson());
    }
    
    
// TODO ********************** commented out during IRB backfit ************************    
//    /**
//     * Each Helper must contain a reference to its document form
//     * so that it can access the actual document.
//     */
//    private ProtocolForm form;
//    
//    private boolean modifyPersonnel;
//    private ProtocolPerson newProtocolPerson;
//    private List<ProtocolUnit> newProtocolPersonUnits;
//    private List<ProtocolAttachmentPersonnel> newProtocolAttachmentPersonnels;
//    private boolean personTrainingSectionRequired;
//    private transient ParameterService parameterService;
//
//    public PersonnelHelper(ProtocolForm form) {
//        setForm(form); 
//        setNewProtocolPerson(new ProtocolPerson());
//        setNewProtocolPersonUnits(new ArrayList<ProtocolUnit>());
//        setNewProtocolAttachmentPersonnels(new ArrayList<ProtocolAttachmentPersonnel>());
//    }    
//    
//    public void prepareView() {
//        initializePermissions(getProtocol());    
//        initializeTrainingSection();
//        getForm().populatePersonEditableFields();
//
//    }
//    
//    private Protocol getProtocol() {
//        ProtocolDocument document = form.getProtocolDocument();
//        if (document == null || document.getProtocol() == null) {
//            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
//        }
//        return document.getProtocol();
//    }
//    
//    public boolean isProtocolFinal() {
//        return form.getDocument().getDocumentHeader().getWorkflowDocument().isFinal();
//    }
//   
//    private void initializePermissions(Protocol protocol) {
//        initializeModifyProtocolPermission(protocol);
//    }

    
    protected void initializeModifyProtocolPermission(ProtocolBase protocol) {
        ProtocolTask task = new ProtocolTask(TaskName.MODIFY_PROTOCOL_PERSONNEL, (Protocol) protocol);
        modifyPersonnel = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);     
    }
    
// TODO ********************** commented out during IRB backfit ************************    
//    private TaskAuthorizationService getTaskAuthorizationService() {
//        return KraServiceLocator.getService(TaskAuthorizationService.class);
//    }
//    
//    private String getUserIdentifier() {
//        return GlobalVariables.getUserSession().getPrincipalId();
//    }
//    
//    public boolean getModifyPersonnel() {
//        return modifyPersonnel;
//    }
//
//    public void setNewProtocolPerson(ProtocolPerson newProtocolPerson) {
//        this.newProtocolPerson = newProtocolPerson;
//    }
//
//    public ProtocolPerson getNewProtocolPerson() {
//        return newProtocolPerson;
//    }
//
//    public List<ProtocolUnit> getNewProtocolPersonUnits() {
//        if (getForm().getProtocolDocument().getProtocol().getProtocolPersons().size() > this.newProtocolPersonUnits.size()) {
//            this.newProtocolPersonUnits.add(this.newProtocolPersonUnits.size(), new ProtocolUnit());
//        }
//        return newProtocolPersonUnits;
//    }
//
//    public void setNewProtocolPersonUnits(List<ProtocolUnit> newProtocolPersonUnits) {
//        this.newProtocolPersonUnits = newProtocolPersonUnits;
//    }
//    
//    public List<ProtocolAttachmentPersonnel> getNewProtocolAttachmentPersonnels() {
//        if (getForm().getProtocolDocument().getProtocol().getProtocolPersons().size() > this.newProtocolAttachmentPersonnels.size()) {
//            this.newProtocolAttachmentPersonnels.add(this.newProtocolAttachmentPersonnels.size(), new ProtocolAttachmentPersonnel());
//        }
//        return newProtocolAttachmentPersonnels;
//    }
//    
//    public void setNewProtocolAttachmentPersonnels(List<ProtocolAttachmentPersonnel> newProtocolAttachmentPersonnels) {
//        this.newProtocolAttachmentPersonnels = newProtocolAttachmentPersonnels;
//    }
//
//    public ProtocolForm getForm() {
//        return form;
//    }
//
//    public void setForm(ProtocolForm form) {
//        this.form = form;
//    }

    protected void initializeTrainingSection() {
        setPersonTrainingSectionRequired(Boolean.parseBoolean(getParameterValue(Constants.PARAMETER_PROTOCOL_PERSON_TRAINING_SECTION)));
    }

// TODO ********************** commented out during IRB backfit ************************    
//    /**
//     * This method is to get parameter value
//     * @return parameter value
//     */
//    private String getParameterValue(String parameterName) {
//        return this.getParameterService().getParameterValueAsString(ProtocolDocument.class, parameterName);        
//    }
//
//    public boolean isPersonTrainingSectionRequired() {
//        return personTrainingSectionRequired;
//    }
//
//    public void setPersonTrainingSectionRequired(boolean personTrainingSectionRequired) {
//        this.personTrainingSectionRequired = personTrainingSectionRequired;
//    }   
//
//    
//    /**
//     * Looks up and returns the ParameterService.
//     * @return the parameter service. 
//     */
//    protected ParameterService getParameterService() {
//        if (this.parameterService == null) {
//            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
//        }
//        return this.parameterService;
//    }
    
    @Override
    protected Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook() {
        return ProtocolDocument.class;
    }

    @Override
    public ProtocolUnitBase createNewProtocolUnitInstanceHook() {
        return new ProtocolUnit();
    }

    @Override
    public ProtocolAttachmentPersonnelBase createNewProtocolAttachmentPersonnelInstanceHook() {
        return new ProtocolAttachmentPersonnel();
    }

}
