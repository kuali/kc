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
package org.kuali.coeus.award.summary;

import java.util.Date;
import java.util.HashMap;

import org.kuali.kra.award.dao.AwardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codiform.moo.Moo;
import com.codiform.moo.curry.Translate;

@Controller("awardSummaryController")
public class AwardSummaryController {

	@Autowired
	@Qualifier("awardDao")
	private AwardDao awardDao;
	
	@RequestMapping(value="/v1/awardSummary")
	public @ResponseBody AwardResults getAwardSummary(@RequestParam(value="updatedSince", required=false) Date updatedSince,
			@RequestParam(value="page", required=false) Integer page, @RequestParam(value="numberPerPage", required=false) Integer numberPerPage) {
		Moo moo = new Moo();
		return Translate.to(AwardResults.class).from(getAwardDao().retrievePopulatedAwardByCriteria(new HashMap<String, Object>(), updatedSince, page, numberPerPage));
	}
	
	public AwardDao getAwardDao() {
		return awardDao;
	}

	public void setAwardDao(AwardDao awardDao) {
		this.awardDao = awardDao;
	}
	
}
