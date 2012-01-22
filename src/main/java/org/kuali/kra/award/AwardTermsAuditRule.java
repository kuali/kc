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
package org.kuali.kra.award;

import java.util.ArrayList;
import java.util.List;

import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.bo.SponsorTermType;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

/**
 * This class processes audit rules (warnings) for the Terms Information related
 * data of the AwardDocument.
 */
public class AwardTermsAuditRule implements DocumentAuditRule {

    private static final int FIVE = 5;
    private static final int ZERO = 0;
    private static final String TERMS_AUDIT_ERRORS = "termsAuditErrors";
    private static final String DESCRIPTION = "description";
    private static final String SPONSOR_TERM_TYPE_CODE = "sponsorTermTypeCode";
    private static final String DOT = ".";
    private List<AuditError> auditErrors;
    private List<KeyValue> sponsorTermTypes;
    
    /**
     * @see org.kuali.core.rule.DocumentAuditRule#processRunAuditBusinessRules(org.kuali.core.document.Document)
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        AwardDocument awardDocument = (AwardDocument) document;
        auditErrors = new ArrayList<AuditError>();
        setSponsorTermTypes();
        List<AwardSponsorTerm> awardSponsorTerms = awardDocument.getAward().getAwardSponsorTerms();
        
        for(KeyValue sponsorTermType : sponsorTermTypes) {
            boolean sponsorTermTypeExists = isSponsorTermTypeInAwardSponsorTerms(sponsorTermType.getKey().toString(), awardSponsorTerms);
            if(!sponsorTermTypeExists){
                valid&=false;
                addErrorToAuditErrors(sponsorTermType.getValue());
            }
        }
        reportAndCreateAuditCluster();
        return valid;
    }
    
    /**
     * This method checks if sponsorTermTypes is null if true sets the list to a list of database return from getSponsorTermTypesFromDatabase()
     */
    @SuppressWarnings("unchecked")
    protected void setSponsorTermTypes() {
        if(this.sponsorTermTypes == null) {
            this.sponsorTermTypes = (ArrayList) getSponsorTermTypesFromDatabase();
        }
    }
    
    /**
     * This method sets sponsor term types to List<KeyValue> argument.
     * @param sponsorTermTypes
     */
    protected void setSponsorTermTypes(List<KeyValue> sponsorTermTypes) {
        this.sponsorTermTypes = sponsorTermTypes;
    }
    
    /**
     * This method tests if there is an Award Sponsor Term with Sponsor Term Type Code that is equal to the parameter "key".
     * @param key
     * @param awardSponsorTerms
     * @return
     */
    protected boolean isSponsorTermTypeInAwardSponsorTerms(String key, List<AwardSponsorTerm> awardSponsorTerms){
        boolean valid = false;
        for(AwardSponsorTerm awardSponsorTerm : awardSponsorTerms){
              if(awardSponsorTerm.getSponsorTermTypeCode().equals(key)){
                  valid = true;
                  break;
              }
        }
        return valid;
    }
    
    /**
     * This method creates and adds the Audit Error to the <code>{@link List<AuditError>}</code> auditError.
     * @param description
     */
    protected void addErrorToAuditErrors(String description) {
        String[] params = new String[FIVE];
        params[ZERO] = description;
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.MAPPING_AWARD_PAYMENT_REPORTS_AND_TERMS_PAGE);
        sb.append(DOT);
        sb.append(Constants.TERMS_PANEL_ANCHOR);
        auditErrors.add(new AuditError(Constants.TERMS_AUDIT_RULES_ERROR_KEY, 
                                        KeyConstants.ERROR_EMPTY_TERMS, 
                                        sb.toString(),
                                        params));   
    }
    
    /**
     * This method creates and adds the AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > ZERO) {
            KNSGlobalVariables.getAuditErrorMap().put(TERMS_AUDIT_ERRORS, new AuditCluster(Constants.TERMS_PANEL_NAME,
                                                                                          auditErrors, Constants.AUDIT_ERRORS));
        }
    }
    
    /**
     * 
     * This method gets the list of Sponsor Term Types from the database.
     * 
     * @param
     */
    protected List<KeyValue> getSponsorTermTypesFromDatabase(){
        PersistableBusinessObjectValuesFinder persistableBusinessObjectValuesFinder = new PersistableBusinessObjectValuesFinder();
        persistableBusinessObjectValuesFinder.setBusinessObjectClass(SponsorTermType.class);
        persistableBusinessObjectValuesFinder.setKeyAttributeName(SPONSOR_TERM_TYPE_CODE);
        persistableBusinessObjectValuesFinder.setLabelAttributeName(DESCRIPTION);
        return persistableBusinessObjectValuesFinder.getKeyValues();
    }

}
