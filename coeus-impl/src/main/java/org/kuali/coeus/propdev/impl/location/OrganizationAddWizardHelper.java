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
