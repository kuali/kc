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

DELIMITER /
alter table EPS_PROP_PERSON add CITIZENSHIP_TYPE_CODE decimal(15,0)
/

update EPS_PROP_PERSON pp set pp.AGE_BY_FISCAL_YEAR = (select AGE_BY_FISCAL_YEAR from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.RACE = (select RACE from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.IS_HANDICAPPED = (select IS_HANDICAPPED from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.HANDICAP_TYPE = (select HANDICAP_TYPE from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.IS_VETERAN = (select IS_VETERAN from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.VETERAN_TYPE = (select VETERAN_TYPE from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.VISA_CODE = (select VISA_CODE from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.VISA_TYPE = (select VISA_TYPE from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.VISA_RENEWAL_DATE = (select VISA_RENEWAL_DATE from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.HAS_VISA = (select HAS_VISA from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.DIRECTORY_DEPARTMENT = (select DIRECTORY_DEPARTMENT from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.IS_RESEARCH_STAFF = (select IS_RESEARCH_STAFF from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.VACATION_ACCURAL = (select VACATION_ACCURAL from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.IS_ON_SABBATICAL = (select IS_ON_SABBATICAL from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.ID_PROVIDED = (select ID_PROVIDED from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.ID_VERIFIED = (select ID_VERIFIED from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

update EPS_PROP_PERSON pp set pp.CITIZENSHIP_TYPE_CODE = (select CITIZENSHIP_TYPE_CODE from EPS_PROP_PERSON_EXT ext where ext.PROPOSAL_NUMBER = pp.PROPOSAL_NUMBER and ext.PROP_PERSON_NUMBER = pp.PROP_PERSON_NUMBER)
/

drop table EPS_PROP_PERSON_EXT
/
 
DELIMITER ;
