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
