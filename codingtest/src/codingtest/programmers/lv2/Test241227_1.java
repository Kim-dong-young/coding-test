package codingtest.programmers.lv2;

// [PCCP 기출문제] 3번 / 충돌위험 찾기

// https://school.programmers.co.kr/learn/courses/30/lessons/340211
public class Test241227_1 {
	public static void main(String[] args) {
		
		int[][] points = {{3,2},{6,4},{4,7},{1,4}};
		int[][] routes = {{4,2},{1,3},{2,4}};
		
		Robot[] robots = new Robot[routes.length];
		
		// 항상 최단 경로로 이동, y 좌표 우선 이동 후, x 좌표 이동
		// => 이동하는 로봇 객체 만들어보자
		for(int i=0; i < routes.length; i++) {
			int[][] destinations = new int[routes[0].length][2];
			
			for(int j=0; j < routes[0].length; j++) {
				destinations[j] = points[routes[i][j]-1];
			}
			
			robots[i] = new Robot(destinations);
		}
		
		for(Robot robot : robots) {
			System.out.println(robot);
		}
	}
}

class Robot{
	private int x;
	private int y;
	private int[][] destinations;
	
	public Robot(int[][] destinations) {
		this.x = destinations[0][0];
		this.y = destinations[0][1];
		this.destinations = destinations;
	}

	@Override
	public String toString() {
		String dstToString = "";
		for(int[] destination : destinations) {
			dstToString += " [ " + destination[0] + "," + destination[1] + " ] ";
		}
		return "Robot [x=" + x + ", y=" + y + ", destination=" + dstToString + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Robot) {
			Robot other = (Robot)obj;
			if(this.x == other.x && this.y == other.y)
				return true;
		}
		return false;
	}

}