/*
 * Copyright 2005-2010 The Kuali Foundation
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
package org.kuali.kra.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.infrastructure.KraServiceLocator;
import org.kuali.kra.service.AwardCommentService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

/**
 * Service class for Award Comments on Comments, Notes & Attachments tab.
 */
@Transactional
public class AwardCommentServiceImpl implements AwardCommentService {
    
    private BusinessObjectService businessObjectService;
    private String AWARD_COMMENT_SCREEN_FLAG = "awardCommentScreenFlag";
    private String AWARD_COMMENT_TYPE_CODE = "commentTypeCode";
    private String AWARD_ID = "awardId";
    
    /**
     * @see org.kuali.kra.service.AwardCommentService#retrieveCommentTypesToAwardFormForPanelHeaderDisplay()
     */
    @SuppressWarnings("unchecked")
    public List<CommentType> retrieveCommentTypes() {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(AWARD_COMMENT_SCREEN_FLAG, CommentType.SCREENFLAG_TRUE);
        List<CommentType> commentTypeList = 
            (List<CommentType>) getBusinessObjectService().findMatching(CommentType.class, queryMap);
        return commentTypeList;
    }
    
    @SuppressWarnings("unchecked")
    public List<AwardComment> retrieveCommentHistoryByType(String awardCommentTypeCode, String awardId) {
        this.businessObjectService = KraServiceLocator.getService(BusinessObjectService.class);
        Award award = getAward(awardId);
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(AWARD_COMMENT_TYPE_CODE, awardCommentTypeCode);
        queryMap.put("awardNumber", award.getAwardNumber());
        return filterAwardComment((List<AwardComment>) getBusinessObjectService().findMatching(AwardComment.class, queryMap), award.getSequenceNumber());
    }

    protected Award getAward(String awardId) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(AWARD_ID, awardId);
        return (Award)getBusinessObjectService().findByPrimaryKey(Award.class, queryMap);
        
    }
    
    protected List<AwardComment> filterAwardComment(List<AwardComment> results, Integer sequenceNum) {
        List<AwardComment> returnList = new ArrayList<AwardComment>();
        List<String> comments = new ArrayList<String>();
        for (AwardComment awardComment : results) {
            if (sequenceNum >= awardComment.getSequenceNumber() 
                    && !comments.contains(awardComment.getComments())) {
                returnList.add(awardComment);
                comments.add(awardComment.getComments());
            }
        }
        Collections.sort(returnList);
        return returnList;
    }
        
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @param bos BusinessObjectService
     */
    public void setBusinessObjectService(BusinessObjectService bos) {
        businessObjectService = bos;
    }
    
    /**
     * Accessor for <code>{@link BusinessObjectService}</code>
     *
     * @return BusinessObjectService
     */
    public BusinessObjectService getBusinessObjectService() {
        return this.businessObjectService;
    }

    

}
