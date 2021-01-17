package com.sec.demo.Security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.sec.demo.Security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    //the student has no permissions
    STUDENT(Sets.newHashSet()),

    //admins are able to read and write for students and courses
    ADMIN(Sets.newHashSet(
            COURSE_READ,
            COURSE_WRITE,
            STUDENT_READ,
            STUDENT_WRITE
    )),


    //admin trainees have all admin permissions apart from writing permissions
    ADMIN_TRAINEE(Sets.newHashSet(
            STUDENT_READ,
            COURSE_READ
    ));


    private final Set<ApplicationUserPermission> permission;

    ApplicationUserRole(Set<ApplicationUserPermission> permission){
        this.permission = permission;
    }

    public Set<ApplicationUserPermission> getPermission() {
        return permission;
    }
}
