DELIMITER /
update KRCR_PARM_T set VAL = '34' where PARM_NM = 'FDP_Prime_Administrative_Contact_Code' and NMSPC_CD = 'KC-SUBAWARD'
/
update KRCR_PARM_T set VAL = '36' where PARM_NM = 'FDP_Prime_Authorized_Official_Code' and NMSPC_CD = 'KC-SUBAWARD'
/
update KRCR_PARM_T set VAL = '35' where PARM_NM = 'FDP_Prime_Financial_Contact_Code' and NMSPC_CD = 'KC-SUBAWARD'
/
update KRCR_PARM_T set VAL = '31' where PARM_NM = 'FDP_Sub_Administrative_Contact_Code' and NMSPC_CD = 'KC-SUBAWARD'
/
update KRCR_PARM_T set VAL = '33' where PARM_NM = 'FDP_Sub_Authorized_Official_Code' and NMSPC_CD = 'KC-SUBAWARD'
/
update KRCR_PARM_T set VAL = '32' where PARM_NM = 'FDP_Sub_Financial_Contact_Code' and NMSPC_CD = 'KC-SUBAWARD'
/
DELIMITER ;

