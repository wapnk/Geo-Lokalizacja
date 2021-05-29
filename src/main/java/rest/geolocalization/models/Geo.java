package rest.geolocalization.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="Geo")
public class Geo
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Length(min=5,max=50)
    private String deviceID;

    @Length(min=1,max=20)
    private String ip;

    @Range(min=5, max=100)
    private Double latitude;

    @Range(min=5, max=100)
    private Double longitude;

    @Length(min=1,max=20)
    private String city;

    @PastOrPresent
    private Date date;

    public Geo(){}

    public Geo(String deviceID, String ip, Double latitude, Double longitude, String city,Date date)
    {
        this.deviceID = deviceID;
        this.ip=ip;
        this.latitude = latitude;
        this.longitude = longitude;
        this.city=city;
        this.date=date;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitiude) {
        this.latitude = latitiude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }
    public String getDateString()
    {
        return new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").format(date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", deviceID='" + deviceID + '\'' +
                ", ip='" + ip + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", date=" + date;
    }
}
