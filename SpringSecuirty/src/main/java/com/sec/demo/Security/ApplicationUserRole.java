package com.sec.demo.Security;

import com.google.common.collect.Sets;

import java.util.Set;

public enum ApplicationUserRole {


    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(ApplicationUserPermission.COURSE_READ,
            ApplicationUserPermission.COURSE_WRITE,
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.STUDENT_WRITE)),

    ADMIN_TRAINEE(Sets.newHashSet(
            ApplicationUserPermission.STUDENT_READ,
            ApplicationUserPermission.COURSE_READ));


    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(Set<ApplicationUserPermission> permission){
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }
}
