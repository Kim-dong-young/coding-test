package codingtest.programmers.lv1;

public class Test241219_1 {
	public static void main(String[] args) {
		int answer = 0;
		
		int[] wallet = { 50, 50 };
		int[] bill = { 100, 241 };
		
		while(true){
            if( bill[0] > wallet[0] || bill[1] > wallet[1] ){
                if( bill[1] > wallet[0] || bill [0] > wallet[1] ) {
                    int maxBill = bill[0] > bill[1] ? bill[0] : bill[1];
                    for(int i = 0; i < bill.length ; i++){
                        if(bill[i] == maxBill) {
                            bill[i] /= 2;
                        }
                    }
                    answer++;
                }
                else {
                    break;
                }
            } else {
                break;
            }
        }
		
		System.out.println(answer);
	}
}
