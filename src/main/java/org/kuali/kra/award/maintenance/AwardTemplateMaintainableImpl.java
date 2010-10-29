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
package org.kuali.kra.award.maintenance;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.AwardTemplate;
import org.kuali.kra.award.home.AwardTemplateReportTerm;
import org.kuali.kra.award.home.AwardTemplateReportTermRecipient;
import org.kuali.kra.award.home.ValidBasisMethodPayment;
import org.kuali.kra.award.paymentreports.ValidClassReportFrequency;
import org.kuali.kra.award.paymentreports.ValidFrequencyBase;
import org.kuali.kra.bo.Rolodex;
import org.kuali.kra.bo.SponsorTerm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.lookup.LookupUtils;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.util.ObjectUtils;
import org.kuali.rice.kns.util.TypedArrayList;

/**
 * This class is for adding validation rules to maintain Award Template
 */
public class AwardTemplateMaintainableImpl extends KraMaintainableImpl {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3368480537790330757L;
    
    private static final String PERSON_OBJECT_REFERENCE = "person";
    
    private static final String ERROR_KEY_PREFIX = "document.newMaintainableObject.add";
    
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(AwardTemplateMaintainableImpl.class); 

    
    public void addMultipleValueLookupResults(MaintenanceDocument document, String collectionName, Collection<PersistableBusinessObject> rawValues, boolean needsBlank, PersistableBusinessObject bo) {
        Collection maintCollection = (Collection) ObjectUtils.getPropertyValue(bo, collectionName);
        String docTypeName = document.getDocumentHeader().getWorkflowDocument().getDocumentType();
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        
        
        if( StringUtils.equals( collectionName, "templateTerms") ) {
            for (PersistableBusinessObject nextBo : rawValues) {
                SponsorTerm aTerm = (SponsorTerm)nextBo;
                aTerm.refreshNonUpdateableReferences();
            }
        }
        super.addMultipleValueLookupResults(document, collectionName, rawValues, needsBlank, bo);
        
    }
    
    /**
     * This method is for performing any new validations while adding new items to the list.
     */
    @Override
    public void addNewLineToCollection(String collectionName) {
        if (collectionName.equals("templateReportTerms")) {
            ErrorReporter errorReporter = new ErrorReporter();
            AwardTemplateReportTerm awardTemplateReportTerm = (AwardTemplateReportTerm)newCollectionLines.get(collectionName );
            if ( awardTemplateReportTerm != null ) {
                if(!isValid(awardTemplateReportTerm)){
                    reportReportTermError(errorReporter, awardTemplateReportTerm);
                }else{
                    super.addNewLineToCollection(collectionName);
                }
            }
        } else if (collectionName.endsWith("awardTemplateReportTermRecipients")) {
            addNewRecipientToCollection(collectionName);
        } else {
            super.addNewLineToCollection(collectionName);
        }
    }
   
    /**
     * This method is for add a new AwardTemplateReportTermRecipient.
     */
    public void addNewRecipientToCollection(String collectionName) {
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("addNewRecipientToCollection( " + collectionName + " )");
        }
        // get the new line from the map
        AwardTemplateReportTermRecipient addLine = (AwardTemplateReportTermRecipient) newCollectionLines.get(collectionName);
        ErrorReporter errorReporter = new ErrorReporter();
        if (addLine != null) {
            // mark the isNewCollectionRecord so the option to delete this line will be presented
            addLine.setNewCollectionRecord(true);
            addLine.setRolodexNameOrganization("");
            // parse contactTypeCodeAndRolodexId to get ContactTypeCode and RolodexId separately
            String contactTypeCodeAndRolodexIdString = addLine.getContactTypeCodeAndRolodexId();
            Integer rolodexIdInt = addLine.getRolodexId();
            if(StringUtils.isNotEmpty(contactTypeCodeAndRolodexIdString) && rolodexIdInt != null) {
                //add error only one can be selected
                addLine.setRolodexNameOrganization("");
                errorReporter.reportError(
                        ERROR_KEY_PREFIX + collectionName, 
                        KeyConstants.ERROR_CAN_NOT_SELECT_BOTH_FIELDS,
                        contactTypeCodeAndRolodexIdString, rolodexIdInt.toString());
                return;
            }
            if(StringUtils.isNotEmpty(contactTypeCodeAndRolodexIdString)) {
                int index1 = contactTypeCodeAndRolodexIdString.indexOf(Constants.AWARD_TEMP_RECPNT_CONTACT_TYPE_CODE_ROLODEX_ID_SEPARATOR);
                if (index1 > 0) {
                    String contactTypeCode = contactTypeCodeAndRolodexIdString.substring(0, index1);
                    Integer rolodexId = Integer.parseInt(contactTypeCodeAndRolodexIdString.substring(
                                                                index1 + Constants.AWARD_TEMP_RECPNT_CONTACT_TYPE_CODE_ROLODEX_ID_SEPARATOR.length(), 
                                                                contactTypeCodeAndRolodexIdString.length()));
                    addLine.setContactTypeCode(contactTypeCode);
                    addLine.setRolodexId(rolodexId);
                    addLine.setRolodexNameOrganization(this.rolodexNameAndOrganization(rolodexId));
                }
            } else if (rolodexIdInt != null) {
                addLine.setContactTypeCode("-1");  // use default contact type code
                addLine.setRolodexId(rolodexIdInt);
                addLine.setRolodexNameOrganization(this.rolodexNameAndOrganization(rolodexIdInt));
            } else { 
                // add error, one of the fields has to be selected
                addLine.setRolodexNameOrganization("");
                errorReporter.reportError(
                        ERROR_KEY_PREFIX + collectionName, 
                        KeyConstants.ERROR_ONE_FIELD_MUST_BE_SELECTED);
                return;
            }
            
            // get the collection from the business object
            Collection maintCollection = (Collection) ObjectUtils.getPropertyValue(getBusinessObject(), collectionName);
            
            if (maintCollection.size() > 0) {
                List<AwardTemplateReportTermRecipient> aList = new TypedArrayList(AwardTemplateReportTermRecipient.class);
                aList.addAll(maintCollection);
                Integer id = addLine.getRolodexId();
                for(int i = 0; i < aList.size(); i++ ){
                    AwardTemplateReportTermRecipient aRecipient = (AwardTemplateReportTermRecipient) aList.get(i);
                    if(aRecipient.getRolodexId().equals(id)) {
                        errorReporter.reportError(
                                ERROR_KEY_PREFIX + collectionName, 
                                KeyConstants.ERROR_DUPLICATE_ROLODEX_ID);
                        return; 
                    }
                }
            }
                       
            // add the line to the collection
            maintCollection.add( addLine );
            //refresh parent object since attributes could of changed prior to user clicking add
            String referencesToRefresh = LookupUtils.convertReferencesToSelectCollectionToString(getAllRefreshableReferences(getBusinessObject().getClass()));
            if (LOG.isInfoEnabled()) {
                LOG.info("References to refresh for adding line to collection " + collectionName + ": " + referencesToRefresh);
            }
            refreshReferences(referencesToRefresh);
        }
        
        initNewCollectionLine( collectionName );
        
    }
    
    public String rolodexNameAndOrganization(Integer rolodexId) {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Rolodex aRolodex = businessObjectService.findBySinglePrimaryKey(Rolodex.class, rolodexId);
        String rolocesNameAndOrganization = "";
        if ( aRolodex != null ) {
            rolocesNameAndOrganization = aRolodex.getFullName() + "/" + aRolodex.getOrganization();
        }
        return rolocesNameAndOrganization;
    }
    
    @Override
    public void prepareForSave() {
        AwardTemplate awardTemplate = (AwardTemplate)this.businessObject;
        if(!isValid(awardTemplate.getBasisOfPaymentCode(),awardTemplate.getMethodOfPaymentCode())){
            reportInvalidAwardBasisError(awardTemplate);
        }
        super.prepareForSave();
    }
    
    private void reportInvalidAwardBasisError(AwardTemplate awardTemplate) {
        ErrorReporter errorReporter = new ErrorReporter();
        awardTemplate.refreshNonUpdateableReferences();
        errorReporter.reportError("document.newMaintainableObject.basisOfPaymentCode", 
                        KeyConstants.INVALID_BASIS_PAYMENT,
                        new String[]{awardTemplate.getAwardBasisOfPayment()==null?"":
                                        awardTemplate.getAwardBasisOfPayment().getDescription()});
        errorReporter.reportError("document.newMaintainableObject.methodOfPaymentCode", 
                        KeyConstants.INVALID_METHOD_PAYMENT,
                        new String[]{awardTemplate.getAwardMethodOfPayment()==null?"":
                                        awardTemplate.getAwardMethodOfPayment().getDescription()});
    }
    private boolean isValid(String basisOfPaymentCode, String methodOfPaymentCode) {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> validBasisOfPaymentsParams = new HashMap<String, String>();
        validBasisOfPaymentsParams.put("basisOfPaymentCode", basisOfPaymentCode);
        validBasisOfPaymentsParams.put("methodOfPaymentCode", methodOfPaymentCode);
        Collection<ValidBasisMethodPayment> validBasisMethodPayments = businessObjectService.findMatching(ValidBasisMethodPayment.class, validBasisOfPaymentsParams);
        return !validBasisMethodPayments.isEmpty();
    }
    /**
     * This method is to report error on the GlobalError
     * @param errorReporter
     * @param awardTemplateReportTerm
     */
    private void reportReportTermError(ErrorReporter errorReporter, AwardTemplateReportTerm awardTemplateReportTerm) {
        awardTemplateReportTerm.refreshNonUpdateableReferences();
        errorReporter.reportError("document.newMaintainableObject.add.templateReportTerms", KeyConstants.INVALID_REPORT_FREQUENCY,
                new String[]{awardTemplateReportTerm.getReportClass().getDescription(),awardTemplateReportTerm.getReport().getDescription(),
                awardTemplateReportTerm.getFrequency().getDescription(),awardTemplateReportTerm.getFrequencyBase().getDescription()});
    }
    /**
     * 
     * This method is to check whether the selected values for ReportTerm 
     * @param awardTemplateReportTerm
     * @return
     */
    private boolean isValid(AwardTemplateReportTerm awardTemplateReportTerm) {
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, String> classReportFreqParams = new HashMap<String, String>();
        classReportFreqParams.put("reportClassCode", awardTemplateReportTerm.getReportClassCode());
        classReportFreqParams.put("reportCode", awardTemplateReportTerm.getReportCode());
        classReportFreqParams.put("frequencyCode", awardTemplateReportTerm.getFrequencyCode());
        Collection<ValidClassReportFrequency> coll = businessObjectService.findMatching(ValidClassReportFrequency.class, classReportFreqParams);
        Map<String, String> freqBaseParams = new HashMap<String, String>();
        freqBaseParams.put("frequencyBaseCode", awardTemplateReportTerm.getFrequencyBaseCode());
        freqBaseParams.put("frequencyCode", awardTemplateReportTerm.getFrequencyCode());
        Collection<ValidFrequencyBase> validFrequencyBases = businessObjectService.findMatching(ValidFrequencyBase.class, freqBaseParams);
        
        return !coll.isEmpty() && !validFrequencyBases.isEmpty();
    }
    
    /**
     * @see org.kuali.rice.kns.maintenance.Maintainable#refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void refresh(String refreshCaller, Map fieldValues, MaintenanceDocument document) {
        super.refresh(refreshCaller, fieldValues, document);
//        LOG.warn("refresh called.");
//        // If a person has been selected, lead unit should default to the person's home unit.
//        String referencesToRefresh = (String) fieldValues.get(KNSConstants.REFERENCES_TO_REFRESH);
//        
//        if (referencesToRefresh != null && referencesToRefresh.contains(PERSON_OBJECT_REFERENCE)) {
//            LOG.info( "*********" + referencesToRefresh );
//            ProposalLog proposalLOG = (ProposalLog) this.getBusinessObject();
//            if (proposalLog.getkcPerson() != null) {
//                proposalLog.setLeadUnit(proposalLog.getkcPerson().getContactOrganizationName());
//            }
//        }
    }
   
}
