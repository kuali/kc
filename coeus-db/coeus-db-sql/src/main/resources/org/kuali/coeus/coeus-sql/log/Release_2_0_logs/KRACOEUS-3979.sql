-- Add create institutional proposal, submit institutional proposal permissions to Proposal Submission role
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values (KRIM_ROLE_PERM_ID_S.NEXTVAL, 'EFEA9886C913436BA189CA7034CB26B4', 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Submission'), (select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), 'Y');

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values (KRIM_ROLE_PERM_ID_S.NEXTVAL, 'EA793FFDE412441EB270C4C0D4D8BEA2', 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Proposal Submission'), (select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), 'Y');

-- Add create institutional proposal, submit institutional proposal permissions to Award Modifier role
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values (KRIM_ROLE_PERM_ID_S.NEXTVAL, '3D4CCEEC-D351-17C6-6DA5-33303E128093', 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), 'Y');

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values (KRIM_ROLE_PERM_ID_S.NEXTVAL, '5890F4B3-CAE4-51F2-10CC-186B0C98CF99', 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), 'Y');
