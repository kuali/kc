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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.CopyProposalEvent;
import org.kuali.kra.proposaldevelopment.service.ProposalCopyService;
import org.kuali.rice.KNSServiceLocator;

/**
 * The Proposal Copy Service creates a new Proposal Development Document
 * based upon a current document and criteria specified by a user.
 * 
 * The service uses the following steps in order to copy a proposal:
 * <ol>
 * <li>The Document Service is used to create a new Proposal Development
 *     Document.  By having a new document, its initiator and timestamp
 *     are set correctly and all workflow information is in its initial
 *     state, e.g.  there are no adhoc routes.
 * </li>
 * <li>Most of the properties declared within the ProposalDevelopmentDocument
 *     are copied from the original document to the new document.  Some
 *     properties, such as <b>proposalNumber</b> are filtered out, i.e.
 *     they are not copied during this phase.<br><br>
 *     
 *     The copying of the properties is done via reflection.  Any
 *     property that has a setter and a getter method is copied.
 *     Property values that are <i>Serializable</i> are copied using
 *     <b>ObjectUtils.deepCopy()</b>. Copying by reflection allows the 
 *     copy service to automatically copy any new properties that are added 
 *     to the document without the requiring the programmer to modify this
 *     copy service.
 * </li>
 * <li>The Document Overview properties are copied.  These are the
 *     description, explanation, and organization doc number fields
 *     on the document.  Since they belong to the base document class,
 *     they are not copied in step 2.
 * </li>
 * <li>The LeadUnit is set according to a user's selection.
 * </li>
 * <li>If the attachments are included, they are then copied.  This
 *     includes copying the contents of the attachment.
 * </li>
 * <li>If the budget is included, it is then copied.
 * </li>
 * <li>The document is saved to the database.
 * </li>
 * </ul>
 *
 * The <b>ProposalCopyCriteria</b> contains the user specified criteria, e.g. whether 
 * or not to copy attachments, etc.
 *
 * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
 */
public class ProposalCopyServiceImpl implements ProposalCopyService {
    
    private static final String MODULE_NUMBER = "moduleNumber";
    private static final String PROPOSAL_NUMBER = "proposalNumber";
    
    /**
     * The set of Proposal Development Document properties that
     * must not be copied during step 2.
     */
    private static String[] filteredProperties = { "ProposalNumber",
                                                // "OwnedByUnitNumber",
                                                   "Narratives",
                                                   "Institutes",
                                                   "PropPersonBios" };
    
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

    private BusinessObjectService businessObjectService;

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalCopyService#copyProposal(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria)
     */
    public String copyProposal(ProposalDevelopmentDocument doc, ProposalCopyCriteria criteria) throws Exception {
        String newDocNbr = null;
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new CopyProposalEvent(doc, criteria));
        
        if (rulePassed) {
            
            DocumentService docService = KNSServiceLocator.getDocumentService();
            ProposalDevelopmentDocument newDoc = (ProposalDevelopmentDocument) docService.getNewDocument(doc.getClass());
    
            copyProposal(doc, newDoc, criteria);
            docService.saveDocument(newDoc);
            
            newDocNbr = newDoc.getDocumentNumber();
        }
        
        return newDocNbr;
    }
    
    /**
     * Copies the source proposal development document to the destination document.
     * 
     * @param src the source document, i.e. the original.
     * @param dest the destination document, i.e. the new document.
     * @param criteria the user-specified criteria.
     * @throws Exception if the copy fails for any reason.
     */
    private void copyProposal(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest, ProposalCopyCriteria criteria) throws Exception {
        
        // Copy over the "normal" proposal development document properties, i.e.
        // those that are not filtered.
        
        copyProposalProperties(src, dest);
        
        // Copy over the document overview properties.
        
        copyOverviewProperties(src, dest);
        
        // Copy over the attachments if required by the user.
        
        if (criteria.getIncludeAttachments()) {
            copyAttachments(src, dest);
        }
        
        // Copy over the budget(s) if required by the user.
        
        if (criteria.getIncludeBudget()) {
            copyBudget(src, dest, criteria.getBudgetVersions());
        }
    }

    /**
     * Copies over the "normal" Proposal Development Document properties.
     * Only the properties declared within the ProposalDevelopmentDocument
     * class are copied.  Properties from parent classes are not copied.
     * 
     * @param src the source proposal development document, i.e. the original.
     * @param dest the destination proposal development document, i.e. the new document.
     * @throws Exception if the copy fails for any reason.
     */
    private void copyProposalProperties(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest)  throws Exception {
        //List<DocProperty> properties = getCopyableProperties(new CopyFilter(...));
        List<DocProperty> properties = getCopyableProperties();
        copyProperties(src, dest, properties);
    }
    
    //Or I could use an anonymous filter class???
            
    private void copyProperties(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest, List<DocProperty> properties) throws Exception {
        for (DocProperty property : properties) {
            Object value = property.getter.invoke(src);
            if (value instanceof Serializable) {
                // Just to be careful, we don't want the two documents
                // referencing the same data.  Each must have its own
                // local copies of the data.
                value = ObjectUtils.deepCopy((Serializable) value);
                
                // If this is a persistable business object, its version number
                // must be reset to null.  The OJB framework is responsible for
                // setting the version number for its optimistic locking.  Or in
                // other words, since this is a new object, its version number 
                // cannot be the same as the original it was copied from.
                
                if (value instanceof PersistableBusinessObjectBase) {
                    PersistableBusinessObjectBase obj = (PersistableBusinessObjectBase) value;
                    obj.setVersionNumber(null);
                }
            }
            property.setter.invoke(dest, value);
        }
    }
    
    /**
     * Copies the document overview properties.  These properties are the
     * Description, Explanation, and Organization Document Number.  These
     * properties belong to a parent class and thus they were not copied 
     * over in step 2.
     * 
     * @param src the source proposal development document, i.e. the original.
     * @param dest the destination proposal development document, i.e. the new document.
     */
    private void copyOverviewProperties(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest) {
        DocumentHeader srcHdr = src.getDocumentHeader();
        DocumentHeader destHdr = dest.getDocumentHeader();
        
        destHdr.setFinancialDocumentDescription(srcHdr.getFinancialDocumentDescription());
        destHdr.setExplanation(srcHdr.getExplanation());
        destHdr.setOrganizationDocumentNumber(srcHdr.getOrganizationDocumentNumber());
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
    private List<DocProperty> getCopyableProperties() {
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
        
        // Some properties are services.  They should
        // never be copied.  Ordinarily, services end with
        // "Service".  If this isn't true, the service will
        // have to be added to the filtered list.
        
        if (name.endsWith("Service")) {
            return true;
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
                return method;
            }
        }
        return null;
    }
    
    /**
     * Copy the Attachments (proposal, personal, and institutional) to the new document.
     * 
     * @param src the source proposal development document, i.e. the original.
     * @param dest the destination proposal development document, i.e. the new document.
     */
    private void copyAttachments(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest) throws Exception {
        
        // By default, attachment contents are not read when an attachment is read
        // from the database.  This is for performance reasons due to the size of
        // contents, i.e. they can be PDF files.  In order to perform the copy, we
        // will load the contents into the source document.  The attachments will then
        // be copied when the properties are copied below.
        
        loadAttachmentContents(src);
        
        // Just copy over all of the data and we will make adjustments to it.
        
        List<DocProperty> properties = new ArrayList<DocProperty>();
        properties.add(getDocProperty("Narratives"));
        properties.add(getDocProperty("Institutes"));
        properties.add(getDocProperty("PropPersonBios"));
        copyProperties(src, dest, properties);
        
        // For the first adjustment, the Proposal Attachments must be set to "Incomplete".
        
        setProposalAttachmentsToIncomplete(dest);
         
        // TODO: Also, make sure I handle user rights correctly!!!
    }
    
    /**
     * Load the attachment contents from the database.
     * 
     * @param doc the proposal development document to load attachment contents into.
     */
    private void loadAttachmentContents(ProposalDevelopmentDocument doc) {
        
        // Load personal attachments.
        List<Narrative> narratives = doc.getNarratives();
        for (Narrative narrative : narratives) {
            loadAttachmentContent(narrative);
        }
        
        // Load institutional attachments.
        narratives = doc.getInstitutes();
        for (Narrative narrative : narratives) {
            loadAttachmentContent(narrative);
        }
        
        // Load proposal attachments.
        List<ProposalPersonBiography> bios = doc.getPropPersonBios();
        for (ProposalPersonBiography bio : bios) {
            loadBioContent(bio);
        }
    }
    
    /**
     * Load the attachment content for a specific narrative from the database.
     * 
     * @param narrative the narrative for which to load the contents.
     */
    private void loadAttachmentContent(Narrative narrative){
        Map<String,Integer> primaryKey = new HashMap<String,Integer>();
        primaryKey.put(PROPOSAL_NUMBER, narrative.getProposalNumber());
        primaryKey.put(MODULE_NUMBER, narrative.getModuleNumber());
        NarrativeAttachment attachment = (NarrativeAttachment)businessObjectService.findByPrimaryKey(NarrativeAttachment.class, primaryKey);
        narrative.getNarrativeAttachmentList().clear();
        narrative.getNarrativeAttachmentList().add(attachment);
    }
    
    /**
     * Load the attachment content for a specific personal attachment from the database.
     * 
     * @param bio the personal attachment for which to load the contents.
     */
    private void loadBioContent(ProposalPersonBiography bio){
        Map<String,Integer> primaryKey = new HashMap<String,Integer>();
        primaryKey.put(PROPOSAL_NUMBER, bio.getProposalNumber());
        primaryKey.put("biographyNumber", bio.getBiographyNumber());
        primaryKey.put("proposalPersonNumber", bio.getProposalPersonNumber());
        ProposalPersonBiographyAttachment attachment = (ProposalPersonBiographyAttachment)businessObjectService.findByPrimaryKey(ProposalPersonBiographyAttachment.class, primaryKey);
        bio.getPersonnelAttachmentList().clear();
        bio.getPersonnelAttachmentList().add(attachment);
    }
    
    /**
     * For the new proposal document, it's proposal attachments are required to
     * have their status' initially set to Incomplete.
     * 
     * @param doc the new proposal development document
     */
    private void setProposalAttachmentsToIncomplete(ProposalDevelopmentDocument doc) {
        List<Narrative> narratives = doc.getNarratives();
        for (Narrative narrative : narratives) {
            narrative.setModuleStatusCode("I");
        }
    }
    
    /**
     * Search the Proposal Development Document class for a property with
     * the given name.  The property must have a setter and a getter method.
     * 
     * @param name the name of the property
     * @return the setter/getter pair or null if not found
     */
    private DocProperty getDocProperty(String name) {
        DocProperty docProperty = null;
        Method getter = null;
        Method setter = null;
        Method[] methods = ProposalDevelopmentDocument.class.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals("set" + name)) {
                setter = method;
            }
            else if (method.getName().equals("get" + name)) {
                getter = method;
            }
        }
        if (getter != null && setter != null) {
            docProperty = new DocProperty(getter, setter);
        }
        return docProperty;
    }
    
    /**
     * This method...
     *
     * @param src the source proposal development document, i.e. the original.
     * @param dest the destination proposal development document, i.e. the new document.
     * @param budgetVersions
     */
    private void copyBudget(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest, String budgetVersions) {
        // TODO Auto-generated method stub
        
    }
    
    /**
     * Set the Business Object Service.  It is set via dependency injection.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    /**
     * Get the Kuali Rule Service.
     * 
     * @return the Kuali Rule Service
     */
    private KualiRuleService getKualiRuleService() {
        return KraServiceLocator.getService(KualiRuleService.class);
    }
}
