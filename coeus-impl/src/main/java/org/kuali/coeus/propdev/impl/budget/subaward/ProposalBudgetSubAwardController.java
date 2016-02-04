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
package org.kuali.coeus.propdev.impl.budget.subaward;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.core.Budget;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetConstants;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetControllerBase;
import org.kuali.coeus.propdev.impl.budget.core.ProposalBudgetForm;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.util.KRADUtils;
import org.kuali.rice.krad.web.service.impl.CollectionControllerServiceImpl.CollectionActionParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("proposalBudgetSubAwardController")
@RequestMapping("/proposalBudget")
public class ProposalBudgetSubAwardController extends ProposalBudgetControllerBase {
	
	private static final Log LOG = LogFactory.getLog(ProposalBudgetSubAwardController.class);

	@Autowired
	@Qualifier("propDevBudgetSubAwardService")
	private PropDevBudgetSubAwardService propDevBudgetSubAwardService;
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	@Autowired
	@Qualifier("kcAttachmentService")
	private KcAttachmentService kcAttachmentService;

	@Transactional @RequestMapping(params={"methodToCall=retrieveEditLineDialog","actionParameters["+UifParameters.SELECTED_COLLECTION_PATH+"]=budget.budgetSubAwards"})
	public ModelAndView showSubawardEditLineDialog(@ModelAttribute("KualiForm") ProposalBudgetForm form) {
		//pre-fetch the period details as this item will be serialized as part of
		BudgetSubAwards budgetSubAward = form.getBudget().getBudgetSubAwards().get(Integer.parseInt(form.getActionParamaterValue(UifParameters.SELECTED_LINE_INDEX)));
		budgetSubAward.getBudgetSubAwardPeriodDetails().iterator();
		budgetSubAward.getBudgetSubAwardFiles().iterator();
		return getCollectionControllerService().retrieveEditLineDialog(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=viewSubAwardPdf")
	public void viewPdf(@RequestParam("subAwardNumber") Integer subAwardNumber, @ModelAttribute("KualiForm") ProposalBudgetForm form, HttpServletResponse response) {
		BudgetSubAwards subAward = getSubAwardByNumber(subAwardNumber, form);
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(subAward.getSubAwardXfdFileData());
            KRADUtils.addAttachmentToResponse(response,inputStream,"application/pdf", subAward.getSubAwardXfdFileName(), subAward.getSubAwardXfdFileData().length);
            response.flushBuffer();
        } catch (Exception e) {
            LOG.error("Error while downloading attachment");
            throw new RuntimeException("IOException occurred while downloading attachment", e);
        }
	}
	
	@Transactional @RequestMapping(params="methodToCall=viewSubAwardXml")
	public void viewXml(@RequestParam("subAwardNumber") Integer subAwardNumber, @ModelAttribute("KualiForm") ProposalBudgetForm form, HttpServletResponse response) {
		BudgetSubAwards subAward = getSubAwardByNumber(subAwardNumber, form);
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(subAward.getSubAwardXmlFileData().getBytes());
            KRADUtils.addAttachmentToResponse(response,inputStream,"text/xml", createXMLFileName(subAward), subAward.getSubAwardXmlFileData().length());
            response.flushBuffer();
        } catch (Exception e) {
            LOG.error("Error while downloading attachment");
            throw new RuntimeException("IOException occurred while downloading attachment", e);
        }
	}
	
	@Transactional @RequestMapping(params="methodToCall=syncFromPdf")
	public ModelAndView syncFromPdf(@RequestParam("subAwardNumber") Integer subAwardNumber, @ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		BudgetSubAwards subAward = getSubAwardByNumber(subAwardNumber, form);
		updateSubAwardBudgetDetails(form.getBudget(), subAward, ProposalBudgetConstants.KradConstants.SUBAWARDS_COLLECTION);
        form.setDialogDataObject(null);
		return getRefreshControllerService().refresh(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=removeAttachment")
	public ModelAndView removeAttachment(@RequestParam("subAwardNumber") Integer subAwardNumber, @ModelAttribute("KualiForm") ProposalBudgetForm form) {
		BudgetSubAwards subAward = getSubAwardByNumber(subAwardNumber, form);
		getPropDevBudgetSubAwardService().removeSubAwardAttachment(subAward);
	    form.setDialogDataObject(subAward);
		return super.save(form);
	}
	
	@Transactional @RequestMapping(params="methodToCall=replaceAttachment")
	public ModelAndView replaceAttachment(@RequestParam("subAwardNumber") Integer subAwardNumber, @ModelAttribute("KualiForm") ProposalBudgetForm form) {
		BudgetSubAwards subAward = getSubAwardByNumber(subAwardNumber, form);
		BudgetSubAwards dialogSubAward = (BudgetSubAwards) form.getDialogDataObject();
		subAward.setNewSubAwardFile(dialogSubAward.getNewSubAwardFile());
        boolean success = getKcBusinessRulesEngine().applyRules(new BudgetSubAwardsEvent(subAward, form.getBudget(), "dialogDataObject"));
        
        if (success && subAward.getNewSubAwardFile() != null) {
            String fileName = subAward.getNewSubAwardFile().getOriginalFilename();
			try {
				byte[] fileData = subAward.getNewSubAwardFile().getBytes();
				success = updateBudgetAttachment(form.getBudget(), subAward, fileName, fileData, "dialogDataObject");
			} catch (Exception e) {
				LOG.warn("Error adding subaward", e);
				success = false;
				globalVariableService.getMessageMap().putError("dialogDataObject." + Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
			} 
        }
        //on success make sure that dialogDataObject is the new, updated version.
        form.setDialogDataObject(subAward);
        return super.save(form);
	}
	
    private String createXMLFileName(BudgetSubAwards subAward) {
        return subAward.getSubAwardXfdFileName().substring(0, subAward.getSubAwardXfdFileName().lastIndexOf(".") + 1) + "xml";
    }
    
    private BudgetSubAwards getSubAwardByNumber(Integer subAwardNumber, ProposalBudgetForm form) {
    	for (BudgetSubAwards subAward : form.getBudget().getBudgetSubAwards()) {
    		if (Objects.equals(subAward.getSubAwardNumber(), subAwardNumber)) {
    			return subAward;
    		}
    	}
    	return null;
    }
    
    @Transactional @RequestMapping(params={"methodToCall=editLine","actionParameters["+UifParameters.SELECTED_COLLECTION_PATH+"]=budget.budgetSubAwards"})
	public ModelAndView editLine(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		getCollectionControllerService().editLine(form);
		return super.save(form);
	}    
	
	@Transactional @RequestMapping(params={"methodToCall=addLine","actionParameters["+UifParameters.SELECTED_COLLECTION_PATH+"]=budget.budgetSubAwards"})
	public ModelAndView addSubAward(@ModelAttribute("KualiForm") ProposalBudgetForm form) throws Exception {
		
		final CollectionActionParameters parameters = new CollectionActionParameters(form, false);
        BindingInfo addLineBindingInfo = (BindingInfo) form.getViewPostMetadata().getComponentPostData(
                parameters.getSelectedCollectionId(), UifConstants.PostMetadata.ADD_LINE_BINDING_INFO);
        BudgetSubAwards newBudgetSubAward = ObjectPropertyUtils.getPropertyValue(form, addLineBindingInfo.getBindingPath());
        newBudgetSubAward.setBudget(form.getBudget());
        newBudgetSubAward.setBudgetId(form.getBudget().getBudgetId());
        newBudgetSubAward.setSubAwardNumber(form.getBudget().getNextValue("subAwardNumber"));
        newBudgetSubAward.setBudgetVersionNumber(form.getBudget().getBudgetVersionNumber());
        newBudgetSubAward.setSubAwardStatusCode(1);
        newBudgetSubAward.getBudgetSubAwardPeriodDetails().clear();
        newBudgetSubAward.setOrganization(getDataObjectService().findUnique(Organization.class, QueryByCriteria.Builder.forAttribute("organizationId", newBudgetSubAward.getOrganizationId()).build()));
        for (BudgetPeriod period : form.getBudget().getBudgetPeriods()) {
            newBudgetSubAward.getBudgetSubAwardPeriodDetails().add(new BudgetSubAwardPeriodDetail(newBudgetSubAward, period));
        }
        boolean success = getKcBusinessRulesEngine().applyRules(new BudgetSubAwardsEvent(newBudgetSubAward, form.getBudget(), addLineBindingInfo.getBindingPath()));
        if (success && newBudgetSubAward.getNewSubAwardFile() != null) {
            String fileName = newBudgetSubAward.getNewSubAwardFile().getOriginalFilename();
			try {
				byte[] fileData = newBudgetSubAward.getNewSubAwardFile().getBytes();
				success = updateBudgetAttachment(form.getBudget(), newBudgetSubAward, fileName, fileData, addLineBindingInfo.getBindingPath());
			} catch (Exception e) {
				LOG.warn("Error adding subaward", e);
				success = false;
				globalVariableService.getMessageMap().putError(addLineBindingInfo.getBindingPath() + "." + Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
			} 
        }
        if (success) {
            form.getBudget().getBudgetSubAwards().add(newBudgetSubAward);
            ObjectPropertyUtils.setPropertyValue(form, addLineBindingInfo.getBindingPath(), new BudgetSubAwards());
            return super.save(form);
        } else {
        	form.setUpdateComponentId(ProposalBudgetConstants.KradConstants.SUBAWARDS_ADD_DIALOG);
        	
        	//replace newline item with only user inputed data
        	BudgetSubAwards newBlankSubAward = new BudgetSubAwards();
        	newBlankSubAward.setOrganizationId(newBudgetSubAward.getOrganizationId());
        	newBlankSubAward.setOrganization(newBudgetSubAward.getOrganization());
        	newBlankSubAward.setComments(newBudgetSubAward.getComments());
        	ObjectPropertyUtils.setPropertyValue(form, addLineBindingInfo.getBindingPath(), newBlankSubAward);
        	
        	return getRefreshControllerService().refresh(form);
        }
	}
	
	@Transactional @RequestMapping(params={"methodToCall=deleteLine", "actionParameters[selectedCollectionPath]=budget.budgetSubAwards"})
	public ModelAndView deleteLine(@RequestParam("actionParameters[lineIndex]") Integer subAwardIndex, @ModelAttribute("KualiForm") ProposalBudgetForm form) {
		final BudgetSubAwards subAwardToDelete = form.getBudget().getBudgetSubAwards().get(subAwardIndex);

		form.getBudget().getBudgetPeriods().stream().forEach(period ->
			period.setBudgetLineItems(period.getBudgetLineItems().stream()
                    .filter(lineItem -> !subAwardToDelete.getSubAwardNumber().equals(lineItem.getSubAwardNumber()))
                    .collect(Collectors.toList())));

		form.setBudget(getDataObjectService().save(form.getBudget()));
		getCollectionControllerService().deleteLine(form);
		return super.save(form);
	}


    protected boolean updateBudgetAttachment(Budget budget, BudgetSubAwards subAward, String fileName, byte[] fileData, String errorPath) throws Exception {
        subAward.setSubAwardXmlFileData(null);
        subAward.setFormName(null);
        subAward.setNamespace(null);
        boolean success = true;
        getPropDevBudgetSubAwardService().populateBudgetSubAwardFiles(budget, subAward, fileName, fileData);
        if (subAward.getNewSubAwardFile().getContentType().equalsIgnoreCase(Constants.PDF_REPORT_CONTENT_TYPE)) {
	        success = updateSubAwardBudgetDetails(budget, subAward, errorPath);
        }
    	if (subAward.getSubAwardXmlFileData() != null && kcAttachmentService.getSpecialCharacter(subAward.getSubAwardXmlFileData())) {
    		globalVariableService.getMessageMap().putWarning(ProposalBudgetConstants.KradConstants.SUBAWARDS_COLLECTION, Constants.SUBAWARD_FILE_SPECIAL_CHARECTOR);
            subAward.getBudgetSubAwardFiles().get(0).setSubAwardXmlFileData(kcAttachmentService.
                    checkAndReplaceSpecialCharacters(subAward.getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData()));
            subAward.setSubAwardXmlFileData(subAward.getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData());
    	}
        return success;
    }

    protected boolean updateSubAwardBudgetDetails(Budget budget, BudgetSubAwards subAward, String errorPath) throws Exception {
        List<String[]> errorMessages = new ArrayList<>();
        boolean success = getPropDevBudgetSubAwardService().updateSubAwardBudgetDetails(budget, subAward, errorMessages);
        if (!errorMessages.isEmpty()) {
            for (String[] message : errorMessages) {
                String[] messageParameters = null;
                if (message.length > 1) {
                    messageParameters = Arrays.copyOfRange(message, 1, message.length);
                }
                if (success) {
                    globalVariableService.getMessageMap().putWarning(ProposalBudgetConstants.KradConstants.SUBAWARDS_COLLECTION, message[0], messageParameters);
                } else {
                	globalVariableService.getMessageMap().putError(errorPath + Constants.SUBAWARD_FILE_FIELD_NAME, message[0], messageParameters);
                }
            }
        }
        if (success && errorMessages.isEmpty()) {
        	globalVariableService.getMessageMap().putInfo(errorPath + Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_DETAILS_UPDATED);
        }
        return success;
    }

	public PropDevBudgetSubAwardService getPropDevBudgetSubAwardService() {
		return propDevBudgetSubAwardService;
	}

	public void setPropDevBudgetSubAwardService(
			PropDevBudgetSubAwardService propDevBudgetSubAwardService) {
		this.propDevBudgetSubAwardService = propDevBudgetSubAwardService;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	}

	public KcAttachmentService getKcAttachmentService() {
		return kcAttachmentService;
	}

	public void setKcAttachmentService(KcAttachmentService kcAttachmentService) {
		this.kcAttachmentService = kcAttachmentService;
	}
	
	
}
