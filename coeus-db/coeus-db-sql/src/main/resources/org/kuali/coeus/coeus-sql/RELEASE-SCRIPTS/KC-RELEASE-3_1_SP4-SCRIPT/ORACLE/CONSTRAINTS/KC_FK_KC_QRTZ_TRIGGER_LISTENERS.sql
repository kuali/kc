ALTER TABLE KC_QRTZ_TRIGGER_LISTENERS
ADD CONSTRAINT FK_KC_QRTZ_TRIGGER_LISTENERS FOREIGN KEY (TRIGGER_NAME,TRIGGER_GROUP) 
    REFERENCES KC_QRTZ_TRIGGERS(TRIGGER_NAME,TRIGGER_GROUP);