package publisher;

import subscriber.Subscriber;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DependencyManager {
    private final Map<Subscriber, Set<Subscriber>> dependencies;

    public DependencyManager(){
        this.dependencies = new ConcurrentHashMap<>();
    }

    public void addDependency(Subscriber dependent, Subscriber dependency) {
        if (dependent == null || dependency == null) {
            throw new IllegalArgumentException("Dependent and dependency cannot be null");
        }
        dependencies.computeIfAbsent(dependent, k -> ConcurrentHashMap.newKeySet()).add(dependency);
        dependencies.putIfAbsent(dependency, ConcurrentHashMap.newKeySet());
    }

    public List<Subscriber> getOrderedSubscribers(Collection<Subscriber> subscribers){
        List<Subscriber> orderedSubscribers = new ArrayList<>();
        Set<Subscriber> visited = ConcurrentHashMap.newKeySet();
        Set<Subscriber> visiting = ConcurrentHashMap.newKeySet();

        for(Subscriber subscriber : subscribers){
            if(!visited.contains(subscriber)){
                if(dfs(subscriber, visited, visiting, orderedSubscribers)) {
                    continue;
                }
                throw new IllegalStateException("Circular dependency detected for subscriber: " + subscriber);
            }
        }
        Collections.reverse(orderedSubscribers);
        return orderedSubscribers;
    }

    private boolean dfs(Subscriber subscriber, Set<Subscriber> visited, Set<Subscriber> visiting, List<Subscriber> orderedSubscribers) {
        if (visiting.contains(subscriber)) {
            return false; // Circular dependency detected
        }
        if (visited.contains(subscriber)) {
            return true; // Already processed
        }

        visiting.add(subscriber);

        Set<Subscriber> deps = dependencies.getOrDefault(subscriber, ConcurrentHashMap.newKeySet());
        for (Subscriber dep : deps) {
            if (!dfs(dep, visited, visiting, orderedSubscribers)) {
                return false;
            }
        }

        visiting.remove(subscriber);
        visited.add(subscriber);
        orderedSubscribers.add(subscriber);

        return true;
    }
}
