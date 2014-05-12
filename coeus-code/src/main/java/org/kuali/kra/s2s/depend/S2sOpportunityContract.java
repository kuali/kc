package org.kuali.kra.s2s.depend;

import java.util.Calendar;
import java.util.List;

public interface S2sOpportunityContract {

    String getProposalNumber();

    String getCfdaNumber();

    Calendar getClosingDate();

    String getCompetetionId();

    String getInstructionUrl();

    Calendar getOpeningDate();

    String getOpportunity();

    String getRevisionOtherDescription();

    String getSchemaUrl();

    String getOpportunityId();

    String getOpportunityTitle();

    String getFundingOpportunityNumber();

    String getOfferingAgency();

    String getAgencyContactInfo();

    String getCfdaDescription();

    boolean isMultiProject();

    S2sProviderContract getS2sProvider();

    S2sRevisionTypeContract getS2sRevisionType();

    S2sSubmissionTypeContract getS2sSubmissionType();

    List<? extends S2sOppFormsContract> getS2sOppForms();
}
