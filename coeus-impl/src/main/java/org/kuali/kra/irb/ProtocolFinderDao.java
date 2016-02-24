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
package org.kuali.kra.irb;

/**
 * The ProtocolFinderDao is used to find protocols.
 */
public interface ProtocolFinderDao extends org.kuali.kra.protocol.ProtocolFinderDao {

    /**
     * This method is invoking the super, opened this to reduce the number of cast in
     * individual class files.
     * Find the current protocol given a protocolNumber.  The
     * current protocol is the one with the highest sequence number.
     * @param protocolNumber
     * @return the protocol or null if not found
     */
    Protocol findCurrentProtocolByNumber(String protocolNumber);
}
