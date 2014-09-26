(function() {

  /* App Module */
  var taskApp = angular.module('taskApp', [ 'ngRoute', 'actionControllers', 'actionServices' ]);

  taskApp.config([ '$routeProvider', function($routeProvider) {
    $routeProvider.when('/task/upd/:taskId', {
      templateUrl : '../../snippets/taskupd.actions.html',
      controller : 'ActionCtrl'
    }).when('/phones/:taskId', {
      templateUrl : 'snippets/phone-detail.html',
      controller : 'PhoneDetailCtrl'
    }).otherwise({
      redirectTo : 'snippets/noroute.html'
    });
  } ]);

  /* Controllers */
  var actionControllers = angular.module('actionControllers', []);

  actionControllers.controller('ActionCtrl', [
      '$scope',
      '$http',
      function($scope, $http, $location) {
        $scope.hola = 'holadentrodelcontrolador';
        $scope.data = [];
        $scope.datas = [];
        $scope.getaction = function(actionId) {
          $http.get('../../taskaction/get/' + actionId).success(
              function(data, status, headers, config) {
                // this callback will be called asynchronously
                // when the response is available
                $scope.data = data;
                console.log('Success getting ../taskaction/get/' + actionId);
              }).error(function(data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
            console.log('Error getting ../taskaction/get/' + actionId);
          })
        };

        $scope.getactions = function(taskId) {
          //alert('../../getactions/' + taskId);
          $http.get('../../getactions/' + taskId).success(function(data, status, headers, config) {
            // this callback will be called asynchronously
            // when the response is available
            $scope.datas = data;
            console.log('Success getting ../getactions/' + taskId);
          }).error(function(data, status, headers, config) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
            console.log('Error getting ../getactions/' + taskId);
          });
        };

        $scope.sendaction = function() {
          var dataPost = {
            idTaskAction : $scope.data.action.idTaskAction,
            idTask : $scope.data.action.idTask,
            actionname : $scope.data.action.actionname,
            date : $scope.data.action.date,
            description : $scope.data.action.description,
            duration : $scope.data.action.duration,
            idUser : $scope.data.action.user.idUser
          };

          /*
           * var dataPost = { idTaskAction : 2, idTask : 3, actionname :
           * 'hellokitty', description : 'description hellokitty' };
           */

          $http({
            url : '../../taskaction/send/',
            method : 'POST',
            data : dataPost,
            headers : {
              'Content-Type' : 'application/json'
            }
          }).success(function(data, status) {
            if (data.msg != '') {
              console.log('Success sending action data.msg: ' + data.msg);
            } else {
              console.log('Success sending action data.errr' + data.error);
            }
          }).error(function(data, status, headers) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
            alert('Error sending action data ' + JSON.stringify(dataPost));

            console.log('@Error sending action status: ' + status);
            console.log('@headers: ' + headers);
            console.log('@failure message: ' + data.msg);
          });
        };

        /*$scope.sendaction1 = function($scope) {
          var error;
          var success;

          var dataPost = {
            idTaskAction : 2,
            idTask : 3,
            actionname : 'hellokitty',
            description : 'description hellokitty'
          };

          alert(dataPost);
          alert(JSON.stringify(dataPost));

          $.ajax({
            type : 'POST',
            url : '../../taskaction/send',
            data : JSON.stringify(dataPost),
            success : function(data, textStatus, jqXHR) {
              console.log('@Success sending action status: ' + textStatus);
            },
            error : function(jqXHR, textStatus, errorThrown) {
              console.log('@Error sending action status: ' + textStatus);
            },
            contentType : "application/json; charset=utf-8",
            dataType : "json"
          });
        };*/

        $scope.test = function() {
          alert(' hello ');
        }
      } ]);

  actionControllers.controller('ActionListCtrl', [ '$scope', 'Action', function($scope, Action) {
    $scope.action = Action.query();
    // $scope.orderProp = 'age';
  } ]);

  /* Services */

  var actionServices = angular.module('actionServices', [ 'ngResource' ]);

  actionServices.factory('Action', [ '$resource', function($resource) {
    return $resource('getactions/:taskId', {}, {
      query : {
        method : 'GET',
        params : {
          taskId : 'actions'
        },
        isArray : true
      }
    });
  } ]);

})();