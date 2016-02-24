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
package org.kuali.coeus.sys.impl.sensitive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to load sensitive fields resource file and store each
 * field in String ArrayList. Also, uses thread safe singleton pattern. 
 */
@Component("sensitiveFieldResourceLoader")
public class SensitiveFieldResourceLoader implements SensitiveFields {
    
    private List<String> listOfFields = new ArrayList<String>();

    @Autowired
    public SensitiveFieldResourceLoader(@Value("classpath:org/kuali/coeus/sys/impl/sensitive/sensitive-fields.txt") Resource fileResource) {
        buildList(fileResource);
    }

    private void buildList(Resource resource) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line = reader.readLine();
            while (line != null) {
                listOfFields.add(line);
                line = reader.readLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method provides list of sensitive fields. 
     * @return
     */
    public List<String> getSensitiveFields() {
        return listOfFields;
    }
}
