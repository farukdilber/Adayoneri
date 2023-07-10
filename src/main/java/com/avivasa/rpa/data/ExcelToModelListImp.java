package com.avivasa.rpa.data;

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

public class ExcelToModelListImp {
	
	public List<TitusModel> getTitusModelsList(List<List<Object>> readToList, TitusModel titusModel)
	{	
		ArrayList<TitusModel> titusModels = new ArrayList<>();
		try {
			
			for(int i = 0; i < readToList.size(); i++)	
			{
				titusModel = new TitusModel();
				titusModel.setComputerName(readToList.get(i).get(0).toString());
				titusModel.setConfigration(readToList.get(i).get(1).toString());
				titusModel.setDate(readToList.get(i).get(2).toString());
				titusModel.setProductVersion(readToList.get(i).get(3).toString());
				titusModels.add(titusModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace( );
		}
		
		return titusModels;
	}
	
	public List<SCCMClientModel> getSCCMClientModelsList(List<List<Object>> readToList, SCCMClientModel sccmClientModel)
	{	
		ArrayList<SCCMClientModel> sccmClientModels = new ArrayList<>();
		try {
			
			for(int i = 0; i < readToList.size(); i++)	
			{
				sccmClientModel = new SCCMClientModel();
				sccmClientModel.setDevice(readToList.get(i).get(0).toString());
				sccmClientModel.setObsoloteClient("");
				sccmClientModel.setActiveClient(readToList.get(i).get(1).toString());
				sccmClientModel.setConfigMgrClient("");
				//sccmClientModel.setObsoloteClient(readToList.get(i).get(1).toString());
				//sccmClientModel.setActiveClient(readToList.get(i).get(2).toString());
				//sccmClientModel.setConfigMgrClient(readToList.get(i).get(3).toString());
				sccmClientModels.add(sccmClientModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace( );
		}
		
		return sccmClientModels;
	}
	
	public List<NessusModel> getNessusModelsList(List<List<Object>> readToList, NessusModel nessusModel)
	{	
		ArrayList<NessusModel> nessusModels = new ArrayList<>();
		try {
			
			for(int i = 0; i < readToList.size(); i++)	
			{
				nessusModel = new NessusModel();
				nessusModel.setAgentName(readToList.get(i).get(0).toString());
				nessusModel.setStatus(readToList.get(i).get(1).toString());
				nessusModel.setAdress_IP(readToList.get(i).get(2).toString());
				nessusModel.setPlatform(readToList.get(i).get(3).toString());
				nessusModel.setVersion(readToList.get(i).get(4).toString());
				nessusModel.setLastPluginUpdate(readToList.get(i).get(5).toString());
				nessusModel.setLastScanned(readToList.get(i).get(6).toString());
				nessusModels.add(nessusModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace( );
		}
		
		return nessusModels;
	}
	
	public List<KeepnetModel> getKeepNetModelsList(List<List<Object>> readToList, KeepnetModel keepnetModel)
	{	
		ArrayList<KeepnetModel> keepnetmodels = new ArrayList<>();
        try {
             for (int i = 0; i < readToList.size(); i++)
             {
                  keepnetModel = new KeepnetModel();
                  keepnetModel.setFirstName(readToList.get(i).get(0).toString());
                  keepnetModel.setLastName(readToList.get(i).get(1).toString());
                  keepnetModel.seteMail(readToList.get(i).get(2).toString());
                  keepnetModel.setStatus(readToList.get(i).get(3).toString());
                  keepnetModel.setLastSeen(readToList.get(i).get(4).toString());
                  keepnetModel.setDiagnosticTool(readToList.get(i).get(5).toString());
                  keepnetModel.setDevice(readToList.get(i).get(6).toString());
                  keepnetModel.setVersion(readToList.get(i).get(7).toString());
                  
                  keepnetmodels.add(keepnetModel);
             }
        } catch (Exception e) {
                  e.printStackTrace();
             }
        return keepnetmodels;

	}
	
	public List<DLPAgentModel> getDLPAgentModelsList(List<List<Object>> readToList, DLPAgentModel dLPAgentmodel)
	{	
		ArrayList<DLPAgentModel> dLPAgentmodels = new ArrayList<>();
		try {
			
			for(int i = 0; i < readToList.size(); i++)	
			{
				dLPAgentmodel = new DLPAgentModel();
				dLPAgentmodel.setStatus(readToList.get(i).get(0).toString());
				dLPAgentmodel.setMachineName(readToList.get(i).get(1).toString());
				dLPAgentmodel.setUserName(readToList.get(i).get(2).toString());
				dLPAgentmodel.setoS(readToList.get(i).get(3).toString());
				dLPAgentmodel.setPlatform(readToList.get(i).get(4).toString());
				dLPAgentmodel.setRecentErrorMessages(readToList.get(i).get(5).toString());
				dLPAgentmodel.setEndpointServer(readToList.get(i).get(6).toString());
				dLPAgentmodel.setAdress_IP(readToList.get(i).get(7).toString());
				dLPAgentmodel.setVersion(readToList.get(i).get(8).toString());
				dLPAgentmodel.setConnecttion(readToList.get(i).get(9).toString());
				dLPAgentmodel.setLastUpdateReceived(readToList.get(i).get(10).toString());

				dLPAgentmodels.add(dLPAgentmodel);
			}
			
		} catch (Exception e) {
			e.printStackTrace( );
		}
		
		return dLPAgentmodels;
	}
	
	public List<CiscoModel> getCiscoModelsList(List<List<Object>> readToList, CiscoModel ciscoModel)
	{	
		ArrayList<CiscoModel> ciscoModels = new ArrayList<>();
		try {
			
			for(int i = 0; i < readToList.size(); i++)	
			{
				ciscoModel = new CiscoModel();
				ciscoModel.setConnector_Guid(readToList.get(i).get(0).toString());
				ciscoModel.setHostName(readToList.get(i).get(1).toString());
				ciscoModel.setOperating_System(readToList.get(i).get(2).toString());
				ciscoModel.setConnector_Version(readToList.get(i).get(3).toString());
				ciscoModel.setGroup(readToList.get(i).get(4).toString());
				ciscoModel.setInstallDate(readToList.get(i).get(5).toString());
				ciscoModel.setLastSeen(readToList.get(i).get(6).toString());
				ciscoModel.setInternal_IP(readToList.get(i).get(7).toString());
				ciscoModel.setExternal_IP(readToList.get(i).get(8).toString());
				ciscoModel.setAdress_MAC(readToList.get(i).get(9).toString());
				ciscoModel.setIos_Serial_Number(readToList.get(i).get(10).toString());
				ciscoModel.setConnector_Antivirus(readToList.get(i).get(11).toString());
				ciscoModel.setLast_Definiton_Update(readToList.get(i).get(12).toString());
				ciscoModel.setLast_Definiton_Update_Failure(readToList.get(i).get(13).toString());
				ciscoModel.setProcess_Hardware_Identifier(readToList.get(i).get(14).toString());
				ciscoModels.add(ciscoModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace( );
		}
		
		return ciscoModels;
	}
	
	public List<ComputersModel> getComputersModelsList(List<List<Object>> readToList, ComputersModel computersModel)
	{	
		ArrayList<ComputersModel> computersModels = new ArrayList<>();
		try {
			
			for(int i = 0; i < readToList.size(); i++)	
			{
				computersModel = new ComputersModel();
				computersModel.setName(readToList.get(i).get(0).toString());
				computersModels.add(computersModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace( );
		}
		
		return computersModels;
	}
	
	public List<AvivasaComputersModel> getAvivasaComputersModelsList(List<List<Object>> readToList, AvivasaComputersModel avivasaComputersModel)
	{	
		ArrayList<AvivasaComputersModel> avivasaComputersModels = new ArrayList<>();
		try {
			
			for(int i = 0; i < readToList.size(); i++)	
			{
				avivasaComputersModel = new AvivasaComputersModel();
				avivasaComputersModel.setName(readToList.get(i).get(0).toString());
				avivasaComputersModels.add(avivasaComputersModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace( );
		}
		
		return avivasaComputersModels;
	}
	public List<MCafeeModel> getMCafeeModelsList(List<List<Object>> readToList, MCafeeModel mCafeeModel)
	{	
		ArrayList<MCafeeModel> mCafeeModels = new ArrayList<>();
		try {
			
			for(int i = 0; i < readToList.size(); i++)	
			{
				mCafeeModel = new MCafeeModel();
				mCafeeModel.setLastCommunication(readToList.get(i).get(0).toString().trim());
				mCafeeModel.setSystemName(readToList.get(i).get(1).toString().trim());

				mCafeeModels.add(mCafeeModel);
			}
			
		} catch (Exception e) {
			e.printStackTrace( );
		}
		
		return mCafeeModels;
	}

}
