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
package org.kuali.coeus.propdev.impl.person;

import org.kuali.coeus.common.api.person.attr.CitizenshipType;
import org.kuali.coeus.common.api.person.attr.CitizenshipTypeService;
import org.kuali.coeus.propdev.api.person.ProposalPersonContract;
import org.kuali.coeus.s2sgen.api.core.ConfigurationConstants;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * 
 * This service has been made available for implementers who will be using an
 * external source for citizenship data. It hooks into S2SUtilService via the
 * system parameter PI_CITIZENSHIP_FROM_CUSTOM_DATA. Setting this to "0" will
 * see that S2SUtilServiceImpl::getCitizenship receive a CitizenshipTypes from
 * this service, as opposed to KcPerson's extended attributes
 * 
 * Schools who need external citizenship data are expected to override this
 * service with their own implementation of
 * "getCitizenshipDataFromExternalSource().
 */
@Component("citizenshipTypeService")
public class CitizenshipTypeServiceImpl implements CitizenshipTypeService {

    @Autowired
	@Qualifier("parameterService")
	private ParameterService parameterService;

	@Override
	public CitizenshipType getPersonCitizenshipType(ProposalPersonContract proposalPerson) {
		if (proposalPerson == null) {
			throw new IllegalArgumentException("proposalPerson is null");
		}
		Boolean citizenshipTypeSourceInternal = isCitizenshipTypeSourceInternal();
		if (citizenshipTypeSourceInternal != null && !citizenshipTypeSourceInternal) {
			return getCitizenshipDataFromExternalSource(proposalPerson);
		} else {
			Integer citizenshipTypeCode = null;
			Boolean allowOverride = isAllowCitizenshipTypeOverride();
			if (allowOverride && proposalPerson.getCitizenshipType() != null) {
				citizenshipTypeCode = proposalPerson.getCitizenshipType().getCode();
			} else {
				citizenshipTypeCode = proposalPerson.getPerson().getCitizenshipTypeCode();
			}
			
			if (citizenshipTypeCode != null) {
				return getCitizenshipTypeFromCode(String.valueOf(citizenshipTypeCode));
			} else {
				return CitizenshipType.NOT_AVAILABLE;
			}
		}
	}

	protected Boolean isAllowCitizenshipTypeOverride() {
		return parameterService.getParameterValueAsBoolean(Constants.KC_S2S_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
						ConfigurationConstants.ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES);
	}

	protected CitizenshipType getCitizenshipTypeFromCode(String citizenShipCode) {
		if (citizenShipCode
				.equals(parameterService.getParameterValueAsString(Constants.KC_S2S_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
						ConfigurationConstants.NON_US_CITIZEN_WITH_TEMPORARY_VISA_TYPE_CODE))) {
			return CitizenshipType.NON_US_CITIZEN_WITH_TEMPORARY_VISA;
		} else if (citizenShipCode
				.equals(parameterService.getParameterValueAsString(Constants.KC_S2S_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
						ConfigurationConstants.PERMANENT_RESIDENT_OF_US_TYPE_CODE))) {
			return CitizenshipType.PERMANENT_RESIDENT_OF_US;
		} else if (citizenShipCode
				.equals(parameterService.getParameterValueAsString(Constants.KC_S2S_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
						ConfigurationConstants.US_CITIZEN_OR_NONCITIZEN_NATIONAL_TYPE_CODE))) {
			return CitizenshipType.US_CITIZEN_OR_NONCITIZEN_NATIONAL;
		} else if (citizenShipCode
				.equals(parameterService.getParameterValueAsString(Constants.KC_S2S_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
						ConfigurationConstants.PERMANENT_RESIDENT_OF_US_PENDING))) {
			return CitizenshipType.PERMANENT_RESIDENT_OF_US_PENDING;
		} else {
			return CitizenshipType.NOT_AVAILABLE;
		}
	}

	protected Boolean isCitizenshipTypeSourceInternal() {
		return parameterService.getParameterValueAsBoolean(Constants.KC_S2S_PARAMETER_NAMESPACE, Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, 
				ConfigurationConstants.PI_CUSTOM_DATA);
	}

	protected CitizenshipType getCitizenshipDataFromExternalSource(ProposalPersonContract proposalPerson) {
		throw new UnsupportedOperationException(
				"External Source Must be configured when system parameter PI_CITIZENSHIP_FROM_CUSTOM_DATA is set to '0'");
	}

}
