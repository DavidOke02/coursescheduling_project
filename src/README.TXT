Use Cases Covered:
-Authentication {LoginController, Login, LoginUI}
    -Refactored to be more seamless
    -This will now take you to the respective dashboard of either student, admin, or advisor depending on what login credentials you use
    -The dashboard lists all the action the user can take

-Admin adding course to db  {Course, AddCourseUI, AdminDashboard, AdminDashboardController, AddCourseController, CourseListView, CourseListViewController}
    -Refactored to have less bloat and work better.
    -From dashboard can either add a course or view list of courses in the db.
        -In list view, selecting a course and pressing View Course will bring a detail and modification screen.
        -Department filter at the top is fully functional.
    -A professor must be added to the db to being able to assign to a course (One is added in the included db)
    -Prerequisite must be a course that exists in the course table

-Making and managing Advisor Approval requests
    -From dashboard can view a list of approval requests by student and either accept or deny them, as well as leave a comment
       -The requests are made by students to look at their schedules, which can be made from their dashboard
       -Students can make override requests for registering for courses
       -Students can see their decisions from the dashboard once an advisor handles any of their requests

-Registering a course to Student Schedule
    -Student can view their schedule from the dashboard.
    -The table headers won't show but they are course id -> registration status -> waitlist status
    -The table shows all their courses they either have in their cart or are enrolled in
       -Selecting a course and pressing view course will show more details about the course.
       -Adding a course will bring up a search view where the student can search for any course in the db
       -Selecting a course and pressing the add button will the student's schedule enrollment cart
       -Selecting a course in the enrollment cart and pressing the enroll in course button will add it to the enrollment table instead of the cart

*Main is where to start the cases (from login) and DB setup is in db folder*


Input Login for Authentication:
    User      Role      Password
   ------------------------------
  	ADM123	| Admin	  | password
  	ADV123	| Advisor |	password
  	STU123  | Student |	password

**Contributions can be found in the GitLog**