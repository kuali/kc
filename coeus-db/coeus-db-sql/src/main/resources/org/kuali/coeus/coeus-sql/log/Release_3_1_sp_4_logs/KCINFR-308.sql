/*create new permission and associate it with current "Aggregator" and "Modify Budget" roles which has been backed up*/
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
values (KRIM_PERM_ID_BS_S.nextval, SYS_GUID(), 1, (select perm_tmpl_id from KRIM_PERM_T where nm='Modify Budget'), 'KC-PD', 'Modify Proposal Rates', 'Modify Proposal Budget Rates', 'Y');
/
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL) 
values (KRIM_ATTR_DATA_ID_BS_S.nextval, SYS_GUID(), 1, KRIM_PERM_ID_BS_S.currval, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Section'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'sectionName'), 'budget');
/
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL) 
values (KRIM_ATTR_DATA_ID_BS_S.nextval, SYS_GUID(), 1, KRIM_PERM_ID_BS_S.currval, (SELECT KIM_TYP_ID FROM KRIM_TYP_T WHERE NM = 'Document Section'), (SELECT KIM_ATTR_DEFN_ID FROM KRIM_ATTR_DEFN_T WHERE NM = 'documentTypeName'), 'ProposalDevelopmentDocument');
/
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
values (KRIM_ROLE_PERM_ID_BS_S.nextval, SYS_GUID(), 1, ( select ROLE_ID from KRIM_ROLE_T where ROLE_NM ='Aggregator' ), KRIM_PERM_ID_BS_S.currval, 'Y');
/
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
values (KRIM_ROLE_PERM_ID_BS_S.nextval, SYS_GUID(), 1, ( select ROLE_ID from KRIM_ROLE_T where ROLE_NM ='Budget Creator'), KRIM_PERM_ID_BS_S.currval, 'Y');
/
commit;