drop procedure if exists p;

delimiter //
create procedure p ()

BEGIN
DECLARE num INT;  
SELECT COUNT(*) INTO num FROM KRIM_AFLTN_TYP_T WHERE AFLTN_TYP_CD = 'GRD_STDNT_STAFF';
  IF (num = 0)
  THEN
    INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
      VALUES ('Y','GRD_STDNT_STAFF','e','Y',NOW(),'Graduate Student Staff',UUID(),1);
  END IF;
END //

delimiter ;

call p ();

drop procedure if exists p;

delimiter //
create procedure p ()

BEGIN
DECLARE num INT;  
  SELECT COUNT(*) INTO num FROM KRIM_AFLTN_TYP_T WHERE AFLTN_TYP_CD = 'RSRCH_STAFF';
  IF (num = 0)
  THEN
    INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
      VALUES ('Y','RSRCH_STAFF','f','Y',NOW(),'Research Staff',UUID(),1);
  END IF;
END //

delimiter ;

call p ();

drop procedure if exists p;

delimiter //
create procedure p ()

BEGIN
DECLARE num INT;  
  SELECT COUNT(*) INTO num FROM KRIM_AFLTN_TYP_T WHERE AFLTN_TYP_CD = 'SRVC_STAFF';
  IF (num = 0)
  THEN
    INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
      VALUES ('Y','SRVC_STAFF','g','Y',NOW(),'Service Staff',UUID(),1);
  END IF;
END //

delimiter ;

call p ();

drop procedure if exists p;

delimiter //
create procedure p ()

BEGIN
DECLARE num INT;  
  SELECT COUNT(*) INTO num FROM KRIM_AFLTN_TYP_T WHERE AFLTN_TYP_CD = 'SUPPRT_STAFF';
  IF (num = 0)
  THEN
    INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
      VALUES ('Y','SUPPRT_STAFF','h','Y',NOW(),'Support Staff',UUID(),1);
  END IF;
END //

delimiter ;

call p ();

drop procedure if exists p;

delimiter //
create procedure p ()

BEGIN
DECLARE num INT;  
  SELECT COUNT(*) INTO num FROM KRIM_AFLTN_TYP_T WHERE AFLTN_TYP_CD = 'OTH_ACADMC_GRP';
  IF (num = 0)
  THEN
    INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
      VALUES ('Y','OTH_ACADMC_GRP','i','N',NOW(),'Other Academic Group',UUID(),1);
  END IF;
END //

delimiter ;

call p ();

drop procedure if exists p;

delimiter //
create procedure p ()

BEGIN
DECLARE num INT;  
  SELECT COUNT(*) INTO num FROM KRIM_AFLTN_TYP_T WHERE AFLTN_TYP_CD = 'MED_STAFF';
  IF (num = 0)
  THEN
    INSERT INTO KRIM_AFLTN_TYP_T (ACTV_IND,AFLTN_TYP_CD,DISPLAY_SORT_CD,EMP_AFLTN_TYP_IND,LAST_UPDT_DT,NM,OBJ_ID,VER_NBR)
      VALUES ('Y','MED_STAFF','j','Y',NOW(),'Medical Staff',UUID(),1);
  END IF;
END //

delimiter ;

call p ();

drop procedure if exists p;