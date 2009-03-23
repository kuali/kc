/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.committee.rules;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.kra.bo.ResearchArea;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeMembership;
import org.kuali.kra.committee.bo.CommitteeResearchArea;
import org.kuali.kra.committee.rule.AddCommitteeResearchAreaRule;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class contains the rules to validate a <code>{@link CommitteeResearchArea}</code>
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class CommitteeResearchAreaRule extends ResearchDocumentRuleBase implements AddCommitteeResearchAreaRule {

    /**
     * @see org.kuali.kra.committee.rule.AddCommitteeResearchAreaRule#processAddCommitteeResearchAreaRules(org.kuali.kra.committee.bo.Committee, java.lang.String)
     */
    public boolean processAddCommitteeResearchAreaRules(Committee committee, String researchAreaCode) {
        boolean valid = true;
        
        if (invalidResearchArea(researchAreaCode)) {
            valid = false;
            reportError(Constants.COMMITTEE_RESEARCH_AREAS_PROPERTY_KEY, 
                        KeyConstants.ERROR_RESEARCH_AREA_INVALID);
        }
        else if (isDuplicateResearchArea(committee, researchAreaCode)) {
            valid = false;
            reportError(Constants.COMMITTEE_RESEARCH_AREAS_PROPERTY_KEY, 
                        KeyConstants.ERROR_RESEARCH_AREA_DUPLICATE);
        }
        
        return valid;
    }

    /**
     * Is this a invalid research area code?
     * @param researchAreaCode the type code of the research area
     * @return true if invalid; otherwise false
     */
    private boolean invalidResearchArea(String researchAreaCode) {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> primaryKeys = new HashMap<String, String>();
        primaryKeys.put("researchAreaCode", researchAreaCode);
        ResearchArea researchArea = (ResearchArea) businessObjectService.findByPrimaryKey(ResearchArea.class, primaryKeys);
        return researchArea == null;
    }

    /**
     * Is this a duplicate research area?
     * @param committee the committee
     * @param researchAreaCode the research area type code to look for
     * @return true if duplicate (already in the committee); otherwise false
     */
    private boolean isDuplicateResearchArea(Committee committee, String researchAreaCode) {
        for (CommitteeResearchArea researchArea : committee.getCommitteeResearchAreas()) {
            if (StringUtils.equals(researchArea.getResearchAreaCode(), researchAreaCode)) {
                return true;
            }
        }
        return false;
    }
}
