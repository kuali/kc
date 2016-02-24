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
package org.kuali.kra.iacuc.onlinereview;

import java.util.List;
import java.util.stream.Collectors;

import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.kra.iacuc.actions.submit.IacucProtocolSubmissionStatus;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewBase;
import org.kuali.kra.protocol.onlinereview.ProtocolOnlineReviewLookupableHelperServiceImplBase;

public class IacucProtocolOnlineReviewLookupableHelperServiceImpl extends ProtocolOnlineReviewLookupableHelperServiceImplBase {

    private static final long serialVersionUID = 6830796311898825327L;
    
    @Override
    protected String getDocumentTypeName() {
        return "IacucProtocolOnlineReviewDocument";
    }

    @Override
    protected String getHtmlAction() {
        return "iacucProtocolOnlineReviewRedirect.do";
    }

    @Override
    protected String getProtocolOLRSavedStatusCodeHook() {
        return IacucProtocolOnlineReviewStatus.SAVED_STATUS_CD;
    }
    
    @Override
    protected String getProtocolSubmissionApprovedStatusCodeHook() {
        return IacucProtocolSubmissionStatus.APPROVED;
    }
    
    private String getProtocolSubmissionAdminApprovedStatusCodeHook() {
        return IacucProtocolSubmissionStatus.ADMINISTRATIVELY_APPROVED;
    }
    
    protected List<ProtocolOnlineReviewBase> filterResults(List<ProtocolOnlineReviewBase> results) {
        List<ProtocolOnlineReviewBase> onlineReviews = CollectionUtils.createCorrectImplementationForCollection(results);
        //ensure that only pending submission statuses are shown for online reviews, i.e. do not show reviews assigned but not completed for approved protocols.
        onlineReviews.addAll(results.stream().filter(review -> review.getProtocolOnlineReviewDocument() != null)
                .filter(review -> !(review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionApprovedStatusCodeHook()) ||
                review.getProtocolSubmission().getSubmissionStatusCode().equalsIgnoreCase(getProtocolSubmissionAdminApprovedStatusCodeHook()))).collect(Collectors.toList()));
        return onlineReviews;
    }
}
