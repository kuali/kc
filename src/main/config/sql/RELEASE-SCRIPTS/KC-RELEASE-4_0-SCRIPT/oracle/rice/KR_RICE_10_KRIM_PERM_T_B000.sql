DECLARE riceId NUMBER;
        duplicateId NUMBER;
BEGIN
    SELECT MIN(PERM_ID) into riceId FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'View Other Action List';
    SELECT MAX(PERM_ID) into duplicateId FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'View Other Action List';
    EXECUTE IMMEDIATE 'UPDATE KRIM_ROLE_PERM_T SET PERM_ID = ' || riceId || ' WHERE PERM_ID = ' || duplicateId || '';
    EXECUTE IMMEDIATE 'DELETE FROM KRIM_PERM_ATTR_DATA_T WHERE PERM_ID = ' || duplicateId || '';
    EXECUTE IMMEDIATE 'DELETE FROM KRIM_PERM_T WHERE PERM_ID = ' || duplicateId || '';
END;
/

DECLARE riceId NUMBER;
        duplicateId NUMBER;
BEGIN
    SELECT MIN(PERM_ID) into riceId FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Unrestricted Document Search';
    SELECT MAX(PERM_ID) into duplicateId FROM KRIM_PERM_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Unrestricted Document Search';
    EXECUTE IMMEDIATE 'UPDATE KRIM_ROLE_PERM_T SET PERM_ID = ' || riceId || ' WHERE PERM_ID = ' || duplicateId || '';
    EXECUTE IMMEDIATE 'DELETE FROM KRIM_PERM_ATTR_DATA_T WHERE PERM_ID = ' || duplicateId || '';
    EXECUTE IMMEDIATE 'DELETE FROM KRIM_PERM_T WHERE PERM_ID = ' || duplicateId || '';
END;
/
UPDATE KRIM_PERM_T SET NM = 'Initiate Simple Document' WHERE DESC_TXT = 'Authorizes the initiation of KC Simple Maintenance documents.'
/
UPDATE KRIM_PERM_T SET NM = 'Ad Hoc Review Document - Approve' WHERE DESC_TXT = 'Authorizes users to take the Approve action on KC documents Ad Hoc routed to them.'
/
UPDATE KRIM_PERM_T SET NM = 'Ad Hoc Review Document - FYI' WHERE DESC_TXT = 'Authorizes users to take the FYI action on KC documents Ad Hoc routed to them.'
/
UPDATE KRIM_PERM_T SET NM = 'Ad Hoc Review Document - Acknowledge' WHERE DESC_TXT = 'Authorizes users to take the Acknowledge action on KC documents Ad Hoc routed to them.'
/
