/*
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
        private final List<LineSegment> segments = new ArrayList<>();

        public FastCollinearPoints(Point[] points) {
            if (points == null) throw new IllegalArgumentException("Input points array is null.");
            for (Point p : points) {
                if (p == null) throw new IllegalArgumentException("Input point is null.");
            }


            Point[] sortedPoints = new Point[points.length];
            System.arraycopy(points, 0, sortedPoints, 0, points.length);

            for (Point origin : sortedPoints) {
                Point[] slopesSorted = sortedPoints.clone();
                Arrays.sort(slopesSorted, origin.slopeOrder());

                for (int o = 1; o < slopesSorted.length; ) {
                    List<Point> collinear = new ArrayList<>();
                    double slope = origin.slopeTo(slopesSorted[o]);

                        for (; o < slopesSorted.length && origin.slopeTo(slopesSorted[o]) == slope; o++) {
                            collinear.add(slopesSorted[o]);

                        }

                        if (collinear.size() >= 3) {
                        collinear.add(origin);
                        collinear.sort(null);
                        if (origin.compareTo(collinear.get(0)) == 0) {
                            segments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));
                        }
                    }
                }

            }
        }

        public int numberOfSegments() {return segments.size();}

        public LineSegment[] segments() {return segments.toArray(new LineSegment[0]);}

    }
