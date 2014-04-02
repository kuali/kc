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
package org.kuali.kra.proposaldevelopment.rules;

import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.budget.core.Budget;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.budget.document.BudgetParentDocument;
import org.kuali.kra.budget.parameters.BudgetPeriod;
import org.kuali.kra.budget.versions.BudgetDocumentVersion;
import org.kuali.kra.budget.versions.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.proposaldevelopment.budget.modular.BudgetModular;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.coreservice.framework.CoreFrameworkServiceLocator;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kew.api.exception.WorkflowException;
import org.kuali.rice.krad.service.DocumentService;
import org.kuali.rice.krad.service.KRADServiceLocatorWeb;
import org.kuali.rice.krad.util.GlobalVariables;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * This validates the Budget Modular's Total Direct Cost.
 *
 * <p>
 * The validation methods in this class may produce errors and/or warnings.
 * </p>
 *
 * <p>
 * The error handling behavior in this class is important to mention
 * since it is a little different than the rest of KC.
 *
 * This class adds error messages directly to the
 * {@link GlobalVariables#getErrorMap() GlobalVariables.getMessageMap()}
 * Make sure to add to the error map's path before calling the validate method.
 *
 * Currently warning are generated and placed in a {@code Set<String>}.
 * This is different because warnings are not supported by the rice framework
 * and therefore behave differently from errors.
 *
 * See {@link #getErrorMessages() getErrorMessages()}
 * </p>
 */
public final class BudgetModularTotalDirectCostRule {

    private final DocumentService documentService;
    private final ConfigurationService configService;
    private final ParameterService paramService;
    private final String budgetStatusCompleteCode;
    private final String tdcWarning;

    public BudgetModularTotalDirectCostRule() {
        this(CoreApiServiceLocator.getKualiConfigurationService(), KRADServiceLocatorWeb.getDocumentService(),
                CoreFrameworkServiceLocator.getParameterService());
    }


    /**
     * Sets the services that this rule uses.  This constructor is provided for easier unit testing.
     *
     * @param configService the config service
     * @param documentService the document service
     * @throws NullPointerException if the configService or documentService service is null
     */
    BudgetModularTotalDirectCostRule(final ConfigurationService configService,
        final DocumentService documentService, final ParameterService paramService) {

        if (configService == null) {
            throw new NullPointerException("the configService is null");
        }

        if (documentService == null) {
            throw new NullPointerException("the documentService is null");
        }
        
        if (paramService == null) {
            throw new NullPointerException("the paramService is null");
        }

        this.documentService = documentService;
        this.configService = configService;
        this.paramService = paramService;

        this.budgetStatusCompleteCode = this.paramService.getParameterValueAsString(
            BudgetDocument.class,
            Constants.BUDGET_STATUS_COMPLETE_CODE);

        this.tdcWarning = this.configService.getPropertyValueAsString(
            KeyConstants.WARNING_BUDGET_VERSION_MODULAR_INVALID_TDC);
    }

    /**
     * Validates the total direct cost (tdc) for each budget version.
     *
     * <p>
     * This method will validate tdc for every <b>completed</b> budget version
     * that has a <b>modular</b> budget.
     * </p>
     *
     * <p>
     * The tdc rule that this method is checking is whether or not the tdc
     * contains a positive value.  If none of the budget versions meeting
     * the aforementioned criteria is positive than an error is produced.
     * If at least one budget version meeting the aforementioned criteria
     * is positive and at least one is not positive than a warning is produced.
     * </p>
     *
     * @param parentDocument the document to check rule against
     * @param reportErrors whether to report errors
     * @param warningMessages container to place warning messages.  Warning messages
     * are added to this set to be accessed by the caller.
     *
     * @throws NullPointerException if the pdDocument or warningMessages are null.
     */
    public boolean validateTotalDirectCost(final BudgetParentDocument parentDocument,
        final boolean reportErrors, Set<String> warningMessages) {

        if (parentDocument == null) {
            throw new NullPointerException("the document is null");
        }

        if (warningMessages == null) {
            throw new NullPointerException("the warningMessages is null");
        }

        boolean passed = true;

        final List<BudgetDocumentVersion> budgetDocumentOverviews = parentDocument.getBudgetDocumentVersions();

        for (int i = 0; i < budgetDocumentOverviews.size(); i++) {
            final BudgetDocumentVersion budgetDocumentOverview = budgetDocumentOverviews.get(i);
            BudgetVersionOverview budgetOverview = budgetDocumentOverview.getBudgetVersionOverview();

            if (this.budgetStatusCompleteCode.equalsIgnoreCase(
                budgetOverview.getBudgetStatus())) {

                final BudgetDocument budgetDocument = this.getBudgetDocument(
                    budgetOverview.getDocumentNumber());
                updateDocumentBudget(budgetDocument, budgetOverview);
                passed &= this.checkTotalDirectCost(budgetDocument, i, reportErrors, warningMessages);
            }
        }
        return passed;
    }

    /* 
     * The budgetdocument.budget may not have complete data match with version
     */
    private void updateDocumentBudget(BudgetDocument budgetDocument, BudgetVersionOverview version) {
        Budget budget = budgetDocument.getBudget();
        BudgetParentDocument proposal = budgetDocument.getParentDocument();
        
        budget.setFinalVersionFlag(version.isFinalVersionFlag());
        budget.setBudgetStatus(version.getBudgetStatus());
        budget.setModularBudgetFlag(version.getModularBudgetFlag());
    }

    /**
     * Checks the tdc on a {@link BudgetDocument BudgetDocument}
     * following the business rules described at
     * {@link #validateTotalDirectCost(ProposalDevelopmentDocument, boolean, Set) validateTotalDirectCost()}
     *
     * @param budgetDocument the current budget document
     * @param currentIndex the current index corresponding to the document.
     * @param reportErrors whether to report errors
     * @param warningMessages container to place warning messages.
     * @return true if no errors false if errors
     */
    private boolean checkTotalDirectCost(final BudgetDocument budgetDocument,
        final int currentIndex, final boolean reportErrors, Set<String> warningMessages) {

        assert budgetDocument != null : "the budget overview was null";
        assert currentIndex >= 0 : "the current index was not valid, index: " + currentIndex;
        assert warningMessages != null : "the warningMessages is null";

        if (Boolean.FALSE.equals(budgetDocument.getBudget().getModularBudgetFlag())) {
            return true;
        }

        final Collection<BudgetPeriod> budgetPeriods = budgetDocument.getBudget().getBudgetPeriods();

        if (budgetPeriods != null) {

            int positiveCount = -1;

            for (final BudgetPeriod budgetPeriod : budgetPeriods) {
                if (budgetPeriod != null) {
                    final BudgetModular budgetModular = budgetPeriod.getBudgetModular();
                    positiveCount = (positiveCount != -1) ? positiveCount : 0;
                    
                    if (budgetModular != null) {
                        final ScaleTwoDecimal tdc = budgetModular.getTotalDirectCost();
                        if (tdc.isPositive()) {
                            positiveCount++;
                        } else {
                            warningMessages.add(this.tdcWarning);
                        }
                    } else {
                        warningMessages.add(this.tdcWarning);
                    }
                }
            }
            if (positiveCount == 0 && reportErrors) {
                GlobalVariables.getMessageMap().putError("budgetVersionOverview[" + currentIndex + "].budgetStatus",
                    KeyConstants.ERROR_BUDGET_STATUS_COMPLETE_WHEN_NOT_MODULER);
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves a budget document from a document number through the
     * {@link DocumentService DocumentService}.
     *
     * @param docNumber the document number
     *
     * @return the budget document
     * @throws RuntimeException if a problem occurs getting the BudgetDocument
     * FIXME: this throws type should change
     * once an exception hierarchy has been created
     */
    private BudgetDocument getBudgetDocument(final String docNumber) {

        assert docNumber != null : "docNumber is null";
        assert docNumber.trim().length() > 0 : "docNumber whitespace or empty";

        try {
            return (BudgetDocument) this.documentService.getByDocumentHeaderId(docNumber);
        } catch (final WorkflowException e) {
            throw new RuntimeException("Error getting document by header id, document number [" + docNumber + "]", e);
        }
    }
}
