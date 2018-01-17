package com.example.sonalsb.samplemvvmproj.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sonalsb.samplemvvmproj.R;
import com.example.sonalsb.samplemvvmproj.databinding.PeopleActivityBinding;
import com.example.sonalsb.samplemvvmproj.viewmodel.PeopleViewModel;

import java.util.Observable;
import java.util.Observer;


public class PeopleActivity extends AppCompatActivity implements Observer {

    private PeopleActivityBinding peopleActivityBinding;
    private PeopleViewModel peopleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.people_activity);
        initDataBinding();
        //setSupportActionBar(peopleActivityBinding.toolbar);
        setupListPeopleView(peopleActivityBinding.listPeople);
        setupObserver(peopleViewModel);
    }

    private void initDataBinding() {
        peopleActivityBinding = DataBindingUtil.setContentView(this, R.layout.people_activity);
        peopleViewModel = new PeopleViewModel(this);
        peopleActivityBinding.setMainViewModel(peopleViewModel);
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {

        PeopleAdapter peopleAdapter = null;

        if (observable instanceof PeopleViewModel) {
            if (peopleAdapter == null) {
                peopleAdapter = (PeopleAdapter) peopleActivityBinding.listPeople.getAdapter();
                PeopleViewModel peopleViewModel = (PeopleViewModel) observable;
                peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
            }else{
                PeopleViewModel peopleViewModel = (PeopleViewModel) observable;
                peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
            }
        }

    }
}
