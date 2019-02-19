import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormGroup, FormControl,Validators} from '@angular/forms';
import {AuthService} from '../shared/services/auth.service'
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrls: ['./register-page.component.css']
})
export class RegisterPageComponent implements OnInit, OnDestroy {

  form: FormGroup
  aSub: Subscription

  constructor(private auth: AuthService) { }

  ngOnInit() {
    this.form = new FormGroup({
      nickname: new FormControl(null,[Validators.required,Validators.minLength(5)]),
      email: new FormControl(null,[Validators.required, Validators.email]),
      password: new FormControl(null,[Validators.required, Validators.minLength(6)])
    })
  }

  ngOnDestroy(){
    if (this.aSub){
      this.aSub.unsubscribe()
    }
  }

  onSubmit(){
    this.form.disable()
    this.aSub = this.auth.register(this.form.value).subscribe(
      () => console.log('PersonInterface success')/*this.router.navigate['url']*/,
      error => {
        console.warn(error);
        this.form.enable()
      }
    )
    
  }
}
