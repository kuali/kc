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

import java.util.List;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;
import org.kuali.kra.coi.CoiDispositionStatus;
import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.service.VersionException;

public interface CoiDisclosureService {

    /**
     * 
     * This method is to get disclosure reporter when initiate a new disclosure.
     * @param personId
     * @param coiDisclosureId
     * @return
     */
    DisclosurePerson getDisclosureReporter(String personId, Long coiDisclosureId);

    /**
     * 
     * This method is to add a disclosure reporter unit
     * @param disclosureReporter
     * @param newDisclosureReporterUnit
     */
    void addDisclosureReporterUnit(DisclosureReporter disclosureReporter , DisclosureReporterUnit newDisclosureReporterUnit);

    /**
     * 
     * This method is to delete a disclosure reporter unit
     * @param disclosureReporter
     * @param deletedUnits
     * @param unitIndex
     */
    void deleteDisclosureReporterUnit(DisclosureReporter disclosureReporter,List<? extends DisclosureReporterUnit> deletedUnits, int unitIndex);

    /**
     * 
     * This method is to reset the lead unit if there is lead unit change
     * @param disclosureReporter
     */
    void resetLeadUnit(DisclosureReporter disclosureReporter);
    
    /**
     * 
     * This method is for annual disclosure to populate all FE to projects eligible for disclosure
     * @param coiDisclosure
     */
    void initializeDisclosureDetails(CoiDisclosure coiDisclosure);
    
    /**
     * 
     * This method is to update disclosure FE when user is loading an existing disclosure
     * it may add new FE, remove inactivated FE, or update new version of FE
     * @param coiDisclosure
     */
    void updateDisclosureDetails(CoiDisclosure coiDisclosure);
    
    /**
     * 
     * This method is to initialize disclosure FE for manual project
     * @param coiDisclProject
     */
    void initializeDisclosureDetails(CoiDisclProject coiDisclProject);
    
    /**
     * 
     * This method is to update disclosure FE for manual project.
     * @param coiDisclProject
     */
    void updateDisclosureDetails(CoiDisclProject coiDisclProject);

    /**
     * 
     * This method is to set coidiscdetails under coidisclosure before save.
     * There is coidisclprojects between coidisclosure & coidiscdetails, but the relationship
     * is just between coidisclosure & coidiscdetails; so have to do this extra handling.
     * @param coiDisclosure
     */
    void setDisclDetailsForSave(CoiDisclosure coiDisclosure);
    
    /**
     * 
     * This method is to create new version of COI disclosure if there is disclosure exist for the reporter
     * @return
     * @throws VersionException
     */
    CoiDisclosure versionCoiDisclosure() throws VersionException;
    
    /**
     * 
     * This method is to get a list of protocols that need coi disclosure
     * @param personId
     * @return
     */
    List<Protocol> getProtocols(String personId);
    
    /**
     * 
     * This method is to get a list of PD that need coi disclosure
     * @param personId
     * @return
     */
    List<DevelopmentProposal> getProposals(String personId);

    /**
     * 
     * This method is to get a list of Award that need coi disclosure
     * @param personId
     * @return
     */
    List<Award> getAwards(String personId);

    
    /**
     * 
     * This method is to get a list of Institutional Proposal that need coi disclosure
     * @param personId
     * @return
     */
    List<InstitutionalProposal> getInstitutionalProposals(String personId);
    
    /**
     * 
     * This method is to initialize coidiscdetails for new projects (PD/Protocol/Award etc) disclosure
     * @param coiDisclosure
     * @param projectId
     */
    void initializeDisclosureDetails(CoiDisclosure coiDisclosure, String projectId);
    
    /**
     * 
     * This method is to check whether this user has active projects to disclose
     * @return
     */
    boolean isReporter();
    
    /**
     * 
     * This method is to set up form bean to display master disclosure projects
     * @param coiDisclosure
     * @return
     */
    MasterDisclosureBean getMasterDisclosureDetail(CoiDisclosure coiDisclosure);
    
    public List<CoiDispositionStatus> getDispositionStatuses(String disclosureStatusCode);
    
    void initDisclosureFromMasterDisclosure(CoiDisclosure coiDisclosure);
    
    public void setDisclDetailsForSave(CoiDisclosure coiDisclosure, MasterDisclosureBean masterDisclosureBean);
    
}
