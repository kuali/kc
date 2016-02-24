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
package org.kuali.kra.external.award.web;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.award.AwardForm;
import org.kuali.kra.award.web.struts.action.AwardActionsAction;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.util.GlobalVariables;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
