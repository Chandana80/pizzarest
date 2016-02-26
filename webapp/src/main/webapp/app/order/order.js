'use strict';

angular.module('myApp.order', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/order', {
    templateUrl: 'order/order.html',
    controller: 'OrderCtrl'
  });
}])

.controller('OrderCtrl', ['$scope','$http',function($scope,$http) {
    $scope.model = {};
    $scope.model.orderDetail = {};
    $scope.selectedToppings = "";
    $http({
      method: 'GET',
      url: '/pizzarest/bases'
    }).then(function successCallback(response) {
      if (response.data) {
          $scope.base = response.data[0];
          $scope.model.total = $scope.base.price;
          $scope.model.qty = 1;
      }
    }, function errorCallback(response) {
      // called asynchronously if an error occurs
      // or server returns response with an error status.
    });

    $http({
        method: 'GET',
        url: '/pizzarest/toppings'
    }).then(function successCallback(response) {
        if (response.data) {

            $scope.toppings = response.data;
        }
    }, function errorCallback(response) {
        // called asynchronously if an error occurs
        // or server returns response with an error status.
    });

    $scope.updateDetails = function(topping) {
        if (document.getElementById(topping.id)) {
            if (document.getElementById(topping.id).checked) {
                $scope.model.total = $scope.model.total + topping.pricePerServing;
                if ($scope.selectedToppings != '') {
                    $scope.selectedToppings = $scope.selectedToppings + ',' + topping.id;
                } else {
                    $scope.selectedToppings = ''+topping.id;
                }

            } else {
                $scope.model.total = $scope.model.total - topping.pricePerServing;
                if ($scope.selectedToppings.indexOf(',') >= 0) {
                    $scope.selectedToppings = $scope.selectedToppings.replace(',' + topping.id, '');
                } else {
                    $scope.selectedToppings = $scope.selectedToppings.replace(topping.id, '');
                }
            }
        }
    }
    $scope.cancel = function() {
        $scope.model.total = $scope.base.price;
        $scope.model.contact  = '';
        $scope.model.name = '';
    }

    $scope.addOrder = function() {
        $scope.model.orderDetail.base = {};
        $scope.model.orderDetail.base.id = $scope.base.id;
        $scope.model.orderDetail.toppings = $scope.selectedToppings;
        $scope.model.orderDetail.total = $scope.model.total;
        $scope.errors = null;
        $http({
            method: 'POST',
            url: '/pizzarest/orders/',
            data: $scope.model,
            dataType: 'json',
            headers: {
                "Content-Type": "application/json"
            }
        }).then(function successCallback(response) {
            if (response.data.errors) {
                $scope.errors = response.data.errors;
            } else {

                alert("Your order has been placed successfully");
            }
        }, function errorCallback(response) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
        });

    }

    $scope.validInput = function(){

    }
}]);