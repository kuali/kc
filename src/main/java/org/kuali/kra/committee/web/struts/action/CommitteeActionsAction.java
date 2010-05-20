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
package org.kuali.kra.committee.web.struts.action;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.committee.dao.CommitteeBatchCorrespondenceDao;
import org.kuali.kra.committee.print.CommitteeReportType;
import org.kuali.kra.committee.rule.event.CommitteeActionFilterBatchCorrespondenceHistoryEvent;
import org.kuali.kra.committee.rule.event.CommitteeActionGenerateBatchCorrespondenceEvent;
import org.kuali.kra.committee.rule.event.CommitteeActionPrintCommitteeDocumentEvent;
import org.kuali.kra.committee.service.CommitteePrintingService;
import org.kuali.kra.committee.web.struts.form.CommitteeForm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.printing.Printable;
import org.kuali.kra.printing.print.AbstractPrint;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;



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
        
        return mapping.findForward(Constants.MAPPING_BASIC);
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

        CommitteeForm committeeForm = (CommitteeForm) form;
        String batchCorrespondenceTypeCode = committeeForm.getCommitteeHelper().getCommitteeActionsHelper().getHistoryBatchCorrespondenceTypeCode();
        Date startDate = committeeForm.getCommitteeHelper().getCommitteeActionsHelper().getHistoryStartDate();
        Date endDate = committeeForm.getCommitteeHelper().getCommitteeActionsHelper().getHistoryEndDate();
        
        committeeForm.getCommitteeHelper().getCommitteeActionsHelper().resetBatchCorrespondenceHistory(committeeForm);
        if (applyRules(new CommitteeActionFilterBatchCorrespondenceHistoryEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), 
                batchCorrespondenceTypeCode, startDate, endDate))) {
            committeeForm.getCommitteeHelper().getCommitteeActionsHelper().setBatchCorrespondenceHistory(getCommitteeBatchCorrespondenceDao()
                    .getCommitteeBatchCorrespondence(batchCorrespondenceTypeCode, startDate, endDate));
        }
        
        return mapping.findForward(Constants.MAPPING_BASIC);
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
        ActionForward actionForward = mapping.findForward(Constants.MAPPING_BASIC);
        CommitteeForm committeeForm = (CommitteeForm) form;
        Boolean printRooster = committeeForm.getCommitteeHelper().getCommitteeActionsHelper().getPrintRooster();
        Boolean printFutureScheduledMeeting = committeeForm.getCommitteeHelper().getCommitteeActionsHelper().getPrintFutureScheduledMeeting();
        
        if (applyRules(new CommitteeActionPrintCommitteeDocumentEvent(Constants.EMPTY_STRING, committeeForm.getDocument(), printRooster, printFutureScheduledMeeting))) {
            AbstractPrint printable;
            List<Printable> printableArtifactList = new ArrayList<Printable>();
            if (printRooster) {
                printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.ROSTER);
                printable.setDocument(committeeForm.getCommitteeDocument());
                printableArtifactList.add(printable);
            }
            if (printFutureScheduledMeeting) {
                printable = getCommitteePrintingService().getCommitteePrintable(CommitteeReportType.FUTURE_SCHEDULED_MEETINGS);
                printable.setDocument(committeeForm.getCommitteeDocument());
                printableArtifactList.add(printable);
            }
            AttachmentDataSource dataStream = getCommitteePrintingService().print(printableArtifactList);
            if (dataStream.getContent() != null) {
                streamToResponse(dataStream, response);
                actionForward = null;
            }
        }

        return actionForward;
    }
    
    private CommitteePrintingService getCommitteePrintingService() {
        return KraServiceLocator.getService(CommitteePrintingService.class);
    }

    public CommitteeBatchCorrespondenceDao getCommitteeBatchCorrespondenceDao() {
        return KraServiceLocator.getService(CommitteeBatchCorrespondenceDao.class);
    }

}
