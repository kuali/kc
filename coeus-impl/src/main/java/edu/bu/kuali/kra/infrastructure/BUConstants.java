/*
 * Copyright (c) 2014. Boston University
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND either express or
 * implied.
 *
 * See the License for the specific language governing permissions and limitations under the License.
 */

package edu.bu.kuali.kra.infrastructure;

/**
 * Constant mapping for Bu Customizations
 */
public class BUConstants {

    // BUKC-0014: KC/SAP Interface -  KC/SAP Transmission mapping
    public static final String FEDERAL_RATE_DATE_OVERHEAD_KEY_FIELD_MAPPINGS = "BU_FEDERAL_RATE_DATE_OVERHEAD_KEY_FIELD_MAPPINGS";
    public static final String REPORT_CODE_TO_INVOICE_FORM_MAPPING = "BU_REPORT_CODE_TO_INVOICE_FORM_MAPPINGS";
    public static final String CHILD_TYPE_MAPPING = "BU_CHILD_TYPE_MAPPINGS";
    public static final String ACTIVITY_TYPE_MAPPING = "BU_ACTIVITY_TYPE_MAPPINGS";
    public static final String BASIS_OF_PAYMENT_MAPPING = "BU_BASIS_OF_PAYMENT_MAPPING";
    public static final String METHOD_OF_PAYMENT_MAPPING = "BU_METHOD_OF_PAYMENT_MAPPING";
    public static final String PROJECT_ROLE_MAPPING = "BU_PROJECT_ROLE_MAPPING";
    public static final String STATUS_CODE_MAPPING = "BU_STATUS_CODE_MAPPING";
    public static final String SPONSOR_CODE_MAPPING = "BU_SPONSOR_CODE_MAPPING";
    public static final String SPONSOR_TERM_MAPPING = "BU_SPONSOR_TERM_MAPPING";
    public static final String ACCOUNT_TYPE_MAPPING = "BU_ACCOUNT_TYPE_MAPPING";
    public static final String AWARD_TYPE_MAPPING = "BU_AWARD_TYPE_MAPPING";
    public static final String LEAD_UNIT_MAPPING = "BU_LEAD_UNIT_MAPPING";

    // BUKC-0016: Budget Unrecovered and Overhead F&A Rate Type validation
    public static final String ERROR_BUDGET_NO_OH_RATE_CLASS = "error.budgetVersion.ohRateClassCode.required";
    public static final String ERROR_BUDGET_NO_UR_RATE_CLASS = "error.budgetVersion.urRateClassCode.required";

    // BUKC-0021: Adding transactions is not allowed when transaction type is No Cost Extension or Administrative Changes
    public static final String PARENT_TRANSACTION_TYPE_NOCOST_EXT_OR_ADMIN_CHANGE = "error.timeandmoney.parentAward.transactionType.noCostExt_Or_administrativeChange";
    public static final int AWARD_TRANSACTION_TYPE_NO_COST_EXTENSION = 6;
    public static final int AWARD_TRANSACTION_TYPE_ADMINISTRATION_CHANGE = 10;

    // BUKC-0022: Add BU's Award Notification
    public static final String ERROR_BUAWARDPRINT_CHECKBOX_SELECTION_REQUIRED = "error.buawardprint.checkbox.selection.required";
    public static final String AWARD_NOTIFICATION_SUPRESS_TERM_ID = "BU_AWARD_NOTIFICATION_SUPRESS_TERM_ID";
    public static final String AWARD_NOTIFICATION_SUPRESS_TERM_CD = "BU_AWARD_NOTIFICATION_SUPRESS_TERM_CD";
    public static final String AWARD_NOTIFICATION_SUPRESS_TERM_TYPE_CD = "BU_AWARD_NOTIFICATION_SUPRESS_TERM_TYPE_CD";
    public static final String OSP_ADMINISTRATOR_TYPE_CODE = "3";
    public static final String PAFO_ADMINISTRATOR_TYPE_CODE = "4";
    public static final String CTAD_ADMINISTRATOR_TYPE_CODE = "8";

    // BUKC-0023: Add History tab
    public static final String MAPPING_AWARD_HISTORY_PAGE = "history";

    // BUKC-0024 Direct/F&A Funds Distribution panel validation
    public static final String ERROR_AWARD_FANDA_DISTRIB_REQUIRED_PARENT_AWARD = "error.awardDirectFandADistribution.required.parent.award";
    public static final String ERROR_AWARD_FANDA_DISTRIB_NOT_ALLOWED_CHILD_AWARD = "error.awardDirectFandADistribution.not.allowed.child.award";

    // BUKC-0041: Parameterize LOC (Line of Credit) method of payment to accommodate the new LOC codes added (Issue 62)
    public static final String BU_LOC_METHOD_OF_PAYMENT_CD = "BU_LOC_METHOD_OF_PAYMENT_CD";

    // BUKC-0053: Enforce validation on direct and F&A costs when broken out (QA issue 42 and JIRA KRAFDBCK-9911)
    public static final String ERROR_OBLIGATED_DIRECT_AMOUNT_INVALID = "error.obligated.direct.amount.invalid";
    public static final String ERROR_OBLIGATED_INDIRECT_AMOUNT_INVALID = "error.obligated.indirect.amount.invalid";
    public static final String ERROR_ANTICIPATED_DIRECT_AMOUNT_INVALID = "error.anticipated.direct.amount.invalid";
    public static final String ERROR_ANTICIPATED_INDIRECT_AMOUNT_INVALID = "error.anticipated.indirect.amount.invalid";
	
    // BUKC-0143: Validation for - Subaward Financial - Amendment No. field (ENHC0013244)
    public static final String ERROR_SUBAWARD_MODIFICATION_ID_DUPLICATE = "error.modification.number.codeAlreadyUsed";
    public static final String ERROR_SUBAWARD_MODIFICATION_ID_REQUIRED = "error.modification.number.empty";
    public static final String ERROR_SUBAWARD_MODIFICATION_ID_INVALID = "error.modification.number.invalid";

    // BUKC-0146: Add Subaward Custom Fields - R&D and Cost Sharing (ENHC0013365 and ENHC0013357)
    public static final int FCOI_CUSTOM_DATA_ID = 125;
	public static final int FFATA_CUSTOM_DATA_ID = 120;
    public static final int RD_CUSTOM_DATA_ID = 130;
	public static final int COSTSHARE_CUSTOM_DATA_ID = 140;

	// BUKC-0154: Negotiation - Required fields for Association = None  (Neg. Enhancements 4)
    public static final String NEGOTIATION_ERROR_PI_REQUIRED= "error.negotiation.pi.required";

    // BUKC-0156: Negotiation - sufficient message when Activity Start/End Date are outside the bounds of the Negotiation Start/End Date (Neg. QA Issue 19)
    public static final String NEGOTIATION_ACTIVITY_START_AFTER_NEGOTIATION_END = "negotiation.error.activity.start.after.negotiation.end";

    // BUKC-0162: Negotiation - Add validation to prevent user from putting status to "Complete" if activites are still open (Neg. Enhancements #6)
    public static final String NEGOTIATION_STATUS_CANNOT_BE_CLOSED_IF_ACTIVITY_IS_OPEN = "error.negotiation.activity.cannot.be.closed.if.activity.is.open";
    public static final String ACTIVITY_END_DATE_REQUIRED_WHEN_STATUS_COMPLETE="error.negotiation.activity.end.date.required.when.status.is.complete";

}
