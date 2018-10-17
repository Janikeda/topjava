package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private Map<Integer, Map<Integer, Meal>> repositoryId = new ConcurrentHashMap<>();

    {
        MealsUtil.MEALS.forEach(this::save);
    }

    @Override
    public Meal save(Meal meal) {
        meal.setId(counter.incrementAndGet());
        repository.put(meal.getId(), meal);
        repositoryId.merge(meal.getUserId(), new ConcurrentHashMap<>(), (a, b) -> {
            Map<Integer, Meal> mapA = new ConcurrentHashMap<>(a);
            Map<Integer, Meal> mapB = new ConcurrentHashMap<>(b);
            mapA.put(meal.getId(), meal);
            mapB.put(meal.getId(), meal);
            mapB.putAll(mapA);
            return mapB;
        });
        return meal;
    }

    @Override
    public boolean delete(int id) {
        Meal mealForDelete = repository.get(id);
        return repository.remove(id, mealForDelete) && repositoryId.get(mealForDelete.getUserId()).remove(id, mealForDelete);
    }

    @Override
    public Meal update(Meal meal) {
        repositoryId.computeIfPresent(meal.getUserId(), (k, v) -> {
            v.computeIfPresent(meal.getId(), (idMeal, oldMeal) -> meal);
            return v;
        });
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public Meal get(int id) {
        return repositoryId.get(repository.get(id).getUserId())
                .get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        Map<Integer, Meal> map = repositoryId.get(userId) == null ? Collections.emptyMap() : repositoryId.get(userId);

        return map.values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed().thenComparing(Meal::getId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllFilteredDate(int userId, LocalDate start, LocalDate finish) {
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetweenDate(meal.getDate(), start, finish))
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAllFilteredTime(List<Meal> meals, LocalTime start, LocalTime finish) {
        return meals.stream()
                .filter(meal -> DateTimeUtil.isBetweenDate(meal.getTime(), start, finish))
                .collect(Collectors.toList());
    }
}