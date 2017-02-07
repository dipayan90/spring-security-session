angular.module('sessionWebApp')
    .controller('mainController',['$scope','$http','$filter','$log',function($scope,$http,$filter,$log){
        "use strict";

        $scope.user = "";

        function init(){
            $http.get("/userDetail")
                .then(function(userData){
                    $scope.user = userData.data.username;
                },function(err){
                    $log.error("Error getting the user data "+ err);
                });
        }

        init();

    }]);
