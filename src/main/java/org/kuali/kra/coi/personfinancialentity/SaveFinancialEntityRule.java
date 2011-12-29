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
package org.kuali.kra.coi.personfinancialentity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.Sponsor;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.rule.BusinessRuleInterface;
import org.kuali.kra.rules.ResearchDocumentRuleBase;
import org.kuali.rice.kns.util.GlobalVariables;

/**
 * 
 * This class is rule for save FE
 */
public class SaveFinancialEntityRule extends ResearchDocumentRuleBase implements BusinessRuleInterface<SaveFinancialEntityEvent> {
    
    private static final String SPONSOR_CODE = "sponsorCode";
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.rule.BusinessRuleInterface#processRules(org.kuali.kra.rule.event.KraDocumentEventBaseExtension)
     */
    public boolean processRules(SaveFinancialEntityEvent event) {
        boolean isValid = checkValidSponsor(event);
        isValid &= checkUniqueEntityName(event);
        return isValid;
    }

    /*
     * validate if sponsorcode is valid
     */
    private boolean checkValidSponsor(SaveFinancialEntityEvent event) {
        boolean isValid = true;
        if(StringUtils.isNotBlank(event.getPersonFinIntDisclosure().getSponsorCode())) {
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put(SPONSOR_CODE, event.getPersonFinIntDisclosure().getSponsorCode());
            if(getBusinessObjectService().countMatching(Sponsor.class, fieldValues) == 0) {
                GlobalVariables.getMessageMap().addToErrorPath(event.getPropertyName());
                GlobalVariables.getMessageMap().putError(SPONSOR_CODE, KeyConstants.ERROR_INVALID_SPONSOR_CODE);
                isValid = false;
                GlobalVariables.getMessageMap().removeFromErrorPath(event.getPropertyName());
            }
        }

        return isValid;

    }
    
    /*
     * validate that entity name is unique.
     */
    private boolean checkUniqueEntityName(SaveFinancialEntityEvent event) {
        if (StringUtils.isNotBlank(event.getPersonFinIntDisclosure().getEntityName())) {
            String entityNumber = event.getPersonFinIntDisclosure().getEntityNumber();
            Map<String, Object> fieldValues = new HashMap<String, Object>();
            fieldValues.put("entityName", event.getPersonFinIntDisclosure().getEntityName());
            fieldValues.put("financialEntityReporterId", event.getPersonFinIntDisclosure().getFinancialEntityReporterId());
            List<PersonFinIntDisclosure> personFinIntDisclosures = (List<PersonFinIntDisclosure>) getBusinessObjectService()
                    .findMatching(PersonFinIntDisclosure.class, fieldValues);
            for (PersonFinIntDisclosure personFinIntDisclosure : personFinIntDisclosures) {
                if (!StringUtils.equalsIgnoreCase(entityNumber, personFinIntDisclosure.getEntityNumber())) {
                    boolean result = false;
                    for (FinancialEntityContactInfo oldFeci:personFinIntDisclosure.getFinEntityContactInfos()) {
                        for (FinancialEntityContactInfo newFeci: event.getPersonFinIntDisclosure().getFinEntityContactInfos()) {
                            if (newFeci.infoMatches(oldFeci)) {
                                GlobalVariables.getMessageMap().addToErrorPath(event.getPropertyName());
                                GlobalVariables.getMessageMap().putError("entityName", KeyConstants.ERROR_DUPLICATE_PROPERTY, 
                                        new String[] {"Entity Name and Contact Info"});
                                GlobalVariables.getMessageMap().removeFromErrorPath(event.getPropertyName());
                                return false;
                            }
                        }
                    }
                    if (result) {
                    }
                }
            }
        }

        return true;

    }

}
