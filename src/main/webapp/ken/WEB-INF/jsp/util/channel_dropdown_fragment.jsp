<%@ include file="../Include.jsp"%>

<select name="channelName">
    <option value="___NONE___">None</option>
    <c:forEach var="channel" items="${channels}">
        <c:choose>
            <c:when test="${f:length(channel.subscriptions) == 1}">
                <c:set var="subscribers" value="1 subscriber"/>
            </c:when>
            <c:otherwise>
                <c:set var="subscribers" value="${f:length(channel.subscriptions)} subscribers"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${f:length(channel.recipientLists) == 1}">
                <c:set var="dflt_recipients" value="1 default recipient"/>
            </c:when>
            <c:otherwise>
                <c:set var="dflt_recipients" value="${f:length(channel.recipientLists)} default recipients"/>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${channel.name eq channelName}">
                <option value="${channel.name}" selected="true">${channel.name} (${subscribers}, ${dflt_recipients})</option>
            </c:when>
            <c:otherwise>
                <option value="${channel.name}">${channel.name} (${subscribers}, ${dflt_recipients})</option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</select>