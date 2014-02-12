package org.kuali.kra.budget.web;

import org.kuali.kra.budget.BudgetDecimal;

import java.math.BigDecimal;

public final class JstlFunctions {

    private JstlFunctions() {}

    /**
     * Returns the BigDecimal value wrapped inside the given BudgetDecimal in order to get correct type coercion for Jetty.
     * Here is an example of how the code is used in a JSP/tag file:
     * <code>
     * <c:set var="cumTotalCost" value="${cumTotalCost + krabfn:getBigDecimal(budgetPeriodObj.totalCost)}" />
     * </code>
     *
     * @param budgetDecimal
     * @return
     */
    public static BigDecimal getBigDecimal(BudgetDecimal budgetDecimal) {
        return budgetDecimal.bigDecimalValue();
    }
}
