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
package org.kuali.coeus.common.framework.sponsor.form;

import org.kuali.coeus.common.framework.sponsor.Sponsor;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

import java.util.ArrayList;
import java.util.List;

public class SponsorForms extends KcPersistableBusinessObjectBase {

    private Long sponsorFormId;

    private String packageName;

    private Integer packageNumber;

    private String sponsorCode;

    private String sponsorHierarchyName;

    private Sponsor sponsor;

    private List<SponsorFormTemplateList> sponsorFormTemplates;

    public SponsorForms() {
        super();
        sponsorFormTemplates = new ArrayList<SponsorFormTemplateList>();
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(Integer packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getSponsorCode() {
        return sponsorCode;
    }

    public void setSponsorCode(String sponsorCode) {
        this.sponsorCode = sponsorCode;
    }

    public final Sponsor getSponsor() {
        return sponsor;
    }

    public final void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public final List<SponsorFormTemplateList> getSponsorFormTemplates() {
        return sponsorFormTemplates;
    }

    public final void setSponsorFormTemplates(List<SponsorFormTemplateList> sponsorFormTemplates) {
        this.sponsorFormTemplates = sponsorFormTemplates;
    }

    public Long getSponsorFormId() {
        return sponsorFormId;
    }

    public void setSponsorFormId(Long sponsorFormId) {
        this.sponsorFormId = sponsorFormId;
    }

    public String getSponsorHierarchyName() {
        return sponsorHierarchyName;
    }

    public void setSponsorHierarchyName(String sponsorHierarchyName) {
        this.sponsorHierarchyName = sponsorHierarchyName;
    }
}
