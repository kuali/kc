-- KRNS_PARM_DTL_TYP_T 
alter table KRNS_PARM_DTL_TYP_T disable constraint KRNS_PARM_DTL_TYP_TR1;
update KRNS_PARM_DTL_TYP_T set NMSPC_CD='KC-PD' where NMSPC_CD='KRA-PD';
update KRNS_PARM_DTL_TYP_T set NMSPC_CD='KC-B' where NMSPC_CD='KRA-B';
update KRNS_PARM_DTL_TYP_T set NMSPC_CD='KC-M' where NMSPC_CD='KRA-M';

-- KRNS_PARM_T 
alter table KRNS_PARM_T disable constraint KRNS_PARM_TR1;
update KRNS_PARM_T set NMSPC_CD='KC-PD' where NMSPC_CD='KRA-PD';
update KRNS_PARM_T set NMSPC_CD='KC-B' where NMSPC_CD='KRA-B';
update KRNS_PARM_T set NMSPC_CD='KC-M' where NMSPC_CD='KRA-M';

-- KRIM_ROLE_T 
update KRIM_ROLE_T set NMSPC_CD='KC-PD' where NMSPC_CD='KRA-PD';

-- KRIM_PERM_T 
update KRIM_PERM_T set NMSPC_CD='KC-PD' where NMSPC_CD='KRA-PD';

-- KRNS_NMSPC_T 
update KRNS_NMSPC_T set NMSPC_CD='KC-PD' where NMSPC_CD='KRA-PD';
update KRNS_NMSPC_T set NMSPC_CD='KC-B' where NMSPC_CD='KRA-B';
update KRNS_NMSPC_T set NMSPC_CD='KC-M' where NMSPC_CD='KRA-M';

-- Reenable constraints
alter table KRNS_PARM_DTL_TYP_T enable constraint KRNS_PARM_DTL_TYP_TR1;
alter table KRNS_PARM_T enable constraint KRNS_PARM_TR1;

commit;