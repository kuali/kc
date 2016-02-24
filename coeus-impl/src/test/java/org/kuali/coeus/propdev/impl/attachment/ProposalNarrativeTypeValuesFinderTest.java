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
package org.kuali.coeus.propdev.impl.attachment;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.core.DevelopmentProposal;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocument;
import org.kuali.coeus.propdev.impl.core.ProposalDevelopmentDocumentForm;
import org.kuali.rice.core.api.util.KeyValue;
import org.kuali.rice.krad.uif.field.InputField;
import org.kuali.rice.krad.uif.field.InputFieldBase;
import org.kuali.rice.krad.uif.view.ViewModel;

public class ProposalNarrativeTypeValuesFinderTest {

	private static final String A_DESCRIPTION_FOR_NARRATIVE_CODE = "A Description for ";
	List<NarrativeType> testNarrativeTypes;
	
	@Before
	public void setup() {
		testNarrativeTypes = new ArrayList<>();
		testNarrativeTypes.add(createNarrativeType("1", "Test1"));
		testNarrativeTypes.add(createNarrativeType("2", "Test2"));
		testNarrativeTypes.add(createNarrativeType("3", "Bc123"));
	}
	
	private NarrativeType createNarrativeType(String code, String description) {
		NarrativeType result = new NarrativeType();
		result.setCode(code);
		result.setDescription(description);
		return result;
	}

	@Test
	public void testNarrativeTypeValuesFinder_simpleNoAlpha() {
		ProposalNarrativeTypeValuesFinder valuesFinder = new ProposalNarrativeTypeValuesFinder() {
			@Override
			protected Collection<NarrativeType> loadAllNarrativeTypes(DevelopmentProposal developmentProposal) {
				return new ArrayList<>(testNarrativeTypes);
			}

			@Override
			protected Boolean shouldAlphabetizeNarrativeTypes() {
				return false;
			}
		};
		List<KeyValue> result = valuesFinder.getKeyValues(new ProposalDevelopmentDocumentForm() {
			@Override
			protected void instantiateDocument() { setDocument(new ProposalDevelopmentDocument()); }
		});
		assertEquals(4, result.size());
		assertEquals(testNarrativeTypes.get(0).getCode(), result.get(1).getKey());
		assertEquals(testNarrativeTypes.get(0).getDescription(), result.get(1).getValue());
	}
	
	@Test
	public void testNarrativeTypeValuesFinder_simpleAlpha() {
		ProposalNarrativeTypeValuesFinder valuesFinder = new ProposalNarrativeTypeValuesFinder() {
			@Override
			protected Collection<NarrativeType> loadAllNarrativeTypes(DevelopmentProposal developmentProposal) {
				return new ArrayList<>(testNarrativeTypes);
			}

			@Override
			protected Boolean shouldAlphabetizeNarrativeTypes() {
				return true;
			}
		};
		List<KeyValue> result = valuesFinder.getKeyValues(new ProposalDevelopmentDocumentForm() {
			@Override
			protected void instantiateDocument() { setDocument(new ProposalDevelopmentDocument()); }
		});
		assertEquals(4, result.size());
		assertEquals(testNarrativeTypes.get(2).getCode(), result.get(1).getKey());
		assertEquals(testNarrativeTypes.get(2).getDescription(), result.get(1).getValue());
	}
	
	@Test
	public void testNarrativeTypeValuesFinder_withInputAndAlpha() {
		final String inputFieldNarrativeTypeCode = "4";
		ProposalNarrativeTypeValuesFinder valuesFinder = new ProposalNarrativeTypeValuesFinder() {
			@Override
			protected Collection<NarrativeType> loadAllNarrativeTypes(DevelopmentProposal developmentProposal) {
				return new ArrayList<>(testNarrativeTypes);
			}

			@Override
			protected Boolean shouldAlphabetizeNarrativeTypes() {
				return true;
			}
			
			@Override
			protected String getNarrativeTypeCodeFromModelValue(ViewModel model, InputField field) {
				return inputFieldNarrativeTypeCode;
			}
			
			@Override
			protected NarrativeType getNarrativeType(String typeCode) {
				return createNarrativeType(typeCode, A_DESCRIPTION_FOR_NARRATIVE_CODE + typeCode);
			}
		};
		List<KeyValue> result = valuesFinder.getKeyValues(new ProposalDevelopmentDocumentForm() {
			@Override
			protected void instantiateDocument() { setDocument(new ProposalDevelopmentDocument()); }
		}, new InputFieldBase());
		assertEquals(5, result.size());
		assertEquals(inputFieldNarrativeTypeCode, result.get(1).getKey());
		assertEquals(A_DESCRIPTION_FOR_NARRATIVE_CODE + inputFieldNarrativeTypeCode, result.get(1).getValue());
	}
}
