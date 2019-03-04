import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../shared/services/auth.service'
import { Subscription } from 'rxjs';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit, OnDestroy {

  form: FormGroup
  aSub: Subscription
  constructor(private auth: AuthService,
    private router: Router,
    private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.form = new FormGroup({
      j_username: new FormControl(localStorage.getItem('curNickname') == null ? null : localStorage.getItem('curNickname'), [Validators.required, Validators.minLength(5)]),
      j_password: new FormControl(null, [Validators.required, Validators.minLength(6)])
    })

  }

  ngOnDestroy() {
    if (this.aSub) {
      this.aSub.unsubscribe()
    }
  }

  onSubmit() {
    let nickname = localStorage.getItem('curNickname');
    this.form.disable()

    this.auth.login(this.form.value).subscribe(
      //() => this.router.navigate(['/user', nickname]),
      () => this.router.navigate(['/welcome']),
      error => {
        //TODO: for debug only
        this.router.navigate(['/welcome']);
        console.warn(error);
        this.form.enable()
      }
    )
  }

}
