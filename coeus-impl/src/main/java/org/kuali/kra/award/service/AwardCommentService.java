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
     * they can be displayed on the Comments, Notes &amp; Attachments tab.
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
     * @return
     */
    List<AwardComment> retrieveCommentHistoryByType(String awardCommentTypeCode, String awardId);
}
