function sendactionn($scope) {
    
    var error;
    var success;
    
    var dataPost = {
        idTaskAction : $scope.data.action.idTaskAction,
        idTask : $scope.data.action.idTask,
        actionname: $scope.data.action.actionname,
        date: $scope.data.action.date, 
        description: $scope.data.action.description, 
        duration: $scope.data.action.duration,
        idUser: $scope.data.action.user.idUser
    };

    function success(data) {
        alert("Success sending action");
    }
    
    function error(data) {
        alert("Error sending message.");
    }
    
    $.ajax({
        "type": 'POST',
        "url": '<c:url value="/taskaction/send" />',
        "data": JSON.stringify(dataPost),
        "success": success,
        "error": error,
        contentType: "application/json",
        dataType: "json"
    });
    
    console.log('@Success sending action status: ' + success);
    console.log('@Error sending action status: ' + error);
}
    
/**
 * 
 
function sendActionCtrl($scope, $http) {
	$scope.errors = [];
	$scope.msgs = [];
 
	$scope.sendAction = function() {
	 
		$scope.errors.splice(0, $scope.errors.length); // remove all error messages
		$scope.msgs.splice(0, $scope.msgs.length);
		
		var dataPost = {'idTaskAction': $scope.data.action.idTaskAction, 
						'idTask': $scope.data.action.idTask,
						'actionname': $scope.data.action.actionname,
						'date': $scope.data.action.date,
						'description': $scope.data.action.description,
						'duration': $scope.data.action.duration,
						'idUser': $scope.data.action.user.idUser };
		
		$http.post('../../taskaction/send', dataPost)
		.success(function(data, status, headers, config) {
	        if (data.msg != '')
	        {
	        	console.log('Success sending action data.msg: ' + data.msg);
	        }
	        else
	        {
	        	console.log('Success sending action data.errr' + data.error);
	        }
	    }).error(function(data, status) { // called asynchronously if an error occurs
	    	// or server returns response with an error status.
	    	console.log('Error sending action' + status);
	    });
    }
}*/