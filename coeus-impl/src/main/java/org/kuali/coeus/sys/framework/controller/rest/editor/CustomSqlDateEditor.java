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
package org.kuali.coeus.sys.framework.controller.rest.editor;

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

        try {
            String newDate = target.format(source.parse(oldDate));
            return Date.valueOf(newDate);
        } catch (ParseException e) {
            try {
                return new Date(target.parse(oldDate).getTime());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
