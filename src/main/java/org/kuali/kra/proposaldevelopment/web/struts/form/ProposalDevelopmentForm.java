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
package org.kuali.kra.proposaldevelopment.web.struts.form;

import static org.kuali.kra.infrastructure.KraServiceLocator.getService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.core.bo.Parameter;
import org.kuali.core.service.BusinessObjectService;
import org.kuali.core.service.DataDictionaryService;
import org.kuali.core.util.ActionFormUtilMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.PersonEditableField;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.pojo.Permission;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalLocation;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalUser;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserEditRoles;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.kra.s2s.bo.S2sOppForms;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.service.SystemAuthorizationService;
import org.kuali.kra.service.UnitService;
import org.kuali.kra.web.struts.form.KraTransactionalDocumentFormBase;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentForm extends KraTransactionalDocumentFormBase {
    private static final org.apache.commons.logging.Log LOG = org.apache.commons.logging.LogFactory.getLog(ProposalDevelopmentForm.class);
    private static final String DELETE_SPECIAL_REVIEW_ACTION = "deleteSpecialReview";
    
    private String primeSponsorName;
    private ProposalLocation newPropLocation;
    private ProposalSpecialReview newPropSpecialReview;
    private ProposalPerson newProposalPerson;
    private List<ProposalPersonDegree> newProposalPersonDegree;
    private List<Unit> newProposalPersonUnit;
    private String newRolodexId;
    private String newPersonId;
    private Narrative newNarrative;
    private FormFile narrativeFile;
    private Map personEditableFields;
    private boolean showMaintenanceLinks;
    private ProposalAbstract newProposalAbstract;
    private ProposalPersonBiography newPropPersonBio;
    private Narrative newInstituteAttachment;
    private boolean auditActivated;
    private ProposalCopyCriteria copyCriteria;
    private Map<String, Parameter> proposalDevelopmentParameters;
    private Integer answerYesNo;
    private Integer answerYesNoNA;
    private ProposalUser newProposalUser;
    private String newBudgetVersionName;
    private List<ProposalUserRoles> proposalUserRolesList = null;
    private ProposalUserEditRoles proposalUserEditRoles;
    private boolean newProposalPersonRoleRendered;
    private List<NarrativeUserRights> newNarrativeUserRights;
    private int narrativeLineNumber;
    private S2sOpportunity newS2sOpportunity;
    private S2sOppForms newS2sOppForms;
    private Map<String, List> customAttributeGroups;
    private Map<String, String[]> customAttributeValues;

    /**
     * Used to indicate which result set we're using when refreshing/returning from a multi-value lookup
     */
    private String lookupResultsSequenceNumber;
    /**
     * The type of result returned by the multi-value lookup
     *
     * TODO: to be persisted in the lookup results service instead? See https://test.kuali.org/confluence/display/KULRNE/Using+multiple+value+lookups
     */
    private String lookupResultsBOClassName;


    public ProposalDevelopmentForm() {
        super();
        this.setDocument(new ProposalDevelopmentDocument());
        initialize();
    }

    /**
     *
     * This method initialize all form variables
     */
    public void initialize() {
        setNewPropLocation(new ProposalLocation());
        setNewPropSpecialReview(new ProposalSpecialReview());
        setNewNarrative(new Narrative());
        setNewProposalPerson(new ProposalPerson());
        setNewProposalPersonDegree(new ArrayList<ProposalPersonDegree>());
        setNewProposalPersonUnit(new ArrayList<Unit>());
        setNewProposalAbstract(new ProposalAbstract());
        setNewProposalUser(new ProposalUser());
        customAttributeValues = new HashMap<String, String[]>();
        setCopyCriteria(new ProposalCopyCriteria());
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        this.setHeaderNavigationTabs((dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument.class.getName())).getHeaderTabNavigation());
        proposalDevelopmentParameters = new HashMap<String, Parameter>();
        newProposalPersonRoleRendered = false;
    }


    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return (ProposalDevelopmentDocument) this.getDocument();
    }

    @Override
    public void populate(HttpServletRequest request) {
        super.populate(request);
        ProposalDevelopmentDocument proposalDevelopmentDocument=getProposalDevelopmentDocument();

        proposalDevelopmentDocument.refreshReferenceObject("sponsor");

		// Temporary hack for KRACOEUS-489
        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
        }
        
        if(getMethodToCall().equalsIgnoreCase(DELETE_SPECIAL_REVIEW_ACTION)) { 
            GlobalVariables.getErrorMap().clear();
        }  
   
    }

    public ProposalLocation getNewPropLocation() {
        return newPropLocation;
    }


    public void setNewPropLocation(ProposalLocation newPropLocation) {
        this.newPropLocation = newPropLocation;
    }


    public ProposalSpecialReview getNewPropSpecialReview() {
        return newPropSpecialReview;
    }


    public void setNewPropSpecialReview(ProposalSpecialReview newPropSpecialReview) {
        this.newPropSpecialReview = newPropSpecialReview;
    }

    /**
     * Gets the new proposal abstract.  This is the abstract filled
     * in by the user on the form before pressing the add button. The
     * abstract can be invalid if the user has not specified an abstract type.
     *
     * @return the new proposal abstract
     */
    public ProposalAbstract getNewProposalAbstract() {
        return newProposalAbstract;
    }

    /**
     * Sets the new proposal abstract.  This is the abstract that will be
     * shown to the user on the form.
     *
     * @param newProposalAbstract
     */
    public void setNewProposalAbstract(ProposalAbstract newProposalAbstract) {
        this.newProposalAbstract = newProposalAbstract;
    }

    /* Reset method
     * @param mapping
     * @param request
     * reset check box values in keyword panel and properties that much be read on each request.
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        this.setCopyCriteria(new ProposalCopyCriteria());
       // following reset the tab stats and will load as default when it returns from lookup.
       // TODO : Do we really need this?
       // implemented headerTab in KraTransactionalDocumentActionBase
       //     this.setTabStates(new HashMap<String, String>());
        this.setCurrentTabIndex(0);


        ProposalDevelopmentDocument proposalDevelopmentDocument = this.getProposalDevelopmentDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getPropScienceKeywords();
        for(int i=0; i<keywords.size(); i++) {
            PropScienceKeyword propScienceKeyword = (PropScienceKeyword)keywords.get(i);
            propScienceKeyword.setSelectKeyword(false);
        }
        
        // Clear the edit roles so that they can then be set by struts
        // when the form is submitted.
        ProposalUserEditRoles editRoles = this.getProposalUserEditRoles();
        if (editRoles != null) {
            editRoles.setAggregator(Boolean.FALSE);
            editRoles.setBudgetCreator(Boolean.FALSE);
            editRoles.setNarrativeWriter(Boolean.FALSE);
            editRoles.setViewer(Boolean.FALSE);
        }
    }


    /**
     * Sets the primeSponsorName attribute value.
     * @param primeSponsorName The primeSponsorName to set.
     */
    public void setPrimeSponsorName(String primeSponsorName) {
        this.primeSponsorName = primeSponsorName;
    }


    /**
     * Gets the primeSponsorName attribute.
     * @return Returns the primeSponsorName.
     */
    public String getPrimeSponsorName() {
        return primeSponsorName;
    }

    /**
     * Gets the value of newPersonId
     *
     * @return the value of newPersonId
     */
    public String getNewPersonId() {
        return this.newPersonId;
    }

    /**
     * Sets the value of newPersonId
     *
     * @param argNewPersonId Value to assign to this.newPersonId
     */
    public void setNewPersonId(String argNewPersonId) {
        this.newPersonId = argNewPersonId;
    }

    public String getLookupResultsSequenceNumber() {
        return lookupResultsSequenceNumber;
    }

    public void setLookupResultsSequenceNumber(String lookupResultsSequenceNumber) {
        this.lookupResultsSequenceNumber = lookupResultsSequenceNumber;
    }


    /**
     * Gets the value of newProposalPerson
     *
     * @return the value of newProposalPerson
     */
    public ProposalPerson getNewProposalPerson() {
        return this.newProposalPerson;
    }

    /**
     * Sets the value of newProposalPerson
     *
     * @param argNewProposalPerson Value to assign to this.newProposalPerson
     */
    public void setNewProposalPerson(ProposalPerson argNewProposalPerson) {
        this.newProposalPerson = argNewProposalPerson;
    }

    /**
     * Gets the value of newProposalPersonUnit
     *
     * @return the value of newProposalPersonUnit
     */
    public List<Unit> getNewProposalPersonUnit() {
        if (this.getProposalDevelopmentDocument().getProposalPersons().size() > this.newProposalPersonUnit.size()) {
            this.newProposalPersonUnit.add(this.newProposalPersonUnit.size(), new Unit());
        }
        return this.newProposalPersonUnit;
    }

    /**
     * Sets the value of newProposalPersonUnit
     *
     * @param argUnit Value to assign to this.newProposalPersonUnit
     */
    public void setNewProposalPersonUnit(List<Unit> argUnit) {
        this.newProposalPersonUnit = argUnit;
    }

    /**
     * Gets the value of newProposalPersonDegree
     *
     * @return the value of newProposalPersonDegree
     */
    public List<ProposalPersonDegree> getNewProposalPersonDegree() {

        if (this.getProposalDevelopmentDocument().getProposalPersons().size() > this.newProposalPersonDegree.size()) {
            this.newProposalPersonDegree.add(this.newProposalPersonDegree.size(),new ProposalPersonDegree());
        }
        return this.newProposalPersonDegree;
    }

    /**
     * Sets the value of newProposalPersonDegree
     *
     * @param argDegree Value to assign to this.newProposalPersonDegree
     */
    public void setNewProposalPersonDegree(List<ProposalPersonDegree> argDegree) {
        this.newProposalPersonDegree = argDegree;
    }

    /**
     * Gets the value of newRolodexId
     *
     * @return the value of newRolodexId
     */
    public String getNewRolodexId() {
        return this.newRolodexId;
    }


    /**
     * Sets the value of newRolodexId
     *
     * @param argNewRolodexId Value to assign to this.newRolodexId
     */
    public void setNewRolodexId(String argNewRolodexId) {
        this.newRolodexId = argNewRolodexId;
    }

    public String getLookupResultsBOClassName() {
        return lookupResultsBOClassName;
    }

    public void setLookupResultsBOClassName(String lookupResultsBOClassName) {
        this.lookupResultsBOClassName = lookupResultsBOClassName;
    }

    /**
     * Gets the newNarrative attribute.
     * @return Returns the newNarrative.
     */
    public Narrative getNewNarrative() {
        return newNarrative;
    }


    /**
     * Sets the newNarrative attribute value.
     * @param newNarrative The newNarrative to set.
     */
    public void setNewNarrative(Narrative newNarrative) {
        this.newNarrative = newNarrative;
    }


    public FormFile getNarrativeFile() {
        return narrativeFile;
    }


    public void setNarrativeFile(FormFile narrativeFile) {
        this.narrativeFile = narrativeFile;
    }

    public boolean isShowMaintenanceLinks(){
        return showMaintenanceLinks;
    }

    public void setShowMaintenanceLinks(boolean showMaintenanceLinks) {
        this.showMaintenanceLinks = showMaintenanceLinks;
    }

    private BusinessObjectService getBusinessObjectService() {
        return KraServiceLocator.getService(BusinessObjectService.class);
    }

    /**
     * Creates the list of <code>{@link PersonEditableField}</code> field names.
     */
    public void populatePersonEditableFields() {
        LOG.debug("Adding PersonEditableFields");

        setPersonEditableFields(new HashMap());

        Collection<PersonEditableField> fields = getBusinessObjectService().findAll(PersonEditableField.class);
        for (PersonEditableField field : fields) {
            LOG.debug("found field " + field.getFieldName());
            getPersonEditableFields().put(field.getFieldName(), new Boolean(field.isActive()));
        }
    }

    /**
     * Write access to <code>{@link Map}</code> containing persisted <code>{@link PersonEditableField}</code> BO instances.
     *
     * @param fields
     */
    public void setPersonEditableFields(Map fields) {
        personEditableFields = fields;
    }

    /**
     * Get persisted <code>{@link PersonEditableField}</code> BO instances as a <code>{@link Map}</code>. If the <code>{@link Map}</code> containing them is
     *  <code>null</code>, then it gets populated here.
     *
     * @return Map containing person editable fields
     */
    public Map getPersonEditableFields() {
        if (personEditableFields == null) {
            populatePersonEditableFields();
        }
        return personEditableFields;
    }

    public Map getCreditSplitTotals() {
        return getKeyPersonnelService().calculateCreditSplitTotals(getProposalDevelopmentDocument());
    }


    public ProposalPersonBiography getNewPropPersonBio() {
        return newPropPersonBio;
    }


    public void setNewPropPersonBio(ProposalPersonBiography newPropPersonBio) {
        this.newPropPersonBio = newPropPersonBio;
    }


    public Narrative getNewInstituteAttachment() {
        return newInstituteAttachment;
    }


    public void setNewInstituteAttachment(Narrative newInstituteAttachment) {
        this.newInstituteAttachment = newInstituteAttachment;
    }


    /**
     * Sets the auditActivated attribute value.
     * @param auditActivated The auditActivated to set.
     */
    public void setAuditActivated(boolean auditActivated) {
        this.auditActivated = auditActivated;
    }

    /**
     * Gets the auditActivated attribute.
     * @return Returns the auditActivated.
     */
    public boolean isAuditActivated() {
        return auditActivated;
    }

    /**
     * Sets the customAttributeGroups attribute value.
     * @param customAttributeGroups The customAttributeGroups to set.
     */
    public void setCustomAttributeGroups(Map<String, List> customAttributeGroups) {
        this.customAttributeGroups = customAttributeGroups;
    }

    private KeyPersonnelService getKeyPersonnelService() {
        return getService(KeyPersonnelService.class);
    }

    /**
     * Gets the Copy Criteria for copying a proposal development document.
     * The criteria is user-specified and controls the operation of the
     * copy.
     *
     * @return the proposal copy criteria
     */
    public ProposalCopyCriteria getCopyCriteria() {
        return copyCriteria;
    }

    /**
     * Sets the Copy Criteria for copying a proposal development document.
     * The criteria is user-specified and controls the operation of the
     * copy.
     *
     * @param copyCriteria the new proposal copy criteria
     */
    public void setCopyCriteria(ProposalCopyCriteria copyCriteria) {
        this.copyCriteria = copyCriteria;
    }

    /**
     * Determines if attachments can be copied.
     *
     * @return true if copying attachments is disabled; otherwise false.
     */
    public boolean getIsCopyAttachmentsDisabled() {
        ProposalDevelopmentDocument doc = this.getProposalDevelopmentDocument();
        return !(doc.getNarratives().size() > 0 ||
            doc.getInstituteAttachments().size() > 0 ||
            doc.getPropPersonBios().size() > 0);
    }

    /**
     * Gets the customAttributeGroups attribute.
     * @return Returns the customAttributeGroups.
     */
    public Map<String, List> getCustomAttributeGroups() {
        return customAttributeGroups;
    }


    /**
     * Sets the customAttributeValues attribute value.
     * @param customAttributeValues The customAttributeValues to set.
     */
    public void setCustomAttributeValues(Map<String, String[]> customAttributeValues) {
        this.customAttributeValues = customAttributeValues;
    }


    /**
     * Gets the customAttributeValues attribute.
     * @return Returns the customAttributeValues.
     */
    public Map<String, String[]> getCustomAttributeValues() {
        return customAttributeValues;
    }

    /**
     * This method...
     *
     * @return true if copying budget(s) is disabled; otherwise false.
     */
    public boolean getIsCopyBudgetDisabled() {
        return true;
    }


    /**
     * Sets the proposalDevelopmentParameters attribute value.
     * @param proposalDevelopmentParameters The proposalDevelopmentParameters to set.
     */
    public void setProposalDevelopmentParameters(Map<String, Parameter> proposalDevelopmentParameters) {
        this.proposalDevelopmentParameters = proposalDevelopmentParameters;
    }


    /**
     * Gets the proposalDevelopmentParameters attribute.
     * @return Returns the proposalDevelopmentParameters.
     */
    public Map<String, Parameter> getProposalDevelopmentParameters() {
        return proposalDevelopmentParameters;
    }

    public Integer getAnswerYesNo() {
        return Constants.ANSWER_YES_NO;
    }


    public Integer getAnswerYesNoNA() {
        return Constants.ANSWER_YES_NO_NA;
    }
    
    /**
     * Get the names of the Aggregators for this proposal from 
     * the Proposal's ACL only.  Used by the JSP tags to display
     * the Aggregators.
     * @return a list of the names of the Aggregators.
     */
    public List<String> getAggregatorsByName() {
        return getUsersInRole(RoleConstants.AGGREGATOR);
    }
    
    /**
     * Get the names of the Budget Creators for this proposal from 
     * the Proposal's ACL only.  Used by the JSP tags to display
     * the Budget Creators.
     * @return a list of the names of the Budget Creators.
     */
    public List<String> getBudgetCreatorsByName() {
        return getUsersInRole(RoleConstants.BUDGET_CREATOR);
    }
    
    /**
     * Get the names of the Narrative Writers for this proposal from 
     * the Proposal's ACL only.  Used by the JSP tags to display
     * the Narrative Writers.
     * @return a list of the names of the Narrative Writers.
     */
    public List<String> getNarrativeWritersByName() {
        return getUsersInRole(RoleConstants.NARRATIVE_WRITER);
    }
    
    /**
     * Get the names of the Proposal Viewers for this proposal from 
     * the Proposal's ACL only.  Used by the JSP tags to display
     * the Viewers.
     * @return a list of the names of the Proposal Viewers.
     */
    public List<String> getViewersByName() {
        return getUsersInRole(RoleConstants.VIEWER);
    }
    
    /**
     * Get the full names of the users with the given role in the proposal.
     * @param roleName the name of the role
     * @return the names of users with the role in the document
     */
    private List<String> getUsersInRole(String roleName) {
        List<String> names = new ArrayList<String>();
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        List<Person> persons = proposalAuthService.getPersonsInRole(this.getProposalDevelopmentDocument(), roleName);
        for (Person person : persons) {
            names.add(person.getFullName());
        }
        
        // Sort the list of names.
        
        Collections.sort(names, new Comparator() {
            public int compare(Object o1, Object o2) {
                String name1 = (String) o1;
                String name2 = (String) o2;
                if (name1 == null && name2 == null) return 0;
                if (name1 == null) return -1;
                return name1.compareTo(name2);
            }
        });
        return names;
    }
    
    /**
     * Get the list of permissions for the Aggregator role.
     * @return the list of Aggregator permissions
     */
    public List<Permission> getAggregatorPermissions() {
        SystemAuthorizationService systemAuthService = KraServiceLocator.getService(SystemAuthorizationService.class);
        return systemAuthService.getPermissionsForRole(RoleConstants.AGGREGATOR);
    }
    
    /**
     * Get the list of permissions for the Budget Creator role.
     * @return the list of Budget Creator permissions
     */
    public List<Permission> getBudgetCreatorPermissions() {
        SystemAuthorizationService systemAuthService = KraServiceLocator.getService(SystemAuthorizationService.class);
        return systemAuthService.getPermissionsForRole(RoleConstants.BUDGET_CREATOR);
    }
    
    /**
     * Get the list of permissions for the Narrative Writer role.
     * @return the list of Narrative Writer permissions
     */
    public List<Permission> getNarrativeWriterPermissions() {
        SystemAuthorizationService systemAuthService = KraServiceLocator.getService(SystemAuthorizationService.class);
        return systemAuthService.getPermissionsForRole(RoleConstants.NARRATIVE_WRITER);
    }
    
    /**
     * Get the list of permissions for the Viewer role.
     * @return the list of Viewer permissions
     */
    public List<Permission> getViewerPermissions() {
        SystemAuthorizationService systemAuthService = (SystemAuthorizationService) KraServiceLocator.getService(SystemAuthorizationService.class);
        return systemAuthService.getPermissionsForRole(RoleConstants.VIEWER);
    }
    
    /** 
     * Gets the new proposal user.  This is the proposal user that is filled
     * in by the user on the form before pressing the add button.
     *
     * @return the new proposal user
     */
    public ProposalUser getNewProposalUser() {
        return newProposalUser;
    }

    /**
     * Sets the new proposal user.  This is the proposal user that will be
     * shown on the form.
     *
     * @param newProposalUser the new proposal user
     */
    public void setNewProposalUser(ProposalUser newProposalUser) {
        this.newProposalUser = newProposalUser;
    }
    
    /**
     * Get the list of Proposal User Roles.  Each user has one or more
     * roles assigned to the proposal.  This method builds the list each
     * time it is invoked.  It is always invoked when the Permissions page
     * is displayed.  After the list is built, the list can be obtained
     * via the getCurrentProposalUserRoles() method.  Typically, the 
     * getCurrentProposalUserRoles() is invoked from the Permission Actions.
     * 
     * @return the list of users with proposal roles and sorted by their full name
     */
    public synchronized List<ProposalUserRoles> getProposalUserRoles() {
        proposalUserRolesList = new ArrayList<ProposalUserRoles>();
        
        // Add persons into the ProposalUserRolesList for each of the roles.
        
        addPersons(proposalUserRolesList, RoleConstants.AGGREGATOR);
        addPersons(proposalUserRolesList, RoleConstants.BUDGET_CREATOR);
        addPersons(proposalUserRolesList, RoleConstants.NARRATIVE_WRITER);
        addPersons(proposalUserRolesList, RoleConstants.VIEWER);
        addPersons(proposalUserRolesList, RoleConstants.UNASSIGNED);
        
        // Sort the list of users by their full name.
        
        Collections.sort(proposalUserRolesList, new Comparator() {
            public int compare(Object o1, Object o2) {
                ProposalUserRoles user1 = (ProposalUserRoles) o1;
                ProposalUserRoles user2 = (ProposalUserRoles) o2;
                return user1.getFullname().compareTo(user2.getFullname());
            }
        });
        
        return proposalUserRolesList;
    }
    
    /**
     * Get the current list of Proposal User Roles.
     * @return the list of users with proposal roles
     */
    public List<ProposalUserRoles> getCurrentProposalUserRoles() {
        if (proposalUserRolesList == null) {
            getProposalUserRoles();
        }
        return proposalUserRolesList;
    }
    
    /**
     * Add a set of persons to the proposalUserRolesList for a given role.
     * 
     * @param proposalUserRolesList the list to add to
     * @param roleName the name of role to query for persons assigned to that role
     */
    private void addPersons(List<ProposalUserRoles> proposalUserRolesList, String roleName) {
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        ProposalDevelopmentDocument doc = this.getProposalDevelopmentDocument();
        
        List<Person> persons = proposalAuthService.getPersonsInRole(doc, roleName);
        for (Person person : persons) {
            ProposalUserRoles proposalUserRoles = findProposalUserRoles(proposalUserRolesList, person.getUserName());
            if (proposalUserRoles != null) {
                proposalUserRoles.addRoleName(roleName);
            } else {
                proposalUserRolesList.add(buildProposalUserRoles(person, roleName));
            }
        }
    }
    
    /**
     * Find a user in the list of proposalUserRolesList based upon the user's username.
     * 
     * @param proposalUserRolesList the list to search
     * @param username the user's username to search for
     * @return the proposalUserRoles or null if not found
     */
    private ProposalUserRoles findProposalUserRoles(List<ProposalUserRoles> proposalUserRolesList, String username) {
        for (ProposalUserRoles proposalUserRoles : proposalUserRolesList) {
            if (StringUtils.equals(username, proposalUserRoles.getUsername())) {
                return proposalUserRoles;
            }
        }
        return null;
    }
    
    /**
     * Build a ProposalUserRoles instance.  Assemble the information about
     * the user (person) into a ProposalUserRoles along with the home unit
     * info for that person.
     * 
     * @param person the person
     * @param roleName the name of the role
     * @return a new ProposalUserRoles instance
     */
    private ProposalUserRoles buildProposalUserRoles(Person person, String roleName) {
        ProposalUserRoles proposalUserRoles = new ProposalUserRoles();
        
        // Set the person's username, rolename, fullname, and home unit.

        proposalUserRoles.setUsername(person.getUserName());
        proposalUserRoles.addRoleName(roleName);
        proposalUserRoles.setFullname(person.getFullName());
        proposalUserRoles.setUnitNumber(person.getHomeUnit());
        
        // Query the database to find the name of the unit.
            
        UnitService unitService = KraServiceLocator.getService(UnitService.class);
        Unit unit = unitService.getUnit(person.getHomeUnit());
        if (unit != null) {
            proposalUserRoles.setUnitName(unit.getUnitName());
        }
        
        return proposalUserRoles;
    }

    /**
     * Get the Edit Roles BO that is simply a form filled in by a
     * user via the Edit Roles web page.
     * 
     * @return the edit roles object
     */
    public ProposalUserEditRoles getProposalUserEditRoles() {
        return proposalUserEditRoles;
    }

    /**
     * Set the Edit Roles BO.
     * @param proposalUserEditRoles the Edit Roles BO
     */
    public void setProposalUserEditRoles(ProposalUserEditRoles proposalUserEditRoles) {
        this.proposalUserEditRoles = proposalUserEditRoles;
    }

    public String getNewBudgetVersionName() {
        return newBudgetVersionName;
    }

    public void setNewBudgetVersionName(String newBudgetVersionName) {
        this.newBudgetVersionName = newBudgetVersionName;
    }

    /**
     * Used to indicate to the values finder whether the role has already been rendered
     * 
     * @return true if the role has been rendered already, false otherwise
     */
    public boolean isNewProposalPersonRoleRendered() {
        return newProposalPersonRoleRendered;
    }

    /**
     * Used to indicate to the values finder whether the role has already been rendered
     * 
     * @param newProposalPersonRoleRendered
     */
    public void setNewProposalPersonRoleRendered(boolean newProposalPersonRoleRendered) {
        this.newProposalPersonRoleRendered = newProposalPersonRoleRendered;
    }
    
    /**
     * Get the Header Dispatch.  This determines the action that will occur
     * when the user switches tabs for a proposal.  If the user can modify
     * the proposal, the proposal is automatically saved.  If not (view-only),
     * then a null op will be executed instead.
     * @return the Header Dispatch action
     */
    public String getHeaderDispatch() {
        return this.getDocumentActionFlags().getCanSave() ? "save" : "nullOp";
    }

    /**
     * Set the New Narrative User Rights.  This is displayed on the View/Edit Rights
     * web page for attachments.
     * @param newNarrativeUserRights the new narrativer user rights
     */
    public void setNewNarrativeUserRights(List<NarrativeUserRights> newNarrativeUserRights) {
        this.newNarrativeUserRights = newNarrativeUserRights;
    }
    
    /**
     * Get the New Narrative User Rights.
     * @return the new narrative user rights
     */
    public List<NarrativeUserRights> getNewNarrativeUserRights() {
        return this.newNarrativeUserRights;
    }
    
    /**
     * Get a New Narrative User Right.
     * @param index the index into the list of narrative user rights
     * @return a new narrative user right
     */
    public NarrativeUserRights getNewNarrativeUserRight(int index) {
        return this.newNarrativeUserRights.get(index);
    }
    
    /**
     * Set the Narrative Line Number.  It is the index into the list
     * of narratives for the narrative being viewed/edited in the Edit/View
     * Rights web page for narrative rights.
     * @param lineNumber the narrative line number
     */
    public void setNarrativeLineNumber(int lineNumber) {
        this.narrativeLineNumber = lineNumber;
    }
    
    /**
     * Get the Narrative Line Number.
     * @return the narrative line number
     */
    public int getNarrativeLineNumber() {
        return this.narrativeLineNumber;
    }

    public S2sOpportunity getNewS2sOpportunity() {
        return newS2sOpportunity;
    }

    public void setNewS2sOpportunity(S2sOpportunity newS2sOpportunity) {
        this.newS2sOpportunity = newS2sOpportunity;
    }
    
    public S2sOppForms getNews2sForms(){
    	return newS2sOppForms;
    }
    
    public void setNews2sForms(S2sOppForms newS2sOppForms){
        this.newS2sOppForms = newS2sOppForms;
    }
}
