-- REM INSERTING into KRIM_TYP_T
Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','Unit','unitRoleTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','UnitHierarchy','unitHierarchyRoleTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Derived Role - PI','proposalRoleTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Derived Role - COI','proposalRoleTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Derived Role - KeyPerson','proposalRoleTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','Document Section','defaultPermissionTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','Document Action','defaultPermissionTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','ProposalType','kimTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','ProtocolType','kimTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','CommitteeType','kimTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','AwardType','kimTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','TimeAndMoneyType','kimTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

Insert into KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-IP','Derived Role - Proposal Log PI','proposalLogPiDerivedRoleTypeService','Y',1,UUID() from KRIM_TYP_ID_S;

Insert into KRIM_TYP_ID_S values (null);

INSERT INTO KRIM_TYP_T (KIM_TYP_ID,NMSPC_CD,NM,SRVC_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-IP','Derived Role - Unit Administrator','unitAdministratorDerivedRoleTypeService','Y',1,UUID() from KRIM_TYP_ID_S;


-- REM INSERTING into KRIM_ATTR_DEFN_T
Insert into KRIM_ATTR_DEFN_ID_S values (null);

Insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','unitNumber','Unit Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',1,UUID() from KRIM_ATTR_DEFN_ID_S;

Insert into KRIM_ATTR_DEFN_ID_S values (null);

Insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','subunits','Descend Flag','org.kuali.kra.kim.bo.KcKimAttributes','Y',1,UUID() from KRIM_ATTR_DEFN_ID_S;

Insert into KRIM_ATTR_DEFN_ID_S values (null);

Insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','sectionName',null,'org.kuali.rice.kim.bo.impl.KimAttributes','Y',1,UUID() from KRIM_ATTR_DEFN_ID_S;

Insert into KRIM_ATTR_DEFN_ID_S values (null);

Insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','documentAction',null,'org.kuali.rice.kim.bo.impl.KimAttributes','Y',1,UUID() from KRIM_ATTR_DEFN_ID_S;

Insert into KRIM_ATTR_DEFN_ID_S values (null);

Insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','proposal','Proposal Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',1,UUID() from KRIM_ATTR_DEFN_ID_S;

Insert into KRIM_ATTR_DEFN_ID_S values (null);

Insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','protocol','Protocol Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',1,UUID() from KRIM_ATTR_DEFN_ID_S;

Insert into KRIM_ATTR_DEFN_ID_S values (null);

Insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','committee','Committee Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',1,UUID() from KRIM_ATTR_DEFN_ID_S;

Insert into KRIM_ATTR_DEFN_ID_S values (null);

Insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','award','Award Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',1,UUID() from KRIM_ATTR_DEFN_ID_S;

Insert into KRIM_ATTR_DEFN_ID_S values (null);

INSERT INTO KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID,NMSPC_CD,NM,LBL,CMPNT_NM,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','timeandmoney','TimeAndMoney Number','org.kuali.kra.kim.bo.KcKimAttributes','Y',1,UUID() from KRIM_ATTR_DEFN_ID_S;

-- REM INSERTING into KRIM_TYP_ATTR_T
insert into KRIM_TYP_ATTR_ID_S values (null);

Insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'unitNumber'),
'A','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;

insert into KRIM_TYP_ATTR_ID_S values (null);

Insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'subunits'),
'B','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;

insert into KRIM_TYP_ATTR_ID_S values (null);

Insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'unitNumber'),
'A','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;

insert into KRIM_TYP_ATTR_ID_S values (null);

Insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'),
'a','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;

insert into KRIM_TYP_ATTR_ID_S values (null);

Insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'),
'a','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;

insert into KRIM_TYP_ATTR_ID_S values (null);

Insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'ProposalType'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'proposal'),
'A','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;

insert into KRIM_TYP_ATTR_ID_S values (null);

Insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'ProtocolType'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'protocol'),
'A','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;

insert into KRIM_TYP_ATTR_ID_S values (null);

Insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'CommitteeType'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'committee'),
'A','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;

insert into KRIM_TYP_ATTR_ID_S values (null);

Insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'AwardType'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'award'),
'A','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;

insert into KRIM_TYP_ATTR_ID_S values (null);

INSERT INTO KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,SORT_CD,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'TimeAndMoneyType'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'timeandmoney'),
'A','Y',1, UUID() from KRIM_TYP_ATTR_ID_S;


-- REM INSERTING into KRIM_PERM_TMPL_T
insert into KRIM_PERM_TMPL_ID_S values (null);

Insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','Answer Questionnaire Permission','Answer Questionnaire',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;

insert into KRIM_PERM_TMPL_ID_S values (null);

Insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','Question Permission','Modify/View Question',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;

insert into KRIM_PERM_TMPL_ID_S values (null);

Insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','Questionnaire Permission','Modify/View Questionnaire',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;

insert into KRIM_PERM_TMPL_ID_S values (null);

Insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','Route All Documents','Route All Documents',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;

insert into KRIM_PERM_TMPL_ID_S values (null);

Insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','Perform Any Document Action','Perform Any Document Action',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;

insert into KRIM_PERM_TMPL_ID_S values (null);

Insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','View All Documents','View All Documents',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;

insert into KRIM_PERM_TMPL_ID_S values (null);

Insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','Modify All Documents','Modify All Documents',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;

insert into KRIM_PERM_TMPL_ID_S values (null);

Insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','Perform Document Action','Perform Document Action',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;

insert into KRIM_PERM_TMPL_ID_S values (null);

Insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','View Document Section','View Document Section',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;

insert into KRIM_PERM_TMPL_ID_S values (null);

INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID,NMSPC_CD,NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IDM','Edit Document Section','Edit Document Section',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
'Y',1, UUID() from KRIM_PERM_TMPL_ID_S;


-- REM INSERTING into KRIM_PERM_T
insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Create ProposalDevelopmentDocument','Create Proposal Development Document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Create ProtocolDocument','Create Protocol Document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Create CommitteeDocument','Add new committees in a unit',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AWARD','Create Award','Create Award',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Submit ProposalDevelopmentDocument','Submit a Proposal for approval',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Route Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Submit Protocol','Submit a Protocol to IRB for review',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Route Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Modify ProposalDevelopmentDocument','Modify Proposal Development Document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Modify Budget','Create/Modify Proposal Budget',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Modify Narrative','Create/Modify Proposal Narrative',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Modify ProposalPermissions','Assign Users to Proposal Roles',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Modify Protocol','Modify Protocol Document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Modify ProtocolPermissions','Assign Users to Protocol Roles',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Modify Committee','Modify existing committees in a unit',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Modify Schedule','Modify schedule details for committees in a unit',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Maintain Memberships','Maintain membership details in committees in a unit',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Maintain Minutes','Add/modify/delete minute entries in any schedule for committees in a unit',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AWARD','Modify Award','Modify Award',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AWARD','Maintain Award Documents','Maintain Award Documents',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','View Proposal','View Proposal Development Document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','View Budget','View Proposal Budget',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','View Narratives','View Proposal Narrative',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','View Protocol','View Protocol Document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','View Minutes','View Minutes',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','View Committee','View all committees in a unit',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','View Schedule','View schedule details of committees in a unit',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','View Agenda','View Agenda',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AWARD','View Award','View Award',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AWARD','View Award Documents','View Award Documents',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Alter Proposal Data','Change proposal master data once the proposal is locked',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Print Proposal','Print proposal on a sponsor specific path',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Certify','Certify',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Submit to Sponsor','Submit a Proposal to Grants.gov',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Add Proposal Viewer','Assign User to Proposal Viewer Role',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Create Ammendment','Create a new ammendment for a protocol',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Create Renewal','Create a new renewal for a protocol',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Administrative Correction','Perform Administrative Corrections on Protocols',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Maintain Protocol Submissions','Modify Protocol Submission details',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Add Notes','Add Protocol Notes',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','View Restricted Notes','View Restricted Notes in Protocols',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Generate Agenda','Generate Agenda',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Generate Minutes','Generate Minutes',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Generate Schedule','Generate Schedule',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','View Member Details','View membership details for a member in a committee',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Modify Any Protocol','Modify Any Protocol Document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Modify All Documents'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AWARD','View Any Proposal','View Any Proposal',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View All Documents'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Perform IRB Actions on a Protocol','Perform any IRB action on a protocol submitted to a committee',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Any Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Edit Proposal Log','Edit a Proposal Log',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Save Proposal Log','Save a Proposal Log',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Save Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Submit Proposal Log','Submit a Proposal Log',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Route Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-QUESTIONNAIRE','Modify Questionnaire',null,
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Questionnaire Permission'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-QUESTIONNAIRE','View Questionnaire',null,
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Questionnaire Permission'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Maintain ProposalHierarchy','Create, modify and synchronize ProposalHierarchies',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Administer Routing for Document','Allows users to open KC documents via the Super search option in Document Search and take Administrative workflow actions on them (such as approving the document, approving individual requests, or sending the document to a specified route node).',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Administer Routing for Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Blanket Approve Document','Allows access to the Blanket Approval button on KC Documents.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Initiate Document','Authorizes the initiation of KC Documents.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Open Document','Authorizes users to open KC Documents.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Save Document','Authorizes user to save documents answering to the KC parent document Type.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Save Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Delete Note / Attachment','Authorizes users to delete notes and attachments created by any user on documents answering to the KC parent document type.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Delete Note / Attachment'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Ad Hoc Review Document','Authorizes users to take the Approve action on KC documents Ad Hoc routed to them.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Ad Hoc Review Document','Authorizes users to take the FYI action on KC documents Ad Hoc routed to them.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Ad Hoc Review Document','Authorizes users to take the Acknowledge action on KC documents Ad Hoc routed to them.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Initiate Document','Authorizes the initiation of KC Simple Maintenance documents.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Assign Role','Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for roles with a Module Code beginning with KRA.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Assign Role'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Grant Permission','Authorizes users to modify the information on the Permissions tab of the Role Document for roles with a module code beginning with KRA.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Grant Permission'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Grant Responsibility','Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with a Module Code that begins with KFS.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Grant Responsibility'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-SYS','Maintain System Parameter','Authorizes users to initiate and edit the Parameter document for pameters with a module code beginning with KFS.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Maintain System Parameter'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KR-WKFLW','View Other Action List','Authorizes users to access other user''s action lists via the Help Desk Action List Login.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KR-WKFLW','Unrestricted Document Search','Allows power users to bypass the security associated with certain document types to limit the result set.',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PD','Blanket Approve ProposalDevelopmentDocument','Blanket Approve ProposalDevelopmentDocument',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'),
'N',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Blanket Approve ProtocolDocument','Blanket Approve ProtocolDocument',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'),
'N',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Blanket Approve CommitteeDocument','Blanket Approve CommitteeDocument',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'),
'N',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AWARD','Blanket Approve AwardDocument','Blanket Approve AwardDocument',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'),
'N',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AWARD','Blanket Approve TimeAndMoneyDocument','Blanket Approve TimeAndMoneyDocument',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'),
'N',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-QUESTIONNAIRE','Modify Question','Modify Question',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Question Permission'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-QUESTIONNAIRE','View Question','View Question',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Question Permission'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Create Proposal Log','Initiate a new Proposal Log',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Open Proposal Log','Open a Proposal Log',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Cancel Proposal Log','Cancel a Proposal Log',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Cancel Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Create Institutional Proposal','Initiate a new Institutional Proposal',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Edit Institutional Proposal','Edit a Institutional Proposal',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Save Institutional Proposal','Save a Institutional Proposal',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Save Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Submit Institutional Proposal','Submit a Institutional Proposal',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Route Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Open Institutional Proposal','Open a Institutional Proposal',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Cancel Institutional Proposal','Cancel a Institutional Proposal',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Cancel Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Create Intellectual Property Review','Initiate a new Intellectual Property Review',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Edit Intellectual Property Review','Edit a Intellectual Property Review',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Save Intellectual Property Review','Save a Intellectual Property Review',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Save Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Submit Intellectual Property Review','Submit a Intellectual Property Review',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Route Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Open Intellectual Property Review','Open a Intellectual Property Review',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-IP','Cancel Intellectual Property Review','Cancel a Intellectual Property Review',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Cancel Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Answer Protocol Questionnaire',null,
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Answer Questionnaire Permission'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-M','Create Valid Rate','Create a Valid Rates entry',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Modify Correspondence Template',null,
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','View Correspondence Template',null,
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AB','Blanket Approve AwardBudgetDocument','Blanket Approve AwardBudgetDocument',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AB','Submit AwardBudget','Submit award budget document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Route Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AB','Approve AwardBudget','Approve award budget document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AB','Post AwardBudget','Post award budget document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AB','Create AwardBudget','Create award budget document',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AB','Maintain AwardBudgetRouting','Maintaining Award budget routing',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Perform Document Action'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AB','Modify AwardBudget','Modify Award Budget at unit level',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'Edit Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-AB','View AwardBudget','View Award Budget at unit level',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KC-IDM' AND NM = 'View Document Section'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

Insert into KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','Modify Batch Correspondence Detail','Modify Batch Correspondence Detail',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Edit Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;

insert into KRIM_PERM_ID_S values (null);

INSERT INTO KRIM_PERM_T (PERM_ID,NMSPC_CD,NM,DESC_TXT,PERM_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),'KC-PROTOCOL','View Batch Correspondence Detail','View Batch Correspondence Detail',
(SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Open Document'),
'Y',1, UUID() from KRIM_PERM_ID_S;


-- REM INSERTING into KRIM_PERM_ATTR_DATA_T
Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Approve AwardBudget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardBudgetDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Blanket Approve AwardBudgetDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardBudgetDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Create AwardBudget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardBudgetDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Maintain AwardBudgetRouting'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardBudgetDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Modify AwardBudget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardBudgetDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Post AwardBudget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardBudgetDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Submit AwardBudget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardBudgetDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Submit AwardBudget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardBudgetDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Blanket Approve AwardDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Blanket Approve TimeAndMoneyDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'TimeAndMoneyDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Create Award'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Maintain Award Documents'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Maintain Award Documents'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'attachments' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Modify Award'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Modify Award'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'award' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Any Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'award' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award Documents'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'AwardDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award Documents'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'attachments' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Cancel Institutional Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'InstitutionalProposalDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Cancel Intellectual Property Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'IntellectualPropertyReviewMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Cancel Proposal Log'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalLogMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Create Institutional Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'InstitutionalProposalDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Create Intellectual Property Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'IntellectualPropertyReviewMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Create Proposal Log'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalLogMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Edit Institutional Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'InstitutionalProposalDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Edit Intellectual Property Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'IntellectualPropertyReviewMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Edit Proposal Log'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalLogMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Institutional Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'InstitutionalProposalDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Intellectual Property Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'IntellectualPropertyReviewMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Proposal Log'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalLogMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Save Institutional Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'InstitutionalProposalDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Save Intellectual Property Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'IntellectualPropertyReviewMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Save Proposal Log'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalLogMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Submit Institutional Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'InstitutionalProposalDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Submit Intellectual Property Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'IntellectualPropertyReviewMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Submit Proposal Log'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalLogMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-M' AND NM = 'Create Valid Rate'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Role'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'roleName'), 'OSP Administrator' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Add Proposal Viewer'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Add Proposal Viewer'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'add_viewer' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Alter Proposal Data'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Alter Proposal Data'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'alter_master_data' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Blanket Approve ProposalDevelopmentDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Certify'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Certify'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'certify' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Create ProposalDevelopmentDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify Budget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify Budget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'budget' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify Narrative'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify Narrative'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'narrative' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify ProposalDevelopmentDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify ProposalDevelopmentDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'proposal' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify ProposalPermissions'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify ProposalPermissions'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'proposal_permissions' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Print Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Print Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'print' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Submit ProposalDevelopmentDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Submit to Sponsor'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Submit to Sponsor'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'submit_to_sponsor' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Budget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Budget'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'budget' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Narratives'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Narratives'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'narrative' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProposalDevelopmentDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Proposal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'proposal' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Add Notes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Add Notes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'notes' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Administrative Correction'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Administrative Correction'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'admin_correction' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Answer Protocol Questionnaire'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Blanket Approve CommitteeDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'CommitteeDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Blanket Approve ProtocolDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create Ammendment'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create Ammendment'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'create_ammendment' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create CommitteeDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'CommitteeDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create ProtocolDocument'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create Renewal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create Renewal'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'create_renewal' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Generate Agenda'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'MeetingManagementDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Generate Agenda'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'generate_agenda' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Generate Minutes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'MeetingManagementDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Generate Minutes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'generate_minutes' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Generate Schedule'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'CommitteeDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Generate Schedule'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'generate_schedule' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Memberships'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'CommitteeDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Memberships'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'members' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Minutes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'MeetingManagementDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Minutes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'minutes' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Protocol Submissions'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Protocol Submissions'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'modify_submission_details' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Any Protocol'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Batch Correspondence Detail'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'BatchCorrespondenceDetailMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Committee'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'CommitteeDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Committee'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'committee' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Correspondence Template'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolCorrespondenceTemplateMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Protocol'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Protocol'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'protocol' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify ProtocolPermissions'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify ProtocolPermissions'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'protocol_permissions' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Schedule'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'CommitteeDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Schedule'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'schedule' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Perform IRB Actions on a Protocol'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Submit Protocol'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Agenda'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'MeetingManagementDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Agenda'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'agenda' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Batch Correspondence Detail'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'BatchCorrespondenceDetailMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Committee'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'CommitteeDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Committee'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'committee' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Correspondence Template'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolCorrespondenceTemplateMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Member Details'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'CommitteeDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Member Details'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'view_member_details' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Minutes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'MeetingManagementDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Minutes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'minutes' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Protocol'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Protocol'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'protocol' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Restricted Notes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'ProtocolDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Restricted Notes'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Action'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'documentAction'), 'view_restricted_notes' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Schedule'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'CommitteeDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Schedule'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Document Section'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'sectionName'), 'schedule' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' AND NM = 'Modify Question'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'QuestionMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' AND NM = 'Modify Questionnaire'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'QuestionnaireMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' AND NM = 'View Question'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'QuestionMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' AND NM = 'View Questionnaire'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'QuestionnaireMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Ad Hoc Review Document' AND DESC_TXT LIKE '% the Approve action %'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionRequestCd'), 'A' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Ad Hoc Review Document' AND DESC_TXT LIKE '% the FYI action %'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionRequestCd'), 'F' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Ad Hoc Review Document' AND DESC_TXT LIKE '% the Acknowledge action %'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionRequestCd'), 'K' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Ad Hoc Review Document' AND DESC_TXT LIKE '% the Approve action %'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Ad Hoc Review Document' AND DESC_TXT LIKE '% the FYI action %'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Ad Hoc Review Document' AND DESC_TXT LIKE '% the Acknowledge action %'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Ad Hoc Review'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Administer Routing for Document'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Assign Role'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Role'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KC*' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Assign Role'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Role'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KRA*' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Blanket Approve Document'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Delete Note / Attachment'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Document Type & Relationship to Note Author'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Delete Note / Attachment'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Document Type & Relationship to Note Author'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'createdBySelfOnly'), 'false' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Grant Permission'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Permission'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KC*' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Grant Permission'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Permission'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KRA*' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Grant Responsibility'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Responsibility'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KC*' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Grant Responsibility'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-IDM' AND NM = 'Responsibility'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KRA*' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Initiate Document' AND DESC_TXT = 'Authorizes the initiation of KC Documents.'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Initiate Document' AND DESC_TXT = 'Authorizes the initiation of KC Simple Maintenance documents.'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KcMaintenanceDocument' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Maintain System Parameter'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Parameter'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KC*' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Maintain System Parameter'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'Parameter'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-NS' AND NM = 'namespaceCode'), 'KRA*' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Open Document'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type (Permission)'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC' from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
select Max(ID),UUID(), 1,
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Save Document'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Document Type & Routing Node or State'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'), 'KC' from KRIM_ATTR_DATA_ID_S;


-- REM INSERTING into KRIM_ROLE_T
Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-SYS','Manager','This role represents a collection of all the KC module manager roles and has permission to initiate simple maintenance documents.',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','IRBApprover','IRB Approver',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','OSPApprover','Office of Sponsored Projects Approver',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AB','Award Budget Viewer','Award Budget Viewer - the role grants permissions to view award budget at departmental level',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AB','Award Budget Modifier','Award Budget Modifier - the role grants permissions to modify or view award budget at departmental level',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-PD','approver','approver',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-PROTOCOL','Protocol Unassigned','Protocol Unassigned - no permissions',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-PROTOCOL','Protocol Viewer','Protocol Viewer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-PROTOCOL','Protocol Aggregator','Protocol Aggregator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-PD','unassigned','Unassigned role - no permissions',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-PD','Viewer','Proposal Viewer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-PD','Budget Creator','Proposal Budget Creator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-PD','Narrative Writer','Proposal Narrative Writer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-PD','Aggregator','Proposal Aggregator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-UNT','Proposal Creator','Proposal Creator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Unit'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AB','Award Budget Approver','Award Budget Approver - the role grants permissions to edit and approve award budget',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AB','Award Budget Aggregator','Award Budget Aggregator - the role grants permissions to create and maintain award budget at department level',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AB','Award Budget Maintainer','Maintain Award Budget - the role grants permissions to modify and submit award budget at departmental level',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AB','Award Budget Administrator','Award Budget Administrator - the role grants permissions to manage any award budget at OSP level',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-IP','Intellectual Property Review Maintainer','Maintain Intellectual Property Review',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-IP','Institutional Proposal Maintainer','Maintain Institutional Proposals',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-IP','Institutional Proposal Viewer','View Institutional Proposals',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AWARD','Award Unassigned','Award Unassigned',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AWARD','Template Viewer','Template Viewer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AWARD','Departments Awards Viewer','Departments Awards Viewer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AWARD','Award Documents Viewer','Award Documents Viewer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AWARD','Award Viewer','Award Viewer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AWARD','Award Documents Maintainer','Award Documents Maintainer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AWARD','Application Administrator','Application Administrator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-AWARD','Award Modifier','Award Modifier',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-UNT','IRB Reviewer','IRB Reviewer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-UNT','IRB Administrator','IRB Administrator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-UNT','Protocol Creator','Protocol Creator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-ADM','Proposal Submission','Proposal Submission',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-ADM','OSP Administrator','OSP Administrator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'UnitHierarchy'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','PI','Proposal Primary Investigator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Derived Role - PI'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','COI','Proposal Co-Investigator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Derived Role - COI'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','DepartmentReviewer','Proposal Departmental Reviewer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Derived Role - KeyPerson'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','CustomReviewer','Proposal Custom Reviewer',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Derived Role - KeyPerson'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','KP','Proposal Key Persons',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Derived Role - KeyPerson'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

Insert into KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-IP','Proposal Log PI','Derived role from PI on Proposal Log',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Derived Role - Proposal Log PI'),
'Y',null,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

INSERT INTO KRIM_ROLE_T (ROLE_ID,NMSPC_CD,ROLE_NM,DESC_TXT,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-IP','Unit Administrator','Derived role based on Unit',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Derived Role - Unit Administrator'),
'Y',NULL,1, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

INSERT INTO KRIM_ROLE_T (ROLE_ID,ROLE_NM,NMSPC_CD,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID,LAST_UPDT_DT) 
select Max(ID),'Create Temporary Proposal Log','KC-IP','Create Temporary Proposal Log',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'),
'Y',1, UUID(), NULL from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

INSERT INTO KRIM_ROLE_T (ROLE_ID,ROLE_NM,NMSPC_CD,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID,LAST_UPDT_DT) 
select Max(ID),'Create Proposal Log','KC-IP','Create Proposal Log',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'),
'Y',1, UUID(), NULL from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

INSERT INTO KRIM_ROLE_T (ROLE_ID,ROLE_NM,NMSPC_CD,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID,LAST_UPDT_DT) 
select Max(ID),'Modify Proposal Log','KC-IP','Modify Proposal Log',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'),
'Y',1, UUID(),NULL from KRIM_ROLE_ID_S;
'Y',1, null, UUID() from KRIM_ROLE_ID_S;

Insert into KRIM_ROLE_ID_S values (null);

INSERT INTO KRIM_ROLE_T (ROLE_ID,ROLE_NM,NMSPC_CD,DESC_TXT,KIM_TYP_ID,ACTV_IND,VER_NBR,OBJ_ID,LAST_UPDT_DT) 
select Max(ID),'View Proposal Log','KC-IP','View Proposal Log',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'UnitHierarchy' AND NMSPC_CD = 'KC-SYS'),
'Y',1, UUID(), NULL from KRIM_ROLE_ID_S;
'Y',1, null, UUID() from KRIM_ROLE_ID_S;

-- REM INSERTING into KRIM_ROLE_PERM_T
insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Proposal Creator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Create ProposalDevelopmentDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify ProposalDevelopmentDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify Narrative'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Narratives'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify Budget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Budget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify ProposalPermissions'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Print Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Certify'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Submit ProposalDevelopmentDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Add Proposal Viewer'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Narrative Writer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify ProposalDevelopmentDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Narrative Writer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Narrative Writer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify Narrative'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Narrative Writer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Narratives'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Narrative Writer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Budget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Narrative Writer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Print Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Budget Creator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify ProposalDevelopmentDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Budget Creator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Budget Creator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Narratives'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Budget Creator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Modify Budget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Budget Creator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Budget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Budget Creator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Print Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Narratives'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Budget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Print Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'OSP Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'OSP Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Narratives'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'OSP Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Budget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'OSP Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Alter Proposal Data'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'OSP Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Add Proposal Viewer'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'Proposal Submission'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'Proposal Submission'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Narratives'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'Proposal Submission'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'View Budget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'Proposal Submission'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Alter Proposal Data'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'Proposal Submission'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Print Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'Proposal Submission'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Submit to Sponsor'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'Protocol Creator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create ProtocolDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Any Protocol'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Submit Protocol'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Protocol'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Protocol'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify ProtocolPermissions'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Add Notes'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create Ammendment'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create Renewal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND ROLE_NM = 'Protocol Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Protocol'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Committee'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Schedule'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Perform IRB Actions on a Protocol'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Restricted Notes'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Agenda'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Committee'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Member Details'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Minutes'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Schedule'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Protocol'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Create CommitteeDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Administrative Correction'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Generate Agenda'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Generate Minutes'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Generate Schedule'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Edit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Save Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Open Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Temporary Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Cancel Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Edit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Save Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Open Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Cancel Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Create Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Edit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Save Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Submit Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Open Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Modify Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Cancel Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,OBJ_ID,VER_NBR) 
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'View Proposal Log' AND NMSPC_CD = 'KC-IP'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NM = 'Open Proposal Log' AND NMSPC_CD = 'KC-IP'),
'Y', UUID(),1 from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Memberships'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Minutes'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Maintain Protocol Submissions'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Agenda'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Committee'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Member Details'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Minutes'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Schedule'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KUALI' AND ROLE_NM = 'User'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Ad Hoc Review Document' AND DESC_TXT like '% Approve %'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KUALI' AND ROLE_NM = 'User'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Ad Hoc Review Document' AND DESC_TXT like '% FYI %'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KUALI' AND ROLE_NM = 'User'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Ad Hoc Review Document' AND DESC_TXT like '% Acknowledge %'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AWARD' AND ROLE_NM = 'Award Modifier'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Create Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AWARD' AND ROLE_NM = 'Award Modifier'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Modify Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AWARD' AND ROLE_NM = 'Award Modifier'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AWARD' AND ROLE_NM = 'Application Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Any Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AWARD' AND ROLE_NM = 'Award Documents Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Maintain Award Documents'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Batch Correspondence Detail'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AWARD' AND ROLE_NM = 'Award Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Batch Correspondence Detail'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AWARD' AND ROLE_NM = 'Award Documents Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award Documents'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' AND NM = 'Modify Questionnaire'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' AND NM = 'View Questionnaire'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-PD' AND ROLE_NM = 'Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Maintain ProposalHierarchy'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Blanket Approve Document'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Save Document'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Delete Note / Attachment'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Initiate Document' AND DESC_TXT LIKE '% KC Simple %'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Assign Role'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Grant Permission'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Grant Responsibility'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Maintain System Parameter'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Maintain System Parameter'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT = 'Authorizes users to view the entire Tax Identification Number on the Person document and inquiry.'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'View Other Action List' AND DESC_TXT LIKE 'Authorizes users to access other user''s action lists via the Help Desk Action List Login.'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Unrestricted Document Search' AND DESC_TXT LIKE 'Allows power users to bypass the security associated with certain document types to limit the result set.'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Full Unmask Field' AND DESC_TXT LIKE '% Person document %'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-SYS' AND NM = 'Initiate Document'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' AND NM = 'Modify Question'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-QUESTIONNAIRE' AND NM = 'View Question'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Proposal Log PI'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Proposal Log'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KUALI' AND ROLE_NM = 'User'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-SYS' AND NM = 'Open Document'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Create Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Edit Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Save Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Submit Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Cancel Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Create Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Edit Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Save Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Submit Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Institutional Proposal Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Cancel Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Intellectual Property Review Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Intellectual Property Review Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Create Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Intellectual Property Review Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Edit Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Intellectual Property Review Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Save Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Intellectual Property Review Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Submit Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Intellectual Property Review Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Intellectual Property Review Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Cancel Intellectual Property Review'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Unit Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

-- Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
-- VALUES (KRIM_ROLE_PERM_ID_S.NEXTVAL,
-- (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-IP' AND ROLE_NM = 'Unit Administrator'),
-- (SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Institutional Proposal'),
-- 'Y',1,SYS_GUID());

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Answer Protocol Questionnaire'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-ADM' AND ROLE_NM = 'OSP Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-M' AND NM = 'Create Valid Rate'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PD' AND NM = 'Blanket Approve ProposalDevelopmentDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Blanket Approve ProtocolDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Blanket Approve CommitteeDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Blanket Approve AwardDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-SYS' AND ROLE_NM = 'Manager'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'Blanket Approve TimeAndMoneyDocument'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'Modify Correspondence Template'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-UNT' AND ROLE_NM = 'IRB Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-PROTOCOL' AND NM = 'View Correspondence Template'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Create AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Post AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Submit AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Maintain AwardBudgetRouting'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Modify AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'View AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Administrator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Submit AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Modify AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'View AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Maintainer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Create AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Submit AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'View AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Aggregator'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'View AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Viewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Modifier'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Modify AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Modifier'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Approver'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AB' AND NM = 'Approve AwardBudget'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Approver'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KR-WKFLW' AND ROLE_NM = 'Initiator or Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Proposal Log'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

Insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KR-WKFLW' AND ROLE_NM = 'Initiator or Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-IP' AND NM = 'Open Institutional Proposal'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;

insert into KRIM_ROLE_PERM_ID_S values (null);

INSERT INTO KRIM_ROLE_PERM_T (ROLE_PERM_ID,ROLE_ID,PERM_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KR-WKFLW' AND ROLE_NM = 'Initiator or Reviewer'),
(SELECT PERM_ID FROM KRIM_PERM_T WHERE NMSPC_CD = 'KC-AWARD' AND NM = 'View Award'),
'Y',1, UUID() from KRIM_ROLE_PERM_ID_S;


-- REM INSERTING into KRIM_GRP_T
Insert into KRIM_GRP_ID_S values (null);

Insert into KRIM_GRP_T (GRP_ID,NMSPC_CD,GRP_NM,GRP_DESC,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','KcAdmin','Kuali Coeus Administrator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',null,1, UUID() from KRIM_GRP_ID_S;

Insert into KRIM_GRP_ID_S values (null);

Insert into KRIM_GRP_T (GRP_ID,NMSPC_CD,GRP_NM,GRP_DESC,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','OSP Superuser','Kuali Coeus Administrator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',null,1, UUID() from KRIM_GRP_ID_S;

Insert into KRIM_GRP_ID_S values (null);

Insert into KRIM_GRP_T (GRP_ID,NMSPC_CD,GRP_NM,GRP_DESC,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','ProposalAdmin','Proposal Workflow Administrator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',null,1, UUID() from KRIM_GRP_ID_S;

Insert into KRIM_GRP_ID_S values (null);

Insert into KRIM_GRP_T (GRP_ID,NMSPC_CD,GRP_NM,GRP_DESC,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','IRBAdmin','IRB Administrator',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',null,1, UUID() from KRIM_GRP_ID_S;

Insert into KRIM_GRP_ID_S values (null);

Insert into KRIM_GRP_T (GRP_ID,NMSPC_CD,GRP_NM,GRP_DESC,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Proposal Development - Department Reviewers','Reviewes for DepartmentalReview route node.',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',NULL,1, UUID() from KRIM_GRP_ID_S;

Insert into KRIM_GRP_ID_S values (null);

INSERT INTO KRIM_GRP_T (GRP_ID,NMSPC_CD,GRP_NM,GRP_DESC,KIM_TYP_ID,ACTV_IND,LAST_UPDT_DT,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Proposal Development - Custom Approval Reviewers','Reviewes for CustomApproval route node.',
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KUALI' AND NM = 'Default'),
'Y',NULL,1, UUID() from KRIM_GRP_ID_S;


-- REM INSERTING into KRIM_RSP_T
Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Proposal Initial Approval','Proposal Development Document - Initial Approval',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Proposal OSP Office Approval','Proposal Development Document - OSP Office Approval',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Proposal Persons Approval','Proposal Development Document - Key Personnel Approval',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Proposal Custom Approval','Proposal Development Document - Custom Approval',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Proposal Departmental Approval','Proposal Development Document - Departmental Approval',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','IRB Approve','Protocol Document - IRBApprove',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','IRB AssignedToCommittee','Protocol Document - AssignedToCommittee',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','IRB AssignedToAgenda','Protocol Document - AssignedToAgenda',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','IRB Review','Protocol Document - IRBReview',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','IRB Receipt','Protocol Document - IRBReceipt',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

Insert into KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Award Budget InitialApproval','Award Budget Document - Initial Approval',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;

Insert into KRIM_RSP_ID_S values (null);

INSERT INTO KRIM_RSP_T (RSP_ID,NMSPC_CD,NM,DESC_TXT,RSP_TMPL_ID,ACTV_IND,VER_NBR,OBJ_ID)
select Max(ID),'KC-WKFLW','Award Budget OSPApproval','Award Budget Document - OSP Approval',
(SELECT RSP_TMPL_ID FROM KRIM_RSP_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Review'),
'Y',1, UUID() from KRIM_RSP_ID_S;


-- REM INSERTING into KRIM_RSP_ATTR_DATA_T
Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Initial Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProposalDevelopmentDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Initial Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'OSPInitial',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Initial Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Initial Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal OSP Office Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProposalDevelopmentDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal OSP Office Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'OSPOfficeRouting',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal OSP Office Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal OSP Office Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProposalDevelopmentDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'ProposalPersons',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Custom Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProposalDevelopmentDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Custom Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'UnitRouting',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Custom Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Custom Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Departmental Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProposalDevelopmentDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Departmental Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'DepartmentalRouting',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Departmental Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Departmental Approval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Approve'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProtocolDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Approve'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'IRBApprove',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Approve'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Approve'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToCommittee'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProtocolDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToCommittee'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'AssignedToCommittee',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToCommittee'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToCommittee'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToAgenda'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProtocolDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToAgenda'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'AssignedToAgenda',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToAgenda'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToAgenda'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProtocolDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'IRBReview',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Review'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Receipt'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'ProtocolDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Receipt'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'IRBReceipt',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Receipt'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Receipt'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget InitialApproval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'AwardBudgetDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget InitialApproval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'AwardBudgetInitialApproval',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget InitialApproval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget InitialApproval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget OSPApproval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'documentTypeName'),
'AwardBudgetDocument',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget OSPApproval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'routeNodeName'),
'AwardBudgetOSPApproval',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget OSPApproval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'required'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

Insert into KRIM_ATTR_DATA_ID_S values (null);

Insert into KRIM_RSP_ATTR_DATA_T (ATTR_DATA_ID,RSP_ID,KIM_TYP_ID,KIM_ATTR_DEFN_ID,ATTR_VAL,VER_NBR,OBJ_ID)
select Max(ID),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget OSPApproval'),
(SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Document Type, Routing Node & Action Information'),
(SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'actionDetailsAtRoleMemberLevel'),
'false',1, UUID() from KRIM_ATTR_DATA_ID_S;

-- REM INSERTING into KRIM_ROLE_RSP_T
insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'OSPApprover'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal OSP Office Approval'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'OSPApprover'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Initial Approval'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'PI'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'COI'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'KP'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'CustomReviewer'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Custom Approval'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'DepartmentReviewer'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Departmental Approval'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'IRBApprover'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Review'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'IRBApprover'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToAgenda'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'IRBApprover'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToCommittee'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-WKFLW' AND ROLE_NM = 'IRBApprover'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Approve'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

Insert into KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Approver'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget OSPApproval'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;

insert into KRIM_ROLE_RSP_ID_S values (null);

INSERT INTO KRIM_ROLE_RSP_T (ROLE_RSP_ID,ROLE_ID,RSP_ID,ACTV_IND,VER_NBR,OBJ_ID)
select max(id),
(SELECT ROLE_ID FROM KRIM_ROLE_T WHERE NMSPC_CD = 'KC-AB' AND ROLE_NM = 'Award Budget Approver'),
(SELECT RSP_ID FROM KRIM_RSP_T WHERE NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget InitialApproval'),
'Y',1, UUID() from KRIM_ROLE_RSP_ID_S;


-- REM INSERTING into KRIM_ROLE_RSP_ACTN_T
Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'N','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'OSPApprover' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal OSP Office Approval'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'N','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'OSPApprover' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Initial Approval'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','A',1,'N','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'PI' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','A',1,'N','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'COI' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','A',1,'N','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'KP' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Persons Approval'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'N','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'CustomReviewer' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Custom Approval'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'N','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'DepartmentReviewer' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'Proposal Departmental Approval'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'Y','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'IRBApprover' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Review'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'Y','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'IRBApprover' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToAgenda'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'Y','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'IRBApprover' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB AssignedToCommittee'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'Y','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-WKFLW' AND RO.ROLE_NM = 'IRBApprover' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'IRB Approve'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

Insert into KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'N','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-AB' AND RO.ROLE_NM = 'Award Budget Approver' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget OSPApproval'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

Insert into KRIM_ROLE_RSP_ACTN_ID_S values (null);

INSERT INTO KRIM_ROLE_RSP_ACTN_T (ROLE_RSP_ACTN_ID,ACTN_TYP_CD,ACTN_PLCY_CD,PRIORITY_NBR,FRC_ACTN,ROLE_MBR_ID,ROLE_RSP_ID,VER_NBR,OBJ_ID)
select Max(ID),'A','F',1,'N','*',
(SELECT ROLE_RSP_ID FROM KRIM_ROLE_RSP_T RR, KRIM_ROLE_T RO, KRIM_RSP_T RS
WHERE RR.ROLE_ID = RO.ROLE_ID AND RO.NMSPC_CD = 'KC-AB' AND RO.ROLE_NM = 'Award Budget Approver' 
AND RR.RSP_ID = RS.RSP_ID AND RS.NMSPC_CD = 'KC-WKFLW' AND NM = 'Award Budget InitialApproval'),
1, UUID() from KRIM_ROLE_RSP_ACTN_ID_S;

-- Add create institutional proposal, submit institutional proposal permissions to Proposal Submission role
Insert into KRIM_ROLE_PERM_ID_S values (null);

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
select Max(ID), UUID(), 1, 
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Submission'), 
(select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), 'Y' from KRIM_ROLE_PERM_ID_S;

Insert into KRIM_ROLE_PERM_ID_S values (null);

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
select Max(ID), UUID(), 1, 
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Submission'), 
(select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), 'Y' from KRIM_ROLE_PERM_ID_S;

-- Add create institutional proposal, submit institutional proposal permissions to Award Modifier role
Insert into KRIM_ROLE_PERM_ID_S values (null);

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
select Max(ID), UUID(), 1, 
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Modifier'), 
(select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), 'Y' from KRIM_ROLE_PERM_ID_S;

Insert into KRIM_ROLE_PERM_ID_S values (null);

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
select Max(ID), UUID(), 1, 
(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Modifier'), 
(select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), 'Y' from KRIM_ROLE_PERM_ID_S;

COMMIT;