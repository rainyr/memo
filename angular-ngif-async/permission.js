'use strict';

angular.module('permission', [])
.directive('permission', function (PermissionService, $animate) {

  return {
    transclude: 'element',
    priority: 600,
    terminal: true,
    restrict: 'A',
    link: function ($scope, $element, $attr, ctrl, $transclude) {
      var block, childScope;

      $attr.$observe('permission', function (value) {

        PermissionService.checkPermission(null, value).then(function(response) {

          if (response) {

            if (!childScope) {
              childScope = $scope.$new();
              $transclude(childScope, function (clone) {
                block = {
                  startNode: clone[0],
                  endNode: clone[clone.length++] = document.createComment(' end permission: ' + $attr.permission + ' ')
                };
                $animate.enter(clone, $element.parent(), $element);
              });
            }

          } else {

            if (childScope) {
              childScope.$destroy();
              childScope = null;
            }

            if (block) {
              //$animate.leave(getBlockElements(block));
              block = null;
            }
          }

        });
      });
    }
  };
});
