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
