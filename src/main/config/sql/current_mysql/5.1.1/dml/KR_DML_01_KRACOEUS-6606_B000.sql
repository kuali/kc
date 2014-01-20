DELIMITER /

UPDATE KRCR_PARM_T
  SET VAL           = 'default.htm?turl=Documents/permissions.htm'
  WHERE NMSPC_CD    = 'KC-PD'
    AND PARM_NM     = 'proposalDevelopmentPermissionsHelp'
    AND PARM_TYP_CD = 'HELP'
    AND APPL_ID     = 'KC'
    AND VAL         = 'default.htm?turl=Documents/permissions1.htm'
/

UPDATE KRCR_PARM_T
  SET VAL           = 'default.htm?turl=Documents/permissions1.htm'
  WHERE NMSPC_CD    = 'KC-PROTOCOL'
    AND PARM_NM     = 'protocolPermissionsHelp'
    AND PARM_TYP_CD = 'HELP'
    AND APPL_ID     = 'KC'
    AND VAL         = 'default.htm?turl=Documents/permissions2.htm'
/

DELIMITER ;
