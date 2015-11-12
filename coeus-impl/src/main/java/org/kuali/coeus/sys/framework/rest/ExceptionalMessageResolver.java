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
package org.kuali.coeus.sys.framework.rest;


import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Allows the response message to be generated from {@link Throwable#getMessage}
 * if not configured statically using the {@link ResponseStatus}.
 */
@Component("exceptionalMessageResolver")
public class ExceptionalMessageResolver extends ResponseStatusExceptionResolver {
    public ExceptionalMessageResolver() {
        setOrder(1);
    }

    @Override
    protected ModelAndView resolveResponseStatus(ResponseStatus responseStatus, HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        final int statusCode = responseStatus.value().value();
        final String reason = responseStatus.reason();
        if (!StringUtils.hasLength(reason)) {
            final String exMessage = ex.getMessage();
            if (StringUtils.hasLength(exMessage)) {
                response.sendError(statusCode, exMessage);
            } else {
                response.sendError(statusCode);
            }
        } else {
            response.sendError(statusCode, reason);
        }
        return new ModelAndView();
    }
}
