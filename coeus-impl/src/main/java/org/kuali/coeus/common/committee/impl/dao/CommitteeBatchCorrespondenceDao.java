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
package org.kuali.coeus.common.committee.impl.dao;

import org.kuali.coeus.common.committee.impl.bo.CommitteeBatchCorrespondenceBase;

import java.sql.Date;
import java.util.List;

/**
 * 
 * This class provides enhanced database access functionality.
 */
public interface CommitteeBatchCorrespondenceDao<CBC extends CommitteeBatchCorrespondenceBase> {
    
    /**
     * This method returns all CommitteeBatchCorrespondenceBase of the specified type.  Optionally a date range may be specified to further
     * narrow the result set.
     * @param batchCorrespondenceTypeCode
     * @param startDate - optional, if specified the CommitteeBatchCorrespondenceBase must be created on or after this date.
     * @param endDate - optional, if specified the CommitteeBatchCorrespondenceBase must be created on or before this date.
     * @return List of the requested CommitteeBatchCorrespondenceBase
     */
    List<CBC> getCommitteeBatchCorrespondence(String batchCorrespondenceTypeCode, Date startDate, Date endDate);

}
