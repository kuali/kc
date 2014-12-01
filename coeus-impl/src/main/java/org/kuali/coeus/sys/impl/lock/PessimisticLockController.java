package org.kuali.coeus.sys.impl.lock;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.krad.data.DataObjectService;
import org.kuali.rice.krad.document.authorization.PessimisticLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;

@Controller
public class PessimisticLockController {

    private static final Log LOG = LogFactory.getLog(PessimisticLockController.class);

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Autowired
    @Qualifier("parameterService")
    private ParameterService parameterService;

    @Transactional @RequestMapping(value = "/sys/pessimisticLock/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePessimisticLock(@PathVariable("id") String id) throws Exception {
        if (StringUtils.isNotBlank(id) && NumberUtils.isNumber(id) &&
                parameterService.getParameterValueAsBoolean("KC-GEN", "All", PessimisticLockConstants.ALLOW_CLEAR_PESSIMISTIC_LOCK_PARM)) {

            try {
                //not using PessimisticLockService since there is security constraints on that api that doesn't allow certain users to call it
                dataObjectService.deleteMatching(PessimisticLock.class, QueryByCriteria.Builder.andAttributes(Collections.singletonMap("id", id)).build());
            } catch (IllegalArgumentException|DataAccessException e) {
                //be lenient with delete failures as per normal REST convention
                LOG.warn("Failed to delete pessimistic lock with id: " + id);
            }
        }

        return new ResponseEntity<String>(HttpStatus.ACCEPTED);
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }

    public ParameterService getParameterService() {
        return parameterService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }
}
