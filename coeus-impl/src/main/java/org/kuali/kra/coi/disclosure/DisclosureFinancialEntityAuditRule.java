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
package org.kuali.kra.coi.disclosure;

import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.coi.*;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.krad.util.AuditCluster;
import org.kuali.rice.krad.util.AuditError;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
public class DisclosureFinancialEntityAuditRule extends KcTransactionalDocumentRuleBase implements DocumentAuditRule {

    private static final String FINANCIAL_ENTITY_AUDIT_ERRORS = "financialEntityDiscAuditErrors";
    private static final String NEW_TAG = "disclosureHelper.newCoiDisclProject.finEntityStatusMissing";
    private List<AuditError> auditErrors;
    
    public boolean processRunAuditBusinessRules(Document document) {
        boolean isValid = true;
        CoiDisclosureDocument coiDisclosureDocument = (CoiDisclosureDocument) document;
        auditErrors = new ArrayList<AuditError>();
        
        // TODO : Once the normalize is done, then the audit rule will be simpler, and we don't
        // need these event check, all events will be the same.
        if (coiDisclosureDocument.getCoiDisclosure().isManualEvent()) {
            isValid = isConflictValueSelectedForManual(coiDisclosureDocument.getCoiDisclosure());
            
        } else if (coiDisclosureDocument.getCoiDisclosure().isAnnualEvent() && !coiDisclosureDocument.getCoiDisclosure().isAnnualUpdate()) {
            isValid = isConflictValueSelectedForAnnual(coiDisclosureDocument.getCoiDisclosure());
            
        } else if (coiDisclosureDocument.getCoiDisclosure().isUpdateEvent() || (coiDisclosureDocument.getCoiDisclosure().isAnnualEvent() && coiDisclosureDocument.getCoiDisclosure().isAnnualUpdate())) {
            isValid = isConflictValueSelectedForUpdate(coiDisclosureDocument.getCoiDisclosure());
            
        } else {
            isValid = isConflictValueSelected(coiDisclosureDocument.getCoiDisclosure());
        }

        reportAndCreateAuditCluster();
        
        return isValid;
    }

    protected void addErrorToAuditErrors(int index, String errorKey, String anchor, String error) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(anchor);
        auditErrors.add(new AuditError(String.format(errorKey, index), error, stringBuilder.toString()));   
    }
    
    protected void addErrorToAuditErrors(int index, int index1, String errorKey) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.DISCLOSURE_FINANCIAL_ENTITY_PANEL_ANCHOR);
        auditErrors.add(new AuditError(String.format(errorKey, index, index1),
                                        KeyConstants.ERROR_COI_FINANCIAL_ENTITY_STATUS_REQUIRED,
                                        stringBuilder.toString()));   
    }

    protected void addErrorToAuditErrors(String property, int index, int index1, String errorKey) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Constants.COI_DISCLOSURE_DISCLOSURE_PAGE);
        stringBuilder.append(".");
        stringBuilder.append(Constants.DISCLOSURE_FINANCIAL_ENTITY_PANEL_ANCHOR);
        auditErrors.add(new AuditError(String.format(errorKey, property, index, index1),
                                        KeyConstants.ERROR_COI_FINANCIAL_ENTITY_STATUS_REQUIRED,
                                        stringBuilder.toString()));   
    }

    protected boolean isConflictValueSelected(CoiDisclosure coiDisclosure) {
        boolean isSelected = true;
        int i = 0;
        for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDisclProjects().get(0).getCoiDiscDetails()) {
            if (coiDiscDetail.getEntityDispositionCode() == null) {
                addErrorToAuditErrors(i, 
                                        Constants.DISCLOSURE_FINANCIAL_ENTITY_KEY2,
                                        Constants.DISCLOSURE_FINANCIAL_ENTITY_PANEL_ANCHOR,
                                        KeyConstants.ERROR_COI_FINANCIAL_ENTITY_STATUS_REQUIRED);
                isSelected = false;
            }
            i++;
        }
        return isSelected;
    }
    
    protected boolean isConflictValueSelectedForAnnual(CoiDisclosure coiDisclosure) {
        boolean isSelected = true;
        int i = 0;
        // Allow annual disclosures to be attached without projects. This is a likely scenario
        if (ObjectUtils.isNotNull(coiDisclosure.getCoiDisclProjects()) && !coiDisclosure.getCoiDisclProjects().isEmpty()) {
            for (CoiDisclProject disclProject : coiDisclosure.getCoiDisclProjects()) {
                if (!isEventExcludedFE(disclProject.getDisclosureEventType())) {
                    int j = 0;
                    for (CoiDiscDetail coiDiscDetail : disclProject.getCoiDiscDetails()) {
                        if (coiDiscDetail.getEntityDispositionCode() == null) {
                            addErrorToAuditErrors(i, j, Constants.DISCLOSURE_ANNUAL_FINANCIAL_ENTITY_KEY2);
                            isSelected = false;
                        }
                        j++;
                    }
                }
                i++;
            }
        }
        return isSelected;
    }

    protected boolean isConflictValueSelectedForManual(CoiDisclosure coiDisclosure) {
        boolean isSelected = true;
        int i = 0;
        // Missing project. There should be a project linked to all manual and event disclosures
        if (coiDisclosure.getCoiDisclProjects().isEmpty()) {
            addErrorToAuditErrors(i, Constants.DISCLOSURE_MANUAL_FINANCIAL_ENTITY_KEY, 
                    Constants.DISCLOSURE_FINANCIAL_ENTITY_PANEL_ANCHOR, KeyConstants.ERROR_COI_PROJECT_REQUIRED);
            isSelected = false;
        }
        else {
            if (!coiDisclosure.getCoiDisclosureEventType().isExcludeFinancialEntities()) {
                for (CoiDiscDetail coiDiscDetail : coiDisclosure.getCoiDisclProjects().get(0).getCoiDiscDetails()) {
                    if (coiDiscDetail.getEntityDispositionCode() == null) {
                        addErrorToAuditErrors(i, Constants.DISCLOSURE_MANUAL_FINANCIAL_ENTITY_KEY, NEW_TAG, KeyConstants.ERROR_COI_FINANCIAL_ENTITY_STATUS_REQUIRED);
                        isSelected = false;
                    }
                    i++;
                }
            }
        }
        return isSelected;
    }

    protected void reportAndCreateAuditCluster() {
        if (auditErrors.size() > 0) {
            GlobalVariables.getAuditErrorMap().put(FINANCIAL_ENTITY_AUDIT_ERRORS, 
                    new AuditCluster(Constants.COI_DISCLOSURE_DISCLOSURE_PANEL_NAME, auditErrors, Constants.AUDIT_ERRORS));
        }
    }

    protected boolean isConflictValueSelectedForUpdate(CoiDisclosure coiDisclosure) {
        MasterDisclosureBean masterDisclosureBean = getCoiDisclosureService().getMasterDisclosureDetail(coiDisclosure);
        boolean isSelected = true;
        isSelected &= checkProject(masterDisclosureBean.getAwardProjects(), "awardProjects");
        isSelected &= checkProject(masterDisclosureBean.getProtocolProjects(), "protocolProjects");
        isSelected &= checkProject(masterDisclosureBean.getProposalProjects(), "proposalProjects");
        isSelected &= checkProject(masterDisclosureBean.getManualAwardProjects(), "manualAwardProjects");
        isSelected &= checkProject(masterDisclosureBean.getManualProtocolProjects(), "manualProtocolProjects");
        isSelected &= checkProject(masterDisclosureBean.getManualProposalProjects(), "manualProposalProjects");
        isSelected &= checkProject(masterDisclosureBean.getManualTravelProjects(), "manualTravelProjects");
        return isSelected;
    }

    private boolean checkProject(List<CoiDisclosureProjectBean> disclProjects, String property) {
        boolean isSelected = true;
        int i = 0;
        for (CoiDisclosureProjectBean disclProjectBean : disclProjects) {
            if (!disclProjectBean.isExcludeFE()) {
                int j = 0;
                for (CoiDiscDetail coiDiscDetail : disclProjectBean.getCoiDisclProject().getCoiDiscDetails()) {
                    if (coiDiscDetail.getEntityDispositionCode() == null) {
                        addErrorToAuditErrors(property, i, j, Constants.DISCLOSURE_UPDATE_FINANCIAL_ENTITY_KEY);
                        isSelected = false;
                    }
                    j++;
                }
            }
            i++;
        }
        return isSelected;

    }


    private CoiDisclosureService getCoiDisclosureService() {
        return KcServiceLocator.getService(CoiDisclosureService.class);
    }
    
    private boolean isEventExcludedFE(String eventTypeCode) {
        Map<String, Object> fieldValues = new HashMap<String, Object>();
        fieldValues.put("eventTypeCode", eventTypeCode);
        CoiDisclosureEventType CoiDisclosureEventType =  KcServiceLocator.getService(BusinessObjectService.class).findByPrimaryKey(CoiDisclosureEventType.class, fieldValues);
        return CoiDisclosureEventType.isExcludeFinancialEntities();        
    }
}
