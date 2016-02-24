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
package org.kuali.kra.timeandmoney.document.authorization;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.kim.api.group.Group;
import org.kuali.rice.kim.api.group.GroupService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.impl.role.RoleMemberAttributeDataBo;
import org.kuali.rice.kim.impl.role.RoleMemberBo;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.*;


public class TimeAndMoneyDocumentAuthorizerTest extends KcIntegrationTestBase {
    
    private TimeAndMoneyDocument timeAndMoneyDocument;
    private DocumentService documentService;
    private DataObjectService dataObjectService;
    private TimeAndMoneyDocumentAuthorizer authorizer;
    private Person quickstart;
    private Person borst;
    private Person irbAdmin;
    private Person iacucAdmin;
    
    @Before
    public void setup() throws Exception {
        documentService = KcServiceLocator.getService(DocumentService.class);
        dataObjectService = KcServiceLocator.getService(DataObjectService.class);
        timeAndMoneyDocument = (TimeAndMoneyDocument) documentService.getNewDocument(TimeAndMoneyDocument.class);
        authorizer = new TimeAndMoneyDocumentAuthorizer();
        quickstart = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("quickstart");
        borst = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("borst");
        irbAdmin = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("irbAdmin");
        iacucAdmin = KcServiceLocator.getService(PersonService.class).getPersonByPrincipalName("iacucAdmin");
        addIrbAdminToAGroupWithTimeAndMoneyPerm();
        addIacucAdminToTimeAndMoneyRole();
    }

    @After
    public void teardown() {
        documentService = null;
        dataObjectService = null;
        timeAndMoneyDocument = null;
        authorizer = null;
        quickstart = null;
        borst = null;
        irbAdmin = null;
        iacucAdmin = null;
    }
    
    private void addIrbAdminToAGroupWithTimeAndMoneyPerm() {
        RoleService rs = KcServiceLocator.getService(RoleService.class);
        Role timeAndMoneyModifier = rs.getRoleByNamespaceCodeAndName("KC-T", "Time And Money Modifier");
        
        GroupService gs = KcServiceLocator.getService(GroupService.class);
        gs.createGroup(Group.Builder.create("KC-T", "TimeAndMoneyTestGroup", "21").build());
        
        /**
         * Get the group from the database so we have a valid ID
         */
        Group timeAndMoneyTestGroup = gs.getGroupByNamespaceCodeAndName("KC-T", "TimeAndMoneyTestGroup");
        gs.addPrincipalToGroup(irbAdmin.getPrincipalId(), timeAndMoneyTestGroup.getId());
        
        String groupId = timeAndMoneyTestGroup.getId();
        String namespaceCode = timeAndMoneyModifier.getNamespaceCode();
        String roleName = timeAndMoneyModifier.getName();
        rs.assignGroupToRole(groupId, namespaceCode, roleName, new HashMap<String, String>());

        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("roleId", timeAndMoneyModifier.getId());
        fieldValues.put("memberId", timeAndMoneyTestGroup.getId());
        Collection<RoleMemberBo> roleMembers = dataObjectService.findMatching(RoleMemberBo.class, QueryByCriteria.Builder.andAttributes(fieldValues).build()).getResults();
        RoleMemberBo roleMember =  roleMembers.iterator().next();

        RoleMemberAttributeDataBo attrData = new RoleMemberAttributeDataBo();
        attrData.setId("KCTEST1");
        attrData.setAttributeValue("000001");
        attrData.setKimAttributeId("47");
        attrData.setKimTypeId("69");
        attrData.setObjectId(UUID.randomUUID().toString());
        attrData.setVersionNumber(0L);
        attrData.setAssignedToId(roleMember.getId());

        RoleMemberAttributeDataBo attrDataTwo = new RoleMemberAttributeDataBo();
        attrDataTwo.setId("KCTEST2");
        attrDataTwo.setAttributeValue("Y");
        attrDataTwo.setKimAttributeId("48");
        attrDataTwo.setKimTypeId("69");
        attrDataTwo.setObjectId(UUID.randomUUID().toString());
        attrDataTwo.setVersionNumber(0L);
        attrDataTwo.setAssignedToId(roleMember.getId());

        roleMember.getAttributeDetails().add(attrData);
        roleMember.getAttributeDetails().add(attrDataTwo);
        dataObjectService.save(roleMember);

    }
    
    private void addIacucAdminToTimeAndMoneyRole() {
        RoleService rs = KcServiceLocator.getService(RoleService.class);
        Role timeAndMoneyModifier = rs.getRoleByNamespaceCodeAndName("KC-T", "Time And Money Modifier");

        String namespaceCode = timeAndMoneyModifier.getNamespaceCode();
        String roleName = timeAndMoneyModifier.getName();
        rs.assignPrincipalToRole(iacucAdmin.getPrincipalId(), namespaceCode, roleName, new HashMap<String, String>());


        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("roleId", timeAndMoneyModifier.getId());
        fieldValues.put("memberId", iacucAdmin.getPrincipalId());
        Collection roleMembers = dataObjectService.findMatching(RoleMemberBo.class, QueryByCriteria.Builder.andAttributes(fieldValues).build()).getResults();
        RoleMemberBo roleMember = (RoleMemberBo) roleMembers.iterator().next();

        RoleMemberAttributeDataBo attrData = new RoleMemberAttributeDataBo();
        attrData.setId("KCTEST3");
        attrData.setAttributeValue("000001");
        attrData.setKimAttributeId("10000");
        attrData.setKimTypeId("10001");
        attrData.setObjectId(UUID.randomUUID().toString());
        attrData.setVersionNumber(0L);
        attrData.setAssignedToId(roleMember.getId());

        RoleMemberAttributeDataBo attrDataTwo = new RoleMemberAttributeDataBo();
        attrDataTwo.setId("KCTEST4");
        attrDataTwo.setAttributeValue("Y");
        attrDataTwo.setKimAttributeId("10001");
        attrDataTwo.setKimTypeId("10001");
        attrDataTwo.setObjectId(UUID.randomUUID().toString());
        attrDataTwo.setVersionNumber(0L);
        attrDataTwo.setAssignedToId(roleMember.getId());

        roleMember.getAttributeDetails().add(attrData);
        roleMember.getAttributeDetails().add(attrDataTwo);
        dataObjectService.save(roleMember);
    }
    
    @Test
    public void verifyRoleStuff() {
        RoleService rs = KcServiceLocator.getService(RoleService.class);
        Role timeAndMoneyModifier = rs.getRoleByNamespaceCodeAndName("KC-T", "Time And Money Modifier");
        GroupService gs = KcServiceLocator.getService(GroupService.class);
        Group timeAndMoneyTestGroup = gs.getGroupByNamespaceCodeAndName("KC-T", "TimeAndMoneyTestGroup");
        
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("roleId", timeAndMoneyModifier.getId());
        fieldValues.put("memberId", timeAndMoneyTestGroup.getId());
        Collection roleMembers = dataObjectService.findMatching(RoleMemberBo.class, QueryByCriteria.Builder.andAttributes(fieldValues).build()).getResults();
        RoleMemberBo roleMember = (RoleMemberBo) roleMembers.iterator().next();
        
        boolean foundUnit = false;
        boolean foundHierarchFlag = false;
        for (RoleMemberAttributeDataBo rmb : roleMember.getAttributeDetails()) {
            if (StringUtils.equalsIgnoreCase(rmb.getAttributeValue(), "000001")) {
                foundUnit = true;
            } else if(StringUtils.equalsIgnoreCase(rmb.getAttributeValue(), "Y")){
                foundHierarchFlag = true;
            }
        }
        assertEquals(2, roleMember.getAttributeDetails().size());
        assertTrue(foundUnit);
        assertTrue(foundHierarchFlag);
    }

    @Test
    public void testAddRoleQualificationObjectMapOfStringString() {
        Map<String, String> roleQual = new HashMap<>();
        authorizer.addRoleQualification(timeAndMoneyDocument, roleQual);
        assertNotNull(roleQual.get(KcKimAttributes.UNIT_NUMBER));
    }


    @Test
    public void testCanAnnotate() {
        boolean canQuickstart = authorizer.canAnnotate(timeAndMoneyDocument, quickstart);
        boolean canBorst = authorizer.canAnnotate(timeAndMoneyDocument, borst);
        boolean canIrbAdmin = authorizer.canAnnotate(timeAndMoneyDocument, irbAdmin);
        boolean canIacucAdmin = authorizer.canAnnotate(timeAndMoneyDocument, iacucAdmin);
        assertTrue(canQuickstart);
        assertFalse(canBorst);
        assertTrue(canIacucAdmin);
        assertFalse(canIrbAdmin);
    }

    @Test
    public void testCanReload() {
        boolean canQuickstart = authorizer.canReload(timeAndMoneyDocument, quickstart);
        assertTrue(canQuickstart);
    }

    @Test
    public void testCanClose() {
        boolean canQuickstart = authorizer.canClose(timeAndMoneyDocument, quickstart);
        assertTrue(canQuickstart);
    }
    
    @Test
    public void testQuickStartPerm() {
        RoleService rs = KcServiceLocator.getService(RoleService.class);
        Role timeAndMoneyModifier = rs.getRoleByNamespaceCodeAndName("KC-T", "Time And Money Modifier");
        
        Map<String, String> fieldValues = new HashMap<>();
        fieldValues.put("roleId", timeAndMoneyModifier.getId());
        fieldValues.put("memberId", quickstart.getPrincipalId());
        Collection roleMembers = dataObjectService.findMatching(RoleMemberBo.class, QueryByCriteria.Builder.andAttributes(fieldValues).build()).getResults();
        RoleMemberBo roleMember = (RoleMemberBo) roleMembers.iterator().next();
        
        boolean foundUnit = false;
        boolean foundHierarchFlag = false;
        for (RoleMemberAttributeDataBo rmb : roleMember.getAttributeDetails()) {
            if (StringUtils.equalsIgnoreCase(rmb.getAttributeValue(), "000001")) {
                foundUnit = true;
            } else if(StringUtils.equalsIgnoreCase(rmb.getAttributeValue(), "Y")){
                foundHierarchFlag = true;
            }
        }
        assertEquals(2, roleMember.getAttributeDetails().size());
        assertTrue(foundUnit);
        assertTrue(foundHierarchFlag);
    }
}
