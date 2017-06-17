package coreLogic;

import java.lang.invoke.MethodHandles;
import org.apache.logging.log4j.*;


/**
 * The Class StopWatch.
 *
 * @author fps
 * Class for returning various timers.
 * All time handling done in System.nanoTime / Long type.
 */

public class StopWatch {
	
	/** The start time. */
	//main timer values
	private Long startTime;
	
	/** The end time. */
	private Long endTime;
	
	/** The log. */
	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	/**
	 * set class startTime to the current time.
	 */
	public void startTimer(){
		this.startTime = System.nanoTime();
		logger.info("StartTime:{}" + startTime);
	}
	
	/**
	 *Set class end time at this time. 
	 */
	public void timerStop(){
		
		Long endTimer = System.nanoTime();
		this.endTime = endTimer;
		//log.
		
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public Long getTime(){
		
		if(this.endTime != null){
			return this.endTime;		
		}
		else{
			return -1l;
		}
	}
	
	/**
	 * Show time as formatted string.
	 *
	 * @return the string
	 */
	public String showTime(){
		
		logger.info("Time:{}", this.endTime);
		return "TIME";
		
	}
	
	//Given known length of target and time to pass from 
	//stern to bow, find speed in m/s.
	
	/**
	 * Fixed wire.
	 *
	 * @param tgtLength the tgt length
	 * @param time the time elapsed for ship to pass from stern to bow
	 * @return the speed in kt
	 */
	public double fixedWire(Double tgtLength, long time){
		return 0;
	}
	
	/**
	 * Time to impact.
	 * Given known tgt range, and Torpedo Speed, find time to impact.
	 */
	//(TODO: calculate speed per gyro angle)
	public void timeToImpact(){}
	
	/**
	 * Speed to distance.
	 * Given known speed, and time variable, calculate distance travelled, in m.
	 */
	

	public double timeToIntercept(){
		return 0d;
	}
	
}
