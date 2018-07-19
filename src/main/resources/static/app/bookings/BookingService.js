'use strict';

angular.module('ProjectApp').factory('BookingService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
            	bookingHistory: bookingHistory,
            	getAvailabelMotorbikes: getAvailableMotorbikes
            };
//            };
//                loadAllBookings: loadAllBookings,
//                getAllBookings: getAllBookings,
//                getBooking: getBooking,
//                createBooking: createBooking,
//                updateBooking: updateBooking,
//                removeBooking: removeBooking
//            };

            return factory;

            function loadAvailableMotorbikes() { //cargar motos disponibles de spring
                console.log('cargando motos disponibles desde spring');
                var deferred = $q.defer();
                $http.post(urls.BOOKING_SERVICE_API)//salva no lo quiere en sessionStorage
                    .then(
                        function (response) {
                            console.log('conseguidas motos disponibles'+response.data);
                            
                            $localStorage.availableMotorbikes = response.data;
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

            function getAvailableMotorbikes(){ //cargar motos disponibles de localStorage
                return $localStorage.availableMotorbikes;
            }
            function bookingHistory() {}; //TODO
//
//            function getBooking(id) {
//                console.log('Fetching Booking with id :'+id);
//                var deferred = $q.defer();
//                $http.get(urls.MOTORBIKE_SERVICE_API + id)
//                    .then(
//                        function (response) {
//                            console.log('Fetched successfully Booking with id :'+id);
//                            deferred.resolve(response.data);
//                        },
//                        function (errResponse) {
//                            console.error('Error while loading Booking with id :'+id);
//                            deferred.reject(errResponse);
//                        }
//                    );
//                return deferred.promise;
//            }
//
//            function createBooking(booking) {
//                console.log('Creating Booking');
//                var deferred = $q.defer();
//                $http.post(urls.MOTORBIKE_SERVICE_API, motorBike)
//                    .then(
//                        function (response) {
//                            loadAllBookings();
//                            deferred.resolve(response.data);
//                        },
//                        function (errResponse) {
//                           console.error('Error while creating Booking : '+errResponse.data.errorMessage);
//                           deferred.reject(errResponse);
//                        }
//                    );
//                return deferred.promise;
//            }
//
//            function updateBooking(motorBike, id) {
//                console.log('Updating Booking with id '+id);
//                var deferred = $q.defer();
//                $http.put(urls.MOTORBIKE_SERVICE_API + id, motorBike)
//                    .then(
//                        function (response) {
//                            loadAllBookings();
//                            deferred.resolve(response.data);
//                        },
//                        function (errResponse) {
//                            console.error('Error while updating Booking with id :'+id);
//                            deferred.reject(errResponse);
//                        }
//                    );
//                return deferred.promise;
//            }
//
//            function removeBooking(id) {
//                console.log('Removing Booking with id '+id);
//                var deferred = $q.defer();
//                $http.delete(urls.MOTORBIKE_SERVICE_API + id)
//                    .then(
//                        function (response) {
//                            loadAllBookings();
//                            deferred.resolve(response.data);
//                        },
//                        function (errResponse) {
//                            console.error('Error while removing Booking with id :'+id);
//                            deferred.reject(errResponse);
//                        }
//                    );
//                return deferred.promise;
//            }
//
        }
    ]);