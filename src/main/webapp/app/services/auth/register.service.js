(function () {
    'use strict';

    angular
        .module('kashafgatewayApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register($resource) {
        return $resource('api/register', {}, {});
    }
})();
