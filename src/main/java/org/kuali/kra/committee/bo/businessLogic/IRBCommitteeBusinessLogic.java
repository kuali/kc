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
package org.kuali.kra.committee.bo.businessLogic;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ErrorReporter;

public class IRBCommitteeBusinessLogic extends CommitteeBusinessLogic {

    private static final String SEPERATOR = ".";
    private static final String INACTIVE_RESEARCH_AREAS_PREFIX = "document.committeeList[0].committeeResearchAreas.inactive";

    public IRBCommitteeBusinessLogic(Committee businessObject, CommitteeCollaboratorFactoryGroup committeeCollaborators) {
        super(businessObject, committeeCollaborators);
    }
    
    /**
     * This method will check if all the research areas that have been added to the committee are indeed active.
     * It is declared public because it will be invoked from the action class for committee as well.
     * @param document
     * @return
     */
    @Override
    public boolean validateCommitteeResearchAreas() {
        boolean inactiveFound = false;
        StringBuffer inactiveResearchAreaIndices = new StringBuffer();
        
        // iterate over all the research areas for the committee BO looking for inactive research areas
        List<CommitteeResearchArea> cras = this.getCommitteeBusinessObject().getCommitteeResearchAreas();
        if(CollectionUtils.isNotEmpty(cras)) {
            int raIndex = 0;
            for (CommitteeResearchArea cra : cras) {
                // get collaborator for the committeeResearchArea BO
                CommitteeResearchAreaBusinessLogic craLogic = getCommitteeCollaboratorFactoryGroup().getCommitteeReserachAreaBusinessLogic(cra);
                if(!(craLogic.isEnclosedResearchAreaActive())) {
                    inactiveFound = true;
                    inactiveResearchAreaIndices.append(raIndex).append(SEPERATOR);
                }
                raIndex++;
            }
        }
        // if we found any inactive research areas in the above loop, report as a single error key suffixed by the list of indices of the inactive areas
        if(inactiveFound) { 
            String committeeResearchAreaInactiveErrorPropertyKey = INACTIVE_RESEARCH_AREAS_PREFIX + SEPERATOR + inactiveResearchAreaIndices.toString();
            new ErrorReporter().reportError(committeeResearchAreaInactiveErrorPropertyKey, KeyConstants.ERROR_COMMITTEE_RESEARCH_AREA_INACTIVE);
        }
        
        return !inactiveFound;
    }
    
}