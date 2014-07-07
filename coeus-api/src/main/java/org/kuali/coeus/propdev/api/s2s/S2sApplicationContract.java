package org.kuali.coeus.propdev.api.s2s;


import org.kuali.coeus.propdev.api.core.NumberedProposal;

import java.util.List;

public interface S2sApplicationContract extends NumberedProposal {

    String getApplication();

    List<? extends S2sAppAttachmentsContract> getS2sAppAttachmentList();
}
