<#import "parts/common.ftlh" as c >
<#import "parts/login.ftlh" as l >
<@c.page>

    <@l.logout/>
    <span><a href = "/user">User list</a></span>

    <div>
        <form method="POST" action="/main">
            <input type="text" name="text" placeholder="input text"/>
            <input type="text" name="tag" placeholder="input tag"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Add</button>
        </form>
    </div>

    <div>Message List</div>

    <form method="GET" action="/main">
        <input type="text" name="filter" value="${filter}" />
        <button type="submit">Find</button>
    </form>

    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <strong>${message.authorName}</strong>
        </div>
    <#else>
        No messages
    </#list>

</@c.page>