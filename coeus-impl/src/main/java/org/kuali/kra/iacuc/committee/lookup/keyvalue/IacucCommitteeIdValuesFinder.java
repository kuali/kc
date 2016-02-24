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
package org.kuali.kra.iacuc.committee.lookup.keyvalue;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBase;
import org.kuali.coeus.common.committee.impl.bo.CommitteeType;
import org.kuali.coeus.common.committee.impl.lookup.keyvalue.CommitteeIdValuesFinderBase;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;

public class IacucCommitteeIdValuesFinder extends CommitteeIdValuesFinderBase {


    private static final long serialVersionUID = -5083716441320247996L;

    @Override
    protected Class<? extends CommitteeBase> getCommitteeBOClassHook() {
        return IacucCommittee.class;
    }

    @Override
    protected String getCommitteeTypeCodeHook() {
        return CommitteeType.IACUC_TYPE_CODE;
    }

}
