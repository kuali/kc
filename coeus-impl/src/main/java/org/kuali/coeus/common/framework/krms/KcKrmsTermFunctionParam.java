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
package org.kuali.coeus.common.framework.krms;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public class KcKrmsTermFunctionParam extends KcPersistableBusinessObjectBase implements Comparable<KcKrmsTermFunctionParam> {


    private static final long serialVersionUID = 5500796091484340802L;
    private Long kcKrmsTermFunctionParamId;
    private Long kcKrmsTermFunctionId;
    private String paramName;
    private String paramType;
    private Integer paramOrder;

    public Long getKcKrmsTermFunctionParamId() {
        return kcKrmsTermFunctionParamId;
    }

    public void setKcKrmsTermFunctionParamId(Long kcKrmsTermFunctionParamId) {
        this.kcKrmsTermFunctionParamId = kcKrmsTermFunctionParamId;
    }

    public Long getKcKrmsTermFunctionId() {
        return kcKrmsTermFunctionId;
    }

    public void setKcKrmsTermFunctionId(Long kcKrmsTermFunctionId) {
        this.kcKrmsTermFunctionId = kcKrmsTermFunctionId;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public Integer getParamOrder() {
        return paramOrder;
    }

    public void setParamOrder(Integer paramOrder) {
        this.paramOrder = paramOrder;
    }

    @Override
    public int compareTo(KcKrmsTermFunctionParam termToCompare) {
        return getParamOrder().compareTo(termToCompare.getParamOrder());
    }
}
