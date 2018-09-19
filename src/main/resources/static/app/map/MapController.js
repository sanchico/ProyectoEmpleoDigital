// JavaScript source code
 
function initMap() {
	//Opciones del map
    var OpcionesMapa = {
        center: new google.maps.LatLng(37.992303, -1.130539),
        mapTypeId: google.maps.MapTypeId.ROADMAP, //ROADMAP  SATELLITE HYBRID TERRAIN
        mapMaker: true,
        zoom: 17
    };
    
    var map, infoWindow;
   
    //constructor del mapa
    map = new google.maps.Map(document.getElementById('mapa'), OpcionesMapa);
	infoWindow = new google.maps.InfoWindow;

    // HTML5 geolocation (Debido a seguridad de google han decidido hacer que el geolocation funcione
	// solo si tienes el protocolo HTTPS o solo sea atraves de localhost)
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function(position) {
        var pos = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };

        infoWindow.setPosition(pos);
        infoWindow.setContent('Location found.');
        infoWindow.open(map);
        map.setCenter(pos);
      }, function() {
        handleLocationError(true, infoWindow, map.getCenter());
      });
    } else {
      // En caso de que exista fallo con la localizacion mostrará lo siguiente
      handleLocationError(false, infoWindow, map.getCenter());
    }
  

  function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
                          'Error: The Geolocation service failed.' :
                          'Error: Your browser doesn\'t support geolocation.');
    infoWindow.open(map);
  }

  //Posicionamiento de las motos en el mapa atraves del JSON creado.
   $.getJSON("http://"+location.host+"/api/user/booking/", function(json1){
    $.each(json1, function(key, data){
      var latLng = new google.maps.LatLng(data.latitude, data.longitude)
      var marker = new google.maps.Marker({
        position: latLng,
        map: map,
        icon: "/custom/iconMap/moto.png",
        title: data.model
    });
    var details = data.model;

        bindInfoWindow(marker, map, infoWindow, details)
  });
    });
  }
    function bindInfoWindow(marker, map, infoWindow, strDescription) {
      google.maps.event.addListener(marker, 'click', function () {
          infoWindow.setContent(strDescription);
          infoWindow.open(map, marker);
      



   });

}

