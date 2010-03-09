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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPrintingService;
import org.kuali.kra.institutionalproposal.printing.xmlstream.InstitutionalProposalBaseStream;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.proposaldevelopment.bo.AttachmentDataSource;
import org.kuali.kra.web.struts.action.AuditActionHelper;
import org.kuali.rice.kns.web.struts.action.AuditModeAction;

/**
 * This class...
 */
public class InstitutionalProposalActionsAction extends
		InstitutionalProposalAction implements AuditModeAction {

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
}
