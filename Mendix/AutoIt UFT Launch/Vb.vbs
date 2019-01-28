Set App = CreateObject("QuickTest.Application")
App.Launch
App.Visible = True
App.WindowState = "Maximized"
App.ActivateView "ExpertView"
App.open "C:\\Users\\IBM_ADMIN\\git\\MDM_TEST\\MDM_POC\\SAP_SystemNov1",False
App.Test.Run ,True 
