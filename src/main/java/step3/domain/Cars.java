package step3.domain;

import step3.domain.rule.MoveRule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Cars {
    private List<Car> cars = new ArrayList<>();

    public Cars(String[] carNames) {
        cars = Arrays.stream(carNames)
                .map(carName -> new Car(carName))
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public void moveAll(int roundTime, MoveRule moveRule) {
        cars.forEach(car -> car.move(roundTime, moveRule.canMove()));
    }

    public List<Car> getValue() {
        return Collections.unmodifiableList(cars);
    }

    public List<String> getWinnerNames() {
        List<String> winnerNames = new ArrayList<String>();

        for(Car car: cars) {
            addWinnerName(winnerNames, car);
        }
        return winnerNames;
    }

    private List<String> addWinnerName(List<String> winnerNames, Car car) {
        if(isWinner(car.getDistance())) {
            winnerNames.add(car.getName());
        }
        return winnerNames;
    }

    private boolean isWinner(int distance) {
        int winnerDistance = getMaxDistance(getFinalDistanceList());

        if(winnerDistance == distance) {
            return true;
        }
        return false;
    }

    private static int getMaxDistance(List<Integer> finalDistances) {
        return Collections.max(finalDistances);
    }

    private List<Integer> getFinalDistanceList() {
        List<Integer> finalDistanceList = new ArrayList<>();

        for(Car car: cars) {
            finalDistanceList.add(car.getDistance());
        }
        return finalDistanceList;
    }
}