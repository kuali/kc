/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.common.api.budget.rates;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.kuali.coeus.common.budget.framework.rate.InstituteRate;
import org.kuali.coeus.common.framework.fiscalyear.FiscalYearMonthService;
import org.kuali.coeus.sys.framework.controller.rest.RestController;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLogger;
import org.kuali.coeus.sys.framework.controller.rest.audit.RestAuditLoggerFactory;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.persistence.PersistenceVerificationService;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.UnauthorizedAccessException;
import org.kuali.coeus.sys.framework.rest.UnprocessableEntityException;
import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.WrapDynaBean;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.codiform.moo.Moo;
import com.codiform.moo.configuration.Configuration;
import com.codiform.moo.curry.Translate;

@Controller("budgetRatesRestController")
public class BudgetRatesRestController extends RestController {
	
	private static final Log LOG = LogFactory.getLog(BudgetRatesRestController.class);

	@Autowired
	@Qualifier("businessObjectService")
	private BusinessObjectService businessObjectService;
	
	@Autowired
	@Qualifier("fiscalYearMonthService")
	private FiscalYearMonthService fiscalYearMonthService;
	
	@Autowired
	@Qualifier("permissionService")
	private PermissionService permissionService;
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	@Autowired
	@Qualifier("restAuditLoggerFactory")
	private RestAuditLoggerFactory restAuditLoggerFactory;
	
	private List<String> dtoProperties;
	
	public BudgetRatesRestController() throws IntrospectionException {
		dtoProperties = getDtoProperties();
	}

	protected List<String> getDtoProperties() throws IntrospectionException {
		List<String> ignoredProperties = Stream.of("class").collect(Collectors.toList());
		return Arrays.asList(Introspector.getBeanInfo(InstituteRateDto.class).getPropertyDescriptors()).stream()
				.map(PropertyDescriptor::getName)
				.filter(prop -> {return !ignoredProperties.contains(prop);})
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value="/api/v1/institute-rates", method=RequestMethod.GET)
	public @ResponseBody Collection<InstituteRateDto> getInstituteRates(@RequestParam(value="rateClassTypeCode", required=false) String rateClassTypeCode) {
		Collection<InstituteRateDto> rates;
		if (rateClassTypeCode != null) {
			rates = Translate.to(InstituteRateDto.class).fromEach(getBusinessObjectService().findMatching(InstituteRate.class, 
					Collections.singletonMap("rateClass.rateClassTypeCode", rateClassTypeCode)));
		} else {
			rates = Translate.to(InstituteRateDto.class).fromEach(getBusinessObjectService().findAll(InstituteRate.class));
		}
		if (rates == null || rates.isEmpty()) {
			throw new ResourceNotFoundException("not found");
		}
		return rates;
	}
	
	@RequestMapping(value="/api/v1/institute-rates", method=RequestMethod.PUT, consumes = {MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(value = HttpStatus.OK)
	public void updateInstituteRates(@Valid @RequestBody List<InstituteRateDto> updatedRates) {
		if (!permissionService.hasPermissionByTemplate(globalVariableService.getUserSession().getPrincipalId(),
				Constants.MODULE_NAMESPACE_SYSTEM, PermissionConstants.WRITE_CLASS, Collections.singletonMap(KcKimAttributes.CLASS_NAME, InstituteRate.class.getName()))) {
			throw new UnauthorizedAccessException();
		}
		RestAuditLogger auditLogger = restAuditLoggerFactory.getNewAuditLogger(InstituteRate.class, dtoProperties);
		Configuration mooConfig = new Configuration();
		mooConfig.setSourcePropertiesRequired(false);
		Moo moo = new Moo(mooConfig);
		Map<Long, InstituteRate> currentRates = getBusinessObjectService().findAll(InstituteRate.class).stream().collect(Collectors.toMap(InstituteRate::getId, Function.identity()));
		updatedRates.forEach(updatedRate -> {
			InstituteRate currentRate = currentRates.get(updatedRate.getId());
			if (currentRate != null && updatedRate.getInstituteRate() != null) {
				if (!areNonUpdatableValuesEqual(updatedRate, currentRate)) {
					throw new UnprocessableEntityException("attempting to update non-updatable values");
				}
				auditLogger.addModifiedItem(currentRate, updatedRate);
				currentRate.setInstituteRate(updatedRate.getInstituteRate());
				currentRate.setStartDate(new java.sql.Date(updatedRate.getStartDate().getTime()));
				getBusinessObjectService().save(currentRate);
			} else if (updatedRate.getInstituteRate() == null) {
				auditLogger.addDeletedItem(currentRate);
				getBusinessObjectService().delete(currentRate);
			} else {
				InstituteRate newRate = new InstituteRate();
				moo.update(updatedRate).to(newRate);
				if (newRate.getStartDate() == null) {
					newRate.setStartDate(new java.sql.Date(getFiscalYearMonthService().getFiscalYearStartDate(Integer.parseInt(newRate.getFiscalYear())).getTimeInMillis()));
				}
				auditLogger.addNewItem(newRate);
				getBusinessObjectService().save(newRate);
			}
		});
		auditLogger.saveAuditLog();
	}

	protected boolean areNonUpdatableValuesEqual(InstituteRateDto updatedRate, InstituteRate currentRate) {
		return new EqualsBuilder()
			.append(currentRate.getRateClassCode(), updatedRate.getRateClassCode())
			.append(currentRate.getRateTypeCode(), updatedRate.getRateTypeCode())
			.append(currentRate.getActivityTypeCode(), updatedRate.getActivityTypeCode())
			.append(currentRate.getFiscalYear(), updatedRate.getFiscalYear())
			.append(currentRate.getOnOffCampusFlag(), updatedRate.getOnOffCampusFlag())
			.append(currentRate.getUnitNumber(), updatedRate.getUnitNumber())
			.isEquals();
	}
	
	@RequestMapping(value="/instituteRates", method=RequestMethod.GET)
	public String displayInstituteRates() {
		return "instituteRates";
	}
	
	public BusinessObjectService getBusinessObjectService() {
		return businessObjectService;
	}

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	public FiscalYearMonthService getFiscalYearMonthService() {
		return fiscalYearMonthService;
	}

	public void setFiscalYearMonthService(
			FiscalYearMonthService fiscalYearMonthService) {
		this.fiscalYearMonthService = fiscalYearMonthService;
	}

	public PermissionService getPermissionService() {
		return permissionService;
	}

	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

	public RestAuditLoggerFactory getRestAuditLoggerFactory() {
		return restAuditLoggerFactory;
	}

	public void setRestAuditLoggerFactory(RestAuditLoggerFactory restAuditLoggerFactory) {
		this.restAuditLoggerFactory = restAuditLoggerFactory;
	}
	
}
