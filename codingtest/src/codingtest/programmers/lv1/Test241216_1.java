package codingtest.programmers.lv1;

import java.util.Arrays;

// [PCCP 기출문제] 1번 / 동영상 재생기
// https://school.programmers.co.kr/learn/courses/30/lessons/340213?language=java
public class Test241216_1 {

	public static void main(String[] args) {		
		String answer = "";
		
		String video_len = "07:22";
		String pos = "04:05";
		String op_start = "00:15";
		String op_end = "04:07";
		String[] commands = {"next"};
		
		Time videoTime = new Time(video_len);
		Time opStartTime = new Time(op_start);
		Time opEndTime = new Time(op_end);
		Time posTime = new Time(pos);
		
		VideoPlayer player = new VideoPlayer(videoTime, posTime, opStartTime, opEndTime);
		player.checkTime();
		
		for(String command : commands) {
			System.out.print(player + " " + command);
			switch(command) {
				case "prev" :
					player.prev();
					break;
				case "next" :
					player.next();
					break;
			}
			System.out.println(" => 현재시각 : " + player);
		}
		
		System.out.println("최종 : " + player);
		
	}
}

class Time{
	private int min;
	private int sec;
	
	public Time() {
		this.min = 0;
		this.sec = 0;
	}
	
	public Time(String time) {
		int[] intArr = Arrays.stream(time.split(":"))
				.mapToInt(s -> Integer.parseInt(s))
				.toArray();
		this.min = intArr[0];
		this.sec = intArr[1];
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getSec() {
		return sec;
	}

	public void setSec(int sec) {
		this.sec = sec;
	}

	@Override
	public String toString() {
		return "Time [min=" + min + ", sec=" + sec + "]";
	}
}

class VideoPlayer extends Time {
	private int moveTime = 10;
	
	private Time videoTime;
	private Time posTime;
	private Time opStartTime;
	private Time opEndTime;
	
	public VideoPlayer(Time videoTime, Time posTime, Time opStartTime, Time opEndTime) {
		this.videoTime = videoTime;
		this.posTime = posTime;
		this.opStartTime = opStartTime;
		this.opEndTime = opEndTime;
	}
	
	public void checkTime() {
		// 현재 시점이 오프닝 시작 시간 후 인지 확인
		if(posTime.getMin() >= opStartTime.getMin() &&
		   posTime.getSec() >= opStartTime.getSec() ) {
			
			// 현재 시점이 오프닝 끝 시간 전 인지 확인
			if(posTime.getMin() <= opEndTime.getMin() &&
			   posTime.getSec() <= opEndTime.getSec()) {
				
				// 오프닝 시작시간과 끝 시간 사이일 경우, 오프닝 스킵
				// => 현재 시간을 오프닝 끝 시간으로 변경
				posTime.setMin(opEndTime.getMin());
				posTime.setSec(opEndTime.getSec());
				
			}
		}
		
		// 현재 시간이 비디오 시간을 초과했는지 확인
		if(posTime.getMin() >= videoTime.getMin() &&
		   posTime.getSec() >= videoTime.getSec()) {
			posTime.setMin(videoTime.getMin());
			posTime.setSec(videoTime.getSec());
		}
		
		// 현재 시간이 비디오 시간 미만인지 확인
		if(posTime.getMin() < 0) {
			posTime.setMin(0);
			posTime.setSec(0);
		}
	}
	
	public void next() {
		if(posTime.getSec() + moveTime <= 60) {
			posTime.setSec(posTime.getSec() + moveTime);
		} else {
			posTime.setMin(posTime.getMin() + 1);
			posTime.setSec(posTime.getSec() % moveTime);
		}
		checkTime();
	}
	
	public void prev() {
		if(posTime.getSec() - moveTime >= 0) {
			posTime.setSec(posTime.getSec() - moveTime);
		} else {
			posTime.setMin(posTime.getMin() - 1);
			posTime.setSec(60 - moveTime + posTime.getSec());
		}
		checkTime();
	}

	@Override
	public String toString() {
		return posTime.getMin() + ":" + posTime.getSec();
	}
}