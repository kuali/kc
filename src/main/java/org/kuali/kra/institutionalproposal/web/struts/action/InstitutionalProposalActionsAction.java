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
package org.kuali.kra.institutionalproposal.web.struts.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.fundedawards.FundedAwardsBean;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.notification.InstitutionalProposalNotificationContext;
import org.kuali.kra.institutionalproposal.notification.InstitutionalProposalNotificationRenderer;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPrintingService;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.printing.service.CurrentAndPendingReportService;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;

/**
 * This class...
 */
public class InstitutionalProposalActionsAction extends InstitutionalProposalAction implements AuditModeAction {
    private static final int ERROR = 2;
    private static final int OK = 0;
    private static final int WARNING = 1;
    
    private static final String CONFIRM_UNLOCK_SELECTED = "confirmUnlockSelected";
    private static final String CONFIRM_UNLOCK_SELECTED_KEY = "confirmUnlockSelectedKey";
    private static final String ERROR_SELECTING_FUNDING_PROPS = "error.fundingproposal.unlockNoSelection";
    /** {@inheritDoc} */
	public ActionForward activate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return new AuditActionHelper().setAuditMode(mapping,
				(InstitutionalProposalForm) form, true);
	}

	/** {@inheritDoc} */
	public ActionForward deactivate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return new AuditActionHelper().setAuditMode(mapping,
				(InstitutionalProposalForm) form, false);
	}
    
    /** {@inheritDoc} */
    public ActionForward unlockSelected(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        InstitutionalProposalForm iForm = (InstitutionalProposalForm) form;
        if (iForm.getSelectedAwardFundingProposals() == null || iForm.getSelectedAwardFundingProposals().length == 0) {
            GlobalVariables.getMessageMap().putErrorWithoutFullErrorPath("selectedAwardFundingProposals", ERROR_SELECTING_FUNDING_PROPS);
            return mapping.findForward(Constants.MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE);
        } else {
            return confirm(buildUnlockSelectedConfirmationQuestion(mapping, form, request, response), CONFIRM_UNLOCK_SELECTED, "");
        }
    }
    
    /** {@inheritDoc} */
    public ActionForward confirmUnlockSelected(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        new FundedAwardsBean((InstitutionalProposalForm) form).removeUnlockedAwards();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /** {@inheritDoc} */
    public ActionForward selectAllFundedAwards(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        new FundedAwardsBean((InstitutionalProposalForm) form).selectAllFundedAwards();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /** {@inheritDoc} */
    public ActionForward deselectAllFundedAwards(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
        throws Exception {
        ((InstitutionalProposalForm) form).setSelectedAwardFundingProposals(new String[0]);
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    /**
     * Prepare proposal summary report.
     * {@inheritDoc}
     */
    public ActionForward printProposalSummary(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        InstitutionalProposalForm ipForm = (InstitutionalProposalForm) form;
        InstitutionalProposalPrintingService ipPrintingService = KraServiceLocator.getService(InstitutionalProposalPrintingService.class);
        AttachmentDataSource dataStream = ipPrintingService.printInstitutionalProposalReport(
                ipForm.getInstitutionalProposalDocument().getInstitutionalProposal(), 
                InstitutionalProposalPrintType.INSTITUTIONAL_PROPOSAL_REPORT.getInstitutionalProposalPrintType(), 
                new HashMap<String, Object>());
        streamToResponse(dataStream, response);
        return null;
    }
    
    /**
	 * Prepare current report (i.e. Awards that selected person is on)
	 * {@inheritDoc}
	 */
	public ActionForward prepareCurrentReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReportHelperBean helper = ((ReportHelperBeanContainer) form)
				.getReportHelperBean();
		request.setAttribute(CurrentAndPendingReportService.CURRENT_REPORT_ROWS_KEY, helper
				.prepareCurrentReport());
		request.setAttribute(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY, helper
				.getTargetPersonName());
		return mapping.findForward(Constants.MAPPING_BASIC);
	}

	/**
	 * Prepare pending report (i.e. InstitutionalProposals that selected person
	 * is on) {@inheritDoc}
	 */
	public ActionForward preparePendingReport(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ReportHelperBean helper = ((ReportHelperBeanContainer) form)
				.getReportHelperBean();
		request.setAttribute(CurrentAndPendingReportService.PENDING_REPORT_ROWS_KEY, helper
				.preparePendingReport());
		request.setAttribute(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY, helper
				.getTargetPersonName());
		return mapping.findForward(Constants.MAPPING_BASIC);
	}

    /**
     * Prepare current report (i.e. Awards that selected person is on)
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward printCurrentReportPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CurrentAndPendingReportService currentAndPendingReportService = KraServiceLocator
                .getService(CurrentAndPendingReportService.class);
        ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(CurrentAndPendingReportService.PERSON_ID_KEY, helper.getPersonId());
        reportParameters.put(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        AttachmentDataSource dataStream = currentAndPendingReportService.printCurrentAndPendingSupportReport(
                CurrentAndPendingReportService.CURRENT_REPORT_TYPE, reportParameters);
        streamToResponse(dataStream.getContent(), dataStream.getFileName(), null, response);
        return null;
    }

    /**
     * Prepare pending report (i.e. InstitutionalProposals that selected person is on) {@inheritDoc}
     */
    public ActionForward printPendingReportPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CurrentAndPendingReportService currentAndPendingReportService = KraServiceLocator
                .getService(CurrentAndPendingReportService.class);
        ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(CurrentAndPendingReportService.PERSON_ID_KEY, helper.getPersonId());
        reportParameters.put(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        AttachmentDataSource dataStream = currentAndPendingReportService.printCurrentAndPendingSupportReport(
                CurrentAndPendingReportService.PENDING_REPORT_TYPE, reportParameters);
        streamToResponse(dataStream.getContent(), dataStream.getFileName(), null, response);
        return null;
    }

    @Override
    public ActionForward approve(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        InstitutionalProposalForm proposalForm = (InstitutionalProposalForm) form;

        ActionForward forward = super.approve(mapping, form, request, response);
        
        String routeHeaderId = proposalForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE, "InstitutionalProposalDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation);
    }

    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm proposalForm = (InstitutionalProposalForm) form;
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);
        
        proposalForm.setAuditActivated(true);
        if (submissionStatus(proposalForm.getInstitutionalProposalDocument()) == ERROR) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            forward = super.blanketApprove(mapping, form, request, response);
        }
        
        String routeHeaderId = proposalForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE, "InstitutionalProposalDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation);
    }
	
    
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        
        InstitutionalProposalForm proposalForm = (InstitutionalProposalForm) form;
        
        ActionForward forward = mapping.findForward(Constants.MAPPING_BASIC);

        proposalForm.setAuditActivated(true);
        if (submissionStatus(proposalForm.getInstitutionalProposalDocument()) == ERROR) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            forward = super.route(mapping, form, request, response);
        }
        
        String routeHeaderId = proposalForm.getDocument().getDocumentNumber();
        String returnLocation = buildActionUrl(routeHeaderId, Constants.MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE, "InstitutionalProposalDocument");
        
        ActionForward basicForward = mapping.findForward(KRADConstants.MAPPING_PORTAL);
        ActionForward holdingPageForward = mapping.findForward(Constants.MAPPING_HOLDING_PAGE);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation);
    }

    private int submissionStatus(InstitutionalProposalDocument institutionalProposalDocument) {
        int state = OK;
        boolean auditPassed = new AuditActionHelper().auditUnconditionally(institutionalProposalDocument);
        if (!auditPassed) {
            state = WARNING;
            for (Iterator iter = KNSGlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();) {
                AuditCluster auditCluster = (AuditCluster)KNSGlobalVariables.getAuditErrorMap().get(iter.next());
                if (!StringUtils.equalsIgnoreCase(auditCluster.getCategory(), Constants.AUDIT_WARNINGS)) {
                    state = ERROR;
                    GlobalVariables.getMessageMap().putError("noKey", KeyConstants.ERROR_VALIDATION_ERRORS_EXIST);
                    break;
                }
            }
        }
        return state;
    }

    @Override
    public ActionForward acknowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ((InstitutionalProposalForm)form).getDocument().prepareForSave();
        return super.acknowledge(mapping, form, request, response);
    }
  
    @Override
    public ActionForward fyi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ((InstitutionalProposalForm)form).getDocument().prepareForSave();
        return super.fyi(mapping, form, request, response);
    }
    
    public ActionForward sendNotification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
        InstitutionalProposal institutionalProposal = institutionalProposalForm.getInstitutionalProposalDocument().getInstitutionalProposal();
        
        InstitutionalProposalNotificationRenderer renderer = new InstitutionalProposalNotificationRenderer(institutionalProposal);
        InstitutionalProposalNotificationContext context 
            = new InstitutionalProposalNotificationContext(institutionalProposal, null, "Ad-Hoc Notification", renderer);
        
        institutionalProposalForm.getNotificationHelper().initializeDefaultValues(context);
        
        return mapping.findForward("notificationEditor");
    }

    /**
     * 
     * This method is to build the confirmation question for unlocking funded awards.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @param deletePeriod
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildUnlockSelectedConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_UNLOCK_SELECTED_KEY,
                KeyConstants.QUESTION_UNLOCK_FUNDED_AWARDS);
    }
    
}
