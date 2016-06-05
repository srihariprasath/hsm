'use strict';
var hsmAstView= angular.module('myApp','')

hsmAstView.controller('hsmAstViewCntrl', function($scope,$http,$location,$rootScope,searchAstData) {
    $scope.viewAst=function(id)
    {  
         $rootScope.astDetails=searchAstData.get(id,$scope);
         $location.path('/computerDetails');
    }

    $scope.editAstView=function(id)
    {  
         $rootScope.astDetails=searchAstData.get(id,$scope);
         $location.path('/editComputers');
    }



    $scope.editAstVal=function()
    { 
        var parameter = JSON.stringify({type:"assests",assetkey:$rootScope.astDetails.assetkey,empId:$rootScope.astDetails.empId,
            assetName:$rootScope.astDetails.assetName,description:$rootScope.astDetails.description,function:$rootScope.astDetails.function,
            assetLocation:$rootScope.astDetails.assetLocation,serialNo:$rootScope.astDetails.serialNo,adminAccount:$rootScope.astDetails.adminAccount,
            adminPassword:$rootScope.astDetails.adminPassword,operatingSys:$rootScope.astDetails.operatingSys,ipAddress1:$rootScope.astDetails.ipAddress1,
            ipAddress2:$rootScope.astDetails.ipAddress2,comments:$rootScope.astDetails.comments});
        $http({
            url: 'http://asmapp.cloudapp.net/hsm/secure/assetEdit',
            method: "POST",
            data: parameter,
            crossDomain:true,
            headers:{
                'Authorization':$rootScope.jwtTokenVal
            }
        }).success(function (response) {
                 $scope.editAsstError=false;
                 $rootScope.$emit("CallAstParentMethod",'AstEditSuccess');
        }).error(function (response) {
                 $scope.editAsstError=true;
                 $location.path('/editComputers');
        });
    }

    $scope.deleteAstVal=function(id)
    {  
        var parameter = JSON.stringify({type:"assests",assetkey:id});
        $http({
            url: 'http://asmapp.cloudapp.net/hsm/secure/assetDelete',
            method: "POST",
            data: parameter,
            crossDomain:true,
            headers:{
                'Authorization':$rootScope.jwtTokenVal
            }
        }).success(function(response){
            $rootScope.$emit("CallAstParentMethod",'AstDeleteSuccess');
        }).error(function(response){
            $rootScope.$emit("CallAstParentMethod",'AstDeleteFailure');
            $location.path('/computers');
        });
    }

})
