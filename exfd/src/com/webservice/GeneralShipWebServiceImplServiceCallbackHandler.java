
/**
 * GeneralShipWebServiceImplServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.webservice;

    /**
     *  GeneralShipWebServiceImplServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class GeneralShipWebServiceImplServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public GeneralShipWebServiceImplServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public GeneralShipWebServiceImplServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for isExitFairplayInfo method
            * override this method for handling normal response from isExitFairplayInfo operation
            */
           public void receiveResultisExitFairplayInfo(
                    com.webservice.IsExitFairplayInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isExitFairplayInfo operation
           */
            public void receiveErrorisExitFairplayInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayDemens method
            * override this method for handling normal response from get_FairplayDemens operation
            */
           public void receiveResultget_FairplayDemens(
                    com.webservice.Get_FairplayDemensResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayDemens operation
           */
            public void receiveErrorget_FairplayDemens(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPortRowInfo method
            * override this method for handling normal response from getPortRowInfo operation
            */
           public void receiveResultgetPortRowInfo(
                    com.webservice.GetPortRowInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPortRowInfo operation
           */
            public void receiveErrorgetPortRowInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCompanyGroups method
            * override this method for handling normal response from getCompanyGroups operation
            */
           public void receiveResultgetCompanyGroups(
                    com.webservice.GetCompanyGroupsResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCompanyGroups operation
           */
            public void receiveErrorgetCompanyGroups(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getWeatherThemesTimes method
            * override this method for handling normal response from getWeatherThemesTimes operation
            */
           public void receiveResultgetWeatherThemesTimes(
                    com.webservice.GetWeatherThemesTimesResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getWeatherThemesTimes operation
           */
            public void receiveErrorgetWeatherThemesTimes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSearchRecByKeyInPort method
            * override this method for handling normal response from getSearchRecByKeyInPort operation
            */
           public void receiveResultgetSearchRecByKeyInPort(
                    com.webservice.GetSearchRecByKeyInPortResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSearchRecByKeyInPort operation
           */
            public void receiveErrorgetSearchRecByKeyInPort(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addAttentionShip method
            * override this method for handling normal response from addAttentionShip operation
            */
           public void receiveResultaddAttentionShip(
                    com.webservice.AddAttentionShipResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addAttentionShip operation
           */
            public void receiveErroraddAttentionShip(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTyphoonForecast method
            * override this method for handling normal response from getTyphoonForecast operation
            */
           public void receiveResultgetTyphoonForecast(
                    com.webservice.GetTyphoonForecastResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTyphoonForecast operation
           */
            public void receiveErrorgetTyphoonForecast(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getWeatherThemesName method
            * override this method for handling normal response from getWeatherThemesName operation
            */
           public void receiveResultgetWeatherThemesName(
                    com.webservice.GetWeatherThemesNameResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getWeatherThemesName operation
           */
            public void receiveErrorgetWeatherThemesName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayConstruc method
            * override this method for handling normal response from get_FairplayConstruc operation
            */
           public void receiveResultget_FairplayConstruc(
                    com.webservice.Get_FairplayConstrucResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayConstruc operation
           */
            public void receiveErrorget_FairplayConstruc(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for saveConstPortInfo method
            * override this method for handling normal response from saveConstPortInfo operation
            */
           public void receiveResultsaveConstPortInfo(
                    com.webservice.SaveConstPortInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from saveConstPortInfo operation
           */
            public void receiveErrorsaveConstPortInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for login method
            * override this method for handling normal response from login operation
            */
           public void receiveResultlogin(
                    com.webservice.LoginResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from login operation
           */
            public void receiveErrorlogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayPumps method
            * override this method for handling normal response from get_FairplayPumps operation
            */
           public void receiveResultget_FairplayPumps(
                    com.webservice.Get_FairplayPumpsResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayPumps operation
           */
            public void receiveErrorget_FairplayPumps(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPortInfo method
            * override this method for handling normal response from getPortInfo operation
            */
           public void receiveResultgetPortInfo(
                    com.webservice.GetPortInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPortInfo operation
           */
            public void receiveErrorgetPortInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getUpdateFleetShipInfo method
            * override this method for handling normal response from getUpdateFleetShipInfo operation
            */
           public void receiveResultgetUpdateFleetShipInfo(
                    com.webservice.GetUpdateFleetShipInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getUpdateFleetShipInfo operation
           */
            public void receiveErrorgetUpdateFleetShipInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAttentionShipFullInfo method
            * override this method for handling normal response from getAttentionShipFullInfo operation
            */
           public void receiveResultgetAttentionShipFullInfo(
                    com.webservice.GetAttentionShipFullInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAttentionShipFullInfo operation
           */
            public void receiveErrorgetAttentionShipFullInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addLogLog method
            * override this method for handling normal response from addLogLog operation
            */
           public void receiveResultaddLogLog(
                    com.webservice.AddLogLogResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addLogLog operation
           */
            public void receiveErroraddLogLog(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getShipFullInfoFromCtrackCtrackInfoC method
            * override this method for handling normal response from getShipFullInfoFromCtrackCtrackInfoC operation
            */
           public void receiveResultgetShipFullInfoFromCtrackCtrackInfoC(
                    com.webservice.GetShipFullInfoFromCtrackCtrackInfoCResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getShipFullInfoFromCtrackCtrackInfoC operation
           */
            public void receiveErrorgetShipFullInfoFromCtrackCtrackInfoC(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayEngine method
            * override this method for handling normal response from get_FairplayEngine operation
            */
           public void receiveResultget_FairplayEngine(
                    com.webservice.Get_FairplayEngineResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayEngine operation
           */
            public void receiveErrorget_FairplayEngine(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_ShipPicShowIndexAry method
            * override this method for handling normal response from get_ShipPicShowIndexAry operation
            */
           public void receiveResultget_ShipPicShowIndexAry(
                    com.webservice.Get_ShipPicShowIndexAryResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_ShipPicShowIndexAry operation
           */
            public void receiveErrorget_ShipPicShowIndexAry(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for editPwd method
            * override this method for handling normal response from editPwd operation
            */
           public void receiveResulteditPwd(
                    com.webservice.EditPwdResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from editPwd operation
           */
            public void receiveErroreditPwd(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTyphoonPath method
            * override this method for handling normal response from getTyphoonPath operation
            */
           public void receiveResultgetTyphoonPath(
                    com.webservice.GetTyphoonPathResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTyphoonPath operation
           */
            public void receiveErrorgetTyphoonPath(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isExitCtrackAisInfo method
            * override this method for handling normal response from isExitCtrackAisInfo operation
            */
           public void receiveResultisExitCtrackAisInfo(
                    com.webservice.IsExitCtrackAisInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isExitCtrackAisInfo operation
           */
            public void receiveErrorisExitCtrackAisInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_SpatialObjCountInfoByUpdatetime method
            * override this method for handling normal response from get_SpatialObjCountInfoByUpdatetime operation
            */
           public void receiveResultget_SpatialObjCountInfoByUpdatetime(
                    com.webservice.Get_SpatialObjCountInfoByUpdatetimeResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_SpatialObjCountInfoByUpdatetime operation
           */
            public void receiveErrorget_SpatialObjCountInfoByUpdatetime(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPortRowCount method
            * override this method for handling normal response from getPortRowCount operation
            */
           public void receiveResultgetPortRowCount(
                    com.webservice.GetPortRowCountResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPortRowCount operation
           */
            public void receiveErrorgetPortRowCount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayRegis method
            * override this method for handling normal response from get_FairplayRegis operation
            */
           public void receiveResultget_FairplayRegis(
                    com.webservice.Get_FairplayRegisResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayRegis operation
           */
            public void receiveErrorget_FairplayRegis(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getShipFullInfoFromCtrackAisInfoC method
            * override this method for handling normal response from getShipFullInfoFromCtrackAisInfoC operation
            */
           public void receiveResultgetShipFullInfoFromCtrackAisInfoC(
                    com.webservice.GetShipFullInfoFromCtrackAisInfoCResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getShipFullInfoFromCtrackAisInfoC operation
           */
            public void receiveErrorgetShipFullInfoFromCtrackAisInfoC(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_DbInfoDynamicByMmsi method
            * override this method for handling normal response from get_DbInfoDynamicByMmsi operation
            */
           public void receiveResultget_DbInfoDynamicByMmsi(
                    com.webservice.Get_DbInfoDynamicByMmsiResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_DbInfoDynamicByMmsi operation
           */
            public void receiveErrorget_DbInfoDynamicByMmsi(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMobilesInfo method
            * override this method for handling normal response from getMobilesInfo operation
            */
           public void receiveResultgetMobilesInfo(
                    com.webservice.GetMobilesInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMobilesInfo operation
           */
            public void receiveErrorgetMobilesInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSearchCountByKeyInPort method
            * override this method for handling normal response from getSearchCountByKeyInPort operation
            */
           public void receiveResultgetSearchCountByKeyInPort(
                    com.webservice.GetSearchCountByKeyInPortResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSearchCountByKeyInPort operation
           */
            public void receiveErrorgetSearchCountByKeyInPort(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSearchRecByKeyInShipBaseInfo method
            * override this method for handling normal response from getSearchRecByKeyInShipBaseInfo operation
            */
           public void receiveResultgetSearchRecByKeyInShipBaseInfo(
                    com.webservice.GetSearchRecByKeyInShipBaseInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSearchRecByKeyInShipBaseInfo operation
           */
            public void receiveErrorgetSearchRecByKeyInShipBaseInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateAisVehicleInfo method
            * override this method for handling normal response from updateAisVehicleInfo operation
            */
           public void receiveResultupdateAisVehicleInfo(
                    com.webservice.UpdateAisVehicleInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateAisVehicleInfo operation
           */
            public void receiveErrorupdateAisVehicleInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_SpatialObjCountInfoByStartEnd method
            * override this method for handling normal response from get_SpatialObjCountInfoByStartEnd operation
            */
           public void receiveResultget_SpatialObjCountInfoByStartEnd(
                    com.webservice.Get_SpatialObjCountInfoByStartEndResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_SpatialObjCountInfoByStartEnd operation
           */
            public void receiveErrorget_SpatialObjCountInfoByStartEnd(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMobileTrackCount method
            * override this method for handling normal response from getMobileTrackCount operation
            */
           public void receiveResultgetMobileTrackCount(
                    com.webservice.GetMobileTrackCountResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMobileTrackCount operation
           */
            public void receiveErrorgetMobileTrackCount(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayCargo method
            * override this method for handling normal response from get_FairplayCargo operation
            */
           public void receiveResultget_FairplayCargo(
                    com.webservice.Get_FairplayCargoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayCargo operation
           */
            public void receiveErrorget_FairplayCargo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAisShipFullInfoByShipId method
            * override this method for handling normal response from getAisShipFullInfoByShipId operation
            */
           public void receiveResultgetAisShipFullInfoByShipId(
                    com.webservice.GetAisShipFullInfoByShipIdResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAisShipFullInfoByShipId operation
           */
            public void receiveErrorgetAisShipFullInfoByShipId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTyphoonsId method
            * override this method for handling normal response from getTyphoonsId operation
            */
           public void receiveResultgetTyphoonsId(
                    com.webservice.GetTyphoonsIdResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTyphoonsId operation
           */
            public void receiveErrorgetTyphoonsId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getConstPortInfo method
            * override this method for handling normal response from getConstPortInfo operation
            */
           public void receiveResultgetConstPortInfo(
                    com.webservice.GetConstPortInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getConstPortInfo operation
           */
            public void receiveErrorgetConstPortInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCellDistributionByMoreCell method
            * override this method for handling normal response from getCellDistributionByMoreCell operation
            */
           public void receiveResultgetCellDistributionByMoreCell(
                    com.webservice.GetCellDistributionByMoreCellResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCellDistributionByMoreCell operation
           */
            public void receiveErrorgetCellDistributionByMoreCell(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getShipFullInfoFromCtrackAisInfoE method
            * override this method for handling normal response from getShipFullInfoFromCtrackAisInfoE operation
            */
           public void receiveResultgetShipFullInfoFromCtrackAisInfoE(
                    com.webservice.GetShipFullInfoFromCtrackAisInfoEResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getShipFullInfoFromCtrackAisInfoE operation
           */
            public void receiveErrorgetShipFullInfoFromCtrackAisInfoE(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSearchCountByKeyInShipBaseInfo method
            * override this method for handling normal response from getSearchCountByKeyInShipBaseInfo operation
            */
           public void receiveResultgetSearchCountByKeyInShipBaseInfo(
                    com.webservice.GetSearchCountByKeyInShipBaseInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSearchCountByKeyInShipBaseInfo operation
           */
            public void receiveErrorgetSearchCountByKeyInShipBaseInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_ShipPic method
            * override this method for handling normal response from get_ShipPic operation
            */
           public void receiveResultget_ShipPic(
                    com.webservice.Get_ShipPicResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_ShipPic operation
           */
            public void receiveErrorget_ShipPic(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getServerDbCurDataTime method
            * override this method for handling normal response from getServerDbCurDataTime operation
            */
           public void receiveResultgetServerDbCurDataTime(
                    com.webservice.GetServerDbCurDataTimeResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getServerDbCurDataTime operation
           */
            public void receiveErrorgetServerDbCurDataTime(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkVehicleDistribution method
            * override this method for handling normal response from checkVehicleDistribution operation
            */
           public void receiveResultcheckVehicleDistribution(
                    com.webservice.CheckVehicleDistributionResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkVehicleDistribution operation
           */
            public void receiveErrorcheckVehicleDistribution(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getLastVersionAndroid method
            * override this method for handling normal response from getLastVersionAndroid operation
            */
           public void receiveResultgetLastVersionAndroid(
                    com.webservice.GetLastVersionAndroidResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLastVersionAndroid operation
           */
            public void receiveErrorgetLastVersionAndroid(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkVehicleDistributionRet method
            * override this method for handling normal response from checkVehicleDistributionRet operation
            */
           public void receiveResultcheckVehicleDistributionRet(
                    com.webservice.CheckVehicleDistributionRetResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkVehicleDistributionRet operation
           */
            public void receiveErrorcheckVehicleDistributionRet(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayRoro method
            * override this method for handling normal response from get_FairplayRoro operation
            */
           public void receiveResultget_FairplayRoro(
                    com.webservice.Get_FairplayRoroResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayRoro operation
           */
            public void receiveErrorget_FairplayRoro(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getFleetShipFullInfoByShipList method
            * override this method for handling normal response from getFleetShipFullInfoByShipList operation
            */
           public void receiveResultgetFleetShipFullInfoByShipList(
                    com.webservice.GetFleetShipFullInfoByShipListResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getFleetShipFullInfoByShipList operation
           */
            public void receiveErrorgetFleetShipFullInfoByShipList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getWeatherBaseUrl method
            * override this method for handling normal response from getWeatherBaseUrl operation
            */
           public void receiveResultgetWeatherBaseUrl(
                    com.webservice.GetWeatherBaseUrlResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getWeatherBaseUrl operation
           */
            public void receiveErrorgetWeatherBaseUrl(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getTyphoonLatestForecast method
            * override this method for handling normal response from getTyphoonLatestForecast operation
            */
           public void receiveResultgetTyphoonLatestForecast(
                    com.webservice.GetTyphoonLatestForecastResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getTyphoonLatestForecast operation
           */
            public void receiveErrorgetTyphoonLatestForecast(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_ShipPicFilenameAry method
            * override this method for handling normal response from get_ShipPicFilenameAry operation
            */
           public void receiveResultget_ShipPicFilenameAry(
                    com.webservice.Get_ShipPicFilenameAryResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_ShipPicFilenameAry operation
           */
            public void receiveErrorget_ShipPicFilenameAry(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSearchRecByKeyInFleet method
            * override this method for handling normal response from getSearchRecByKeyInFleet operation
            */
           public void receiveResultgetSearchRecByKeyInFleet(
                    com.webservice.GetSearchRecByKeyInFleetResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSearchRecByKeyInFleet operation
           */
            public void receiveErrorgetSearchRecByKeyInFleet(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkVehicleDistributionRetPhone method
            * override this method for handling normal response from checkVehicleDistributionRetPhone operation
            */
           public void receiveResultcheckVehicleDistributionRetPhone(
                    com.webservice.CheckVehicleDistributionRetPhoneResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkVehicleDistributionRetPhone operation
           */
            public void receiveErrorcheckVehicleDistributionRetPhone(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayGg method
            * override this method for handling normal response from get_FairplayGg operation
            */
           public void receiveResultget_FairplayGg(
                    com.webservice.Get_FairplayGgResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayGg operation
           */
            public void receiveErrorget_FairplayGg(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getShipFullInfoFromCtrackCtrackInfoE method
            * override this method for handling normal response from getShipFullInfoFromCtrackCtrackInfoE operation
            */
           public void receiveResultgetShipFullInfoFromCtrackCtrackInfoE(
                    com.webservice.GetShipFullInfoFromCtrackCtrackInfoEResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getShipFullInfoFromCtrackCtrackInfoE operation
           */
            public void receiveErrorgetShipFullInfoFromCtrackCtrackInfoE(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getServerCurDataTimeForMs method
            * override this method for handling normal response from getServerCurDataTimeForMs operation
            */
           public void receiveResultgetServerCurDataTimeForMs(
                    com.webservice.GetServerCurDataTimeForMsResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getServerCurDataTimeForMs operation
           */
            public void receiveErrorgetServerCurDataTimeForMs(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAttentionShip method
            * override this method for handling normal response from getAttentionShip operation
            */
           public void receiveResultgetAttentionShip(
                    com.webservice.GetAttentionShipResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAttentionShip operation
           */
            public void receiveErrorgetAttentionShip(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for del_ShipPic method
            * override this method for handling normal response from del_ShipPic operation
            */
           public void receiveResultdel_ShipPic(
                    com.webservice.Del_ShipPicResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from del_ShipPic operation
           */
            public void receiveErrordel_ShipPic(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayOwnership method
            * override this method for handling normal response from get_FairplayOwnership operation
            */
           public void receiveResultget_FairplayOwnership(
                    com.webservice.Get_FairplayOwnershipResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayOwnership operation
           */
            public void receiveErrorget_FairplayOwnership(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateShipInfo method
            * override this method for handling normal response from updateShipInfo operation
            */
           public void receiveResultupdateShipInfo(
                    com.webservice.UpdateShipInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateShipInfo operation
           */
            public void receiveErrorupdateShipInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for searchHint method
            * override this method for handling normal response from searchHint operation
            */
           public void receiveResultsearchHint(
                    com.webservice.SearchHintResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from searchHint operation
           */
            public void receiveErrorsearchHint(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for checkVehicleDistributionPhone method
            * override this method for handling normal response from checkVehicleDistributionPhone operation
            */
           public void receiveResultcheckVehicleDistributionPhone(
                    com.webservice.CheckVehicleDistributionPhoneResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkVehicleDistributionPhone operation
           */
            public void receiveErrorcheckVehicleDistributionPhone(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayLiquid method
            * override this method for handling normal response from get_FairplayLiquid operation
            */
           public void receiveResultget_FairplayLiquid(
                    com.webservice.Get_FairplayLiquidResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayLiquid operation
           */
            public void receiveErrorget_FairplayLiquid(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for get_FairplayClass method
            * override this method for handling normal response from get_FairplayClass operation
            */
           public void receiveResultget_FairplayClass(
                    com.webservice.Get_FairplayClassResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from get_FairplayClass operation
           */
            public void receiveErrorget_FairplayClass(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSearchCountByKeyAndTypeInShipBaseInfo method
            * override this method for handling normal response from getSearchCountByKeyAndTypeInShipBaseInfo operation
            */
           public void receiveResultgetSearchCountByKeyAndTypeInShipBaseInfo(
                    com.webservice.GetSearchCountByKeyAndTypeInShipBaseInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSearchCountByKeyAndTypeInShipBaseInfo operation
           */
            public void receiveErrorgetSearchCountByKeyAndTypeInShipBaseInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for update_ShipPic method
            * override this method for handling normal response from update_ShipPic operation
            */
           public void receiveResultupdate_ShipPic(
                    com.webservice.Update_ShipPicResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from update_ShipPic operation
           */
            public void receiveErrorupdate_ShipPic(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getShipFullInfoByOperidShipId method
            * override this method for handling normal response from getShipFullInfoByOperidShipId operation
            */
           public void receiveResultgetShipFullInfoByOperidShipId(
                    com.webservice.GetShipFullInfoByOperidShipIdResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getShipFullInfoByOperidShipId operation
           */
            public void receiveErrorgetShipFullInfoByOperidShipId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getShipFullInfoByOperidShipIdList method
            * override this method for handling normal response from getShipFullInfoByOperidShipIdList operation
            */
           public void receiveResultgetShipFullInfoByOperidShipIdList(
                    com.webservice.GetShipFullInfoByOperidShipIdListResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getShipFullInfoByOperidShipIdList operation
           */
            public void receiveErrorgetShipFullInfoByOperidShipIdList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMobileTrackInfo method
            * override this method for handling normal response from getMobileTrackInfo operation
            */
           public void receiveResultgetMobileTrackInfo(
                    com.webservice.GetMobileTrackInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMobileTrackInfo operation
           */
            public void receiveErrorgetMobileTrackInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getFleetMobilesInfo method
            * override this method for handling normal response from getFleetMobilesInfo operation
            */
           public void receiveResultgetFleetMobilesInfo(
                    com.webservice.GetFleetMobilesInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getFleetMobilesInfo operation
           */
            public void receiveErrorgetFleetMobilesInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getServerCurDataTimeForS method
            * override this method for handling normal response from getServerCurDataTimeForS operation
            */
           public void receiveResultgetServerCurDataTimeForS(
                    com.webservice.GetServerCurDataTimeForSResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getServerCurDataTimeForS operation
           */
            public void receiveErrorgetServerCurDataTimeForS(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for isExitCtrackWxInfo method
            * override this method for handling normal response from isExitCtrackWxInfo operation
            */
           public void receiveResultisExitCtrackWxInfo(
                    com.webservice.IsExitCtrackWxInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from isExitCtrackWxInfo operation
           */
            public void receiveErrorisExitCtrackWxInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for delAttentionShip method
            * override this method for handling normal response from delAttentionShip operation
            */
           public void receiveResultdelAttentionShip(
                    com.webservice.DelAttentionShipResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from delAttentionShip operation
           */
            public void receiveErrordelAttentionShip(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for add_ShipPic method
            * override this method for handling normal response from add_ShipPic operation
            */
           public void receiveResultadd_ShipPic(
                    com.webservice.Add_ShipPicResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from add_ShipPic operation
           */
            public void receiveErroradd_ShipPic(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCellDistribution method
            * override this method for handling normal response from getCellDistribution operation
            */
           public void receiveResultgetCellDistribution(
                    com.webservice.GetCellDistributionResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCellDistribution operation
           */
            public void receiveErrorgetCellDistribution(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSearchRecByKeyAndTypeInShipBaseInfo method
            * override this method for handling normal response from getSearchRecByKeyAndTypeInShipBaseInfo operation
            */
           public void receiveResultgetSearchRecByKeyAndTypeInShipBaseInfo(
                    com.webservice.GetSearchRecByKeyAndTypeInShipBaseInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSearchRecByKeyAndTypeInShipBaseInfo operation
           */
            public void receiveErrorgetSearchRecByKeyAndTypeInShipBaseInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCodeEnCnInfo method
            * override this method for handling normal response from getCodeEnCnInfo operation
            */
           public void receiveResultgetCodeEnCnInfo(
                    com.webservice.GetCodeEnCnInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCodeEnCnInfo operation
           */
            public void receiveErrorgetCodeEnCnInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPitateInfo method
            * override this method for handling normal response from getPitateInfo operation
            */
           public void receiveResultgetPitateInfo(
                    com.webservice.GetPitateInfoResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPitateInfo operation
           */
            public void receiveErrorgetPitateInfo(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getSearchCountByKeyInFleet method
            * override this method for handling normal response from getSearchCountByKeyInFleet operation
            */
           public void receiveResultgetSearchCountByKeyInFleet(
                    com.webservice.GetSearchCountByKeyInFleetResponseE result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSearchCountByKeyInFleet operation
           */
            public void receiveErrorgetSearchCountByKeyInFleet(java.lang.Exception e) {
            }
                


    }
    