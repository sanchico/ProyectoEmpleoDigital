'use strict';

angular.module('ProjectApp').controller('MotorbikeController',
	[ 'MotorbikeService', '$scope', '$location', '$stateParams', function(MotorbikeService, $scope, $location, $stateParams) {

		var self = this;
		self.motorbike = {};
		self.motorbikes = [];

		self.submit = submit;
		self.getAllMotorbikes = getAllMotorbikes;
		self.createMotorbike = createMotorbike;
		self.updateMotorbike = updateMotorbike;
		self.removeMotorbike = removeMotorbike;
		self.editMotorbike = editMotorbike;
		self.showMotorbike = showMotorbike;
		self.reset = reset;

		self.successMessage = '';
		self.errorMessage = '';
		self.done = false;

		self.onlyIntegers = /^\d+$/;
		self.onlyNumbers = /^\d+([,.]\d+)?$/;

		function submit() {
			console.log('Submitting');
			if (self.motorbike.id_Motorbike === undefined || self.motorbike.id_Motorbike === null) {
				console.log('Saving New Motorbike', self.motorbike);
				createMotorbike(self.motorbike);
			} else {
				updateMotorbike(self.motorbike, self.motorbike.id_Motorbike);
				console.log('Motorbike updated with id ', self.motorbike.id_Motorbike);
			}
		}

		function createMotorbike(motorbike) {
			console.log('About to create motorbike');
			MotorbikeService.createMotorbike(motorbike)
				.then(
					function(response) {
						console.log('Motorbike created successfully');
						self.successMessage = 'Motorbike created successfully';
						self.errorMessage = '';
						self.done = true;
						self.motorbike = {};
						$location.path("motorbike-list.html");
					},
					function(errResponse) {
						console.error('Error while creating Motorbike');
						self.errorMessage = 'Error while creating Motorbike: ' + errResponse.data.errorMessage;
						self.successMessage = '';
					}
			);
		}


		function updateMotorbike(motorbike, id) {
			console.log('About to update user');
			MotorbikeService.updateMotorbike(motorbike, id)
				.then(
					function(response) {
						console.log('Motorbike updated successfully');
						self.successMessage = 'Motorbike updated successfully';
						self.errorMessage = '';
						self.done = true;
						$location.path("motorbikes-list.html");
					},
					function(errResponse) {
						console.error('Error while updating Motorbike');
						self.errorMessage = 'Error while updating Motorbike ' + errResponse.data;
						self.successMessage = '';
					}
			);
		}


		function removeMotorbike(id) {
			console.log('About to remove Motorbike with id ' + id);
			MotorbikeService.removeMotorbike(id)
				.then(
					function() {
						console.log('Motorbike ' + id + ' removed successfully');
					},
					function(errResponse) {
						console.error('Error while removing motorbike ' + id + ', Error :' + errResponse.data);
					}
			);
		}


		function getAllMotorbikes() {
			return MotorbikeService.getAllMotorbikes();
		}

		function showMotorbike(id) {
			$location.path("motorbikes-edit.html").search({
				id : id
			});
		}

		function editMotorbike() {
			self.successMessage = '';
			self.errorMessage = '';
			var id = $stateParams.id;
			if (id !== null) {
				MotorbikeService.getMotorbike(id).then(
					function(motorbike) {
						self.motorbike = motorbike;
					},
					function(errResponse) {
						console.error('Error while removing motorbike ' + id + ', Error :' + errResponse.data);
					}
				);
			}
		}

		function reset() {
			self.successMessage = '';
			self.errorMessage = '';
			self.user = {};
			$location.path("motorbikes-list.html");
		}
	}


	]);