package com.example.try_recyclerview;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ExampleItem implements Parcelable {
    private String mText1;
    private String mText2;
    private List<ExampleItem> anExampleItem;
//    Uri pathImage;

    public ExampleItem(String mText1, String mText2) {
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.anExampleItem = new ArrayList<>();
//        this.pathImage = pathImage;
    }

    public ExampleItem() {

    }

    public void addItem(ExampleItem exampleitem)
    {
        mText1 = "ADUH";
        mText2 = "MATANE";
        anExampleItem.add(exampleitem);
    }


    protected ExampleItem(Parcel in) {
//        this.pathImage = in.readParcelable(Uri.class.getClassLoader());
        mText1 = in.readString();
        mText2 = in.readString();
    }

    public static final Creator<ExampleItem> CREATOR = new Creator<ExampleItem>() {
        @Override
        public ExampleItem createFromParcel(Parcel in) {
            return new ExampleItem(in);
        }

        @Override
        public ExampleItem[] newArray(int size) {
            return new ExampleItem[size];
        }
    };


    //SETTER GETTER

//    public Uri getPathImage() {
//        return pathImage;
//    }

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeParcelable(this.pathImage, flags);
        dest.writeString(mText1);
        dest.writeString(mText2);
    }
}
