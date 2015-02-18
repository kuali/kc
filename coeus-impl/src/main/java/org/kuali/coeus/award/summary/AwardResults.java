package org.kuali.coeus.award.summary;

import java.util.Collection;

import org.kuali.kra.award.dao.SearchResults;

import com.codiform.moo.annotation.CollectionProperty;
import com.codiform.moo.annotation.Ignore;
import com.codiform.moo.annotation.Property;

public class AwardResults {

	@Property(source="totalResults")
	private Integer totalFound;
	@Property(source="mvel:results.size()")
	private Integer count;
	@CollectionProperty(source="results", itemClass=AwardSummaryDto.class)
	private Collection<AwardSummaryDto> awards;
	
	public Integer getTotalFound() {
		return totalFound;
	}
	public void setTotalFound(Integer totalFound) {
		this.totalFound = totalFound;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Collection<AwardSummaryDto> getAwards() {
		return awards;
	}
	public void setAwards(Collection<AwardSummaryDto> awards) {
		this.awards = awards;
	}
}
