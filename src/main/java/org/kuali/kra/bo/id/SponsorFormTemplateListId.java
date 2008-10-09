/*
 * Copyright 2006-2008 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.bo.id;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * Primary Key for the SponsorFormTemplateList BO.
 */
@SuppressWarnings("serial")
public class SponsorFormTemplateListId extends AbstractSponsorFormTemplateId {
    @Column(name="PACKAGE_NUMBER")
    private Integer packageNumber;
    
    @Column(name="PAGE_NUMBER")
    private Integer pageNumber;
    
    @Column(name="SPONSOR_CODE")
    private String sponsorCode;

    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof SponsorFormTemplateListId)) return false;
        return true;
    }
    
    public int hashCode() {
        return getHashCodeBuilder().toHashCode();
    }
}
