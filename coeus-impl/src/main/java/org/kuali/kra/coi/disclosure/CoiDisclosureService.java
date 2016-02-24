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
package org.kuali.kra.coi.disclosure;

import org.kuali.coeus.common.framework.version.VersionException;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.coi.*;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;

import java.util.List;
import java.util.Map;

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
    //void updateDisclosureDetails(CoiDisclosure coiDisclosure);
    
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
     * This method is to get a list of protocols that need coi disclosure
     * @param personId
     * @return
     */
    List<IacucProtocol> getIacucProtocols(String personId);
    
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
        
    /**
     * 
     * This method sets up a disclosure project and its corresponding details
     * @param coiDisclosure
     * @param projectId
     */
    void initializeDisclosureProject(CoiDisclosure coiDisclosure, String projectId);
    
    /**
     * 
     * This method sets up the disclosure projects and details for save
     * @param coiDisclosure
     * @param masterDisclosureBean
     */
    public boolean setDisclProjectForSave(CoiDisclosure coiDisclosure, MasterDisclosureBean masterDisclosureBean);
    
    public CoiDisclosure getCurrentDisclosure();
    
    public void populateProposalsAndAwardToCompleteDisclosure(String userId, DisclosureHelper disclosureHelper);
    
    /**
     * This method is to group all project disclosures by financial entity
     * @param coiDisclosure
     * @param masterDisclosureBean
     */
    public void createDisclosuresGroupedByFinancialEntity(CoiDisclosure coiDisclosure, MasterDisclosureBean masterDisclosureBean);

    /**
     * This method is to group all disclosures by event
     * @param masterDisclosureBean
     */
    public void createDisclosuresGroupedByEvent(MasterDisclosureBean masterDisclosureBean);
    
    /**
     * return a list of all disclosures for the given user
     * 
     */
    public List<CoiDisclosure> getAllDisclosuresForUser(String userId);
    
    /**
     * 
     * Check the KRMS rule designated in KRMS_SCREENING_QUESTIONNAIRE_RULE to see if the screening questionnaire rule has passed or not.
     * @return
     */
    public boolean checkScreeningQuestionnaireRule(CoiDisclosureDocument coiDisclosureDocument);


    /**
     * This method is to get a list of undisclosed events from all projects based
     * on search criteria.
     * @param searchCriteria
     * @return
     */
    public List<CoiDisclosureUndisclosedEvents> getUndisclosedEvents(Map<String, String> searchCriteria);
    
    /**
     * Loops through all projects and financial entities and sets the project disclosure to the maximum disposition in that project
     * and returns the maximum disposition status from the disclosure.
     * Returns in progress when the disclosure is in progress and not conflict exists when no financial entities exist.
     * @param coiDisclosure
     * @return
     */
    public CoiDispositionStatus calculateMaximumDispositionStatus(CoiDisclosure coiDisclosure);
    
    public void updateDisclosureAndProjectDisposition(CoiDisclosure coiDisclosure);


    /**
     * This method is to group undisclosed projects by event type
     * @param coiDisclProjects
     * @return
     */
    public List<CoiGroupedMasterDisclosureBean> getUndisclosedProjectsGroupedByEvent(List<CoiDisclProject> coiDisclProjects);
    
    /**
     * This method is to group undisclosed projects by financial entity
     * @param coiDisclProjects
     * @return
     */
    public List<CoiGroupedMasterDisclosureBean> getUndisclosedProjectsGroupedByFinancialEntity(List<CoiDisclProject> coiDisclProjects);
    
}
