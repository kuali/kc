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
package org.kuali.coeus.propdev.impl.docperm;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.rice.core.api.util.ConcreteKeyValue;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.krad.uif.control.UifKeyValuesFinderBase;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.view.ViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Finds the available set of proposal roles.  See
 * the method <code>getKeyValues()</code> for a full description.
 * 
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalRoleValuesFinder extends UifKeyValuesFinderBase {
    
    /**
     * The set of proposal roles is fetched from rice.  These roles are the document-level
     * roles to allow access to the document.
     * 
     * @return the list of key/value pairs of Proposal Roles.
     * @see org.kuali.rice.krad.keyvalues.KeyValuesFinder#getKeyValues()
     */
    @Override
    public List<KeyValue> getKeyValues(ViewModel model,InputField field) {
        ProposalDevelopmentDocument document = ((ProposalDevelopmentDocumentForm)model).getProposalDevelopmentDocument();
        List<KeyValue> keyValues = new ArrayList<KeyValue>();
        if (document.getDocumentHeader().getWorkflowDocument().isEnroute()) {
            List<String> roleNames = new ArrayList<String>();
            try {
                roleNames = (List<String>) PropertyUtils.getProperty(model, field.getBindingInfo().getBindingPath());
            } catch (Exception e) {
                //do nothing, if unable to retrieve the property from the model treat it as no value being selected.
            }
            for (String roleName : roleNames) {
                KeyValue pair = new ConcreteKeyValue(roleName, roleName);
                keyValues.add(pair);
            }
            KeyValue pair = new ConcreteKeyValue(RoleConstants.VIEWER_DOCUMENT_LEVEL, RoleConstants.VIEWER_DOCUMENT_LEVEL);
            keyValues.add(pair);

        } else {
            ProposalRoleService proposalRoleService = KcServiceLocator.getService(ProposalRoleService.class);
            List<Role> proposalRoles = proposalRoleService.getRolesForDisplay();


            for (Role role : proposalRoles) {
                KeyValue pair = new ConcreteKeyValue(role.getName(), role.getName());
                keyValues.add(pair);
            }
        }
        return keyValues;
    }
}
