DELIMITER /
UPDATE KRCR_PARM_T
SET VAL = '0',
    PARM_DESC_TXT = 'When the sponsor or the prime sponsor has the sponsor type specified in this parameter and the KFS parameter FEDERAL_ONLY_IND is "Y", the Effort reporting Document is routed.  Also is used in proposal for checking grants.gov checking.'
WHERE PARM_NM = 'FEDERAL_SPONSOR_TYPE_CODE' AND NMSPC_CD = 'KC-AWARD'
/

DELIMITER ;
