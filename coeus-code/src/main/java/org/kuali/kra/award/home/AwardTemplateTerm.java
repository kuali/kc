/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.home;

import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.kra.bo.SponsorTerm;

public class AwardTemplateTerm extends KcPersistableBusinessObjectBase {


    private static final long serialVersionUID = 737831469929642714L;

    private Integer awardTemplateTermId;

    private String templateCode;

    private AwardTemplate awardTemplate;

    @AwardSyncable
    private Long sponsorTermId;

    private String sponsorTermTypeCode;

    private SponsorTerm sponsorTerm;

    private String description;

    private String sponsorTermCode;

    public AwardTemplateTerm() {
    }

    public Integer getAwardTemplateTermId() {
        return awardTemplateTermId;
    }

    public void setAwardTemplateTermId(Integer awardTemplateTermId) {
        this.awardTemplateTermId = awardTemplateTermId;
    }

    public AwardTemplate getAwardTemplate() {
        return awardTemplate;
    }

    public void setAwardTemplate(AwardTemplate awardTemplate) {
        this.awardTemplate = awardTemplate;
    }

    /**
     * Gets the sponsorTermsTypeCode attribute. 
     * @return Returns the sponsorTermsTypeCode.
     */
    public String getSponsorTermTypeCode() {
        return sponsorTerm.getSponsorTermTypeCode();
    }

    /**
     * Sets the sponsorTermsTypeCode attribute value.
     * @param sponsorTermTypeCode The sponsorTermTypeCode to set.
     */
    public void setSponsorTermTypeCode(String sponsorTermTypeCode) {
        this.sponsorTermTypeCode = sponsorTermTypeCode;
    }

    /**
     * Gets the sponsorTermId attribute. 
     * @return Returns the sponsorTermId.
     */
    public Long getSponsorTermId() {
        return sponsorTermId;
    }

    /**
     * Sets the sponsorTermId attribute value.
     * @param sponsorTermId The sponsorTermId to set.
     */
    public void setSponsorTermId(Long sponsorTermId) {
        this.sponsorTermId = sponsorTermId;
    }

    /**
     * Gets the sponsorTerm attribute. 
     * @return Returns the sponsorTerm.
     */
    public SponsorTerm getSponsorTerm() {
        return sponsorTerm;
    }

    /**
     * Sets the sponsorTerm attribute value.
     * @param sponsorTerm The sponsorTerm to set.
     */
    public void setSponsorTerm(SponsorTerm sponsorTerm) {
        this.sponsorTerm = sponsorTerm;
    }

    /**
     * Gets the description attribute. 
     * @return Returns the description.
     */
    public String getDescription() {
        return sponsorTerm.getDescription();
    }

    /**
     * Sets the description attribute value.
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * Gets the sponsorTermCode attribute. 
     * @return Returns the sponsorTermCode.
     */
    public String getSponsorTermCode() {
        return sponsorTerm.getSponsorTermCode();
    }
    
    /**
     * Sets the sponsorTermCode attribute value.
     * @param sponsorTermCode The sponsorTermCode to set.
     */
    public void setSponsorTermCode(String sponsorTermCode) {
        this.sponsorTermCode = sponsorTermCode;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
}
