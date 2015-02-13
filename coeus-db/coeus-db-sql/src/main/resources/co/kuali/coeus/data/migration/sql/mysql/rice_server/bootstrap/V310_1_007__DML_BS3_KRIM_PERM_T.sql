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

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);

INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
SELECT MAX(ID), UUID(), 1, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' and NM = 'View Document Section'), 'KC-PROTOCOL',
    'Maintain Protocol Notes', 'permission for maintaining notes on a submitted protocol', 'Y' FROM KRIM_PERM_ID_S;

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);

INSERT INTO KRIM_PERM_T (ACTV_IND,DESC_TXT,NM,NMSPC_CD,OBJ_ID,PERM_ID, PERM_TMPL_ID,VER_NBR)
SELECT 'Y','Create Award Account','Create Award Account','KC-AWARD',UUID(),MAX(ID),
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),1 from KRIM_PERM_ID_S;

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);

INSERT INTO KRIM_PERM_T(PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
SELECT MAX(s.ID), UUID(), 0, pt.perm_tmpl_id, 'KC-PD', 'Maintain Questionnaire Usage', 'Add/Edit Questionnaire Usages for Proposal Development', 'Y' 
from KRIM_PERM_ID_S s, KRIM_PERM_TMPL_T pt
where pt.nm = 'Questionnaire Permission';

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);

INSERT INTO KRIM_PERM_T(PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
SELECT MAX(s.ID), UUID(), 0, pt.perm_tmpl_id, 'KC-PROTOCOL', 'Maintain Questionnaire Usage', 'Add/Edit Questionnaire Usages for Protocol', 'Y'
from KRIM_PERM_ID_S s, KRIM_PERM_TMPL_T pt 
where pt.nm = 'Questionnaire Permission';

COMMIT;
