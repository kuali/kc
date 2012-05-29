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
package org.kuali.kra.proposaldevelopment.budget.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.upload.FormFile;
import org.kuali.kra.bo.Organization;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.GlobalVariables;

public class BudgetSubAwardsRule {
    
    public static final String SUBAWARD_FILE_FIELD_NAME = ".subAwardFile";
    public static final String SUBAWARD_ORG_NAME_FIELD_NAME = ".organizationName";
    
    private BudgetSubAwards budgetSubAwards;
    private String fieldStarter;
    private BusinessObjectService businessObjectService;
    
    
    public BudgetSubAwardsRule(BudgetSubAwards budgetSubAwards, String fieldStarter) {
        this.budgetSubAwards = budgetSubAwards;
        this.fieldStarter = fieldStarter;
    }
    
    public boolean processApply() {
        budgetSubAwards.setNewSubAwardFileError(false);
        return verifyOrganizationName();
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
    
    protected boolean verifyOrganizationName(){
        boolean success = true;
        if (StringUtils.isBlank(budgetSubAwards.getOrganizationName())) {
            GlobalVariables.getMessageMap().putError(fieldStarter + SUBAWARD_ORG_NAME_FIELD_NAME, Constants.SUBAWARD_ORG_NAME_REQUIERED);
            success = false;
        } else {
            Map params = new HashMap();
            params.put("organizationName", budgetSubAwards.getOrganizationName());
            Organization dbOrganization = this.getBusinessObjectService().findByPrimaryKey(Organization.class, params);
            if (dbOrganization == null || !StringUtils.equals(dbOrganization.getOrganizationName(), budgetSubAwards.getOrganizationName())) {
                GlobalVariables.getMessageMap().putError(fieldStarter + SUBAWARD_ORG_NAME_FIELD_NAME, Constants.SUBAWARD_ORG_NAME_INVALID);
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
                GlobalVariables.getMessageMap().putError(fieldStarter + SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
                success = false;
            }
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            GlobalVariables.getMessageMap().putError(fieldStarter + SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
            success = false;
        } catch(IOException ioe) {
            ioe.printStackTrace();
            GlobalVariables.getMessageMap().putError(fieldStarter + SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
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
                // TODO Auto-generated catch block
                //e.printStackTrace();
                //should never happen as this would be caught in verifyNonXFDAttachment
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
                //should never happen as this would be caught in verifyNonXFDAttachment
            }
            if(subAwardData==null || subAwardData.length==0 || !contentType.equals(Constants.PDF_REPORT_CONTENT_TYPE)){
                GlobalVariables.getMessageMap().putError(fieldStarter + SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_REQUIERED);
                success = false;
            } else {
                if(budgetSubAwards.getBudgetSubAwardFiles().isEmpty() || budgetSubAwards.getBudgetSubAwardFiles().get(0).getSubAwardXmlFileData()==null){
                    GlobalVariables.getMessageMap().putError(fieldStarter + SUBAWARD_FILE_FIELD_NAME, Constants.SUBAWARD_FILE_NOT_EXTRACTED);
                    success = false;
                }
            }
        }
        return success;
    }
    
    private BusinessObjectService getBusinessObjectService() {
        if (businessObjectService == null) {
            businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        }
        return businessObjectService;
    }
}
