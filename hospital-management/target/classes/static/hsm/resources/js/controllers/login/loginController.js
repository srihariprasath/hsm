'use strict';
var hsmAngularapp= angular.module('myApp','')

hsmAngularapp.controller('hsmLoginCntrl', function($scope,$http,$location,$rootScope) {
    $scope.submit=function()
    {
        var parameter = JSON.stringify({type:"user", userName:$scope.username, password:$scope.password});
        $http.post('http://asmapp.cloudapp.net/hsm/login',parameter).success(function(response){
            
            $rootScope.input = response;
            if(response.loginFailure=='N'){
                $rootScope.jwtTokenVal='Bearer '+response.jwtToken;
                $rootScope.admin=response.admin;
                $rootScope.loginSuccess=true;
                $location.path('/employeeDetails');
            }else{
                $scope.loginErrorFlag=true;
                $location.path('/login');
            }
        }).error(function(response){
            $scope.loginErrorFlag=true;
            $rootScope.loginSuccess=false;
            $location.path('/login');
        });
    }
})
