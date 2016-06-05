'use strict';
var hsmInfoView= angular.module('myApp','')

hsmInfoView.controller('hsmInfoViewCntrl', function($scope,$http,$location,$rootScope,searchInfoData) {
   

    $scope.editInfoView=function(id)
    {  
         $rootScope.editinfoDetails=searchInfoData.get(id,$scope);
         $location.path('/editInfoSystems');
    }


    $scope.editInfosystem=function()
    {  
            var parameter = JSON.stringify({type:"information",adminID:$rootScope.editinfoDetails.adminID,empId:$rootScope.editinfoDetails.empId,
                sysName:$scope.editinfoDetails.sysName,backupAdministrator:$scope.editinfoDetails.backupAdministrator,
                purposeDet:$scope.editinfoDetails.purposeDet,administrator:$scope.editinfoDetails.administrator});

            $http({
                url: 'http://asmapp.cloudapp.net/hsm/secure/infoEdit',
                method: "POST",
                data: parameter,
                crossDomain:true,
                headers:{
                    'Authorization':$rootScope.jwtTokenVal
                }
            }).success(function(response){
                $scope.showInfoEditError=false;
                $rootScope.$emit("CallInfoParentMethod",'InfoEditSucess');
            }).error(function(response){
                $scope.showInfoEditError=true;
                $location.path('/editInfoSystems');
            });
    }

    $scope.deleteInfo=function(id)
    {  
            var parameter = JSON.stringify({type:"information",adminID:id});
            var success=0;
            $http({
                url: 'http://asmapp.cloudapp.net/hsm/secure/infoDelete',
                method: "POST",
                data: parameter,
                crossDomain:true,
                headers:{
                    'Authorization':$rootScope.jwtTokenVal
                }
            }).success(function(response){
                $rootScope.$emit("CallInfoParentMethod",'InfoDeleteSucess');
                $location.path('/infoSystems');
        }).error(function(response){
            $rootScope.$emit("CallInfoParentMethod",'InfoDeleteFailure');
        });

    }

})
