set sqlterminator {
set define off
INSERT INTO KREN_NTFCTN_T (CHNL_ID,CNTNT,CNTNT_TYP_ID,CRTE_DTTM,DELIV_TYP,NTFCTN_ID,PRIO_ID,PROCESSING_FLAG,PRODCR_ID,SND_DTTM,TTL,VER_NBR)
  VALUES (1000,'<content xmlns="ns:notification/ContentTypeSimple" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="ns:notification/ContentTypeSimple resource:notification/ContentTypeSimple">
        <message>&lt;?xml version="1.0" encoding="UTF-8"?&gt;&#13;
&#13;
		The IRB protocol number&#13;
		&lt;a href="../kew/DocHandler.do?command=displayDocSearchView&amp;amp;docId=3096" target="_self" title=""&gt;1009000019&lt;/a&gt;&#13;
&#13;
		, Principal Investigator&#13;
		IRB Researcher&#13;
		has had the action "Withdrawn" performed on it.&#13;
		&lt;br/&gt;&#13;
		The action was executed by&#13;
		Geoff McGregor&#13;
		. Additional information and further actions can be accessed through&#13;
		the Kuali Coeus system.&#13;
&#13;
	</message>
    </content>
',1,TO_DATE( '20100914141956', 'YYYYMMDDHH24MISS' ),'FYI',1,1,'RESOLVED',1,TO_DATE( '20100914141955', 'YYYYMMDDHH24MISS' ),'Protocol 1009000019 Withdrawn',4)
{
set sqlterminator ;