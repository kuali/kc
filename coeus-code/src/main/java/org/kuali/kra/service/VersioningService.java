/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.kra.service;

import org.kuali.coeus.common.framework.sequence.owner.SequenceOwner;
import org.kuali.kra.SeparatelySequenceableAssociate;

import java.util.List;

/**
 * This interface defines generic versioning behavior. Versioning always implies that 
 * a new SequenceOwner object is created from the original and its identifier is set 
 * to null.
 *  
 * All 1:1 and 1:M associations are copied and the new copies are assigned with the 
 * new SequenceOwner and their persistence identity is reset. Many:Many associations are 
 * handled differently.
 * 
 * They are also copied as part of the deep copy process. Making them transient would 
 * avoid serialization in the deep copy, but could also prevent persistence using JPA 
 * or OJB. So we eat the memory consumption during the deep copy where both the new 
 * and old sequence owner simultaneously have the same collections of M:N associates. 
 * As soon as the old sequence owner reference is not needed (i.e. just after versioning), 
 * it should set to null to allow garbage collection of its associated references.
 * 
 * In a simple versioning not involving the update of a M:N associate, the M:N 
 * associates' persistence identifities are NOT cleared. When the new SequenceOwner is saved
 * it's expected a new join table record will be added linking the new SequenceOwner
 * to the existing M:N associate.
 * 
 * In a versioning involving the update of one or more M:N associates, the M:N 
 * associates' persistence identifities are cleared, thus making them new versions.  The
 * newly versioned attachment must be manually associated with the new owner.
 * When the new SequenceOwner is saved, the new M:N associate will also 
 * be saved, and it's expected a new join table record will be automatically added linking the 
 * new SequenceOwner to the new M:N associate.
 */
public interface VersioningService {
    /**
     * Cause old version of SequenceOwner object to be versioned to new version
     * SeparatelySequenceableAssociate BOs are also copied, but their identifiers are left.
     * @param <T> the type of SequenceOwner to version.
     * @param oldVersion
     * @return The newly sequenced version of the SequenceOwner
     * @throws VersionException
     */
    <T extends SequenceOwner<?>> T createNewVersion(T oldVersion) throws VersionException;

    /**
     * Cause new version of specified separately sequenced associate is copied.
     * @param <T> the type of SeparatelySequenceableAssociate to version.
     * @param oldAssociate
     * @return The newly versioned associate
     * @throws VersionException
     */
    <T extends SeparatelySequenceableAssociate> T versionAssociate(T oldAssociate) throws VersionException;
    
    /**
     * Cause new version of specified separately sequenced associates to be copied.
     * @param <T> the type of SeparatelySequenceableAssociate to version.
     * @param oldAssociates
     * @return The list of newly versioned associates
     * @throws VersionException
     */
    <T extends SeparatelySequenceableAssociate> List<T> versionAssociates(List<T> oldAssociates) throws VersionException;
}
