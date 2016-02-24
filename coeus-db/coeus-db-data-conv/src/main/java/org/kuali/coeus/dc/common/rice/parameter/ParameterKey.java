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

public final class ParameterKey {

    private String namespaceCode;
    private String componentCode;
    private String name;
    private String applicationId;

    public ParameterKey(String namespaceCode, String componentCode, String name, String applicationId) {
        this.namespaceCode = namespaceCode;
        this.componentCode = componentCode;
        this.name = name;
        this.applicationId = applicationId;
    }

    public ParameterKey() {
    }

    public String getNamespaceCode() {
        return namespaceCode;
    }

    public void setNamespaceCode(String namespaceCode) {
        this.namespaceCode = namespaceCode;
    }

    public String getComponentCode() {
        return componentCode;
    }

    public void setComponentCode(String componentCode) {
        this.componentCode = componentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParameterKey that = (ParameterKey) o;

        if (applicationId != null ? !applicationId.equals(that.applicationId) : that.applicationId != null)
            return false;
        if (componentCode != null ? !componentCode.equals(that.componentCode) : that.componentCode != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (namespaceCode != null ? !namespaceCode.equals(that.namespaceCode) : that.namespaceCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = namespaceCode != null ? namespaceCode.hashCode() : 0;
        result = 31 * result + (componentCode != null ? componentCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (applicationId != null ? applicationId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ParameterKey{" +
                "namespaceCode='" + namespaceCode + '\'' +
                ", componentCode='" + componentCode + '\'' +
                ", name='" + name + '\'' +
                ", applicationId='" + applicationId + '\'' +
                '}';
    }
}
