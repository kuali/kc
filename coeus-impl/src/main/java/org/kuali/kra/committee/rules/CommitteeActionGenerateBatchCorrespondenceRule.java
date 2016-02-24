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
package org.kuali.kra.committee.rules;

import org.kuali.coeus.common.committee.impl.rules.CommitteeActionGenerateBatchCorrespondenceRuleBase;
import org.kuali.kra.irb.correspondence.BatchCorrespondence;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceTemplateService;
import org.kuali.kra.irb.correspondence.ProtocolCorrespondenceType;
import org.kuali.kra.protocol.correspondence.BatchCorrespondenceBase;
import org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTypeBase;

/**
 * 
 * This class implements the business rules for submitting a generate batch correspondence request.
 */
public class CommitteeActionGenerateBatchCorrespondenceRule extends CommitteeActionGenerateBatchCorrespondenceRuleBase {

    @Override
    protected Class<? extends BatchCorrespondenceBase> getBatchCorrespondenceBOClassHook() {
        return BatchCorrespondence.class;
    }

    @Override
    protected Class<? extends org.kuali.kra.protocol.correspondence.ProtocolCorrespondenceTemplateService> getProtocolCorrespondenceTemplateServiceClassHook() {
        return ProtocolCorrespondenceTemplateService.class;
    }

    @Override
    protected Class<? extends ProtocolCorrespondenceTypeBase> getProtocolCorrespondenceTypeBOClassHook() {
        return ProtocolCorrespondenceType.class;
    }

}
