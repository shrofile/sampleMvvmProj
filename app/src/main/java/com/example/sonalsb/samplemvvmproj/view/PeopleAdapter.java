package com.example.sonalsb.samplemvvmproj.view;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.sonalsb.samplemvvmproj.R;
import com.example.sonalsb.samplemvvmproj.databinding.ItemPeopleBinding;
import com.example.sonalsb.samplemvvmproj.model.People;
import com.example.sonalsb.samplemvvmproj.viewmodel.ItemPeopleViewModel;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> {

    private List<People> peopleList;


    public PeopleAdapter() {
        this.peopleList = Collections.emptyList();
    }

    @Override
    public PeopleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemPeopleBinding itemPeopleBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_people
                , parent, false);


        return new PeopleAdapterViewHolder(itemPeopleBinding);
    }

    public void setPeopleList(List<People> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(PeopleAdapterViewHolder holder, int position) {
        holder.bindPeople(peopleList.get(position));
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
        ItemPeopleBinding mItemPeopleBinding;


        public PeopleAdapterViewHolder(ItemPeopleBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemPeople);
            this.mItemPeopleBinding = itemPeopleBinding;
        }


        public void bindPeople(People people) {
            if (mItemPeopleBinding.getPeopleViewModel() == null) {
                mItemPeopleBinding.setPeopleViewModel(new ItemPeopleViewModel(people, itemView.getContext()));
            }
        }
    }
}
