/*
 * Copyright 2006-2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
