package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, Map<Integer,Meal>> repositoryId = new ConcurrentHashMap<>(repository);;

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    private void populateRepoCommonMap(Map<Integer, Meal> map) {
        map.merge()
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            repositoryId.merge(meal.getUserId(), repository, meal.getUserId() -> meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return repositoryId.remove(id) != null;
    }

    @Override
    public Meal get(int idMeal) {
        return repository.get(idMeal);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repositoryId.get(userId).values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

}