package org.kuali.kra.award.external.award;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.cgb.AwardCgb;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardConstants;
import org.kuali.kra.award.home.AwardService;
import org.kuali.kra.award.paymentreports.awardreports.AwardReportTerm;
import org.kuali.coeus.common.framework.version.VersionStatus;
import org.kuali.kra.award.dao.AwardLookupDao;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class AwardWebServiceImpl implements AwardWebService {
	
	private BusinessObjectService businessObjectService;
	private AwardService awardService;
	private AwardLookupDao awardLookupDao;
	private KcDtoService<AwardDTO, Award> awardDtoService;
	private ParameterService parameterService;
	
	public AwardDTO getAward(Long awardId) {
		String awardNumber = getAwardService().getAwardNumber(awardId);
		if (StringUtils.isNotBlank(awardNumber)) {
			Award newestAward = getAwardService().getActiveOrNewestAward(awardNumber);
			AwardDTO result = getAwardDtoService().buildDto(newestAward);
			return result;
		} else {
			return null;
		}
	}
	
	public List<AwardDTO> searchAwards(AwardSearchCriteriaDto searchDto) {
		List<AwardDTO> results = new ArrayList<AwardDTO>();
		Map<String, String> values = new HashMap<String, String>();
		values.put("awardId", searchDto.getAwardId());
		values.put("financialChartOfAccountsCode", searchDto.getChartOfAccounts());
		values.put("accountNumber", searchDto.getAccountNumber());
		values.put("awardNumber", searchDto.getAwardNumber());
		values.put("projectPersons.personId", searchDto.getPrincipalInvestigatorId());
		values.put("sponsorCode", searchDto.getSponsorCode());
		values.put("beginDate",	searchDto.getStartDate());
		values.put("rangeLowerBoundKeyPrefix_beginDate", searchDto.getStartDateLowerBound());
		values.put("awardAmountInfos.finalExpirationDate", searchDto.getEndDate());
		values.put("rangeLowerBoundKeyPrefix_awardAmountInfos.finalExpirationDate", searchDto.getEndDateLowerBound());
		values.put("awardAmountInfos.amountObligatedToDate", searchDto.getAwardTotal());
		
		String invoiceReportDesc =  getParameterService().getParameterValueAsString(Award.class, AwardConstants.INVOICE_REPORT_DESC_PARAM);
		values.put("awardReportTermItems.report.description", invoiceReportDesc);
		values.put("awardReportTermItems.frequencyCode", searchDto.getBillingFrequency());
		
		List<Award> awards = (List<Award>) getAwardLookupDao().getAwardSearchResults(values, false);
		if (awards != null && !awards.isEmpty()) {
			for (Award award : awards) {
				results.add(getAwardDtoService().buildDto(award));
			}
		}
		return results;
		
	}

	public List<AwardDTO> getMatchingAwards(AwardFieldValuesDto fieldValuesDto) {
		List<AwardDTO> results = new ArrayList<AwardDTO>();
		Map<String, Object> fieldValues = new HashMap<String, Object>();
		if (fieldValuesDto.getAwardId() != null) {
			fieldValues.put("awardId", fieldValuesDto.getAwardId());
		}
		if (fieldValuesDto.getChartOfAccounts() != null) {
			fieldValues.put("financialChartOfAccountsCode", fieldValuesDto.getChartOfAccounts());
		}
		if (fieldValuesDto.getAccountNumber() != null) {
			fieldValues.put("accountNumber", fieldValuesDto.getAccountNumber());
		}
		if (fieldValuesDto.getPrincipalInvestigatorId() != null) {
			fieldValues.put("projectPersons.personId", fieldValuesDto.getPrincipalInvestigatorId());
		}
		if (fieldValuesDto.getAwardNumber() != null) {
			fieldValues.put("awardNumber", fieldValuesDto.getAwardNumber());
		}
        // use the awardSequenceStatus to return the latest active award
		fieldValues.put("awardSequenceStatus", VersionStatus.ACTIVE.name());
		Collection<Award> awards = getAwardService().retrieveAwardsByCriteria(fieldValues);

		if (awards != null && !awards.isEmpty()) {
			for (Award award : awards) {
				results.add(getAwardDtoService().buildDto(award));
			}
		}
		return results;
	}

	protected AwardService getAwardService() {
		return awardService;
	}

	@Autowired
	@Qualifier("awardService")
	public void setAwardService(AwardService awardService) {
		this.awardService = awardService;
	}

	public AwardLookupDao getAwardLookupDao() {
		return awardLookupDao;
	}

	@Autowired
	@Qualifier("awardLookupDao")
	public void setAwardLookupDao(AwardLookupDao awardLookupDao) {
		this.awardLookupDao = awardLookupDao;
	}

	public KcDtoService<AwardDTO, Award> getAwardDtoService() {
		return awardDtoService;
	}

	public void setAwardDtoService(KcDtoService<AwardDTO, Award> awardDtoService) {
		this.awardDtoService = awardDtoService;
	}

	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	@Override
	public AwardBillingUpdateStatusDto updateAwardBillingStatus(AwardFieldValuesDto searchDto,
			AwardBillingUpdateDto updateDto) {
		AwardBillingUpdateStatusDto result = new AwardBillingUpdateStatusDto();
		AwardCgb award = null;
		if (StringUtils.isNotEmpty(searchDto.getChartOfAccounts()) && StringUtils.isNotEmpty(searchDto.getAccountNumber())) {
			Map<String, Object> values = new HashMap<String, Object>();
			values.put("award.financialChartOfAccountsCode", searchDto.getChartOfAccounts());
			values.put("award.accountNumber", searchDto.getAccountNumber());
            // use the awardSequenceStatus to return the latest active award
            values.put("awardSequenceStatus", VersionStatus.ACTIVE.name());
            List<AwardCgb> cgbAwards = new ArrayList<AwardCgb>(businessObjectService.findMatching(AwardCgb.class, values));
            if (!cgbAwards.isEmpty()) {
            	award = cgbAwards.get(0);
            }
		}
		if (award == null) {
			result.setSuccess(false);
			result.getErrorMessages().add("Unable to find an award for update based on unique identifiers.");
			return result;
		}
		
		if (updateDto.isDoFinalBilledUpdate()) {
			award.setFinalBill(updateDto.isFinalBilledIndicator());
		}
		if (updateDto.isDoLastBillDateUpdate()) {
			award.setPreviousLastBilledDate(award.getLastBilledDate());
			award.setLastBilledDate(updateDto.getLastBillDate());
		}
		if (updateDto.isDoAmountToDrawUpdate()) {
			award.setAmountToDraw(new ScaleTwoDecimal(updateDto.getAmountToDraw()));
		}
		if (updateDto.isDoInvoiceDocStatusUpdate()) {
			award.setInvoiceDocumentStatus(updateDto.getInvoiceDocumentStatus());
		}
		if (updateDto.isDoLocCreationTypeUpdate()) {
			
		}
		if (updateDto.isDoLocReviewUpdate()) {
			award.setLetterOfCreditReviewIndicator(updateDto.isLocReviewIndicator());
		}
		if (updateDto.isRestorePreviousBillDate()) {
			award.setLastBilledDate(award.getPreviousLastBilledDate());
			award.setPreviousLastBilledDate(null);
		}
		
		getBusinessObjectService().save(award);
		result.setAwardNumber(award.getAwardNumber());
		result.setSuccess(true);
		return result;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}

}
