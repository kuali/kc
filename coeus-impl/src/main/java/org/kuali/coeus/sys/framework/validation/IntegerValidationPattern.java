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
package org.kuali.coeus.sys.framework.validation;

import org.kuali.rice.krad.datadictionary.exporter.ExportMap;
import org.kuali.rice.krad.datadictionary.validation.CharacterLevelValidationPattern;

public class IntegerValidationPattern extends CharacterLevelValidationPattern {
    protected boolean allowNegative;
    protected boolean onlyNegative;
    protected boolean omitZero;

    @Override
    protected String getRegexString() {
        StringBuffer regex = new StringBuffer();

        if (isAllowNegative() && !isOnlyNegative()) {
            regex.append("((-?");
        } else if (isOnlyNegative()) {
            regex.append("((-");
        } else {
            regex.append("((");
        }
        if (isOmitZero()) {
            regex.append("[1-9][0-9]*))");
        } else {
            regex.append("[1-9][0-9]*)|[0]*)");
        }

        return regex.toString();
    }

    @Override
    public void extendExportMap(ExportMap exportMap) {
        exportMap.set("type", "numeric");
    }

    public boolean isAllowNegative() {
        return allowNegative;
    }

    public void setAllowNegative(boolean allowNegative) {
        this.allowNegative = allowNegative;
    }

    public boolean isOnlyNegative() {
        return onlyNegative;
    }

    public void setOnlyNegative(boolean onlyNegative) {
        this.onlyNegative = onlyNegative;
    }

    public boolean isOmitZero() {
        return omitZero;
    }

    public void setOmitZero(boolean omitZero) {
        this.omitZero = omitZero;
    }
}
