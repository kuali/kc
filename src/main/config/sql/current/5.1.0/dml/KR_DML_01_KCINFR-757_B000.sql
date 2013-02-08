update KRIM_TYP_T set SRVC_NM = '{http://kc.kuali.org/core/v5_0}'||SRVC_NM where NM LIKE 'Derived Role%' AND NMSPC_CD LIKE 'KC-%'
/
