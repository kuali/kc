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
import org.kuali.kra.coi.DisclosureReporter;
import org.kuali.kra.coi.DisclosureReporterUnit;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.service.VersionException;

public interface CoiDisclosureService {
    // TODO : refactor financialentityservice and this service to share many very similar methods
    DisclosurePerson getDisclosureReporter(String personId, Long coiDisclosureId);

    void addDisclosureReporterUnit(DisclosureReporter disclosureReporter , DisclosureReporterUnit newDisclosureReporterUnit);
    void deleteDisclosureReporterUnit(DisclosureReporter disclosureReporter,List<? extends DisclosureReporterUnit> deletedUnits, int unitIndex);
    void resetLeadUnit(DisclosureReporter disclosureReporter);
    
    void initializeDisclosureDetails(CoiDisclosure coiDisclosure);
    
    void updateDisclosureDetails(CoiDisclosure coiDisclosure);
    
    void initializeDisclosureDetails(CoiDisclProject coiDisclProject);
    
    void updateDisclosureDetails(CoiDisclProject coiDisclProject);

    void setDisclDetailsForSave(CoiDisclosure coiDisclosure);
    
    CoiDisclosure versionCoiDisclosure() throws VersionException;
    
    List<Protocol> getProtocols(String personId);
    List<DevelopmentProposal> getProposals(String personId);
    List<Award> getAwards(String personId);
    List<InstitutionalProposal> getInstitutionalProposals(String personId);
    void initializeDisclosureDetails(CoiDisclosure coiDisclosure, String projectId);
}
