/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.institutionalproposal.rules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.RiceKeyConstants;

/**
 * This class...
 */
public class InstitutionalProposalSponsorAndProgramRuleImpl extends ResearchDocumentRuleBase implements
        InstitutionalProposalSponsorAndProgramRule {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4913188586827287608L;


    /**
     * @see org.kuali.kra.institutionalproposal.rules.InstitutionalProposalSponsorAndProgramRule#processInstitutionalProposalSponsorAndProgramRules(org.kuali.kra.institutionalproposal.rules.InstitutionalProposalAddUnrecoveredFandARuleEvent)
     */
    public boolean processInstitutionalProposalSponsorAndProgramRules(
            InstitutionalProposalSponsorAndProgramRuleEvent institutionalProposalSponsorAndProgramRuleEvent) {
        return processCommonValidations(institutionalProposalSponsorAndProgramRuleEvent.getInstitutionalProposalForValidation());
    }
    
    
    /**
     * This method processes common validations for business rules
     * @param event
     * @return
     */
    public boolean processCommonValidations(InstitutionalProposal institutionalProposal) {
        boolean validCfdaNumber = validateCfdaNumber(institutionalProposal);
        
        boolean validSponsorCode = validateSponsorCodeExists(institutionalProposal.getSponsorCode());
        
        return validCfdaNumber && validSponsorCode;
    }
    
    @SuppressWarnings("unchecked")
    private boolean validateSponsorCodeExists(String sponsorCode) {
        boolean valid = true;
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("sponsorCode", sponsorCode);
        BusinessObjectService businessObjectService =  KraServiceLocator.getService(BusinessObjectService.class);       
        List<Sponsor> sponsors = (List<Sponsor>)businessObjectService.findMatching(Sponsor.class, fieldValues);
        if(sponsors.size() == 0) {
            this.reportError("document.institutionalProposal.sponsorCode", KeyConstants.ERROR_INVALID_SPONSOR_CODE);
            valid = false;
        }
       return valid;
        
    }
    
    private boolean validateCfdaNumber(InstitutionalProposal institutionalProposal) {
        boolean valid = true;
        String regExpr = "(\\d{2})(\\.)(\\d{3})[a-zA-z]?";
        DataDictionaryService dataDictionaryService = KraServiceLocator.getService(DataDictionaryService.class);
        if (StringUtils.isNotBlank(institutionalProposal.getCfdaNumber())
                && !(institutionalProposal.getCfdaNumber().matches(regExpr))
                && GlobalVariables.getErrorMap().getMessages("document.institutionalProposalList[0].cfdaNumber") == null) {
            this.reportError("document.institutionalProposal.cfdaNumber", RiceKeyConstants.ERROR_INVALID_FORMAT, new String[] {
                    dataDictionaryService.getAttributeErrorLabel(InstitutionalProposal.class, "cfdaNumber"),
                    institutionalProposal.getCfdaNumber() });
            valid = false;
         }
        return valid;
    }

}
