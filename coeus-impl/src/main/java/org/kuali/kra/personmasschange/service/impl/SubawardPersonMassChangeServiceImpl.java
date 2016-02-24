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
package org.kuali.kra.personmasschange.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.SubawardPersonMassChangeService;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardContact;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Defines the service for performing a Person Mass Change on SubAwards.
 * 
 * Person roles that might be replaced are: Requisitioner, Contact.
 */
@Component("subawardPersonMassChangeService")
public class SubawardPersonMassChangeServiceImpl extends MassPersonChangeServiceBase implements SubawardPersonMassChangeService {
    
    private static final String SUBAWARD_CODE = "subAwardCode";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    
    private static final String SUBAWARD = "subaward";
    private static final String SUBAWARD_WARNINGS = "subawardWarnings";

    @Override
    public List<SubAward> getSubawardChangeCandidates(PersonMassChange personMassChange) {
        List<SubAward> subawardChangeCandidates = new ArrayList<SubAward>();
        
        List<SubAward> subawards = new ArrayList<SubAward>();
        if (personMassChange.getSubawardPersonMassChange().requiresChange()) {
            subawards.addAll(getSubawards(personMassChange));
        }

        for (SubAward subaward : subawards) {
            if (isSubawardChangeCandidate(personMassChange, subaward)) {
                subawardChangeCandidates.add(subaward);
            }
        }
        
        for (SubAward subawardChangeCandidate : subawardChangeCandidates) {
            if (!subawardChangeCandidate.getSubAwardDocument().getPessimisticLocks().isEmpty()) {
                reportSoftError(subawardChangeCandidate);
            }
        }
        
        return subawardChangeCandidates;
    }
    
    private List<SubAward> getSubawards(PersonMassChange personMassChange) {
        List<SubAward> subawards = new ArrayList<SubAward>();
        
        Collection<SubAward> allSubawards = getBusinessObjectService().findAll(SubAward.class);

        if (personMassChange.isChangeAllSequences()) {
            subawards.addAll(allSubawards);
        } else {
            subawards.addAll(getLatestSubawards(allSubawards));
        }
        
        return subawards;
    }
    
    private List<SubAward> getLatestSubawards(Collection<SubAward> subawards) {
        List<SubAward> latestSubawards = new ArrayList<SubAward>();
        
        for (String uniqueSubawardCode : getUniqueSubawardCodes(subawards)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(SUBAWARD_CODE, uniqueSubawardCode);
            Collection<SubAward> uniqueSubawards = getBusinessObjectService().findMatchingOrderBy(SubAward.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueSubawards.isEmpty()) {
                latestSubawards.add((SubAward) CollectionUtils.get(uniqueSubawards, 0));
            }
        }
        
        return latestSubawards;
    }
    
    private Set<String> getUniqueSubawardCodes(Collection<SubAward> subawards) {
        Set<String> uniqueSubawardIds = new HashSet<String>();
        
        for (SubAward subaward : subawards) {
            uniqueSubawardIds.add(subaward.getSubAwardCode());
        }
        
        return uniqueSubawardIds;
    }
    
    private boolean isSubawardChangeCandidate(PersonMassChange personMassChange, SubAward subaward) {
        boolean isSubawardChangeCandidate = false;
        
        String requisitionerId = subaward.getRequisitionerId();
        List<SubAwardContact> contacts = subaward.getSubAwardContactsList();

        if (personMassChange.getSubawardPersonMassChange().isRequisitioner()) {
            isSubawardChangeCandidate |= isRequisitionerChangeCandidate(personMassChange, requisitionerId);
        }
        if (personMassChange.getSubawardPersonMassChange().isContact()) {
            isSubawardChangeCandidate |= isContactCandidate(personMassChange, contacts);
        }
        
        return isSubawardChangeCandidate;
    }
    
    private boolean isRequisitionerChangeCandidate(PersonMassChange personMassChange, String requisitionerId) {
        return isPersonIdMassChange(personMassChange, requisitionerId);
    }
    
    private boolean isContactCandidate(PersonMassChange personMassChange, List<SubAwardContact> contacts) {
        boolean isContactChangeCandidate = false;
        
        for (SubAwardContact subawardContact : contacts) {
            if (isRolodexIdMassChange(personMassChange, subawardContact.getRolodexId())) {
                isContactChangeCandidate = true;
                break;
            }
        }
        
        return isContactChangeCandidate;
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<SubAward> subawardChangeCandidates) {
        for (SubAward subawardChangeCandidate : subawardChangeCandidates) {
            if (subawardChangeCandidate.getSubAwardDocument().getPessimisticLocks().isEmpty()) {
                performRequistionerPersonMassChange(personMassChange, subawardChangeCandidate);
                performContactMassChange(personMassChange, subawardChangeCandidate);
            }
        }
    }
    
    private void performRequistionerPersonMassChange(PersonMassChange personMassChange, SubAward subaward) {
        if (personMassChange.getSubawardPersonMassChange().isRequisitioner()) {
            subaward.setRequisitionerId(personMassChange.getReplacerPersonId());
            
            getBusinessObjectService().save(subaward);
        }
    }
    
    private void performContactMassChange(PersonMassChange personMassChange, SubAward subaward) {
        if (personMassChange.getSubawardPersonMassChange().isContact()) {
            for (SubAwardContact subawardContact : subaward.getSubAwardContactsList()) {
                if (isRolodexIdMassChange(personMassChange, subawardContact.getRolodexId())) {
                    subawardContact.setRolodexId(personMassChange.getReplacerRolodexId());
                    
                    getBusinessObjectService().save(subawardContact);
                }
            }
        }
    }
    
    private void reportSoftError(SubAward subaward) {
        String subawardCode = subaward.getSubAwardCode();
        errorReporter.reportSoftError(PMC_LOCKED_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, SUBAWARD, subawardCode);
    }

    @Override
    protected String getDocumentId(PersistableBusinessObject parent) {
        return ((SubAward) parent).getSubAwardCode();
    }

    @Override
    protected String getDocumentName() {
        return SUBAWARD;
    }

    @Override
    protected String getWarningKey() {
        return SUBAWARD_WARNINGS;
    }

}
