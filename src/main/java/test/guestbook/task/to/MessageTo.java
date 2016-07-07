package test.guestbook.task.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Created by Qouer on 04.07.2016.
 */
public class MessageTo {

    private Integer id;

    private String defaultName;

    private String text;

    private String userName;

    private Integer plusCount;

    private Integer minusCount;

    private String authRate;

    private String data;

    private String time;


    public MessageTo(@JsonProperty("id") Integer id,
                     @JsonProperty("defaultName") String defaultName,
                     @JsonProperty("text") String text,
                     @JsonProperty("userName") String userName,
                     @JsonProperty("plusCount") Integer plusCount,
                     @JsonProperty("minusCount") Integer minusCount,
                     @JsonProperty("authRate") String authRate,
                     @JsonProperty("data") String data,
                     @JsonProperty("time") String time
    ) {
        this.id = id;
        this.defaultName = defaultName;
        this.text = text;
        this.userName = userName;
        this.plusCount = plusCount;
        this.minusCount = minusCount;
        this.authRate = authRate;
        this.data = data;
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPlusCount() {
        return plusCount;
    }

    public void setPlusCount(Integer plusCount) {
        this.plusCount = plusCount;
    }

    public Integer getMinusCount() {
        return minusCount;
    }

    public void setMinusCount(Integer minusCount) {
        this.minusCount = minusCount;
    }

    public String getAuthRate() {
        return authRate;
    }

    public void setAuthRate(String authRate) {
        this.authRate = authRate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "MessageTo{" +
                "id=" + id +
                ", defaultName='" + defaultName + '\'' +
                ", text='" + text + '\'' +
                ", userName='" + userName + '\'' +
                ", plusCount=" + plusCount +
                ", minusCount=" + minusCount +
                ", data='" + data + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MessageTo that = (MessageTo) o;
        return Objects.equals(this.defaultName, that.defaultName)
                && Objects.equals(this.id, that.id)
                && Objects.equals(this.text, that.text)
                && Objects.equals(this.userName, that.userName)
                && Objects.equals(this.plusCount, that.plusCount)
                && Objects.equals(this.minusCount, that.minusCount)
                && Objects.equals(this.data, that.data)
                && Objects.equals(this.time, that.time);
    }

}
