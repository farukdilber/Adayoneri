package com.avivasa.rpa.KPIAjanlariHesaplamalari.actions;

import java.util.ArrayList;
import java.util.List;
import com.avivasa.rpa.model.AvivasaComputersModel;
import com.avivasa.rpa.model.CiscoModel;
import com.avivasa.rpa.model.ComputersModel;
import com.avivasa.rpa.model.DLPAgentModel;
import com.avivasa.rpa.model.KeepnetModel;
import com.avivasa.rpa.model.MCafeeModel;
import com.avivasa.rpa.model.NessusModel;
import com.avivasa.rpa.model.SCCMClientModel;
import com.avivasa.rpa.model.TitusModel;

public class KPIAjanlariHesaplamalariActions {
	
	public List<AvivasaComputersModel> mergeAvivasaToCiscoDatas(List<CiscoModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{	
		boolean isMerge = false;
		String tempName = "";
		List<AvivasaComputersModel> mergeList = new ArrayList<>();
		List<AvivasaComputersModel> notMergeList = new ArrayList<>();
		for(AvivasaComputersModel list : avivasaComputersModelList) {
			isMerge = false;
			for(CiscoModel list2 : reportList)	{
				if(list2.getHostName().contains("."))
					tempName = list2.getHostName().substring(0, list2.getHostName().indexOf("."));
				else
					tempName = list2.getHostName();

				if(list.getName().equalsIgnoreCase(tempName))	{
					isMerge = true;
					mergeList.add(list);
					break;
				}
			}
			
			if(isMerge == false)
				notMergeList.add(list);
		}

		return notMergeList;
	}
	
	public List<CiscoModel> mergeCiscoToAvivasaDatas(List<CiscoModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{
		boolean isMerge = false;
		String tempName = "";
		List<CiscoModel> mergeList = new ArrayList<>();
		List<CiscoModel> notMergeList = new ArrayList<>();
		 for(CiscoModel list2 : reportList)	{
			 isMerge = false;
			for(AvivasaComputersModel list : avivasaComputersModelList){
				if(list2.getHostName().contains("."))
					tempName = list2.getHostName().substring(0, list2.getHostName().indexOf("."));
				else
					tempName = list2.getHostName();

				if(list.getName().equalsIgnoreCase(tempName))		{
					isMerge = true;
					mergeList.add(list2);	
					break;
				}
			}
			
			if(isMerge == false)
				notMergeList.add(list2);
		}

		return notMergeList;
	}
	public List<AvivasaComputersModel> mergeAvivasaToDLPDatas(List<DLPAgentModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{	
		boolean isMerge = false;
		List<AvivasaComputersModel> mergeList = new ArrayList<>();
		List<AvivasaComputersModel> notMergeList = new ArrayList<>();
		for(AvivasaComputersModel list : avivasaComputersModelList) {
			isMerge = false;
			for(DLPAgentModel list2 : reportList)	{
				if(list.getName().equalsIgnoreCase(list2.getMachineName()))	{
					isMerge = true;
					mergeList.add(list);
					break;
				}
			}

			if(isMerge == false)
				notMergeList.add(list);
		}

		return notMergeList;
	}
	
	public List<DLPAgentModel> mergeDLPToAvivasaDatas(List<DLPAgentModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{
		boolean isMerge = false;
		List<DLPAgentModel> mergeList = new ArrayList<>();
		List<DLPAgentModel> notMergeList = new ArrayList<>();
		 for(DLPAgentModel list2 : reportList)	{
			 isMerge = false;
			for(AvivasaComputersModel list : avivasaComputersModelList){
				if(list.getName().equalsIgnoreCase(list2.getMachineName()))		{
					isMerge = true;
					mergeList.add(list2);	
					break;
				}
			}
			
			if(isMerge == false)
				notMergeList.add(list2);
		}

		return notMergeList;
	}
	
	public List<AvivasaComputersModel> mergeAvivasaToKeepnetDatas(List<KeepnetModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{	
		boolean isMerge = false;
		List<AvivasaComputersModel> mergeList = new ArrayList<>();
		List<AvivasaComputersModel> notMergeList = new ArrayList<>();
		for(AvivasaComputersModel list : avivasaComputersModelList) {
			isMerge = false;
			for(KeepnetModel list2 : reportList)	{
				if(list.getName().equalsIgnoreCase(list2.getDevice()))	{
					isMerge = true;
					mergeList.add(list);
					break;
				}
			}

			if(isMerge == false)
				notMergeList.add(list);
		}

		return notMergeList;
	}
	
	public List<KeepnetModel> mergeKeepnetToAvivasaDatas(List<KeepnetModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{
		boolean isMerge = false;
		List<KeepnetModel> mergeList = new ArrayList<>();
		List<KeepnetModel> notMergeList = new ArrayList<>();
		 for(KeepnetModel list2 : reportList)	{
			 isMerge = false;
			for(AvivasaComputersModel list : avivasaComputersModelList){
				if(list.getName().equalsIgnoreCase(list2.getDevice())) {
					isMerge = true;
					mergeList.add(list2);	
					break;
				}
			}
			
			if(isMerge == false)
				notMergeList.add(list2);
		}

		return notMergeList;
	}
	
	public List<AvivasaComputersModel> mergeAvivasaToNessusDatas(List<NessusModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{	
		boolean isMerge = false;
		List<AvivasaComputersModel> mergeList = new ArrayList<>();
		List<AvivasaComputersModel> notMergeList = new ArrayList<>();
		for(AvivasaComputersModel list : avivasaComputersModelList) {
			isMerge = false;
			for(NessusModel list2 : reportList)	{
				if(list.getName().equalsIgnoreCase(list2.getAgentName()))	{
					isMerge = true;
					mergeList.add(list);
					break;
				}
			}

			if(isMerge == false)
				notMergeList.add(list);
		}

		return notMergeList;
	}
	
	public List<NessusModel> mergeNessusToAvivasaDatas(List<NessusModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{
		boolean isMerge = false;
		List<NessusModel> mergeList = new ArrayList<>();
		List<NessusModel> notMergeList = new ArrayList<>();
		 for(NessusModel list2 : reportList)	{
			 isMerge = false;
			for(AvivasaComputersModel list : avivasaComputersModelList){
				if(list.getName().equalsIgnoreCase(list2.getAgentName()))		{
					isMerge = true;
					mergeList.add(list2);	
					break;
				}
			}
			
			if(isMerge == false)
				notMergeList.add(list2);
		}

		return notMergeList;
	}
		
	public List<AvivasaComputersModel> mergeAvivasaToSCCMDatas(List<SCCMClientModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{	
		boolean isMerge = false;
		List<AvivasaComputersModel> mergeList = new ArrayList<>();
		List<AvivasaComputersModel> notMergeList = new ArrayList<>();
		for(AvivasaComputersModel list : avivasaComputersModelList) {
			isMerge = false;
			for(SCCMClientModel list2 : reportList)	{
				if(list.getName().equalsIgnoreCase(list2.getDevice()))	{
					isMerge = true;
					mergeList.add(list);
					break;
				}
			}

			if(isMerge == false)
				notMergeList.add(list);
		}

		return notMergeList;
	}
	
	public List<SCCMClientModel> mergeSCCMToAvivasaDatas(List<SCCMClientModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{
		boolean isMerge = false;
		List<SCCMClientModel> mergeList = new ArrayList<>();
		List<SCCMClientModel> notMergeList = new ArrayList<>();
		 for(SCCMClientModel list2 : reportList)	{
			 isMerge = false;
			for(AvivasaComputersModel list : avivasaComputersModelList){
				if(list.getName().equalsIgnoreCase(list2.getDevice()))		{
					isMerge = true;
					mergeList.add(list2);	
					break;
				}
			}
			
			if(isMerge == false)
				notMergeList.add(list2);
		}

		return notMergeList;
	}

	public List<AvivasaComputersModel> mergeAvivasaToTitusDatas(List<TitusModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{	
		boolean isMerge = false;
		List<AvivasaComputersModel> mergeList = new ArrayList<>();
		List<AvivasaComputersModel> notMergeList = new ArrayList<>();
		for(AvivasaComputersModel list : avivasaComputersModelList) {
			isMerge = false;
			for(TitusModel list2 : reportList)	{
				if(list.getName().equalsIgnoreCase(list2.getComputerName()))	{
					isMerge = true;
					mergeList.add(list);
					break;
				}
			}

			if(isMerge == false)
				notMergeList.add(list);
		}

		return notMergeList;
	}
	
	public List<TitusModel> mergeTitusToAvivasaDatas(List<TitusModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{
		boolean isMerge = false;
		List<TitusModel> mergeList = new ArrayList<>();
		List<TitusModel> notMergeList = new ArrayList<>();
		 for(TitusModel list2 : reportList)	{
			 isMerge = false;
			for(AvivasaComputersModel list : avivasaComputersModelList){
				if(list.getName().equalsIgnoreCase(list2.getComputerName()))		{
					isMerge = true;
					mergeList.add(list2);	
					break;
				}
			}
			
			if(isMerge == false)
				notMergeList.add(list2);
		}

		return notMergeList;
	}
	public List<AvivasaComputersModel> mergeAvivasaToMCafeeDatas(List<MCafeeModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{	
		boolean isMerge = false;
		List<AvivasaComputersModel> mergeList = new ArrayList<>();
		List<AvivasaComputersModel> notMergeList = new ArrayList<>();
		for(AvivasaComputersModel list : avivasaComputersModelList) {
			isMerge = false;
			for(MCafeeModel list2 : reportList)	{
				if(list.getName().equalsIgnoreCase(list2.getSystemName()))	{
					isMerge = true;
					mergeList.add(list);
					break;
				}
			}

			if(isMerge == false)
				notMergeList.add(list);
		}

		return notMergeList;
	}
	
	public List<MCafeeModel> mergeMCafeeToAvivasaDatas(List<MCafeeModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{
		boolean isMerge = false;
		List<MCafeeModel> mergeList = new ArrayList<>();
		List<MCafeeModel> notMergeList = new ArrayList<>();
		 for(MCafeeModel list2 : reportList)	{
			 isMerge = false;
			for(AvivasaComputersModel list : avivasaComputersModelList){
				if(list.getName().equalsIgnoreCase(list2.getSystemName()))		{
					isMerge = true;
					mergeList.add(list2);	
					break;
				}
			}
			
			if(isMerge == false)
				notMergeList.add(list2);
		}

		return notMergeList;
	}
	public List<AvivasaComputersModel> mergeAvivasaToComputersDatas(List<ComputersModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{	
		boolean isMerge = false;
		List<AvivasaComputersModel> mergeList = new ArrayList<>();
		List<AvivasaComputersModel> notMergeList = new ArrayList<>();
		for(AvivasaComputersModel list : avivasaComputersModelList) {
			isMerge = false;
			for(ComputersModel list2 : reportList)	{
				if(list.getName().equalsIgnoreCase(list2.getName()))	{
					isMerge = true;
					mergeList.add(list);
					break;
				}
			}

			if(isMerge == false)
				notMergeList.add(list);
		}

		return notMergeList;
	}
	
	public List<ComputersModel> mergeComputersToAvivasaDatas(List<ComputersModel> reportList, List<AvivasaComputersModel> avivasaComputersModelList)
	{
		boolean isMerge = false;
		List<ComputersModel> mergeList = new ArrayList<>();
		List<ComputersModel> notMergeList = new ArrayList<>();
		 for(ComputersModel list2 : reportList)	{
			 isMerge = false;
			for(AvivasaComputersModel list : avivasaComputersModelList){
				if(list.getName().equalsIgnoreCase(list2.getName()))		{
					isMerge = true;
					mergeList.add(list2);	
					break;
				}
			}
			
			if(isMerge == false)
				notMergeList.add(list2);
		}

		return notMergeList;
	}

}
