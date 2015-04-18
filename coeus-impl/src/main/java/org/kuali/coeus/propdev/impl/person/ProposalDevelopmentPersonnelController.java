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
package org.kuali.coeus.propdev.impl.person;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.notification.impl.bo.KcNotification;
import org.kuali.coeus.common.notification.impl.bo.NotificationTypeRecipient;
import org.kuali.coeus.common.view.wizard.framework.WizardControllerService;
import org.kuali.coeus.common.view.wizard.framework.WizardResultsDto;
import org.kuali.coeus.propdev.impl.core.*;
import org.kuali.coeus.common.questionnaire.framework.answer.Answer;
import org.kuali.coeus.common.questionnaire.framework.answer.AnswerHeader;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentViewHelperServiceImpl;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationContext;
import org.kuali.coeus.propdev.impl.notification.ProposalDevelopmentNotificationRenderer;
import org.kuali.coeus.propdev.impl.person.attachment.ProposalPersonBiography;
import org.kuali.coeus.common.framework.person.PersonTypeConstants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.form.DocumentFormBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@Controller
public class ProposalDevelopmentPersonnelController extends ProposalDevelopmentControllerBase {

    @Autowired
    @Qualifier("wizardControllerService")
    private WizardControllerService wizardControllerService;

    @Autowired
    @Qualifier("keyPersonnelService")
	private KeyPersonnelService keyPersonnelService;

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-PersonnelPage"})
    public ModelAndView navigateToPersonnel(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        for (ProposalPerson person : form.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
            //workaround for the document associated with the OJB retrived dev prop not having a workflow doc.
            person.setDevelopmentProposal(form.getProposalDevelopmentDocument().getDevelopmentProposal());
            person.getQuestionnaireHelper().populateAnswers();
        }
        return super.navigate(form, result, request, response);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=prepareAddPersonDialog"})
    public ModelAndView prepareAddPersonDialog(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) {
        form.getAddKeyPersonHelper().setLineType(PersonTypeConstants.EMPLOYEE.getCode());
        return getModelAndViewService().showDialog("PropDev-PersonnelPage-Wizard",true,form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigateToPersonError"})
    public ModelAndView navigateToPersonError(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        form.setAjaxReturnType("update-page");
    	return navigateToPersonnel(form, result, request, response);
    } 
    
    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=save", "pageId=PropDev-PersonnelPage"})
    public ModelAndView save(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result, 
    		HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
    	ModelAndView mv = super.save(form);
    	return mv;
    }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=performPersonnelSearch")
   public ModelAndView performPersonnelSearch(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       form.getAddKeyPersonHelper().getResults().clear();
       List<Object> results = new ArrayList<Object>();

       for (Object object : getWizardControllerService().performWizardSearch(form.getAddKeyPersonHelper().getLookupFieldValues(),form.getAddKeyPersonHelper().getLineType())) {
           WizardResultsDto wizardResult = (WizardResultsDto) object;
           String personId = wizardResult.getKcPerson() != null ? wizardResult.getKcPerson().getPersonId() : wizardResult.getRolodex().getRolodexId().toString();
           if (!personAlreadyExists(personId,form.getDevelopmentProposal().getProposalPersons())) {
               results.add(object);
           }
       }

       form.getAddKeyPersonHelper().setResults(results);
       return getRefreshControllerService().refresh(form);
   }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params="methodToCall=addPerson")
   public ModelAndView addPerson(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       ProposalPerson newProposalPerson = new ProposalPerson();
       for (Object object : form.getAddKeyPersonHelper().getResults()) {
           WizardResultsDto wizardResult = (WizardResultsDto) object;
           if (wizardResult.isSelected() == true) {
               if (wizardResult.getKcPerson() != null) {
                   newProposalPerson.setPersonId(wizardResult.getKcPerson().getPersonId());
                   newProposalPerson.setFullName(wizardResult.getKcPerson().getFullName());
                   newProposalPerson.setUserName(wizardResult.getKcPerson().getUserName());
               } else if (wizardResult.getRolodex() != null) {
                   newProposalPerson.setRolodexId(wizardResult.getRolodex().getRolodexId());
                   newProposalPerson.setFullName(wizardResult.getRolodex().getFullName());

               }
               break;
           }
       }

       newProposalPerson.setProposalPersonRoleId((String)form.getAddKeyPersonHelper().getParameter("personRole"));
       if (form.getAddKeyPersonHelper().getParameterMap().containsKey("keyPersonProjectRole")) {
        newProposalPerson.setProjectRole((String)form.getAddKeyPersonHelper().getParameter("keyPersonProjectRole"));
       }
       getKeyPersonnelService().addProposalPerson(newProposalPerson, form.getProposalDevelopmentDocument());
       form.getAddKeyPersonHelper().reset();
       form.setAjaxReturnType(UifConstants.AjaxReturnTypes.UPDATEPAGE.getKey());
       return super.save(form);
   }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=navigate", "actionParameters[navigateToPageId]=PropDev-CreditAllocationPage"})
    public ModelAndView navigateToCreditAllocation(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form, BindingResult result,
                                  HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = super.navigate(form,result,request,response);
        ((ProposalDevelopmentViewHelperServiceImpl) form.getViewHelperService()).populateCreditSplits(form);
        return mv;
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=deleteLine", "pageId=PropDev-PersonnelPage"})
    public ModelAndView deletePerson(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
        @RequestParam("actionParameters[" + UifParameters.SELECTED_COLLECTION_PATH + "]") String selectedCollectionPath,
        @RequestParam("actionParameters[" + UifParameters.SELECTED_LINE_INDEX + "]") String selectedLine) throws Exception {
        if (selectedCollectionPath.equals("document.developmentProposal.proposalPersons")) {
            Collection<Object> collection = ObjectPropertyUtils.getPropertyValue(form, selectedCollectionPath);
            Object deleteLine = ((List<Object>) collection).get(Integer.parseInt(selectedLine));String personId = ((ProposalPerson)deleteLine).getPersonId();

            List<ProposalPersonBiography> tmpBios= new ArrayList<ProposalPersonBiography>();
            for (ProposalPersonBiography biography : form.getDevelopmentProposal().getPropPersonBios()) {
                if (!biography.getPersonId().equals(personId)) {
                    tmpBios.add(biography);
                }
            }
            form.getDevelopmentProposal().setPropPersonBios(tmpBios);
        }

        return getCollectionControllerService().deleteLine(form);
    }

   @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=clearAnswers")
   public ModelAndView clearAnswers(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
	   ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
	   String personNumber = pdForm.getActionParamaterValue("personNumber");
	   for (ProposalPerson person : pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
		   if (StringUtils.equals(personNumber, person.getProposalPersonNumber().toString())) {
			   //get the certification questions
			   AnswerHeader ah = person.getQuestionnaireHelper().getAnswerHeaders().get(0);
			   for (Answer answer : ah.getAnswers()) {
				   answer.setAnswer(null);
			   }
		   }
	   }
	   saveAnswerHeaders(pdForm,request.getParameter(UifParameters.PAGE_ID));
	   ModelAndView mv = this.save(pdForm, result, request, response);
	   return mv;
   }
    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=certificationToggle")
    public ModelAndView certificationToggle(@ModelAttribute("KualiForm") DocumentFormBase form, BindingResult result,
                                            HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProposalDevelopmentDocumentForm pdForm = (ProposalDevelopmentDocumentForm) form;
        String personNumber = pdForm.getActionParamaterValue("personNumber");
        for (ProposalPerson person : pdForm.getProposalDevelopmentDocument().getDevelopmentProposal().getProposalPersons()) {
            if (StringUtils.equals(personNumber, person.getProposalPersonNumber().toString())) {

                person.setOptInCertificationStatus(!person.getOptInCertificationStatus());
                if (!person.getOptInCertificationStatus()){
                    return clearAnswers(form, result, request, response);
                }
            }
        }
        return getRefreshControllerService().refresh(form);
    }


    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=viewCertification")
    public ModelAndView viewCertification(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                       @RequestParam("actionParameters[" + UifParameters.SELECTED_LINE_INDEX + "]") String selectedLine) throws Exception {
    ProposalPerson person = form.getDevelopmentProposal().getProposalPerson(Integer.parseInt(selectedLine));
    person.getQuestionnaireHelper().populateAnswers();
    form.setProposalPersonQuestionnaireHelper(person.getQuestionnaireHelper());
    return getModelAndViewService().showDialog("PropDev-SubmitPage-CertificationDetail",true,form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=sendCertificationNotification")
    public ModelAndView sendCertificationNotification(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                       @RequestParam("actionParameters[" + UifParameters.SELECTED_LINE_INDEX + "]") String selectedLine) throws Exception {

        sendPersonNotification(form, String.valueOf(selectedLine));
        return super.save(form);
    }


    public void sendPersonNotification(ProposalDevelopmentDocumentForm form, String selectedLine) throws Exception {
        ProposalPerson person = form.getDevelopmentProposal().getProposalPerson(Integer.parseInt(selectedLine));

        ProposalDevelopmentNotificationContext context =
                new ProposalDevelopmentNotificationContext(form.getDevelopmentProposal(), "104", "Certify Notification");
        ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setDevelopmentProposal(form.getDevelopmentProposal());
        ((ProposalDevelopmentNotificationRenderer) context.getRenderer()).setProposalPerson(person);
        KcNotification notification = getKcNotificationService().createNotificationObject(context);
        NotificationTypeRecipient recipient = new NotificationTypeRecipient();
        recipient.setPersonId(person.getPersonId());
        getKcNotificationService().sendNotification(context,notification,Collections.singletonList(recipient));
        getGlobalVariableService().getMessageMap().putInfoForSectionId("PropDev-PersonnelPage-Collection", KeyConstants.INFO_NOTIFICATIONS_SENT, person.getFullName());
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=sendAllCertificationNotifications")
    public ModelAndView sendAllCertificationNotifications(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception {
        int index = 0;
        for (ProposalPerson proposalPerson : form.getDevelopmentProposal().getProposalPersons()) {
            boolean certificationComplete = true;
            for (AnswerHeader answerHeader : proposalPerson.getQuestionnaireHelper().getAnswerHeaders()) {
                certificationComplete &= answerHeader.isCompleted();
            }
            if (!certificationComplete) {
                sendPersonNotification(form, String.valueOf(index));
            }

            index++;
        }
        return super.save(form);
    }



    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=movePersonUp"})
    public ModelAndView movePersonUp(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                     @RequestParam("actionParameters[" + UifParameters.SELECTED_LINE_INDEX + "]") String selectedLine) throws Exception {

        swapAdjacentPersonnel(form.getDevelopmentProposal().getProposalPersons(), Integer.parseInt(selectedLine), MoveOperationEnum.MOVING_PERSON_UP);

        return getRefreshControllerService().refresh(form);
    }

    @Transactional @RequestMapping(value = "/proposalDevelopment", params={"methodToCall=movePersonDown"})
    public ModelAndView movePersonDown(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form,
                                     @RequestParam("actionParameters[" + UifParameters.SELECTED_LINE_INDEX + "]") String selectedLine) throws Exception {

        swapAdjacentPersonnel(form.getDevelopmentProposal().getProposalPersons(), Integer.parseInt(selectedLine), MoveOperationEnum.MOVING_PERSON_DOWN);

        return getRefreshControllerService().refresh(form);
    }

    @MethodAccessible @Transactional @RequestMapping(value = "/proposalDevelopment", params = "methodToCall=certifyAnswers")
    public ModelAndView certifyAnswers(@ModelAttribute("KualiForm") ProposalDevelopmentDocumentForm form) throws Exception{
        String selectedPersonId = form.getProposalPersonQuestionnaireHelper().getProposalPerson().getPersonId();
        for (ProposalPerson proposalPerson : form.getDevelopmentProposal().getProposalPersons()) {
            if (StringUtils.equals(proposalPerson.getPersonId(),selectedPersonId)) {
                proposalPerson.setQuestionnaireHelper(form.getProposalPersonQuestionnaireHelper());
            }
        }
        return super.save(form);
    }

    private enum MoveOperationEnum {
        MOVING_PERSON_DOWN (1),
        MOVING_PERSON_UP(-1);

        private int offset;

        private MoveOperationEnum(int offset) {
            this.offset = offset;
        }

        public int getOffset() { return offset; }
    };

    private void swapAdjacentPersonnel(List<ProposalPerson> keyPersonnel, int index1, MoveOperationEnum op) {
        ProposalPerson movingPerson = keyPersonnel.get(index1);

        if ((op == MoveOperationEnum.MOVING_PERSON_DOWN && movingPerson.isMoveDownAllowed()) || (op == MoveOperationEnum.MOVING_PERSON_UP && movingPerson.isMoveUpAllowed())) {
            int index2 = index1 + op.getOffset();
            keyPersonnel.set(index1, keyPersonnel.get(index2));
            keyPersonnel.set(index2, movingPerson);
        }
    }

    protected boolean personAlreadyExists(String personId, List<ProposalPerson> existingPersons) {
        for (ProposalPerson existingPerson: existingPersons ) {
            if (personId.equals(existingPerson.getPersonId())) {
                return true;
            }
        }
        return false;
    }

    protected KeyPersonnelService getKeyPersonnelService() {
        return keyPersonnelService;
    }

    public void setKeyPersonnelService(KeyPersonnelService keyPersonnelService) {
        this.keyPersonnelService = keyPersonnelService;
    }

    public WizardControllerService getWizardControllerService() {
        return wizardControllerService;
    }

    public void setWizardControllerService(WizardControllerService wizardControllerService) {
        this.wizardControllerService = wizardControllerService;
    }
}
