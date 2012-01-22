/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.kra.proposaldevelopment.printing.xmlstream;

import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import noNamespace.Certification;
import noNamespace.Investigator;
import noNamespace.OrganizationType;
import noNamespace.PCschoolInfoType;
import noNamespace.PrintCertificationDocument;
import noNamespace.Sponsor;
import noNamespace.PrintCertificationDocument.PrintCertification;

import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.ArgValueLookup;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.printing.service.ProposalDevelopmentPrintingService;

/**
 * This class generates XML that confirms with the XSD related to Print
 * Certification Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 */
public class PrintCertificationXmlStream extends ProposalBaseStream {

	private static final String NSF_SPONSOR_CODE = "000100";
	private static final String NSF_SPONSOR_ACRONYM = "NSF";
	private static final String SPONSOR_GROUPS = "SPONSOR GROUPS";
	private static final String PI_QUESTION = "PI_QUESTION";
	private static final String ARGUMENT_NAME = "argumentName";
	private static final String IMAGES_PATH = "/images/";
	private static final String QUESTION_ID_Z6 = "Z6";
	private static final String QUESTION_ID_Z5 = "Z5";
	private static final String QUESTION_ID_Z4 = "Z4";
	private static final String QUESTION_ID_Z3 = "Z3";
	private static final String QUESTION_ID_Z2 = "Z2";
	private static final String QUESTION_ID_Z1 = "Z1";
	private static final String EMPTY_STRING = "";
	private static final String SPONSOR_TYPE_CODE_ZERO = "0";
	private static final String SPONSOR_TYPE_FED = "FED";
	private static final String SPONSOR_TYPE_NONFED = "NONFED";
	private static final String SCHOOL_NAME = "SCHOOL_NAME";
	private static final String SCHOOL_ACRONYM = "SCHOOL_ACRONYM";
	private static final String KEY_ROLODEX_ID = "rolodexId";
	private static final String STATEMENT_CONFLICT = "I have indicated whether or not there is any potential for a real or perceived conflict of interest as defined in MIT Policies and Procedures, 4.4:  http://web.mit.edu/afs/athena.mit.edu/org/p/policies/4.4.html";
	private static final String STATEMENT_CERTIFICATION_NOT_NEEDED = "Certification is not needed";
	private static final String STATEMENT_NIH_PROPOSAL = "For NSF & NIH proposals, only: I have submitted the required financial conflict of interest documentation to the Director, Office of Sponsored Programs: http://web.mit.edu/osp/www/resources_policy.htm";
	private static final String STATEMENT_NOT_DEBARRED = "I am not debarred, suspended or proposed for debarment or suspended by any agency of the U.S. government: http://web.mit.edu/osp/www/debarmen.htm";
	private static final String STATEMENT_FEDERAL_AGENCY_LOBBY = "I certify that I have not and will not lobby any federal agency on behalf of this award: http://web.mit.edu/osp/www/fedlobrg.htm";
	private static final String STATEMENT_REQUIREMENT_FAMILIARITY = "I am familiar with the requirements of the Procurement Integrity Act [OFPP, Section 27 (a-e)] and will report any violations to the Office of Sponsored Programs: http://web.mit.edu/osp/www/Procuint.htm";

	/**
	 * This method generates XML for Print Certification Report. It uses data
	 * passed in {@link ResearchDocumentBase} for populating the XML nodes. The
	 * XMl once generated is returned as {@link XmlObject}
	 * 
	 * @param printableBusinessObject
	 *            using which XML is generated
	 * @param reportParameters
	 *            parameters related to XML generation
	 * @return {@link XmlObject} representing the XML
	 * @throws PrintingException
	 *             in case of any errors occur during XML generation.
	 */
	public Map<String, XmlObject> generateXmlStream(
			KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
		Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
		DevelopmentProposal developmentProposal = (DevelopmentProposal) printableBusinessObject;
		ProposalPerson personToPrint = (ProposalPerson) reportParameters.get(ProposalDevelopmentPrintingService.PRINT_CERTIFICATION_PERSON);
		for (ProposalPerson proposalPerson : developmentProposal.getProposalPersons()) {
		    if (personToPrint == null || personToPrint.equals(proposalPerson)) {
    			PrintCertificationDocument printCertDocument = PrintCertificationDocument.Factory.newInstance();
    			PrintCertification printCertification = PrintCertification.Factory.newInstance();
    			printCertification = getPrintCertification(developmentProposal, proposalPerson);
    			printCertDocument.setPrintCertification(printCertification);
    			xmlObjectList.put(getPersonName(proposalPerson), printCertDocument);
		    }
		}
		return xmlObjectList;
	}

	/*
	 * This method sets printCertification values from DevelopmentProposal and
	 * finally returns printCertification XML Object
	 */
	private PrintCertification getPrintCertification(
			DevelopmentProposal developmentProposal,
			ProposalPerson proposalPerson) {
		PrintCertification printCertification = PrintCertification.Factory
				.newInstance();
		printCertification.setProposalNumber(developmentProposal
				.getProposalNumber());
		printCertification.setProposalTitle(developmentProposal.getTitle());
		printCertification.setInvestigator(getInvestigator(proposalPerson));
		Sponsor sponsor = getSponsor(developmentProposal.getSponsorCode(),
				developmentProposal.getPrimeSponsorCode(), developmentProposal
						.getSponsorName(), developmentProposal.getSponsor());
		printCertification.setSponsor(sponsor);
		printCertification.setSchoolInfo(getSchoolInfoType());
		printCertification
				.setOrganizationInfo(getOrganizationType(developmentProposal
						.getApplicantOrganization()));
		Certification[] certifications = getCertifications(proposalPerson
				.getProposalPersonYnqs(), developmentProposal.getSponsor()
				.getSponsorTypeCode(), developmentProposal.getSponsorCode(),
				developmentProposal.getPrimeSponsorCode());
		printCertification.setCertificationsArray(certifications);
		printCertification.setLogoPath(IMAGES_PATH);
		printCertification.setCurrentDate(getDateTimeService().getCurrentCalendar());
		printCertification.setOfficeName(getOfficeName(proposalPerson));
		return printCertification;
	}

	/*
	 * This method gets officeName from list of ProposalPersonUnits by checking
	 * leadUnit which iterate through list of proposalPerson
	 */
	private String getOfficeName(ProposalPerson proposalPerson) {
		String officeName = Constants.EMPTY_STRING;
		for (ProposalPersonUnit proposalPersonUnit : proposalPerson.getUnits()) {
			if (proposalPerson.getPerson() != null
					&& proposalPersonUnit.getUnit() != null
					&& proposalPersonUnit.isLeadUnit()){
				if (proposalPersonUnit.getUnit().getUnitName() != null) {
					officeName = proposalPersonUnit.getUnit().getUnitName();
					break;
				}
			}
		}
		return officeName;
	}

	/*
	 * This method sets value of each certification and construct a list of
	 * certification and finally returns array of certification XML Object from
	 * List of ProposalYnq and list of ProposalPersonYnq
	 */
	private Certification[] getCertifications(
			List<ProposalPersonYnq> proposalPersonYnqs, String sponsorTypeCode,
			String sponsorCode, String primeSponsorCode) {
		List<Certification> certificationList = new ArrayList<Certification>();
		int statementNumber = 1;
		for (ProposalPersonYnq proposalPersonYnq : proposalPersonYnqs) {
			if (proposalPersonYnq.getQuestionId() != null) {
				Certification certification = Certification.Factory
						.newInstance();
				certification.setQuestionID(proposalPersonYnq.getQuestionId());
				if (proposalPersonYnq.getAnswer() != null) {
					certification.setAnswer(proposalPersonYnq.getAnswer());
				}
				certificationList.add(certification);
			}
		}
		List<String> questionIds = getQuestionIdsForCertification(
				sponsorTypeCode, sponsorCode, primeSponsorCode);
		for (String questionId : questionIds) {
			Certification certification = Certification.Factory.newInstance();
			String statement = getStatementForCertification(questionId);
			certification.setStmtNumber(statementNumber++);
			certification.setStatement(statement);
			certificationList.add(certification);
		}
		return certificationList.toArray(new Certification[0]);
	}

	/*
	 * This method gets questionIds based on sponsorTypeCode and
	 * primSponsorTypeCode values, if the sponsorTypeCode and
	 * primeSponsorTypeCode code values is 0 then check for availability of
	 * sponsorCode in SponsorHirarchy if available return set of questions. If
	 * the typeCode other than 0 then return only Z1 questionId
	 */
	private List<String> getQuestionIdsForCertification(String sponsorTypeCode,
			String sponsorCode, String primeSponsorCode) {
		List<String> questionIds = new ArrayList<String>();
		String primSponsorTypeCode = getSponsorTypeCodeFromSponsor(primeSponsorCode);
		List<String> questionIdsList = getQuestionIdsFromArgValueLookup();
		if (sponsorTypeCode.equals(SPONSOR_TYPE_CODE_ZERO)
				|| primSponsorTypeCode.equals(SPONSOR_TYPE_CODE_ZERO)) {
			if (isSponsorCodeInSponsorHierarchy()) {
				for (String questionId : questionIdsList) {
					if (questionId.equals(QUESTION_ID_Z2)
							|| questionId.equals(QUESTION_ID_Z3)
							|| questionId.equals(QUESTION_ID_Z4)
							|| questionId.equals(QUESTION_ID_Z5)) {
						questionIds.add(questionId);
					}
				}
			} else {
				for (String questionId : questionIdsList) {
					if (questionId.equals(QUESTION_ID_Z2)
							|| questionId.equals(QUESTION_ID_Z4)
							|| questionId.equals(QUESTION_ID_Z5)) {
						questionIds.add(questionId);
					}
				}
			}
		} else {
			for (String questionId : questionIdsList) {
				if (questionId.equals(QUESTION_ID_Z1)) {
					questionIds.add(questionId);
				}
			}
		}
		return questionIds;
	}

	/*
	 * This method gets statement based on questionID
	 */
	private String getStatementForCertification(String questionId) {
		String statement = Constants.EMPTY_STRING;
		if (questionId.equals(QUESTION_ID_Z1)) {
			statement = STATEMENT_CERTIFICATION_NOT_NEEDED;
		}
		if (questionId.equals(QUESTION_ID_Z2)) {
			statement = STATEMENT_CONFLICT;
		}
		if (questionId.equals(QUESTION_ID_Z3)) {
			statement = STATEMENT_NIH_PROPOSAL;
		}
		if (questionId.equals(QUESTION_ID_Z4)) {
			statement = STATEMENT_NOT_DEBARRED;
		}
		if (questionId.equals(QUESTION_ID_Z5)) {
			statement = STATEMENT_FEDERAL_AGENCY_LOBBY;
		}
		if (questionId.equals(QUESTION_ID_Z6)) {
			statement = STATEMENT_REQUIREMENT_FAMILIARITY;
		}
		return statement;
	}

	/*
	 * This method gets OrganizationType XMLObject and set Organization details,
	 * Rolodex Details to it and finally return OrganizationType Details with
	 * Rolodex details XML Object.
	 */
	private OrganizationType getOrganizationType(ProposalSite proposalSite) {
		OrganizationType organizationType = OrganizationType.Factory
				.newInstance();
		Rolodex rolodex = proposalSite.getRolodex();
		String organizationId = proposalSite.getOrganizationId();
		Organization organization = proposalSite.getOrganization();
		if (organizationId != null) {
			organizationType.setOrganizationID(organizationId);
			if (organization.getOrganizationName() != null) {
				organizationType.setOrganizationName(organization
						.getOrganizationName());
			}
			if (rolodex != null) {
				String contactName = rolodex.getOrganization();
				if (contactName != null
						&& !contactName.equalsIgnoreCase(organization
								.getOrganizationName())) {
					organizationType.setContactName(contactName);
				}
			}
		}
		setRolodexDetailsToOrganizationType(organizationType, rolodex);
		return organizationType;
	}

	/*
	 * This method sets Rolodex Contact details to OrganizationType XMLObject
	 */
	private void setRolodexDetailsToOrganizationType(
			OrganizationType organizationType, Rolodex rolodox) {
		if (rolodox != null) {
			organizationType.setContactName(rolodox.getOrganization());
			String addressLine1 = rolodox.getAddressLine1();
			if (addressLine1 != null) {
				organizationType.setAddress1(addressLine1);
			}
			String addressLine2 = rolodox.getAddressLine2();
			if (addressLine2 != null) {
				organizationType.setAddress2(addressLine2);
			}
			String addressLine3 = rolodox.getAddressLine3();
			if (addressLine3 != null) {
				organizationType.setAddress3(addressLine3);
			}
			String city = rolodox.getCity();
			if (city != null) {
				organizationType.setCity(city);
			}
			String state = rolodox.getState();
			if (state != null) {
				organizationType.setState(state);
			}
			String postalCode = rolodox.getPostalCode();
			if (postalCode != null) {
				organizationType.setPostCode(postalCode);
			}
			String county = rolodox.getCounty();
			if (county != null) {
				organizationType.setCountry(county);
			}
		}
	}

	/*
	 * This method will set the values to school info attributes and finally
	 * returns SchoolInfoType XML Object
	 */
	private PCschoolInfoType getSchoolInfoType() {
		PCschoolInfoType schoolInfoType = PCschoolInfoType.Factory
				.newInstance();
		String schoolName = getCertificationParameterValue(SCHOOL_NAME);
		String schoolAcronym = getCertificationParameterValue(SCHOOL_ACRONYM);
		if (schoolName != null) {
			schoolInfoType.setSchoolName(schoolName);
		}
		if (schoolAcronym != null) {
			schoolInfoType.setAcronym(schoolAcronym);
		}
		return schoolInfoType;
	}

	/*
	 * This method sets the value of investigator and finally returns
	 * Investigator XML Object
	 */
	private Investigator getInvestigator(ProposalPerson proposalPerson) {
		Investigator investigator = Investigator.Factory.newInstance();
		investigator.setPersonID(proposalPerson.getPersonId());
		investigator.setPersonName(getPersonName(proposalPerson));
		investigator.setPrincipalInvFlag(proposalPerson
				.getProposalPersonRoleId().equals(PRINCIPAL_INVESTIGATOR_ROLE));
		return investigator;
	}

	/*
	 * This method sets value of sponsor from SponsorBO by checking the
	 * sponsorTypeCode and finally returns sponsor XML Object
	 */
	private Sponsor getSponsor(String sponsorCode, String primeSponsorCode,
			String sponsorName, org.kuali.kra.bo.Sponsor sponsorBO) {
		Sponsor sponsor = Sponsor.Factory.newInstance();
		sponsor.setSponsorName(sponsorName);
		sponsor.setSponsorCode(sponsorCode);
		String sponsorType = SPONSOR_TYPE_NONFED;
		if (sponsorBO.getSponsorTypeCode().equals(SPONSOR_TYPE_CODE_ZERO)
				|| getSponsorTypeCodeFromSponsor(primeSponsorCode).equals(
						SPONSOR_TYPE_CODE_ZERO)) {
			sponsorType = SPONSOR_TYPE_FED;
		}
		sponsor.setSponsorType(sponsorType);
		return sponsor;
	}

	/*
	 * This method gets SponsorTypeCode from Sponsor based on sponsorCode from
	 * database
	 */
	private String getSponsorTypeCodeFromSponsor(String sponsorCode) {
		Map<String, String> sponsorCodeMap = new HashMap<String, String>();
		sponsorCodeMap.put(Constants.SPONSOR_CODE, sponsorCode);
		String sponsorTypeCode = EMPTY_STRING;
		org.kuali.kra.bo.Sponsor sponsor = (org.kuali.kra.bo.Sponsor) getBusinessObjectService()
		            .findByPrimaryKey(org.kuali.kra.bo.Sponsor.class,sponsorCodeMap);
		if (sponsor != null) {
			sponsorTypeCode = sponsor.getSponsorTypeCode();
		}
		return sponsorTypeCode;
	}

	/*
	 * This method gets List of ArgValueLookup object from table based on
	 * argumentName and finally return list of values as questionIds
	 */
	private List<String> getQuestionIdsFromArgValueLookup() {
		Map<String, String> questionMap = new HashMap<String, String>();
		questionMap.put(ARGUMENT_NAME, PI_QUESTION);
		List<ArgValueLookup> argValueLookupList = (List<ArgValueLookup>) getBusinessObjectService()
				.findMatching(ArgValueLookup.class, questionMap);
		List<String> questionIds = new ArrayList<String>();
		for (ArgValueLookup argValueLookup : argValueLookupList) {
			questionIds.add(argValueLookup.getValue());
		}
		return questionIds;
	}

	/*
	 * This method check availability of sponsorGroup, sponsorCode and level1 in
	 * SponsorHirarchy
	 */
	private boolean isSponsorCodeInSponsorHierarchy() {
		boolean available = false;
		Map<String, String> sponsorHirarchyMap = new HashMap<String, String>();
		sponsorHirarchyMap.put(Constants.HIERARCHY_NAME, SPONSOR_GROUPS);
		List<SponsorHierarchy> sponsorHierarchyList = (List<SponsorHierarchy>) getBusinessObjectService()
				.findMatching(SponsorHierarchy.class, sponsorHirarchyMap);
		for (SponsorHierarchy sponsorHierarchy : sponsorHierarchyList) {
			String sponsorCode = sponsorHierarchy.getSponsorCode();
			String level1 = sponsorHierarchy.getLevel1();
			if ((sponsorCode.equals(Constants.NIH_SPONSOR_CODE) || sponsorCode
					.equals(NSF_SPONSOR_CODE))
					&& (level1.equalsIgnoreCase(Constants.NIH_SPONSOR_ACRONYM) || level1
							.equalsIgnoreCase(NSF_SPONSOR_ACRONYM))) {
				available = true;
			}
		}
		return available;
	}

	/*
	 * This method returns the full name of the person in the given
	 * ProposalPerson
	 */
	private String getPersonName(ProposalPerson proposalPerson) {
		String personName = null;
		if (proposalPerson.getPerson() != null) {
			personName = proposalPerson.getPerson().getFullName();
		} else {
			Map<String, String> conditionMap = new HashMap<String, String>();
			conditionMap.put(KEY_ROLODEX_ID, proposalPerson.getRolodexId()
					.toString());
			Rolodex rolodex = (Rolodex) getBusinessObjectService().findByPrimaryKey(
					Rolodex.class, conditionMap);
			if (rolodex != null) {
				personName = rolodex.getFullName();
			}
		}
		return personName;
	}
	private String getCertificationParameterValue(String param){
		String value=null;
		try{
			value = PrintingUtils.getParameterValue(param);
		}catch (Exception e) {
		}
		return value;
	}
}
