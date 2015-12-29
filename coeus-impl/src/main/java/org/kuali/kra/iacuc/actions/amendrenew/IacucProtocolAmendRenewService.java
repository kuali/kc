/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.kra.iacuc.actions.amendrenew;

import org.kuali.kra.iacuc.IacucProtocol;
import org.kuali.kra.iacuc.IacucProtocolDocument;
import org.kuali.kra.iacuc.actions.notifyiacuc.IacucProtocolNotifyIacucBean;
import org.kuali.kra.protocol.ProtocolDocumentBase;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendRenewService;
import org.kuali.kra.protocol.actions.amendrenew.ProtocolAmendmentBean;

import java.util.Collection;

public interface IacucProtocolAmendRenewService extends ProtocolAmendRenewService {

    public static final String AMEND_RENEW_CONTINUATION_ALLOW_NEW_PROTOCOL_DOCUMENT = "onAmendAndRenewAllowNewProtocolDocument";
    
    public Collection<IacucProtocol> getContinuations(String protocolNumber) throws Exception;
    
    public String createContinuation (IacucProtocolDocument protocolDocument, String continuationSummary) throws Exception;
    
    public String createContinuationWithAmendment(IacucProtocolDocument protocolDocument, ProtocolAmendmentBean amendmentBean) throws Exception;

    public String createFYI(ProtocolDocumentBase protocolDocument, IacucProtocolNotifyIacucBean fyiBean) throws Exception;

}
