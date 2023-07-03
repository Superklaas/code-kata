import java.util.ArrayList;
import java.util.List;

public class BowlingService {

    public List<Integer> parseInput(String input) {
        List<Integer> pinsDown = new ArrayList<>();
        for (String scorePerFrame : input.split(" ")) {
            if ("X".equals(scorePerFrame)) {
                pinsDown.add(10);
            } else {
                if (scorePerFrame.length() == 2) {
                    String[] rollsPerFrame = scorePerFrame.split("");
                    int roll1 = Integer.parseInt(rollsPerFrame[0]);
                    pinsDown.add(roll1);
                    String roll2 = rollsPerFrame[1];
                    if ("-".equals(roll2)) {
                        pinsDown.add(0);
                    } else if ("/".equals(roll2)) {
                        pinsDown.add(10 - roll1);
                    } else {
                        pinsDown.add(Integer.parseInt(roll2));
                    }
                } else {
                    pinsDown.add(Integer.parseInt(scorePerFrame));
                }
            }
        }
        return pinsDown;
    }

}
