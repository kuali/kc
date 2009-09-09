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
package org.kuali.kra.proposaldevelopment.web.struts.form;

import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_RULE_NAME;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_COMPONENT_DOCUMENT;
import static org.kuali.kra.infrastructure.Constants.PARAMETER_MODULE_PROPOSAL_DEVELOPMENT;
import static org.kuali.kra.infrastructure.KraServiceLocator.getService;
import static org.kuali.kra.logging.BufferedLogger.debug;
import static org.kuali.kra.logging.BufferedLogger.warn;
import static org.kuali.rice.kns.util.KNSConstants.EMPTY_STRING;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.bo.CustomAttributeDocument;
import org.kuali.kra.bo.Person;
import org.kuali.kra.bo.PersonEditableField;
import org.kuali.kra.bo.SponsorFormTemplateList;
import org.kuali.kra.bo.Unit;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.kim.bo.KimRole;
import org.kuali.kra.kim.service.KIMService;
import org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal;
import org.kuali.kra.proposaldevelopment.bo.Narrative;
import org.kuali.kra.proposaldevelopment.bo.NarrativeUserRights;
import org.kuali.kra.proposaldevelopment.bo.PropScienceKeyword;
import org.kuali.kra.proposaldevelopment.bo.ProposalAbstract;
import org.kuali.kra.proposaldevelopment.bo.ProposalAssignedRole;
import org.kuali.kra.proposaldevelopment.bo.ProposalChangedData;
import org.kuali.kra.proposaldevelopment.bo.ProposalCopyCriteria;
import org.kuali.kra.proposaldevelopment.bo.ProposalPerson;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonBiography;
import org.kuali.kra.proposaldevelopment.bo.ProposalPersonDegree;
import org.kuali.kra.proposaldevelopment.bo.ProposalSite;
import org.kuali.kra.proposaldevelopment.bo.ProposalSpecialReview;
import org.kuali.kra.proposaldevelopment.bo.ProposalState;
import org.kuali.kra.proposaldevelopment.bo.ProposalUser;
import org.kuali.kra.proposaldevelopment.bo.ProposalUserEditRoles;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.hierarchy.HierarchyStatusConstants;
import org.kuali.kra.proposaldevelopment.hierarchy.bo.HierarchyProposalSummary;
import org.kuali.kra.proposaldevelopment.service.KeyPersonnelService;
import org.kuali.kra.proposaldevelopment.service.ProposalAuthorizationService;
import org.kuali.kra.proposaldevelopment.web.bean.ProposalUserRoles;
import org.kuali.kra.s2s.bo.S2sAppSubmission;
import org.kuali.kra.s2s.bo.S2sOpportunity;
import org.kuali.kra.s2s.bo.S2sSubmissionHistory;
import org.kuali.kra.service.KraWorkflowService;
import org.kuali.kra.service.PersonService;
import org.kuali.kra.service.UnitService;
import org.kuali.kra.web.struts.form.ProposalFormBase;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.util.PerformanceLogger;
import org.kuali.rice.kim.bo.group.dto.GroupInfo;
import org.kuali.rice.kns.bo.Parameter;
import org.kuali.rice.kns.datadictionary.DocumentEntry;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.rice.kns.service.KualiConfigurationService;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.util.GlobalVariables;
import org.kuali.rice.kns.util.KNSConstants;
import org.kuali.rice.kns.util.KualiDecimal;
import org.kuali.rice.kns.util.TypedArrayList;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.kns.workflow.service.KualiWorkflowDocument;

/**
 * This class...
 * @author Kuali Nervous System Team (kualidev@oncourse.iu.edu)
 */
public class ProposalDevelopmentForm extends ProposalFormBase {
    
    private static final long serialVersionUID = 7928293162992415894L;
    private static final String MISSING_PARAM_MSG = "Couldn't find parameter ";
    //private static final String DELETE_SPECIAL_REVIEW_ACTION = "deleteSpecialReview";
    
    private boolean creditSplitEnabled;
    private String primeSponsorName;
    private ProposalSpecialReview newPropSpecialReview;
    private ProposalPerson newProposalPerson;
    private List<ProposalPersonDegree> newProposalPersonDegree;
    private List<Unit> newProposalPersonUnit;
    private String newRolodexId;
    private String newPersonId;
    private Narrative newNarrative;
    private FormFile narrativeFile;
    private Map<String, Boolean> personEditableFields;
    private boolean showMaintenanceLinks;
    private ProposalAbstract newProposalAbstract;
    private ProposalPersonBiography newPropPersonBio;
    private Narrative newInstituteAttachment;
    //private boolean auditActivated;
    private ProposalCopyCriteria copyCriteria;
    private Map<String, Parameter> proposalDevelopmentParameters;
    //private Integer answerYesNo;
    //private Integer answerYesNoNA;
    private ProposalUser newProposalUser;
    private String newBudgetVersionName;
    private List<ProposalUserRoles> proposalUserRolesList = null;
    private ProposalUserEditRoles proposalUserEditRoles;
    private boolean newProposalPersonRoleRendered;
    private List<NarrativeUserRights> newNarrativeUserRights;
    private S2sOpportunity newS2sOpportunity;
    private List<S2sAppSubmission> newS2sAppSubmission;
    private SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups;
    private Map<String, String[]> customAttributeValues;
    private List<Narrative> narratives;
    private boolean reject;
    private boolean saveAfterCopy;
    private String optInUnitDetails;
    private String optInCertificationStatus;
    private ProposalChangedData newProposalChangedData;
    private Long versionNumberForS2sOpportunity;
    private ProposalSite newPerformanceSite;
    private ProposalSite newOtherOrganization;
    private CongressionalDistrictHelper applicantOrganizationHelper;
    private CongressionalDistrictHelper performingOrganizationHelper;
    private List<CongressionalDistrictHelper> performanceSiteHelpers;
    private List<CongressionalDistrictHelper> otherOrganizationHelpers;
    private DevelopmentProposal newHierarchyProposal;
    private DevelopmentProposal newHierarchyChildProposal;
    private List<HierarchyProposalSummary> hierarchyProposalSummaries;
   

    private String proposalFormTabTitle = "Print Sponsor Form Packages ";

    /* This is just a list of sponsor form package details - large objects not loaded */
    private List<SponsorFormTemplateList> sponsorFormTemplates;
    
    public ProposalDevelopmentForm() {
        super();
        this.setDocument(new ProposalDevelopmentDocument());
        initialize();
        sponsorFormTemplates = new ArrayList<SponsorFormTemplateList>();
    }

    /**
     *
     * This method initialize all form variables
     */
    @SuppressWarnings("unchecked")
    public void initialize() {
        setNewPropSpecialReview(new ProposalSpecialReview());
        setNewNarrative(createNarrative());
        setNewProposalPerson(new ProposalPerson());
        setNewProposalPersonDegree(new ArrayList<ProposalPersonDegree>());
        setNewProposalPersonUnit(new ArrayList<Unit>());
        setNewProposalAbstract(new ProposalAbstract());
        setNewProposalUser(new ProposalUser());
        setNewS2sOpportunity(new S2sOpportunity());
        setNewPerformanceSite(new ProposalSite());
        setNewOtherOrganization(new ProposalSite());
        setApplicantOrganizationHelper(new CongressionalDistrictHelper());
        setPerformingOrganizationHelper(new CongressionalDistrictHelper());
        setPerformanceSiteHelpers(new TypedArrayList(CongressionalDistrictHelper.class));
        setOtherOrganizationHelpers(new TypedArrayList(CongressionalDistrictHelper.class));
        customAttributeValues = new HashMap<String, String[]>();
        setCopyCriteria(new ProposalCopyCriteria(getDocument()));
        DataDictionaryService dataDictionaryService = (DataDictionaryService) KraServiceLocator.getService(Constants.DATA_DICTIONARY_SERVICE_NAME);
        DocumentEntry docEntry = dataDictionaryService.getDataDictionary().getDocumentEntry(org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument.class.getName());
        List<HeaderNavigation> navList = docEntry.getHeaderNavigationList();
        HeaderNavigation[] list = new HeaderNavigation[navList.size()];
        navList.toArray(list);
        this.setHeaderNavigationTabs(list);
        proposalDevelopmentParameters = new HashMap<String, Parameter>();
        newProposalPersonRoleRendered = false;
        setNewProposalChangedData(new ProposalChangedData());
        versionNumberForS2sOpportunity = null;
        setNewHierarchyChildProposal(new DevelopmentProposal());
        setNewHierarchyProposal(new DevelopmentProposal());
        setHierarchyProposalSummaries(new ArrayList<HierarchyProposalSummary>());
    }
    
    //  TODO Overriding for 1.1 upgrade 'till we figure out how to actually use this
    public boolean shouldMethodToCallParameterBeUsed(String methodToCallParameterName, String methodToCallParameterValue, HttpServletRequest request) {
        
        return true;
    }

    /**
     * This creates a new Narrative. Protected to allow mocks and stubs to provide their own Narrative that doesn't do a user id lookup
     * @return
     */
    protected Narrative createNarrative() {
        return new Narrative();
    }
    /**
     * Multiple Value Lookups return values to the form through the request, but in some instances do not clear previous values from other lookups because the form resides in the session scope. 
     * This is to set the Multiple Value Lookups to a good state. Values getting cleared are:
     * <ul>
     *   <li><code>lookupResultsSequenceNumber</code></li>
     *   <li><code>lookupResultsBOClassName</code></li>
     * </ul>
     * 
     */
    private void clearMultipleValueLookupResults() {
        setLookupResultsSequenceNumber(null);
        setLookupResultsBOClassName(null);
    }

    @Override
    public void populate(HttpServletRequest request) {
       
        clearMultipleValueLookupResults();
        super.populate(request);
        ProposalDevelopmentDocument proposalDevelopmentDocument=getDocument();

        proposalDevelopmentDocument.getDevelopmentProposal().refreshReferenceObject("sponsor");

        // Temporary hack for KRACOEUS-489
        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
        }       
      
        ProposalCopyCriteria cCriteria = this.getCopyCriteria();
        if (cCriteria != null) {
            cCriteria.setOriginalLeadUnitNumber(proposalDevelopmentDocument.getDevelopmentProposal().getOwnedByUnitNumber());
        }
    }
    
    @Override
    public void populateHeaderFields(KualiWorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
        
        ProposalDevelopmentDocument pd = getDocument();
        ProposalState proposalState = (pd == null) ? null : pd.getDevelopmentProposal().getProposalState();
        HeaderField docStatus = new HeaderField("DataDictionary.DocumentHeader.attributes.financialDocumentStatusCode", proposalState == null? "" : proposalState.getDescription());
        
        getDocInfo().set(1, docStatus);
        
        if (pd.getDevelopmentProposal().getSponsor() == null) {
            getDocInfo().add(new HeaderField("DataDictionary.Sponsor.attributes.sponsorName", ""));
        } else {
            getDocInfo().add(new HeaderField("DataDictionary.Sponsor.attributes.sponsorName", pd.getDevelopmentProposal().getSponsor().getSponsorName()));
        }
        
        if (getKeyPersonnelService().hasPrincipalInvestigator(pd)) {
            boolean found = false;
            
            for(Iterator<ProposalPerson> person_it = pd.getDevelopmentProposal().getInvestigators().iterator();
                person_it.hasNext() && !found; ){
                ProposalPerson investigator = person_it.next();
                
                if (getKeyPersonnelService().isPrincipalInvestigator(investigator)) {
                    found = true; // Will break out of the loop as soon as the PI is found
                    getDocInfo().add(new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", investigator.getFullName()));
                }
            }
        }
        else {
            getDocInfo().add(new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", EMPTY_STRING));
        }
        
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

    /**
     * Reset method
     * @param mapping
     * @param request
     * reset check box values in keyword panel and properties that much be read on each request.
     */
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        super.reset(mapping, request);
        
        ProposalCopyCriteria cCriteria = this.getCopyCriteria();
        if (cCriteria != null) {
            cCriteria.setIncludeAttachments(false);
            cCriteria.setIncludeBudget(false);
        }
        
       // following reset the tab stats and will load as default when it returns from lookup.
       // TODO : Do we really need this?
       // implemented headerTab in KraTransactionalDocumentActionBase
       //     this.setTabStates(new HashMap<String, String>());
        this.setCurrentTabIndex(0);


        ProposalDevelopmentDocument proposalDevelopmentDocument = this.getDocument();
        List<PropScienceKeyword> keywords = proposalDevelopmentDocument.getDevelopmentProposal().getPropScienceKeywords();
        for(int i=0; i<keywords.size(); i++) {
            PropScienceKeyword propScienceKeyword = keywords.get(i);
            propScienceKeyword.setSelectKeyword(false);
        }

        
        // Clear the edit roles so that they can then be set by struts
        // when the form is submitted.
        ProposalUserEditRoles editRoles = this.getProposalUserEditRoles();
        if (editRoles != null) {
            editRoles.clear();
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
        if (this.getDocument().getDevelopmentProposal().getProposalPersons().size() > this.newProposalPersonUnit.size()) {
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

        if (this.getDocument().getDevelopmentProposal().getProposalPersons().size() > this.newProposalPersonDegree.size()) {
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
        debug("Adding PersonEditableFields");

        setPersonEditableFields(new HashMap<String, Boolean>());

        @SuppressWarnings("unchecked")
        Collection<PersonEditableField> fields = getBusinessObjectService().findAll(PersonEditableField.class);
        for (PersonEditableField field : fields) {
            debug("found field " + field.getFieldName());
            getPersonEditableFields().put(field.getFieldName(), Boolean.valueOf(field.isActive()));
        }
    }

    /**
     * Write access to <code>{@link Map}</code> containing persisted <code>{@link PersonEditableField}</code> BO instances.
     *
     * @param fields
     */
    public void setPersonEditableFields(Map<String, Boolean> fields) {
        personEditableFields = fields;
    }

    /**
     * Get persisted <code>{@link PersonEditableField}</code> BO instances as a <code>{@link Map}</code>. If the <code>{@link Map}</code> containing them is
     *  <code>null</code>, then it gets populated here.
     *
     * @return Map containing person editable fields
     */
    public Map<String, Boolean> getPersonEditableFields() {
        if (personEditableFields == null) {
            populatePersonEditableFields();
        }
        return personEditableFields;
    }

    @SuppressWarnings("unchecked")
    public Map<String, KualiDecimal> getCreditSplitTotals() {
        return this.getKeyPersonnelService().calculateCreditSplitTotals(getDocument());    
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
//    public void setAuditActivated(boolean auditActivated) {
//        this.auditActivated = auditActivated;
//    }

    /**
     * Gets the auditActivated attribute.
     * @return Returns the auditActivated.
     */
//    public boolean isAuditActivated() {
//        return auditActivated;
//    }

    /**
     * Sets the customAttributeGroups attribute value.
     * @param customAttributeGroups The customAttributeGroups to set.
     */
    public void setCustomAttributeGroups(SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups) {
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
        ProposalDevelopmentDocument doc = this.getDocument();
        return !(doc.getDevelopmentProposal().getNarratives().size() > 0 ||
            doc.getDevelopmentProposal().getInstituteAttachments().size() > 0 ||
            doc.getDevelopmentProposal().getPropPersonBios().size() > 0);
    }

    /**
     * Gets the customAttributeGroups attribute.
     * @return Returns the customAttributeGroups.
     */
    public Map<String, List<CustomAttributeDocument>> getCustomAttributeGroups() {
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
        return !(this.getDocument().getBudgetDocumentVersions().size() > 0);
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
     * Used by the Assigned Roles panel in the Permissions page.  
     * @return
     */
    public List<ProposalAssignedRole> getProposalAssignedRoles() {
        PerformanceLogger perfLog = new PerformanceLogger();
        
        List<ProposalAssignedRole> assignedRoles = new ArrayList<ProposalAssignedRole>();
        
        Collection<KimRole> roles = getKimProposalRoles();
        for (KimRole role : roles) {
            if (!role.isUnassigned()) {
                ProposalAssignedRole assignedRole = 
                    new ProposalAssignedRole(role.getName(), getUsersInRole(role.getName()));
                assignedRoles.add(assignedRole);
            }
        }
        //For perf testing: will be removed
        perfLog.log("Time to execute getProposalAssignedRoles method.", true);
        return assignedRoles;
    }
    
    /**
     * Get the full names of the users with the given role in the proposal.
     * @param roleName the name of the role
     * @return the names of users with the role in the document
     */
    private List<String> getUsersInRole(String roleName) {
        List<String> names = new ArrayList<String>();
        List<ProposalUserRoles> proposalUsers = getProposalUserRoles();
        for (ProposalUserRoles proposalUser : proposalUsers) {
            if (proposalUser.getRoleNames().contains(roleName)) {
                names.add(proposalUser.getFullname());
            }
        }
        
        // Sort the list of names.
        
        Collections.sort(names, new Comparator<String>() {
            public int compare(String name1, String name2) {
                if (name1 == null && name2 == null) {
                    return 0;
                }
                
                if (name1 == null) {
                    return -1;
                }
                return name1.compareTo(name2);
            }
        });
        return names;
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
     * Get the list of all of the Proposal roles (filter out unassigned).
     * @return the list of proposal roles
     */
    public List<KimRole> getProposalRoles() {
        List<KimRole> proposalRoles = new ArrayList<KimRole>();
        Collection<KimRole> roles = getKimProposalRoles();
        for (KimRole role : roles) {
            if (!role.isUnassigned()) {
                proposalRoles.add(role);
            }
        }
        return proposalRoles;
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
        if (proposalUserRolesList == null) {
            proposalUserRolesList = new ArrayList<ProposalUserRoles>();
            
            // Add persons into the ProposalUserRolesList for each of the roles.
            Collection<KimRole> roles = getKimProposalRoles();
            for (KimRole role : roles) {
                addPersons(proposalUserRolesList, role.getName());
            }
            
            sortProposalUsers();  
        }
        
        return proposalUserRolesList;
    }
    
    public List<ProposalUserRoles> getCurrentProposalUserRoles() {
        List<ProposalUserRoles> current = new ArrayList<ProposalUserRoles>();
        
        Collection<KimRole> roles = getKimProposalRoles();
        for (KimRole role : roles) {
            addPersons(current, role.getName());
        }
        
        return current;
    }
    
    /**
     * Get all of the proposal roles.
     * @return
     */
    public Collection<KimRole> getKimProposalRoles() {
        List<KimRole> proposalRoles = new ArrayList<KimRole>();
        BusinessObjectService businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("roleTypeCode", RoleConstants.PROPOSAL_ROLE_TYPE);
        
        @SuppressWarnings("unchecked")
        Collection<KimRole> roles = businessObjectService.findMatching(KimRole.class, fieldValues);
       
        /*
         * Add in unassigned and standard proposal roles first so that
         * they always show up first on the web pages.
         */
        for (KimRole role : roles) {
            if (role.isUnassigned()) {
                proposalRoles.add(0, role);
            } else if (role.isStandardProposalRole()) {
                proposalRoles.add(role);
            }
        }
        
        /*
         * Now add in any user-define proposal roles.
         */
        for (KimRole role : roles) {
            if (!role.isUnassigned() && !role.isStandardProposalRole()) {
                proposalRoles.add(role);
            }
        }
        return proposalRoles;
    }
   
    
    private void sortProposalUsers() {
        // Sort the list of users by their full name.
        
        Collections.sort(proposalUserRolesList, new Comparator<ProposalUserRoles>() {
            public int compare(ProposalUserRoles o1, ProposalUserRoles o2) {
                return o1.getFullname().compareTo(o2.getFullname());
            }
        });
    }
    
    public void addProposalUser(ProposalUser proposalUser) {
        PersonService personService = KraServiceLocator.getService(PersonService.class);
        Person person = personService.getPersonByName(proposalUser.getUsername());
        ProposalUserRoles userRoles = buildProposalUserRoles(person, proposalUser.getRoleName());
        proposalUserRolesList.add(userRoles);
        sortProposalUsers();
    }
    
    /**
     * Add a set of persons to the proposalUserRolesList for a given role.
     * 
     * @param propUserRolesList the list to add to
     * @param roleName the name of role to query for persons assigned to that role
     */
    private void addPersons(List<ProposalUserRoles> propUserRolesList, String roleName) {
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        ProposalDevelopmentDocument doc = this.getDocument();
        
        List<Person> persons = proposalAuthService.getPersonsInRole(doc, roleName);
        for (Person person : persons) {
            ProposalUserRoles proposalUserRoles = findProposalUserRoles(propUserRolesList, person.getUserName());
            if (proposalUserRoles != null) {
                proposalUserRoles.addRoleName(roleName);
            } else {
                propUserRolesList.add(buildProposalUserRoles(person, roleName));
            }
        }
    }
    
    /**
     * Find a user in the list of proposalUserRolesList based upon the user's username.
     * 
     * @param propUserRolesList the list to search
     * @param username the user's username to search for
     * @return the proposalUserRoles or null if not found
     */
    private ProposalUserRoles findProposalUserRoles(List<ProposalUserRoles> propUserRolesList, String username) {
        for (ProposalUserRoles proposalUserRoles : propUserRolesList) {
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
     * 
     * Reset Document form data so that it is not added to copied document.
     */

    public void clearDocumentRelatedState(){
        this.proposalUserRolesList = null;
        this.setCopyCriteria(new ProposalCopyCriteria());
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

    @Override
    public String getNewBudgetVersionName() {
        return newBudgetVersionName;
    }

    @Override
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
     * then a reload will be executed instead.
     * @return the Header Dispatch action
     */
    public String getHeaderDispatch() {
        return this.getDocumentActions().containsKey(KNSConstants.KUALI_ACTION_CAN_SAVE) ? "save" : "reload";
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

    public S2sOpportunity getNewS2sOpportunity() {
        return newS2sOpportunity;
    }

    public void setNewS2sOpportunity(S2sOpportunity newS2sOpportunity) {
        this.newS2sOpportunity = newS2sOpportunity;
    }
    
    public List<S2sAppSubmission> getNewS2sAppSubmission() {
        return newS2sAppSubmission;
    }

    public void setNewS2sAppSubmission(List<S2sAppSubmission> newS2sAppSubmission) {
        this.newS2sAppSubmission = newS2sAppSubmission;
    }
    
    /**
     * Set the original list of narratives for comparison when a save occurs.
     * @param narratives the list of narratives
     */
    public void setNarratives(List<Narrative> narratives) {
        this.narratives = narratives;
    }
    
    /**
     * Get the original list of narratives.
     * @return the original list of narratives
     */
    public List<Narrative> getNarratives() {
        return this.narratives;
    }

    public boolean isReject() {
        return reject;
    }

    public void setReject(boolean reject) {
        this.reject = reject;
    }
    
    public List<ExtraButton> getExtraActionsButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        boolean showSubmitButton = true;
        boolean showResubmitButton = true;
        ProposalDevelopmentDocument doc = this.getDocument();
        if(doc.getDevelopmentProposal().getS2sSubmissionHistory()!=null && doc.getDevelopmentProposal().getS2sSubmissionHistory().size()!=0){
            for(S2sSubmissionHistory s2sSubmissionHistory:doc.getDevelopmentProposal().getS2sSubmissionHistory()){
                if(StringUtils.equalsIgnoreCase(s2sSubmissionHistory.getProposalNumberOrig(),doc.getDevelopmentProposal().getProposalNumber())){
                    showSubmitButton=false;
                }
                if(StringUtils.equalsIgnoreCase(s2sSubmissionHistory.getOriginalProposalId() ,doc.getDevelopmentProposal().getProposalNumber())){
                    showResubmitButton=false;
                }
            }
        } else if (doc.getDevelopmentProposal().getSubmitFlag()) {
            /*
             * If we get here, we have a non-electronic submission which doesn't have a submission history.
             */
            showSubmitButton = false;
            showResubmitButton = false;
        }
        else {
            showResubmitButton=false;
        }  
        
        String externalImageURL = "kra.externalizable.images.url";
        KualiWorkflowDocument wfDoc=doc.getDocumentHeader().getWorkflowDocument();
 
        if (showSubmitButton) {
            if (wfDoc.stateIsEnroute() || wfDoc.stateIsFinal() || wfDoc.stateIsProcessed()) {
                String submitToGrantsGovImage = KraServiceLocator.getService(KualiConfigurationService.class).getPropertyString(externalImageURL) + "buttonsmall_submittosponsor.gif";
                addExtraButton("methodToCall.submitToSponsor", submitToGrantsGovImage, "Submit To Sponsor");
            }
        }else if(showResubmitButton){
            String resubmissionImage = KraServiceLocator.getService(KualiConfigurationService.class).getPropertyString(externalImageURL) + "buttonsmall_replaceproposal.gif";
            addExtraButton("methodToCall.resubmit", resubmissionImage, "Replace Sponsor");
        }       
        
        return extraButtons;
    }
    
    /**
     * This is a utility method to add a new button to the extra buttons
     * collection.
     *   
     * @param property
     * @param source
     * @param altText
     */ 
    protected void addExtraButton(String property, String source, String altText){
        
        ExtraButton newButton = new ExtraButton();
        
        newButton.setExtraButtonProperty(property);
        newButton.setExtraButtonSource(source);
        newButton.setExtraButtonAltText(altText);
        
        extraButtons.add(newButton);
    }
    
    public KualiConfigurationService getConfigurationService() {
        return getService(KualiConfigurationService.class);
    }

    /**
     * Overridden to force business logic even after validation failures. In this case we want to force the enabling of credit split.
     * 
     * @see org.kuali.rice.kns.web.struts.pojo.PojoFormBase#processValidationFail()
     */
    @Override
    public void processValidationFail() {
        try {
            boolean cSplitEnabled = getConfigurationService().getIndicatorParameter(PARAMETER_MODULE_PROPOSAL_DEVELOPMENT, PARAMETER_COMPONENT_DOCUMENT, CREDIT_SPLIT_ENABLED_RULE_NAME)
                && getDocument().getDevelopmentProposal().getInvestigators().size() > 0;
            setCreditSplitEnabled(cSplitEnabled);
        }
        catch (Exception e) {
            warn(MISSING_PARAM_MSG, CREDIT_SPLIT_ENABLED_RULE_NAME);
            warn(e.getMessage());
        }
    }

    /**
     * Gets the creditSplitEnabled attribute. 
     * @return Returns the creditSplitEnabled.
     */
    public boolean isCreditSplitEnabled() {
        return creditSplitEnabled;
    }

    /**
     * Sets the creditSplitEnabled attribute value.
     * @param creditSplitEnabled The creditSplitEnabled to set.
     */
    public void setCreditSplitEnabled(boolean creditSplitEnabled) {
        this.creditSplitEnabled = creditSplitEnabled;
    }

    public String getOptInUnitDetails() {
        return optInUnitDetails;
    }

    public void setOptInUnitDetails(String optInUnitDetails) {
        this.optInUnitDetails = optInUnitDetails;
    }

    public String getOptInCertificationStatus() {
        return optInCertificationStatus;
    }

    public void setOptInCertificationStatus(String optInCertificationStatus) {
        this.optInCertificationStatus = optInCertificationStatus;
    }
    
    public ProposalChangedData getNewProposalChangedData() {
        return newProposalChangedData;
    }

    public void setNewProposalChangedData(ProposalChangedData newProposalChangedData) {
        this.newProposalChangedData = newProposalChangedData;
    }
    
    public boolean isSubmissionStatusVisible() {
        String routeStatus = this.getDocument().getDocumentHeader().getWorkflowDocument().getRouteHeader()
        .getDocRouteStatus();
        return KEWConstants.ROUTE_HEADER_PROCESSED_CD.equals(routeStatus) || KEWConstants.ROUTE_HEADER_FINAL_CD.equals(routeStatus) || (this.getDocument().getDevelopmentProposal().getSubmitFlag() && KEWConstants.ROUTE_HEADER_ENROUTE_CD.equals(routeStatus));
    }
    
    public boolean isSubmissionStatusReadOnly() {
        String principalId = GlobalVariables.getUserSession().getPrincipalId();
        ProposalAuthorizationService proposalAuthService = KraServiceLocator.getService(ProposalAuthorizationService.class);
        boolean canModify = proposalAuthService.hasPermission(principalId, this.getDocument(), PermissionConstants.MODIFY_PROPOSAL);
        KIMService kimService = KraServiceLocator.getService(KIMService.class);
        if (canModify) { return false; }
        List<? extends GroupInfo> groups = kimService.getGroupsForPrincipal(principalId);
        for (GroupInfo group: groups) {
            if (group.getGroupName().equals("OSP")) {
                return false;
            }
        }
        return true;
    }

    public Long getVersionNumberForS2sOpportunity() {
        return versionNumberForS2sOpportunity;
    }

    public void setVersionNumberForS2sOpportunity(Long versionNumberForS2sOpportunity) {
        this.versionNumberForS2sOpportunity = versionNumberForS2sOpportunity;
    }    
    
    public void setNewPerformanceSite(ProposalSite newPerformanceSite) {
        this.newPerformanceSite = newPerformanceSite;
    }

    public ProposalSite getNewPerformanceSite() {
        return newPerformanceSite;
    }

    public void setNewOtherOrganization(ProposalSite newOtherOrganization) {
        this.newOtherOrganization = newOtherOrganization;
    }

    public ProposalSite getNewOtherOrganization() {
        return newOtherOrganization;
    }

    public void setApplicantOrganizationHelper(CongressionalDistrictHelper applicantOrganizationHelper) {
        this.applicantOrganizationHelper = applicantOrganizationHelper;
    }

    public CongressionalDistrictHelper getApplicantOrganizationHelper() {
        return applicantOrganizationHelper;
    }

    public void setPerformingOrganizationHelper(CongressionalDistrictHelper performingOrganizationHelper) {
        this.performingOrganizationHelper = performingOrganizationHelper;
    }

    public CongressionalDistrictHelper getPerformingOrganizationHelper() {
        return performingOrganizationHelper;
    }

    public void setPerformanceSiteHelpers(List<CongressionalDistrictHelper> performanceSiteHelpers) {
        this.performanceSiteHelpers = performanceSiteHelpers;
    }

    public List<CongressionalDistrictHelper> getPerformanceSiteHelpers() {
        return performanceSiteHelpers;
    }

    public void setOtherOrganizationHelpers(List<CongressionalDistrictHelper> otherOrganizationHelpers) {
        this.otherOrganizationHelpers = otherOrganizationHelpers;
    }

    public List<CongressionalDistrictHelper> getOtherOrganizationHelpers() {
        return otherOrganizationHelpers;
    }

    public final String getProposalFormTabTitle() {
        String totalForms = getSponsorFormTemplates().size() + "";
        return proposalFormTabTitle.concat("(" + totalForms + ")");
    }

    public final void setProposalFormTabTitle(String proposalFormTabTitle) {
        this.proposalFormTabTitle = proposalFormTabTitle;
    }
 
    public List<SponsorFormTemplateList> getSponsorFormTemplates() {
        return sponsorFormTemplates;
    }

    public void setSponsorFormTemplates(List<SponsorFormTemplateList> sponsorFormTemplates) {
        this.sponsorFormTemplates = sponsorFormTemplates;
    }

    public SponsorFormTemplateList getSponsorFormTemplate(int index) {
        while (getSponsorFormTemplates().size() <= index) {
            getSponsorFormTemplates().add(new SponsorFormTemplateList());
        }
        
        return getSponsorFormTemplates().get(index);
    }

    public boolean isSaveAfterCopy() {
        return saveAfterCopy;
    }

    public void setSaveAfterCopy(boolean val) {
        saveAfterCopy = val;
    }
    
//  Set the document controls that should be available on the page
    protected void setSaveDocumentControl(Map editMode) {
        getDocumentActions().remove(KNSConstants.KUALI_ACTION_CAN_SAVE);

        if (isProposalAction() && hasModifyProposalPermission(editMode)) {
            getDocumentActions().put(KNSConstants.KUALI_ACTION_CAN_SAVE, KNSConstants.KUALI_DEFAULT_TRUE_VALUE);
        }
        else if (isNarrativeAction() && hasModifyNarrativesPermission(editMode)) {
            getDocumentActions().put(KNSConstants.KUALI_ACTION_CAN_SAVE, KNSConstants.KUALI_DEFAULT_TRUE_VALUE);
        }
        else if (isBudgetVersionsAction() && (hasModifyCompletedBudgetPermission(editMode) || hasModifyBudgetPermission(editMode))) {
            getDocumentActions().put(KNSConstants.KUALI_ACTION_CAN_SAVE, KNSConstants.KUALI_DEFAULT_TRUE_VALUE);
        }
    }
    
    // Returns piece that should be locked for this form
    protected String getLockRegion() {
        String lockRegion = "";
        if (isProposalAction()) {
            lockRegion = KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL;
        } else if (isNarrativeAction()) {
            lockRegion = null;
        } else if (isBudgetVersionsAction()) {
            lockRegion = KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET;
        }
       // return lockRegion;
        // TODO : a hack to return true for now because 'navigateto is null
        return KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL;
    }
    
    // Checks whether the action associated with this form instance maps to a ProposalDevelopment page
    private boolean isProposalAction() {
        boolean isProposalAction = false;

        if ((StringUtils.isNotBlank(actionName) && StringUtils.isNotBlank(getMethodToCall())) 
                && actionName.startsWith("Proposal") && !actionName.contains("AbstractsAttachments")
                && !actionName.contains("BudgetVersions") 
                && StringUtils.isEmpty(navigateTo) && !getMethodToCall().equalsIgnoreCase(Constants.HEADER_TAB)) {
            isProposalAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateTo) && (navigateTo.equalsIgnoreCase(Constants.PROPOSAL_PAGE) 
                || navigateTo.equalsIgnoreCase(Constants.SPECIAL_REVIEW_PAGE) || navigateTo.equalsIgnoreCase(Constants.CUSTOM_ATTRIBUTES_PAGE) 
                || navigateTo.equalsIgnoreCase(Constants.KEY_PERSONNEL_PAGE) || navigateTo.equalsIgnoreCase(Constants.PERMISSIONS_PAGE) 
                || navigateTo.equalsIgnoreCase(Constants.QUESTIONS_PAGE) 
                || navigateTo.equalsIgnoreCase(Constants.GRANTS_GOV_PAGE) || navigateTo.equalsIgnoreCase(Constants.PROPOSAL_ACTIONS_PAGE))) {
            isProposalAction = true;
        }

        return isProposalAction;
    }
    
    // Checks whether the action associated with this form instance maps to the Narrative page
    private boolean isNarrativeAction() {
        boolean isNarrativeAction = false;

        if (StringUtils.isNotBlank(actionName) && StringUtils.isNotBlank(getMethodToCall()) 
                && actionName.contains("AbstractsAttachments") 
                && StringUtils.isEmpty(navigateTo) && !getMethodToCall().equalsIgnoreCase(Constants.HEADER_TAB)) { 
            isNarrativeAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateTo) && navigateTo.equalsIgnoreCase(Constants.ATTACHMENTS_PAGE)) {
            isNarrativeAction = true;
        }

        return isNarrativeAction;

    }
    
    // Checks whether the action associated with this form instance maps to the BudgetVersions page
    private boolean isBudgetVersionsAction() {
        boolean isBudgetVersionsAction = false;

        if (StringUtils.isNotBlank(actionName) && actionName.contains("BudgetVersions")  
                && StringUtils.isNotBlank(getMethodToCall()) 
                && StringUtils.isEmpty(navigateTo) && !getMethodToCall().equalsIgnoreCase(Constants.HEADER_TAB)) { 
            isBudgetVersionsAction = true;
        }
        else if (StringUtils.isNotEmpty(navigateTo) && (navigateTo.equalsIgnoreCase(Constants.BUDGET_VERSIONS_PAGE) 
                || navigateTo.equalsIgnoreCase(Constants.PD_BUDGET_VERSIONS_PAGE))) {
            isBudgetVersionsAction = true; 
        }

        return isBudgetVersionsAction;
    }
    
    public boolean isInWorkflow() {
        return KraServiceLocator.getService(KraWorkflowService.class).isInWorkflow(this.getDocument());
    }
    
    /**
     * Retrieves the {@link ProposalDevelopmentDocument ProposalDevelopmentDocument}.
     * @return {@link ProposalDevelopmentDocument ProposalDevelopmentDocument}
     */
    @Override
    public ProposalDevelopmentDocument getDocument() {
        //overriding and using covariant return to avoid casting
        //Document to ProposalDevelopmentDocument everywhere
        return (ProposalDevelopmentDocument) super.getDocument();
    }
    
    @Override
    public boolean shouldPropertyBePopulatedInForm(String requestParameterName, HttpServletRequest request) {
        // TODO rice upgrade temp hack - to populate customdata
        // use expandedtextarea will cause issues with editableproperties in formdata.
        // the sessiondocument is not working well with this.  not sure if this should be a rice issue.
//        if (requestParameterName.startsWith("customAttributeValue")) {
//            return true;
//        } else {
//            return super.shouldPropertyBePopulatedInForm(requestParameterName, request);
//        }
        return true;
    }
    
    /**
     * Gets the newHierarchyProposal attribute. 
     * @return Returns the newHierarchyProposal.
     */
    public DevelopmentProposal getNewHierarchyProposal() {
        return newHierarchyProposal;
    }

    /**
     * Sets the newHierarchyProposal attribute value.
     * @param newHierarchyProposal The newHierarchyProposal to set.
     */
    public void setNewHierarchyProposal(DevelopmentProposal newHierarchyProposal) {
        this.newHierarchyProposal = newHierarchyProposal;
    }

    /**
     * Gets the newHierarchyChildProposal attribute. 
     * @return Returns the newHierarchyChildProposal.
     */
    public DevelopmentProposal getNewHierarchyChildProposal() {
        return newHierarchyChildProposal;
    }

    /**
     * Sets the newHierarchyChildProposal attribute value.
     * @param newHierarchyChildProposal The newHierarchyChildProposal to set.
     */
    public void setNewHierarchyChildProposal(DevelopmentProposal newHierarchyChildProposal) {
        this.newHierarchyChildProposal = newHierarchyChildProposal;
    }
    
    public String getHierarchyParentStatus() {
        return HierarchyStatusConstants.Parent.code();
    }
    public String getHierarchyNoneStatus() {
        return HierarchyStatusConstants.None.code();
    }
    public String getHierarchyChildStatus() {
        return HierarchyStatusConstants.Child.code();
    }

    /**
     * Sets the hierarchyProposalSummaries attribute value.
     * @param hierarchyProposalSummaries The hierarchyProposalSummaries to set.
     */
    public void setHierarchyProposalSummaries(List<HierarchyProposalSummary> hierarchyProposalSummaries) {
        this.hierarchyProposalSummaries = hierarchyProposalSummaries;
    }

    /**
     * Gets the hierarchyProposalSummaries attribute. 
     * @return Returns the hierarchyProposalSummaries.
     */
    public List<HierarchyProposalSummary> getHierarchyProposalSummaries() {
        return hierarchyProposalSummaries;
    }

    /**
     * This method makes sure that the Hierarchy tab is not displayed for proposals
     * not in a hierarchy and that the Grants.gov tab is not displayed for children
     * in a hierarchy.
     * 
     * @return Returns the headerNavigationTabs filtered based on hierarchy status.
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
     */
    public HeaderNavigation[] getHeaderNavigationTabs() {
        HeaderNavigation[] tabs = super.getHeaderNavigationTabs();
        List<HeaderNavigation> newTabs = new ArrayList<HeaderNavigation>();
        boolean showHierarchy = getDocument().getDevelopmentProposal().isInHierarchy();
        boolean disableGrantsGov = getDocument().getDevelopmentProposal().isChild();
        
        for (HeaderNavigation tab : tabs) {
            if (tab.getHeaderTabNavigateTo().equals("grantsGov")) {
                tab.setDisabled(disableGrantsGov);
            }
            if(showHierarchy || !tab.getHeaderTabNavigateTo().equals("hierarchy")) {
                newTabs.add(tab);
            }
        }
        tabs = newTabs.toArray(new HeaderNavigation[newTabs.size()]);
        return tabs;
    }    
}
