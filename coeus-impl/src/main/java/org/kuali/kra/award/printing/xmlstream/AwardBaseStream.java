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
package org.kuali.kra.award.printing.xmlstream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.print.stream.xml.XmlStream;
import org.kuali.coeus.common.framework.print.util.PrintingUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.term.SponsorTermType;
import org.kuali.coeus.common.framework.type.ActivityType;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministrator;
import org.kuali.coeus.common.framework.unit.admin.UnitAdministratorType;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.kra.award.awardhierarchy.AwardHierarchy;
import org.kuali.kra.award.budget.document.AwardBudgetDocument;
import org.kuali.kra.award.commitments.AwardCostShare;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.contacts.AwardPersonUnit;
import org.kuali.kra.award.contacts.AwardSponsorContact;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.*;
import org.kuali.kra.award.home.AwardAmountInfo;
import org.kuali.kra.award.home.AwardType;
import org.kuali.kra.award.home.approvedsubawards.AwardApprovedSubaward;
import org.kuali.kra.award.home.fundingproposal.AwardFundingProposal;
import org.kuali.kra.award.home.keywords.AwardScienceKeyword;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTermRecipient;
import org.kuali.kra.award.paymentreports.closeout.AwardCloseout;
import org.kuali.kra.award.paymentreports.paymentschedule.AwardPaymentSchedule;
import org.kuali.kra.award.paymentreports.specialapproval.approvedequipment.AwardApprovedEquipment;
import org.kuali.kra.award.paymentreports.specialapproval.foreigntravel.AwardApprovedForeignTravel;
import org.kuali.kra.award.specialreview.AwardSpecialReview;
import org.kuali.kra.bo.*;
import org.kuali.coeus.common.framework.costshare.CostShareService;
import org.kuali.kra.printing.schema.*;
import org.kuali.kra.printing.schema.AwardNoticeDocument.AwardNotice;
import org.kuali.kra.printing.schema.AwardNoticeDocument.AwardNotice.AODetails;
import org.kuali.kra.printing.schema.AwardNoticeDocument.AwardNotice.PrintRequirement;
import org.kuali.kra.printing.schema.AwardType.*;
import org.kuali.kra.printing.schema.AwardType.AwardContacts.ContactDetails;
import org.kuali.kra.printing.schema.AwardType.AwardCostSharing.CostSharingItem;
import org.kuali.kra.printing.schema.AwardType.AwardDetails.OtherHeaderDetails;
import org.kuali.kra.printing.schema.AwardType.AwardFundingProposals.FundingProposal;
import org.kuali.kra.printing.schema.AwardType.AwardIndirectCosts.IndirectCostSharingItem;
import org.kuali.kra.printing.schema.AwardType.AwardPaymentSchedules.PaymentSchedule;
import org.kuali.kra.printing.schema.AwardType.AwardSpecialItems.Equipment;
import org.kuali.kra.printing.schema.AwardType.AwardSpecialItems.ForeignTravel;
import org.kuali.kra.printing.schema.AwardType.AwardSpecialItems.Subcontract;
import org.kuali.kra.printing.schema.AwardType.AwardTransferringSponsors.TransferringSponsor;
import org.kuali.kra.printing.schema.AwardType.AwardCloseOutDeadlines;
import org.kuali.kra.printing.schema.AwardType.AwardCloseOutDeadlines.CloseOutDeadlines;
import org.kuali.kra.printing.schema.SpecialReviewType;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.state.State;

import java.math.BigDecimal;
import java.util.*;

/**
 * This class will contain all common methods that can be used across all XML
 * generator streams related to Award. All award report XML stream
 * implementations need to extend and use the functions defined in this class.
 */
public abstract class AwardBaseStream implements XmlStream {

	private static final String INVOICE_INSTRUCTIONS_COMMENT_TYPE_CODE = "1";
    private static final String NOT_PRESENT_INDICATOR = "N";
    private static final String PRESENT_INDICATOR = "P";
    protected static final String OBLIGATED_DIRECT_INDIRECT_COST_CONSTANT = "ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST";
	private static final String ON_CAMPUS_FLAG_SET = "N";
	private static final Log LOG = LogFactory.getLog(AwardBaseStream.class);
	private static final String STRING_SEPARATER = ",";
    protected static final String OTHER_DATA = "otherData";
	protected static final String SIGNATURE_REQUIRED = "signatureRequired";
	protected static final String COMMENT_TYPE_CODE_PARAMETER = "commentTypeCode";
	protected static final String ACTIVITY_TYPE_CODE_PARAMETER = "code";
	protected static final String ACCOUNT_TYPE_CODE_PARAMETER = "accountTypeCode";
	protected static final String REQUIRED = "1";
	protected static final String NOT_REQUIRED = "0";
	protected static final String START_ASTERISK_SPACE_INDICATOR = "* ";
	protected static final String END_ASTERISK_SPACE_INDICATOR = " *";
	protected static final String FELLOWSHIP_ADMIN_NAME = "FELLOWSHIP_OSP_ADMIN";
	private static final String SCHOOL_NAME = "SCHOOL_NAME";
	private static final String SCHOOL_ACRONYM = "SCHOOL_ACRONYM";
	protected static final String EMPTY_STRING = "";
	protected static final String INDICATOR_CONSTANT = "1";
	protected static final String SPONSOR_CODE_DELETED_INDICATOR = "DELETED - ";
	protected static final String SPONSOR_CODE_ADDED_INDICATOR = "ADDED - ";
	protected static final String SEQUENCE_NUMBER_PARAMETER = "sequenceNumber";
	protected static final String AWARD_NUMBER_PARAMETER = "awardNumber";
	protected static final String ROOT_AWARD_NUMBER_PARAMETER = "rootAwardNumber";
	protected static final String COST_SHARING_COMMENT = "9";
	protected static final String IDC_COMMENT = "8";
	protected static final String CURRENT_ACTION_COMMENT = "21";
	protected static final String COMMA_STRING = ", ";
	protected static final String EMPTY_SPACE = " ";
	protected static final String DOCUMENT_NUMBER = "documentNumber";
	protected AwardDocument awardDocument = null;
	protected Award award = null;

	protected AwardAmountInfo awardAmountInfo = null;
	protected Award prevAward = null;
	protected AwardAmountInfo prevAwardAmountInfo = null;
	protected BusinessObjectService businessObjectService = null;
    protected DataObjectService dataObjectService = null;
    private DocumentService documentService = null;
	protected DateTimeService dateTimeService = null;
	private ParameterService parameterService;
	private CostShareService costShareService;

	protected abstract PrintRequirement getPrintRequirement(
			Map<String, Object> reportParameters);

	protected org.kuali.kra.printing.schema.AwardType getAward() {
		org.kuali.kra.printing.schema.AwardType awardType = org.kuali.kra.printing.schema.AwardType.Factory
				.newInstance();	
		awardType.setAwardDetails(getAwardDetails());
		awardType.setAwardInvestigators(getAwardInvestigators());
		awardType.setAwardKeyPersons(getKeyPersons());
		awardType.setAwardComments(getAwardComments());
		awardType.setAwardAmountInfo(getAwardAmountInfo());
		awardType.setAwardTransactionInfo(getAwardTransactionInfo());
		awardType.setAwardScienceCodes(getAwardScienceCodes());		
		awardType.setAwardTermsDetails(getAwardTermsDetails());
		awardType.setAwardReportingDetails(getAwardReportingDetails());
		awardType.setAwardSpecialItems(getAwardSpecialItems());
		awardType.setAwardCostSharing(getAwardCostSharing());
		awardType.setAwardIndirectCosts(getAwardIndirectCosts());
		awardType.setAwardCloseOutDeadlines(getAwardCloseOutDeadlines());
		awardType.setAwardContacts(getAwardContacts());
		awardType.setAwardFundingProposals(getAwardFundingProposals());
		return awardType;
	}

	/*
	 * This method will set the values to award notice attributes and finally
	 * returns award notice Xml Object
	 */
	protected AwardNotice getAwardNotice(Map<String, Object> reportParameters) {
		AwardNotice awardNotice = AwardNotice.Factory.newInstance();
		awardNotice.setSchoolInfo(getSchoolInfoType());
		awardNotice.setAward(getAward());
		awardNotice.setAODetails(getAODetailsType());
		awardNotice.setPrintRequirement(getPrintRequirement(reportParameters));
		return awardNotice;
	}

	/**
	 * <p>
	 * This method will set the values to school info attributes and finally
	 * returns SchoolInfoType XmlObject
	 * </p>
	 * 
	 * @return returns SchoolInfoType XmlObject
	 */
	protected SchoolInfoType2 getSchoolInfoType() {
		SchoolInfoType2 schoolInfoType = SchoolInfoType2.Factory.newInstance();
		try {
			String schoolName = getProposalParameterValue(SCHOOL_NAME);
			String schoolAcronym = getProposalParameterValue(SCHOOL_ACRONYM);
			if (schoolName != null) {
				schoolInfoType.setSchoolName(schoolName);
			}
			if (schoolAcronym != null) {
				schoolInfoType.setAcronym(schoolAcronym);
			}
		} catch (Exception e) {
			// LOg e
		}
		return schoolInfoType;
	}

	private String getProposalParameterValue(String param) {
        return parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, param);
    }

	/**
	 * <p>
	 * This method will set the values the payment schedule XmlObject and return
	 * it
	 * </p>
	 * 
	 * @param awardPaymentSchedule
	 *            it contains information about payment schedule {@link
	 *            AwardPaymentSchedule}
	 * @return paymentSchedule XmlObject
	 */
	protected PaymentSchedule getAwardPaymentSchedule(
			AwardPaymentSchedule awardPaymentSchedule) {
		PaymentSchedule paymentSchedule = PaymentSchedule.Factory.newInstance();
		if (awardPaymentSchedule.getAwardNumber() != null) {
			paymentSchedule.setAwardNumber(awardPaymentSchedule
					.getAwardNumber());
		}
		if (awardPaymentSchedule.getSequenceNumber() != null) {
			paymentSchedule.setSequenceNumber(awardPaymentSchedule
					.getSequenceNumber());
		}
		Calendar dueDate = getDueDate(awardPaymentSchedule);
		if (dueDate != null) {
			paymentSchedule.setDueDate(dueDate);
		}
		if (awardPaymentSchedule.getAmount() != null) {
			paymentSchedule.setAmount(awardPaymentSchedule.getAmount()
					.bigDecimalValue());
		}
		Calendar submitDate = getSubmitDate(awardPaymentSchedule);
		if (submitDate != null) {
			paymentSchedule.setSubmitDate(submitDate);
		}
		if (awardPaymentSchedule.getSubmittedBy() != null) {
			paymentSchedule.setSubmittedBy(awardPaymentSchedule
					.getSubmittedBy());
		}
		if (awardPaymentSchedule.getInvoiceNumber() != null) {
			paymentSchedule.setInvoiceNumber(awardPaymentSchedule
					.getInvoiceNumber());
		}
		if (awardPaymentSchedule.getStatusDescription() != null) {
			paymentSchedule.setStatusDescription(awardPaymentSchedule
					.getStatusDescription());
		}
		return paymentSchedule;
	}

    /**
     * <p>
     * This method will set the values to transferring sponsor XmlObject and
     * return it
     * </p>
     * 
     * @param awardTransferringSponsor
     *            it contains information about the award transferring sponsor
     *            {@link AwardTransferringSponsor}
     * @return awardTransferringSponsor xmlObject
     */
    protected TransferringSponsor getAwardTransferringSponsor(
            AwardTransferringSponsor awardTransferringSponsor) {
        TransferringSponsor transferringSponsor = TransferringSponsor.Factory
                .newInstance();
        if (awardTransferringSponsor.getAwardNumber() != null) {
            transferringSponsor.setAwardNumber(awardTransferringSponsor
                    .getAwardNumber());
        }
        if (awardTransferringSponsor.getSequenceNumber() != null) {
            transferringSponsor.setSequenceNumber(awardTransferringSponsor
                    .getSequenceNumber());
        }
        if (awardTransferringSponsor.getSponsorCode() != null) {
            transferringSponsor.setSponsorCode(awardTransferringSponsor.getSponsorCode());
        }
        Sponsor sponsor = awardTransferringSponsor.getSponsor();
        if (sponsor != null && sponsor.getSponsorName() != null) {
            transferringSponsor.setSponsorDescription(sponsor.getSponsorName());
        }
        return transferringSponsor;
    }

	/**
	 * <p>
	 * This method will set the values to award Special Review XmlObject and
	 * return it
	 * </p>
	 * 
	 * @param awardSpecialReview
	 *            it contains information about the award Special Review {@link
	 *            AwardSpecialReview}
	 * @return awardSpecialReview XmlObject
	 */
	protected SpecialReviewType getAwardSpecialReview(
			AwardSpecialReview awardSpecialReview) {
		SpecialReviewType specialReviewType = SpecialReviewType.Factory
				.newInstance();
		if (award.getAwardNumber() != null) {
			specialReviewType.setAwardNumber(award.getAwardNumber());
		}
		if (awardSpecialReview.getSequenceNumber() != null) {
			specialReviewType.setSequenceNumber(awardSpecialReview
					.getSequenceNumber());
		}
		if (awardSpecialReview.getSpecialReviewTypeCode() != null) {
			specialReviewType.setReviewType(Integer.valueOf(awardSpecialReview.getSpecialReviewTypeCode()));
		}
        if (awardSpecialReview.getApprovalTypeCode() != null) {
            specialReviewType.setApprovalType(Integer.parseInt(awardSpecialReview.getApprovalTypeCode()));
            awardSpecialReview.refreshReferenceObject("specialReviewApprovalType");
            if(awardSpecialReview.getApprovalType()!=null){
                specialReviewType.setApprovalTypeDesc(awardSpecialReview.getApprovalType().getDescription());
            }
        }
		if (awardSpecialReview.getApprovalTypeCode() != null) {
			specialReviewType.setApprovalType(Integer.valueOf(awardSpecialReview.getApprovalTypeCode()));
		}
		if (awardSpecialReview.getComments() != null) {
			specialReviewType.setComments(awardSpecialReview.getComments());
		}
		if (awardSpecialReview.getProtocolNumber() != null) {
			specialReviewType.setProtocolNumber(awardSpecialReview.getProtocolNumber());
		}
		awardSpecialReview.refreshReferenceObject("specialReview");
		if (awardSpecialReview.getSpecialReviewType() != null
				&& awardSpecialReview.getSpecialReviewType().getDescription() != null) {
			specialReviewType.setReviewTypeDesc(awardSpecialReview.getSpecialReviewType().getDescription());
		}
		Calendar applicationDate = getApplicationDate(awardSpecialReview);
		if (applicationDate != null) {
			specialReviewType.setApplicationDate(applicationDate);
		}
		Calendar approvalDate = getApprovalDate(awardSpecialReview);
		if (approvalDate != null) {
			specialReviewType.setApprovalDate(approvalDate);
		}
		return specialReviewType;
	}

	/**
	 * <p>
	 * This method will set the values to Award Header attributes and finally
	 * returns AwardHeaderType XmlObject
	 * </p>
	 * 
	 * @return returns AwardHeaderType XmlObject
	 */
	protected AwardHeaderType getAwardHeaderType() {
		AwardHeaderType awardHeaderType = AwardHeaderType.Factory.newInstance();
		if (award.getAwardNumber() != null) {
			awardHeaderType.setAwardNumber(award.getAwardNumber());
		}
		if (award.getSequenceNumber() != null) {
			awardHeaderType.setSequenceNumber(award.getSequenceNumber());
		}
		if (award.getAccountNumber() != null) {
			awardHeaderType.setAccountNumber(award.getAccountNumber());
		}
		if (award.getStatusCode() != null) {
			awardHeaderType
					.setStatusCode(String.valueOf(award.getStatusCode()));
		}
		if (award.getStatusDescription() != null) {
			if (prevAward != null && prevAward.getStatusDescription() != null) {
				String CurrAwardStatus = award.getStatusDescription();
				String prevAwardStatus = prevAward.getStatusDescription();
				String awardStatusMod = getModAwardStatus(CurrAwardStatus,
						prevAwardStatus);
				if (awardStatusMod != null) {
					awardHeaderType.setStatusDescription(awardStatusMod);
				}
			} else {
				awardHeaderType.setStatusDescription(award.getStatusDescription());
			}	
		}	
		if (award.getSponsorCode() != null) {
			awardHeaderType.setSponsorCode(award.getSponsorCode());
		}
		String sponsorDesc = getSponsorDescription();
		if (sponsorDesc != null) {
			awardHeaderType.setSponsorDescription(sponsorDesc);
		}
		String sponosorAwardnumber = getSponsorAwardNumber();
		if (sponosorAwardnumber != null) {
			awardHeaderType.setSponsorAwardNumber(sponosorAwardnumber);
		}
		if (award.getModificationNumber() != null) {
			awardHeaderType
					.setModificationNumber(award.getModificationNumber());			
		}
		if (award.getNsfSequenceNumber() != null && award.getNsfCodeBo() != null) {
			awardHeaderType.setNSFCode(award.getNsfCodeBo().getNsfCode());
			String nsfDescription = award.getNsfCodeBo().getDescription();
			if (nsfDescription != null) {
				awardHeaderType.setNSFDescription(nsfDescription);
			}
		}
		if (award.getPrincipalInvestigatorName() != null) {
			awardHeaderType.setPIName(award.getPrincipalInvestigatorName());
		}
		if (award.getTitle() != null) {
			if (prevAward != null && prevAward.getTitle() != null) {
				String awardTitle = award.getTitle();
				String prevAwardTitle = prevAward.getTitle();
				String awardTitleMod = getModAwardTitle(awardTitle,
						prevAwardTitle);
				if (awardTitleMod != null) {
					awardHeaderType.setTitle(awardTitleMod);
				}
			} else {
				awardHeaderType.setTitle(award.getTitle());
			}	
		}
		return awardHeaderType;
	}
	
	
	/*
	 * This method will get the title based on if previous award type
	 * title is equal to null or not equal to award award title
	 * Otherwise returns * (Asterisk)
	 */
	private String getModAwardTitle(String awardTitle,
			String prevAwardTitle) {
		String awardTitleMod = null;
		if (awardTitle != null) {
			if (prevAwardTitle == null
					|| !awardTitle.equals(prevAwardTitle)) {
				awardTitleMod = new StringBuilder(awardTitle).append(
						END_ASTERISK_SPACE_INDICATOR).toString();

			} else {
				awardTitleMod = awardTitle;
			}
		} else if (prevAwardTitle != null) {

			awardTitleMod = START_ASTERISK_SPACE_INDICATOR;
		}
		return awardTitleMod;
	}
	
	/*
	 * This method will get the previous status
	 * status is equal to null or not equal to award award status
	 * Otherwise returns * (Asterisk)
	 */
	private String getModAwardStatus(String CurrAwardStatus,
			String prevAwardStatus) {
		String awardStatusMod = null;
		if (CurrAwardStatus != null) {
			if (prevAwardStatus == null
					|| !CurrAwardStatus.equals(prevAwardStatus)) {
				awardStatusMod = new StringBuilder(CurrAwardStatus).append(
						END_ASTERISK_SPACE_INDICATOR).toString();
				} else {
				awardStatusMod = CurrAwardStatus;
			}
		} else if (prevAwardStatus != null) {
	    awardStatusMod = START_ASTERISK_SPACE_INDICATOR;
		}
		return awardStatusMod;
	}
	

	/**
	 * <p>
	 * This method will set the values to Award Reporting Details attributes and
	 * finally returns AwardReportingDetails XmlObject
	 * </p>
	 * 
	 * @return returns AwardHeaderType XmlObject
	 */
	protected AwardReportingDetails getAwardReportingDetails() {
		AwardReportingDetails awardReportingDetails = AwardReportingDetails.Factory.newInstance();
		String reportClassCode = null;
		ReportTermType2 reportTermType = null;
		for (AwardReportTerm awardReportTerm : award.getAwardReportTermItems()) {
		    if(reportClassCode==null || !reportClassCode.equals(awardReportTerm.getReportClassCode())){
		        reportTermType = awardReportingDetails.addNewReportDetails();
		    }
		    reportClassCode = awardReportTerm.getReportClassCode();
			getAwardReportTermBean(awardReportTerm,reportTermType);
			if (awardReportTerm.getReportClass() != null) {
				String reportTermTypeDescription = awardReportTerm
						.getReportClass().getDescription();
				if (reportTermTypeDescription != null) {
					reportTermType.setDescription(reportTermTypeDescription);
				}
			}
		}
		return awardReportingDetails;
	}

	/**
	 * <p>
	 * This method will set the values to AO Details attributes and finally
	 * returns AODetails XmlObject
	 * </p>
	 * 
	 * @return returns AODetails XmlObject
	 */
	protected AODetails getAODetailsType() {

		final Optional<KcPerson> person = award.getCentralAdminContacts().stream()
				.filter(contact -> contact.getUnitAdministratorType().getCode().equals(UnitAdministratorType.ADMINISTRATIVE_OFFICER_TYPE_CODE))
				.map(AwardUnitContact::getPerson)
				.findAny();

		final AODetails aODetailsType = AODetails.Factory.newInstance();
		if (person.isPresent()) {
			if (person.get().getOfficeLocation() != null) {
				aODetailsType.setAOAddress(person.get().getOfficeLocation());
			}
			if (person.get().getFullName() != null) {
				aODetailsType.setAOName(person.get().getFullName());
			}
		}
		return aODetailsType;
	}

	/**
	 * <p>
	 * This method will set the values to Award Funding Proposals attributes and
	 * finally returns AwardFundingProposals XmlObject
	 * </p>
	 * 
	 * @return returns AwardFundingProposals XmlObject
	 */
	protected AwardFundingProposals getAwardFundingProposals() {
		AwardFundingProposals awardFundingProposals = AwardFundingProposals.Factory
				.newInstance();
		List<FundingProposal> fundingProposals = new ArrayList<FundingProposal>();
		for (AwardFundingProposal awardFundingProposal : award
				.getFundingProposals()) {
			FundingProposal fundingProposal = FundingProposal.Factory
					.newInstance();
			fundingProposal.setAwardNumber(award.getAwardNumber());
			fundingProposal.setSequenceNumber(award.getSequenceNumber());
			if (awardFundingProposal.getProposal() != null) {
				fundingProposal.setProposalNumber(awardFundingProposal
						.getProposal().getProposalNumber());

				fundingProposal.setProposalSequenceNumber(awardFundingProposal
						.getProposal().getSequenceNumber());
				fundingProposal.setProopsalTypeCode(awardFundingProposal
						.getProposal().getProposalTypeCode());
				if (awardFundingProposal.getProposal().getProposalType() != null) {
					fundingProposal
							.setProposalTypeDescription(awardFundingProposal
									.getProposal().getProposalType()
									.getDescription());
				}
				fundingProposal.setProposalStatusCode(awardFundingProposal
						.getProposal().getStatusCode());
				if (awardFundingProposal.getProposal()
						.getRequestedStartDateTotal() != null) {
					fundingProposal.setRequestedStartDateTotal(dateTimeService
							.getCalendar(awardFundingProposal.getProposal()
									.getRequestedStartDateTotal()));
				}
				if(awardFundingProposal.getProposal()
						.getRequestedEndDateTotal()!=null){
				fundingProposal.setRequestedEndDateTotal(dateTimeService
						.getCalendar(awardFundingProposal.getProposal()
								.getRequestedEndDateTotal()));
				}
				if (awardFundingProposal.getProposal()
						.getTotalDirectCostTotal() != null) {
					fundingProposal.setDirectCostTotal(awardFundingProposal
							.getProposal().getTotalDirectCostTotal()
							.bigDecimalValue());
				}
				if (awardFundingProposal.getProposal()
						.getTotalIndirectCostTotal() != null) {
					fundingProposal.setIndirectCostTotal(awardFundingProposal
							.getProposal().getTotalIndirectCostTotal()
							.bigDecimalValue());
				}
			}
			fundingProposals.add(fundingProposal);
		}
		awardFundingProposals.setFundingProposalArray(fundingProposals
				.toArray(new FundingProposal[0]));
		return awardFundingProposals;
	}

	/**
	 * <p>
	 * This method will set the values to Award Contacts attributes and finally
	 * returns AwardContacts XmlObject
	 * </p>
	 * 
	 * @return returns AwardContacts XmlObject
	 */
	protected AwardContacts getAwardContacts() {
		AwardContacts awardContacts = AwardContacts.Factory.newInstance();
		List<AwardSponsorContact> awardSponsorContacts = award
				.getSponsorContacts();
		for (AwardSponsorContact awardSponsorContact : awardSponsorContacts) {
		    ContactDetails contactDetails = awardContacts.addNewContactDetails();
			if (award.getAwardNumber() != null) {
				contactDetails.setAwardNumber(award.getAwardNumber());
			}
			if (award.getSequenceNumber() != null) {
				contactDetails.setSequenceNumber(award.getSequenceNumber());
			}
			if (awardSponsorContact.getContactRoleCode() != null) {
				contactDetails.setContactType(Integer
						.valueOf(awardSponsorContact.getContactRoleCode()));
			}
			if (awardSponsorContact.getContactOrganizationName() != null) {
				contactDetails.setContactTypeDesc(awardSponsorContact
						.getContactRole().getRoleDescription());	
			}
			contactDetails
					.setRolodexDetails(getRolodexDetails(awardSponsorContact));
		}
		return awardContacts;
	}

	/**
	 * <p>
	 * This method will get the approval date {@link Calendar} from award
	 * special review
	 * </p>
	 * 
	 * @param awardSpecialReview
	 *            it contains information about award special review {@link
	 *            AwardSpecialReview}
	 * @return approval date{@link Calendar}if it found in award special
	 *         review otherwise null
	 */
	protected Calendar getApprovalDate(AwardSpecialReview awardSpecialReview) {
		Calendar approvalDate = null;
		if (awardSpecialReview.getApprovalDate() != null) {
			approvalDate = dateTimeService.getCalendar(awardSpecialReview
					.getApprovalDate());
		}
		return approvalDate;
	}

	/**
	 * <p>
	 * This method will get the application {@link Calendar} from award special
	 * review
	 * </p>
	 * 
	 * @param awardSpecialReview
	 *            it contains information about award special review {@link
	 *            AwardSpecialReview}
	 * @return application date{@link Calendar}if it found in award special
	 *         review otherwise null
	 */
	protected Calendar getApplicationDate(AwardSpecialReview awardSpecialReview) {
		Calendar applicationDate = null;
		if (awardSpecialReview.getApplicationDate() != null) {
			applicationDate = dateTimeService.getCalendar(awardSpecialReview
					.getApplicationDate());
		}
		return applicationDate;
	}

	/**
	 * <p>
	 * This method will set the values to CloseOut Deadlines attributes and
	 * finally returns CloseOut Deadlines XmlObject
	 * </p>
	 * 
	 * @return returns CloseOut Deadlines XmlObject
	 */
	protected AwardCloseOutDeadlines getAwardCloseOutDeadlines() {
		AwardCloseOutDeadlines awardcloseOutDeadlines = AwardCloseOutDeadlines.Factory.newInstance();
		

		List<AwardCloseout> awardCloseoutItems = award.getAwardCloseoutItems();
		AwardCloseout preAwardCloseout = null;
		if (prevAward != null && !prevAward.getAwardCloseoutItems().isEmpty()) {
			preAwardCloseout = prevAward.getAwardCloseoutItems().get(0);
		}
		List<CloseOutDeadlines> closeOutDeadlines = new ArrayList<>();
		if (awardCloseoutItems != null && !awardCloseoutItems.isEmpty()) {
			if (awardCloseoutItems.get(0).getSequenceNumber() != null) {
				awardcloseOutDeadlines.setSequenceNumber(awardCloseoutItems.get(0).getSequenceNumber());








			}
			if (awardCloseoutItems.get(0).getAwardNumber() != null) {
				awardcloseOutDeadlines
						.setAwardNumber(awardCloseoutItems.get(0).getAwardNumber());

			}
			if(award.getArchiveLocation()!=null)
				awardcloseOutDeadlines.setArchiveLocation(award.getArchiveLocation());
			if(award.getCloseoutDate()!=null)
				awardcloseOutDeadlines.setArchiveDate(dateTimeService.getCalendar(award.getCloseoutDate()));
			
			for (AwardCloseout awardCloseout:awardCloseoutItems ){
				CloseOutDeadlines closeOutDeadline = CloseOutDeadlines.Factory.newInstance();		
				
				if (awardCloseout.getCloseoutReportName() != null) {
					closeOutDeadline.setFinalReportType(awardCloseout.getCloseoutReportName());
				}
				if (awardCloseout.getDueDate() != null) {
					Calendar closeoutDate = dateTimeService
							.getCalendar(awardCloseout.getDueDate());
					closeOutDeadline.setFinalDueDate(closeoutDate);
				}
				if (preAwardCloseout != null) {
					String closeOutDateModified = getCloseOutDateModified(
							awardCloseout, preAwardCloseout);
					if (closeOutDateModified != null) {
						closeOutDeadline
								.setFinalDueDateModified(closeOutDateModified);
					}
				} else if (awardCloseout.getDueDate() != null) {
					closeOutDeadline.setFinalDueDateModified(String
							.valueOf(awardCloseout.getDueDate()));
				}
				if (awardCloseout.getFinalSubmissionDate() != null) {
					Calendar finalSubmissionDate = dateTimeService
							.getCalendar(awardCloseout.getFinalSubmissionDate());
					closeOutDeadline.setFinalSubDate(finalSubmissionDate);
				}
				if (preAwardCloseout != null) {
					String finalPropSubDateModified = getFinalProbSubDateModified(
							awardCloseout, preAwardCloseout);
					if (finalPropSubDateModified != null) {
						closeOutDeadline.setFinalSubDateModified(finalPropSubDateModified);
					}
				} else if (awardCloseout.getFinalSubmissionDate() != null) {
					closeOutDeadline.setFinalSubDateModified(String
							.valueOf(awardCloseout.getFinalSubmissionDate()));
				}
				closeOutDeadlines.add(closeOutDeadline);
			}



		}

		awardcloseOutDeadlines.setCloseOutDeadlinesArray(closeOutDeadlines
				.toArray(new CloseOutDeadlines[0]));
		return awardcloseOutDeadlines;
	}

	/**
	 * <p>
	 * This method will set the values to Award Indirect Costs attributes and
	 * finally returns Award IndirectCosts XmlObject
	 * </p>
	 * 
	 * @return returns Award IndirectCosts XmlObject
	 */
	protected AwardIndirectCosts getAwardIndirectCosts() {
        AwardIndirectCosts awardIndirectCost = AwardIndirectCosts.Factory
                .newInstance();
        String comemnts = getIndirectCostComments();
        if (comemnts != null) {
            awardIndirectCost.setComments(comemnts);
        }
        List<IndirectCostSharingItem> indirectCostSharingItems = new ArrayList<>();
        award.getAwardFandaRate().forEach(awardFandaRate -> {
            if(awardFandaRate!=null){
                IndirectCostSharingItem indirectCostSharingItem = IndirectCostSharingItem.Factory
                .newInstance();
                indirectCostSharingItem.setSequenceNumber(award.getSequenceNumber());   
                if(awardFandaRate.getFiscalYear()!=null){
                    indirectCostSharingItem.setFiscalYear(String.valueOf(awardFandaRate.getFiscalYear())); 
                }
                if(awardFandaRate.getApplicableFandaRate()!=null){
                    indirectCostSharingItem
                    .setApplicableRate(awardFandaRate.getApplicableFandaRate().bigDecimalValue());    
                }
                else{
                    indirectCostSharingItem
                    .setApplicableRate(ScaleTwoDecimal.ZERO.bigDecimalValue());
                }
                boolean campus = (awardFandaRate.getOnCampusFlag() != null && awardFandaRate
						.getOnCampusFlag().equals(ON_CAMPUS_FLAG_SET));
                indirectCostSharingItem.setCampus(campus);
                if(awardFandaRate.getSourceAccount()!=null){
                    indirectCostSharingItem.setSourceAccount(awardFandaRate.getSourceAccount());         
                }
                if(awardFandaRate.getUnderrecoveryOfIndirectCost()!=null){
                    indirectCostSharingItem.setUnderRecoveryAmount(awardFandaRate.getUnderrecoveryOfIndirectCost().bigDecimalValue());
                }
                else{
                    indirectCostSharingItem.setUnderRecoveryAmount(ScaleTwoDecimal.ZERO.bigDecimalValue());
                }
                if(awardFandaRate.getDestinationAccount()!=null){
                    indirectCostSharingItem.setDestinationAccount(awardFandaRate.getDestinationAccount());
                }
                if(awardFandaRate.getStartDate()!=null){
                    indirectCostSharingItem.setStartDate(dateTimeService.getCalendar(awardFandaRate.getStartDate()));
                }
                if(awardFandaRate.getEndDate()!=null){
                    indirectCostSharingItem.setEndDate(dateTimeService.getCalendar(awardFandaRate.getEndDate()));  
                }
                if(awardFandaRate.getFandaRateType()!=null && awardFandaRate.getFandaRateType().getDescription()!=null){
                    indirectCostSharingItem.setIDCRateDescription(awardFandaRate.getFandaRateType().getDescription());
                }
                indirectCostSharingItems.add(indirectCostSharingItem);
            }
        });
        awardIndirectCost
                .setIndirectCostSharingItemArray(indirectCostSharingItems
						.toArray(new IndirectCostSharingItem[0]));
        return awardIndirectCost;
    }

	/*
	 * Fetch the BudgetDocument associated with Award
	 */
	protected AwardBudgetDocument getBudgetDocument() {
		AwardBudgetDocument awardBudgetDocument = null;
		try {
			if (!awardDocument.getBudgetDocumentVersions().isEmpty()) {
				awardBudgetDocument = (AwardBudgetDocument) documentService
						.getByDocumentHeaderId(awardDocument
								.getBudgetDocumentVersion(0)
								.getDocumentNumber());
			}
		} catch (WorkflowException e) {
			LOG.error(e.getMessage(), e);
		}
		return awardBudgetDocument;
	}

	/**
	 * <p>
	 * This method will set the values to Award Cost Sharing attributes and
	 * finally returns Award CostSharing XmlObject
	 * </p>
	 * 
	 * @return returns Award CostSharing XmlObject
	 */
	protected AwardCostSharing getAwardCostSharing() {
		AwardCostSharing awardCostSharing = AwardCostSharing.Factory
				.newInstance();
		String comments = getCostSharingComments();
		if (comments != null) {
			awardCostSharing.setComments(comments);
		}
		awardCostSharing.setCostSharingItemArray(getCostSharingItemDetails());
		return awardCostSharing;
	}

	/**
	 * <p>
	 * This method will set the values to Award Special Items attributes and
	 * finally returns Award Special Items XmlObject
	 * </p>
	 * 
	 * @return returns Award Special Items XmlObject
	 */
	protected AwardSpecialItems getAwardSpecialItems() {
		AwardSpecialItems awardSpecialItem = AwardSpecialItems.Factory.newInstance();
		awardSpecialItem.setEquipmentArray(getEquipmentType());
		awardSpecialItem.setForeignTravelArray(getForeignTravel());
		setSubcontracts(awardSpecialItem);
		return awardSpecialItem;
	}



    private void setSubcontracts(AwardSpecialItems awardSpecialItem) {
        List<AwardApprovedSubaward> awardApprovedSubawards = award.getAwardApprovedSubawards();
        for (AwardApprovedSubaward awardApprovedSubcontractBean : awardApprovedSubawards) {
            Subcontract subcontractType = awardSpecialItem.addNewSubcontract();
            BigDecimal bdecAmount = awardApprovedSubcontractBean.getAmount().bigDecimalValue();
            subcontractType.setAmount(bdecAmount.setScale(2,BigDecimal.ROUND_HALF_DOWN));
            subcontractType.setAwardNumber(awardApprovedSubcontractBean.getAwardNumber());
            subcontractType.setSequenceNumber(awardApprovedSubcontractBean.getSequenceNumber());
            subcontractType.setSubcontractorName(awardApprovedSubcontractBean.getOrganizationName());
        }
    }

    /**
	 * <p>
	 * This method will set the values to Award Comments attributes and finally
	 * returns Award Comments XmlObject
	 * </p>
	 * 
	 * @return returns Award Comments XmlObject
	 */
	protected AwardComments getAwardComments() {
		AwardComments awardComments = AwardComments.Factory.newInstance();

		for (AwardComment awardComment : award.getAwardComments()) {

    		    CommentType2 commentType = awardComments.addNewComment();
    		    CommentDetailsType commentDetailsType = commentType.addNewCommentDetails();
    			if (awardComment.getAwardNumber() != null) {
    				commentDetailsType.setAwardNumber(award.getAwardNumber());
    			}
    			if (awardComment.getSequenceNumber() != null) {
    				commentDetailsType.setSequenceNumber(awardComment
    						.getSequenceNumber());
    			}
    			if (awardComment.getCommentTypeCode() != null) {
    				commentDetailsType.setCommentCode(awardComment
    						.getCommentTypeCode());
    				commentType.setDescription(getCommentTypeDesc(awardComment
    						.getCommentTypeCode()));
    			}
    			if (awardComment.getComments() != null) {
    				commentDetailsType.setComments(awardComment.getComments());
    			}
    			if (awardComment.getChecklistPrintFlag() != null) {
    				commentDetailsType.setPrintChecklist(awardComment
    						.getChecklistPrintFlag());
    			}
    			commentType.setCommentDetails(commentDetailsType);		    
		}
		return awardComments;
	}

	/**
	 * <p>
	 * This method will set the values to Award TransactionInfo attributes and
	 * finally returns Award TransactionInfo XmlObject
	 * </p>
	 * 
	 * @return returns Award TransactionInfo XmlObject
	 */
	protected AwardTransactionInfo getAwardTransactionInfo() {
		AwardTransactionInfo awardTransactionInfo = AwardTransactionInfo.Factory
				.newInstance();
		AwardTransactionType awardTransactionType = awardTransactionInfo.addNewTransactionInfo();

		if (award.getAwardNumber() != null) {
			awardTransactionType.setAwardNumber(award.getAwardNumber());
		}
		if (award.getSequenceNumber() != null) {
			awardTransactionType.setSequenceNumber(award.getSequenceNumber());
		}
		if (awardAmountInfo != null
				&& awardAmountInfo.getSequenceNumber() != null) {
			awardTransactionType.setAmountSequenceNumber(awardAmountInfo
					.getSequenceNumber());
		}
        if (award.getNoticeDate() != null) {
            awardTransactionType.setNoticeDate(dateTimeService
                                 .getCalendar(award.getNoticeDate()));
        }
        award.getAwardComments().forEach(awardComments -> {
            if (awardComments.getCommentTypeCode().equals(CURRENT_ACTION_COMMENT)) {
                awardTransactionType.setComments(awardComments.getComments());
            }
        });
        if (award.getAwardTransactionTypeCode() != null) {
            award.refreshReferenceObject("awardTransactionType");  
            awardTransactionType.setTransactionTypeCode(award.getAwardTransactionTypeCode());
            if (award.getAwardTransactionType() != null) {
                awardTransactionType.setTransactionTypeDesc(award.getAwardTransactionType().getDescription());
            }
        }
		return awardTransactionInfo;
	}

	/**
	 * <p>
	 * This method will set the values to Award Key Persons attributes and
	 * finally returns Award Key Persons XmlObject
	 * </p>
	 * 
	 * @return returns Award Key Persons XmlObject
	 */
	protected AwardKeyPersons getKeyPersons() {
		KeyPersonType keyPersonType;
		List<KeyPersonType> keyPersonTypes = new ArrayList<>();
		for (AwardPerson awardPerson : award.getProjectPersons()) {
			if (awardPerson.isKeyPerson()) {
				keyPersonType = KeyPersonType.Factory.newInstance();
				if (award.getAwardNumber() != null) {
					keyPersonType.setAwardNumber(award.getAwardNumber());
				}
				if (award.getSequenceNumber() != null) {
					keyPersonType.setSequenceNumber(awardPerson
							.getSequenceNumber());
				}
				if (awardPerson.getPersonId() != null) {
					keyPersonType.setPersonId(awardPerson.getPersonId());
				}
				if (awardPerson.getFullName() != null) {
					keyPersonType.setPersonName(awardPerson.getFullName());
				}
				keyPersonType.setFaculty(awardPerson.isFaculty());
				if (awardPerson.getRoleCode() != null) {
					keyPersonType.setRoleName(awardPerson.getRoleCode());
				}
				if (awardPerson.getPerson() != null
						&& awardPerson.getPerson().getOfficeLocation() != null) {
					keyPersonType.setPersonAddress(awardPerson.getPerson()
							.getOfficeLocation());
				}
				keyPersonType.setNonEmployee(!awardPerson.isEmployee());
				BigDecimal percentageEffort = getPercentageEffort(awardPerson);
				if (percentageEffort != null) {
					keyPersonType.setPercentEffort(percentageEffort);
				}
				keyPersonTypes.add(keyPersonType);
			}
		}
		AwardKeyPersons awardKeyPersons = AwardKeyPersons.Factory.newInstance();
		awardKeyPersons.setKeyPersonArray(keyPersonTypes
				.toArray(new KeyPersonType[0]));
		return awardKeyPersons;
	}

	/**
	 * <p>
	 * This method will set the values to Award Investigators attributes and
	 * finally returns Award Investigators XmlObject
	 * </p>
	 * 
	 * @return returns Award Investigators XmlObject
	 */
	protected AwardInvestigators getAwardInvestigators() {
		AwardInvestigators awardInvestigators = AwardInvestigators.Factory
				.newInstance();
		List<InvestigatorType> investigatorTypes = new ArrayList<>();
		award.getProjectPersons().stream().filter(awardPerson -> awardPerson.isPrincipalInvestigator() || awardPerson.isMultiplePi() || awardPerson.isCoInvestigator()).forEach(awardPerson -> {
			InvestigatorType investigatorType = getInvestigatorType(awardPerson);
			investigatorTypes.add(investigatorType);
		});
		awardInvestigators.setInvestigatorArray(investigatorTypes
				.toArray(new InvestigatorType[0]));
		return awardInvestigators;
	}

	/**
	 * <p>
	 * This method will set the values to Award science codes attributes and
	 * finally returns Award science codes XmlObject
	 * </p>
	 * 
	 * @return returns Award science codes XmlObject
	 */
	protected AwardScienceCodes getAwardScienceCodes() {
		AwardScienceCodes awardScienceCodes = AwardScienceCodes.Factory
				.newInstance();
		List<ScienceCodeDetailType> scienceCodeDetailTypes = new ArrayList<>();
		for (AwardScienceKeyword awardScienceKeyword : award.getKeywords()) {
			ScienceCodeDetailType scienceCodeDetailType = ScienceCodeDetailType.Factory
					.newInstance();
			if (award.getAwardNumber() != null) {
				scienceCodeDetailType.setAwardNumber(award.getAwardNumber());
			}
			if (award.getSequenceNumber() != null) {
				scienceCodeDetailType.setSequenceNumber(award
						.getSequenceNumber());
			}
			if (awardScienceKeyword.getScienceKeywordCode() != null) {
				scienceCodeDetailType.setCode(awardScienceKeyword
						.getScienceKeywordCode());
			}
			if (awardScienceKeyword.getScienceKeyword().getDescription() != null) {
				scienceCodeDetailType.setDescription(awardScienceKeyword
						.getScienceKeyword().getDescription());				
			}
			scienceCodeDetailTypes.add(scienceCodeDetailType);
		}
		awardScienceCodes.setScienceCodeDetailArray(scienceCodeDetailTypes
				.toArray(new ScienceCodeDetailType[0]));		
		return awardScienceCodes;
	}

	/**
	 * <p>
	 * This method will set the values to Award Terms Details attributes and
	 * finally returns Award Terms Details XmlObject
	 * </p>
	 * 
	 * @return returns Award Terms Details XmlObject
	 */
	protected AwardTermsDetails getAwardTermsDetails() {
		AwardTermsDetails awardTermsDetails = AwardTermsDetails.Factory.newInstance();
		String sponsorTermTypeCode = null;
        TermType2 termType = null;
		for (AwardSponsorTerm awardSponsorTerm : award.getAwardSponsorTerms()) {
		    if(sponsorTermTypeCode==null || !sponsorTermTypeCode.equals(awardSponsorTerm.getSponsorTermTypeCode())){
		        termType = awardTermsDetails.addNewTerm();
		    }
            sponsorTermTypeCode = awardSponsorTerm.getSponsorTermTypeCode();
		    String sponsorTermTypeDescription =  getSponsorTermTypeDescription(awardSponsorTerm.getSponsorTermTypeCode());
		    if(sponsorTermTypeDescription!=null){
		        termType.setDescription(sponsorTermTypeDescription);
		    }
			TermDetailsType2 detailsType = termType.addNewTermDetails();
			if (award.getAwardNumber() != null) {
				detailsType.setAwardNumber(award.getAwardNumber());
			}
			if (awardSponsorTerm.getSequenceNumber() != null) {
				detailsType.setSequenceNumber(awardSponsorTerm
						.getSequenceNumber());
			}
			if (awardSponsorTerm.getSponsorTerm() != null
					&& awardSponsorTerm.getSponsorTerm().getSponsorTermCode() != null) {
				detailsType.setTermCode(Integer.valueOf(awardSponsorTerm
						.getSponsorTerm().getSponsorTermCode()));
			}
			if (awardSponsorTerm.getSponsorTerm() != null
					&& awardSponsorTerm.getSponsorTerm().getDescription() != null) {
				detailsType.setTermDescription(awardSponsorTerm
						.getSponsorTerm().getDescription());
			}
		}
		return awardTermsDetails;
	}

	private String getSponsorTermTypeDescription(String sponsorTermTypeCode) {
	    SponsorTermType sponsorTermType = getBusinessObjectService().findBySinglePrimaryKey(SponsorTermType.class, sponsorTermTypeCode);
	    return sponsorTermType==null?null:sponsorTermType.getDescription();
    }

    /**
	 * <p>
	 * This method will set the values to Award Details attributes and finally
	 * returns AwardDetails XmlObject
	 * </p>
	 * 
	 * @return returns AwardDetails XmlObject
	 */
	protected AwardDetails getAwardDetails() {
		AwardDetails awardDetailsType = AwardDetails.Factory.newInstance();
		AwardHeaderType awardHeaderType = getAwardHeaderType();
		awardDetailsType.setAwardHeader(awardHeaderType);
		awardDetailsType.setOtherHeaderDetails(getOtherHeaderDetails());
		if (prevAward != null) {
			String beginDateModified = getBeginDateModified();
			if (beginDateModified != null) {
				awardDetailsType.setBeginDateModified(beginDateModified);
			}
			String executionDateModified = getExecutionDateModified();
			if (executionDateModified != null) {
				awardDetailsType
						.setExecutionDateModified(executionDateModified);
			}
			String effectiveDateModified = getEffectiveDateModified();
			if (effectiveDateModified != null) {
				awardDetailsType
						.setEffectiveDateModified(effectiveDateModified);
			}
		}
		Calendar beginDate = getBeginDate();
		if (beginDate != null) {
			awardDetailsType.setBeginDate(beginDate);
		}
		Calendar executionDate = getExecutionDate();
		if (executionDate != null) {
			awardDetailsType.setExecutionDate(executionDate);
		}
		Calendar effectiveDate = getEffectiveDate();
		if (effectiveDate != null) {
			awardDetailsType.setEffectiveDate(effectiveDate);
		}
		awardDetailsType.setApprvdEquipmentIndicator(getIndicator(award.getApprovedEquipmentItems()));
		awardDetailsType.setApprvdForeginTripIndicator(getIndicator(award.getApprovedForeignTravelTrips()));
		awardDetailsType.setApprvdSubcontractIndicator(getIndicator(award.getAwardApprovedSubawards()));
		awardDetailsType.setPaymentScheduleIndicator(getIndicator(award.getPaymentScheduleItems()));
		awardDetailsType.setIDCIndicator(getIndicator(award.getAwardFandaRate()));
		awardDetailsType.setTransferSponsorIndicator(getIndicator(award.getAwardTransferringSponsors()));
		awardDetailsType.setCostSharingIndicator(getIndicator(award.getAwardCostShares()));
		awardDetailsType.setCostSharingProjectPeriodFieldDescription(getProjectPeriodFieldDescription());	
		awardDetailsType.setSpecialReviewIndicator(getIndicator(award.getSpecialReviews()));
		awardDetailsType.setScienceCodeIndicator(getIndicator(award.getKeywords()));
		return awardDetailsType;
	}
	
	private String getProjectPeriodFieldDescription() {
	    return costShareService.getCostShareLabel();
	}
	
	private String getIndicator(List list){
	    return list.isEmpty()?NOT_PRESENT_INDICATOR:PRESENT_INDICATOR;
	}

	/**
	 * <p>
	 * This method will set the values to Award Amount Info attributes and
	 * finally returns Award Amount Info XmlObject
	 * </p>
	 * 
	 * @return returns Award Amount Info XmlObject
	 */
	protected org.kuali.kra.printing.schema.AwardType.AwardAmountInfo getAwardAmountInfo() {
		org.kuali.kra.printing.schema.AwardType.AwardAmountInfo awardAmountInfoType;
		awardAmountInfoType = org.kuali.kra.printing.schema.AwardType.AwardAmountInfo.Factory
				.newInstance();
		if (awardAmountInfo != null) {
			AmountInfoType amountInfoType = AmountInfoType.Factory
					.newInstance();
			if (award.getAwardNumber() != null) {
				amountInfoType.setAwardNumber(award.getAwardNumber());
			}
			if (award.getSequenceNumber() != null) {
				amountInfoType.setSequenceNumber(awardAmountInfo
						.getSequenceNumber());
			}
			if (awardAmountInfo.getSequenceNumber() != null) {
				amountInfoType.setAmountSequenceNumber(awardAmountInfo
						.getSequenceNumber());
			}
			BigDecimal anticipatedTotalAmount = getAnticipatedTotalAmount();
			if (anticipatedTotalAmount != null) {
				amountInfoType.setAnticipatedTotalAmt(anticipatedTotalAmount);
			}
			BigDecimal antDistributableAmount = getAntDistributableAmount();
			if (antDistributableAmount != null) {
				amountInfoType
						.setAnticipatedDistributableAmt(antDistributableAmount);
			}
			Calendar finalExpirationDate = getFinalExpirationDate();
			if (finalExpirationDate != null) {
				amountInfoType.setFinalExpirationDate(finalExpirationDate);
			}
			Calendar currentFundEffectiveDate = getCurrentFundEffectiveDate();
			if (currentFundEffectiveDate != null) {
				amountInfoType
						.setCurrentFundEffectiveDate(currentFundEffectiveDate);
			}
			if (prevAwardAmountInfo != null) {
				String finalExpirationDateModified = getFinalExpirationDateModified();
				if (finalExpirationDateModified != null) {
					amountInfoType
							.setFinalExpDateModified(finalExpirationDateModified);
				}
				String currentFundEffectiveDateModified = getCurrentFundEffectiveDateModified();
				if (currentFundEffectiveDateModified != null) {
					amountInfoType
							.setCurrentFundEffectiveDateModified(currentFundEffectiveDateModified);
				}
				String obligatedDistributableAmtModified = getObligatedDistributableAmtModified();
				if (obligatedDistributableAmtModified != null) {
					amountInfoType
							.setObligatedDistributableAmtModified(obligatedDistributableAmtModified);
				}
				String obligationExpirationDateModified = getObligationExpirationDateModified();
				if (obligationExpirationDateModified != null) {
					amountInfoType
							.setObligationExpDateModified(obligationExpirationDateModified);
				}
			}
			BigDecimal amountObligatedToDate = getAmtObligatedToDate();
			if (amountObligatedToDate != null) {
				amountInfoType.setAmtObligatedToDate(amountObligatedToDate);
			}
			BigDecimal obligatedDistributableAmount = getObligatedDistributableAmt();
			if (obligatedDistributableAmount != null) {
				amountInfoType
						.setObligatedDistributableAmt(obligatedDistributableAmount);
			}
			Calendar obligationExpirationDate = getObligationExpirationDate();
			if (obligationExpirationDate != null) {
				amountInfoType
						.setObligationExpirationDate(obligationExpirationDate);
			}
			if (awardAmountInfo.getTransactionId() != null) {
				amountInfoType.setTransactionId(String.valueOf(awardAmountInfo
						.getTransactionId()));
			}
			BigDecimal anticipatedChange = getAnticipatedChange();
			if (anticipatedChange != null) {
				amountInfoType.setAnticipatedChange(anticipatedChange);
			}
			BigDecimal obligatedChange = getObligatedChange();
			if (obligatedChange != null) {
				amountInfoType.setObligatedChange(obligatedChange);
			}
			amountInfoType.setEntryType(String.valueOf(awardAmountInfo
					.getEntryType()));
			amountInfoType.setEOMProcess(true);
			if (award.getAwardEffectiveDate() != null) {
				Calendar totalStartDate = dateTimeService.getCalendar(award
						.getAwardEffectiveDate());
				if (totalStartDate != null) {
					amountInfoType.setTotalStartDate(totalStartDate);
				}
			}
			if (award.getProjectEndDate() != null) {
				Calendar totalEndDate = dateTimeService.getCalendar(award
						.getProjectEndDate());
				if (totalEndDate != null) {
					amountInfoType.setTotalEndDate(totalEndDate);
				}
			}
			ScaleTwoDecimal obligatedChangeDirect = getObligatedChangeDirect();
			if (obligatedChangeDirect != null) {
				amountInfoType.setObligatedChangeDirect(obligatedChangeDirect.bigDecimalValue());
			}
			ScaleTwoDecimal obligatedChangeIndirect = getObligatedChangeIndirect();
			if (obligatedChangeIndirect != null) {
				amountInfoType
						.setObligatedChangeIndirect(obligatedChangeIndirect.bigDecimalValue());
			}
			ScaleTwoDecimal anticipatedChangeDirect = getAnticipatedChangeDirect();
			if (anticipatedChangeDirect != null) {
				amountInfoType
						.setAnticipatedChangeDirect(anticipatedChangeDirect.bigDecimalValue());
			}
			ScaleTwoDecimal anticipatedChangeIndirect = getAnticipatedChangeIndirect();
			if (anticipatedChangeIndirect != null) {
				amountInfoType
						.setAnticipatedChangeIndirect(anticipatedChangeIndirect.bigDecimalValue());
			}
			BigDecimal anticipatedTotalDirect = getAnticipatedTotalDirect();
			if (anticipatedTotalDirect != null) {
				amountInfoType
						.setAnticipatedTotalDirect(anticipatedTotalDirect);
			}
			BigDecimal anticipatedTotalIndirect = getAnticipatedTotalIndirect();
			if (anticipatedTotalIndirect != null) {
				amountInfoType
						.setAnticipatedTotalIndirect(anticipatedTotalIndirect);
			}
			BigDecimal obligatedTotalDirect = getObligatedTotalDirect();
			if (obligatedTotalDirect != null) {
				amountInfoType.setObligatedTotalDirect(obligatedTotalDirect);
			}
			BigDecimal obligatedTotalIndirect = getObligatedTotalIndirect();
			if (obligatedTotalIndirect != null) {
				amountInfoType
						.setObligatedTotalIndirect(obligatedTotalIndirect);
			}
			String enableAwdAntOblDirectIndirectCost = getAwardParameterValue(OBLIGATED_DIRECT_INDIRECT_COST_CONSTANT);
			if (enableAwdAntOblDirectIndirectCost != null) {
				amountInfoType
						.setEnableAwdAntOblDirectIndirectCost(enableAwdAntOblDirectIndirectCost);
			}
			AmountInfoType[] amountInfoTypes = { amountInfoType };
			awardAmountInfoType.setAmountInfoArray(amountInfoTypes);
		}
		return awardAmountInfoType;
	}

	/*
	 * 
	 * This method will get the sponsor award number.
	 * 
	 * If AwardDeltaXmlStream instance calling this method then checks if
	 * prevAward sponsorAwardNumber null or prevAward sponsorAwardNumber is not
	 * equal to award sponsorAwardNumber then *(Asterisk)append to description
	 * otherwise return award sponsorAwardNumber
	 * 
	 * If AwardNoticeXmlStream {@link AwardNoticeXmlStream} instance calling
	 * this method then returns award sponsorAwardNumber
	 * 
	 */
	private String getSponsorAwardNumber() {
		String sponsorAwardNumber = null;
		if (prevAward != null) {
			if (award.getSponsorAwardNumber() != null) {
				if (prevAward.getSponsorAwardNumber() == null
						|| !award.getSponsorAwardNumber().equals(
								prevAward.getSponsorAwardNumber())) {
					sponsorAwardNumber = new StringBuilder(award
							.getSponsorAwardNumber()).append(
							END_ASTERISK_SPACE_INDICATOR).toString();
				} else {
					sponsorAwardNumber = award.getSponsorAwardNumber();
				}
			} else if (prevAward.getSponsorAwardNumber() != null) {
				sponsorAwardNumber = START_ASTERISK_SPACE_INDICATOR;
			}
		} else {
			sponsorAwardNumber = award.getSponsorAwardNumber();
		}
		return sponsorAwardNumber;
	}

	/*
	 * 
	 * This method will get the SponsorDescriptionr.
	 * 
	 * If AwardDeltaXmlStream instance calling this method then checks if
	 * prevAward SponsorDescription null or prevAward SponsorDescription is not
	 * equal to award SponsorDescription then *(Asterisk)append to description
	 * otherwise return award SponsorDescription
	 * 
	 * If AwardNoticeXmlStream instance calling this method then returns award
	 * SponsorDescription
	 * 
	 */
	private String getSponsorDescription() {
		String sponsordescription = null;
		if (prevAward != null) {
			if (award.getSponsorCode() != null) {
				if (prevAward.getSponsorCode() == null
						|| !award.getSponsorCode().equals(
								prevAward.getSponsorCode())) {
					sponsordescription = new StringBuilder(award
							.getSponsorName()).append(
							END_ASTERISK_SPACE_INDICATOR).toString();
				} else {
					sponsordescription = award.getSponsorName();
				}
			} else if (prevAward.getSponsorCode() != null) {
				sponsordescription = START_ASTERISK_SPACE_INDICATOR;
			}
		} else {
			sponsordescription = award.getSponsorName();
		}
		return sponsordescription;
	}

	/*
	 * This method will set the values to Report TermDetails Type and finally
	 * return Report TermDetails Type XmlObject.
	 * 
	 */
	private ReportTermDetailsType2 getAwardReportTermBean(
			AwardReportTerm awardReportTerm, ReportTermType2 reportTermType) {
		ReportTermDetailsType2 reportTermDetailsType = reportTermType.addNewReportTermDetails();
		if (award.getAwardNumber() != null) {
			reportTermDetailsType.setAwardNumber(award.getAwardNumber());
		}
		if (award.getSequenceNumber() != null) {
			reportTermDetailsType.setSequenceNumber(award.getSequenceNumber());
		}
		if (awardReportTerm.getReportClassCode() != null) {
			reportTermDetailsType.setReportClassCode(Integer
					.valueOf(awardReportTerm.getReportClassCode()));
		}
		if (awardReportTerm.getReportClass() != null
				&& awardReportTerm.getReportClass().getDescription() != null) {
			reportTermDetailsType.setReportClassDesc(awardReportTerm
					.getReportClass().getDescription());
		}
		if (awardReportTerm.getReportCode() != null) {
			reportTermDetailsType.setReportCode(Integer.valueOf(awardReportTerm
					.getReportCode()));
		}
		if (awardReportTerm.getReport() != null
				&& awardReportTerm.getReport().getDescription() != null) {
			reportTermDetailsType.setReportCodeDesc(awardReportTerm.getReport()
					.getDescription());
		}
		if (awardReportTerm.getFrequencyCode() != null) {
			reportTermDetailsType.setFrequencyCode(Integer
					.valueOf(awardReportTerm.getFrequencyCode()));
		}
		if (awardReportTerm.getFrequency() != null
				&& awardReportTerm.getFrequency().getDescription() != null) {
			reportTermDetailsType.setFrequencyCodeDesc(awardReportTerm
					.getFrequency().getDescription());
		}
		if (awardReportTerm.getFrequencyBaseCode() != null) {
			reportTermDetailsType.setFrequencyBaseCode(Integer
					.valueOf(awardReportTerm.getFrequencyBaseCode()));
		}
		if (awardReportTerm.getFrequencyBase() != null && awardReportTerm.getFrequencyBase().getDescription() != null) {
			reportTermDetailsType.setFrequencyBaseDesc(awardReportTerm
					.getFrequencyBase().getDescription());
		}
		if (awardReportTerm.getOspDistributionCode() != null) {
			reportTermDetailsType.setOSPDistributionCode(Integer
					.valueOf(awardReportTerm.getOspDistributionCode()));
		}
		if (awardReportTerm.getDistribution() != null
				&& awardReportTerm.getDistribution().getDescription() != null) {
			reportTermDetailsType.setOSPDistributionDesc(awardReportTerm
					.getDistribution().getDescription());
		}
		Calendar dueDate = getDueDate(awardReportTerm);
		if (dueDate != null) {
			reportTermDetailsType.setDueDate(dueDate);
		}
		List<org.kuali.kra.printing.schema.ReportTermDetailsType2.MailCopies> mailCopiesList = getMailCopiesList(awardReportTerm);
		reportTermDetailsType.setMailCopiesArray(mailCopiesList
				.toArray(new org.kuali.kra.printing.schema.ReportTermDetailsType2.MailCopies[0]));
		return reportTermDetailsType;
	}

	private List<org.kuali.kra.printing.schema.ReportTermDetailsType2.MailCopies> getMailCopiesList(
			AwardReportTerm awardReportTerm) {
		List<org.kuali.kra.printing.schema.ReportTermDetailsType2.MailCopies> mailCopiesList = new ArrayList<>();
		for (AwardReportTermRecipient awardReportTermRecipient : awardReportTerm
				.getAwardReportTermRecipients()) {
			org.kuali.kra.printing.schema.ReportTermDetailsType2.MailCopies mailCopies = org.kuali.kra.printing.schema.ReportTermDetailsType2.MailCopies.Factory
					.newInstance();
			mailCopies.setContactTypeCode(Integer
					.parseInt(awardReportTermRecipient.getContactTypeCode()));
			mailCopies.setContactTypeDesc(awardReportTermRecipient
					.getContactType().getDescription());
			mailCopies.setNumberOfCopies(String
					.valueOf(awardReportTermRecipient.getNumberOfCopies()));
			mailCopies.setRolodexId(String.valueOf(awardReportTermRecipient
					.getRolodexId()));
			mailCopies.setRolodexName(awardReportTermRecipient.getRolodex()
					.getFullName());
			mailCopies.setRolodexOrganization(awardReportTermRecipient
					.getRolodex().getOrganization());
			mailCopiesList.add(mailCopies);
		}
		return mailCopiesList;
	}

	/*
	 * This method will get the calendar object for due date of award report
	 * term
	 */
	private Calendar getDueDate(AwardReportTerm awardReportTerm) {
		Calendar dueDate = null;
		if (awardReportTerm.getDueDate() != null) {
			dueDate = dateTimeService.getCalendar(awardReportTerm.getDueDate());
		}
		return dueDate;
	}

	/*
	 * This method will set the values to Rolodex Details Type and finally
	 * return Rolodex Details Type XmlObject.
	 * 
	 */
	private RolodexDetailsType2 getRolodexDetails(
			AwardSponsorContact awardSponsorContact) {
		RolodexDetailsType2 rolodexDetailsType = RolodexDetailsType2.Factory
				.newInstance();
		if (awardSponsorContact.getRolodex() != null) {
			Rolodex rolodex = awardSponsorContact.getRolodex();
			if (rolodex.getAddressLine1() != null) {
				rolodexDetailsType.setAddress1(rolodex.getAddressLine1());
			}
			if (rolodex.getAddressLine2() != null) {
				rolodexDetailsType.setAddress2(rolodex.getAddressLine2());
			}
			if (rolodex.getAddressLine3() != null) {
				rolodexDetailsType.setAddress3(rolodex.getAddressLine3());
			}
			if (rolodex.getCity() != null) {
				rolodexDetailsType.setCity(rolodex.getCity());
			}
			if (rolodex.getComments() != null) {
				rolodexDetailsType.setComments(rolodex.getComments());
			}
			if (rolodex.getCountryCode() != null) {
				rolodexDetailsType.setCountryCode(rolodex.getCountryCode());
			}
			String countryDesc = getCountryDescription(rolodex);
			if (countryDesc != null) {
				rolodexDetailsType.setCountryDescription(countryDesc);
			}
			if (rolodex.getCounty() != null) {
				rolodexDetailsType.setCounty(rolodex.getCounty());
			}
			if (rolodex.getEmailAddress() != null) {
				rolodexDetailsType.setEmail(rolodex.getEmailAddress());
			}
			if (rolodex.getFaxNumber() != null) {
				rolodexDetailsType.setFax(rolodex.getFaxNumber());
			}
			if (rolodex.getFirstName() != null) {
				rolodexDetailsType.setFirstName(rolodex.getFirstName());
			}
			if (rolodex.getMiddleName() != null) {
				rolodexDetailsType.setMiddleName(rolodex.getMiddleName());
			}
			String name = getName(rolodex);
			if (!name.equals(EMPTY_SPACE)) {
				rolodexDetailsType.setLastName(name);
			}
			if (rolodex.getOrganization() != null) {
				rolodexDetailsType.setOrganization(rolodex.getOrganization());
			}
			if (rolodex.getPhoneNumber() != null) {
				rolodexDetailsType.setPhoneNumber(rolodex.getPhoneNumber());
			}
			if (rolodex.getPostalCode() != null) {
				rolodexDetailsType.setPincode(rolodex.getPostalCode());
			}
			if (rolodex.getPrefix() != null) {
				rolodexDetailsType.setPrefix(rolodex.getPrefix());
			}
			if (rolodex.getRolodexId() != null) {
				rolodexDetailsType.setRolodexId(String.valueOf(rolodex
						.getRolodexId()));
			}
			if (rolodex.getSponsorCode() != null) {
				rolodexDetailsType.setSponsorCode(rolodex.getSponsorCode());
			}
			if (rolodex.getSponsor() != null
					&& rolodex.getSponsor().getSponsorName() != null) {
				rolodexDetailsType.setSponsorName(rolodex.getSponsor()
						.getSponsorName());
			}
			if (rolodex.getState() != null) {
				rolodexDetailsType.setStateCode(rolodex.getState());
			}
			String stateDesc = getState(rolodex);
			if (stateDesc != null) {
				rolodexDetailsType.setStateDescription(stateDesc);
			}
			if (rolodex.getSuffix() != null) {
				rolodexDetailsType.setSuffix(rolodex.getSuffix());
			}
			if (rolodex.getTitle() != null) {
				rolodexDetailsType.setTitle(rolodex.getTitle());
			}
			if (rolodex.getOwnedByUnit() != null) {
				rolodexDetailsType.setOwnedByUnit(rolodex.getOwnedByUnit());
			}
			if (rolodex.getUnit() != null
					&& rolodex.getUnit().getUnitName() != null) {
				rolodexDetailsType.setOwnedByUnit(rolodex.getUnit()
						.getUnitName());
			}
		}
		return rolodexDetailsType;
	}

	/*
	 * This method will get the state description for given state code from
	 * rolodex
	 */
	private String getState(Rolodex rolodex) {
		String stateDesc = null;
		if (StringUtils.isNotBlank(rolodex.getState()) && StringUtils.isNotBlank(rolodex.getCountryCode())) {
			State state = PrintingUtils.getStateFromName(rolodex.getCountryCode(), rolodex.getState());
			if (state != null) {
				stateDesc = state.getName();
			}
		} else if (StringUtils.isNotBlank(rolodex.getState())) {
			return rolodex.getState();
		}
		return stateDesc;
	}

	/*
	 * This method will get the name for rolodex
	 */
	private String getName(Rolodex rolodex) {
		StringBuilder name = new StringBuilder();
		if (rolodex.getLastName() != null) {
			name.append(rolodex.getLastName());
		}
		if (rolodex.getSuffix() != null) {
			name.append(EMPTY_SPACE).append(rolodex.getSuffix());
		}
		if (rolodex.getPrefix() != null) {
			name.append(COMMA_STRING).append(rolodex.getPrefix());
			if (rolodex.getFirstName() != null) {
				name.append(EMPTY_SPACE).append(rolodex.getFirstName());
			}
		} else if (rolodex.getFirstName() != null) {
			name.append(COMMA_STRING).append(rolodex.getFirstName());
		}
		if (rolodex.getMiddleName() != null) {
			name.append(EMPTY_SPACE).append(rolodex.getMiddleName());
		}
		return name.toString();
	}

	/*
	 * This method will get the country description for given country code from
	 * rolodex
	 */
	private String getCountryDescription(Rolodex rolodex) {
		String countryDesc = null;
		if (StringUtils.isNotBlank(rolodex.getCountryCode())) {
			Country country = PrintingUtils.getCountryFromCode(rolodex
					.getCountryCode());
			if (country != null) {
				countryDesc = country.getName();
			}
		}
		return countryDesc;
	}

	/*
	 * This method will get the final prop submission date modified
	 */
	private String getFinalProbSubDateModified(AwardCloseout awardCloseout,
			AwardCloseout preAwardCloseout) {
		return getIndicatorIfDatesChanged(preAwardCloseout.getFinalSubmissionDate(),awardCloseout.getFinalSubmissionDate());
	}

	/*
	 * This method will get the closeout date modified
	 */
	private String getCloseOutDateModified(AwardCloseout awardCloseout,
			AwardCloseout preAwardCloseout) {
		return getIndicatorIfDatesChanged(preAwardCloseout.getDueDate(),awardCloseout.getDueDate());
	}
	private String getIndicatorIfDatesChanged(Date preAwardDate, Date awardDate) {
		String finalPropSubDateModified = null;
		if (preAwardDate == null && awardDate != null) {
			finalPropSubDateModified = END_ASTERISK_SPACE_INDICATOR;
		} else if (preAwardDate != null && !preAwardDate.equals(awardDate)) {
			finalPropSubDateModified = END_ASTERISK_SPACE_INDICATOR;
		}
		return finalPropSubDateModified;
	}

	/*
	 * This method will get the indirect cost comments
	 */
	private String getIndirectCostComments() {
		String comments = null;
		for (AwardComment awardComment : award.getAwardComments()) {
			if (awardComment.getCommentTypeCode() != null
					&& awardComment.getCommentTypeCode().equals(IDC_COMMENT)) {
				comments = awardComment.getComments();
				break;
			}
		}
		return comments;
	}

	/*
	 * This method will set the values to CostSharing Item and finally return
	 * CostSharing Item XmlObject array.
	 * 
	 */
	private CostSharingItem[] getCostSharingItemDetails() {
		List<CostSharingItem> costSharingItems = new ArrayList<>();
		CostSharingItem costSharingItem;
		for (AwardCostShare awardCostShare : award.getAwardCostShares()) {
			costSharingItem = CostSharingItem.Factory.newInstance();
			if (awardCostShare.getAwardNumber() != null) {
				costSharingItem.setAwardNumber(awardCostShare.getAwardNumber());
			}
			if (awardCostShare.getSequenceNumber() != null) {
				costSharingItem.setSequenceNumber(awardCostShare
						.getSequenceNumber());
			}
			if (awardCostShare.getProjectPeriod() != null) {
				costSharingItem.setFiscalYear(awardCostShare.getProjectPeriod());
			}
			if (awardCostShare.getCostSharePercentage() != null) {
				costSharingItem.setPercentage(awardCostShare
						.getCostSharePercentage().doubleValue());
			}
			if (awardCostShare.getCostShareTypeCode() != null) {
				costSharingItem.setCostSharingType(awardCostShare
						.getCostShareTypeCode());
			}
			if (awardCostShare.getCostShareType() != null
					&& awardCostShare.getCostShareType().getDescription() != null) {
				costSharingItem.setCostSharingDescription(awardCostShare
						.getCostShareType().getDescription());
			}
			String sourceAccount = awardCostShare.getSource();
			if (sourceAccount != null) {
				costSharingItem.setSourceAccount(sourceAccount);
			}
			if (awardCostShare.getDestination() != null) {
				costSharingItem.setDestinationAccount(awardCostShare
						.getDestination());
			}
			if (awardCostShare.getAmount() != null) {
				costSharingItem.setAmount(awardCostShare.getAmount()
						.bigDecimalValue());
			}
			costSharingItems.add(costSharingItem);
		}
		return costSharingItems.toArray(new CostSharingItem[0]);
	}

	/*
	 * This method will get the Cost sharing comments
	 */
	private String getCostSharingComments() {
		String comments = null;
		for (AwardComment awardComment : award.getAwardComments()) {
			if (awardComment.getCommentTypeCode() != null
					&& awardComment.getCommentTypeCode().equals(
							COST_SHARING_COMMENT)) {
				comments = awardComment.getComments();
				break;
			}
		}
		return comments;
	}

	/*
	 * This method will set the values to Foreign Travel and finally return
	 * Foreign Travel XmlObject array.
	 * 
	 */
	private ForeignTravel[] getForeignTravel() {
		List<ForeignTravel> foreignTravels = new ArrayList<>();
		ForeignTravel foreignTravel;
		for (AwardApprovedForeignTravel approvedForeignTravel : award
				.getApprovedForeignTravelTrips()) {
			foreignTravel = ForeignTravel.Factory.newInstance();
			if (approvedForeignTravel.getAwardNumber() != null) {
				foreignTravel.setAwardNumber(approvedForeignTravel
						.getAwardNumber());
			}
			if (approvedForeignTravel.getSequenceNumber() != null) {
				foreignTravel.setSequenceNumber(approvedForeignTravel
						.getSequenceNumber());
			}
			if (approvedForeignTravel.getContactId() != null) {
				foreignTravel.setPersonId(approvedForeignTravel.getContactId()
						.toString());
			}
			if (approvedForeignTravel.getTravelerName() != null) {
				foreignTravel.setPersonName(approvedForeignTravel
						.getTravelerName());
			}
			if (approvedForeignTravel.getDestination() != null) {
				foreignTravel.setDestination(approvedForeignTravel
						.getDestination());
			}
			if (approvedForeignTravel.getStartDate() != null) {
				Calendar startDate = dateTimeService
						.getCalendar(approvedForeignTravel.getStartDate());
				foreignTravel.setDateFrom(startDate);
			}
			if (approvedForeignTravel.getEndDate() != null) {
				Calendar endDate = dateTimeService
						.getCalendar(approvedForeignTravel.getEndDate());
				foreignTravel.setDateTo(endDate);
			}
			if (approvedForeignTravel.getAmount() != null) {
				foreignTravel.setAmount(approvedForeignTravel.getAmount()
						.bigDecimalValue());
			}
			foreignTravels.add(foreignTravel);
		}
		return foreignTravels.toArray(new ForeignTravel[0]);
	}

	/*
	 * This method will set the values to Equipment and finally return Equipment
	 * XmlObject array.
	 * 
	 */
	private Equipment[] getEquipmentType() {
		List<Equipment> equipments = new ArrayList<>();
		Equipment equipment;
		for (AwardApprovedEquipment approvedEquipment : award
				.getApprovedEquipmentItems()) {
			equipment = Equipment.Factory.newInstance();
			if (approvedEquipment.getAwardNumber() != null) {
				equipment.setAwardNumber(approvedEquipment.getAwardNumber());
			}
			if (approvedEquipment.getSequenceNumber() != null) {
				equipment.setSequenceNumber(approvedEquipment
						.getSequenceNumber());
			}
			if (approvedEquipment.getItem() != null) {
				equipment.setItem(approvedEquipment.getItem());
			}
			if (approvedEquipment.getVendor() != null) {
				equipment.setVendor(approvedEquipment.getVendor());
			}
			if (approvedEquipment.getModel() != null) {
				equipment.setModel(approvedEquipment.getModel());
			}
			if (approvedEquipment.getAmount() != null) {
				equipment.setAmount(approvedEquipment.getAmount()
						.bigDecimalValue());
			}
			equipments.add(equipment);
		}
		return equipments.toArray(new Equipment[0]);
	}

	/*
	 * This method will get the percentage effort of award person
	 */
	private BigDecimal getPercentageEffort(AwardPerson awardPerson) {
		BigDecimal percentageEffort = null;
		if (awardPerson.getTotalEffort() != null) {
			BigDecimal bdecAmount = new BigDecimal(awardPerson.getTotalEffort()
					.doubleValue());
			percentageEffort = bdecAmount.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return percentageEffort;
	}

	/*
	 * This method will set the values to Investigator Bean of award person and
	 * finally returns Investigator Bean XmlObject.
	 */
	private InvestigatorType getInvestigatorType(AwardPerson awardPerson) {
		InvestigatorType investigatorType = InvestigatorType.Factory
				.newInstance();
		if (award.getAwardNumber() != null) {
			investigatorType.setAwardNumber(award.getAwardNumber());
		}
		if (award.getSequenceNumber() != null) {
			investigatorType.setSequenceNumber(awardPerson.getSequenceNumber());
		}
		if (awardPerson.getPersonId() != null) {
			investigatorType.setPersonId(awardPerson.getPersonId());
		}
		if (awardPerson.getPerson() != null
				&& awardPerson.getPerson().getFullName() != null) {
			investigatorType.setPersonName(awardPerson.getPerson()
					.getFullName());
		}
		investigatorType.setPrincipalInvestigator(awardPerson
				.isPrincipalInvestigator());
		if (awardPerson.getPerson() != null
				&& awardPerson.getPerson().getOfficeLocation() != null) {
			investigatorType.setPersonAddress(awardPerson.getPerson()
					.getOfficeLocation());
		}
		investigatorType.setNonEmployee(!awardPerson.isEmployee());
		investigatorType.setFaculty(awardPerson.isFaculty());
		investigatorType.setCOIFlag(true);
		if (awardPerson.getTotalEffort() != null) {
			investigatorType.setPercentEffort(awardPerson.getTotalEffort()
					.bigDecimalValue());
		}

		investigatorType.setInvestigatorUnitArray(getInvestigatorUnitsTypes(
				awardPerson).toArray(new InvestigatorUnitsType[0]));
		return investigatorType;
	}

	private List<InvestigatorUnitsType> getInvestigatorUnitsTypes(
			AwardPerson awardPerson) {
		List<InvestigatorUnitsType> investigatorUnitsTypes = new ArrayList<>();
		for (AwardPersonUnit awardPersonUnit : awardPerson.getUnits()) {
			InvestigatorUnitsType investigatorUnitsType = InvestigatorUnitsType.Factory
					.newInstance();
			if (award.getAwardNumber() != null) {
				investigatorUnitsType.setAwardNumber(award.getAwardNumber());
			}
			if (award.getSequenceNumber() != null) {
				investigatorUnitsType.setSequenceNumber(awardPerson
						.getSequenceNumber());
			}
			if (awardPerson.getPersonId() != null) {
				investigatorUnitsType.setPersonId(awardPerson.getPersonId());
			}
			if (awardPersonUnit.getUnitNumber() != null) {
				investigatorUnitsType.setUnitNumber(awardPersonUnit
						.getUnitNumber());
			}
			if (awardPersonUnit.getUnitName() != null) {
				investigatorUnitsType
						.setUnitName(awardPersonUnit.getUnitName());
			}
			investigatorUnitsType.setLeadUnit(awardPersonUnit.isLeadUnit());
			if (awardPerson.getAward() != null
					&& awardPerson.getAward().getOspAdministratorName() != null) {
				investigatorUnitsType.setOSPAdminName(awardPerson.getAward()
						.getOspAdministratorName());
			}
			investigatorUnitsType
					.setUnitAdministratorArray(getUnitAdministratorList(
							awardPersonUnit)
							.toArray(
									new InvestigatorUnitsType.UnitAdministrator[0]));
			investigatorUnitsTypes.add(investigatorUnitsType);
		}
		return investigatorUnitsTypes;
	}

	private List<InvestigatorUnitsType.UnitAdministrator> getUnitAdministratorList(
			AwardPersonUnit awardPersonUnit) {
		List<InvestigatorUnitsType.UnitAdministrator> unitAdministratorList = new ArrayList<>();
		for (UnitAdministrator unitAdministrator : awardPersonUnit.getUnit()
				.getUnitAdministrators()) {
			InvestigatorUnitsType.UnitAdministrator unitAdministratorElement = InvestigatorUnitsType.UnitAdministrator.Factory
					.newInstance();
			if (unitAdministrator.getUnitAdministratorTypeCode() != null) {
				unitAdministratorElement.setUnitAdministratorTypeCode(Integer
						.parseInt(unitAdministrator
								.getUnitAdministratorTypeCode()));
			}
			if (unitAdministrator.getPersonId() != null) {
				unitAdministratorElement.setAdministrator(unitAdministrator
						.getPersonId());
			}
			if (unitAdministrator.getPerson() != null
					&& unitAdministrator.getPerson().getFullName() != null) {
				unitAdministratorElement.setAdministratorName(unitAdministrator
						.getPerson().getFullName());
			}
			unitAdministratorList.add(unitAdministratorElement);
		}
		return unitAdministratorList;
	}

	/*
	 * This method will get the anticipated total indirect of award Amount Info
	 */
	private BigDecimal getAnticipatedTotalIndirect() {
		BigDecimal anticipatedTotalIndirect = null;
		if (awardAmountInfo.getAnticipatedTotalIndirect() != null) {
			BigDecimal bdecAnticipatedTotalIndirect = new BigDecimal(
					awardAmountInfo.getAnticipatedTotalIndirect().doubleValue());
			anticipatedTotalIndirect = bdecAnticipatedTotalIndirect.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return anticipatedTotalIndirect;
	}

	/*
	 * This method will get the anticipated total direct of award Amount Info
	 */
	private BigDecimal getAnticipatedTotalDirect() {
		BigDecimal anticipatedTotalDirect = null;
		if (awardAmountInfo.getAnticipatedTotalDirect() != null) {
			anticipatedTotalDirect = new BigDecimal(awardAmountInfo
					.getAnticipatedTotalDirect().doubleValue());
			anticipatedTotalDirect = anticipatedTotalDirect.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return anticipatedTotalDirect;
	}

	/*
	 * This method will get the Anticipated Change Indirect of award Amount Info
	 */
	private ScaleTwoDecimal getAnticipatedChangeIndirect() {
		ScaleTwoDecimal anticipatedChangeIndirect = null;
		if (awardAmountInfo.getAnticipatedChangeIndirect() != null) {
			anticipatedChangeIndirect = awardAmountInfo.getAnticipatedChangeIndirect();
		}
		return anticipatedChangeIndirect;
	}

	/*
	 * This method will get the anticipated change direct of award Amount Info
	 */
	private ScaleTwoDecimal getAnticipatedChangeDirect() {
	    ScaleTwoDecimal anticipatedChangeDirect = null;
		if (awardAmountInfo.getAnticipatedChangeDirect() != null) {
			anticipatedChangeDirect = awardAmountInfo.getAnticipatedChangeDirect();
		}
		return anticipatedChangeDirect;
	}

	/*
	 * This method will get the obligated total indirect of award amount info
	 */
	private BigDecimal getObligatedTotalIndirect() {
		BigDecimal obligatedTotalIndirect = null;
		if (awardAmountInfo.getObligatedTotalIndirect() != null) {
			obligatedTotalIndirect = new BigDecimal(awardAmountInfo
					.getObligatedTotalIndirect().doubleValue());
			obligatedTotalIndirect = obligatedTotalIndirect.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return obligatedTotalIndirect;
	}

	/*
	 * This method will get the obligated total direct of award amount info
	 */
	private BigDecimal getObligatedTotalDirect() {
		BigDecimal obligatedTotalDirect = null;
		if (awardAmountInfo.getObligatedTotalDirect() != null) {
			obligatedTotalDirect = new BigDecimal(awardAmountInfo
					.getObligatedTotalDirect().doubleValue());
			obligatedTotalDirect = obligatedTotalDirect.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return obligatedTotalDirect;
	}

	/*
	 * This method will get the obligated change indirect of award amount info
	 */
	private ScaleTwoDecimal getObligatedChangeIndirect() {
	    ScaleTwoDecimal obligatedChangeIndirect = null;
		if (awardAmountInfo.getObligatedChangeIndirect() != null) {
			obligatedChangeIndirect = awardAmountInfo.getObligatedChangeIndirect();
		}
		return obligatedChangeIndirect;
	}

	/*
	 * This method will get the obligated change direct of award amount info
	 */
	private ScaleTwoDecimal getObligatedChangeDirect() {
	    ScaleTwoDecimal obligatedChangeDirect = null;
		if (awardAmountInfo.getObligatedChangeDirect() != null) {
			obligatedChangeDirect = awardAmountInfo.getObligatedChangeDirect();
		}
		return obligatedChangeDirect;
	}

	/*
	 * This method will get the obligated change of award amount info
	 */
	private BigDecimal getObligatedChange() {
		BigDecimal obligatedChange = null;
		if (awardAmountInfo.getObligatedChange() != null) {
			obligatedChange = awardAmountInfo.getObligatedChange()
					.bigDecimalValue();
			obligatedChange = obligatedChange.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return obligatedChange;
	}

	/*
	 * This method will get the Anticipated Change of award Amount Info
	 */
	private BigDecimal getAnticipatedChange() {
		BigDecimal anticipatedChange = null;
		if (awardAmountInfo.getAnticipatedChange() != null) {
			anticipatedChange = awardAmountInfo.getAnticipatedChange()
					.bigDecimalValue();
			anticipatedChange = anticipatedChange.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return anticipatedChange;
	}

	/*
	 * This method will get the obligated expiration date of award amount info
	 */
	private Calendar getObligationExpirationDate() {
		Calendar obligationExpirationDate = null;
		if (awardAmountInfo.getObligationExpirationDate() != null) {
			obligationExpirationDate = dateTimeService
					.getCalendar(awardAmountInfo.getObligationExpirationDate());
		}
		return obligationExpirationDate;
	}

	/*
	 * This method will get the obligated expiration date modified of award
	 * amount info
	 */
	private String getObligationExpirationDateModified() {
		String obligationExpirationDateModified = null;
		if (awardAmountInfo.getObligationExpirationDate() != null) {
			if (prevAwardAmountInfo != null
					&& prevAwardAmountInfo.getObligationExpirationDate() != null) {
				obligationExpirationDateModified = END_ASTERISK_SPACE_INDICATOR;
			}
		} else if (prevAwardAmountInfo != null
				&& prevAwardAmountInfo.getObligationExpirationDate() != null) {
			obligationExpirationDateModified = END_ASTERISK_SPACE_INDICATOR;
		}
		return obligationExpirationDateModified;
	}

	/*
	 * This method will get the obligated distributable amount of award amount
	 * info
	 */
	private BigDecimal getObligatedDistributableAmt() {
		BigDecimal obligatedDistributableAmt = null;
		if (awardAmountInfo.getObliDistributableAmount() != null) {
			obligatedDistributableAmt = awardAmountInfo
					.getObliDistributableAmount().bigDecimalValue();
			obligatedDistributableAmt = obligatedDistributableAmt.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return obligatedDistributableAmt;
	}

	/*
	 * This method will get the obligated distributable amount Modified of award
	 * amount info
	 */
	private String getObligatedDistributableAmtModified() {
		String obligatedDistributableAmtModified = null;
		double prevObligatedDistributableAmt;
		if (prevAwardAmountInfo != null
				&& prevAwardAmountInfo.getObliDistributableAmount() != null) {
			prevObligatedDistributableAmt = prevAwardAmountInfo
					.getObliDistributableAmount().doubleValue();
			if (prevObligatedDistributableAmt != awardAmountInfo
					.getObliDistributableAmount().doubleValue()) {
				obligatedDistributableAmtModified = END_ASTERISK_SPACE_INDICATOR;
			}
		}
		return obligatedDistributableAmtModified;
	}

	/*
	 * This method will get the amount obligated to date of award amount info
	 */
	private BigDecimal getAmtObligatedToDate() {
		BigDecimal amtObligatedToDate = null;
		if (awardAmountInfo.getAmountObligatedToDate() != null) {
			amtObligatedToDate = awardAmountInfo.getAmountObligatedToDate()
					.bigDecimalValue();
			amtObligatedToDate = amtObligatedToDate.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return amtObligatedToDate;
	}

	/*
	 * This method will get the current fund effective date of award amount info
	 */
	private Calendar getCurrentFundEffectiveDate() {
		Calendar currentFundEffectiveDate = null;
		if (awardAmountInfo.getCurrentFundEffectiveDate() != null) {
			currentFundEffectiveDate = dateTimeService
					.getCalendar(awardAmountInfo.getCurrentFundEffectiveDate());
		}
		return currentFundEffectiveDate;
	}

	/*
	 * This method will get the current fund effective date modified of award
	 * amount info
	 */
	private String getCurrentFundEffectiveDateModified() {
		String currentFundEffectiveDateModified = null;
		if (awardAmountInfo.getCurrentFundEffectiveDate() != null) {
			if (prevAwardAmountInfo != null
					&& (prevAwardAmountInfo.getCurrentFundEffectiveDate() == null || !(prevAwardAmountInfo
							.getCurrentFundEffectiveDate()
							.equals(awardAmountInfo
									.getCurrentFundEffectiveDate())))) {
				currentFundEffectiveDateModified = END_ASTERISK_SPACE_INDICATOR;
			}
		} else {
			if (prevAwardAmountInfo != null
					&& prevAwardAmountInfo.getCurrentFundEffectiveDate() != null) {
				currentFundEffectiveDateModified = END_ASTERISK_SPACE_INDICATOR;
			}
		}
		return currentFundEffectiveDateModified;
	}

	/*
	 * This method will get the final expiration date of award amount info
	 */
	private Calendar getFinalExpirationDate() {
		Calendar finalExpirationDate = null;
		if (awardAmountInfo.getFinalExpirationDate() != null) {
			Date finalExp = awardAmountInfo.getFinalExpirationDate();
			finalExpirationDate = dateTimeService.getCalendar(finalExp);
		}
		return finalExpirationDate;
	}

	/*
	 * This method will get the final expiration date modified of award amount
	 * info
	 */
	private String getFinalExpirationDateModified() {
		String finalExpirationDateModified = null;
		if (awardAmountInfo.getFinalExpirationDate() != null) {
			if (prevAwardAmountInfo != null
					&& (prevAwardAmountInfo.getFinalExpirationDate() == null || !(prevAwardAmountInfo
							.getFinalExpirationDate().equals(awardAmountInfo
							.getFinalExpirationDate())))) {
				finalExpirationDateModified = END_ASTERISK_SPACE_INDICATOR;
			}
		} else {
			if (prevAwardAmountInfo != null
					&& prevAwardAmountInfo.getFinalExpirationDate() != null) {
				finalExpirationDateModified = END_ASTERISK_SPACE_INDICATOR;
			}
		}
		return finalExpirationDateModified;
	}

	/*
	 * This method will get the anticipated distributable amount of award amount
	 * info
	 */
	private BigDecimal getAntDistributableAmount() {
		BigDecimal anticipatedDistributableAmt = null;
		if (awardAmountInfo.getAntDistributableAmount() != null) {
			anticipatedDistributableAmt = awardAmountInfo
					.getAntDistributableAmount().bigDecimalValue();
			anticipatedDistributableAmt = anticipatedDistributableAmt.setScale(
					2, BigDecimal.ROUND_HALF_DOWN);
		}
		return anticipatedDistributableAmt;
	}

	/*
	 * This method will get the anticipated total amount of award amount info
	 */
	private BigDecimal getAnticipatedTotalAmount() {
		BigDecimal anticipatedTotalAmt = null;
		if (awardAmountInfo.getAnticipatedTotalAmount() != null) {
			anticipatedTotalAmt = awardAmountInfo.getAnticipatedTotalAmount()
					.bigDecimalValue();
			anticipatedTotalAmt = anticipatedTotalAmt.setScale(2,
					BigDecimal.ROUND_HALF_DOWN);
		}
		return anticipatedTotalAmt;
	}

	/*
	 * This method will get the effective date
	 */
	private Calendar getEffectiveDate() {
		Calendar effectiveDate = null;
		if (award.getAwardEffectiveDate() != null) {
			effectiveDate = dateTimeService.getCalendar(award
					.getAwardEffectiveDate());
		}
		return effectiveDate;
	}

	/*
	 * This method will get the effective date modified
	 */
	private String getEffectiveDateModified() {
		String effectiveDateModified = null;
		if (award.getAwardEffectiveDate() != null) {
			if (prevAward.getAwardEffectiveDate() == null
					|| !award.getAwardEffectiveDate().equals(
							prevAward.getAwardEffectiveDate())) {
				effectiveDateModified = END_ASTERISK_SPACE_INDICATOR;
			}
		} else if (prevAward.getAwardEffectiveDate() != null) {
			effectiveDateModified = END_ASTERISK_SPACE_INDICATOR;
		}
		return effectiveDateModified;
	}

	/*
	 * This method will get the execution date
	 */
	private Calendar getExecutionDate() {
		Calendar executionDate = null;
		if (award.getAwardExecutionDate() != null) {
			executionDate = dateTimeService.getCalendar(award
					.getAwardExecutionDate());
		}
		return executionDate;
	}

	/*
	 * This method will get the begin date
	 */
	private Calendar getBeginDate() {
		Calendar beginDate = null;
		if (award.getBeginDate() != null) {
			beginDate = dateTimeService.getCalendar(award.getBeginDate());
		}
		return beginDate;
	}

	/*
	 * This method will get the begin date modified
	 */
	private String getBeginDateModified() {
		String beginDateModified = null;
		if (award.getBeginDate() != null) {
			if (prevAward.getBeginDate() == null
					|| !award.getBeginDate().equals(prevAward.getBeginDate())) {
				beginDateModified = END_ASTERISK_SPACE_INDICATOR;
			}
		} else if (prevAward.getBeginDate() != null) {
			beginDateModified = END_ASTERISK_SPACE_INDICATOR;
		}
		return beginDateModified;
	}

	/*
	 * This method will get the execution date modified
	 */
	private String getExecutionDateModified() {
		String executionDateModified = null;
		if (award.getAwardExecutionDate() != null) {
			if (prevAward.getAwardExecutionDate() == null
					|| !(prevAward.getAwardExecutionDate().equals(award
							.getAwardExecutionDate()))) {
				executionDateModified = END_ASTERISK_SPACE_INDICATOR;
			}
		} else if (prevAward.getAwardExecutionDate() != null) {
			executionDateModified = END_ASTERISK_SPACE_INDICATOR;
		}
		return executionDateModified;
	}

	/*
	 * This method will set the values to other header details attributes and
	 * finally returns OtherHeaderDetails XmlObject.
	 */
	private OtherHeaderDetails getOtherHeaderDetails() {
		OtherHeaderDetails otherHeaderDetails = OtherHeaderDetails.Factory
				.newInstance();
		if (award.getProposalNumber() != null) {
			otherHeaderDetails.setProposalNumber(award.getProposalNumber());
		}
		if (award.getAwardTypeCode() != null) {
			otherHeaderDetails.setAwardTypeCode(award.getAwardTypeCode());
		}
		if (award.getAwardTypeCode() != null) {
			if (prevAward != null && prevAward.getAwardTypeCode() != null) {
				String awardTypeDescription = getAwardTypeDesc(award
						.getAwardTypeCode());
				String prevAwardTypeDescription = getAwardTypeDesc(prevAward
						.getAwardTypeCode());
				String awardTypeDesc = getTypeDescription(awardTypeDescription,
						prevAwardTypeDescription);
				if (awardTypeDesc != null) {
					otherHeaderDetails.setAwardTypeDesc(awardTypeDesc);
				}
			} else {
				String awardTypeDesc = getAwardTypeDesc(award
						.getAwardTypeCode());
				if (awardTypeDesc != null) {
					otherHeaderDetails.setAwardTypeDesc(awardTypeDesc);
				}
			}
		}
		if (award.getSpecialEbRateOffCampus() != null) {
			otherHeaderDetails.setSpecialEBRateOffCampus(award.getSpecialEbRateOffCampus().bigDecimalValue());
		}
		if (award.getSpecialEbRateOnCampus() != null) {
			otherHeaderDetails.setSpecialEBRateOnCampus(award.getSpecialEbRateOnCampus().bigDecimalValue());
		}
		if (award.getAwardSpecialRate() != null
				&& award.getAwardSpecialRate().getComments() != null) {		  
			otherHeaderDetails.setSpecialRateComments(award.getAwardBenefitsRateComment().getComments());
		}
		ScaleTwoDecimal bdecPreAwardAuthorizedAmt = award
				.getPreAwardAuthorizedAmount() == null ? ScaleTwoDecimal.ZERO
				: award.getPreAwardAuthorizedAmount();
		otherHeaderDetails.setPreAwardAuthorizedAmt(bdecPreAwardAuthorizedAmt
				.bigDecimalValue().setScale(2, BigDecimal.ROUND_HALF_DOWN));
		ScaleTwoDecimal bdecPreAwardAuthorizedAmtModified = award
                .getPreAwardInstitutionalAuthorizedAmount() == null ? ScaleTwoDecimal.ZERO
                 : award.getPreAwardInstitutionalAuthorizedAmount();
		otherHeaderDetails.setPreAwardAuthorizedAmtModifeid(bdecPreAwardAuthorizedAmtModified
		        .bigDecimalValue().setScale(2, BigDecimal.ROUND_HALF_DOWN).toString());
		if (award.getPreAwardEffectiveDate() != null) {
		    Calendar preAwardEffectiveDate = dateTimeService.getCalendar(award
            .getPreAwardEffectiveDate());
		    otherHeaderDetails.setPreAwardEffectiveDate(preAwardEffectiveDate); 
		}       
		if(award.getPreAwardInstitutionalEffectiveDate()!=null){                   
		    otherHeaderDetails.setPreAwardEffectiveDateModifeid(dateTimeService.toDateString(award.getPreAwardInstitutionalEffectiveDate()));
		}
		if (prevAward != null) {
			String preAwardAuthorizedAmtModified = getPreAwardAuthorizedAmountModified();
			if (preAwardAuthorizedAmtModified != null) {
				otherHeaderDetails
						.setPreAwardAuthorizedAmtModifeid(preAwardAuthorizedAmtModified);
			}
			String awardEffectiveDate = getPreAwardEffectiveDateModified();
			if (awardEffectiveDate != null) {
				otherHeaderDetails
						.setPreAwardEffectiveDateModifeid(awardEffectiveDate);
			}
			if (award.getCfdaNumber() != null
					&& (prevAward.getCfdaNumber() == null || !award
							.getCfdaNumber().equals(prevAward.getCfdaNumber()))) {
				otherHeaderDetails.setCFDANumber(award.getCfdaNumber());
			}
			if (award.getSubPlanFlag() != null	&& 
			        (prevAward.getSubPlanFlag() != null || !award.getSubPlanFlag().equals(prevAward.getSubPlanFlag()))) {
				otherHeaderDetails.setSubPlan(award.getSubPlanFlag());
			}
			if (award.getPrimeSponsorCode() != null
					&& (prevAward.getPrimeSponsorCode() == null || !award
							.getPrimeSponsorCode().equals(
									prevAward.getPrimeSponsorCode()))) {
				otherHeaderDetails.setPrimeSponsorCode(award
						.getPrimeSponsorCode());
			}
		} else {
			if (award.getCfdaNumber() != null) {
				otherHeaderDetails.setCFDANumber(award.getCfdaNumber());
			}
			if (award.getSubPlanFlag() != null) {
				otherHeaderDetails.setSubPlan(award.getSubPlanFlag());
			}
			if (award.getPrimeSponsorCode() != null) {
				otherHeaderDetails.setPrimeSponsorCode(award
						.getPrimeSponsorCode());
			}
		}

        if (award.getDocumentFundingId() != null) {
            otherHeaderDetails.setDFAFSNumber(award.getDocumentFundingId());
        }
		if (award.getProcurementPriorityCode() != null) {
			otherHeaderDetails.setProcurementPriorityCode(award
					.getProcurementPriorityCode());
		}
		if (award.getPrimeSponsor() != null
				&& award.getPrimeSponsor().getSponsorName() != null) {
			otherHeaderDetails.setPrimeSponsorDescription(award
					.getPrimeSponsor().getSponsorName());
		}

		if (award.getBasisOfPaymentCode() != null) {
			otherHeaderDetails.setBasisPaymentCode(award
					.getBasisOfPaymentCode());
		}
		String basisPaymentDesc = getAwardBasisPaymentDesc();
		if (basisPaymentDesc != null) {
			otherHeaderDetails.setBasisPaymentDesc(basisPaymentDesc);
		}
		if (award.getMethodOfPaymentCode() != null) {
			otherHeaderDetails.setPaymentMethodCode(award
					.getMethodOfPaymentCode());
		}
		String paymentMethodDesc = getAwardPaymentMethodDesc();
		if (paymentMethodDesc != null) {
			otherHeaderDetails.setPaymentMethodDesc(paymentMethodDesc);
		}
		if (award.getActivityTypeCode() != null) {
			otherHeaderDetails.setActivityTypeCode(Integer.parseInt(award
					.getActivityTypeCode()));
			if (prevAward != null && prevAward.getActivityTypeCode() != null) {
				String activityTypeDescription = getActivityTypeDesc(String
						.valueOf(award.getActivityTypeCode()));
				String prevActivityypeDescription = getActivityTypeDesc(String
						.valueOf(prevAward.getActivityTypeCode()));
				String activityTypeDesc = getTypeDescription(
						activityTypeDescription, prevActivityypeDescription);
				if (activityTypeDesc != null) {
					otherHeaderDetails.setActivityTypeDesc(activityTypeDesc);
				}
			} else {
				String activityTypeDesc = getActivityTypeDesc(String
						.valueOf(award.getActivityTypeCode()));
				if (activityTypeDesc != null) {
					otherHeaderDetails.setActivityTypeDesc(activityTypeDesc);
				}
			}
		}
		String invoiceInstructions = getInvoiceInstructionComments();
		if (invoiceInstructions != null) {
			otherHeaderDetails.setInvoiceInstructions(invoiceInstructions);
		}
		String fellowShipname = getFellowshipAdminName();
		if (fellowShipname != null) {
			otherHeaderDetails.setFellowshipAdminName(fellowShipname);
		}

		if (award.getAccountTypeCode() != null) {
			otherHeaderDetails.setAccountTypeCode(String.valueOf(award
					.getAccountTypeCode()));
		}
		if (award.getAccountTypeCode() != null)
			if (prevAward != null && prevAward.getAccountTypeCode() != null) {
				String accountTypeDescription = getAccountTypeDesc(String
						.valueOf(award.getAccountTypeCode()));
				String prevAccountTypeDescription = getAccountTypeDesc(String
						.valueOf(prevAward.getAccountTypeCode()));
				String accountTypeDesc = getTypeDescription(
						accountTypeDescription, prevAccountTypeDescription);
				if (accountTypeDesc != null) {
					otherHeaderDetails.setAccountTypeDesc(accountTypeDesc);
				}
			} else {
				String accountTypeDescription = getAccountTypeDesc(String
						.valueOf(award.getAccountTypeCode()));
				if (accountTypeDescription != null) {
					otherHeaderDetails
							.setAccountTypeDesc(accountTypeDescription);
				}
			}
		AwardHierarchy hierarchy = award.getAwardHierarchyService().loadAwardHierarchy(awardDocument.getAward().getAwardNumber());
        if (hierarchy != null) {
            AwardHierarchy parent = hierarchy.getParent();
            if (parent != null) {
                otherHeaderDetails.setRootAccountNumber(parent.getAward().getAccountNumber());
            } else {
                otherHeaderDetails.setRootAccountNumber(award.getAccountNumber());
            }
        }
		if (award.getUpdateUser() != null) {
			otherHeaderDetails.setUpdateUser(award.getUpdateUser());
		}
		if (award.getUpdateTimestamp() != null) {
			Calendar calendar = dateTimeService.getCalendar(award
					.getUpdateTimestamp());
			otherHeaderDetails.setLastUpdate(calendar);
		}
		return otherHeaderDetails;
	}

	private String getInvoiceInstructionComments() {
	    List<AwardComment> awardComments = award.getAwardComments();
	    for (AwardComment awardComment : awardComments) {
            if(awardComment.getCommentTypeCode().equals(INVOICE_INSTRUCTIONS_COMMENT_TYPE_CODE)){
                return awardComment.getComments();
            }
        }
        return null;
    }

    /*
	 * This method will get the award payment method description
	 */
	private String getAwardPaymentMethodDesc() {
	    award.refreshReferenceObject("awardMethodOfPayment");
		String paymentMethodDesc = null;
		if (prevAward != null) {
			if ((award.getAwardMethodOfPayment() != null && award
					.getAwardMethodOfPayment().getDescription() != null)
					&& (award.getMethodOfPaymentCode() != null && prevAward
							.getMethodOfPaymentCode() != null)) {
				if (award.getMethodOfPaymentCode().equals(
						prevAward.getMethodOfPaymentCode())) {
					paymentMethodDesc = award.getAwardMethodOfPayment()
							.getDescription();
				} else {
					paymentMethodDesc = new StringBuilder(
							START_ASTERISK_SPACE_INDICATOR).append(
							award.getAwardMethodOfPayment().getDescription())
							.toString();
				}
			}
		} else if (award.getAwardMethodOfPayment() != null
				&& award.getAwardMethodOfPayment().getDescription() != null) {
			paymentMethodDesc = award.getAwardMethodOfPayment()
					.getDescription();
		}
		return paymentMethodDesc;
	}

	/*
	 * This method will get the award basis payment description.
	 */
	private String getAwardBasisPaymentDesc() {
	    award.refreshReferenceObject("awardBasisOfPayment");
		String basisPaymentDesc = null;
		if (prevAward != null) {
			if (award.getBasisOfPaymentCode() != null && prevAward
							.getBasisOfPaymentCode() != null) {
				if (award.getBasisOfPaymentCode().equals(
						prevAward.getBasisOfPaymentCode())) {
					basisPaymentDesc = award.getAwardBasisOfPayment().getDescription();
				} else {
					basisPaymentDesc = new StringBuilder(
							START_ASTERISK_SPACE_INDICATOR).append(award.getAwardBasisOfPayment().getDescription())
							.toString();
				}
			}
		} else if (award.getAwardBasisOfPayment() != null
				&& award.getAwardBasisOfPayment().getDescription() != null) {
			basisPaymentDesc = award.getAwardBasisOfPayment().getDescription();
		}
		return basisPaymentDesc;
	}

	/*
	 * This method will get the fellowship administrator name based on the
	 * Activity Type Code . If Activity Type Code is not empty or not equal to 3
	 * or 7 then construct the fellowship administrator name from administrator
	 * name of award
	 * 
	 */
	private String getFellowshipAdminName() {
		String fellowshipname;
		if (award.getActivityTypeCode() != null
				&& award.getActivityTypeCode().equals("3")
				&& award.getActivityTypeCode().equals("7")) {
			fellowshipname = getAdminName();
		} else {
			fellowshipname = getAwardParameterValue(FELLOWSHIP_ADMIN_NAME);
		}
		return fellowshipname;
	}

	/*
	 * This method will get the administrator name of award
	 */
	private String getAdminName() {
		String adminName = award.getOspAdministratorName();
		if (adminName != null) {
			String lastName = null;
			String firstName = null;
			int pos;
			pos = adminName.indexOf(STRING_SEPARATER);
			if (!(pos > 0)) {
				pos = adminName.indexOf(EMPTY_SPACE);
			}
			if (pos >= 0) {
				firstName = adminName.substring(0, pos).trim();
				lastName = adminName.substring(pos + 1).trim();
			}
			if (lastName != null && firstName != null) {
				adminName = new StringBuilder(firstName).append(EMPTY_SPACE)
						.append(lastName).toString();
			} else if (lastName != null) {
				adminName = firstName;
			} else if (firstName != null) {
				adminName = lastName;
			}
		}
		return adminName;
	}

	/*
	 * This method will get the description based on if previous award type
	 * description is equal to null or not equal to award award type description
	 * Otherwise returns * (Asterisk)
	 */
	private String getTypeDescription(String awardTypeDescription,
			String prevAwardTypeDescription) {
		String awardTypeDesc = null;
		if (awardTypeDescription != null) {
			if (prevAwardTypeDescription == null
					|| !awardTypeDescription.equals(prevAwardTypeDescription)) {
				awardTypeDesc = new StringBuilder(awardTypeDescription).append(
						END_ASTERISK_SPACE_INDICATOR).toString();

			} else {
				awardTypeDesc = awardTypeDescription;
			}
		} else if (prevAwardTypeDescription != null) {

			awardTypeDesc = START_ASTERISK_SPACE_INDICATOR;
		}
		return awardTypeDesc;
	}

	/*
	 * This method will get the award type description for given award type code
	 */
	private String getAwardTypeDesc(Integer awardTypeCode) {
		String description = null;
		AwardType awardType = dataObjectService.find(AwardType.class, awardTypeCode);
		if (awardType != null) {
			description = awardType.getDescription();
		}
		return description;
	}

	/*
	 * This method will get the activity type description for given activity
	 * type code
	 */
	private String getActivityTypeDesc(String activityTypeCode) {
		String description = null;
		Map<String, String> activityTypeCodeMap = new HashMap<>();
		activityTypeCodeMap.put(ACTIVITY_TYPE_CODE_PARAMETER, activityTypeCode);
		List<ActivityType> activityTypes = (List<ActivityType>) businessObjectService
				.findMatching(ActivityType.class, activityTypeCodeMap);
		if (activityTypes != null && !activityTypes.isEmpty()) {
			description = activityTypes.get(0).getDescription();
		}
		return description;
	}

	/*
	 * This method will get the account type description for given account type
	 * code
	 */
	private String getAccountTypeDesc(String accountTypeCode) {
		String description = null;
		Map<String, String> accountTypeCodeMap = new HashMap<>();
		accountTypeCodeMap.put(ACCOUNT_TYPE_CODE_PARAMETER, accountTypeCode);
		List<AccountType> accountTypes = (List<AccountType>) businessObjectService
				.findMatching(AccountType.class, accountTypeCodeMap);
		if (accountTypes != null && !accountTypes.isEmpty()) {
			description = accountTypes.get(0).getDescription();
		}
		return description;
	}

	/*
	 * This method wil get the comment type description for given comment type
	 * code
	 */
	private String getCommentTypeDesc(String commentTypeCode) {
		String description = null;
		Map<String, String> commentTypeCodeMap = new HashMap<>();
		commentTypeCodeMap.put(COMMENT_TYPE_CODE_PARAMETER, commentTypeCode);
		List<org.kuali.kra.bo.CommentType> commentTypes = (List<org.kuali.kra.bo.CommentType>) businessObjectService
				.findMatching(org.kuali.kra.bo.CommentType.class,
						commentTypeCodeMap);
		if (commentTypes != null && !commentTypes.isEmpty()) {
			description = commentTypes.get(0).getDescription();
		}
		return description;
	}

	/*
	 * This method will get the previous award effective date modified
	 */
	private String getPreAwardEffectiveDateModified() {
		String preAwardEffectiveDateModified = null;
		if (award.getPreAwardEffectiveDate() != null) {
			if (prevAward.getPreAwardEffectiveDate() == null
					|| !(prevAward.getPreAwardEffectiveDate().equals(award
							.getPreAwardEffectiveDate()))) {
				preAwardEffectiveDateModified = END_ASTERISK_SPACE_INDICATOR;
			}
		} else if (prevAward.getPreAwardEffectiveDate() != null) {
			preAwardEffectiveDateModified = END_ASTERISK_SPACE_INDICATOR;
		}
		return preAwardEffectiveDateModified;
	}

	/*
	 * This method will get the previous award authorized amount modified
	 */
	private String getPreAwardAuthorizedAmountModified() {
		String preAwardAuthorizedAmtModified = null;
		double prevPreAwardAuthorizedAmt = prevAward
				.getPreAwardAuthorizedAmount() == null ? 0 : prevAward
				.getPreAwardAuthorizedAmount().doubleValue();
		if (award.getPreAwardAuthorizedAmount() != null
				&& prevPreAwardAuthorizedAmt != award
						.getPreAwardAuthorizedAmount().doubleValue()) {
			preAwardAuthorizedAmtModified = END_ASTERISK_SPACE_INDICATOR;
		}
		return preAwardAuthorizedAmtModified;
	}

	/*
	 * This method will get the submit date of payment schedule
	 */
	private Calendar getSubmitDate(AwardPaymentSchedule awardPaymentSchedule) {
		Calendar submitDate = null;
		if (awardPaymentSchedule.getSubmitDate() != null) {
			submitDate = dateTimeService.getCalendar(awardPaymentSchedule
					.getSubmitDate());
		}
		return submitDate;
	}

	/*
	 * This method will get the due date of payment schedule
	 */
	private Calendar getDueDate(AwardPaymentSchedule awardPaymentSchedule) {
		Calendar paymentScheduleDueDate = null;
		if (awardPaymentSchedule.getDueDate() != null) {
			paymentScheduleDueDate = dateTimeService
					.getCalendar(awardPaymentSchedule.getDueDate());
		}
		return paymentScheduleDueDate;
	}

	protected String getAwardParameterValue(String param) {
		String value = null;
		try {
			value = parameterService.getParameterValueAsString(AwardDocument.class,param);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
		}
		return value;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}


	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(
			BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	public DataObjectService getDataObjectService() {
		return dataObjectService;
	}

	public void setDataObjectService(DataObjectService dataObjectService) {
		this.dataObjectService = dataObjectService;
	}

	public DateTimeService getDateTimeService() {
		return dateTimeService;
	}

	public void setDateTimeService(DateTimeService dateTimeService) {
		this.dateTimeService = dateTimeService;
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public CostShareService getCostShareService() {
		return costShareService;
	}

	public void setCostShareService(CostShareService costShareService) {
		this.costShareService = costShareService;
	}
}
