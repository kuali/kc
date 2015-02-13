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
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-SYS','Unit','unitRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-SYS','UnitHierarchy','unitHierarchyRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-WKFLW','Derived Role - PI','proposalRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-WKFLW','Derived Role - COI','proposalRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-WKFLW','Derived Role - KeyPerson','proposalRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-SYS','Document Section','defaultPermissionTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-SYS','Document Action','defaultPermissionTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-SYS','ProposalType','kimTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-SYS','ProtocolType','kimTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-SYS','CommitteeType','kimTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-SYS','AwardType','kimTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-SYS','TimeAndMoneyType','kimTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-IP','Derived Role - Proposal Log PI','proposalLogPiDerivedRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-WKFLW','Derived Role - Unit Administrator','unitAdministratorDerivedRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-WKFLW','Derived Role: IRB Online Reviewer','protocolOnlineReviewRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC_SYS','IRBApprover-Nested','protocolApproverRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-WKFLW','Derived Role: Active Committee Member','activeCommitteeMemberDerivedRoleTypeService','Y',UUID(),1)
/
INSERT INTO KRIM_TYP_ID_S VALUES (null)
/
INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,OBJ_ID,VER_NBR) 
    VALUES ((SELECT MAX(ID) FROM KRIM_TYP_ID_S),'KC-WKFLW','Derived Role: Active Committee Member on Scheduled Date','activeCommitteeMemberOnScheduledDateDerivedRoleTypeService','Y',UUID(),1)
/
delimiter ;
