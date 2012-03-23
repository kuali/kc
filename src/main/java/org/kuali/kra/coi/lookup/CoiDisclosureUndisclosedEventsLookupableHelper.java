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
package org.kuali.kra.coi.lookup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.CoiDisclosureUndisclosedEvents;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.lookup.KraLookupableHelperServiceImpl;
import org.kuali.rice.kns.lookup.HtmlData;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.util.GlobalVariables;


public class CoiDisclosureUndisclosedEventsLookupableHelper extends KraLookupableHelperServiceImpl {

    private CoiDisclosureService coiDisclosureService;

    public List<? extends BusinessObject> getSearchResults(Map<String, String> fieldValues) {
        List<CoiDisclosureUndisclosedEvents>  undisclosedEvents = new ArrayList<CoiDisclosureUndisclosedEvents>();
        String personId = GlobalVariables.getUserSession().getPrincipalId();
        List<Award> awards = coiDisclosureService.getAwards(personId);
        List<InstitutionalProposal> proposals = coiDisclosureService.getInstitutionalProposals(personId);
        List<Protocol> protocols = coiDisclosureService.getProtocols(personId);

        for (Award award : awards) {
            CoiDisclosureUndisclosedEvents event = new CoiDisclosureUndisclosedEvents();
            event.setProjectId(award.getAwardNumber());
            event.setProjectTitle(award.getTitle());
            event.setProjectType("Award");
            event.setReporter(personId);
            undisclosedEvents.add(event);
        }
        
        for (InstitutionalProposal proposal : proposals) {
            CoiDisclosureUndisclosedEvents event = new CoiDisclosureUndisclosedEvents();
            event.setProjectId(proposal.getProposalNumber());
            event.setProjectTitle(proposal.getTitle());
            event.setProjectType("Institutional Proposal");
            event.setReporter(personId);
            undisclosedEvents.add(event);
        }
        
        for (Protocol protocol : protocols) {
            CoiDisclosureUndisclosedEvents event = new CoiDisclosureUndisclosedEvents();
            event.setProjectId(protocol.getProtocolNumber());
            event.setProjectTitle(protocol.getTitle());
            event.setProjectType("Protocol");
            event.setReporter(personId);
            undisclosedEvents.add(event);
        }
        
        
        return undisclosedEvents;
    }

    
   
    public CoiDisclosureService getCoiDisclosureService(CoiDisclosureService coiDisclosureService) {
        return coiDisclosureService;
    }
    
    public void setCoiDisclosureService(CoiDisclosureService coiDisclosureService) {
        this.coiDisclosureService = coiDisclosureService;
    }
    /*
     * The only action that we might have here in the future 
     * would be a notification to the reporter.
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HtmlData> getCustomActionUrls(BusinessObject businessObject, List pkNames) {
        List<HtmlData> htmlDataList = new ArrayList<HtmlData>();
        return htmlDataList;
    }
    
   /*
    * Leaving this empty because we are not trying to open any documents.
    */
    @Override
    protected String getDocumentTypeName() {
        return "";
    }

    /*
     *This lookup is also not associated with any particular document, so leaving the html action part to null 
     */
    @Override
    protected String getHtmlAction() {
        return null;
    }

    @Override
    protected String getKeyFieldName() {
        return "projectId";
    }
       
}
