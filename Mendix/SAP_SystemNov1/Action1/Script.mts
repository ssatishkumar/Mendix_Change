StrExcelSheetName = "TestPlan"
StrSheetName = StrExcelSheetName

'AH1 Login Details
StrServerDescription_AH1 = "AH1"
StrClient_AH1 = "700"
StrUserID_AH1 = "sidlaa01"
StrPassword_AH1 = "Ibm@2018"
StrLanguage_AH1 = "EN"

'AE1 Login Details
StrServerDescription_AE1 = "AE1"
StrClient_AE1 = "700"
StrUserID_AE1 = "ramega01"
StrPassword_AE1 = "Hnk@3456"
StrLanguage_AE1 = "EN"

'SAPGuiUtil.AutoLogon StrServerDescription_AH1, StrClient_AH1, StrUserID_AH1, StrPassword_AH1, StrLanguage_AH1
'SAPGuiUtil.AutoLogon StrServerDescription_AE1, StrClient_AE1, StrUserID_AE1, StrPassword_AE1, StrLanguage_AE1

'Provide TestData excel sheet path
strExcelFilePath = "C:\Users\IBM_ADMIN\git\MDM_TEST\MDM_POC\input\Mendix_TestPlan.xlsx"

DataTable.AddSheet(StrSheetName)
DataTable.ImportSheet strExcelFilePath, StrExcelSheetName, StrSheetName
DataTable.Value("Status_AH1", StrSheetName) = "FAIL"

RowCount =DataTable.GetSheet(StrSheetName).GetRowCount

For i = 1 To RowCount		
	DataTable.SetCurrentRow(i)
	
	StrMaterial_Number_AH1 =  DataTable.Value("Material_Number_AH1", StrSheetName)
	StrMaterial_Number_AE1 =  DataTable.Value("Material_Number_AE1", StrSheetName)
	StrExecute = DataTable.Value("Execute", StrSheetName)
	
	If UCase(StrExecute) = "Y" Then
		SAPGuiUtil.CloseConnections
		SAPGuiUtil.AutoLogon StrServerDescription_AH1, StrClient_AH1, StrUserID_AH1, StrPassword_AH1, StrLanguage_AH1
		'Verify if other logins in this system
		If SAPGuiSession("guicomponenttype:=12").SAPGuiWindow("guicomponenttype:=22").Exist(5) Then
		   SAPGuiSession("guicomponenttype:=12").SAPGuiWindow("guicomponenttype:=22").SAPGuiRadioButton("guicomponenttype:=41","attachedtext:=Continue with this logon and end any other logons in the system\.").Set
		   SAPGuiSession("guicomponenttype:=12").SAPGuiWindow("guicomponenttype:=22").SendKey ENTER
		End If
		'Verify Login
		Set SAPWindow = SAPGuiSession("guicomponenttype:=12").SAPGuiWindow("guicomponenttype:=21")
		If SAPWindow.SAPGuiTree("guicomponenttype:=200","name:=shell").Exist(10) Then
			ResultReport "PASS","SAP Login","Loggin SucessFul","Yes"
		Else
			ResultReport "FAIL","SAP Login","Loggin Failed","Yes"
			SAPGuiUtil.CloseConnections
			ExitRun()
		End If
		'Navigate to MM03
		If SAPGuiSession("Session").SAPGuiWindow("SAP Easy Access").Exist(10) Then
			SAPGuiSession("Session").SAPGuiWindow("SAP Easy Access").SAPGuiOKCode("OKCode").Object.focus
			wait 1
			SAPGuiSession("Session").SAPGuiWindow("SAP Easy Access").SAPGuiOKCode("OKCode").Set "MM03"
			ResultReport "PASS","Navigate to SAP Easy Access Window","Navigated & Enterted '/nmm03' to navaigate to the Materail search window","Yes"
			
			'SAPGuiSession("Session").SAPGuiWindow("SAP Easy Access").SAPGuiOKCode("OKCode").Object.focus
			SAPGuiSession("Session").SAPGuiWindow("SAP Easy Access").SendKey ENTER @@ hightlight id_;_0_;_script infofile_;_ZIP::ssf2.xml_;_
			'ResultReport "PASS","Navigate to SAP Easy Access Window","Navigated & Enterted '/nmm03' to navaigate to the Materail search window","Yes"
		Else
			ResultReport "FAIL","Navigate to SAP Easy Access Window","Not navigated to Easy Access Window","Yes"
			'DataTable.Value("Status_AH1", StrSheetName) = "FAIL"
			'SAPGuiUtil.CloseConnections
		End If
		If SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").Exist(5) Then
		StrMaterailNotExistORNotActivated = "The material.*.does not exist or is not activated"
		StrFillOutAllRequiredEntryFields = "Fill out all required entry fields"
			wait 1
			SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").SAPGuiEdit("Material").Set StrMaterial_Number_AH1  'VarMaterialNumber
				wait 1
			SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").SendKey ENTER @@ hightlight id_;_0_;_script infofile_;_ZIP::ssf3.xml_;_
				wait 2
			If SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").SAPGuiStatusBar("StatusBar").Exist(5) Then
				StatusBarText = SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").SAPGuiStatusBar("StatusBar").GetROProperty("text")
				If Instr(StatusBarText,StrMaterailNotExistORNotActivated) > 0 or Instr(StatusBarText,StrFillOutAllRequiredEntryFields) > 0 or StatusBarText = "" or StatusBarText = " " Then
					DataTable.Value("Status_AH1", StrSheetName) = "FAIL"
					ResultReport "FAIL","Search for Materail in Initial Screen Window","Not able to search the material - "&VarMaterialNumber,"Yes"
					'SAPGuiUtil.CloseConnections
				End If
			ElseIf SAPGuiSession("Session").SAPGuiWindow("Select view(s)").Exist(5) Then
				SAPGuiSession("Session").SAPGuiWindow("Select view(s)").SAPGuiButton("Select All   (Shift+F8)").Click @@ hightlight id_;_1_;_script infofile_;_ZIP::ssf6.xml_;_
				wait 1
				SAPGuiSession("Session").SAPGuiWindow("Select view(s)").SAPGuiButton("Continue   (Enter)").Click @@ hightlight id_;_1_;_script infofile_;_ZIP::ssf7.xml_;_
				wait 1
				SAPGuiSession("Session").SAPGuiWindow("Organizational Levels").SAPGuiButton("Continue   (Enter)").Click @@ hightlight id_;_1_;_script infofile_;_ZIP::ssf8.xml_;_
				wait 1
				VarMaterialNumberText = SAPGuiSession("Session").SAPGuiWindow("Display Material screen").SAPGuiEdit("Material").GetROProperty("value")
				If Trim(VarMaterialNumberText) = Trim(StrMaterial_Number_AH1) Then
					ResultReport "PASS","Search For Materail","Materail found successfully - "&VarMaterialNumberText,"Yes"
					DataTable.Value("Status_AH1", StrSheetName) = "PASS"
					Else
					DataTable.Value("Status_AH1", StrSheetName) = "FAIL"
					ResultReport "FAIL","Search For Materail","Materail not matched with serached materail - "&VarMaterialNumberText&" - "&VarMaterialNumber,"Yes"
					'SAPGuiUtil.CloseConnections
		
				End If
			Else
				DataTable.Value("Status_AH1", StrSheetName) = "FAIL"
				ResultReport "FAIL","Search for Materail","Not able to serach the materail - "&VarMaterialNumber,"Yes"
				'SAPGuiUtil.CloseConnections
			End If
		Else
			DataTable.Value("Status_AH1", StrSheetName) = "FAIL"
			ResultReport "FAIL","Navigate to Display Materail Initial Screen Window","Not navigated to Display Materail Initial Screen Window","Yes"
			'SAPGuiUtil.CloseConnections
		End If
		SAPGuiSession("Session").SAPGuiWindow("Display Material screen").SAPGuiButton("Back   (F3)").Click
		'SAPGuiUtil.CloseConnections
	Else
		DataTable.Value("Status_AH1", StrSheetName) = "No Run"
		DataTable.Value("Status_AE1", StrSheetName) = "No Run"
	End If
Next

Datatable.ExportSheet strExcelFilePath, StrSheetName, StrExcelSheetName

SAPGuiUtil.CloseConnections

''---------------------------------------------------------------------------------------------------
'SAPGuiUtil.CloseConnections
'SAPGuiUtil.AutoLogon StrServerDescription_AH1, StrClient_AH1, StrUserID_AH1, StrPassword_AH1, StrLanguage_AH1
'
'If SAPGuiSession("guicomponenttype:=12").SAPGuiWindow("guicomponenttype:=22").Exist(2) Then
'   SAPGuiSession("guicomponenttype:=12").SAPGuiWindow("guicomponenttype:=22").SAPGuiRadioButton("guicomponenttype:=41","attachedtext:=Continue with this logon and end any other logons in the system\.").Set
'   SAPGuiSession("guicomponenttype:=12").SAPGuiWindow("guicomponenttype:=22").SendKey ENTER
'End If
'Set SAPWindow = SAPGuiSession("guicomponenttype:=12").SAPGuiWindow("guicomponenttype:=21")
'If SAPWindow.SAPGuiTree("guicomponenttype:=200","name:=shell").Exist(10) Then
'	ResultReport "PASS","SAP Login","Loggin SucessFul","Yes"
'Else
'	ResultReport "FAIL","SAP Login","Loggin Failed","Yes"
'	SAPGuiUtil.CloseConnections
'End If
'
'If SAPGuiSession("Session").SAPGuiWindow("SAP Easy Access").Exist(10) Then
'	SAPGuiSession("Session").SAPGuiWindow("SAP Easy Access").SAPGuiOKCode("OKCode").Set "/nmm03"
'	SAPGuiSession("Session").SAPGuiWindow("SAP Easy Access").SendKey ENTER @@ hightlight id_;_0_;_script infofile_;_ZIP::ssf2.xml_;_
'	ResultReport "PASS","Navigate to SAP Easy Access Window","Navigated & Enterted '/nmm03' to navaigate to the Materail search window","Yes"
'Else
'	ResultReport "FAIL","Navigate to SAP Easy Access Window","Not navigated to Easy Access Window","Yes"
'	TestData.Cells(2,5).value = "FAIL"
'	SAPGuiUtil.CloseConnections
'End If
'If SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").Exist(5) Then
'	SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").SAPGuiEdit("Material").Set VarMaterialNumber
'	SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").SendKey ENTER @@ hightlight id_;_0_;_script infofile_;_ZIP::ssf3.xml_;_
'	If SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").SAPGuiStatusBar("StatusBar").Exist(5) Then
'		StatusBarText = SAPGuiSession("Session").SAPGuiWindow("Display Material (Initial").SAPGuiStatusBar("StatusBar").GetROProperty("text")
'		If Instr(StatusBarText,StrMaterailNotExistORNotActivated) > 0 or Instr(StatusBarText,StrFillOutAllRequiredEntryFields) > 0 or StatusBarText = "" or StatusBarText = " " Then
'			TestData.Cells(2,5).value = "FAIL"
'			ResultReport "FAIL","Search for Materail in Initial Screen Window","The material does not exist or is not activated - "&VarMaterialNumber,"Yes"
'			SAPGuiUtil.CloseConnections
'		End If
'	ElseIf SAPGuiSession("Session").SAPGuiWindow("Select view(s)").Exist(5) Then
'		SAPGuiSession("Session").SAPGuiWindow("Select view(s)").SAPGuiButton("Select All   (Shift+F8)").Click @@ hightlight id_;_1_;_script infofile_;_ZIP::ssf6.xml_;_
'		wait 1
'		SAPGuiSession("Session").SAPGuiWindow("Select view(s)").SAPGuiButton("Continue   (Enter)").Click @@ hightlight id_;_1_;_script infofile_;_ZIP::ssf7.xml_;_
'		wait 1
'		SAPGuiSession("Session").SAPGuiWindow("Organizational Levels").SAPGuiButton("Continue   (Enter)").Click @@ hightlight id_;_1_;_script infofile_;_ZIP::ssf8.xml_;_
'		wait 1
'		VarMaterialNumberText = SAPGuiSession("Session").SAPGuiWindow("Display Material screen").SAPGuiEdit("Material").GetROProperty("value")
'		If Trim(VarMaterialNumberText) = Trim(VarMaterialNumber) Then
'			ResultReport "PASS","Search For Materail","Materail found successfully - "&VarMaterialNumberText,"Yes"
'			TestData.Cells(2,5).value = "PASS"
'		Else
'			TestData.Cells(2,5).value = "FAIL"
'			ResultReport "FAIL","Search For Materail","Materail not matched with serached materail - "&VarMaterialNumberText&" - "&VarMaterialNumber,"Yes"
'			SAPGuiUtil.CloseConnections
'		End If
'	Else
'		TestData.Cells(2,5).value = "FAIL"
'		ResultReport "FAIL","Search for Materail","Not able to serach the materail - "&VarMaterialNumber,"Yes"
'		SAPGuiUtil.CloseConnections
'	End If
'Else
'	TestData.Cells(2,5).value = "FAIL"
'	ResultReport "FAIL","Navigate to Display Materail Initial Screen Window","Not navigated to Display Materail Initial Screen Window","Yes"
'	SAPGuiUtil.CloseConnections
'End If
'SAPGuiSession("Session").SAPGuiWindow("Display Material screen").SAPGuiButton("Back   (F3)").Click
'SAPGuiUtil.CloseConnections

'TestData.ActiveWorkbook.Save
'TestData.ActiveWorkbook.Close
'TestData.Quit
'
'CloseExcel()
'
'*************************************************************************************************
'* Function Name	: CaptureScreenshot
'* PURPOSE			: To Capture the screen shot
'* Limitations      : NA
'* Input Parameters	: 
'* 		strStep - Step name
'*      strStatus- Status(Pass/Fail)
'* Out Parameter	: True or False
'*      strFilepath- Screen shot Path along with screen shot Name
'* Example1			: 
'* 		CaptureScreenshot "Verify Order Creation","Pass"
'* Example2			: 
'* 		CaptureScreenshot "Verify Order Creation","Fail"
'* Revision History
'* Date:				Updated By:					Reason:
'* **********************************************************************************************
'* 16-July-2018		   Sai Vivek k					Initial Version
'************************************************************************************************
Public Function CaptureScreenshot(strStep,strStatus,strFilepath)
    On error Resume Next
'' Define TimeStamp
	strMonth = month(Date)
	If len(strMonth) = 1 Then
		strMonth = "0" & strMonth
	End If
	
	strDate = day(Date)
	If len(strDate) = 1 Then
		strDate = "0" & strDate
	End If
	
	strHour = hour(Time)
	If len(strHour) = 1 Then
		strHour = "0" & strHour
	End If
	
	strMin = minute(Time)
	If len(strMin) = 1 Then
		strMin = "0" & strMin
	End If
	
	strSec = Second(Time)
	If len(strSec) = 1 Then
		strSec = "0" & strSec
	End If
	
	strTimeStamp = strMonth & strDate &  year(Date) & "_" & strHour & "h" & strMin & "m"&strSec&"s"		
    
	Set oWsh = CreateObject("WScript.Shell")
	strTempFolder = oWsh.ExpandEnvironmentStrings("%Temp%")
	strTempFolderPath = strTempFolder&"\"
	Sheetname=DataTable.LocalSheet.Name
	
	If ucase(strStatus)="PASS" Then
		strScreenShotName= "Pass_"&strStep&"_"&strTimeStamp&".png"
	Elseif ucase(strStatus)="FAIL" then
		strScreenShotName="Fail_"&strStep&"_"&strTimeStamp&".png"
	Else
		strScreenShotName=strTimeStamp&".png"
	End If
	
	strScreenShotFileName=Sheetname&"_"&strScreenShotName
	strFilepath=strTempFolderPath&strScreenShotFileName
    Desktop.CaptureBitmap strFilepath
    CaptureScreenshot = strFilepath
    err.clear
	On error goto 0
End Function

'***********************************************************************************************
'* Function Name	: ResultReport
'* PURPOSE			: This Function writes result to UFT Report and saves a snapshot.
'* Limitations      : NA
'* Input Parameters	: 
'* 		strStatus - Status Pass/Fail
'*      strStep -   Step Name
'*      StrMessage-  Actual Result Passed/Failed statement with description
'*      strScreenshot- To Capture the screenshot
        
'* Out Parameter	: True or False
'* Example1			:  
'* 		ResultReport "PASS","Verify Order","Order SucessFul","Yes"
'* Example2			: 
'* 		ResultReport "FAIL","Verify Order","Order Failed","Yes"
'* Revision History
'* Date:				Updated By:					Reason:
'* **********************************************************************************************
'* 16-July-2018			Naresh P					Initial Version
'************************************************************************************************
Public Function ResultReport(strStatus,strStep,StrMessage,strScreenshot)
   On Error Resume Next
   strFilepath = CaptureScreenshot("","","")
   If Ucase(Trim(strScreenshot))= "YES" or Ucase(Trim(strScreenshot))= "" Then
   	Call CaptureScreenshot(strStep,strStatus,"")
   End If
   If UCase(Trim(strstatus))="PASS" Then
   	Reporter.ReportEvent micPass,strStep,StrMessage,strFilepath
   ElseIf UCase(Trim(strstatus))="FAIL" Then	   
	Reporter.ReportEvent micFail,strStep,StrMessage,strFilepath
	'ExitTest()
   Else
   	Reporter.ReportEvent micDone,strStep,StrMessage,strFilepath
   End If
End Function

Public Function CloseIEBrowser()
	On Error Resume Next
	SystemUtil.CloseProcessByName "IEXPLORE.EXE"
	Wait(2)
	If Browser("version:=internet explorer.*.","CreationTime:=0").Dialog("regexpwndtitle:=Internet Explorer").Exist(5) Then
		Browser("version:=internet explorer.*.","CreationTime:=0").Dialog("regexpwndtitle:=Internet Explorer").WinButton("regexpwndtitle:=Close all &tabs").Click
	End If
End Function

Public Function CloseExcel()
	Set objWMIService = GetObject("winmgmts:" _
    & "{impersonationLevel=impersonate, " _
    & "(Debug)}!\\.\root\cimv2")
	Set colProcessList = objWMIService.ExecQuery _
    ("Select * from Win32_Process Where Name = 'Excel.exe'")
	For Each objProcess In colProcessList
	    objProcess.Terminate()
	Next
End Function
