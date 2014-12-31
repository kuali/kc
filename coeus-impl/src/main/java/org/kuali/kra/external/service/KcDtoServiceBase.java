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
