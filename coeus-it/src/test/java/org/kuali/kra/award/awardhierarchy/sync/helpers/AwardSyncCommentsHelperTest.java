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


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncChange;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncType;
import org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.bo.CommentType;
import static org.junit.Assert.*;
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
