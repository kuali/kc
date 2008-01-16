/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.bo;

import java.util.Comparator;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;

/**
 * Used to order <code>{@link ProposalPerson}</code> instances by <code>{@link ProposalPersonRole}</code>.
 * 
 */
public class ProposalPersonComparator implements Comparator<ProposalPerson> {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalPersonComparator.class);
    
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }

    /**
     * compare one <code>{@link ProposalPerson}</code> instance to another. Sort by <code>{@link ProposalPersonRole}</code> of the
     *  <code>{@link ProposalPerson}</code>
     * 
     * @param person1
     * @param person2
     * @return int
     */
    public int compare(ProposalPerson person1, ProposalPerson person2) {
        int retval = 0;
               
        if (person1.isInvestigator() && person2.isInvestigator()) {
           if (getKeyPersonnelService().isPrincipalInvestigator(person1)) {
               retval--;
           }
           
           if (getKeyPersonnelService().isPrincipalInvestigator(person2)) {
               retval++;
           }
        }
        else if (person1.isInvestigator()) {
            retval--;
        }
        else if (person2.isInvestigator()) {
            retval++;
        }
        
        if (retval == 0) {
            retval = person1.getFullName().compareTo(person2.getFullName());
        }
        
        LOG.info("retval = " + retval);

        return retval;
    }

}
