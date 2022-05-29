package at.ac.uibk.pm.g01.csaz8744.s08.e03;

import java.util.List;

class LazyList {
    private List<Runnable> list;

    public LazyList(List<Runnable> list){
        this.list = list;
    }

    public void evaluateIndex(int i){
        list.get(i).run();
    }
}