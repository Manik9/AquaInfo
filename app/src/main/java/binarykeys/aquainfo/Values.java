package binarykeys.aquainfo;

/**
 * Created by ACER on 10/6/2017.
 */

public class Values {


    private String id;
    private String temperature;
    private String turbidity;
    private String ph;
    private String conductivity;
    private String d_o;
    private String orp;
    private String time;
    private String salinity;
    private String eColi;
    private String fluoride;
    private String arsenic;
    private String rChlorine;
    private String latitude;

    private String longitude;

    public Values(String id, String temperature, String turbidity, String ph,
                  String conductivity, String d_o, String orp, String time,
                  String salinity, String eColi, String fluoride,
                  String arsenic, String rChlorine, String latitude, String longitude) {
        this.id = id;
        this.temperature = temperature;
        this.turbidity = turbidity;
        this.ph = ph;
        this.conductivity = conductivity;
        this.d_o = d_o;
        this.orp = orp;
        this.time = time;
        this.salinity = salinity;
        this.eColi = eColi;
        this.fluoride = fluoride;
        this.arsenic = arsenic;
        this.rChlorine = rChlorine;
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTurbidity() {
        return turbidity;
    }

    public void setTurbidity(String turbidity) {
        this.turbidity = turbidity;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getConductivity() {
        return conductivity;
    }

    public void setConductivity(String conductivity) {
        this.conductivity = conductivity;
    }

    public String getD_o() {
        return d_o;
    }

    public void setD_o(String d_o) {
        this.d_o = d_o;
    }

    public String getOrp() {
        return orp;
    }

    public void setOrp(String orp) {
        this.orp = orp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSalinity() {
        return salinity;
    }

    public void setSalinity(String salinity) {
        this.salinity = salinity;
    }

    public String geteColi() {
        return eColi;
    }

    public void seteColi(String eColi) {
        this.eColi = eColi;
    }

    public String getFluoride() {
        return fluoride;
    }

    public void setFluoride(String fluoride) {
        this.fluoride = fluoride;
    }

    public String getArsenic() {
        return arsenic;
    }

    public void setArsenic(String arsenic) {
        this.arsenic = arsenic;
    }

    public String getrChlorine() {
        return rChlorine;
    }

    public void setrChlorine(String rChlorine) {
        this.rChlorine = rChlorine;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


}
