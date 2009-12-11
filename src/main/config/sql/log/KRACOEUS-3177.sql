UPDATE KRIM_TYP_ATTR_T T SET T.SORT_CD = 'B' WHERE T.KIM_TYP_ID = (SELECT KIM_TYP_ID from krim_typ_t t where t.nm = 'UnitHierarchy' and nmspc_cd = 'KC-SYS')
AND T.KIM_ATTR_DEFN_ID = (SELECT kim_attr_defn_id FROM KRIM_ATTR_DEFN_T T where NM = 'subunits' and nmspc_cd = 'KC-SYS');
COMMIT;
