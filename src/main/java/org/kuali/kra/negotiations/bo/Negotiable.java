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
package org.kuali.kra.negotiations.bo;

import java.util.List;

import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalType;

/**
 * 
 * This interface defines the methods a BO needs to implement in order to successfully associate with the negotiations module.
 */
public interface Negotiable {
    
    public String EMPTY_STRING = "";
    
    /**
     * Returns the negotiable's document id.
     * @return
     */
    String getAssociatedDocumentId();
    
    /**
     * 
     * This method returns the lead unit's number, if it exists, otherwise returns an empty string.
     * @return
     */
    String getLeadUnitNumber();
    
    /**
     * 
     * This method returns the lead unit's name, if it exists, otherwise returns an empty string.
     * @return
     */
    String getLeadUnitName();
    
    /**
     * 
     * This method returns the BO's title, if it exists, otherwise returns an empty string.
     * @return
     */
    String getTitle();
    
    /**
     * Returns the PI's name whether employee or non-employee.
     * @return
     */
    String getPiName();
    
    /**
     * 
     * This method returns the employee principle investigator's name, if it exists, otherwise returns an empty string.
     * @return
     */
    String getPiEmployeeName();
    
    /**
     * 
     * This method returns the the non-employee (Rolodex) Principle Ivestigator's name, if it exists, otherwise returns an empty string.
     * @return
     */
    String getPiNonEmployeeName();
    
    /**
     * 
     * This method returns the admin person's name, if it exists, otherwise returns an empty string.
     * @return
     */
    String getAdminPersonName();
    
    /**
     * 
     * This method returns the sponsor's code, if it exists, otherwise returns an empty string.
     * @return
     */
    String getSponsorCode();
    
    /**
     * 
     * This method returns the sponsor's name, if it exists, otherwise returns an empty string.
     * @return
     */
    String getSponsorName();
    
    /**
     * 
     * This method returns the prime sponsor's code, if it exists, otherwise returns an empty string.
     * @return
     */
    String getPrimeSponsorCode();
    
    /**
     * 
     * This method returns the prime sponsor's name, if it exists, otherwise returns an empty string.
     * @return
     */
    String getPrimeSponsorName();
    
    /**
     * 
     * This method returns the sponsor award number, if it exists, otherwise returns an empty string.
     * @return
     */
    String getSponsorAwardNumber();
    
    /**
     * 
     * This method returns the sub award organization name, if it exists, otherwise returns an empty string.
     * @return
     */
    String getSubAwardOrganizationName();
    
    /**
     * 
     * This method returns a list of KcPersons that include the PI, COI, and Key Personnel.
     * @return
     */
    List<NegotiationPersonDTO> getProjectPeople();
    
    /**
     * Get the proposal type code from this negotiable. Renamed to negotiableproposaltypecode as
     * PropertyUtils appears to get a descriptor for the interface instead of the BO itself in some
     * cases and this can cause the property to seem unwritable.
     * @return
     */
    String getNegotiableProposalTypeCode();
    
    ProposalType getNegotiableProposalType();
    
}