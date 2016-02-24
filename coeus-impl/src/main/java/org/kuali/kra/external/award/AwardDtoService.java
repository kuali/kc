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
package org.kuali.kra.external.award;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.cgb.AwardCgbConstants;
import org.kuali.kra.award.contacts.AwardUnitContact;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardConstants;
import org.kuali.kra.award.home.AwardMethodOfPayment;
import org.kuali.kra.award.paymentreports.Frequency;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.external.awardpayment.AwardMethodOfPaymentDTO;
import org.kuali.kra.external.frequency.FrequencyDto;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.kra.external.service.KcDtoServiceBase;
import org.kuali.kra.external.sponsor.SponsorDTO;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AwardDtoService extends KcDtoServiceBase<AwardDTO, Award> {

	public static final String FUND_MANAGER_TYPE_CODE_PARAM = "FIN_SYS_UNIT_ADMIN_TYPE_FUND_MANAGER";
	
	private BusinessObjectService businessObjectService;
	private ParameterService parameterService;
	private KcDtoService<ProposalDTO, InstitutionalProposal> proposalDtoService;
	private KcDtoService<SponsorDTO, Sponsor> sponsorDtoService;
	private KcDtoService<AwardMethodOfPaymentDTO, AwardMethodOfPayment> awardMethodOfPaymentDtoService;
	private KcDtoService<FrequencyDto, Frequency> frequencyDtoService;
	
	@Override
	public AwardDTO buildDto(Award award) {
		if (award != null) {
			AwardDTO dto = new AwardDTO();
			dto.setAwardId(award.getAwardId());
			dto.setAwardNumber(award.getAwardNumber());
			dto.setAwardStartDate(award.getBeginDate());
			dto.setAwardEndDate(award.getLastAwardAmountInfo().getFinalExpirationDate());
			dto.setAwardTotalAmount(award.getLastAwardAmountInfo().getObliDistributableAmount().bigDecimalValue());
			dto.setAwardDocumentNumber(award.getDocumentKey());
			dto.setAwardLastUpdateDate(award.getUpdateTimestamp());
			dto.setAwardDirectCostAmount(award.getLastAwardAmountInfo().getObligatedTotalDirect().bigDecimalValue());
			dto.setAwardIndirectCostAmount(award.getLastAwardAmountInfo().getObligatedTotalIndirect().bigDecimalValue());
			dto.setProposalAwardTypeCode(award.getAwardTypeCode().toString());
			dto.setAwardStatusCode(award.getStatusCode().toString());
			dto.setSponsorCode(award.getSponsorCode());
			dto.setTitle(award.getTitle());
			dto.setUnitNumber(award.getLeadUnitNumber());
			dto.setAwardCommentText(award.getAwardGeneralComments().getComments());
			dto.setPrincipalInvestigatorId(award.getPrincipalInvestigator().getPersonId());
			dto.setAdditionalFormsRequired(award.getAwardCgb().isAdditionalFormsRequired());
			dto.setAutoApproveInvoice(award.getAwardCgb().isAutoApproveInvoice());
			dto.setStopWork(award.getAwardCgb().isStopWork());
			dto.setInvoicingOption(award.getAwardCgb().getInvoicingOption());
			dto.setInvoicingOptionDescription(AwardCgbConstants.InvoicingOptions.Types.get(award.getAwardCgb().getInvoicingOption()));
			dto.setDunningCampaignId(award.getAwardCgb().getDunningCampaignId());
			dto.setAdditionalFormsDescription(award.getAdditionalFormsDescriptionComment().getComments());
			dto.setStopWorkReason(award.getStopWorkReasonComment().getComments());
			dto.setMinInvoiceAmount(award.getAwardCgb().getMinInvoiceAmount() != null ? award.getAwardCgb().getMinInvoiceAmount().bigDecimalValue() : null);
			dto.setExcludedFromInvoicing(award.getAwardCgb().isSuspendInvoicing());
			dto.setExcludedFromInvoicingReason(award.getSuspendInvoicingComment().getComments());
			dto.setMethodOfPayment(awardMethodOfPaymentDtoService.buildDto(award.getAwardMethodOfPayment()));
						
			if (award.getFundingProposals() != null && !award.getFundingProposals().isEmpty()) {
				InstitutionalProposal instProp = getBusinessObjectService().findBySinglePrimaryKey(InstitutionalProposal.class, award.getFundingProposals().get(0).getProposalId());
				dto.setProposal(proposalDtoService.buildDto(instProp));
			}
			if (award.getSponsor() != null) {
				dto.setSponsor(sponsorDtoService.buildDto(award.getSponsor()));
			} else {
				//this shouldn't ever happen and so we will simply avoid returning a dto in this case
				return null;
			}
			
			String fundManagerTypeCode = getParameterService().getParameterValueAsString(Award.class, FUND_MANAGER_TYPE_CODE_PARAM);
			for (AwardUnitContact contact : award.getAwardUnitContacts()) {
				if (StringUtils.equals(contact.getUnitAdministratorTypeCode(), fundManagerTypeCode)) {
					dto.setFundManagerId(contact.getPersonId());
				}
			}
			String invoiceReportDesc =  getParameterService().getParameterValueAsString(Award.class, AwardConstants.INVOICE_REPORT_DESC_PARAM);
			for (AwardReportTerm reportItem : award.getAwardReportTermItems()) {
				if (StringUtils.equals(invoiceReportDesc, reportItem.getReport().getDescription())) {
					dto.setInvoiceBillingFrequency(frequencyDtoService.buildDto(reportItem.getFrequency()));
				}
			}
			
			return dto;
		} else {
			return null;
		}
	}

	protected BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	protected ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

	protected KcDtoService<ProposalDTO, InstitutionalProposal> getProposalDtoService() {
		return proposalDtoService;
	}

	public void setProposalDtoService(
			KcDtoService<ProposalDTO, InstitutionalProposal> proposalDtoService) {
		this.proposalDtoService = proposalDtoService;
	}

	protected KcDtoService<SponsorDTO, Sponsor> getSponsorDtoService() {
		return sponsorDtoService;
	}

	public void setSponsorDtoService(
			KcDtoService<SponsorDTO, Sponsor> sponsorDtoService) {
		this.sponsorDtoService = sponsorDtoService;
	}

	protected KcDtoService<AwardMethodOfPaymentDTO, AwardMethodOfPayment> getAwardMethodOfPaymentDtoService() {
		return awardMethodOfPaymentDtoService;
	}

	public void setAwardMethodOfPaymentDtoService(
			KcDtoService<AwardMethodOfPaymentDTO, AwardMethodOfPayment> awardMethodOfPaymentDtoService) {
		this.awardMethodOfPaymentDtoService = awardMethodOfPaymentDtoService;
	}

	public KcDtoService<FrequencyDto, Frequency> getFrequencyDtoService() {
		return frequencyDtoService;
	}

	public void setFrequencyDtoService(
			KcDtoService<FrequencyDto, Frequency> frequencyDtoService) {
		this.frequencyDtoService = frequencyDtoService;
	}

}
