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
package org.kuali.kra.irb.actions.grantexemption;

import org.kuali.kra.irb.Protocol;

/**
 * The Protocol Grant Exemption Service is used to grant an
 * exemption to a protocol.
 */
public interface ProtocolGrantExemptionService {

    /**
     * Grant an exemption to a protocol that is
     * submitted to the IRB office.
     * @param protocol
     * @param grantExemptionBean
     * @throws Exception
     */
    public void grantExemption(Protocol protocol, ProtocolGrantExemptionBean grantExemptionBean) throws Exception;
}
