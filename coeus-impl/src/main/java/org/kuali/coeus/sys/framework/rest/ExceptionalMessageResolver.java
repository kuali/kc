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
