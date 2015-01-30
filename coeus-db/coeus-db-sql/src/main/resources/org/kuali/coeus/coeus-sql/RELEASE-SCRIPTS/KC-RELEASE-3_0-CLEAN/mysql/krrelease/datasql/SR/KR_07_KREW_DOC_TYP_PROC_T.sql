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
INSERT INTO KREW_RTE_NODE_S VALUES(NULL)
/

INSERT INTO KREW_DOC_TYP_PROC_T (DOC_TYP_PROC_ID,NM,DOC_TYP_ID,INIT_RTE_NODE_ID,INIT_IND,VER_NBR) VALUES ((SELECT (MAX(ID)) FROM KREW_RTE_NODE_S),'PRIMARY',(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KualiDocument' AND DOC_TYP_VER_NBR = 1),(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KualiDocument' AND DOC_TYP_VER_NBR = 1)),1,3)
/
INSERT INTO KREW_RTE_NODE_S VALUES(NULL)
/
INSERT INTO KREW_DOC_TYP_PROC_T (DOC_TYP_PROC_ID,NM,DOC_TYP_ID,INIT_RTE_NODE_ID,INIT_IND,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KREW_RTE_NODE_S),'PRIMARY',(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KcMaintenanceDocument' AND DOC_TYP_VER_NBR = 0),(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KcMaintenanceDocument' AND DOC_TYP_VER_NBR = 0)),1,24)
/
INSERT INTO KREW_RTE_NODE_S VALUES(NULL)
/
INSERT INTO KREW_DOC_TYP_PROC_T (DOC_TYP_PROC_ID,NM,DOC_TYP_ID,INIT_RTE_NODE_ID,INIT_IND,VER_NBR)
VALUES ((SELECT (MAX(ID)) FROM KREW_RTE_NODE_S),'PRIMARY',(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KcSharedMaintenanceDocument' AND DOC_TYP_VER_NBR = 0),(SELECT RTE_NODE_ID FROM KREW_RTE_NODE_T WHERE DOC_TYP_ID = (SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KcSharedMaintenanceDocument' AND DOC_TYP_VER_NBR = 0)),1,61)
/

DELIMITER ;
