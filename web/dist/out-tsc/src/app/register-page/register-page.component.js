import * as tslib_1 from "tslib";
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../shared/services/auth.service';
var RegisterPageComponent = /** @class */ (function () {
    function RegisterPageComponent(auth) {
        this.auth = auth;
    }
    RegisterPageComponent.prototype.ngOnInit = function () {
        this.form = new FormGroup({
            nickname: new FormControl(null, [Validators.required, Validators.minLength(5)]),
            email: new FormControl(null, [Validators.required, Validators.email]),
            password: new FormControl(null, [Validators.required, Validators.minLength(6)])
        });
    };
    RegisterPageComponent.prototype.ngOnDestroy = function () {
        if (this.aSub) {
            this.aSub.unsubscribe();
        }
    };
    RegisterPageComponent.prototype.onSubmit = function () {
        var _this = this;
        this.form.disable();
        this.aSub = this.auth.register(this.form.value).subscribe(function () { return console.log('Registration success'); } /*this.router.navigate['url']*/, function (error) {
            console.warn(error);
            _this.form.enable();
        });
    };
    RegisterPageComponent = tslib_1.__decorate([
        Component({
            selector: 'app-register-page',
            templateUrl: './register-page.component.html',
            styleUrls: ['./register-page.component.css']
        }),
        tslib_1.__metadata("design:paramtypes", [AuthService])
    ], RegisterPageComponent);
    return RegisterPageComponent;
}());
export { RegisterPageComponent };
//# sourceMappingURL=register-page.component.js.map