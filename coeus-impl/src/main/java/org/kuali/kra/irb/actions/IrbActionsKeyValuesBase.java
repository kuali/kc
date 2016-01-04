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
package org.kuali.kra.irb.actions;

import org.kuali.coeus.common.committee.impl.service.CommitteeServiceBase;
import org.kuali.kra.committee.service.CommitteeService;
import org.kuali.kra.protocol.actions.ProtocolActionsKeyValuesBase;

/**
 * 
 * This class should be extended by IRB  values finder classes.  It creates a single function to get a 
 * BusinessObjectService, so each class need not do that it self.
 */
public abstract class IrbActionsKeyValuesBase extends ProtocolActionsKeyValuesBase {

    private static final long serialVersionUID = 3859318308316835838L;

    @Override
    public CommitteeService getCommitteeService() {
        return (CommitteeService) super.getCommitteeService();
    }

    @Override
    protected Class<? extends CommitteeServiceBase> getCommitteeServiceClassHook() {
        return CommitteeService.class;
    }

}
