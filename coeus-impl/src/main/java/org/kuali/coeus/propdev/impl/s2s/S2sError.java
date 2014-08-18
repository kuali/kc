package org.kuali.coeus.propdev.impl.s2s;

import org.kuali.coeus.propdev.api.s2s.S2sErrorContract;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;
import org.kuali.rice.krad.data.jpa.PortableSequenceGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "S2S_ERROR")
public class S2sError extends KcPersistableBusinessObjectBase implements S2sErrorContract {

    @PortableSequenceGenerator(name = "SEQ_S2S_ERROR_ID")
    @GeneratedValue(generator = "SEQ_S2S_ERROR_ID")
    @Id
    @Column(name = "S2S_ERROR_ID")
    private String id;

    @Column(name = "MESSAGE_KEY")
    private String key;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "FIX_LINK")
    private String link;

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
