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
package org.kuali.coeus.propdev.impl.location;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.junit.Before;
import org.junit.Test;
import org.kuali.rice.core.api.criteria.GenericQueryResults;
import org.kuali.rice.location.api.country.CountryService;
import org.kuali.rice.location.impl.country.CountryBo;

public class ProposalCountryServiceImplTest {

	private Mockery context;
	private GenericQueryResults.Builder<CountryBo> countryListBuilder;
	private CountryService countryService;
	
	@Before()
    public void setUpMockery() {
        context = new JUnit4Mockery() {{ setThreadingPolicy(new Synchroniser()); }}; 
        countryService = context.mock(CountryService.class);
        countryListBuilder = GenericQueryResults.Builder.<CountryBo>create();
        countryListBuilder.setResults(new ArrayList<CountryBo>(){{
        	add(createCountryBo("AU", "AUS", "AUSTRALIA"));
        }});
	}
    
	protected CountryBo createCountryBo(String code, String altCode,
			String name) {
		CountryBo countryBo = new CountryBo();
		countryBo.setAlternateCode(altCode);
		countryBo.setCode(code);
		countryBo.setName(name);
		return countryBo;
	}
	
	@Test
    public void test_convertAltCountryCodeToRealCountryCode_null_alternatecode() {
		final ProposalCountryServiceImpl proposalCountryServiceImpl = new ProposalCountryServiceImpl();		
		context.checking(new Expectations() {
			{
				one(countryService).getCountryByAlternateCode(null);
				will(returnValue(""));
			}
		});
		
		proposalCountryServiceImpl.setCountryService(countryService);
		assertEquals("",proposalCountryServiceImpl.convertAltCountryCodeToRealCountryCode(null));
    }
	
	@Test
    public void test_convertAltCountryCodeToRealCountryCode_blank_alternatecode() {
		final ProposalCountryServiceImpl proposalCountryServiceImpl = new ProposalCountryServiceImpl();
		context.checking(new Expectations() {
			{
				one(countryService).getCountryByAlternateCode("");
				will(returnValue(""));
			}
		});
		
		proposalCountryServiceImpl.setCountryService(countryService);
		assertEquals("",proposalCountryServiceImpl.convertAltCountryCodeToRealCountryCode(""));
    }
	
	@Test
    public void test_convertAltCountryCodeToRealCountryCode_notvalid_alternatecode() {
		final ProposalCountryServiceImpl proposalCountryServiceImpl = new ProposalCountryServiceImpl();		
		context.checking(new Expectations() {
			{
				one(countryService).getCountryByAlternateCode("USA");
				will(returnValue(null));
			}
		});
		
		proposalCountryServiceImpl.setCountryService(countryService);
		assertEquals("",proposalCountryServiceImpl.convertAltCountryCodeToRealCountryCode("USA"));
    }
	
	@Test
    public void test_convertAltCountryCodeToRealCountryCode_valid_alternatecode() {
		final ProposalCountryServiceImpl proposalCountryServiceImpl = new ProposalCountryServiceImpl();		
		context.checking(new Expectations() {
			{
				one(countryService).getCountryByAlternateCode("AUS");
				will(returnValue(countryListBuilder.build()));
			}
		});
		
		proposalCountryServiceImpl.setCountryService(countryService);
		assertEquals("",proposalCountryServiceImpl.convertAltCountryCodeToRealCountryCode("AUS"));
    }
}
