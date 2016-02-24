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
package org.kuali.kra.committee.dao.ojb;

import org.kuali.coeus.common.committee.impl.dao.ojb.CommitteeBatchCorrespondenceDaoOjbBase;
import org.kuali.kra.committee.bo.CommitteeBatchCorrespondence;
import org.kuali.kra.committee.dao.CommitteeBatchCorrespondenceDao;

/**
 * 
 * This class is the OJB implementation of CommitteeBatchCorrespondenceDao 
 * which provides enhanced database access functionality.
 */
public class CommitteeBatchCorrespondenceDaoOjb extends CommitteeBatchCorrespondenceDaoOjbBase<CommitteeBatchCorrespondence> implements CommitteeBatchCorrespondenceDao {

    @Override
    protected Class<CommitteeBatchCorrespondence> getCommitteeBatchCorrespondenceBOClassHook() {
        return CommitteeBatchCorrespondence.class;
    }
}
