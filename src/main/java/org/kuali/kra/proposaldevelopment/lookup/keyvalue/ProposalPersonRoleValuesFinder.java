/*
 * Copyright 2006-2009 The Kuali Foundation
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

import static org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_PERSON_ROLE_PARAMETER_PREFIX;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.logging.BufferedLogger.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;
import org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase;
import org.kuali.rice.kns.service.KeyValuesService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.web.ui.KeyLabelPair;

/**
 * Temporary class until this can be gotten working via table.
 *
 * @author $Author: gmcgrego $
 * @version $Revision: 1.16 $
 */
public class ProposalPersonRoleValuesFinder extends KeyValuesBase {
    private String forAddedPerson;
    
    /**
     * @see org.kuali.rice.kns.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List getKeyValues() {
        final Collection<ProposalPersonRole> roles = getKeyValuesService().findAll(ProposalPersonRole.class);
        final ProposalDevelopmentDocument document = ((ProposalDevelopmentForm) GlobalVariables.getKualiForm()).getDocument();
        final boolean hasPrincipalInvestigator = getKeyPersonnelService().hasPrincipalInvestigator(document);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
         for (ProposalPersonRole role : roles) {
            info("Adding role ", role.getProposalPersonRoleId());
            info("With description ", findRoleDescription(role,document));

            boolean showRole = true;

            // If the person has already been added, then exclude Key Person
            if (isPersonAdded()) {
                showRole = !KEY_PERSON_ROLE.equals(role.getProposalPersonRoleId());
            }

            info("showRole = ", showRole);

            if (showRole) {
                if(document.isNih()){
                    keyValues.add(new KeyLabelPair(role.getProposalPersonRoleId(), findNIHRoleDescription(role,document)));
                }
                else
                {
                    keyValues.add(new KeyLabelPair(role.getProposalPersonRoleId(), findRoleDescription(role,document)));
                }
            }

            info("Returning ", keyValues);
        }
        return keyValues;
    }
    protected String getRoleIdPrefix(ProposalDevelopmentDocument document) {
        if(document.isNih()){
            return "nonnih.";
        }
        else
        {
            return new String();
        }
    }
    protected String findRoleDescription(ProposalPersonRole role,ProposalDevelopmentDocument document) {
          return getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                PARAMETER_COMPONENT_DOCUMENT, 
                PROPOSAL_PERSON_ROLE_PARAMETER_PREFIX 
                + getRoleIdPrefix(document)
                + role.getProposalPersonRoleId().toLowerCase());    
    }


    protected String findNIHRoleDescription(ProposalPersonRole role,ProposalDevelopmentDocument document) {
            return getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                PARAMETER_COMPONENT_DOCUMENT, 
                PROPOSAL_PERSON_ROLE_PARAMETER_PREFIX 
                + getRoleIdPrefix(document)
                + role.getProposalPersonRoleId().toLowerCase());    
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
     * Locate from Spring a singleton instance of the <code>{@link KualiConfigurationService}</code>.
     * 
     * @return KualiConfigurationService
     */
    protected KualiConfigurationService getConfigurationService() {
        return getService(KualiConfigurationService.class);
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
