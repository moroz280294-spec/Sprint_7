package ru.yandex.practicum.models;

public class Orders {
    private String firstNameClient;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    public Integer track;

    public Integer getTrack() {
        return track;
    }

    public Orders setTrack(Integer track) {
        this.track = track;
        return this;
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public Orders setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
        return this;
    }

    public String[] getColor() {
        return color;
    }

    public Orders setColor(String[] color) {
        this.color = color;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Orders setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Orders setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public Orders setMetroStation(String metroStation) {
        this.metroStation = metroStation;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Orders setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public int getRentTime() {
        return rentTime;
    }

    public Orders setRentTime(int rentTime) {
        this.rentTime = rentTime;
        return this;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public Orders setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Orders setComment(String comment) {
        this.comment = comment;
        return this;
    }

    private String comment;
    private String[] color;
}
