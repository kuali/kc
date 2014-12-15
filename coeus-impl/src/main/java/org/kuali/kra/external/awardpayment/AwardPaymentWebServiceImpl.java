package org.kuali.kra.external.awardpayment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebParam;

import org.kuali.kra.award.home.AwardBasisOfPayment;
import org.kuali.kra.award.home.AwardMethodOfPayment;
import org.kuali.kra.award.home.ValidBasisMethodPayment;
import org.kuali.kra.external.HashMapElement;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.kra.award.service.AwardPaymentAndInvoicesService;
import org.kuali.rice.krad.service.BusinessObjectService;

public class AwardPaymentWebServiceImpl implements AwardPaymentWebService {

	private BusinessObjectService businessObjectService;
	private AwardPaymentAndInvoicesService awardPaymentAndInvoicesService;
	private KcDtoService<AwardBasisOfPaymentDTO, AwardBasisOfPayment> awardBasisOfPaymentDtoService;
	private KcDtoService<AwardMethodOfPaymentDTO, AwardMethodOfPayment> awardMethodOfPaymentDtoService;
	
	@Override
	public AwardBasisOfPaymentDTO getBasisOfPayment(
			@WebParam(name = "basisOfPaymentCode") String basisOfPaymentCode) {
		return awardBasisOfPaymentDtoService.buildDto(getBusinessObjectService().findBySinglePrimaryKey(AwardBasisOfPayment.class, basisOfPaymentCode));
	}

	@Override
	public AwardMethodOfPaymentDTO getMethodOfPayment(
			@WebParam(name = "methodOfPaymentCode") String methodOfPaymentCode) {
		return awardMethodOfPaymentDtoService.buildDto(getBusinessObjectService().findBySinglePrimaryKey(AwardMethodOfPayment.class, methodOfPaymentCode));
	}
	
	@Override
	public List<AwardBasisOfPaymentDTO> getMatchingBasisOfPayments(
			@WebParam(name = "searchCriteria") AwardBasisOfPaymentDTO searchCriteria) {
		Map<String, String> values = new HashMap<String, String>();
		if (searchCriteria.getBasisOfPaymentCode() != null) {
			values.put("basisOfPaymentCode", searchCriteria.getBasisOfPaymentCode());
		}
		if (searchCriteria.getDescription() != null) {
			values.put("description", searchCriteria.getDescription());
		}
		Collection<AwardBasisOfPayment> basisList = null;
		if (values.isEmpty()) {
			basisList = getBusinessObjectService().findAll(AwardBasisOfPayment.class);
		} else {
			basisList = getBusinessObjectService().findMatching(AwardBasisOfPayment.class, values);
		}
		return getBasisOfPaymentDTOs(basisList);
	}

	@Override
	public List<AwardMethodOfPaymentDTO> getMatchingMethodOfPayments(
			@WebParam(name = "searchCriteria") AwardMethodOfPaymentDTO searchCriteria) {
		Map<String, String> values = new HashMap<String, String>();
		if (searchCriteria.getMethodOfPaymentCode() != null) {
			values.put("basisOfPaymentCode", searchCriteria.getMethodOfPaymentCode());
		}
		if (searchCriteria.getDescription() != null) {
			values.put("description", searchCriteria.getDescription());
		}
		Collection<AwardMethodOfPayment> basisList = null;
		if (values.isEmpty()) {
			basisList = getBusinessObjectService().findAll(AwardMethodOfPayment.class);
		} else {
			basisList = getBusinessObjectService().findMatching(AwardMethodOfPayment.class, values);
		}
		return getMethodOfPaymentDTOs(basisList);
	}

	@Override
	public List<AwardMethodOfPaymentDTO> getMatchingMethodOfPaymentsForBasisOfPayment(
			@WebParam(name = "basisOfPaymentCode") String basisOfPaymentCode) {
		List<ValidBasisMethodPayment> validPayments = getAwardPaymentAndInvoicesService().getValidBasisMethodPaymentByBasisCode(basisOfPaymentCode);
		List<AwardMethodOfPaymentDTO> result = new ArrayList<AwardMethodOfPaymentDTO>();
		if (validPayments != null) {
			for (ValidBasisMethodPayment validPayment : validPayments) {
				result.add(awardMethodOfPaymentDtoService.buildDto(validPayment.getMethodOfPayment()));
			}
		}
		return result;
	}	
	
	protected List<AwardBasisOfPaymentDTO> getBasisOfPaymentDTOs(Collection<AwardBasisOfPayment> basisList) {
		List<AwardBasisOfPaymentDTO> result = new ArrayList<AwardBasisOfPaymentDTO>();
		if (basisList != null) {
			for (AwardBasisOfPayment basis : basisList) {
				result.add(awardBasisOfPaymentDtoService.buildDto(basis));
			}
		}
		return result;
	}
	
	protected List<AwardMethodOfPaymentDTO> getMethodOfPaymentDTOs(Collection<AwardMethodOfPayment> basisList) {
		List<AwardMethodOfPaymentDTO> result = new ArrayList<AwardMethodOfPaymentDTO>();
		if (basisList != null) {
			for (AwardMethodOfPayment basis : basisList) {
				result.add(awardMethodOfPaymentDtoService.buildDto(basis));
			}
		}
		return result;
	}

	protected BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	protected AwardPaymentAndInvoicesService getAwardPaymentAndInvoicesService() {
		return awardPaymentAndInvoicesService;
	}

	public void setAwardPaymentAndInvoicesService(
			AwardPaymentAndInvoicesService awardPaymentAndInvoicesService) {
		this.awardPaymentAndInvoicesService = awardPaymentAndInvoicesService;
	}

	public KcDtoService<AwardBasisOfPaymentDTO, AwardBasisOfPayment> getAwardBasisOfPaymentDtoService() {
		return awardBasisOfPaymentDtoService;
	}

	public void setAwardBasisOfPaymentDtoService(
			KcDtoService<AwardBasisOfPaymentDTO, AwardBasisOfPayment> awardBasisOfPaymentDtoService) {
		this.awardBasisOfPaymentDtoService = awardBasisOfPaymentDtoService;
	}

	public KcDtoService<AwardMethodOfPaymentDTO, AwardMethodOfPayment> getAwardMethodOfPaymentDtoService() {
		return awardMethodOfPaymentDtoService;
	}

	public void setAwardMethodOfPaymentDtoService(
			KcDtoService<AwardMethodOfPaymentDTO, AwardMethodOfPayment> awardMethodOfPaymentDtoService) {
		this.awardMethodOfPaymentDtoService = awardMethodOfPaymentDtoService;
	}
}
