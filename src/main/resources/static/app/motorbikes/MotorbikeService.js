'use strict';

angular.module('ProjectApp').factory('MotorbikeService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {

            var factory = {
                loadAllMotorbikes: loadAllMotorbikes,
                getAllMotorbikes: getAllMotorbikes,
                getMotorbike: getMotorbike,
                createMotorbike: createMotorbike,
                updateMotorbike: updateMotorbike,
                removeMotorbike: removeMotorbike
            };

            return factory;

            function loadAllMotorbikes() {
                console.log('Fetching all motorbikes');
                var deferred = $q.defer();
                $http.get(urls.MOTORBIKE_SERVICE_API )
                    .then(
                        function (response) {
                            console.log('Fetched successfully all motorbikes'+response.data);
                            
                            $localStorage.motorbikes = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading motorbikes');
                            $localStorage.motorbikes = null;
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllMotorbikes(){
                return $localStorage.motorbikes;
            }

            function getMotorbike(id) {
                console.log('Fetching Motorbike with id :'+id);
                var deferred = $q.defer();
                $http.get(urls.MOTORBIKE_SERVICE_API + id)
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

            function createMotorbike(motorbike) {
                console.log('Creating Motorbike');
                var deferred = $q.defer();
                $http.post(urls.MOTORBIKE_SERVICE_API, motorbike)
                    .then(
                        function (response) {
                            loadAllMotorbikes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                           console.error('Error while creating Motorbike : '+errResponse.data.errorMessage);
                           deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function updateMotorbike(motorbike, id) {
                console.log('Updating Motorbike with id '+id);
                var deferred = $q.defer();
                $http.put(urls.MOTORBIKE_SERVICE_API+ id, motorbike)
                    .then(
                        function (response) {
                            loadAllMotorbikes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while updating Motorbike with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function removeMotorbike(id) {
                console.log('Removing Motorbike with id '+id);
                var deferred = $q.defer();
                $http.delete(urls.MOTORBIKE_SERVICE_API + id)
                    .then(
                        function (response) {
                            loadAllMotorbikes();
                            deferred.resolve(response.data);
                        },
                        function (errResponse) {
                            console.error('Error while removing Motorbike with id :'+id);
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

        }
    ]);