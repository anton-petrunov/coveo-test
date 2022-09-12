package io.github.anton_petrunov.coveo_test_wip.util;

public class ScoreUtil {
    public static Float getNameScore(Integer quantity) {
        return quantity >= 0 && quantity <= 15 ? (float) (-0.05 * quantity + 0.95) : 0.2F;
    }

    public static Float getDistanceScore(Float distanceKm) {
        return distanceKm <= 50 ? 1 : (float) (-0.144 * Math.log(distanceKm) + 1.4644);
    }

    public static Float getScore(Float nameScore, Float distanceScore) {
        float weightedNamedScore = 0.5F;
        Float weightedDistanceScore = 1F - weightedNamedScore;
        return weightedNamedScore * nameScore + weightedDistanceScore * distanceScore;
    }
}
