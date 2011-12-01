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
package org.kuali.kra.subaward.subawardrule;

import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.bo.SubAwardAmountReleased;
import org.kuali.kra.subaward.bo.SubAwardCloseout;
import org.kuali.kra.subaward.bo.SubAwardContact;
import org.kuali.kra.subaward.bo.SubAwardFundingSource;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.rice.kns.document.Document;
import org.kuali.rice.kns.rule.BusinessRule;
import org.kuali.rice.kns.util.ErrorMap;
import org.kuali.rice.kns.util.GlobalVariables;

public class SubAwardDocumentRule extends ResearchDocumentRuleBase implements SubAwardRule,
                                                                                 SubAwardAmountInfoRule,
                                                                                 SubAwardAmountReleasedRule,
                                                                                 SubAwardContactRule,
                                                                                 SubAwardCloseoutRule,
                                                                                 SubAwardFundingSourceRule{
    
    private static final String STATUS_CODE = ".statusCode";
    private static final String SUBAWARD_TYPE_CODE = ".subAwardTypeCode";
    private static final String REQUISITIONER = ".requisitionerName";
    private static final String PURCHASE_ORDER_NUM= ".purchaseOrderNum";
    private static final String SUBCONTRACTOR_ID = ".organization.organizationName";
    private static final String NEW_SUBAWARD = "document.subAwardList[0]";
    private static final String SUBAWARD_START_DATE =".startDate";
    
    
    private static final String AMOUNT_INFO_EFFECTIVE_DATE = "newSubAwardAmountInfo.effectiveDate";
    private static final String AMOUNT_INFO_OBLIGATED_AMOUNT = "newSubAwardAmountInfo.obligatedChange";
    private static final String AMOUNT_INFO_ANTICIPATED_AMOUNT = "newSubAwardAmountInfo.anticipatedChange";

    private static final String AMOUNT_RELEASED_EFFECTIVE_DATE = "newSubAwardAmountReleased.effectiveDate";
    private static final String INVOICE_NUMBER = "newSubAwardAmountReleased.invoiceNumber";
    private static final String START_DATE = "newSubAwardAmountReleased.startDate";
    private static final String END_DATE = "newSubAwardAmountReleased.endDate";
    private static final String AMOUNT_RELEASED="newSubAwardAmountReleased.amountReleased";
    
    private static final String ROLODEX_ID="newSubAwardContact.rolodex.firstName";
    private static final String CONTACT_TYPE_CODE="newSubAwardContact.contactTypeCode";
    
    private static final String CLOSEOUT_TYPE_CODE="newSubAwardCloseout.closeoutTypeCode";
    
    private static final String AWARD_NUMBER="newSubAwardFundingSource.award.awardNumber";
    
    
    

    public boolean processAddSubAwardBusinessRules(SubAward subAward) {
 
        boolean rulePassed = true;        
        rulePassed &= processSaveSubAwardBusinessRules(subAward, NEW_SUBAWARD);        
        return rulePassed;
    }      
 
    protected boolean  processSaveSubAwardBusinessRules(SubAward subAward,String propertyPrefix){    
     
        boolean rulePassed = true;   
        
        if(subAward.getOrganizationId()==null ){ 
            rulePassed = false;            
            reportError(propertyPrefix+SUBCONTRACTOR_ID
                    , KeyConstants.ERROR_REQUIRED_SUBRECIPIENT_ID); 
        }   
        
        if(subAward.getStatusCode()==null ){ 
            rulePassed = false;            
            reportError(propertyPrefix+STATUS_CODE
                    , KeyConstants.ERROR_REQUIRED_STATUS);          
        }  
        if(subAward.getSubAwardTypeCode()==null ){ 
            rulePassed = false;            
            reportError(propertyPrefix+SUBAWARD_TYPE_CODE
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_TYPE);
        }  
        if(subAward.getRequisitionerId()==null ){ 
            rulePassed = false;            
            reportError(propertyPrefix+REQUISITIONER
                    , KeyConstants.ERROR_REQUIRED_REQUISITIONER); 
        }  
        if(subAward.getPurchaseOrderNum()==null ){
            rulePassed = false;
            
            reportError(propertyPrefix+PURCHASE_ORDER_NUM
                    , KeyConstants.ERROR_REQUIRED_PURCHASE_ORDER_NUM); 
        }      
        if(subAward.getStartDate()!=null && subAward.getEndDate()!=null){
            if(subAward.getStartDate().after(subAward.getEndDate())){
                rulePassed = false;
                reportError(propertyPrefix+SUBAWARD_START_DATE
                        , KeyConstants.SUBAWARD_ERROR_END_DATE_GREATER_THAN_START); 
            }
        }
        return rulePassed;
    }



    
    

    public boolean processAddSubAwardAmountInfoBusinessRules(SubAwardAmountInfo amountInfo) {
        boolean rulePassed = true; 
        
        rulePassed &= processSaveSubAwardAmountInfoBusinessRules(amountInfo);
        
        return rulePassed;
    }    
    
    protected boolean  processSaveSubAwardAmountInfoBusinessRules(SubAwardAmountInfo amountInfo){    
        
        boolean rulePassed = true;   
        
        if(amountInfo==null 
                || amountInfo.getEffectiveDate()==null){
            rulePassed = false;
            
            reportError(AMOUNT_INFO_EFFECTIVE_DATE
                    , KeyConstants.ERROR_REQUIRED_EFFECTIVE_DATE);            
            
        }  
        if(amountInfo!=null && amountInfo.getObligatedChange()!=null && amountInfo.getAnticipatedChange()!=null ){
            
            if(amountInfo.getObligatedChange().isGreaterThan(amountInfo.getAnticipatedChange())){
                
                rulePassed = false;
                
                reportError(AMOUNT_INFO_ANTICIPATED_AMOUNT
                        , KeyConstants.ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT);   
            }
            
        }
        if(amountInfo!=null && amountInfo.getObligatedChange()!=null){
            if(amountInfo.getObligatedChange().isNegative()){
                rulePassed = false;
                
                reportError(AMOUNT_INFO_OBLIGATED_AMOUNT
                        , KeyConstants.ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_NEGATIVE); 
            }
        }
        if(amountInfo!=null && amountInfo.getObligatedChange()!=null){
            if(amountInfo.getObligatedChange().isZero()){
                rulePassed = false;
                
                reportError(AMOUNT_INFO_OBLIGATED_AMOUNT
                        , KeyConstants.ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_ZERO); 
            }
        }
        if(amountInfo!=null && amountInfo.getAnticipatedChange()!=null){
            if(amountInfo.getAnticipatedChange().isNegative()){
                rulePassed = false;
                
                reportError(AMOUNT_INFO_ANTICIPATED_AMOUNT
                        , KeyConstants.ERROR_AMOUNT_INFO_ANTICIPATED_AMOUNT_NEGATIVE); 
            }
        }
        if(amountInfo!=null && amountInfo.getAnticipatedChange()!=null){
            if(amountInfo.getAnticipatedChange().isZero()){
                rulePassed = false;
                
                reportError(AMOUNT_INFO_ANTICIPATED_AMOUNT
                        , KeyConstants.ERROR_AMOUNT_INFO_ANTICIPATED_AMOUNT_ZERO); 
            }
        }
        return rulePassed;
    }
    
    
    
    
    
    

    public boolean processAddSubAwardAmountReleasedBusinessRules(SubAwardAmountReleased amountReleased) {
        
        boolean rulePassed = true;
        rulePassed &= processSaveSubAwardAmountReleasedBusinessRules(amountReleased);
        
        return rulePassed;
        
    }
    
    protected boolean  processSaveSubAwardAmountReleasedBusinessRules(SubAwardAmountReleased amountReleased){    
        
        boolean rulePassed = true;   
        
        if(amountReleased==null 
                || amountReleased.getInvoiceNumber()==null){
            rulePassed = false;            
            reportError(INVOICE_NUMBER
                    , KeyConstants.ERROR_REQUIRED_INVOICE_NUMBER);
        }  
        if(amountReleased==null 
                || amountReleased.getStartDate()==null){
            rulePassed = false;            
            reportError(START_DATE
                    , KeyConstants.ERROR_REQUIRED_AMOUNT_RELEASED_START_DATE);
        }  
        if(amountReleased==null 
                || amountReleased.getEndDate()==null){
            rulePassed = false;            
            reportError(END_DATE
                    , KeyConstants.ERROR_REQUIRED_AMOUNT_RELEASED_END_DATE);
        }  
        if(amountReleased==null 
                || amountReleased.getEffectiveDate()==null){
            rulePassed = false;            
            reportError(AMOUNT_RELEASED_EFFECTIVE_DATE
                    , KeyConstants.ERROR_REQUIRED_AMOUNT_RELEASED_EFFECTIVE_DATE);
        }  
        if(amountReleased==null 
                || amountReleased.getAmountReleased()==null){
            rulePassed = false;            
            reportError(AMOUNT_RELEASED
                    , KeyConstants.ERROR_REQUIRED_AMOUNT_RELEASED);
        }  
        if(amountReleased!=null && amountReleased.getStartDate()!=null && amountReleased.getEndDate()!=null){
            if(amountReleased.getStartDate().after(amountReleased.getEndDate())){
                rulePassed = false;            
                reportError(START_DATE
                        , KeyConstants.SUBAWARD_ERROR_END_DATE_GREATER_THAN_START);
            }
        }
        
        return rulePassed;
    }
    
    
    
    
    

    public boolean processAddSubAwardContactBusinessRules(SubAwardContact subAwardContact) {
        boolean rulePassed = true;
        rulePassed &= processSaveSubAwardContactBusinessRules(subAwardContact);
        
        return rulePassed;
    }
    protected boolean  processSaveSubAwardContactBusinessRules(SubAwardContact subAwardContact){ 
        
        boolean rulePassed = true;   
        
        if(subAwardContact==null 
                || subAwardContact.getRolodexId()==null){
            rulePassed = false;            
            reportError(ROLODEX_ID
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_CONTACT_ROLODEX_ID);
        }  
        if(subAwardContact==null 
                || subAwardContact.getContactTypeCode()==null){
            rulePassed = false;            
            reportError(CONTACT_TYPE_CODE
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_CONTACT_TYPE_CODE);
        }  
        return rulePassed;
    }
    
    
    
    
    
    

    public boolean processAddSubAwardCloseoutBusinessRules(SubAwardCloseout subAwardCloseout) {
        boolean rulePassed = true;
        rulePassed &= processSaveSubAwardCloseoutBusinessRules(subAwardCloseout);
        
        return rulePassed;
    }
    protected boolean  processSaveSubAwardCloseoutBusinessRules(SubAwardCloseout subAwardCloseout){
        boolean rulePassed = true;   
        
        if(subAwardCloseout==null 
                || subAwardCloseout.getCloseoutTypeCode()==null){
            rulePassed = false;            
            reportError(CLOSEOUT_TYPE_CODE
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_CLOSEOUT_TYPE_CODE);
        }  
        return rulePassed;
    }
    
    
    

    public boolean processAddSubAwardFundingSourceBusinessRules(SubAwardFundingSource subAwardFundingSource) {
        boolean rulePassed = true;
        rulePassed &= processSaveSubAwardFundingSourceBusinessRules(subAwardFundingSource);
        
        return rulePassed;
    }
    protected boolean processSaveSubAwardFundingSourceBusinessRules(SubAwardFundingSource subAwardFundingSource){
        boolean rulePassed = true;   
        
        if(subAwardFundingSource==null 
                || subAwardFundingSource.getAwardId()==null){
            rulePassed = false;            
            reportError(AWARD_NUMBER
                    , KeyConstants.ERROR_REQUIRED_SUBAWARD_FUNDING_SOURCE_AWARD_NUMBER);
        }  
        return rulePassed;
    }
    /**
     * @see org.kuali.rice.kns.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.rice.kns.document.Document)
     */
    @Override
    public boolean processRunAuditBusinessRules(Document document){
        boolean retval = true;
        retval &= new SubAwardAuditRule().processRunAuditBusinessRules(document);
        return retval;
    }
}
