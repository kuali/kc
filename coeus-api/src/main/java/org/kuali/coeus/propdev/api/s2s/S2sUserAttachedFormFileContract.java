package org.kuali.coeus.propdev.api.s2s;

import org.kuali.coeus.sys.api.model.IdentifiableNumeric;

public interface S2sUserAttachedFormFileContract extends IdentifiableNumeric {

	public Long getS2sUserAttachedFormId();
	
	public byte[] getFormFile();
	
	public String getXmlFile();
}
