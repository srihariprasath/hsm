'use strict';
var hsmEmpAddApp= angular.module('myApp','')

hsmEmpAddApp.controller('hsmEmpAddController', function($scope,$http,$location,$rootScope,searchData) {

    $scope.addEmployee=function()
    {
        var success=0;
        var parameter = JSON.stringify({type:"employee",empFirstName:$scope.empFirstName,
            empLastName:$scope.empLastName,tilte:$scope.tilte,supervisor:$scope.supervisor,
            phiAccess:$scope.phiAccess,location:$scope.location,status:$scope.status});
        $http({
            url: 'http://asmapp.cloudapp.net/hsm/secure/employeeAdd',
            method: "POST",
            data: parameter,
            crossDomain:true,
            headers:{
                'Authorization':$rootScope.jwtTokenVal
            }
        }).success(function (response) {
                 $scope.addEmployeeError=false;
                 $rootScope.$emit("CallEmpParentMethod", {});
            }).error(function (response) {
                 $scope.addEmployeeError=true;
                 $location.path('/addEmployee');
        });
    }


})