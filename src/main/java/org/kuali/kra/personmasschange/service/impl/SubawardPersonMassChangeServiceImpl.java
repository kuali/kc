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
package org.kuali.kra.personmasschange.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.SubawardPersonMassChangeService;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardContact;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on SubAwards.
 */
public class SubawardPersonMassChangeServiceImpl implements SubawardPersonMassChangeService {
    
    private static final String SUBAWARD_FIELD = "document.personMassChange.subawardPersonMassChange.";
    
    private static final String SUBAWARD_ID = "subAwardId";
    private static final String SEQUENCE_NUMBER = "sequenceNumber";
    private static final String CONTACT_PERSON = "contactPerson";
    private static final String REQUISITIONER = "requisitioner";
    
    private static final String SUBAWARD_CONTACT_PERSON_FIELD = SUBAWARD_FIELD + CONTACT_PERSON;
    private static final String SUBAWARD_REQUISITIONER_FIELD = SUBAWARD_FIELD + REQUISITIONER;
    
    private static final String SUBAWARD = "subaward";
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    private BusinessObjectService businessObjectService;

    @Override
    public List<SubAward> getSubawardChangeCandidates(PersonMassChange personMassChange) {
        List<SubAward> subawardChangeCandidates = new ArrayList<SubAward>();
        
        List<SubAward> subawards = new ArrayList<SubAward>();
        if (personMassChange.getSubawardPersonMassChange().isContactPerson() || personMassChange.getSubawardPersonMassChange().isRequisitioner()) {
            subawards.addAll(getSubawards(personMassChange));
        }

        for (SubAward subaward : subawards) {
            if (isSubawardChangeCandidate(personMassChange, subaward)) {
                subawardChangeCandidates.add(subaward);
            }
        }
        
        return subawardChangeCandidates;
    }
    
    private List<SubAward> getSubawards(PersonMassChange personMassChange) {
        List<SubAward> subawardChangeCandidates = new ArrayList<SubAward>();
        
        Collection<SubAward> subawards = getBusinessObjectService().findAll(SubAward.class);

        if (personMassChange.isChangeAllSequences()) {
            subawardChangeCandidates.addAll(subawards);
        } else {
            subawardChangeCandidates.addAll(getLatestSubawards(subawards));
        }
        
        return subawardChangeCandidates;
    }
    
    private List<SubAward> getLatestSubawards(Collection<SubAward> subawards) {
        List<SubAward> latestSubawards = new ArrayList<SubAward>();
        
        for (String uniqueSubawardId : getUniqueSubawardIds(subawards)) {
            Map<String, String> fieldValues = new HashMap<String, String>();
            fieldValues.put(SUBAWARD_ID, uniqueSubawardId);
            Collection<SubAward> uniqueSubawards = getBusinessObjectService().findMatchingOrderBy(SubAward.class, fieldValues, SEQUENCE_NUMBER, false);
            if (!uniqueSubawards.isEmpty()) {
                latestSubawards.add((SubAward) CollectionUtils.get(uniqueSubawards, 0));
            }
        }
        
        return latestSubawards;
    }
    
    private Set<String> getUniqueSubawardIds(Collection<SubAward> subawards) {
        Set<String> uniqueSubawardIds = new HashSet<String>();
        
        for (SubAward subaward : subawards) {
            uniqueSubawardIds.add(String.valueOf(subaward.getSubAwardId()));
        }
        
        return uniqueSubawardIds;
    }
    
    private boolean isSubawardChangeCandidate(PersonMassChange personMassChange, SubAward subaward) {
        boolean isSubawardChangeCandidate = false;
        
        List<SubAwardContact> subawardContacts = subaward.getSubAwardContactsList();
        
        String requisitionerId = subaward.getRequisitionerId();

        if (personMassChange.getSubawardPersonMassChange().isContactPerson()) {
            isSubawardChangeCandidate |= isSubawardContactChangeCandidate(personMassChange, subawardContacts);
        }
        if (personMassChange.getSubawardPersonMassChange().isRequisitioner()) {
            isSubawardChangeCandidate |= isSubawardRequisitionerChangeCandidate(personMassChange, requisitionerId);
        }
        
        return isSubawardChangeCandidate;
    }
    
    private boolean isSubawardContactChangeCandidate(PersonMassChange personMassChange, List<SubAwardContact> subawardContacts) {
        boolean isSubawardContactChangeCandidate = false;
        
        for (SubAwardContact subawardContact : subawardContacts) {
            if (isRolodexIdMassChange(personMassChange, subawardContact)) {
                isSubawardContactChangeCandidate = true;
                break;
            }
        }
        
        return isSubawardContactChangeCandidate;
    }
    
    private boolean isSubawardRequisitionerChangeCandidate(PersonMassChange personMassChange, String requisitionerId) {
        return isPersonIdMassChange(personMassChange, requisitionerId);
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<SubAward> subawardChangeCandidates) {
        for (SubAward subawardChangeCandidate : subawardChangeCandidates) {
            if (subawardChangeCandidate.getSubAwardDocument().getPessimisticLocks().isEmpty()) {
                performSubawardContactPersonMassChange(personMassChange, subawardChangeCandidate);
                performSubawardRequistionerPersonMassChange(personMassChange, subawardChangeCandidate);
            } else {
                if (personMassChange.getSubawardPersonMassChange().isContactPerson()) {
                    reportWarning(SUBAWARD_CONTACT_PERSON_FIELD, subawardChangeCandidate);
                } else if (personMassChange.getSubawardPersonMassChange().isRequisitioner()) {
                    reportWarning(SUBAWARD_REQUISITIONER_FIELD, subawardChangeCandidate);
                }
            }
        }
    }
    
    private void reportWarning(String propertyName, SubAward subaward) {
        String subawardCode = subaward.getSubAwardCode();
        errorReporter.reportWarning(propertyName, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, SUBAWARD, subawardCode);
    }
    
    private void performSubawardContactPersonMassChange(PersonMassChange personMassChange, SubAward subaward) {
        if (personMassChange.getSubawardPersonMassChange().isContactPerson()) {
            for (SubAwardContact subawardContact : subaward.getSubAwardContactsList()) {
                subawardContact.setRolodexId(Integer.valueOf(personMassChange.getReplacerRolodexId()));
                
                getBusinessObjectService().save(subawardContact);
            }
        }
    }
    
    private void performSubawardRequistionerPersonMassChange(PersonMassChange personMassChange, SubAward subaward) {
        if (personMassChange.getSubawardPersonMassChange().isRequisitioner()) {
            subaward.setRequisitionerId(personMassChange.getReplacerPersonId());
            
            getBusinessObjectService().save(subaward);
        }
    }
    
    private boolean isRolodexIdMassChange(PersonMassChange personMassChange, SubAwardContact subawardContact) {
        String replaceeRolodexId = personMassChange.getReplaceeRolodexId();
        return replaceeRolodexId != null && StringUtils.equals(replaceeRolodexId, String.valueOf(subawardContact.getRolodexId()));
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, String rolodexId) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && StringUtils.equals(replaceePersonId, rolodexId);
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }

}