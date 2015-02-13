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
INSERT INTO KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
  VALUES ((SELECT MAX(ID) FROM KRIM_PERM_ID_S),'KC-PROTOCOL','Create Any Amendment','Create amendments on any protocol',
         (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
         'Y',1,UUID());

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
  VALUES ((SELECT MAX(ID) FROM KRIM_PERM_ID_S),'KC-PROTOCOL','Create Any Renewal','Create renewals for any protocol',
         (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
         'Y',1,UUID());

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
  VALUES ((SELECT MAX(ID) FROM KRIM_PERM_ID_S),'KC-PROTOCOL','Submit Any Protocol','Submit Any Protocol',
         (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
         'Y',1,UUID());

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
  VALUES ((SELECT MAX(ID) FROM KRIM_PERM_ID_S),'KC-PROTOCOL','Maintain Protocol Review Comments','Maintain Protocol Review Comments',
         (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
         'Y',1,UUID());

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
  VALUES ((SELECT MAX(ID) FROM KRIM_PERM_ID_S),'KC-PROTOCOL','Maintain Protocol Related Proj','Maintain Protocols link to award and proposal',
         (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
         'Y',1,UUID());

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
  VALUES ((SELECT MAX(ID) FROM KRIM_PERM_ID_S),'KC-PROTOCOL','Maintain Any Protocol Access','Maintain Any Protocol Access',
         (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
         'Y',1,UUID());

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
  VALUES ((SELECT MAX(ID) FROM KRIM_PERM_ID_S),'KC-PROTOCOL','Add Any Protocol Notes','Add Any Protocol Notes',
         (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
         'Y',1,UUID());

INSERT INTO KRIM_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
  VALUES ((SELECT MAX(ID) FROM KRIM_PERM_ID_S), UUID(), 1, 
         (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Edit Document Section'), 
         'KC-PD', 'Modify Proposal Rates', 'Modify Proposal Budget Rates', 'Y');
