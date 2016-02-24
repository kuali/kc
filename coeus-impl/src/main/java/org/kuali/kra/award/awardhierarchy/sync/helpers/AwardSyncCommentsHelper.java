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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import org.apache.commons.lang3.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Award Hierarchy Sync Helper for Award Comments.
 */
public class AwardSyncCommentsHelper extends AwardSyncHelperBase {
    
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IllegalAccessException, InvocationTargetException, 
        ClassNotFoundException, NoSuchMethodException, InstantiationException, AwardSyncException {
        List<AwardComment> comments = award.getAwardComments();
        AwardComment comment = (AwardComment) getAwardSyncUtilityService().findMatchingBo(comments, change.getXmlExport().getKeys());
        if (StringUtils.equals(change.getSyncType(), AwardSyncType.ADD_SYNC.getSyncValue())) {
            //comment should never be null as there should always be one comment of each type, but if there isn't, add it anyway.
            if (comment == null) {
                comment = new AwardComment();
                setValuesOnSyncable(comment, change.getXmlExport().getKeys(), change);
                setValuesOnSyncable(comment, change.getXmlExport().getValues(), change);
                award.add(comment);
            } else {
                String newComment = "";
                if (!StringUtils.isBlank(comment.getComments())) {
                    newComment = comment.getComments() + "\n\n";
                }
                newComment += (String) change.getXmlExport().getValues().get("comments");
                comment.setComments(newComment);
            }
        } else {
            throw new AwardSyncException("Comment deletion not allowed.", false);
        }
    }
    
    @Override
    protected String getObjectDesc(PersistableBusinessObject syncableObject, String attrName) {
        AwardComment comment = (AwardComment) syncableObject;
        return "Comment : " + comment.getCommentType().getDescription();
    }
    
    @Override
    protected String getDataDesc(PersistableBusinessObject syncableObject, String attrName) {
        AwardComment comment = (AwardComment) syncableObject;
        return comment.getComments();
    }     
}
