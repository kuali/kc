/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.award.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.service.AwardJavaFunctionKrmsTermService;
import org.kuali.coeus.common.impl.krms.KcKrmsJavaFunctionTermServiceBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.service.AwardJavaFunctionKrmsTermService;
import org.kuali.kra.award.specialreview.AwardSpecialReview;

import java.math.BigDecimal;
import java.util.Objects;

public class AwardJavaFunctionKrmsTermServiceImpl extends KcKrmsJavaFunctionTermServiceBase implements AwardJavaFunctionKrmsTermService {

    public Boolean checkCommentEntered(Award award, String commentTypeCode) {
        for (AwardComment comment : award.getAwardComments()) {
            if (StringUtils.equals(comment.getCommentTypeCode(), commentTypeCode) && StringUtils.isNotBlank(comment.getComments())) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasSpecialReviewOfType(Award award, String specialReviewType) {
        for (AwardSpecialReview specialReview : award.getSpecialReviews()) {
            if (StringUtils.equals(specialReview.getSpecialReviewTypeCode(), specialReviewType)) {
                return true;
            }
            else if (specialReview.getSpecialReviewType() != null && StringUtils.equals(specialReview.getSpecialReviewType().getDescription(), specialReviewType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean awardPersonnelTotalEffort(Award award, String effortToMatch) {
        ScaleTwoDecimal effort = convertToScaleTwoDecimal(effortToMatch);
        return award.getProjectPersons().size() == 0 ? Boolean.FALSE : award.getProjectPersons().stream().anyMatch(person -> Objects.equals(person.getTotalEffort(), effort));
    }

    @Override
    public Boolean awardPersonnelCalendarEffort(Award award, String effortToMatch) {
        ScaleTwoDecimal effort = convertToScaleTwoDecimal(effortToMatch);
        return award.getProjectPersons().size() == 0 ? Boolean.FALSE : award.getProjectPersons().stream().anyMatch(person -> Objects.equals(person.getCalendarYearEffort(), effort));
    }

    @Override
    public Boolean awardCommentsRule(Award award, String comments) {
        return award.getAwardCurrentActionComments().getComments() == null && comments.equalsIgnoreCase("null") ||
                comments.equalsIgnoreCase(award.getAwardCurrentActionComments().getComments());
    }

    protected ScaleTwoDecimal convertToScaleTwoDecimal(String effortToMatch) {
        ScaleTwoDecimal effort;
        if (effortToMatch.equalsIgnoreCase("null") || StringUtils.isEmpty(effortToMatch)) {
            effort = null;
        } else {
            effort = new ScaleTwoDecimal(effortToMatch);
        }
        return effort;
    }
}
