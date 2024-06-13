package es.ssdd.PracticaSSDD.service;

import es.ssdd.PracticaSSDD.entities.Dish;
import es.ssdd.PracticaSSDD.entities.Restaurant;
import es.ssdd.PracticaSSDD.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.MalformedParametersException;
import java.util.*;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(Restaurant restaurant) {
        checkRestaurant(restaurant);
        restaurantRepository.save(restaurant);
        return restaurant;
    }


    public Restaurant getRestaurant(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public Collection<Restaurant> getRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant editRestaurant(Long id, Restaurant restaurant) {
        if (!restaurantRepository.existsById(id)) {
            return null;
        }
        Restaurant original = restaurantRepository.findById(id).get();
        if (restaurant.getName() != null) original.setName(restaurant.getName());
        if (restaurant.getStyle() != null) original.setStyle(restaurant.getStyle());
        if (restaurant.getQuality() != null) original.setQuality(restaurant.getQuality());
        if (restaurant.getLocation() != null) original.setLocation(restaurant.getLocation());
        if (restaurant.getDishes() != null && restaurant.getDishes().size() > 0){
            System.out.println(restaurant.getDishes().size());
            original.setDishes(restaurant.getDishes());
        } else {
            original.setDishes(new HashSet<>());
        }

        checkRestaurant(original);
        restaurantRepository.save(original);
        return original;
    }

    public Restaurant putRestaurant(Long id, Restaurant restaurant) {
        if (!restaurantRepository.existsById(id)) {
            return null;
        }
        checkRestaurant(restaurant);
        restaurant.setId(id);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public boolean removeRestaurant(Long id) {
        if (!restaurantRepository.existsById(id)) return false;
        for (Dish dish : restaurantRepository.findById(id).get().getDishes()) {
            dish.setRestaurant(null);
        }
        restaurantRepository.deleteById(id);
        return true;
    }

    private void checkRestaurant(Restaurant restaurant) {
        if(restaurant.getName() == null ||
                restaurant.getLocation() == null ||
                restaurant.getStyle() == null ||
                restaurant.getQuality() == null) {
            throw new MalformedParametersException("Los campos no pueden ser nulos");
        }
        if (restaurant.getName().isEmpty() ||
                restaurant.getLocation().isEmpty() ||
                restaurant.getStyle().isEmpty()
        ) {
            throw new MalformedParametersException("Los campos no pueden estar vacios - REST");
        }
        if (restaurant.getQuality() < 0 || restaurant.getQuality() > 10) {
            throw new MalformedParametersException("La calidad debe estar entre 0 y 10");
        }
    }
}
