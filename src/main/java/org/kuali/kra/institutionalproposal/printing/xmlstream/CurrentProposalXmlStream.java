package org.kuali.kra.institutionalproposal.printing.xmlstream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.CurrentAndPendingSupportDocument;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport;
import noNamespace.CurrentAndPendingSupportDocument.CurrentAndPendingSupport.CurrentSupport;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;

/**
 * This class generates XML that confirms with the XSD related to Current
 * Proposal Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 */
public class CurrentProposalXmlStream extends InstitutionalProposalBaseStream {
	private static final String AWARD_NUMBER_PARAMETER = "awardNumber";

	/**
	 * This method generates XML for Current Proposal Report. It uses data
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
		CurrentAndPendingSupportDocument currentAndPendingSupportDocument=CurrentAndPendingSupportDocument.Factory.newInstance();
		CurrentAndPendingSupport currentAndPendingSupport = CurrentAndPendingSupport.Factory
				.newInstance();
		InstitutionalProposal institutionalProposal = ipDoc
				.getInstitutionalProposal();
		List<Award> awards = null;
		if (institutionalProposal.getCurrentAwardNumber() != null) {
			awards = getAwardForCurrentAwardNumber(institutionalProposal
					.getCurrentAwardNumber());
		}
		CurrentSupport[] currentSupports = getCurrentSupportInformation(awards,
				reportParameters);
		if (personName != null) {
			currentAndPendingSupport.setPersonName(personName);
		}
		currentAndPendingSupport.setCurrentSupportArray(currentSupports);
		currentAndPendingSupportDocument.setCurrentAndPendingSupport(currentAndPendingSupport);
		xmlObjectList.put(InstitutionalProposalPrintType.CURRENT_REPORT
				.getInstitutionalProposalPrintType(), currentAndPendingSupportDocument);
		return xmlObjectList;
	}

	/*
	 * This method will set the values to current support information xml object
	 * and finally returns a array of currentSupport xml objects
	 */
	private CurrentSupport[] getCurrentSupportInformation(List<Award> awards,
			Map<String, Object> reportParameters) {
		List<CurrentSupport> currentSupports = new ArrayList<CurrentSupport>();
		String persionId = getPersonIdFromReportParams(reportParameters);
		for (Award award : awards) {
			if ((award.getStatusCode() == AWARD_STATUS_ACTIVE || award.getStatusCode() == AWARD_STATUS_PENDING)) {
				CurrentSupport currentSupport = CurrentSupport.Factory
						.newInstance();
				for (AwardPerson awardPerson : award.getProjectPersons()) {
					setCurrentSupportFromAwardPerson(persionId, currentSupport,
							awardPerson);
				}
				for (AwardAmountInfo awardAmountInfo : award
						.getAwardAmountInfos()) {
					if (awardAmountInfo.getObliDistributableAmount() != null) {
						currentSupport
								.setAwardAmount(awardAmountInfo
										.getObliDistributableAmount()
										.bigDecimalValue());
					}
					if (awardAmountInfo.getFinalExpirationDate() != null) {
						currentSupport.setEndDate(dateTimeService
								.getCalendar(awardAmountInfo
										.getFinalExpirationDate()));
					}
					break;
				}
				setCurrentSupportProposalForBasicInformation(award,
						currentSupport);
				currentSupports.add(currentSupport);
			}
		}
		return currentSupports.toArray(new CurrentSupport[0]);
	}

	/*
	 * This method will set the values to current support xml object of basic
	 * information like effective date,sponsor award number and sponsor name.
	 */
	private void setCurrentSupportProposalForBasicInformation(Award award,
			CurrentSupport currentSupport) {
		if (award.getTitle() != null) {
			currentSupport.setTitle(award.getTitle());
		}
		if (award.getAwardEffectiveDate() != null) {
			currentSupport.setEffectiveDate(dateTimeService.getCalendar(award
					.getAwardEffectiveDate()));
		}
		if (award.getSponsorAwardNumber() != null) {
			currentSupport.setSponsorAwardNumber(award.getSponsorAwardNumber());
		}
		if (award.getSponsorName() != null) {
			currentSupport.setAgency(award.getSponsorName());
		}
	}

	/*
	 * This method will set the values to current support proposal. Iterate over
	 * the award persons check for the person id ,if person id finds the then
	 * set the values to current support proposal.
	 */
	private void setCurrentSupportFromAwardPerson(String persionId,
			CurrentSupport currentSupport, AwardPerson awardPerson) {
		if (awardPerson.getPersonId() != null
				&& awardPerson.getPersonId().equals(persionId)) {
			if (awardPerson.getFullName() != null) {
				personName = awardPerson.getFullName();
			}
			if (awardPerson.getAcademicYearEffort() != null) {
				currentSupport.setAcademicYearEffort(awardPerson
						.getAcademicYearEffort().bigDecimalValue());
			}
			if (awardPerson.getCalendarYearEffort() != null) {
				currentSupport.setCalendarYearEffort(awardPerson
						.getCalendarYearEffort().bigDecimalValue());
			}
			if (awardPerson.getTotalEffort() != null) {
				currentSupport.setPercentageEffort(awardPerson.getTotalEffort()
						.bigDecimalValue());
			}
			if (awardPerson.getSummerEffort() != null) {
				currentSupport.setSummerYearEffort(awardPerson
						.getSummerEffort().bigDecimalValue());
			}
			String principleInvestigator = getPrincipleInvestigator(awardPerson);
			if (principleInvestigator != null) {
				currentSupport.setPI(principleInvestigator);
			}
		}
	}

	/*
	 * This method will return the person id.
	 */
	private String getPersonIdFromReportParams(
			Map<String, Object> reportParameters) {
		String personId = null;
		if (reportParameters != null && !reportParameters.isEmpty()
				&& reportParameters.get(PERSON_ID) != null) {
			personId = (String) reportParameters.get(PERSON_ID);
		}
		return personId;
	}

	/*
	 * This method will return the principle investigator.
	 */
	private String getPrincipleInvestigator(AwardPerson awardPerson) {
		String principleInvestigator = null;
		if (awardPerson.isPrincipalInvestigator()) {
			principleInvestigator = ContactRole.PI_CODE;
		} else if (awardPerson.isCoInvestigator()) {
			principleInvestigator = ContactRole.COI_CODE;
		}
		return principleInvestigator;
	}

	/*
	 * This method will get the list of awards for given award number
	 */
	private List<Award> getAwardForCurrentAwardNumber(String currentAwardNumber) {
		Map<String, String> awardMap = new HashMap<String, String>();
		awardMap
				.put(AWARD_NUMBER_PARAMETER, String.valueOf(currentAwardNumber));
		List<Award> awards = (List<Award>) businessObjectService.findMatching(
				Award.class, awardMap);
		return awards;
	}
}
