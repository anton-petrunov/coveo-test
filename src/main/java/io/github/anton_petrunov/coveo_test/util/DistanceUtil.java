package io.github.anton_petrunov.coveo_test.util;

public class DistanceUtil {
    public static Float calculateDistanceKm(float searchPointLatitude, float searchPointLongitude,
                                            float cityPointLatitude, float cityPointLongitude) {

//        Distance on Earth surface calculation - http://osiktakan.ru/geo_koor.htm#:~:text=%D0%A0%D0%B0%D1%81%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5%20%D0%BC%D0%B5%D0%B6%D0%B4%D1%83%20%D0%BF%D1%83%D0%BD%D0%BA%D1%82%D0%B0%D0%BC%D0%B8%2C%20%D0%B8%D0%B7%D0%BC%D0%B5%D1%80%D1%8F%D0%B5%D0%BC%D0%BE%D0%B5%20%D0%B2,%D0%BA%D0%BC%20%E2%80%94%20%D1%81%D1%80%D0%B5%D0%B4%D0%BD%D0%B8%D0%B9%20%D1%80%D0%B0%D0%B4%D0%B8%D1%83%D1%81%20%D0%B7%D0%B5%D0%BC%D0%BD%D0%BE%D0%B3%D0%BE%20%D1%88%D0%B0%D1%80%D0%B0.
        double searchPointLatitudeRadians = Math.toRadians(searchPointLatitude);
        double searchPointLongitudeRadians = Math.toRadians(searchPointLongitude);
        double cityPointLatitudeRadians = Math.toRadians(cityPointLatitude);
        double cityPointLongitudeRadians = Math.toRadians(cityPointLongitude);

        double cosD = Math.sin(searchPointLatitudeRadians) * Math.sin(cityPointLatitudeRadians) +
                Math.cos(searchPointLatitudeRadians) * Math.cos(cityPointLatitudeRadians) *
                        Math.cos(searchPointLongitudeRadians - cityPointLongitudeRadians);

        return (float) Math.acos(cosD) * 6371;
    }
}
