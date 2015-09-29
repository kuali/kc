package org.kuali.coeus.coi.impl;


import org.codehaus.jackson.map.ObjectMapper;
import org.kuali.coeus.coi.framework.Project;
import org.kuali.coeus.coi.framework.ProjectPublisher;
import org.kuali.coeus.sys.framework.mq.Producer;
import org.kuali.coeus.sys.framework.mq.rest.HttpMethod;
import org.kuali.coeus.sys.framework.mq.rest.RestRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;

@Component("projectPublisher")
public class ProjectPublisherImpl implements ProjectPublisher {

    private static final String COI_PROJECTS = "coi.projects";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    @Autowired
    @Qualifier("restMessageProducer")
    private Producer<RestRequest> restMessageProducer;

    @Autowired
    @Qualifier("beanValidator")
    private Validator validator;

    @Override
    public void publishProject(Project project) {
        final Set<ConstraintViolation<Project>> violations = validator.validate(project);
        if (!violations.isEmpty()){
            throw new IllegalArgumentException(violations.toString());
        }

        final RestRequest request = new RestRequest();
        request.setDestination(COI_PROJECTS);
        request.setHeaders(Collections.singletonMap(CONTENT_TYPE, Collections.singletonList(APPLICATION_JSON)));
        try {
            request.setBody(new ObjectMapper().writeValueAsString(project));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        request.setMethod(HttpMethod.POST);

        restMessageProducer.sendMessage(request);
    }

    public Producer<RestRequest> getRestMessageProducer() {
        return restMessageProducer;
    }

    public void setRestMessageProducer(Producer<RestRequest> restMessageProducer) {
        this.restMessageProducer = restMessageProducer;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }
}
