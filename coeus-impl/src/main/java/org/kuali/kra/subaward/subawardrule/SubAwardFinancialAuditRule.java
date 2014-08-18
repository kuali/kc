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
package org.kuali.kra.subaward.subawardrule;

import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.subaward.bo.SubAwardAmountInfo;
import org.kuali.kra.subaward.document.SubAwardDocument;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.rice.kns.util.AuditCluster;
import org.kuali.rice.kns.util.AuditError;
import org.kuali.rice.kns.util.KNSGlobalVariables;
import org.kuali.rice.krad.document.Document;
import org.kuali.rice.krad.rules.rule.DocumentAuditRule;

import java.util.ArrayList;
import java.util.List;

/**
 * This class processes audit rules (warnings)
 *  for the Report Information related
 * data of the SubAwardDocument.
 */
public class SubAwardFinancialAuditRule extends
        KcTransactionalDocumentRuleBase implements DocumentAuditRule {

    private static final String SUBAWARD_FINANCIAL_AUDIT_WARNINGS
    = "subawardFinancialdAuditWarnings";
    private List<AuditError> auditWarnings;


    /**
     *
     * Constructs a SubAwardFinancialAuditRule.java.
     *  Added so unit test would not
     * need to call processRunAuditBusinessRules
     * and therefore declare a document.
     */
    public SubAwardFinancialAuditRule() {
        auditWarnings = new ArrayList<AuditError>();
    }

    /**
     * @see org.kuali.core.rule.DocumentAuditRule
     * #processRunAuditBusinessRules(org.kuali.core.document.Document)
     * @return boolean
     */
    public boolean processRunAuditBusinessRules(Document document) {
        boolean valid = true;
        auditWarnings = new ArrayList<AuditError>();

        valid &= checkForObligatedAmountZero(document);
        valid &= checkForAnticipatedAmountZero(document);

        reportAndCreateFinancialAuditCluster();

        return valid;

    }

    /**
     * This method creates and adds the
     * AuditCluster to the Global AuditErrorMap.
     */
    @SuppressWarnings("unchecked")
    protected void reportAndCreateFinancialAuditCluster() {
        if (auditWarnings.size() > 0) {
            AuditCluster existingErrors = (AuditCluster) KNSGlobalVariables.
            getAuditErrorMap().get(SUBAWARD_FINANCIAL_AUDIT_WARNINGS);
            if (existingErrors == null) {
                KNSGlobalVariables.getAuditErrorMap().put(
                SUBAWARD_FINANCIAL_AUDIT_WARNINGS, new AuditCluster(Constants.
                SUBAWARD_FINANCIAL_PANEL_NAME, auditWarnings,
                Constants.AUDIT_WARNINGS));
            } else {
                existingErrors.getAuditErrorList().addAll(auditWarnings);
            }
        }
    }
    /**.
     * This method is for checking whether obligated amount is zero
     * @param document
     * @return boolean
     */
    protected boolean checkForObligatedAmountZero(Document document) {
        SubAwardDocument subAwardDocument = (SubAwardDocument) document;
        ScaleTwoDecimal obligatedAmount = ScaleTwoDecimal.ZERO;
        for (SubAwardAmountInfo subAwardAmountInfo
        :subAwardDocument.getSubAward().getSubAwardAmountInfoList()) {
            if (subAwardAmountInfo.getObligatedChange() != null) {
              obligatedAmount = obligatedAmount.
              add(subAwardAmountInfo.getObligatedChange());
            }
        }
        if (obligatedAmount.isZero()) {
            subAwardDocument.getSubAward().setDefaultOpen(false);
            auditWarnings.add(new AuditError(
            Constants.SUBAWARD_FINANCIAL_OBLIGATED_AMOUNT, 
            KeyConstants.ERROR_AMOUNT_INFO_OBLIGATED_AMOUNT_ZERO, 
            Constants.MAPPING_FINANCIAL_PAGE + "." + 
            Constants.SUBAWARD_FINANCIAL_PANEL));
            return false;
        } else {
            return true;
        }
    }

    /**.
     * This method is for checking whether anticipated amount is zero
     * @param document
     * @return boolean
     */
    protected boolean checkForAnticipatedAmountZero(Document document) {
        ScaleTwoDecimal anticipateAmount = ScaleTwoDecimal.ZERO;
        SubAwardDocument subAwardDocument = (SubAwardDocument) document;
        for (SubAwardAmountInfo subAwardAmountInfo
        :subAwardDocument.getSubAward().getSubAwardAmountInfoList()) {
            if (subAwardAmountInfo.getAnticipatedChange() != null) {
             anticipateAmount = anticipateAmount.add(
           subAwardAmountInfo.getAnticipatedChange());
            }
        }
        if (anticipateAmount.isZero()) {
            subAwardDocument.getSubAward().setDefaultOpen(false);
            auditWarnings.add(new AuditError(
            Constants.SUBAWARD_FINANCIAL_ANTICIPATED_AMOUNT,
            KeyConstants.ERROR_AMOUNT_INFO_ANTICIPATED_AMOUNT_ZERO,
            Constants.MAPPING_FINANCIAL_PAGE + "." +
            Constants.SUBAWARD_FINANCIAL_PANEL));

            return false;
        } else {
            return true;
        }
    }
}
