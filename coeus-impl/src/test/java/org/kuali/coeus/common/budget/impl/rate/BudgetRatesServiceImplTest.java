package org.kuali.coeus.common.budget.impl.rate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.kuali.coeus.common.budget.framework.calculator.ValidCalcType;


public class BudgetRatesServiceImplTest {

	public ValidCalcType buildValidCalcType(String rateClassCode, String rateTypeCode) {
		ValidCalcType calc = new ValidCalcType();
		calc.setRateClassCode(rateClassCode);
		calc.setRateTypeCode(rateTypeCode);
		return calc;
	}
	
	@Test
	public void testIsVacationOnLabAllocation_noValidCalc() {
		BudgetRatesServiceImpl service = mock(BudgetRatesServiceImpl.class);
		when(service.getDependentValidRateClassTypeForLA(anyString())).thenReturn(null);
		when(service.isVacationOnLabAllocation(anyString(), anyString())).thenCallRealMethod();
		assertFalse(service.isVacationOnLabAllocation("1", "2"));
	}
	
	@Test
	public void testIsVacationOnLabAllocation_validCalc() {
		BudgetRatesServiceImpl service = mock(BudgetRatesServiceImpl.class);
		when(service.getDependentValidRateClassTypeForLA(anyString())).thenReturn(buildValidCalcType("1", "2"));
		when(service.isVacationOnLabAllocation(anyString(), anyString())).thenCallRealMethod();
		assertTrue(service.isVacationOnLabAllocation("1", "2"));
	}	

	@Test
	public void testIsVacationOnLabAllocation_notVacationLa() {
		BudgetRatesServiceImpl service = mock(BudgetRatesServiceImpl.class);
		when(service.getDependentValidRateClassTypeForLA(anyString())).thenReturn(buildValidCalcType("1", "2"));
		when(service.isEmployeeBenefitOnLabAllocation(anyString(), anyString())).thenCallRealMethod();
		assertFalse(service.isEmployeeBenefitOnLabAllocation("1", "3"));
	}
	
	@Test
	public void testisEmployeeBenefitOnLabAllocation_noValidCalc() {
		BudgetRatesServiceImpl service = mock(BudgetRatesServiceImpl.class);
		when(service.getDependentValidRateClassTypeForLA(anyString())).thenReturn(null);
		when(service.isEmployeeBenefitOnLabAllocation(anyString(), anyString())).thenCallRealMethod();
		assertFalse(service.isEmployeeBenefitOnLabAllocation("1", "2"));
	}
	
	@Test
	public void testisEmployeeBenefitOnLabAllocation_validCalc() {
		BudgetRatesServiceImpl service = mock(BudgetRatesServiceImpl.class);
		when(service.getDependentValidRateClassTypeForLA(anyString())).thenReturn(buildValidCalcType("1", "2"));
		when(service.isEmployeeBenefitOnLabAllocation(anyString(), anyString())).thenCallRealMethod();
		assertTrue(service.isEmployeeBenefitOnLabAllocation("1", "2"));
	}	

	@Test
	public void testisEmployeeBenefitOnLabAllocation_notVacationLa() {
		BudgetRatesServiceImpl service = mock(BudgetRatesServiceImpl.class);
		when(service.getDependentValidRateClassTypeForLA(anyString())).thenReturn(buildValidCalcType("1", "2"));
		when(service.isEmployeeBenefitOnLabAllocation(anyString(), anyString())).thenCallRealMethod();
		assertFalse(service.isEmployeeBenefitOnLabAllocation("1", "3"));
	}
}
