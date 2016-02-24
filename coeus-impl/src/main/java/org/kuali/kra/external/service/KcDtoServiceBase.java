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
package org.kuali.kra.external.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class KcDtoServiceBase<T, S> implements KcDtoService<T, S> {
	public List<T> buildDtoList(Collection<S> items) {
		List<T> result = new ArrayList<T>();
		if (items != null) {
			for (S item : items) {
				result.add(buildDto(item));
			}
		}
		return result;
	}

	@Override
	public abstract T buildDto(S dataObject);
}
