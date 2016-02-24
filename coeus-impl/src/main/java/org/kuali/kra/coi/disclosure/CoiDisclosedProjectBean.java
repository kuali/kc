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
