package org.kuali.kra.s2s.depend;


import java.util.List;

public interface S2sApplicationContract {

    String getProposalNumber();

    String getApplication();

    List<? extends S2sAppAttachmentsContract> getS2sAppAttachmentList();
}
