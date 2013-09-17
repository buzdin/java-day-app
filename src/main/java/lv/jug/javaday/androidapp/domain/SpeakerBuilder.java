package lv.jug.javaday.androidapp.domain;

public class SpeakerBuilder {
    private String name;
    private String company;
    private String photo;
    private String info;

    public SpeakerBuilder() {
        this.name = "Jhon";
        this.company = "Doe company";
        this.photo = "portrait.jpg";
        this.info = "Hodor, hodor. Hodor. Hodor, hodor; hodor hodor hodor hodor! Hodor hodor HODOR! Hodor hodor, hodor. Hodor hodor hodor hodor, hodor. Hodor hodor, hodor, hodor hodor. Hodor, hodor. Hodor. Hodor, hodor, hodor. Hodor hodor? Hodor, hodor... Hodor hodor hodor; hodor HODOR hodor, hodor hodor hodor! Hodor, hodor. Hodor. Hodor, hodor... Hodor hodor hodor, hodor. Hodor hodor, hodor. Hodor hodor hodor hodor!";
    }

    public SpeakerBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SpeakerBuilder setCompany(String company) {
        this.company = company;
        return this;
    }

    public SpeakerBuilder setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public SpeakerBuilder setInfo(String info) {
        this.info = info;
        return this;
    }

    public Speaker createSpeaker() {
        return new Speaker(name, company, photo, info);
    }
}