'use strict';

angular.module('ProjectApp').controller('BookingController',
	[ 'BookingService', '$scope', '$location', '$stateParams', function(BookingService, $scope, $location, $stateParams) {
		var self = this;
		self.motorbike = {};
		self.motorbikes = [];
	// si sólo devuelve una moto y está alquilada: vista con boton devolver
	// de lo contrario: vista de motos disponibles
	// lo puedo hacer en la vista con ngIf
	}
	]);