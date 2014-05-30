package org.kuali.coeus.propdev.api.s2s;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface S2sUserAttachedFormAttFileContract extends IdentifiableNumeric {
	
    public Long getS2sUserAttachedFormAttId();
    
    public byte[] getAttachment();
}
