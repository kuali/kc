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
package org.kuali.kra.coi.lookup.keyvalue;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.keyvalue.FormViewAwareUifKeyValuesFinderBase;
import org.kuali.kra.coi.*;
import org.kuali.kra.coi.disclosure.CoiDisclosureProjectBean;
import org.kuali.kra.coi.disclosure.MasterDisclosureBean;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.List;


public class CoiDisclosureProjectValuesFinder extends FormViewAwareUifKeyValuesFinderBase {


    @Override
    public List<KeyValue> getKeyValues() {
        List<KeyValue> keyLabels = new ArrayList<KeyValue>();
        CoiDisclosureForm coiDisclosureForm = (CoiDisclosureForm) getFormOrView();

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
