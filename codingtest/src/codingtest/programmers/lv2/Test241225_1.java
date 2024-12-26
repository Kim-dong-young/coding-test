package codingtest.programmers.lv2;

// [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지
// https://school.programmers.co.kr/learn/courses/30/lessons/340212
public class Test241225_1 {
	
	public static void main(String[] args) {
		int[] diffs = {1, 99999, 100000, 99995};
		int[] times = {9999, 9001, 9999, 9001};
		long limit = 3456789012L;
		
		int max = 100000;
		int min = 1;
		
		// 근사치 찾기
		while(min < max) {
			int level = (min + max) / 2;
			
			if(isSolveInTime(diffs, times, limit, level)) {
				max = level;
			} else {
				min = level + 1;
			}
		}
		
		System.out.println(max);
	}
	
	public static boolean isSolveInTime(int[] diffs, int[] times, long limit, int level) {
		int time_prev = 0;
		long totalTime = 0;
		
		for(int pNum = 0; pNum < diffs.length; pNum++) {
			if(diffs[pNum] <= level) {
				totalTime += times[pNum];
			} else {
				totalTime += (long) (diffs[pNum] - level) * (times[pNum] + time_prev) 
						+ times[pNum];
			}
			
			time_prev = times[pNum];
			
			if(totalTime > limit) {
				return false;
			}
		}
		
		return true;
	}
	
}
