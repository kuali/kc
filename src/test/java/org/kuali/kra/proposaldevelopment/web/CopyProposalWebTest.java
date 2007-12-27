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
package org.kuali.kra.proposaldevelopment.web;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;

import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CopyProposalWebTest extends ProposalDevelopmentWebTestBase {
    /**
     * The set of Proposal Development Document properties that
     * must not be copied.
     */
    private static String[] filteredProperties = { "ProposalNumber",
                                                   "OwnedByUnitNumber",
                                                   "Narratives",
                                                   "InstituteAttachments",
                                                   "PropPersonBios",
                                                   "ProposalPersonBiographyService",
                                                   "NarrativeService"};
    
    private static final String ATTACHMENT_FILENAME = "/org/kuali/kra/proposaldevelopment/web/ProposalAttachmentWebTest.class";

    private static final String NARRATIVE_TYPE_CODE_ID = "newNarrative.narrativeTypeCode";
    private static final String NARRATIVE_FILE_ID = "newNarrative.narrativeFile";
    private static final String NARRATIVE_STATUS_ID = "newNarrative.moduleStatusCode";
    private static final String COPY_ATTACHMENTS_ID = "copyCriteria.includeAttachments";
    
    private static final String PERSONAL_PERSON_ID = "newPropPersonBio.proposalPersonNumber";
    private static final String PERSONAL_ATTACHMENT_TYPE_ID = "newPropPersonBio.documentTypeCode";
    private static final String PERSONAL_FILE_ID = "newPropPersonBio.personnelAttachmentFile";
    
    private static final String INSTITUTE_TYPE_CODE_ID = "newInstituteAttachment.institutionalAttachmentTypeCode";
    private static final String INSTITUTE_FILE_ID = "newInstituteAttachment.narrativeFile";
    
    private static final String COPY_PROPOSAL_BTN = "methodToCall.copyProposal";
    
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
     * Do a proposal copy without copying any of the attachments.  A document will
     * be created with some attachments.  When the original document and the
     * copied document are compared, everything should be the same except for
     * the attachments.  The copied version should have none.
     * 
     * @throws Exception
     */
    @Test
    public void testSimpleCopy() throws Exception {
        
        HtmlPage actionsPage = buildDocument();
        
        ProposalDevelopmentDocument srcDoc = getProposalDevelopmentDocument(actionsPage);
        
        setFieldValue(actionsPage, COPY_ATTACHMENTS_ID, "off");
        
        ProposalDevelopmentDocument destDoc = copyDocument(actionsPage);
       
        ProposalCopyCriteria criteria = new ProposalCopyCriteria();
        criteria.setIncludeAttachments(false);
        assertTrue(compareDocuments(srcDoc, destDoc, criteria));
    }
    
    /**
     * Do a proposal copy and also include the attachments in the copy.  A document
     * will be created with some attachments.  When the original and the copied
     * documents are compared, those attachments must match.
     * 
     * @throws Exception
     */
    @Test
    public void testAttachmentCopy() throws Exception {
      
        HtmlPage actionsPage = buildDocument();
        
        ProposalDevelopmentDocument srcDoc = getProposalDevelopmentDocument(actionsPage);
        
        setFieldValue(actionsPage, COPY_ATTACHMENTS_ID, "on");
        
        ProposalDevelopmentDocument destDoc = copyDocument(actionsPage);
       
        ProposalCopyCriteria criteria = new ProposalCopyCriteria();
        criteria.setIncludeAttachments(true);
        assertTrue(compareDocuments(srcDoc, destDoc, criteria));
    }
    
    /**
     * Builds a Proposal Development Document.  Adds several attachments to
     * the document.
     * 
     * NOTE: A better test of the copy feature would add other items to the
     *       documents, e.g. add some abstracts.
     *       
     * @return the Actions web page containing the document.
     * @throws Exception
     */
    private HtmlPage buildDocument() throws Exception {
        HtmlPage attachmentsPage = this.getAbstractsAndAttachmentsPage();
        addProposalAttachment(attachmentsPage);
        addProposalAttachment(attachmentsPage);
        addPersonalAttachment(attachmentsPage);
        addInstituteAttachment(attachmentsPage);
        return clickActionsHyperlink(attachmentsPage);
    }
    
    /**
     * Get a Proposal Development Document from the database.
     * 
     * @param page the HTML page containing the document.
     * @return the proposal development document.
     * @throws Exception
     */
    private ProposalDevelopmentDocument getProposalDevelopmentDocument(HtmlPage page) throws Exception {
        String docNbr = getDocNbr(page);
        return (ProposalDevelopmentDocument) this.getDocument(docNbr);
    }
    
    /**
     * Copies a Proposal Development Document.
     * 
     * @param actionsPage the Actions web page containing the copy feature.
     * @return the new copied document.
     * @throws Exception
     */
    private ProposalDevelopmentDocument copyDocument(HtmlPage actionsPage) throws Exception {
        HtmlElement btn = this.getElementByName(actionsPage, COPY_PROPOSAL_BTN, true);
        HtmlPage proposalPage = this.clickOn(btn);
        
        String docNbr = this.getDocNbr(proposalPage);
        return (ProposalDevelopmentDocument) this.getDocument(docNbr);
    }
    
    /**
     * Compare the original (source) proposal development document with its copy (destination).
     * 
     * @param srcDoc the original document
     * @param destDoc the copied document
     * @param criteria the criteria used to copy the document.
     * @return true if the copied document is valid; otherwise false.
     * @throws Exception
     */
    private boolean compareDocuments(ProposalDevelopmentDocument srcDoc, ProposalDevelopmentDocument destDoc, ProposalCopyCriteria criteria) throws Exception {
       
        // They had better have different document numbers.
        
        String nbr1 = srcDoc.getDocumentNumber();
        String nbr2 = destDoc.getDocumentNumber();
        if (isSame(nbr1, nbr2)) {
            return false;
        }
        
        // The must have the same document description.
        
        String desc1 = srcDoc.getDocumentHeader().getFinancialDocumentDescription();
        String desc2 = srcDoc.getDocumentHeader().getFinancialDocumentDescription();
        if (!isSame(desc1, desc2)) {
            return false;
        }
        
        // They must have the same organization document number.
        
        String org1 = srcDoc.getDocumentHeader().getOrganizationDocumentNumber();
        String org2 = srcDoc.getDocumentHeader().getOrganizationDocumentNumber();
        if (!isSame(org1, org2)) {
            return false;
        }
        
        // Compare the document's properties.  They must be the same.
        
        List<DocProperty> properties = getComparableProperties();
        for (DocProperty property : properties) {
            Object value1 = property.getter.invoke(srcDoc);
            Object value2 = property.getter.invoke(destDoc);
            if (!equals(value1, value2)) {
                return false;
            }
        }
        
        if (criteria.getIncludeAttachments()) {
            
            // If the attachments are copied, they had better be the same.
            
            if (!equals(srcDoc.getNarratives(), destDoc.getNarratives())) {
                return false;
            }
            
            if (!equals(srcDoc.getInstituteAttachments(), destDoc.getInstituteAttachments())) {
                return false;
            }
            
            if (!equals(srcDoc.getPropPersonBios(), destDoc.getPropPersonBios())) {
                return false;
            }
        } else {
            
            // If the attachments are not copied, there must not be any.
            
            if (destDoc.getNarratives().size() != 0) {
                return false;
            } else if (destDoc.getInstituteAttachments().size() != 0) {
                return false;
            } else if (destDoc.getPropPersonBios().size() != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Get the list of Proposal Development Document properties that can be copied.
     * A property can only be copied if it meets the following criteria.
     * <ul>
     * <li>It was declared in the <b>ProposalDevelopmentDocument</b> class.</li>
     * <li>It has a setter and a getter method.</li>
     * <li>It is not a filtered property.</li>
     * </ul>
     * 
     * @return the list of properties that can be copied.
     */
    private List<DocProperty> getComparableProperties() {
        List<DocProperty> list = new ArrayList<DocProperty>();
        
        Method[] methods = ProposalDevelopmentDocument.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {      
                String name = getPropertyName(method);
                if (!isFilteredProperty(name)) {
                    Method getter = getGetter(name, methods);         
                    if (getter != null) {   
                        list.add(new DocProperty(getter, method));
                    }
                }
            }
        }
        
        return list;
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
            if (name.equals(filteredProperty)) {
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
    private boolean equals(Object obj1, Object obj2) throws Exception {
        boolean isEqual = false;
        
        if (obj1 == null && obj2 == null) {
            isEqual = true;
        }
        else if (obj1 == null || obj2 == null) {
            isEqual = false;
        }
        else if (!obj1.getClass().equals(obj2.getClass())) {
            isEqual = false;
        }
        else {
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
     * Compare two collections to see if they are equal or not.
     * The contents of the collections don't need to be in the same
     * order.
     * 
     * @param c1 collection 1
     * @param c2 collection 2
     * @return true if equal; otherwise false
     * @throws Exception
     */
    private boolean compareCollections(Collection c1, Collection c2) throws Exception {
        if (c1.size() != c2.size()) {
            return false;
        } else if (c1.size() != 0) {
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
    private List<DocProperty> getComparableProperties(Class clazz) {
        List<DocProperty> list = new ArrayList<DocProperty>();
        
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {      
                String name = getPropertyName(method);
                Method getter = getGetter(name, methods);         
                if (getter != null) {   
                    if (!hasIdProperty(clazz, name) && !isExcludedMethod(getter.getName())) {
                        list.add(new DocProperty(getter, method));
                    }
                }
            }
        }
        
        return list;
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
     * Is this an excluded method?  Some properties should never be compared since
     * they will always be different between two business objects.  For example,
     * timestamps will always be different.  This method identifies those property
     * getter methods that must be excluded from the comparison.
     * 
     * @param methodName
     * @return
     */
    private boolean isExcludedMethod(String methodName) {
        // TODO : getUnit() in UnitAdministrator caused infinite loop.  Excluded it here  ??
        String[] names = { "getUpdateTimestamp",
                           "getUpdateUser",
                           "getProposalNumber",
                           "getVersionNumber",
                           "getBusinessObjectService",
                           "getDateTimeService",
                           "getNarrativeAuthZService",
                           "getNarrativeService",
                           "getProposalPersonBiographyService",
                           "getObjectId",
                           "getUnit"};
        
        for (String name : names) {
            if (methodName.equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Determines if two strings are the same or not.  Two
     * strings are the same if both are null or they have the
     * same string value.
     * 
     * @param s1 string 1
     * @param s2 string 2
     * @return true if the same; otherwise false
     */
    private boolean isSame(String s1, String s2) {
        if (s1 == null && s2 != null) {
            return false;
        }
        else if (s1 != null && s2 == null) {
            return false;
        }
        else if (s1 != null && !s1.equals(s2)) {
            return false;
        }
        return true;
    }
    
    /**
     * Add a Proposal Attachment to the document.
     * 
     * @param attachmentsPage the Abstracts & Attachments web page
     */
    private void addProposalAttachment(HtmlPage attachmentsPage) {
        setFieldValue(attachmentsPage, NARRATIVE_TYPE_CODE_ID, "4");
        setFieldValue(attachmentsPage, NARRATIVE_STATUS_ID, "I");
        setFieldValue(attachmentsPage, NARRATIVE_FILE_ID, getFilePath());
    }
    
    /**
     * Add a Personal Attachment to the document.
     * 
     * @param attachmentsPage the Abstracts & Attachments web page
     */
    private void addPersonalAttachment(HtmlPage attachmentsPage) {
        selectAnyOption(attachmentsPage, NARRATIVE_TYPE_CODE_ID);
        selectAnyOption(attachmentsPage, PERSONAL_ATTACHMENT_TYPE_ID);
        setFieldValue(attachmentsPage, PERSONAL_FILE_ID, getFilePath());
    }
    
    /**
     * Add an Institute Attachment to the document.
     * 
     * @param attachmentsPage the Abstracts & Attachments web page
     */
    private void addInstituteAttachment(HtmlPage attachmentsPage) {
        selectAnyOption(attachmentsPage, INSTITUTE_TYPE_CODE_ID);
        setFieldValue(attachmentsPage, INSTITUTE_FILE_ID, getFilePath());
    }
    
    /**
     * Get the path to a file.  That file will be attached to the document.
     * @return
     */
    private String getFilePath() {
        URL fileUrl = getClass().getResource(ATTACHMENT_FILENAME);
        assertNotNull(fileUrl);
        return fileUrl.getPath();
    }
}
