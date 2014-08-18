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
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBase;
import org.kuali.rice.krad.rules.rule.BusinessRule;

import java.util.ArrayList;
import java.util.List;

/**
 * Superclass for rule events related to Proposal Sites.
 */
public class BasicProposalSiteEvent extends KcDocumentEventBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AddProposalCongressionalDistrictEvent.class);
    private List<ProposalSite> proposalSites;
    private String siteIndex;
    private List<CongressionalDistrict> congressionalDistricts;

    public BasicProposalSiteEvent(String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this("Creating a rule event that only checks the proposal site index. Document Id = " + getDocumentId(proposalDevelopmentDocument), errorPathPrefix, proposalDevelopmentDocument);
    }
    
    /**
     * This constructor takes a single ProposalSite.
     * @param description
     * @param errorPathPrefix
     * @param proposalDevelopmentDocument
     * @param proposalSite
     */
    public BasicProposalSiteEvent(String description, String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument, ProposalSite proposalSite) {
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
    public BasicProposalSiteEvent(String description, String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument, List<ProposalSite> proposalSites, String siteIndex) {
        this(description, errorPathPrefix, proposalDevelopmentDocument);
        this.proposalSites = proposalSites;
        this.siteIndex = siteIndex;
    }

    private BasicProposalSiteEvent(String description, String errorPathPrefix, ProposalDevelopmentDocument proposalDevelopmentDocument) {
        super(description, errorPathPrefix, proposalDevelopmentDocument);
    }
    
    public BasicProposalSiteEvent(String description, ProposalDevelopmentDocument proposalDevelopmentDocument, List<CongressionalDistrict> congressionalDistricts) {
        this(description,proposalDevelopmentDocument);
        this.congressionalDistricts = congressionalDistricts;       
    }
    
    public BasicProposalSiteEvent(ProposalDevelopmentDocument proposalDevelopmentDocument) {
        this("Creating a rule event that only checks the proposal site index. Document Id = " + getDocumentId(proposalDevelopmentDocument), proposalDevelopmentDocument);
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

    @SuppressWarnings("unchecked")
    public Class getRuleInterfaceClass() {
        return ProposalSiteRule.class;
    }

    public boolean invokeRuleMethod(BusinessRule rule) {
        return ((ProposalSiteRule)rule).processBasicProposalSiteRules(this);
    }
}
