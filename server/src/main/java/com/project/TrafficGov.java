package com.project;

public class TrafficGov {
    private String deviceid;
    private int countedcars;
    private String appprocesstime;
    private String road_name;
    private String road_info;
    private double average_speed;

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public int getCountedcars() {
        return countedcars;
    }

    public void setCountedcars(int countedcars) {
        this.countedcars = countedcars;
    }

    public String getAppprocesstime() {
        return appprocesstime;
    }

    public void setAppprocesstime(String appprocesstime) {
        this.appprocesstime = appprocesstime;
    }

    public String getRoad_name() {
        return road_name;
    }

    public void setRoad_name(String road_name) {
        this.road_name = road_name;
    }

    public String getRoad_info() {
        return road_info;
    }

    public void setRoad_info(String road_info) {
        this.road_info = road_info;
    }

    public double getAverage_speed() {
        return average_speed;
    }

    public void setAverage_speed(double average_speed) {
        this.average_speed = average_speed;
    }

    @Override
    public String toString() {
        return "TrafficGov{" +
                "deviceid='" + deviceid + '\'' +
                ", countedcars=" + countedcars +
                ", appprocesstime='" + appprocesstime + '\'' +
                ", road_name='" + road_name + '\'' +
                ", road_info='" + road_info + '\'' +
                ", average_speed=" + average_speed +
                '}';
    }
}
