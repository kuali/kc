package org.kuali.kra.institutionalproposal.service;

import org.kuali.coeus.common.framework.krms.KcKrmsJavaFunctionTermService;
import org.kuali.kra.institutionalproposal.home.InstitutionalProposal;

public interface InstitutionalProposalJavaFunctionKrmsTermService extends KcKrmsJavaFunctionTermService {

    Boolean isCurrentFiscalMonth(InstitutionalProposal ip);

    Boolean hasSpecialReviewOfType(InstitutionalProposal ip, String specialReviewTypeCode);

}
