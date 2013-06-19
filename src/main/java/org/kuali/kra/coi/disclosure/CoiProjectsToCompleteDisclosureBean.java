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

import org.kuali.kra.award.home.Award;
import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;

public class CoiProjectsToCompleteDisclosureBean implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7805100701232656867L;
    
    private String personId;
    private String personName;
    List<Award> awardsToDisclose;
    List<DevelopmentProposal> devProposalsToDisclose;
    List<InstitutionalProposal> instituteProposalsToDisclose;
    List<Protocol> irbProtocolsToDisclose;
    List<IacucProtocol> iacucProtocolsToDisclose;
    
    public CoiProjectsToCompleteDisclosureBean() {
        setAwardsToDisclose(new ArrayList<Award>());
        setDevProposalsToDisclose(new ArrayList<DevelopmentProposal>());
        setInstituteProposalsToDisclose(new ArrayList<InstitutionalProposal>());
        setIrbProtocolsToDisclose(new ArrayList<Protocol>());
        setIacucProtocolsToDisclose(new ArrayList<IacucProtocol>());
    }

    public List<Award> getAwardsToDisclose() {
        return awardsToDisclose;
    }

    public void setAwardsToDisclose(List<Award> awardsToDisclose) {
        this.awardsToDisclose = awardsToDisclose;
    }

    public List<DevelopmentProposal> getDevProposalsToDisclose() {
        return devProposalsToDisclose;
    }

    public void setDevProposalsToDisclose(List<DevelopmentProposal> devProposalsToDisclose) {
        this.devProposalsToDisclose = devProposalsToDisclose;
    }

    public List<InstitutionalProposal> getInstituteProposalsToDisclose() {
        return instituteProposalsToDisclose;
    }

    public void setInstituteProposalsToDisclose(List<InstitutionalProposal> instituteProposalsToDisclose) {
        this.instituteProposalsToDisclose = instituteProposalsToDisclose;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public List<Protocol> getIrbProtocolsToDisclose() {
        return irbProtocolsToDisclose;
    }

    public void setIrbProtocolsToDisclose(List<Protocol> irbProtocolsToDisclose) {
        this.irbProtocolsToDisclose = irbProtocolsToDisclose;
    }

    public List<IacucProtocol> getIacucProtocolsToDisclose() {
        return iacucProtocolsToDisclose;
    }

    public void setIacucProtocolsToDisclose(List<IacucProtocol> iacucProtocolsToDisclose) {
        this.iacucProtocolsToDisclose = iacucProtocolsToDisclose;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
    
    

}
