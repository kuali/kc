delimiter /

drop procedure if exists roleChange
/

create procedure roleChange ()

BEGIN
DECLARE roleCount INT DEFAULT 0;
DECLARE rolePermCount INT DEFAULT 0;

SELECT COUNT(*) INTO roleCount FROM information_schema.tables WHERE table_name = 'KRIM_ROLE_T' and table_schema = (SELECT DATABASE());
SELECT COUNT(*) INTO rolePermCount FROM information_schema.tables WHERE table_name = 'KRIM_ROLE_T' and table_schema = (SELECT DATABASE());

IF (roleCount > 0 && rolePermCount > 0)
  THEN
    update KRIM_ROLE_T set ROLE_ID = 'KR1001' where ROLE_NM = 'Complete Request Recipient' and NMSPC_CD = 'KR-WKFLW';
    UPDATE KRIM_ROLE_PERM_T SET ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient') where ROLE_PERM_ID = 'KR1000';
    UPDATE KRIM_ROLE_PERM_T SET ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient') where ROLE_PERM_ID = 'KR1001';
  END IF;
END //

call roleChange ()
/
drop procedure if exists roleChange
/
delimiter ;
