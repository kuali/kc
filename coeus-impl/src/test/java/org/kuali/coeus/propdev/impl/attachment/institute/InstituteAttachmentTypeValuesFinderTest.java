package org.kuali.coeus.propdev.impl.attachment.institute;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kuali.coeus.propdev.impl.attachment.NarrativeType;
import org.kuali.rice.core.api.util.KeyValue;

public class InstituteAttachmentTypeValuesFinderTest {

	List<NarrativeType> testNarrativeTypes;
	
	@Before
	public void setup() {
		testNarrativeTypes = new ArrayList<>();
		testNarrativeTypes.add(createNarrativeType("1", "Test1"));
		testNarrativeTypes.add(createNarrativeType("2", "Test2"));
		testNarrativeTypes.add(createNarrativeType("3", "Abc123"));
	}
	
	private NarrativeType createNarrativeType(String code, String description) {
		NarrativeType result = new NarrativeType();
		result.setCode(code);
		result.setDescription(description);
		return result;
	}
	
	@Test
	public void testValuesFinder_notAlpha() {
		InstituteAttachmentTypeValuesFinder valuesFinder = new InstituteAttachmentTypeValuesFinder() {
			@Override
			protected Boolean shouldAttachmentListBeAlphabetized() {
				return false;
			}

			@Override
			protected List<NarrativeType> getInstituteNarrativeTypes() {
				return new ArrayList<>(testNarrativeTypes);
			}
		};
		
		List<KeyValue> result = valuesFinder.getKeyValues();
		assertEquals(3, result.size());
		assertEquals(testNarrativeTypes.get(0).getCode(), result.get(0).getKey());
		assertEquals(testNarrativeTypes.get(0).getDescription(), result.get(0).getValue());
	}
	
	@Test
	public void testValuesFinder_alphaOrdered() {
		InstituteAttachmentTypeValuesFinder valuesFinder = new InstituteAttachmentTypeValuesFinder() {
			@Override
			protected Boolean shouldAttachmentListBeAlphabetized() {
				return true;
			}

			@Override
			protected List<NarrativeType> getInstituteNarrativeTypes() {
				return new ArrayList<>(testNarrativeTypes);
			}
		};
		
		List<KeyValue> result = valuesFinder.getKeyValues();
		assertEquals(3, result.size());
		assertEquals(testNarrativeTypes.get(2).getCode(), result.get(0).getKey());
		assertEquals(testNarrativeTypes.get(2).getDescription(), result.get(0).getValue());
	}
	
}
