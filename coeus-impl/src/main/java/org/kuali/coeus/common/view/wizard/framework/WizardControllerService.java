package org.kuali.coeus.common.view.wizard.framework;

import java.util.List;
import java.util.Map;

public interface WizardControllerService {
    public List<Object> performWizardSearch(Map<String,String> searchCriteria, String lineType);
}
