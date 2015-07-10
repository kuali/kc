/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award;

import org.kuali.coeus.common.framework.sponsor.term.SponsorTermType;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.award.home.AwardSponsorTerm;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.keyvalues.PersistableBusinessObjectValuesFinder;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

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
    
    @Override
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
     * This method sets sponsor term types to List&lt;KeyValue&gt; argument.
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
     * This method creates and adds the Audit Error to the <code>{@link List&lt;AuditError&gt;}</code> auditError.
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
            GlobalVariables.getAuditErrorMap().put(TERMS_AUDIT_ERRORS, new AuditCluster(Constants.TERMS_PANEL_NAME,
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
