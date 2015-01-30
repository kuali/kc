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
package org.kuali.kra.protocol.protocol.research;

import org.kuali.kra.bo.ResearchAreaBase;
import org.kuali.kra.protocol.ProtocolBase;

import java.util.Collection;


public interface ProtocolResearchAreaService {

    /**
     * When a multi-lookup returns for a set of Research Areas, we must add them to the ProtocolBase Document.
     * Note that we don't add duplicate research areas.
     * NOTE: This should be moved to a service since it is business logic.
     * @param protocolDocument the ProtocolBase Document
     * @param selectedBOs the selected BOs (Research Areas)
     */
    public abstract void addProtocolResearchArea(ProtocolBase protocol, Collection<ResearchAreaBase> selectedBOs);
    
}
