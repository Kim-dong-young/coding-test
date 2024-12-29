package codingtest.programmers.lv2;

// [PCCP 기출문제] 3번 / 충돌위험 찾기
// https://school.programmers.co.kr/learn/courses/30/lessons/340211
public class Test241227_1 {
	public static void main(String[] args) {
		/*
			{{3,2},{6,4},{4,7},{1,4}}
			{{4,2},{1,3},{2,4}}
			
			{{3, 2}, {6, 4}, {4, 7}, {1, 4}} 
			{{4, 2}, {1, 3}, {4, 2}, {4, 3}} 

			{{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}} 
			{{2, 3, 4, 5}, {1, 3, 4, 5}}
			
			{{1, 1}, {2, 2}, {3, 3}}
			{{1, 2, 1}, {3, 2, 1}}
		 */
		int[][] points = {{1, 1}, {2, 2}, {3, 3}};
		int[][] routes = {{1, 2, 1}, {3, 2, 1}};
		
		int result = 0;
		
		Robot[] robots = new Robot[routes.length];
		
		// => 이동하는 로봇 객체 만들어보자
		// 로봇에게 목적지 좌표 입력 ( routes 에 있는 points 번호를 좌표로 변환 )
		for(int i=0; i < routes.length; i++) {
			int[][] destinations = new int[routes[0].length][2];
			
			for(int j=0; j < routes[0].length; j++) {
				destinations[j] = points[routes[i][j]-1];
			}
			
			robots[i] = new Robot(destinations);
		}
		
		int index = 1;
		while(true) {
			System.out.println("============"+ index +"번째 =============");
			index ++;
			
			for(Robot robot : robots) {
				System.out.println(robot);
			}
			
			// 충돌 검사
			boolean[] isChecked = new boolean[robots.length];
			for(int i=0; i < robots.length; i++) {
				for(int j=0; j < robots.length; j++) {
					
					if(i == j) continue;
					
					if(robots[i].equals(robots[j]) && 
						!(isChecked[i] || isChecked[j])) {
						
						if(!isChecked[i] && !isChecked[j]) {
							System.out.println( (i+1) + "번 로봇과 " + (j+1) + "번 로봇 충돌 발생");
							result++;
						}
						
						isChecked[i] = true;
						isChecked[j] = true;
					
					}
				}
			}
			
			System.out.println("\nResult : " + result + "\n");
			
			for(Robot robot : robots) {
				robot.move();
			}
			
			System.out.println("\n움직인 후 로봇\n");
			
			for(Robot robot : robots) {
				System.out.println(robot);
			}
			
			System.out.println("==========================");
			
			boolean isAllFinish = true;
			for(Robot robot : robots) {
				if(!robot.isEnd()) {
					isAllFinish = false;
					break;
				}
			}
			
			if(isAllFinish) break;
		}
		
		System.out.println(result);
	}
}

class Robot{
	private int x;
	private int y;
	private int[][] destinations;
	private int dIndex;
	
	public Robot(int[][] destinations) {
		this.x = destinations[0][0];
		this.y = destinations[0][1];
		this.destinations = destinations;
		this.dIndex = 1;
	}
	
	public void move() {
		if(dIndex >= destinations.length) {
			x = -1;
			y = -1;
			return;
		}
		
		// 항상 최단 경로로 이동, y 좌표 우선 이동 후, x 좌표 이동
		if(x != destinations[dIndex][0]) {
			if(x < destinations[dIndex][0]) x++;
			else if(x > destinations[dIndex][0]) x--;
		}
		else if(y != destinations[dIndex][1]) {
			if(y < destinations[dIndex][1]) y++;
			else if(y > destinations[dIndex][1]) y--;
		}
		
		if(x == destinations[dIndex][0] && y == destinations[dIndex][1]) {
			dIndex++;
		}
	}
	
	public boolean isEnd() {
		return dIndex >= destinations.length;
	}

	@Override
	public String toString() {
		String dstToString = "";
		for(int[] destination : destinations) {
			dstToString += " [ " + destination[0] + "," + destination[1] + " ] ";
		}
		return "Robot [x=" + x + ", y=" + y + ", dIndex="+ dIndex +" , destination=" + dstToString + "]";
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