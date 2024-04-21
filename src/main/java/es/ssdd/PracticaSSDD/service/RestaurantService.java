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

    public Restaurant createTable(Restaurant restaurant) {
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
        checkRestaurant(restaurant);
        Restaurant original = restaurantRepository.getReferenceById(id);
        if(restaurant.getName() != null)original.setName(restaurant.getName());
        if(restaurant.getStyle() != null)original.setStyle(restaurant.getStyle());
        if(restaurant.getQuality() != null)original.setQuality(restaurant.getQuality());
        if(restaurant.getLocation() != null)original.setLocation(restaurant.getLocation());
        if(restaurant.getDishes() != null){
            original.setDishes(restaurant.getDishes());
        } else{
            Set<Dish> dishes = new HashSet<>();
            original.setDishes(dishes);
        }


        restaurantRepository.save(original);
        return original;
    }

    public Restaurant putRestaurant(Long id, Restaurant restaurant){
        if (!restaurantRepository.existsById(id)) {
            return null;
        }
        checkRestaurant(restaurant);
        restaurant.setId(id);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public boolean removeRestaurant(Long id) {
        if(!restaurantRepository.existsById(id)) return false;
        restaurantRepository.deleteById(id);
        return true;
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
