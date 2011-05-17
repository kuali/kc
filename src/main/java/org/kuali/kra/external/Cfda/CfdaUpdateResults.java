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
package org.kuali.kra.external.Cfda;

public class CfdaUpdateResults {
    
    private int numberOfRecordsRetrievedFromWebSite;
    private int numberOfRecordsInKcDatabase;
    private int numberOfRecordsNotUpdatedForHistoricalPurposes;
    private int numberOfRecordsDeactivatedBecauseNoLongerOnWebSite;
    private int numberOfRecordsReActivated;
    private int numberOfRecordsNotUpdatedBecauseManual;
    private int numberOfRecordsUpdatedBecauseAutomatic;
    private int numberOfRecordsNewlyAddedFromWebSite;
    private String message;
    
    public CfdaUpdateResults() {
        setNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite(0);
        setNumberOfRecordsInKcDatabase(0);
        setNumberOfRecordsNewlyAddedFromWebSite(0);
        setNumberOfRecordsNotUpdatedBecauseManual(0);
        setNumberOfRecordsReActivated(0);
        setNumberOfRecordsRetrievedFromWebSite(0);
        setNumberOfRecordsUpdatedBecauseAutomatic(0);
        setNumberOfRecordsNotUpdatedForHistoricalPurposes(0);
    }
    
    // NumberOfRecordsRetrievedFromWebsite
    public void increaseNumberOfRecordsRetrievedFromWebsite(int value) {
        setNumberOfRecordsRetrievedFromWebSite(getNumberOfRecordsRetrievedFromWebSite() +  value);
    }
    
    public int getNumberOfRecordsRetrievedFromWebSite() {
        return numberOfRecordsRetrievedFromWebSite;
    }
    public void setNumberOfRecordsRetrievedFromWebSite(int numberOfRecordsRetrievedFromWebSite) {
        this.numberOfRecordsRetrievedFromWebSite = numberOfRecordsRetrievedFromWebSite;
    }
    
    // NumberOfRecordsInKcDatabase
    public void increaseNumberOfRecordsInKcDatabase(int value) {
        setNumberOfRecordsInKcDatabase(getNumberOfRecordsInKcDatabase() + value);
    }
    
    public int getNumberOfRecordsInKcDatabase() {
        return numberOfRecordsInKcDatabase;
    }
    public void setNumberOfRecordsInKcDatabase(int numberOfRecordsInKcDatabase) {
        this.numberOfRecordsInKcDatabase = numberOfRecordsInKcDatabase;
    }
    
    // NumberOfRecordsNotUpdatedForHistoricalPurposes
    public void increaseNumberOfRecordsNotUpdatedForHistoricalPurposes(int value) {
        setNumberOfRecordsNotUpdatedForHistoricalPurposes(getNumberOfRecordsNotUpdatedForHistoricalPurposes() + value);
    }
    
    public int getNumberOfRecordsNotUpdatedForHistoricalPurposes() {
        return numberOfRecordsNotUpdatedForHistoricalPurposes;
    }
    public void setNumberOfRecordsNotUpdatedForHistoricalPurposes(int numberOfRecordsNotUpdatedForHistoricalPurposes) {
        this.numberOfRecordsNotUpdatedForHistoricalPurposes = numberOfRecordsNotUpdatedForHistoricalPurposes;
    }
    
    // NumberOfRecordsDeactivatedBecauseNoLongerOnWebSite
    public void increaseNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite(int value) {
        setNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite(getNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite() + value);
    }
    public int getNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite() {
        return numberOfRecordsDeactivatedBecauseNoLongerOnWebSite;
    }
    public void setNumberOfRecordsDeactivatedBecauseNoLongerOnWebSite(int numberOfRecordsDeactivatedBecauseNoLongerOnWebSite) {
        this.numberOfRecordsDeactivatedBecauseNoLongerOnWebSite = numberOfRecordsDeactivatedBecauseNoLongerOnWebSite;
    }
    
    // NumberOfRecordsReActivated
    public void increaseNumberOfRecordsReActivated(int value) {
        setNumberOfRecordsReActivated(getNumberOfRecordsReActivated() + value);
    }
    public int getNumberOfRecordsReActivated() {
        return numberOfRecordsReActivated;
    }
    public void setNumberOfRecordsReActivated(int numberOfRecordsReActivated) {
        this.numberOfRecordsReActivated = numberOfRecordsReActivated;
    }
    
    // NumberOfRecordsNotUpdatedBecauseManual
    public void increaseNumberOfRecordsNotUpdatedBecauseManual(int value) {
        setNumberOfRecordsNotUpdatedBecauseManual(getNumberOfRecordsNotUpdatedBecauseManual() + value);
    }
    public int getNumberOfRecordsNotUpdatedBecauseManual() {
        return numberOfRecordsNotUpdatedBecauseManual;
    }
    public void setNumberOfRecordsNotUpdatedBecauseManual(int numberOfRecordsNotUpdatedBecauseManual) {
        this.numberOfRecordsNotUpdatedBecauseManual = numberOfRecordsNotUpdatedBecauseManual;
    }
    
    // NumberOfRecordsUpdatedBecauseAutomatic
    public void increaseNumberOfRecordsUpdatedBecauseAutomatic(int value) {
        setNumberOfRecordsUpdatedBecauseAutomatic(getNumberOfRecordsUpdatedBecauseAutomatic() + value);
    }
    public int getNumberOfRecordsUpdatedBecauseAutomatic() {
        return numberOfRecordsUpdatedBecauseAutomatic;
    }
    public void setNumberOfRecordsUpdatedBecauseAutomatic(int numberOfRecordsUpdatedBecauseAutomatic) {
        this.numberOfRecordsUpdatedBecauseAutomatic = numberOfRecordsUpdatedBecauseAutomatic;
    }
    
    // NumberOfRecordsNewlyAddedFromWebSite
    public void increaseNumberOfRecordsNewlyAddedFromWebSite(int value) {
        setNumberOfRecordsNewlyAddedFromWebSite(getNumberOfRecordsNewlyAddedFromWebSite() + value);
    }
    public int getNumberOfRecordsNewlyAddedFromWebSite() {
        return numberOfRecordsNewlyAddedFromWebSite;
    }
    public void setNumberOfRecordsNewlyAddedFromWebSite(int numberOfRecordsNewlyAddedFromWebSite) {
        this.numberOfRecordsNewlyAddedFromWebSite = numberOfRecordsNewlyAddedFromWebSite;
    }
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
