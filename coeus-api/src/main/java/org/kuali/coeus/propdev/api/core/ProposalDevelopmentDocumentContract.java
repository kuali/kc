package org.kuali.coeus.propdev.api.core;

import org.kuali.coeus.common.api.custom.attr.CustomAttributeDocValueContract;
import org.kuali.coeus.sys.api.model.DocumentNumbered;

import java.util.List;

public interface ProposalDevelopmentDocumentContract extends DocumentNumbered {

    boolean isProposalDeleted();

    DevelopmentProposalContract getDevelopmentProposal();

    List<? extends CustomAttributeDocValueContract> getCustomDataList();
}
