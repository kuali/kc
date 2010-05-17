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
package org.kuali.kra.bo;

import java.util.LinkedHashMap;

public class CountryCode extends KraPersistableBusinessObjectBase {
    
    private String countryName;
    private String twoCharacterCountryCode;
    private String threeCharacterCountryCode;
    private Long id;
    

    public String getCountryName() {
        return countryName;
    }


    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public String getTwoCharacterCountryCode() {
        return twoCharacterCountryCode;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public void setTwoCharacterCountryCode(String twoCharacterCountryCode) {
        this.twoCharacterCountryCode = twoCharacterCountryCode;
    }


    public String getThreeCharacterCountryCode() {
        return threeCharacterCountryCode;
    }


    public void setThreeCharacterCountryCode(String threeCharacterCountryCode) {
        this.threeCharacterCountryCode = threeCharacterCountryCode;
    }


    @Override
    protected LinkedHashMap toStringMapper() {
        LinkedHashMap propMap = new LinkedHashMap();
        propMap.put("twoCharacterCountryCode", this.getTwoCharacterCountryCode());
        propMap.put("threeCharacterCountryCode", this.getThreeCharacterCountryCode());
        propMap.put("countryName", this.getCountryName());
        propMap.put("updateTimestamp", this.getUpdateTimestamp());
        propMap.put("updateUser", this.getUpdateUser());
        propMap.put("versionNumber", this.getVersionNumber());
        propMap.put("id", this.getId());
        return propMap;
    }

}
