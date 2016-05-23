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
package org.kuali.coeus.sys.impl.controller.rest;


import org.kuali.coeus.sys.framework.controller.rest.RestBeanWrapperFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.beans.PropertyEditor;
import java.util.Map;

@Component("restBeanWrapperFactory")
public class RestBeanWrapperFactoryImpl implements RestBeanWrapperFactory {

    @Resource(name="restPropertyEditors")
    private Map<Class<?>, ? extends PropertyEditor> restPropertyEditors;

    @Override
    public BeanWrapper newInstance(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("object to wrap is null");
        }
        final BeanWrapper wrapper;
        if (o instanceof Class) {
            wrapper = new BeanWrapperImpl((Class<?>) o);
        } else {
            wrapper = new BeanWrapperImpl(o);
        }

        restPropertyEditors.forEach(wrapper::registerCustomEditor);
        wrapper.setAutoGrowNestedPaths(true);

        return wrapper;
    }

    public Map<Class<?>, ? extends PropertyEditor> getRestPropertyEditors() {
        return restPropertyEditors;
    }

    public void setRestPropertyEditors(Map<Class<?>, ? extends PropertyEditor> restPropertyEditors) {
        this.restPropertyEditors = restPropertyEditors;
    }
}
