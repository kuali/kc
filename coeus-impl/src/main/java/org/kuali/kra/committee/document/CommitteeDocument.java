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

package org.kuali.kra.committee.document;

import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.committee.bo.Committee;
import org.kuali.kra.committee.bo.CommitteeSchedule;
import org.kuali.kra.committee.service.CommitteeService;

import java.util.ArrayList;
import java.util.List;

public class CommitteeDocument extends CommitteeDocumentBase<CommitteeDocument, Committee, CommitteeSchedule> {


    private static final long serialVersionUID = -2248266592790548394L;

    @Override
    protected CommitteeDocument getThisHook() {
        return this;
    }

    @Override
    protected Committee getNewCommitteeInstanceHook() {
        return new Committee();
    }

    @Override
    protected CommitteeService getCommitteeService() {
        return KcServiceLocator.getService(CommitteeService.class);
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return new ArrayList<>();
    }

}
