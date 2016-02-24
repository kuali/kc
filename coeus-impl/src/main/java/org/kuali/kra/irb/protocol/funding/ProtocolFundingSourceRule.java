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
package org.kuali.kra.irb.protocol.funding;

import org.kuali.kra.protocol.protocol.funding.ProtocolFundingSourceRuleBase;

/**
 * 
 * This class provides business logic for adding a protocol funding source to a protocol. 
 * Also it uses newer paradigm for KC Event/Rule creation for reduced Interface implementation in the ProtocolDocumentRule.
 */
public class ProtocolFundingSourceRule extends ProtocolFundingSourceRuleBase {

    @Override
    protected Class<ProtocolFundingSourceService> getProtocolFundingSourceServiceClassHook() {
        return ProtocolFundingSourceService.class;
    }

}
