import java.io.*;
import java.util.*;

class Classes {

    public int total_instances;
    public double tp;
    public double fp;
    public double fn;

    public Classes() {
        tp = 0.0;
        fp = 0.0;
        fn = 0.0;
        total_instances = 0;
    }

    public void updateInstances() {
        total_instances++;
    }

    public void update(double t, double f, double n) {
        tp += t;
        fp += f;
        fn += n;
    }

    public double cal_precision() {
        if ((tp + fp) != 0.0) {
            double p = tp / (tp + fp);
            return p;
        } else {
            return 0.0;
        }
    }

    public double cal_recall() {
        double r = tp / (tp + fn);
        return r;
    }
    
    public int getSize(){
        return total_instances;
    }
}