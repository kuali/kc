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
insert into S2S_FORM_TO_QUESTIONNAIRE (S2S_FORM_TO_QUESTIONNAIRE_ID,OPP_NAME_SPACE,FORM_NAME,QUESTIONNAIRE_ID,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR)
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),'http://apply.grants.gov/forms/PHS398_TrainingBudget-V1.0','PHS398_TrainingBudget-V1.0',3,NOW(),'admin',UUID(),1);

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into S2S_FORM_TO_QUESTIONNAIRE (S2S_FORM_TO_QUESTIONNAIRE_ID,OPP_NAME_SPACE,FORM_NAME,QUESTIONNAIRE_ID,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR)
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),'http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_1-V1.1','PHS_Fellowship_Supplemental_1_1-V1.1',1,NOW(),'admin',UUID(),1);

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into S2S_FORM_TO_QUESTIONNAIRE (S2S_FORM_TO_QUESTIONNAIRE_ID,OPP_NAME_SPACE,FORM_NAME,QUESTIONNAIRE_ID,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR)
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),'http://apply.grants.gov/forms/PHS_Fellowship_Supplemental_1_2-V1.2','PHS_Fellowship_Supplemental_1_2-V1.2',4,NOW(),'admin',UUID(),1);

insert into SEQ_QUESTIONNAIRE_REF_ID values (NULL);
insert into S2S_FORM_TO_QUESTIONNAIRE (S2S_FORM_TO_QUESTIONNAIRE_ID,OPP_NAME_SPACE,FORM_NAME,QUESTIONNAIRE_ID,UPDATE_TIMESTAMP,UPDATE_USER,OBJ_ID,VER_NBR)
    values ((select max(id) from SEQ_QUESTIONNAIRE_REF_ID),'http://apply.grants.gov/forms/NSF_CoverPage_1_3-V1.3','NSF_CoverPage_1_3-V1.3',2,NOW(),'admin',UUID(),1);
