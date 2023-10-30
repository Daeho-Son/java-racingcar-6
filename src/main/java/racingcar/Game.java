package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {

    public final String ANNOUNCEMENT_INPUT_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    public final String ANNOUNCEMENT_INPUT_MOVE_COUNT = "시도할 회수는 몇회인가요?";
    public final String ANNOUNCEMENT_EXECUTION_RESULT = "실행 결과";
    public final String ANNOUNCEMENT_WINNER = "최종 우승자 : ";

    public String[] carNameArray;
    public int[] carLocationArray;
    public int numberOfCar;
    public int moveCount;

    public Game() {
        init();
    }

    public void run() {
        System.out.println(this.ANNOUNCEMENT_EXECUTION_RESULT);
        for (int i = 0; i < this.moveCount; i++) {
            carRuns();
            System.out.println();
        }
        String[] winnerArray = getWinnerArray();
        printWinner(winnerArray);
    }

    private void printWinner(String[] winnerArray) {
        System.out.print(this.ANNOUNCEMENT_WINNER);
        int i = 0;
        for (; i < winnerArray.length - 1; i++) {
            System.out.print(winnerArray[i] + ",");
        }
        System.out.println(winnerArray[i]);
    }

    private String[] getWinnerArray() {
        List<String> winnerList = new ArrayList<>();
        int maxLocation = Arrays.stream(this.carLocationArray).max().getAsInt();
        for (int i = 0; i < this.numberOfCar; i++) {
            if (this.carLocationArray[i] == maxLocation) {
                winnerList.add(carNameArray[i]);
            }
        }
        return winnerList.toArray(new String[winnerList.size()]);
    }

    private void carRuns() {
        for (int i = 0; i < this.numberOfCar; i++) {
            int randomNumber = Randoms.pickNumberInRange(0, 9);
            if (randomNumber >= 4) {
                this.carLocationArray[i]++;
            }
            int currentLocation = this.carLocationArray[i];
            System.out.println(this.carNameArray[i] + " : " + "-".repeat(currentLocation));
        }
    }

    private void init() {
        this.carNameArray = getCarNameArrayFromInput();
        this.numberOfCar = this.carNameArray.length;
        this.carLocationArray = new int[this.numberOfCar];
        this.moveCount = getMoveCountFromInput();
    }

    private String[] getCarNameArrayFromInput() {
        System.out.println(this.ANNOUNCEMENT_INPUT_CAR_NAMES);
        String[] inputCarNameArray = Console.readLine().split(",");
        Validator.validateCarNameArray(inputCarNameArray);
        return inputCarNameArray;
    }

    private int getMoveCountFromInput() {
        System.out.println(this.ANNOUNCEMENT_INPUT_MOVE_COUNT);
        String inputMoveCountString = Console.readLine();
        Validator.validateMoveCount(inputMoveCountString);
        return Integer.parseInt(inputMoveCountString);
    }

}
