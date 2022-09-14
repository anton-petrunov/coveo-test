package io.github.anton_petrunov.coveo_test_wip.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = {"name", "latitude", "longitude", "score"})
@JsonIgnoreProperties(value = {"geoNameId", "nameScore", "distanceScore", "distanceKm", "country", "admin1", "shortName"})
public class CityTo {
    private final Integer geoNameId;
    private final String shortName;
    private Float nameScore;
    private final Float latitude;
    private final Float longitude;
    private final String country;
    private final String admin1;
    private Float distanceKm;
    private Float distanceScore;
    private Float score;

    public CityTo(Integer geoNameId, String shortName, Float latitude, Float longitude, String country, String admin1) {
        this(geoNameId, shortName, 0F, latitude, longitude, country, admin1, 0F, 0F, 0F);
    }

    public CityTo(Integer geoNameId, String shortName, Float nameScore, Float latitude, Float longitude,
                  String country, String admin1, Float distanceKm, Float distanceScore, Float score) {
        this.geoNameId = geoNameId;
        this.shortName = shortName;
        this.nameScore = nameScore;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.admin1 = admin1;
        this.distanceKm = distanceKm;
        this.distanceScore = distanceScore;
        this.score = score;
    }

    @JsonProperty(value = "name", access = JsonProperty.Access.READ_ONLY)
    public String getName() {
        return getShortName() + ", " + getAdmin1() + ", " + getCountry();
    }

    public String getShortName() {
        return shortName;
    }

    public String getCountry() {
        return country;
    }

    public String getAdmin1() {
        return admin1;
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
                "name='" + shortName + '\'' +
                ", nameScore=" + nameScore +
                ", country='" + country + '\'' +
                ", admin1='" + admin1 + '\'' +
                ", distanceKm=" + distanceKm +
                ", distanceScore=" + distanceScore +
                ", score=" + score +
                '}';
    }
}
