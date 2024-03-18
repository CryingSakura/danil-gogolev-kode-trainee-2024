package com.example.stafflist.data

fun ChangeDepartmentStringView(staffs: List<Employee>): List<Employee> {

    staffs.forEach {
        when(it.department){
            "android" -> it.department = "Android"
            "ios" -> it.department = "iOS"
            "design" -> it.department = "Designers"
            "management" -> it.department = "Managers"
            "qa" -> it.department = "QA"
            "back_office" -> it.department = "Back-Office"
            "frontend" -> it.department = "Frontend"
            "hr" -> it.department = "HR"
            "pr" -> it.department = "PR"
            "backend" -> it.department = "Backend"
            "support" -> it.department = "Support"
            "analytics" -> it.department = "Analysts"
        }
    }



    return staffs
}


