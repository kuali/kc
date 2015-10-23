package org.kuali.coeus.sys.impl.mq.rest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.coeus.sys.framework.mq.MessageFactory;
import org.kuali.coeus.sys.framework.mq.Producer;
import org.kuali.coeus.sys.framework.mq.rest.RestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

/**
 * This creates and sends a jms message that encapsulates a REST request that should happen at some point in the future.
 */
@Component("restMessageProducer")
public class RestMessageProducer implements Producer<RestRequest> {

    private static Log LOG = LogFactory.getLog(RestMessageProducer.class);

    @Autowired
    @Qualifier("restJmsOperations")
    private JmsOperations restJmsOperations;

    @Autowired
    @Qualifier("restDestinationRegistry")
    private RestDestinationRegistry restDestinationRegistry;

    @Override
    public void sendMessage(final RestRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }

        if (request.getMethod() == null) {
            throw new IllegalArgumentException("request must have a non null method");
        }

        if (StringUtils.isBlank(request.getDestination())) {
            throw new IllegalArgumentException("request must have a non null and non blank destination");
        }

        if (restDestinationRegistry.isEnabled(request.getDestination())) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Producing Message " + request);
            }
            restJmsOperations.convertAndSend(MessageFactory.createObjectMessage(request));
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Destination Disabled.  Ignoring Message " + request);
            }
        }
    }

    public JmsOperations getRestJmsOperations() {
        return restJmsOperations;
    }

    public void setRestJmsOperations(JmsOperations restJmsOperations) {
        this.restJmsOperations = restJmsOperations;
    }

    public RestDestinationRegistry getRestDestinationRegistry() {
        return restDestinationRegistry;
    }

    public void setRestDestinationRegistry(RestDestinationRegistry restDestinationRegistry) {
        this.restDestinationRegistry = restDestinationRegistry;
    }
}
