drop procedure if exists p;

delimiter //
create procedure p ()

BEGIN
DECLARE num INT;
SELECT COUNT(*) INTO num FROM KREN_PRODCR_T;
  IF (num = 0)
  THEN
    INSERT INTO KREN_PRODCR_T (CNTCT_INFO,DESC_TXT,NM,PRODCR_ID,VER_NBR)
      VALUES ('admins-notsys@cornell.edu','This producer represents messages sent from the general message sending forms.','Notification System',1,1);
  END IF;
END //

delimiter ;

call p ();

drop procedure if exists p;