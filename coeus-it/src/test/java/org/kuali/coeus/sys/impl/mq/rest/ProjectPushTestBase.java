package org.kuali.coeus.sys.impl.mq.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.load.Dereferencing;
import com.github.fge.jsonschema.core.load.configuration.LoadingConfiguration;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.kuali.coeus.coi.framework.Project;
import org.kuali.coeus.coi.framework.ProjectPublisher;
import org.kuali.coeus.coi.framework.ProjectRetrievalService;
import org.kuali.coeus.sys.framework.mq.MessageFactory;
import org.kuali.coeus.sys.framework.mq.rest.RestRequest;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.test.infrastructure.KcIntegrationTestBase;
import org.kuali.rice.core.api.config.property.ConfigurationService;
import org.kuali.rice.core.api.util.ClassLoaderUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import javax.jms.ObjectMessage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class ProjectPushTestBase extends KcIntegrationTestBase {

    public static final String PROJECT_SCHEMA = "classpath:schema/completeProjectSchema.json";

    protected boolean isCoiEnabled() {
        return getConfigurationService().getPropertyValueAsBoolean(Constants.COI_PROJECTS_ENABLED);
    }

    public ConfigurationService getConfigurationService() {
        return KcServiceLocator.getService(ConfigurationService.class);
    }

    public RestOperations getRestOperations() {
        return KcServiceLocator.getService(RestOperations.class);
    }

    public ObjectMessage getMessageFromProject(Project project) {
        final RestRequest request = new RestRequest();
        request.setDestination(Constants.COI_PROJECTS);
        request.setHeaders(Collections.singletonMap(Constants.CONTENT_TYPE, Collections.singletonList(Constants.APPLICATION_JSON)));
        try {
            request.setBody(new ObjectMapper().writeValueAsString(project));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        request.setMethod(org.kuali.coeus.sys.framework.mq.rest.HttpMethod.POST);
        return MessageFactory.createObjectMessage(request);
    }

    public RestMessageConsumerTest getRestMessageConsumer() {
        return new RestMessageConsumerTest();
    }

    class RestMessageConsumerTest extends RestMessageConsumer {
        @Override
        public String retrieveUrl(RestRequest request) {
            return getConfigurationService().getPropertyValueAsString("coi.projects.url");
        }

        @Override
        protected void makeCall(String url, Map<String, List<String>> params, HttpEntity<String> entity, HttpMethod method) {
            try {
                ResponseEntity<Void> response = getRestOperations().exchange(url, method, entity, Void.class, params);
                LOG.debug(createSentMsg(url, params, entity, method) + " status code " + response.getStatusCode());
                Assert.assertTrue(response.getStatusCode().equals(HttpStatus.OK));
            } catch (UnknownHttpStatusCodeException e) {
                LOG.error(createSentMsg(url, params, entity, method) + " status code " + e.getRawStatusCode());
                throw e;
            } catch(HttpServerErrorException e) {
                if(e.getStatusCode().equals(HttpStatus.BAD_GATEWAY)) {
                    LOG.error("Project endpoint unreachable.");
                } else {
                    throw e;
                }
            } catch(ResourceAccessException e) {
                LOG.error("Project endpoint unreachable.");
            } catch (RuntimeException e) {
                LOG.error(createSentMsg(url, params, entity, method) + " in error " + e.getMessage());
                throw e;
            }
        }

        @Override
        public ConfigurationService getConfigurationService() {
            return KcServiceLocator.getService(ConfigurationService.class);
        }
    }

    @Test
    public void testSchema() throws Exception {
        final Project project = getProjectRetrievalService().retrieveProject(getDocumentIdentifier());
        RestRequest request = (RestRequest) getMessageFromProject(project).getObject();
        JsonNode projectData = JsonLoader.fromString(request.getBody());
        JsonNode projectSchema = JsonLoader.fromFile(getFile());
        final LoadingConfiguration cfg = LoadingConfiguration.newBuilder()
                .dereferencing(Dereferencing.INLINE).freeze();
        final JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
                .setLoadingConfiguration(cfg).freeze();
        final JsonSchema schema = factory.getJsonSchema(projectSchema);
        ProcessingReport report;
        report = schema.validate(projectData);
        LOG.info(report);
        Assert.assertTrue(report.isSuccess());
    }

    public abstract String getDocumentIdentifier();

    public ProjectPublisher getProjectPublisher() {
        return KcServiceLocator.getService(ProjectPublisher.class);
    }

    protected File getFile() throws IOException {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader(ClassLoaderUtils.getDefaultClassLoader());
        Resource schemaResource = resourceLoader.getResource(PROJECT_SCHEMA);
        return schemaResource.getFile();
    }

    public abstract ProjectRetrievalService getProjectRetrievalService();
}
