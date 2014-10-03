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
import org.kuali.coeus.common.framework.attachment.KcAttachmentService;
import org.kuali.coeus.common.framework.ruleengine.KcBusinessRule;
import org.kuali.coeus.common.framework.ruleengine.KcEventMethod;
import org.kuali.coeus.common.framework.ruleengine.KcEventResult;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.kra.infrastructure.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.io.FileNotFoundException;
import java.io.IOException;

@KcBusinessRule("budgetSubAwardsRule")
public class BudgetSubAwardsRule  {

    private static final Log LOG = LogFactory.getLog(BudgetSubAwardsRule.class);
    public static final String SUBAWARD_ORG_NAME_FIELD_NAME = "organizationId";
    
    @Autowired
    @Qualifier("kcAttachmentService")
    private KcAttachmentService kcAttachmentService;
    
    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;
    
    @KcEventMethod
    public KcEventResult processSubaward(BudgetSubAwardsEvent event) {
    	KcEventResult result = new KcEventResult();
    	result.getMessageMap().addToErrorPath(event.getErrorPath());
        verifyAttachment(event, result);
        event.getBudgetSubAwards().setNewSubAwardFileError(result.getSuccess());
        verifyOrganizationName(event, result);
        return result;
    }
    
    protected void verifyOrganizationName(BudgetSubAwardsEvent event, KcEventResult result){
        if (StringUtils.isBlank(event.getBudgetSubAwards().getOrganizationId())) {
        	result.getMessageMap().putError(SUBAWARD_ORG_NAME_FIELD_NAME, Constants.SUBAWARD_ORG_NAME_REQUIERED);
            result.setSuccess(false);
        } else {
        	if (event.getBudgetSubAwards().getOrganization() == null) {
            	result.getMessageMap().putError(SUBAWARD_ORG_NAME_FIELD_NAME, Constants.SUBAWARD_ORG_NAME_INVALID);
                result.setSuccess(false);
            }
        }
    }
    
    protected void verifyAttachment(BudgetSubAwardsEvent event, KcEventResult result) {
        if (event.getBudgetSubAwards().getNewSubAwardFile() != null) {
	        try {
	        	byte[] subAwardData = event.getBudgetSubAwards().getNewSubAwardFile().getBytes();
	            if (ArrayUtils.isEmpty(subAwardData)) {
	            	result.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
	                result.setSuccess(false);
	            }
	            String contentType = event.getBudgetSubAwards().getNewSubAwardFile().getContentType();

	            if(ArrayUtils.isEmpty(subAwardData) || !contentType.equals(Constants.PDF_REPORT_CONTENT_TYPE)){
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

    protected KcAttachmentService getKcAttachmentService() {
        return kcAttachmentService;
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
