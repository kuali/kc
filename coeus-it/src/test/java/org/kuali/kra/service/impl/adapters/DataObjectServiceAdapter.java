package org.kuali.kra.service.impl.adapters;

import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.core.api.criteria.QueryResults;
import org.kuali.rice.krad.data.CopyOption;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.data.DataObjectWrapper;
import org.kuali.rice.krad.data.PersistenceOption;
import org.kuali.rice.krad.data.metadata.MetadataRepository;


public class DataObjectServiceAdapter implements DataObjectService {
    @Override
    public <T> T find(Class<T> type, Object id) {
        return null;
    }

    @Override
    public <T> QueryResults<T> findMatching(Class<T> type, QueryByCriteria queryByCriteria) {
        return null;
    }

    @Override
    public <T> QueryResults<T> findAll(Class<T> type) {
        return null;
    }

    @Override
    public <T> T findUnique(Class<T> type, QueryByCriteria queryByCriteria) {
        return null;
    }

    @Override
    public void delete(Object dataObject) {

    }

    @Override
    public <T> void deleteMatching(Class<T> type, QueryByCriteria queryByCriteria) {

    }

    @Override
    public <T> void deleteAll(Class<T> type) {

    }

    @Override
    public <T> T save(T dataObject, PersistenceOption... options) {
        return null;
    }

    @Override
    public void flush(Class<?> type) {

    }

    @Override
    public MetadataRepository getMetadataRepository() {
        return null;
    }

    @Override
    public <T> DataObjectWrapper<T> wrap(T dataObject) {
        return null;
    }

    @Override
    public <T> T copyInstance(T dataObject, CopyOption... options) {
        return null;
    }

    @Override
    public <T> boolean supports(Class<T> type) {
        return false;
    }
}
