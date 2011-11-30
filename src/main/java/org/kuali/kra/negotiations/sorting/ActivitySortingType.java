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
package org.kuali.kra.negotiations.sorting;

import java.util.Comparator;

import org.kuali.kra.negotiations.bo.NegotiationActivity;

/**
 * Activity Sorting Enum for displaying and choosing the correct comparator.
 */
public enum ActivitySortingType {
    @SuppressWarnings("unchecked")
    ST ("Activity Start Date, Activity Type", 
            new MultiComparator<NegotiationActivity>(new Comparator[]{new StartDateComparator(), new ActivityTypeComparator()})),
    @SuppressWarnings("unchecked")
    UT ("Last Update, Activity Type", 
            new MultiComparator<NegotiationActivity>(new Comparator[]{new LastUpdateComparator(), new ActivityTypeComparator()})),
    @SuppressWarnings("unchecked")
    UU ("Last Update By, Last Update", 
            new MultiComparator<NegotiationActivity>(new Comparator[]{new LastUpdateByComparator(), new LastUpdateComparator()})),
    @SuppressWarnings("unchecked")
    TU ("Activity Type, Last Update",
            new MultiComparator<NegotiationActivity>(new Comparator[]{new ActivityTypeComparator(), new LastUpdateComparator()})),
    L ("Location", new LocationComparator());
    
    private String desc;
    private Comparator<NegotiationActivity> comparator;
    
    private ActivitySortingType(String desc, Comparator<NegotiationActivity> comparator) {
        this.desc = desc;
        this.comparator = comparator;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public Comparator<NegotiationActivity> getComparator() {
        return comparator;
    }
}

class StartDateComparator implements Comparator<NegotiationActivity> {
    @Override
    public int compare(NegotiationActivity o1, NegotiationActivity o2) {
        return o1.getStartDate().compareTo(o2.getStartDate());
    }
}
class ActivityTypeComparator implements Comparator<NegotiationActivity> {
    @Override
    public int compare(NegotiationActivity o1, NegotiationActivity o2) {
        return o1.getActivityType().getDescription().compareTo(o2.getActivityType().getDescription());
    } 
}
class LastUpdateComparator implements Comparator<NegotiationActivity> {
    @Override
    public int compare(NegotiationActivity o1, NegotiationActivity o2) {
        return o1.getLastModifiedDate().compareTo(o2.getLastModifiedDate());
    } 
}
class LastUpdateByComparator implements Comparator<NegotiationActivity> {
    @Override
    public int compare(NegotiationActivity o1, NegotiationActivity o2) {
        return o1.getLastModifiedUser().getUserName().compareTo(o2.getLastModifiedUser().getUserName());
    } 
}
class LocationComparator implements Comparator<NegotiationActivity> {
    @Override
    public int compare(NegotiationActivity o1, NegotiationActivity o2) {
        return o1.getLocation().getDescription().compareTo(o2.getLocation().getDescription());
    } 
}