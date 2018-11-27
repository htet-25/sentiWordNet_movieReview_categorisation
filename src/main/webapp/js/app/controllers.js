'use strict';

var app = angular.module('anti-criminal.controllers', [ ]);

app.controller('DashboardCtrl', ['$timeout', '$rootScope', '$scope', 'DashboardService', 'ReportService','$location', '$window',
function($timeout, $rootScope, $scope, DashboardService, ReportService,location, $window) {
	
	var controller = this;
	
	$scope.test = false;
	$scope.allList = [];
	$scope.normalList = [];
	$scope.wantedList = [];
	$scope.emergencyList = [];
	$scope.suspiciousList = [];
	$scope.reportType = 4;
	$scope.doSearch = false;
	var start = 10;
	
	/* Initialize Web SOCKET */
	DashboardService.initialize();
	
	/* Receive Report */
	DashboardService.receiveReport().then(null, null, function(message) {
		if(!$scope.doSearch){
			if($scope.reportType == 0 && $scope.reportType == message.reportType){
				$scope.normalList.splice(0, 0, message);
	        }else if($scope.reportType == 1 && $scope.reportType == message.reportType){
	        	$scope.wantedList.splice(0, 0, message);
	        }else if($scope.reportType == 2 && $scope.reportType == message.reportType){
	        	$scope.emergencyList.splice(0, 0, message);
	        }else if($scope.reportType == 3 && $scope.reportType == message.reportType){
	        	$scope.suspiciousList.splice(0, 0, message);
	        }else {
	        	$scope.allList.splice(0, 0, message);
	        }
		}
	});
	
	/* Init */
	$scope.init = function(){
		$scope.reportSearchDto = {'reportType': $scope.reportType, 'startOffset' : 0, 'size': 10};
		$scope.allList = ReportService.getReportList.query($scope.reportSearchDto, function(res){
			$scope.allList = res;
		});
	};
	
	$scope.init();
	
	/* Clear */
	$scope.clear = function(){
		$scope.allList = [];
		$scope.normalList = [];
		$scope.wantedList = [];
		$scope.emergencyList = [];
		$scope.suspiciousList = [];
	}
	
	/* Load Data */
	$scope.loadMore = function() {
		$scope.test = true;
		$timeout(function() {
			$scope.reportSearchParam = {
				  	'reportType': $scope.reportType, 
				  	'name': $scope.reportSearchDto.name, 
				  	'message': $scope.reportSearchDto.message, 
				  	'fromDate': PF('fromDate').getDate(), 
					'toDate': PF('toDate').getDate(),
					'wantedNoticeId': $scope.reportSearchDto.wantedNoticeId,
					'startOffset' : start, 'size': 10};
			
			if($scope.reportType == 0){
				$scope.reportSearchParam.startOffset = $scope.normalList.length;
				ReportService.getReportList.query($scope.reportSearchParam, function(res){
					for (var i = 0; i < res.length; i++) {
						$scope.normalList.push(res[i]);
					}
				});
			}else if($scope.reportType == 1){
				$scope.reportSearchParam.startOffset = $scope.wantedList.length;
				ReportService.getReportList.query($scope.reportSearchParam, function(res){
					for (var i = 0; i < res.length; i++) {
						$scope.wantedList.push(res[i]);
					}
				});
			}else if($scope.reportType == 2){
				$scope.reportSearchParam.startOffset = $scope.emergencyList.length;
				ReportService.getReportList.query($scope.reportSearchParam, function(res){
					for (var i = 0; i < res.length; i++) {
						$scope.emergencyList.push(res[i]);
					}
				});
			}else if($scope.reportType == 3){
				$scope.reportSearchParam.startOffset = $scope.suspiciousList.length;
				ReportService.getReportList.query($scope.reportSearchParam, function(res){
					for (var i = 0; i < res.length; i++) {
						$scope.suspiciousList.push(res[i]);
					}
				});
			}else if($scope.reportType == 4){
				$scope.reportSearchParam.startOffset = $scope.allList.length;
				ReportService.getReportList.query($scope.reportSearchParam, function(res){
					for (var i = 0; i < res.length; i++) {
						$scope.allList.push(res[i]);
					}
				});
			}
			start += 10;	
		}, 1000);
		
	  };
	  
	  /* InfiniteScroll */
	  angular.element($window)
		.bind(
			"scroll",
		 			function() {
					var windowHeight = "innerHeight" in window ? window.innerHeight
							: document.documentElement.offsetHeight;
					var body = document.body, html = document.documentElement;
					var docHeight = Math.max(body.scrollHeight,
							body.offsetHeight, html.clientHeight,
							html.scrollHeight, html.offsetHeight);
					var windowBottom = windowHeight + window.pageYOffset;
					if (windowBottom >= docHeight) {
						$scope.loadMore();
					}
			});
	  
	  /* Report Search */
	  $scope.reportSearch = function(){
		  $scope.clear();
		  start = 10;
		  $scope.reportSearchParam = {
				  	'reportType': $scope.reportType, 
				  	'name': $scope.reportSearchDto.name, 
				  	'message': $scope.reportSearchDto.message, 
					'fromDate': PF('fromDate').getDate(), 
					'toDate': PF('toDate').getDate(),
					'wantedNoticeId': $scope.reportSearchDto.wantedNoticeId,
					'startOffset' : 0, 'size': 10};
		  
		   if($scope.reportType == 0){
				$scope.normalList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.normalList = res;
				});
			}else if($scope.reportType == 1){
				$scope.wantedList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.wantedList = res;
				});
			}else if($scope.reportType == 2){
				$scope.emergencyList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.emergencyList = res;
				});
			}else if($scope.reportType == 3){
				$scope.suspiciousList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.suspiciousList = res;
				});
			}else if($scope.reportType == 4){
				$scope.allList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.allList = res;
				});
			}

		  $scope.doSearch = true;
	  };
	  
	  /* Report Type Tag */
	  $scope.getReportType = function (reportType){
		  $scope.clear();
		  start = 10;
		  $scope.reportType = reportType;
		  $scope.reportSearchParam = {
				  	'reportType': $scope.reportType, 
				  	'name': $scope.reportSearchDto.name, 
				  	'message': $scope.reportSearchDto.message, 
				  	'fromDate': PF('fromDate').getDate(), 
					'toDate': PF('toDate').getDate(),
					'wantedNoticeId': $scope.reportSearchDto.wantedNoticeId,
					'startOffset' : 0, 'size': 10};
		  
			if($scope.reportType == 0){
				$scope.normalList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.normalList = res;
				});
			}else if($scope.reportType == 1){
				$scope.wantedList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.wantedList = res;
				});
			}else if($scope.reportType == 2){
				$scope.emergencyList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.emergencyList = res;
				});
			}else if($scope.reportType == 3){
				$scope.suspiciousList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.suspiciousList = res;
				});
			}else if($scope.reportType == 4){
				$scope.allList = ReportService.getReportList.query($scope.reportSearchParam, function(res){
					$scope.allList = res;
				});
			}
	  };
	  
	  /* Report Detail */
	  $scope.goReportDetail = function(message){
		  $window.location.href = 'report/cmrs/criminalReportDetail.xhtml?reportId=' + message.reportId;
	  };
	  
	  $scope.syncReport = function(){
		  ReportService.syncReport.query();
	  };

} ]);
