import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../shared/services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
var LoginPageComponent = /** @class */ (function () {
    function LoginPageComponent(auth, router, route) {
        this.auth = auth;
        this.router = router;
        this.route = route;
    }
    LoginPageComponent.prototype.ngOnInit = function () {
        this.form = new FormGroup({
            nickname: new FormControl(null, [Validators.required, Validators.minLength(5)]),
            password: new FormControl(null, [Validators.required, Validators.minLength(6)])
        });
        this.route.queryParams.subscribe(function (params) {
            if (params['registered']) {
                //можете зайти со своими данными
            }
            else if (params['accessDenied']) {
                //сначала авторизуйтесь 
            }
        });
    };
    LoginPageComponent.prototype.ngOnDestroy = function () {
        if (this.aSub) {
            this.aSub.unsubscribe();
        }
    };
    LoginPageComponent.prototype.onSubmit = function () {
        var _this = this;
        this.form.disable();
        this.auth.login(this.form.value).subscribe(function () { return _this.router.navigate['profile-page']; }, function (error) {
            console.warn(error);
            _this.form.enable();
        });
    };
    LoginPageComponent = tslib_1.__decorate([
        Component({
            selector: 'app-login-page',
            templateUrl: './login-page.component.html',
            styleUrls: ['./login-page.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [AuthService,
            Router,
            ActivatedRoute])
    ], LoginPageComponent);
    return LoginPageComponent;
}());
export { LoginPageComponent };
//# sourceMappingURL=login-page.component.js.map