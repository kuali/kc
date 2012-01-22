/*
 * Copyright 2005-2010 The Kuali Foundation
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
import org.kuali.kra.bo.CoeusModule;
import org.kuali.kra.bo.CustomAttributeDocValue;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.core.BudgetService;
import org.kuali.kra.budget.distributionincome.BudgetProjectIncome;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.summary.BudgetSummaryService;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.proposaldevelopment.bo.CongressionalDistrict;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiographyAttachment;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonExtendedAttributes;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonUnit;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonYnq;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalUnitCreditSplit;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardAttachment;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwardFiles;
import org.kuali.kra.proposaldevelopment.budget.bo.BudgetSubAwards;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyStatusConstants;
import org.kuali.kra.proposaldevelopment.rule.event.CopyProposalEvent;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.NarrativeService;
import org.kuali.kra.proposaldevelopment.service.ProposalCopyService;
import org.kuali.kra.proposaldevelopment.service.ProposalPersonBiographyService;
import org.kuali.kra.questionnaire.QuestionnaireService;
import org.kuali.kra.questionnaire.answer.AnswerHeader;
import org.kuali.kra.questionnaire.answer.ModuleQuestionnaireBean;
import org.kuali.kra.questionnaire.answer.QuestionnaireAnswerService;
import org.kuali.kra.service.KraAuthorizationService;
import org.kuali.kra.service.UnitService;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.bo.BusinessObject;
import org.kuali.rice.krad.bo.DocumentHeader;
import org.kuali.rice.krad.bo.PersistableBusinessObject;
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADPropertyConstants;
import org.kuali.rice.krad.util.ObjectUtils;

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
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalCopyServiceImpl.class);
    private BudgetService<DevelopmentProposal> budgetService;
    private BudgetSummaryService budgetSummaryService;
    private QuestionnaireService questionnaireService;
    private QuestionnaireAnswerService questionnaireAnswerService;
    
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
                                                   "ProposalState",
                                                   "ProposalDocument",
                                                   "YnqGroupNames"};
    
    /**
     * Each property in the document that can be copied is represented
     * by its getter and setter method.
     *
     * @author Kuali Research Administration Team (kualidev@oncourse.iu.edu)
     */
    protected class DocProperty {
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
    private ParameterService parameterService;
    private DateTimeService dateTimeService;
    
    /**
     * Sets the ParameterService.
     * @param parameterService the parameter service. 
     */
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    /**
     * @see org.kuali.kra.proposaldevelopment.service.ProposalCopyService#copyProposal(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument, org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria)
     */
    public String copyProposal(ProposalDevelopmentDocument doc, ProposalCopyCriteria criteria) throws Exception {
        String newDocNbr = null;
        LOG.info("STARTING PROPOSAL COPY");
        // check any business rules
        boolean rulePassed = getKualiRuleService().applyRules(new CopyProposalEvent(doc, criteria));
        
        if (rulePassed) {
            
            ProposalDevelopmentDocument newDoc = createNewProposal(doc, criteria);
            
            copyProposal(doc, newDoc, criteria);
            fixProposal(doc, newDoc, criteria);

            DocumentService docService = KRADServiceLocatorWeb.getDocumentService();
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

            //copy existing questionnaires (if we can, otherwise copy the pieces of the questionnaires that we can. )
            //if (criteria.getIncludeQuestionnaires()) {
            if (criteria.getIncludeQuestionnaire()) {
                ModuleQuestionnaireBean moduleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE, doc.getDevelopmentProposal().getProposalNumber(), "0" ,"0", true);
                List<AnswerHeader> answerHeaders = questionnaireAnswerService.getQuestionnaireAnswer(moduleQuestionnaireBean);
                ModuleQuestionnaireBean destModuleQuestionnaireBean = new ModuleQuestionnaireBean(CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE,newDoc.getDevelopmentProposal().getProposalNumber(),"0","0",false);
                questionnaireAnswerService.copyAnswerHeaders(moduleQuestionnaireBean, destModuleQuestionnaireBean);
            }
            
            //save extended attributes
            List<ProposalPerson> proposalPersons = newDoc.getDevelopmentProposal().getProposalPersons();
            for (ProposalPerson person : proposalPersons) {
                if (person.getProposalPersonExtendedAttributes() != null) {
                    this.businessObjectService.save(person.getProposalPersonExtendedAttributes());
                }
            }
            
            copyCustomData(doc, newDoc);
            
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
    protected ProposalDevelopmentDocument createNewProposal(ProposalDevelopmentDocument srcDoc, ProposalCopyCriteria criteria) throws Exception {
        
        DocumentService docService = KRADServiceLocatorWeb.getDocumentService();
        ProposalDevelopmentDocument newDoc = (ProposalDevelopmentDocument) docService.getNewDocument(srcDoc.getClass());
        
        LOG.info("EXECUTING IN createNewProposal");
        
        // Copy over the document overview properties.
        
        copyOverviewProperties(srcDoc, newDoc);
        
        copyRequiredProperties(srcDoc, newDoc);
        
        // Set lead unit.
        
        setLeadUnit(newDoc, criteria.getLeadUnitNumber());
        
        newDoc.getDocumentHeader().setDocumentTemplateNumber(srcDoc.getDocumentNumber());
        docService.saveDocument(newDoc);
        
        return newDoc;
    }
    
    /**
     * Copy over the required properties so we can do an initial save of the document
     * in order to obtain a proposal number.
     * @param srcDoc
     * @param destDoc
     */
    protected void copyRequiredProperties(ProposalDevelopmentDocument srcDoc, ProposalDevelopmentDocument destDoc) {
        DevelopmentProposal srcDevelopmentProposal = srcDoc.getDevelopmentProposal();
        DevelopmentProposal destDevelopmentProposal = destDoc.getDevelopmentProposal();
        
        destDoc.getDocumentHeader().setDocumentDescription(srcDoc.getDocumentHeader().getDocumentDescription());
        destDevelopmentProposal.setProposalTypeCode(srcDevelopmentProposal.getProposalTypeCode());
        destDevelopmentProposal.setActivityTypeCode(srcDevelopmentProposal.getActivityTypeCode());
        destDevelopmentProposal.setTitle(srcDevelopmentProposal.getTitle());
        destDevelopmentProposal.setSponsorCode(srcDevelopmentProposal.getSponsorCode());
        destDevelopmentProposal.setRequestedStartDateInitial(srcDevelopmentProposal.getRequestedStartDateInitial());
        destDevelopmentProposal.setRequestedEndDateInitial(srcDevelopmentProposal.getRequestedEndDateInitial());
        
        destDevelopmentProposal.getApplicantOrganization().setLocationName(srcDevelopmentProposal.getApplicantOrganization().getLocationName());
        destDevelopmentProposal.getApplicantOrganization().setSiteNumber(srcDevelopmentProposal.getApplicantOrganization().getSiteNumber());
        destDevelopmentProposal.getPerformingOrganization().setLocationName(srcDevelopmentProposal.getPerformingOrganization().getLocationName());
        destDevelopmentProposal.getPerformingOrganization().setSiteNumber(srcDevelopmentProposal.getPerformingOrganization().getSiteNumber());
        
        if (isProposalTypeRenewalRevisionContinuation(srcDevelopmentProposal.getProposalTypeCode())) {
            destDevelopmentProposal.setSponsorProposalNumber(srcDevelopmentProposal.getSponsorProposalNumber());
        }
    }
    
    /**
     * Copies the source proposal development document to the destination document.
     * 
     * @param src the source document, i.e. the original.
     * @param dest the destination document, i.e. the new document.
     * @param criteria the user-specified criteria.
     * @throws Exception if the copy fails for any reason.
     */
    protected void copyProposal(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest, ProposalCopyCriteria criteria) throws Exception {
        
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
    protected void copyProposalProperties(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest)  throws Exception {
        List<DocProperty> properties = getCopyableProperties();
        
        //We need to copy DocumentNextValues to properly handle copied collections
        fixNextValues(src, dest);
        
        copyProperties(src.getDevelopmentProposal(), dest.getDevelopmentProposal(), properties);
    }
    
    /**
     * The document next values must be the same in the new version as in
     * the old document.  Note that the next document values must be assigned
     * the document number of the new version.
     * @param oldDoc
     * @param newDoc
     */
    protected void fixNextValues(ProposalDevelopmentDocument oldDoc, ProposalDevelopmentDocument newDoc) {
        List<DocumentNextvalue> newNextValues = new ArrayList<DocumentNextvalue>();
        List<DocumentNextvalue> oldNextValues = oldDoc.getDocumentNextvalues();
        for (DocumentNextvalue oldNextValue : oldNextValues) {
            DocumentNextvalue newNextValue = new DocumentNextvalue();
            newNextValue.setPropertyName(oldNextValue.getPropertyName());
            newNextValue.setNextValue(oldNextValue.getNextValue());
            newNextValue.setDocumentKey(newDoc.getDocumentNumber());
            newNextValues.add(newNextValue);
        }
        newDoc.setDocumentNextvalues(newNextValues);
    }
    
    //Or I could use an anonymous filter class???
            
    protected void copyProperties(DevelopmentProposal src, DevelopmentProposal dest, List<DocProperty> properties) throws Exception {
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
    protected void copyOverviewProperties(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest) {
        DocumentHeader srcHdr = src.getDocumentHeader();
        DocumentHeader destHdr = dest.getDocumentHeader();
        
        destHdr.setDocumentDescription(srcHdr.getDocumentDescription());
        destHdr.setExplanation(srcHdr.getExplanation());
        destHdr.setOrganizationDocumentNumber(srcHdr.getOrganizationDocumentNumber());
    }
    
    /**
     * Get the list of DevelopmentProposal properties that can be copied.
     * A property can only be copied if it meets the following criteria.
     * <ul>
     * <li>It was declared in the <b>DevelopmentProposal</b> class.</li>
     * <li>It has a setter and a getter method.</li>
     * <li>It is not a filtered property.</li>
     * </ul>
     * 
     * @return the list of properties that can be copied.
     */
    protected List<DocProperty> getCopyableProperties() {
        List<DocProperty> list = new ArrayList<DocProperty>();
        
        Method[] methods = DevelopmentProposal.class.getDeclaredMethods();
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
    protected String getPropertyName(Method method) {
        String name = method.getName();
        return name.substring(3);
    }

    /**
     * Is this a filtered property?
     * 
     * @param name the name of the property.
     * @return true if filtered; otherwise false.
     */
    protected boolean isFilteredProperty(String name) {
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
    protected Method getGetter(String name, Method[] methods) {
        String getter = "get" + name;
        for (Method method : methods) {
            if (getter.equals(method.getName())) {
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
    protected void setLeadUnit(ProposalDevelopmentDocument doc, String newLeadUnitNumber) {
        UnitService unitService = KraServiceLocator.getService(UnitService.class);
        Unit newLeadUnit = unitService.getUnit(newLeadUnitNumber);
        doc.getDevelopmentProposal().setOwnedByUnitNumber(newLeadUnitNumber);
        doc.getDevelopmentProposal().setOwnedByUnit(newLeadUnit);
    }
        
    /**
     * Fix the proposal.
     * @param criteria the copy criteria
     * @throws Exception 
     */
    protected void fixProposal(ProposalDevelopmentDocument srcDoc, ProposalDevelopmentDocument newDoc, ProposalCopyCriteria criteria) throws Exception {
        List<Object> list = new ArrayList<Object>();
        // force to materialize - jira 1644 only happen for disapproved doc ??
        for (ProposalPerson proposalperson : newDoc.getDevelopmentProposal().getProposalPersons()) {
            //KRACOEUS-4545 - set the proposal reference on the person to the new proposal as OJB will try 
            //to rematerialize this reference to the old one during fixProposalNumber otherwise.
            //tried doing this via reflection, but it didn't seem to trigger the OJB proxy.
            proposalperson.setDevelopmentProposal(newDoc.getDevelopmentProposal());
            proposalperson.setProposalNumber(newDoc.getDevelopmentProposal().getProposalNumber());
            for (ProposalPersonUnit proposalPersonUnit : proposalperson.getUnits()) {
                ObjectUtils.materializeObjects(proposalPersonUnit.getCreditSplits());
            }
            
            /**
             * Need to copy extended attributes KRACOEUS-4834
             */
            for (ProposalPerson srcProposalperson : srcDoc.getDevelopmentProposal().getProposalPersons()) {
                if (StringUtils.equals(proposalperson.getFullName(), srcProposalperson.getFullName())
                    && StringUtils.equals(proposalperson.getProposalPersonRoleId(), srcProposalperson.getProposalPersonRoleId())) {
                    if (srcProposalperson.getProposalPersonExtendedAttributes() != null) {
                        ProposalPersonExtendedAttributes ppea = 
                            (ProposalPersonExtendedAttributes) ObjectUtils.deepCopy(srcProposalperson.getProposalPersonExtendedAttributes());
                        ppea.setProposalPerson(proposalperson);
                        ppea.setProposalNumber(proposalperson.getProposalNumber());
                        ppea.setProposalPersonNumber(proposalperson.getProposalPersonNumber());
                        ppea.setProposalPersonRoleId(proposalperson.getProposalPersonRoleId());
                        proposalperson.setProposalPersonExtendedAttributes(ppea);
                    }
                }
            }
        }

        fixProposalNumbers(newDoc, newDoc.getDevelopmentProposal().getProposalNumber(), list);
        fixKeyPersonnel(newDoc, srcDoc.getDevelopmentProposal().getOwnedByUnitNumber(), criteria.getLeadUnitNumber());
        fixCongressionalDistricts(newDoc);
        // reset organization / location info only if lead unit changed
        if (!StringUtils.equals(srcDoc.getDevelopmentProposal().getUnitNumber(), newDoc.getDevelopmentProposal().getUnitNumber())) {
            fixOrganizationAndLocations(newDoc);
        }
        list.clear();
        fixBudgetVersions(newDoc);

        newDoc.getDevelopmentProposal().setHierarchyStatus(HierarchyStatusConstants.None.code());
        newDoc.getDevelopmentProposal().setHierarchyParentProposalNumber(null);
        newDoc.getDevelopmentProposal().setHierarchyLastSyncHashCode(null);
        newDoc.getDevelopmentProposal().cleanupSpecialReviews(srcDoc.getDevelopmentProposal());
        
        //update timestamp on abstracts to match doc creation time
        for (ProposalAbstract curAbstract : newDoc.getDevelopmentProposal().getProposalAbstracts()) {
            curAbstract.setTimestampDisplay(dateTimeService.getCurrentTimestamp());
        }
    }

    /**
     * If the Lead Unit has changed in the previous {@link Document}, then this method corrects related
     * properties {@link Organization} and {@link ProposalSite} instances
     *
     * @param doc {@link ProposalDevelopmentDocument} to fix
     */
    protected void fixOrganizationAndLocations(ProposalDevelopmentDocument doc) {
        DevelopmentProposal developmentProposal = doc.getDevelopmentProposal();
        
        Unit ownedByUnit = developmentProposal.getOwnedByUnit();
        if (ownedByUnit != null) {
            String unitOrganizationId = ownedByUnit.getOrganizationId();
            for (ProposalSite proposalSite: developmentProposal.getProposalSites()) {
                // set location name to default from Organization
                proposalSite.setOrganizationId(unitOrganizationId);
                proposalSite.refreshReferenceObject("organization");
                proposalSite.setLocationName(proposalSite.getOrganization().getOrganizationName());
                proposalSite.setRolodexId(proposalSite.getOrganization().getContactAddressId());
                proposalSite.refreshReferenceObject("rolodex");
            }
        }
    }
    
    /**
     * Recurse through all of the BOs and if a BO has a ProposalNumber property,
     * set its value to the new proposal number.
     * @param object the object
     * @param proposalNumber the proposal number
     * @param list I assume this is the list of objects that have already been processed.
     */
    @SuppressWarnings("unchecked")
    protected void fixProposalNumbers(Object object, String proposalNumber, List<Object> list) throws Exception {
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
    protected boolean isPropertyGetterMethod(Method method, Method methods[]) {
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
    protected void fixKeyPersonnel(ProposalDevelopmentDocument doc, String oldLeadUnitNumber, String newLeadUnitNumber) throws Exception {
        clearCertifyQuestions(doc);
        fixKeyPersonnelUnits(doc, oldLeadUnitNumber, newLeadUnitNumber);
    }
    
    /**
     * This method sets all congressional district ids to null, so new ids get assigned to them.
     * @param doc
     */
    protected void fixCongressionalDistricts(ProposalDevelopmentDocument doc) {
        for (ProposalSite proposalSite: doc.getDevelopmentProposal().getProposalSites()) {
            for (CongressionalDistrict district: proposalSite.getCongressionalDistricts()) {
                district.setCongressionalDistrictId(null);
            }
        }
    }
    
    /**
     * Fix data related to Budget Versions.
     * @param doc the proposal development document
     * @param oldLeadUnitNumber the old lead unit number
     * @param newLeadUnitNumber the new lead unit number
     */
    protected void fixBudgetVersions(ProposalDevelopmentDocument doc) {
        if (doc.getBudgetDocumentVersions().size() > 0) {
            String budgetStatusIncompleteCode = this.parameterService.getParameterValueAsString(
                    BudgetDocument.class, Constants.BUDGET_STATUS_INCOMPLETE_CODE);
            
            doc.getDevelopmentProposal().setBudgetStatus(budgetStatusIncompleteCode);
        }
    }
    
    /**
     * Clear the Certify questions for each investigator.
     * @param doc the Proposal Development Document
     */
    protected void clearCertifyQuestions(ProposalDevelopmentDocument doc) {
        List<ProposalPerson> persons = doc.getDevelopmentProposal().getProposalPersons();
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
    protected void fixKeyPersonnelUnits(ProposalDevelopmentDocument doc, String oldLeadUnitNumber, String newLeadUnitNumber) throws Exception {
       
        List<ProposalPerson> persons = doc.getDevelopmentProposal().getProposalPersons();
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
        doc.getDevelopmentProposal().setInvestigators(new ArrayList<ProposalPerson>());
        keyPersonnelService.populateDocument(doc);
    }
    
    protected ProposalPersonUnit createProposalPersonUnit(ProposalPerson person, String unitNumber, boolean isLeadUnit, boolean isDeletable, List<ProposalPersonUnit> oldProposalPersonUnits) {
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
    
    protected ProposalPersonUnit findProposalPersonUnit(String unitNumber, List<ProposalPersonUnit> proposalPersonUnits) {
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
    protected void initializeAuthorization(ProposalDevelopmentDocument doc) {
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        KraAuthorizationService kraAuthService = KraServiceLocator.getService(KraAuthorizationService.class);
        kraAuthService.addRole(userId, RoleConstants.AGGREGATOR, doc);
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
    protected void copyAttachments(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest) throws Exception {
        
        NarrativeService narrativeService = dest.getDevelopmentProposal().getNarrativeService();
        ProposalPersonBiographyService propPersonBioService = dest.getDevelopmentProposal().getProposalPersonBiographyService();
 
        loadAttachmentContents(src);
        
        List<ProposalPersonBiography> propPersonBios = src.getDevelopmentProposal().getPropPersonBios();
        ProposalPersonBiography destPropPersonBio;
        for (ProposalPersonBiography srcPropPersonBio : propPersonBios) {
            destPropPersonBio = (ProposalPersonBiography)ObjectUtils.deepCopy(srcPropPersonBio);
            propPersonBioService.addProposalPersonBiography(dest, destPropPersonBio);
        }

        List<Narrative> narratives = src.getDevelopmentProposal().getNarratives();
        Narrative destNarrative;
        for (Narrative srcNarrative : narratives) {
            destNarrative = (Narrative)ObjectUtils.deepCopy(srcNarrative);
            
            // For the new proposal document, it's proposal attachments are required to
            // have their status' initially set to Incomplete. 
            // Not all of them though, only P type narratives are makred complete/incomplete in the UI
            if( StringUtils.equals(destNarrative.getNarrativeType().getNarrativeTypeGroup(), Constants.PROPOSAL_NARRATIVE_TYPE_GROUP_CODE))
                destNarrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_INCOMPLETE);
            else
                destNarrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
            
            narrativeService.addNarrative(dest, destNarrative);
        }
    }
    
    /**
     * Load the attachment contents from the database.
     * 
     * @param doc the proposal development document to load attachment contents into.
     */
    protected void loadAttachmentContents(ProposalDevelopmentDocument doc) {
        
        // Load personal attachments.
        List<Narrative> narratives = doc.getDevelopmentProposal().getNarratives();
        for (Narrative narrative : narratives) {
            loadAttachmentContent(narrative);
        }
        
        // Load institutional attachments.
        narratives = doc.getDevelopmentProposal().getInstituteAttachments();
        for (Narrative narrative : narratives) {
            loadAttachmentContent(narrative);
            /**
             * The getNarratives collection contains both personal and instiutional attachments for persisting and displaying.  Am not sure
             * why it was implemented this way, but do to time constraints significant refactor isn't ideal.
             */
            if(!doc.getDevelopmentProposal().getNarratives().contains(narrative)) {
                doc.getDevelopmentProposal().getNarratives().add(narrative);
            }
        }
        
        // Load proposal attachments.
        List<ProposalPersonBiography> bios = doc.getDevelopmentProposal().getPropPersonBios();
        for (ProposalPersonBiography bio : bios) {
            loadBioContent(bio);
        }
    }
    
    /**
     * Load the attachment content for a specific narrative from the database.
     * 
     * @param narrative the narrative for which to load the contents.
     */
    protected void loadAttachmentContent(Narrative narrative){
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
    protected void loadBioContent(ProposalPersonBiography bio){
        Map<String,String> primaryKey = new HashMap<String,String>();
        primaryKey.put(PROPOSAL_NUMBER, bio.getProposalNumber());
        primaryKey.put("biographyNumber", bio.getBiographyNumber()+"");
        primaryKey.put("proposalPersonNumber", bio.getProposalPersonNumber()+"");
        ProposalPersonBiographyAttachment attachment = (ProposalPersonBiographyAttachment)businessObjectService.findByPrimaryKey(ProposalPersonBiographyAttachment.class, primaryKey);
        bio.getPersonnelAttachmentList().clear();
        bio.getPersonnelAttachmentList().add(attachment);
    }
    
    /**
     * This method...
     *
     * @param src the source proposal development document, i.e. the original.
     * @param dest the destination proposal development document, i.e. the new document.
     * @param budgetVersions
     */
    protected void copyBudget(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest, String budgetVersions) throws Exception {
        if (budgetVersions.equals(ProposalCopyCriteria.BUDGET_FINAL_VERSION)) {
            BudgetDocumentVersion finalBudgetVersion = src.getFinalBudgetVersion();
            if (finalBudgetVersion != null) {
                copyAndFinalizeBudgetVersion(finalBudgetVersion.getDocumentNumber(), dest, 1,
                        StringUtils.equals(src.getDevelopmentProposal().getHierarchyStatus(), HierarchyStatusConstants.Parent.code()));
            }
        } else if (budgetVersions.equals(ProposalCopyCriteria.BUDGET_ALL_VERSIONS)) {
            int i = 1;
            for (BudgetDocumentVersion budgetDocumentVersion: src.getBudgetDocumentVersions()) {
                copyAndFinalizeBudgetVersion(budgetDocumentVersion.getDocumentNumber(), dest, i++,
                        StringUtils.equals(src.getDevelopmentProposal().getHierarchyStatus(), HierarchyStatusConstants.Parent.code()));
            }
        }
        
    }
    
    protected void copyAndFinalizeBudgetVersion(String documentNumber, ProposalDevelopmentDocument dest, int budgetVersionNumber, boolean resetRates) throws Exception {
        BudgetDocument budgetDocument = (BudgetDocument) documentService.getByDocumentHeaderId(documentNumber);
        List<BudgetSubAwards> budgetSubAwards = budgetDocument.getBudget().getBudgetSubAwards();
        for (BudgetSubAwards budgetSubAward : budgetSubAwards) {
            List<BudgetSubAwardFiles> budgetSubAwardFiles = budgetSubAward.getBudgetSubAwardFiles();
            if(budgetSubAwardFiles==null || budgetSubAwardFiles.isEmpty()){
                Map param = new HashMap();
                param.put("budgetId", budgetSubAward.getBudgetId());
                param.put("subAwardNumber", budgetSubAward.getSubAwardNumber());
                budgetSubAward.setBudgetSubAwardFiles((List)businessObjectService.findMatching(BudgetSubAwardFiles.class, param));
                budgetSubAward.setBudgetSubAwardAttachments((List)businessObjectService.findMatching(BudgetSubAwardAttachment.class, param));
            }
        }

        
        budgetDocument.toCopy();
        budgetDocument.setVersionNumber(null);
        if(budgetDocument.getBudgets().isEmpty()) return;
        budgetDocument.getBudget().setBudgetVersionNumber(budgetVersionNumber);
        Map<String, Object> objectMap = new HashMap<String, Object>();
        fixNumericProperty(budgetDocument, "setBudgetId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPeriodId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetLineItemId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetLineItemCalculatedAmountId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPersonnelLineItemId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPersonnelCalculatedAmountId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetPersonnelRateAndBaseId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetRateAndBaseId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setBudgetSubawardAttachmentId", Long.class, null, objectMap);
        objectMap.clear();
        fixNumericProperty(budgetDocument, "setVersionNumber", Integer.class, null, objectMap);
        objectMap.clear();
        
        ObjectUtils.materializeAllSubObjects(budgetDocument.getBudget());

        Budget budget = budgetDocument.getBudget();
        
        budget.setFinalVersionFlag(false);
        budgetDocument.setParentDocumentKey(dest.getDocumentNumber());
        
        //Work around for 1-to-1 Relationship between BudgetPeriod & BudgetModular
        Map<BudgetPeriod, BudgetModular> tmpBudgetModulars = new HashMap<BudgetPeriod, BudgetModular>(); 
        for(BudgetPeriod budgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpObject = null;
            if(budgetPeriod.getBudgetModular() != null) {
                tmpObject = (BudgetModular) ObjectUtils.deepCopy(budgetPeriod.getBudgetModular());
            }
            tmpBudgetModulars.put(budgetPeriod, tmpObject);
            budgetPeriod.setBudgetModular(null);
        }
        
        List<BudgetProjectIncome> srcProjectIncomeList = budget.getBudgetProjectIncomes();
        budget.setBudgetProjectIncomes(new ArrayList<BudgetProjectIncome>());
        budget.setBudgetDocument(budgetDocument);
        budget.setDocumentNumber(budgetDocument.getDocumentNumber());
        documentService.saveDocument(budgetDocument);
        
        for(BudgetPeriod tmpBudgetPeriod: budget.getBudgetPeriods()) {
            BudgetModular tmpBudgetModular = tmpBudgetModulars.get(tmpBudgetPeriod);
            if(tmpBudgetModular != null) {
                tmpBudgetModular.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                tmpBudgetPeriod.setBudgetModular(tmpBudgetModular);
            }
            
            for(BudgetProjectIncome budgetProjectIncome : srcProjectIncomeList) {
                if(budgetProjectIncome.getBudgetPeriodNumber().intValue() == tmpBudgetPeriod.getBudgetPeriod().intValue()) {
                    budgetProjectIncome.setBudgetPeriodId(tmpBudgetPeriod.getBudgetPeriodId());
                    budgetProjectIncome.setBudgetId(tmpBudgetPeriod.getBudget().getBudgetId());
                    budgetProjectIncome.setVersionNumber(new Long(0));
                }
            }
        }
        
        budget.setBudgetProjectIncomes(srcProjectIncomeList);
        budgetSummaryService.calculateBudget(budgetDocument.getBudget());
        if (resetRates) {
            budgetDocument.getBudget().getBudgetRates().clear();
            budgetDocument.getBudget().getBudgetLaRates().clear();
        }
        documentService.saveDocument(budgetDocument);
        documentService.routeDocument(budgetDocument, "Route to Final", new ArrayList());
        budgetDocument.getParentDocument().refreshBudgetDocumentVersions();
    }
    
    /**
     * Recurse through all of the BOs and if a BO has a specific property,
     * set its value to the new value.
     * @param object the object
     * @param propertyValue 
     */
    protected void fixNumericProperty(Object object, String methodName, Class clazz, Object propertyValue, Map<String, Object> objectMap) throws Exception {
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
     * This method copies all custom data from one document to another.
     * @param src
     * @param dest
     */
    protected void copyCustomData(ProposalDevelopmentDocument src, ProposalDevelopmentDocument dest) {
        for (Map.Entry<String, CustomAttributeDocument> entry: src.getCustomAttributeDocuments().entrySet()) {
            // Find the attribute value
            CustomAttributeDocument customAttributeDocument = entry.getValue();
            Map<String, Object> primaryKeys = new HashMap<String, Object>();
            primaryKeys.put(KRADPropertyConstants.DOCUMENT_NUMBER, src.getDocumentNumber());
            primaryKeys.put(Constants.CUSTOM_ATTRIBUTE_ID, customAttributeDocument.getCustomAttributeId());
            CustomAttributeDocValue customAttributeDocValue = (CustomAttributeDocValue)businessObjectService.findByPrimaryKey(CustomAttributeDocValue.class, primaryKeys);
            
            // Store a new CustomAttributeDocValue using the new document's document number
            if (customAttributeDocValue != null) {
                CustomAttributeDocValue newDocValue = new CustomAttributeDocValue();
                newDocValue.setDocumentNumber(dest.getDocumentNumber());
                newDocValue.setCustomAttributeId(customAttributeDocument.getCustomAttributeId());
                newDocValue.setValue(customAttributeDocValue.getValue());
                KraServiceLocator.getService(BusinessObjectService.class).save(newDocValue);
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
     * Get the Kuali Rule Service.
     * 
     * @return the Kuali Rule Service
     */
    protected KualiRuleService getKualiRuleService() {
        return KraServiceLocator.getService(KualiRuleService.class);
    }
    

    /**
     * Gets the questionnaireService attribute. 
     * @return Returns the questionnaireService.
     */
    public QuestionnaireService getQuestionnaireService() {
        return questionnaireService;
    }

    /**
     * Sets the questionnaireService attribute value.
     * @param questionnaireService The questionnaireService to set.
     */
    public void setQuestionnaireService(QuestionnaireService questionnaireService) {
        this.questionnaireService = questionnaireService;
    }


    /**
     * Gets the questionnaireAnswerService attribute. 
     * @return Returns the questionnaireAnswerService.
     */
    public QuestionnaireAnswerService getQuestionnaireAnswerService() {
        return questionnaireAnswerService;
    }

    /**
     * Sets the questionnaireAnswerService attribute value.
     * @param questionnaireAnswerService The questionnaireAnswerService to set.
     */
    public void setQuestionnaireAnswerService(QuestionnaireAnswerService questionnaireAnswerService) {
        this.questionnaireAnswerService = questionnaireAnswerService;
    }

    /**
     * Is the Proposal Type set to Renewal, Revision, or a Continuation?
     * @param proposalTypeCode proposal type code
     * @return true or false
     */
    protected boolean isProposalTypeRenewalRevisionContinuation(String proposalTypeCode) {
        String proposalTypeCodeRenewal = this.parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL);
        String proposalTypeCodeRevision = this.parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION);
        String proposalTypeCodeContinuation = this.parameterService.getParameterValueAsString(ProposalDevelopmentDocument.class, KeyConstants.PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION);
         
        return !StringUtils.isEmpty(proposalTypeCode) &&
               (proposalTypeCode.equals(proposalTypeCodeRenewal) ||
                proposalTypeCode.equals(proposalTypeCodeRevision) ||
                proposalTypeCode.equals(proposalTypeCodeContinuation));
    }

    protected DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    /**
     * Gets the budgetService attribute. 
     * @return Returns the budgetService.
     */
    public BudgetService<DevelopmentProposal> getBudgetService() {
        return budgetService;
    }

    /**
     * Sets the budgetService attribute value.
     * @param budgetService The budgetService to set.
     */
    public void setBudgetService(BudgetService<DevelopmentProposal> budgetService) {
        this.budgetService = budgetService;
    }

    /**
     * Gets the budgetSummaryService attribute. 
     * @return Returns the budgetSummaryService.
     */
    public BudgetSummaryService getBudgetSummaryService() {
        return budgetSummaryService;
    }

    /**
     * Sets the budgetSummaryService attribute value.
     * @param budgetSummaryService The budgetSummaryService to set.
     */
    public void setBudgetSummaryService(BudgetSummaryService budgetSummaryService) {
        this.budgetSummaryService = budgetSummaryService;
    }
}
