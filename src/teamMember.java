import java.awt.List;
import java.util.ArrayList;


public class teamMember {
	ArrayList<String []> teamMemberData = new ArrayList<String[]>();
	ArrayList<String> batches = new ArrayList <String>();
	ArrayList goalTotals = new ArrayList();
	ArrayList actualTotals = new ArrayList();
	int count;
	
	
	String batch;
	String teamMember;
	String teamMemberFirst;
	String teamMemberLast;
	int goalPullTime;
	int actualPullTime;
	String stringGoal;
	String stringActual;
	String [] dataLine = new String[11];
	boolean meatManual;
	
	public teamMember(ArrayList <String []> data,boolean meat){
		teamMemberData = data;
		dataLine = data.get(0);
		meatManual=meat;
		
	}

public int numberOfPulls(){
	return teamMemberData.size();
}
	
public void setTeamMember(){
	String tmf;
	String tml;

	tmf = teamMemberData.get(0)[9];
	tml = teamMemberData.get(0)[8];
	
	if(tmf.equalsIgnoreCase("ryan") && tml.equalsIgnoreCase("sc,")){
		teamMember = "Scram";
	}else if (tmf.equalsIgnoreCase("josh")&& tml.equalsIgnoreCase("tu,")){
		teamMember = "JT 'The Dollar Sign'";
	}else if (tmf.equalsIgnoreCase("William")&& tml.equalsIgnoreCase("ha,")){
		teamMember = "Trevor 'Sunshine'";
	}else if (tmf.equalsIgnoreCase("Stephen")&& tml.equalsIgnoreCase("pu,")){
		teamMember = "Bull-Jargon";
	}else if (tmf.equalsIgnoreCase("matt")&& tml.equalsIgnoreCase("la,")){
		teamMember = "Matt Larson";
	}else{
	teamMember =tmf+" "+tml;		
	}
	}
public String getTeamMember(){
	return teamMember;
	
}

public void setBatches(){
	count = 0;
	while(count<teamMemberData.size()){
	batches.add(teamMemberData.get(count)[0]);
		count++;
	}
}

public ArrayList <String> getBatches(){
	return batches;
}
public void getBatchesPrint(){
	count = 0;
	while(count<batches.size()){
		System.out.print(batches.get(count)+" ");
		count++;
	}
System.out.println();
}

public void setGoalTotal(){
	count = 0;
	int min;
	int hour;
	String [] str;
	String pt;
	while(count<teamMemberData.size()){
		pt=teamMemberData.get(count)[3];
		str = pt.split(":");
		hour=Integer.parseInt(str[0]);
		min=Integer.parseInt(str[1]);
		if(hour>0){
			min = (hour * 60)+min;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("pro1")){
			min+=20;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("pro2")){
			min+=1;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("mtcl")){
			min+=2;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("meat") && meatManual ==true){
			min +=10;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("deli") && meatManual ==true){
			min+=2;
		}
		goalTotals.add (min);
		count++;
	}
}
public int getGoalTotal(){
	int sum=0;
	int add;
	count = 0;
	while(count<goalTotals.size()){
		add = (Integer) goalTotals.get(count);
		sum +=add;
		count++;
	}
	
	return sum;
}

public void setActualTotal(){
	count = 0;
	int min;
	int hour;
	String [] str;
	String pt;
	while(count<teamMemberData.size()){
		pt=teamMemberData.get(count)[7];
		str = pt.split(":");
		hour=Integer.parseInt(str[0]);
		min=Integer.parseInt(str[1]);
		if(hour>0){
			min = (hour * 60)+min;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("pro1")){
			min+=7;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("pro2")){
			min+=1;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("mtcl")){
			min+=1;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("meat") && meatManual ==true){
			min +=5;
		}
		if(teamMemberData.get(count)[0].equalsIgnoreCase("deli") && meatManual ==true){
			min+=1;
		}
		actualTotals.add (min);
		count++;
	}
}
public int getActualTotal(){
	int sum=0;
	count = 0;
	while(count<actualTotals.size()){
		sum += (int)actualTotals.get(count);
		count++;
	}
	
	return sum;
}
public int getMin(String pt){
//sets pull time in minutes	
	String [] str;
	int min;
	int hour;
	str = pt.split(":");
	hour=Integer.parseInt(str[0]);
	min=Integer.parseInt(str[1]);
	if(hour>0){
		min = (hour * 60)+min;
	}
	
	goalPullTime=min;
	return goalPullTime;
}

public String getGoalTimeFormat(String ft){
	getMin(ft);
	String returnString;
	if(goalPullTime<10){
		returnString ="0"+goalPullTime/60+":"+"0"+goalPullTime%60;
	}else{
	returnString ="0"+goalPullTime/60+":"+goalPullTime%60;
}
return returnString;
}

public void printRawData(){
	int i = 0;
	System.out.println(" 	   Goal          Actual");
	while(i<teamMemberData.size()){
		for(int j=0;j<teamMemberData.get(i).length-2;j++){
			System.out.print(teamMemberData.get(i)[j]+" ");
		}
	System.out.println();
	i++;
	}


}

public int getBatchNumber(){
	return batches.size();
}
}


