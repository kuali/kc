package org.kuali.kra.printing.xmlstream;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.CurrentAndPendingSupportDocument;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.PendingSupport;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.kra.common.printing.PendingReportBean;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPersonService;
import org.kuali.kra.printing.service.CurrentAndPendingReportService;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.rice.kns.util.KualiDecimal;

/**
 * This class generates XML that confirms with the XSD related to Pending
 * Proposal Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 */
public class PendingProposalXmlStream extends CurrentAndPendingBaseStream {
	private InstitutionalProposalPersonService institutionalProposalPersonService;

	/**
	 * This method generates XML for Pending Proposal Report. It uses data
	 * passed in {@link ResearchDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		CurrentAndPendingSupportDocument currentAndPendingSupportDocument=CurrentAndPendingSupportDocument.Factory.newInstance();
		CurrentAndPendingSupport currentAndPendingSupport = CurrentAndPendingSupport.Factory
				.newInstance();
		List<PendingReportBean> pendingReportBeans = (List<PendingReportBean>)reportParameters.get(CurrentAndPendingReportService.PENDING_REPORT_BEANS_KEY);
        PendingSupport[] pendingSupports = getPendingSupportInformation(pendingReportBeans);
        currentAndPendingSupport.setPersonName((String)reportParameters.get(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY));
        currentAndPendingSupport.setPendingSupportArray(pendingSupports);
        currentAndPendingSupportDocument.setCurrentAndPendingSupport(currentAndPendingSupport);
        xmlObjectList.put(CurrentAndPendingReportService.PENDING_REPORT_TYPE, currentAndPendingSupportDocument);
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
//		if (reportParameters != null && !reportParameters.isEmpty()
//				&& reportParameters.get(PERSON_ID) != null) {
//			persionId = (String) reportParameters.get(PERSON_ID);
//		}
//		if (instituteProposal.getTitle() != null) {
//			pendingSupport.setTitle(instituteProposal.getTitle());
//		}
//		setPendingSupportProposalsFromProposalPersons(instituteProposal,
//				pendingSupport, persionId);
//		KualiDecimal totalCost = getTotalCost(instituteProposal);
//		if (totalCost != null) {
//			pendingSupport.setTotalRequested(totalCost.bigDecimalValue());
//		}
//		if (instituteProposal.getProposalNumber() != null) {
//			pendingSupport.setProposalNumber(instituteProposal
//					.getProposalNumber());
//		}
//		if (instituteProposal.getRequestedEndDateInitial() != null) {
//			pendingSupport
//					.setEndDate(dateTimeService.getCalendar(instituteProposal
//							.getRequestedEndDateInitial()));
//		}
//		if (instituteProposal.getRequestedStartDateInitial() != null) {
//			pendingSupport.setEffectiveDate(dateTimeService
//					.getCalendar(instituteProposal
//							.getRequestedStartDateInitial()));
//		}
//		if (instituteProposal.getSponsorName() != null) {
//			pendingSupport.setAgency(instituteProposal.getSponsorName());
//		}
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

	private PendingSupport[] getPendingSupportInformation(List<PendingReportBean> pendingReportBeans) {
	    List<PendingSupport> pendingSupports = new ArrayList<PendingSupport>();
	    for (PendingReportBean bean : pendingReportBeans) {
	        PendingSupport pendingSupport = PendingSupport.Factory.newInstance();
	        pendingSupports.add(pendingSupport);
	        if (bean.getProposalTitle() != null) {
	            pendingSupport.setTitle(bean.getProposalTitle());
	        }
	        if (bean.getTotalDirectCostTotal() != null) {
	            pendingSupport.setTotalDirectCost(bean.getTotalDirectCostTotal().bigDecimalValue());
	        }
	        if (bean.getTotalIndirectCostTotal() != null) {
	            pendingSupport.setTotalIndirectCost(bean.getTotalIndirectCostTotal().bigDecimalValue());
	        }
	        if (bean.getTotalRequestedCost() != null) {
	            pendingSupport.setTotalRequested(bean.getTotalRequestedCost().bigDecimalValue());
	        }
	        if (bean.getProposalNumber() != null) {
	            pendingSupport.setProposalNumber(bean
	                    .getProposalNumber());
	        }
	        if (bean.getRequestedEndDateTotal() != null) {
	            pendingSupport
	                    .setEndDate(dateTimeService.getCalendar(bean
	                            .getRequestedEndDateTotal()));
	        }
	        if (bean.getRequestedStartDateInitial() != null) {
	            pendingSupport.setEffectiveDate(dateTimeService
	                    .getCalendar(bean
	                            .getRequestedStartDateInitial()));
	        }
	        if (bean.getSponsorName() != null) {
	            pendingSupport.setAgency(bean.getSponsorName());
	        }
            if (bean.getRoleCode() != null) {
                pendingSupport.setPI(bean.getRoleCode());
            }
            if (bean.getTotalEffort() != null) {
                pendingSupport.setPercentageEffort(bean.getTotalEffort().bigDecimalValue());
            }
            if (bean.getAcademicYearEffort() != null) {
                pendingSupport.setAcademicYearEffort(bean.getAcademicYearEffort().bigDecimalValue());
            }
            if (bean.getCalendarYearEffort() != null) {
                pendingSupport.setCalendarYearEffort(bean.getCalendarYearEffort().bigDecimalValue());
            }
            if (bean.getSummerEffort() != null) {
                pendingSupport.setSummerYearEffort(bean.getSummerEffort().bigDecimalValue());
            }
	    }
	    return pendingSupports.toArray(new PendingSupport[0]);
	}

}
