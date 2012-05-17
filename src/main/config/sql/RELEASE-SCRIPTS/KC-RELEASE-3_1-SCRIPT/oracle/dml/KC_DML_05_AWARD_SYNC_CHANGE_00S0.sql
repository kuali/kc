INSERT INTO AWARD_SYNC_CHANGE (AWARD_SYNC_CHANGE_ID,AWARD_ID,CLASS_NAME,ATTRIBUTE_NAME,OBJECT_DESC,DATA_DESC,SYNC_TYPE,SYNC_DESCEND,SYNC_FABRICATED_DESCEND,SYNC_COST_SHARE_DESCEND,XML_CHANGES,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
    VALUES (SEQ_AWARD_SYNC_CHANGE_ID.NEXTVAL,(SELECT AWARD_ID FROM AWARD WHERE AWARD_NUMBER = '001002-00001' AND SEQUENCE_NUMBER = 2),'org.kuali.kra.award.home.Award','sponsorCode','Sponsor','000340 : NIH','A','ALL','Y','N',
            '<org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport>
                <className>org.kuali.kra.award.home.Award</className>
                <addIfNotFound>true</addIfNotFound>
                <keys/>
                <values>
                    <entry>
                        <string>sponsorCode</string>
                        <string>000340</string>
                    </entry>
                </values>
            </org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport>',SYSDATE,'quickstart',0,SYS_GUID())
/
INSERT INTO AWARD_SYNC_CHANGE (AWARD_SYNC_CHANGE_ID,AWARD_ID,CLASS_NAME,ATTRIBUTE_NAME,OBJECT_DESC,DATA_DESC,SYNC_TYPE,SYNC_DESCEND,SYNC_FABRICATED_DESCEND,SYNC_COST_SHARE_DESCEND,XML_CHANGES,UPDATE_TIMESTAMP,UPDATE_USER,VER_NBR,OBJ_ID) 
    VALUES (SEQ_AWARD_SYNC_CHANGE_ID.NEXTVAL,(SELECT AWARD_ID FROM AWARD WHERE AWARD_NUMBER = '001002-00001' AND SEQUENCE_NUMBER = 3),'org.kuali.kra.award.home.Award','sponsorCode','Sponsor','000500 : NSF','A','ALL','Y','N',
            '<org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport>
                <className>org.kuali.kra.award.home.Award</className>
                <addIfNotFound>true</addIfNotFound>
                <keys/>
                <values>
                    <entry>
                        <string>sponsorCode</string>
                        <string>000500</string>
                    </entry>
                </values>
            </org.kuali.kra.award.awardhierarchy.sync.AwardSyncXmlExport>',SYSDATE,'quickstart',0,SYS_GUID())
/
