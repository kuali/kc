package org.kuali.kra.s2s.formmapping;

import java.util.List;
import java.util.Map;

public interface FormMappingService {

    FormMappingInfo getFormInfo(String namespace);

    FormMappingInfo getFormInfo(String proposalNumber, String namespace);

    Map<String, FormMappingInfo> getBindings();

    Map<Integer, List<String>> getSortedNameSpaces();
}
