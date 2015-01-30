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

delimiter /
INSERT INTO KREW_RTE_NODE_S VALUES (null)
/
INSERT INTO KREW_RTE_NODE_T (RTE_NODE_ID,DOC_TYP_ID,NM,TYP,GRP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,ACTVN_TYP,VER_NBR)
   VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KualiDocument' AND DOC_TYP_VER_NBR = 1),'AdHoc','org.kuali.rice.kew.engine.node.InitialNode',null,0,0,'S',3)
/
INSERT INTO KREW_RTE_NODE_S VALUES (null)
/
INSERT INTO KREW_RTE_NODE_T (RTE_NODE_ID,DOC_TYP_ID,NM,TYP,GRP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,ACTVN_TYP,VER_NBR)
   VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KcMaintenanceDocument' AND DOC_TYP_VER_NBR = 0),'Initiated','org.kuali.rice.kew.engine.node.InitialNode',(SELECT GRP_ID FROM KRIM_GRP_T WHERE NMSPC_CD = 'KC-WKFLW' AND GRP_NM = 'KcAdmin'),0,0,'P',24)
/
INSERT INTO KREW_RTE_NODE_S VALUES (null)
/
INSERT INTO KREW_RTE_NODE_T (RTE_NODE_ID,DOC_TYP_ID,NM,TYP,GRP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,ACTVN_TYP,VER_NBR)
   VALUES ((SELECT MAX(ID) FROM KREW_RTE_NODE_S),(SELECT DOC_TYP_ID FROM KREW_DOC_TYP_T WHERE DOC_TYP_NM = 'KcSharedMaintenanceDocument' AND DOC_TYP_VER_NBR = 0),'Initiated','org.kuali.rice.kew.engine.node.InitialNode',null,0,0,'P',61)
/
delimiter ;
