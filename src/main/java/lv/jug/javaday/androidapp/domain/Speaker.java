package lv.jug.javaday.androidapp.domain;

import android.os.Parcel;
import android.os.Parcelable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "speakers")
public class Speaker implements Parcelable {

    public static final String SIMON_RITTER = "simon_ritter";
    public static final String CEDRIC_CHAMPEAU = "cedric";
    public static final String MIRCO_DOTTA = "mirco_dotta";
    public static final String TERO_PARVIAINEN = "tero";
    public static final String PATROKLOS_PAPAPERROU = "patroklos";
    public static final String SERGEY_KUKSENKO = "kuksenko";
    public static final String EDUARD_SIZOV = "sizov";
    public static final String SHEKHAR_GULATI = "gulati";
    public static final String JAN_VALENTA = "valenta";
    public static final String JAROSLAW_PALKA = "jaroslaw_palka";
    public static final String NICK_ZEEB = "zeeb";
    public static final String ALEXEY_FEDOROV = "fedorov";
    public static final String LUCIANO_FIANDESIQ = "luciano";
    public static final String ROMAN_ANTIPIN = "antipin";
    public static final String DENIS_MAGDA = "magda";

    @DatabaseField(id = true)
    private String name;

    @DatabaseField
    private String company;

    @DatabaseField
    private String photo;

    @DatabaseField
    private String info;

    @DatabaseField
    private String country;

    public Speaker() {}

    public Speaker(String name, String company, String photo, String info, String country) {
        this.name = name;
        this.company = company;
        this.photo = photo;
        this.info = info;
        this.country = country;
    }

    private Speaker(Parcel in) {
        name = in.readString();
        company = in.readString();
        photo = in.readString();
        info = in.readString();
        country = in.readString();
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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
        out.writeString(country);
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
