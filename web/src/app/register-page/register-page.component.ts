import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,Validators} from '@angular/forms';
import {AuthService} from '../shared/services/auth.service'

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit {

  regform: FormGroup;

  constructor(private auth: AuthService) { }

  ngOnInit() {
    this.regform = new FormGroup({
      nickname: new FormControl(null,[Validators.required,Validators.minLength(5)]),
      email: new FormControl(null,[Validators.required, Validators.email]),
      password: new FormControl(null,[Validators.required, Validators.minLength(6)])
    })
  }

  onSubmit(){
    this.regform.disable
    
  }
}
