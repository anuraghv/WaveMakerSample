Application.$controller("BoxCallsPageController", ["$scope", function($scope) {
    "use strict";

    /* perform any action on widgets/variables within this block */
    $scope.onPageReady = function() {
        /*
         * variables can be accessed through '$scope.Variables' property here
         * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
         * $scope.Variables.loggedInUser.getData()
         *
         * widgets can be accessed through '$scope.Widgets' property here
         * e.g. to get value of text widget named 'username' use following script
         * '$scope.Widgets.username.datavalue'
         */
    };
    $scope.treeData = [];

    $scope.tree1Select = function($event, $isolateScope, $item, $path) {
        if ($item.type === "file") {
            var svFile = $scope.Variables.svBoxGetEmbedLink;
            svFile.setInput("fileid", $item.id);
            svFile.id = $item.id;
            svFile.invoke();
            return;
        }
        if ($item.children !== undefined) {
            return;
        }

        var sv = $scope.Variables.svBoxGetFolderItems;
        sv.setInput("folderid", $item.id);
        sv.id = $item.id;
        sv.invoke();
        $scope.activeTreeElement = $item;
    };

    $scope.GetFolderItemsonBeforeDatasetReady = function(variable, data) {
        var newData = [];
        var rootFolder = {};
        rootFolder.label = "All Items";
        rootFolder.icon = "fa fa-folder";
        rootFolder.id = 0;
        rootFolder.children = [];

        if (data !== undefined && data !== null) {
            for (var i = 0; i < data.entries.length; i++) {
                var entry = data.entries[i];
                var folder = {};
                folder.label = entry.name;
                folder.icon = (entry.type === "folder") ? "fa fa-folder" : "wi wi-picture-as-pdf";
                folder.id = entry.id;
                folder.type = entry.type;
                rootFolder.children.push(folder);
            }
        }

        newData.push(rootFolder);
        $scope.treeData = newData;
        return newData;
    };


    $scope.button1Click = function($event, $isolateScope) {
        var sv = $scope.Variables.GetFolderItems;
        sv.setInput("folderid", 0);
        sv.invoke();
    };


    $scope.svBoxGetFolderItemsonBeforeDatasetReady = function(variable, data) {
        if (variable.id === undefined || variable.id === null) {
            return;
        }
        //console.log($scope.treeData[0]);
        var node = $scope.getNodeWithId($scope.treeData[0], variable.id);
        if (node === null) {
            return;
        }
        if (data !== undefined && data !== null) {
            for (var i = 0; i < data.entries.length; i++) {
                var entry = data.entries[i];
                var folder = {};
                folder.label = entry.name;
                folder.icon = (entry.type === "folder") ? "fa fa-folder" : "wi wi-picture-as-pdf";
                folder.id = entry.id;
                folder.type = entry.type;
                if (node.children === undefined || node.children === null) {
                    node.children = [];
                }
                node.children.push(folder);
            }
        }
    };

    $scope.getNodeWithId = function(treeD, id) {
        console.log(id);
        console.log(treeD.id);
        if (treeD.id === id + "") {
            return treeD;
        } else {
            var children = treeD.children;
            if (children !== undefined && children !== null) {
                for (var i = 0; i < children.length; i++) {
                    var nodeWithId = $scope.getNodeWithId(children[i], id);
                    if (nodeWithId !== null) {
                        return nodeWithId;
                    }
                }
            }
        }
        return null;
    };

}]);