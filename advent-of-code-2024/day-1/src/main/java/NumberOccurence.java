public class NumberOccurence {

    private final Integer number;
    private long timesLeft;
    private long timesRight;

    public NumberOccurence(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public long getTimesLeft() {
        return timesLeft;
    }

    public void setTimesLeft(long timesLeft) {
        this.timesLeft = timesLeft;
    }

    public long getTimesRight() {
        return timesRight;
    }

    public void setTimesRight(long timesRight) {
        this.timesRight = timesRight;
    }

    @Override
    public String toString() {
        return "{" +
                "number=" + number +
                ", timesLeft=" + timesLeft +
                ", timesRight=" + timesRight +
                "}";
    }
}
