package com.example.testemployes1.screens.employees;

import android.widget.Toast;

import com.example.testemployes1.api.ApiFactory;
import com.example.testemployes1.api.ApiService;
import com.example.testemployes1.pojo.EmployeResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MVP {

    /*private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    //ApiFactory являяется синглтоном, созданным с помощью данного паттерна
    ApiFactory apiFactory = ApiFactory.getInstance();
    ApiService apiService = apiFactory.getApiService();

    //getEmploye() - возвращает тип Observable
    //если ннесколько объектов disposable то создаем compositeDisposable и методом add добавляем туда
    compositeDisposable = new CompositeDisposable();
    // что бы не было утечки памяти disposable
    disposable = apiService.getEmploye()
            //нужен для того что бы показать в каком потоке это делать, все скачивания из интернета, обращение к БД (здесь показано что получаем данные в другом потоке
            .subscribeOn(Schedulers.io())
            //указываем в каокм пототке мы принимаем данные, здесь показываем что принимаем в главном потоке
            .observeOn(AndroidSchedulers.mainThread())
            //этот метод выполняется если данные были загружены успешно
            .subscribe(new Consumer<EmployeResponse>() {
        @Override
        public void accept(EmployeResponse employeResponse) throws Exception {
            adapter.setEmployees(employeResponse.getResponse());
        }
    }, //это метод работает если загрузка прошла не успешно
            new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            Toast.makeText(EmployeeListActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
        compositeDisposable.add(disposable);

}
    @Override
    //что бы при закрытии программы юзером освободить ресурсы и прекратить загрузку данных вызывается метод .dispose()
    protected void onDestroy() {
        super.onDestroy();
        if (compositeDisposable != null){
            compositeDisposable.dispose();
        };
    }*/
}
