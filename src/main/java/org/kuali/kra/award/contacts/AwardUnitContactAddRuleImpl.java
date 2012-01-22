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
package org.kuali.kra.award.contacts;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.bo.UnitAdministratorType;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * This class implements the specified rule
 */
public class AwardUnitContactAddRuleImpl extends BaseAwardContactAddRule {
    public static final String AWARD_UNIT_CONTACT_LIST_ERROR_KEY = "unitContactsBean.unitContact.unitAdministratorTypeCode";
    public static final String ERROR_AWARD_UNIT_CONTACT_EXISTS = "error.awardUnitContact.person.exists";
    private static final String PERSON_ERROR_KEY = "unitContactsBean.newAwardContact.fullName";
    /**
     * @param event
     * @return
     */
    public boolean processAddAwardUnitContactBusinessRules(Award award, AwardUnitContact newUnitContact) {
        boolean valid = checkForSelectedContactAdministratorTypeCode(newUnitContact);
        valid &= checkForDuplicatePerson(award, newUnitContact);
        valid &= checkForSelectedPerson(newUnitContact);
        return  valid;
    }

    public boolean checkForSelectedContactAdministratorTypeCode(AwardUnitContact newContact) {
        AwardUnitContact awardUnitContact = (AwardUnitContact) newContact;
        boolean valid = awardUnitContact.getUnitAdministratorTypeCode() != null;

        if(!valid) {
            GlobalVariables.getMessageMap().putError(AWARD_UNIT_CONTACT_LIST_ERROR_KEY, ERROR_AWARD_CONTACT_ROLE_REQUIRED);
        }

        return valid;
    }
    
    private boolean checkForSelectedPerson(AwardUnitContact newContact) {
        boolean valid = true;

        if (StringUtils.isBlank(newContact.getPersonId())) {
            if (StringUtils.isBlank(newContact.getFullName())) {
                GlobalVariables.getMessageMap().putError(PERSON_ERROR_KEY, KeyConstants.ERROR_MISSING_UNITCONTACT_PERSON);
            }
            else {
                GlobalVariables.getMessageMap().putError(PERSON_ERROR_KEY, KeyConstants.ERROR_INVALID_UNITCONTACT_PERSON);
            }
            valid = false;
        }

        return valid;
    }

    boolean checkForDuplicatePerson(Award award, AwardUnitContact newUnitContact) {
        boolean valid = true;
        for(AwardUnitContact unitContact: award.getAwardUnitContacts()) {
            // equal, but not both are null
            valid = !(StringUtils.equals(unitContact.getPersonId(),newUnitContact.getPersonId())
                        && StringUtils.equals(unitContact.getUnitAdministratorTypeCode(),newUnitContact.getUnitAdministratorTypeCode()));
            if(!valid) {
                registerError(newUnitContact);
                break;
            }
        }
        
        return valid;
    }

    private void registerError(AwardUnitContact newUnitContact) {
        String roleDescription = getRoleDescription(newUnitContact);
        GlobalVariables.getMessageMap().putError(PERSON_ERROR_KEY, ERROR_AWARD_UNIT_CONTACT_EXISTS, 
                                                newUnitContact.getContact().getFullName(), roleDescription);
    }
    private String getRoleDescription(AwardUnitContact newUnitContact) {
        String roleDescription = "";
        BusinessObjectService boService = KraServiceLocator.getService(BusinessObjectService.class);
        UnitAdministratorType aType = boService.findBySinglePrimaryKey(UnitAdministratorType.class, newUnitContact.getUnitAdministratorTypeCode());
        if (aType != null) {
            roleDescription = aType.getDescription();
        }
        return roleDescription;
    }
}
