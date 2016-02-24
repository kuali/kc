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
package org.kuali.coeus.sys.impl.gv;

import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.util.MessageList;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.web.form.UifFormManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

@Component("globalVariableService")
public class GlobalVariableServiceImpl implements GlobalVariableService {

    @Override
    public UserSession getUserSession() {
        return GlobalVariables.getUserSession();
    }

    @Override
    public void setUserSession(UserSession userSession) {
        GlobalVariables.setUserSession(userSession);
    }

    @Override
    public MessageMap getMessageMap() {
        return GlobalVariables.getMessageMap();
    }

    @Override
    public void setMessageMap(MessageMap messageMap) {
        GlobalVariables.setMessageMap(messageMap);
    }

    @Override
    public MessageList getMessageList() {
        return KNSGlobalVariables.getMessageList();
    }

    @Override
    public void setMessageList(MessageList messageList) {
        KNSGlobalVariables.setMessageList(messageList);
    }

    @Override
    public Map<String, AuditCluster> getAuditErrorMap() {
    	return GlobalVariables.getAuditErrorMap();
    }
    
    @Override
    public void setAuditErrorMap(Map<String, AuditCluster> auditErrorMap) {
    	GlobalVariables.setAuditErrorMap(new HashMap<>(auditErrorMap));
    }

    @Override
    public UifFormManager getUifFormManager() {
        return GlobalVariables.getUifFormManager();
    }

    @Override
    public void setUifFormManager(UifFormManager uifFormManager) {
        GlobalVariables.setUifFormManager(uifFormManager);
    }

    @Override
    public <T> T doInNewGlobalVariables(Callable<T> callable) {
        try {
            return GlobalVariables.doInNewGlobalVariables(callable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T doInNewGlobalVariables(UserSession userSession, Callable<T> callable) {
        try {
            return GlobalVariables.doInNewGlobalVariables(userSession, callable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
