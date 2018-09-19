'use strict';

angular.module('ProjectApp').factory('BookingService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
            	
            	loadAvailableMotorbikes: loadAvailableMotorbikes,
            	getAvailabelMotorbikes: getAvailabelMotorbikes,
            	ComprobacionDevolver:ComprobacionDevolver,
            	alquilar:alquilar,
            	devolver:devolver,
            	getMotorbike:getMotorbike
            };


            return factory;

            function loadAvailableMotorbikes() { //cargar motos disponibles de spring
                console.log('cargando motos disponibles desde spring');
                var deferred = $q.defer();
                $http.get(urls.BOOKING_SERVICE_API)
                    .then(
                        function (response) {
                            console.log('conseguidas motos disponibles'+response.data);
                            
                            $localStorage.availableMotorbikes = response.data;
                            console.log("$localStorage"+$localStorage.availableMotorbikes);
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error al cargar las motos disponibles');
                            $localStorage.availableMotorbikes = null;
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAvailabelMotorbikes(){ //cargar motos disponibles de localStorage
                console.log("getAvailabelMotorbikes")
            	return $localStorage.availableMotorbikes;
            }
            
            
            function alquilar(booking,id) {
              
                var deferred = $q.defer();
                $http.put(urls.BOOKING_SERVICE_API+"alquilar/"+id,booking)
                    .then(
                        function (response) {
                            
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while alquilar Motorbike : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            
            function devolver(booking,id) {
                console.log('devolver Motorbike');
                var deferred = $q.defer();
                $http.put(urls.BOOKING_SERVICE_API+"devolver/"+id,booking)
                    .then(
                        function (response) {
                            
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while devolver Motorbike : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
          
            function getMotorbike(id) {
                console.log('Fetching Motorbike with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.BOOKING_SERVICE_API + id)
                    .then(
                        function (response) {
                            console.log('Fetched successfully Motorbike with id :'+id);
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Motorbike with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
            
            
            function ComprobacionDevolver() {
                console.log('Fetching comprobaciondevolver with id :');
                var deferred = $q.defer();
                $http.post(urls.BOOKING_SERVICE_API)
                    .then(
                        function (response) {
                           
                            console.log("funcion estamos BookingService.ComprobacionDevolver "+response.data)
                            var id =response.data;
                           
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while loading Motorbike with id :');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }
            
        
        }
    ]);