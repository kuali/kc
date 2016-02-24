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
