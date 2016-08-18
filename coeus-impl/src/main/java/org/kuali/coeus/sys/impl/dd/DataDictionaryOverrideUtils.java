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
package org.kuali.coeus.sys.impl.dd;

import org.kuali.coeus.sys.framework.dd.DataDictionaryOverride;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.datadictionary.DataDictionaryException;
import org.springframework.core.io.Resource;
import org.springframework.security.util.InMemoryResource;


import java.util.List;
import java.util.Map;


public class DataDictionaryOverrideUtils {


    private DataDictionaryOverrideUtils() {
        throw new UnsupportedOperationException("do not call");
    }

    public static DataDictionary createNewWithOverride(DataDictionary old, byte[] override) throws DataDictionaryException {
        final DataDictionary modifiedDataDictionary = new DataDictionary();
        final Resource newOverride = new InMemoryResource(override);

        final Map<String, List<Resource>> previousFiles = old.getModuleDictionaryFiles();
        modifiedDataDictionary.setModuleDictionaryFiles(previousFiles);

        final List<String> previousLoadOrder = old.getModuleLoadOrder();
        if (!previousLoadOrder.contains(DataDictionaryOverrideConstants.OVERRIDE)) {
            previousLoadOrder.add(DataDictionaryOverrideConstants.OVERRIDE);
        }

        modifiedDataDictionary.setModuleLoadOrder(previousLoadOrder);

        modifiedDataDictionary.addModuleDictionaryFile(DataDictionaryOverrideConstants.OVERRIDE, newOverride);
        try {
            modifiedDataDictionary.parseDataDictionaryConfigurationFiles();
            modifiedDataDictionary.validateDD();
        } catch (RuntimeException e) {
            previousFiles.get(DataDictionaryOverrideConstants.OVERRIDE).remove(newOverride);
            throw e;
        }
        return modifiedDataDictionary;
    }
}
