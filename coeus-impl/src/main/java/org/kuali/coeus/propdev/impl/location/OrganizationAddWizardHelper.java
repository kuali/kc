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
package org.kuali.coeus.propdev.impl.location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.coeus.common.framework.rolodex.Rolodex;

public class OrganizationAddWizardHelper {

    private Map<String, String> lookupFieldValues;
    private Map<String, String> newOrganizationFieldValues;
    private List<Rolodex> results;      
   
	public OrganizationAddWizardHelper() {
        lookupFieldValues = new HashMap<String, String>();
        results = new ArrayList<Rolodex>();
        newOrganizationFieldValues = new HashMap<String, String>();
    }
    
    public void reset() {
        lookupFieldValues.clear();
        results.clear();       
    } 
    
    public List<Rolodex> getResults() {
		return results;
	}

	public void setResults(List<Rolodex> results) {
		this.results = results;
	}

	public Map<String, String> getLookupFieldValues() {
        return lookupFieldValues;
    }

    public void setLookupFieldValues(Map<String, String> lookupFieldValues) {
        this.lookupFieldValues = lookupFieldValues;
    }

	public Map<String, String> getNewOrganizationFieldValues() {
		return newOrganizationFieldValues;
	}

	public void setNewOrganizationFieldValues(
			Map<String, String> newOrganizationFieldValues) {
		this.newOrganizationFieldValues = newOrganizationFieldValues;
	}
	
}
