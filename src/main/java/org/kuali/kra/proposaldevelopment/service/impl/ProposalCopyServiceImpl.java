/*
 * Copyright 2006-2009 The Kuali Foundation
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
package org.kuali.kra.proposaldevelopment.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.kuali.core.bo.BusinessObject;
import org.kuali.core.bo.DocumentHeader;
import org.kuali.core.bo.PersistableBusinessObject;
import org.kuali.core.bo.PersistableBusinessObjectBase;
import org.kuali.core.bo.user.UniversalUser;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.service.KualiRuleService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.core.util.ObjectUtils;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.bo.BudgetModular;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetProjectIncome;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.NarrativeRight;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.rule.event.CopyProposalEvent;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.proposaldevelopment.service.ProposalCopyService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.kra.service.PersonService;
import org.kuali.kra.service.UnitService;
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
                                                   "OwnedByUnitNumber",
                                                   "OwnedByUnit",
                                                   "Narratives",
                                                   "InstituteAttachments",
                                                   "PropPersonBios",
                                                   "BudgetVersionOverviews",
                                                   "SubmitFlag",
                                                   "ProposalStateTypeCode",
                                                   "ProposalState" };
    
    private static String forceCopyProperty = "documentNextvalues";
    
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
    private KeyPersonnelService keyPersonnelService;
    private DocumentService documentService;
    private PersonService personService;
    private KualiConfigurationService kualiConfigurationService;

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalCopyService#copyProposal(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria)
     */
    public String copyProposal(ProposalDevelopmentDocument doc, ProposalCopyCriteria criteria) throws Exception {
        String newDocNbr = null;
        
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new CopyProposalEvent(doc, criteria));
        
        if (rulePassed) {
            
            ProposalDevelopmentDocument newDoc = createNewProposal(doc, criteria);
            
            copyProposal(doc, newDoc, criteria);
            fixProposal(doc, newDoc, criteria);

            DocumentService docService = KNSServiceLocator.getDocumentService();
            docService.saveDocument(newDoc);
            
            // Can't initialize authorization until a proposal is saved
            // and we have a new proposal number.
            initializeAuthorization(newDoc);
            
            if (criteria.getIncludeAttachments()) {
                copyAttachments(doc, newDoc);
            }
            
//          Copy over the budget(s) if required by the user.  newDoc must be saved so we know proposal number.
            if (criteria.getIncludeBudget()) {
                copyBudget(doc, newDoc, criteria.getBudgetVersions());
            }

            newDocNbr = newDoc.getDocumentNumber();
        }
        
        return newDocNbr;
    }
    
    /**
     * Create a new proposal based upon a source proposal.  Only copy over the
     * properties necessary for the initial creation of the proposal.  This will
     * give us the proposal number to use when copying over the remainder of the
     * proposal.
     * @param srcDoc
     * @param criteria
     * @return
     * @throws Exception
     */
    private ProposalDevelopmentDocument createNewProposal(ProposalDevelopmentDocument srcDoc, ProposalCopyCriteria criteria) throws Exception {
        DocumentService docService = KNSServiceLocator.getDocumentService();
        ProposalDevelopmentDocument newDoc = (ProposalDevelopmentDocument) docService.getNewDocument(srcDoc.getClass());
        
        // Copy over the document overview properties.
        
        copyOverviewProperties(srcDoc, newDoc);
        
        copyRequiredProperties(srcDoc, newDoc);
        
        // Set lead unit.
        
        setLeadUnit(newDoc, criteria.getLeadUnitNumber());
        
        newDoc.getDocumentHeader().setFinancialDocumentTemplateNumber(srcDoc.getDocumentNumber());
        docService.saveDocument(newDoc);
        
        return newDoc;
    }
    
    /**
     * Copy over the required properties so we can do an initial save of the document
     * in order to obtain a proposal number.
     * @param srcDoc
     * @param destDoc
     */
    private void copyRequiredProperties(ProposalDevelopmentDocument srcDoc, ProposalDevelopmentDocument destDoc) {
        destDoc.getDocumentHeader().setFinancialDocumentDescription(srcDoc.getDocumentHeader().getFinancialDocumentDescription());
        destDoc.setProposalTypeCode(srcDoc.getProposalTypeCode());
        destDoc.setActivityTypeCode(srcDoc.getActivityTypeCode());
        destDoc.setTitle(srcDoc.getTitle());
        destDoc.setSponsorCode(srcDoc.getSponsorCode());
        destDoc.setRequestedStartDateInitial(srcDoc.getRequestedStartDateInitial());
        destDoc.setRequestedEndDateInitial(srcDoc.getRequestedEndDateInitial());
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
        List<DocProperty> properties = getCopyableProperties();
        
        //We need to copy DocumentNextValues to properly handle copied collections
        String capitalizedPropertyName = StringUtils.capitalize(forceCopyProperty);
        Method getDocumentNextValues = getGetter(capitalizedPropertyName, ProposalDevelopmentDocument.class.getSuperclass().getDeclaredMethods());
        Method setDocumentNextValues = getSetter(capitalizedPropertyName, ProposalDevelopmentDocument.class.getSuperclass().getDeclaredMethods());
        DocProperty documentNextValues = new DocProperty(getDocumentNextValues, setDocumentNextValues);
        properties.add(documentNextValues);
        
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
                        if ((getter.getParameterTypes().length == 0) &&
                            (method.getParameterTypes().length == 1)) {
                            list.add(new DocProperty(getter, method));
                        }
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
     * Gets the setter method for a property.
     * 
     * @param name the name of the property.
     * @param methods the list of methods to look in for the getter method.
     * @return the getter method or null if not found.
     */
    private Method getSetter(String name, Method[] methods) {
        String setter = "set" + name;
        for (Method method : methods) {
            if (setter.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }
        
    /**
     * Set the lead unit for the new proposal.
     * @param doc the new proposal development document
     * @param newLeadUnitNumber the new lead unit number
     */
    private void setLeadUnit(ProposalDevelopmentDocument doc, String newLeadUnitNumber) {
        UnitService unitService = KraServiceLocator.getService(UnitService.class);
        Unit newLeadUnit = unitService.getUnit(newLeadUnitNumber);
        doc.setOwnedByUnitNumber(newLeadUnitNumber);
        doc.setOwnedByUnit(newLeadUnit);
    }
        
    /**
     * Fix the proposal.
     * @param criteria the copy criteria
     * @throws Exception 
     */
    private void fixProposal(ProposalDevelopmentDocument srcDoc, ProposalDevelopmentDocument newDoc, ProposalCopyCriteria criteria) throws Exception {
        List<Object> list = new ArrayList<Object>();
        // force to materialize - jira 1644 only happen for disapproved doc ??
        for (ProposalPerson proposalperson : newDoc.getProposalPersons()) {
            for (ProposalPersonUnit proposalPersonUnit : proposalperson.getUnits()) {
                ObjectUtils.materializeObjects(proposalPersonUnit.getCreditSplits());
            }
        }

        fixProposalNumbers(newDoc, newDoc.getProposalNumber(), list);
        fixKeyPersonnel(newDoc, srcDoc.getOwnedByUnitNumber(), criteria.getLeadUnitNumber());
        fixOrganizationAndLocations(newDoc);
        list.clear();
        fixVersionNumbers(newDoc, list);
        fixBudgetVersions(newDoc);
    }

    /**
     * If the Lead Unit has changed in the previous {@link Document}, then this method corrects related
     * properties {@link Organization} and {@link ProposalLocation} instances
     *
     * @param doc {@link ProposalDevelopmentDocument} to fix
     */
    private void fixOrganizationAndLocations(ProposalDevelopmentDocument doc) {
        doc.setOrganizationId(doc.getOwnedByUnit().getOrganizationId());
        doc.refreshReferenceObject("organization");
        doc.setPerformingOrganizationId(doc.getOwnedByUnit().getOrganizationId());
        doc.refreshReferenceObject("performingOrganization");
        
        // Remove the first Location because it's probably the old one.
        Integer firstProposalLocationSeqeunceNumber = null;
        if (doc.getProposalLocations().size() > 0) {
            ProposalLocation proposalLocation = doc.getProposalLocations().get(0);
            firstProposalLocationSeqeunceNumber = proposalLocation.getLocationSequenceNumber();
            doc.getProposalLocations().remove(proposalLocation);
        }
  
        // re-initialize Proposal Locations with Organization details
        ProposalLocation newProposalLocation = new ProposalLocation();
        newProposalLocation.setLocation(doc.getOrganization().getOrganizationName());
        newProposalLocation.setRolodexId(doc.getOrganization().getContactAddressId());
        newProposalLocation.refreshReferenceObject("rolodex");
        if(firstProposalLocationSeqeunceNumber == null || firstProposalLocationSeqeunceNumber.intValue() <= 0) {
            firstProposalLocationSeqeunceNumber = doc.getDocumentNextValue(Constants.PROPOSAL_LOCATION_SEQUENCE_NUMBER);
        }
        newProposalLocation.setLocationSequenceNumber(firstProposalLocationSeqeunceNumber);
        doc.getProposalLocations().add(0, newProposalLocation);
    }
    
    /**
     * Recurse through all of the BOs and if a BO has a ProposalNumber property,
     * set its value to the new proposal number.
     * @param object the object
     * @param proposalNumber the proposal number
     */
    private void fixProposalNumbers(Object object, String proposalNumber, List<Object> list) throws Exception {
        if (object instanceof BusinessObject) {
            if (list.contains(object)) return;
            list.add(object);
            Method[] methods = object.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals("setProposalNumber")) {
                    method.invoke(object, proposalNumber);
                } else if (isPropertyGetterMethod(method, methods)) {
                    Object value = method.invoke(object);
                    if (value instanceof Collection) {
                        Collection c = (Collection) value;
                        Iterator iter = c.iterator();
                        while (iter.hasNext()) {
                            Object entry = iter.next();
                            fixProposalNumbers(entry, proposalNumber, list);
                        }
                    } else {
                        fixProposalNumbers(value, proposalNumber, list);
                    }   
                }
            }
        }
    }
    
    /**
     * Recurse through all of the BOs and reset all of the Version Number
     * properties to null.  Note that the version number for the top-level
     * document must be left as is.
     * @param object the object
     */
    private void fixVersionNumbers(Object object, List<Object> list) throws Exception {
        
        if (object instanceof BusinessObject) {
            if (list.contains(object)) return;
            list.add(object);
            Method[] methods = object.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals("setVersionNumber")) {
                    if (!(object instanceof ProposalDevelopmentDocument)) {
                        method.invoke(object, (Long) null);
                    }
                    break;
                }
            }
            methods = object.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (isPropertyGetterMethod(method, methods)) {
                    Object value = method.invoke(object);
                    if (value instanceof Collection) {
                        Collection c = (Collection) value;
                        Iterator iter = c.iterator();
                        while (iter.hasNext()) {
                            Object entry = iter.next();
                            fixVersionNumbers(entry, list);
                        }
                    } else {
                        fixVersionNumbers(value, list);
                    }   
                }
            }
        }
    }
    
    /**
     * Is the given method a getter method for a property?  Must conform to
     * the following:
     * <ol>
     * <li>Must start with the <b>get</b></li>
     * <li>Must have a corresponding setter method</li>
     * <li>Must have zero arguments.</li>
     * </ol>
     * @param method the method to check
     * @param methods the other methods in the object
     * @return true if it is property getter method; otherwise false
     */
    private boolean isPropertyGetterMethod(Method method, Method methods[]) {
        if (method.getName().startsWith("get") && method.getParameterTypes().length == 0) {
            String setterName = method.getName().replaceFirst("get", "set");
            for (Method m : methods) {
                if (m.getName().equals(setterName)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Fix the Key Personnel.
     * @param doc the proposal development document
     * @param oldLeadUnitNumber the old lead unit number
     * @param newLeadUnitNumber the new lead unit number
     * @throws Exception 
     */
    private void fixKeyPersonnel(ProposalDevelopmentDocument doc, String oldLeadUnitNumber, String newLeadUnitNumber) throws Exception {
        clearCertifyQuestions(doc);
        fixKeyPersonnelUnits(doc, oldLeadUnitNumber, newLeadUnitNumber);
    }
    
    /**
     * Fix data related to Budget Versions.
     * @param doc the proposal development document
     * @param oldLeadUnitNumber the old lead unit number
     * @param newLeadUnitNumber the new lead unit number
     */
    private void fixBudgetVersions(ProposalDevelopmentDocument doc) {
        if (doc.getBudgetVersionOverviews().size() > 0) {
            String budgetStatusIncompleteCode = kualiConfigurationService.getParameterValue(
                    Constants.PARAMETER_MODULE_BUDGET, Constants.PARAMETER_COMPONENT_DOCUMENT, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
            
            doc.setBudgetStatus(budgetStatusIncompleteCode);
        }
    }
    
    /**
     * Clear the Certify questions for each investigator.
     * @param doc the Proposal Development Document
     */
    private void clearCertifyQuestions(ProposalDevelopmentDocument doc) {
        List<ProposalPerson> persons = doc.getProposalPersons();
        for (ProposalPerson person : persons) {
            ProposalPersonRole role = person.getRole();
            String roleId = role.getProposalPersonRoleId();
            if ((StringUtils.equals(roleId, Constants.PRINCIPAL_INVESTIGATOR_ROLE)) || 
                (StringUtils.equals(roleId, Constants.CO_INVESTIGATOR_ROLE))) {
                
                List<ProposalPersonYnq> questions = person.getProposalPersonYnqs();
                for (ProposalPersonYnq question : questions) {
                    question.setAnswer(null);
                    question.setDummyAnswer(null);
                }
            }
        }
    }
    
    /**
     * Fix the Key Personnel.  This requires changing the lead unit for the PI
     * and the COIs to the new lead unit.  Also, if the PI's home unit is not in
     * the list, we must add it.
     * @param doc the proposal development document
     * @param oldLeadUnitNumber the old lead unit number
     * @param newLeadUnitNumber the new lead unit number
     * @throws Exception 
     */
    private void fixKeyPersonnelUnits(ProposalDevelopmentDocument doc, String oldLeadUnitNumber, String newLeadUnitNumber) throws Exception {
       
        List<ProposalPerson> persons = doc.getProposalPersons();
        for (ProposalPerson person : persons) {
            person.setProposalNumber(null);
           
            ProposalPersonRole role = person.getRole();
            String roleId = role.getProposalPersonRoleId();
            
            if (StringUtils.equals(roleId, Constants.PRINCIPAL_INVESTIGATOR_ROLE)) {
                
                List<ProposalPersonUnit> proposalPersonUnits = person.getUnits();
                List<ProposalPersonUnit> newProposalPersonUnits = new ArrayList<ProposalPersonUnit>();
                
                ProposalPersonUnit unit = createProposalPersonUnit(person, newLeadUnitNumber, true, false, proposalPersonUnits);
                newProposalPersonUnits.add(unit);
                
                String homeUnitNumber = person.getHomeUnit();
                if (!StringUtils.equals(newLeadUnitNumber, homeUnitNumber)) {
                    unit = createProposalPersonUnit(person, homeUnitNumber, false, true, proposalPersonUnits);
                    if (unit != null) {
                        newProposalPersonUnits.add(unit);
                    }
                }
                
                for (ProposalPersonUnit oldUnit : proposalPersonUnits) {
                    String oldUnitNumber = oldUnit.getUnitNumber();
                    if (!StringUtils.equals(newLeadUnitNumber, oldUnitNumber) &&
                        !StringUtils.equals(homeUnitNumber, oldUnitNumber) && 
                        !StringUtils.equals(oldLeadUnitNumber, oldUnitNumber)) {
                        
                        unit = createProposalPersonUnit(person, oldUnitNumber, false, true, proposalPersonUnits);
                        newProposalPersonUnits.add(unit);
                    }
                }
                
                person.setUnits(newProposalPersonUnits);  
            }
            
            for (ProposalPersonYnq ynq : person.getProposalPersonYnqs()) {
                ynq.setAnswer(null);
            }
        }
        doc.setInvestigators(new ArrayList<ProposalPerson>());
        keyPersonnelService.populateDocument(doc);
    }
    
    private ProposalPersonUnit createProposalPersonUnit(ProposalPerson person, String unitNumber, boolean isLeadUnit, boolean isDeletable, List<ProposalPersonUnit> oldProposalPersonUnits) {
        ProposalPersonUnit proposalPersonUnit = keyPersonnelService.createProposalPersonUnit(unitNumber, person);
        if (proposalPersonUnit.getUnitNumber() == null) {
            return null;
        }
        proposalPersonUnit.setLeadUnit(isLeadUnit);
        proposalPersonUnit.setDelete(isDeletable);
        proposalPersonUnit.setVersionNumber(null);
        
        ProposalPersonUnit oldProposalPersonUnit = findProposalPersonUnit(unitNumber, oldProposalPersonUnits);
        if (oldProposalPersonUnit != null) {
            List<ProposalUnitCreditSplit> newUnitCreditSplits = new ArrayList<ProposalUnitCreditSplit>();
            List<ProposalUnitCreditSplit> oldUnitCreditSplits = oldProposalPersonUnit.getCreditSplits();
            for (ProposalUnitCreditSplit oldUnitCreditSplit : oldUnitCreditSplits) {
                ProposalUnitCreditSplit newUnitCreditSplit = (ProposalUnitCreditSplit) ObjectUtils.deepCopy(oldUnitCreditSplit);
                newUnitCreditSplit.setVersionNumber(null);
                newUnitCreditSplits.add(newUnitCreditSplit);
            }
            proposalPersonUnit.setCreditSplits(newUnitCreditSplits);
        }
        
        return proposalPersonUnit;
    }
    
    private ProposalPersonUnit findProposalPersonUnit(String unitNumber, List<ProposalPersonUnit> proposalPersonUnits) {
        for (ProposalPersonUnit proposalPersonUnit : proposalPersonUnits) {
            if (StringUtils.equals(unitNumber, proposalPersonUnit.getUnitNumber())) {
                return proposalPersonUnit;
            }
        }
        return null;
    }
    
    /**
     * Initialize the Authorizations for a new proposal.  The initiator/creator
     * is assigned the Aggregator role.
     * @param doc the proposal development document
     */
    private void initializeAuthorization(ProposalDevelopmentDocument doc) {
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        proposalAuthService.addRole(username, RoleConstants.AGGREGATOR, doc);
    }
    
    /**
     * Copy the Attachments (proposal, personal, and institutional) to the new document.  Does this
     * by loading the actual attachments (since they are left out of the object graph under normal
     * conditions, then copies the attachments (ProposalPersonBiographies, Narratives, and 
     * InstituteAttachments).
     * 
     * @param src the source proposal development document, i.e. the original.
     * @param dest the destination proposal development document, i.e. the new document.
     */
    private void copyAttachments(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest) throws Exception {
        
        NarrativeService narrativeService = dest.getNarrativeService();
        ProposalPersonBiographyService propPersonBioService = dest.getProposalPersonBiographyService();
 
        loadAttachmentContents(src);
        
        List<ProposalPersonBiography> propPersonBios = src.getPropPersonBios();
        ProposalPersonBiography destPropPersonBio;
        for (ProposalPersonBiography srcPropPersonBio : propPersonBios) {
            destPropPersonBio = (ProposalPersonBiography)ObjectUtils.deepCopy(srcPropPersonBio);
            propPersonBioService.addProposalPersonBiography(dest, destPropPersonBio);
        }

        List<Narrative> narratives = src.getNarratives();
        Narrative destNarrative;
        for (Narrative srcNarrative : narratives) {
            destNarrative = (Narrative)ObjectUtils.deepCopy(srcNarrative);
            narrativeService.addNarrative(dest, destNarrative);
        }
        
        List<Narrative> instituteAttachments = src.getInstituteAttachments();
        Narrative destInstituteAttachment;
        for (Narrative srcInstituteAttachment : instituteAttachments) {
            destInstituteAttachment = (Narrative)ObjectUtils.deepCopy(srcInstituteAttachment);
            narrativeService.addInstituteAttachment(dest, destInstituteAttachment);
        }
        
        setProposalAttachmentsToIncomplete(dest);
    }
    
    /**
     * Copy a list of narratives.  The only narratives that are
     * copied are those that the user has read or modify access.
     * @param narratives the narratives to copy
     * @return the copied narratives
     */
    private List<Narrative> copyNarratives(List<Narrative> narratives, int moduleNumber) {
        UniversalUser user = GlobalVariables.getUserSession().getUniversalUser();
        String username = user.getPersonUserIdentifier();
        Person person = personService.getPersonByName(username);
        
        List<Narrative> newNarratives = new ArrayList<Narrative>();
        for (Narrative narrative : narratives) {
            if (hasReadPermission(person, narrative)) {
                newNarratives.add(copyNarrative(person, narrative, moduleNumber++));
            }
        }
        return newNarratives;
    }
    
    /**
     * Does the person have permission to read this narrative?  The
     * person can read the narrative if they have read or modify access.
     * @param person the person
     * @param narrative the narrative
     * @return true if read permission; otherwise false
     */
    private boolean hasReadPermission(Person person, Narrative narrative) {
        List<NarrativeUserRights> userRightsList = narrative.getNarrativeUserRights();
        for (NarrativeUserRights userRights : userRightsList) {
            if (StringUtils.equals(userRights.getUserId(), person.getPersonId())) {
                if (userRights.getAccessType().equals(NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType())) {
                    return true;
                }
                else if (userRights.getAccessType().equals(NarrativeRight.VIEW_NARRATIVE_RIGHT.getAccessType())) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    /**
     * Copy a narrative.  The narrative rights are also reset to reflect that the
     * person doing the copying is the only person who has access and that he/she
     * has modify access.
     * @param person the person
     * @param narrative the narrative
     * @param moduleNumber the narratives new module number
     * @return the copied narrative
     */
    private Narrative copyNarrative(Person person, Narrative narrative, int moduleNumber) {
        Narrative newNarrative = (Narrative) ObjectUtils.deepCopy(narrative);
        newNarrative.setModuleNumber(moduleNumber);
        NarrativeUserRights userRights = new NarrativeUserRights();
        userRights.setAccessType(NarrativeRight.MODIFY_NARRATIVE_RIGHT.getAccessType());
        userRights.setUserId(person.getPersonId());
        userRights.setPersonName(person.getFullName());
        List<NarrativeUserRights> userRightsList = new ArrayList<NarrativeUserRights>();
        userRightsList.add(userRights);
        newNarrative.setVersionNumber(null);
        newNarrative.setNarrativeUserRights(userRightsList);
        return newNarrative;
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
        narratives = doc.getInstituteAttachments();
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
        Map<String,String> primaryKey = new HashMap<String,String>();
        primaryKey.put(PROPOSAL_NUMBER, narrative.getProposalNumber());
        primaryKey.put(MODULE_NUMBER, narrative.getModuleNumber()+"");
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
        Map<String,String> primaryKey = new HashMap<String,String>();
        primaryKey.put(PROPOSAL_NUMBER, bio.getProposalNumber());
        primaryKey.put("biographyNumber", bio.getBiographyNumber()+"");
        primaryKey.put("proposalPersonNumber", bio.getProposalPersonNumber()+"");
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
            if ((getter.getParameterTypes().length == 0) &&
                (setter.getParameterTypes().length == 1)) {
                docProperty = new DocProperty(getter, setter);
            }
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
    private void copyBudget(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest, String budgetVersions) throws Exception {
        if (budgetVersions.equals(ProposalCopyCriteria.BUDGET_FINAL_VERSION)) {
            BudgetVersionOverview finalBudgetVersion = src.getFinalBudgetVersion();
            if (finalBudgetVersion != null) {
                copyAndFinalizeBudgetVersion(finalBudgetVersion.getDocumentNumber(), dest, 1);
            }
        } else if (budgetVersions.equals(ProposalCopyCriteria.BUDGET_ALL_VERSIONS)) {
            int i = 1;
            for (BudgetVersionOverview budgetVersionOverview: src.getBudgetVersionOverviews()) {
                copyAndFinalizeBudgetVersion(budgetVersionOverview.getDocumentNumber(), dest, i++);
            }
        }
        
    }
    
    private void copyAndFinalizeBudgetVersion(String documentNumber, ProposalDevelopmentDocument dest, int budgetVersionNumber) throws Exception {
        BudgetDocument budget = (BudgetDocument) documentService.getByDocumentHeaderId(documentNumber);
        budget.getProposal().setBudgetVersionOverviews(new ArrayList<BudgetVersionOverview>());
        Integer origBudgetVersionNumber = budget.getBudgetVersionNumber();
        budget.toCopy();
        // budget.tocopy set the budgetversionnumber to the new one
        // need to rest it so setobjectpropertydeep can work for 'budgetversionnuumber'
        budget.setBudgetVersionNumber(origBudgetVersionNumber);
        ObjectUtils.setObjectPropertyDeep(budget, "proposalNumber", String.class, dest.getProposalNumber());
        ObjectUtils.setObjectPropertyDeep(budget, "budgetVersionNumber", Integer.class, budgetVersionNumber);
        
        ObjectUtils.materializeAllSubObjects(budget);

        Map<String, Object> objectMap = new HashMap<String, Object>();
        fixNumericProperty(budget, "setBudgetPeriodId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budget, "setVersionNumber", Long.class, new Long(0), objectMap);
        objectMap.clear(); 
        
        budget.setFinalVersionFlag(false);
        budget.setProposal(dest);
        
        //Work around for 1-to-1 Relationship between BudgetPeriod & BudgetModular
        Map<String, BudgetModular> tmpBudgetModulars = new HashMap<String, BudgetModular>(); 
        for(BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpObject = null;
            if(budgetPeriod.getBudgetModular() != null) {
                tmpObject = (BudgetModular) ObjectUtils.deepCopy(budgetPeriod.getBudgetModular());
            }
            tmpBudgetModulars.put(budgetPeriod.getProposalNumber()+ (budgetPeriod.getVersionNumber()+1) + budgetPeriod.getBudgetPeriod(), tmpObject);
            budgetPeriod.setBudgetModular(null);
        }
        
        List<BudgetProjectIncome> srcProjectIncomeList = budget.getBudgetProjectIncomes();
        budget.setBudgetProjectIncomes(new ArrayList<BudgetProjectIncome>());
        
        documentService.saveDocument(budget);
        
        for(BudgetPeriod tmpBudgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpBudgetModular = tmpBudgetModulars.get(tmpBudgetPeriod.getProposalNumber()+ tmpBudgetPeriod.getVersionNumber() + tmpBudgetPeriod.getBudgetPeriod());
            if(tmpBudgetModular != null) {
                tmpBudgetModular.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                tmpBudgetPeriod.setBudgetModular(tmpBudgetModular);
            }
            
            for(BudgetProjectIncome budgetProjectIncome : srcProjectIncomeList) {
                if(budgetProjectIncome.getBudgetPeriodNumber().intValue() == tmpBudgetPeriod.getBudgetPeriod().intValue()) {
                    budgetProjectIncome.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                    budgetProjectIncome.setProposalNumber(tmpBudgetPeriod.getProposalNumber());
                    budgetProjectIncome.setVersionNumber(new Long(0));
                }
            }
        }
        
        budget.setBudgetProjectIncomes(srcProjectIncomeList);
        documentService.saveDocument(budget);
        documentService.routeDocument(budget, "Route to Final", new ArrayList());
    }
    
    /**
     * Recurse through all of the BOs and if a BO has a specific property,
     * set its value to the new value.
     * @param object the object
     * @param propertyValue 
     */
    private void fixNumericProperty(Object object, String methodName, Class clazz, Object propertyValue, Map<String, Object> objectMap) throws Exception {
        if(ObjectUtils.isNotNull(object) && object instanceof PersistableBusinessObject) {
            PersistableBusinessObject objectWId = (PersistableBusinessObject) object;
            if (objectMap.get(objectWId.getObjectId()) != null) return;
            objectMap.put(((PersistableBusinessObject) object).getObjectId(), object);
            
            Method[] methods = object.getClass().getMethods();
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                      try {
                        if(clazz.equals(Long.class))
                            method.invoke(object, (Long) propertyValue);  
                        else 
                            method.invoke(object, (Integer) propertyValue);
                       } catch (Throwable e) { }  
                } else if (isPropertyGetterMethod(method, methods)) {
                    Object value = null;
                    try {
                        value = method.invoke(object);
                    } catch (Throwable e) {
                        //We don't need to propagate this exception
                    }
                    
                    if(value != null) {
                        if (value instanceof Collection) {
                            Collection c = (Collection) value;
                            Iterator iter = c.iterator();
                            while (iter.hasNext()) {
                                Object entry = iter.next();
                                fixNumericProperty(entry, methodName, clazz, propertyValue, objectMap);
                            }
                        } else {
                            fixNumericProperty(value, methodName, clazz, propertyValue, objectMap);
                        }   
                    }
                }
            }
        }
    }
    
    /**
     * Set the Business Object Service.  It is set via dependency injection.
     * 
     * @param businessObjectService the Business Object Service
     */
    public void setBusinessObjectService(BusinessObjectService businessObjectService) {
        this.businessObjectService = businessObjectService;
    }
    
    public void setDocumentService(DocumentService documentService) {
        this.documentService = documentService;
    }

    /**
     * Set the Key Personnel Service.  It is set via dependency injection.
     * 
     * @param keyPersonnelService the Key Personnel Service
     */
    public void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }
    
    /**
     * Set the Person Service.  Injected by Spring.
     * @param personService the Person Service
     */
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    
    /**
     * Get the Kuali Rule Service.
     * 
     * @return the Kuali Rule Service
     */
    private KualiRuleService getKualiRuleService() {
        return KraServiceLocator.getService(KualiRuleService.class);
    }

    public void setKualiConfigurationService(KualiConfigurationService kualiConfigurationService) {
        this.kualiConfigurationService = kualiConfigurationService;
    }
    
}
