


//Este js controla con la directiva Ng-init el index.html para saber si estamos en modo administrador o en modo user, ocultando la
//la zona de administador cuando eres usuario

angular.module('ProjectApp').controller('IndexController', [ '$sessionStorage', '$localStorage', '$http', '$q', 'urls', '$scope','$location',
	function($sessionStorage, $localStorage, $http, $q, urls, $scope,$location) {


		/////////////////////Crear objeto Usuario Loqueado////////////

		$scope.UsuarioLogueado = function() {
			var deferred = $q.defer();
			var name = document.getElementById("nombreusuario").value;
			var role = "ROLE_ADMIN";
			$http.post(urls.USER_SERVICE_API+ name)
				.then(
					function(response) {
						console.log('Fetched successfully UsuarioLogueado');

						$sessionStorage.usuarioLogueado = response.data;
						deferred.resolve(response);
						$scope.comprobacion();
						motoDispoble();
						console.log('Fetched successfully UsuarioLogueado'+$scope.motoDispobile);
					},
					function(errResponse) {
						console.error('Error while loading UsuarioLoqueado');
						$sessionStorage.usuarioLogueado = null;
						deferred.reject(errResponse);
					}

			);

			return deferred.promise;
		}
		
		
		
		
		 function motoDispoble (){
				


			
			var deferred = $q.defer();
			$http.post(urls.BOOKING_SERVICE_API)
				.then(
					function(response) {
						console.log('Fetched successfully motoDisponible');
						
						var comprobacion = response.data;
						
						deferred.resolve(response);
						
						
						if(comprobacion === null || comprobacion === undefined  )

							{
							

							$scope.motoList=true;
							$scope.motoshowMap=false;
						
							}else{	
								
								
								$scope.motoList=false;
								$scope.motoshowMap=true;
								}
						
						
						
						
					},
					function(errResponse) {
						console.error('Error while loading motoDisponible');
						$sessionStorage.usuarioLogueado = null;
						deferred.reject(errResponse);
						
						
						
					}

			);


		}
			
			
				
					
			





		////////////////////////////////////////Comprobacion para esconder las preferencias que no puedan entrar los que no son admin///



		$scope.comprobacion = function() {
			var usuarioLogueadoRole = $sessionStorage.usuarioLogueado;
			if (usuarioLogueadoRole !== undefined && usuarioLogueadoRole !== null) {
				


				if (usuarioLogueadoRole.role !== undefined && usuarioLogueadoRole.role !== null) {


					

					var nombre = document.getElementById("nombreusuario").value;

					if( (usuarioLogueadoRole.role === 'ROLE_USER') ) {
						return true;
					}

					return false;
				}
			}
			return false;
		}

		







	} ]);