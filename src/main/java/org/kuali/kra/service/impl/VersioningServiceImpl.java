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
 * This service implements generic versioning
 */
public class VersioningServiceImpl implements VersioningService {
    private static final String TO = " to ";
    private static final String VERSIONED_FROM = " versioned from ";
    private static final String PERIOD = ".";
    private static final Log LOGGER = LogFactory.getLog(VersioningServiceImpl.class);
    
    /**
     * @see org.kuali.kra.service.VersioningService#version(org.kuali.kra.Sequenceable)
     */
    public SequenceOwner createNewVersion(SequenceOwner oldVersion) throws VersionException {
        SequenceOwner newVersion = new SequenceUtils().sequence(oldVersion);
        logVersionOperation(oldVersion, newVersion);

        return newVersion;
    }

    /**
     * @see org.kuali.kra.service.VersioningService#versionAssociate(org.kuali.kra.SequenceOwner, org.kuali.kra.SeparatelySequenceableAssociate)
     */
    public SeparatelySequenceableAssociate versionAssociate(SequenceOwner newVersion, 
                                            SeparatelySequenceableAssociate oldAssociate) 
                                            throws VersionException {
        SequenceUtils sequenceUtils = new SequenceUtils();
        SeparatelySequenceableAssociate newAssociate = sequenceUtils.sequence(oldAssociate);
        
        logVersionOperation(newVersion, newVersion, oldAssociate);
        return newAssociate;
    }

    /**
     * @see org.kuali.kra.service.VersioningService#versionAssociates(org.kuali.kra.SequenceOwner, java.util.List)
     */
    public List<? extends SeparatelySequenceableAssociate> versionAssociates(SequenceOwner newVersion, 
                                            List<? extends SeparatelySequenceableAssociate> oldAssociates)
                                            throws VersionException {
        SequenceUtils sequenceUtils = new SequenceUtils();
        List<? extends SeparatelySequenceableAssociate> newAssociates = sequenceUtils.sequence(newVersion, oldAssociates);
        logVersionOperation(newVersion, newVersion, oldAssociates);
        return newAssociates;
    }

    /* 
     * This method logs the versioning operation
     * @param oldVersion
     * @param newVersion
     */
    private void logVersionOperation(Sequenceable oldVersion, Sequenceable newVersion) {
        if (LOGGER.isInfoEnabled()) {
            String className = oldVersion.getClass().getName();
            String versionLoggingMessage = new StringBuilder()
                                                    .append(className.substring(className.lastIndexOf(PERIOD) + 1))
                                                    .append(VERSIONED_FROM)
                                                    .append(oldVersion.getSequenceNumber())
                                                    .append(TO)
                                                    .append(newVersion.getSequenceNumber())
                                                    .toString();
            LOGGER.info(versionLoggingMessage);
        }
    }
    
    /* 
     * This method logs the versioning operation
     * @param oldVersion
     * @param newVersion
     * @param oldAssociate
     */
    private void logVersionOperation(Sequenceable oldVersion, Sequenceable newVersion, SeparatelySequenceableAssociate oldAssociate) {
        if (LOGGER.isInfoEnabled()) {
            String className = oldVersion.getClass().getName();
            String versionLoggingMessage = new StringBuilder()
                                                    .append(className.substring(className.lastIndexOf(PERIOD) + 1))
                                                    .append(VERSIONED_FROM)
                                                    .append(oldVersion.getSequenceNumber())
                                                    .append(TO)
                                                    .append(newVersion.getSequenceNumber())
                                                    .append(" for old attachment: ")
                                                    .append(oldAssociate)
                                                    .toString();
            LOGGER.info(versionLoggingMessage);
        }
    }
    
    /**
     * This method logs the versioning operation
     * @param oldVersion
     * @param newVersion
     * @param associates
     */
    private void logVersionOperation(Sequenceable oldVersion, Sequenceable newVersion, 
                                        List<? extends SeparatelySequenceableAssociate> associates) {
        if (LOGGER.isInfoEnabled()) {
            String className = oldVersion.getClass().getName();
            StringBuilder sb = new StringBuilder()
                                                    .append(className.substring(className.lastIndexOf(PERIOD) + 1))
                                                    .append(VERSIONED_FROM)
                                                    .append(oldVersion.getSequenceNumber())
                                                    .append(TO)
                                                    .append(newVersion.getSequenceNumber())
                                                    .append(" for old attachments: ");
            for(SeparatelySequenceableAssociate associate: associates) {
                sb.append(associate);
                sb.append("; ");
            }
            LOGGER.info(sb.toString());
        }
    }
}
