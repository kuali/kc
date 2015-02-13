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

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),3,2,(select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE b where QUESTIONNAIRE_ID=2 and sequence_number=
                                                (select max(sequence_number) from questionnaire p where p.questionnaire_id=b.questionnaire_id)),1,NULL,
                                                'NSF Cover page  s2s form supporting questions',NOW(),'admin',1,UUID(),'N');

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),3,2,(select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE b where QUESTIONNAIRE_ID=3 and sequence_number=
                                                (select max(sequence_number) from questionnaire p where p.questionnaire_id=b.questionnaire_id)),1,NULL,
                                                'PHS398 Training Budget V1-0',NOW(),'admin',1,UUID(),'Y');

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),3,2,(select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE b where QUESTIONNAIRE_ID=4 and sequence_number=
                                                (select max(sequence_number) from questionnaire p where p.questionnaire_id=b.questionnaire_id)),1,NULL,
                                                'PHS Fellowship Form V1-2',NOW(),'admin',1,UUID(),'Y');

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into QUESTIONNAIRE_USAGE (QUESTIONNAIRE_USAGE_ID,MODULE_ITEM_CODE,MODULE_SUB_ITEM_CODE,QUESTIONNAIRE_REF_ID_FK,QUESTIONNAIRE_SEQUENCE_NUMBER,RULE_ID,QUESTIONNAIRE_LABEL,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID,IS_MANDATORY) 
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),3,2,(select QUESTIONNAIRE_REF_ID from QUESTIONNAIRE b where QUESTIONNAIRE_ID=1 and sequence_number=
                                                (select max(sequence_number) from questionnaire p where p.questionnaire_id=b.questionnaire_id)),1,NULL,
                                                'PHS Fellowship Form V1-1',NOW(),'admin',1,UUID(),'Y');
