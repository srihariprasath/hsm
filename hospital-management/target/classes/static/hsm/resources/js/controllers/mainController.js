var myApp = angular.module('myApp', ['ui.router']);

myApp.controller('MainCtrl', ['$scope', '$location', '$anchorScroll', function($scope, $location, $anchorScroll) {
	$scope.mobileMenu = true;
	$scope.mobileViewMenu = function() {
		$scope.mobileMenu = $scope.mobileMenu === false ? true: false;
	}
	
	angular.element(document).ready(function () {
        $location.hash('top');
    });
	$anchorScroll();
}]);

myApp.config(function($stateProvider, $urlRouterProvider) {
	// default route
    $urlRouterProvider.otherwise("/login");
	var loginHeader = {
		templateUrl: 'resources/views/login-header.html',
		controller: function($scope) {}
	}
	var mainHeader = {
       templateUrl: 'resources/views/main-header.html',
       controller: function($scope) {}
	}
    var footer = {
		templateUrl: 'resources/views/footer.html',
        controller: function($scope) {}
	}
    
	// ui router states
    $stateProvider
		//Login Screen
        .state('login', {
            url: "/login",
            views: {
                header: loginHeader,
                content: {
                    templateUrl: 'resources/views/login.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Employee Details
        .state('employeeDetails', {
            url: "/employeeDetails",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/employee-details.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Information systems
        .state('infoSystems', {
            url: "/infoSystems",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/information-systems.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Add Information systems
        .state('addInfoSystems', {
            url: "/addInfoSystems",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/add-info-systems.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })

        //Edit Information systems
        .state('editInfoSystems', {
            url: "/editInfoSystems",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/edit-info-systems.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Computers
        .state('computers', {
            url: "/computers",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/computers.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Add Computers
        .state('addComputers', {
            url: "/addComputers",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/add-computers.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Computer Details
        .state('computerDetails', {
            url: "/computerDetails",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'views/computer-details.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Policies
        .state('policies', {
            url: "/policies",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/policies.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Add Employees
		.state('addEmployee', {
            url: "/addEmployee",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/add-employee.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Employee Data
		.state('employeeData', {
            url: "/employeeData",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/employee-datas.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        })
		
		//Edit Employees
		.state('editEmployees', {
            url: "/editEmployees",
            views: {
                header: mainHeader,
                content: {
                    templateUrl: 'resources/views/edit-employee.html',
                    controller: function($scope) {}
                },
                footer: footer
            }
        });
});