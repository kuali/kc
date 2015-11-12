package org.kuali.coeus.sys.framework.persistence;

import java.util.Collection;

public interface PersistenceVerificationService {

    boolean verifyRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships);
    boolean verifyRelationshipsForUpdate(Object bo, Collection<Class<?>> ignoredRelationships);
    boolean verifyRelationshipsForInsert(Object bo, Collection<Class<?>> ignoredRelationships);
}
