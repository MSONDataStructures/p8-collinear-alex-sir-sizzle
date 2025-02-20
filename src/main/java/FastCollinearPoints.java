/*************************************************************************
 *   Given a point p, the following method determines
 *   whether p participates in a set of 4 or more collinear points.
 *   Think of p as the origin.
 *   - For each other point q, determine the slope it makes with p.
 *   - Sort the points according to the slopes they makes with p.
 *   - Check if any 3 (or more) adjacent points in 
 *      the sorted order have equal slopes with respect to p. 
 *      If so, these points, together with p, are collinear.
 *************************************************************************/
import java.util.*;

public class FastCollinearPoints {

    private final List<LineSegment> segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException("Input points array is null.");
        for (Point p : points) {
            if (p == null) throw new IllegalArgumentException("Input point is null.");
        }
        segments = new ArrayList<>();


        Point[] sortedPoints = new Point[points.length];
        System.arraycopy(points, 0, sortedPoints, 0, points.length);

        for (Point origin : sortedPoints) {
            Point[] slopesSorted = sortedPoints.clone();
            Arrays.sort(slopesSorted, origin.slopeOrder());

            for (int j = 1; j < slopesSorted.length - 2; j++) {

                if (origin.slopeTo(slopesSorted[j]) == origin.slopeTo(slopesSorted[j + 1]) &&
                        origin.slopeTo(slopesSorted[j + 1]) == origin.slopeTo(slopesSorted[j + 2]) &&
                        origin.slopeTo(slopesSorted[j + 2]) == origin.slopeTo(slopesSorted[j + 3])) {

                    LineSegment segment = new LineSegment(origin, slopesSorted[j + 3]);
                    segments.add(segment);

                    origin.drawTo(slopesSorted[j + 3]);
                }
            }
        }
        }



        public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}
