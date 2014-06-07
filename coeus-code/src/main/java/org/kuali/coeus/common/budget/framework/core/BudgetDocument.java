/*
 * Copyright 2005-2014 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.coeus.common.budget.framework.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.common.budget.framework.period.BudgetPeriod;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.propdev.impl.budget.ProposalDevelopmentBudgetExt;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.framework.auth.perm.Permissionable;
import org.kuali.coeus.sys.framework.model.KcTransactionalDocumentBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.authorization.KraAuthorizationConstants;
import org.kuali.kra.award.document.AwardDocument;
import org.kuali.kra.bo.DocumentNextvalue;
import org.kuali.coeus.common.budget.framework.nonpersonnel.BudgetLineItem;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.RoleConstants;
import org.kuali.kra.krms.KcKrmsConstants;
import org.kuali.kra.krms.KrmsRulesContext;
import org.kuali.kra.krms.service.impl.KcKrmsFactBuilderServiceHelper;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.COMPONENT;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants.NAMESPACE;
import org.kuali.rice.coreservice.framework.parameter.ParameterConstants;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.kim.api.identity.Person;
import org.kuali.rice.krad.data.jpa.converters.BooleanYNConverter;
import org.kuali.rice.krad.document.Copyable;
import org.kuali.rice.krad.document.SessionDocument;
import org.kuali.rice.krad.service.BusinessObjectService;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.ObjectUtils;
import org.kuali.rice.krms.api.engine.Facts.Builder;

@NAMESPACE(namespace = Constants.MODULE_NAMESPACE_BUDGET)
@COMPONENT(component = ParameterConstants.DOCUMENT_COMPONENT)
@Entity
@Table(name = "BUDGET_DOCUMENT")
public class BudgetDocument<T extends BudgetParent> extends KcTransactionalDocumentBase implements Copyable, SessionDocument, Permissionable, BudgetDocumentTypeChecker, KrmsRulesContext {

    private static final Log LOG = LogFactory.getLog(BudgetDocument.class);

    private static final long serialVersionUID = 6716733800206633452L;

    private static final String DOCUMENT_TYPE_CODE = "BUDG";

    @Column(name = "PARENT_DOCUMENT_KEY")
    private String parentDocumentKey;

    @Column(name = "PARENT_DOCUMENT_TYPE_CODE")
    private String parentDocumentTypeCode;

    @OneToOne(mappedBy = "budgetDocument", cascade = CascadeType.ALL)
    @JoinColumn(insertable = false, updatable = false)
    private Budget budget;

    @Column(name = "BUDGET_DELETED")
    @Convert(converter = BooleanYNConverter.class)
    private boolean budgetDeleted;

    @Transient
    private BudgetParentDocument<T> parentDocument;

    @Transient
    private transient DocumentService documentService;

    @Override
    public void processAfterRetrieve() {
        super.processAfterRetrieve();
        if (getParentDocumentKey() != null) {
            getParentDocument().processAfterRetrieveForBudget(this);
        }
        Long budgetId = getBudget().getBudgetId();
        try {
            List<BudgetPeriod> budgetPeriods = getBudget().getBudgetPeriods();
            for (BudgetPeriod budgetPeriod : budgetPeriods) {
                ObjectUtils.setObjectPropertyDeep(budgetPeriod, "budgetId", Long.class, budgetId);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void initialize() {
    }

    public Integer getHackedDocumentNextValue(String propertyName) {
        Integer propNextValue = 1;
        // search for property and get the latest number - increment for next call 
        for (Object element : getDocumentNextvalues()) {
            DocumentNextvalue documentNextvalue = (DocumentNextvalue) element;
            if (documentNextvalue.getPropertyName().equalsIgnoreCase(propertyName)) {
                propNextValue = documentNextvalue.getNextValue();
                BusinessObjectService bos = KcServiceLocator.getService(BusinessObjectService.class);
                Map<String, Object> budgetIdMap = new HashMap<String, Object>();
                budgetIdMap.put("budgetId", getBudget().getBudgetId());
                if (budgetIdMap != null) {
                    List<BudgetLineItem> lineItemNumber = (List<BudgetLineItem>) bos.findMatchingOrderBy(BudgetLineItem.class, budgetIdMap, "lineItemNumber", true);
                    if (lineItemNumber != null) {
                        for (BudgetLineItem budgetLineItem : lineItemNumber) {
                            if (propNextValue.intValue() == budgetLineItem.getLineItemNumber().intValue()) {
                                propNextValue++;
                            }
                        }
                    }
                }
                documentNextvalue.setNextValue(propNextValue + 1);
            }
        }

        /*****BEGIN BLOCK *****/
        if (propNextValue == 1) {
            BusinessObjectService bos = KcServiceLocator.getService(BusinessObjectService.class);
            Map<String, Object> pkMap = new HashMap<String, Object>();
            pkMap.put("documentKey", getBudget().getBudgetId());
            pkMap.put("propertyName", propertyName);
            DocumentNextvalue documentNextvalue = (DocumentNextvalue) bos.findByPrimaryKey(DocumentNextvalue.class, pkMap);
            if (documentNextvalue != null) {
                propNextValue = documentNextvalue.getNextValue();
                documentNextvalue.setNextValue(propNextValue + 1);
                getDocumentNextvalues().add(documentNextvalue);
            }
        }
        /*****END BLOCK********/
        // property does not exist - set initial value and increment for next call 
        if (propNextValue == 1) {
            DocumentNextvalue documentNextvalue = new DocumentNextvalue();
            documentNextvalue.setNextValue(propNextValue + 1);
            documentNextvalue.setPropertyName(propertyName);
            documentNextvalue.setDocumentKey(getDocumentNumber());
            getDocumentNextvalues().add(documentNextvalue);
        }
        setDocumentNextvalues(getDocumentNextvalues());
        return propNextValue;
    }

    @Override
    public void prepareForSave() {
        super.prepareForSave();
        getParentDocument().saveBudgetFinalVersionStatus(this);
        if (this.getParentDocument() != null) {
            if (this.getParentDocument().getBudgetDocumentVersions() != null) {
                this.updateDocumentDescriptions(this.getParentDocument().getBudgetDocumentVersions());
            }
        } else {
            this.refreshReferenceObject("parentDocument");
        }
        if (ObjectUtils.isNull(this.getVersionNumber())) {
            this.setVersionNumber(new Long(0));
        }
        this.getBudget().getRateClassTypes();
        this.getBudget().handlePeriodToProjectIncomeRelationship();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List buildListOfDeletionAwareLists() {
        List managedLists = super.buildListOfDeletionAwareLists();
        managedLists.addAll(getBudget().buildListOfDeletionAwareLists());
        return managedLists;
    }

    /**
     * Gets the parentDocument attribute. 
     * @return Returns the parentDocument.
     */
    public BudgetParentDocument<T> getParentDocument() {
        if (parentDocument == null) {
            if (StringUtils.isNotBlank(parentDocumentKey)) {
                try {
                    parentDocument = (BudgetParentDocument<T>) getDocumentService().getByDocumentHeaderId(parentDocumentKey);
                } catch (WorkflowException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return parentDocument;
    }

    /**
     * Sets the parentDocument attribute value.
     * @param parentDocument The parentDocument to set.
     */
    public void setParentDocument(BudgetParentDocument<T> parentDocument) {
        this.parentDocument = parentDocument;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Budget getBudget() {
        if(budget == null && !isBudgetDeleted()){
            budget = new ProposalDevelopmentBudgetExt();
        } else if (isBudgetDeleted()) {
            return new ProposalDevelopmentBudgetExt();
        }
        return budget;
    }
    @Override
    public String getDocumentTypeCode() {
        return DOCUMENT_TYPE_CODE;
    }

    /**
     * Gets the parentDocumentKey attribute. 
     * @return Returns the parentDocumentKey.
     */
    public String getParentDocumentKey() {
        return parentDocumentKey;
    }

    /**
     * Sets the parentDocumentKey attribute value.
     * @param parentDocumentNumber The parentDocumentKey to set.
     */
    public void setParentDocumentKey(String parentDocumentNumber) {
        this.parentDocumentKey = parentDocumentNumber;
    }

    /**
     * Gets the parentDocumentTypeCode attribute. 
     * @return Returns the parentDocumentTypeCode.
     */
    public String getParentDocumentTypeCode() {
        return parentDocumentTypeCode;
    }

    /**
     * Sets the parentDocumentTypeCode attribute value.
     * @param parentDocumentTypeCode The parentDocumentTypeCode to set.
     */
    public void setParentDocumentTypeCode(String parentDocumentTypeCode) {
        this.parentDocumentTypeCode = parentDocumentTypeCode;
    }

    @Override
    public String getCustomLockDescriptor(Person user) {
        String activeLockRegion = (String) GlobalVariables.getUserSession().retrieveObject(KraAuthorizationConstants.ACTIVE_LOCK_REGION);
        if (StringUtils.isNotEmpty(activeLockRegion)) {
            BudgetParentDocument parent = this.getParentDocument();
            if (parent != null) {
                return getDocumentBoNumber() + "-" + activeLockRegion + "-" + activeLockRegion + "-" + GlobalVariables.getUserSession().getPrincipalName();
            }
            return getDocumentBoNumber() + "-" + activeLockRegion + "-" + activeLockRegion + "-" + GlobalVariables.getUserSession().getPrincipalName();
        }
        return null;
    }

    public String getDocumentBoNumber() {
        return getBudget().getBudgetId().toString();
    }

    public String getDocumentKey() {
        return getParentDocument().getBudgetPermissionable().getDocumentKey();
    }

    public String getDocumentNumberForPermission() {
        return getDocumentNumber();
    }

    public List<String> getRoleNames() {
        return getParentDocument().getBudgetPermissionable().getRoleNames();
    }

    public String getNamespace() {
        return Constants.MODULE_NAMESPACE_BUDGET;
    }

    public String getLeadUnitNumber() {
        return getParentDocument().getBudgetPermissionable().getLeadUnitNumber();
    }

    public String getDocumentRoleTypeCode() {
        return RoleConstants.PROPOSAL_ROLE_TYPE;
    }

    public String getProposalBudgetFlag() {
        return getParentDocument().getProposalBudgetFlag();
    }

    @Override
    public List<String> getLockClearningMethodNames() {
        List<String> methodToCalls = super.getLockClearningMethodNames();
        methodToCalls.add("returnToProposal");
        methodToCalls.add("returnToAward");
        return methodToCalls;
    }

    public void documentHasBeenRejected(String reason) {
    }

    public boolean isBudgetDeleted() {
        return budgetDeleted;
    }

    public void setBudgetDeleted(boolean budgetDeleted) {
        this.budgetDeleted = budgetDeleted;
    }

    public boolean isProcessComplete() {
        return true;
    }

    public java.util.Date getBudgetStartDate() {
        if (StringUtils.equalsIgnoreCase("true", getProposalBudgetFlag())) {
            ProposalDevelopmentDocument pdd = (ProposalDevelopmentDocument) getParentDocument();
            return pdd.getDevelopmentProposal().getRequestedStartDateInitial();
        } else {
            AwardDocument ad = (AwardDocument) getParentDocument();
            return ad.getAward().getAwardAmountInfos().get(ad.getAward().getAwardAmountInfos().size() - 1).getCurrentFundEffectiveDate();
        }
    }

    public java.util.Date getBudgetEndDate() {
        if (StringUtils.equalsIgnoreCase("true", getProposalBudgetFlag())) {
            ProposalDevelopmentDocument pdd = (ProposalDevelopmentDocument) getParentDocument();
            return pdd.getDevelopmentProposal().getRequestedEndDateInitial();
        } else {
            AwardDocument ad = (AwardDocument) getParentDocument();
            return ad.getAward().getAwardAmountInfos().get(ad.getAward().getAwardAmountInfos().size() - 1).getObligationExpirationDate();
        }
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return new ArrayList();
    }

    @Override
    public void populateContextQualifiers(Map<String, String> qualifiers) {
        qualifiers.put("namespaceCode", Constants.MODULE_NAMESPACE_BUDGET);
        qualifiers.put("name", KcKrmsConstants.PropDevBudget.BUDGET_CONTEXT);
    }

    @Override
    public void addFacts(Builder factsBuilder) {
        KcKrmsFactBuilderServiceHelper fbService = KcServiceLocator.getService("propDevBudgetFactBuilderService");
        fbService.addFacts(factsBuilder, this);
    }

    @Override
    public void populateAgendaQualifiers(Map<String, String> qualifiers) {
        qualifiers.put(KcKrmsConstants.UNIT_NUMBER, getLeadUnitNumber());
    }

    protected DocumentService getDocumentService() {
        if (documentService == null) {
            documentService = KcServiceLocator.getService(DocumentService.class);
        }

        return documentService;
    }
}
