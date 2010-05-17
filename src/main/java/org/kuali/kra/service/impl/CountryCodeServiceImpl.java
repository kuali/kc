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

//import org.kuali.kra.bo.CommentType;
import org.kuali.kra.bo.CountryCode;
import org.kuali.kra.service.CountryCodeService;
import org.kuali.rice.kns.service.BusinessObjectService;

import java.util.*;

public class CountryCodeServiceImpl implements CountryCodeService {
    
    private BusinessObjectService businessObjectService;
    private static final String TWO_CHARACTER_CODE_FIELD = "TWO_CHAR_CODE";
    private static final String THREE_CHARACTER_CODE_FIELD = "THREE_CHAR_CODE";
    private static final String COUNTRY_NAME_FIELD = "COUNTRY_NAME";

    public CountryCode getCountryCodeByCountryName(String countryName) {
        return runQuery(COUNTRY_NAME_FIELD, countryName);
    }

    public CountryCode getCountryCodeByThreeCharacterCode(String threeCharacterCode) {
        return runQuery(THREE_CHARACTER_CODE_FIELD, threeCharacterCode);
    }

    public CountryCode getCountryCodeByTwoCharacterCode(String twoCharacterCode) {
        return runQuery(TWO_CHARACTER_CODE_FIELD, twoCharacterCode);
    }
    
    private CountryCode runQuery(String fieldName, String fieldValue){
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(fieldName, fieldValue);
        Collection countryCodes = businessObjectService.findMatching(CountryCode.class, queryMap);
        if(countryCodes.isEmpty()){
            return null;
        }else{
            return (CountryCode)countryCodes.iterator().next();
        }
    }
    
    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

}
