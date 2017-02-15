[#ftl]
=======================
===   ${message}  ====
=======================
[#list topNFile as topN]
* ${topN.name} ==> user ${topN.userInfo.userName}, ${topN.size} bytes
[/#list]