/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
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
package org.kuali.kra.proposaldevelopment.lookup.keyvalue;

import static org.kuali.core.util.GlobalVariables.getKualiForm;
import static org.kuali.kra.infrastructure.Constants.KEY_PERSON_ROLE;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.Constants.PRINCIPAL_INVESTIGATOR_ROLE;
import static org.kuali.kra.infrastructure.Constants.PROPOSAL_PERSON_ROLE_PARAMETER_PREFIX;
import static org.kuali.kra.infrastructure.Constants.SPONSOR_LEVEL_HIERARCHY;
import static org.kuali.kra.infrastructure.Constants.NIH_SPONSOR_ACRONYM;
import static org.kuali.kra.infrastructure.Constants.SPONSOR_HIERARCHY_NAME;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.logging.FormattedLogger.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.kuali.core.lookup.keyvalues.KeyValuesBase;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.KeyValuesService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.web.ui.KeyLabelPair;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.SponsorHierarchy;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

import edu.iu.uis.eden.engine.node.KeyValuePair;

/**
 * Temporary class until this can be gotten working via table.
 *
 * @author $Author: jsalam $
 * @version $Revision: 1.9.2.4 $
 */
public class ProposalPersonRoleValuesFinder extends KeyValuesBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalPersonRoleValuesFinder.class);

    private String forAddedPerson;
    
    /**
     * @see org.kuali.core.lookup.keyvalues.KeyValuesBase#getKeyValues()
     */
    public List getKeyValues() {
        final Collection<ProposalPersonRole> roles = getKeyValuesService().findAll(ProposalPersonRole.class);
        final ProposalDevelopmentDocument document = ((ProposalDevelopmentForm) getKualiForm()).getProposalDevelopmentDocument();
        final boolean hasPrincipalInvestigator = getKeyPersonnelService().hasPrincipalInvestigator(document);
        List<KeyLabelPair> keyValues = new ArrayList<KeyLabelPair>();
        keyValues.add(new KeyLabelPair("", "select"));
        Map valueMap = new HashMap();
        valueMap.put("sponsorCode", document.getSponsorCode());
        valueMap.put("hierarchyName",getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                PARAMETER_COMPONENT_DOCUMENT, 
                SPONSOR_HIERARCHY_NAME ));
        BusinessObjectService bos = KraServiceLocator.getService(BusinessObjectService.class);
        Collection<SponsorHierarchy> sponsor_hierarchy=  bos.findMatching(SponsorHierarchy.class, valueMap);
        if (CollectionUtils.isNotEmpty(sponsor_hierarchy)) {
            for (Object variable : sponsor_hierarchy) {
                SponsorHierarchy sponhierarchy=(SponsorHierarchy) variable;
                if(StringUtils.isNotEmpty(sponhierarchy.getLevel1()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel2()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    
                    document.setNih(true);
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel3()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    
                    document.setNih(true);
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel4()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel5()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel6()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel7()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel8()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel9()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                }else if(StringUtils.isNotEmpty(sponhierarchy.getLevel9()) && (sponhierarchy.getLevel1().equals(getConfigurationService().getParameterValue(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, 
                        PARAMETER_COMPONENT_DOCUMENT, 
                        SPONSOR_LEVEL_HIERARCHY )))){
                    document.setNih(true);
                }
            }
        }

        for (ProposalPersonRole role : roles) {
            info("Adding role %s", role.getProposalPersonRoleId());
            info("With description %s", findRoleDescription(role,document));

            boolean showRole = true;

            // If the person has already been added, then exclude Key Person
            if (isPersonAdded()) {
                showRole = !KEY_PERSON_ROLE.equals(role.getProposalPersonRoleId());
            }

            info("showRole = %s", showRole);

            if (showRole) {
                if(document.isNih()){
                    keyValues.add(new KeyLabelPair(role.getProposalPersonRoleId(), findNIHRoleDescription(role,document)));
                }
                else
                {
                    keyValues.add(new KeyLabelPair(role.getProposalPersonRoleId(), findRoleDescription(role,document)));
                }
            }

            info("Returning %s", keyValues);
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
        return ((ProposalDevelopmentForm) getKualiForm()).isNewProposalPersonRoleRendered();
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
