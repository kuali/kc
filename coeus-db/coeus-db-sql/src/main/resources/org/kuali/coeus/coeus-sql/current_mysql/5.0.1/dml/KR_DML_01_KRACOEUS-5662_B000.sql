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
UPDATE KRMS_TYP_T SET NM='CONTEXT-RULE-TEST' WHERE NM='CONTEXT' AND NMSPC_CD='KR-RULE-TEST'
/
INSERT INTO KRMS_TYP_T(TYP_ID,NM,NMSPC_CD,SRVC_NM,ACTV,VER_NBR) values ('KC1003','CONTEXT','KC-KRMS',null,'Y',1)
/
UPDATE KRMS_CNTXT_T SET TYP_ID='KC1003' where CNTXT_ID='KC-AWARD-CONTEXT'
/
UPDATE KRMS_CNTXT_T SET TYP_ID='KC1003' where CNTXT_ID='KC-COIDISCLOSURE-CONTEXT'
/
UPDATE KRMS_CNTXT_T SET TYP_ID='KC1003' where CNTXT_ID='KC-IACUC-CONTEXT'
/
UPDATE KRMS_CNTXT_T SET TYP_ID='KC1003' where CNTXT_ID='KC-PROTOCOL-CONTEXT'
/

DELIMITER ;
