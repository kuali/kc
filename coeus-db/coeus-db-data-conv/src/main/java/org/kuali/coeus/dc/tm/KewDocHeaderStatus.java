package org.kuali.coeus.dc.tm;

public class KewDocHeaderStatus {

	public String documentHeaderId;
	public String documentStatusCode;
	
	public KewDocHeaderStatus() {
		super();
	}
	
	public KewDocHeaderStatus(String documentHeaderId, String documentStatusCode) {
		this();
		this.documentHeaderId = documentHeaderId;
		this.documentStatusCode = documentStatusCode;
	}
}
