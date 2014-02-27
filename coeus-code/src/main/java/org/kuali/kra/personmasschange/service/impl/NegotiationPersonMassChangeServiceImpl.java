/*
 * Copyright 2005-2014 The Kuali Foundation
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

import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.negotiations.bo.Negotiation;
import org.kuali.kra.personmasschange.bo.PersonMassChange;
import org.kuali.kra.personmasschange.service.NegotiationPersonMassChangeService;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the service for performing a Person Mass Change on Negotiations.
 * 
 * Person roles that might be replaced are: Negotiator.
 */
@Component("negotiationPersonMassChangeService")
public class NegotiationPersonMassChangeServiceImpl extends MassPersonChangeServiceBase implements NegotiationPersonMassChangeService {
    
    private static final String NEGOTIATION = "negotiation";
    private static final String NEGOTIATION_WARNINGS = "negotiationWarnings";

    @Override
    public List<Negotiation> getNegotiationChangeCandidates(PersonMassChange personMassChange) {
        List<Negotiation> negotiationChangeCandidates = new ArrayList<Negotiation>();
        
        List<Negotiation> negotiations = new ArrayList<Negotiation>();
        if (personMassChange.getNegotiationPersonMassChange().requiresChange()) {
            negotiations.addAll(getNegotiations(personMassChange));
        }

        for (Negotiation negotiation : negotiations) {
            if (isNegotiationChangeCandidate(personMassChange, negotiation)) {
                negotiationChangeCandidates.add(negotiation);
            }
        }
        
        for (Negotiation negotiationChangeCandidate : negotiationChangeCandidates) {
            if (!negotiationChangeCandidate.getDocument().getPessimisticLocks().isEmpty()) {
                reportSoftError(negotiationChangeCandidate);
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
            isNegotiationChangeCandidate |= isNegotiatorChangeCandidate(personMassChange, negotiation);
        }
        
        return isNegotiationChangeCandidate;
    }
    
    private boolean isNegotiatorChangeCandidate(PersonMassChange personMassChange, Negotiation negotiation) {
        return isPersonIdMassChange(personMassChange, negotiation.getNegotiatorPersonId());
    }

    @Override
    public void performPersonMassChange(PersonMassChange personMassChange, List<Negotiation> negotiationChangeCandidates) {
        for (Negotiation negotiationChangeCandidate : negotiationChangeCandidates) {
            if (negotiationChangeCandidate.getDocument().getPessimisticLocks().isEmpty()) {
                performNegotiatorPersonMassChange(personMassChange, negotiationChangeCandidate);
            }
        }
    }
    
    private void performNegotiatorPersonMassChange(PersonMassChange personMassChange, Negotiation negotiation) {
        if (personMassChange.getNegotiationPersonMassChange().isNegotiator()) {
            KcPerson kcPerson = getKcPersonService().getKcPersonByPersonId(personMassChange.getReplacerPersonId());
            negotiation.setNegotiatorPersonId(kcPerson.getPersonId());
            negotiation.setNegotiatorName(kcPerson.getFullName());
            
            getBusinessObjectService().save(negotiation);
        }
    }
        
    private void reportSoftError(Negotiation negotiation) {
        Long negotiationId = negotiation.getNegotiationId();
        errorReporter.reportSoftError(PMC_LOCKED_FIELD, KeyConstants.ERROR_PERSON_MASS_CHANGE_DOCUMENT_LOCKED, NEGOTIATION, String.valueOf(negotiationId));
    }

    @Override
    protected String getDocumentId(PersistableBusinessObject parent) {
        return ((Negotiation) parent).getNegotiationId().toString();
    }

    @Override
    protected String getDocumentName() {
        return NEGOTIATION;
    }

    @Override
    protected String getWarningKey() {
        return NEGOTIATION_WARNINGS;
    }

}