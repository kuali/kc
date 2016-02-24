/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.sys.framework.model;

import java.beans.PropertyEditorSupport;
import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;

public class ScaleTwoDecimalEditor extends PropertyEditorSupport {

	private DecimalFormat numberFormat;
	private boolean allowEmpty;

	public ScaleTwoDecimalEditor(DecimalFormat numberFormat, boolean allowEmpty) {
		this.numberFormat = numberFormat;
		this.numberFormat.setGroupingUsed(true);
		this.numberFormat.setGroupingSize(3);
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

	public DecimalFormat getNumberFormat() {
		return numberFormat;
	}

	public void setNumberFormat(DecimalFormat numberFormat) {
		this.numberFormat = numberFormat;
	}

	public boolean isAllowEmpty() {
		return allowEmpty;
	}

	public void setAllowEmpty(boolean allowEmpty) {
		this.allowEmpty = allowEmpty;
	}
	

}
