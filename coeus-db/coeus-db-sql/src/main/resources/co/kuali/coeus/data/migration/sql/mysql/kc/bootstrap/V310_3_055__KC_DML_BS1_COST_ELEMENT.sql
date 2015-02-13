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

insert into COST_ELEMENT (COST_ELEMENT,DESCRIPTION,BUDGET_CATEGORY_CODE,ON_OFF_CAMPUS_FLAG, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('TUIOTHER','Tuition Other Trainee type',35,'N',now(),'admin',1,uuid());
insert into COST_ELEMENT (COST_ELEMENT,DESCRIPTION,BUDGET_CATEGORY_CODE,ON_OFF_CAMPUS_FLAG, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('TUIPDYDS','Tuition PostDoc Trainee Degree Seeking',35,'N',now(),'admin',1,uuid());
insert into COST_ELEMENT (COST_ELEMENT,DESCRIPTION,BUDGET_CATEGORY_CODE,ON_OFF_CAMPUS_FLAG, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('TUIPDNDS','Tuition PostDoc Trainee Not Degree Seeking',35,'N',now(),'admin',1,uuid());
insert into COST_ELEMENT (COST_ELEMENT,DESCRIPTION,BUDGET_CATEGORY_CODE,ON_OFF_CAMPUS_FLAG, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('TUIPREDD','Tuition PreDoc Trainee Dual Degree',35,'N',now(),'admin',1,uuid());
insert into COST_ELEMENT (COST_ELEMENT,DESCRIPTION,BUDGET_CATEGORY_CODE,ON_OFF_CAMPUS_FLAG, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('TUIPRESD','Tuition PreDoc Trainee Single Degree',35,'N',now(),'admin',1,uuid());
insert into COST_ELEMENT (COST_ELEMENT,DESCRIPTION,BUDGET_CATEGORY_CODE,ON_OFF_CAMPUS_FLAG, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('TUIUNDGR','Tuition Undergrad Trainee',35,'N',now(),'admin',1,uuid());

insert into COST_ELEMENT (COST_ELEMENT,DESCRIPTION,BUDGET_CATEGORY_CODE,ON_OFF_CAMPUS_FLAG, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('400315','Fellows - Non-Student- Not MTDC',25,'N',now(),'admin',1,uuid());
insert into COST_ELEMENT (COST_ELEMENT,DESCRIPTION,BUDGET_CATEGORY_CODE,ON_OFF_CAMPUS_FLAG, UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) values ('422311','Tuition - Other - Not MTDC ','19','N',now(),'admin',1,uuid());

commit;
