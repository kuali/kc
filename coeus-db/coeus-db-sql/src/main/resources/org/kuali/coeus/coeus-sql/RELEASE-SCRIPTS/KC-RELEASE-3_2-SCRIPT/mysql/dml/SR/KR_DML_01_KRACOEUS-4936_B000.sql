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


INSERT INTO KRCR_NMSPC_T (ACTV_IND,NM,NMSPC_CD,APPL_ID,OBJ_ID,VER_NBR)
  VALUES ('Y','KC Negotiation','KC-NEGOTIATION','KC',UUID(),1)
/

INSERT INTO KRCR_CMPNT_T (NMSPC_CD, CMPNT_CD, NM, ACTV_IND, OBJ_ID, VER_NBR)
	VALUES ('KC-NEGOTIATION', 'Document', 'Document', 'Y', UUID(), 1)
/

INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC', 'KC-NEGOTIATION', 'CONFG', 'Document', 'negotiationInProgressStatusCodes', 'A comma delimited list of the negotiation status codes that are considered in progress. The first status defined here will be used as the default status for new negotiations.', 'IP', 'A', UUID(), 1)
/

INSERT INTO KRCR_PARM_T (APPL_ID,NMSPC_CD,PARM_TYP_CD,CMPNT_CD,PARM_NM,PARM_DESC_TXT,VAL,EVAL_OPRTR_CD,OBJ_ID,VER_NBR)
    VALUES ('KC', 'KC-NEGOTIATION', 'CONFG', 'Document', 'negotiationCompletedStatusCodes', 'A comma delimited list of the negotiation status codes that are considered completed.', 'COM,SP', 'A', UUID(), 1)
/

DELIMITER ;
