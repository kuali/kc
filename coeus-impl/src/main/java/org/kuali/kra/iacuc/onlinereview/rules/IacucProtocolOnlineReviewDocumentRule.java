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
package org.kuali.kra.iacuc.onlinereview.rules;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.committee.impl.meeting.CommitteeScheduleMinuteBase;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcDocumentEventBaseExtension;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.coeus.sys.framework.util.DateUtils;
import org.kuali.kra.iacuc.onlinereview.IacucProtocolOnlineReview;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.kra.protocol.onlinereview.event.*;
import org.kuali.kra.protocol.onlinereview.rules.*;
import org.kuali.rice.krad.service.KualiRuleService;
import org.kuali.rice.krad.util.GlobalVariables;

public class IacucProtocolOnlineReviewDocumentRule extends KcTransactionalDocumentRuleBase
implements AddOnlineReviewCommentRule,
        SaveProtocolOnlineReviewRule, KcBusinessRule, RouteProtocolOnlineReviewRule, DisapproveOnlineReviewCommentRule,
        RejectOnlineReviewCommentRule, DeleteOnlineReviewRule {

    private static final String ONLINE_REVIEW_COMMENTS_ERROR_PATH = "onlineReviewsActionHelper.reviewCommentsBeans[%s]";
    private static final String ONLINE_REVIEW_ATTACHMENTS_ERROR_PATH = "onlineReviewsActionHelper.reviewAttachmentsBeans[%s]";
    private static final String ONLINE_REVIEW_ERROR_PATH = "onlineReviewsActionHelper.protocolOnlineReviewDocuments[%s].protocolOnlineReview";

    public boolean processAddProtocolOnlineReviewComment(IacucProtocolOnlineReview protocolOnlineReview, CommitteeScheduleMinuteBase minute) {
        return false;
    }


    public boolean processAddProtocolOnlineReviewComment(AddProtocolOnlineReviewCommentEvent event) {

        boolean valid = true;
        CommitteeScheduleMinuteBase minute = event.getCommitteeScheduleMinute();
        if (StringUtils.isEmpty(minute.getMinuteEntry()) && StringUtils.isEmpty(minute.getProtocolContingencyCode())) {
            valid = false;
            GlobalVariables.getMessageMap().clearErrorPath();
            GlobalVariables.getMessageMap().addToErrorPath(
                    String.format(ONLINE_REVIEW_COMMENTS_ERROR_PATH, event.getOnlineReviewIndex()));
            GlobalVariables.getMessageMap().putError("newReviewComment.minuteEntry",
                    KeyConstants.ERROR_ONLINE_REVIEW_COMMENT_REQUIRED);
        }
        return valid;
    }

    public boolean processSaveProtocolOnlineReview(SaveProtocolOnlineReviewEvent event) {
        boolean valid = true;
        GlobalVariables.getMessageMap().clearErrorPath();
        GlobalVariables.getMessageMap().addToErrorPath(
                String.format(ONLINE_REVIEW_COMMENTS_ERROR_PATH, event.getOnlineReviewIndex()));

        ProtocolOnlineReviewBase protocolOnlineReview = event.getProtocolOnlineReviewDocument().getProtocolOnlineReview();

        int index = 0;

        for (CommitteeScheduleMinuteBase minute : event.getMinutes()) {
            if (StringUtils.isEmpty(minute.getMinuteEntry()) && StringUtils.isEmpty(minute.getProtocolContingencyCode())) {
                valid = false;
                GlobalVariables.getMessageMap().putError(String.format("reviewComments[%s].minuteEntry", index),
                        KeyConstants.ERROR_ONLINE_REVIEW_COMMENT_REQUIRED);
            }
            index++;
        }

        GlobalVariables.getMessageMap().clearErrorPath();
        GlobalVariables.getMessageMap().addToErrorPath(
                String.format(ONLINE_REVIEW_ATTACHMENTS_ERROR_PATH, event.getOnlineReviewIndex()));
        index = 0;

        for (ProtocolReviewAttachmentBase reviewAttachment : event.getReviewAttachments()) {
            if (StringUtils.isEmpty(reviewAttachment.getDescription())) {
                valid = false;
                GlobalVariables.getMessageMap().putError(String.format("reviewAttachments[%s].description", index),
                        KeyConstants.ERROR_ONLINE_REVIEW_ATTACHMENT_DESCRIPTION_REQUIRED);
            }
            index++;
        }

        GlobalVariables.getMessageMap().clearErrorPath();
        GlobalVariables.getMessageMap().addToErrorPath(String.format(ONLINE_REVIEW_ERROR_PATH, event.getOnlineReviewIndex()));

        if (StringUtils.isEmpty(protocolOnlineReview.getProtocolOnlineReviewStatusCode())) {
            GlobalVariables.getMessageMap().putError("protocolOnlineReviewStatusCode",
                    KeyConstants.ERROR_ONLINE_REVIEW_STATUS_REQUIRED);
            valid = false;
        }

        if (protocolOnlineReview.getDateRequested() != null && protocolOnlineReview.getDateDue() != null) {
            if ( (DateUtils.isSameDay(protocolOnlineReview.getDateDue(), protocolOnlineReview.getDateRequested())) || 
                    (protocolOnlineReview.getDateDue().after(protocolOnlineReview.getDateRequested())) ) {
                  //no-op,
               }
               else
               {   //dates are not the same or due date is before requested date
                valid = false;
                GlobalVariables.getMessageMap().putError("protocolOnlineReviewDueDate",
                        "error.protocol.onlinereview.create.dueDateAfterRequestedDate", new String[0]);
            }
        }


        return valid;

    }

    public boolean processRouteProtocolOnlineReview(RouteProtocolOnlineReviewEvent event) {

        boolean valid = true;
        KualiRuleService ruleService = KcServiceLocator.getService(KualiRuleService.class);
        valid &= ruleService.applyRules(new SaveProtocolOnlineReviewEvent(event.getProtocolOnlineReviewDocument(), event
                .getMinutes(), event.getOnlineReviewIndex()));
        GlobalVariables.getMessageMap().clearErrorPath();
        GlobalVariables.getMessageMap().addToErrorPath(
                String.format(ONLINE_REVIEW_COMMENTS_ERROR_PATH, event.getOnlineReviewIndex()));

        return valid;
    }


    public boolean processDisapproveOnlineReviewComment(DisapproveProtocolOnlineReviewCommentEvent event) {
        boolean valid = true;
        if (StringUtils.isBlank(event.getReason()) || event.getNoteText().length() > event.getMaxLength()) {
            valid = false;
        }
        return valid;
    }

    public boolean processRejectOnlineReviewComment(RejectProtocolOnlineReviewCommentEvent event) {
        boolean valid = true;
        if (StringUtils.isBlank(event.getReason()) || event.getReason().length() > event.getMaxLength()) {
            valid = false;
        }
        return valid;
    }

    public boolean processDeleteOnlineReview(DeleteProtocolOnlineReviewEvent event) {
        boolean valid = true;
        if (StringUtils.isBlank(event.getReason()) || event.getNoteText().length() > event.getMaxLength()) {
            valid = false;
        }
        return valid;
    }

    public boolean processRules(KcDocumentEventBaseExtension event) {
        boolean retVal = false;
        retVal = event.getRule().processRules(event);
        return retVal;
    }


}
