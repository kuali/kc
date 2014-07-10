/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.s2sgen.impl.citizenship;

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType.Enum;

import org.kuali.coeus.common.api.person.attr.CitizenshipTypeContract;
import org.springframework.stereotype.Component;

/**
 * 
 * This service has been made available for implementers who will be using an external source
 * for citizenship data. It hooks into S2SUtilService via the system parameter
 * PI_CITIZENSHIP_FROM_CUSTOM_DATA. Setting this to "0" will see that S2SUtilServiceImpl::getCitizenship receive a
 * CitizenshipTypes from this service, as opposed to KcPerson's extended attributes
 * 
 * Schools who need external citizenship data are expected to override this service with their own
 * implementation of "getCitizenshipDataFromExternalSource().
 * 
 * getEnumValueOfCitizenshipType has been included as a convenience method should it be needed.
 */
@Component("citizenshipTypeService")
public class CitizenshipTypeServiceImpl implements CitizenshipTypeService {
    
    @Override
    public Enum getEnumValueOfCitizenshipType(CitizenshipTypeContract citizenshipType) throws IllegalArgumentException {
        Enum retVal = null;
        switch(citizenshipType.getCode()){
            case CitizenshipDataType.INT_NON_U_S_CITIZEN_WITH_TEMPORARY_VISA : {
                retVal = CitizenshipDataType.NON_U_S_CITIZEN_WITH_TEMPORARY_VISA;
                break;
            }
            case CitizenshipDataType.INT_PERMANENT_RESIDENT_OF_U_S : {
                retVal = CitizenshipDataType.PERMANENT_RESIDENT_OF_U_S;
                break;
            }
            case CitizenshipDataType.INT_U_S_CITIZEN_OR_NONCITIZEN_NATIONAL : {
                retVal = CitizenshipDataType.U_S_CITIZEN_OR_NONCITIZEN_NATIONAL;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid citizenship type provided");
            }
        }
        return retVal;
    }

    @Override
	public CitizenshipType getCitizenshipDataFromExternalSource() {
		throw new UnsupportedOperationException("External Source Must be configured when system parameter PI_CITIZENSHIP_FROM_CUSTOM_DATA is set to '0'");
	}

}
