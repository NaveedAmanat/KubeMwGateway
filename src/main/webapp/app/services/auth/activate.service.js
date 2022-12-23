(function () {
    'use strict';

    angular
        .module('kashafgatewayApp')
        .factory('Activate', Activate);

    Activate.$inject = ['$resource'];

    function Activate($resource) {
        var service = $resource('api/activate', {}, {
            'get': {method: 'GET', params: {}, isArray: false}
        });

        return service;
    }
})();
