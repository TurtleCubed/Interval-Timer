import java.util.ArrayDeque;

class Main {
    public static void main(String[] args) {
        //argument check
        if (args.length % 2 != 0 || args.length == 0) {
            String errorMessage =
                    "Illegal number arguments found\nEnter numbers in pairs\n" +
                            "For example, \"30.5 3\" for 3 rounds of 30 seconds";
            throw new IllegalArgumentException(errorMessage);
        } else {
            String ref1 = "\\d+";
            String ref2 = "\\d+|\\d+[.]|\\d+[.]\\d+";
            for (int i = 0; i < args.length; i++) {
                if (i % 2 == 0) {
                    if (!args[i].matches(ref2)) {
                        String errorMessage =
                                "Non numeric character found";
                        throw new IllegalArgumentException(errorMessage);
                    }
                } else {
                    if (!args[i].matches(ref1)) {
                        String errorMessage =
                                "Non numeric character found";
                        throw new IllegalArgumentException(errorMessage);
                    }
                }
            }

        }

        ArrayDeque<TimeBlock> blockQueue = new ArrayDeque<TimeBlock>();

        //loading up the queue
        for (int i = 0; i < args.length; i += 2) {
            double duration = Double.parseDouble(args[i]);
            int multiplier = Integer.parseInt(args[i + 1]);
            for (int j = 0; j < multiplier; j++)
            blockQueue.add(new TimeBlock(duration));
        }


        //Running the timer
        TimeBlock currentBlock = blockQueue.peek();
        long currentTime;
        long timeElapsed;
        long startTime = System.currentTimeMillis();
        while (!blockQueue.isEmpty()) {
            currentTime = System.currentTimeMillis();
            timeElapsed = currentTime - startTime;
            startTime = currentTime;

            double remainingTime = (double)currentBlock.getTimeRemaining();
            remainingTime = remainingTime / 1000;
            System.out.format("%.3f%n", remainingTime);
            currentBlock.reduceTimeRemaining((int)timeElapsed);
            if (currentBlock.isFinished()) {
                blockQueue.remove();
                currentBlock = blockQueue.peek();
            }
            Waiter.wait(101);
        }
        System.out.println("Done!");

    }
}