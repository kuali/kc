/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.service.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.kra.SeparatelySequenceableAssociate;
import org.kuali.kra.SequenceOwner;
import org.kuali.kra.Sequenceable;
import org.kuali.kra.service.VersionException;
import org.kuali.kra.service.VersioningService;

/**
 * This service implements generic versioning.
 */
public class VersioningServiceImpl implements VersioningService {
    
    private static final double NANOS_PER_SECOND = 1000000000.0;
    private static final String IN = " in ";
    private static final String SECONDS = " seconds";
    private static final String TO = " to ";
    private static final String VERSIONED_FROM = " versioned from ";
    private static final String PERIOD = ".";
    private static final Log LOGGER = LogFactory.getLog(VersioningServiceImpl.class);
    
    /**
     * {@inheritDoc}
     */
    public <T extends SequenceOwner<?>> T createNewVersion(T oldVersion) throws VersionException {
        final long time = getCurrentTime();
        T newVersion = new SequenceUtils().sequence(oldVersion);
        logVersionOperation(time, oldVersion, newVersion);

        return newVersion;
    }

    /**
     * {@inheritDoc}
     */
    public <T extends SeparatelySequenceableAssociate> T versionAssociate(T oldAssociate) throws VersionException {
        final long time = getCurrentTime();
        SequenceUtils sequenceUtils = new SequenceUtils();
        T newAssociate = sequenceUtils.sequence(oldAssociate);
        logVersionOperation(time, oldAssociate, newAssociate);
        
        return newAssociate;
    }

    /**
     * {@inheritDoc}
     */
    public <T extends SeparatelySequenceableAssociate> List<T> versionAssociates(List<T> oldAssociates) throws VersionException {
        final long time = getCurrentTime();
        SequenceUtils sequenceUtils = new SequenceUtils();
        List<T> newAssociates = sequenceUtils.sequence(oldAssociates);
        
        logVersionOperation(time, oldAssociates, newAssociates);
        return newAssociates;
    }
    
    /**
     * Gets the current time in nanos.
     * @return the current time.
     */
    private static long getCurrentTime() {
        return System.nanoTime();
    }
    
    /**
     * Gets an elapsed time in seconds based on a start and end time.
     * @param startTime the start time in nanos
     * @param endTime the end time in nanos
     * @return the time in seconds
     */
    private static double calcElapsedTimeInSeconds(long startTime, long endTime) {
        return (endTime - startTime) / NANOS_PER_SECOND;
    }
    
    /**
     * Gets the non qualified class name of an object.
     * @param o the object
     * @return the non-qualified class name
     */
    private static String getNonQualifiedClassName(Object o) {
        final String name = o.getClass().getName();
        return name.substring(name.lastIndexOf(PERIOD) + 1);
    }

    /**
     * This method logs the versioning operation
     * @param oldVersion
     * @param newVersion
     */
    private void logVersionOperation(long startTime, Sequenceable oldVersion, Sequenceable newVersion) {
        this.logVersionOperation(startTime, Collections.singletonList(oldVersion), Collections.singletonList(newVersion));
    }
    
    /**
     * This method logs the versioning operation
     * @param oldVersion
     * @param newVersion
     */
    private <T extends Sequenceable> void logVersionOperation(long startTime, List<T> oldVersions, List<T> newVersions) {
        if (LOGGER.isInfoEnabled()) {
            final double seconds = calcElapsedTimeInSeconds(startTime, getCurrentTime());
            
            for (int i = 0; i < oldVersions.size(); i++) {
                final StringBuilder versionLoggingMessage = new StringBuilder()
                    .append(getNonQualifiedClassName(oldVersions.get(i)))
                    .append(VERSIONED_FROM)
                    .append(oldVersions.get(i).getSequenceNumber())
                    .append(TO)
                    .append(newVersions.get(i).getSequenceNumber())
                    .append(IN)
                    .append(seconds)
                    .append(SECONDS);
                
                LOGGER.info(versionLoggingMessage);
            }
        }
    }
}
