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
package org.kuali.coeus.common.framework.compliance.core;

import org.kuali.coeus.common.api.compliance.core.SpecialReviewTypeContract;
import org.kuali.kra.bo.KraSortablePersistableBusinessObjectBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Defines the type of the Special Review.
 */
@Entity
@Table(name = "SPECIAL_REVIEW")
public class SpecialReviewType extends KraSortablePersistableBusinessObjectBase implements SpecialReviewTypeContract {

    /**
     * The Human Subjects Special Review type.
     */
    public static final String HUMAN_SUBJECTS = "1";

    public static final String ANIMAL_USAGE = "2";

    private static final long serialVersionUID = -7939863013575475658L;

    @Id
    @Column(name = "SPECIAL_REVIEW_CODE")
    private String specialReviewTypeCode;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getSpecialReviewTypeCode() {
        return specialReviewTypeCode;
    }

    public void setSpecialReviewTypeCode(String specialReviewTypeCode) {
        this.specialReviewTypeCode = specialReviewTypeCode;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return getSpecialReviewTypeCode();
    }
}
