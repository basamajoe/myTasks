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
        $scope.action = []; // new action
        $scope.data = []; // data of action recovered
        $scope.datas = []; // data of list of actions recovered

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

        $scope.sendaction = function(i) {
          var dataPost = {
            idTaskAction : $scope.datas.actions[i].idTaskAction,
            idTask : $scope.datas.actions[i].task.idTask,
            actionname : $scope.datas.actions[i].actionname,
            date : $scope.datas.actions[i].date,
            description : $scope.datas.actions[i].description,
            duration : $scope.datas.actions[i].duration,
            idUser : $scope.datas.actions[i].user.idUser
          };

          $http({
            url : '../../taskaction/send',
            method : 'POST',
            data : JSON.stringify(dataPost),
            headers : {
              'Content-Type' : 'application/json'
            }
          }).success(function(data, status) {
            $scope.datas.actions[i].idUpdated = true;
            if (data.msg != '') {
              console.log('Success sending action data.msg: ' + data.msg);
            } else {
              console.log('Success sending action data.errr' + data.error);
            }
          }).error(function(data, status, headers) {
            // called asynchronously if an error occurs
            // or server returns response with an error status.
            
            $scope.datas.actions[i].isUpdated = false;
            console.log('@Error sending action status: ' + status);
            console.log('@headers: ' + headers);
            console.log('@failure message: ' + data.msg);
          });
        };

        $scope.closeModal = function(i) {
          $('#action' + $scope.datas.actions[i].idTaskAction).modal('hide');
        };

        /*
         * $scope.sendaction1 = function() { var error; var success;
         * 
         * var dataPost = { idTaskAction : $scope.data.action.idTaskAction,
         * idTask : $scope.data.action.idTask, actionname :
         * $scope.datas.action.actionname, date : $scope.data.action.date,
         * description : $scope.data.action.description, duration :
         * $scope.data.action.duration, idUser : $scope.data.action.user.idUser };
         * 
         * alert(dataPost); alert(JSON.stringify(dataPost));
         * 
         * $.ajax({ type : 'POST', url : '../../taskaction/send', data :
         * JSON.stringify(dataPost), success : function(data, textStatus, jqXHR) {
         * console.log('@Success sending action status: ' + textStatus); },
         * error : function(jqXHR, textStatus, errorThrown) {
         * console.log('@Error sending action status: ' + textStatus); },
         * contentType : "application/json; charset=utf-8", dataType : "json"
         * }); };
         */

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