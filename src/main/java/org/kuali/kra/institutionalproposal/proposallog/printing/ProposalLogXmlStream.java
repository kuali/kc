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
package org.kuali.kra.institutionalproposal.proposallog.printing;

import java.util.LinkedHashMap;
import java.util.Map;

import noNamespace.ProposalLogDocument;
import noNamespace.ProposalLogLeadUnit;
import noNamespace.ProposalLogPrincipalInvestigator;
import noNamespace.ProposalLogProposalType;
import noNamespace.ProposalLogSponsor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xmlbeans.XmlObject;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;
import org.kuali.kra.document.ResearchDocumentBase;
import org.kuali.kra.institutionalproposal.proposallog.ProposalLog;
import org.kuali.kra.institutionalproposal.proposallog.service.ProposalLogPrintingService;
import org.kuali.kra.printing.PrintingException;
import org.kuali.kra.printing.xmlstream.XmlStream;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.krad.service.BusinessObjectService;


/**
 * This class will contain all common methods that can be used across Proposal
 * Development XML generator streams . All those report XML stream
 * implementations need to extend and use the functions defined in this class.
 * 
 */
public class ProposalLogXmlStream implements XmlStream {
	
	private final static Log LOG=LogFactory.getLog(ProposalLogXmlStream.class);
	private DateTimeService dateTimeService;
	private BusinessObjectService businessObjectService = null;

    /**
     * This method generates XML for Print Certification Report. It uses data
     * passed in {@link ResearchDocumentBase} for populating the XML nodes. The
     * XMl once generated is returned as {@link XmlObject}
     * 
     * @param object
     *            using which XML is generated
     * @param reportParameters
     *            parameters related to XML generation
     * @return {@link XmlObject} representing the XML
     * @throws PrintingException
     *             in case of any errors occur during XML generation.
     */
    public Map<String, XmlObject> generateXmlStream(
            KraPersistableBusinessObjectBase printableBusinessObject, Map<String, Object> reportParameters) {
        Map<String, XmlObject> xmlObjectList = new LinkedHashMap<String, XmlObject>();
        ProposalLog proposalLog = (ProposalLog)reportParameters.get(ProposalLogPrintingService.PROPOSAL_LOG_KEY);
        proposalLog.refreshNonUpdateableReferences();
        ProposalLogDocument proposalLogDocument = ProposalLogDocument.Factory.newInstance();
        ProposalLogDocument.ProposalLog printProposalLog = ProposalLogDocument.ProposalLog.Factory.newInstance();
        printProposalLog.setProposalNumber(proposalLog.getProposalNumber());
        printProposalLog.setProposalTitle(proposalLog.getTitle());
        printProposalLog.setStatus(proposalLog.getProposalLogStatus().getDescription());
        printProposalLog.setComments(proposalLog.getComments());
        printProposalLog.setUpdateUser(proposalLog.getUpdateUser());
        printProposalLog.setUpdateTimeStamp(dateTimeService.toDateString(proposalLog.getUpdateTimestamp()));
        printProposalLog.setPI(getPrincipalInvestigator(proposalLog));
        
        
        ProposalLogLeadUnit unit = ProposalLogLeadUnit.Factory.newInstance();
        unit.setUnitNumber(proposalLog.getLeadUnit());
        if (proposalLog.getUnit() != null) {
            unit.setUnitName(proposalLog.getUnit().getUnitName());
        }
     
        printProposalLog.setLeadUnit(unit);
        
        ProposalLogSponsor sponsor = ProposalLogSponsor.Factory.newInstance();
        sponsor.setSponsorCode(proposalLog.getSponsorCode());
        sponsor.setSponsorName(proposalLog.getSponsorName());
        printProposalLog.setSponsor(sponsor);
        
        ProposalLogProposalType type = ProposalLogProposalType.Factory.newInstance();
        type.setProposalTypeCode(proposalLog.getProposalTypeCode());
        if (proposalLog.getProposalType() != null) {
            type.setProposalTypeDesc(proposalLog.getProposalType().getDescription());
        }
        printProposalLog.setProposalType(type);
        
        proposalLogDocument.setProposalLog(printProposalLog);
        xmlObjectList.put("proposalLog", proposalLogDocument);
        return xmlObjectList;
    }
    
    private ProposalLogPrincipalInvestigator getPrincipalInvestigator(ProposalLog proposalLog) {
        ProposalLogPrincipalInvestigator pi = ProposalLogPrincipalInvestigator.Factory.newInstance();
        KcPerson person = proposalLog.getPerson();
        pi.setFirstName(person.getFirstName());
        pi.setMiddleName(person.getMiddleName());
        pi.setLastName(person.getLastName());
        pi.setFullName(person.getFullName());
        pi.setPersonID(person.getPersonId());
        return pi;
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    public void setBusinessObjectService(
            BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

}
