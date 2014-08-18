/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.propdev.impl.location.BasicProposalSiteEvent;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;

/**
 * This class handles the "Delete Proposal Site" and "Clear Proposal Site Address" events; it is also a superclass for other rules
 */
public class ProposalSiteRule extends KcTransactionalDocumentRuleBase {

    public boolean processBasicProposalSiteRules(BasicProposalSiteEvent proposalSiteEvent) {
        String siteIndexStr = proposalSiteEvent.getSiteIndex();
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
