package org.kuali.coeus.s2sgen.impl.print;

import org.kuali.coeus.sys.api.model.KcFile;

public class S2SFile implements KcFile {

    private String name;

    private String type;

    private byte[] data;

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
