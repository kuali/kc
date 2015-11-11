package org.kuali.coeus.sys.framework.persistence;

import java.util.Collection;

public interface PersistenceVerificationService {

    boolean verifyRelationshipsForDelete(Object bo, Collection<Class<?>> ignoredRelationships);
    boolean verifyRelationshipsForUpsert(Object bo, Collection<Class<?>> ignoredRelationships);
}
