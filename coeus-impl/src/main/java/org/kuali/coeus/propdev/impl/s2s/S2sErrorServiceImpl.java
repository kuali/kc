package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.s2s.S2sErrorContract;
import org.kuali.coeus.propdev.api.s2s.S2sErrorService;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component("s2sErrorService")
public class S2sErrorServiceImpl implements S2sErrorService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public S2sErrorContract findS2sErrorByKey(String key) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("key is blank");
        }

        final List<S2sError> errors = dataObjectService.findMatching(S2sError.class,
                QueryByCriteria.Builder.andAttributes(Collections.singletonMap("key", key)).build()).getResults();
        return !errors.isEmpty() ? errors.get(0) : null;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
