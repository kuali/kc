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
package org.kuali.coeus.common.impl.sponsor;

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

import java.util.regex.Pattern;

/**
 * This class overrides the custom route and custom approve methods of the MaintenanceDocument processing to check the length of the
 * sponsor code and return a more informative error message than the Rice message if the length constraint is violated.
 */
public class SponsorMaintenanceDocumentRule extends KcMaintenanceDocumentRuleBase {
    private static final String SPONSOR_CODE_FIELD_NAME = "sponsorCode";
    private static final String SPONSOR_CODE_FORMAT_DESCRIPTION = "exactly six(6) alphanumeric characters";
    private static final String SPONSOR_CODE_ERROR_PROPERTY_NAME = "document.newMaintainableObject.sponsorCode";
    private static final String SPONSOR_CODE_REGEX = "[a-zA-Z0-9]{6}";

    private CustomerCreationClient customerCreationClient;
    private DataDictionaryService dataDictionaryService;
    private DunningCampaignClient dunningCampaignClient;
    private GlobalVariableService globalVariableService;

    /**
     * Constructs a SponsorMaintenanceDocumentRule.java.
     */
    public SponsorMaintenanceDocumentRule() {
        super();
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase#processCustomApproveDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomApproveDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCode(document) && checkDunningCampaign(document)
                && checkCustomer(document);
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.coeus.sys.framework.rule.KcMaintenanceDocumentRuleBase#processCustomRouteDocumentBusinessRules(org.kuali.rice.kns.document.MaintenanceDocument)
     */
    @Override
    protected boolean processCustomRouteDocumentBusinessRules(MaintenanceDocument document) {
        return checkSponsorCode(document) && checkDunningCampaign(document)
                && checkCustomer(document);
    }

    /**
     * This method verifies that the sponsorCode adheres to the required rules.
     *
     * @param document
     * @return
     */
    private boolean checkSponsorCode(MaintenanceDocument document) {
        boolean valid = true;
        Sponsor sponsor = (Sponsor) document.getNewMaintainableObject().getDataObject();
        if (sponsor.getSponsorCode() != null && !Pattern.matches(SPONSOR_CODE_REGEX, sponsor.getSponsorCode())) {
            String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, SPONSOR_CODE_FIELD_NAME);
            getGlobalVariableService().getMessageMap().putError(SPONSOR_CODE_ERROR_PROPERTY_NAME, KeyConstants.ERROR_INVALID_FORMAT_WITH_FORMAT, errorLabel,
                    sponsor.getSponsorCode(), SPONSOR_CODE_FORMAT_DESCRIPTION);
            valid = false;
        }
        return valid;
    }

    protected boolean checkDunningCampaign(MaintenanceDocument document) {
        boolean valid = true;
        Sponsor sponsor = (Sponsor) document.getNewMaintainableObject().getDataObject();
        if (StringUtils.isNotBlank(sponsor.getDunningCampaignId())
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
        if (StringUtils.equals(sponsor.getCustomerExists(), "Y")) {
            if (!getCustomerCreationClient().isValidCustomer(sponsor.getCustomerNumber())) {
                String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "customerNumber");
                getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.customerNumber", KeyConstants.ERROR_MISSING, errorLabel);
                valid = false;
            }
        } else if (StringUtils.equals(sponsor.getCustomerExists(), "N") &&
                StringUtils.isBlank(sponsor.getCustomerTypeCode())) {
            String errorLabel = getDataDictionaryService().getAttributeErrorLabel(Sponsor.class, "customerTypeCode");
            getGlobalVariableService().getMessageMap().putError("document.newMaintainableObject.customerTypeCode", KeyConstants.ERROR_MISSING, errorLabel);
            valid = false;
        } else if (StringUtils.equals(sponsor.getCustomerExists(), "NA")
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

    public DataDictionaryService getDataDictionaryService() {
        if (this.dataDictionaryService == null) {
            this.dataDictionaryService = KcServiceLocator.getService(DataDictionaryService.class);
        }
        return this.dataDictionaryService;
    }

    public DunningCampaignClient getDunningCampaignClient() {
        if (dunningCampaignClient == null) {
            dunningCampaignClient = KcServiceLocator.getService(DunningCampaignClient.class);
        }
        return dunningCampaignClient;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (this.globalVariableService == null) {
            this.globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return this.globalVariableService;
    }
}
