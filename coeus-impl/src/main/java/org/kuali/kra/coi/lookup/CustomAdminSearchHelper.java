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
package org.kuali.kra.coi.lookup;

import org.kuali.kra.coi.CoiDisclProject;
import org.kuali.kra.coi.CoiDisclosure;

import java.util.ArrayList;
import java.util.List;

public class CustomAdminSearchHelper {

    private List<CoiDisclosure> allOpenReviews = new ArrayList<CoiDisclosure>();
    private List<CoiDisclosure> pendingReviews = new ArrayList<CoiDisclosure>();
    private List<CoiDisclosure> inProgressReviews = new ArrayList<CoiDisclosure>();
    
    protected List<CoiDisclosure> filterByFinEnt(List<CoiDisclosure> disclosures, boolean hasFinEnt) {
        List<CoiDisclosure> results = new ArrayList<CoiDisclosure>();
        for (CoiDisclosure discl : disclosures) {
            boolean disclHasFinEnt = hasFinEnt(discl);
            if (disclHasFinEnt == hasFinEnt) {
                results.add(discl);
            }
        }
        return results;
    }
    
    public boolean hasFinEnt(CoiDisclosure coiDisclosure) {
        boolean disclHasFinEnt = false;
        for (CoiDisclProject disclProject : coiDisclosure.getCoiDisclProjects()) {
            if (!disclProject.getCoiDiscDetails().isEmpty()) {
                disclHasFinEnt = true;
            }
        }
        return disclHasFinEnt;
    }
    
    public List<CoiDisclosure> getAllOpenReviews() {
        return allOpenReviews;
    }
    
    public void setAllOpenReviews(List<CoiDisclosure> allOpenReviews) {
        this.allOpenReviews = allOpenReviews;
    }
    
    public List<CoiDisclosure> getAllOpenReviewsWithoutFinEnts() {
        return filterByFinEnt(getAllOpenReviews(), false);
    }
    
    public List<CoiDisclosure> getAllOpenReviewsWithFinEnts() {
        return filterByFinEnt(getAllOpenReviews(), true);
    }
    
    public List<CoiDisclosure> getPendingReviews() {
        return pendingReviews;
    }
    
    public void setPendingReviews(List<CoiDisclosure> pendingReviews) {
        this.pendingReviews = pendingReviews;
    }
    
    public List<CoiDisclosure> getPendingReviewsWithoutFinEnts() {
        return filterByFinEnt(getPendingReviews(), false);
    }
    
    public List<CoiDisclosure> getPendingReviewsWithFinEnts() {
        return filterByFinEnt(getPendingReviews(), true);
    }

    public List<CoiDisclosure> getInProgressReviews() {
        return inProgressReviews;
    }

    public void setInProgressReviews(List<CoiDisclosure> inProgressReviews) {
        this.inProgressReviews = inProgressReviews;
    } 
    
    public List<CoiDisclosure> getInProgressReviewsWithoutFinEnts() {
        return filterByFinEnt(getInProgressReviews(), false);
    }
    
    public List<CoiDisclosure> getInProgressReviewsWithFinEnts() {
        return filterByFinEnt(getInProgressReviews(), true);
    }
}
