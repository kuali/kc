/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.kuali.coeus.sys.framework.controller.rest.SimpleCrudMapBasedRestController;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.rest.SearchResults;
import org.kuali.kra.award.dao.AwardDao;
import org.kuali.kra.award.home.Award;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codiform.moo.curry.Translate;

public class AwardController extends SimpleCrudMapBasedRestController<Award> {

	@Autowired
	@Qualifier("awardDao")
	private AwardDao awardDao;
	
	@RequestMapping(params="summary", method=RequestMethod.GET)
	public @ResponseBody AwardResults getAwardSummary(@RequestParam(value="updatedSince", required=false) Instant updatedSince,
			@RequestParam(value="page", required=false) Integer page, @RequestParam(value="limit", required=false) Integer limit) {
		assertUserHasReadAccess();
		AwardResults result = Translate.to(AwardResults.class).from(getAwards(updatedSince == null ? null : Date.from(updatedSince), page, limit));
		if (result == null || result.getCount() == null || result.getCount() == 0) {
			throw new ResourceNotFoundException("not found");
		}
		return result;
	}

	SearchResults<Award> getAwards(Date updatedSince, Integer page, Integer numberPerPage) {
		return getAwardDao().retrieveActiveAwardsByCriteria(new HashMap<>(), updatedSince, page, numberPerPage);
	}
	
	public AwardDao getAwardDao() {
		return awardDao;
	}

	public void setAwardDao(AwardDao awardDao) {
		this.awardDao = awardDao;
	}

	@Override
	public List<String> getExposedProperties() {
		return super.getExposedProperties().stream().filter(p -> !"documentNumber".equals(p)).collect(Collectors.toList());
	}
}
