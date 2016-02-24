/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.kra.coi.personfinancialentity;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.api.sponsor.SponsorService;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * This class is rule for save FE
 */
public class SaveFinancialEntityRule extends KcTransactionalDocumentRuleBase implements KcBusinessRule<SaveFinancialEntityEvent> {
    
    private static final String SPONSOR_CODE = "sponsorCode";
    private SponsorService sponsorService;
    @Override
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
            Sponsor sp = this.getBusinessObjectService().findByPrimaryKey(Sponsor.class, fieldValues);
            if (!this.getSponsorService().isValidSponsor(sp)) {
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

    public SponsorService getSponsorService() {
        if (sponsorService == null) {
            sponsorService = KcServiceLocator.getService(SponsorService.class);
        }
        return sponsorService;
    }

    public void setSponsorService(SponsorService sponsorService) {
        this.sponsorService = sponsorService;
    }

}
