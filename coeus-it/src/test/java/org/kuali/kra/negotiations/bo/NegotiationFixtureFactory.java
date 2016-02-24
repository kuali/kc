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
