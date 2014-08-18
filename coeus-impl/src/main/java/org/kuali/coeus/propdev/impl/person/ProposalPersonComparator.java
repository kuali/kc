/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.propdev.impl.person;

import java.util.Comparator;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Used to order <code>{@link ProposalPerson}</code> instances by the role.
 */
public class ProposalPersonComparator implements Comparator<ProposalPerson> {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalPersonComparator.class);


    /**
     * compare one <code>{@link ProposalPerson}</code> instance to another. Sort by the role of the
     *  <code>{@link ProposalPerson}</code>
     * 
     * @param person1
     * @param person2
     * @return int
     */
    public int compare(ProposalPerson person1, ProposalPerson person2) {
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
        }
        
        if (retval == 0) {
            retval = massageOrdinalNumber(person1).compareTo(massageOrdinalNumber(person2));
        }
        
        if (retval == 0) {
            if (isNotBlank(person1.getFullName())) {
                retval = person1.getLastName().compareTo(person2.getLastName());
            }
            else if (isNotBlank(person2.getLastName())) {
                retval--; 
            }
        }
        
        LOG.info("retval = " + retval);

        return retval;
    }

    private Integer massageOrdinalNumber(ProposalPerson person) {
        return person.getOrdinalPosition() != null ? person.getOrdinalPosition() : -1;
    }
    
}
