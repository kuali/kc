package org.kuali.coeus.propdev.api.s2s;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
import org.kuali.coeus.sys.api.model.KcFile;

public interface S2sUserAttachedFormAttContract extends IdentifiableNumeric, KcFile {

    Long getS2sUserAttachedFormId();

    String getProposalNumber();

    String getContentId();
}
