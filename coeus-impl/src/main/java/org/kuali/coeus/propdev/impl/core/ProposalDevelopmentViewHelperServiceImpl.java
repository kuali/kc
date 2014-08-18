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
import org.kuali.coeus.common.framework.krms.KrmsRulesExecutionService;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionExplanation;
import org.apache.log4j.Logger;
import org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationItem;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sQuestionnaireHelper;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.propdev.impl.attachment.ProposalDevelopmentAttachmentService;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.coeus.propdev.impl.attachment.Narrative;
import org.kuali.coeus.propdev.impl.location.AddProposalCongressionalDistrictEvent;
import org.kuali.coeus.propdev.impl.location.CongressionalDistrict;
import org.kuali.coeus.propdev.impl.location.ProposalSite;
import org.kuali.coeus.propdev.impl.person.ProposalPersonDegree;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.propdev.impl.specialreview.ProposalSpecialReview;
import org.kuali.coeus.propdev.impl.attachment.LegacyNarrativeService;
import org.kuali.rice.core.api.criteria.PredicateFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.LookupService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.element.Header;
import org.kuali.rice.krad.uif.service.impl.ViewHelperServiceImpl;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.view.ViewIndex;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.kuali.rice.krad.util.ErrorMessage;
import org.kuali.rice.krad.util.KRADUtils;
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

    @Autowired
    @Qualifier("dateTimeService")
    private DateTimeService dateTimeService;

    @Autowired
    @Qualifier("legacyNarrativeService")
    private LegacyNarrativeService narrativeService;

    @Autowired
    @Qualifier("proposalDevelopmentAttachmentService")
    private ProposalDevelopmentAttachmentService proposalDevelopmentAttachmentService;

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
    @Qualifier("keyPersonnelService")
    private KeyPersonnelService keyPersonnelService;

    @Autowired
    @Qualifier("krmsRulesExecutionService")
    private KrmsRulesExecutionService krmsRulesExecutionService;

    @Autowired
    @Qualifier("auditHelper")
    private AuditHelper auditHelper;
    
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
        if (lineObject instanceof Note) {
            getNoteService().save((Note)lineObject);
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
        Collection<CongressionalDistrict> CongressionalDistricts= ObjectPropertyUtils.getPropertyValue(viewModel, collectionPath);
        if (newLine instanceof CongressionalDistrict) {        	
        	isValid = KcServiceLocator.getService(KualiRuleService.class).applyRules(
        			new AddProposalCongressionalDistrictEvent(document, (List<CongressionalDistrict>) CongressionalDistricts,(CongressionalDistrict) newLine,collectionId,collectionLabel));
        	
        }
        return isValid;
    }

    @Override
    public void processAfterSaveLine(ViewModel model, Object lineObject, String collectionId, String collectionPath) {
           ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) model;
           getDataObjectService().save(lineObject);
           if (lineObject instanceof ProposalPersonBiography) {
               try {
                   ((ProposalPersonBiography)lineObject).init(((ProposalPersonBiography)lineObject).getMultipartFile());
                   getProposalDevelopmentAttachmentService().standardizeAttachment(pdForm.getDevelopmentProposal(),(ProposalPersonBiography) lineObject);
               } catch (Exception e) {
                   LOG.info("No File Attached");
               }
           } else if (lineObject instanceof  Narrative) {
               try {
                   ((Narrative)lineObject).init(((Narrative)lineObject).getMultipartFile());
                   getProposalDevelopmentAttachmentService().standardizeAttachment(pdForm.getDevelopmentProposal(),(Narrative) lineObject);
               } catch (Exception e) {
                   LOG.info("No File Attached");
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

    public boolean isAttachmentEditable(String selectedCollectionPath, String index, HashMap<String,List<String>> editableAttachments) {
        boolean retVal = false;
        if (editableAttachments.containsKey(selectedCollectionPath)) {
            if (editableAttachments.get(selectedCollectionPath).contains(index)) {
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

    protected LegacyNarrativeService getNarrativeService() {
    	if (narrativeService == null) {
    		narrativeService = KcServiceLocator.getService(LegacyNarrativeService.class);
    	}
        return narrativeService;
    }

    public void setNarrativeService(LegacyNarrativeService narrativeService) {
        this.narrativeService = narrativeService;
    }
    
    protected LookupService getLookupService() {
    	if (lookupService == null) {
    		lookupService = KcServiceLocator.getService(LookupService.class);
    	}
        return lookupService;
    }

    public void setLookupService(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    public DateTimeService getDateTimeService() {
        if (dateTimeService == null) {
            dateTimeService = KcServiceLocator.getService(DateTimeService.class);
        }
        return dateTimeService;
    }

    public void setDateTimeService(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }
    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
    protected ParameterService getParameterService (){
    	if(parameterService==null){
    		parameterService= KcServiceLocator.getService(ParameterService.class);
    	}
    	return parameterService;
    }
    
    public ProposalDevelopmentAttachmentService getProposalDevelopmentAttachmentService() {
        if (proposalDevelopmentAttachmentService == null) {
            proposalDevelopmentAttachmentService = KcServiceLocator.getService(ProposalDevelopmentAttachmentService.class);
        }        return proposalDevelopmentAttachmentService;
    }

    public void setProposalDevelopmentAttachmentService(ProposalDevelopmentAttachmentService proposalDevelopmentAttachmentService) {
        this.proposalDevelopmentAttachmentService = proposalDevelopmentAttachmentService;
    }
    public boolean isCreditSplitEnabled(){
    	return getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, Constants.CREDIT_SPLIT_ENABLED_RULE_NAME);
   	}

    public NoteService getNoteService() {
        if (noteService == null) {
            noteService = KcServiceLocator.getService(NoteService.class);
        }
        return noteService;
    }

    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }

    public KeyPersonnelService getKeyPersonnelService() {
        if (keyPersonnelService == null) {
            keyPersonnelService = KcServiceLocator.getService(KeyPersonnelService.class);
        }
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
        moreInfo.append("\n" + question.getSequenceNumber() + " : " + question.getQuestion() + "\n");
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

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null){
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
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

        form.getQuestionnaireHelper().prepareView();
        form.getQuestionnaireHelper().populateAnswers();

        form.getS2sQuestionnaireHelper().prepareView();
        form.getS2sQuestionnaireHelper().populateAnswers();
    }

    public boolean displayProposalDevelopmentActions(WorkflowDocument wd) {
        boolean success = false;

        if (wd.isSaved() || wd.isInitiated()){
            success = true;
        }

        return success;
    }

    public List<ProposalDevelopmentDataValidationItem> populateDataValidation(ProposalDevelopmentDocumentForm form, ViewIndex viewIndex) {
        ProposalDevelopmentDocument document = form.getProposalDevelopmentDocument();
        List<ProposalDevelopmentDataValidationItem> dataValidationItems = new ArrayList<ProposalDevelopmentDataValidationItem>();
        KNSGlobalVariables.getAuditErrorMap().clear();
        getAuditHelper().auditConditionally(form);
        for (Map.Entry<String,AuditCluster> entry : KNSGlobalVariables.getAuditErrorMap().entrySet()) {
            AuditCluster auditCluster = (AuditCluster) entry.getValue();
            List<AuditError> auditErrors = auditCluster.getAuditErrorList();
            for (AuditError auditError : auditErrors) {
                ProposalDevelopmentDataValidationItem dataValidationItem = new ProposalDevelopmentDataValidationItem();
                String links[] = StringUtils.split(auditError.getLink(),".");
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setErrorKey(auditError.getMessageKey());
                errorMessage.setMessageParameters(auditError.getParams());

                dataValidationItem.setArea(auditCluster.getLabel());
                dataValidationItem.setSection(getComponentLabel(links[1],viewIndex));
                dataValidationItem.setDescription(KRADUtils.getMessageText(errorMessage, false));
                dataValidationItem.setSeverity(auditCluster.getCategory());
                dataValidationItem.setNavigateToPageId(links[0]);
                if(links[0] != null && links[0].equals(org.kuali.kra.infrastructure.Constants.KEY_PERSONNEL_PAGE)) {
                    dataValidationItem.setMetodToCall("navigateToPersonError");
                }
                dataValidationItem.setNavigateToSectionId(links[1]);

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
        if (krmsRulesExecutionService == null){
            krmsRulesExecutionService = KcServiceLocator.getService(KrmsRulesExecutionService.class);
        }
        return krmsRulesExecutionService;
    }

    public void setKrmsRulesExecutionService(KrmsRulesExecutionService krmsRulesExecutionService) {
        this.krmsRulesExecutionService = krmsRulesExecutionService;
    }

    public AuditHelper getAuditHelper() {
        if (auditHelper == null){
            auditHelper = KcServiceLocator.getService(AuditHelper.class);
        }
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

    public void setOrdinalPosition(List<ProposalPerson> proposalPersons) {
        int index = 0;
        for (ProposalPerson proposalPerson : proposalPersons) {
            proposalPerson.setOrdinalPosition(index);
            index++;
        }
    }
}
