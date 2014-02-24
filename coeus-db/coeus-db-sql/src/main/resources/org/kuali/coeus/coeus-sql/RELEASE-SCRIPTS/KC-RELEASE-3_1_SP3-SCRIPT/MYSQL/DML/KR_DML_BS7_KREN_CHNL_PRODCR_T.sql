drop procedure if exists p;

delimiter //
create procedure p ()

BEGIN
DECLARE num INT;
SELECT COUNT(*) INTO num FROM KREN_CHNL_PRODCR_T WHERE CHNL_ID = (SELECT CHNL_ID FROM KREN_CHNL_T WHERE NM = 'KC Notification Channel') AND PRODCR_ID = (SELECT PRODCR_ID FROM KREN_PRODCR_T WHERE NM = 'Notification System');
  IF (num = 0)
  THEN
    INSERT INTO KREN_CHNL_PRODCR_T (CHNL_ID, PRODCR_ID)
      VALUES ((SELECT CHNL_ID FROM KREN_CHNL_T WHERE NM = 'KC Notification Channel'), (SELECT PRODCR_ID FROM KREN_PRODCR_T WHERE NM = 'Notification System'));
  END IF;
END //

delimiter ;

call p ();

drop procedure if exists p;