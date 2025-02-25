/*
 *   A program that examines 4 points at a time
 *   and checks whether they all lie on the same line segment,
 *   returning all such line segments.
 *   To check whether the 4 points p, q, r, and s are collinear,
 *   check whether the three slopes between p and q,
 *   between p and r, and between p and s are all equal.
 */
import java.util.ArrayList;
import edu.princeton.cs.algs4.*;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegments = new ArrayList<>();

    public BruteCollinearPoints(Point[] points) {

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];

                        double slopePQ = p.slopeTo(q);
                        double slopePR = p.slopeTo(r);
                        double slopePS = p.slopeTo(s);

                        if (slopePQ == slopePR && slopePQ == slopePS) {
                            Point[] collinearPoints = { p, q, r, s };
                            java.util.Arrays.sort(collinearPoints);
                            lineSegments.add(new LineSegment(collinearPoints[0], collinearPoints[3]));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }
}

