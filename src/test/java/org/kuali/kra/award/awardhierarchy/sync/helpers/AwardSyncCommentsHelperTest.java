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


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.bo.CommentType;

public class AwardSyncCommentsHelperTest extends AwardSyncHelperTestBase {
    
    protected AwardComment comment;
    protected final String awardTypeCode1 = "2";
    protected final String awardTestComment1 = "Test Comment";
    protected final String awardTestComment2 = "My new comment";
    
    public AwardSyncCommentsHelperTest() {
        super("AwardComment");
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        comment = new AwardComment();
        comment.setCommentType(new CommentType());
        comment.getCommentType().setCommentTypeCode(awardTypeCode1);
        comment.getCommentType().setDescription("Test Comment Type");
        comment.setCommentTypeCode(awardTypeCode1);
        comment.setComments(awardTestComment1);
        //add the empty comment to the award so it will act like it was there already.
        AwardComment blankComment = new AwardComment();
        blankComment.setCommentType(comment.getCommentType());
        award.add(blankComment);
        
        
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testBuildXmlExport() throws Exception {
        AwardSyncXmlExport xmlExport = awardSyncHelper.buildXmlExport(comment, null);
        assertNotNull(xmlExport);
        assertFalse(xmlExport.getKeys().isEmpty());
        assertFalse(xmlExport.getValues().isEmpty());
        assertEquals(comment.getCommentTypeCode(), xmlExport.getKeys().get("commentTypeCode")); 
        assertEquals(comment.getComments(), xmlExport.getValues().get("comments"));
    }
    
    @Test
    public void testCreateAwardSyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, comment, "awardComments", null);
        assertNotNull(change);
        assertNotNull(change.getXmlExport());
    }
    
    @Test
    public void testApplySyncChange() throws Exception {
        AwardSyncChange change = 
            awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, comment, "awardComments", null);
        awardSyncHelper.applySyncChange(award, change);
        assertFalse(award.getAwardComments().isEmpty());
        assertEquals(award.getAwardCommentByType(awardTypeCode1, false, false).getComments(), awardTestComment1);
        
        comment.setComments(awardTestComment2);
        change = awardSyncHelper.createAwardSyncChange(AwardSyncType.ADD_SYNC, comment, "awardComments", null);
        awardSyncHelper.applySyncChange(award, change);
        assertFalse(award.getAwardComments().isEmpty());
        assertTrue(award.getAwardCommentByType(awardTypeCode1, false, false).getComments().contains(awardTestComment1));
        assertTrue(award.getAwardCommentByType(awardTypeCode1, false, false).getComments().contains(awardTestComment2));
    }
}
