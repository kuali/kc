/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.irb.web;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.irb.Protocol;
import org.kuali.kra.irb.ProtocolDocument;
import org.kuali.kra.irb.auth.ProtocolAuthorizationService;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ProtocolCopyWebTest extends ProtocolWebTestBase {
   
    /**
     * The set of Protocol properties that must not be compared.
     */
    private static String[] filteredProperties = { "protocolNumber",
                                                   "updateTimestamp",
                                                   "updateUser",
                                                   "protocolId",
                                                   "sequenceNumber",
                                                   "versionNumber",
                                                   "objectId",
                                                   "protocol",
                                                   "protocolDocument",
                                                   "protocolLocationId",
                                                   "protocolOrganizationType",
                                                   "protocolPersonId",
                                                   "unit",
                                                   "protocolUnitsId"
                                                 };
    
    private static final String LEAD_UNIT_NBR_1 = "000001";
    private static final String LEAD_UNIT_NBR_2 = "BL-IIDC";
    
    private static final String COPY_PROTOCOL_BTN = "methodToCall.copyProtocol";
     
    private static final String QUICKSTART = "quickstart";
    private static final String TDURKIN = "tdurkin";
    private static final String JTESTER = "jtester";
    
    private static final String USERNAME_FIELD_ID = "permissionsHelper.newUser.userName";
    private static final String ROLENAME_FIELD_ID = "permissionsHelper.newUser.roleName";
    private static final String ADD_BTN_ID = "methodToCall.addUser";
  
    /***********************************************************************
     * Setup and TearDown
     ***********************************************************************/
    
    private boolean javaScriptEnabled;
    
    @Before
    public void setUp() throws Exception {
        super.setUp();
        javaScriptEnabled = webClient.isJavaScriptEnabled();
        webClient.setJavaScriptEnabled(false);
    }
    
    @After
    public void tearDown() throws Exception {
        webClient.setJavaScriptEnabled(javaScriptEnabled);
        super.tearDown();
    }
    
    
    /**
     * Each property in the document that can be copied is represented
     * by its getter and setter method.
     *
     * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
     */
    private class DocProperty {
        Method getter;
        Method setter;
        
        DocProperty(Method getter, Method setter) {
            this.getter = getter;
            this.setter = setter;
        }
    }
    
    /**
     * Test the copy protocol functionality.
     * @throws Exception
     */
    @Test
    public void testCopy() throws Exception {
        HtmlPage actionsPage = buildDocument();
        ProtocolDocument srcDoc = getProtocolDocument(actionsPage);
        ProtocolDocument destDoc = copyDocument(actionsPage);
      
        compareDocuments(srcDoc, destDoc);
    }
    
    /**
     * Builds a complex Protocol Document.      
     * @return the Actions web page containing the document.
     * @throws Exception
     */
    private HtmlPage buildDocument() throws Exception {
        HtmlPage protocolPage = getProtocolSavedRequiredFieldsPage();
       
        setAdditionalFields(protocolPage);
        protocolPage = addParticipant(protocolPage, "3", "5");
        protocolPage = addLocation(protocolPage, "000020", "2");
        
        HtmlPage personnelPage = clickOnTab(protocolPage, PERSONNEL_LINK_NAME);
        personnelPage = addPersonnelUnit(personnelPage, 0, "IN-CARD");
        personnelPage = addPersonnel(personnelPage, TDURKIN, "COI", 1);
        
        HtmlPage permissionsPage = clickOnTab(personnelPage, PERMISSIONS_LINK_NAME);
        permissionsPage = addUser(permissionsPage, JTESTER, RoleConstants.PROTOCOL_VIEWER);
        permissionsPage = addUser(permissionsPage, TDURKIN, RoleConstants.PROTOCOL_UNASSIGNED);
        
        return clickOnTab(permissionsPage, PROTOCOL_ACTIONS_LINK_NAME);
    }

    /**
     * Get a Protocol Document from the database.
     * 
     * @param page the HTML page containing the document.
     * @return the protocol document.
     * @throws Exception
     */
    private ProtocolDocument getProtocolDocument(HtmlPage page) throws Exception {
        String docNbr = getDocNbr(page);
        return (ProtocolDocument) this.getDocument(docNbr);
    }
    
    /**
     * Copies a Protocol Document.
     * 
     * @param actionsPage the Actions web page containing the copy feature.
     * @return the new copied document.
     * @throws Exception
     */
    private ProtocolDocument copyDocument(HtmlPage actionsPage) throws Exception {
        HtmlElement btn = this.getElementByName(actionsPage, COPY_PROTOCOL_BTN, true);
        HtmlPage protocolPage = this.clickOn(btn);
        assertTrue(!hasError(protocolPage));
        
        String docNbr = this.getDocNbr(protocolPage);
        return (ProtocolDocument) this.getDocument(docNbr);
    }
    
    /**
     * Compare the original (source) protocol document with its copy (destination).
     * 
     * @param srcDoc the original document
     * @param destDoc the copied document
     * @throws Exception
     */
    private void compareDocuments(ProtocolDocument srcDoc, ProtocolDocument destDoc) throws Exception {
       
        // They had better have different document numbers.
        
        String nbr1 = srcDoc.getDocumentNumber();
        String nbr2 = destDoc.getDocumentNumber();
        if (StringUtils.equals(nbr1, nbr2)) {
            fail("Document numbers are the same: " + nbr1);
        }
 
        // The must have the same document description.
        
        String desc1 = srcDoc.getDocumentHeader().getDocumentDescription();
        String desc2 = destDoc.getDocumentHeader().getDocumentDescription();
        assertEquals("Descriptions are different", desc1, desc2);
        
        // They must have the same organization document number.

        String org1 = srcDoc.getDocumentHeader().getOrganizationDocumentNumber();
        String org2 = destDoc.getDocumentHeader().getOrganizationDocumentNumber();
        assertEquals("Organization document numbers are different", org1, org2);
         
        // Compare the document's properties.  They must be the same.

        List<DocProperty> properties = getComparableProperties(Protocol.class);
        for (DocProperty property : properties) {
           
          //  System.out.println("Compare " + property.getter.getName());
            Object value1 = property.getter.invoke(srcDoc.getProtocol());
            Object value2 = property.getter.invoke(destDoc.getProtocol());
            if (!equals(value1, value2)) {
                fail("Property " + property.getter.getName() + " is different. {" + getStringValue(value1) + "}, {" + getStringValue(value2) + "}");
            }
        }
        
        checkPermissions(destDoc);
    }
    
    /**
     * Verify that the user/roles for the new protocol are as expected based upon the
     * users/roles that were assigned to the original protocol.
     * @param destDoc
     */
    private void checkPermissions(ProtocolDocument destDoc) {
        ProtocolAuthorizationService protocolAuthorizationService = KraServiceLocator.getService(ProtocolAuthorizationService.class);
        
        List<String> roleNames = protocolAuthorizationService.getRoles(QUICKSTART, destDoc.getProtocol());
        assertEquals(1, roleNames.size());
        assertTrue(roleNames.contains(RoleConstants.PROTOCOL_AGGREGATOR));
        
        roleNames = protocolAuthorizationService.getRoles(JTESTER, destDoc.getProtocol());
        assertEquals(1, roleNames.size());
        assertTrue(roleNames.contains(RoleConstants.PROTOCOL_VIEWER));
        
        roleNames = protocolAuthorizationService.getRoles(TDURKIN, destDoc.getProtocol());
        assertEquals(1, roleNames.size());
        assertTrue(roleNames.contains(RoleConstants.PROTOCOL_UNASSIGNED));
    }

    /**
     * Get the name of a property.  The method must be a setter method.
     * The property name is computed by removing the "set" from the
     * method name.
     *   
     * @param method the setter method.
     * @return the name of the corresponding property.
     */
    private String getPropertyName(Method method) {
        String name = method.getName();
        return name.substring(3);
    }

    /**
     * Is this a filtered property?
     * 
     * @param name the name of the property.
     * @return true if filtered; otherwise false.
     */
    private boolean isFilteredProperty(String name) {
        for (String filteredProperty : filteredProperties) {
            if (StringUtils.equalsIgnoreCase(name, filteredProperty)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the getter method for a property.
     * 
     * @param name the name of the property.
     * @param methods the list of methods to look in for the getter method.
     * @return the getter method or null if not found.
     */
    private Method getGetter(String name, Method[] methods) {
        String getter = "get" + name;
        for (Method method : methods) {
            if (getter.equals(method.getName())) {
                if (method.getParameterTypes().length != 0) {
                    return null;
                }
                return method;
            }
        }
        return null;
    }
    
    /**
     * Determines if the two objects are equal or not.
     * 
     * @param obj1 object
     * @param obj2 object
     * @return true if equal; otherwise false.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private boolean equals(Object obj1, Object obj2) throws Exception {
        
        boolean isEqual = false;
        
        if (obj1 == null && obj2 == null) {
            isEqual = true;
        }
        else if (obj1 == null || obj2 == null) {
            isEqual = false;
        }
        else if (!obj1.getClass().equals(obj2.getClass())) {
            //System.out.println("   Equals1 " + obj1.getClass().getName());
            isEqual = false;
        }
        else {
           // System.out.println("   Equals2 " + obj1.getClass().getName());
            Package pkg = obj1.getClass().getPackage();
            if (obj1 instanceof Collection) {
                Collection c1 = (Collection) obj1;
                Collection c2 = (Collection) obj2;         
                isEqual = compareCollections(c1, c2);
            }
            else if (!pkg.getName().startsWith("org.kuali")) { 
                isEqual = obj1.equals(obj2);
            } 
            else {
                isEqual = compareKualiObjects(obj1, obj2);
            }       
        }
        return isEqual;
    }
        
    /**
     * Compares to Kuali Business objects to determine if they are equal or not.
     * 
     * @param obj1
     * @param obj2
     * @return true if equal; otherwise false.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private boolean compareKualiObjects(Object obj1, Object obj2) throws Exception {
        Class clazz = obj1.getClass();
        List<DocProperty> properties = getComparableProperties(clazz);
        for (DocProperty property : properties) {  
            Object value1 = property.getter.invoke(obj1);
            Object value2 = property.getter.invoke(obj2);
            if (!equals(value1, value2)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Build a string representation for the given object.
     * @param value the object
     * @return a string representation
     */
    @SuppressWarnings("unchecked")
    private String getStringValue(Object value) {
        String s = null;
        if (value instanceof Collection) {
            Collection c = (Collection) value;
            s = "[";
            Iterator iter1 = c.iterator();
            while (iter1.hasNext()) {
                Object v1 = iter1.next();
                s += getStringValue(v1) + ", ";
            }
            s += "]";
        } else if (value == null) {
            s = "null";
        } else {
            s = value.toString();
        }
        return s;
    }
    
    /**
     * Compare two collections to see if they are equal or not.
     * The contents of the collections don't need to be in the same
     * order.
     * 
     * @param c1 collection 1
     * @param c2 collection 2
     * @return true if equal; otherwise false
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    private boolean compareCollections(Collection c1, Collection c2) throws Exception {
        if (c1.size() != c2.size()) {
            return false;
        } else if (c1.size() > 0) {
            Iterator iter1 = c1.iterator();
            while (iter1.hasNext()) {
                Object v1 = iter1.next();
                Iterator iter2 = c2.iterator();
                boolean match = false;
                while (iter2.hasNext()) {
                    Object v2 = iter2.next();
                    if (equals(v1, v2)) {
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Get the set of properties that can be compared between two
     * Kuali Business objects.
     * 
     * @param clazz the Kauli Business Class.
     * @return the list of comparable properties.
     */
    @SuppressWarnings("unchecked")
    private List<DocProperty> getComparableProperties(Class clazz) {
        List<DocProperty> list = new ArrayList<DocProperty>();
        
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {      
                String name = getPropertyName(method);
                Method getter = getGetter(name, methods);         
                if (getter != null) { 
                    if (!isFilteredProperty(name) && !hasIdProperty(clazz, name) && !hasCodeProperty(clazz, name) && !isService(name)) {
                        list.add(new DocProperty(getter, method));
                    }
                }
            }
        }
        
        return list;
    }
    
    /**
     * Is this a service method?
     * @param methodName the method name
     * @return
     */
    private boolean isService(String methodName) {
        return methodName.endsWith("Service");
    }

    /**
     * Does this property have an associated "Id" property?  Some business objects
     * have the following:
     * 
     *      String get<PropertyName>Id();
     *      <BusinessObjectClass> get<PropertyName>();
     *      
     * To avoid circular relationships (which lead to infinite loops), we only need
     * to compare the "Id" values between two business objects. 
     * 
     * @param clazz the business object class
     * @param name the property name
     * @return true if there is a corresponding "Id" property; otherwise false.
     */
    @SuppressWarnings("unchecked")
    private boolean hasIdProperty(Class clazz, String name) {
        if (name.endsWith("Id")) {
            return false;
        }
        
        String idName = "get" + name + "Id";
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(idName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Does this property have an associated "Code" property?  Some business objects
     * have the following:
     * 
     *      String get<PropertyName>Code();
     *      <BusinessObjectClass> get<PropertyName>();
     *      
     * To avoid circular relationships (which lead to infinite loops), we only need
     * to compare the "Code" values between two business objects. 
     * 
     * @param clazz the business object class
     * @param name the property name
     * @return true if there is a corresponding "Id" property; otherwise false.
     */
    @SuppressWarnings("unchecked")
    private boolean hasCodeProperty(Class clazz, String name) {
        if (name.endsWith("Code")) {
            return false;
        }
        
        String idName = "get" + name + "Code";
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(idName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Add a single user to the proposal.
     * @param page
     * @param username
     * @param roleName
     * @return
     * @throws Exception
     */
    private HtmlPage addUser(HtmlPage page, String username, String roleName) throws Exception {
        setFieldValue(page, USERNAME_FIELD_ID, username);
        setFieldValue(page, ROLENAME_FIELD_ID, roleName);
        HtmlElement addBtn = getElementByName(page, ADD_BTN_ID, true);
        return clickOn(addBtn);
    }
}
