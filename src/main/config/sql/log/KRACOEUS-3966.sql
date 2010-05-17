insert into KRNS_PARM_T 
	(APPL_NMSPC_CD, NMSPC_CD, PARM_DTL_TYP_CD, PARM_NM, OBJ_ID, VER_NBR, PARM_TYP_CD, TXT, PARM_DESC_TXT, CONS_CD) 
	values
    ('KUALI', 'KC-PD', 'D', 's2sschedulercronExpressionstarttime', '22f44dbf-23b6-4aa9-9d72-f83a227eeedd', 1, 'CONFG', '01-JAN-2010 01:00 AM', 'Start Time expression for the S2S Polling Process. The S2S Polling Process will only start if this parameters date is before today. Must be formatted as "dd-MMM-yyyy hh:mm a". For example "01-JAN-2010 01:00 AM".', 'A');
commit;