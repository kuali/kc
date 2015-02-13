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

INSERT INTO KRIM_GRP_ID_S VALUES (NULL);
INSERT INTO KRIM_GRP_T (GRP_ID, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT, OBJ_ID) 
  VALUES ((SELECT MAX(ID) FROM KRIM_GRP_ID_S), 'QuestionnaireAdmin', 'KC-WKFLW', 'Questionnaire Blanket Approver', 1, 'Y', NOW(), UUID());
  
COMMIT;
