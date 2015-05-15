/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package co.kuali.coeus.web.jstl;

import java.util.Arrays;
import java.util.List;

import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.affiliation.EntityAffiliationContract;
import org.kuali.rice.kim.impl.identity.PersonImpl;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;

public class PortalFunctions {

	//STAFF,FCLTY,AFLT, etc...
    /**
     * Determines if the given the currently logged in user has affiliations of the types specified in <code>types</code>
     * 
     * @param types comma separated list of affiliation types. It will get split.
     * @return true if the currently logged in user has ANY type in <code>types</code>.
     */
	public static boolean showByAffiliateType(String types){
		boolean success = false;
        if (GlobalVariables.getUserSession() == null) {
            return false;
        }
		final PersonImpl currentUser = (PersonImpl) GlobalVariables.getUserSession().getPerson();
		
		if (ObjectUtils.isNull(currentUser)){
			return false;
		}
		final List<String> typesList = Arrays.asList(types.split(","));

		for (int i = 0; currentUser.getAffiliations() != null 
                      && i < currentUser.getAffiliations().size(); i++){
			if (currentUser.getAffiliations().get(i) != null 
                && currentUser.getAffiliations().get(i).isDefaultValue() 
                && currentUser.getAffiliations().get(i).isActive()) {
                
				if (currentUser.getAffiliations().get(i).getAffiliationType() != null 
                    && typesList.contains(currentUser.getAffiliations().get(i).getAffiliationType().getCode())){
					success = true;
				}
				break;
			}
		}
		return success;
	}

    /**
     * This is not like {@link #showByAffiliateType(String)}. It is not checking if a user has a specific affiliation. Rather
     * it checks if the currently logged in user has any affiliation whatsoever. An affiliation is only valid if it is active.
     *
     * @return if the currently logged in user has an affiliation at all, or false otherwise.
     */
    public static boolean hasAffiliation() {
        if (GlobalVariables.getUserSession() == null) {
            return false;
        }

		final PersonImpl currentUser = (PersonImpl) GlobalVariables.getUserSession().getPerson();

		if (ObjectUtils.isNull(currentUser)){
			return false;
		}

        for (final EntityAffiliationContract affiliation : currentUser.getAffiliations()) {
            if (affiliation != null && affiliation.isActive()) {
                return true;
            }
        }
        return false;
    }
}
