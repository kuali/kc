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
package org.kuali.kra.external.customercreation;

import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.module.external.kc.dto.CustomerCreationStatusDto;
import org.kuali.kfs.module.external.kc.dto.CustomerTypeDto;
import org.kuali.kfs.module.external.kc.dto.RolodexDTO;
import org.kuali.kfs.module.external.kc.dto.SponsorDTO;
import org.kuali.kfs.module.external.kc.service.CustomerCreationService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.kra.external.service.KcDtoService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;

public abstract class CustomerCreationClientBase implements CustomerCreationClient {
	
	protected static final String SOAP_SERVICE_NAME = "customerCreationServiceSOAP";
	protected static final QName SERVICE_NAME = new QName(Constants.FINANCIAL_SYSTEM_SERVICE_NAMESPACE, SOAP_SERVICE_NAME);
    private static final String ERROR_MESSAGE = "Cannot connect to the service. The service may be down, please try again later.";
    private static final Log LOG = LogFactory.getLog(CustomerCreationClientBase.class);
    
    private KcDtoService<org.kuali.kra.external.sponsor.SponsorDTO, Sponsor> sponsorDtoService;
    private ParameterService parameterService;
    
    protected abstract CustomerCreationService getServiceHandle();
	
	@Override
	public List<String> createCustomer(Sponsor sponsor, String initiatedByPrincipalName) {
		try {
			CustomerCreationService port = getServiceHandle();
            LOG.info("Connecting to financial system...");
            CustomerCreationStatusDto status = port.createCustomer(createSponsorDto(sponsor), initiatedByPrincipalName);
            if (status.getErrors().isEmpty()) {
            	sponsor.setCustomerNumber(status.getCustomerNumber());
            	return null;
            } else {
            	return status.getErrors();
            }

		} catch (Exception e) {
			LOG.error(ERROR_MESSAGE + e.getMessage(), e);
			List<String> result = new ArrayList<String>();
			result.add(ERROR_MESSAGE + e.getMessage());
			return result;
		}
	}
	
    public List<KeyValue> getCustomerTypes() {
    	List<KeyValue> result = new ArrayList<KeyValue>();
    	LOG.info("Connecting to financial system...");
    	List<CustomerTypeDto> types = getServiceHandle().getCustomerTypes();
    	for (CustomerTypeDto dto : types) {
    		result.add(new ConcreteKeyValue(dto.getCustomerTypeCode(), dto.getCustomerTypeDescription()));
    	}
    	return result;
    }

    public boolean isValidCustomer(String customerNumber) {
    	LOG.info("Connecting to financial system...");
    	return getServiceHandle().isValidCustomer(customerNumber);
    }
	
	public SponsorDTO createSponsorDto(Sponsor sponsor) {
		SponsorDTO dto = new SponsorDTO();
		try {
		    org.kuali.kra.external.sponsor.SponsorDTO kcDto = getSponsorDtoService().buildDto(sponsor);
		    org.kuali.kra.external.sponsor.RolodexDTO rolodex = kcDto.getContactInformation();
		    kcDto.setContactInformation(null);
			BeanUtils.copyProperties(dto, kcDto);
			dto.setContactInformation(new RolodexDTO());
			BeanUtils.copyProperties(dto.getContactInformation(), rolodex);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return dto;
	}

	public KcDtoService<org.kuali.kra.external.sponsor.SponsorDTO, Sponsor> getSponsorDtoService() {
		return sponsorDtoService;
	}

	@Override
	public void setSponsorDtoService(
			KcDtoService<org.kuali.kra.external.sponsor.SponsorDTO, Sponsor> sponsorDtoService) {
		this.sponsorDtoService = sponsorDtoService;
	}

	public ParameterService getParameterService() {
		return parameterService;
	}

	public void setParameterService(ParameterService parameterService) {
		this.parameterService = parameterService;
	}
}
