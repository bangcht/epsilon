package com.epsilon.screens.coursedetail;

import com.epsilon.commons.GenericViewInterface;
import com.epsilon.models.entities.Course;

import java.util.List;

/**
 * Created by AnhVu on 4/9/16.
 */
public interface CourseDetailContract {

    interface View extends GenericViewInterface {
        void displayCourseDetail(Course course);
        void goToCourseUrl(String url);
        void onError(String error);
        void displayRecommendedCourse(List<Course> courses);
        void goToCourseDetail(int id);
    }

    interface UserActionListener {
        void getCourseDetailById(int id);
        void enrollCourse();
        void goToCourse();
        void getRecommendedCourse(int courseId);
        void viewCourseDetail(int position, Course course);
    }
}
