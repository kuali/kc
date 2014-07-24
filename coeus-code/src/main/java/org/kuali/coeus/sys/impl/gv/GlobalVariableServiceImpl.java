package org.kuali.coeus.sys.impl.gv;

import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.rice.krad.UserSession;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;
import org.kuali.rice.krad.web.form.UifFormManager;
import org.springframework.stereotype.Component;

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
