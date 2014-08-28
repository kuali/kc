package org.kuali.coeus.propdev.impl.budget.subaward;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.uif.UifConstants;
import org.kuali.rice.krad.uif.UifParameters;
import org.kuali.rice.krad.uif.component.BindingInfo;
import org.kuali.rice.krad.uif.util.ObjectPropertyUtils;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.web.controller.MethodAccessible;
import org.kuali.rice.krad.web.service.impl.CollectionControllerServiceImpl.CollectionActionParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("proposalBudgetSubAwardController")
public class ProposalBudgetSubAwardController extends
		ProposalBudgetControllerBase {
	
	protected Log LOG = LogFactory.getLog(ProposalBudgetSubAwardController.class);

	@Autowired
	@Qualifier("propDevBudgetSubAwardService")
	private PropDevBudgetSubAwardService propDevBudgetSubAwardService;
	
	@Autowired
	@Qualifier("globalVariableService")
	private GlobalVariableService globalVariableService;
	
	@Autowired
	@Qualifier("kcAttachmentService")
	private KcAttachmentService kcAttachmentService;
	
	@MethodAccessible
	@RequestMapping(value="/proposalBudget", params={"methodToCall=addLine","actionParameters["+UifParameters.SELECTED_COLLECTION_PATH+"]=budget.budgetSubAwards"})
	public ModelAndView addSubAward(ProposalBudgetForm form) throws Exception {
		
		final CollectionActionParameters parameters = new CollectionActionParameters(form, false);
        BindingInfo addLineBindingInfo = (BindingInfo) form.getViewPostMetadata().getComponentPostData(
                parameters.getSelectedCollectionId(), UifConstants.PostMetadata.ADD_LINE_BINDING_INFO);
        BudgetSubAwards newBudgetSubAward = ObjectPropertyUtils.getPropertyValue(form, addLineBindingInfo.getBindingPath());
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
            byte[] fileData;
			try {
				fileData = newBudgetSubAward.getNewSubAwardFile().getBytes();
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
	
    protected boolean updateBudgetAttachment(Budget budget, BudgetSubAwards subAward, String fileName, byte[] fileData, String errorPath) throws Exception {
        subAward.setSubAwardXmlFileData(null);
        subAward.setFormName(null);
        subAward.setNamespace(null);
        boolean success = true;
        if (subAward.getNewSubAwardFile().getContentType().equalsIgnoreCase(Constants.PDF_REPORT_CONTENT_TYPE)) {
            getPropDevBudgetSubAwardService().populateBudgetSubAwardFiles(budget, subAward, fileName, fileData);
	        success &= updateSubAwardBudgetDetails(budget, subAward, errorPath);
        	if (subAward.getSubAwardXmlFileData() != null && kcAttachmentService.getSpecialCharacter(subAward.getSubAwardXmlFileData().toString())) {
        		globalVariableService.getMessageMap().putWarning(ProposalBudgetConstants.KradConstants.SUBAWARDS_COLLECTION, Constants.SUBAWARD_FILE_SPECIAL_CHARECTOR);
	            subAward.getBudgetSubAwardFiles().get(0).setSubAwardXmlFileData(kcAttachmentService.
	                    checkAndReplaceSpecialCharacters(subAward.getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData().toString()));
	            subAward.setSubAwardXmlFileData(subAward.getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData());
        	}
        }
        return success;
    }

    protected boolean updateSubAwardBudgetDetails(Budget budget, BudgetSubAwards subAward, String errorPath) throws Exception {
        List<String[]> errorMessages = new ArrayList<String[]>();
        boolean success = getPropDevBudgetSubAwardService().updateSubAwardBudgetDetails(budget, subAward, errorMessages);
        if (!errorMessages.isEmpty()) {
            for (String[] message : errorMessages) {
                String[] messageParameters = null;
                if (message.length > 1) {
                    messageParameters = Arrays.copyOfRange(message, 1, message.length);
                }
                if (success) {
                    GlobalVariables.getMessageMap().putWarning(ProposalBudgetConstants.KradConstants.SUBAWARDS_COLLECTION, message[0], messageParameters);
                } else {
                    GlobalVariables.getMessageMap().putError(errorPath + Constants.SUBAWARD_FILE_FIELD_NAME, message[0], messageParameters);
                }
            }
        }
        if (success && errorMessages.isEmpty()) {
            GlobalVariables.getMessageMap().putInfo(errorPath + Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_DETAILS_UPDATED);
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
