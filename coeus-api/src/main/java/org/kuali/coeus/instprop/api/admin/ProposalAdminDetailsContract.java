package org.kuali.coeus.instprop.api.admin;

import java.util.Date;

public interface ProposalAdminDetailsContract {

    Date getDateSubmittedByDept();

    Date getDateReturnedToDept();

    Date getDateApprovedByOsp();

    Date getDateSubmittedToAgency();

    Date getInstPropCreateDate();

    String getInstPropCreateUser();

    String getSignedBy();

    boolean getSubmissionType();

    Long getProposalAdminDetailId();

    String getDevProposalNumber();

    Long getInstProposalId();
}
