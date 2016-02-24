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
package org.kuali.kra.institutionalproposal.web.struts.action;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.kuali.coeus.common.framework.print.PrintConstants;
import org.kuali.coeus.common.proposal.framework.report.CurrentAndPendingReportService;
import org.kuali.coeus.sys.framework.controller.KcHoldingPageConstants;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.controller.StrutsConfirmation;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.fundedawards.FundedAwardsBean;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.notification.InstitutionalProposalNotificationContext;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPrintingService;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.coeus.common.framework.print.AttachmentDataSource;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.krad.util.KRADConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class InstitutionalProposalActionsAction extends InstitutionalProposalAction implements AuditModeAction {
    private static final int ERROR = 2;
    private static final int OK = 0;
    private static final int WARNING = 1;
    
    private static final String CONFIRM_UNLOCK_SELECTED = "confirmUnlockSelected";
    private static final String CONFIRM_UNLOCK_SELECTED_KEY = "confirmUnlockSelectedKey";
    private static final String ERROR_SELECTING_FUNDING_PROPS = "error.fundingproposal.unlockNoSelection";
    @Override
	public ActionForward activate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping,
				(InstitutionalProposalForm) form, true);
	}

    @Override
	public ActionForward deactivate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return KcServiceLocator.getService(AuditHelper.class).setAuditMode(mapping,
				(InstitutionalProposalForm) form, false);
	}

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
    
    public ActionForward confirmUnlockSelected(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        new FundedAwardsBean((InstitutionalProposalForm) form).removeUnlockedAwards();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
    public ActionForward selectAllFundedAwards(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        new FundedAwardsBean((InstitutionalProposalForm) form).selectAllFundedAwards();
        return mapping.findForward(Constants.MAPPING_BASIC);
    }
    
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
        InstitutionalProposalPrintingService ipPrintingService = KcServiceLocator.getService(InstitutionalProposalPrintingService.class);
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
		request.setAttribute(PrintConstants.CURRENT_REPORT_ROWS_KEY, helper
				.prepareCurrentReport());
		request.setAttribute(PrintConstants.REPORT_PERSON_NAME_KEY, helper
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
		request.setAttribute(PrintConstants.PENDING_REPORT_ROWS_KEY, helper
				.preparePendingReport());
		request.setAttribute(PrintConstants.REPORT_PERSON_NAME_KEY, helper
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
        CurrentAndPendingReportService currentAndPendingReportService = KcServiceLocator
                .getService(CurrentAndPendingReportService.class);
        ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(PrintConstants.PERSON_ID_KEY, helper.getPersonId());
        reportParameters.put(PrintConstants.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        AttachmentDataSource dataStream = currentAndPendingReportService.printCurrentReport(reportParameters);
        streamToResponse(dataStream.getData(), dataStream.getName(), null, response);
        return null;
    }

    /**
     * Prepare pending report (i.e. InstitutionalProposals that selected person is on) {@inheritDoc}
     */
    public ActionForward printPendingReportPdf(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        CurrentAndPendingReportService currentAndPendingReportService = KcServiceLocator
                .getService(CurrentAndPendingReportService.class);
        ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        reportParameters.put(PrintConstants.PERSON_ID_KEY, helper.getPersonId());
        reportParameters.put(PrintConstants.REPORT_PERSON_NAME_KEY, helper.getTargetPersonName());
        AttachmentDataSource dataStream = currentAndPendingReportService.printPendingReport(reportParameters);
        streamToResponse(dataStream.getData(), dataStream.getName(), null, response);
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
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation, routeHeaderId);
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
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation, routeHeaderId);
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
        ActionRedirect holdingPageForward = new ActionRedirect(mapping.findForward(KcHoldingPageConstants.MAPPING_HOLDING_PAGE));
        holdingPageForward.addParameter(KcHoldingPageConstants.HOLDING_PAGE_DOCUMENT_ID, routeHeaderId);
        return routeToHoldingPage(basicForward, forward, holdingPageForward, returnLocation, routeHeaderId);
    }

    private int submissionStatus(InstitutionalProposalDocument institutionalProposalDocument) {
        int state = OK;
        boolean auditPassed = KcServiceLocator.getService(AuditHelper.class).auditUnconditionally(institutionalProposalDocument);
        if (!auditPassed) {
            state = WARNING;
            for (Iterator iter = GlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();) {
                AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(iter.next());
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
       
        InstitutionalProposalNotificationContext context 
            = new InstitutionalProposalNotificationContext(institutionalProposal, null, "Ad-Hoc Notification", Constants.MAPPING_INSTITUTIONAL_PROPOSAL_ACTIONS_PAGE);
        
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
     * @return
     * @throws Exception
     */
    private StrutsConfirmation buildUnlockSelectedConfirmationQuestion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return buildParameterizedConfirmationQuestion(mapping, form, request, response, CONFIRM_UNLOCK_SELECTED_KEY,
                KeyConstants.QUESTION_UNLOCK_FUNDED_AWARDS);
    }
    
}
