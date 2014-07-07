package org.kuali.coeus.propdev.api.s2s;

import org.kuali.coeus.propdev.api.core.NumberedProposal;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface S2sAppAttachmentsContract extends IdentifiableNumeric, NumberedProposal {

    String getContentId();

    String getContentType();

    String getHashCode();
}
