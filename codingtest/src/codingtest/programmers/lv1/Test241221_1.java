package codingtest.programmers.lv1;

import java.util.ArrayList;
import java.util.Arrays;

// [PCCE 기출문제] 10번 / 데이터 분석
// https://school.programmers.co.kr/learn/courses/30/lessons/250121
public class Test241221_1 {

	public static void main(String[] args) {
		int[][] data = {{1, 20300104, 100, 80}, 
				{2, 20300804, 847, 37}, 
				{3, 20300401, 10, 8}};
		
		String ext = "date";
		int val_ext = 20300501;
		String sort_by = "remain";
		
		int[][] answer = {};
        
        int extNo = 0;
        switch(ext){
            case "code":
                extNo = 0;
                break;
            case "date":
                extNo = 1;
                break;
            case "maximum":
                extNo = 2;
                break;
            case "remain":
                extNo = 3;
                break;
        }
        
        int sortNo = 0;
        switch(sort_by){
            case "code":
                sortNo = 0;
                break;
            case "date":
                sortNo = 1;
                break;
            case "maximum":
                sortNo = 2;
                break;
            case "remain":
                sortNo = 3;
                break;
        }
        
        ArrayList<int[]> dataList = new ArrayList<>();
        
        for(int[] product : data){
            if(product[extNo] < val_ext){
            	dataList.add(product);
            }
        }
        
        for(int i=1; i < dataList.size(); i++) {
        	for(int j=0; j < i; j++) {
            	if(dataList.get(i)[sortNo] < dataList.get(j)[sortNo]) {
            		dataList.add(j, dataList.get(i));
            		dataList.remove(i+1);
            	}
        	}
        }
        
        for(int[] i : dataList) {
        	System.out.println(Arrays.toString(i));
        }
	}

}
