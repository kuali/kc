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
package org.kuali.coeus.propdev.impl.person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.coeus.propdev.impl.person.ProposalPerson;

public class KeyPersonnelAddWizardHelper {

    private String personType;
    private Map<String, String> lookupFieldValues;
    private List<ProposalPerson> results;
    private String personRole;
    private String keyPersonProjectRole;
    
    public KeyPersonnelAddWizardHelper() {
        lookupFieldValues = new HashMap<String, String>();
        results = new ArrayList<ProposalPerson>();
    }
    
    public void reset() {
        personType = "E";
        lookupFieldValues.clear();
        results.clear();
        personRole = null;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Map<String, String> getLookupFieldValues() {
        return lookupFieldValues;
    }

    public void setLookupFieldValues(Map<String, String> lookupFieldValues) {
        this.lookupFieldValues = lookupFieldValues;
    }

    public List<ProposalPerson> getResults() {
        return results;
    }

    public void setResults(List<ProposalPerson> results) {
        this.results = results;
    }

    public String getPersonRole() {
        return personRole;
    }

    public void setPersonRole(String personRole) {
        this.personRole = personRole;
    }

	public String getKeyPersonProjectRole() {
		return keyPersonProjectRole;
	}

	public void setKeyPersonProjectRole(String keyPersonProjectRole) {
		this.keyPersonProjectRole = keyPersonProjectRole;
	}
}
