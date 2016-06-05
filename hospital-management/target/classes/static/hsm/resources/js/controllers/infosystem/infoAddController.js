'use strict';
var hsmInfoAddApp= angular.module('myApp','')

hsmInfoAddApp.controller('hsmInfoAddController', function($scope,$http,$location,$rootScope,searchData) {
    $scope.addInfoApp=function()
    {
        var success=0;
        var parameter = JSON.stringify({type:"information",empId:$scope.empId,
            sysName:$scope.sysName,backupAdministrator:$scope.backupAdministrator,purposeDet:$scope.purposeDet,
            administrator:$scope.administrator});

        $http({
            url: 'http://asmapp.cloudapp.net/hsm/secure/infoAdd',
            method: "POST",
            data: parameter,
            crossDomain:true,
            headers:{
                'Authorization':$rootScope.jwtTokenVal
            }
        }).success(function(response){
            $location.path('/infoSystems');
            $rootScope.$emit("CallInfoParentMethod",'InfoAddSucess');
        }).error(function(response){
            $scope.addInfoErrorFlag=true;
            $location.path('/addInfoSystems');
        });
    }
})