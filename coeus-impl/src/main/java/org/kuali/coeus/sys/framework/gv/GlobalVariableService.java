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
package org.kuali.coeus.sys.framework.gv;

import org.kuali.rice.kns.util.MessageList;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.web.form.UifFormManager;

import java.util.Map;
import java.util.concurrent.Callable;

public interface GlobalVariableService {

    UserSession getUserSession();

    void setUserSession(UserSession userSession);

    MessageMap getMessageMap();

    void setMessageMap(MessageMap messageMap);

    @SuppressWarnings("deprecation")
    MessageList getMessageList();

    @SuppressWarnings("deprecation")
    void setMessageList(MessageList messageList);
    
    Map<String, AuditCluster> getAuditErrorMap();
    
    void setAuditErrorMap(Map<String, AuditCluster> auditMap);

    UifFormManager getUifFormManager();

    void setUifFormManager(UifFormManager uifFormManager);

    <T> T doInNewGlobalVariables(Callable<T> callable);

    <T> T doInNewGlobalVariables(UserSession userSession, Callable<T> callable);
}
