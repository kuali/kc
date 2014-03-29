/**
 * Copyright 2005-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.kra.s2s.util;

public final class AuditError {

    private String errorKey;
    private String messageKey;
    private String link;

    public AuditError() {
    }

    public AuditError(String errorKey, String messageKey, String link) {
        this.errorKey = errorKey;
        this.messageKey = messageKey;
        this.link = link;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public void setErrorKey(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuditError that = (AuditError) o;

        if (errorKey != null ? !errorKey.equals(that.errorKey) : that.errorKey != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (messageKey != null ? !messageKey.equals(that.messageKey) : that.messageKey != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = errorKey != null ? errorKey.hashCode() : 0;
        result = 31 * result + (messageKey != null ? messageKey.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AuditError{" +
                "errorKey='" + errorKey + '\'' +
                ", messageKey='" + messageKey + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}

