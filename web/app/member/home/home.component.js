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
// import {MemberComponent} from '../member.component';
var post_service_1 = require("./../services/post.service");
var HomeComponent = (function () {
    function HomeComponent(postService) {
        this.postService = postService;
        this.postFormVisible = true;
        this.userSelect = new core_1.EventEmitter();
    }
    HomeComponent.prototype.ngOnInit = function () {
        this._updateFollowedPosts();
        console.log('home', this.username);
    };
    HomeComponent.prototype.addPost = function (postContent) {
        this.postService.addPost(this.username, postContent)
            .then(function () {
            console.log('add post comp', this.username, postContent);
            return this._updateFollowedPosts();
        }.bind(this));
    };
    HomeComponent.prototype._updateFollowedPosts = function () {
        return this.postService.getFollowedPosts(this.username)
            .then(function (followedPosts) {
            console.log('update', followedPosts);
            this.followedPosts = followedPosts;
        }.bind(this));
    };
    HomeComponent.prototype.selectUser = function (otherUsername) {
        this.userSelect.next(otherUsername);
    };
    return HomeComponent;
}());
HomeComponent = __decorate([
    core_1.Component({
        selector: 'member-home',
        inputs: ['username'],
        outputs: ['userSelect'],
        templateUrl: './home.component.html',
        styleUrls: ['./../member.component.css', './home.component.css'],
    }),
    __metadata("design:paramtypes", [post_service_1.PostService])
], HomeComponent);
exports.HomeComponent = HomeComponent;
//# sourceMappingURL=home.component.js.map