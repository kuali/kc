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
package org.kuali.kra.coi.disclosure;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDisclosureEventType;

public class MasterDisclosureBean implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8636120108965638825L;
    private CoiDisclosure coiDisclosure;
    private List<CoiDisclosureProjectBean> awardProjects;
    private List<CoiDisclosureProjectBean> proposalProjects;
    private List<CoiDisclosureProjectBean> protocolProjects;
    private List<CoiDisclosureProjectBean> manualAwardProjects;
    private List<CoiDisclosureProjectBean> manualProposalProjects;
    private List<CoiDisclosureProjectBean> manualProtocolProjects;
    private List<CoiDisclosureProjectBean> allProjects;

    
    public MasterDisclosureBean() {
        awardProjects = new ArrayList<CoiDisclosureProjectBean>();
        proposalProjects = new ArrayList<CoiDisclosureProjectBean>();
        protocolProjects = new ArrayList<CoiDisclosureProjectBean>();
        manualAwardProjects = new ArrayList<CoiDisclosureProjectBean>();
        manualProposalProjects = new ArrayList<CoiDisclosureProjectBean>();
        manualProtocolProjects = new ArrayList<CoiDisclosureProjectBean>();
        allProjects = new ArrayList<CoiDisclosureProjectBean>();
    }
    
    public CoiDisclosure getCoiDisclosure() {
        return coiDisclosure;
    }

    public void setCoiDisclosure(CoiDisclosure coiDisclosure) {
        this.coiDisclosure = coiDisclosure;
    }

    public List<CoiDisclosureProjectBean> getAwardProjects() {
        return awardProjects;
    }

    public void setAwardProjects(List<CoiDisclosureProjectBean> awardProjects) {
        this.awardProjects = awardProjects;
    }

    public List<CoiDisclosureProjectBean> getProposalProjects() {
        return proposalProjects;
    }

    public void setProposalProjects(List<CoiDisclosureProjectBean> proposalProjects) {
        this.proposalProjects = proposalProjects;
    }

    public List<CoiDisclosureProjectBean> getProtocolProjects() {
        return protocolProjects;
    }

    public void setProtocolProjects(List<CoiDisclosureProjectBean> protocolProjects) {
        this.protocolProjects = protocolProjects;
    }

    public List<CoiDisclosureProjectBean> getManualAwardProjects() {
        return manualAwardProjects;
    }

    public void setManualAwardProjects(List<CoiDisclosureProjectBean> manualAwardProjects) {
        this.manualAwardProjects = manualAwardProjects;
    }

    public List<CoiDisclosureProjectBean> getManualProposalProjects() {
        return manualProposalProjects;
    }

    public void setManualProposalProjects(List<CoiDisclosureProjectBean> manualProposalProjects) {
        this.manualProposalProjects = manualProposalProjects;
    }

    public List<CoiDisclosureProjectBean> getManualProtocolProjects() {
        return manualProtocolProjects;
    }

    public void setManualProtocolProjects(List<CoiDisclosureProjectBean> manualProtocolProjects) {
        this.manualProtocolProjects = manualProtocolProjects;
    }

    public void addProject(CoiDisclosureProjectBean coiDisclosureProjectBean, String projectTypeCode) {
        allProjects.add(coiDisclosureProjectBean);
        int projectType = Integer.parseInt(projectTypeCode);
        switch (projectType) {
            case 1 :
                getAwardProjects().add(coiDisclosureProjectBean);
                break;
            case 2:
                getProposalProjects().add(coiDisclosureProjectBean);
                break;
            case 3:
                getProtocolProjects().add(coiDisclosureProjectBean);
                break;
            case 11:
                getManualAwardProjects().add(coiDisclosureProjectBean);
                break;
            case 12:
                getManualProposalProjects().add(coiDisclosureProjectBean);
                break;
            case 13:
                getManualProtocolProjects().add(coiDisclosureProjectBean);
                break;
        }
    }

    public List<CoiDisclosureProjectBean> getAllProjects() {
        return allProjects;
    }

    public void setAllProjects(List<CoiDisclosureProjectBean> allProjects) {
        this.allProjects = allProjects;
    }
}
