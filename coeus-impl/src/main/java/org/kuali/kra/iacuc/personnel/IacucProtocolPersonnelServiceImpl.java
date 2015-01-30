/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.iacuc.personnel;

import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.procedures.IacucProtocolProcedureService;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.protocol.ProtocolBase;
import org.kuali.kra.protocol.personnel.*;

public class IacucProtocolPersonnelServiceImpl extends ProtocolPersonnelServiceImplBase implements IacucProtocolPersonnelService {

    private IacucProtocolProcedureService iacucProtocolProcedureService;
    
    @Override
    protected ProtocolUnitBase createNewProtocolUnitInstanceHook() {
        return new IacucProtocolUnit();
    }

    @Override
    protected String getSequenceNumberNameHook() {
        return "SEQ_IACUC_PROTOCOL_ID";
    }

    @Override
    public Class<? extends ProtocolPersonRoleMappingBase> getProtocolPersonRoleMappingClassHook() {
        return IacucProtocolPersonRoleMapping.class;
    }

    @Override
    public Class<? extends ProtocolPersonRoleBase> getProtocolPersonRoleClassHook() {
        return IacucProtocolPersonRole.class;
    }

    @Override
    public void setPrincipalInvestigator(ProtocolPersonBase newPrincipalInvestigator, ProtocolBase protocol) {
        if (protocol != null) {
            ProtocolPersonBase currentPrincipalInvestigator = getPrincipalInvestigator(protocol.getProtocolPersons());

            if (newPrincipalInvestigator != null) {
                newPrincipalInvestigator.setProtocolPersonRoleId(getPrincipalInvestigatorRole());
                if (currentPrincipalInvestigator == null) {
                    protocol.getProtocolPersons().add(newPrincipalInvestigator);
                }
                else if (!isDuplicatePerson(protocol.getProtocolPersons(), newPrincipalInvestigator)) {
                    protocol.getProtocolPersons().remove(currentPrincipalInvestigator);
                    protocol.getProtocolPersons().add(newPrincipalInvestigator);
                }

                // Assign the PI the AGGREGATOR role if PI has a personId.
                if (newPrincipalInvestigator.getPersonId() != null) {
                    personEditableService.populateContactFieldsFromPersonId(newPrincipalInvestigator);
                    KcAuthorizationService kraAuthService = KcServiceLocator.getService(KcAuthorizationService.class);
                    kraAuthService.addDocumentLevelRole(newPrincipalInvestigator.getPersonId(), RoleConstants.IACUC_PROTOCOL_AGGREGATOR,
                            protocol);
                    kraAuthService.addDocumentLevelRole(newPrincipalInvestigator.getPersonId(), RoleConstants.IACUC_PROTOCOL_APPROVER, protocol);
                }
                else {
                    personEditableService.populateContactFieldsFromRolodexId(newPrincipalInvestigator);
                }
            }
        }
    }    
    
    @Override
    public void addProtocolPerson(ProtocolBase protocol, ProtocolPersonBase protocolPerson) {
        super.addProtocolPerson(protocol, protocolPerson);
    }

    public IacucProtocolProcedureService getIacucProtocolProcedureService() {
        return iacucProtocolProcedureService;
    }

    public void setIacucProtocolProcedureService(IacucProtocolProcedureService iacucProtocolProcedureService) {
        this.iacucProtocolProcedureService = iacucProtocolProcedureService;
    }

}
