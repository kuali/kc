package org.kuali.coeus.common.notification.impl;

import java.util.Map;

import org.kuali.coeus.common.framework.compliance.core.SpecialReview;


public class SpecialReviewNotificationRenderer extends NotificationRendererBase {

    private static final long serialVersionUID = 1946945581636879395L;

    public static final String SPECIAL_REVIEW_PROTOCOL_NUMBER_PARAM = "{PROTOCOL_NUMBER}";

    private NotificationRenderer internalRenderer;
    private SpecialReview<?> specialReview;

    public SpecialReviewNotificationRenderer(NotificationRenderer internalRenderer, SpecialReview<?> specialReview) {
        this.internalRenderer = internalRenderer;
        this.specialReview = specialReview;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> parameters = internalRenderer.getDefaultReplacementParameters();
        parameters.put(SPECIAL_REVIEW_PROTOCOL_NUMBER_PARAM, this.specialReview.getProtocolNumber());
        return parameters;
    }



}