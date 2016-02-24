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
package org.kuali.kra.award.home;

import org.kuali.coeus.common.framework.sponsor.term.SponsorTerm;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

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
