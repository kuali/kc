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
package org.kuali.kra.proposaldevelopment.rules;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.rule.event.DeleteProposalSiteEvent;
import org.kuali.kra.rules.ResearchDocumentRuleBase;

/**
 * This class handles the "Delete Proposal Site" event; it is also a superclass for other rules
 */
public class ProposalSiteRule extends ResearchDocumentRuleBase {

    public boolean processDeleteProposalSiteRules(DeleteProposalSiteEvent deleteProposalSiteEvent) {
        String siteIndexStr = deleteProposalSiteEvent.getSiteIndex();
        return isIndexValid(siteIndexStr, "Site Index");
    }
    
    protected boolean isIndexValid(String indexStr, String indexName) {
        if (StringUtils.isEmpty(indexStr) || !StringUtils.isNumeric(indexStr)) {
            reportError("newPropLocation.location", KeyConstants.ERROR_PROPOSAL_SITES_INDEX_INVALID_FORMAT, indexName);
            return false;
        }
        return true;
    }
}