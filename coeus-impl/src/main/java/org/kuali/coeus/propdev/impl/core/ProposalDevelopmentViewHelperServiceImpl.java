/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.core;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.coeus.common.framework.auth.KcAuthConstants;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionExplanation;
import org.apache.log4j.Logger;
import org.kuali.coeus.propdev.impl.attachment.ProposalDevelopmentAttachmentHelper;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalDevelopmentPermissionsService;
import org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationItem;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationRenderer;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.S2sRevisionTypeConstants;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sQuestionnaireHelper;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.coeus.sys.framework.workflow.KcWorkflowService;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.protocol.actions.ProtocolStatusBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.location.AddProposalCongressionalDistrictEvent;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.coeus.propdev.impl.docperm.ProposalUserRoles;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.LookupService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.Header;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycle;
import org.kuali.rice.krad.uif.service.impl.ViewHelperServiceImpl;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.view.View;
import org.kuali.rice.krad.uif.view.ViewIndex;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.rice.krms.framework.type.ValidationActionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("proposalDevelopmentViewHelperService")
@Scope("prototype")
public class ProposalDevelopmentViewHelperServiceImpl extends ViewHelperServiceImpl {

    private static final long serialVersionUID = -5122498699317873886L;
    private static final Logger LOG = Logger.getLogger(ProposalDevelopmentViewHelperServiceImpl.class);
    private static final String PARENT_PROPOSAL_TYPE_CODE = "PRDV";
    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    @Autowired
    @Qualifier("legacyNarrativeService")
    private LegacyNarrativeService narrativeService;

    @Autowired
    @Qualifier("lookupService")
    private LookupService lookupService;

    @Autowired
    @Qualifier("noteService")
    private NoteService noteService;

    @Autowired
	@Qualifier("parameterService")
	private ParameterService parameterService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("personService")
    private PersonService personService;

    @Autowired
    @Qualifier("keyPersonnelService")
    private KeyPersonnelService keyPersonnelService;

    @Autowired
    @Qualifier("krmsRulesExecutionService")
    private KrmsRulesExecutionService krmsRulesExecutionService;

    @Autowired
    @Qualifier("auditHelper")
    private AuditHelper auditHelper;

    @Autowired
    @Qualifier("kualiRuleService")
    private KualiRuleService kualiRuleService;

    @Autowired
    @Qualifier("proposalDevelopmentPermissionsService")
    private ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService;

    @Autowired
    @Qualifier("kcWorkflowService")
    private KcWorkflowService kcWorkflowService;

    @Autowired
    @Qualifier("proposalDevelopmentService")
    private ProposalDevelopmentService proposalDevelopmentService;
    private String protocolStatusCode;

    @Autowired
    @Qualifier("customAttributeService")
    private CustomAttributeService customAttributeService;

    @Autowired
    @Qualifier("proposalHierarchyService")
    private ProposalHierarchyService proposalHierarchyService;

    @Override
    public void processBeforeAddLine(ViewModel model, Object addLine, String collectionId, final String collectionPath) {
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        DevelopmentProposal proposal = document.getDevelopmentProposal();
        if (addLine instanceof Narrative) {
            Narrative narrative = (Narrative) addLine;
            getNarrativeService().prepareNarrative(document, narrative);
            if (StringUtils.equals(collectionPath,"document.developmentProposal.instituteAttachments")) {
                narrative.setModuleStatusCode(Constants.NARRATIVE_MODULE_STATUS_COMPLETE);
            }
        } else if (addLine instanceof ProposalPersonBiography) {
            ProposalPersonBiography biography = (ProposalPersonBiography) addLine;
            biography.setDevelopmentProposal(document.getDevelopmentProposal());
            biography.setBiographyNumber(document
                    .getDocumentNextValue(Constants.PROP_PERSON_BIO_NUMBER));
        } else if (addLine instanceof ProposalPersonDegree) {
			((ProposalPersonDegree)addLine).setDegreeSequenceNumber(document.getDocumentNextValue(Constants.PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER));
            try {
            ((ProposalPersonDegree)addLine).setProposalPerson((ProposalPerson)PropertyUtils.getNestedProperty(form.getDevelopmentProposal(),StringUtils.replace(collectionPath,".proposalPersonDegrees","")));
            } catch (Exception e) {
                throw new RuntimeException("proposal person cannot be retrieved from development proposal",e);
            }
        } else if (addLine instanceof ProposalPersonUnit) {
            try {
                ((ProposalPersonUnit)addLine).setProposalPerson((ProposalPerson)PropertyUtils.getNestedProperty(form.getDevelopmentProposal(),StringUtils.replace(collectionPath,".units","")));
            } catch (Exception e) {
                throw new RuntimeException("proposal person cannot be retrieved from development proposal",e);
            }
        } else if (addLine instanceof ProposalAbstract) {
            ProposalAbstract proposalAbstract = (ProposalAbstract) addLine;
            proposalAbstract.setProposalNumber(proposal.getProposalNumber());
            proposalAbstract.refreshReferenceObject("abstractType");
        } else if (addLine instanceof ProposalSpecialReview) {
        	ProposalSpecialReview proposalSpecialReview = (ProposalSpecialReview) addLine;
        	proposalSpecialReview.setDevelopmentProposal(document.getDevelopmentProposal());
        } else if (addLine instanceof ProposalSite) {
            ProposalSite newProposalSite = (ProposalSite) addLine;
            if (newProposalSite.getOrganizationId() != null){
                ((ProposalSite) addLine).setLocationTypeCode(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION);
            }
            else if (newProposalSite.getRolodexId() != null){
                ((ProposalSite) addLine).setLocationTypeCode(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE);
            }
       	 	((ProposalSite) addLine).setDevelopmentProposal(document.getDevelopmentProposal());
        } else if (addLine instanceof CongressionalDistrict) {
       	 	CongressionalDistrict congressionalDistrict =(CongressionalDistrict) addLine;
       	 	((CongressionalDistrict) addLine).setCongressionalDistrict(congressionalDistrict.getNewState(), congressionalDistrict.getNewDistrictNumber());
        } else if (addLine instanceof Note) {
            Note note = (Note) addLine;
            note.setRemoteObjectIdentifier(document.getNoteTarget().getObjectId());
            note.setAuthorUniversalIdentifier(globalVariableService.getUserSession().getPrincipalId());
            note.setNotePostedTimestampToCurrent();
            note.setNoteTypeCode("BO");
        }

        if (addLine instanceof KcPersistableBusinessObjectBase) {
            ((KcPersistableBusinessObjectBase) addLine).setUpdateTimestamp(getDateTimeService().getCurrentTimestamp());
            ((KcPersistableBusinessObjectBase) addLine).setUpdateUser(getGlobalVariableService().getUserSession().getPrincipalName());
        }
    }

    @Override
    public void processAfterAddLine(ViewModel model, Object lineObject, String collectionId, String collectionPath,
                                    boolean isValidLine) {
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        if (lineObject instanceof Note) {
            getNoteService().save((Note)lineObject);
        }
        else if (lineObject instanceof ProposalUserRoles){
            getProposalDevelopmentPermissionsService().processAddPermission(document,(ProposalUserRoles)lineObject);
        }
        else if (lineObject instanceof ProposalSite) {
            // Some collections of ProposalSites do not represent the full collection by ref so their special setter
            // must be invoked to maintain the parent collection
            List<ProposalSite> sites = ObjectPropertyUtils.getPropertyValue(model, collectionPath);
            if (sites == null) {
                sites = new ArrayList<ProposalSite>();
            }

            if (!sites.contains(lineObject)) {
                sites.add(0, (ProposalSite)lineObject);
                ObjectPropertyUtils.setPropertyValue(model, collectionPath, sites);
            }
        }
    }

    public void finalizeNavigationLinks(Action action, Object model, String direction) {
     ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) model;
     List<Action> actions = pdForm.getOrderedNavigationActions();
     int indexOfCurrentAction = pdForm.findIndexOfPageId(actions);
     if (StringUtils.equals(direction, ProposalDevelopmentConstants.KradConstants.PREVIOUS_PAGE_ARG)) {
  		 action.setRender(indexOfCurrentAction > 0);
  		 if (indexOfCurrentAction > 0) {
  			 action.getActionParameters().put(UifParameters.NAVIGATE_TO_PAGE_ID, pdForm.getOrderedNavigationActions().get(indexOfCurrentAction-1).getNavigateToPageId());
  		 }
  	 } else if (StringUtils.equals(direction, ProposalDevelopmentConstants.KradConstants.NEXT_PAGE_ARG)) {
  		 action.setRender(indexOfCurrentAction < actions.size());
  		 if (indexOfCurrentAction < actions.size()) {
  			 action.getActionParameters().put(UifParameters.NAVIGATE_TO_PAGE_ID, pdForm.getOrderedNavigationActions().get(indexOfCurrentAction+1).getNavigateToPageId());
  		 }
  	 }
   }    

    public void setInvestigatorCreditTypes(Object model) {
        ((ProposalDevelopmentDocumentForm) model).getDevelopmentProposal().setInvestigatorCreditTypes(getKeyPersonnelService().getInvestigatorCreditTypes());
    }

    @Override
    protected boolean performAddLineValidation(ViewModel viewModel, Object newLine, String collectionId,
            String collectionPath) {
    	boolean isValid = true;
    	isValid = super.performAddLineValidation(viewModel, newLine, collectionId, collectionPath);
    	String collectionLabel = (String) viewModel.getViewPostMetadata().getComponentPostData(collectionId,UifConstants.PostMetadata.COLL_LABEL);
    	ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) viewModel;
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();

        if (newLine instanceof CongressionalDistrict) {
            Collection<CongressionalDistrict> CongressionalDistricts= ObjectPropertyUtils.getPropertyValue(viewModel, collectionPath);
        	isValid = getKualiRuleService().applyRules(
                    new AddProposalCongressionalDistrictEvent(document, (List<CongressionalDistrict>) CongressionalDistricts, (CongressionalDistrict) newLine, collectionId, collectionLabel));
        }
        else if (newLine instanceof ProposalUserRoles){
            ProposalUserRoles newProposalUserRoles = (ProposalUserRoles) newLine;
            isValid = getProposalDevelopmentPermissionsService().validateAddPermissions(document, form.getPermissionsHelper().getWorkingUserRoles(),newProposalUserRoles);
        }
        else if (newLine instanceof Narrative) {
            Narrative narrative = (Narrative) newLine;
            String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
            if (StringUtils.isNotBlank(selectedLine)){
                String collectionPathWithIndex = collectionPath + "[" + selectedLine + "]";
                isValid = validateNarrativeRequiredFields(narrative,collectionPathWithIndex,true);
            }
        }
        else if (newLine instanceof ProposalPersonBiography) {
            ProposalPersonBiography biography = (ProposalPersonBiography) newLine;
            String selectedLine = form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX);
            if (StringUtils.isNotBlank(selectedLine)){
                String collectionPathWithIndex = collectionPath + "[" + selectedLine + "]";
                isValid = validateProposalPersonBiographyRequiredFields(biography,collectionPathWithIndex,true);
            }

        }
        return isValid;
    }

    @Override
    public void processAfterSaveLine(ViewModel model, Object lineObject, String collectionId, String collectionPath) {
           ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) model;
           getDataObjectService().save(lineObject);
           if (lineObject instanceof ProposalPersonBiography) {
               try {
                   ((ProposalPersonBiography)lineObject).init(((ProposalPersonBiography) lineObject).getMultipartFile());
               } catch (Exception e) {
                   LOG.info("No File Attached");
               }
           } else if (lineObject instanceof  Narrative) {
               try {
                   ((Narrative)lineObject).init(((Narrative) lineObject).getMultipartFile());
               } catch (Exception e) {
                   LOG.info("No File Attached");
               }
           }
    }

    @Override
    protected boolean performDeleteLineValidation(ViewModel model, String collectionId, String collectionPath, Object deleteLine) {
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm) model;
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        Collection<Object> collection = ObjectPropertyUtils.getPropertyValue(model, collectionPath);
        int index = ((List<Object>) collection).indexOf(deleteLine);
        boolean isValid = true;
        if (deleteLine instanceof ProposalUserRoles){
            isValid = getProposalDevelopmentPermissionsService().validateDeletePermissions(document, form.getPermissionsHelper().getWorkingUserRoles(),index);
            if (isValid){
                getProposalDevelopmentPermissionsService().processDeletePermission(document,((ProposalUserRoles)deleteLine));
            }
        }

        return isValid;
    }

    @Override
    public void processAfterDeleteLine(ViewModel model, String collectionId, String collectionPath, int lineIndex) {
        ProposalDevelopmentDocumentForm proposalDevelopmentDocumentForm = (ProposalDevelopmentDocumentForm) model;
        if (proposalDevelopmentDocumentForm.getEditableCollectionLines().containsKey(collectionPath)) {
            for (String index : proposalDevelopmentDocumentForm.getEditableCollectionLines().get(collectionPath)) {
                if (index.equals(String.valueOf(lineIndex))) {
                    proposalDevelopmentDocumentForm.getEditableCollectionLines().get(collectionPath).remove(index);
                    break;
                }
            }
        }

        // Special handling for proposal site subset deletions because delete assumes it has a handle to the actual
        // collection its manipulating and in these cases it does not (so item was never actually deleted)
        List collection = ObjectPropertyUtils.getPropertyValue(model, collectionPath);
        if (collection != null && !collection.isEmpty() && collection.size() > lineIndex &&
                collection.get(lineIndex) instanceof ProposalSite) {
            Integer typeCode = ((ProposalSite)(collection.get(lineIndex))).getLocationTypeCode();
            if (typeCode.equals(ProposalSite.PROPOSAL_SITE_OTHER_ORGANIZATION)
                    || typeCode.equals(ProposalSite.PROPOSAL_SITE_PERFORMANCE_SITE)) {
                collection.remove(lineIndex);
                ObjectPropertyUtils.setPropertyValue(model, collectionPath, collection);
            }
        }
    }

    public static class SponsorSuggestResult {
        private Sponsor sponsor;
        public SponsorSuggestResult(Sponsor sponsor) {
            this.sponsor = sponsor;
        }
        public String getValue() {
            return sponsor.getSponsorCode();
        }
        public String getLabel() {
            return "<b>" + sponsor.getSponsorCode() + "</b><br/><i>" + sponsor.getSponsorName() + "</i>";
        }
        public String getSponsorName() {
            return sponsor.getSponsorName();
        }
    }

    public List<SponsorSuggestResult> performSponsorFieldSuggest(String sponsorCode) {
        List<SponsorSuggestResult> result = new ArrayList<SponsorSuggestResult>();
        List<Sponsor> allSponsors = new ArrayList<Sponsor>();
        String searchString = "%" + sponsorCode + "%";
        allSponsors = getDataObjectService().findMatching(Sponsor.class, QueryByCriteria.Builder.fromPredicates(PredicateFactory.or(PredicateFactory.likeIgnoreCase("sponsorCode", searchString),
                PredicateFactory.likeIgnoreCase("acronym", searchString),
                PredicateFactory.likeIgnoreCase("sponsorName", searchString)))).getResults();
        for (Sponsor sponsor : allSponsors) {
            result.add(new SponsorSuggestResult(sponsor));
        }
        return result;
    }

    public boolean isCollectionLineEditable(String selectedCollectionPath, String index, Map<String,List<String>> editableCollectionLines) {
        boolean retVal = false;
        if (editableCollectionLines.containsKey(selectedCollectionPath)) {
            if (editableCollectionLines.get(selectedCollectionPath).contains(index)) {
                retVal = true;
            }
        }
        return retVal;
    }

    public String getDateUploadedFormatted(Timestamp uploadDate) {
        if (uploadDate != null) {
            return getDateTimeService().toDateString(new Date(uploadDate.getTime())) + " " + getDateTimeService().toTimeString(new Time(uploadDate.getTime()));
        }
        return StringUtils.EMPTY;
    }


    public boolean validateProposalPersonBiographyRequiredFields(ProposalPersonBiography biography, String collectionPath, boolean showErrors){
        boolean success = true;
        if (StringUtils.isBlank(biography.getProposalPersonNumberString())){
            if (showErrors) {
                getGlobalVariableService().getMessageMap().putError(collectionPath + ".proposalPersonNumberString", UifConstants.Messages.VALIDATION_MSG_KEY_PREFIX + "required");
            }
            success = false;
        }
        if (StringUtils.isBlank(biography.getDocumentTypeCode())){
            if (showErrors) {
                getGlobalVariableService().getMessageMap().putError(collectionPath + ".documentTypeCode", UifConstants.Messages.VALIDATION_MSG_KEY_PREFIX + "required");
            }
            success = false;
        }
        return success;
    }

    public boolean validateNarrativeRequiredFields(Narrative narrative, String collectionPath, boolean showErrors){
        boolean success = true;
        if (StringUtils.isBlank(narrative.getNarrativeTypeCode())){
            if (showErrors) {
                getGlobalVariableService().getMessageMap().putError(collectionPath + ".narrativeTypeCode", UifConstants.Messages.VALIDATION_MSG_KEY_PREFIX + "required");
            }
            success = false;
        }
        if (StringUtils.isBlank(narrative.getModuleStatusCode())){
            if (showErrors) {
                getGlobalVariableService().getMessageMap().putError(collectionPath + ".moduleStatusCode", UifConstants.Messages.VALIDATION_MSG_KEY_PREFIX + "required");
            }
            success = false;
        }
        return success;
    }

    protected LegacyNarrativeService getNarrativeService() {
        return narrativeService;
    }

    public void setNarrativeService(LegacyNarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }

    protected LookupService getLookupService() {
        return lookupService;
    }

    public void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    public DateTimeService getDateTimeService() {
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    protected ParameterService getParameterService (){
    	return parameterService;
    }

    public boolean isCreditSplitEnabled(){
    	return getKeyPersonnelService().isCreditSplitEnabled();
    }

    public NoteService getNoteService() {
        return noteService;
    }

    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    public KeyPersonnelService getKeyPersonnelService() {
        return keyPersonnelService;
    }

    public void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }

    public String displayProposalProgressCode(WorkflowDocument wd) {
  	  String proposalProgressCode;

  	  if (wd.isSaved())
  	   proposalProgressCode = DocumentStatus.SAVED.name();
  	  else if (wd.isEnroute() || wd.isException())
  	   proposalProgressCode = DocumentStatus.ENROUTE.name();
  	  else if (wd.isApproved())
  	   proposalProgressCode = DocumentStatus.FINAL.name();
  	  else
  	   proposalProgressCode = "";

  	  return proposalProgressCode;
  	 }

    public boolean areActiveQuestionnaires(List<AnswerHeader> answerHeaders) {
        for (AnswerHeader answerHeader : answerHeaders) {
            if (answerHeader.isActive()) {
                return true;
            }
        }
        return false;
    }

    public String getQuestionMoreInfo(Question question) {
        StringBuilder moreInfo = new StringBuilder();
        moreInfo.append("\n" + "Question Id : " + question.getQuestionSeqId() + "\n");
        for (QuestionExplanation explanation : question.getQuestionExplanations()){
            if (StringUtils.isNotEmpty(explanation.getExplanation()) && explanation.getExplanationType().equals(Constants.QUESTION_EXPLANATION)){
                moreInfo.append("Explanation : " + explanation.getExplanation() + "\n");
            } else if (StringUtils.isNotEmpty(explanation.getExplanation()) && explanation.getExplanationType().equals(Constants.QUESTION_POLICY)) {
                moreInfo.append("Policy : " + explanation.getExplanation() + "\n");
            } else if (StringUtils.isNotEmpty(explanation.getExplanation()) && explanation.getExplanationType().equals(Constants.QUESTION_REGULATION)) {
                moreInfo.append("Regulation : " + explanation.getExplanation() + "\n");
            }
        }

        return moreInfo.toString();
    }

    @Override
    public void retrieveEditModesAndActionFlags() {
    	super.retrieveEditModesAndActionFlags();
    	View view = ViewLifecycle.getView();
        UifFormBase model = (UifFormBase) ViewLifecycle.getModel();
    	if(! getKcWorkflowService().isInWorkflow(((DocumentFormBase) model).getDocument())) {
    		view.getActionFlags().put(KcAuthConstants.DocumentActions.DELETE_DOCUMENT, Boolean.TRUE);
    	}
    }

    public KcWorkflowService getKcWorkflowService() {
		return kcWorkflowService;
	}

	public void setKcWorkflowService(KcWorkflowService kcWorkflowService) {
		this.kcWorkflowService = kcWorkflowService;
	}

	public GlobalVariableService getGlobalVariableService() {
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public void populateCreditSplits(ProposalDevelopmentDocumentForm form) {
        getKeyPersonnelService().populateDocument(form.getProposalDevelopmentDocument());
        form.setCreditSplitListItems(getKeyPersonnelService().createCreditSplitListItems(form.getDevelopmentProposal().getInvestigators()));
    }

    public void populateQuestionnaires(ProposalDevelopmentDocumentForm form) {
        form.setQuestionnaireHelper(new ProposalDevelopmentQuestionnaireHelper(form));
        form.setS2sQuestionnaireHelper(new ProposalDevelopmentS2sQuestionnaireHelper(form));

        form.getQuestionnaireHelper().populateAnswers();
        form.getQuestionnaireHelper().updateChildIndicators();

        form.getS2sQuestionnaireHelper().populateAnswers();
        form.getS2sQuestionnaireHelper().updateChildIndicators();
    }

    public List<ProposalDevelopmentDataValidationItem> populateDataValidation(ProposalDevelopmentDocumentForm form, ViewIndex viewIndex) {
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        List<ProposalDevelopmentDataValidationItem> dataValidationItems = new ArrayList<ProposalDevelopmentDataValidationItem>();
        GlobalVariables.getAuditErrorMap().clear();
        getAuditHelper().auditConditionally(form);
        for (Map.Entry<String,AuditCluster> entry : GlobalVariables.getAuditErrorMap().entrySet()) {
            AuditCluster auditCluster = (AuditCluster) entry.getValue();
            List<AuditError> auditErrors = auditCluster.getAuditErrorList();
            for (AuditError auditError : auditErrors) {
            	ProposalDevelopmentDataValidationItem dataValidationItem = new ProposalDevelopmentDataValidationItem();
                String page = StringUtils.substringBefore(auditError.getLink()," ");
                String section = StringUtils.substringAfter(auditError.getLink()," ");
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setErrorKey(auditError.getMessageKey());
                errorMessage.setMessageParameters(auditError.getParams());

                dataValidationItem.setArea(auditCluster.getLabel());
                dataValidationItem.setSection(getComponentLabel(section,viewIndex));
                dataValidationItem.setDescription(KRADUtils.getMessageText(errorMessage, false));
                dataValidationItem.setSeverity(auditCluster.getCategory());
                dataValidationItem.setNavigateToPageId(page);
                if(page != null && page.equals(org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PAGE)) {
                    dataValidationItem.setMetodToCall("navigateToPersonError");
                }
                dataValidationItem.setNavigateToSectionId(section);

                dataValidationItems.add(dataValidationItem);
            }
        }



        List<Map<String,String>> krmsErrors = getKrmsRulesExecutionService().processUnitKcValidations(document.getLeadUnitNumber(),document);
        for (Map<String,String> error: krmsErrors) {
            ProposalDevelopmentDataValidationItem dataValidationItem = new ProposalDevelopmentDataValidationItem();
            dataValidationItem.setArea(error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_AREA_ATTRIBUTE));
            dataValidationItem.setSection(error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_SECTION_ATTRIBUTE));
            dataValidationItem.setDescription(error.get(ValidationActionTypeService.VALIDATIONS_ACTION_MESSAGE_ATTRIBUTE));
            dataValidationItem.setSeverity(error.get(ValidationActionTypeService.VALIDATIONS_ACTION_TYPE_CODE_ATTRIBUTE).equals("E")?"Error":"Warning");
            dataValidationItem.setNavigateToPageId(error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_PAGE_ID_ATTRIBUTE));
            dataValidationItem.setNavigateToSectionId(error.get(KcKrmsConstants.ValidationAction.VALIDATIONS_ACTION_NAVIGATE_TO_SECTION_ID_ATTRIBUTE));
            dataValidationItems.add(dataValidationItem);
        }
        return dataValidationItems;
    }

    public KrmsRulesExecutionService getKrmsRulesExecutionService() {
        return krmsRulesExecutionService;
    }

    public void setKrmsRulesExecutionService(KrmsRulesExecutionService krmsRulesExecutionService) {
        this.krmsRulesExecutionService = krmsRulesExecutionService;
    }

    public AuditHelper getAuditHelper() {
        return auditHelper;
    }

    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
    }

    private String getComponentLabel(String componentId, ViewIndex viewIndex) {
        try {
            Header header = (Header) PropertyUtils.getProperty(viewIndex.getComponentById(componentId), "header");
            return header.getHeaderText();
        } catch (Exception e) {
            LOG.error("SectionID does not have a header property");
        }
        return null;
    }

    public KualiRuleService getKualiRuleService() {
        return kualiRuleService;
    }

    public void setKualiRuleService(KualiRuleService kualiRuleService) {
        this.kualiRuleService = kualiRuleService;
    }

    public ProposalDevelopmentPermissionsService getProposalDevelopmentPermissionsService() {
        return proposalDevelopmentPermissionsService;
    }

    public void setProposalDevelopmentPermissionsService(ProposalDevelopmentPermissionsService proposalDevelopmentPermissionsService) {
        this.proposalDevelopmentPermissionsService = proposalDevelopmentPermissionsService;
    }

    public void setOrdinalPosition(List<ProposalPerson> proposalPersons) {
        int index = 0;
        for (ProposalPerson proposalPerson : proposalPersons) {
            proposalPerson.setOrdinalPosition(index);
            index++;
        }
    }

    public PersonService getPersonService() {
        return personService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public String setErrorCssClass(String severity) {
        if (severity.endsWith(Constants.AUDIT_ERRORS)) {
            return "label-danger";
        } else if (severity.equals(Constants.AUDIT_WARNINGS)) {
            return "label-warning";
        }
        return "label-info";
    }


    public boolean isAttachmentFileEditable(ProposalDevelopmentAttachmentHelper helper, String collectionPath, String index){
        if (helper.getEditableFileLineAttachments().get(collectionPath) != null
                && helper.getEditableFileLineAttachments().get(collectionPath).contains(index)) {
            return true;
        }

        return false;
    }

    public void toggleAttachmentFile(ProposalDevelopmentDocumentForm form, String collectionPath, String index) {
        ProposalDevelopmentAttachmentHelper helper = form.getProposalDevelopmentAttachmentHelper();
        if (!isAttachmentFileEditable(helper, collectionPath, index)){
            if (helper.getEditableFileLineAttachments().get(collectionPath) == null){
                List<String> tempList = new ArrayList<String>();
                helper.getEditableFileLineAttachments().put(collectionPath, tempList);
            }
            helper.getEditableFileLineAttachments().get(collectionPath).add(index);
        }
        else{
            helper.getEditableFileLineAttachments().get(collectionPath).remove(index);
        }
    }

    public String showFileAttachmentName(ProposalDevelopmentAttachmentHelper helper, String attachmentType){
        if (hasAttachment(helper, attachmentType)){
            if (StringUtils.equals(attachmentType,Constants.PROPOSAL_ATTACHMENT_TYPE_NAME)) {
                return helper.getNarrative().getNarrativeAttachment().getName();
            } else if (StringUtils.equals(attachmentType,Constants.PERSONNEL_ATTACHMENT_TYPE_NAME)) {
                return helper.getBiography().getPersonnelAttachment().getName();
            } else if (StringUtils.equals(attachmentType,Constants.INSTITUTIONAL_ATTACHMENT_TYPE_NAME)) {
                return helper.getInstituteAttachment().getNarrativeAttachment().getName();
            }
        }
        return "";
    }

    public boolean hasAttachment(ProposalDevelopmentAttachmentHelper helper, String attachmentType){
        if (StringUtils.equals(attachmentType,Constants.PROPOSAL_ATTACHMENT_TYPE_NAME)) {
            return helper.getNarrative().getNarrativeAttachment() != null;
        } else if (StringUtils.equals(attachmentType,Constants.PERSONNEL_ATTACHMENT_TYPE_NAME)) {
            return helper.getBiography().getPersonnelAttachment() != null;
        } else if (StringUtils.equals(attachmentType,Constants.INSTITUTIONAL_ATTACHMENT_TYPE_NAME)) {
            return helper.getInstituteAttachment().getNarrativeAttachment() != null;
        }
        return false;
    }

    public String replaceLineBreaks(String string) {
        return StringUtils.replace(string,"\n","[br]");
    }


    public void prepareSummaryPage(ProposalDevelopmentDocumentForm form) {
      populateCreditSplits(form);
        populateQuestionnaires(form);
        getDataObjectService().wrap(form.getDevelopmentProposal()).fetchRelationship("deadlineTypeRef");
        ProposalDevelopmentNotificationRenderer renderer = new ProposalDevelopmentNotificationRenderer(form.getDevelopmentProposal());
        ProposalDevelopmentNotificationContext context = new ProposalDevelopmentNotificationContext(form.getDevelopmentProposal(), null, "Ad-Hoc Notification", renderer);

        form.getNotificationHelper().initializeDefaultValues(context);

        if (form.getDevelopmentProposal().isInHierarchy()) {
            form.setHierarchyDevelopmentProposals(getProposalHierarchyService().getHierarchyProposals(form.getDevelopmentProposal()));
        }
    }

    public boolean isChildSynchronized(DevelopmentProposal proposal) {
       return getProposalHierarchyService().isSynchronized(proposal);
    }

    public boolean isPersonFieldEditable(String propertyName){
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm)ViewLifecycle.getModel();
        Boolean returnValue = form.getPersonEditableFields().get(propertyName);
        return returnValue==null?false:returnValue.booleanValue();
    }

    public ProposalDevelopmentService getProposalDevelopmentService() {
		return proposalDevelopmentService;
	}

	public void setProposalDevelopmentService(
			ProposalDevelopmentService proposalDevelopmentService) {
		this.proposalDevelopmentService = proposalDevelopmentService;
	}
	public boolean isSummaryQuestionsPanelEnabled() {
		return "Y".equalsIgnoreCase(getProposalDevelopmentService().getProposalDevelopmentParameters().get(ProposalDevelopmentService.SUMMARY_QUESTIONS_INDICATOR).getValue());

	}


    public boolean isSummaryAttachmentsPanelEnabled() {
    	return "Y".equalsIgnoreCase(getProposalDevelopmentService().getProposalDevelopmentParameters().get(ProposalDevelopmentService.SUMMARY_ATTACHMENTS_INDICATOR).getValue());
    }

	public boolean isSummaryKeywordsPanelEnabled() {
		return "Y".equalsIgnoreCase(getProposalDevelopmentService().getProposalDevelopmentParameters().get(ProposalDevelopmentService.SUMMARY_KEYWORDS_INDICATOR).getValue());

	}
	 public String getProtocolStatusCode() {
			return protocolStatusCode;
	}

	public void setProtocolStatusCode(ProtocolStatusBase protocolStatusCode) {
			this.protocolStatusCode = protocolStatusCode.getProtocolStatusCode();
	}

    public boolean isS2sRevisionOther(S2sOpportunity s2sOpportunity) {
        if (s2sOpportunity != null) {
            return StringUtils.equals(s2sOpportunity.getRevisionCode(), S2sRevisionTypeConstants.OTHER);
        }
        return false;
    }

	public CustomAttributeService getCustomAttributeService() {
        return customAttributeService;
    }

	public void setCustomAttributeService(CustomAttributeService customAttributeService) {
		this.customAttributeService = customAttributeService;
	}
	public boolean isRequired(CustomAttribute attr, List<? extends DocumentCustomData> customDataList){
		return getCustomAttributeService().isRequired(PARENT_PROPOSAL_TYPE_CODE, attr, customDataList);

    }

    public ProposalHierarchyService getProposalHierarchyService() {
        return proposalHierarchyService;
    }

    public void setProposalHierarchyService(ProposalHierarchyService proposalHierarchyService) {
        this.proposalHierarchyService = proposalHierarchyService;
    }

    public boolean displayDirectIndierctCosts() {
        return StringUtils.equals(getParameterService().getParameter(Constants.PARAMETER_MODULE_AWARD, ParameterConstants.DOCUMENT_COMPONENT,"ENABLE_AWD_ANT_OBL_DIRECT_INDIRECT_COST").getValue(),"1");
    }
}
