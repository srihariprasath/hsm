'use strict';
var hsmAssetAddApp= angular.module('myApp','')

hsmAssetAddApp.controller('hsmAssetAddController', function($scope,$http,$location,$rootScope) {
    $scope.addAsset=function()
    {
        
        var parameter = JSON.stringify({type:"assests",empId:$scope.empId,
            assetName:$scope.assetName,description:$scope.description,function:$scope.function,
            assetLocation:$scope.assetLocation,serialNo:$scope.serialNo,adminAccount:$scope.adminAccount,
            adminPassword:$scope.adminPassword,operatingSys:$scope.operatingSys,ipAddress1:$scope.ipAddress1,
            ipAddress2:$scope.ipAddress2,comments:$scope.comments});

        $http({
            url: 'http://asmapp.cloudapp.net/hsm/secure/assetAdd',
            method: "POST",
            data: parameter,
            crossDomain:true,
            headers:{
                'Authorization':$rootScope.jwtTokenVal
            }
        }).success(function (response) {
                $rootScope.asstLstValue= response;
                $rootScope.$emit("CallAstParentMethod",'AstAddSuccess');
        }).error(function (response) {
                $scope.addAstErrorFlag=true;
                $location.path('/addComputers');
        });

    }
})