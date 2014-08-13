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
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BudgetSubAwardsRule {

    private static final Log LOG = LogFactory.getLog(BudgetSubAwardsRule.class);
    public static final String SUBAWARD_ORG_NAME_FIELD_NAME = ".organizationName";
    
    private BudgetSubAwards budgetSubAwards;
    private BusinessObjectService businessObjectService;
    
    
    public BudgetSubAwardsRule(BudgetSubAwards budgetSubAwards) {
        this.budgetSubAwards = budgetSubAwards;
    }
    
    public boolean processNonXFDAttachment() {
        boolean nonXFD = verifyNonXFDAttachment();
        budgetSubAwards.setNewSubAwardFileError(!nonXFD);
        return verifyOrganizationName() && nonXFD;
    }
    
    public boolean processXFDAttachment() {
        boolean xfd = verifyXFDAttachment();
        budgetSubAwards.setNewSubAwardFileError(!xfd);
        return verifyOrganizationName() && xfd;
    }
    
    public boolean checkSpecialCharacters(String text){
        if(getKcAttachmentService().getSpecialCharacter(text)) {
            GlobalVariables.getMessageMap().putWarning(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_SPECIAL_CHARECTOR);
            return true;
        }
        return false;
    }
    
    protected boolean verifyOrganizationName(){
        boolean success = true;
        if (StringUtils.isBlank(budgetSubAwards.getOrganizationName())) {
            GlobalVariables.getMessageMap().putError(SUBAWARD_ORG_NAME_FIELD_NAME, Constants.SUBAWARD_ORG_NAME_REQUIERED);
            success = false;
        } else {
            Map params = new HashMap();
            params.put("organizationName", budgetSubAwards.getOrganizationName());
            Organization dbOrganization = this.getBusinessObjectService().findByPrimaryKey(Organization.class, params);
            if (dbOrganization == null || !StringUtils.equals(dbOrganization.getOrganizationName(), budgetSubAwards.getOrganizationName())) {
                GlobalVariables.getMessageMap().putError(SUBAWARD_ORG_NAME_FIELD_NAME, Constants.SUBAWARD_ORG_NAME_INVALID);
                success = false;
            }
        }
        return success;
    }
    
    protected boolean verifyNonXFDAttachment() {
        boolean success = true;
        FormFile newBudgetSubAwardFile = budgetSubAwards.getNewSubAwardFile();
        try {
            if (ArrayUtils.isEmpty(newBudgetSubAwardFile.getFileData())) {
                GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
                success = false;
            }
        } catch(FileNotFoundException fnfe) {
            LOG.error(fnfe.getMessage(), fnfe);
            GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
            success = false;
        } catch(IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
            GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
            success = false;
        }
        return success;
    }
    
    protected boolean verifyXFDAttachment() {
        boolean success = verifyNonXFDAttachment();
        if (success) {
            FormFile subAwardFile = budgetSubAwards.getNewSubAwardFile();
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
                GlobalVariables.getMessageMap().putError(Constants.SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
                success = false;
            } else {
                if(budgetSubAwards.getBudgetSubAwardFiles().isEmpty() || budgetSubAwards.getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData()==null){
                    success = false;
                }
            }
        }
        return success;
    }
    
    private BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KcServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }

    public KcAttachmentService getKcAttachmentService() {
        return KcServiceLocator.getService(KcAttachmentService.class);
    } 
}
