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
