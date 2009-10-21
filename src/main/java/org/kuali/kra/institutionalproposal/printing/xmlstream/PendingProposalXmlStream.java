package org.kuali.kra.institutionalproposal.printing.xmlstream;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.PendingSupport;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPersonService;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class generates XML that confirms with the XSD related to Pending
 * Proposal Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 */
public class PendingProposalXmlStream extends InstitutionalProposalBaseStream {
	private InstitutionalProposalPersonService institutionalProposalPersonService;

	/**
	 * This method generates XML for Pending Proposal Report. It uses data
	 * passed in {@link ResearchDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param document
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			ResearchDocumentBase document, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		InstitutionalProposalDocument ipDoc = (InstitutionalProposalDocument) document;
		CurrentAndPendingSupport currentAndPendingSupport = CurrentAndPendingSupport.Factory
				.newInstance();
		InstitutionalProposal institutionalProposal = ipDoc
				.getInstitutionalProposal();
		List<PendingSupport> pendingSupports = new ArrayList<PendingSupport>();
		int proposalTypeCode = institutionalProposal.getProposalTypeCode();
		int statusCode = institutionalProposal.getStatusCode();
		if ((proposalTypeCode == PROPOSAL_TYPE_NEW
				|| proposalTypeCode == PROPOSAL_TYPE_CONTINUATION
				|| proposalTypeCode == PROPOSAL_TYPE_RESUBMISSION
				|| proposalTypeCode == PROPOSAL_TYPE_REVISION
				|| proposalTypeCode == PROPOSAL_TYPE_TASK_ORDER || proposalTypeCode == PROPOSAL_TYPE_CODE_8)
				&& (statusCode == PROPOSAL_STATUS_PENDING || statusCode == PROPOSAL_STATUS_REVISION_REQUESTED)) {
			PendingSupport pendingSupport = getPendingSupportProposals(
					institutionalProposal, reportParameters);
			pendingSupports.add(pendingSupport);
		}
		currentAndPendingSupport.setPendingSupportArray(pendingSupports
				.toArray(new PendingSupport[0]));
		if (personName != null) {
			currentAndPendingSupport.setPersonName(personName);
		}
		xmlObjectList.put(InstitutionalProposalPrintType.PENDING_REPORT
				.getInstitutionalProposalPrintType(), currentAndPendingSupport);
		return xmlObjectList;
	}

	/*
	 * This method set the values to pending support xml object attributes and
	 * finally returns the Pending support xml object.
	 */
	private PendingSupport getPendingSupportProposals(
			InstitutionalProposal instituteProposal,
			Map<String, Object> reportParameters) {
		PendingSupport pendingSupport = PendingSupport.Factory.newInstance();
		String persionId = null;
		if (reportParameters != null && !reportParameters.isEmpty()
				&& reportParameters.get(PERSON_ID) != null) {
			persionId = (String) reportParameters.get(PERSON_ID);
		}
		if (instituteProposal.getTitle() != null) {
			pendingSupport.setTitle(instituteProposal.getTitle());
		}
		setPendingSupportProposalsFromProposalPersons(instituteProposal,
				pendingSupport, persionId);
		KualiDecimal totalCost = getTotalCost(instituteProposal);
		if (totalCost != null) {
			pendingSupport.setTotalRequested(totalCost.bigDecimalValue());
		}
		if (instituteProposal.getProposalNumber() != null) {
			pendingSupport.setProposalNumber(instituteProposal
					.getProposalNumber());
		}
		if (instituteProposal.getRequestedEndDateInitial() != null) {
			pendingSupport
					.setEndDate(dateTimeService.getCalendar(instituteProposal
							.getRequestedEndDateInitial()));
		}
		if (instituteProposal.getRequestedStartDateInitial() != null) {
			pendingSupport.setEffectiveDate(dateTimeService
					.getCalendar(instituteProposal
							.getRequestedStartDateInitial()));
		}
		if (instituteProposal.getSponsorName() != null) {
			pendingSupport.setAgency(instituteProposal.getSponsorName());
		}
		return pendingSupport;
	}

	/*
	 * This method will return the sum of total direct cost and total indirect
	 * cost.
	 */
	private KualiDecimal getTotalCost(InstitutionalProposal instituteProposal) {
		KualiDecimal totalDirectCostTotal = instituteProposal
				.getTotalDirectCostTotal();
		KualiDecimal totalIndirectCostTotal = instituteProposal
				.getTotalIndirectCostTotal();
		KualiDecimal totalCost = null;
		if (totalIndirectCostTotal != null && totalIndirectCostTotal != null) {
			totalCost = totalDirectCostTotal.add(totalIndirectCostTotal);
		}
		return totalCost;
	}

	/*
	 * This method set the values to pending support proposals.For a given
	 * proposal number get a list of proposal persons ,iterate over and set the
	 * values to pending proposal.
	 */
	private void setPendingSupportProposalsFromProposalPersons(
			InstitutionalProposal instituteProposal,
			PendingSupport pendingSupport, String persionId) {
		List<ProposalPerson> proposalPersons = institutionalProposalPersonService
				.getProposalPersonsFromDevelopmentProposal(instituteProposal
						.getProposalNumber());
		for (ProposalPerson proposalPerson : proposalPersons) {
			if (proposalPerson.getPersonId() != null
					&& proposalPerson.getPersonId().equals(persionId)) {
				// TODO : SummerYearEffort,AcademicYearEffort and
				// CalendarYearEffort not found
				if (proposalPerson.getPercentageEffort() != null) {
					pendingSupport.setPercentageEffort(proposalPerson
							.getPercentageEffort().bigDecimalValue());
				}
				String principleInvestigator = null;
				if (proposalPerson.isInvestigator()) {
					principleInvestigator = ContactRole.PI_CODE;
				} else {
					principleInvestigator = ContactRole.COI_CODE;
				}
				pendingSupport.setPI(principleInvestigator);
				if (proposalPerson.getFullName() != null) {
					personName = proposalPerson.getFullName();
				}
			}
		}
	}

	/**
	 * @return the institutionalProposalPersonService
	 */
	public InstitutionalProposalPersonService getInstitutionalProposalPersonService() {
		return institutionalProposalPersonService;
	}

	/**
	 * @param institutionalProposalPersonService
	 *            the institutionalProposalPersonService to set
	 */
	public void setInstitutionalProposalPersonService(
			InstitutionalProposalPersonService institutionalProposalPersonService) {
		this.institutionalProposalPersonService = institutionalProposalPersonService;
	}

}
