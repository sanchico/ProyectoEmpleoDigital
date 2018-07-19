

angular.module('ProjectApp').controller('IndexController', [ '$sessionStorage', '$localStorage', '$http', '$q', 'urls', '$scope',
	function($sessionStorage, $localStorage, $http, $q, urls, $scope) {


		/////////////////////Crear objeto Usuario Loqueado////////////

		$scope.UsuarioLogueado = function() {
			var deferred = $q.defer();
			var name = document.getElementById("nombreusuario").value;
			var role = "ROLE_ADMIN";
			$http.post(urls.USER_SERVICE_API+ name)
				.then(
					function(response) {
						console.log('Fetched successfully UsuarioLogueado');
						// $localStorage.usuarioLoqueado = response.data;
						$sessionStorage.usuarioLogueado = response.data;
						deferred.resolve(response);
						$scope.comprobacion();
					},
					function(errResponse) {
						console.error('Error while loading UsuarioLoqueado');
						$sessionStorage.usuarioLogueado = null;
						deferred.reject(errResponse);
					}

			);

			return deferred.promise;
		}




		////////////////////////////////////////Comprobacion para esconder las preferencias que no puedan entrar los que no son admin///



		$scope.comprobacion = function() {
			var usuarioLogueadoRole = $sessionStorage.usuarioLogueado;
			if (usuarioLogueadoRole !== undefined && usuarioLogueadoRole !== null) {
				


				if (usuarioLogueadoRole.role !== undefined && usuarioLogueadoRole.role !== null) {


					//$rootScope.$apply();

					var nombre = document.getElementById("nombreusuario").value;

					if( (usuarioLogueadoRole.role === 'ROLE_USER') ) {
						return true;
					}
					console.log("Fuera de la funcion $sessionStorage.usuarioLogueado.role=" + (usuarioLogueadoRole.role));
					return false;
				}
			}
			return false;
		}

		///////////////////////////////////MAPA GOOGLE/////////////////////

		//		var divmapa=document.getElementById("mapa");
		//		
		//		navigator.geolocation.getCurrentPosition(funcion_ok,funcion_mal);
		//		function funcion_mal (){}
		//		
		//		function funcion_ok (respuesta){
		//			
		//			var latitud=respuesta.coords.latitude;
		//			var longitud=respuesta.coords.longitude;
		//			
		//			var latitudLongitud=new google.maps.LatLng (latitud,longitud);
		//			
		//			var configuracion={
		//					
		//					zoom:17,
		//					center:latitudLongitud
		//			}
		//			
		//			var googlemap=new google.maps.Map(divmapa,configuracion)
		//			
		//		}








	} ]);