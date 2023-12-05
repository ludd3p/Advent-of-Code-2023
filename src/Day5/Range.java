package Day5;

public class Range {
    long start;
    long destination;
    long range;
    public Range (String line) {
        String[] splits = line.split(" ");
        destination = Long.parseLong(splits[0]);
        start = Long.parseLong(splits[1]);
        range = Long.parseLong(splits[2]);
    }
}
