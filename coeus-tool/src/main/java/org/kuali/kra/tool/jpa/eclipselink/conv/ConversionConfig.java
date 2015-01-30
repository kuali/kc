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
package org.kuali.kra.tool.jpa.eclipselink.conv;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The global config for this conversion utility.
 */
public final class ConversionConfig {

    private String projectHomeDir;
    private String projectResourceDir;
    private String projectSourceDir;
    private List<String> ojbRepositoryFiles = new ArrayList<String>();
    private Map<String, String> converters = new HashMap<String, String>();

    static class ConfigHolder {
        static final ConversionConfig INSTANCE = new ConversionConfig();
    }

    private ConversionConfig() {
        //for singleton
    }

    public static ConversionConfig getInstance() {
        return ConfigHolder.INSTANCE;
    }

    public String getProjectHomeDir() {
        return projectHomeDir;
    }

    public void setProjectHomeDir(String projectHomeDir) {
        this.projectHomeDir = projectHomeDir;
    }

    public String getProjectResourceDir() {
        return projectResourceDir;
    }

    public void setProjectResourceDir(String projectResourceDir) {
        this.projectResourceDir = projectResourceDir;
    }

    public String getProjectSourceDir() {
        return projectSourceDir;
    }

    public void setProjectSourceDir(String projectSourceDir) {
        this.projectSourceDir = projectSourceDir;
    }

    public List<String> getOjbRepositoryFiles() {
        return new ArrayList<String>(ojbRepositoryFiles);
    }

    public void setOjbRepositoryFiles(List<String> ojbRepositoryFiles) {
        this.ojbRepositoryFiles = new ArrayList<String>(ojbRepositoryFiles);
    }

    public void addOjbRepositoryFile(String file) {
        this.ojbRepositoryFiles.add(file);
    }

    public Map<String, String> getConverters() {
        return new HashMap<String, String>(converters);
    }

    public void setConverters(Map<String, String> converters) {
        this.converters = new HashMap<String, String>(converters);
    }

    public void addConverter(String key, String value) {
        this.converters.put(key, value);
    }
}
