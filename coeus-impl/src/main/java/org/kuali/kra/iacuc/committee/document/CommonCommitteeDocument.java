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
package org.kuali.kra.iacuc.committee.document;

import org.kuali.coeus.common.committee.impl.document.CommitteeDocumentBase;
import org.kuali.coeus.common.framework.custom.DocumentCustomData;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.iacuc.committee.bo.IacucCommittee;
import org.kuali.kra.iacuc.committee.bo.IacucCommitteeSchedule;
import org.kuali.kra.iacuc.committee.service.IacucCommitteeService;

import java.util.ArrayList;
import java.util.List;

public class CommonCommitteeDocument extends CommitteeDocumentBase<CommonCommitteeDocument, IacucCommittee, IacucCommitteeSchedule> {


    private static final long serialVersionUID = 7253898081493879835L;

    @Override
    protected CommonCommitteeDocument getThisHook() {
        return this;
    }

    @Override
    protected IacucCommittee getNewCommitteeInstanceHook() {
        return new IacucCommittee();
    }

    @Override
    protected IacucCommitteeService getCommitteeService() {
        return KcServiceLocator.getService(IacucCommitteeService.class);
    }

    @Override
    public List<? extends DocumentCustomData> getDocumentCustomData() {
        return new ArrayList<>();
    }

}
