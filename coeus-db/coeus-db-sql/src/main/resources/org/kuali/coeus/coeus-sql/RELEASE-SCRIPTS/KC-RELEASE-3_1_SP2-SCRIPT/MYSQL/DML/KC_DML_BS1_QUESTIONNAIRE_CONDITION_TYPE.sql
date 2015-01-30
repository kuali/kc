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

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('1','Contains text value',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('2','Begins with text',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('3','Ends with text',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('4','Matches text',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('5','Less than number',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('6','Less than or equals number',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('7','Equals number',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('8','Not Equal to number',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('9','Greater than or equals number',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('10','Greater than number',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('11','Before date',NOW(),'admin',1,UUID());

insert into QUESTIONNAIRE_CONDITION_TYPE (QUEST_CONDITION_TYPE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID) 
    values ('12','After date',NOW(),'admin',1,UUID());
