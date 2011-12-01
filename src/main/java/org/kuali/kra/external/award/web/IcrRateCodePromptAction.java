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
package org.kuali.kra.external.award.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.web.struts.action.AwardActionsAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * For Account Creation, if there is more than one possible ICR Rate Code mapping, prompt the user to select the correct one. 
 */
public class IcrRateCodePromptAction extends AwardActionsAction {
    
    private static final String NO_OPTION_SELECTED_ERROR_KEY = "error.award.createAccount.noIcrSelected";
    
    /**
     * Proceed with account creation using the selected ICR Rate Code.
     */
    public ActionForward proceed(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        AwardForm awardForm = (AwardForm) form;
        if (awardForm.getAccountCreationHelper().getSelectedIcrRateCode() == null) {
            GlobalVariables.getMessageMap().putError("accountCreationHelper.selectedIcrRateCode", NO_OPTION_SELECTED_ERROR_KEY, "");
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            awardForm.getAwardDocument().getAward().setIcrRateCode(awardForm.getAccountCreationHelper().getSelectedIcrRateCode());
        }
        return super.createAccount(mapping, form, request, response);
    }
    
    /**
     * Return to Award Actions and cancel the account creation.
     */
    public ActionForward cancel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_AWARD_ACTIONS_PAGE);
    }

}
