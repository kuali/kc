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
package org.kuali.kra.iacuc.threers;

import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolForm;
import org.kuali.kra.iacuc.auth.IacucProtocolTask;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.protocol.auth.ProtocolTaskBase;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class IacucAlternateSearchHelper implements Serializable {

    protected IacucProtocolForm form;
    private IacucAlternateSearch newAlternateSearch;
    private List<String> newDatabases;

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
        final ProtocolTaskBase task = new IacucProtocolTask(TaskName.MODIFY_IACUC_PROTOCOL_THREE_RS, (IacucProtocol) form.getProtocolDocument().getProtocol());
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }

    private TaskAuthorizationService getTaskAuthorizationService() {
        return KcServiceLocator.getService(TaskAuthorizationService.class);
    }

}
