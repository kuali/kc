package org.kuali.coeus.propdev.api.s2s;

import org.kuali.coeus.propdev.api.core.NumberedProposal;
import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

import java.util.List;

public interface S2sUserAttachedFormContract extends IdentifiableNumeric, NumberedProposal {
    
    String getNamespace();
    
    String getFormName();
    
    String getFormFileName();

    String getDescription();

    List<? extends S2sUserAttachedFormAttContract> getS2sUserAttachedFormAtts();

	List<? extends S2sUserAttachedFormFileContract> getS2sUserAttachedFormFileList();

}
