/*
 * Copyright 2005-2010 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.subaward.web.struts.action;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.kra.subaward.bo.SubAward;
import org.kuali.kra.subaward.bo.SubAwardCloseout;
import org.kuali.kra.test.infrastructure.KcUnitTestBase;

public class SubAwardCloseoutActionTest extends KcUnitTestBase{
    
    SubAwardCloseoutAction subAwardCloseoutAction;    
    SubAward subAward;
    SubAwardCloseout subAwardCloseout;
    
@Before
public void setUp() throws Exception {
    subAwardCloseoutAction = new  SubAwardCloseoutAction();
    subAward = new SubAward();
    subAwardCloseout = new SubAwardCloseout();
    
    subAwardCloseout.setCloseoutTypeCode(1);
    subAwardCloseout.setCloseoutNumber(2);
    subAwardCloseout.setComments("test");
    
}
@After
public void tearDown() throws Exception {
    
    subAwardCloseoutAction=null;
    subAward = null;
    subAwardCloseout =null;
    
}
@Test
public void testAddCloseoutToSubAward(){    
    Assert.assertTrue(subAwardCloseoutAction.addCloseoutToSubAward(subAward, subAwardCloseout));

}

}
