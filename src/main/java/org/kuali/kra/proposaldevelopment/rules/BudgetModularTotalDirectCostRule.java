/*
 * Copyright 2006-2009 The Kuali Foundation
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

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.kuali.core.service.DocumentService;
import org.kuali.core.service.KualiConfigurationService;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.budget.BudgetDecimal;
import org.kuali.kra.budget.bo.BudgetModular;
import org.kuali.kra.budget.bo.BudgetPeriod;
import org.kuali.kra.budget.bo.BudgetVersionOverview;
import org.kuali.kra.budget.document.BudgetDocument;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.rice.KNSServiceLocator;

import edu.iu.uis.eden.exception.WorkflowException;

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
 * {@link GlobalVariables#getErrorMap() GlobalVariables.getErrorMap()}
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
    private final KualiConfigurationService configService;
    private final String budgetStatusCompleteCode;
    private final String tdcWarning;

    public BudgetModularTotalDirectCostRule() {
        this(KNSServiceLocator.getKualiConfigurationService(), KraServiceLocator.getService(DocumentService.class));
    }


    /**
     * Sets the services that this rule uses.  This constructor is provided for easier unit testing.
     *
     * @param configService the config service
     * @param documentService the document service
     * @throws NullPointerException if the configService or documentService service is null
     */
    BudgetModularTotalDirectCostRule(final KualiConfigurationService configService,
        final DocumentService documentService) {

        if (configService == null) {
            throw new NullPointerException("the configService is null");
        }

        if (documentService == null) {
            throw new NullPointerException("the documentService is null");
        }

        this.documentService = documentService;
        this.configService = configService;

        this.budgetStatusCompleteCode = this.configService.getParameterValue(
            Constants.PARAMETER_MODULE_BUDGET,
            Constants.PARAMETER_COMPONENT_DOCUMENT,
            Constants.BUDGET_STATUS_COMPLETE_CODE);

        this.tdcWarning = this.configService.getPropertyString(
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
     * @param pdDocument the document to check rule against
     * @param reportErrors whether to report errors
     * @param warningMessages container to place warning messages.  Warning messages
     * are added to this set to be accessed by the caller.
     *
     * @throws NullPointerException if the pdDocument or warningMessages are null.
     */
    public boolean validateTotalDirectCost(final ProposalDevelopmentDocument pdDocument,
        final boolean reportErrors, Set<String> warningMessages) {

        if (pdDocument == null) {
            throw new NullPointerException("the document is null");
        }

        if (warningMessages == null) {
            throw new NullPointerException("the warningMessages is null");
        }

        boolean passed = true;

        final List<BudgetVersionOverview> budgetOverviews
        = pdDocument.getBudgetVersionOverviews();

        for (int i = 0; i < budgetOverviews.size(); i++) {
            final BudgetVersionOverview budgetOverview
            = budgetOverviews.get(i);

            if (this.budgetStatusCompleteCode.equalsIgnoreCase(
                budgetOverview.getBudgetStatus())) {

                final BudgetDocument budgetDocument = this.getBudgetDocument(
                    budgetOverview.getDocumentNumber());
                passed &= this.checkTotalDirectCost(budgetDocument, i, reportErrors, warningMessages);
            }
        }
        return passed;
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

        if (Boolean.FALSE.equals(budgetDocument.getModularBudgetFlag())) {
            return true;
        }

        final Collection<BudgetPeriod> budgetPeriods = budgetDocument.getBudgetPeriods();

        if (budgetPeriods != null) {

            int positiveCount = -1;

            for (final BudgetPeriod budgetPeriod : budgetPeriods) {
                if (budgetPeriod != null) {
                    final BudgetModular budgetModular = budgetPeriod.getBudgetModular();
                    positiveCount = (positiveCount != -1) ? positiveCount : 0;
                    
                    if (budgetModular != null) {
                        final BudgetDecimal tdc = budgetModular.getTotalDirectCost();
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
                GlobalVariables.getErrorMap().putError("budgetVersionOverview[" + currentIndex + "].budgetStatus",
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
            //FIXME find an appropriate RuntimeException to wrap this condition in
            throw new RuntimeException("Error getting document by header id, document number [" + docNumber + "]", e);
        }
    }
}
