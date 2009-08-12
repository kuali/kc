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
package org.kuali.kra.proposaldevelopment.rule.event;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.rule.event.KraDocumentEventBase;

/**
 * Abstract superclass for rule events related to Proposal Sites.
 */
public abstract class ProposalSiteEventBase extends KraDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AddProposalCongressionalDistrictEvent.class);
    private List<ProposalSite> proposalSites;
    private String siteIndex;

    /**
     * This constructor takes a single ProposalSite.
     * @param description
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposalSite
     */
    public ProposalSiteEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument, ProposalSite proposalSite) {
        this(description, errorPathPrefix, proposalDevelopmentDocument);
        
        proposalSites = new ArrayList<ProposalSite>();
        proposalSites.add(proposalSite);
        
        siteIndex = "0";
    }

    /**
     * This constructor takes a List of ProposalSites and an index that points to one of them.
     * @param description
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposalSites
     * @param siteIndex
     */
    public ProposalSiteEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument, List<ProposalSite> proposalSites, String siteIndex) {
        this(description, errorPathPrefix, proposalDevelopmentDocument);
        this.proposalSites = proposalSites;
        this.siteIndex = siteIndex;
    }

    private ProposalSiteEventBase(String description, String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        super(description, errorPathPrefix, proposalDevelopmentDocument);
    }
    
    public List<ProposalSite> getProposalSites() {
        return proposalSites;
    }

    public String getSiteIndex() {
        return siteIndex;
    }

    /**
     * This method returns the proposal site at the index pointed to by siteIndex.
     * No check is done to ensure siteIndex is a valid index, etc.
     * @return
     */
    public ProposalSite getProposalSite() {
        return proposalSites.get(new Integer(siteIndex));
    }
    
    @Override
    protected void logEvent() {
        StringBuffer logMessage = new StringBuffer(StringUtils.substringAfterLast(this.getClass().getName(), "."));

        logMessage.append("with site index ");
        logMessage.append(siteIndex);
        logMessage.append(", ");
        logMessage.append(proposalSites==null?"null":proposalSites.size());
        logMessage.append(" proposal sites");

        LOG.debug(logMessage);
    }
}