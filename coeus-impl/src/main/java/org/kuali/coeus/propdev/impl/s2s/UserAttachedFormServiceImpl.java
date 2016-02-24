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
package org.kuali.coeus.propdev.impl.s2s;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.propdev.api.s2s.*;
import org.kuali.coeus.propdev.impl.s2s.S2sUserAttachedForm;
import org.kuali.coeus.propdev.impl.s2s.S2sUserAttachedFormAttFile;
import org.kuali.coeus.propdev.impl.s2s.S2sUserAttachedFormFile;
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

    @Override
    public S2sUserAttachedFormFileContract findUserAttachedFormFile(S2sUserAttachedFormContract selectedForm) {
        if (selectedForm == null) {
            throw new IllegalArgumentException("selectedForm is null");
        }

        List<? extends S2sUserAttachedFormFileContract> selectedFormFiles = selectedForm.getS2sUserAttachedFormFileList();
        S2sUserAttachedFormFileContract userAttachedFormFile = null;
        if(selectedFormFiles.isEmpty() || selectedFormFiles.get(0).getXmlFile()==null){
            selectedFormFiles = getDataObjectService().
                    findMatching(S2sUserAttachedFormFile.class,
                            QueryByCriteria.Builder.andAttributes(Collections.singletonMap("s2sUserAttachedFormId", selectedForm.getId())).build()).getResults();
            if(!selectedFormFiles.isEmpty()){
                userAttachedFormFile = selectedFormFiles.get(0);
            }
        }else{
            userAttachedFormFile = selectedFormFiles.get(0);
        }
        return userAttachedFormFile;
    }

    @Override
    public S2sUserAttachedFormAttFileContract findUserAttachedFormAttFile(S2sUserAttachedFormAttContract selectedFormAtt) {
        if (selectedFormAtt == null) {
            throw new IllegalArgumentException("selectedFormAtt is null");
        }

        List<? extends S2sUserAttachedFormAttFileContract> selectedFormAttFiles = selectedFormAtt.getS2sUserAttachedFormAttFiles();
        S2sUserAttachedFormAttFileContract userAttachedFormFile = null;
        if(selectedFormAttFiles.isEmpty() ){
            selectedFormAttFiles = getDataObjectService().
                    findMatching(S2sUserAttachedFormAttFile.class,
                            QueryByCriteria.Builder.andAttributes(Collections.singletonMap("s2sUserAttachedFormAttId", selectedFormAtt.getId())).build()).getResults();
            if(!selectedFormAttFiles.isEmpty()){
                userAttachedFormFile = selectedFormAttFiles.get(0);
            }
        }else{
            userAttachedFormFile = selectedFormAttFiles.get(0);
        }
        return userAttachedFormFile;
    }

    public DataObjectService getDataObjectService() {
        return dataObjectService;
    }

    public void setDataObjectService(DataObjectService dataObjectService) {
        this.dataObjectService = dataObjectService;
    }
}
