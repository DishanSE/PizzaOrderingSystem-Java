package PromotionsAndSeasonalSpecials;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PromotionManager {
    private List<Promotion> promotions = new ArrayList<>();

    public void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public List<Promotion> getActivePromotions() {
        return promotions.stream().filter(Promotion::isActive).collect(Collectors.toList());
    }
}
