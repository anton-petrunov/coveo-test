package io.github.anton_petrunov.coveo_test_wip.to;

public class CityTo {
    private final Integer geoNameId;
    private final String name;
    private final Float Latitude;
    private final Float longitude;
    private final String country;
    private final String admin1;
    private Float distanceKm;

    public CityTo(Integer geoNameId, String name, Float latitude, Float longitude, String country, String admin1, float distanceKm) {
        this.geoNameId = geoNameId;
        this.name = name;
        Latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.admin1 = admin1;
        this.distanceKm = distanceKm;
    }

    public Float getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(float distanceKm) {
        this.distanceKm = distanceKm;
    }

    @Override
    public String toString() {
        return "CityTo{" +
                "geoNameId=" + geoNameId +
                ", name='" + name + '\'' +
                ", Latitude=" + Latitude +
                ", longitude=" + longitude +
                ", country='" + country + '\'' +
                ", admin1='" + admin1 + '\'' +
                ", distanceKm=" + distanceKm +
                '}';
    }
}
