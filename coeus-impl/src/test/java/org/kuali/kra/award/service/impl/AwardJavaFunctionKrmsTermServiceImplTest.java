package org.kuali.kra.award.service.impl;


import junit.framework.Assert;
import org.junit.Test;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.kra.award.contacts.AwardPerson;
import org.kuali.kra.award.home.Award;
import org.kuali.kra.award.home.AwardComment;
import org.kuali.kra.bo.CommentType;
import org.kuali.kra.infrastructure.Constants;


public class AwardJavaFunctionKrmsTermServiceImplTest {

    @Test
    public void testAwardCalendarEffortRule() {

        Award award = new Award();
        AwardPerson person1 = new AwardPerson();
        AwardJavaFunctionKrmsTermServiceImpl termService = new AwardJavaFunctionKrmsTermServiceImpl();
        boolean result = termService.awardPersonnelCalendarEffort(award, "null");
        Assert.assertFalse(result);

        person1.setCalendarYearEffort(new ScaleTwoDecimal(70.15));
        award.getProjectPersons().add(person1);
        termService = new AwardJavaFunctionKrmsTermServiceImpl();
        result = termService.awardPersonnelCalendarEffort(award, "null");
        Assert.assertFalse(result);

        award.getProjectPersons().get(0).setCalendarYearEffort(null);
        result = termService.awardPersonnelCalendarEffort(award, "null");
        Assert.assertTrue(result);

        award.getProjectPersons().get(0).setCalendarYearEffort(new ScaleTwoDecimal(70.15));
        result = termService.awardPersonnelCalendarEffort(award, "70.154");
        Assert.assertTrue(result);

        award.getProjectPersons().get(0).setCalendarYearEffort(null);
        result = termService.awardPersonnelCalendarEffort(award, "70.154");
        Assert.assertFalse(result);

    }

    @Test
    public void testAwardTotalEffortRule() {

        Award award = new Award();
        AwardPerson person1 = new AwardPerson();

        AwardJavaFunctionKrmsTermServiceImpl termService = new AwardJavaFunctionKrmsTermServiceImpl();
        boolean result = termService.awardPersonnelTotalEffort(award, "null");
        Assert.assertFalse(result);

        person1.setTotalEffort(new ScaleTwoDecimal(70.15));
        award.getProjectPersons().add(person1);
        termService = new AwardJavaFunctionKrmsTermServiceImpl();
        result = termService.awardPersonnelTotalEffort(award, "null");
        Assert.assertFalse(result);

        award.getProjectPersons().get(0).setTotalEffort(null);
        result = termService.awardPersonnelTotalEffort(award, "null");
        Assert.assertTrue(result);

        award.getProjectPersons().get(0).setTotalEffort(new ScaleTwoDecimal(70.15));
        result = termService.awardPersonnelTotalEffort(award, "70.154");
        Assert.assertTrue(result);

        award.getProjectPersons().get(0).setTotalEffort(null);
        result = termService.awardPersonnelTotalEffort(award, "70.154");
        Assert.assertFalse(result);

    }

    @Test
    public void testAwardCommentRule() {

        Award award = new Award();
        AwardComment awardComment = new AwardComment();
        CommentType commentType = new CommentType();
        commentType.setCommentTypeCode(Constants.CURRENT_ACTION_COMMENT_TYPE_CODE);
        awardComment.setCommentType(commentType);
        awardComment.setCommentTypeCode(Constants.CURRENT_ACTION_COMMENT_TYPE_CODE);
        awardComment.setComments("Test");
        award.getAwardComments().add(awardComment);

        AwardJavaFunctionKrmsTermServiceImpl termService = new AwardJavaFunctionKrmsTermServiceImpl();
        boolean result = termService.awardCommentsRule(award, "null");
        Assert.assertFalse(result);

        award.getAwardComments().get(0).setComments(null);
        result = termService.awardCommentsRule(award, "null");
        Assert.assertTrue(result);

        award.getAwardComments().get(0).setComments("Batman");
        result = termService.awardCommentsRule(award, "Batman");
        Assert.assertTrue(result);

        award.getAwardComments().get(0).setComments(null);
        result = termService.awardCommentsRule(award, "Batman");
        Assert.assertFalse(result);

    }
}
