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
package org.kuali.kra.iacuc.notification;

import org.kuali.kra.iacuc.IacucProtocol;

import java.util.Map;

/**
 * Renders additional fields for the Batch Correspondence notification.
 */
public class IacucBatchCorrespondenceNotificationRenderer extends IacucProtocolNotificationRenderer {

    private static final long serialVersionUID = -3536458485352249776L;

    private Long detailId;
    private String protocolCorrespondenceType;
    private String userFullName;

    /**
     * Constructs a Batch Correspondence notification renderer.
     * 
     * @param protocol
     * @param detailId
     * @param protocolCorrespondenceType
     * @param userFullName
     */
    public IacucBatchCorrespondenceNotificationRenderer(IacucProtocol protocol, Long detailId, String protocolCorrespondenceType, String userFullName) {
        super(protocol);
        
        this.detailId = detailId;
        this.protocolCorrespondenceType = protocolCorrespondenceType;
        this.userFullName = userFullName;
    }

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getProtocolCorrespondenceType() {
        return protocolCorrespondenceType;
    }

    public void setProtocolCorrespondenceType(String protocolCorrespondenceType) {
        this.protocolCorrespondenceType = protocolCorrespondenceType;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    @Override
    public Map<String, String> getDefaultReplacementParameters() {
        Map<String, String> params = super.getDefaultReplacementParameters();
        params.put("{DETAIL_ID}", detailId.toString());
        params.put("{PROTOCOL_CORRESPONDENCE_TYPE}", protocolCorrespondenceType);
        return params;
    }

}
