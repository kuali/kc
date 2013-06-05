/*
 * Copyright 2005-2013 The Kuali Foundation
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
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureDocument;
import org.kuali.kra.coi.CoiDisclosureEventType;
import org.kuali.kra.coi.CoiDisclosureForm;
import org.kuali.kra.coi.Disclosurable;
import org.kuali.kra.coi.disclosure.CoiDisclosureProjectBean;
import org.kuali.kra.coi.disclosure.CoiDisclosureService;
import org.kuali.kra.coi.disclosure.MasterDisclosureBean;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.keyvalues.KeyValuesBase;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;


public class CoiDisclosureProjectValuesFinder extends KeyValuesBase {


    @SuppressWarnings("all")
    public List getKeyValues() {
        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) KNSGlobalVariables.getKualiForm();

        CoiDisclosureDocument coiDisclosureDocument = coiDisclosureForm.getCoiDisclosureDocument();
        CoiDisclosure coiDisclosure = coiDisclosureDocument.getCoiDisclosure();
        String event = coiDisclosure.getEventTypeCode();
        String pid = coiDisclosureForm.getDisclosureHelper().getNewProjectId();


        if (coiDisclosureDocument != null && coiDisclosure != null && !StringUtils.isEmpty(event)) { 
            if (StringUtils.equalsIgnoreCase(event, CoiDisclosureEventType.UPDATE)) {
                addMasterProjects(keyLabels, coiDisclosureForm.getDisclosureHelper().getMasterDisclosureBean());
            } else {
                if (!coiDisclosure.getCoiDisclProjects().isEmpty()) {
                    for (CoiDisclProject project : coiDisclosure.getCoiDisclProjects()) {
                        String projectId = project.getProjectId();
                        keyLabels.add(new ConcreteKeyValue (projectId, project.getEventDescription() 
                                + "--" + project.getProjectName() + "--" + projectId));
                    }
                } 
            }         
        }

        return keyLabels;

    }

    private void addMasterProjects(List<KeyValue> keyLabels, MasterDisclosureBean masterDisclosureBean) {
        addManualProject(keyLabels, masterDisclosureBean.getManualProtocolProjects(), "Manual Protocol --");
        addManualProject(keyLabels, masterDisclosureBean.getManualTravelProjects(), "Manual Travel --");
        addManualProject(keyLabels, masterDisclosureBean.getManualAwardProjects(), "Manual Award --");
        addManualProject(keyLabels, masterDisclosureBean.getManualProposalProjects(), "Manual Proposal --");
        addManualProject(keyLabels, masterDisclosureBean.getOtherManualProjects(), "Manual Other --");
        addAutomaticProject(keyLabels, masterDisclosureBean.getAwardProjects(), "AWARD --");
        addAutomaticProject(keyLabels, masterDisclosureBean.getProtocolProjects(), "PROTOCOL --");
        addAutomaticProject(keyLabels, masterDisclosureBean.getProposalProjects(), "PROPOSAL --");
    }

    private void addManualProject(List<KeyValue> keyLabels, List<CoiDisclosureProjectBean> manualProtocolProjects, String projectLabel) {
        for (CoiDisclosureProjectBean disclProjectBean : manualProtocolProjects) {
            CoiDisclProject disclProject = (CoiDisclProject) disclProjectBean.getCoiDisclProject();
            keyLabels.add(new ConcreteKeyValue(disclProject.getProjectId(), 
                    formatLabel(projectLabel + disclProject.getProjectId(), disclProject.getProjectName())));
        }

    }

    private void addAutomaticProject(List<KeyValue> keyLabels, List<CoiDisclosureProjectBean> automaticProtocolProjects, String projectLabel) {
        for (CoiDisclosureProjectBean disclProjectBean : automaticProtocolProjects) {
            CoiDisclProject coiDisclProject = disclProjectBean.getCoiDisclProject();
            keyLabels.add(new ConcreteKeyValue(coiDisclProject.getProjectId(), 
                    formatLabel(projectLabel + coiDisclProject.getProjectId(),coiDisclProject.getProjectName())));
        }

    }

    protected String formatLabel(String number, String title) {
        return number + "--" + title;
    }

}
