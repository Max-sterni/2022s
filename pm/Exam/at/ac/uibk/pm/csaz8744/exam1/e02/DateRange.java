package at.ac.uibk.pm.csaz8744.exam1.e02;

import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Iterator;
import java.time.LocalDate;

public class DateRange implements Iterable<LocalDate> {

    private final LocalDate startDate;
    private final LocalDate endDate;

    private LocalDate currentDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        if(startDate.compareTo(endDate) >= 0){
            throw new IllegalArgumentException("start date must be smaller then end date");
        }
        this.currentDate = startDate;
    }

    @Override
    public Iterator<LocalDate> iterator() {
        return new DateIterator();
    }

    private class DateIterator implements Iterator<LocalDate> {

        @Override
        public boolean hasNext() {
            return currentDate.isBefore(endDate) || currentDate.isEqual(endDate);
        }

        @Override
        public LocalDate next() {
            LocalDate out = currentDate;
            currentDate = currentDate.plusDays(1);
            return out;
        }
    }

    public static void main(String[] args) {
        DateRange dateRange = new DateRange(LocalDate.of(2022, 12, 1), LocalDate.of(2023, 1, 1));
        for (LocalDate localDate : dateRange) {
            System.out.println(localDate);
        }
    }

}
