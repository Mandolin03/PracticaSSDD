package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Restaurant;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParametersException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RestaurantService {
    private final Map<Long, Restaurant> restaurants = new HashMap<>();
    private final AtomicLong nextId = new AtomicLong();

    public Restaurant createTable(Restaurant restaurant) {
        checkRestaurant(restaurant);
        long id = nextId.getAndIncrement();
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
        checkRestaurant(restaurant);
        Restaurant original = restaurants.get(id);
        if(restaurant.getName() != null)original.setName(restaurant.getName());
        if(restaurant.getStyle() != null)original.setStyle(restaurant.getStyle());
        if(restaurant.getQuality() != null)original.setQuality(restaurant.getQuality());
        if(restaurant.getLocation() != null)original.setLocation(restaurant.getLocation());

        restaurants.put(id, original);
        return original;
    }

    public Restaurant putRestaurant(Long id, Restaurant restaurant){
        if (!restaurants.containsKey(id)) {
            return null;
        }
        checkRestaurant(restaurant);
        restaurant.setId(id);
        restaurants.put(id, restaurant);
        return restaurant;
    }

    public boolean removeRestaurant(Long id) {

        return restaurants.remove(id) != null;
    }

    private void checkRestaurant(Restaurant restaurant){
        if((restaurant.getName() != null && restaurant.getName().isEmpty()) ||
                (restaurant.getLocation() != null && restaurant.getLocation().isEmpty()) ||
                (restaurant.getStyle() != null && restaurant.getStyle().isEmpty()))
        {
            throw new MalformedParametersException("Los campos no pueden estar vacios");
        }
        else if(restaurant.getQuality() != null && (restaurant.getQuality() < 0 || restaurant.getQuality() > 10)){
            throw new MalformedParametersException("La calidad debe estar entre 0 y 10");
        }
    }
}
