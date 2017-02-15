[#ftl]
=======================
===  ${message}  ====
=======================
[#list usersAudit as key,value]
## User: ${value.userName}
    [#list value.files as file]
    * ${file.name} ==> ${file.size} bytes
    [/#list]
[/#list]
