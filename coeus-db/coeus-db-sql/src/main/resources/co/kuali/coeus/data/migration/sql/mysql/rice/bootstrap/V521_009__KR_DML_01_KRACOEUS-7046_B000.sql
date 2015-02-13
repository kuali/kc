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

UPDATE KRMS_FUNC_T SET DESC_TXT = 'Applies to all proposals' WHERE NM = 'allProposalsRule' AND NMSPC_CD = 'KC-PD'
/

UPDATE KRMS_TERM_SPEC_T SET NM = 
    (SELECT FUNC_ID FROM KRMS_FUNC_T WHERE NM = 'allProposalsRule' AND NMSPC_CD = 'KC-PD') 
        WHERE NM = 'allProposals' AND NMSPC_CD = 'KC-PD'
/

INSERT INTO KRMS_TERM_RSLVR_S VALUES(NULL)
/

INSERT INTO KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TERM_RSLVR_S)),'KC-PD','All proposals resolver',
            (SELECT TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' AND NMSPC_CD='KC-KRMS'),
                (SELECT TERM_SPEC_ID from KRMS_TERM_SPEC_T WHERE NMSPC_CD='KC-PD' AND 
                    NM=(SELECT FUNC_ID from KRMS_FUNC_T WHERE  NM='allProposalsRule' AND NMSPC_CD='KC-PD')),
                    'Y',1)
/
DELIMITER ;

