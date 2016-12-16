'use strict';

angular.module('myApp').factory('FileService',
		[ '$http', '$q', function($http, $q) {

			var REST_SERVICE_URI = 'http://localhost:8080/file/';

			var factory = {
				fetchAllFiles : fetchAllFiles,
				downloadFile : downloadFile
			};

			return factory;

			function fetchAllFiles() {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI).then(function(response) {
					deferred.resolve(response.data);
				}, function(errResponse) {
					console.error('Error while fetching Files');
					deferred.reject(errResponse);
				});
				return deferred.promise;
			}
			function downloadFile(id) {
				var deferred = $q.defer();
				$http.get(REST_SERVICE_URI + id, {
					responseType : 'arraybuffer'
				}).then(function(response) {
					deferred.resolve(response.data);
				}, function(errResponse) {
					console.error('Error while downloading File');
					deferred.reject(errResponse);
				});
				return deferred.promise;
			}

		} ]);
