package org.kuali.coeus.coi.framework;

import java.util.Map;

public interface ProjectMetadata {

    Map<String, String> getMetadata();
    void setMetadata(Map<String, String> metadata);
}
