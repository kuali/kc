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
package org.kuali.kra.negotiations.sorting;

import org.kuali.kra.negotiations.bo.NegotiationActivity;

import java.util.Comparator;

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
