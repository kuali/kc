package org.kuali.kra.award.notification;

import org.kuali.coeus.common.notification.impl.NotificationRendererBase;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.config.property.ConfigurationService;

import java.util.HashMap;
import java.util.Map;

public class AwardNoticeNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = -2831418548566311094L;

    private static final String DOCHANDLER_APP_URL_PROP = "application.url";

    public static final String AWARD_NOTICE_ID = "{AWARD_NOTICE_ID}";
    public static final String AWARD_NUMBER = "{AWARD_NUMBER}";
    public static final String DOCHANDLER_PREFIX = "{DOCUMENT_PREFIX}";

    protected Long awardNoticeId;
    protected String awardNumber;

    private ConfigurationService kualiConfigurationService;

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
        String result = null;
        String appUrl = getKualiConfigurationService().getPropertyValueAsString(DOCHANDLER_APP_URL_PROP);
        if (appUrl == null) {
            result = "..";   // default is to back up URL before KEN (relative to base at this server)
        } else {
            result = appUrl;
        }
        return result;
    }

    protected ConfigurationService getKualiConfigurationService() {
        if (kualiConfigurationService == null) {
            kualiConfigurationService = CoreApiServiceLocator.getKualiConfigurationService();
        }
        return kualiConfigurationService;
    }
}
