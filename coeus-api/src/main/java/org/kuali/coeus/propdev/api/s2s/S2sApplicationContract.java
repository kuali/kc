package org.kuali.coeus.propdev.api.s2s;


import java.util.List;

public interface S2sApplicationContract {

    String getProposalNumber();

    String getApplication();

    List<? extends S2sAppAttachmentsContract> getS2sAppAttachmentList();
}
