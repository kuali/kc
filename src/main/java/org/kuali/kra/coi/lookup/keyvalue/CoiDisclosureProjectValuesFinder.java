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
package org.kuali.kra.coi.lookup.keyvalue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;


public class CoiDisclosureProjectValuesFinder extends KeyValuesBase {
    

    @SuppressWarnings("all")
    public List getKeyValues() {
        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) KNSGlobalVariables.getKualiForm();
        keyLabels.add(new ConcreteKeyValue("", "select"));

        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
        String event = coiDisclosure.getEventTypeCode();
        String pid = coiDisclosureForm.getDisclosureHelper().getNewProjectId();


        if (coiDisclosureDocument != null && coiDisclosure != null && !StringUtils.isEmpty(event)) { 

            // annual disclosure
            if (StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.ANNUAL)) {
                addAwards(keyLabels, pid);
                addProposals(keyLabels, pid);
                addIp(keyLabels, pid);
                addProtocols(keyLabels, pid);
            } else {
                // manual disclosure
                if (!coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getCoiDisclProjects().isEmpty()) {
                String projectId = coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getCoiDisclProjects().get(0).getCoiProjectId();
                keyLabels.add(new ConcreteKeyValue(projectId, coiDisclosureForm.getCoiDisclosureDocument().getCoiDisclosure().getCoiDisclProjects().get(0).getCoiProjectTitle() + "--"
                                                          + projectId));
                }
                
                else {
                    addProjects(keyLabels, event, pid);
                }
            }

        }

        return keyLabels;

    }


    protected void addAwards(List<KeyValue> keyLabels, String pid) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        List<Award> awards = getCoiDisclosureService().getAwards(userId);
        for (Award award : awards) {
            if (ObjectUtils.isNotNull(pid)) {
                if (StringUtils.equalsIgnoreCase(award.getAwardId().toString(), pid)) {
                    keyLabels.add(new ConcreteKeyValue(award.getAwardId().toString(), 
                            formatLabel("AWARD--" + award.getAwardNumber(), award.getTitle())));
                }
            } else {
                keyLabels.add(new ConcreteKeyValue(award.getAwardId().toString(), 
                        formatLabel("AWARD--" + award.getAwardNumber(), award.getTitle())));
            }
            
        }
    }
    
    private void addProtocols(List<KeyValue> keyLabels, String pid) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();

        List<Protocol> protocols = getCoiDisclosureService().getProtocols(userId);
        for (Protocol protocol : protocols) {
            
            keyLabels.add(new ConcreteKeyValue(protocol.getProtocolId().toString(), 
                    formatLabel("PROTOCOL--" + protocol.getProtocolNumber(), protocol.getTitle())));
        }        
    }


    protected void addIp(List<KeyValue> keyLabels, String pid) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();

        List<InstitutionalProposal> ips = getCoiDisclosureService().getInstitutionalProposals(userId);
        for (InstitutionalProposal ip : ips) {
            keyLabels.add(new ConcreteKeyValue(ip.getInstProposalNumber(), 
                    formatLabel("IP--" + ip.getInstProposalNumber(), ip.getTitle())));
        }
    }


    private void addProposals(List<KeyValue> keyLabels, String pid) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();

        List<DevelopmentProposal> proposals = getCoiDisclosureService().getProposals(userId);
        for (DevelopmentProposal proposal : proposals) {
            keyLabels.add(new ConcreteKeyValue(proposal.getProposalNumber(), 
                    formatLabel("PROPOSAL--" + proposal.getProposalNumber(),proposal.getTitle())));
        }        
    }


    protected String formatLabel(String number, String title) {
        return number + "--" + title;
    }

    /**
     * This method...
     * @param keyLabels
     * @param id
     */
    protected void addProjects(List<KeyValue> keyLabels, String event, String pid) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();

       /* if (StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.MANUAL_AWARD)) {
            CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) GlobalVariables.getKualiForm();
           // keyLabels.add(new KeyLabelPair("", "select"));

            CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
            CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
            addAwards(keyLabels, coiDisclosure.getModuleItemKey());
           // check moduleItemKey for award id.
        }*/
        if (StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.AWARD)) {
            addAwards(keyLabels, pid);
        }
        if (StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.DEVELOPMENT_PROPOSAL)) {
            addProposals(keyLabels, pid);
            /*List<InstitutionalProposal> ips = getCoiDisclosureService().getInstitutionalProposals(userId);
            for (InstitutionalProposal ip : ips) {
                keyLabels.add(new KeyLabelPair(ip.getInstProposalNumber(), 
                        formatLabel("IP--" + ip.getInstProposalNumber(), ip.getTitle())));
            }*/
        }
        if (StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.IRB_PROTOCOL)) {
            addProtocols(keyLabels, pid);
        }
        if (StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.INSTITUTIONAL_PROPOSAL)) {
            addIp(keyLabels, pid);
        }
    }

    protected CoiDisclosureService getCoiDisclosureService() {
        return KraServiceLocator.getService(CoiDisclosureService.class);
    }


}
