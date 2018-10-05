package coreLogic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;

/**
 * @author fps
 * <p>
 * Method for determining the AOB based on an observed and reference
 * aspect ratio. Note, this method requires an approximate visual AOB or
 * it will return wildly inaccurate figures, because that's how
 * trigonometry works.
 */
public class AspectAOB {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Wrapper for calculateAOB, calculates AOB bearing with AOB estimate
     * checking against a visual estimate of AOB due to the limits of this
     * method. Errors: -1: NaN found in observed AR -2: Observed aspect > Ref
     * Aspect
     *
     * @param estAOB
     * @param target
     * @param mastObs
     * @param lenObs
     * @return
     */
    public double aspectAOB(int estAOB, coreLogic.TgtShip target, double mastObs, double lenObs) {
        double obsAR = 0;
        // Check if it's a number. It's possible that NaN will be input by user.
        if (!Double.isNaN((lenObs / mastObs))) {
            obsAR = lenObs / mastObs;
        } else {
            return -1;
        }

        double AOB = 0;
        double aspAOB = calculateAOB(target, obsAR);

        if (estAOB >= 0 && estAOB <= 90) {
            AOB = aspAOB;
        } else if (estAOB > 90 && estAOB <= 180) {
            AOB = 180 - (aspAOB);
        } else if (estAOB > 180 && estAOB <= 270) {
            AOB = 360 - (180 - (aspAOB));
        } else if (estAOB > 270 && estAOB < 360) {
            AOB = 360 - (aspAOB);
        }
        return AOB;
    }

    /**
     * Calculates the AOB, does not account for front/back or port/stbd
     *
     * @param target
     * @param obsAR
     * @return
     */
    private double calculateAOB(TgtShip target, double obsAR) {
        // Reference Aspect Ratio, from Ship data
        double refAR = target.getRefAspect();
        double AOB = Math.toDegrees(Math.asin(obsAR / refAR));
        if (!Double.isNaN(AOB)) {
            return AOB;
        } else {
            return -2;
        }

    }

}
