package lv.jug.javaday.androidapp.domain;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "events")
public class Event implements Parcelable {

    public static final String ID_ID = "id";
    public static final String ROOM_ID_ID = "roomId";


    @DatabaseField(generatedId = true, allowGeneratedIdInsert=true)
    private int id;

    @DatabaseField
    private int roomId;

    @DatabaseField
    private String startingTime;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private String speakerId;

    @DatabaseField
    private String icon;

    public Event() {}

    public Event(int roomId, String startingTime, String title, String description, String speakerId, String icon) {
        this.roomId = roomId;
        this.startingTime = startingTime;
        this.title = title;
        this.description = description;
        this.speakerId = speakerId;
        this.icon = icon;
    }

    private Event(Parcel in) {
        roomId = in.readInt();
        startingTime = in.readString();
        title = in.readString();
        speakerId = in.readString();
        icon = in.readString();
        description = in.readString();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(roomId);
        out.writeString(startingTime);
        out.writeString(title);
        out.writeString(speakerId);
        out.writeString(icon);
        out.writeString(description);
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
