package org.kuali.coeus.sys.framework.controller.rest.CustomEditors;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CustomSqlDateEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String value) {
        setValue(value);
    }

    @Override
    public Object getValue() {
        SimpleDateFormat source = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat target = new SimpleDateFormat("yyyy-MM-dd");

        String oldDate = super.getValue().toString();
        if (super.getValue() == null || StringUtils.isBlank(oldDate)) {
            return null;
        }

        String newDate;
        try {
            newDate = target.format(source.parse(oldDate));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return Date.valueOf(newDate);
    }

}
