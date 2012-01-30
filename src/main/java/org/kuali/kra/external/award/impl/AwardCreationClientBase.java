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
package org.kuali.kra.external.award.impl;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kfs.integration.cg.dto.AwardCreationStatusDTO;
import org.kuali.kfs.integration.cg.dto.AwardParametersDTO;
import org.kuali.kfs.integration.cg.service.AwardCreationService;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.notesandattachments.notes.AwardNotepad;
import org.kuali.kra.external.award.AwardCreationClient;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kew.exception.WorkflowException;
import org.kuali.rice.kns.service.DocumentService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.ObjectUtils;

/**
 * This class is the implementation of the client that
 * connects to the financial web service that creates 
 * an award.
 * 
 */

public abstract class AwardCreationClientBase implements AwardCreationClient {

    private AwardParametersDTO awardParameters;
    private DocumentService documentService;
    
    private static final Log LOG = LogFactory.getLog(AwardCreationClientBase.class);
    protected static final QName SERVICE_NAME = new QName("KFS", "awardCreationServiceSOAP");
    private static final String ERROR_MESSAGE = "Cannot connect to the service. The service may be down, please try again later.";
    private static final String FINANCIAL_AWARD_ERROR = "error.award.createFinancialAward.error";
    protected abstract AwardCreationService getServiceHandle();
   
    
    /**
     * This method calls the web service on KFS to create a C&G award.
     * @see org.kuali.kra.external.award.AwardCreationClient#createAward(org.kuali.kra.award.home.Award)
     */
    public void createAward(Award award) throws DatatypeConfigurationException, WorkflowException {
        
        setAwardParameters(award);
        awardParameters = getAwardParameters();
        AwardCreationStatusDTO createAwardResult = null;
        
        try {
            AwardCreationService port = getServiceHandle();   
            LOG.info("Connecting to financial system...");
            createAwardResult = port.createAward(awardParameters);
        } catch (Exception e) {
            LOG.error(ERROR_MESSAGE + e.getMessage(), e);
            GlobalVariables.getMessageMap().putError(FINANCIAL_AWARD_ERROR, KeyConstants.FIN_SYS_SERVICE_CANNOT_CONNECT);
            //writeErrorToNotes(award, createAwardResult);
        }
            
        // If the award did not get created display the errors
        // the result should never be null if the client connects to the financial system.
        if (createAwardResult != null) {
            // if failure status
            if (!StringUtils.equalsIgnoreCase(createAwardResult.getStatus(), "success")) {
                String completeErrorMessage = "";
                List<String> errorMessages = createAwardResult.getErrorMessages();
                for (String errorMessage : errorMessages) {
                    completeErrorMessage += errorMessage;
                }
                GlobalVariables.getMessageMap().putError(FINANCIAL_AWARD_ERROR, KeyConstants.FIN_SYS_SERVICE_ERRORS, completeErrorMessage);
            } else {
                // if award created successfully, then update the award table with the document number and date
                // if there are error messages but the document was saved in KFS
               
                String financialAwardDocumentNumber = createAwardResult.getDocumentNumber();
                if (financialAwardDocumentNumber == null) {
                    GlobalVariables.getMessageMap().putError(FINANCIAL_AWARD_ERROR, KeyConstants.FIN_SYS_SERVICE_DOCUMENT_NUMBER_NULL);
                    LOG.warn("Document number returned from KFS award creation service is null.");
                    //writeErrorToNotes(award, createAwardResult);
                }
                // if it saved correctly with a document number, update that in the Award
                else {
                    award.setFinancialAwardDocumentNumber(financialAwardDocumentNumber);
                    Calendar calendar = Calendar.getInstance();
                    award.setFinancialAwardCreationDate(new Date(calendar.getTime().getTime()));
                    AwardDocument awardDocument = award.getAwardDocument();
                    documentService.saveDocument(awardDocument);
                }
                if (ObjectUtils.isNotNull(createAwardResult.getErrorMessages()) 
                        && !createAwardResult.getErrorMessages().isEmpty()) {
                    String completeErrorMessage = "";
                    List<String> errorMessages = createAwardResult.getErrorMessages();
                    for (String errorMessage : errorMessages) {
                        completeErrorMessage += errorMessage;
                    }
                    GlobalVariables.getMessageMap().putError(FINANCIAL_AWARD_ERROR, KeyConstants.FIN_SYS_SERVICE_SAVED_WITH_ERRORS, completeErrorMessage);
                }
            }  
        }
        //writeErrorToNotes(award, createAwardResult);
    }

    protected void writeErrorToNotes(Award award, AwardCreationStatusDTO createAwardResult) throws WorkflowException{
        AwardNotepad awardNotepad = new AwardNotepad();
        awardNotepad.setAward(award);
        awardNotepad.setAwardNumber(award.getAwardNumber());
        awardNotepad.setNoteTopic("Financial Award Creation Error");
        awardNotepad.setComments(getFormattedError(createAwardResult));
        
        // debug
        System.out.println(getFormattedError(createAwardResult));
        
        award.getAwardNotepads().add(awardNotepad);
        
        AwardDocument awardDocument = award.getAwardDocument();
        documentService.saveDocument(awardDocument);
    }
    
    private String getFormattedError(AwardCreationStatusDTO createAwardResult){
        StringBuilder sb = new StringBuilder();
        Set<String> errorKeys = GlobalVariables.getMessageMap().getErrorMessages().keySet();
        for(String key : errorKeys){
            sb.append(GlobalVariables.getMessageMap().getErrorMessages().get(key).toString() + "\n");
        }
        
        for(String error : createAwardResult.getErrorMessages()){
            sb.append(error + "\n");
        }
        return sb.toString();
    }
      
    /**
     * This method sets the necessary values in the AwardParametersDTO object to be sent 
     * across to the financial service.
     * @param award
     * @throws DatatypeConfigurationException
     */
    protected void setAwardParameters(Award award) throws DatatypeConfigurationException {
        awardParameters = new AwardParametersDTO();
        awardParameters.setAgencyNumber(award.getSponsorCode());
        
        String awardId = award.getAwardNumber();  // award Number shows up as award id on the award document.
        awardParameters.setAwardId(awardId);

        awardParameters.setAwardProjectTitle(award.getTitle());
        awardParameters.setAwardPurposeCode(award.getActivityTypeCode());
        awardParameters.setAwardStatusCode(award.getAwardStatus().getStatusCode());
        
        //todo make real
        //todo make real
        //todo make real
        //todo make real
        //todo make real
        //todo make real
        //todo make real
        //todo make real
        awardParameters.setPrincipalId("admin");
        
        Date projectEndDate = award.getProjectEndDate();
        GregorianCalendar dateExpiration = new GregorianCalendar();
        dateExpiration.setTime(projectEndDate);
        XMLGregorianCalendar gregorianDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateExpiration);
        awardParameters.setProjectEndDate(gregorianDate);
        
        Date projectStartDate = award.getAwardEffectiveDate();
        GregorianCalendar dateEffective = new GregorianCalendar();
        dateEffective.setTime(projectStartDate);
        gregorianDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateEffective);
        awardParameters.setProjectStartDate(gregorianDate);
        
        // proposals may not exist yet in KC, but need a date for KFS proposal.
        awardParameters.setProposalSubmissionDate(gregorianDate);
        
        String awardTypeCode =award.getAwardTypeCode()==null?"":award.getAwardTypeCode().toString();
        awardParameters.setProposalAwardTypeCode(awardTypeCode);
        
        awardParameters.setProposalPrimaryProjectDirectorId(award.getPrincipalInvestigator().getPersonId());

        awardParameters.setUnit(award.getUnitNumber().toString());
        awardParameters.setProposalDirectCostAmount(award.getObligatedTotalDirect().toString());
    }
   
    
    protected AwardParametersDTO getAwardParameters() {
        return awardParameters;
    }
    

    /**
     * Sets the documentService attribute value. Injected by Spring.
     * 
     * @param documentService The documentService to set.
     */
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }
}
