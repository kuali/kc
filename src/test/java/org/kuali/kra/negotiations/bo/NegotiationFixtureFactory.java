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

public class NegotiationFixtureFactory {
    
    public static Negotiation createNegotiationFixture() {
        NegotiationStatus status = new NegotiationStatus();
        status.setId(1L);
        status.setActive(true);
        status.setCode("IP");
        status.setDescription("In Progress");
        
        NegotiationAgreementType agreementType = new NegotiationAgreementType();
        agreementType.setId(1L);
        agreementType.setCode("SRA");
        agreementType.setDescription("Standard Research Agreement");
        agreementType.setActive(true);

        NegotiationAssociationType associationType = new NegotiationAssociationType();
        associationType.setId(1L);
        associationType.setCode("NO");
        associationType.setDescription("None");
        associationType.setActive(true);
        
        Negotiation negotiation = new Negotiation();
        negotiation.setNegotiationStatus(status);
        negotiation.setNegotiationAgreementType(agreementType);
        negotiation.setNegotiationAssociationType(associationType);
        
        java.sql.Date testEndDate = new java.sql.Date(2011, 12, 31);
        java.sql.Date testStartDate = new java.sql.Date(2009, 12, 31);
        
        negotiation.setAnticipatedAwardDate(testEndDate);
        negotiation.setNegotiationEndDate(testEndDate);
        negotiation.setNegotiationStartDate(testStartDate);
        negotiation.setDocumentFolder("document folder");
        negotiation.setDocumentNumber("123321");
        negotiation.setNegotiatorUserName("quickstart");
        negotiation.setNegotiatorName("quickstart");

        return negotiation;
    }

}
