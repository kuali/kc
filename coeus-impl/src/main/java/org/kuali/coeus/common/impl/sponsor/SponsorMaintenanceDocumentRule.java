/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.common.impl.sponsor;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.external.customercreation.CustomerConstants;
import org.kuali.kra.external.customercreation.CustomerCreationClient;
import org.kuali.kra.external.dunningcampaign.DunningCampaignClient;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.service.DataDictionaryService;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;

import java.util.regex.Pattern;

/**
 * This class overrides the custom route and custom approve methods of the MaintenanceDocument processing to check the length of the
 * sponsor code and return a more informative error message than the Rice message if the length constraint is violated.
 */
public class SponsorMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    private CustomerCreationClient customerCreationClient;
    private DataDictionaryService dataDictionaryService;
    private DunningCampaignClient dunningCampaignClient;
    private GlobalVariableService globalVariableService;
    private ParameterService parameterService;

    public SponsorMaintenanceDocumentRule() {
        super();
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkDunningCampaign(document) && checkCustomer(document);
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkDunningCampaign(document) && checkCustomer(document);
    }

    protected boolean checkDunningCampaign(MaintenanceDocument document) {
        boolean valid = true;
        Sponsor sponsor = (Sponsor) document.getNewMaintainableObject().getDataObject();
        if (StringUtils.isNotBlank(sponsor.getDunningCampaignId())
                && getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.PARAMETER_COMPONENT_DOCUMENT, SponsorConstants.FIN_SYSTEM_INTEGRATION_ON_SPONSOR)
                && getDunningCampaignClient().getDunningCampaign(sponsor.getDunningCampaignId()) == null) {
            String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "dunningCampaignId");
            getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.dunningCampaignId", KeyConstants.ERROR_MISSING, errorLabel);
            valid = false;
        }
        return valid;
    }

    protected boolean checkCustomer(MaintenanceDocument document) {
        boolean valid = true;
        Sponsor sponsor = (Sponsor) document.getNewMaintainableObject().getDataObject();
        if (StringUtils.equals(sponsor.getCustomerExists(), CustomerConstants.CustomerOptions.Types.EXISTING.getCode())) {
            if (getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, Constants.PARAMETER_COMPONENT_DOCUMENT, SponsorConstants.FIN_SYSTEM_INTEGRATION_ON_SPONSOR)
                    && !getCustomerCreationClient().isValidCustomer(sponsor.getCustomerNumber())) {
                String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "customerNumber");
                getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.customerNumber", KeyConstants.ERROR_MISSING, errorLabel);
                valid = false;
            }
        } else if (StringUtils.equals(sponsor.getCustomerExists(), CustomerConstants.CustomerOptions.Types.NEW.getCode()) &&
                StringUtils.isBlank(sponsor.getCustomerTypeCode())) {
            String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "customerTypeCode");
            getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.customerTypeCode", KeyConstants.ERROR_MISSING, errorLabel);
            valid = false;
        } else if (StringUtils.equals(sponsor.getCustomerExists(), CustomerConstants.CustomerOptions.Types.NO.getCode())
                && !StringUtils.isBlank(sponsor.getCustomerNumber())
                && !StringUtils.isBlank(sponsor.getCustomerTypeCode())) {
            String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "customerExists");
            getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.customerExists", KeyConstants.ERROR_MISSING, errorLabel);
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

    public DataDictionaryService getDataDictionaryService() {
        if (this.dataDictionaryService == null) {
            this.dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        }
        return this.dataDictionaryService;
    }

    public void setDataDictionaryService(DataDictionaryService dataDictionaryService) {
        this.dataDictionaryService = dataDictionaryService;
    }

    public DunningCampaignClient getDunningCampaignClient() {
        if (dunningCampaignClient == null) {
            dunningCampaignClient = KcServiceLocator.getService(DunningCampaignClient.class);
        }
        return dunningCampaignClient;
    }

    public void setDunningCampaignClient(DunningCampaignClient dunningCampaignClient) {
        this.dunningCampaignClient = dunningCampaignClient;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (this.globalVariableService == null) {
            this.globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return this.globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
