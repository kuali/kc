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
package org.kuali.kra.iacuc.species;

import java.io.Serializable;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.util.GlobalVariables;

public class IacucProtocolSpeciesHelper implements Serializable{

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2090976351003068814L;

    protected IacucProtocolForm form;
    
    protected boolean modifyProtocolSpecies = true;
    protected IacucProtocolSpecies newIacucProtocolSpecies;

    public IacucProtocolSpeciesHelper(IacucProtocolForm form) {
        setForm(form); 
        setNewIacucProtocolSpecies(new IacucProtocolSpecies());
    }    
    
    public void prepareView() {
        //getForm().populateEditableFields();
        initializePermission(getProtocol());

    }

    protected IacucProtocol getProtocol() {
        IacucProtocolDocument document = form.getIacucProtocolDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) Iacuc ProtocolDocument in ProtocolForm");
        }
        return document.getIacucProtocol();
    }
    
    public IacucProtocolForm getForm() {
        return form;
    }

    public void setForm(IacucProtocolForm form) {
        this.form = form;
    }

    public boolean isModifyProtocolSpecies() {
        return modifyProtocolSpecies;
    }

    public void setModifyProtocolSpecies(boolean modifyProtocolSpecies) {
        this.modifyProtocolSpecies = modifyProtocolSpecies;
    }

    public IacucProtocolSpecies getNewIacucProtocolSpecies() {
        return newIacucProtocolSpecies;
    }

    public void setNewIacucProtocolSpecies(IacucProtocolSpecies newIacucProtocolSpecies) {
        this.newIacucProtocolSpecies = newIacucProtocolSpecies;
    }

    protected void initializePermission(IacucProtocol protocol) {
        //IacucProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_PROTOCOL_SPECIES, protocol);
        //modifyProtocolSpecies = getTaskAuthorizationService().isAuthorized(getUserIdentifier(), task);
        modifyProtocolSpecies = true;
    }
    
    protected TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

    protected String getUserIdentifier() {
        return GlobalVariables.getUserSession().getPrincipalId();
    }

}
