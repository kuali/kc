INSERT INTO krim_perm_t(PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND) 
  VALUES('840','97469975-D110-9A65-5EE5-F21FD1BEB5B2', '1', '29', 'KR-BUS', 'Use Screen', 'Allows users to access the Configuration Viewer screen', 'Y') 
/ 

INSERT INTO krim_perm_attr_data_t(ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL) 
  VALUES('880', 'ECCB8A6C-A0DA-5311-6A57-40F743EA334C', '1', '840', '12', '2','org.kuali.rice.ksb.messaging.web.ConfigViewerAction') 
/ 

INSERT INTO krim_role_perm_t(ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND) 
  VALUES('855', 'E83AB210-EB48-3BDE-2D6F-F6177869AE27', '1', '63', '840', 'Y') 
/ 

COMMIT;