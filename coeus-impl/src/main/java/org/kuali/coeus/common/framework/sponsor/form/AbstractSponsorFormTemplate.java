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


import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public abstract class AbstractSponsorFormTemplate extends KcPersistableBusinessObjectBase implements Comparable<AbstractSponsorFormTemplate> {

    private Long sponsorFormTemplateId;

    private Long sponsorFormId;

    private Integer pageNumber;

    private String pageDescription;

    private SponsorForms sponsorForms;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPageDescription() {
        return pageDescription;
    }

    public void setPageDescription(String pageDescription) {
        this.pageDescription = pageDescription;
    }

    public final SponsorForms getSponsorForms() {
        return sponsorForms;
    }

    public final void setSponsorForms(SponsorForms sponsorForms) {
        this.sponsorForms = sponsorForms;
    }

    public int compareTo(AbstractSponsorFormTemplate abstractSponsorFormTemplate) {
        int result = getSponsorForms().getPackageNumber().compareTo(abstractSponsorFormTemplate.getSponsorForms().getPackageNumber());
        result = result != 0 ? result : getPageNumber().compareTo(abstractSponsorFormTemplate.getPageNumber());
        return result;
    }

    public Long getSponsorFormTemplateId() {
        return sponsorFormTemplateId;
    }

    public void setSponsorFormTemplateId(Long sponsorFormTemplateId) {
        this.sponsorFormTemplateId = sponsorFormTemplateId;
    }

    public Long getSponsorFormId() {
        return sponsorFormId;
    }

    public void setSponsorFormId(Long sponsorFormId) {
        this.sponsorFormId = sponsorFormId;
    }
}
