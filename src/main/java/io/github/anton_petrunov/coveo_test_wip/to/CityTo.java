package io.github.anton_petrunov.coveo_test_wip.to;

public class CityTo {
    private final Integer geoNameId;
    private final String name;
    private Float nameScore;
    private final Float latitude;
    private final Float longitude;
    private final String country;
    private final String admin1;
    private Float distanceKm;
    private Float distanceScore;
    private Float score;

    public CityTo(Integer geoNameId, String name, Float latitude, Float longitude, String country, String admin1) {
        this(geoNameId, name, 0F, latitude, longitude, country, admin1, 0F, 0F, 0F);
    }

    public CityTo(Integer geoNameId, String name, Float nameScore, Float latitude, Float longitude, String country, String admin1, Float distanceKm, Float distanceScore, Float score) {
        this.geoNameId = geoNameId;
        this.name = name;
        this.nameScore = nameScore;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.admin1 = admin1;
        this.distanceKm = distanceKm;
        this.distanceScore = distanceScore;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Float getDistanceKm() {
        return distanceKm;
    }

    public Float getNameScore() {
        return nameScore;
    }

    public Float getDistanceScore() {
        return distanceScore;
    }

    public Float getScore() {
        return score;
    }

    public void setDistanceKm(float distanceKm) {
        this.distanceKm = distanceKm;
    }

    public void setNameScore(Float nameScore) {
        this.nameScore = nameScore;
    }

    public void setDistanceScore(Float distanceScore) {
        this.distanceScore = distanceScore;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "CityTo{" +
                "name='" + name + '\'' +
                ", nameScore=" + nameScore +
                ", country='" + country + '\'' +
                ", admin1='" + admin1 + '\'' +
                ", distanceKm=" + distanceKm +
                ", distanceScore=" + distanceScore +
                ", score=" + score +
                '}';
    }
}
