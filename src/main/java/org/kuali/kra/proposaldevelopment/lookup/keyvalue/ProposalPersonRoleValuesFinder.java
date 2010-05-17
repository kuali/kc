/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import static org.kuali.kra.infrastructure.Constants.*;
import org.kuali.kra.infrastructure.KraServiceLocator;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.logging.BufferedLogger.info;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.service.ParameterService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.core.util.KeyLabelPair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Temporary class until this can be gotten working via table.
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.16 $
 */
public class ProposalPersonRoleValuesFinder extends KeyValuesBase {
    private String forAddedPerson;
    private ParameterService parameterService;
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KraServiceLocator.getService(ParameterService.class);        
        }
        return this.parameterService;
    }
    
    /**
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List getKeyValues() {
        final Collection<ProposalPersonRole> roles = getKeyValuesService().findAll(ProposalPersonRole.class);
        final ProposalDevelopmentDocument document = ((ProposalDevelopmentForm) GlobalVariables.getKualiForm()).getDocument();
        final DevelopmentProposal developmentProposal = document.getDevelopmentProposal();

        final boolean hasPrincipalInvestigator = getKeyPersonnelService().hasPrincipalInvestigator(document);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
         for (ProposalPersonRole role : roles) {
            info("Adding role ", role.getProposalPersonRoleId());
            info("With description ", findRoleDescription(role, developmentProposal));

            boolean showRole = true;

            // If the person has already been added, then exclude Key Person
            if (isPersonAdded()) {
                showRole = !KEY_PERSON_ROLE.equals(role.getProposalPersonRoleId());
            }

            info("showRole = ", showRole);

            if (showRole) {
                keyValues.add(new KeyLabelPair(role.getProposalPersonRoleId(), findRoleDescription(role, developmentProposal)));
            }

            info("Returning ", keyValues);
        }
        return keyValues;
    }
    protected String getRoleIdPrefix(DevelopmentProposal proposal) {
        return proposal.isSponsorNihMultiplePi() ? "nih." : "";
    }

    protected String findRoleDescription(ProposalPersonRole role, DevelopmentProposal proposal) {
          return this.getParameterService().getParameterValue(KC_GENERIC_PARAMETER_NAMESPACE, KC_ALL_PARAMETER_DETAIL_TYPE_CODE,
                PERSON_ROLE_PARAMETER_PREFIX + getRoleIdPrefix(proposal) + role.getProposalPersonRoleId().toLowerCase());
    }
    /**
     * Used to indicate to the values finder whether the role has already been rendered
     * 
     * @return true if the role has been rendered already, false otherwise
     */
    private boolean isNewProposalPersonRoleRendered() {
        return ((ProposalDevelopmentForm) GlobalVariables.getKualiForm()).isNewProposalPersonRoleRendered();
    }

    /**
     * Locate from Spring a singleton instance of the <code>{@link KeyPersonnelService}</code>.
     * 
     * @return KeyPersonnelService
     */
    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }

    /**
     * Locate from Spring a singleton instance of the <code>{@link KeyValuesService}</code>.
     * 
     * @return KeyValuesService
     */
    private KeyValuesService getKeyValuesService() {
        return getService(KeyValuesService.class);
    }

    private Boolean isPersonAdded() {
        return new Boolean(getForAddedPerson());
    }

    // START SNIPPET: ProposalPersonRoleValuesFinder#properties
    public String getForAddedPerson() {
        return forAddedPerson;
    }

    public void setForAddedPerson(String forAddedPerson) {
        this.forAddedPerson = forAddedPerson;
    }
    // END SNIPPET: ProposalPersonRoleValuesFinder#properties
}
