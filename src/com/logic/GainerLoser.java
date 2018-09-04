package com.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.trade.ShareDetail;
import com.trade.ShareInfo;

public class GainerLoser {
	
	public List<Integer> comparison(List<ShareInfo> share,List<ShareDetail> sharedetail) {
	
		List<Integer> ans=new ArrayList<Integer>();
		List<BigDecimal> difference = new ArrayList<BigDecimal>();
		List<BigDecimal> difference1 = new ArrayList<BigDecimal>();
		System.out.println(share.size()+""+sharedetail.size());
		System.out.println(share.get(0).getPriceOfSecurity());
		System.out.println(sharedetail.get(0).getOpen());
		for(int i=0;i<7;i++) {
			System.out.println(i);
			System.out.println(share.get(i).getSecurityName()+"  "+sharedetail.get(i).getSecurityName());
			System.out.println();
			BigDecimal diff=share.get(i).getPriceOfSecurity().subtract(sharedetail.get(i).getOpen());
			difference.add(diff);
			difference1.add(diff);
		}
		Collections.sort(difference);
		for(int i=0;i<difference.size();i++) {
			if(difference.get(difference.size()-1)==difference1.get(i)) {
				ans.add(i);
				break;
			}
		}
		for(int i=0;i<difference.size();i++) {
			if(difference.get(difference.size()-2)==difference1.get(i)) {
				ans.add(i);
				break;
			}
		}
		
		for(int i=0;i<difference.size();i++) {
			if(difference.get(0)==difference1.get(i)) {
				ans.add(i);
				break;
			}
		}
		for(int i=0;i<difference.size();i++) {
			if(difference.get(1)==difference1.get(i)) {
				ans.add(i);
				break;
			}
		}
		
		
		return ans;
	}
	
	public BigDecimal calculatePercentage(BigDecimal start, BigDecimal current) {
		BigDecimal difference;
		BigDecimal differencePercentage;
		BigDecimal hundred=new BigDecimal(100);
		difference=current.subtract(start);
	//differencePercentage=(difference.divide(current,2, RoundingMode.HALF_UP)).multiply(hundred);
    return difference;
	}

}
