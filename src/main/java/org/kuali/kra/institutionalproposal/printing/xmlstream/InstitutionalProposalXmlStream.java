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
package org.kuali.kra.institutionalproposal.printing.xmlstream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import noNamespace.ActivityType;
import noNamespace.AnticipatedAwardType;
import noNamespace.BudgetDataType;
import noNamespace.CostSharingType;
import noNamespace.IDCRateType;
import noNamespace.IPDisclosureItemType;
import noNamespace.IPKeyPersonType;
import noNamespace.IPSchoolInfoType;
import noNamespace.IPsponsorType;
import noNamespace.InstProposalMasterData;
import noNamespace.InstituteProposalDocument;
import noNamespace.InvestigatorType2;
import noNamespace.KeyPersonType;
import noNamespace.MailingInfoType;
import noNamespace.NSFcodeType;
import noNamespace.NoticeOfOppType;
import noNamespace.OtherGroupDetailsTypes;
import noNamespace.OtherGroupTypes;
import noNamespace.PersonType;
import noNamespace.ProposalStatusType;
import noNamespace.ProposalType;
import noNamespace.ScienceCodeType;
import noNamespace.SpecialReviewType2;
import noNamespace.UnitType;
import noNamespace.InstituteProposalDocument.InstituteProposal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.bo.NoticeOfOpportunity;
import org.kuali.kra.bo.NsfCode;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.SpecialReviewApprovalType;
import org.kuali.kra.bo.SpecialReviewType;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.common.specialreview.bo.SpecialReview;
import org.kuali.kra.costshare.CostShareService;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.InstitutionalProposalConstants;
import org.kuali.kra.institutionalproposal.ProposalStatus;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPerson;
import org.kuali.kra.institutionalproposal.contacts.InstitutionalProposalPersonUnit;
import org.kuali.kra.institutionalproposal.customdata.InstitutionalProposalCustomData;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalComment;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalCostShare;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalScienceKeyword;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposalUnrecoveredFandA;
import org.kuali.kra.institutionalproposal.printing.InstitutionalProposalPrintType;
import org.kuali.kra.institutionalproposal.printing.service.InstitutionalProposalPersonService;
import org.kuali.kra.institutionalproposal.specialreview.InstitutionalProposalSpecialReview;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.printing.util.PrintingUtils;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.service.InstitutionalProposalCustomAttributeService;
import org.kuali.rice.kns.service.ParameterConstants;
import org.kuali.rice.kns.service.ParameterService;

/**
 * This class generates XML that conforms with the XSD related to Institution
 * Proposal Report. The data for XML is derived from
 * {@link ResearchDocumentBase} and {@link Map} of details passed to the class.
 * 
 */
public class InstitutionalProposalXmlStream extends
		InstitutionalProposalBaseStream {
    
    private static final Log LOG = LogFactory.getLog(InstitutionalProposalXmlStream.class);
	
	private static final String PROPOSAL_SUMMARY_COMMENT_CODE;
	private static final String COST_SHARING_COMMENT_CODE;
	private static final String INDIRECT_COST_COMMENT_CODE;
	private static final String PROTOCOL_NUMBER = "protocolNumber";
	private static final String SPECIAL_REVIEW_APPROVAL_CODE = "5";
	private static final String SPONSOR_CODE = "sponsorCode";
	private static final String NSF_CODE = "nsfCode";
	private static final String NOTICE_OF_OPPORTUNITY_CODE = "noticeOfOpportunityCode";
	private static final String PROPOSAL_TYPE_CODE = "proposalTypeCode";
	private static final String PROPOSAL_STATUS_CODE = "proposalStatusCode";
	private static final String SCHOOL_NAME = "SCHOOL_NAME";
	private static final String SCHOOL_ACRONYM = "SCHOOL_ACRONYM";
	private InstitutionalProposalPersonService institutionalProposalPersonService;
	
	static{
		//FIXME below hardcoded values to be fixed once InstituteProposalComments BO is fully integrated
		PROPOSAL_SUMMARY_COMMENT_CODE = "21";
		COST_SHARING_COMMENT_CODE = "22";
		INDIRECT_COST_COMMENT_CODE = "23";
	}
	
	
	/**
	 * This method generates XML for Institution Proposal Report. It uses data
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
		InstitutionalProposal institutionalProposal = (InstitutionalProposal) printableBusinessObject;
		InstituteProposalDocument instituteProposalDocument = InstituteProposalDocument.Factory
				.newInstance();
		InstituteProposal instituteProposalXmlObject = getInstituteProposal(institutionalProposal);
		instituteProposalDocument
				.setInstituteProposal(instituteProposalXmlObject);
		xmlObjectList.put(
				InstitutionalProposalPrintType.INSTITUTIONAL_PROPOSAL_REPORT
						.getInstitutionalProposalPrintType(),
				instituteProposalDocument);		
		return xmlObjectList;
	}

	/*
	 * This method will set the values to institute proposal xml object and
	 * finally returns the institute proposal xml object.
	 */
	private InstituteProposal getInstituteProposal(
			InstitutionalProposal institutionalProposal) {
		InstituteProposal instituteProposalXmlObject = InstituteProposal.Factory
				.newInstance();
		List<ProposalPerson> proposalPersons = institutionalProposalPersonService
				.getInvestigatorsFromDevelopmentProposal(institutionalProposal
						.getProposalNumber());
		instituteProposalXmlObject
				.setInstProposalMaster(getInstProposalMasterData(institutionalProposal));
		instituteProposalXmlObject.setInvestigatorsArray(getInvestigatorTypes(
				institutionalProposal));
		instituteProposalXmlObject
				.setBudgetData(getBudgetDataType(institutionalProposal));
		instituteProposalXmlObject
				.setMailingInfo(getMailingInfoType(institutionalProposal));
		instituteProposalXmlObject
				.setIDCRatesArray(getIdcRateTypes(institutionalProposal));
		if (institutionalProposal.getUnrecoveredFandAComment() != null) {
			instituteProposalXmlObject
					.setIDCRatesComments(institutionalProposal.getUnrecoveredFandAComment()
							.getComments());
		}
		if (institutionalProposal.getCostShareComment() != null) {
			instituteProposalXmlObject
					.setCostSharingComments(institutionalProposal.getCostShareComment()
							.getComments());
		}
		instituteProposalXmlObject
				.setSpecialReviewsArray(getSpecialReviewTypes(institutionalProposal));
		instituteProposalXmlObject
				.setCostSharingInfoArray(getCostSharingTypes(institutionalProposal));
		instituteProposalXmlObject
				.setScienceCodeArray(getScienceCodes(institutionalProposal));
		instituteProposalXmlObject.setSchoolInfo(getSchoolInfoType());
		// TODO : To be fixed
		IPDisclosureItemType[] disclosureItemTypes = getDisclosureItems(institutionalProposal);
		instituteProposalXmlObject.setDisclosureItemArray(disclosureItemTypes);
		instituteProposalXmlObject.setKeyPersonsArray(getKeyPersons(
				institutionalProposal));
		instituteProposalXmlObject.setCostSharingProjectPeriodFieldDescription(getProjectPeriodFieldDescription());
		instituteProposalXmlObject.setCFDANum(institutionalProposal.getCfdaNumber());
		instituteProposalXmlObject.setOpportunityID(institutionalProposal.getOpportunity());
	    instituteProposalXmlObject.setOtherData(getCustomData(institutionalProposal));
		
		return instituteProposalXmlObject;
	}
	
	private String getProjectPeriodFieldDescription() {
        String retVal =  KraServiceLocator.getService(CostShareService.class).getCostShareLabel(false);
        return retVal;
    }
	
	/*
	 * This method will iterate over  InstitutionalProposalCustomData 
	 * and set records with group name 'other' to the xmlObject 'otherGroup'. 
	 */
	private OtherGroupTypes getCustomData(
	        InstitutionalProposal institutionalProposal){	    
	    InstitutionalProposalCustomAttributeService institutionalProposalCustomAttributeService = KraServiceLocator.getService(InstitutionalProposalCustomAttributeService.class);
        Map<String, CustomAttributeDocument> customAttributeDocuments = institutionalProposalCustomAttributeService.getDefaultInstitutionalProposalCustomAttributeDocuments();
        OtherGroupTypes otherGroup=OtherGroupTypes.Factory.newInstance();        
        List<OtherGroupDetailsTypes> otherGroupDetailsTypesList = new LinkedList<OtherGroupDetailsTypes>();       
       
        for (Map.Entry<String, CustomAttributeDocument> customAttributeDocumentEntry : customAttributeDocuments.entrySet()) {
            OtherGroupDetailsTypes otherGroupDetails=OtherGroupDetailsTypes.Factory.newInstance();                  
          
            for(InstitutionalProposalCustomData custData:institutionalProposal.getInstitutionalProposalCustomDataList()){  
               
                if(custData.getCustomAttributeId() == (long)customAttributeDocumentEntry.getValue().getCustomAttribute().getId()
                        && customAttributeDocuments.get(custData.getCustomAttributeId().toString()).getCustomAttribute().getGroupName()
                            .equalsIgnoreCase(KraServiceLocator.getService(ParameterService.class).
                                 getParameterValue(InstitutionalProposalConstants.INSTITUTIONAL_PROPOSAL_NAMESPACE,ParameterConstants.DOCUMENT_COMPONENT,Constants.INSTITUTE_PROPOSAL_OTHER_GROUP))){    
                    
                    otherGroup.setGroupName(customAttributeDocuments.get(custData.getCustomAttributeId().toString()).getCustomAttribute().getGroupName());
                    otherGroupDetails.setColumnValue(custData.getValue());                  
                    otherGroupDetails.setColumnName(customAttributeDocuments.get(custData.getCustomAttributeId().toString()).getCustomAttribute().getLabel());                                       
                    otherGroupDetailsTypesList.add(otherGroupDetails);
                    break;
                }
             }
          }    
                otherGroup.setOtherGroupDetailsArray(otherGroupDetailsTypesList.toArray(new OtherGroupDetailsTypes[0]));            
        
        return otherGroup;	    
	}

	/*
	 * This method will set the values to key person type.It Iterate over
	 * proposal persons check for is it investigator.If the proposal person is
	 * investigator then get attributes from the proposal person and set to the
	 * key person type.
	 */
	private IPKeyPersonType[] getKeyPersons(
			InstitutionalProposal institutionalProposal) {
		List<KeyPersonType> keyPersonTypes = new ArrayList<KeyPersonType>();
		IPKeyPersonType keyPersonType = null;
		for (InstitutionalProposalPerson proposalPerson : institutionalProposal.getProjectPersons()) {
			if (proposalPerson.isPrincipalInvestigator() || proposalPerson.isCoInvestigator() || proposalPerson.isKeyPerson()) {
				keyPersonType = IPKeyPersonType.Factory.newInstance();
				if (institutionalProposal.getProposalNumber() != null) {
					keyPersonType.setProposalNumber(institutionalProposal
							.getProposalNumber());
				}
				if (proposalPerson.getPersonId() != null) {
					keyPersonType.setPersonId(proposalPerson.getPersonId());
				}
				KcPerson person = proposalPerson.getPerson();
				if (person != null && person.getFullName() != null) {
					keyPersonType.setPersonName(person.getFullName());
				}
				ContactRole role = proposalPerson.getContactRole();
				if (role != null && role.getRoleDescription() != null) {
					keyPersonType.setRoleName(role.getRoleDescription());
				}
				if (proposalPerson.getPerson().getAddressLine1() != null) {
					keyPersonType.setPersonAddress(proposalPerson.getPerson()
							.getAddressLine1());
				}
				if (proposalPerson.getTotalEffort() != null) {
					keyPersonType.setPercentEffort(proposalPerson
							.getTotalEffort().bigDecimalValue());
				}
					keyPersonType.setFaculty(proposalPerson.isFaculty());
				if (proposalPerson.getRolodexId() != null) {
					keyPersonType.setNonEmployee(true);
				}
			}
		}
		return keyPersonTypes.toArray(new IPKeyPersonType[0]);
	}

	/*
	 * This method will set the values to investigator type .It iterates over
	 * institute proposal persons of institute proposal and checks for role
	 * investigator type if investigator type is found set the values to
	 * investigatorType xml object and finally returns array of investigatorType
	 * xml object.
	 */
	private InvestigatorType2[] getInvestigatorTypes(
			InstitutionalProposal institutionalProposal) {
		List<InvestigatorType2> investigatorTypesList = new ArrayList<InvestigatorType2>();
		InvestigatorType2 investigatorType = null;
		for (InstitutionalProposalPerson proposalPerson : institutionalProposal.getProjectPersons()) {
			if (proposalPerson.isPrincipalInvestigator() || proposalPerson.isCoInvestigator() || proposalPerson.isKeyPerson()) {
				investigatorType = InvestigatorType2.Factory.newInstance();
				PersonType personType = PersonType.Factory.newInstance();
				if (proposalPerson.getPerson().getAddressLine1() != null) {
					personType.setAddress(proposalPerson.getPerson().getAddressLine1());
				}
				if (proposalPerson.getPerson().getCity() != null) {
					personType.setCity(proposalPerson.getPerson().getCity());
				}
				if (proposalPerson.getPerson().getFirstName() != null) {
					personType.setFirstName(proposalPerson.getPerson().getFirstName());
				}
				if (proposalPerson.getFullName() != null) {
					personType.setFullName(proposalPerson.getFullName());
				}
				if (proposalPerson.getPerson().getLastName() != null) {
					personType.setLastName(proposalPerson.getPerson().getLastName());
				}
				if (proposalPerson.getPerson().getMiddleName() != null) {
					personType.setMiddleName(proposalPerson.getPerson().getMiddleName());
				}
				if (proposalPerson.getPhoneNumber() != null) {
					personType.setPhone(proposalPerson.getPhoneNumber());
				}
				if (proposalPerson.getPerson().getState() != null) {
					personType.setState(proposalPerson.getPerson().getState());
				}
				if (proposalPerson.getPerson().getPostalCode() != null) {
					personType.setZip(proposalPerson.getPerson().getPostalCode());
				}
				investigatorType.setPIName(personType);
				investigatorType.setFacultyFlag(proposalPerson
						.isFaculty());
				investigatorType.setPrincipalInvFlag(proposalPerson
						.isPrincipalInvestigator());
				List<UnitType> unitTypes = getUnitTypes(proposalPerson);
				investigatorType.setUnitArray(unitTypes
						.toArray(new UnitType[0]));
				investigatorTypesList.add(investigatorType);
			}
		}
		return investigatorTypesList.toArray(new InvestigatorType2[0]);
	}

	/*
	 * This method will set the values to unit types xml object attributes. It
	 * basically iterates over the proposal person units.
	 */
	private List<UnitType> getUnitTypes(InstitutionalProposalPerson proposalPerson) {
		List<UnitType> unitTypes = new ArrayList<UnitType>();
		UnitType unitType = null;
		for (InstitutionalProposalPersonUnit proposalPersonUnit : proposalPerson.getUnits()) {
			unitType = UnitType.Factory.newInstance();
			unitType.setLeadUnitFlag(proposalPersonUnit.isLeadUnit());
			Unit unit = proposalPersonUnit.getUnit();
			if (unit != null) {
				if (unit.getUnitName() != null) {
					unitType.setUnitName(unit.getUnitName());
				}
				if (unit.getUnitNumber() != null) {
					unitType.setUnitNumber(unit.getUnitNumber());
				}
			}
			unitTypes.add(unitType);
		}
		return unitTypes;
	}

	/*
	 * This method will set the values to disclosure items xml object.
	 */
	private IPDisclosureItemType[] getDisclosureItems(
			InstitutionalProposal institutionalProposal) {
		// TODO: To be fixed
		List<IPDisclosureItemType> disclosureItemTypesList = new ArrayList<IPDisclosureItemType>();
		IPDisclosureItemType disclosureItemType = IPDisclosureItemType.Factory
				.newInstance();
		disclosureItemTypesList.add(disclosureItemType);
		return disclosureItemTypesList.toArray(new IPDisclosureItemType[0]);
	}

	/*
	 * This method will set the values to school info attributes and finally
	 * returns SchoolInfoType Xml object. Get the school name and school acronym
	 * from the data base.
	 */
	private IPSchoolInfoType getSchoolInfoType() {
		IPSchoolInfoType schoolInfoType = IPSchoolInfoType.Factory
				.newInstance();
		String schoolName = getIPParameterValue(SCHOOL_NAME);
		String schoolAcronym = getIPParameterValue(SCHOOL_ACRONYM);
		if (schoolName != null) {
			schoolInfoType.setSchoolName(schoolName);
		}
		if (schoolAcronym != null) {
			schoolInfoType.setAcronym(schoolAcronym);
		}
		return schoolInfoType;
	}

	/*
	 * This method will set the values to science codes xml object. Iterates
	 * over institute proposal science keyword ,get science keyword code and
	 * science keyword description set to science code Type xml object
	 * attributes and finally return science code type array.
	 */
	private ScienceCodeType[] getScienceCodes(
			InstitutionalProposal institutionalProposal) {
		ScienceCodeType scienceCodeType = null;
		List<ScienceCodeType> scienceCodeTypelist = new ArrayList<ScienceCodeType>();
		for (InstitutionalProposalScienceKeyword institutionalProposalScienceKeyword : institutionalProposal
				.getInstitutionalProposalScienceKeywords()) {
			scienceCodeType = ScienceCodeType.Factory.newInstance();
			if (institutionalProposalScienceKeyword.getScienceKeywordCode() != null) {
				scienceCodeType.
				    setScienceCode(institutionalProposalScienceKeyword.getScienceKeywordCode());
			}
			if (institutionalProposalScienceKeyword.getScienceKeyword().getDescription() != null) {
				scienceCodeType.
				    setScienceCodeDesc(institutionalProposalScienceKeyword.getScienceKeyword().getDescription());
			}
			scienceCodeTypelist.add(scienceCodeType);
		}
		return scienceCodeTypelist.toArray(new ScienceCodeType[0]);
	}

	/*
	 * This method will set the values to cost sharing type xml object. Iterates
	 * over institute proposal cost share of institute proposal get all values
	 * set to cost sharing type attributes and finally return cost sharing type
	 * xml object array.
	 */
	private CostSharingType[] getCostSharingTypes(
			InstitutionalProposal institutionalProposal) {
		List<CostSharingType> costSharingTypes = new ArrayList<CostSharingType>();
		for (InstitutionalProposalCostShare institutionalProposalCostShare : institutionalProposal
				.getInstitutionalProposalCostShares()) {
		    CostSharingType costSharingType = CostSharingType.Factory.newInstance();		    
			if (institutionalProposalCostShare.getAmount() != null) {
				costSharingType.setAmount(institutionalProposalCostShare
						.getAmount().doubleValue());
			}
			institutionalProposalCostShare.refreshReferenceObject("costShareType");
			if (institutionalProposalCostShare.getCostShareType() != null) {
				costSharingType.setCostSharingType(institutionalProposalCostShare.getCostShareTypeCode() + " - " + 
				        institutionalProposalCostShare.getCostShareType().getDescription());
			}
			if (institutionalProposalCostShare.getProjectPeriod() != null) {
				costSharingType.setFY(institutionalProposalCostShare
						.getProjectPeriod());
			}
			if (institutionalProposalCostShare.getSourceAccount() != null) {
				costSharingType.setSourceAccount(institutionalProposalCostShare
						.getSourceAccount());
			}
			if (institutionalProposalCostShare.getCostSharePercentage() != null) {
				costSharingType.setPercentage(institutionalProposalCostShare
						.getCostSharePercentage().doubleValue());
			}
			costSharingTypes.add(costSharingType);
		}
		return costSharingTypes.toArray(new CostSharingType[0]);
	}

	/*
	 * This method will set the values to institute proposal special review type
	 * xml object.Iterate over institute proposal special review type initially
	 * check for protocol number if not null fetch protocol from database for
	 * this protocol number check for approval type code equal to 5 ,if equal
	 * then set protocol description to special review status of
	 * InstitutionalProposalSpecialReviewType object otherwise set approval type
	 * description .In addition to that set approval date and application date
	 * of protocol
	 * 
	 * In case of protocol number null or protocol not found in database for
	 * this protocol number then set the values to application date ,approval
	 * date and special review status of institutionalProposalSpecialReview
	 */
	private SpecialReviewType2[] getSpecialReviewTypes(
			InstitutionalProposal institutionalProposal) {
		List<SpecialReviewType2> specialReviewTypes = new ArrayList<SpecialReviewType2>();
		SpecialReviewType2 specialReviewType = null;
		for (InstitutionalProposalSpecialReview institutionalProposalSpecialReview : institutionalProposal
				.getSpecialReviews()) {
			specialReviewType = SpecialReviewType2.Factory.newInstance();
			Protocol protocol = null;
			String protocolNumber = institutionalProposalSpecialReview
					.getProtocolNumber();
			institutionalProposalSpecialReview.refreshNonUpdateableReferences();
			SpecialReviewApprovalType specialReviewApprovalType = institutionalProposalSpecialReview
					.getApprovalType();
			if (protocolNumber != null
					&& (protocol = getProtocolInfo(protocolNumber)) != null) {
				specialReviewType.setProtocolNumber(protocolNumber);
				if (specialReviewApprovalType.getApprovalTypeCode() != null
						&& specialReviewApprovalType.getApprovalTypeCode()
								.equals(SPECIAL_REVIEW_APPROVAL_CODE)) {
					specialReviewType.setSpecialReviewStatus(protocol
							.getDescription());
				} else {
					if (specialReviewApprovalType != null) {
						specialReviewType
								.setSpecialReviewStatus(specialReviewApprovalType
										.getDescription());
					}
				}
				if (protocol.getSubmissionDate() != null) {
					specialReviewType.setApplicationDate(dateTimeService
							.getCalendar(protocol.getInitialSubmissionDate()));
				}
				if (protocol.getApprovalDate() != null) {
					specialReviewType.setApprovalDate(dateTimeService
							.getCalendar(protocol.getApprovalDate()));
				}
			} else {
				if (institutionalProposalSpecialReview.getApplicationDate() != null) {
					specialReviewType.setApplicationDate(dateTimeService
							.getCalendar(institutionalProposalSpecialReview
									.getApplicationDate()));
				}
				if (institutionalProposalSpecialReview.getApprovalDate() != null) {
					specialReviewType.setApprovalDate(dateTimeService
							.getCalendar(institutionalProposalSpecialReview
									.getApprovalDate()));
				}
				if (specialReviewApprovalType != null) {
					specialReviewType
							.setSpecialReviewStatus(specialReviewApprovalType
									.getDescription());
				}
				specialReviewType.setProtocolNumber(institutionalProposalSpecialReview.getProtocolNumber());
			}
			SpecialReviewType specialReview = institutionalProposalSpecialReview
					.getSpecialReviewType();
			if (specialReview != null && specialReview.getDescription() != null) {
				specialReviewType.setSpecialReviewType(specialReview
						.getDescription());
			}
			if (institutionalProposalSpecialReview.getComments() != null) {
				specialReviewType
						.setComments(institutionalProposalSpecialReview
								.getComments());
			}
			specialReviewTypes.add(specialReviewType);
		}
		return specialReviewTypes.toArray(new SpecialReviewType2[0]);
	}

	/*
	 * This method will fetch the protocol for protocol number from data base.
	 * if protocol found in data base then returns protocol otherwise null
	 */
	private Protocol getProtocolInfo(String protocolNumber) {
		Protocol protocol = null;
		Map<String, String> protocolMap = new HashMap<String, String>();
		protocolMap.put(PROTOCOL_NUMBER, String.valueOf(protocolNumber));
		List<Protocol> protocolList = (List<Protocol>) businessObjectService
				.findMatching(Protocol.class, protocolMap);
		if (protocolList != null && !protocolList.isEmpty()) {
			protocol = protocolList.get(0);
		}
		return protocol;
	}

	/*
	 * This method will set the values to IDCRateType xml object. Iterates over
	 * InstitutionalProposalUnrecoveredFandA of InstitutionalProposal set the
	 * values to IDCRateType xml object and finally returns the IDCRateType xml
	 * object array.
	 */
	private IDCRateType[] getIdcRateTypes(
			InstitutionalProposal institutionalProposal) {
		List<IDCRateType> idcRateTypes = new ArrayList<IDCRateType>();
		IDCRateType idcRateType = null;
		for (InstitutionalProposalUnrecoveredFandA institutionalProposalUnrecoveredFandA : institutionalProposal
				.getInstitutionalProposalUnrecoveredFandAs()) {
			idcRateType = IDCRateType.Factory.newInstance();
			if (institutionalProposalUnrecoveredFandA.getFiscalYear() != null) {
				idcRateType.setFY(institutionalProposalUnrecoveredFandA
						.getFiscalYear());
			}
			idcRateType.setOnCampus(institutionalProposalUnrecoveredFandA
					.getOnCampusFlag());
			if (institutionalProposalUnrecoveredFandA.getSourceAccount() != null) {
				idcRateType
						.setSourceAccount(institutionalProposalUnrecoveredFandA
								.getSourceAccount());
			}
			if (institutionalProposalUnrecoveredFandA
					.getUnderrecoveryOfIndirectcost() != null) {
				idcRateType
						.setUnderRecovery(institutionalProposalUnrecoveredFandA
								.getUnderrecoveryOfIndirectcost().doubleValue());
			}
			institutionalProposalUnrecoveredFandA.refreshReferenceObject("indirectcostRateType");
			if (institutionalProposalUnrecoveredFandA
					.getIndirectcostRateType() != null) {
				idcRateType.setRateType(institutionalProposalUnrecoveredFandA.getIndirectcostRateTypeCode() + " - " + 
				        institutionalProposalUnrecoveredFandA.getIndirectcostRateType().getDescription());
			}
			if (institutionalProposalUnrecoveredFandA
					.getApplicableIndirectcostRate() != null) {
				idcRateType.setRate(institutionalProposalUnrecoveredFandA
						.getApplicableIndirectcostRate().doubleValue());
			}
			idcRateTypes.add(idcRateType);
		}
		return idcRateTypes.toArray(new IDCRateType[0]);
	}

	/*
	 * This method will set the values to MailingInfoType xml object. Get the
	 * values from InstitutionalProposal set to MailingInfoType xml object and
	 * finally return MailingInfoType xml object.
	 */
	private MailingInfoType getMailingInfoType(
			InstitutionalProposal institutionalProposal) {
		MailingInfoType mailingInfoType = MailingInfoType.Factory.newInstance();
		if (institutionalProposal.getDeadlineDate() != null) {
			mailingInfoType.setDeadlineDate(dateTimeService
					.getCalendar(institutionalProposal.getDeadlineDate()));
		}
		mailingInfoType.setDeadlineType(institutionalProposal
				.getDeadlineType());
		mailingInfoType.setMailByOSP(institutionalProposal
				.getMailBy());
		mailingInfoType.setMailType(institutionalProposal
				.getMailType());
		if (institutionalProposal.getMailAccountNumber() != null) {
			mailingInfoType.setMailAccount(institutionalProposal
					.getMailAccountNumber());
		}
		if (institutionalProposal.getNumberOfCopies() != null) {
			mailingInfoType.setNumberCopies(Integer
					.valueOf(institutionalProposal.getNumberOfCopies()));
		}
		Rolodex rolodex = institutionalProposal.getRolodex();
		if (rolodex != null) {
			PersonType personType = PersonType.Factory.newInstance();
			if (rolodex.getFirstName() != null) {
				personType.setFirstName(rolodex.getFirstName());
			}
			if (rolodex.getLastName() != null) {
				personType.setLastName(rolodex.getLastName());
			}
			if (rolodex.getCity() != null) {
				personType.setCity(rolodex.getCity());
			}
			if (rolodex.getMiddleName() != null) {
				personType.setMiddleName(rolodex.getMiddleName());
			}
			if (rolodex.getPhoneNumber() != null) {
				personType.setPhone(rolodex.getPhoneNumber());
			}
			if (rolodex.getState() != null) {
				personType.setState(rolodex.getState());
			}
			if (rolodex.getFullName() != null) {
				personType.setFullName(rolodex.getFullName());
			}
			if (rolodex.getPostalCode() != null) {
				personType.setZip(rolodex.getPostalCode());
			}
			if (rolodex.getAddressLine1() != null) {
				personType.setAddress(rolodex.getAddressLine1());
			}
			mailingInfoType.setMailToPerson(personType);
		}
		if (institutionalProposal.getProposalComments() != null
				&& institutionalProposal.getDeliveryComment().getComments() != null) {
			mailingInfoType.setComments(institutionalProposal
					.getDeliveryComment().getComments());
		}
		return mailingInfoType;
	}

	/*
	 * This method will set the values to budget data type xml object attributes
	 * by getting values from the institute proposal.
	 */
	private BudgetDataType getBudgetDataType(
			InstitutionalProposal institutionalProposal) {
		BudgetDataType budgetDataType = BudgetDataType.Factory.newInstance();
		budgetDataType.setAccountType(String.valueOf(institutionalProposal
				.getTypeOfAccount()));
		if (institutionalProposal.getRequestedStartDateInitial() != null) {
			budgetDataType.setRequestedStartDateInitial(dateTimeService
					.getCalendar(institutionalProposal
							.getRequestedStartDateInitial()));
		}
		if (institutionalProposal.getRequestedEndDateInitial() != null) {
			budgetDataType.setRequestedEndDateInitial(dateTimeService
					.getCalendar(institutionalProposal
							.getRequestedEndDateInitial()));
		}
		if (institutionalProposal.getRequestedStartDateTotal() != null) {
			budgetDataType.setRequestedStartDateTotal(dateTimeService
					.getCalendar(institutionalProposal
							.getRequestedStartDateTotal()));
		}
		if (institutionalProposal.getRequestedEndDateTotal() != null) {
			budgetDataType.setRequestedEndDateTotal(dateTimeService
					.getCalendar(institutionalProposal
							.getRequestedEndDateTotal()));
		}
		if (institutionalProposal.getTotalDirectCostInitial() != null) {
			budgetDataType.setTotalDirectCostInitial(institutionalProposal
					.getTotalDirectCostInitial().bigDecimalValue());
		}
		if (institutionalProposal.getTotalDirectCostTotal() != null) {
			budgetDataType.setTotalDirectCostTotal(institutionalProposal
					.getTotalDirectCostTotal().bigDecimalValue());
		}
		if (institutionalProposal.getTotalIndirectCostInitial() != null) {
			budgetDataType.setTotalIndirectCostInitial(institutionalProposal
					.getTotalIndirectCostInitial().bigDecimalValue());
		}
        if (institutionalProposal.getTotalIndirectCostTotal() != null) {
            budgetDataType.setTotalIndirectCostTotal(institutionalProposal
                    .getTotalIndirectCostTotal().bigDecimalValue());
        }		
		if (institutionalProposal.getTotalInitialCost() != null) {
			budgetDataType.setTotalCostInitial(institutionalProposal
					.getTotalInitialCost().bigDecimalValue());
		}
		if (institutionalProposal.getTotalCost() != null) {
			budgetDataType.setTotalCostTotal(institutionalProposal
					.getTotalCost().bigDecimalValue());
		}
		return budgetDataType;
	}

	/*
	 * This method will set the values to Institute proposal master data
	 * xmlobject attributes by getting values from the institute proposal.
	 */
	private InstProposalMasterData getInstProposalMasterData(
			InstitutionalProposal institutionalProposal) {
		InstProposalMasterData instProposalMasterData = InstProposalMasterData.Factory
				.newInstance();
		instProposalMasterData.setProposalNumber(institutionalProposal
				.getProposalNumber());
		if (institutionalProposal.getCurrentAccountNumber() != null) {
			instProposalMasterData.setAccountNumber(institutionalProposal
					.getCurrentAccountNumber());
		}
		if (institutionalProposal.getCurrentAwardNumber() != null) {
			instProposalMasterData.setAwardNumber(institutionalProposal
					.getCurrentAwardNumber());
		}
		setProposalStatus(institutionalProposal, instProposalMasterData);
		setProposalType(institutionalProposal, instProposalMasterData);
		instProposalMasterData.setTitle(institutionalProposal.getTitle());
		setActivityType(institutionalProposal, instProposalMasterData);
		if (institutionalProposal.getSponsorProposalNumber() != null) {
			instProposalMasterData
					.setSponsorProposalNumber(institutionalProposal
							.getSponsorProposalNumber());
		}
		setNoticeOfOpportunity(institutionalProposal, instProposalMasterData);
		setNSFCode(institutionalProposal, instProposalMasterData);
		setSponsor(institutionalProposal, instProposalMasterData);
		setPrimeSponsor(institutionalProposal, instProposalMasterData);
		instProposalMasterData.setHasSubcontracts(institutionalProposal
				.getSubcontractFlag());
		if (institutionalProposal.getGradStudHeadcount() != null) {
		    instProposalMasterData.setGradStudentCount(institutionalProposal.getGradStudHeadcount());
		}
		if (institutionalProposal.getGradStudPersonMonths() != null) {
			instProposalMasterData.setGradStudentmonths(institutionalProposal
					.getGradStudPersonMonths().doubleValue());
		}
		instProposalMasterData.setAccountType(String
				.valueOf(institutionalProposal.getTypeOfAccount()));
		if (institutionalProposal.getSequenceNumber() != null) {
			instProposalMasterData.setSequenceNumber(institutionalProposal
					.getSequenceNumber());
		}
		setComments(institutionalProposal, instProposalMasterData);
		setAnticipatedAwardType(institutionalProposal, instProposalMasterData);
		return instProposalMasterData;
	}

	private void setAnticipatedAwardType(
			InstitutionalProposal institutionalProposal,
			InstProposalMasterData instProposalMasterData) {
		AnticipatedAwardType anticipatedAwardType = AnticipatedAwardType.Factory
				.newInstance();
		if (institutionalProposal.getAwardTypeCode() != null) {
			anticipatedAwardType
					.setAnticipatedAwardTypeCode(institutionalProposal
							.getAwardTypeCode());
		}
		AwardType awardType = institutionalProposal.getAwardType();
		if (awardType != null && awardType.getDescription() != null) {
			anticipatedAwardType.setAnticipatedAwardTypeDesc(awardType
					.getDescription());
		}
		instProposalMasterData.setAnticipatedAwardType(anticipatedAwardType);
	}

	private void setComments(InstitutionalProposal institutionalProposal,
			InstProposalMasterData instProposalMasterData) {
		if (institutionalProposal.getProposalComments() != null) {
			InstitutionalProposalComment institutionalProposalComments = institutionalProposal
					.getSummaryComment();
			if (institutionalProposalComments != null
					&& institutionalProposalComments.getCommentTypeCode()
							.equals(PROPOSAL_SUMMARY_COMMENT_CODE)) {
				instProposalMasterData
						.setComments(institutionalProposalComments
								.getComments());
			}
		}
	}

	private void setPrimeSponsor(InstitutionalProposal institutionalProposal,
			InstProposalMasterData instProposalMasterData) {
		if (institutionalProposal.getPrimeSponsorCode() != null) {
			IPsponsorType primeSponsorType = IPsponsorType.Factory.newInstance();
			String primeSponsorCode = institutionalProposal
					.getPrimeSponsorCode();
			primeSponsorType.setSponsorCode(primeSponsorCode);
			String primeSponsorName = getPrimeSponsorName(primeSponsorCode);
			if (primeSponsorName != null) {
				primeSponsorType.setSponsorName(primeSponsorName);
			}
			instProposalMasterData.setPrimeSponsor(primeSponsorType);
		}
	}

	private void setSponsor(InstitutionalProposal institutionalProposal,
			InstProposalMasterData instProposalMasterData) {
		if (institutionalProposal.getSponsor() != null) {
			Sponsor sponsor = institutionalProposal.getSponsor();
			IPsponsorType sponsorType = IPsponsorType.Factory.newInstance();
			if (sponsor.getSponsorCode() != null) {
				sponsorType.setSponsorCode(sponsor.getSponsorCode());
			}
			if (sponsor.getSponsorName() != null) {
				sponsorType.setSponsorName(sponsor.getSponsorName());
			}
			instProposalMasterData.setSponsor(sponsorType);
		}
	}

	private void setNSFCode(InstitutionalProposal institutionalProposal,
			InstProposalMasterData instProposalMasterData) {
		if (institutionalProposal.getNsfCode() != null) {
			NSFcodeType nsfCodeType = NSFcodeType.Factory.newInstance();
			String nsfCode = institutionalProposal.getNsfCode();
			nsfCodeType.setNSFcode(nsfCode);
			String nsfDesc = getNsfDesc(nsfCode);
			if (nsfDesc != null) {
				nsfCodeType.setNSFcodeDesc(nsfDesc);
			}
			instProposalMasterData.setNSFcode(nsfCodeType);
		}
	}

	private void setNoticeOfOpportunity(
			InstitutionalProposal institutionalProposal,
			InstProposalMasterData instProposalMasterData) {
		if (institutionalProposal.getNoticeOfOpportunityCode() != null) {
			NoticeOfOppType noticeOfOppType = NoticeOfOppType.Factory
					.newInstance();
			String noticeOfOpportunityCode = institutionalProposal
					.getNoticeOfOpportunityCode();
			noticeOfOppType.setNoticeOfOppcode(noticeOfOpportunityCode);
			String noticeOfOpportunityDesc = getNoticeOfOpportunityDesc(noticeOfOpportunityCode);
			if (noticeOfOpportunityDesc != null) {
				noticeOfOppType.setNoticeOfOppDesc(noticeOfOpportunityDesc);
			}
			instProposalMasterData.setNoticeOfOpportunity(noticeOfOppType);
		}
	}

	private void setActivityType(InstitutionalProposal institutionalProposal,
			InstProposalMasterData instProposalMasterData) {
		if (institutionalProposal.getActivityType() != null) {
			org.kuali.kra.proposaldevelopment.bo.ActivityType activityType = institutionalProposal
					.getActivityType();
			ActivityType activityTypeXmlObject = ActivityType.Factory
					.newInstance();
			if (activityType.getActivityTypeCode() != null) {
				activityTypeXmlObject.setActivityTypeCode(Integer
						.valueOf(activityType.getActivityTypeCode()));
			}
			if (activityType.getDescription() != null) {
				activityTypeXmlObject.setActivityTypeDesc(activityType
						.getDescription());
			}
			instProposalMasterData.setActivityType(activityTypeXmlObject);
		}
	}

	private void setProposalType(InstitutionalProposal institutionalProposal,
			InstProposalMasterData instProposalMasterData) {
		if (institutionalProposal.getProposalTypeCode() != null) {
			ProposalType proposalType = ProposalType.Factory.newInstance();
			int proposalTypeCode = Integer.valueOf(institutionalProposal
					.getProposalTypeCode());
			proposalType.setProposalTypeCode(proposalTypeCode);
			String proposalTypeDescription = getProposalTypeDescription(proposalTypeCode);
			if (proposalTypeDescription != null) {
				proposalType.setProposalTypeDesc(proposalTypeDescription);
			}
			instProposalMasterData.setProposalType(proposalType);
		}
	}

	private void setProposalStatus(InstitutionalProposal institutionalProposal,
			InstProposalMasterData instProposalMasterData) {
		if (institutionalProposal.getStatusCode() != null) {
			ProposalStatusType proposalStatusType = ProposalStatusType.Factory
					.newInstance();
			int proposalStatusCode = Integer.valueOf(institutionalProposal
					.getStatusCode());
			proposalStatusType.setStatusCode(proposalStatusCode);
			String proposalDescription = getProposalDescription(proposalStatusCode);
			if (proposalDescription != null) {
				proposalStatusType.setStatusDesc(proposalDescription);
			}
			instProposalMasterData.setProposalStatus(proposalStatusType);
		}
	}

	/*
	 * This method will return the sponsor name based on the prime sponsor code.
	 */
	private String getPrimeSponsorName(String primeSponsorCode) {
		String primeSponsorName = null;
		Map<String, String> sponsorMap = new HashMap<String, String>();
		sponsorMap.put(SPONSOR_CODE, primeSponsorCode);
		List<Sponsor> sponsorList = (List<Sponsor>) businessObjectService
				.findMatching(Sponsor.class, sponsorMap);
		if (sponsorList != null && !sponsorList.isEmpty()) {
			Sponsor sponsor = sponsorList.get(0);
			primeSponsorName = sponsor.getSponsorName();
		}
		return primeSponsorName;
	}

	/*
	 * This method will return the nsf description based on nsf code.1
	 */
	private String getNsfDesc(String nsfCode) {
		String nsfDesc = null;
		Map<String, String> nsfCodeMap = new HashMap<String, String>();
		nsfCodeMap.put(NSF_CODE, nsfCode);
		List<NsfCode> nsfCodeTypeList = (List<NsfCode>) businessObjectService
				.findMatching(NsfCode.class, nsfCodeMap);
		if (nsfCodeTypeList != null && !nsfCodeTypeList.isEmpty()) {
			NsfCode noticeOfOpportunity = nsfCodeTypeList.get(0);
			nsfDesc = noticeOfOpportunity.getDescription();
		}
		return nsfDesc;
	}

	/*
	 * This method will return the notice of opportunity description based on
	 * notice of opportunity code.
	 */
	private String getNoticeOfOpportunityDesc(String noticeOfOpportunityCode) {
		String noticeOfOpportunityDesc = null;
		Map<String, String> noticeOfOppMap = new HashMap<String, String>();
		noticeOfOppMap.put(NOTICE_OF_OPPORTUNITY_CODE, noticeOfOpportunityCode);
		List<NoticeOfOpportunity> noticeOfOppList = (List<NoticeOfOpportunity>) businessObjectService
				.findMatching(NoticeOfOpportunity.class, noticeOfOppMap);
		if (noticeOfOppList != null && !noticeOfOppList.isEmpty()) {
			NoticeOfOpportunity noticeOfOpportunity = noticeOfOppList.get(0);
			noticeOfOpportunityDesc = noticeOfOpportunity.getDescription();
		}
		return noticeOfOpportunityDesc;
	}

	/*
	 * This method will return the proposal type description based on the
	 * proposal type code.
	 */
	private String getProposalTypeDescription(int proposalTypeCode) {
		String proposalTypeDescription = null;
		Map<String, String> proposalTypeDescMap = new HashMap<String, String>();
		proposalTypeDescMap.put(PROPOSAL_TYPE_CODE, String
				.valueOf(proposalTypeCode));
		List<org.kuali.kra.proposaldevelopment.bo.ProposalType> proposalTypeList = null;
		proposalTypeList = (List<org.kuali.kra.proposaldevelopment.bo.ProposalType>) businessObjectService
				.findMatching(
						org.kuali.kra.proposaldevelopment.bo.ProposalType.class,
						proposalTypeDescMap);
		if (proposalTypeList != null && !proposalTypeList.isEmpty()) {
			org.kuali.kra.proposaldevelopment.bo.ProposalType proposalType = proposalTypeList
					.get(0);
			proposalTypeDescription = proposalType.getDescription();
		}
		return proposalTypeDescription;
	}

	/*
	 * This method will return the proposal description based on proposal status
	 * code.
	 */
	private String getProposalDescription(int proposalStatusCode) {
		String proposalDescription = null;
		Map<String, String> proposalStatusMap = new HashMap<String, String>();
		proposalStatusMap.put(PROPOSAL_STATUS_CODE, String
				.valueOf(proposalStatusCode));
		List<ProposalStatus> proposalStatusList = (List<ProposalStatus>) businessObjectService
				.findMatching(ProposalStatus.class, proposalStatusMap);
		if (proposalStatusList != null && !proposalStatusList.isEmpty()) {
			ProposalStatus proposalStatus = proposalStatusList.get(0);
			proposalDescription = proposalStatus.getDescription();
		}
		return proposalDescription;
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
	private String getIPParameterValue(String param) {
		String value = null;
		try {
			value = PrintingUtils.getParameterValue(param);
		} catch (Exception e) {
			//TODO Log Exception
		}
		return value;
	}
}
