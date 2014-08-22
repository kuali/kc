package org.kuali.coeus.sys.framework.model;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class ScaleTwoDecimalEditor extends PropertyEditorSupport {

	private NumberFormat numberFormat;
	private boolean allowEmpty;
	
	public ScaleTwoDecimalEditor(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}
	
	public ScaleTwoDecimalEditor(NumberFormat numberFormat, boolean allowEmpty) {
		this.numberFormat = numberFormat;
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && StringUtils.isBlank(text)) {
			setValue(null);
		} else if (this.numberFormat != null) {
			try {
				setValue(new ScaleTwoDecimal(numberFormat.parse(text).toString()));
			} catch (ParseException e) {
				throw new IllegalArgumentException(text + " is not parsable by the NumberFormat provided(" + numberFormat.toString() + ")");
			}
		} else {
			setValue(new ScaleTwoDecimal(text));
		}
	}

	@Override
	public String getAsText() {
		String strValue = "";
		Object value = getValue();
		if (value == null) {
			if (numberFormat != null) {
				strValue = numberFormat.format(0);
			}
		} else if (numberFormat != null) {
			strValue = numberFormat.format(value);
		}else {
			strValue = value.toString();
		}
		return strValue;
	}
	

	public NumberFormat getNumberFormat() {
		return numberFormat;
	}

	public void setNumberFormat(NumberFormat numberFormat) {
		this.numberFormat = numberFormat;
	}

	public boolean isAllowEmpty() {
		return allowEmpty;
	}

	public void setAllowEmpty(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}
	

}
