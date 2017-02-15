[#ftl]
[#list usersAudit as key,value]
    [#list value.files as file]
    ${value.userName},${file.name},${file.size}
    [/#list]
[/#list]