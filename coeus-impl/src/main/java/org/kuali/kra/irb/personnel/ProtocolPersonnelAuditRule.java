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
package org.kuali.kra.irb.personnel;

import org.kuali.kra.protocol.personnel.ProtocolPersonnelAuditRuleBase;

import static org.kuali.coeus.sys.framework.service.KcServiceLocator.getService;


/**
 * Rules that invoke audit mode for KeyPersonnel
 */
public class ProtocolPersonnelAuditRule extends ProtocolPersonnelAuditRuleBase {
    
    /**
     * This method is to get personnel sevice
     * @return ProtocolPersonnelService
     */
    protected ProtocolPersonnelService getProtocolPersonnelService() {
        return getService(ProtocolPersonnelService.class);
    }
}
