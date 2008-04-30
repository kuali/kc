
  CREATE TABLE "APPOINTMENT_TYPE" 
   ("APPOINTMENT_TYPE_CODE" VARCHAR2(3) CONSTRAINT "APPOINTMENT_TYPE_N1" NOT NULL, 
	"DESCRIPTION" VARCHAR2(200) CONSTRAINT "APPOINTMENT_TYPE_N2" NOT NULL, 
	"DURATION" NUMBER(2,0) NOT NULL, 
	"UPDATE_TIMESTAMP" DATE CONSTRAINT "APPOINTMENT_TYPE_N4" NOT NULL, 
	"UPDATE_USER" VARCHAR2(60) CONSTRAINT "APPOINTMENT_TYPE_N5" NOT NULL, 
	"VER_NBR" NUMBER(8,0) DEFAULT 1 CONSTRAINT "APPOINTMENT_TYPE_N6" NOT NULL, 
	"OBJ_ID" VARCHAR2(36 BYTE) DEFAULT SYS_GUID() CONSTRAINT "APPOINTMENT_TYPE_N7" NOT NULL, 
	 CONSTRAINT "APPOINTMENT_TYPE_P1" PRIMARY KEY ("APPOINTMENT_TYPE_CODE")) ;
 