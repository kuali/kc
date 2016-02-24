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
package org.kuali.coeus.award.api;

import java.util.Collection;

import org.kuali.coeus.sys.framework.rest.ResponseResults;

import com.codiform.moo.annotation.CollectionProperty;

public class AwardResults extends ResponseResults {


	@CollectionProperty(source="results", itemClass=AwardSummaryDto.class)
	private Collection<AwardSummaryDto> awards;

	public Collection<AwardSummaryDto> getAwards() {
		return awards;
	}
	public void setAwards(Collection<AwardSummaryDto> awards) {
		this.awards = awards;
	}
}
