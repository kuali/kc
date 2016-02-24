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
package org.kuali.coeus.dc.common.rice.parameter;

public class Parameter {

    private ParameterKey parameterKey;

    private String value;
    private String description;
    private String parameterTypeCode;
    private String evaluationOperatorCode;

    public ParameterKey getParameterKey() {
        return parameterKey;
    }

    public void setParameterKey(ParameterKey parameterKey) {
        this.parameterKey = parameterKey;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParameterTypeCode() {
        return parameterTypeCode;
    }

    public void setParameterTypeCode(String parameterTypeCode) {
        this.parameterTypeCode = parameterTypeCode;
    }

    public String getEvaluationOperatorCode() {
        return evaluationOperatorCode;
    }

    public void setEvaluationOperatorCode(String evaluationOperatorCode) {
        this.evaluationOperatorCode = evaluationOperatorCode;
    }
}
