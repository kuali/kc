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
package org.kuali.coeus.common.impl.sponsor.form;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.common.framework.sponsor.form.SponsorForms;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.external.customercreation.CustomerConstants;
import org.kuali.kra.external.customercreation.CustomerCreationClient;
import org.kuali.kra.external.dunningcampaign.DunningCampaignClient;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
/**
 * This class overrides the custom route and custom approve methods of the MaintenanceDocument processing to check the length of the
 * sponsor code and return a more informative error message than the Rice message if the length constraint is violated.
 */
public class SponsorFormsMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {

    private CustomerCreationClient customerCreationClient;
    private DataDictionaryService dataDictionaryService;
    private GlobalVariableService globalVariableService;

    public SponsorFormsMaintenanceDocumentRule() {
        super();
    }
    
    @Override
    protected boolean processCustomSaveDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCodeOrHierarchyName(document) && checkDunningCampaign(document) && checkCustomer(document);
    }

    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCodeOrHierarchyName(document) && checkDunningCampaign(document) && checkCustomer(document);
    }

    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCodeOrHierarchyName(document) && checkDunningCampaign(document) && checkCustomer(document);
    }

    /**
     * This method verifies that the sponsorCode adheres to the required rules.
     * 
     * @param document
     * @return
     */
    private boolean checkSponsorCodeOrHierarchyName(MaintenanceDocument document) {
        boolean valid = true;
        SponsorForms sponsorForm = (SponsorForms) document.getNewMaintainableObject().getDataObject();
        if (StringUtils.isBlank(sponsorForm.getSponsorCode()) && StringUtils.isBlank(sponsorForm.getSponsorHierarchyName())
                || (StringUtils.isNotBlank(sponsorForm.getSponsorCode()) && StringUtils.isNotBlank(sponsorForm.getSponsorHierarchyName()))) {
            globalVariableService.getMessageMap().putError("document.newMaintainableObject.sponsorCode", "error.sponsorForms.selector");
            valid = false;
        }
        return valid;
    }

    protected boolean checkDunningCampaign(MaintenanceDocument document) {
        boolean valid = true;
        Sponsor sponsor = (Sponsor) document.getNewMaintainableObject().getDataObject();
        if (StringUtils.isNotBlank(sponsor.getDunningCampaignId())
                && KcServiceLocator.getService(DunningCampaignClient.class).getDunningCampaign(sponsor.getDunningCampaignId()) == null) {
            String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "dunningCampaignId");
            globalVariableService.getMessageMap().putError("document.newMaintainableObject.dunningCampaignId", KeyConstants.ERROR_MISSING, errorLabel);
            valid = false;
        }
        return valid;
    }

    protected boolean checkCustomer(MaintenanceDocument document) {
        boolean valid = true;
        Sponsor sponsor = (Sponsor) document.getNewMaintainableObject().getDataObject();
        if (StringUtils.equals(sponsor.getCustomerExists(), CustomerConstants.CustomerOptions.Types.EXISTING.getCode())) {
            if (!KcServiceLocator.getService(CustomerCreationClient.class).isValidCustomer(sponsor.getCustomerNumber())) {
                String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "customerNumber");
                globalVariableService.getMessageMap().putError("document.newMaintainableObject.customerNumber", KeyConstants.ERROR_MISSING, errorLabel);
                valid = false;
            }
        } else if (StringUtils.equals(sponsor.getCustomerExists(), CustomerConstants.CustomerOptions.Types.NEW.getCode()) &&
                StringUtils.isBlank(sponsor.getCustomerTypeCode())) {
            String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "customerTypeCode");
            globalVariableService.getMessageMap().putError("document.newMaintainableObject.customerTypeCode", KeyConstants.ERROR_MISSING, errorLabel);
            valid = false;
        } else if (StringUtils.equals(sponsor.getCustomerExists(), CustomerConstants.CustomerOptions.Types.NO.getCode())
                && !StringUtils.isBlank(sponsor.getCustomerNumber())
                && !StringUtils.isBlank(sponsor.getCustomerTypeCode())) {
            String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "customerExists");
            globalVariableService.getMessageMap().putError("document.newMaintainableObject.customerExists", KeyConstants.ERROR_MISSING, errorLabel);
            valid = false;
        }
        return valid;
    }

    public CustomerCreationClient getCustomerCreationClient() {
        if (this.customerCreationClient == null) {
            this.customerCreationClient = KcServiceLocator.getService(CustomerCreationClient.class);
        }
        return this.customerCreationClient;
    }

    public void setCustomerCreationClient(CustomerCreationClient customerCreationClient) {
        this.customerCreationClient = customerCreationClient;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    public DataDictionaryService getDataDictionaryService() {
        if (this.dataDictionaryService == null) {
            this.dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        }
        return this.dataDictionaryService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (this.globalVariableService == null) {
            this.globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return this.globalVariableService;
    }
}