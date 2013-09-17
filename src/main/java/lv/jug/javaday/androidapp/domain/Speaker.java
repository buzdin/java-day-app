package lv.jug.javaday.androidapp.domain;

public class Speaker {

    private String name;

    private String company;

    private String photo;

    private String info;

    public Speaker(String name, String company, String photo, String info) {
        this.name = name;
        this.company = company;
        this.photo = photo;
        this.info = info;
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
}
