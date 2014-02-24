
-- Proposal Log
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values (KRIM_ROLE_PERM_ID_S.NEXTVAL, '2EBED68E667B4D2A98770165A78346FD', 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Initiator or Reviewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Proposal Log'), 'Y');

-- Institutional Proposal
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values (KRIM_ROLE_PERM_ID_S.NEXTVAL, '2BF7F3D7A59B4D518FA1111BFE566843', 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Initiator or Reviewer'), (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
