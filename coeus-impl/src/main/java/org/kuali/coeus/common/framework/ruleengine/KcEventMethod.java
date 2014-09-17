package org.kuali.coeus.common.framework.ruleengine;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * KcEventMethod declares a method that will be run to respond to
 * a KcEvent. Each method can respond to multiple events, but
 * the event type must still match.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KcEventMethod {

}
