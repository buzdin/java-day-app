package lv.jug.javaday.androidapp.domain;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "speakers")
public class Speaker implements Parcelable {

    @DatabaseField(id = true)
    private String name;

    @DatabaseField
    private String company;

    private String photo;

    private String info;

    public Speaker(String name, String company, String photo, String info) {
        this.name = name;
        this.company = company;
        this.photo = photo;
        this.info = info;
    }

    private Speaker(Parcel in) {
        name = in.readString();
        company = in.readString();
        photo = in.readString();
        info = in.readString();
    }

    public Speaker() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(name);
        out.writeString(company);
        out.writeString(photo);
        out.writeString(info);
    }

    public static final Parcelable.Creator<Speaker> CREATOR = new Parcelable.Creator<Speaker>() {
        public Speaker createFromParcel(Parcel in) {
            return new Speaker(in);
        }

        public Speaker[] newArray(int size) {
            return new Speaker[size];
        }
    };
}
