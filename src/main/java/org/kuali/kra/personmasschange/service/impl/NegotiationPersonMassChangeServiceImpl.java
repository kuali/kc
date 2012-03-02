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
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.bo.KcPerson;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.NegotiationPersonMassChangeService;
import org.kuali.kra.rules.ErrorReporter;
import org.kuali.kra.service.KcPersonService;
import org.kuali.rice.krad.service.BusinessObjectService;

/**
 * Defines the service for performing a Person Mass Change on Negotiations.
 */
public class NegotiationPersonMassChangeServiceImpl implements NegotiationPersonMassChangeService {
    
    private static final String NEGOTIATION_FIELD = "document.personMassChange.negotiationPersonMassChange.";
    
    private static final String NEGOTIATIOR = "negotiator";
    
    private static final String NEGOTIATION_NEGOTIATOR_FIELD = NEGOTIATION_FIELD + NEGOTIATIOR;
    
    private static final String NEGOTIATION = "negotiation";
    
    private final ErrorReporter errorReporter = new ErrorReporter();
    
    private BusinessObjectService businessObjectService;
    private KcPersonService kcPersonService;

    @Override
    public List<Negotiation> getNegotiationChangeCandidates(PersonMassChange personMassChange) {
        List<Negotiation> negotiationChangeCandidates = new ArrayList<Negotiation>();
        
        List<Negotiation> negotiations = new ArrayList<Negotiation>();
        if (personMassChange.getNegotiationPersonMassChange().isNegotiator()) {
            negotiations.addAll(getNegotiations(personMassChange));
        }

        for (Negotiation negotiation : negotiations) {
            if (isNegotiationChangeCandidate(personMassChange, negotiation)) {
                negotiationChangeCandidates.add(negotiation);
            }
        }
        
        return negotiationChangeCandidates;
    }
    
    private List<Negotiation> getNegotiations(PersonMassChange personMassChange) {
        return new ArrayList<Negotiation>(getBusinessObjectService().findAll(Negotiation.class));
    }
    
    private boolean isNegotiationChangeCandidate(PersonMassChange personMassChange, Negotiation negotiation) {
        boolean isNegotiationChangeCandidate = false;

        if (personMassChange.getNegotiationPersonMassChange().isNegotiator()) {
            isNegotiationChangeCandidate |= isNegotiationNegotiatorChangeCandidate(personMassChange, negotiation);
        }
        
        return isNegotiationChangeCandidate;
    }
    
    private boolean isNegotiationNegotiatorChangeCandidate(PersonMassChange personMassChange, Negotiation negotiation) {
        return isPersonIdMassChange(personMassChange, negotiation.getNegotiatorPersonId());
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<Negotiation> negotiationChangeCandidates) {
        for (Negotiation negotiationChangeCandidate : negotiationChangeCandidates) {
            negotiationChangeCandidate.getNegotiationDocument().refreshPessimisticLocks();
            if (negotiationChangeCandidate.getNegotiationDocument().getPessimisticLocks().isEmpty()) {
                performNegotiationNegotiatorPersonMassChange(personMassChange, negotiationChangeCandidate);
            } else {
                if (personMassChange.getNegotiationPersonMassChange().isNegotiator()) {
                    reportWarning(NEGOTIATION_NEGOTIATOR_FIELD, negotiationChangeCandidate);
                }
            }
        }
    }
    
    private void performNegotiationNegotiatorPersonMassChange(PersonMassChange personMassChange, Negotiation negotiation) {
        if (personMassChange.getNegotiationPersonMassChange().isNegotiator()) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
            negotiation.setNegotiatorPersonId(person.getPersonId());
            negotiation.setNegotiatorName(person.getFullName());
            
            getBusinessObjectService().save(negotiation);
        }
    }
    
    private void reportWarning(String propertyName, Negotiation negotiation) {
        Long negotiationId = negotiation.getNegotiationId();
        errorReporter.reportWarning(propertyName, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, NEGOTIATION, String.valueOf(negotiationId));
    }
    
    private boolean isPersonIdMassChange(PersonMassChange personMassChange, String personId) {
        String replaceePersonId = personMassChange.getReplaceePersonId();
        return replaceePersonId != null && StringUtils.equals(replaceePersonId, personId);
    }
    
    public BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }
    
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }
    
    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

}