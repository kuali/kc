package org.kuali.coeus.sys.framework.controller.rest;

import java.beans.PropertyEditorSupport;
import java.time.Instant;

public class InstantCustomPropertyEditor extends PropertyEditorSupport {
	public String getAsText() {
		if (getValue() != null && getValue() instanceof Instant) {
			return String.valueOf(((Instant)getValue()).toEpochMilli());
		} else {
			return super.getAsText();
		}
	}
	
	public void setAsText(String value) {
		setValue(Instant.ofEpochMilli(Long.parseLong(value)));
	}
}
