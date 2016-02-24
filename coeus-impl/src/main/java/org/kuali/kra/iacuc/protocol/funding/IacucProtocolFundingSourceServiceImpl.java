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

import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceBase;
import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceServiceImplBase;

public class IacucProtocolFundingSourceServiceImpl extends ProtocolFundingSourceServiceImplBase implements IacucProtocolFundingSourceService {

    @Override
    protected ProtocolFundingSourceBase creatNewProtocolFundingSourceInstanceHook(String fundingSourceNumber,
            String fundingSourceTypeCode, String fundingSourceName, String fundingSourceTitle) {
        return new IacucProtocolFundingSource(fundingSourceNumber, fundingSourceTypeCode, fundingSourceName, fundingSourceTitle);
    }

    @Override
    protected Class<? extends ProtocolDocumentBase> getProtocolDocumentBOClassHook() {
        return IacucProtocolDocument.class;
    }

}
