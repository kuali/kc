update sh_parm_t t
   set t.sh_parm_txt = 'N'
 where t.sh_parm_nmspc_cd = 'KR-NS'
   AND T.SH_PARM_DTL_TYP_CD = 'Document'
   and t.sh_parm_nm = 'DEFAULT_CAN_PERFORM_ROUTE_REPORT_IND';
