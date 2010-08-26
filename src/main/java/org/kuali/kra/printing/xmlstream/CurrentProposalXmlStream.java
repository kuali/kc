package org.kuali.kra.printing.xmlstream;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.CurrentAndPendingSupportDocument;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.CurrentSupport;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.common.printing.CurrentReportBean;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.printing.service.CurrentAndPendingReportService;

/**
 * This class generates XML that confirms with the XSD related to Current
 * Proposal Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 */
public class CurrentProposalXmlStream extends CurrentAndPendingBaseStream {

	/**
	 * This method generates XML for Current Proposal Report. It uses data
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
        List<CurrentReportBean> currentReportBeans = (List<CurrentReportBean>)reportParameters.get(CurrentAndPendingReportService.CURRENT_REPORT_BEANS_KEY);
		CurrentSupport[] currentSupports = getCurrentSupportInformation(currentReportBeans);
		currentAndPendingSupport.setPersonName((String)reportParameters.get(CurrentAndPendingReportService.REPORT_PERSON_NAME_KEY));
		currentAndPendingSupport.setCurrentSupportArray(currentSupports);
		currentAndPendingSupportDocument.setCurrentAndPendingSupport(currentAndPendingSupport);
		xmlObjectList.put(CurrentAndPendingReportService.CURRENT_REPORT_TYPE, currentAndPendingSupportDocument);
		return xmlObjectList;
	}

	/*
	 * This method will set the values to current support information xml object
	 * and finally returns a array of currentSupport xml objects
	 */
	private CurrentSupport[] getCurrentSupportInformation(List<CurrentReportBean> currentReportBeans) {
		List<CurrentSupport> currentSupports = new ArrayList<CurrentSupport>();
		for (CurrentReportBean bean : currentReportBeans) {
		    CurrentSupport currentSupport = CurrentSupport.Factory.newInstance();
		    currentSupports.add(currentSupport);
            if (bean.getAcademicYearEffort() != null) {
                currentSupport.setAcademicYearEffort(bean
                        .getAcademicYearEffort().bigDecimalValue());
            }
            if (bean.getCalendarYearEffort() != null) {
                currentSupport.setCalendarYearEffort(bean
                        .getCalendarYearEffort().bigDecimalValue());
            }
            if (bean.getTotalEffort() != null) {
                currentSupport.setPercentageEffort(bean.getTotalEffort()
                        .bigDecimalValue());
            }
            if (bean.getSummerEffort() != null) {
                currentSupport.setSummerYearEffort(bean
                        .getSummerEffort().bigDecimalValue());
            }
            if (bean.getRoleCode() != null) {
                currentSupport.setPI(bean.getRoleCode());
            }
            if (bean.getAwardAmount() != null) {
                currentSupport.setAwardAmount(bean.getAwardAmount().bigDecimalValue());
            }
            if (bean.getProjectEndDate() != null) {
                currentSupport.setEndDate(dateTimeService.getCalendar(bean.getProjectEndDate()));
            }
            if (bean.getAwardTitle() != null) {
                currentSupport.setTitle(bean.getAwardTitle());
            }
            if (bean.getProjectStartDate() != null) {
                currentSupport.setEffectiveDate(dateTimeService.getCalendar(bean.getProjectStartDate()));
            }
            if (bean.getSponsorAwardNumber() != null) {
                currentSupport.setSponsorAwardNumber(bean.getSponsorAwardNumber());
            }
            if (bean.getSponsorName() != null) {
                currentSupport.setAgency(bean.getSponsorName());
            }
		}
		return currentSupports.toArray(new CurrentSupport[0]);
	}
}
