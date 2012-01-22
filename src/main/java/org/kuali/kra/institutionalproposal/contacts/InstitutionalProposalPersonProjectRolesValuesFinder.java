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
package org.kuali.kra.institutionalproposal.contacts;

import static org.kuali.kra.logging.BufferedLogger.info;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.kra.institutionalproposal.web.struts.form.InstitutionalProposalForm;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.service.Sponsorable;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kns.util.KNSGlobalVariables;

/**
 * This class...
 */
public class InstitutionalProposalPersonProjectRolesValuesFinder extends InstitutionalProposalContactsProjectRoleValuesFinder {

    private KeyPersonnelService keyPersonnelService;

    public List getKeyValues() {
        @SuppressWarnings("unchecked") final Collection<ProposalPersonRole> roles = getKeyValuesService().findAll(ProposalPersonRole.class);
        final InstitutionalProposalDocument institutionalProposalDocument = ((InstitutionalProposalForm) KNSGlobalVariables.getKualiForm()).getInstitutionalProposalDocument();

        Sponsorable sponsorable = institutionalProposalDocument.getInstitutionalProposal();
        Map<String, String> roleDescriptions = getKeyPersonnelService().loadKeyPersonnelRoleDescriptions(sponsorable.isSponsorNihMultiplePi());

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (ProposalPersonRole role : roles) {
            boolean showRole = true;

             // TODO: JF - Investigate why this is done this way in ProposalPersonRoleValuesFinder. Also tied to proposalPersonRole.tag
            // If the person has already been added, then exclude Key Person
//            if (isPersonAdded()) {
//                showRole = !KEY_PERSON_ROLE.equals(role.getProposalPersonRoleId());
//            }

            info("showRole = ", showRole);

            if (showRole) {
                String roleDescription =  roleDescriptions.get(role.getRoleCode());
                keyValues.add(new ConcreteKeyValue(role.getProposalPersonRoleId(), roleDescription));
                info("Added role ", role.getProposalPersonRoleId());
                info("With description ", roleDescription);
            }

            info("Returning ", keyValues);
        }
        return keyValues;
    }

    protected KeyPersonnelService getKeyPersonnelService() {
        if(keyPersonnelService == null) {
            keyPersonnelService = KraServiceLocator.getService(KeyPersonnelService.class);
        }
        return keyPersonnelService; 
    }

    @Override
    protected Class<? extends ContactRole> getRoleType() {
        return ProposalPersonRole.class;
    }

    protected void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }

}
