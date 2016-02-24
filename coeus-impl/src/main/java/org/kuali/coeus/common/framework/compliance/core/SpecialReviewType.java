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
