package org.kuali.coeus.propdev.api.s2s;

import java.util.List;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;
import org.kuali.coeus.sys.api.model.KcFile;

public interface S2sUserAttachedFormAttContract extends IdentifiableNumeric, KcFile {

    Long getS2sUserAttachedFormId();

    String getProposalNumber();

    String getContentId();

	List<? extends S2sUserAttachedFormAttFileContract> getS2sUserAttachedFormAttFiles();
}
