package com.example.testemployes1.api;

import com.example.testemployes1.pojo.EmployeResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

     //в даном интерфейсе указываем запросы на сайт
public interface ApiService {
    //помечаем анннотацие что возвращаем первое для загрузки объект JSON, с помощью Observable мы можем слушать что происходит с нашим запросом
    //в скобках указываем end point, в запросе который указывается после слэша -> testTask.json
    //полный запрос http://gitlab.65apps.com/65gb/static/raw/master/testTask.json
    //хранятся все методы по работе с сетью
    @GET("testTask.json")
    Observable<EmployeResponse> getEmployees();

}
