'use strict';

/**
 * A light version of ng-repeat
 */

angular.module('repeat', [])
  .directive('repeat', function(){
    return {
      scope: {
        repeat: '=',
        limit: '@'
      },
      restrict: 'A',
      transclude: 'element',

      link: function(scope, elm, attrs, ctrl, transclude) {

        var newScope, current = elm;
        var max = parseInt(scope.limit);

        var cloner = function (clone) {
          current.after(clone);
          current = clone;
        };

        scope.$watch('repeat', function (newVal) {
          if (newVal) {
            for (var i = 0; i < newVal.length; i++) {

              if (max && i >= max) {
                break;
              }

              newScope = scope.$new();//new scope is isolated scope

              newScope.content = newVal[i];

              transclude(newScope, cloner);
            }
          }
        });
      }
    };
  });