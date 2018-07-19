'use strict';
angular.module('showcase', ['datatables']);
angular.module('ProjectApp').controller('UserController',
	[ 'UserService', '$scope', '$location', '$stateParams', '$localStorage', function(UserService, $scope, $location, $stateParams, $localStorage) {

		var self = this;
		self.user = {};
		self.users = [];
		self.submit = submit;
		self.getAllUsers = getAllUsers;
		self.createUser = createUser;
		self.updateUser = updateUser;
		self.removeUser = removeUser;
		self.editUser = editUser;
		self.showUser = showUser;
		self.reset = reset;

		self.successMessage = '';
		self.errorMessage = '';
		self.done = false;

		self.onlyIntegers = /^\d+$/;
		self.onlyNumbers = /^\d+([,.]\d+)?$/;


		function submit() {
			console.log('Submitting');
			if (self.user.id_user === undefined || self.user.id_user === null) {
				console.log('Saving New User', self.user);
				createUser(self.user);
			} else {
				updateUser(self.user, self.user.id_user);
				console.log('User updated with id ', self.user.id_user);
			}
		}

		function createUser(user) {
			console.log('About to create user');
			UserService.createUser(user)
				.then(
					function(response) {
						console.log('User created successfully');
						self.successMessage = 'User created successfully';
						self.errorMessage = '';
						self.done = true;
						self.user = {};
						$location.path("users-list.html");
					},
					function(errResponse) {
						console.error('Error while creating User');
						self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
						self.successMessage = '';
					}
			);
		}


		function updateUser(user, id) {
			console.log('About to update user');
			UserService.updateUser(user, id)
				.then(
					function(response) {
						console.log('User updated successfully');
						self.successMessage = 'User updated successfully';
						self.errorMessage = '';
						self.done = true;
						$location.path("users-list.html");
					},
					function(errResponse) {
						console.error('Error while updating User');
						self.errorMessage = 'Error while updating User ' + errResponse.data;
						self.successMessage = '';
					}
			);
		}


		function removeUser(name) {
			console.log('About to remove User with id ' + name);
			UserService.removeUser(name)
				.then(
					function() {
						console.log('User ' + name + ' removed successfully');
					},
					function(errResponse) {
						console.error('Error while removing user ' + name + ', Error :' + errResponse.data);
					}
			);
		}



		function getAllUsers() {
			return UserService.getAllUsers();
		}

		function showUser(id) {
			$location.path("users-edit.html").search({
				id : id
			});
		}

		function editUser() {
			self.successMessage = '';
			self.errorMessage = '';
			var id = $stateParams.id;
			if (id !== null) {
				UserService.getUser(id).then(
					function(user) {
						self.user = user;
					},
					function(errResponse) {
						console.error('Error while removing user ' + id + ', Error :' + errResponse.data);
					}
				);
			}
		}





		function reset() {
			self.successMessage = '';
			self.errorMessage = '';
			self.user = {};
			$location.path("users-list.html");
		}
	}


	]);