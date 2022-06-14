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

public class EmployeeListPresenter {

    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    private EmployeesListView view;

    public EmployeeListPresenter(EmployeesListView view) {
        this.view = view;
    }


    public void loadData(){
    //ApiFactory являяется синглтоном, созданным с помощью данного паттерна
    ApiFactory apiFactory = ApiFactory.getInstance();
    ApiService apiService = apiFactory.getApiService();

    //getEmploye() - возвращает тип Observable
    //если ннесколько объектов disposable то создаем compositeDisposable и методом add добавляем туда
    compositeDisposable = new CompositeDisposable();
    // что бы не было утечки памяти disposable
    disposable = apiService.getEmployees()
            //нужен для того что бы показать в каком потоке это делать, все скачивания из интернета, обращение к БД (здесь показано что получаем данные в другом потоке
            .subscribeOn(Schedulers.io())
            //указываем в каокм пототке мы принимаем данные, здесь показываем что принимаем в главном потоке
            .observeOn(AndroidSchedulers.mainThread())
            //этот метод выполняется если данные были загружены успешно
            .subscribe(new Consumer<EmployeResponse>() {
        @Override
        public void accept(EmployeResponse employeResponse) throws Exception {
            view.showData(employeResponse.getEmploye());
        }
    }, //это метод работает если загрузка прошла не успешно
            new Consumer<Throwable>() {
        @Override
        public void accept(Throwable throwable) throws Exception {
            view.showError();
        }
    });
        compositeDisposable.add(disposable);

}

    //что бы при закрытии программы юзером освободить ресурсы и прекратить загрузку данных вызывается метод .dispose()
    public void disposableaDispose() {
        if (compositeDisposable != null){
            compositeDisposable.dispose();
        }
    }
}
