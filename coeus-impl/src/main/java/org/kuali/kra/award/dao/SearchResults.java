package org.kuali.kra.award.dao;

import java.util.Collection;

public class SearchResults<T> {

	private Integer totalResults;
	private Collection<T> results;
	
	public Integer getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	
	public Collection<T> getResults() {
		return results;
	}
	public void setResults(Collection<T> results) {
		this.results = results;
	}
	
}
