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
package org.kuali.kra.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.kra.bo.CommentType;
import org.kuali.kra.service.AwardCommentService;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service class for Award Comments on Comments, Notes & Attachments tab.
 */
@Transactional
public class AwardCommentServiceImpl implements AwardCommentService {
    
    private BusinessObjectService businessObjectService;
    
    /**
     * @see org.kuali.kra.service.AwardCommentService#retrieveCommentTypesToAwardFormForPanelHeaderDisplay()
     */
    @SuppressWarnings("unchecked")
    public List<CommentType> retrieveCommentTypesToAwardFormForPanelHeaderDisplay() {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("awardCommentScreenFlag", "Y");
        List<CommentType> commentTypeList = 
            (List<CommentType>) getBusinessObjectService().findMatching(CommentType.class, queryMap);
        return commentTypeList;
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
        return businessObjectService;
    }

}
