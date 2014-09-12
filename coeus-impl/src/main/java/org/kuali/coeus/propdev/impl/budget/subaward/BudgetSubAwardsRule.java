/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.propdev.impl.budget.subaward;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.upload.FormFile;
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.common.framework.org.Organization;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRuleBase;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.common.framework.ruleengine.KcEventResult;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@KcBusinessRule("budgetSubAwardsRule")
public class BudgetSubAwardsRule extends KcBusinessRuleBase {

    private static final Log LOG = LogFactory.getLog(BudgetSubAwardsRule.class);
    public static final String SUBAWARD_ORG_NAME_FIELD_NAME = ".organizationName";

    @Autowired
    @Qualifier("businessObjectService")
    private BusinessObjectService businessObjectService;
    
    @Autowired
    @Qualifier("kcAttachmentService")
    private KcAttachmentService kcAttachmentService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @KcEventMethod(events={BudgetSubAwardsEvent.EVENT_NAME})
    public KcEventResult processNonXFDAttachment(BudgetSubAwardsEvent event) {
    	KcEventResult result = new KcEventResult();
        verifyNonXFDAttachment(event, result);
        event.getBudgetSubAwards().setNewSubAwardFileError(!result.getSuccess());
        verifyOrganizationName(event, result);
        return result;
    }
    
    @KcEventMethod(events={BudgetSubAwardsEvent.EVENT_NAME})
    public KcEventResult processXFDAttachment(BudgetSubAwardsEvent event) {
    	KcEventResult result = new KcEventResult();
        verifyXFDAttachment(event, result);
        event.getBudgetSubAwards().setNewSubAwardFileError(result.getSuccess());
        verifyOrganizationName(event, result);
        return result;
    }
    
    @KcEventMethod(events={BudgetSubAwardsEvent.EVENT_NAME})
    public boolean checkSpecialCharacters(BudgetSubAwardsEvent event){
        if(getKcAttachmentService().getSpecialCharacter(event.getBudgetSubAwards().getSubAwardXmlFileData().toString())) {
            globalVariableService.getMessageMap().putWarning(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_SPECIAL_CHARECTOR);
            return true;
        }
        return false;
    }
    
    protected void verifyOrganizationName(BudgetSubAwardsEvent event, KcEventResult result){
        boolean success = true;
        if (StringUtils.isBlank(event.getBudgetSubAwards().getOrganizationName())) {
        	result.getMessageMap().putError(SUBAWARD_ORG_NAME_FIELD_NAME, Constants.SUBAWARD_ORG_NAME_REQUIERED);
            success = false;
        } else {
            Map params = new HashMap();
            params.put("organizationName", event.getBudgetSubAwards().getOrganizationName());
            Organization dbOrganization = this.getBusinessObjectService().findByPrimaryKey(Organization.class, params);
            if (dbOrganization == null || !StringUtils.equals(dbOrganization.getOrganizationName(), event.getBudgetSubAwards().getOrganizationName())) {
            	result.getMessageMap().putError(SUBAWARD_ORG_NAME_FIELD_NAME, Constants.SUBAWARD_ORG_NAME_INVALID);
                success = false;
            }
        }
    }
    
    protected void verifyNonXFDAttachment(BudgetSubAwardsEvent event, KcEventResult result) {
        FormFile newBudgetSubAwardFile = event.getBudgetSubAwards().getNewSubAwardFile();
        if (newBudgetSubAwardFile == null) {
            result.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
            result.setSuccess(false);
        } else {
	        try {
	            if (ArrayUtils.isEmpty(newBudgetSubAwardFile.getFileData())) {
	            	result.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
	                result.setSuccess(false);
	            }
	        } catch(FileNotFoundException fnfe) {
	            LOG.error(fnfe.getMessage(), fnfe);
	            result.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
	            result.setSuccess(false);
	        } catch(IOException ioe) {
	            LOG.error(ioe.getMessage(), ioe);
	            result.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
	            result.setSuccess(false);
	        }
        }
    }
    
    protected void verifyXFDAttachment(BudgetSubAwardsEvent event, KcEventResult result) {
        verifyNonXFDAttachment(event, result);
        if (result.getSuccess()) {
            FormFile subAwardFile = event.getBudgetSubAwards().getNewSubAwardFile();
            String contentType = subAwardFile.getContentType();
            byte[] subAwardData = null;
            try {
                subAwardData = subAwardFile.getFileData();
            }
            catch (FileNotFoundException e) {
                LOG.error(e.getMessage(), e);
                //should never happen as this would be caught in verifyNonXFDAttachment
            }
            catch (IOException e) {
                LOG.error(e.getMessage(), e);
                //should never happen as this would be caught in verifyNonXFDAttachment
            }
            if(subAwardData==null || subAwardData.length==0 || !contentType.equals(Constants.PDF_REPORT_CONTENT_TYPE)){
            	result.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
            	result.setSuccess(false);
            } else {
                if(event.getBudgetSubAwards().getBudgetSubAwardFiles().isEmpty() || event.getBudgetSubAwards().getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData()==null){
                	result.setSuccess(false);
                }
            }
        }
    }
    
    protected BusinessObjectService getBusinessObjectService() {
        return businessObjectService;
    }

    protected KcAttachmentService getKcAttachmentService() {
        return kcAttachmentService;
    }

	public void setBusinessObjectService(BusinessObjectService businessObjectService) {
		this.businessObjectService = businessObjectService;
	}

	public void setKcAttachmentService(KcAttachmentService kcAttachmentService) {
		this.kcAttachmentService = kcAttachmentService;
	}

	public GlobalVariableService getGlobalVariableService() {
		return globalVariableService;
	}

	public void setGlobalVariableService(GlobalVariableService globalVariableService) {
		this.globalVariableService = globalVariableService;
	} 
}
