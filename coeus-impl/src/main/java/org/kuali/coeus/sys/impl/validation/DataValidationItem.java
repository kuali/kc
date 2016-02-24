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
package org.kuali.coeus.sys.impl.validation;

public class DataValidationItem {
    private String area;
    private String section;
    private String description;
    private String severity;
    private String navigateToPageId;
    private String navigateToSectionId;

    
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getNavigateToPageId() {
        return navigateToPageId;
    }

    public void setNavigateToPageId(String navigateToPageId) {
        this.navigateToPageId = navigateToPageId;
    }

    public String getNavigateToSectionId() {
        return navigateToSectionId;
    }

    public void setNavigateToSectionId(String navigateToSectionId) {
        this.navigateToSectionId = navigateToSectionId;
    }

}
