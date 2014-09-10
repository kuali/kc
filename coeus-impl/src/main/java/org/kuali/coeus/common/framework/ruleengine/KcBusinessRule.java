package org.kuali.coeus.common.framework.ruleengine;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Used to annotate a business rule class. Should provide a value that will be
 * the name of the defined spring bean after spring bean loading.
 * KcBusinessRules should register themselves with the KcBusinessRulesService
 * or inherit from KcBusinessRuleBase which will do this for you.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface KcBusinessRule {
	String value() default "";
}
