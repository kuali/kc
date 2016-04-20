package org.kuali.coeus.sys.framework.controller.rest.CustomEditors;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CustomSqlTimestampEditor extends PropertyEditorSupport {

    public void setAsText(String value) {
        setValue(value);
    }

    public Object getValue() {
        return Timestamp.valueOf(super.getValue().toString());
    }


}
