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
package org.kuali.coeus.common.impl.shortUrl;

import org.kuali.coeus.common.framework.shortUrl.ResourceNotFoundException;
import org.kuali.coeus.common.framework.shortUrl.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;


@Controller("shortUrlController")
public class ShortUrlController {

    public static final String REDIRECT = "redirect:";
    public static final String PROPOSAL_NUMBER = "proposal_number";
    public static final String EPS_PROPOSAL = "eps_proposal";
    public static final String AWARD = "award";
    public static final String AWARD_NUMBER = "award_number";
    public static final String PROPOSAL = "proposal";
    public static final String SUBAWARD = "subaward";
    public static final String NEGOTIATION = "negotiation";
    public static final String NEGOTIATION_ID = "negotiation_id";
    public static final String PROTOCOL = "protocol";
    public static final String PROTOCOL_NUMBER = "protocol_number";
    public static final String IACUC_PROTOCOL = "iacuc_protocol";
    public static final String COMMITTEE = "committee";
    public static final String COMMITTEE_ID = "committee_id";
    public static final String UNABLE_TO_RETRIEVE_DOCUMENT_FOR_ID = "Unable to retrieve document for id %s";
    public static final String SUBAWARD_CODE = "subaward_code";

    @Autowired
    @Qualifier("shortUrlService")
    private ShortUrlService shortUrlService;

    @Transactional
    @RequestMapping(value = "documents/{docId}")
    public String documentShortUrl(@PathVariable String docId, HttpServletResponse response) throws Exception {
        try {
            return REDIRECT + getShortUrlService().constructUrl(docId);
        } catch (ResourceNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Unable to retrieve document Number " + docId);
            return null;
        }
    }

    @Transactional
    @RequestMapping(value = "development-proposals/{proposalNumber}")
    public String propDevShortUrl(@PathVariable String proposalNumber, HttpServletResponse response) throws Exception {
        return redirect((id, table, column) -> getShortUrlService().constructUrl(id, table, column), proposalNumber, EPS_PROPOSAL, PROPOSAL_NUMBER,response);
    }

    @Transactional
    @RequestMapping(value = "awards/{awardNumber}")
    public String awardShortUrl(@PathVariable String awardNumber, HttpServletResponse response) throws Exception {
        return redirect((id, table, column) -> getShortUrlService().constructUrlByVersionStatus(id, table, column), awardNumber, AWARD, AWARD_NUMBER, response);
    }

    @Transactional
    @RequestMapping(value = "proposals/{proposalNumber}")
    public String proposalShortUrl(@PathVariable String proposalNumber, HttpServletResponse response) throws Exception {
        return redirect((id, table, column) -> getShortUrlService().constructUrlByVersionStatus(id, table, column), proposalNumber, PROPOSAL, PROPOSAL_NUMBER, response);
    }

    @Transactional
    @RequestMapping(value = "subawards/{subawardId}")
    public String subawardShortUrl(@PathVariable String subawardId, HttpServletResponse response) throws Exception {
        return redirect((id, table, column) -> getShortUrlService().constructUrlByVersionHistory(id, table, column),subawardId, SUBAWARD, SUBAWARD_CODE, response);
    }

    @Transactional
    @RequestMapping(value = "negotiations/{negotiationId}")
    public String negotiationShortUrl(@PathVariable String negotiationId, HttpServletResponse response) throws Exception {
        return redirect((id, table, column) -> getShortUrlService().constructUrl(id, table, column), negotiationId, NEGOTIATION, NEGOTIATION_ID, response);
    }

    @Transactional
    @RequestMapping(value = "irb-protocols/{protocolNumber}")
    public String irbShortUrl(@PathVariable String protocolNumber, HttpServletResponse response) throws Exception {
        return redirect((id, table, column) -> getShortUrlService().constructUrl(id, table, column), protocolNumber, PROTOCOL, PROTOCOL_NUMBER, response);
    }

    @Transactional
    @RequestMapping(value = "iacuc-protocols/{protocolNumber}")
    public String iacucShortUrl(@PathVariable String protocolNumber, HttpServletResponse response) throws Exception {
        return redirect((id, table, column) -> getShortUrlService().constructUrl(id, table, column), protocolNumber, IACUC_PROTOCOL, PROTOCOL_NUMBER,response);
    }

    @Transactional
    @RequestMapping(value = "committees/{committeeId}")
    public String committeeShortUrl(@PathVariable String committeeId, HttpServletResponse response) throws Exception {
        return redirect((id, table, column) -> getShortUrlService().constructUrlByMaxSequenceNumber(id, table, column), committeeId, COMMITTEE, COMMITTEE_ID, response);
    }

    @FunctionalInterface
    interface ThreeParameterFunction<A, B, C, D> {
        D apply(A a, B b, C c);
    }

    String redirect(ThreeParameterFunction<String, String, String, String> urlFunction, String id, String table, String column, HttpServletResponse response) throws Exception {
        try {
            return REDIRECT + urlFunction.apply(id,table,column);
        } catch (ResourceNotFoundException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, String.format(UNABLE_TO_RETRIEVE_DOCUMENT_FOR_ID,id));
            return null;
        }
    }

    public ShortUrlService getShortUrlService() {
        return shortUrlService;
    }

    public void setShortUrlService(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

}
