package lv.jug.javaday.androidapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {

    private int roomId;

    private String startingTime;

    private String details;

    private String speakerId;

    private String icon;

    public Event() {}

    private Event(Parcel in) {
        roomId = in.readInt();
        startingTime = in.readString();
        details = in.readString();
        speakerId = in.readString();
        icon = in.readString();
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(String startingTime) {
        this.startingTime = startingTime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(String speakerId) {
        this.speakerId = speakerId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(roomId);
        out.writeString(startingTime);
        out.writeString(details);
        out.writeString(speakerId);
        out.writeString(icon);
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
