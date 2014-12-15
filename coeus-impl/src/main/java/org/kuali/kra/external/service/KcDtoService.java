package org.kuali.kra.external.service;

import java.util.Collection;
import java.util.List;

public interface KcDtoService<T, S> {
	
	public T buildDto(S dataObject);
	
	public List<T> buildDtoList(Collection<S> items);
}
