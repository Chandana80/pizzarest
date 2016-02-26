'use strict';

angular.module('myApp.manager', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/manager', {
    templateUrl: 'manager/manager.html',
    controller: 'ManagerCtrl'
  });
}])

.controller('ManagerCtrl', ['$scope','$http',function($scope,$http) {
  $scope.type = "";

  $scope.getToppings = function() {
    $scope.type = "toppings";
    $http({
      method: 'GET',
      url: '/pizzarest/toppings'
    }).then(function successCallback(response) {
      if (response.data) {
        $scope.toppings = response.data;
        $scope.showFields = false;
      }
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });
  }

  $scope.showDetails = function(value) {
    if (val != '') {
      $http({
        method: 'GET',
        url: '/pizzarest/toppings/' + $scope.toppingId
      }).then(function successCallback(response) {
        if (response.data) {
          $scope.toppings = response.data;
        }
      }, function errorCallback(response) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
      });
    } else {
      $scope.displayEmpty = true;
    }
  }

  $scope.editTopping = function() {
    $http({
      method: 'PUT',
      url: '/pizzarest/toppings/'+$scope.model.id,
      data:$scope.model,
      dataType:'json',
      headers: {
        "Content-Type": "application/json"
      }
    }).then(function successCallback(response) {
        $scope.getToppings();
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });
  }
  $scope.addTopping = function() {
    $http({
      method: 'POST',
      url: '/pizzarest/toppings/',
      data:$scope.model,
      dataType:'json',
      headers: {
        "Content-Type": "application/json"
      }
    }).then(function successCallback(response) {
        $scope.getToppings();
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });
  }
  $scope.deleteTopping = function(topping) {
    $http({
      method: 'DELETE',
      url: '/pizzarest/toppings/'+topping.id
    }).then(function successCallback(response) {
        $scope.getToppings();
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });
  }

  $scope.createTopping = function() {
    $scope.model = {};
    $scope.showFields = true;
  }

  $scope.showDetails = function(t) {
    $scope.model = t;
    $scope.showFields = true;
  }

  $scope.cancel = function() {
    $scope.showFields=false;
  }

  $scope.getBases = function() {
    $scope.type = "bases";
    $http({
      method: 'GET',
      url: '/pizzarest/bases'
    }).then(function successCallback(response) {
      if (response.data) {
        $scope.bases = response.data;
        $scope.showFields = false;
      }
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });
  }

  $scope.showBaseDetails = function(value) {
    if (val != '') {
      $http({
        method: 'GET',
        url: '/pizzarest/bases/' + $scope.baseId
      }).then(function successCallback(response) {
        if (response.data) {
          $scope.bases = response.data;
        }
      }, function errorCallback(response) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
      });
    } else {
      $scope.displayEmpty = true;
    }
  }

  $scope.editBase = function() {
    $http({
      method: 'PUT',
      url: '/pizzarest/bases/'+$scope.model.id,
      data:$scope.model,
      dataType:'json',
      headers: {
        "Content-Type": "application/json"
      }
    }).then(function successCallback(response) {
      $scope.getBases();
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });
  }
  $scope.addBase = function() {
    $http({
      method: 'POST',
      url: '/pizzarest/bases/',
      data:$scope.model,
      dataType:'json',
      headers: {
        "Content-Type": "application/json"
      }
    }).then(function successCallback(response) {
      $scope.getBases();
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });
  }
  $scope.createBase = function() {
    $scope.model = {};
    $scope.showFields = true;
  }

  $scope.showBaseDetails = function(t) {
    $scope.model = t;
    $scope.showFields = true;
  }

}]);