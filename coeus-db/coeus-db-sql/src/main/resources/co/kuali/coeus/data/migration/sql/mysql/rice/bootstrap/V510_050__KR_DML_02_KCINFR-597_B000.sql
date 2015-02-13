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
insert into KRMS_TYP_T (TYP_ID, NM, NMSPC_CD, SRVC_NM, ACTV, VER_NBR)
	VALUES ('KC1004', 'Questionnaire Agenda', 'KC-KRMS', 'unitAgendaTypeService', 'Y', 1)
/

insert into KRMS_TYP_ATTR_T (TYP_ATTR_ID, SEQ_NO, TYP_ID, ATTR_DEFN_ID, ACTV, VER_NBR)
	VALUES ('KC1001', 1, 'KC1004', 'KC1000', 'Y', 1)
/

insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
	VALUES ('KC1005', 'KC-PD-CONTEXT', 'KC1004', 1)
/

insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
	VALUES ('KC1006', 'KC-PROTOCOL-CONTEXT', 'KC1004', 1)
/

insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
	VALUES ('KC1007', 'KC-IACUC-CONTEXT', 'KC1004', 1)
/

insert into KRMS_CNTXT_VLD_AGENDA_TYP_T (CNTXT_VLD_AGENDA_ID, CNTXT_ID, AGENDA_TYP_ID, VER_NBR)
	VALUES ('KC1008', 'KC-COIDISCLOSURE-CONTEXT', 'KC1004', 1)
/

update KRMS_AGENDA_T set TYP_ID = 'KC1004' where AGENDA_ID = 'KC1000'
/

insert into KRMS_AGENDA_ATTR_T (AGENDA_ATTR_ID, AGENDA_ID, ATTR_VAL, ATTR_DEFN_ID, VER_NBR)
	values ('KC1000', 'KC1000', '000001', 'KC1000', 1)
/

delete from KRCR_PARM_T where NMSPC_CD = 'KC-PD' and PARM_NM = 'QUESTIONNAIRE_BRANCHING_AGENDA'
/


DELIMITER ;
