package io.github.anton_petrunov.coveo_test_wip.model;

public class City {
    private final Integer geoNameId;
    private final String name;
    private final float latitude;
    private final float longitude;
    private final String country;
    private final String admin1;

    public City(Integer geoNameId, String name, float latitude, float longitude, String country, String admin1) {
        this.geoNameId = geoNameId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.admin1 = admin1;
    }

    public Integer getGeoNameId() {
        return geoNameId;
    }

    public String getName() {
        return name;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getAdmin1() {
        return admin1;
    }

    @Override
    public String toString() {
        return "City{" +
                "geoNameId=" + geoNameId +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", admin1='" + admin1 + '\'' +
                '}';
    }
}
