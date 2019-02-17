import { Component, OnInit } from '@angular/core';
import {FormGroup, FormControl, Validators} from '@angular/forms';
import {AuthService} from '../shared/services/auth.service'

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  form: FormGroup;

  constructor(private auth: AuthService) {

  }

  ngOnInit() {
    this.form = new FormGroup({
      nickname: new FormControl(null,[Validators.required,Validators.minLength(5)]),
      password: new FormControl(null,[Validators.required, Validators.minLength(6)])
    })
  }
  onSubmit(){
    this.auth.login(this.form.value).subscribe(
      () => console.log('Login succes'),
      error => {
        console.warn(error);
      }
    )
  }

}
