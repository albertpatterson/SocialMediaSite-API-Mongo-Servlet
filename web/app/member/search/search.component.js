"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require("@angular/core");
var personal_data_service_1 = require("./../services/personal-data.service");
var SearchComponent = (function () {
    function SearchComponent(personalDataService) {
        this.personalDataService = personalDataService;
        this.userSelect = new core_1.EventEmitter();
    }
    SearchComponent.prototype._selectUser = function (name) {
        console.log('selected ' + name);
        this.userSelect.next(name);
    };
    SearchComponent.prototype.ngOnChanges = function () {
        console.log('searchpattern', this.searchPattern);
        this.personalDataService.searchUserData(this.username, this.searchPattern)
            .then(function (users) {
            this.userGroups = this._toGrid(users, 3);
        }.bind(this));
    };
    SearchComponent.prototype._toGrid = function (items, nCols) {
        var grid = [];
        var max = nCols * Math.ceil(items.length / nCols);
        for (var idx = 0; idx < max; idx += nCols) {
            grid.push(items.slice(idx, idx + nCols));
        }
        return grid;
    };
    return SearchComponent;
}());
SearchComponent = __decorate([
    core_1.Component({
        selector: 'member-search',
        inputs: ['username', 'searchPattern'],
        outputs: ['userSelect'],
        templateUrl: './search.component.html',
        styleUrls: [
            './../member.component.css',
            './search.component.css'
        ]
    }),
    __metadata("design:paramtypes", [personal_data_service_1.PersonalDataService])
], SearchComponent);
exports.SearchComponent = SearchComponent;
//# sourceMappingURL=search.component.js.map