package org.kuali.kra.award.external.award;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.external.service.KcDtoServiceBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.institutionalproposal.service.InstitutionalProposalService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

public class AwardAccountDtoService extends KcDtoServiceBase<AwardAccountDTO, Award> {

	private BusinessObjectService businessObjectService;
	private ParameterService parameterService;
    private InstitutionalProposalService institutionalProposalService;
	@Override
	public AwardAccountDTO buildDto(Award award) {
        AwardAccountDTO awardAccountDTO = new AwardAccountDTO();
        awardAccountDTO.setProposalFederalPassThroughAgencyNumber(award.getSponsorCode());
        //sponsor award id same as sponsor award number
        awardAccountDTO.setGrantNumber(award.getSponsorAwardNumber());
        // how to get IP id from award
        awardAccountDTO.setInstitutionalproposalId(getProposalId(award));
        awardAccountDTO.setAwardId(award.getAwardId());
        if (ObjectUtils.isNotNull(award.getPrincipalInvestigator())) {
            awardAccountDTO.setProjectDirector(award.getPrincipalInvestigator().getPersonId());
        } else {
            awardAccountDTO.setProjectDirector(null);
        }
        
        // send the award number which is the proposal number on the KFS side
        awardAccountDTO.setProposalNumber(award.getAwardNumber());
        awardAccountDTO.setSponsorCode(award.getSponsorCode());
        awardAccountDTO.setSponsorName(award.getSponsorName());
        awardAccountDTO.setFederalSponsor(isFederalSponsor(award));
        awardAccountDTO.setAwardTitle(award.getTitle());
        awardAccountDTO.setPrimeSponsorCode(award.getPrimeSponsorCode());
        
        if (ObjectUtils.isNotNull(award.getPrimeSponsor())) {
            awardAccountDTO.setPrimeSponsorName(award.getPrimeSponsor().getSponsorName());
            awardAccountDTO.setPrimeSponsorTypeCode(award.getPrimeSponsor().getSponsorTypeCode());
        } else {
            awardAccountDTO.setPrimeSponsorTypeCode(null);
            awardAccountDTO.setPrimeSponsorName(null);
        }
        
        if(ObjectUtils.isNotNull(award.getSponsor())) {
            awardAccountDTO.setSponsorTypeCode(award.getSponsor().getSponsorTypeCode());
        } else {
            awardAccountDTO.setSponsorTypeCode(null);
        }
        awardAccountDTO.setAccountNumber(award.getAccountNumber());
        awardAccountDTO.setChartOfAcccountsCode(award.getFinancialChartOfAccountsCode());
        awardAccountDTO.setFinalBill(award.getAwardCgb().isFinalBill());
        awardAccountDTO.setLastBilledDate(award.getAwardCgb().getLastBilledDate());
        awardAccountDTO.setPreviousLastBilledDate(award.getAwardCgb().getPreviousLastBilledDate());
        awardAccountDTO.setAmountToDraw(award.getAwardCgb().getAmountToDraw().bigDecimalValue());
        awardAccountDTO.setLetterOfCreditReviewIndicator(award.getAwardCgb().isLetterOfCreditReviewIndicator());
        awardAccountDTO.setInvoiceDocumentStatus(award.getAwardCgb().getInvoiceDocumentStatus());
        
        return awardAccountDTO;
	}
	
    /**
     * This method returns the proposal ID related to an award
     * Can award have multiple P IDs?
     * @param award
     * @return the id of the found proposal
     */
    protected Long getProposalId(Award award) {
        return getInstitutionalProposalService().getProposalId(award);
    }
    
    /**
     * * Method checks if the award has a federal sponsor.
     * If the award sponsor type code or the prime sponsor type is federal, then
     * the document should be routed.
     * @see org.kuali.kra.external.award.AwardAccountService#isFederalSponsor(String)
     */
    protected boolean isFederalSponsor(Award award) {
       
        String federalSponsorTypeCode = parameterService.getParameterValueAsString(AwardDocument.class, Constants.FEDERAL_SPONSOR_TYPE_CODE);
        //If the sponsor type or prime sponsor type is federal, then document should be routed, return true.
        return isFederal(award.getSponsor(), federalSponsorTypeCode) || isFederal(award.getPrimeSponsor(), federalSponsorTypeCode);
    }

    /**
     * This method checks if sponsor is federal.
     * @param sponsor
     * @param federalSponsorTypeCode
     * @return
     */
    protected boolean isFederal(Sponsor sponsor, String federalSponsorTypeCode) {
        if (ObjectUtils.isNotNull(sponsor) && ObjectUtils.isNotNull(sponsor.getSponsorTypeCode())) {
            if (sponsor.getSponsorTypeCode().equals(federalSponsorTypeCode)) {
                return true;
            }
        }
        return false;
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

    public InstitutionalProposalService getInstitutionalProposalService() {
        return institutionalProposalService;
    }

    public void setInstitutionalProposalService(InstitutionalProposalService institutionalProposalService) {
        this.institutionalProposalService = institutionalProposalService;
    }
}
