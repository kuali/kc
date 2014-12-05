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
