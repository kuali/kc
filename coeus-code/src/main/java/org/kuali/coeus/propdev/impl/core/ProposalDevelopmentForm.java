/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocument;
import org.kuali.coeus.common.framework.module.CoeusModule;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.sponsor.form.SponsorFormTemplateList;
import org.kuali.coeus.common.framework.unit.Unit;
import org.kuali.coeus.common.framework.unit.UnitService;
import org.kuali.coeus.common.notification.impl.web.struts.form.NotificationHelper;
import org.kuali.coeus.propdev.impl.action.ProposalDevelopmentRejectionBean;
import org.kuali.coeus.propdev.impl.approve.ProposalDevelopmentApproverViewDO;
import org.kuali.coeus.propdev.impl.copy.ProposalCopyCriteria;
import org.kuali.coeus.propdev.impl.custom.ProposalDevelopmentCustomDataHelper;
import org.kuali.coeus.propdev.impl.docperm.ProposalAssignedRole;
import org.kuali.coeus.propdev.impl.docperm.ProposalUser;
import org.kuali.coeus.propdev.impl.docperm.ProposalUserEditRoles;
import org.kuali.coeus.propdev.impl.docperm.ProposalUserRoles;
import org.kuali.coeus.propdev.impl.keyword.PropScienceKeyword;
import org.kuali.coeus.propdev.impl.state.ProposalState;
import org.kuali.coeus.common.framework.auth.KcTransactionalDocumentAuthorizerBase;
import org.kuali.coeus.common.framework.auth.perm.KcAuthorizationService;
import org.kuali.coeus.common.framework.auth.task.ApplicationTask;
import org.kuali.coeus.common.framework.auth.task.TaskAuthorizationService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.kra.common.web.struts.form.ReportHelperBean;
import org.kuali.kra.common.web.struts.form.ReportHelperBeanContainer;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.PermissionConstants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.infrastructure.TaskName;
import org.kuali.coeus.propdev.impl.docperm.ProposalRoleService;
import org.kuali.coeus.common.framework.medusa.MedusaBean;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.attachment.NarrativeStatus;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.coeus.propdev.impl.attachment.NarrativeUserRights;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.editable.ProposalChangedData;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.budget.editable.BudgetChangedData;
import org.kuali.coeus.propdev.impl.auth.task.ProposalTask;
import org.kuali.coeus.propdev.impl.hierarchy.HierarchyProposalSummary;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.person.question.ProposalPersonQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.specialreview.SpecialReviewHelper;
import org.kuali.coeus.common.questionnaire.framework.core.MultiQuestionableFormInterface;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.s2s.S2sAppSubmission;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.S2sUserAttachedForm;
import org.kuali.coeus.common.budget.framework.core.BudgetVersionFormBase;
import org.kuali.coeus.common.framework.custom.CustomDataDocumentForm;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.coreservice.api.parameter.Parameter;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.KewApiConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.util.PerformanceLogger;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.permission.PermissionQueryResults;
import org.kuali.rice.kim.api.permission.PermissionService;
import org.kuali.rice.kim.api.role.Role;
import org.kuali.rice.kns.datadictionary.HeaderNavigation;
import org.kuali.rice.kns.util.ActionFormUtilMap;
import org.kuali.rice.kns.web.ui.ExtraButton;
import org.kuali.rice.kns.web.ui.HeaderField;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.service.DocumentDictionaryService;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;
import static org.kuali.kra.infrastructure.Constants.CREDIT_SPLIT_ENABLED_RULE_NAME;
import static org.kuali.rice.krad.util.KRADConstants.EMPTY_STRING;


/**
 * This class is the Struts form bean for DevelopmentProposal
 */
public class ProposalDevelopmentForm extends BudgetVersionFormBase implements ReportHelperBeanContainer, MultiQuestionableFormInterface, 
                                                                        CustomDataDocumentForm {
    
    private static final long serialVersionUID = 7928293162992415894L;
    private static final Log LOG = LogFactory.getLog(ProposalDevelopmentForm.class);
    private static final String MISSING_PARAM_MSG = "Couldn't find parameter ";
    
    private boolean creditSplitEnabled;
    private String primeSponsorName;
    private ProposalPerson newProposalPerson;
    private List<ProposalPersonDegree> newProposalPersonDegree;
    private List<Unit> newProposalPersonUnit;
    private String newRolodexId;
    private String newPersonId;
    private Narrative newNarrative;
    private FormFile narrativeFile;
    private boolean showMaintenanceLinks;
    private ProposalAbstract newProposalAbstract;
    private ProposalPersonBiography newPropPersonBio;
    private Narrative newInstituteAttachment;
    private boolean grantsGovAuditActivated;
    private ProposalCopyCriteria copyCriteria;
    private Map<String, Parameter> proposalDevelopmentParameters;
    private ProposalUser newProposalUser;
    private String newBudgetVersionName;
    private List<ProposalUserRoles> proposalUserRolesList = null;
    private ProposalUserEditRoles proposalUserEditRoles;
    private boolean newProposalPersonRoleRendered;
    private List<NarrativeUserRights> newNarrativeUserRights;
    private S2sOpportunity newS2sOpportunity;
    private List<S2sAppSubmission> newS2sAppSubmission;
    private S2sUserAttachedForm newS2sUserAttachedForm;

    private SortedMap<String, List<CustomAttributeDocument>> customAttributeGroups;
    private Map<String, String[]> customAttributeValues;
    private List<Narrative> narratives;
    private boolean saveAfterCopy;
    private String optInUnitDetails;
    private String optInCertificationStatus;
    private ProposalChangedData newProposalChangedData;
    private Long versionNumberForS2sOpportunity;
    private ProposalSite newPerformanceSite;
    private ProposalSite newOtherOrganization;
    
    private ProposalDevelopmentApproverViewDO approverViewDO;
    
    private SpecialReviewHelper specialReviewHelper;
    private String newHierarchyProposalNumber;
    private String newHierarchyChildProposalNumber;
    private String newHierarchyBudgetTypeCode;
    private List<HierarchyProposalSummary> hierarchyProposalSummaries;
    private DevelopmentProposal proposalToSummarize;
    private HierarchyProposalSummary proposalSummary;
    private Budget budgetToSummarize;
    private String proposalNumberToSummarize;
    private String budgetNumberToSummarize;
    private transient boolean showSubmissionDetails;
    private transient boolean grantsGovSubmitFlag;
    private transient boolean saveXmlPermission; 
    private transient boolean grantsGovSelectFlag; 
   
    private String proposalFormTabTitle = "Print Sponsor Form Packages ";
    private transient ParameterService parameterService;

    /* This is just a list of sponsor form package details - large objects not loaded */
    private List<SponsorFormTemplateList> sponsorFormTemplates;
    private transient KcPersonService kcPersonService;
    
    /* These 2 properties are used for autogenerating an institutional proposal for a resubmission */
    private String resubmissionOption;
    private String institutionalProposalToVersion;
    
    private MedusaBean medusaBean;
    
    private ReportHelperBean reportHelperBean;
    private List<String> proposalDataOverrideMethodToCalls;
    
    private List<String> BudgetDataOverrideMethodToCalls;
    
    private boolean canCreateProposal;
    
    private boolean viewFundingSource;
    
    private ProposalDevelopmentRejectionBean proposalDevelopmentRejectionBean;
    private boolean showRejectionConfirmation;
    
    private NotificationHelper<ProposalDevelopmentNotificationContext> notificationHelper;
    
    private ProposalDevelopmentQuestionnaireHelper proposalDevelopmentQuestionnaireHelper;
    private ProposalDevelopmentS2sQuestionnaireHelper proposalDevelopmentS2sQuestionnaireHelper;
    private List<ProposalPersonQuestionnaireHelper> proposalPersonQuestionnaireHelpers;
    private List<AnswerHeader> answerHeadersToDelete;
    private List<ProposalPerson> proposalPersonsToDelete;
    private transient S2sOpportunity s2sOpportunity;
    private BudgetChangedData newBudgetChangedData;
   
    private String[] selectedBudgetPrint;
    private static final String PROPOSAL_SUMMARY_TAB_INDICATOR = "enableProposalSummaryTab";   
    private static final String NONE = "None";
    
    private transient String currentPersonCountryCode = "";
    private ProposalDevelopmentCustomDataHelper customDataHelper;
    private String narrativeStatusesChangeKey;
    private NarrativeStatus narrativeStatusesChange;

    private transient ProposalRoleService proposalRoleService;
    private transient KcAuthorizationService kcAuthorizationService;
    private transient KcWorkflowService kcWorkflowService;
    private transient UnitService unitService;
    private transient TaskAuthorizationService taskAuthorizationService;
    private transient DocumentDictionaryService documentDictionaryService;
    private transient ProposalDevelopmentService proposalDevelopmentService;
    
	public ProposalDevelopmentForm() {
        super();
        initialize();        
        sponsorFormTemplates = new ArrayList<SponsorFormTemplateList>();
    }
    
    @Override
    protected String getDefaultDocumentTypeName() {
        return "ProposalDevelopmentDocument";
    }
    
    /**
     * Looks up and returns the ParameterService.
     * @return the parameter service. 
     */
    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    
    protected ProposalRoleService getProposalRoleService() {
		if (proposalRoleService == null )
			proposalRoleService = KcServiceLocator.getService(ProposalRoleService.class);
    	return proposalRoleService;
	}
    
	protected KcAuthorizationService getKcAuthorizationService() {
		if (kcAuthorizationService == null)
			kcAuthorizationService = KcServiceLocator.getService(KcAuthorizationService.class);
		return kcAuthorizationService;
	}
	
	protected KcWorkflowService getKcWorkflowService(){
		if (kcWorkflowService == null )
			kcWorkflowService = KcServiceLocator.getService(KcWorkflowService.class);
		return kcWorkflowService;
	}
	
	 protected UnitService getUnitService(){
		 if (unitService == null)
			 unitService = KcServiceLocator.getService(UnitService.class);
		 return unitService;
	 }
	 
	 protected TaskAuthorizationService getTaskAuthorizationService (){
		 if (taskAuthorizationService == null)
			 taskAuthorizationService = KcServiceLocator.getService(TaskAuthorizationService.class);
		 return taskAuthorizationService;
	 }
	 
	 protected DocumentDictionaryService getDocumentDictionaryService() {
	        if (documentDictionaryService == null) {
	        	documentDictionaryService = KRADServiceLocatorWeb.getDocumentDictionaryService();
	        }
	        return documentDictionaryService;
	    }
	 
	 protected ProposalDevelopmentService getProposalDevelopmentService(){
		 if (proposalDevelopmentService == null)
			 proposalDevelopmentService = KcServiceLocator.getService(ProposalDevelopmentService.class);
		 return proposalDevelopmentService;
	 }
    /**
     *
     * This method initialize all form variables
     */
    @SuppressWarnings("unchecked")
    public void initialize() {
        setNewNarrative(createNarrative());
        setNewProposalPerson(new ProposalPerson());
        setNewProposalPersonDegree(new ArrayList<ProposalPersonDegree>());
        setNewProposalPersonUnit(new ArrayList<Unit>());
        setNewProposalAbstract(new ProposalAbstract());
        setNewProposalUser(new ProposalUser());
        setNewS2sOpportunity(new S2sOpportunity());
        setNewS2sUserAttachedForm(new S2sUserAttachedForm());
        setNewPerformanceSite(new ProposalSite());
        setNewOtherOrganization(new ProposalSite());
        setSpecialReviewHelper(new SpecialReviewHelper(this));
        customAttributeValues = new HashMap<String, String[]>();
        setCopyCriteria(new ProposalCopyCriteria(getProposalDevelopmentDocument()));
        proposalDevelopmentParameters = new HashMap<String, Parameter>();
        newProposalPersonRoleRendered = false;
        setNewProposalChangedData(new ProposalChangedData());
        setNewBudgetChangedData(new BudgetChangedData());
        versionNumberForS2sOpportunity = null;
        setHierarchyProposalSummaries(new ArrayList<HierarchyProposalSummary>());
        medusaBean = new MedusaBean();
        reportHelperBean = new ReportHelperBean(this);
        canCreateProposal = isAuthorizedToCreateProposal();
        setProposalDevelopmentRejectionBean(new ProposalDevelopmentRejectionBean());
        setNotificationHelper(new NotificationHelper());
        //setQuestionnaireHelper(new ProposalDevelopmentQuestionnaireHelper(this));
        //setS2sQuestionnaireHelper(new ProposalDevelopmentS2sQuestionnaireHelper(this));
        
        proposalPersonQuestionnaireHelpers = new ArrayList<ProposalPersonQuestionnaireHelper>();
        for (ProposalPerson person : this.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
            ProposalPersonQuestionnaireHelper helper = new ProposalPersonQuestionnaireHelper(person);
            proposalPersonQuestionnaireHelpers.add(helper);
        }
        
        answerHeadersToDelete = new ArrayList<AnswerHeader>();
        proposalPersonsToDelete = new ArrayList<ProposalPerson>();
        
        setNewInstituteAttachment(createNarrative());
        setNewPropPersonBio(new ProposalPersonBiography());
        setApproverViewTabTitle();

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
        ProposalDevelopmentDocument proposalDevelopmentDocument=getProposalDevelopmentDocument();

        proposalDevelopmentDocument.getDevelopmentProposal().refreshReferenceObject("sponsor");

        // Temporary hack for KRACOEUS-489
        if (getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) getActionFormUtilMap()).clear();
        }
    }
    
    /**
     * 
     * This method helps debug the http request object.  It prints the values in the request.
     * @param request
     */
    public static void printRequest(HttpServletRequest request) {
        for (Object param : request.getParameterMap().keySet()) {
            Object value = request.getParameter(param.toString());
        }
    }
    
    @Override
    public void populateHeaderFields(WorkflowDocument workflowDocument) {
        super.populateHeaderFields(workflowDocument);
        
        ProposalDevelopmentDocument pd = getProposalDevelopmentDocument();
        if (!pd.isProposalDeleted()) {
            ProposalState proposalState = (pd == null) ? null : pd.getDevelopmentProposal().getProposalState();
            HeaderField docStatus = new HeaderField("DataDictionary.AttributeReference.attributes.workflowDocumentStatus", proposalState == null? "" : proposalState.getDescription());
            
            getDocInfo().set(1, docStatus);
            
            if (pd.getDevelopmentProposal().getSponsor() == null) {
                getDocInfo().add(new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.sponsorS2S", ""));
            } else {
                S2sOpportunity opportunity = pd.getDevelopmentProposal().getS2sOpportunity();
                String provider = (opportunity == null || opportunity.getS2sProvider() == null || opportunity.getS2sProvider().getDescription() == null) ? NONE : opportunity.getS2sProvider().getDescription();
                getDocInfo().add(new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.sponsorS2S", 
                        pd.getDevelopmentProposal().getSponsor().getSponsorName() + "/" + provider));
            }
            
            if (pd.getDevelopmentProposal().getPrincipalInvestigator() != null) {
                boolean found = false;
                
                for(Iterator<ProposalPerson> person_it = pd.getDevelopmentProposal().getInvestigators().iterator();
                    person_it.hasNext() && !found; ){
                    ProposalPerson investigator = person_it.next();
                    
                    if (investigator.isPrincipalInvestigator()) {
                        found = true; // Will break out of the loop as soon as the PI is found
                        getDocInfo().add(new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", investigator.getFullName()));
                    }
                }
            }
            else {
                getDocInfo().add(new HeaderField("DataDictionary.KraAttributeReferenceDummy.attributes.principalInvestigator", EMPTY_STRING));
            }
        }
        
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


        ProposalDevelopmentDocument proposalDevelopmentDocument = this.getProposalDevelopmentDocument();
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
        setResubmissionOption(null);
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
        if (this.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons().size() > this.newProposalPersonUnit.size()) {
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

        if (this.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons().size() > this.newProposalPersonDegree.size()) {
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
        return KcServiceLocator.getService(BusinessObjectService.class);
    }

    @SuppressWarnings("unchecked")
    public Map<String, ScaleTwoDecimal> getCreditSplitTotals() {
        return this.getKeyPersonnelService().calculateCreditSplitTotals(getProposalDevelopmentDocument());    
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
        ProposalDevelopmentDocument doc = this.getProposalDevelopmentDocument();
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
     * @return true if copying budget(s) is disabled; otherwise false.
     */
    public boolean getIsCopyBudgetDisabled() {
        return !(this.getProposalDevelopmentDocument().getBudgetDocumentVersions().size() > 0);
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
        
        Collection<Role> roles = getKimProposalRoles();
        for (Role role : roles) {
            if (!StringUtils.equals(role.getName(), RoleConstants.UNASSIGNED)) {
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
     * @return the list of proposal roles of type org.kuali.coeus.common.permissions.impl.web.bean.Role
     */
    public List<org.kuali.coeus.common.permissions.impl.web.bean.Role> getProposalRoles() {
        List<org.kuali.coeus.common.permissions.impl.web.bean.Role> returnRoleBeans = 
            new ArrayList<org.kuali.coeus.common.permissions.impl.web.bean.Role>();
        
        Collection<Role> roles = getKimProposalRoles();
        
        
        QueryByCriteria.Builder queryBuilder = QueryByCriteria.Builder.create();
        List<Predicate> predicates = new ArrayList<Predicate>();
        PermissionQueryResults permissionResults = null;
        
        for (Role role : roles) {
            if (!StringUtils.equals(role.getName(), RoleConstants.UNASSIGNED)) {
                predicates.add(PredicateFactory.equal("rolePermissions.roleId", role.getId()));
                queryBuilder.setPredicates(PredicateFactory.and(predicates.toArray(new Predicate[] {})));
                permissionResults = getKimPermissionService().findPermissions(queryBuilder.build());
                if (permissionResults != null && permissionResults.getResults().size() > 0) {
                    returnRoleBeans.add(new org.kuali.coeus.common.permissions.impl.web.bean.Role(
                    		role.getName(), role.getDescription(),permissionResults.getResults()));
                }
                predicates.clear();
                queryBuilder = QueryByCriteria.Builder.create();
                permissionResults = null;
                }
        }
        
        return returnRoleBeans;
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
            Collection<Role> roles = getKimProposalRoles();
            for (Role role : roles) {
                addPersons(proposalUserRolesList, role.getName());
            }
            
            sortProposalUsers();  
        }
        
        return proposalUserRolesList;
    }
    
    public List<ProposalUserRoles> getCurrentProposalUserRoles() {
        List<ProposalUserRoles> current = new ArrayList<ProposalUserRoles>();
        
        Collection<Role> roles = getKimProposalRoles();
        for (Role role : roles) {
            addPersons(current, role.getName());
        }
        
        return current;
    }
    
    /**
     * Get all of the proposal roles.
     * @return
     */
    public Collection<Role> getKimProposalRoles() {
    	List<Role> proposalRoles = getProposalRoleService().getRolesForDisplay();
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
        KcPerson person = getKcPersonService().getKcPersonByUserName(proposalUser.getUsername());
        ProposalUserRoles userRoles = buildProposalUserRoles(person, proposalUser.getRoleName());
        proposalUserRolesList.add(userRoles);
        sortProposalUsers();
    }
    
    /**
     * Gets the KC Person Service.
     * @return KC Person Service.
     */
    protected KcPersonService getKcPersonService() {
        if (this.kcPersonService == null) {
            this.kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        
        return this.kcPersonService;
    }
    
    /**
     * Add a set of persons to the proposalUserRolesList for a given role.
     * 
     * @param propUserRolesList the list to add to
     * @param roleName the name of role to query for persons assigned to that role
     */
    private void addPersons(List<ProposalUserRoles> propUserRolesList, String roleName) {
    	ProposalDevelopmentDocument doc = this.getProposalDevelopmentDocument();
        
        List<String> users = getKcAuthorizationService().getPrincipalsInRole(doc, roleName);
        List<KcPerson> persons = new ArrayList<>();
        for(String userId : users) {
            KcPerson person = getKcPersonService().getKcPersonByPersonId(userId);
            if (person != null && person.getActive()) {
                persons.add(person);
            }
        }

        for (KcPerson person : persons) {
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
    private ProposalUserRoles buildProposalUserRoles(KcPerson person, String roleName) {
        ProposalUserRoles proposalUserRoles = new ProposalUserRoles();
        
        // Set the person's username, rolename, fullname, and home unit.

        proposalUserRoles.setUsername(person.getUserName());
        proposalUserRoles.addRoleName(roleName);
        proposalUserRoles.setFullname(person.getFullName());
        proposalUserRoles.setUnitNumber(person.getOrganizationIdentifier());
        
        // Query the database to find the name of the unit.
            
        Unit unit = getUnitService().getUnit(person.getOrganizationIdentifier());
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
        this.initialize();
        this.proposalUserRolesList = null;
        
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
    @Override
    public String getHeaderDispatch() {
        return this.getDocumentActions().containsKey(KRADConstants.KUALI_ACTION_CAN_SAVE) ? "save" : "reload";
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

//    public boolean isReject() {
//        return reject;
//    }
//
//    public void setReject(boolean reject) {
//        this.reject = reject;
//    }
    
    public List<ExtraButton> getExtraActionsButtons() {
        // clear out the extra buttons array
        extraButtons.clear();
        ProposalDevelopmentDocument doc = this.getProposalDevelopmentDocument();
        String externalImageURL = Constants.KRA_EXTERNALIZABLE_IMAGES_URI_KEY;

        
        ConfigurationService configurationService = CoreApiServiceLocator.getKualiConfigurationService();
        if( getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new ProposalTask("submitToSponsor",doc ))) {       
            if ( isCanSubmitToSponsor() ) {
                String submitToGrantsGovImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_submittosponsor.gif";
                addExtraButton("methodToCall.submitToSponsor", submitToGrantsGovImage, "Submit To Sponsor");
            }
            if(isCanSubmitToGrantsGov()) {
              if(doc.getDevelopmentProposal().getS2sOpportunity() != null 
                      && doc.getDevelopmentProposal().getS2sAppSubmission().size() == 0 ){ 
                     String grantsGovSubmitImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_submittos2s.gif";
                     addExtraButton("methodToCall.submitToGrantsGov", grantsGovSubmitImage, "Submit To S2S");
              }
            }
        }
        //check to see if they are authorized to reject the document
        
        if( getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new ProposalTask("rejectProposal",doc))) {
            String resubmissionImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_reject.gif";
            addExtraButton("methodToCall.reject", resubmissionImage, "Reject");
        }
        
        if (getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), new ProposalTask("deleteProposal", doc))) {
            String deleteProposalImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_deleteproposal.gif";
            addExtraButton("methodToCall.deleteProposal", deleteProposalImage, "Delete Proposal");
        }
        
        String sendNotificationImage = configurationService.getPropertyValueAsString(externalImageURL) + "buttonsmall_send_notification.gif";
        addExtraButton("methodToCall.sendNotification", sendNotificationImage, "Send Notification");
        
        return extraButtons;
    }
    
    /**
     * Overridden to force business logic even after validation failures. In this case we want to force the enabling of credit split.
     * 
     * @see org.kuali.rice.kns.web.struts.form.pojo.PojoFormBase#processValidationFail()
     */
    @Override
    public void processValidationFail() {
        try {
            boolean cSplitEnabled = this.getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, CREDIT_SPLIT_ENABLED_RULE_NAME)
                && getProposalDevelopmentDocument().getDevelopmentProposal().getInvestigators().size() > 0;
            setCreditSplitEnabled(cSplitEnabled);
        }
        catch (Exception e) {
            LOG.warn(MISSING_PARAM_MSG + CREDIT_SPLIT_ENABLED_RULE_NAME);
            LOG.warn(e.getMessage());
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
    
    public ProposalChangedData getNewProposalChangedData() {
        return newProposalChangedData;
    }

    public void setNewProposalChangedData(ProposalChangedData newProposalChangedData) {
        this.newProposalChangedData = newProposalChangedData;
    }
    
    public boolean isSubmissionStatusVisible() {
        String routeStatus = this.getProposalDevelopmentDocument().getDocumentHeader().getWorkflowDocument().getStatus().getCode();
        return KewApiConstants.ROUTE_HEADER_PROCESSED_CD.equals(routeStatus) || KewApiConstants.ROUTE_HEADER_FINAL_CD.equals(routeStatus) || (this.getProposalDevelopmentDocument().getDevelopmentProposal().getSubmitFlag() && KewApiConstants.ROUTE_HEADER_ENROUTE_CD.equals(routeStatus));
    }
    
    public boolean isSubmissionStatusReadOnly() {
        boolean result = true;
        String userId = GlobalVariables.getUserSession().getPrincipalId();
        boolean canModify = getKcAuthorizationService().hasPermission(userId, this.getProposalDevelopmentDocument(), PermissionConstants.MODIFY_PROPOSAL);
        if (canModify) { result = false; }
        
        if(getKcAuthorizationService().hasRole(userId, RoleConstants.KC_ADMIN_NAMESPACE, RoleConstants.OSP_ADMINISTRATOR)) {
            result =  false;
        }
        
        return result;
    }
    
    public boolean isCanSubmitToSponsor() {
        String routeStatus = this.getProposalDevelopmentDocument().getDocumentHeader().getWorkflowDocument().getStatus().getCode();
        return ( KewApiConstants.ROUTE_HEADER_PROCESSED_CD.equals(routeStatus) || KewApiConstants.ROUTE_HEADER_FINAL_CD.equals(routeStatus) || KewApiConstants.ROUTE_HEADER_ENROUTE_CD.equals(routeStatus) ) 
                    && !this.getProposalDevelopmentDocument().getDevelopmentProposal().getSubmitFlag() && !isSubmissionStatusReadOnly();
    }

    public boolean isCanSubmitToGrantsGov() {
        String routeStatus = this.getProposalDevelopmentDocument().getDocumentHeader().getWorkflowDocument().getStatus().getCode();
        return ( KewApiConstants.ROUTE_HEADER_PROCESSED_CD.equals(routeStatus) || KewApiConstants.ROUTE_HEADER_FINAL_CD.equals(routeStatus) || KewApiConstants.ROUTE_HEADER_ENROUTE_CD.equals(routeStatus) ) 
                    &&  !isSubmissionStatusReadOnly();
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

    public ProposalDevelopmentApproverViewDO getApproverViewDO() {
        return approverViewDO;
    }

    public void setApproverViewDO(ProposalDevelopmentApproverViewDO approverViewDO) {
        this.approverViewDO = approverViewDO;
    }
    
    public SpecialReviewHelper getSpecialReviewHelper() {
        return specialReviewHelper;
    }

    public void setSpecialReviewHelper(SpecialReviewHelper specialReviewHelper) {
        this.specialReviewHelper = specialReviewHelper;
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

    @Override
    public boolean isSaveAfterCopy() {
        return saveAfterCopy;
    }

    @Override
    public void setSaveAfterCopy(boolean val) {
        saveAfterCopy = val;
    }
    
//  Set the document controls that should be available on the page
    @Override
    protected void setSaveDocumentControl(Map editMode) {
        getDocumentActions().remove(KRADConstants.KUALI_ACTION_CAN_SAVE);

        if (isProposalAction() && hasModifyProposalPermission(editMode)) {
            getDocumentActions().put(KRADConstants.KUALI_ACTION_CAN_SAVE, KRADConstants.KUALI_DEFAULT_TRUE_VALUE);
        }
        else if (isNarrativeAction() && hasModifyNarrativesPermission(editMode)) {
            getDocumentActions().put(KRADConstants.KUALI_ACTION_CAN_SAVE, KRADConstants.KUALI_DEFAULT_TRUE_VALUE);
        }
        else if (isBudgetVersionsAction() && (hasModifyCompletedBudgetPermission(editMode) || hasModifyBudgetPermission(editMode))) {
            getDocumentActions().put(KRADConstants.KUALI_ACTION_CAN_SAVE, KRADConstants.KUALI_DEFAULT_TRUE_VALUE);
        }
    }
    
    // Returns piece that should be locked for this form
    @Override
    protected String getLockRegion() {
        //default lock region
        String lockRegion = KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL;
        if (isProposalAction()) {
            lockRegion = KraAuthorizationConstants.LOCK_DESCRIPTOR_PROPOSAL;
        } else if (isNarrativeAction()) {
            lockRegion = KraAuthorizationConstants.LOCK_DESCRIPTOR_NARRATIVES;
        } else if (isBudgetVersionsAction()) {
            lockRegion = KraAuthorizationConstants.LOCK_DESCRIPTOR_BUDGET;
        }
        
        return lockRegion;
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
        return getKcWorkflowService().isInWorkflow(this.getDocument());
    }
    
    /**
     * Retrieves the {@link ProposalDevelopmentDocument ProposalDevelopmentDocument}.
     * @return {@link ProposalDevelopmentDocument ProposalDevelopmentDocument}
     */
    public ProposalDevelopmentDocument getProposalDevelopmentDocument() {
        return (ProposalDevelopmentDocument) super.getDocument();
    }
    
    /**
     * Gets the newHierarchyProposalNumber attribute. 
     * @return Returns the newHierarchyProposalNumber.
     */
    public String getNewHierarchyProposalNumber() {
        return newHierarchyProposalNumber;
    }

    /**
     * Sets the newHierarchyProposalNumber attribute value.
     * @param newHierarchyProposalNumber The newHierarchyProposalNumber to set.
     */
    public void setNewHierarchyProposalNumber(String newHierarchyProposalNumber) {
        this.newHierarchyProposalNumber = newHierarchyProposalNumber;
    }

    /**
     * Gets the newHierarchyChildProposalNumber attribute. 
     * @return Returns the newHierarchyChildProposalNumber.
     */
    public String getNewHierarchyChildProposalNumber() {
        return newHierarchyChildProposalNumber;
    }

    /**
     * Sets the newHierarchyChildProposalNumber attribute value.
     * @param newHierarchyChildProposalNumber The newHierarchyChildProposalNumber to set.
     */
    public void setNewHierarchyChildProposalNumber(String newHierarchyChildProposalNumber) {
        this.newHierarchyChildProposalNumber = newHierarchyChildProposalNumber;
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
     * Sets the newHierarchyBudgetTypeCode attribute value.
     * @param newHierarchyBudgetTypeCode The newHierarchyBudgetTypeCode to set.
     */
    public void setNewHierarchyBudgetTypeCode(String newHierarchyBudgetTypeCode) {
        this.newHierarchyBudgetTypeCode = newHierarchyBudgetTypeCode;
    }

    /**
     * Gets the newHierarchyBudgetTypeCode attribute. 
     * @return Returns the newHierarchyBudgetTypeCode.
     */
    public String getNewHierarchyBudgetTypeCode() {
        return newHierarchyBudgetTypeCode;
    }

    /**
     * This method makes sure that the Hierarchy tab is not displayed for proposals
     * not in a hierarchy and that the Grants.gov tab is not displayed for children
     * in a hierarchy and when the sponsor is not a Federal Sponsor.
     * 
     * @return Returns the headerNavigationTabs filtered based on hierarchy status.
     * @see org.kuali.rice.kns.web.struts.form.KualiForm#getHeaderNavigationTabs()
     */
    @Override
    public HeaderNavigation[] getHeaderNavigationTabs() {
        HeaderNavigation[] tabs = super.getHeaderNavigationTabs();
        List<HeaderNavigation> newTabs = new ArrayList<HeaderNavigation>();
        DevelopmentProposal devProposal = getProposalDevelopmentDocument().getDevelopmentProposal();
        boolean showHierarchy = devProposal.isInHierarchy();
        boolean disableGrantsGov = !isGrantsGovEnabled();

        boolean showProposalSummary = true;        
        Parameter proposalSummaryIndicatorParam = this.getParameterService().getParameter(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, PROPOSAL_SUMMARY_TAB_INDICATOR);
        if ( proposalSummaryIndicatorParam != null && "N".equalsIgnoreCase(proposalSummaryIndicatorParam.getValue()) )
        {
            showProposalSummary = false;
        }
        
        
        for (HeaderNavigation tab : tabs) {
            if (tab.getHeaderTabNavigateTo().equals("grantsGov")) {
                tab.setDisabled(disableGrantsGov);
            }
//            if (showHierarchy || !tab.getHeaderTabNavigateTo().equals("hierarchy")) {
//                if (tab.getHeaderTabNavigateTo().equals("customData")) {
//                    if (!this.getProposalDevelopmentDocument().getCustomAttributeDocuments().isEmpty()) {
//                        newTabs.add(tab);
//                    }
//                } else {
//                    newTabs.add(tab);
//                }
//            }
            if((showHierarchy || !tab.getHeaderTabNavigateTo().equals("hierarchy"))) 
            {
                if (!tab.getHeaderTabNavigateTo().toUpperCase().equals("APPROVERVIEW") || showProposalSummary || canPerformWorkflowAction()) 
                {
                    newTabs.add(tab);
                }
            }
        }
        tabs = newTabs.toArray(new HeaderNavigation[newTabs.size()]);
        return tabs;
    }
    
    public boolean canPerformWorkflowAction() {
        //If an exception (like AuthorizationException) occurred before this call, the workflowDocument will be null
        if (!getProposalDevelopmentDocument().getDocumentHeader().hasWorkflowDocument()) {
           return false;
        }
        KcTransactionalDocumentAuthorizerBase documentAuthorizer = (KcTransactionalDocumentAuthorizerBase)getDocumentDictionaryService().getDocumentAuthorizer(this.getDocument());
        
        Person user = GlobalVariables.getUserSession().getPerson();
        Set<String> documentActions = documentAuthorizer.getDocumentActions(this.getDocument(), user, null);

        boolean canApprove= documentActions.contains(KRADConstants.KUALI_ACTION_CAN_APPROVE);
        boolean canAck = documentActions.contains(KRADConstants.KUALI_ACTION_CAN_ACKNOWLEDGE);
        boolean canDisapprove = documentActions.contains(KRADConstants.KUALI_ACTION_CAN_DISAPPROVE);

        return canApprove || canAck || canDisapprove;
    }
    
    
    public boolean isGrantsGovEnabled() {
        return getProposalDevelopmentService().isGrantsGovEnabledForProposal(getProposalDevelopmentDocument().getDevelopmentProposal());
    }

    /**
     * Gets the grantsGovAuditActivated attribute. 
     * @return Returns the grantsGovAuditActivated.
     */
    public boolean isGrantsGovAuditActivated() {
        return grantsGovAuditActivated;
    }

    /**
     * Sets the grantsGovAuditActivated attribute value.
     * @param grantsGovAuditActivated The grantsGovAuditActivated to set.
     */
    public void setGrantsGovAuditActivated(boolean grantsGovAuditActivated) {
        this.grantsGovAuditActivated = grantsGovAuditActivated;
    }

    public String getResubmissionOption() {
        return resubmissionOption;
    }

    public void setResubmissionOption(String resubmissionOption) {
        this.resubmissionOption = resubmissionOption;
    }

    public String getInstitutionalProposalToVersion() {
        return institutionalProposalToVersion;
    }

    public void setInstitutionalProposalToVersion(String institutionalProposalToVersion) {
        this.institutionalProposalToVersion = institutionalProposalToVersion;
    }

    /**
     * Gets the medusaBean attribute. 
     * @return Returns the medusaBean.
     */
    public MedusaBean getMedusaBean() {
        return medusaBean;
    }

    /**
     * Sets the medusaBean attribute value.
     * @param medusaBean The medusaBean to set.
     */
    public void setMedusaBean(MedusaBean medusaBean) {
        this.medusaBean = medusaBean;
    }    
    

    public ReportHelperBean getReportHelperBean() {
        return reportHelperBean;
    }

    public List<String> getProposalDataOverrideMethodToCalls() {
        return proposalDataOverrideMethodToCalls;
    }

    public void setProposalDataOverrideMethodToCalls(List<String> proposalDataOverrideMethodToCalls) {
        this.proposalDataOverrideMethodToCalls = proposalDataOverrideMethodToCalls;
    }

    /**
     * Sets the proposalToSummarize attribute value.
     * @param proposalToSummarize The proposalToSummarize to set.
     */
    public void setProposalToSummarize(DevelopmentProposal proposalToSummarize) {
        this.proposalToSummarize = proposalToSummarize;
    }

    /**
     * Gets the proposalToSummarize attribute. 
     * @return Returns the proposalToSummarize.
     */
    public DevelopmentProposal getProposalToSummarize() {
        return proposalToSummarize;
    }

    /**
     * Sets the budgetToSummarize attribute value.
     * @param budgetToSummarize The budgetToSummarize to set.
     */
    public void setBudgetToSummarize(Budget budgetToSummarize) {
        this.budgetToSummarize = budgetToSummarize;
    }

    /**
     * Gets the budgetToSummarize attribute. 
     * @return Returns the budgetToSummarize.
     */
    public Budget getBudgetToSummarize() {
        return budgetToSummarize;
    }

    /**
     * Sets the proposalNumberToSummarize attribute value.
     * @param proposalNumberToSummarize The proposalNumberToSummarize to set.
     */
    public void setProposalNumberToSummarize(String proposalNumberToSummarize) {
        this.proposalNumberToSummarize = proposalNumberToSummarize;
    }

    /**
     * Gets the proposalNumberToSummarize attribute. 
     * @return Returns the proposalNumberToSummarize.
     */
    public String getProposalNumberToSummarize() {
        return proposalNumberToSummarize;
    }

    /**
     * Sets the budgetNumberToSummarize attribute value.
     * @param budgetNumberToSummarize The budgetNumberToSummarize to set.
     */
    public void setBudgetNumberToSummarize(String budgetNumberToSummarize) {
        this.budgetNumberToSummarize = budgetNumberToSummarize;
    }

    /**
     * Gets the budgetNumberToSummarize attribute. 
     * @return Returns the budgetNumberToSummarize.
     */
    public String getBudgetNumberToSummarize() {
        return budgetNumberToSummarize;
    }
    
    /**
     * Gets the showSubmissionDetails attribute. 
     * @return Returns the showSubmissionDetails.
     */
    public boolean isShowSubmissionDetails() {
        return showSubmissionDetails;
    }
    
    /**
     * Sets the showSubmissionDetails attribute value.
     * @param showSubmissionDetails The showSubmissionDetails to set.
     */
    public void setShowSubmissionDetails(boolean showSubmissionDetails) {
        this.showSubmissionDetails = showSubmissionDetails;
    }
    
    /**
     * Gets the GrantsGovSubmitFlag attribute. 
     * @return Returns the GrantsGovSubmitFlag.
     */
     public boolean isGrantsGovSubmitFlag() {
         return grantsGovSubmitFlag;
     }

     /**
      * Sets the GrantsGovSubmitFlag attribute value.
      * @param GrantsGovSubmitFlag The GrantsGovSubmitFlag to set.
      */
     public void setGrantsGovSubmitFlag(boolean grantsGovSubmitFlag) {
         this.grantsGovSubmitFlag = grantsGovSubmitFlag;
     }
     /**
      * Gets the SaveXmlPermission attribute. 
      * @return Returns the SaveXmlPermission.
      */
     public boolean isSaveXmlPermission() {
         return saveXmlPermission;
     }
     /**
      * Sets the SaveXmlPermission attribute value.
      * @param SaveXmlPermission The SaveXmlPermission to set.
      */
     public void setSaveXmlPermission(boolean saveXmlPermission) {
         this.saveXmlPermission = saveXmlPermission;
     }
     /**
      * Gets the GrantsGovSelectFlag attribute. 
      * @return Returns the GrantsGovSelectFlag.
      */
     public boolean isGrantsGovSelectFlag() {
         return grantsGovSelectFlag;
     }
     /**
      * Sets the GrantsGovSelectFlag attribute value.
      * @param GrantsGovSelectFlag The GrantsGovSelectFlag to set.
      */
     public void setGrantsGovSelectFlag(boolean grantsGovSelectFlag) {
         this.grantsGovSelectFlag = grantsGovSelectFlag;
     }

    /**
     * 
     * This method is to be used whether user can copy proposal.  The copy tab will work even after PD is submitted.
     * @return
     */
    private boolean isAuthorizedToCreateProposal() {
        ApplicationTask task = new ApplicationTask(TaskName.CREATE_PROPOSAL);       
        return getTaskAuthorizationService().isAuthorized(GlobalVariables.getUserSession().getPrincipalId(), task);
    }

    public boolean isCanCreateProposal() {
        return canCreateProposal;
    }

    public void setCanCreateProposal(boolean canCreateProposal) {
        this.canCreateProposal = canCreateProposal;
    }

    public boolean getViewFundingSource() {
        return viewFundingSource;
    }
    
    public void setViewFundingSource(boolean viewFundingSource) {
        this.viewFundingSource = viewFundingSource;
    }
    
    public ProposalDevelopmentRejectionBean getProposalDevelopmentRejectionBean() {
        return this.proposalDevelopmentRejectionBean;
    }
    
    public void setProposalDevelopmentRejectionBean(ProposalDevelopmentRejectionBean proposalDevelopmentRejectionBean) {
        this.proposalDevelopmentRejectionBean = proposalDevelopmentRejectionBean;
    }
    
    public boolean isShowRejectionConfirmation() {
        return this.showRejectionConfirmation;
    }
    
    public void setShowRejectionConfirmation(boolean showRejectionConfirmation) {
        this.showRejectionConfirmation = showRejectionConfirmation;
    }
    
    public NotificationHelper<ProposalDevelopmentNotificationContext> getNotificationHelper() {
        return notificationHelper;
    }
    
    public void setNotificationHelper(NotificationHelper<ProposalDevelopmentNotificationContext> notificationHelper) {
        this.notificationHelper = notificationHelper;
    }
    
    /**
     * Gets the proposalDevelopmentQuestionnaireHelper attribute. 
     * @return Returns the proposalDevelopmentQuestionnaireHelper.
     */
    public ProposalDevelopmentQuestionnaireHelper getQuestionnaireHelper() {
        return proposalDevelopmentQuestionnaireHelper;
    }

    /**
     * Sets the proposalDevelopmentQuestionnaireHelper attribute value.
     * @param proposalDevelopmentQuestionnaireHelper The proposalDevelopmentQuestionnaireHelper to set.
     */
    public void setQuestionnaireHelper(ProposalDevelopmentQuestionnaireHelper proposalDevelopmentQuestionnaireHelper) {
        this.proposalDevelopmentQuestionnaireHelper = proposalDevelopmentQuestionnaireHelper;
    }
    
    /**
     * Gets the proposalDevelopmentS2sQuestionnaireHelper attribute. 
     * @return Returns the proposalDevelopmentS2sQuestionnaireHelper.
     */
    public ProposalDevelopmentQuestionnaireHelper getS2sQuestionnaireHelper() {
        return proposalDevelopmentS2sQuestionnaireHelper;
    }

    /**
     * Sets the proposalDevelopmentS2sQuestionnaireHelper attribute value.
     * @param proposalDevelopmentS2sQuestionnaireHelper The proposalDevelopmentS2sQuestionnaireHelper to set.
     */
    public void setS2sQuestionnaireHelper(ProposalDevelopmentS2sQuestionnaireHelper proposalDevelopmentS2sQuestionnaireHelper) {
        this.proposalDevelopmentS2sQuestionnaireHelper = proposalDevelopmentS2sQuestionnaireHelper;
    }

    public List<ProposalPersonQuestionnaireHelper> getProposalPersonQuestionnaireHelpers() {
        return this.proposalPersonQuestionnaireHelpers;
    }
    
    public void setProposalPersonQuestionnaireHelpers(List<ProposalPersonQuestionnaireHelper> proposalPersonQuestionnaireHelpers) {
        this.proposalPersonQuestionnaireHelpers = proposalPersonQuestionnaireHelpers;
    }

    public List<AnswerHeader> getAnswerHeadersToDelete() {
        return this.answerHeadersToDelete;
    }

    public String getModuleCode() {
        return CoeusModule.PROPOSAL_DEVELOPMENT_MODULE_CODE;
    }
    
    public List<ProposalPerson> getProposalPersonsToDelete() {
        return this.proposalPersonsToDelete;
    }
    
    public void setPropsoalPersonsToDelete(List<ProposalPerson> proposalPersonsToDelete) {
        this.proposalPersonsToDelete = proposalPersonsToDelete;
    }

    public void setS2sOpportunity(S2sOpportunity s2sOpportunity) {
        this.s2sOpportunity = s2sOpportunity;
    }

    public S2sOpportunity getS2sOpportunity() {
        return s2sOpportunity;
    }

    @Override
    public String[] getQuestionnaireFieldStarters() {
        String[] starters = {"proposalPersonQuestionnaireHelpers[", "s2sQuestionnaireHelper", "questionnaireHelper"};
        return starters;
    }

    @Override
    public String[] getQuestionnaireFieldMiddles() {
        String[] middles = {DEFAULT_MIDDLE, DEFAULT_MIDDLE, DEFAULT_MIDDLE};
        return middles;
    }

    @Override
    public String[] getQuestionnaireFieldEnds() {
        String[] ends = {DEFAULT_END, DEFAULT_END, DEFAULT_END};
        return ends;
    }

    public void setNewBudgetChangedData(BudgetChangedData newBudgetChangedData) {
        this.newBudgetChangedData = newBudgetChangedData;
    }

    public BudgetChangedData getNewBudgetChangedData() {
        return newBudgetChangedData;
    }
    
    /**
     * Gets the selectedBudgetPrint attribute. 
     * @return Returns the selectedBudgetPrint.
     */
    public String[] getSelectedBudgetPrint() {
        return selectedBudgetPrint;
    }

    /**
     * Sets the selectedBudgetPrint attribute value.
     * @param selectedBudgetPrint The selectedBudgetPrint to set.
     */
    public void setSelectedBudgetPrint(String[] selectedBudgetPrint) {
        this.selectedBudgetPrint = selectedBudgetPrint;
    }

    public void setBudgetDataOverrideMethodToCalls(List<String> budgetDataOverrideMethodToCalls) {
        BudgetDataOverrideMethodToCalls = budgetDataOverrideMethodToCalls;
    }

    public List<String> getBudgetDataOverrideMethodToCalls() {
        return BudgetDataOverrideMethodToCalls;
    }

    

    private void setApproverViewTabTitle()
    {
        HeaderNavigation[] headerNavigationTabs = super.getHeaderNavigationTabs();
 
        for(HeaderNavigation headerNavigation: headerNavigationTabs)
        {
            if ( headerNavigation.getHeaderTabNavigateTo().equalsIgnoreCase(Constants.MAPPING_PROPOSAL_APPROVER_VIEW_PAGE) )
            {
                String approverViewTabTitle = this.getParameterService().getParameterValueAsString(
                        ProposalDevelopmentDocument.class, Constants.PARAMETER_PROPOSAL_APPROVER_VIEW_TITLE);
                 if (StringUtils.isEmpty(approverViewTabTitle))
                 {
                     approverViewTabTitle = "Proposal Summary";
                 }

                headerNavigation.setHeaderTabDisplayName(approverViewTabTitle);
                break;
            }
        }

    }

    public String getCurrentPersonCountryCode() {
        return currentPersonCountryCode;
    }

    public void setCurrentPersonCountryCode(String pCurrentPersonCountryCode) {
        this.currentPersonCountryCode = pCurrentPersonCountryCode;
    }
    
    public String getValueFinderResultDoNotCache(){
        if (this.getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) this.getActionFormUtilMap()).setCacheValueFinderResults(false);
        }
        return "";
    }
    
    public String getValueFinderResultCache(){
        if (this.getActionFormUtilMap() instanceof ActionFormUtilMap) {
            ((ActionFormUtilMap) this.getActionFormUtilMap()).setCacheValueFinderResults(true);
        }
        return "";
    }
    
    public Collection<NarrativeType> getAllNarrativeTypes() {
        return getBusinessObjectService().findAll(NarrativeType.class);
    }
    
    public boolean isHidePropDevDocDescriptionPanel() {
        return getProposalDevelopmentDocument().isDefaultDocumentDescription();
    }

    public ProposalDevelopmentCustomDataHelper getCustomDataHelper() {
        return customDataHelper;
    }

    public void setCustomDataHelper(ProposalDevelopmentCustomDataHelper customDataHelper) {
        this.customDataHelper = customDataHelper;
    }

    public HierarchyProposalSummary getProposalSummary() {
        return proposalSummary;
    }

    public void setProposalSummary(HierarchyProposalSummary proposalSummary) {
        this.proposalSummary = proposalSummary;
    }
    
    protected PermissionService getKimPermissionService() {
        return KcServiceLocator.getService("kimPermissionService");
    }

    public String getNarrativeStatusesChangeKey() {
        return narrativeStatusesChangeKey;
    }

    public void setNarrativeStatusesChangeKey(String narrativeStatusesChangeKey) {
        this.narrativeStatusesChangeKey = narrativeStatusesChangeKey;
    }

    public void clearNarrativeStatusChangeKey() {
        narrativeStatusesChangeKey = "";
        narrativeStatusesChange = null;
    }

    public void setNarrativeStatusChange(NarrativeStatus narrativeStatus) {
        this.narrativeStatusesChange = narrativeStatus;
    }

    public NarrativeStatus getNarrativeStatusChange() {
        return narrativeStatusesChange;
    }

    /**
     * Gets the newS2sUserAttachedForm attribute. 
     * @return Returns the newS2sUserAttachedForm.
     */
    public S2sUserAttachedForm getNewS2sUserAttachedForm() {
        return newS2sUserAttachedForm;
    }

    /**
     * Sets the newS2sUserAttachedForm attribute value.
     * @param newS2sUserAttachedForm The newS2sUserAttachedForm to set.
     */
    public void setNewS2sUserAttachedForm(S2sUserAttachedForm newS2sUserAttachedForm) {
        this.newS2sUserAttachedForm = newS2sUserAttachedForm;
    }

}
