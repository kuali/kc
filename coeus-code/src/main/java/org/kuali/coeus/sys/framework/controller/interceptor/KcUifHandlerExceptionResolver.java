package org.kuali.coeus.sys.framework.controller.interceptor;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A KC specific exception handler that delegates to a rice krad exception handler.
 */
//not exactly sure how to do this with autowiring since exception handlers are special
public class KcUifHandlerExceptionResolver implements org.springframework.web.servlet.HandlerExceptionResolver, Ordered {

    private HandlerExceptionResolver innerHandler;

    public int getOrder() {
        return Integer.MIN_VALUE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        //Avoids NPE in rice incident handler
        if (handler == null) {
            return innerHandler.resolveException(request, response, NullHandler.INSTANCE, ex);
        }

        return innerHandler.resolveException(request, response, NullHandler.INSTANCE, ex);
    }

    public void setInnerHandler(HandlerExceptionResolver innerHandler) {
        this.innerHandler = innerHandler;
    }

    private static final class NullHandler{
        private static final NullHandler INSTANCE = new NullHandler();
    }
}
