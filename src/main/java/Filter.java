import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {

    public static List<Flight> filter(List<Flight> flight) {
        System.out.println("вылет до сегодняшнего дня");
        return flight.stream().filter(f -> f.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now())).collect(Collectors.toList());
    }

    public static Flight filter1(List<Flight> flight) {
        List<Segment> segments = new ArrayList<>();
        for (Flight fly : flight) {
            for (int i = 0; i < fly.getSegments().size()-2; i+=2) {
                if (fly.getSegments().get(i).getDepartureDate().isBefore(fly.getSegments().get(i + 1).getArrivalDate())) {
                    segments.add(new Segment(fly.getSegments().get(i+1).getDepartureDate(), fly.getSegments().get(i).getArrivalDate()));
                }
            }
        }
        System.out.println("имеются сегменты с датой прилёта раньше даты вылета");
        return new Flight(segments);
    }

    public static Flight filter2(List<Flight> flight) {
        List<Segment> segments = new ArrayList<>();
        for (Flight fly : flight) {
            for (int i = 0; i < fly.getSegments().size() - 1; i ++) {
                if (fly.getSegments().get(i).getArrivalDate().plusHours(2).isBefore(fly.getSegments().get(i + 1).getDepartureDate())) {
                    System.out.println("общее время, проведённое на земле превышает два часа");
                    segments.add(new Segment(fly.getSegments().get(i).getDepartureDate(), fly.getSegments().get(i + 1).getArrivalDate()));
                }
            }
        }
        return new Flight(segments);
    }

}

