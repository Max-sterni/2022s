package at.ac.uibk.pm.g01.csaz8744.midterm2.e03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class Exercise3Imperative implements Exercise3Interface {

    @Override
    public List<Integer> methodA(final List<Integer> list) {
        List<Integer> result = new ArrayList<>();
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            int x = iterator.next();
            if (!result.contains(x)) {
                result.add(x);
            }
        }
        return result;
    }

    @Override
    public int methodB(final List<List<Integer>> lists) {
        int i = 0;
        int result = 0;
        while (i < lists.size()) {
            List<Integer> l = lists.get(i);
            result += l.size();
            i++;
        }
        return result;
    }

    @Override
    public Optional<String> methodC(final List<String> list) {
        if (list.isEmpty()) {
            return Optional.empty();
        }
        String s = list.get(0);
        int l = s.length();
        Iterator<String> iterator = list.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.length() > l) {
                s = next;
                l = s.length();
            }
        }
        return Optional.of(s);
    }

}
