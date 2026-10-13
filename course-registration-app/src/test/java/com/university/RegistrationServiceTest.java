package com.university;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationServiceTest {

    @Test
    void shouldRegisterStudentForValidCourse() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertTrue(result);
        assertTrue(student.isRegistered("CS301"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectInactiveCourse() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                false,
                null
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS301"));
    }

    @Test
    void shouldRejectMissingPrerequisite() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS401"));
    }

    @Test
    void shouldAllowRegistrationWhenPrerequisiteIsCompleted() {

        Student student = new Student("S101", "Anita");
        student.addCompletedPrerequisite("CS301");

        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertTrue(result);
        assertTrue(student.isRegistered("CS401"));
    }

    @Test
    void shouldRejectDuplicateRegistration() {

        Student student = new Student("S101", "Anita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        assertTrue(service.register(student, course));

        boolean secondAttempt = service.register(student, course);

        assertFalse(secondAttempt);
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
    }

    @Test
    void shouldRejectRegistrationWhenCreditLimitIsExceeded() {

        Student student = new Student("S101", "Anita");

        // Simulate 20 existing credits
        student.addRegistration("CS101", 4);
        student.addRegistration("CS102", 4);
        student.addRegistration("CS201", 4);
        student.addRegistration("CS202", 4);
        student.addRegistration("CS203", 4);

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                30,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS301"));
    }

    @Test 
    void shouldRejectRegistrationWhenCourseLimitExceeded(){

        Student student1 = new Student("S101", "Anita");
        Student student2 = new Student("S102", "Sunita");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                1,
                true,
                null
        );

        RegistrationService service = new RegistrationService();
        assertTrue(service.register(student1, course));
        assertEquals(1, course.getCurrentEnrollment());

        boolean result = service.register(student2, course);
        assertFalse(result);
        assertFalse(student2.isRegistered("CS301"));
        assertEquals(0, student2.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());

        
    }

    @Test
    void shouldAllowRegistrationWhenExactlyOneSeatRemains() {

        Student student1 = new Student("S101", "Anita");
        Student student2 = new Student("S102", "Rahul");

        Course course = new Course(
                "CS301",
                "Data Structures",
                4,
                2,
                true,
                null
        );

        RegistrationService service = new RegistrationService();

        assertTrue(service.register(student1, course));
        assertEquals(1, course.getCurrentEnrollment());

        boolean lastSeatResult = service.register(student2, course);

        assertTrue(lastSeatResult);
        assertTrue(student2.isRegistered("CS301"));
        assertEquals(4, student2.getTotalCredits());
        assertEquals(2, course.getCurrentEnrollment());
    } 
    
    @Test
    void shouldAllowRegistrationWhenCS3A1isCompleted(){
        Student student = new Student("S103", "Anita");
        student.addCompletedPrerequisite("CS3A1");
        
        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertTrue(result);
        assertTrue(student.isRegistered("CS401"));
        assertEquals(4, student.getTotalCredits());
        assertEquals(1, course.getCurrentEnrollment());
 
    }

     @Test
    void shouldRejectRegistrationWhenNeitherCS301NorCS3A1IsCompletedWithoutMutatingState() {

        Student student = new Student("S104", "Kiran");
        student.addCompletedPrerequisite("CS201"); // Unrelated course

        Course course = new Course(
                "CS401",
                "Advanced Algorithms",
                4,
                30,
                true,
                "CS301"
        );

        RegistrationService service = new RegistrationService();

        boolean result = service.register(student, course);

        assertFalse(result);
        assertFalse(student.isRegistered("CS401"));
        assertEquals(0, student.getTotalCredits());
        assertEquals(0, course.getCurrentEnrollment());
    }

}