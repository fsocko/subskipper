package coreLogic;

import java.lang.invoke.MethodHandles;

import org.apache.logging.log4j.*;

/**
 * The Class Nomo. Nomograph Logic: Inputs: any two of: speed , distance, time
 * Returns a string which contains the missing value. Missing value must be a
 * double, size 0.
 * 
 * NOTE: as long as distance and speed corespond to speed: e.g. speed = m/s,
 * d=m, t=s then units do not matter. Really though, it should probably be m/s.
 */

public final class Nomo {

	/** The logger. */
	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	private Long speed = 0l;
	private Long dist = 0l;
	private Long time = 0l;
	private boolean isValid = false;

	/**
	 * Default constructor converts to longs, just so I don't need to convert
	 * every time. Output doesn't need to be *THAT* accurate anyway and it stops
	 * rounding errors.
	 *
	 * @param speed
	 *            the speed
	 * @param dist
	 *            the dist
	 * @param time
	 *            the time
	 */
	public Nomo(Double s, Double d, Long t) {
		logger.error("Double nomo input not implemented.");
	}

	/**
	 * Instantiates a new nomo.
	 *
	 * @param speed
	 *            the speed
	 * @param dist
	 *            the dist
	 * @param time
	 *            the time
	 */
	public Nomo(Long s, Long d, Long t) {
		
		int valCheck = 0; // this will add up to known numbers given

		if ((speed >= 0) && speed != null) {
			this.speed = s;
			valCheck += 2;
		}
		if ((dist >= 0) && dist != null) {
			this.dist = d;
			valCheck += 3;
		}
		if ((time >= 0) && time != null) {
			this.time = t;
			valCheck += 6;
		}
		
		//I.e., if Nomo has 2 params.
		if (valCheck != 9 || valCheck != 5 || valCheck != 8) {
			isValid = false;
			logger.error("Invalid Nomo: s{}, d{}, t{}", this.getSpeed(), this.getDist(), this.getTime());
		} 
		
		else {
			// s = d/t
			if (valCheck == 9) {
				this.speed = (this.dist / this.time);
				isValid = true;
				logger.info("Valid Nomo; speed set to:{}", this.getSpeed());
			}

			// t = d/s
			if (valCheck == 5) {
				this.time = (this.dist / this.speed);
				isValid = true;
				logger.info("Valid Nomo; time set to:{}", this.getTime());
			}

			// d = st
			if (valCheck == 8) {
				this.dist = (this.speed * this.time);
				isValid = true;
				logger.info("Valid Nomo; speed set to:{}", this.getSpeed());
			}

		}
	}
	

	@Override
	public String toString() {
		return "Nomo [ isValid=" + isValid + ", speed=" + speed + ", dist=" + dist + ", time=" + time + "]";
	}

	public Long getSpeed() {
		return speed;
	}


	public Long getDist() {
		return dist;
	}

	public Long getTime() {
		return time;
	}


	public boolean getIsValid() {
		return isValid;
	}

}
