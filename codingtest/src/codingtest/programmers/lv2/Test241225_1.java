package codingtest.programmers.lv2;

// [PCCP 기출문제] 2번 / 퍼즐 게임 챌린지
// https://school.programmers.co.kr/learn/courses/30/lessons/340212
public class Test241225_1 {
	public static void main(String[] args) {
		int[] diffs = {1, 99999, 100000, 99995};
		int[] times = {9999, 9001, 9999, 9001};
		long limit = 3456789012L;
		
		int MAX_LEVEL = 100000;
		int level = 1;
		int time_prev = 0;
		long totalTime = 0;
		
		// 근사치 찾기
		while(true) {
			for(int pNum = 0; pNum < diffs.length; pNum++) {
				if(diffs[pNum] <= level) {
					totalTime += times[pNum];
				} else {
					totalTime += (long) (diffs[pNum] - level) * (times[pNum] + time_prev) 
							+ times[pNum];
				}
				
				time_prev = times[pNum];
				
				if(totalTime > limit) {
					level++;
					totalTime = 0;
					time_prev = 0;
					break;
				}
			}
			if(totalTime != 0) break;
		}
		
		System.out.println(level);
	}
}
