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

import org.apache.commons.lang3.text.WordUtils;
import org.kuali.coeus.sys.framework.rest.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RestEndpointController extends RestController {

    private static final Pattern MAPPING_REGEX = Pattern.compile("/(api)/(v\\d*)/(.*)/(.*)");

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

    protected Stream<String> getEndpointUrls() {
        return Stream.concat(
                mappings.stream()
                        .filter(mapping -> mapping instanceof SimpleUrlHandlerMapping)
                        .map(mapping -> (SimpleUrlHandlerMapping) mapping)
                        .flatMap(mapping -> mapping.getHandlerMap().entrySet().stream())
                        .filter(entry -> entry.getValue() instanceof SimpleCrudRestControllerBase)
                        .map(Map.Entry::getKey)
                        .filter(url -> MAPPING_REGEX.matcher(url).matches()),
                mappings.stream()
                        .filter(mapping -> mapping instanceof RequestMappingHandlerMapping)
                        .map(mapping -> (RequestMappingHandlerMapping) mapping)
                        .flatMap(mapping -> mapping.getHandlerMethods().entrySet().stream())
                        .filter(entry -> SimpleCrudRestControllerBase.class.isAssignableFrom(entry.getValue().getBeanType()))
                        .map(Map.Entry::getKey)
                        .flatMap(mappingInfo -> mappingInfo.getPatternsCondition().getPatterns().stream())
                        .filter(pattern -> MAPPING_REGEX.matcher(pattern).matches()))
                .filter(url -> !url.equals("/api/v1/endpoints/*"))
                .distinct()
                .sorted();

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
