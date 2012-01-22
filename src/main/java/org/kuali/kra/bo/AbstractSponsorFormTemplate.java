/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.osedu.org/licenses/ECL-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo;


public abstract class AbstractSponsorFormTemplate extends KraPersistableBusinessObjectBase implements Comparable<AbstractSponsorFormTemplate> {

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
