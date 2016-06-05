'use strict';
var hsmEmpView= angular.module('myApp','')

hsmEmpView.controller('hsmEmpViewCntrl', function($scope,$http,$location,$rootScope,searchData) {
    $scope.viewEmployee=function(id)
    {  
         $rootScope.empDetails=searchData.get(id,$scope);
         $location.path('/employeeData');
    }

    $scope.editEmployeeView=function(id)
    {  
         $rootScope.empDetails=searchData.get(id,$scope);
          $rootScope.phiAcessProp = {"type": "select", 
            "name": "status",
            "value": $rootScope.empDetails.phiAccess, 
            "values": [ "None", "Limited", "Full"] 
          };
          $rootScope.statusProp = {"type": "select", 
            "name": "status",
            "value": $rootScope.empDetails.status, 
            "values": [ "Yes", "No"] 
          };

         $location.path('/editEmployees');
    }

    $scope.editEmployee=function()
    { 
        $rootScope.error=""; 
        var parameter = JSON.stringify({type:"employee",empId:$rootScope.empDetails.empId,
            empFirstName:$rootScope.empDetails.empFirstName,
            empLastName:$rootScope.empDetails.empLastName,tilte:$rootScope.empDetails.tilte,
            supervisor:$rootScope.empDetails.supervisor,phiAccess:$rootScope.empDetails.phiAccess,
            location:$rootScope.empDetails.location,status:$rootScope.empDetails.status});
        $http({
            url: 'http://asmapp.cloudapp.net/hsm/secure/employeeEdit',
            method: "POST",
            data: parameter,
            crossDomain:true,
            headers:{
                'Authorization':$rootScope.jwtTokenVal
            }
        }).success(function (response) {
                 $scope.editEmployeeError=false;
                 $rootScope.$emit("CallEmpParentMethod",'EmpEditSucess');
        }).error(function (response) {
                $scope.editEmployeeError=true;
                $location.path('/editEmployees');
        });
    }

    $scope.deleteEmployee=function(id)
    {  
        $rootScope.error="";
        var parameter = JSON.stringify({type:"employee",empId:id});
        $http({
            url: 'http://asmapp.cloudapp.net/hsm/secure/employeeDelete1',
            method: "POST",
            data: parameter,
            crossDomain:true,
            headers:{
                'Authorization':$rootScope.jwtTokenVal
            }
        }).success(function (response) {
            $rootScope.$emit("CallEmpParentMethod",'EmpDeleteSucess');
        }).error(function(response){
            $rootScope.$emit("CallEmpParentMethod",'EmpDeleteFailure');

        });
    }

})
