package com.example.testemployes1.screens.employees;


import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testemployes1.api.ApiFactory;
import com.example.testemployes1.api.ApiService;
import com.example.testemployes1.data.AppDatabase;
import com.example.testemployes1.pojo.EmployeResponse;
import com.example.testemployes1.pojo.Employee;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class EmployeeViewModel extends AndroidViewModel {

    private static AppDatabase db;
    private final LiveData<List<Employee>> employees;
    //создали класс MutableLiveData а не LiveData т.к он является абстрактным а его реализует MutableLiveData
    private final MutableLiveData <Throwable> error;

    private CompositeDisposable compositeDisposable;


    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        employees = db.employeeDao().getAllEmployees();
        error = new MutableLiveData<>();
    }

    public LiveData<List<Employee>> getEmployees() {
        return employees;
    }

    //ОСТАВЛЯЕМ родительский класс LiveData что бы нельзя было изменять из вне
    public LiveData<Throwable> getError() {
        return error;
    }

    public void clearErrors(){
        error.setValue(null);
    }

    @SuppressWarnings("unchecked")
    public void insertEmployees(List<Employee> employees) {
        new InsertEmployeesTask().execute(employees);
    }

    private static class InsertEmployeesTask extends AsyncTask<List<Employee>, Void, Void> {

        @Override
        protected final Void doInBackground(List<Employee>... lists) {
            if (lists != null && lists.length > 0) {
                db.employeeDao().insertEmployees(lists[0]);
            }
            return null;
        }
    }

    public void deleteAllEmployees() {
        new DeleteAllEmployeeTask().execute();
    }

    private static class DeleteAllEmployeeTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... lists) {
            db.employeeDao().deleteAllEmployees();
            return null;
        }
    }




    public void loadData() {
        //ApiFactory являяется синглтоном, созданным с помощью данного паттерна
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();

        //getEmploye() - возвращает тип Observable
        //если ннесколько объектов disposable то создаем compositeDisposable и методом add добавляем туда
        compositeDisposable = new CompositeDisposable();
        // что бы не было утечки памяти disposable
        //нужен для того что бы показать в каком потоке это делать, все скачивания из интернета, обращение к БД (здесь показано что получаем данные в другом потоке
        //указываем в каокм пототке мы принимаем данные, здесь показываем что принимаем в главном потоке
        //этот метод выполняется если данные были загружены успешно
        //сначала нужно удалить старые данные потом вставлять новые данные
        //это метод работает если загрузка прошла не успешно
        Disposable disposable = apiService.getEmployees()
                //нужен для того что бы показать в каком потоке это делать, все скачивания из интернета, обращение к БД (здесь показано что получаем данные в другом потоке
                .subscribeOn(Schedulers.io())
                //указываем в каокм пототке мы принимаем данные, здесь показываем что принимаем в главном потоке
                .observeOn(AndroidSchedulers.mainThread())
                //этот метод выполняется если данные были загружены успешно
                .subscribe(new Consumer<EmployeResponse>() {
                               @Override
                               public void accept(EmployeResponse employeResponse) throws Exception {
                                   //сначала нужно удалить старые данные потом вставлять новые данные
                                   deleteAllEmployees();
                                   insertEmployees(employeResponse.getEmploye());
                               }
                           },
                        //это метод работает если загрузка прошла не успешно
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                error.setValue(throwable);
                            }
                        });
        compositeDisposable.add(disposable);

    }
    @Override
    protected void onCleared() {
        //что бы при закрытии программы юзером освободить ресурсы и прекратить загрузку данных вызывается метод .dispose()
        compositeDisposable.dispose();
        super.onCleared();
    }
}
}
