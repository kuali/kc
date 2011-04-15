-- assign open Time and Money permissions to Time And Money Viewer
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Viewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), 'Y');

-- assign view Time and Money permissions to Time And Money Viewer
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Viewer'), (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), 'Y');



-- assign all time and money permissions to Time and Money Modifier role
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Create Time And Money Document'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Modify Time And Money Document'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Save Time And Money Document'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Submit Time And Money Document'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='Cancel Time And Money Document'), 'Y');
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Time And Money Modifier'), (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), 'Y');

--assign time and money viewer permission to award budget viewer
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer'), (select PERM_ID from KRIM_PERM_T where NM='View Time And Money Document'), 'Y');

-- assign open Time and Money permissions to award budget Viewer
insert into krim_role_perm_t values (KRIM_ROLE_PERM_ID_BS_S.nextval, sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Award Budget Viewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Time And Money Document'), 'Y');

COMMIT;