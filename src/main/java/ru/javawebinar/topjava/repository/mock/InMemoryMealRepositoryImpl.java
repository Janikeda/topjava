package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
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
       // repositoryId = populateRepoCommonMap(repository);
    }

//    private Map<Integer, Map<Integer, Meal>> populateRepoCommonMap(Map<Integer, Meal> map) {
//        Map<Integer, Map<Integer, Meal>> mapCommon = new ConcurrentHashMap<>();
//        map.forEach((k, v) -> mapCommon.merge(v.getUserId(), new ConcurrentHashMap<>(), (a, b) -> {
//            Map<Integer, Meal> mapA = new ConcurrentHashMap<>(a);
//            Map<Integer, Meal> mapB = new ConcurrentHashMap<>(b);
//            mapA.put(k, v);
//            mapB.put(k, v);
//            mapB.putAll(mapA);
//            return mapB;
//        }));
//        return mapCommon;
//    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
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
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        // treat case: update, but absent in storage
        //repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
        //return repositoryId.get(repository.get(meal.getId()).getUserId()).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        Meal mealForDelete = repository.get(id);
        return repositoryId.get(mealForDelete.getUserId())
         .remove(id, mealForDelete);
    }

    @Override
    public Meal get(int id) {
        return repositoryId.get(repository.get(id).getUserId())
                .get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repositoryId.get(userId).values().stream()
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
    public List<Meal> getAllFilteredTime(int userId, LocalTime start, LocalTime finish) {
        return getAll(userId).stream()
                .filter(meal -> DateTimeUtil.isBetweenDate(meal.getTime(), start, finish))
                .collect(Collectors.toList());
    }
}