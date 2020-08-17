

class TimeBlock {
    private int maxTime;
    private int timeRemaining;
    private boolean finished;

    public TimeBlock(double maxS) {
        maxTime = (int)(Math.floor(maxS * 1000));
        timeRemaining = maxTime;
        finished = false;
    }

    public int getMaxTime() {
        return maxTime;
    }

    public void reduceTimeRemaining(int ms) {
        timeRemaining = timeRemaining - ms;
        if (timeRemaining <= 0) {
            finished = true;
        }
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public boolean isFinished() {
        return finished;
    }
}