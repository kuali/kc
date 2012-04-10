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
package org.kuali.kra.iacuc;

import java.sql.Date;
import java.sql.Timestamp;

import org.kuali.kra.iacuc.personnel.IacucProtocolPersonnelService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.protocol.Protocol;
import org.kuali.rice.krad.service.SequenceAccessorService;

/**
 * 
 * This class is Protocol Business Object.
 */
public class IacucProtocol extends Protocol {
    
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7380281405644745576L;
    
    private Date applicationDate;
    private boolean isBillable;
    private String layStatement1; 
    private String layStatement2;
    
    
    private String overviewTimeline; 
    private String speciesStudyGroupIndicator; 
    private String alternativeSearchIndicator; 
    private String scientificJustifIndicator; 

    private Timestamp createTimestamp;
    private String createUser;
    IacucProtocolDocument iacucProtocolDocument;
    
    public IacucProtocol() { 
        // TODO : temporary only; remove this when protocol is ready
        initializaTestData();
    } 
    
    private void initializaTestData() {
        // TODO : this is just for plumbing work.  remove it when working on required fields tab.
        Long protNumber = KraServiceLocator.getService(SequenceAccessorService.class).getNextAvailableSequenceNumber("SEQ_PROTOCOL_ID");
        setProtocolNumber(protNumber.toString());
        setSequenceNumber(0);
        setProtocolStatusCode("100");
//        setProjectTypeCode("1");
        setApplicationDate(new Date(new java.util.Date().getTime()));
        setCreateTimestamp(new Timestamp(new java.util.Date().getTime()));
        setCreateUser("test");
        setScientificJustifIndicator("no");
        setSpecialReviewIndicator("no");
        setSpeciesStudyGroupIndicator("no");
        setKeyStudyPersonIndicator("no");
        setFundingSourceIndicator("no");
        setCorrespondentIndicator("no");
        setReferenceIndicator("no");
        setAlternativeSearchIndicator("no");
        
    }
    
    /*
    public String getLeadUnitName() {
        return leadUnitName;
    }

    public void setLeadUnitName(String leadUnitName) {
        this.leadUnitName = leadUnitName;
    }
    */
    

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public boolean getIsBillable() {
        return isBillable;
    }

    public void setIsBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }
    
    public String getLayStatement1() {
        return layStatement1;
    }

    public void setLayStatement1(String layStatement1) {
        this.layStatement1 = layStatement1;
    }

    public String getLayStatement2() {
        return layStatement2;
    }

    public void setLayStatement2(String layStatement2) {
        this.layStatement2 = layStatement2;
    }

    public String getOverviewTimeline() {
        return overviewTimeline;
    }

    public void setOverviewTimeline(String overviewTimeline) {
        this.overviewTimeline = overviewTimeline;
    }

    public String getSpeciesStudyGroupIndicator() {
        return speciesStudyGroupIndicator;
    }

    public void setSpeciesStudyGroupIndicator(String speciesStudyGroupIndicator) {
        this.speciesStudyGroupIndicator = speciesStudyGroupIndicator;
    }

    public String getAlternativeSearchIndicator() {
        return alternativeSearchIndicator;
    }

    public void setAlternativeSearchIndicator(String alternativeSearchIndicator) {
        this.alternativeSearchIndicator = alternativeSearchIndicator;
    }

    public String getScientificJustifIndicator() {
        return scientificJustifIndicator;
    }

    public void setScientificJustifIndicator(String scientificJustifIndicator) {
        this.scientificJustifIndicator = scientificJustifIndicator;
    }
    
    

    public void setIacucProtocolDocument(IacucProtocolDocument iacucProtocolDocument) {
        this.iacucProtocolDocument = iacucProtocolDocument;
    }

    public IacucProtocolDocument getIacucProtocolDocument() {
        return iacucProtocolDocument;
    }

    public void setBillable(boolean isBillable) {
        this.isBillable = isBillable;
    }

    public Timestamp getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Timestamp createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    

    @Override
    protected IacucProtocolPersonnelService getProtocolPersonnelServiceHook() {
        return (IacucProtocolPersonnelService)KraServiceLocator.getService("iacucProtocolPersonnelService");
    }

    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_IACUC;
    }

}
