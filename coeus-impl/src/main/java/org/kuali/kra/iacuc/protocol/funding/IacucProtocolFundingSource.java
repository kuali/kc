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
package org.kuali.kra.iacuc.protocol.funding;

import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceService;

public class IacucProtocolFundingSource extends ProtocolFundingSourceBase {


    private static final long serialVersionUID = -7739447137061210927L;
    
    /**
     * Constructs a ProtocolFundingSourceBase.
     * @param fundingSourceNumber The user-readable number of the funding source (often the same as fundingSource)
     * @param fundingSourceTypeCode The type code of the funding source
     * @param fundingSourceName The name of the funding source
     * @param fundingSourceTitle The title of the funding source
     */
    public IacucProtocolFundingSource(String fundingSourceNumber, String fundingSourceTypeCode, String fundingSourceName, String fundingSourceTitle) {
        super(fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, fundingSourceTitle);
    }

    public IacucProtocolFundingSource() {
        super();
    }

    @Override
    protected Class<? extends ProtocolFundingSourceService> getProtocolFundingSourceServiceClassHook() {
        return IacucProtocolFundingSourceService.class;
    }

}
