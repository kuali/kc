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
