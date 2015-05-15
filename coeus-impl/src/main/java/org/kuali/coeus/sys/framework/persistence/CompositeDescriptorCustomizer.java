/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2015 Kuali, Inc.
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
package org.kuali.coeus.sys.framework.persistence;

import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;

import java.util.Collection;

/**
 * In eclipse link, you are only allowed a single customizer annotation on an entity.  This customizer
 * implementation makes it possible to aggregate customizers so that each customization
 * can be in a separate class.
 *
 * For example:
 * {@code
 * @Customizer(DepartmentCustomizer.class)
 * class Department {
 *     List<Employee> employees;
 *     List<Rooms> rooms;
 * }
 *
 * class EmployeeCustomizer extends DescriptorCustomizer { ... }
 * class RoomCustomizer extends DescriptorCustomizer { ... }
 *
 * class DepartmentCustomizer extends CompositeDescriptorCustomizer {
 *      @Override
 *      protected Collection<DescriptorCustomizer> getCustomizers() {
 *          Collection<DescriptorCustomizer> customizers = new ArrayList<DescriptorCustomizer>();
 *          customizers.add(new EmployeeCustomizer());
 *          customizers.add(new RoomCustomizer());
 *          return customizers;
 *      }
 * }
 * }
 */
@Deprecated
public abstract class CompositeDescriptorCustomizer implements DescriptorCustomizer {

    protected abstract Collection<DescriptorCustomizer> getCustomizers();

    @Override
    public final void customize(ClassDescriptor descriptor) throws Exception {
        final Collection<DescriptorCustomizer> customizers = getCustomizers();
        if (CollectionUtils.isEmpty(customizers)) {
            throw new IllegalStateException("getCustomizers cannot return an empty Collection");
        }

        for (DescriptorCustomizer customizer : customizers) {
            if (customizer == null) {
                throw new IllegalStateException("customizer is null");
            }

            customizer.customize(descriptor);
        }
    }
}
