package org.kuali.coeus.s2sgen.api.core;

/**
 * Configuration constants used by the s2s subsystem.
 */
public final class ConfigurationConstants {

    //referenced from other modules
    public static final String IRB_PROTOCOL_DEVELOPMENT_PROPOSAL_LINKING_ENABLED = "irb.protocol.development.proposal.linking.enabled";
    public static final String IACUC_PROTOCOL_PROPOSAL_DEVELOPMENT_LINKING_ENABLED_PARAMETER = "iacuc.protocol.proposal.development.linking.enabled";
    public static final String DHHS_AGREEMENT = "DHHS_AGREEMENT";
    public static final String PROPOSAL_TYPE_CODE_NEW = "PROPOSAL_TYPE_CODE_NEW";
    public static final String PROPOSAL_TYPE_CODE_RESUBMISSION = "PROPOSAL_TYPE_CODE_RESUBMISSION";
    public static final String PROPOSAL_TYPE_CODE_RENEWAL = "PROPOSAL_TYPE_CODE_RENEWAL";
    public static final String PROPOSAL_TYPE_CODE_CONTINUATION = "PROPOSAL_TYPE_CODE_CONTINUATION";
    public static final String PROPOSAL_TYPE_CODE_REVISION = "PROPOSAL_TYPE_CODE_REVISION";
    public static final String ACTIVITY_TYPE_CODE_CONSTRUCTION = "ACTIVITY_TYPE_CODE_CONSTRUCTION";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_NEW = "proposaldevelopment.proposaltype.new";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_RENEWAL = "proposaldevelopment.proposaltype.renewal";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_REVISION = "proposaldevelopment.proposaltype.revision";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_CONTINUATION = "proposaldevelopment.proposaltype.continuation";
    public static final String PROPOSALDEVELOPMENT_PROPOSALTYPE_RESUBMISSION = "proposaldevelopment.proposaltype.resubmission";
    public static final String MULTI_CAMPUS_ENABLED = "MULTI_CAMPUS_ENABLED";

    //s2s specific
    public static final String TUITION_COST_ELEMENTS = "TUITION_COST_ELEMENTS";
    public static final String STIPEND_COST_ELEMENTS = "STIPEND_COST_ELEMENTS";
    public static final String S2S_SUBMISSION_TYPE_CODE_PREAPPLICATION = "S2S_SUBMISSION_TYPE_CODE_PREAPPLICATION";
    public static final String TUITION_POSTDOC_DEG_COST_ELEMENTS = "TUITION_POSTDOC_DEG_COST_ELEMENTS";
    public static final String TUITION_OTHER_COST_ELEMENTS = "TUITION_OTHER_COST_ELEMENTS";
    public static final String TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS = "TUITION_PREDOC_SINGLE_DEG_COST_ELEMENTS";
    public static final String TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS = "TUITION_PREDOC_DUAL_DEG_COST_ELEMENTS";
    public static final String TUITION_UNDERGRAD_COST_ELEMENTS = "TUITION_UNDERGRAD_COST_ELEMENTS";
    public static final String TUITION_POSTDOC_NONDEG_COST_ELEMENTS = "TUITION_POSTDOC_NONDEG_COST_ELEMENTS";
    public static final String SUBCONTRACT_COST_ELEMENTS = "SUBCONTRACT_COST_ELEMENTS";
    public static final String TRAINING_REL_COST_ELEMENTS = "TRAINING_REL_COST_ELEMENTS";
    public static final String TRAINEE_TRAVEL_COST_ELEMENTS = "TRAINEE_TRAVEL_COST_ELEMENTS";
    public static final String PROPOSAL_CONTACT_TYPE = "PROPOSAL_CONTACT_TYPE";
    public static final String S2SBUDGET_BUDGET_CATEGORY_TYPE_PERSONNEL = "s2sBudgetBudgetCategoryTypePersonnel";
    public static final String S2SBUDGET_FILTER_CATEGORY_TYPE_PERSONNEL = "s2sBudgetFilterCategoryTypePersonnel";
    public static final String S2SBUDGET_RATE_CLASS_TYPE_SALARIES_MS = "s2sBudgetRateClassTypeSalariesMs";
    public static final String S2SBUDGET_RATE_CLASS_TYPE_LAB_ALLOCATION_SALARIES = "s2sBudgetRateClassTypeLabAllocationSalaries";
    public static final String S2SBUDGET_RATE_CLASS_TYPE_EMPLOYEE_BENEFITS = "s2sBudgetRateClassTypeEmployeeBenefits";
    public static final String S2SBUDGET_RATE_CLASS_TYPE_VACATION = "s2sBudgetRateClassTypeVacation";
    public static final String S2SBUDGET_RATE_TYPE_ADMINISTRATIVE_SALARIES = "s2sBudgetRateTypeAdministrativeSalaries";
    public static final String S2SBUDGET_RATE_TYPE_SUPPORT_STAFF_SALARIES = "s2sBudgetRateTypeSupportStaffSalaries";
    public static final String S2SBUDGET_RATE_CLASS_CODE_EMPLOYEE_BENEFITS = "s2sBudgetRateClassCodeEmployeeBenefits";
    public static final String S2SBUDGET_RATE_CLASS_CODE_VACATION = "s2sBudgetRateClassCodeVacation";
    public static final String S2SBUDGET_PERIOD_TYPE_ACADEMIC_MONTHS = "s2sBudgetPeriodTypeAcademicMonths";
    public static final String S2SBUDGET_PERIOD_TYPE_CALENDAR_MONTHS = "s2sBudgetPeriodTypeCalendarMonths";
    public static final String S2SBUDGET_PERIOD_TYPE_SUMMER_MONTHS = "s2sBudgetPeriodTypeSummerMonths";
    public static final String S2SBUDGET_PERIOD_TYPE_CYCLE_MONTHS = "s2sBudgetPeriodTypeCycleMonths";
    public static final String S2SBUDGET_TARGET_CATEGORY_CODE_EQUIPMENT_COST = "s2sBudgetTargetCategoryCodeEquipmentCost";
    public static final String S2SBUDGET_BUDGET_CATEGORY_CODE_PERSONNEL = "s2sBudgetCategoryCodePersonnel";
    public static final String S2SBUDGET_CATEGORY_01_GRADUATES = "s2sBudgetCategory01Graduates";
    public static final String S2SBUDGET_CATEGORY_01_POSTDOCS = "s2sBudgetCategory01Postdocs";
    public static final String S2SBUDGET_CATEGORY_01_UNDERGRADS = "s2sBudgetCategory01Undergrads";
    public static final String S2SBUDGET_CATEGORY_01_SECRETARIAL = "s2sBudgetCategory01Secretarial";
    public static final String S2SBUDGET_CATEGORY_01_OTHER = "s2sBudgetCategory01Other";
    public static final String S2SBUDGET_CATEGORY_01_OTHER_PROFS = "s2sBudgetCategory01OtherProfs";
    public static final String S2SBUDGET_MATERIALS_AND_SUPPLIES_CATEGORY = "s2sBudgetMaterialsAndSuppliesCategory";
    public static final String S2SBUDGET_CONSULTANT_COSTS_CATEGORY = "s2sBudgetConsultantCostsCategory";
    public static final String S2SBUDGET_PUBLICATION_COSTS_CATEGORY = "s2sBudgetPublicationCostsCategory";
    public static final String S2SBUDGET_COMPUTER_SERVICES_CATEGORY = "s2sBudgetComputerServicesCategory";
    public static final String S2SBUDGET_ALTERATIONS_CATEGORY = "s2sBudgetAlterationsCategory";
    public static final String S2SBUDGET_SUBCONTRACT_CATEGORY = "s2sBudgetSubcontractCategory";
    public static final String S2SBUDGET_EQUIPMENT_RENTAL_CATEGORY = "s2sBudgetEquipmentRentalCategory";
    public static final String S2SBUDGET_DOMESTIC_TRAVEL_CATEGORY = "s2sBudgetDomesticTravelCategory";
    public static final String S2SBUDGET_FOREIGN_TRAVEL_CATEGORY = "s2sBudgetForeignTravelCategory";
    public static final String S2SBUDGET_PARTICIPANT_STIPENDS_CATEGORY = "s2sBudgetParticipantStipendsCategory";
    public static final String S2SBUDGET_PARTICIPANT_TRAVEL_CATEGORY = "s2sBudgetParticipantTravelCategory";
    public static final String S2SBUDGET_PARTICIPANT_TUITION_CATEGORY = "s2sBudgetParticipantTutionCategory";
    public static final String S2SBUDGET_PARTICIPANT_SUBSISTENCE_CATEGORY = "s2sBudgetParticipantSubsistenceCategory";
    public static final String S2SBUDGET_PARTICIPANT_OTHER_CATEGORY = "s2sBudgetParticipantOtherCategory";
    public static final String S2SBUDGET_OTHER_DIRECT_COSTS_CATEGORY = "s2sBudgetOtherDirectCostsCategory";
    public static final String S2SBUDGET_APPOINTMENT_TYPE_SUM_EMPLOYEE = "s2sBudgetAppointmentTypeSumEmployee";
    public static final String S2SBUDGET_APPOINTMENT_TYPE_TMP_EMPLOYEE = "s2sBudgetAppointmentTypeTmpEmployee";
    public static final String BUDGET_CATEGORY_TYPE_PARTICIPANT_SUPPORT = "budgetCategoryType.participantSupport";
    public static final String PI_CUSTOM_DATA = "PI_CITIZENSHIP_FROM_CUSTOM_DATA";
    public static final String PERMANENT_RESIDENT_OF_US_TYPE_CODE = "PERMANENT_RESIDENT_OF_US_TYPE_CODE";
    public static final String ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES = "ALLOW_PROPOSAL_PERSON_TO_OVERRIDE_KC_PERSON_EXTENDED_ATTRIBUTES";
    public static final String NON_US_CITIZEN_WITH_TEMPORARY_VISA_TYPE_CODE = "NON_US_CITIZEN_WITH_TEMPORARY_VISA_TYPE_CODE";
    public static final String US_CITIZEN_OR_NONCITIZEN_NATIONAL_TYPE_CODE = "US_CITIZEN_OR_NONCITIZEN_NATIONAL_TYPE_CODE";
    public static final String PERMANENT_RESIDENT_OF_US_PENDING = "PERMANENT_RESIDENT_OF_US_PENDING";

    //printing
    public static final String PRINT_XML_DIRECTORY = "print.xml.directory";
    public static final String PRINT_LOGGING_ENABLE = "print.logging.enable";
    public static final String PRINT_LOGGING_DIRECTORY = "print.logging.directory";
    public static final String APPLICATION_URL_KEY = "application.url";

    private ConfigurationConstants() {
        throw new UnsupportedOperationException("do not call");
    }
}

