import tw.StdDraw;
/*
A Bird in Flight by Hamid Naderi Yeganeh in my interpretation.
 */
public class Bird {
    public static void main(String[] args) {
        StdDraw.setXscale(-30000, 30000);
        StdDraw.setYscale(-30000, 30000);
        StdDraw.setPenRadius(0.00001);
        for (int k = -20000; k <= 0; k++) {
            double dividedK = k * 0.00005;
            double PIk = Math.PI * dividedK;
            double a = A(dividedK, PIk) * 10000;
            double b = B(dividedK, PIk) * 10000;
            double bN = B(-dividedK, -PIk) * 10000;
            double r = R(PIk) * 10000;
            StdDraw.circle(a, b, r);
            StdDraw.circle(-a, bN, r);
        }
    }
    static double A(double k, double PIk){
        double s1 = 4 * k / 3;
        double s21 = Math.sin(Math.pow(k, 5) * Math.PI * 0.85);
        double m = Math.cos(41 * PIk);
        double s2 = Math.pow(m, 6) * s21;
        double s31 = (Math.pow(m, 16) + Math.pow(m, 80)) / 3;
        double s32 = Math.pow(Math.cos(PIk / 2), 12);
        double s33 = Math.sin(6 * PIk);
        double s3 = s31 * s32 * s33;
        return s1 + s2 + s3;
    }

    static double B(double k, double PIk){
        double s1 = Math.pow(k, 4) * 0.5;
        double s21 = Math.cos(Math.pow(k, 5) * Math.PI * 0.85);
        double s221 = Math.pow(Math.cos(PIk / 2), 8);
        double s222 = Math.pow(Math.cos(PIk * 1.5), 6);
        double s22 = 1.1 + ((45 * s221 * s222) / 20);
        double s23 = Math.pow(Math.cos(41 * PIk), 6);
        double s2 = s21 * s22 * s23;
        double s31 = Math.pow(Math.cos(0.3 * PIk), 10);
        double s32 = Math.pow(Math.cos(0.9 * PIk), 10);
        double s33 = Math.pow(Math.cos(1.8 * PIk), 10);
        double s3 = 0.6 * s31 * s32 * s33;
        return s1 - s2 + s3;
    }

    static double R(double PIk){
        double m = 41 * PIk;
        double s11 = Math.pow(Math.sin(m), 2);
        double s12 = Math.pow(Math.sin(0.9 * PIk), 2);
        double s1 = (s11 * s12) / 40;
        double s21 = Math.pow(Math.cos(m), 2);
        double s22 = Math.pow(Math.cos(PIk/2), 10);
        double s2 = (s21 * s22) / 17;
        return 0.02 + s1 + s2;
    }
}
