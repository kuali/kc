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
package org.kuali.kra.service;

import gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType.Enum;

import org.kuali.kra.bo.CitizenshipType;
import org.kuali.kra.infrastructure.CitizenshipTypes;

/**
 * 
 * This class converts and CitizenshipType object to to a gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType.Enum object.
 */
public interface CitizenshipTypeService {
    
    /**
     * 
     * This method converts and CitizenshipType object to to a gov.grants.apply.forms.phs398CareerDevelopmentAwardSup11V11.CitizenshipDataType.Enum object.
     * @param citizenshipType
     * @return
     * @throws IllegalArgumentException If an invalid CitizenshipType is passed in.
     */
    public Enum getEnumValueOfCitizenshipType(CitizenshipType citizenshipType) throws IllegalArgumentException;
    public CitizenshipTypes getCitizenshipDataFromExternalSource();

}
