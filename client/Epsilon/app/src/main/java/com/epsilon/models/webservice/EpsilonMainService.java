package com.epsilon.models.webservice;

import com.epsilon.models.entities.Course;
import com.epsilon.models.webservice.json.AllCategoriesResultJSON;
import com.epsilon.models.webservice.json.CourseResultJSON;
import com.epsilon.models.webservice.json.CoursesOfCategoryResultJSON;
import com.epsilon.models.webservice.json.LoginRequestJSON;
import com.epsilon.models.webservice.json.LoginResultJSON;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by Dandoh on 4/9/16.
 */
public interface EpsilonMainService {

    @GET("/categories")
    Call<AllCategoriesResultJSON> getAllCategories();

    @GET("/categories/{id}/all")
    Call<CoursesOfCategoryResultJSON> getCoursesOfCategory(@Path("id") int id);

    @GET("/courses/{id}")
    Call<CourseResultJSON> getCourseById(@Path("id") int id);


}
