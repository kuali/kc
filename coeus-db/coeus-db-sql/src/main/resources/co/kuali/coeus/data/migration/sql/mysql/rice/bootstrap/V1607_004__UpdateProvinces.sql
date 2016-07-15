--
-- Kuali Coeus, a comprehensive research administration system for higher education.
--
-- Copyright 2005-2016 Kuali, Inc.
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

insert into krlc_st_t (POSTAL_STATE_CD, POSTAL_CNTRY_CD, OBJ_ID, VER_NBR, POSTAL_STATE_NM, ACTV_IND) values ('AB','CA',uuid(),1,'ALBERTA','Y');

insert into krlc_st_t (POSTAL_STATE_CD, POSTAL_CNTRY_CD, OBJ_ID, VER_NBR, POSTAL_STATE_NM, ACTV_IND) values ('NT','CA',uuid(),1,'NORTHWEST TERRITORIES','Y');

insert into krlc_st_t (POSTAL_STATE_CD, POSTAL_CNTRY_CD, OBJ_ID, VER_NBR, POSTAL_STATE_NM, ACTV_IND) values ('NU','CA',uuid(),1,'NUNAVUT','Y');

insert into krlc_st_t (POSTAL_STATE_CD, POSTAL_CNTRY_CD, OBJ_ID, VER_NBR, POSTAL_STATE_NM, ACTV_IND) values ('YT','CA',uuid(),1,'YUKON','Y');

update krlc_st_t set POSTAL_STATE_CD = 'MB' where POSTAL_STATE_CD = 'MA' and postal_cntry_cd = 'CA';
