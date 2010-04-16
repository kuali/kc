/*
 * Copyright 2006-2010 The Kuali Foundation
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
package org.kuali.kra.committee.web.struts.action;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.infrastructure.Constants;



/**
 * The CommitteeActionsAction corresponds to the Actions tab (web page).  It is
 * responsible for handling all user requests from that tab (web page).
 */
public class CommitteeActionsAction extends CommitteeAction {

    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CommitteeActionsAction.class);

    /**
     * This method is perform the action - Generate Batch Correspondence.
     * Method is called in CommitteeActions.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward generateBatchCorrespondence(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        
        CommitteeForm committeeForm = (CommitteeForm) form;
        String committeeId = committeeForm.getCommitteeDocument().getCommittee().getCommitteeId();
        String batchCorrespondenceTypeCode = committeeForm.getCommitteeHelper().getCommitteeActionsHelper().getGenerateBatchCorrespondenceTypeCode();
        Date startDate = committeeForm.getCommitteeHelper().getCommitteeActionsHelper().getGenerateStartDate();
        Date endDate = committeeForm.getCommitteeHelper().getCommitteeActionsHelper().getGenerateEndDate();
        
        if (applyRules(new CommitteeActionGenerateBatchCorrespondenceEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), 
                batchCorrespondenceTypeCode, startDate, endDate))) {
            System.out.println("GenerateBatchCorrespondence: committeeId:" + committeeId + " batchCorrespondenceTypeCode:" 
                + batchCorrespondenceTypeCode + " startDate:" + startDate + " endDate:" + endDate);
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    /**
     * This method is perform the action - Filter Batch Correspondence History.
     * Method is called in CommitteeActions.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     * @throws Exception
     */
    public ActionForward filterBatchCorrespondenceHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
    /**
     * This method is perform the action - Print Committee Document.
     * Method is called in CommitteeActions.jsp
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return 
     * @throws Exception
     */
    public ActionForward printCommitteeDocument(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        return mapping.findForward(Constants.MAPPING_BASIC );
    }
    
}
