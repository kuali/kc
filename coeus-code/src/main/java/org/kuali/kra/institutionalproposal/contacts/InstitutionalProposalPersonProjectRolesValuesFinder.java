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
package org.kuali.kra.institutionalproposal.contacts;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.framework.person.PropAwardPersonRole;
import org.kuali.coeus.common.framework.sponsor.Sponsorable;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.award.home.ContactRole;
import org.kuali.kra.institutionalproposal.document.InstitutionalProposalDocument;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class InstitutionalProposalPersonProjectRolesValuesFinder extends InstitutionalProposalContactsProjectRoleValuesFinder {

    private static final Log LOG = LogFactory.getLog(InstitutionalProposalPersonProjectRolesValuesFinder.class);

    private KeyPersonnelService keyPersonnelService;

    @Override
    public List<KeyValue> getKeyValues() {
        @SuppressWarnings("unchecked") final Collection<PropAwardPersonRole> roles = getKeyValuesService().findAll(PropAwardPersonRole.class);
        final InstitutionalProposalDocument institutionalProposalDocument = (InstitutionalProposalDocument) getDocument();

        Sponsorable sponsorable = institutionalProposalDocument.getInstitutionalProposal();
        Map<String, String> roleDescriptions = getKeyPersonnelService().loadKeyPersonnelRoleDescriptions(sponsorable.isSponsorNihMultiplePi());

        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        keyValues.add(new ConcreteKeyValue("", "select"));
        for (PropAwardPersonRole role : roles) {
            boolean showRole = true;

            LOG.info("showRole = " + showRole);

            if (showRole) {
                String roleDescription =  roleDescriptions.get(role.getRoleCode());
                keyValues.add(new ConcreteKeyValue(role.getCode(), roleDescription));
                LOG.info("Added role " + role.getCode());
                LOG.info("With description " + roleDescription);
            }

            LOG.info("Returning " + keyValues);
        }
        return keyValues;
    }

    protected KeyPersonnelService getKeyPersonnelService() {
        if(keyPersonnelService == null) {
            keyPersonnelService = KcServiceLocator.getService(KeyPersonnelService.class);
        }
        return keyPersonnelService; 
    }

    @Override
    protected Class<? extends ContactRole> getRoleType() {
        return PropAwardPersonRole.class;
    }

    protected void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }

}
