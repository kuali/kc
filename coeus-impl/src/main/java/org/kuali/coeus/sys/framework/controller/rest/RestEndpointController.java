/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 *
 * Copyright 2005-2016 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.controller.rest;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.poi.util.IOUtils;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class RestEndpointController extends RestController {

    private static final Pattern MAPPING_REGEX = Pattern.compile("/(api)/(v\\d*)/(.*)/(.*)");
    private static final String BLUEPRINT_PARM = "_blueprint";

    @Autowired
    private List<HandlerMapping> mappings;

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody Collection<EndpointInfoDto> getAll() {
        return getEndpointUrls()
                .map(this::mappingUrlToDto)
                .collect(Collectors.toList());
    }

    @RequestMapping(value="/{resource}", method=RequestMethod.GET)
    public @ResponseBody EndpointInfoDto get(@PathVariable String resource) {
        return getEndpointUrls()
                .filter(url -> resource.equals(mappingUrlToResourceName(url)))
                .map(this::mappingUrlToDto)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("endpoint not found"));
    }

    @RequestMapping(method= RequestMethod.GET, params={BLUEPRINT_PARM})
    public @ResponseBody Resource getAllBlueprint(HttpServletResponse response) throws IOException {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment;filename=blueprint.zip");

        try(final ByteArrayOutputStream stream = new ByteArrayOutputStream(); ZipOutputStream zipFile = new ZipOutputStream(stream)) {
            getBlueprintEnabledControllers().collect(Collectors.toList()).stream()
                    .forEach(ctrl -> {
                        Resource file = ctrl.getBlueprintResource();
                        try {
                            zipFile.putNextEntry(new ZipEntry(CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(ctrl.getCamelCasePluralName()) + ".md"));
                            zipFile.setMethod(ZipOutputStream.DEFLATED);
                            zipFile.setLevel(5);
                            IOUtils.copy(file.getInputStream(), zipFile);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
            zipFile.flush();
            zipFile.close();
            return new ByteArrayResource(stream.toByteArray());
        }
    }

    @RequestMapping(value="/{resource}", method=RequestMethod.GET, params={BLUEPRINT_PARM})
    public @ResponseBody Resource getBlueprint(@PathVariable String resource, HttpServletResponse response) {
        response.setContentType("text/markdown");
        response.setHeader("Content-Disposition", "attachment;filename=" + resource + ".md");

        return getBlueprintEnabledControllers().filter(ctrl -> resource.equals(CaseFormat.UPPER_CAMEL.converterTo(CaseFormat.LOWER_HYPHEN).convert(ctrl.getCamelCasePluralName())))
                .map(SimpleCrudRestControllerBase::getBlueprintResource)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("endpoint not found"));
    }

    protected Stream<String> getEndpointUrls() {
        return Stream.concat(
                mappings.stream()
                        .filter(mapping -> mapping instanceof SimpleUrlHandlerMapping)
                        .map(mapping -> (SimpleUrlHandlerMapping) mapping)
                        .flatMap(mapping -> mapping.getHandlerMap().entrySet().stream())
                        .filter(entry -> entry.getValue() instanceof SimpleCrudRestControllerBase)
                        .map(this::cast)
                        .map(Map.Entry::getKey)
                        .filter(url -> MAPPING_REGEX.matcher(url).matches()),
                mappings.stream()
                        .filter(mapping -> mapping instanceof RequestMappingHandlerMapping)
                        .map(mapping -> (RequestMappingHandlerMapping) mapping)
                        .flatMap(mapping -> mapping.getHandlerMethods().entrySet().stream())
                        .filter(entry -> SimpleCrudRestControllerBase.class.isAssignableFrom(entry.getValue().getBeanType()))
                        .map(this::cast)
                        .map(Map.Entry::getKey)
                        .flatMap(mappingInfo -> mappingInfo.getPatternsCondition().getPatterns().stream())
                        .filter(pattern -> MAPPING_REGEX.matcher(pattern).matches()))
                .filter(url -> !url.equals("/api/v1/endpoints/*"))
                .distinct()
                .sorted();

    }

    protected Stream<SimpleCrudRestControllerBase> getBlueprintEnabledControllers() {
        final List<String> endpointUrls = getEndpointUrls().collect(Collectors.toList());
        return Stream.concat(
                mappings.stream()
                        .filter(mapping -> mapping instanceof SimpleUrlHandlerMapping)
                        .map(mapping -> (SimpleUrlHandlerMapping) mapping)
                        .flatMap(mapping -> mapping.getHandlerMap().entrySet().stream())
                        .filter(entry -> entry.getValue() instanceof SimpleCrudRestControllerBase)
                        .map(this::cast)
                        .filter(entry -> endpointUrls.stream().anyMatch(url -> url.equals(entry.getKey())))
                        .map(Map.Entry::getValue),
                mappings.stream()
                        .filter(mapping -> mapping instanceof RequestMappingHandlerMapping)
                        .map(mapping -> (RequestMappingHandlerMapping) mapping)
                        .flatMap(mapping -> mapping.getHandlerMethods().entrySet().stream())
                        .filter(entry -> SimpleCrudRestControllerBase.class.isAssignableFrom(entry.getValue().getBeanType()))
                        .filter(entry -> endpointUrls.stream().anyMatch(url -> entry.getKey().getPatternsCondition().getPatterns().contains(url)))
                        .map(entry ->  (SimpleCrudRestControllerBase) (entry.getValue().getBean() instanceof String ? KcServiceLocator.getService((String) entry.getValue().getBean()) : entry.getValue().getBean())))
                .distinct()
                .sorted(Comparator.comparing(mapping -> mapping.getDataObjectClazz().getName()));
    }

    @SuppressWarnings("unchecked")
    private <T> Map.Entry<T, SimpleCrudRestControllerBase> cast(Map.Entry<T, ?> entry) {
        return (Map.Entry<T, SimpleCrudRestControllerBase>) entry;
    }

    protected EndpointInfoDto mappingUrlToDto(String url) {
        return new EndpointInfoDto(this.mappingUrlToResourceName(url),
                WordUtils.capitalizeFully(this.mappingUrlToResourceName(url).replaceAll("-", " ")),
                mappingUrlToUrl(url),
                new Random().nextInt());
    }

    protected String mappingUrlToResourceName(String url) {
        final Matcher m = MAPPING_REGEX.matcher(url);
        return m.matches() ? m.group(3): null;
    }

    protected String mappingUrlToUrl(String url) {
        final Matcher m = MAPPING_REGEX.matcher(url);
        return m.matches() ? url.substring(0, m.start(4)) : null;
    }

    public List<HandlerMapping> getMappings() {
        return mappings;
    }

    public void setMappings(List<HandlerMapping> mappings) {
        this.mappings = mappings;
    }

    private static class EndpointInfoDto {
        private String systemName;
        private String displayName;
        private String endpoint;
        private int order;

        public EndpointInfoDto(String systemName, String displayName, String endpoint, int order) {
            this.systemName = systemName;
            this.displayName = displayName;
            this.endpoint = endpoint;
            this.order = order;
        }

        public String getSystemName() {
            return systemName;
        }

        public void setSystemName(String systemName) {
            this.systemName = systemName;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EndpointInfoDto)) return false;

            EndpointInfoDto that = (EndpointInfoDto) o;

            if (order != that.order) return false;
            if (!systemName.equals(that.systemName)) return false;
            if (!displayName.equals(that.displayName)) return false;
            return endpoint.equals(that.endpoint);

        }

        @Override
        public int hashCode() {
            int result = systemName.hashCode();
            result = 31 * result + displayName.hashCode();
            result = 31 * result + endpoint.hashCode();
            result = 31 * result + order;
            return result;
        }

        @Override
        public String toString() {
            return "EndpointInfoDto{" +
                    "systemName='" + systemName + '\'' +
                    ", displayName='" + displayName + '\'' +
                    ", endpoint='" + endpoint + '\'' +
                    ", order=" + order +
                    '}';
        }
    }
}
