package com.example.sonalsb.samplemvvmproj.viewmodel;


import android.content.Context;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.view.View;

import com.example.sonalsb.samplemvvmproj.PeopleApplication;
import com.example.sonalsb.samplemvvmproj.R;
import com.example.sonalsb.samplemvvmproj.data.PeopleFactory;
import com.example.sonalsb.samplemvvmproj.data.PeopleResponse;
import com.example.sonalsb.samplemvvmproj.data.PeopleService;
import com.example.sonalsb.samplemvvmproj.model.People;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PeopleViewModel extends Observable {


    private List<People> peopleList;
    private Context context;

    public ObservableInt peopleProgress;
    public ObservableInt peopleRecycler;
    public ObservableInt peopleLable;

    public ObservableField<String> messageLable;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public PeopleViewModel(Context context) {
        this.context = context;
        this.peopleList = new ArrayList<>();

        peopleProgress = new ObservableInt(View.GONE);
        peopleRecycler = new ObservableInt(View.GONE);
        peopleLable = new ObservableInt(View.VISIBLE);
        messageLable = new ObservableField<>(context.getString(R.string.default_loading_people));
    }

    public void onClickFabLoad(View view) {
        initializeViews();
        fetchPeopleList();
    }

    private void fetchPeopleList() {
        PeopleApplication peopleApplication = PeopleApplication.create(context);
        PeopleService peopleService = peopleApplication.getPeopleService();

        Disposable disposable = peopleService.fetchPeople(PeopleFactory.RANDOM_USER_URL)
                .subscribeOn(peopleApplication.subscribeScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PeopleResponse>() {
                    @Override
                    public void accept(PeopleResponse peopleResponse) throws Exception {
                        changePeopleDataSet(peopleResponse.getPeopleList());
                        peopleProgress.set(View.GONE);
                        peopleLable.set(View.GONE);
                        peopleRecycler.set(View.VISIBLE);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        messageLable.set(context.getString(R.string.error_loading_people));
                        peopleProgress.set(View.GONE);
                        peopleLable.set(View.VISIBLE);
                        peopleRecycler.set(View.GONE);
                    }
                });
    }

    private void changePeopleDataSet(List<People> peoples) {
        peopleList.addAll(peoples);
        setChanged();
        notifyObservers();
    }


    public List<People> getPeopleList() {
        return peopleList;
    }

    private void initializeViews() {
    }
}
