'use strict';

var services = angular.module('anti-criminal.services', [ 'ngResource' ]);

services.factory('DashboardService', function($q, $timeout, $rootScope) {

	var service = {}, reportListener = $q.defer(), socket = {
		client : null,
		stomp : null
	}, messageIds = [];

	service.RECONNECT_TIMEOUT = 2;
	
	service.receiveReport = function() {
		return reportListener.promise;
	};
	
	var reconnect = function() {
		$timeout(function() {
			initialize();
		}, this.RECONNECT_TIMEOUT);
	};

	var getReportMessage = function(data) {
		var message = JSON.parse(data), reportData = {};
		reportData.reportId = message.reportId;
		reportData.imeiNo = message.imeiNo;
		reportData.phoneNo = message.phoneNo;
		if(message.message.length > 500){
			reportData.message = message.message.substring( 0, 400 );
		}else {
			reportData.message = message.message;
		}
		reportData.reportType = message.reportType;
		reportData.reportTypeLabel = message.reportTypeLabel;
		reportData.name = message.name;
		reportData.reportedDate = message.reportedDate;
		reportData.branchId = message.branchId;
		reportData.wantedId = message.wantedId;
		reportData.reportAttachmentList = message.reportAttachmentList;
		
		if (_.contains(messageIds, message.id)) {
			reportData.self = true;
			messageIds = _.remove(messageIds, message.id);
		}
		return reportData;
	};

	var startListener = function() {
		service.REPORT_MESSAGE_TOPIC = "/report/reportMessage";
		socket.stomp.subscribe(service.REPORT_MESSAGE_TOPIC, function(data) {
			reportListener.notify(getReportMessage(data.body));
		});
	};
	
	service.initialize = function() {
		service.SOCKET_URL = "/anti-criminal/receiveReport";
		socket.client = new SockJS(service.SOCKET_URL);
		socket.stomp = Stomp.over(socket.client);
		socket.stomp.debug = null;
		socket.stomp.connect({}, startListener);
		socket.stomp.onclose = reconnect;
	};

	return service;

});

services.factory('ReportService', function($resource) {
	return {
		getReportList : $resource('/anti-criminal/getReportDtoList', {}, {
			query : {
				method : 'POST',
				headers : [ {
					'Content-Type' : 'application/json'
				} ],
				isArray : true
			}
		}),
		goDetail : $resource('/anti-criminal/goDetail', {}, {				
			query : {
				method : 'POST'
			}
		}),
		syncReport : $resource('/anti-criminal/syncReport', {}, {				
			query : {
				method : 'POST',
			}
		})
	}
});
