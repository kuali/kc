package org.kuali.coeus.sys.framework.controller.rest.CustomEditors;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CustomSqlTimestampEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String value) {
        setValue(value);
    }

    @Override
    public Object getValue() {
        if (super.getValue() == null || StringUtils.isBlank(super.getValue().toString())) {
            return null;
        }

        return Timestamp.valueOf(super.getValue().toString());
    }


}
