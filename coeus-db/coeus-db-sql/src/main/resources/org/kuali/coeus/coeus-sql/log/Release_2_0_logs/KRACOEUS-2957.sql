update KRNS_PARM_T set PARM_DTL_TYP_CD = 'D' where
NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'A' and PARM_NM = 'initialUnitLoadDepth';
commit;