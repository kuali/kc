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
TRUNCATE TABLE KREW_RTE_NODE_T
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2011,0,'1',0,'placeholder',2004,'org.kuali.rice.kew.engine.node.InitialNode',2)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2012,0,'1',0,'placeholder',2006,'org.kuali.rice.kew.engine.node.InitialNode',2)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2023,1,'1',0,'Adhoc Routing',2039,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2024,0,'1',0,'Initiated',2041,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_MTHD_CD,RTE_MTHD_NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2024,0,'1',0,'ReviewersNode','FR','ReviewersRouting',2042,'org.kuali.rice.kew.engine.node.RequestsNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_MTHD_CD,RTE_MTHD_NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2024,0,'1',0,'RequestsNode','FR','NotificationRouting',2043,'org.kuali.rice.kew.engine.node.RequestsNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('P',2031,0,'1',0,'Initiated',2061,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('P',2032,0,'1',0,'Initiated',2063,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('P',2033,0,'1',0,'Initiated',2065,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,GRP_ID,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('P',2034,0,'1',0,'Initiated',2067,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2680,0,0,'PreRoute',2840,'org.kuali.rice.kew.engine.node.InitialNode',2)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2995,0,0,'AdHoc',2898,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_MTHD_CD,RTE_MTHD_NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('P',2995,0,0,'RoleType','RM','org.kuali.rice.kew.role.RoleRouteModule',2899,'org.kuali.rice.kew.engine.node.RoleNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2996,0,0,'AdHoc',2901,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_MTHD_CD,RTE_MTHD_NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('P',2996,0,0,'GroupType','RM','org.kuali.rice.kew.role.RoleRouteModule',2902,'org.kuali.rice.kew.engine.node.RoleNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2997,0,0,'AdHoc',2904,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_MTHD_CD,RTE_MTHD_NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('P',2997,0,0,'GroupType','RM','org.kuali.rice.kew.role.RoleRouteModule',2905,'org.kuali.rice.kew.engine.node.RoleNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_MTHD_CD,RTE_MTHD_NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('P',2997,0,0,'RoleType','RM','org.kuali.rice.kew.role.RoleRouteModule',2906,'org.kuali.rice.kew.engine.node.RoleNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2998,0,0,'AdHoc',2908,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
INSERT INTO KREW_RTE_NODE_T (ACTVN_TYP,DOC_TYP_ID,FNL_APRVR_IND,MNDTRY_RTE_IND,NM,RTE_NODE_ID,TYP,VER_NBR)
  VALUES ('S',2999,0,0,'AdHoc',2910,'org.kuali.rice.kew.engine.node.InitialNode',1)
/
delimiter ;
