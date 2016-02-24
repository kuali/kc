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
package org.kuali.kra.award.notification;

import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;

import java.util.HashMap;
import java.util.Map;

public class AwardNoticeNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = -2831418548566311094L;

    private static final String DOCHANDLER_APP_URL_PROP = "application.url";

    private static final String AWARD_NOTICE_ID = "{AWARD_NOTICE_ID}";
    private static final String AWARD_NUMBER = "{AWARD_NUMBER}";
    private static final String DOCHANDLER_PREFIX = "{DOCUMENT_PREFIX}";

    protected Long awardNoticeId;
    protected String awardNumber;

    public AwardNoticeNotificationRenderer(Long awardNoticeId, String awardNumber) {
        this.awardNoticeId = awardNoticeId;
        this.awardNumber = awardNumber;
    }

    @Override
    public Map<String,String> getDefaultReplacementParameters() {
        Map<String,String> replacementParams = new HashMap<String,String>();
        replacementParams.put(AWARD_NOTICE_ID, awardNoticeId.toString());
        replacementParams.put(AWARD_NUMBER, awardNumber);
        replacementParams.put(DOCHANDLER_PREFIX, getDocumentLocation());

        return replacementParams;
    }

    // Overriding this method since NotificationRendererBase doesn't form the URL correctly
    protected String getDocumentLocation() {
        String appUrl = getKualiConfigurationService().getPropertyValueAsString(DOCHANDLER_APP_URL_PROP);
        if (appUrl == null) {
            // default is to back up URL before KEN (relative to base at this server)
           return "..";
        } else {
            return appUrl;
        }
    }
}
