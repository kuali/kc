package org.kuali.kra.s2s.depend;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.s2s.S2sUserAttachedFormContract;
import org.kuali.coeus.propdev.api.s2s.UserAttachedFormService;
import org.kuali.coeus.propdev.impl.s2s.S2sUserAttachedForm;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.rice.krad.data.DataObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("userAttachedFormService")
public class UserAttachedFormServiceImpl implements UserAttachedFormService {

    @Autowired
    @Qualifier("dataObjectService")
    private DataObjectService dataObjectService;

    @Override
    public S2sUserAttachedFormContract findFormByProposalNumberAndNamespace(String proposalNumber, String namespace) {
        if (StringUtils.isBlank(proposalNumber)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        if (StringUtils.isBlank(namespace)) {
            throw new IllegalArgumentException("namespace is blank");
        }

        final Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("proposalNumber", proposalNumber);
        fieldMap.put("namespace", namespace);
        final List<S2sUserAttachedForm> userAttachedForms = dataObjectService.findMatching(S2sUserAttachedForm.class,
                QueryByCriteria.Builder.andAttributes(fieldMap).build()).getResults();

        return userAttachedForms.isEmpty() ? null : userAttachedForms.iterator().next();
    }

    @Override
    public String findFormNameByProposalNumberAndNamespace(String proposalNumber, String namespace) {
        final S2sUserAttachedFormContract form = findFormByProposalNumberAndNamespace(proposalNumber, namespace);
        return form != null ? form.getNamespace() : null;
    }

    @Override
    public List<String> findFormNamespaces(String proposalNumber) {
        if (StringUtils.isBlank(proposalNumber)) {
            throw new IllegalArgumentException("proposalNumber is blank");
        }

        final List<S2sUserAttachedForm> forms = findUserAttachedFormByProposalNumber(proposalNumber);
        final List<String> namespaces = new ArrayList<>();
        for (S2sUserAttachedForm form : forms) {
            namespaces.add(form.getNamespace());
        }

        return namespaces;
    }

    protected List<S2sUserAttachedForm> findUserAttachedFormByProposalNumber(String proposalNumber) {
        final Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("proposalNumber", proposalNumber);
        return ListUtils.emptyIfNull(dataObjectService.findMatching(S2sUserAttachedForm.class,
                QueryByCriteria.Builder.andAttributes(fieldMap).build()).getResults());
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
