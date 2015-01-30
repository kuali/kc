--
-- Kuali Coeus, a comprehensive research administration system for higher education.
-- 
-- Copyright 2005-2015 Kuali, Inc.
-- 
-- This program is free software: you can redistribute it and/or modify
-- it under the terms of the GNU Affero General Public License as
-- published by the Free Software Foundation, either version 3 of the
-- License, or (at your option) any later version.
-- 
-- This program is distributed in the hope that it will be useful,
-- but WITHOUT ANY WARRANTY; without even the implied warranty of
-- MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
-- GNU Affero General Public License for more details.
-- 
-- You should have received a copy of the GNU Affero General Public License
-- along with this program.  If not, see <http://www.gnu.org/licenses/>.
--

create or replace view OSP$ORGANIZATION as
 select ORGANIZATION_ID, ORGANIZATION_NAME, CONTACT_ADDRESS_ID, ADDRESS,                       
 CABLE_ADDRESS, TELEX_NUMBER, COUNTY, CONGRESSIONAL_DISTRICT, INCORPORATED_IN,               
 INCORPORATED_DATE, NUMBER_OF_EMPLOYEES, IRS_TAX_EXCEMPTION, FEDRAL_EMPLOYER_ID,            
 MASS_TAX_EXCEMPT_NUM,AGENCY_SYMBOL, VENDOR_CODE, COM_GOV_ENTITY_CODE,           
 MASS_EMPLOYEE_CLAIM, DUNS_NUMBER, DUNS_PLUS_FOUR_NUMBER, DODAC_NUMBER,                  
 CAGE_NUMBER, HUMAN_SUB_ASSURANCE, ANIMAL_WELFARE_ASSURANCE,  SCIENCE_MISCONDUCT_COMPL_DATE, 
 PHS_ACOUNT, NSF_INSTITUTIONAL_CODE, INDIRECT_COST_RATE_AGREEMENT, COGNIZANT_AUDITOR,             
 ONR_RESIDENT_REP, UPDATE_TIMESTAMP,  UPDATE_USER           
 from ORGANIZATION;
