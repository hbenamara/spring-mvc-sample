'use strict';

angular.module('myApp').controller('FileController',
		[ '$scope', 'FileService', function($scope, FileService) {
			var self = this;
			self.file = {
				id : null,
				title : '',
				description : '',
				creationDate : '',
				downloadLink : ''
			};
			self.files = [];

			self.downloadFile = downloadFile;

			fetchAllFiles();

			function fetchAllFiles() {
				FileService.fetchAllFiles().then(function(d) {
					self.files = d;
				}, function(errResponse) {
					console.error('Error while fetching Files');
				});
			}

			function downloadFile(id, name) {
				FileService.downloadFile(id).then(function(d) {
					var url = URL.createObjectURL(new Blob([ d ]));
					var a = document.createElement('a');
					a.href = url;
					a.download = name;
					a.target = '_blank';
					a.click();
				},

				function(errResponse) {
					console.error('Error while fetching Files');
				});
			}
		} ]);
