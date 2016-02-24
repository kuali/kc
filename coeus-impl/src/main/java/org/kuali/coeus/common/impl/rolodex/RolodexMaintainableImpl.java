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
package org.kuali.coeus.common.impl.rolodex;

import org.apache.commons.lang3.StringUtils;
import org.kuali.coeus.common.framework.person.KcPerson;
import org.kuali.coeus.common.framework.person.KcPersonService;
import org.kuali.coeus.common.framework.rolodex.Rolodex;
import org.kuali.coeus.sys.framework.gv.GlobalVariableService;
import org.kuali.coeus.sys.framework.service.KcServiceLocator;
import org.kuali.kra.infrastructure.Constants;
import org.kuali.kra.maintenance.KraMaintainableImpl;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.coreservice.framework.parameter.ParameterService;
import org.kuali.rice.kns.document.MaintenanceDocument;
import org.kuali.rice.kns.maintenance.Maintainable;
import org.kuali.rice.kns.web.ui.Field;
import org.kuali.rice.kns.web.ui.Row;
import org.kuali.rice.kns.web.ui.Section;
import org.kuali.rice.krad.data.platform.MaxValueIncrementerFactory;
import org.kuali.rice.krad.service.SequenceAccessorService;
import org.kuali.rice.location.api.country.Country;
import org.kuali.rice.location.api.country.CountryService;
import org.kuali.rice.location.framework.state.StateValuesFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component("rolodexMaintainableImpl")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RolodexMaintainableImpl extends KraMaintainableImpl {


    private static final long serialVersionUID = -6436670290567992063L;
    
    public static final String ROLODEX_ID_SEQUENCE_NAME = "SEQ_ROLODEX_ID";
    public static final String AUTO_GEN_ROLODEX_ID_PARM = "AUTO_GENERATE_NON_EMPLOYEE_ID";
    public static final String SECTION_ID = "Edit Address Book";
    public static final String ROLODEX_ID_NAME = "rolodexId";
    public static final String STATE = "state";

    @Autowired
    @Qualifier("kradApplicationDataSource")
    private DataSource kradApplicationDataSource;

    @Autowired
    @Qualifier("parameterService")
    private transient ParameterService parameterService;

    @Autowired
    @Qualifier("sequenceAccessorService")
    private transient SequenceAccessorService sequenceAccessorService;

    @Autowired
    @Qualifier("globalVariableService")
    private GlobalVariableService globalVariableService;

    @Autowired
    @Qualifier("kcPersonService")
    private KcPersonService kcPersonService;

    @Autowired
    @Qualifier("countryService")
    private transient CountryService countryService;

    @Override
    public void setGenerateDefaultValues(String docTypeName) {
        super.setGenerateDefaultValues(docTypeName);
        Rolodex rolodex = (Rolodex) getBusinessObject();
        if (isAutoGenerateCode()) {
            rolodex.setRolodexId(Integer.parseInt(getSequenceAccessorService().getNextAvailableSequenceNumber(ROLODEX_ID_SEQUENCE_NAME, Rolodex.class).toString()));
        }
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<Section> getSections(MaintenanceDocument document, Maintainable oldMaintainable) {
        List<Section> sections = super.getSections(document, oldMaintainable);
        if (isAutoGenerateCode()) {
            disableRolodexId(sections);
        }
        return sections;
    }
    
    protected void disableRolodexId(List<Section> sections) {
        for (Section section : sections) {
            if (StringUtils.equals(section.getSectionId(), SECTION_ID)) {
                for (Row row : section.getRows()) {
                    for (Field field : row.getFields()) {
                        if (StringUtils.equals(field.getPropertyName(), ROLODEX_ID_NAME)) {
                            field.setReadOnly(true);
                        }
                        if (StringUtils.equals(field.getPropertyName(), STATE)) {
                            field.setFieldValidValues(getFieldValues(((Rolodex) this.businessObject).getCountryCode()));
                        }
                    }
                }
            }
        }        
    }

    protected List<KeyValue> getFieldValues(String alternateCode) {
        StateValuesFinder valuesFinder = new StateValuesFinder();
        if (StringUtils.isNotBlank(alternateCode)) {
            Country country = this.getCountryService().getCountryByAlternateCode(alternateCode);
            valuesFinder.setCountryCode(country.getCode());
        } else {
            valuesFinder.setCountryCode("");
        }
        return valuesFinder.getKeyValues();
    }

    public void processAfterNew(org.kuali.rice.krad.maintenance.MaintenanceDocument document, Map<String, String[]> requestParameters) {
        Rolodex rolodex = (Rolodex) document.getNewMaintainableObject().getDataObject();
        rolodex.setActive(true);
        KcPerson person = getKcPersonService().getKcPersonByPersonId(getGlobalVariableService().getUserSession().getPrincipalId());
        rolodex.setOwnedByUnit(person.getUnit().getUnitNumber());
        if (isAutoGenerateCode()) {
            rolodex.setRolodexId(Integer.parseInt(Long.valueOf(MaxValueIncrementerFactory.getIncrementer(getKradApplicationDataSource(), ROLODEX_ID_SEQUENCE_NAME).nextLongValue()).toString()));
        }
    }
    @Override
    public void processAfterCopy(MaintenanceDocument document, Map<String, String[]> parameters) {
        super.processAfterCopy(document, parameters);
        setGenerateDefaultValues(document.getDocumentHeader().getWorkflowDocument().getDocumentTypeName());       
    }

    public boolean isAutoGenerateCode() {
        return getParameterService().getParameterValueAsBoolean(Constants.KC_GENERIC_PARAMETER_NAMESPACE, 
                Constants.KC_ALL_PARAMETER_DETAIL_TYPE_CODE, AUTO_GEN_ROLODEX_ID_PARM);
    }


    @Override
    public void saveDataObject() {
		if(getBusinessObject() instanceof Rolodex) {
			Rolodex rolodex = (Rolodex)getBusinessObject();
			rolodex.setSponsorAddressFlag(false);
		}
    	super.saveDataObject(); 	
    }    
    protected ParameterService getParameterService() {
        if (parameterService == null) {
            parameterService = KcServiceLocator.getService(ParameterService.class);
        }
        return parameterService;
    }

    protected CountryService getCountryService() {
        if (countryService == null) {
            countryService = KcServiceLocator.getService(CountryService.class);
        }
        return countryService;
    }

    public void setParameterService(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    protected SequenceAccessorService getSequenceAccessorService() {
        if(sequenceAccessorService == null) {
            sequenceAccessorService = KcServiceLocator.getService(SequenceAccessorService.class);
        }
        return sequenceAccessorService;
    }

    public void setSequenceAccessorService(SequenceAccessorService sequenceAccessorService) {
        this.sequenceAccessorService = sequenceAccessorService;
    }

    public DataSource getKradApplicationDataSource() {
        if(kradApplicationDataSource == null) {
            kradApplicationDataSource = KcServiceLocator.getService("kradApplicationDataSource");
        }
        return kradApplicationDataSource;
    }

    public void setKradApplicationDataSource(DataSource kradApplicationDataSource) {
        this.kradApplicationDataSource = kradApplicationDataSource;
    }

    public GlobalVariableService getGlobalVariableService() {
        if (globalVariableService == null) {
            globalVariableService = KcServiceLocator.getService(GlobalVariableService.class);
        }
        return globalVariableService;
    }

    public void setGlobalVariableService(GlobalVariableService globalVariableService) {
        this.globalVariableService = globalVariableService;
    }

    public KcPersonService getKcPersonService() {
        if (kcPersonService == null) {
            kcPersonService = KcServiceLocator.getService(KcPersonService.class);
        }
        return kcPersonService;
    }

    public void setKcPersonService(KcPersonService kcPersonService) {
        this.kcPersonService = kcPersonService;
    }
}
