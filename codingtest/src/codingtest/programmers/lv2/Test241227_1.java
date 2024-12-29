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
		int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}} ;
		int[][] routes = {{4, 2}, {1, 3}, {4, 2}, {4, 3}} ;
		
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
		
//		int index = 1;
		while(true) {
//			System.out.println("============"+ index +"번째 =============");
//			index ++;
//			
//			for(Robot robot : robots) {
//				System.out.println(robot);
//			}
			
			// 충돌 검사 ( 마지막으로 로봇이 보드를 벗어나기 전에도 검사해줘야 하므로, 우선적으로 검사한다. )
			boolean[] isChecked = new boolean[robots.length];
			for(int i=0; i < robots.length; i++) {
				for(int j=0; j < robots.length; j++) {
					
					if(i == j) continue; // 본인과 본인 충돌은 확인하지 않음
					if(robots[i].getX() < 0 || robots[j].getX() < 0) continue; // 로봇이 탐색을 마친 경우
					
					// 두 로봇의 좌표가 같은가 ? && 둘중 하나라도 충돌난 적이 없는가 ?
					if(robots[i].equals(robots[j]) && 
						(!isChecked[i] || !isChecked[j])) {
						
						// 둘다 충돌난 적이 없다면 횟수 증가 ( 한쪽이라도 충돌이 났다면 중복임 )
						if(!isChecked[i] && !isChecked[j]) {
//							System.out.println( (i+1) + "번 로봇과 " + (j+1) + "번 로봇 충돌 발생");
							result++;
						}
						
						// 충돌 표시
						isChecked[i] = true;
						isChecked[j] = true;
					
					}
				}
			}
			
//			System.out.println("\nResult : " + result + "\n");
			
			for(Robot robot : robots) {
				robot.move();
			}
			
//			System.out.println("\n움직인 후 로봇\n");
//			
//			for(Robot robot : robots) {
//				System.out.println(robot);
//			}
//			
//			System.out.println("==========================");
			
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
	private int x; // 로봇의 현재 x 좌표 ( 그림 상 세로(y) )
	private int y; // 로봇의 현재 y 좌표 ( 그림 상 가로(x) )
	private int[][] destinations; // destinations[N] 번쨰 목표지의 N[0] = x좌표 / N[1] = y좌표
	private int dIndex; // 현재 로봇이 몇번째 목적지로 이동하고 있는지
	
	public Robot(int[][] destinations) {
		this.x = destinations[0][0];
		this.y = destinations[0][1];
		this.destinations = destinations;
		this.dIndex = 1;
	}
	
	public void move() {
		// 보드를 벗어난 상태
		if(dIndex > destinations.length) {
			return;
		}
		
		// 마지막 목적지에 도착하고, 아직 보드를 벗어나지 않은 상태
		if(dIndex == destinations.length) {
			x = -1;
			y = -1;
			dIndex++;
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
		
		// 목적지에 도달했을 경우, dIndex 값을 destinations.length로 만들어 마지막 목적지에 도착한 상태임을 표시
		if(x == destinations[dIndex][0] && y == destinations[dIndex][1]) {
			dIndex++;
		}
	}
	
	public boolean isEnd() {
		// 보드를 벗어난 상태
		return dIndex > destinations.length;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		String dstToString = "";
		for(int[] destination : destinations) {
			dstToString += " [ " + destination[0] + "," + destination[1] + " ] ";
		}
		return "Robot [x=" + x + ", y=" + y + ", dIndex="+ dIndex +" , destination=" + dstToString + "]";
	}

	// 좌표가 같은지 확인
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