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
package org.kuali.kra.coi.disclosure;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CoiDisclosedProjectBean implements Serializable {


    private static final long serialVersionUID = 7805100701232656867L;
    
    private boolean projectDisclosed;
    List<Award> disclosedAwards = new ArrayList<Award>();
    List<DevelopmentProposal> disclosedDevProposals = new ArrayList<DevelopmentProposal>();
    List<InstitutionalProposal> disclosedInstProposals = new ArrayList<InstitutionalProposal>();
    
    public CoiDisclosedProjectBean() {
        setDisclosedAwards(new ArrayList<Award>());
        setDisclosedDevProposals(new ArrayList<DevelopmentProposal>());
        setDisclosedInstProposals(new ArrayList<InstitutionalProposal>());
    }
    
    public boolean isProjectDisclosed() {
        return projectDisclosed;
    }
    public void setProjectDisclosed(boolean projectDisclosed) {
        this.projectDisclosed = projectDisclosed;
    }
    public List<Award> getDisclosedAwards() {
        return disclosedAwards;
    }
    public void setDisclosedAwards(List<Award> disclosedAwards) {
        this.disclosedAwards = disclosedAwards;
    }
    public List<DevelopmentProposal> getDisclosedDevProposals() {
        return disclosedDevProposals;
    }
    public void setDisclosedDevProposals(List<DevelopmentProposal> disclosedDevProposals) {
        this.disclosedDevProposals = disclosedDevProposals;
    }
    public List<InstitutionalProposal> getDisclosedInstProposals() {
        return disclosedInstProposals;
    }
    public void setDisclosedInstProposals(List<InstitutionalProposal> disclosedInstProposals) {
        this.disclosedInstProposals = disclosedInstProposals;
    }
    

}
