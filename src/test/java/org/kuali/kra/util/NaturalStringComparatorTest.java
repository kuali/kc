package org.kuali.kra.util;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class NaturalStringComparatorTest {
	
	private NaturalStringComparator comparator;
	private String[] sorted = {
			null,
			"",
			"1",
			"5",
			"10",
			"10ab",
			"f001a",
			"f1a",
			"f30a",
			"f200a"
	};

	private String[] unsorted = {
			"10",
			"f30a",
			"1",
			"f200a",
			"5",
			null,
			"10ab",
			"f001a",
			"",
			"f1a"
	};

    @Before
    public void setUp() throws Exception {
    	comparator = new NaturalStringComparator();
    }

    @After
    public void tearDown() throws Exception {
    	comparator = null;
    }
    
    @Test
    public void testNullComparison() throws Exception {
    	int val = comparator.compare(sorted[0], sorted[5]);
    	Assert.assertTrue(val < 0);
    }

    @Test
    public void testEmptyStringComparison() throws Exception {
    	int val = comparator.compare(sorted[1], sorted[5]);
    	Assert.assertTrue(val < 0);
    }

    @Test
    public void testNumericStringComparison() throws Exception {
    	int val = comparator.compare(sorted[3], sorted[4]);
    	Assert.assertTrue(val < 0);
    }

    @Test
    public void testMixedStringComparison() throws Exception {
    	int val = comparator.compare(sorted[8], sorted[9]);
    	Assert.assertTrue(val < 0);
    }
    
    @Test 
    public void testListSorting() throws Exception {
    	Assert.assertFalse(Arrays.equals(sorted, unsorted));
    	Arrays.sort(unsorted, comparator);
    	Assert.assertTrue(Arrays.equals(sorted, unsorted));
    }
}
