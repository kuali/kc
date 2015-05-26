/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.propdev.impl.core;


import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.coeus.common.api.sponsor.hierarchy.SponsorHierarchyService;
import org.kuali.coeus.common.budget.framework.calculator.BudgetCalculationService;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.common.framework.custom.attr.CustomAttribute;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeDocValue;
import org.kuali.coeus.common.framework.custom.attr.CustomAttributeService;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.print.KcAttachmentDataSource;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.SponsorSearchResult;
import org.kuali.coeus.common.framework.sponsor.SponsorSearchService;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.common.questionnaire.framework.question.Question;
import org.kuali.coeus.common.questionnaire.framework.question.QuestionExplanation;
import org.apache.log4j.Logger;
import org.kuali.coeus.propdev.impl.attachment.ProposalDevelopmentAttachmentHelper;
import org.kuali.coeus.propdev.impl.auth.perm.ProposalDevelopmentPermissionsService;
import org.kuali.coeus.propdev.impl.custom.ProposalDevelopmentCustomDataGroupDto;
import org.kuali.coeus.propdev.impl.datavalidation.ProposalDevelopmentDataValidationConstants;
import org.kuali.coeus.propdev.impl.hierarchy.ProposalHierarchyService;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationRenderer;
import org.kuali.coeus.propdev.impl.person.ProposalPerson;
import org.kuali.coeus.propdev.impl.person.ProposalPersonUnit;
import org.kuali.coeus.propdev.impl.s2s.S2sOpportunity;
import org.kuali.coeus.propdev.impl.s2s.S2sRevisionTypeConstants;
import org.kuali.coeus.propdev.impl.person.KeyPersonnelService;
import org.kuali.coeus.propdev.impl.questionnaire.ProposalDevelopmentQuestionnaireHelper;
import org.kuali.coeus.propdev.impl.s2s.question.ProposalDevelopmentS2sQuestionnaireHelper;
import org.kuali.coeus.sys.framework.controller.KcFileService;
import org.kuali.coeus.sys.framework.validation.AuditHelper;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.actions.ProtocolStatusBase;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.WorkflowDocument;
import org.kuali.rice.kew.api.document.DocumentStatus;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.impl.KcViewHelperServiceImpl;
import org.kuali.coeus.propdev.impl.abstrct.ProposalAbstract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.coeus.sys.impl.validation.DataValidationItem;
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
import org.kuali.rice.core.api.datetime.DateTimeService;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.kim.api.identity.PersonService;
import org.kuali.rice.krad.file.FileMeta;
import org.kuali.rice.krad.util.*;
import org.kuali.rice.krad.bo.Note;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.service.LookupService;
import org.kuali.rice.krad.service.NoteService;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.element.Action;
import org.kuali.rice.krad.uif.lifecycle.ViewLifecycle;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.uif.view.ViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("proposalDevelopmentViewHelperService")
@Scope("prototype")
public class ProposalDevelopmentViewHelperServiceImpl extends KcViewHelperServiceImpl implements ProposalDevelopmentViewHelperService {

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
    @Qualifier("personService")
    private PersonService personService;

    @Autowired
    @Qualifier("keyPersonnelService")
    private KeyPersonnelService keyPersonnelService;

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
    @Qualifier("proposalDevelopmentService")
    private ProposalDevelopmentService proposalDevelopmentService;
    private String protocolStatusCode;

    @Autowired
    @Qualifier("customAttributeService")
    private CustomAttributeService customAttributeService;

    @Autowired
    @Qualifier("proposalHierarchyService")
    private ProposalHierarchyService proposalHierarchyService;

    @Autowired
    @Qualifier("sponsorHierarchyService")
    private SponsorHierarchyService sponsorHierarchyService;
    
    @Autowired
    @Qualifier("proposalTypeService")
    private ProposalTypeService proposalTypeService;

    @Autowired
    @Qualifier("sponsorSearchService")
    private SponsorSearchService sponsorSearchService;

    @Autowired
    @Qualifier("budgetCalculationService")
    private BudgetCalculationService budgetCalculationService;

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("kcFileService")
    private KcFileService kcFileService;

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
            updateAttachmentInformation(narrative.getNarrativeAttachment());
        } else if (addLine instanceof ProposalPersonBiography) {
            ProposalPersonBiography biography = (ProposalPersonBiography) addLine;
            biography.setDevelopmentProposal(document.getDevelopmentProposal());
            biography.setBiographyNumber(document
                    .getDocumentNextValue(Constants.PROP_PERSON_BIO_NUMBER));
            updateAttachmentInformation(biography.getPersonnelAttachment());
        } else if (addLine instanceof ProposalPersonDegree) {
			((ProposalPersonDegree)addLine).setDegreeSequenceNumber(document.getDocumentNextValue(Constants.PROPOSAL_PERSON_DEGREE_SEQUENCE_NUMBER));
            try {
            ((ProposalPersonDegree)addLine).setProposalPerson((ProposalPerson)PropertyUtils.getNestedProperty(form.getDevelopmentProposal(),StringUtils.replace(collectionPath,".proposalPersonDegrees","")));
            } catch (Exception e) {
                throw new RuntimeException("proposal person cannot be retrieved from development proposal",e);
            }
        } else if (addLine instanceof ProposalPersonUnit) {
            try {
                ProposalPersonUnit unit = (ProposalPersonUnit)addLine;
                ProposalPerson proposalPerson = (ProposalPerson) PropertyUtils.getNestedProperty(form.getDevelopmentProposal(),StringUtils.replace(collectionPath,".units",""));
                unit.setProposalPerson(proposalPerson);
                unit.getCreditSplits().addAll(getKeyPersonnelService().createCreditSplits(unit));
            } catch (Exception e) {
                throw new RuntimeException("proposal person cannot be retrieved from development proposal",e);
            }
        } else if (addLine instanceof ProposalAbstract) {
            ProposalAbstract proposalAbstract = (ProposalAbstract) addLine;
            proposalAbstract.setProposalNumber(proposal.getProposalNumber());
            proposalAbstract.refreshReferenceObject("abstractType");
            proposalAbstract.setUpdateDisplayFields();
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
            note.setAuthorUniversalIdentifier(getGlobalVariableService().getUserSession().getPrincipalId());
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
            getNoteService().save((Note) lineObject);
        }
        else if (lineObject instanceof ProposalUserRoles){
            String fullName = getKcPersonService().getKcPersonByUserName(((ProposalUserRoles)lineObject).getUsername()).getFullName();
            ((ProposalUserRoles)lineObject).setFullname(fullName);
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
  		 action.setRender(action.isRender() && indexOfCurrentAction < actions.size());
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
            isValid = getProposalDevelopmentPermissionsService().validateAddPermissions(document, form.getWorkingUserRoles(),newProposalUserRoles);
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
        else if (newLine instanceof ProposalPersonUnit) {
            Collection<ProposalPersonUnit> existingUnits = ObjectPropertyUtils.getPropertyValue(viewModel, collectionPath);
            ProposalPersonUnit personUnit= (ProposalPersonUnit) newLine;
            for (ProposalPersonUnit existingUnit : existingUnits) {
                if (existingUnit.getUnitNumber().equals(personUnit.getUnitNumber())) {
                   getGlobalVariableService().getMessageMap().putError(collectionPath, KeyConstants.ERROR_ADD_EXISTING_UNIT, personUnit.getUnitNumber(), personUnit.getProposalPerson().getFullName());
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    @Override
    public void processAfterSaveLine(ViewModel model, Object lineObject, String collectionId, String collectionPath) {
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
            isValid = getProposalDevelopmentPermissionsService().validateDeletePermissions(document, form.getWorkingUserRoles(),index);
            if (isValid){
                getProposalDevelopmentPermissionsService().processDeletePermission(document, ((ProposalUserRoles) deleteLine));
            }
        }

        if (deleteLine instanceof FileMeta) {
            getDataObjectService().delete(deleteLine);
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
        private SponsorSearchResult sponsor;
        public SponsorSuggestResult(SponsorSearchResult sponsor) {
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

    public List<SponsorSuggestResult> performSponsorFieldSuggest(String sponsorSearchStr) {
       if (StringUtils.isBlank(sponsorSearchStr)) {
            return Collections.emptyList();
        }

        final List<SponsorSearchResult> allSponsors = getSponsorSearchService().findSponsors(sponsorSearchStr);
        List<SponsorSuggestResult> result = new ArrayList<>();
        for (SponsorSearchResult sponsor : allSponsors) {
            result.add(new SponsorSuggestResult(sponsor));
        }
        return result;
    }

    public String getSponsorString(Sponsor sponsor){
        if (sponsor != null){
            return sponsor.getSponsorCode() + " - " + sponsor.getSponsorName();
        }
        else{
            return "";
        }
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

    public boolean displayKeywords() {
        return getParameterService().getParameterValueAsBoolean(ProposalDevelopmentDocument.class, Constants.KEYWORD_PANEL_DISPLAY);
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

    public boolean isAnsweredAnswer(Answer answer) {
        if (answer.getAnswerNumber() != 1) {
            return true;
        }
        return answer.isAnswered();
    }

    public boolean isQuestionnaireMandatory(AnswerHeader header) {
        return header.isQuestionnaireMandatory();
    }

    public boolean areAnsweredQuestionnaires(List<AnswerHeader> answerHeaders) {
        for (AnswerHeader answerHeader : answerHeaders) {
            if (answerHeader.isActive()) {
                for (Answer answer : answerHeader.getAnswers()) {
                    if (StringUtils.isNotEmpty(answer.getAnswer())) {
                        return true;
                    }
                }
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

    public void populateCreditSplits(ProposalDevelopmentDocumentForm form) {
        getKeyPersonnelService().populateCreditSplit(form.getProposalDevelopmentDocument());
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

    public List<DataValidationItem> populateDataValidation(ProposalDevelopmentDocumentForm form) {
        if (StringUtils.equalsIgnoreCase(form.getPageId(), ProposalDevelopmentDataValidationConstants.ATTACHMENT_PAGE_ID)) {
            populateAttachmentReferences(form.getDevelopmentProposal());
        }
        getGlobalVariableService().getAuditErrorMap().clear();
        getAuditHelper().auditConditionally(form);
        return populateDataValidation();
    }

    public void populateAttachmentReferences(DevelopmentProposal developmentProposal) {
        for (Narrative narrative : developmentProposal.getNarratives()) {
            getDataObjectService().wrap(narrative).fetchRelationship("narrativeType");
            getDataObjectService().wrap(narrative).fetchRelationship("narrativeStatus");
        }
        for (Narrative narrative : developmentProposal.getInstituteAttachments()){
            getDataObjectService().wrap(narrative).fetchRelationship("narrativeType");
            getDataObjectService().wrap(narrative).fetchRelationship("narrativeStatus");
        }
        for (ProposalPersonBiography biography : developmentProposal.getPropPersonBios()) {
            getDataObjectService().wrap(biography).fetchRelationship("propPerDocType");
        }
    }

    public AuditHelper getAuditHelper() {
        return auditHelper;
    }

    public void setAuditHelper(AuditHelper auditHelper) {
        this.auditHelper = auditHelper;
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
        if (StringUtils.equals(attachmentType, Constants.PROPOSAL_ATTACHMENT_TYPE_NAME)) {
            return helper.getNarrative().getNarrativeAttachment() != null;
        } else if (StringUtils.equals(attachmentType,Constants.PERSONNEL_ATTACHMENT_TYPE_NAME)) {
            return helper.getBiography().getPersonnelAttachment() != null;
        } else if (StringUtils.equals(attachmentType,Constants.INSTITUTIONAL_ATTACHMENT_TYPE_NAME)) {
            return helper.getInstituteAttachment().getNarrativeAttachment() != null;
        }
        return false;
    }

    public String displayFullName(String userName){
        return ObjectUtils.isNull(userName) ? "" : getPersonService().getPersonByPrincipalName(userName).getName();
    }

    public String replaceLineBreaks(String string) {
        return StringUtils.replace(string,"\n","[br]");
    }

    /*
    Personnel which appears in multiple proposals should not allow update of personnel attachments at the child (critical)
    Personnel attachments for personnel who appears only once in proposal hierarchy should be view only at the parent (no update of details nor delete) (critical)
     */
    public boolean renderPersonnelEditForHierarchyProposal(String personId, DevelopmentProposal proposal) {
        return (proposal.isInHierarchy()) ? renderEditForPersonnelAttachment(personId, proposal) : true;
    }

    protected boolean renderEditForPersonnelAttachment(String personId, DevelopmentProposal proposal) {
        if (personId != null) {
            boolean inMultiple = getProposalHierarchyService().personInMultipleProposals(personId, proposal);
            return (proposal.isParent()) ? inMultiple : !inMultiple;
        }
        return true;
    }

    public String getProposalStatusForDisplay(DevelopmentProposal proposal) {
        if (proposal.isChild()) {
            return getProposalHierarchyService().getProposalState(proposal.getHierarchyParentProposalNumber());
        } else {
            return proposal.getProposalState().getDescription();
        }
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

        if (form.getDevelopmentProposal().getFinalBudget() != null || form.getDevelopmentProposal().getLatestBudget() != null) {
            if (form.getDevelopmentProposal().getFinalBudget() != null) {
                form.setSelectedBudget(form.getDevelopmentProposal().getFinalBudget());
            } else {
                form.setSelectedBudget(form.getDevelopmentProposal().getLatestBudget());
            }
            if(form.getSelectedBudget().getBudgetSummaryDetails().isEmpty()) {
               getBudgetCalculationService().populateBudgetSummaryTotals(form.getSelectedBudget());
            }
        }

        for (DevelopmentProposal developmentProposal : form.getHierarchyDevelopmentProposals()) {
                if (developmentProposal.getHierarchySummaryBudget().getBudgetSummaryDetails().isEmpty()){
                    getBudgetCalculationService().populateBudgetSummaryTotals(developmentProposal.getHierarchySummaryBudget());
                }
        }
    }

    public void populateCustomData(ProposalDevelopmentDocumentForm form) {
        for (CustomAttributeDocValue customAttributeDocValue : form.getProposalDevelopmentDocument().getCustomDataList()) {
            boolean groupNamePresent = false;
            for(ProposalDevelopmentCustomDataGroupDto customDataGroupDto : form.getCustomDataGroups()) {
                if (customDataGroupDto.getDescription().equals(customAttributeDocValue.getCustomAttribute().getGroupName())){
                    groupNamePresent = true;
                    break;
                }
            }
            if (!groupNamePresent) {
                ProposalDevelopmentCustomDataGroupDto customDataGroupDto = new ProposalDevelopmentCustomDataGroupDto();
                customDataGroupDto.setDescription(customAttributeDocValue.getCustomAttribute().getGroupName());
                customDataGroupDto.setIdSuffix(customAttributeDocValue.getCustomAttribute().getGroupName().replace(" ","_"));
                form.getCustomDataGroups().add(customDataGroupDto);
            }
        }
    }

    public void updateAttachmentInformation(KcAttachmentDataSource attachment){
        if (attachment != null){
            attachment.setUploadUser(getGlobalVariableService().getUserSession().getPrincipalName());
            attachment.setUploadTimestamp(getDateTimeService().getCurrentTimestamp());
        }
    }

    public boolean isPersonFieldEditable(String propertyName){
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm)ViewLifecycle.getModel();
        Boolean returnValue = form.getPersonEditableFields().get(propertyName);
        return returnValue==null?false:returnValue.booleanValue();
    }

    public boolean requiresResubmissionPrompt(DevelopmentProposal developmentProposal, String resubmissionOption) {
       return ( getProposalTypeService().getContinuationProposalTypeCode().equals(developmentProposal.getProposalTypeCode())
            || getProposalTypeService().getRenewProposalTypeCode().equals(developmentProposal.getProposalTypeCode())
            || getProposalTypeService().getResubmissionProposalTypeCode().equals(developmentProposal.getProposalTypeCode())
            || getProposalTypeService().getRevisionProposalTypeCode().equals(developmentProposal.getProposalTypeCode())
            || getProposalTypeService().getS2SSubmissionChangeCorrectedCode().equals(developmentProposal.getProposalTypeCode())
            || isSubmissionChangeCorrected(developmentProposal))
            && resubmissionOption == null;
    }
    

    
    
    private boolean isSubmissionChangeCorrected(DevelopmentProposal developmentProposal) {
        return developmentProposal.getS2sOpportunity() != null && getProposalTypeService().getS2SSubmissionChangeCorrectedCode().equals(developmentProposal.getS2sOpportunity().getS2sSubmissionTypeCode());
    }
    
    public boolean renderQuestionnaire(ProposalPerson proposalPerson){
        if (proposalPerson.getRole().getCertificationRequired()){
            return true;
        }
        else if (proposalPerson.getOptInCertificationStatus()){
            return true;
        }

        return false;
    }

    public ProposalDevelopmentService getProposalDevelopmentService() {
		return proposalDevelopmentService;
	}

	public void setProposalDevelopmentService(
			ProposalDevelopmentService proposalDevelopmentService) {
		this.proposalDevelopmentService = proposalDevelopmentService;
	}
	public boolean isSummaryQuestionsPanelEnabled() {
		return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentService.SUMMARY_QUESTIONS_INDICATOR);

	}

    public boolean isSummaryAttachmentsPanelEnabled() {
    	return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentService.SUMMARY_ATTACHMENTS_INDICATOR);
    }

	public boolean isSummaryKeywordsPanelEnabled() {

		return displayKeywords() &&
                getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT,ParameterConstants.DOCUMENT_COMPONENT,ProposalDevelopmentService.SUMMARY_KEYWORDS_INDICATOR);
	}

    public boolean isSummaryBudgetPanelEnabled(DevelopmentProposal developmentProposal) {
        return getParameterService().getParameterValueAsBoolean(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, ProposalDevelopmentService.BUDGET_SUMMARY_INDICATOR) &&
                (developmentProposal.getLatestBudget() != null || developmentProposal.getFinalBudget() != null);

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

    public String getDisclaimerText() {
       return getParameterService().getParameterValueAsString(Constants.MODULE_NAMESPACE_PROPOSAL_DEVELOPMENT, ParameterConstants.DOCUMENT_COMPONENT, "propSummaryDisclaimerText");
    }

    public boolean areAllCertificationsComplete(List<ProposalPerson> proposalPersons) {
        for (ProposalPerson person : proposalPersons) {
            for (AnswerHeader answerHeader : person.getQuestionnaireHelper().getAnswerHeaders())  {
                if (!answerHeader.isCompleted()) {
                    return false;
                }
            }
        }
        return true;
    }
    public String getWizardMaxResults() {
        return getParameterService().getParameterValueAsString(KRADConstants.KRAD_NAMESPACE,
                KRADConstants.DetailTypes.LOOKUP_PARM_DETAIL_TYPE,
                KRADConstants.SystemGroupParameterNames.LOOKUP_RESULTS_LIMIT);
    }
    public SponsorHierarchyService getSponsorHierarchyService() {
        return sponsorHierarchyService;
    }

    public void setSponsorHierarchyService(SponsorHierarchyService sponsorHierarchyService) {
        this.sponsorHierarchyService = sponsorHierarchyService;
    }

    public boolean isShowModularBudgetQuestion(String sponsorCode) {
        return getSponsorHierarchyService().isSponsorNihMultiplePi(sponsorCode);
    }

    public boolean syncRequiresEndDateExtension(DevelopmentProposal proposal) {
    	DevelopmentProposal hierarchyProposal = getProposalHierarchyService().getDevelopmentProposal(proposal.getHierarchyParentProposalNumber());
    	return getProposalHierarchyService().needToExtendProjectDate(hierarchyProposal, proposal);
    }
    
    public boolean syncAllRequiresEndDateExtension(DevelopmentProposal hierarchyProposal) {
    	return getProposalHierarchyService().needToExtendProjectDate(hierarchyProposal);
    }   

	public ProposalTypeService getProposalTypeService() {
		return proposalTypeService;
	}

	public void setProposalTypeService(ProposalTypeService proposalTypeService) {
		this.proposalTypeService = proposalTypeService;
	}


    public SponsorSearchService getSponsorSearchService() {
        return sponsorSearchService;
    }

    public void setSponsorSearchService(SponsorSearchService sponsorSearchService) {
        this.sponsorSearchService = sponsorSearchService;
    }
    public BudgetCalculationService getBudgetCalculationService() {
        return budgetCalculationService;
    }

    public void setBudgetCalculationService(BudgetCalculationService budgetCalculationService) {
        this.budgetCalculationService = budgetCalculationService;
    }
    
    public String getDefaultOpenTab() {
        ProposalDevelopmentDocumentForm form = (ProposalDevelopmentDocumentForm)ViewLifecycle.getModel();
        String openTab = form.getDefaultOpenTab();
        form.setDefaultOpenTab("");
        return openTab;
    }

    public String getPropPersonName(String personId) {
        if (StringUtils.isNotEmpty(personId)) {
            Person person= getPersonService().getPerson(personId);

            final String middleName = person.getMiddleName() != null ? person.getMiddleName() + " " : "";

            return (person.getFirstName() + " " + middleName + person.getLastName()).trim();
        }
        return StringUtils.EMPTY;
    }

    public KcPersonService getKcPersonService() {
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }

    public boolean isFederalSponsor(DevelopmentProposal developmentProposal) {
        return getProposalDevelopmentService().isGrantsGovEnabledForProposal(developmentProposal);
    }

    public void clearOpportunity(DevelopmentProposal proposal) {
        getLegacyDataAdapter().delete(proposal.getS2sOpportunity());
        proposal.setS2sOpportunity(null);
        //Reset Opportunity Title and Opportunity ID in the Sponsor & Program Information section
        proposal.setProgramAnnouncementTitle("");
        proposal.setProgramAnnouncementNumber("");
        proposal.setCfdaNumber("");
        proposal.setOpportunityIdForGG("");
    }

    public String getMaxUploadSizeParameter() {
        return String.valueOf(getKcFileService().getMaxUploadSizeParameter());
    }

    public KcFileService getKcFileService() {
        return kcFileService;
    }

    public void setKcFileService(KcFileService kcFileService) {
        this.kcFileService = kcFileService;
    }

}
