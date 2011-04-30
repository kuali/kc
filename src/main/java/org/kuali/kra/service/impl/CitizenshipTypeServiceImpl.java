/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType;
import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType.Enum;

import org.kuali.kra.bo.CitizenshipType;
import org.kuali.kra.infrastructure.CitizenshipTypes;
import org.kuali.kra.service.CitizenshipTypeService;

/**
 * 
 * This class...
 */
public class CitizenshipTypeServiceImpl implements CitizenshipTypeService {
    
    /**
     * 
     * @see org.kuali.kra.service.CitizenshipTypeService#getEnumValueOfCitizenshipType(org.kuali.kra.bo.CitizenshipType)
     */
    public Enum getEnumValueOfCitizenshipType(CitizenshipType citizenshipType) throws IllegalArgumentException {
        Enum retVal = null;
        switch(citizenshipType.getCitizenshipTypeCode()){
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

	public CitizenshipTypes getCitizenshipDataFromExternalSource() {
		// TODO  generate data for citizenship
		throw new RuntimeException("Method not implemented");
		
	}
   

}
