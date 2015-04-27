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
package org.kuali.coeus.instprop.impl.summary;

import com.codiform.moo.Moo;
import com.codiform.moo.curry.Translate;
import org.kuali.kra.institutionalproposal.dao.InstitutionalProposalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;

@Controller()
public class InstitutionalProposalSummaryController {

    @Autowired
    @Qualifier("institutionalProposalDao")
    private InstitutionalProposalDao institutionalProposalDao;

    @RequestMapping(value="/v1/institutionalProposalSummary")
    public @ResponseBody
    InstitutionalProposalResults getInstitutionalProposalSummary(@RequestParam(value="updatedSince", required=false) Date updatedSince,
                                 @RequestParam(value="page", required=false) Integer page, @RequestParam(value="numberPerPage", required=false) Integer numberPerPage) {
        Moo moo = new Moo();
        return Translate.to(InstitutionalProposalResults.class).from(getInstitutionalProposalDao().retrievePopulatedInstitutionalProposalByCriteria(new HashMap<String, Object>(), updatedSince, page, numberPerPage));
    }

    public InstitutionalProposalDao getInstitutionalProposalDao() {
        return institutionalProposalDao;
    }

    public void setInstitutionalProposalDao(InstitutionalProposalDao institutionalProposalDao) {
        this.institutionalProposalDao = institutionalProposalDao;
    }
}
