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
package org.kuali.kra.award.service;

import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.bo.CommentType;

import java.util.List;

/**
 * This is the Award Comment Service interface.
 */
public interface AwardCommentService {

    /**
     * This method gets all of the comment types from database where awardCommentScreenFlag is set to 'Y' so
     * they can be displayed on the Comments, Notes & Attachments tab.
     * @return
     */
    List<CommentType> retrieveCommentTypes();
    
    /**
     * This method retrieves a list of Booleans that indicate whether or not to display the Show History
     * button on the panel.  Each element should correspond to an element returned by retrieveCommentTypes() method.
     * @return
     */
    List<String> retrieveCommentHistoryFlags(String awardId);
    
    /**
     * This method retrieves from the database all Award Comments with the give type code and Award ID for
     * display in view history popup page.
     * @param awardCommentTypeCode
     * @param AwardId
     * @return
     */
    List<AwardComment> retrieveCommentHistoryByType(String awardCommentTypeCode, String awardId);
}
