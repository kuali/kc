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
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.fundedawards.FundedAwardsBean;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPrintingService;
import org.kuali.kra.institutionalproposal.printing.xmlstream.InstitutionalProposalBaseStream;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.kra.web.struts.action.StrutsConfirmation;
import org.kuali.rice.core.util.RiceConstants;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.event.SendAdHocRequestsEvent;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;
import org.kuali.rice.kns.web.struts.form.KualiDocumentFormBase;

/**
 * This class...
 */
public class InstitutionalProposalActionsAction extends InstitutionalProposalAction implements AuditModeAction {
    private static final int ERROR = 2;
    private static final int OK = 0;
    private static final int WARNING = 1;
    
    private static final String CONFIRM_UNLOCK_SELECTED = "confirmUnlockSelected";
    private static final String CONFIRM_UNLOCK_SELECTED_KEY = "confirmUnlockSelectedKey";
    private static final String ONE_ADHOC_REQUIRED_ERROR_KEY="error.adhoc.oneAdHocRequired";
   
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
        return confirm(buildUnlockSelectedConfirmationQuestion(mapping, form, request, response), CONFIRM_UNLOCK_SELECTED, "");
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
                ipForm.getInstitutionalProposalDocument(), 
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
		request.setAttribute(ReportHelperBean.CURRENT_REPORT_BEANS_KEY, helper
				.prepareCurrentReport());
		request.setAttribute(ReportHelperBean.REPORT_PERSON_NAME_KEY, helper
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
		request.setAttribute(ReportHelperBean.PENDING_REPORT_BEANS_KEY, helper
				.preparePendingReport());
		request.setAttribute(ReportHelperBean.REPORT_PERSON_NAME_KEY, helper
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
	public ActionForward printCurrentReportPdf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
		InstitutionalProposalPrintingService institutionalProposalPrintingService = KraServiceLocator
				.getService(InstitutionalProposalPrintingService.class);
		ResearchDocumentBase document = institutionalProposalForm
				.getInstitutionalProposalDocument();
		ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
		Map<String, Object> reportParameters=new HashMap<String, Object>();
		reportParameters.put(InstitutionalProposalBaseStream.PERSON_ID, helper.getPersonId());
		AttachmentDataSource dataStream = institutionalProposalPrintingService
				.printInstitutionalProposalReport(document, InstitutionalProposalPrintType.CURRENT_REPORT
						.getInstitutionalProposalPrintType(), reportParameters);
		streamToResponse(dataStream.getContent(), null, null, response);
		return mapping.findForward(Constants.MAPPING_BASIC);
	}
	
	/**
	 * Prepare pending report (i.e. InstitutionalProposals that selected person
	 * is on) {@inheritDoc}
	 */
	public ActionForward printPendingReportPdf(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		InstitutionalProposalForm institutionalProposalForm = (InstitutionalProposalForm) form;
		InstitutionalProposalPrintingService institutionalProposalPrintingService = KraServiceLocator
				.getService(InstitutionalProposalPrintingService.class);
		ResearchDocumentBase document = institutionalProposalForm
				.getInstitutionalProposalDocument();
		ReportHelperBean helper = ((ReportHelperBeanContainer) form).getReportHelperBean();
		Map<String, Object> reportParameters=new HashMap<String, Object>();
		reportParameters.put(InstitutionalProposalBaseStream.PERSON_ID, helper.getPersonId());
		AttachmentDataSource dataStream = institutionalProposalPrintingService
				.printInstitutionalProposalReport(document, InstitutionalProposalPrintType.PENDING_REPORT
						.getInstitutionalProposalPrintType(), reportParameters);
		streamToResponse(dataStream.getContent(), null, null, response);
		return mapping.findForward(Constants.MAPPING_BASIC);
	}

    @Override
    public ActionForward blanketApprove(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ((InstitutionalProposalForm) form).setAuditActivated(true);
        if (submissionStatus(((InstitutionalProposalForm) form).getInstitutionalProposalDocument()) == ERROR) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            return super.blanketApprove(mapping, form, request, response);
        }
    }
	
    
    @Override
    public ActionForward route(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ((InstitutionalProposalForm) form).setAuditActivated(true);
        if (submissionStatus(((InstitutionalProposalForm) form).getInstitutionalProposalDocument()) == ERROR) {
            return mapping.findForward(Constants.MAPPING_BASIC);
        } else {
            return super.route(mapping, form, request, response);
        }
    }

    private int submissionStatus(InstitutionalProposalDocument institutionalProposalDocument) {
        int state = OK;
        boolean auditPassed = new AuditActionHelper().auditUnconditionally(institutionalProposalDocument);
        if (!auditPassed) {
            state = WARNING;
            for (Iterator iter = GlobalVariables.getAuditErrorMap().keySet().iterator(); iter.hasNext();) {
                AuditCluster auditCluster = (AuditCluster)GlobalVariables.getAuditErrorMap().get(iter.next());
                if (!StringUtils.equalsIgnoreCase(auditCluster.getCategory(), Constants.AUDIT_WARNINGS)) {
                    state = ERROR;
                    GlobalVariables.getErrorMap().putError("noKey", KeyConstants.VALIDATTION_ERRORS_BEFORE_GRANTS_GOV_SUBMISSION);
                    break;
                }
            }
        }
        return state;
    }

    public ActionForward sendAdHocRequests(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        InstitutionalProposalForm dform = (InstitutionalProposalForm) form;
        Document document = dform.getDocument();
        if( dform.getAdHocRoutePersons().size() > 0 || dform.getAdHocRouteWorkgroups().size() > 0) {
            document.prepareForSave();
            return super.sendAdHocRequests(mapping, dform, request, response);
        } else {
            GlobalVariables.getErrorMap().putError("newAdHocRoutePerson.id", ONE_ADHOC_REQUIRED_ERROR_KEY);
            return mapping.findForward(Constants.MAPPING_BASIC);
        }
    }

    @Override
    public ActionForward acknowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception {
        ((InstitutionalProposalForm)form).getDocument().prepareForSave();
        return super.acknowledge(mapping, form, request, response);
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
