package at.ac.uibk.pm.g01.csaz8744.s07.e01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * InMemoryDatabase
 */
public class InMemoryDatabase<UniqueIdentifier, DataType extends Identifiable<UniqueIdentifier>> {

    private Map<UniqueIdentifier, DataType> storage = new HashMap<>();

    public DataType save(DataType data) throws IllegalStateException{
        if (storage.putIfAbsent(data.getUniqueIdentifier(), data) != null) {
            throw new IllegalStateException("unique identifier already used");
        }
        return data;
    }

    public DataType delete(DataType data){
        if (storage.remove(data.getUniqueIdentifier()) == null) {
            throw new IllegalStateException("unique identifier not saved");
        }
        return data;
    }

    public Optional<DataType> findOne(UniqueIdentifier key){
        // Using the optional here reduces Exceptions and therefore code complexity
        return Optional.ofNullable(storage.get(key));
    }

    public List<DataType> findAll(Comparator<DataType> Comparator){
        List<DataType> output = new ArrayList<>();
        output.addAll(valueSet());
        output.sort(Comparator);
        return output;
    }

    private Set<DataType> valueSet(){
        Set<DataType> output = new HashSet<>();
        for (UniqueIdentifier key : storage.keySet()) {
            output.add(storage.get(key));
        }
        return output;
    }
}