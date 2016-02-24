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
package org.kuali.kra.protocol.actions.reviewcomments;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.sys.framework.rule.KcBusinessRule;
import org.kuali.coeus.sys.framework.rule.KcTransactionalDocumentRuleBase;
import org.kuali.kra.infrastructure.KeyConstants;
import org.kuali.kra.protocol.onlinereview.ProtocolReviewAttachmentBase;
import org.kuali.rice.krad.util.GlobalVariables;

/**
 * 
 * This class is validating the description of review attachments is not empty 
 */
public class ProtocolManageReviewAttachmentRule<E extends ProtocolManageReviewAttachmentEventBase<?>> extends KcTransactionalDocumentRuleBase implements KcBusinessRule<E> {

    @Override
    public boolean processRules(E event) {
        boolean isValid = true;


        GlobalVariables.getMessageMap().clearErrorPath();
        GlobalVariables.getMessageMap().addToErrorPath(event.getPropertyName());
        int index = 0;

        for (ProtocolReviewAttachmentBase reviewAttachment : event.getReviewAttachments()) {
            if (StringUtils.isEmpty(reviewAttachment.getDescription())) {
                isValid = false;
                GlobalVariables.getMessageMap().putError(String.format("reviewAttachments[%s].description", index),
                        KeyConstants.ERROR_ONLINE_REVIEW_ATTACHMENT_DESCRIPTION_REQUIRED);
            }
            index++;
        }

        return isValid;
    }
}
