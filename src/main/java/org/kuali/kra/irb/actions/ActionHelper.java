/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.actions;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.ProtocolForm;
import org.kuali.kra.irb.actions.submit.ProtocolSubmitAction;
import org.kuali.kra.irb.actions.withdraw.ProtocolWithdrawBean;
import org.kuali.kra.irb.auth.ProtocolTask;
import org.kuali.kra.rice.shim.UniversalUser;
import org.kuali.kra.service.TaskAuthorizationService;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * The form helper class for the Protocol Actions tab.
 */
@SuppressWarnings("serial")
public class ActionHelper implements Serializable {

    /**
     * Each Helper must contain a reference to its document form
     * so that it can access the document.
     */
    private ProtocolForm form;
    
    private boolean canSubmitProtocol = false;
    private boolean canSelectCommittee = false;
    private boolean canSelectSchedule = false;
    private boolean canSelectReviewers = false;
    
    private ProtocolSubmitAction protocolSubmitAction;
    private ProtocolWithdrawBean protocolWithdrawBean;
   
    /**
     * Constructs an ActionHelper.
     * @param form the protocol form
     */
    public ActionHelper(ProtocolForm form) {
        this.form = form;
        protocolSubmitAction = new ProtocolSubmitAction(this);
        protocolWithdrawBean = new ProtocolWithdrawBean();
    }
    
    public void prepareView() {
        protocolSubmitAction.prepareView();
        canSubmitProtocol = hasSubmitProtocolPermission();
        canSelectCommittee = getParameterValue(Constants.PARAMETER_PROTOCOL_SELECT_COMMITTEE);
        canSelectSchedule = getParameterValue(Constants.PARAMETER_PROTOCOL_SELECT_SCHEDULE);
        canSelectReviewers = getParameterValue(Constants.PARAMETER_PROTOCOL_SELECT_REVIEWERS);
    }
    
    private boolean getParameterValue(String parameterName) {
        KualiConfigurationService configService = getService(KualiConfigurationService.class);
        Parameter param = configService.getParameterWithoutExceptions(Constants.PARAMETER_MODULE_PROTOCOL, Constants.PARAMETER_COMPONENT_DOCUMENT, parameterName);
        if (param == null) {
            return false;
        }
        String value = param.getParameterValue();        
        return StringUtils.equalsIgnoreCase(value, "true");
    }

    private Protocol getProtocol() {
        ProtocolDocument document = form.getDocument();
        if (document == null || document.getProtocol() == null) {
            throw new IllegalArgumentException("invalid (null) ProtocolDocument in ProtocolForm");
        }
        return document.getProtocol();
    }
    
    private boolean hasSubmitProtocolPermission() {
        ProtocolTask task = new ProtocolTask(TaskName.SUBMIT_PROTOCOL, getProtocol());
        return getTaskAuthorizationService().isAuthorized(getUserName(), task);
    }
    
    private TaskAuthorizationService getTaskAuthorizationService() {
        return KraServiceLocator.getService(TaskAuthorizationService.class);
    }
    
    public ProtocolSubmitAction getProtocolSubmitAction() {
        return protocolSubmitAction;
    }

    public ProtocolForm getProtocolForm() {
        return form;
    }

    public boolean getCanSubmitProtocol() {
        return canSubmitProtocol;
    }
    
    /**
     * Get the userName of the user for the current session.
     * @return the current session's userName
     */
    private String getUserName() {
         UniversalUser user = new UniversalUser(GlobalVariables.getUserSession().getPerson());
         return user.getPersonUserIdentifier();
    }

    public boolean getCanSelectCommittee() {
        return canSelectCommittee;
    }

    public boolean getCanSelectSchedule() {
        return canSelectSchedule;
    }

    public boolean getCanSelectReviewers() {
        return canSelectReviewers;
    }

    public ProtocolWithdrawBean getProtocolWithdrawBean() {
        return protocolWithdrawBean;
    }
}
