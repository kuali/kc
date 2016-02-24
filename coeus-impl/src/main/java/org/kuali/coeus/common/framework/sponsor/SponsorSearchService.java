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
package org.kuali.coeus.common.framework.sponsor;

import java.util.List;

public interface SponsorSearchService {

    /**
     * Searches for sponsors where the search string is like the sponsor code, sponsor name, or acronym.  A blank
     * searchString is not allowed.
     * @param searchString the search string.  cannot be blank.
     * @return a list of results.  Will never return null.  Will return a max of 25 items.
     * @throws java.lang.IllegalArgumentException if the searchString is blank
     */
    List<SponsorSearchResult> findSponsors(String searchString);
}
