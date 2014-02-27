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
package org.kuali.kra.protocol;

public interface ProtocolJavaFunctionKrmsTermService {

    /**
     * 
     * This method returns true always. This is used for building business rule for any protocols. 
     * @return
     */
    public Boolean allProtocols();
    /**
     * 
     * This method check if the protocol is an amendment
     * @param protocol
     * @return
     */
    public Boolean isProtocolAmendment(ProtocolBase protocol);
    
    /**
     * 
     * This method check if the protocol is an renewal
     * @param protocol
     * @return
     */
    public Boolean isProtocolRenewal(ProtocolBase protocol);
    
    /**
     * 
     * This method check if the lead unit for the protocol is below a specified unit in the hierarchy
     * @param protocol
     * @return
     */
    public Boolean isProtocolLeadUnitBelow(ProtocolBase protocol,String leadUnit);
    
    /**
     * 
     * This method checks if the principal investigator is not a faculty member.
     * @param protocol
     * @return
     */
    public Boolean isPINotFaculty(ProtocolBase protocol);
    
    /**
     * 
     * This method check if the protocol contains a specified special review type.
     * @param protocol
     * @return
     */
    public Boolean isSpecialReviewExists(ProtocolBase protocol);
    
    /**
     * 
     * This method check if the protocol organizations contains a specified organization.
     * @param protocol
     * @param organization id
     * @return
     */
    public Boolean hasProtocolContainsOrganization(ProtocolBase protocol,String organizationId);
    
    /**
     * 
     * This method check if all persons has completed training
     * @param protocol
     * @return
     */
    public Boolean hasAllPersonsCompletedTraining(ProtocolBase protocol);
    
    /**
     * 
     * This method check if the protocol funding source sponsor contains a specified sponsor.
     * @param protocol
     * @param sponsor
     * @return
     */
    public Boolean hasFundingSourceContainsSponsor(ProtocolBase protocol,String sponsor);
    
    /**
     * 
     * This method check if the protocol funding source units contains a specified unit.
     * @param protocol
     * @param unitNumber
     * @return
     */
    public Boolean hasFundingSourceContainsUnit(ProtocolBase protocol,String unitNumber);
    
    /**
     * 
     * This method check if the protocol areas of research contains a specified research area.
     * @param protocol
     * @param areaOfResearch
     * @return
     */
    public Boolean hasProtocolContainsAreaOfResearch(ProtocolBase protocol,String areaOfResearchCode);
    
    /**
     * 
     * This method check if the Principal Investigator is changed in amendment/renewal.
     * @param protocol
     * @return
     */
    public Boolean isPiChangedInAmendmentOrRenewal(ProtocolBase protocol);

    /**
     * 
     * This method check if the Organazation is changed in amendment/renewal.
     * @param protocol
     * @return
     */
    public Boolean isOrganizationChangedInAmendmentOrRenewal(ProtocolBase protocol);

    /**
     * 
     * This method check if the submit user is the PI of the protocol.
     * @param protocol
     * @param submittedUserId
     * @return
     */
    public Boolean isSubmitUserProtocolPi(ProtocolBase protocol,String userId);
   
    /**
     * 
     * This method check if the lead unit of the ProtocolBase belong to campus
     * @param protocol
     * @param campusCode
     * @return
     */
    public Boolean isLeadUnitProtocolCampus(ProtocolBase protocol,String campusCode);
    
    /**
     * 
     * This method check if the protocol is renewal with amendment
     * @param protocol
     * @return
     */
    public Boolean isProtocolRenewalWithAmendment(ProtocolBase protocol);

    /**
     * 
     * This method check if any of the protocol document type is the specified document type
     * @param protocol
     * @param documentTypeCode
     * @return
     */
    public Boolean hasProtocolContainsDocumentType(ProtocolBase protocol,String documentTypeCode);

    /**
     * 
     * This method check if the protocol is in the specified submission
     * @param protocol
     * @param submissionTypeCode
     * @return
     */
    public Boolean isProtocolInSubmission(ProtocolBase protocol,String submissionTypeCode);

    /**
     * 
     * This method check if the selected submission qualifier type is a specified type
     * @param protocol
     * @param submissionQualifierTypeCode
     * @return
     */
    public Boolean hasProtocolContainsNotifySubmissionQualifierType(ProtocolBase protocol,Integer submissionNumber,String submissionQualifierTypeCode);
    
    /**
     * 
     * This method check if the selected submission type is a specified type
     * @param protocol
     * @param submissionNumber
     * @param submissionTypeCode
     * @return
     */
    public Boolean hasSubmissionType(ProtocolBase protocol,Integer submissionNumber,String submissionTypeCode);
    
}
