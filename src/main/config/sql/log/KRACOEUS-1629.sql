-- This sql will set the lookup results limit to 200 (currently 70)

update sh_parm_t set SH_PARM_TXT=200 where SH_PARM_NM='RESULTS_LIMIT'