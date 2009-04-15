/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.contacts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kuali.core.util.ErrorMap;
import org.kuali.core.util.GlobalVariables;
import org.kuali.kra.award.bo.Award;
import org.kuali.kra.bo.NonOrganizationalRolodex;
import org.kuali.kra.bo.Person;

/**
 * This class tests AddAwardProjectPersonRuleImpl
 */
public class AwardProjectPersonAddRuleImplTest {

    private static final int ROLODEX_ID = 1002;
    private Award award;
    private AwardProjectPersonAddRuleImpl rule;
    private Person person1;
    private NonOrganizationalRolodex person2;    
    
    @Before
    public void setUp() {
        rule = new AwardProjectPersonAddRuleImpl();
        award = new Award();
        
        person1 = new Person();
        person1.setPersonId("1001");
        
        person2 = new NonOrganizationalRolodex();
        person2.setRolodexId(ROLODEX_ID);
        
        award.add(new AwardPerson(person1, ContactRoleFixtureFactory.MOCK_PI));
        award.add(new AwardPerson(person2, ContactRoleFixtureFactory.MOCK_COI));
        
        
        GlobalVariables.setErrorMap(new ErrorMap());
    }
    
    @After
    public void tearDown() {
        rule = null;
        award = null;
    }
    
    @Test
    public void testCheckForExistingPI_DuplicateFound() {
        AwardPerson newPerson = new AwardPerson(new Person(), ContactRoleFixtureFactory.MOCK_PI);
        Assert.assertFalse("Duplicate PI not identified", rule.checkForExistingPrincipalInvestigators(award, newPerson));
    }
    
    @Test
    public void testCheckForExistingPI_NoDuplicateFound() {
        AwardPerson newPerson = new AwardPerson(new Person(), ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertTrue("Duplicate PI misidentified", rule.checkForExistingPrincipalInvestigators(award, newPerson));
    }
    

    @Test
    public void testCheckForDuplicateContact_DuplicatePersonFound() {
        Person duplicatePerson = new Person();
        duplicatePerson.setPersonId(person1.getPersonId());
        AwardPerson newPerson = new AwardPerson(duplicatePerson, ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse("Duplicate Person not identified", rule.checkForDuplicatePerson(award, newPerson));
    }
    
    @Test
    public void testCheckForDuplicateContact_DuplicateRolodexFound() {
        NonOrganizationalRolodex duplicatePerson = new NonOrganizationalRolodex ();
        duplicatePerson.setRolodexId(ROLODEX_ID);
        AwardPerson newPerson = new AwardPerson(duplicatePerson, ContactRoleFixtureFactory.MOCK_KEY_PERSON);
        Assert.assertFalse("Duplicate Rolodex not identified", rule.checkForDuplicatePerson(award, newPerson));
    }
}
