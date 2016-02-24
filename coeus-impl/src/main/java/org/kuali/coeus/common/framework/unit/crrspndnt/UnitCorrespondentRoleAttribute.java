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
package org.kuali.coeus.common.framework.unit.crrspndnt;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.rice.kew.api.identity.Id;
import org.kuali.rice.kew.api.identity.PrincipalId;
import org.kuali.rice.kew.api.rule.RoleName;
import org.kuali.rice.kew.engine.RouteContext;
import org.kuali.rice.kew.routeheader.DocumentContent;
import org.kuali.rice.kew.rule.GenericRoleAttribute;
import org.kuali.rice.kew.rule.QualifiedRoleName;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UnitCorrespondentRoleAttribute extends GenericRoleAttribute {

    private static final String ROLE_NAME = "UnitCorrespondent";
    private static final String UNIT_NODE_NAME = "leadUnitNumber";

    public List<String> getQualifiedRoleNames(String roleName, DocumentContent documentContent) {
        List<String> qualifiedRoleNames = new ArrayList<String>();
        qualifiedRoleNames.add(ROLE_NAME);
        
        return qualifiedRoleNames;
    }

    public List<RoleName> getRoleNames() {
        RoleName role = RoleName.Builder.create(UnitCorrespondentRoleAttribute.class.getName(), ROLE_NAME, ROLE_NAME).build();
        return Collections.singletonList(role);
    }
    
    @Override
    public Map<String, String> getProperties() {
        // intentionally unimplemented...not intending on using this attribute client-side
        return null;
    }

    private UnitService getUnitService() {
        return KcServiceLocator.getService(UnitService.class);
    }
    
    @Override
    protected List<Id> resolveRecipients(RouteContext routeContext, QualifiedRoleName qualifiedRoleName) {
        List<Id> members = new ArrayList<Id>();
        
        DocumentContent dc = routeContext.getDocumentContent();
        NodeList nodes = dc.getDocument().getElementsByTagName(UNIT_NODE_NAME);
        String unitNumber = nodes.item(0).getTextContent();
        if (StringUtils.isNotBlank(unitNumber)) {
            List<UnitCorrespondent> unitCorrespondents = getUnitService().retrieveUnitCorrespondentsByUnitNumber(unitNumber);
            for ( UnitCorrespondent unitCorrespondent : unitCorrespondents ) {
                if ( StringUtils.isNotBlank(unitCorrespondent.getPersonId()) ) {
                    members.add( new PrincipalId(unitCorrespondent.getPersonId()));
                }
            }
        }
        
        return members;
    }

}
