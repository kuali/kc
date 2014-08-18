package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.common.budget.framework.version.BudgetDocumentVersion;
import org.kuali.coeus.common.budget.framework.version.BudgetVersionOverview;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.service.DictionaryValidationService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.MessageMap;

public class BudgetParentDocumentRule extends KcTransactionalDocumentRuleBase {

    private ParameterService parameterService;
    private DictionaryValidationService dictionaryValidationService;
    /**
     *
     * This method checks budget versions business rules.
     *
     * @param budgetParentDocument the document
     * @param runDatactionaryValidation if dd validation should be run
     * @return true if valid false if not
     * @throws NullPointerException if the proposalDevelopmentDocument is null
     */
    protected boolean processBudgetVersionsBusinessRule(
            final BudgetParentDocument budgetParentDocument,
            final boolean runDatactionaryValidation) {
        if (budgetParentDocument == null) {
            throw new NullPointerException("the parentDocument is null.");
        }

        boolean valid = true;
        final MessageMap errorMap = GlobalVariables.getMessageMap();
        boolean finalVersionFound = false;

        final DictionaryValidationService dictionaryValidationService = getKnsDictionaryValidationService();

        int index = 0;
        for (BudgetDocumentVersion budgetDocumentVersion: budgetParentDocument.getBudgetDocumentVersions()) {
            BudgetVersionOverview budgetVersion = budgetDocumentVersion.getBudgetVersionOverview();
            if (runDatactionaryValidation) {
                dictionaryValidationService.validateBusinessObject(budgetVersion, true);
            }
            if (budgetVersion.isFinalVersionFlag()) {
                if (finalVersionFound) {
                    errorMap.putError("finalVersionFlag", KeyConstants.ERROR_MULTIPLE_FINAL_BUDGETS);
                } else {
                    finalVersionFound = true;
                }
            }

            final String budgetStatusCompleteCode = getParameterService().getParameterValueAsString(BudgetDocument.class, Constants.BUDGET_STATUS_COMPLETE_CODE);

            if (budgetStatusCompleteCode.equalsIgnoreCase(budgetVersion.getBudgetStatus())) {
                if (!budgetVersion.isFinalVersionFlag()) {
                    errorMap.putError("budgetVersionOverview[" + index + "].budgetStatus",
                            KeyConstants.ERROR_NO_FINAL_BUDGET);
                    valid = false;
                }
            }
            index++;
        }

        return valid;
    }

    protected DictionaryValidationService getKnsDictionaryValidationService() {
        if (this.dictionaryValidationService == null) {
            this.dictionaryValidationService = KNSServiceLocator.getKNSDictionaryValidationService();
        }
        return this.dictionaryValidationService;
    }

    protected ParameterService getParameterService() {
        if (this.parameterService == null) {
            this.parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return this.parameterService;
    }
}
