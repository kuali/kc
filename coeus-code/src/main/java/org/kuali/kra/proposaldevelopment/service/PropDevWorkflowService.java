package org.kuali.kra.proposaldevelopment.service;

import org.kuali.kra.proposaldevelopment.bo.ProposalDevelopmentApproverViewDO;
import org.kuali.kra.proposaldevelopment.document.ProposalDevelopmentDocument;
import org.kuali.kra.proposaldevelopment.web.struts.form.ProposalDevelopmentForm;

public interface PropDevWorkflowService {

    public ProposalDevelopmentApproverViewDO populateApproverViewDO (ProposalDevelopmentForm proposalDevelopmentForm);

    public boolean canPerformWorkflowAction(ProposalDevelopmentDocument document);
}
