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
package org.kuali.coeus.common.framework.person;

import java.util.Comparator;

import org.kuali.coeus.common.framework.rolodex.PersonRolodex;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Used to order <code>{@link PersonRolodex}</code> instances by the role.
 */
public class PersonRolodexComparator implements Comparator<PersonRolodex> {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(PersonRolodexComparator.class);


    /**
     * compare one <code>{@link PersonRolodex}</code> instance to another. Sort by the role of the
     *  <code>{@link PersonRolodex}</code>
     * 
     * @param person1
     * @param person2
     * @return int
     */
    public int compare(PersonRolodex person1, PersonRolodex person2) {
        int retval = 0;
               
        if (person1.isInvestigator() || person2.isInvestigator()) {
            if (person1.isPrincipalInvestigator() 
                    || person2.isPrincipalInvestigator()) {
               if (person1.isPrincipalInvestigator()) {
                   retval--;
               }
               
               if (person2.isPrincipalInvestigator()) {
                   retval++;
               }
            }

            if (retval == 0) {
                if (person1.isMultiplePi()
                        || person2.isMultiplePi()) {
                    if (person1.isMultiplePi()) {
                        retval--;
                    }

                    if (person2.isMultiplePi()) {
                        retval++;
                    }
                }
            }
        }

        if (retval == 0) {
            retval = massageOrdinalNumber(person1).compareTo(massageOrdinalNumber(person2));
        }
        
        if (retval == 0) {
            if (isNotBlank(person1.getFullName()) && isNotBlank(person1.getLastName()) && isNotBlank(person2.getLastName())) {
                retval = person1.getLastName().compareTo(person2.getLastName());
            }
            else if (isNotBlank(person2.getLastName())) {
                retval--; 
            }
        }
        
        if (LOG.isDebugEnabled()) {
        	LOG.debug("retval = " + retval);
        }

        return retval;
    }

    private Integer massageOrdinalNumber(PersonRolodex person) {
        return person.getOrdinalPosition() != null ? person.getOrdinalPosition() : -1;
    }
    
}
