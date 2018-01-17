package com.example.sonalsb.samplemvvmproj.viewmodel;


import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.sonalsb.samplemvvmproj.model.People;

public class ItemPeopleViewModel extends BaseObservable {

    private People people;
    private Context context;

    public ItemPeopleViewModel(People people, Context context) {
        this.people = people;
        this.context = context;
    }

    public String getFullName() {
        people.fullName = people.name.title + " . " + people.name.title + " . "
                + " . " + people.name.last;

        return people.fullName;
    }


    public String getCell() {
        return people.cell;
    }

    public String getMail() {
        return people.mail;
    }

    public String getProfilePicture() {
        return people.picture.medium;
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    public void onItemClick(View view) {
        // context.startActivity(PeopleDe);
    }

    public void setPeople(People people) {
        this.people = people;
        notifyChange();
    }
}
