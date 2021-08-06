package com.taveeshsharma.requesthandler.measurements;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * This is the base measurement class and contains all the column fields that each measurement should have.
 * All the other network measurement extend from this class.
 */
// TODO: Add boolean flag "success"
@Measurement(name="SuperMeasurement")
public class Measurements {
    @Column(name = "time")
    private Instant time;

    @Column(name="taskKey", tag = true)
    private String taskKey;

    @Column(name = "username")
    private String userName;

    @Column(name="target")
    private String target;

    @Column(name = "instance_number")
    private Integer instanceNumber;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setTaskKey(String taskKey) {
        this.taskKey = taskKey;
    }

    public String getTaskKey() {
        return taskKey;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Integer getInstanceNumber() {
        return instanceNumber;
    }

    public void setInstanceNumber(Integer instanceNumber) {
        this.instanceNumber = instanceNumber;
    }
}
