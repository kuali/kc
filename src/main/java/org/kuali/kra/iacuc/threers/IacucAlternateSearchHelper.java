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
package org.kuali.kra.iacuc.threers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.auth.ProtocolTask;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.krad.util.GlobalVariables;

public class IacucAlternateSearchHelper implements Serializable {

    protected IacucProtocolForm form;
    private IacucAlternateSearch newAlternateSearch;
    private List<String> newDatabases;
    
    //TODO: Tie in with authorizer
    private boolean modifyPermissions = true;

    public IacucAlternateSearchHelper(IacucProtocolForm form) {
        setForm(form);
        newAlternateSearch = new IacucAlternateSearch();
        newDatabases = new ArrayList<String>();
    }
    
    public IacucAlternateSearch getNewAlternateSearch() {
        return newAlternateSearch;
    }

    public void setNewAlternateSearch(IacucAlternateSearch newAlternateSearch) {
        this.newAlternateSearch = newAlternateSearch;
    }
    
    public void prepareView() {
//        newAlternateSearch = new IacucAlternateSearch();
//        newDatabases = new ArrayList<String>();
    }
    
    public IacucProtocolForm getForm() {
        return form;
    }

    public void setForm(IacucProtocolForm form) {
        this.form = form;
    }

    public List<String> getNewDatabases() {
        return newDatabases;
    }

    public void setNewDatabases(List<String> newDatabases) {
        this.newDatabases = newDatabases;
    }

    public boolean isModifyPermissions() {
        final ProtocolTask task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_THREE_RS, (IacucProtocol) form.getProtocolDocument().getProtocol());
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }

    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }

}
