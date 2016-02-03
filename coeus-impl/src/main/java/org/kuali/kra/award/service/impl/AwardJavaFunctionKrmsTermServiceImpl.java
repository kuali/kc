/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.award.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.impl.krms.KcKrmsJavaFunctionTermServiceBase;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.award.service.AwardJavaFunctionKrmsTermService;
import org.kuali.kra.award.specialreview.AwardSpecialReview;

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

}
