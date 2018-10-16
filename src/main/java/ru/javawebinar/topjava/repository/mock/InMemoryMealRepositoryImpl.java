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
    private AtomicInteger counter = new AtomicInteger(0);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private Map<Integer, Map<Integer, Meal>> repositoryId;

    {
        MealsUtil.MEALS.forEach(this::save);
        repositoryId = populateRepoCommonMap(repository);
    }

    private Map<Integer, Map<Integer, Meal>> populateRepoCommonMap(Map<Integer, Meal> map) {
        Map<Integer, Map<Integer, Meal>> mapCommon = new ConcurrentHashMap<>();
        map.forEach((k, v) -> mapCommon.merge(v.getUserId(), new ConcurrentHashMap<>(), (a, b) -> {
            Map<Integer, Meal> mapA = new ConcurrentHashMap<>(a);
            Map<Integer, Meal> mapB = new ConcurrentHashMap<>(b);
            mapA.put(k, v);
            mapB.put(k, v);
            mapB.putAll(mapA);
            return mapB;
        }));
        return mapCommon;
    }

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id, repository.get(id));
    }

    @Override
    public Meal get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return repositoryId.get(userId).values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}