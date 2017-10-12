package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Contains the details for the retrieved movie.
 * Created by Gary on 9/17/2017.
 */

class MovieData implements Parcelable{
    private String mOriginalTitle;
    private String mPosterImage;
    private String mPlotSynopsis;
    private double mUserRating = -1.0;
    private Date mReleaseDate;

    MovieData(){}

    private MovieData(Parcel in){
        mOriginalTitle = in.readString();
        mPosterImage = in.readString();
        mPlotSynopsis = in.readString();
        mUserRating = in.readDouble();
        mReleaseDate = new Date(in.readLong());
    }

    void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    String getOriginalTitle() {
        return mOriginalTitle;
    }

    String getPosterImage() {
        return mPosterImage;
    }

    void setPosterImage(String posterImage) {
        mPosterImage = posterImage;
    }

    String getPlotSynopsis() {
        return mPlotSynopsis;
    }

    void setPlotSynopsis(String plotSynopsis) {
        mPlotSynopsis = plotSynopsis;
    }

    double getUserRating() {
        return mUserRating;
    }

    void setUserRating(double userRating) {
        mUserRating = userRating;
    }

    Date getReleaseDate() {
        return mReleaseDate;
    }

    void setReleaseDate(Date releaseDate) {
        mReleaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mOriginalTitle);
        parcel.writeString(mPosterImage);
        parcel.writeString(mPlotSynopsis);
        parcel.writeDouble(mUserRating);
        parcel.writeLong(mReleaseDate.getTime());
    }
    public static final Parcelable.Creator<MovieData> CREATOR = new Parcelable.Creator<MovieData>() {
        @Override
        public MovieData createFromParcel(Parcel source) {
            return new MovieData(source);
        }

        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };

}
