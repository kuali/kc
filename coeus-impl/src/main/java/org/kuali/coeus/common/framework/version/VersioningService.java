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
package org.kuali.coeus.common.framework.version;

import org.kuali.coeus.common.framework.version.sequence.associate.SeparatelySequenceableAssociate;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;

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
