package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RestaurantService {
    private final Map<Long, Restaurant> restaurants = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Restaurant createTable(Restaurant restaurant) {
        long id = nextId.incrementAndGet();
        restaurant.setId(id);
        restaurants.put(id, restaurant);
        return restaurant;
    }

    public Restaurant getRestaurant(Long id) {

        return restaurants.get(id);
    }

    public Collection<Restaurant> getRestaurants() {

        return restaurants.values();
    }

    public Restaurant editRestaurant(Long id, Restaurant restaurant) {

        if (!restaurants.containsKey(id)) {
            return null;
        }
        Restaurant original = restaurants.get(id);
        if(restaurant.getName() != null)original.setName(restaurant.getName());
        if(restaurant.getStyle() != null)original.setStyle(restaurant.getStyle());
        if(restaurant.getQuality() != null)original.setQuality(restaurant.getQuality());
        if(restaurant.getLocation() != null)original.setLocation(restaurant.getLocation());

        restaurants.put(id, original);
        return original;
    }

    public boolean removeRestaurant(Long id) {

        return restaurants.remove(id) != null;
    }
}
