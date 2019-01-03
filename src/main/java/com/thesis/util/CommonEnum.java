package com.thesis.util;

public class CommonEnum {
	
	
	public enum Gender{  
		Male(1,"Male"),Female(2,"Female"); 

		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		Gender(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}  
	} 
	
	public enum Role{  
		
		adminRole(2,"Admin Role"),userRole(3,"User Role"); 

		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		Role(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}  
	} 
	
	public enum UserStatus{  
		
		active(1,"Active"),delete(0,"Not Active"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		UserStatus(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}  
	}
	
public enum SchoolStatus{  
		
		active(1,"Active"),delete(0,"Not Active"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		SchoolStatus(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}  
	}

public enum SchoolOpenClose{  
	
	open(1,"Open"),close(0,"Close"); 
	private final int value;  
	private final String description;        
	public int value(){  return this.value;}  
	public String description(){ return this.description; }

	SchoolOpenClose(int aStatus, String desc){  
		this.value = aStatus;  
		this.description = desc;  
	}  
}

public enum SchoolMonitoringStatus{  
	
	active(1,"Active"),delete(0,"Not Active"); 
	private final int value;  
	private final String description;        
	public int value(){  return this.value;}  
	public String description(){ return this.description; }

	SchoolMonitoringStatus(int aStatus, String desc){  
		this.value = aStatus;  
		this.description = desc;  
	}  
}
	
public enum ActivityStatus{  
		
		eccd(1,"ECCD"),ape(2,"APE"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		ActivityStatus(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}  
	}
	
	public enum SchoolSurveyRequirement
	{  
		enrollChildren(1,"Enrolled Children"),poorChildEnrollment(2,"Enrolled Children(Poor)"),disableChildEnrollment(3,"Enrolled Children(Disabled)")
		,enrollEthnic(4,"Enrollment data(Ethnic)"),trainEccd(5 ,"Trained ECCD CGs") , trainPefs(6,"Trained PEFs") , trainedECCDMcs(7,"Trained ECCD MCs"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		SchoolSurveyRequirement(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}  
	}

	public enum SupportTitle
	{
		Technical(1,"Technical Support"),Funding(2,"Funding Support"),Tlms(3,"TLMs Support"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		SupportTitle(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}
	}
	
	public enum rdoResolveComplete
	{
		Yes(1,"Yes"),No(2,"No"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		rdoResolveComplete(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}
	}
	
	public enum getCleanWater
	{
		Yes(1,"Yes"),No(2,"No"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		getCleanWater(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}
	}
	
	public enum SupportValue
	{
		moe(1,"MoE"),sci(2,"SCI"),nwg(3,"NWG"),dsw(4,"DSW"),ministry(5,"Other Ministry"),lngo(6,"LNGO")
		,ingo(7,"INGO"),fgo(8,"FGO"),donor(9,"Individual Donor"),un(10,"UN"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		SupportValue(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}
	}
	
	
	public enum DistanceEccdBtwTown
	{
		lt1mile(1,"Less than 1 mile"),oneto10mile(2,"1-10 miles"),tento20miles(3,"10-20 miles")
		,twentyto30miles(4,"20-30 miles"),over30miles(5,"Over 30 miles"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		DistanceEccdBtwTown(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}
	}
	
	public enum DistanceEccdBtwSchool
	{
		lt1mile(1,"Less than 1 miles"),oneto5mile(2,"1-5 miles"),fiveto10mile(3,"5-10 miles")
		,tento15mile(4,"10-15 miles"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		DistanceEccdBtwSchool(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}
	}
	
	public enum FrequencyMeeting
	{
		monthly(1,"Monthly"),bimonthly(2,"Bi-Monthly"),quartley(3,"Quarterly"),sixmonthly(4,"Six monthly"),annual(5,"Annually")
		,ifrequire(6,"If Require"),never(7,"Never"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		FrequencyMeeting(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}
	}
	
	public enum SchoolMonitoringSurveyData
	{  
		noOfChildren0To3(1,"No. of children in village(0-3)"),noOfChildren3To5(2,"No. of children in village(3-5)"),noOfChildrenEnroll0To3(3,"No. of children enrolled(0-3)")
		,noOfChildrenEnroll3To5(4 ,"No. of children enrolled(3-5)") , noOfChildrenAttending(5,"No. of children attending") , 
		transition(6,"Transition to BE"),noOfPoor0To3(7,"No. of poor children enrolled(0-3)"),noOfPoor3To5(8,"No. of poor children enrolled(3-5)"),noOfDisableCildren0to3(9,"No. of disabled children in village(0-3)")
		,noOfDisableCildren3To5(10,"No. of disabled children in village(3-5)"),noOfDisableChildrenEnroll0To3(11,"No. of disabled children enrolled(0-3)")
		,noOfDisableChildrenEnroll3To5(12,"No. of disabled children enrolled(3-5)"),noOFDisableChildrenAttending(13,"No. of disabled children attending")
		,transitionToBe(14,"Transition to KG (disabled children)"),noOfEthnicChildren0To3(15,"No. of Ethnic children enrolled(0-3)"),noOfEthnicChildren3To5(16,"No. of Ethnic children enrolled(3-5)")
		,noOfCareGivers(17,"No. of Caregivers"),noOfTrainCareGiver(18,"No. of Trained Caregivers"),noOfPefs(19,"No. of PEFs")
		,noOfTrainedPefs(20,"No. of Trained PEFs"),noOfEccd(21,"No. of ECCD MCs"),noOfTrainedEccd(22,"No. of Trained ECCD MCs"); 
		private final int value;  
		private final String description;        
		public int value(){  return this.value;}  
		public String description(){ return this.description; }

		SchoolMonitoringSurveyData(int aStatus, String desc){  
			this.value = aStatus;  
			this.description = desc;  
		}  
	}

}
