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
package org.kuali.kra.irb.personnel;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.personnel.*;


public class ProtocolPersonnelServiceImpl extends ProtocolPersonnelServiceImplBase implements ProtocolPersonnelService {
    
    @Override
    protected ProtocolUnitBase createNewProtocolUnitInstanceHook() {
        return new ProtocolUnit();
    }

    @Override
    protected String getSequenceNumberNameHook() {
        return "SEQ_PROTOCOL_ID";
    }

    @Override
    public Class<? extends ProtocolPersonRoleMappingBase> getProtocolPersonRoleMappingClassHook() {
        return ProtocolPersonRoleMapping.class;
    }

    @Override
    public Class<? extends ProtocolPersonRoleBase> getProtocolPersonRoleClassHook() {
        return ProtocolPersonRole.class;
    }

    @Override
    public void setPrincipalInvestigator(ProtocolPersonBase newPrincipalInvestigator, ProtocolBase protocol) {
        if (protocol != null) {
            ProtocolPerson currentPrincipalInvestigator = (ProtocolPerson) getPrincipalInvestigator(protocol.getProtocolPersons());
            
            if (newPrincipalInvestigator != null) {
                newPrincipalInvestigator.setProtocolPersonRoleId(getPrincipalInvestigatorRole());
                if (currentPrincipalInvestigator == null) {
                    protocol.getProtocolPersons().add(newPrincipalInvestigator);
                } else if (!isDuplicatePerson(protocol.getProtocolPersons(), newPrincipalInvestigator)) {
                    protocol.getProtocolPersons().remove(currentPrincipalInvestigator);
                    protocol.getProtocolPersons().add(newPrincipalInvestigator);
                }
                
                // Assign the PI the APPROVER role if PI has a personId (for doc cancel).
                if (newPrincipalInvestigator.getPersonId() != null) {
                    personEditableService.populateContactFieldsFromPersonId(newPrincipalInvestigator);
                    KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
                    kraAuthService.addDocumentLevelRole(newPrincipalInvestigator.getPersonId(), RoleConstants.PROTOCOL_APPROVER, protocol);
                } else {
                    personEditableService.populateContactFieldsFromRolodexId(newPrincipalInvestigator);
                }
            }
        }
    }

	@Override
	protected boolean isDuplicatePersonAllowed() {
		return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROTOCOL, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.IRB_PROTOCOL_DUPLICATE_PERSON_ENABLED);
	}
}
