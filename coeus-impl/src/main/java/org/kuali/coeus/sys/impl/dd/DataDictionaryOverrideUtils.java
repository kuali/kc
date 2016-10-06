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


import org.kuali.coeus.sys.framework.util.CollectionUtils;
import org.kuali.rice.krad.datadictionary.DataDictionary;
import org.kuali.rice.krad.datadictionary.DataDictionaryException;
import org.springframework.core.io.Resource;
import org.springframework.security.util.InMemoryResource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.kuali.coeus.sys.framework.util.CollectionUtils.*;


public final class DataDictionaryOverrideUtils {

    private DataDictionaryOverrideUtils() {
        throw new UnsupportedOperationException("do not call");
    }

    /**
     * This method creates a new DataDictionary instance based on an old instance conditionally adding/replacing an in-memory data dictionary override file.
     * The internal state of the source data dictionary is copied to the target so that updates to the target data dictionary do not impact the source.
     *
     * If the overrideId already exists in the source data dictionary, then the override file (byte[]) will replace the file with the same id in the same load order.
     * If the overrideId does not exist, then the override file (byte[]) will be added to the end of the load order as a new override.
     */
    public static DataDictionary createNewWithOverride(DataDictionary old, String overrideId, byte[] override) throws DataDictionaryException {
        final DataDictionary modifiedDataDictionary = copyDataDictionaryWithOverride(old);

        addOrReplaceOverrideResource(modifiedDataDictionary, overrideId, new InMemoryResource(override, overrideId));

        modifiedDataDictionary.parseDataDictionaryConfigurationFiles();
        modifiedDataDictionary.validateDD();

        return modifiedDataDictionary;
    }

    /**
     * This method creates a new DataDictionary instance based on an old instance conditionally removing an in-memory data dictionary override file.
     * The internal state of the source data dictionary is copied to the target so that updates to the target data dictionary do not impact the source.
     *
     * If the overrideId already exists in the source data dictionary, then the override file (byte[]) removed.
     * If the overrideId does not exist, then nothing is removed.
     */
    public static DataDictionary createNewRemovingOverride(DataDictionary old, String overrideId) throws DataDictionaryException {
        final DataDictionary modifiedDataDictionary = copyDataDictionaryWithOverride(old);

        removeOverrideResource(modifiedDataDictionary, overrideId);

        modifiedDataDictionary.parseDataDictionaryConfigurationFiles();
        modifiedDataDictionary.validateDD();

        return modifiedDataDictionary;
    }

    private static DataDictionary copyDataDictionaryWithOverride(DataDictionary old) {
        final DataDictionary copiedDataDictionary = new DataDictionary();
        copiedDataDictionary.setModuleDictionaryFiles(copyModuleDictionaryFilesWithOverride(old.getModuleDictionaryFiles()));
        copiedDataDictionary.setModuleLoadOrder(copyModuleLoadOrderWithOverride(old.getModuleLoadOrder()));

        return copiedDataDictionary;
    }

    private static List<String> copyModuleLoadOrderWithOverride(List<String> loadOrder) {
        final List<String> copiedLoadOrder = new ArrayList<>(loadOrder);
        if (!copiedLoadOrder.contains(DataDictionaryOverrideConstants.OVERRIDE)) {
            copiedLoadOrder.add(DataDictionaryOverrideConstants.OVERRIDE);
        }
        return copiedLoadOrder;
    }

    private static Map<String, List<Resource>> copyModuleDictionaryFilesWithOverride(Map<String, List<Resource>> moduleDictionaryFiles) {
        final Map<String, List<Resource>> copiedResources = copyModuleDictionaryFiles(moduleDictionaryFiles);
        copiedResources.putIfAbsent(DataDictionaryOverrideConstants.OVERRIDE, new ArrayList<>());
        return copiedResources;
    }

    private static Map<String, List<Resource>> copyModuleDictionaryFiles(Map<String, List<Resource>> moduleDictionaryFiles) {
        return moduleDictionaryFiles.entrySet().stream()
                .map(entry -> CollectionUtils.<String, List<Resource>>entry(entry.getKey(), new ArrayList<>(entry.getValue())))
                .collect(entriesToMap());
    }

    private static void addOrReplaceOverrideResource(DataDictionary dataDictionary, String overrideId, Resource resource) {
        final List<Resource> overrideFiles = dataDictionary.getModuleDictionaryFiles().get(DataDictionaryOverrideConstants.OVERRIDE);
        int previousOverrideIndex = -1;
        for (int i = 0; i < overrideFiles.size(); i++) {
            final Resource r = overrideFiles.get(i);
            if (r instanceof InMemoryResource && overrideId.equals(r.getDescription())) {
                previousOverrideIndex = i;
                break;
            }
        }

        if (previousOverrideIndex != -1) {
            dataDictionary.getModuleDictionaryFiles().get(DataDictionaryOverrideConstants.OVERRIDE).set(previousOverrideIndex, resource);
        } else {
            dataDictionary.getModuleDictionaryFiles().get(DataDictionaryOverrideConstants.OVERRIDE).add(resource);
        }
    }

    private static void removeOverrideResource(DataDictionary dataDictionary, String overrideId) {
        final List<Resource> overrideFiles = dataDictionary.getModuleDictionaryFiles().get(DataDictionaryOverrideConstants.OVERRIDE);
        for (Iterator<Resource> i = overrideFiles.iterator(); i.hasNext();) {
            final Resource r = i.next();
            if (r instanceof InMemoryResource && overrideId.equals(r.getDescription())) {
                i.remove();
                break;
            }
        }
    }
}
