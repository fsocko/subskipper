package coreLogic;

import org.apache.logging.log4j.*;

/**
 * @author fps
 * Class for returning various timers.
 * All time handling done in System.nanoTime / Long type.
 * 
 */

public class StopWatch {
	//main timer values
	private Long startTime;
	private Long endTime;
	final Logger log = LogManager.getLogger(this.getClass());
	
	/**
	 * set class startTime to the current time.
	 */
	public void startTimer(){
		this.startTime = System.nanoTime();
		log.info("StartTime:{}" + startTime);
	}
	
	/**
	 *Set class end time at this time. 
	 */
	public void timerStop(){
		
		Long endTimer = System.nanoTime();
		this.endTime = endTimer;
		//log.
		
	}
	
	public Long getTime(){
		
		if(this.endTime != null){
			return this.endTime;		
		}
		else{
			return -1l;
		}
	}
	
	public String showTime(){
		
		log.info("Time:{}", this.endTime);
		return "TIME";
		
	}
	
	//Given known length of target and time to pass from 
	//stern to bow, find speed in m/s.
	
	public double fixedWire(Double tgtLength, long time){
		
		return 0;
		
	}
	
	//Given known range and Torpedo Speed, find time. 
	//(TODO: calculate speed per gyro angle)
	public void timeToImpact(){}
	
	//Given known speed, and time variable, calculate distance travelled, in m.
	public void SpeedToDistance(){}

}
