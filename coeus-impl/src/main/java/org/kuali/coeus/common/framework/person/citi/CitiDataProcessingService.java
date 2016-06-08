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
package org.kuali.coeus.common.framework.person.citi;


public interface CitiDataProcessingService {

    /**
     * This method processes all the of the citi records that are Staged, or Errored and does the following:
     *
     * 1) clears existing error messages for each record
     * 2) validates each record, saving any validation errors and marking Errored status if applicable
     * 3) Using the mapping table, generates new or updates existing training records, and marks the record as processed.
     *
     * At the end of this method, all records should be marked as either Processed or Errored.  If Errored, there
     * should be error messages present for the record.
     */
    void processRecords();
}
