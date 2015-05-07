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
package org.kuali.kra.institutionalproposal.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class InstitutionalProposalSponsorAndProgramRuleImpl extends KcTransactionalDocumentRuleBase implements
        InstitutionalProposalSponsorAndProgramRule {


    public boolean processInstitutionalProposalSponsorAndProgramRules(
            InstitutionalProposalSponsorAndProgramRuleEvent institutionalProposalSponsorAndProgramRuleEvent) {
        return processCommonValidations(institutionalProposalSponsorAndProgramRuleEvent.getInstitutionalProposalForValidation());
    }
    
    
    /**
     * This method processes common validations for business rules
     * @return
     */
    public boolean processCommonValidations(InstitutionalProposal institutionalProposal) {
        boolean validCfdaNumber = validateCfdaNumber(institutionalProposal);
        
        boolean validSponsorCode = validateSponsorCodeExists(institutionalProposal.getSponsorCode());
        
        boolean validPrimeSponsorId = validatePrimeSponsorIdExists(institutionalProposal.getPrimeSponsorCode());
        
        boolean validSponsorDeadlineTime = validateSponsorDeadlineTime(institutionalProposal);
        
        return validCfdaNumber && validSponsorCode && validSponsorDeadlineTime ;
    }
    
    private boolean validateSponsorDeadlineTime(InstitutionalProposal institutionalProposal) {
        boolean valid = true;
        if(!(institutionalProposal.getDeadlineTime() == null)) {

            String formatTime = DateUtils.formatFrom12Or24Str(institutionalProposal.getDeadlineTime());
            if (!formatTime.equalsIgnoreCase(Constants.INVALID_TIME)) {
                institutionalProposal.setDeadlineTime(formatTime);
            } else {
                GlobalVariables.getMessageMap().putError("document.institutionalProposal.deadlineTime", KeyConstants.INVALID_DEADLINE_TIME);
                valid = false;
            }
            
        }
        return valid;
    }


    @SuppressWarnings("unchecked")
    private boolean validateSponsorCodeExists(String sponsorCode) {
        boolean valid = true;
        if(!(sponsorCode == null)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("sponsorCode", sponsorCode);
            BusinessObjectService businessObjectService =  KcServiceLocator.getService(BusinessObjectService.class);
            List<Sponsor> sponsors = (List<Sponsor>)businessObjectService.findMatching(Sponsor.class, fieldValues);
            if(sponsors.size() == 0) {
                this.reportError("document.institutionalProposalList[0].sponsorCode", KeyConstants.ERROR_INVALID_SPONSOR_CODE);
                valid = false;
            }
        }
       return valid;
        
    }
    
    @SuppressWarnings("unchecked")
    private boolean validatePrimeSponsorIdExists(String primeSponsorId) {
        boolean valid = true;
        if (!(primeSponsorId == null)) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("sponsorCode", primeSponsorId);
            BusinessObjectService businessObjectService =  KcServiceLocator.getService(BusinessObjectService.class);
            List<Sponsor> sponsors = (List<Sponsor>)businessObjectService.findMatching(Sponsor.class, fieldValues);
            if(sponsors.size() == 0) {
                this.reportError("document.institutionalProposalList[0].primeSponsorCode", KeyConstants.ERROR_INVALID_PRIME_SPONSOR_CODE);
                valid = false;
            }
        }
       return valid;
        
    }
    
    private boolean validateCfdaNumber(InstitutionalProposal institutionalProposal) {
        boolean valid = true;
        String regExpr = "(\\d{2})(\\.)(\\d{3})[a-zA-z]?";
        DataDictionaryService dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        if (StringUtils.isNotBlank(institutionalProposal.getCfdaNumber())
                && !(institutionalProposal.getCfdaNumber().matches(regExpr))
                && GlobalVariables.getMessageMap().getMessages("document.institutionalProposalList[0].cfdaNumber") == null) {
            this.reportError("document.institutionalProposal.cfdaNumber", RiceKeyConstants.ERROR_INVALID_FORMAT, new String[] {
                    dataDictionaryService.getAttributeErrorLabel(InstitutionalProposal.class, "cfdaNumber"),
                    institutionalProposal.getCfdaNumber() });
            valid = false;
         }
        return valid;
    }

}
