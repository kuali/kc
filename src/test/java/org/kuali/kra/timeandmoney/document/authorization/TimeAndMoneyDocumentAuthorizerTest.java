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
package org.kuali.kra.timeandmoney.document.authorization;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.kim.bo.KcKimAttributes;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;
import org.kuali.kra.timeandmoney.document.TimeAndMoneyDocument;
import org.kuali.rice.kim.api.group.Group;
import org.kuali.rice.kim.api.group.GroupService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kim.api.role.RoleService;
import org.kuali.rice.kim.impl.role.RoleMemberAttributeDataBo;
import org.kuali.rice.kim.impl.role.RoleMemberBo;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class TimeAndMoneyDocumentAuthorizerTest extends KcUnitTestBase {
    
    private TimeAndMoneyDocument timeAndMoneyDocument;
    private DocumentService documentService;
    private BusinessObjectService businessObjectService;
    private TimeAndMoneyDocumentAuthorizer authorizer;
    private Person quickstart;
    private Person borst;
    private Person irbAdmin;
    private Person iacucAdmin;
    
    @Before
    public void setup() throws Exception {
        documentService = KraServiceLocator.getService(DocumentService.class);
        businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        timeAndMoneyDocument = (TimeAndMoneyDocument) documentService.getNewDocument(TimeAndMoneyDocument.class);
        authorizer = new TimeAndMoneyDocumentAuthorizer();
        quickstart = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("quickstart");
        borst = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("borst");
        irbAdmin = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("irbAdmin");
        iacucAdmin = KraServiceLocator.getService(PersonService.class).getPersonByPrincipalName("iacucAdmin");
        addIrbAdminToAGroupWithTimeAndMoneyPerm();
        addIacucAdminToTimeAndMoneyRole();
    }
    
    @After
    public void teardown() {
        documentService = null;
        businessObjectService = null;
        timeAndMoneyDocument = null;
        authorizer = null;
        quickstart = null;
        borst = null;
        irbAdmin = null;
        iacucAdmin = null;
    }
    
    private void addIrbAdminToAGroupWithTimeAndMoneyPerm() {
        RoleService rs = KraServiceLocator.getService(RoleService.class);
        Role timeAndMoneyModifier = rs.getRoleByNamespaceCodeAndName("KC-T", "Time And Money Modifier");
        
        GroupService gs = KraServiceLocator.getService(GroupService.class);
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
        
        Map fieldValues = new HashMap();
        fieldValues.put("ROLE_ID", timeAndMoneyModifier.getId());
        fieldValues.put("MBR_ID", timeAndMoneyTestGroup.getId());
        Collection roleMembers = businessObjectService.findMatching(RoleMemberBo.class, fieldValues);
        RoleMemberBo roleMember = (RoleMemberBo) roleMembers.iterator().next();
        
        RoleMemberAttributeDataBo attrData = new RoleMemberAttributeDataBo();
        attrData.setAttributeValue("000001");
        attrData.setKimAttributeId("47");
        attrData.setKimTypeId("69");
        attrData.setAssignedToId(roleMember.getId());
        
        RoleMemberAttributeDataBo attrDataTwo = new RoleMemberAttributeDataBo();
        attrDataTwo.setAttributeValue("Y");
        attrDataTwo.setKimAttributeId("48");
        attrDataTwo.setKimTypeId("69");
        attrDataTwo.setAssignedToId(roleMember.getId());
        
        roleMember.getAttributeDetails().add(attrData);
        roleMember.getAttributeDetails().add(attrDataTwo);
        businessObjectService.save(roleMember);
        businessObjectService.save(attrData);
        businessObjectService.save(attrDataTwo);
    }
    
    private void addIacucAdminToTimeAndMoneyRole() {
        RoleService rs = KraServiceLocator.getService(RoleService.class);
        Role timeAndMoneyModifier = rs.getRoleByNamespaceCodeAndName("KC-T", "Time And Money Modifier");
        
        //GroupService gs = KraServiceLocator.getService(GroupService.class);
        //gs.createGroup(Group.Builder.create("KC-T", "TimeAndMoneyTestGroup", "21").build());
        
        /**
         * Get the group from the database so we have a valid ID
         */
        //Group timeAndMoneyTestGroup = gs.getGroupByNamespaceCodeAndName("KC-T", "TimeAndMoneyTestGroup");
        //gs.addPrincipalToGroup(irbAdmin.getPrincipalId(), timeAndMoneyTestGroup.getId());
        
        //String groupId = timeAndMoneyTestGroup.getId();
        String namespaceCode = timeAndMoneyModifier.getNamespaceCode();
        String roleName = timeAndMoneyModifier.getName();
        //rs.assignGroupToRole(groupId, namespaceCode, roleName, new HashMap<String, String>());
        rs.assignPrincipalToRole(iacucAdmin.getPrincipalId(), namespaceCode, roleName, new HashMap<String, String>());
        
        
        Map fieldValues = new HashMap();
        fieldValues.put("ROLE_ID", timeAndMoneyModifier.getId());
        fieldValues.put("MBR_ID", iacucAdmin.getPrincipalId());
        Collection roleMembers = businessObjectService.findMatching(RoleMemberBo.class, fieldValues);
        RoleMemberBo roleMember = (RoleMemberBo) roleMembers.iterator().next();
        
        RoleMemberAttributeDataBo attrData = new RoleMemberAttributeDataBo();
        attrData.setAttributeValue("000001");
        attrData.setKimAttributeId("47");
        attrData.setKimTypeId("69");
        attrData.setAssignedToId(roleMember.getId());
        
        RoleMemberAttributeDataBo attrDataTwo = new RoleMemberAttributeDataBo();
        attrDataTwo.setAttributeValue("Y");
        attrDataTwo.setKimAttributeId("48");
        attrDataTwo.setKimTypeId("69");
        attrDataTwo.setAssignedToId(roleMember.getId());
        
        roleMember.getAttributeDetails().add(attrData);
        roleMember.getAttributeDetails().add(attrDataTwo);
        businessObjectService.save(roleMember);
        businessObjectService.save(attrData);
        businessObjectService.save(attrDataTwo);
    }
    
    @Test
    public void verifyRoleStuff() {
        RoleService rs = KraServiceLocator.getService(RoleService.class);
        Role timeAndMoneyModifier = rs.getRoleByNamespaceCodeAndName("KC-T", "Time And Money Modifier");
        GroupService gs = KraServiceLocator.getService(GroupService.class);
        Group timeAndMoneyTestGroup = gs.getGroupByNamespaceCodeAndName("KC-T", "TimeAndMoneyTestGroup");
        
        Map fieldValues = new HashMap();
        fieldValues.put("ROLE_ID", timeAndMoneyModifier.getId());
        fieldValues.put("MBR_ID", timeAndMoneyTestGroup.getId());
        Collection roleMembers = businessObjectService.findMatching(RoleMemberBo.class, fieldValues);
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
        Map<String, String> roleQual = new HashMap<String, String>();
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
        assertTrue(canQuickstart);
    }

    @Test
    public void testCanReload() {
        boolean canQuickstart = authorizer.canReload(timeAndMoneyDocument, quickstart);
        //boolean canBorst = authorizer.canReload(timeAndMoneyDocument, borst);
        assertTrue(canQuickstart);
        //assertFalse(canBorst);
    }

    @Test
    public void testCanClose() {
        boolean canQuickstart = authorizer.canClose(timeAndMoneyDocument, quickstart);
        //boolean canBorst = authorizer.canClose(timeAndMoneyDocument, borst);
        assertTrue(canQuickstart);
        //assertFalse(canBorst);
    }
    
    @Test
    public void testQuickStartPerm() {
        RoleService rs = KraServiceLocator.getService(RoleService.class);
        Role timeAndMoneyModifier = rs.getRoleByNamespaceCodeAndName("KC-T", "Time And Money Modifier");
        
        Map fieldValues = new HashMap();
        fieldValues.put("ROLE_ID", timeAndMoneyModifier.getId());
        fieldValues.put("MBR_ID", quickstart.getPrincipalId());
        Collection roleMembers = businessObjectService.findMatching(RoleMemberBo.class, fieldValues);
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
