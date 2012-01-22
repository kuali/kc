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
package org.kuali.kra.award.awardhierarchy.sync.helpers;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncException;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.rice.krad.bo.PersistableBusinessObject;

/**
 * Award Hierarchy Sync Helper for Award Comments.
 */
public class AwardSyncCommentsHelper extends AwardSyncHelperBase {
    
    @Override
    public void applySyncChange(Award award, AwardSyncChange change) 
        throws NoSuchFieldException, IntrospectionException, IllegalAccessException, InvocationTargetException, 
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
