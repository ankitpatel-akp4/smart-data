package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		// TODO
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		// TODO
		return new MemberImporterImpl();
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {

		// TODO
		
		Set<String> memberIds = new HashSet<>();
		List<Member> nonDuplicateMembers = new ArrayList<>();
		
		for(Member member : membersFromFile) {
			
			if(!memberIds.contains(member.getId())) {
				nonDuplicateMembers.add(member);
			}
			
		}
		
		return nonDuplicateMembers;
	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		// TODO
		
		Map< String, List< Member >> stateMembersMap = new HashMap<>();
		
		for(Member member: validMembers) {
			if( stateMembersMap.containsKey(member.getState()) ) {
				stateMembersMap.get(member.getState()).add(member);
			}else {
				stateMembersMap.put(member.getState(), new ArrayList<>());
				stateMembersMap.get(member.getState()).add(member);
			}
		}
		
		return stateMembersMap;
	}

	public static void main( String[] args ) {
		//TODO
		Main main = new Main();
		
		String currentDir = System.getProperty("user.dir");
		File inputFile = new File(currentDir+File.separator+"Members.txt");
		
//		outout dir
		String outputDir = currentDir+File.separator+"output";
		File outoutDir = new File(outputDir);
		if(!outoutDir.exists()) {
			outoutDir.mkdir();
		}
		
		try {
			main.convert(inputFile, outputDir, "outputFile.csv");
			System.out.println("output dir: "+outputDir+"\ndone!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
