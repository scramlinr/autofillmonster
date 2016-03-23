import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Main {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	
	
	public static void main(String[] args) throws IOException {
		//Auto Fill program main
	
		String str[];
		ArrayList<String> dataArray = new ArrayList<String>();			//Holds File string after initial split
		ArrayList<String[]> allLines = new ArrayList<String[]>();		//Holds all string dtat
		ArrayList<teamMember> allMembers = new ArrayList<teamMember>();	//ArrayList holds all teamMember objects
		ArrayList<ArrayList<String[]>> listSort = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> holdList = new ArrayList<String[]>();		//Place holder to hold teamMember <String []>
		String file = readFile();										//TXT file as a string
		Scanner keyboard = new Scanner (System.in);
		boolean meatManual = false;
		String meat;
		
	//	getFile();
		str = file.split("\n");
		dataArray = splitData(str);
		allLines = splitList(dataArray);
		meat = JOptionPane.showInputDialog("Were MEAT Manuals Pulled Today (y/n)? ");
		if (meat.equalsIgnoreCase("y")){
			meatManual = true;
		}else{
			meatManual = false;
		}
		
		while (allLines.size() != 0) {
			holdList = sortList(allLines);
			listSort.add(holdList);
		}

		allMembers = createMembers(listSort,meatManual);
		writeFile(allMembers);
		JOptionPane.showMessageDialog(null, "Your File has been generated");
//		printTeamTotals(allMembers);
//		System.out.println();
//		System.out.println();
//		System.out.println("---------------------------------------------------------------------------------------------");
//		printTMSummary(allMembers);

	}

	public static void checkHalfTime(int actual, int goal) {
		boolean halfTime;

		if (actual < (goal / 2)) {
			halfTime = true;
		} else {
			halfTime = false;
		}
	//	System.out.println(halfTime);
		if (halfTime == true) {
			System.out.println("Pull was under half by "
					+ ((goal / 2) - actual + " min"));
		} else {
			System.out.println("Pull was over half by "
					+ (actual - (goal / 2) + " min"));
		}
	}

	public static void setAll(ArrayList <teamMember> team){
		int count=0;
		
		while(count<team.size()){
			team.get(count).setTeamMember();
			team.get(count).setBatches();
			team.get(count).setGoalTotal();
			team.get(count).setActualTotal();
			count++;
		}
	}
	
	public static void printTMSummary(ArrayList<teamMember> team) {
		int count = 0;
		DecimalFormat df = new DecimalFormat("#.00");
		
		while (count < team.size()) {
			System.out.println();
			System.out.println("TM: " +team.get(count).getTeamMember());
			System.out.println();
			System.out.println("Number of Batches pulled: "+team.get(count).numberOfPulls());
			System.out.print("Goal Total: " + team.get(count).getGoalTotal()/60
					+ " Hour/s "+team.get(count).getGoalTotal()%60+ " Minutes");
			System.out.print("		");
			System.out.print("Actual Total: "
					+ team.get(count).getActualTotal()/60 + " Hours "+team.get(count).getActualTotal()%60+" Minutes");
			System.out.print(" 		");
			checkHalfTime(team.get(count).getActualTotal(), team.get(count)
					.getGoalTotal());
			System.out.println("PercentPulled: "+df.format(getPercentage(team,count))+"%");
			System.out.println();
			team.get(count).printRawData();
			
			
			System.out
					.println("---------------------------------------------------------------------------------------------------------------");
			
			count++;
		}

	}

	public static double getPercentage(ArrayList<teamMember> team,int element) {
		double percent = 0;
		double teamTotal=0;
		int count = 0;

		while (count < team.size()) {
			teamTotal = (teamTotal + team.get(count).getGoalTotal());
			count++;
			
		}
			percent =  ( team.get(element).getGoalTotal() / teamTotal)*100;

	return percent;
	}

	
	private static ArrayList<String[]> splitList(ArrayList l) {
		
		ArrayList<String[]> lineData = new ArrayList<String[]>();
		String [] list;		// original
		String [] listClone = new String [10];
		
		int i = 0;
		
		while (i < l.size()) {
			list = (l.get(i).toString().split(" "));
			if (list.length < 9){
				listClone = mDelete(list);
				lineData.add(listClone);
				i++;
			}else if(list.length >10){
				listClone = multiPull(list);
				lineData.add(listClone);
				i++;
			}else{
			lineData.add(list);
			i++;

			}
		}
		return lineData;
	}


	public static ArrayList<String[]> sortList(ArrayList<String[]> dataList) {
		ArrayList<String[]> sorted = new ArrayList<String[]>();
		sorted.add(dataList.get(0));
		int i = 1;

		while (i < dataList.size()) {
			
			if (dataList.get(0)[9].equalsIgnoreCase(dataList.get(i)[9])) {
				sorted.add(dataList.get(i));
				dataList.remove(i);
			} else {
				i++;
			}
		}
		dataList.remove(0);

		return sorted;

	}

	private static ArrayList<String> splitData(String[] str) {
		ArrayList<String> split = new ArrayList<String>();
		int i = 0;

		while (i < str.length) {
			split.add(str[i]);
			i++;
		}
		i = 0;
		while (i < split.size()) {
			i++;
		}

		return split;
	}

	public static String readFile() {
		String fileToString = "George";
		String filePath;
		
		try {
			File file = getFile();
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();

			fileToString = stringBuffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return fileToString;
	}

	public static ArrayList<teamMember> createMembers(
			ArrayList<ArrayList<String[]>> list, boolean meat) {
		ArrayList<teamMember> memberList = new ArrayList<teamMember>();
		int i = 0;

		while (i < list.size()) {
			memberList.add(new teamMember(list.get(i), meat));
			i++;
		}
		return memberList;

	}

public static String [] mDelete (String [] list){
	ArrayList <String> reSize = new ArrayList <String>();
	int i = 0;
	String [] listClone = new String [10];
	
	
	while(i < list.length){
	reSize.add(list[i]);	
	i++;
	}
	
	i=0;
	reSize.add("DELETE");
	reSize.add("M");
	
	while(i<reSize.size()){
		listClone [i] = reSize.get(i);
		i++;
	}
	
	return listClone;
}

public static String [] multiPull (String [] list){
	
	ArrayList <String> reSize = new ArrayList <String>();
	int i = 0;
	String [] listClone = new String [10];
	
	
	while(i < list.length){
	reSize.add(list[i]);	
	i++;
	}
	i = 0;
	while(i<listClone.length-1){
		listClone [i] = reSize.get(0);
		reSize.remove(0);
		i++;
	}
	i = 0;
	
	while (i < reSize.size()){
		listClone [8] = listClone [8]+" "+ reSize.get(i);
		i++;
	}
	System.out.println("Beaks: "+listClone.length);
	listClone [9] = "Muliple Pullers: ";
	return listClone;
}

public static void printTeamTotals(ArrayList <teamMember> team){
	ArrayList <String> percentage = new ArrayList <String> ();
	int teamTotalTime = 0;
	int teamActualTime = 0;
	int i = 0;
    DecimalFormat df = new DecimalFormat("#.00");
    int batchTotal = 0;

	setAll(team);
	while(i < team.size()){
		teamTotalTime += team.get(i).getGoalTotal();
		teamActualTime += team.get(i).getActualTotal();
		batchTotal += team.get(i).getBatchNumber();
		percentage.add(df.format(getPercentage(team,i))+"%"+" "+team.get(i).getTeamMember());
		i++;
	}
	Collections.sort(percentage);
	Collections.reverse(percentage);
	i=0;
	
	System.out.println("Team Auto Fill Summary");
	System.out.println("Number of Batches Pulled: "+batchTotal);
	System.out.println("Total Goal Time: "+teamTotalTime/60+" Hours and "+teamTotalTime%60+" Minutes");
	System.out.println("Total Actual Time: "+teamActualTime/60+" Hours and "+teamActualTime%60+" Minutes");
	checkHalfTime(teamActualTime, teamTotalTime);
	System.out.println("Percentage Pulled by Team Members");
	System.out.println();
while(i < percentage.size()){
	if(i==0){
	System.out.print(percentage.get(i)+ " <--------- Top Puller");
	System.out.println();
	}else{
	System.out.println(percentage.get(i));
	}
	System.out.println();
	i++;
}
}


public static void writeFile(ArrayList <teamMember> team) throws IOException {
Scanner keyboard = new Scanner (System.in);
String filePath;
System.out.println("File");
filePath = keyboard.nextLine();

	File file = new File (filePath);
	if (!file.exists()) {
		file.createNewFile();
	}
	
	FileOutputStream fis = new FileOutputStream(file);
	PrintStream out = new PrintStream(fis);
	System.setOut(out);
	printTeamTotals(team);
	System.out.println();
	System.out.println();
	System.out.println("---------------------------------------------------------------------------------------------");
	printTMSummary(team);
 
	fis.close();
}
public static File getFile() throws IOException{
	
	String fileToString = "George";
	String filePath;
	String fileName;
	Scanner keyboard = new Scanner (System.in);
	
	System.out.println("Specify File Path: ");
	fileName = keyboard.nextLine();
		File file = new File (fileName);


//	System.out.println(fileToString);
	return file;
 
}

public static File saveFile() throws IOException{
	Scanner keyboard = new Scanner (System.in);
	String filePath;
	
	System.out.println("File Path");
	filePath = keyboard.nextLine();
	
	
		 
		String content = "This is the content to write into file";

		File file = new File(filePath);



		return file;
}
}

