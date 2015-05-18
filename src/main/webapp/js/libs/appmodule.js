(function () {
    var app = angular.module('modone', ['ngRoute']);

    app.config(function ($routeProvider) {
        $routeProvider

                // route for the about page
                .when('/new', {
                    templateUrl: 'registro.html'

                })

                // route for the contact page
                .when('/list', {
                    templateUrl: 'catalogo.html'

                })
                
                // route for the about page
                .when('/content', {
                    templateUrl: 'contenidoProveedor.html'

                });

    });
    
    app.controller('controlregistro',
        function($scope,$http){
            
            $scope.categorias=[];
            
            $scope.descuentos=[];
            
            $scope.proveedores=[];
            
            this.detalleProducto={
                nombre:'',
                precio:0
            };
            
            this.detalleCategoria={
                idCategorias:018222,
                categoria:'NUEVA CATEGORIA',
                descripcion:'otra categoria bb'
            };
            
            this.producto={
                categoria:null,
                descuentos:null,
                descripcion:'',
                precioLista:0,
                imagen:'',
                metaDatos:[],
                proveedores:null
            };
            
            this.categoriaSeleccionada={
                idCategorias:0,
                categoria:'',
                descripcion:''
            };
            
            this.descuentoSeleccionado={
                idPromociones:0,
                porcentaje:0,
                fechaInicio:'',
                fechaFinal:'',
                descripcion:''
            };
            
            this.proveedor={
                idProveedores:2047,
                lugares:null,
                razonSocial:'fabrica de lacteos',
                direccion:'calle 90 32-45',
                contactoTelefonico:'5267656',
                sitioWeb:'ww.ss',
                email:'email',
                usuario:'',
                contrasena:'',
                productos:null
            };
            
            this.proveedores = this.proveedor;
            
            $scope.cargarCategorias=function(){
               $http.get('rest/categorias').success(
                    function (data, status, headers, config) {
                        $scope.categorias = data;
                    }).error(function(data, status, headers, config) {
                        alert('error');
                      }
                    );
            };
            
            $scope.cargarDescuentos=function(){
               $http.get('rest/descuentos').success(
                    function (data, status, headers, config) {
                        $scope.descuentos = data;
                    }).error(function(data, status, headers, config) {
                        alert('error');
                      }
                    );
            };
            
            this.registrar=function(){
               //accion
                this.producto.proveedores = this.proveedores;
                this.producto.categoria = this.categoriaSeleccionada;
                this.producto.descuentos = this.descuentoSeleccionado;
                $http.post('rest/productos', this.producto).
                    success(function (data, status, headers, config) {
                        alert('success!');
                    }).
                   error(function (data, status, headers, config) {
                        alert('error!');
                   });   
            };
            
            $scope.cargarCategorias();
            
            $scope.cargarDescuentos();
        }
    );
    
    app.controller('todosLosProductos',
       function ($http,$scope){
            $scope.productos=[];
            
            this.consultar=function(){
                $http.get('rest/products').success(
                    function (data, status, headers, config) {
                        $scope.productos = data;
                        }
                    ).error(function(data, status, headers, config) {
                            alert('error');
                      }
                    );
            };
       }
       
    );

})();





