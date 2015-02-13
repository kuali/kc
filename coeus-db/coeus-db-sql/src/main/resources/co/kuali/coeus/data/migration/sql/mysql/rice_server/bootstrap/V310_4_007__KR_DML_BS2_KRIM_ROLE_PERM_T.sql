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

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Application Administrator'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Active Special Review Types'),'Y',UUID(),1);

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Award Budget Administrator'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Active Special Review Types'),'Y',UUID(),1);

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'IRB Administrator'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Active Special Review Types'),'Y',UUID(),1);

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'OSP Administrator'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Active Special Review Types'),'Y',UUID(),1);
  
INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Technical Administrator'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Active Special Review Types'),'Y',UUID(),1);

INSERT INTO KRIM_ROLE_PERM_ID_S VALUES (NULL);
INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR)
  VALUES ((SELECT MAX(ID) FROM KRIM_ROLE_PERM_ID_S),(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Unit Administrator'),(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'View Active Special Review Types'),'Y',UUID(),1);
