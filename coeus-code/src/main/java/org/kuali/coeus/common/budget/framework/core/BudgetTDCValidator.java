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
package org.kuali.coeus.common.budget.framework.core;

import org.kuali.coeus.common.budget.framework.core.BudgetParentDocument;
import org.kuali.coeus.propdev.impl.budget.modular.BudgetModularTotalDirectCostRule;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * This class contains methods to validate the BudgetModular Total Direct Cost rule.
 */
public class BudgetTDCValidator {

    public static final String TDC_WARNINGS_ATTRIBUTE_NAME = "budgetModularWarnings";

    private final HttpServletRequest request;

    /**
     * Sets a request used to set warnings into.
     *
     * @param request the current request
     */
    public BudgetTDCValidator(final HttpServletRequest request) {

        if (request == null) {
            throw new NullPointerException("the request is null.");
        }

        this.request = request;
    }

    /**
     * Calls the tdc rule generating errors and warnings.  Errors generated will be placed
     * in the {@link org.kuali.rice.krad.util.GlobalVariables.GlobalVariables#getErrorMap() MessageMap}
     * while warnings will be added as a request attribute named
     * {@link TDC_WARNINGS_ATTRIBUTE_NAME TDC_WARNINGS_ATTRIBUTE_NAME}.
     *
     * <p>
     * <b>Make sure to set the proper path in the error map before calling this method.</b>
     * </p>
     * @param proposalDocument the proposal document to validate.
     * @throws NullPointerException if the document is null.
     */
    public void validateGeneratingErrorsAndWarnings(final BudgetParentDocument proposalDocument) {

        if (proposalDocument == null) {
            throw new NullPointerException("the request is null.");
        }

        final BudgetModularTotalDirectCostRule tdcRule
        = new BudgetModularTotalDirectCostRule();

        final Set<String> warnings = new HashSet<String>();

        final boolean passedWithoutErrors
        = tdcRule.validateTotalDirectCost(proposalDocument, true, warnings);

        if (passedWithoutErrors) {
            this.request.setAttribute(TDC_WARNINGS_ATTRIBUTE_NAME, warnings);
        }
    }

    /**
     * Calls the tdc rule generating errors and warnings.  Warnings will be added as a request
     * attribute named {@link TDC_WARNINGS_ATTRIBUTE_NAME TDC_WARNINGS_ATTRIBUTE_NAME}.
     *
     * @param proposalDocument the proposal document to validate.
     * @throws NullPointerException if the document is null.
     */
    public void validateGeneratingWarnings(final BudgetParentDocument proposalDocument) {

        if (proposalDocument == null) {
            throw new NullPointerException("the request is null.");
        }

        final BudgetModularTotalDirectCostRule tdcRule = new BudgetModularTotalDirectCostRule();

        final Set<String> warnings = new HashSet<String>();

        tdcRule.validateTotalDirectCost(proposalDocument, false, warnings);
        this.request.setAttribute(TDC_WARNINGS_ATTRIBUTE_NAME, warnings);
    }
}
