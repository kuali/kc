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

alter table AWARD_PERSONS drop constraint FK_PROP_PERS_ROLE_AWD_PERSONS
/

alter table EPS_PROP_PERSON_ROLE drop primary key
/

alter table EPS_PROP_PERSON_ROLE add PROP_PERSON_ROLE_CODE varchar2(12)
/

update EPS_PROP_PERSON_ROLE set PROP_PERSON_ROLE_CODE = PROP_PERSON_ROLE_ID
/

alter table EPS_PROP_PERSON_ROLE modify PROP_PERSON_ROLE_CODE varchar2(12) not null
/

alter table EPS_PROP_PERSON_ROLE add SPONSOR_HIERARCHY_NAME varchar2(50) default 'DEFAULT' not null
/

update EPS_PROP_PERSON_ROLE set PROP_PERSON_ROLE_ID = null
/

alter table EPS_PROP_PERSON_ROLE modify PROP_PERSON_ROLE_ID number(12,0)
/

update EPS_PROP_PERSON_ROLE set PROP_PERSON_ROLE_ID = SEQ_EPS_PROP_PERSON_ROLE.nextval
/

alter table EPS_PROP_PERSON_ROLE modify PROP_PERSON_ROLE_ID number(12,0) not null
/

alter table EPS_PROP_PERSON_ROLE add primary key (PROP_PERSON_ROLE_ID)
/

alter table EPS_PROP_PERSON_ROLE add constraint EPS_PROP_PERSON_ROLE_U1 unique (PROP_PERSON_ROLE_CODE, SPONSOR_HIERARCHY_NAME)
/

alter table EPS_PROP_PERSON_ROLE add READ_ONLY_ROLE char(1) default 'N' not null
/
