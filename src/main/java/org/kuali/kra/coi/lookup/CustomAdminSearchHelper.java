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
