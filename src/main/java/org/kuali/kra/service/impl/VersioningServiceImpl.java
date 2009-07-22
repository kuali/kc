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
    private static final String FOR_OLD = " for old: ";
    private static final double NANOS_PER_SECOND = 1000000000.0;
    private static final String IN = " in ";
    private static final String SECONDS = " seconds";
    private static final String TO = " to ";
    private static final String VERSIONED_FROM = " versioned from ";
    private static final String PERIOD = ".";
    private static final Log LOGGER = LogFactory.getLog(VersioningServiceImpl.class);
    
    /**
     * {@inheritDoc}
     * @see org.kuali.kra.service.VersioningService#createNewVersion(SequenceOwner)
     */
    public <T extends SequenceOwner<?>> T createNewVersion(T oldVersion) throws VersionException {
        final long time = getCurrentTime();
        T newVersion = new SequenceUtils().sequence(oldVersion);
        logVersionOperation(time, oldVersion, newVersion);

        return newVersion;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.service.VersioningService#versionAssociate(SequenceOwner, SeparatelySequenceableAssociate)
     */
    public <T extends SeparatelySequenceableAssociate<U>, U extends SequenceOwner<?>> T versionAssociate(U newVersion, T oldAssociate) throws VersionException {
        final long time = getCurrentTime();
        SequenceUtils sequenceUtils = new SequenceUtils();
        T newAssociate = sequenceUtils.sequence(oldAssociate);
        
        logVersionOperation(time, newVersion, newVersion, oldAssociate);
        return newAssociate;
    }

    /**
     * {@inheritDoc}
     * @see org.kuali.kra.service.VersioningService#versionAssociates(org.kuali.kra.SequenceOwner, java.util.List)
     */
    public <T extends SeparatelySequenceableAssociate<U>, U extends SequenceOwner<?>> List<T> versionAssociates(U newVersion, List<T> oldAssociates) throws VersionException {
        final long time = getCurrentTime();
        SequenceUtils sequenceUtils = new SequenceUtils();
        List<T> newAssociates = sequenceUtils.sequence(newVersion, oldAssociates);
        logVersionOperation(time, newVersion, newVersion, oldAssociates);
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
     * Gets an elapsed time StringBuilder snippet based on the current time.
     * @param startTime the start time in nanos
     * @return the snippet
     */
    private static StringBuilder getElapsedTimeSnippet(long startTime) {
        return new StringBuilder().append(IN)
            .append((getCurrentTime() - startTime) / NANOS_PER_SECOND)
            .append(SECONDS);
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
        if (LOGGER.isInfoEnabled()) {
            StringBuilder versionLoggingMessage = new StringBuilder()
                .append(getNonQualifiedClassName(oldVersion))
                .append(VERSIONED_FROM)
                .append(oldVersion.getSequenceNumber())
                .append(TO)
                .append(newVersion.getSequenceNumber())
                .append(getElapsedTimeSnippet(startTime));
            
            LOGGER.info(versionLoggingMessage);
        }
    }
    
    /**
     * This method logs the versioning operation
     * @param oldVersion
     * @param newVersion
     * @param oldAssociate
     */
    private void logVersionOperation(long startTime, Sequenceable oldVersion, Sequenceable newVersion, SeparatelySequenceableAssociate<?> oldAssociate) {
        if (LOGGER.isInfoEnabled()) {
            StringBuilder versionLoggingMessage = new StringBuilder()
                .append(getNonQualifiedClassName(oldVersion))
                .append(VERSIONED_FROM)
                .append(oldVersion.getSequenceNumber())
                .append(TO)
                .append(newVersion.getSequenceNumber())
                .append(getElapsedTimeSnippet(startTime))
                .append(FOR_OLD)
                .append(getNonQualifiedClassName(oldAssociate));
            
            LOGGER.info(versionLoggingMessage);
        }
    }
    
    /**
     * This method logs the versioning operation
     * @param oldVersion
     * @param newVersion
     * @param associates
     */
    private void logVersionOperation(long startTime, Sequenceable oldVersion, Sequenceable newVersion, 
                                        List<? extends SeparatelySequenceableAssociate<?>> associates) {
        if (LOGGER.isInfoEnabled()) {
            
            StringBuilder versionLoggingMessage = new StringBuilder()
                .append(getNonQualifiedClassName(oldVersion))
                .append(VERSIONED_FROM)
                .append(oldVersion.getSequenceNumber())
                .append(TO)
                .append(newVersion.getSequenceNumber())
                .append(getElapsedTimeSnippet(startTime))
                .append(FOR_OLD);
            
            for (SeparatelySequenceableAssociate<?> associate : associates) {
                versionLoggingMessage.append(getNonQualifiedClassName(associate));
                versionLoggingMessage.append("; ");
            }
            LOGGER.info(versionLoggingMessage);
        }
    }
}
