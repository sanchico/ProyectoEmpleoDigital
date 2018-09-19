'use strict';


//Dentro de la carpeta booking el archivo list.html tiene dos funcionalidades: Cuando alquilamos motos y cuando se devuelve la moto
//dependiendo lo que vayamos a hacer muestra una cosa u otra.
//Si el atributo reserved está en true es disponible (No alquilada) pero si está en false no estará disponible (Alquilada)


angular.module('ProjectApp').controller('BookingController',
	[ 'BookingService', '$scope', '$location', '$stateParams','$localStorage','$sessionStorage', function(BookingService, $scope, $location, $stateParams, $localStorage,$sessionStorage) {
		var self = this;
		self.availableMotorbike = {};
		self.availableMotorbikes = [];
		self.booking={};
		self.getAvailableMotorbike= getAvailableMotorbike;
		self.submit1=submit1;
		self.devolver=devolver;
		self.ComprobacionDevolver=ComprobacionDevolver;
		self.showMotorbike=showMotorbike;
		self.editMotorbike4=editMotorbike4;
		self.reset=reset;
		
		self.successMessage = '';
		self.errorMessage = '';
		self.done = false;

		self.onlyIntegers = /^\d+$/;
		self.onlyNumbers = /^\d+([,.]\d+)?$/;
		
		function getAvailableMotorbike() {
			console.log("getAvailabelMotorbikes")
			return BookingService.getAvailabelMotorbikes();
		}
		
	
		function submit1() {
			console.log('Submitting');
			if (self.availableMotorbike.id_Motorbike === undefined || self.availableMotorbike.id_Motorbike === null || self.availableMotorbike.reserved===true) {
				self.booking.pickupDate = $scope.pickdata;
				console.log('Saving New Motorbike'+ self.booking);
				alquilar(self.booking,self.availableMotorbike.id_Motorbike);
			} else {

                self.booking.devolucionReal = $scope.devolucionReal;
				self.booking.dropoffDate=$localStorage.bookingdro;
				self.booking.pickupDate=$localStorage.bookingPick;
				
				devolver(self.booking,self.availableMotorbike.id_Motorbike);
				console.log('Motorbike updated with id ',self.availableMotorbike.id_Motorbike);
			}
		}
		
		function showMotorbike(id) {
			console.log("showmotorbike booking"+id);
			$location.path("bookings-list.html").search({
				id : id
			});
		}
		
		function alquilar(booking,id) {
			console.log('About to create booking'+id);
			BookingService.alquilar(booking,id)
				.then(
					function(response) {
						console.log('booking alquilar successfully');
						self.errorMessage = '';
						self.done = true;	
						console.log('booking enviamos a list');
						$location.path("bookings-showMap.html");
					},
					function(errResponse) {
						console.error('Error while devolver  booking');
						self.errorMessage = 'Error while devolver  booking: ' + errResponse.data.errorMessage;
						self.successMessage = '';
					}
			);
		}
		
		function devolver(booking,id) {
			console.log('About to return motorbike'+id);
			BookingService.devolver(booking,id)
				.then(
					function(response) {
						console.log('booking devolver successfully');
						self.successMessage = 'booking devolver successfully';
						self.errorMessage = '';
						self.done = true;
						self.booking = {};
						self.availableMotorbike = {};
						$location.path("bookings-showMap.html");
					
					},
					function(errResponse) {
						console.error('Error while devolver  Motorbike');
						self.errorMessage = 'Error while devolver  Motorbike: ' + errResponse.data.errorMessage;
						self.successMessage = '';
					}
			);
		}
		
		
		function editMotorbike4() {
			self.successMessage = '';
			self.errorMessage = '';
			var id = $stateParams.id;
			console.log('editMotorbike4')
			if (id !== null) {
				BookingService.getMotorbike(id).then(
					function(motorbike) {
						self.availableMotorbike = motorbike;
					


						self.booking=$localStorage.booking;


						$localStorage.bookingdro = self.booking.dropoffDate;
						$localStorage.bookingPick =self.booking.pickupDate;


						// bookings/list.html tiene 2 partes: una para alquilar y otra para devolver
						// dependiendo si existe reserved es true(pantalla alquilar) o false(pantalla devolver) muestra una u otra
					// pickdata es la fecha que genera automáticamente en alquilar
						$scope.pickdata= new Date();

						// la fecha que genera en la pantalla devolver
						$scope.devolucionReal = new Date();


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
			self.availableMotorbikes = {};
			$location.path("bookings-showMap.html");
		}
		
		function ComprobacionDevolver() {
			console.log('About to ComprobacionDevolver motorbike');
			BookingService.ComprobacionDevolver()
				.then(
					function(response) {
						console.log('ComprobacionDevolver Motorbike  successfully');
						self.successMessage = 'Comprobacion devolver successfully';
						self.errorMessage = '';
						self.done = true;
						self.booking=response;
						$localStorage.booking=self.booking;
					
						var id=self.booking.motorbike_id;
						

						if( id===undefined || id===null){
							$location.path("bookings-showMap.html");
						}else {
							$location.path("bookings-list.html").search({
								id : id
							});
							
							
						}
						
					},
					function(errResponse) {
						console.error('Error while ComprobacionDevovler Motorbike');
						self.errorMessage = 'Error while Comprobacion Devvolver Motorbike: ' + errResponse.data.errorMessage;
						self.successMessage = '';
					}
			);
		}
	
		
	}
	]);