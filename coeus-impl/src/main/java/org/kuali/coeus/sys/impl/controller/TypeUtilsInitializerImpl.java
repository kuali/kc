package org.kuali.coeus.sys.impl.controller;

import org.kuali.coeus.sys.api.model.ScaleThreeDecimal;
import org.kuali.coeus.sys.api.model.ScaleTwoDecimal;
import org.kuali.coeus.sys.framework.controller.TypeUtilsInitializer;
import org.kuali.rice.core.api.util.type.TypeUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("typeUtilsInitializer")
public class TypeUtilsInitializerImpl implements TypeUtilsInitializer {

    @PostConstruct
    public void addToTypeUtils() {
        TypeUtils.addToDecimalType(ScaleThreeDecimal.class);
        TypeUtils.addToDecimalType(ScaleTwoDecimal.class);
    }
}
